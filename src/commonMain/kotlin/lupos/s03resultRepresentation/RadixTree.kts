#!/bin/kscript

class RadixTree {

    var debugMap = mutableMapOf<String, Int>()
    var next_key = null_key + 1
    var pages = Array<ByteArray>(2048) { ByteArray(1024) }
    var pagesCounter = 2
    var rootNode = pages[1]
    var rootNodeOffset = 0
    var rootNodePtr = 512
    fun convertUTF32ToUTF8(data: IntArray, dataLength: Int, outBuffer: ByteArray): Int {
        var len = 0
        for (i in 0 until dataLength) {
            val v = data[i]
            if (v <= 0x7f) {
                outBuffer[len++] = v.toByte()
            } else if (v <= 0x07ff) {
                outBuffer[len++] = ((v shr 6) and 0x1f).toByte()
                outBuffer[len++] = (v and 0x3f).toByte()
            } else if (v <= 0x0ffff) {
                outBuffer[len++] = ((v shr 12) and 0xf).toByte()
                outBuffer[len++] = ((v shr 6) and 0x3f).toByte()
                outBuffer[len++] = (v and 0x3f).toByte()
            } else {
                outBuffer[len++] = ((v shr 18) and 0x7).toByte()
                outBuffer[len++] = ((v shr 12) and 0x3f).toByte()
                outBuffer[len++] = ((v shr 6) and 0x3f).toByte()
                outBuffer[len++] = (v and 0x3f).toByte()
            }
        }
        return len
    }

    var allocedBytes = 0
    fun allocBytes(len: Int): Int {
        allocedBytes += len
        return (pagesCounter++) shl 9
    }

    fun freeBytes(ptr: Int) {
        val nodeOff = (ptr and 0x1ff) shl 4
        var node = pages[ptr shr 9]
        allocedBytes -= readConsumedBytes(node, nodeOff)
    }

    companion object {
        const val null_key = 0
        const val null_ptr = 0

        const val off_ptrA = 1 //if ptr exist always the same offset
        const val off_ptrB = 5 //if ptr exist always the same offset

//header 0x0 stores PtrA, PtrB, len, data
//header 0x1 stores PtrA, PtrB, len, key, data
//header 0x2 stores len, key, data

        const val off_0_len = 9
        const val off_0_data = 11

        const val off_1_len = 9
        const val off_1_key = 11
        const val off_1_data = 15

        const val off_2_len = 1
        const val off_2_key = 3
        const val off_2_data = 7
    }


    fun readHeader(node: ByteArray, offset: Int): Int {
        return node.readInt1(offset)
    }

    fun readDataOffset(node: ByteArray, offset: Int): Int {
        val header = readHeader(node, offset)
        when (header) {
            0x0 -> return offset + off_0_data
            0x1 -> return offset + off_1_data
            0x2 -> return offset + off_2_data
            else -> throw Exception("unknown header $header")
        }
    }

    fun readLen(node: ByteArray, offset: Int): Int {
        val header = readHeader(node, offset)
        when (header) {
            0x0 -> return node.readInt2(offset + off_0_len)
            0x1 -> return node.readInt2(offset + off_1_len)
            0x2 -> return node.readInt2(offset + off_2_len)
            else -> throw Exception("unknown header $header")
        }
    }

    fun readConsumedBytes(node: ByteArray, offset: Int): Int {
        val header = readHeader(node, offset)
        when (header) {
            0x0 -> return off_0_data + ((node.readInt2(offset + off_0_len) + 0x7) shr 3)
            0x1 -> return off_1_data + ((node.readInt2(offset + off_1_len) + 0x7) shr 3)
            0x2 -> return off_2_data + ((node.readInt2(offset + off_2_len) + 0x7) shr 3)
            else -> throw Exception("unknown header $header")
        }
    }

    fun readPtrA(node: ByteArray, offset: Int): Int {
        val header = readHeader(node, offset)
        when (header) {
            0x0 -> return node.readInt4(offset + off_ptrA)
            0x1 -> return node.readInt4(offset + off_ptrA)
            0x2 -> return null_ptr
            else -> throw Exception("unknown header $header")
        }
    }

    fun readPtrB(node: ByteArray, offset: Int): Int {
        val header = readHeader(node, offset)
        when (header) {
            0x0 -> return node.readInt4(offset + off_ptrB)
            0x1 -> return node.readInt4(offset + off_ptrB)
            0x2 -> return null_ptr
            else -> throw Exception("unknown header $header")
        }
    }

    fun readKey(node: ByteArray, offset: Int): Int {
        val header = readHeader(node, offset)
        when (header) {
            0x0 -> return null_key
            0x1 -> return node.readInt4(offset + off_1_key)
            0x2 -> return node.readInt4(offset + off_2_key)
            else -> throw Exception("unknown header $header")
        }
    }

    fun createChild(data: ByteArray, dataOff: Int, len: Int, key: Int, currentDepth: Int, ptrA: Int = null_ptr, ptrB: Int = null_ptr): Int {
        var header = 0x0
        if (key != null_key) {
            if (ptrA == null_ptr && ptrB == null_ptr) {
                header = 0x2
            } else {
                header = 0x1
            }
        }
        when (header) {
            0x0 -> {
                val nodePtr = allocBytes(off_0_data + ((len + 0x7) shr 3))
                val nodeOff = (nodePtr and 0x1ff) shl 4
                var node = pages[nodePtr shr 9]
                node.writeInt1(nodeOff, header)
                node.writeInt4(nodeOff + off_ptrA, ptrA)
                node.writeInt4(nodeOff + off_ptrB, ptrB)
                node.writeInt2(nodeOff + off_0_len, len)
                val b = (len + 0x7) shr 3
                val dataOffOut = nodeOff + off_0_data
                for (i in 0 until b) {
                    node[dataOffOut + i] = data[dataOff + i]
                }
                return nodePtr
            }
            0x1 -> {
                val nodePtr = allocBytes(off_1_data + ((len + 0x7) shr 3))
                val nodeOff = (nodePtr and 0x1ff) shl 4
                var node = pages[nodePtr shr 9]
                node.writeInt1(nodeOff, header)
                node.writeInt4(nodeOff + off_ptrA, ptrA)
                node.writeInt4(nodeOff + off_ptrB, ptrB)
                node.writeInt2(nodeOff + off_1_len, len)
                node.writeInt4(nodeOff + off_1_key, key)
                val b = (len + 0x7) shr 3
                val dataOffOut = nodeOff + off_1_data
                for (i in 0 until b) {
                    node[dataOffOut + i] = data[dataOff + i]
                }
                return nodePtr
            }
            0x2 -> {
                val nodePtr = allocBytes(off_2_data + ((len + 0x7) shr 3))
                val nodeOff = (nodePtr and 0x1ff) shl 4
                var node = pages[nodePtr shr 9]
                node.writeInt1(nodeOff, header)
                node.writeInt2(nodeOff + off_2_len, len)
                node.writeInt4(nodeOff + off_2_key, key)
                val b = (len + 0x7) shr 3
                val dataOffOut = nodeOff + off_2_data
                for (i in 0 until b) {
                    node[dataOffOut + i] = data[dataOff + i]
                }
                return nodePtr
            }
            else -> {
                throw Exception("unkwnown header $header")
            }
        }
    }


    fun shiftLeft(inBuffer: ByteArray, outBuffer: ByteArray, inBufferOffset: Int, inBufferLength: Int) {
        var offIn = inBufferOffset shr 3
        val lenIn = (inBufferOffset + inBufferLength + 0x7) shr 3
        var offOut = 0
        var toShift = inBufferOffset and 0x7
        when (toShift) {
            0 -> {
                while (offIn < lenIn) {
                    outBuffer[offOut] = inBuffer[offIn]
                    offIn++
                    offOut++
                }
            }
            1 -> {
                while (offIn < lenIn) {
                    outBuffer[offOut] = (((inBuffer[offIn].toInt() shl 1) and 0xfe) or ((inBuffer[offIn + 1].toInt() shr 7) and 0x01)).toByte()
                    offIn++
                    offOut++
                }
            }
            2 -> {
                while (offIn < lenIn) {
                    outBuffer[offOut] = (((inBuffer[offIn].toInt() shl 2) and 0xfc) or ((inBuffer[offIn + 1].toInt() shr 6) and 0x03)).toByte()
                    offIn++
                    offOut++
                }
            }
            3 -> {
                while (offIn < lenIn) {
                    outBuffer[offOut] = (((inBuffer[offIn].toInt() shl 3) and 0xf8) or ((inBuffer[offIn + 1].toInt() shr 5) and 0x07)).toByte()
                    offIn++
                    offOut++
                }
            }
            4 -> {
                while (offIn < lenIn) {
                    outBuffer[offOut] = (((inBuffer[offIn].toInt() shl 4) and 0xf0) or ((inBuffer[offIn + 1].toInt() shr 4) and 0x0f)).toByte()
                    offIn++
                    offOut++
                }
            }
            5 -> {
                while (offIn < lenIn) {
                    outBuffer[offOut] = (((inBuffer[offIn].toInt() shl 5) and 0xe0) or ((inBuffer[offIn + 1].toInt() shr 3) and 0x1f)).toByte()
                    offIn++
                    offOut++
                }
            }
            6 -> {
                while (offIn < lenIn) {
                    outBuffer[offOut] = (((inBuffer[offIn].toInt() shl 6) and 0xc0) or ((inBuffer[offIn + 1].toInt() shr 2) and 0x3f)).toByte()
                    offIn++
                    offOut++
                }
            }
            7 -> {
                while (offIn < lenIn) {
                    outBuffer[offOut] = (((inBuffer[offIn].toInt() shl 7) and 0x80) or ((inBuffer[offIn + 1].toInt() shr 1) and 0x7f)).toByte()
                    offIn++
                    offOut++
                }
            }
        }
    }

    fun equalBits(dataA: ByteArray, dataB: ByteArray, offsetA: Int, offsetB: Int, len: Int): Int {
        val lenBytes = (len + 0x7) shr 3
        var b = 0
        while (b < lenBytes) {
            if (dataA[offsetA + b] != dataB[b]) {
                break
            }
            b++
        }
        if (b == lenBytes) {
            return len
        } else {
            var d = 0
            while (d < 8) {
                if (dataA[offsetA + b].toInt() shr d == dataB[b].toInt() shr d) {
                    break
                }
                d++
            }
            val res = (b shl 3) + 8 - d
            if (res > len) {
                return len
            }
            return res
        }
    }

    fun print() {
        debugMap.clear()
        var usedBytes = print(rootNodePtr, "")
        println("bytes consumed $usedBytes ($allocedBytes)")
    }

    fun print(pagePtr: Int, prefix: String): Int {
        var usedBytes = 0
        val currentPage = pages[pagePtr shr 9]
        val currentPageOffset = (pagePtr and 0x1ff) shl 4
        val len = readLen(currentPage, currentPageOffset)
        var s = ""
        val x = (len + 0x7) shr 3
        usedBytes += readConsumedBytes(currentPage, currentPageOffset)
        val dataOff = readDataOffset(currentPage, currentPageOffset)
        for (i in 0 until x) {
            s += (currentPage[dataOff + i].toInt() and 0xff).toString(2).padStart(8, '0')
        }
        s = s.substring(0, len)
        val key = readKey(currentPage, currentPageOffset)
        val value = prefix + s
        if (key != null_key) {
            if (debugMap[value] != null) {
                throw Exception("write twice ${value} ${debugMap[value]} $key")
            }
            debugMap[value] = key
        }
        val header = readHeader(currentPage, currentPageOffset)
        println(value + " :: " + key + " = " + pagePtr + " ? " + header)
        var ptr = readPtrA(currentPage, currentPageOffset)
        if (ptr != null_ptr) {
            usedBytes += print(ptr, value + "0")
        }
        ptr = readPtrB(currentPage, currentPageOffset)
        if (ptr != null_ptr) {
            usedBytes += print(ptr, value + "1")
        }
        return usedBytes
    }


    fun updatePointer(parentPtr: Int, currentPtr: Int, newPtr: Int) {
        if (parentPtr == null_ptr) {
            rootNode = pages[newPtr shr 9]
            rootNodeOffset = (newPtr and 0x1ff) shl 4
            rootNodePtr = newPtr
        } else {
            val parent = pages[parentPtr shr 9]
            val parentOff = (parentPtr and 0x1ff) shl 4
            if (parent.readInt1(parentOff) == 2) {
                throw Exception("invalud header")
            }
            if (parent.readInt4(parentOff + off_ptrA) == currentPtr) {
                parent.writeInt4(parentOff + off_ptrA, newPtr)
            } else {
                parent.writeInt4(parentOff + off_ptrB, newPtr)
            }
        }
    }

    fun insertUTF32(inData: IntArray, inDataLength: Int): Int {
        var pageBuffer = ByteArray(rootNode.size)
        var data = ByteArray(0)
        var data1 = ByteArray(0)
        var data2 = data1
        if (data.size < inDataLength shl 2) {
            data = ByteArray(inDataLength shl 2)
            data1 = ByteArray(inDataLength shl 2)
        }
        var inLen = convertUTF32ToUTF8(inData, inDataLength, data) shl 3
        var parentParentPtr = null_ptr
        var parentPtr = null_ptr
        var currentPage = rootNode
        var currentPageOffset = rootNodeOffset
        var currentPtr = rootNodePtr
        var currentDepth = 0
        while (true) {
            val len = readLen(currentPage, currentPageOffset)
            val dataOff = readDataOffset(currentPage, currentPageOffset)
            var common: Int = 0
            if (len <= inLen) {
                common = equalBits(currentPage, data, dataOff, 0, len)
            } else {
                common = equalBits(currentPage, data, dataOff, 0, inLen)
            }
            if (common == inLen) {
                if (common == len) {
                    val k = readKey(currentPage, currentPageOffset)
                    if (k == null_key) {
                        val k2 = next_key++
                        val ptrA = readPtrA(currentPage, currentPageOffset)
                        val ptrB = readPtrB(currentPage, currentPageOffset)
                        val newPtr = createChild(currentPage, dataOff, len, k2, currentDepth, ptrA, ptrB)
                        freeBytes(currentPtr)
                        updatePointer(parentPtr, currentPtr, newPtr)
                        println("return A")
                        return k2
                    } else {
                        println("return B")
                        return k
                    }
                } else {
                    val ptrA = readPtrA(currentPage, currentPageOffset)
                    val ptrB = readPtrB(currentPage, currentPageOffset)
                    var currentKey = readKey(currentPage, currentPageOffset)
                    var newKey = next_key++
                    var newLen = common
                    val significantByte = (common) shr 3
                    val significantBitNumber = ((15 - ((common) and 0x7)) and 0x7)
                    val significantBit = (currentPage[dataOff + significantByte].toInt() shr significantBitNumber) and 0x1
                    common += 1
                    val splitChildLen = len - common
                    shiftLeft(currentPage, pageBuffer, (dataOff shl 3) + common, splitChildLen)
                    val splitChildPage = createChild(pageBuffer, 0, splitChildLen, currentKey, currentDepth, ptrA, ptrB)
                    val off = dataOff + (common shr 3)
                    when (common % 8) {
                        1 -> currentPage[off] = (currentPage[off].toInt() and 0x80).toByte()
                        2 -> currentPage[off] = (currentPage[off].toInt() and 0xc0).toByte()
                        3 -> currentPage[off] = (currentPage[off].toInt() and 0xe0).toByte()
                        4 -> currentPage[off] = (currentPage[off].toInt() and 0xf0).toByte()
                        5 -> currentPage[off] = (currentPage[off].toInt() and 0xf8).toByte()
                        6 -> currentPage[off] = (currentPage[off].toInt() and 0xfc).toByte()
                        7 -> currentPage[off] = (currentPage[off].toInt() and 0xfe).toByte()
                    }
                    if (significantBit == 0) {
                        val newPtr = createChild(currentPage, dataOff, newLen, newKey, currentDepth, splitChildPage, null_ptr)
                        freeBytes(currentPtr)
                        updatePointer(parentPtr, currentPtr, newPtr)
                    } else {
                        val newPtr = createChild(currentPage, dataOff, newLen, newKey, currentDepth, null_ptr, splitChildPage)
                        freeBytes(currentPtr)
                        updatePointer(parentPtr, currentPtr, newPtr)
                    }
                    println("return C")
                    return newKey

                }
            } else if (common == len) {
                val significantByte = common shr 3
                val significantBitNumber = ((15 - (common and 0x7)) and 0x7)
                val significantBit = (data[significantByte].toInt() shr significantBitNumber) and 0x1
                common += 1
                shiftLeft(data, data1, common, inLen)
                inLen -= common
                if (significantBit == 0) {
                    val key = readKey(currentPage, currentPageOffset)
                    val ptrA = readPtrA(currentPage, currentPageOffset)
                    val ptrB = readPtrB(currentPage, currentPageOffset)
                    if (ptrA == null_ptr) {
                        var kk = next_key++
                        val pagePtr = createChild(data1, 0, inLen, kk, currentDepth)
                        val newPtr = createChild(currentPage, dataOff, len, key, currentDepth, pagePtr, ptrB)
                        freeBytes(currentPtr)
                        updatePointer(parentPtr, currentPtr, newPtr)
                        println("return D")
                        return kk
                    }
                    currentPage = pages[ptrA shr 9]
                    currentPageOffset = (ptrA and 0x1ff) shl 4
                    currentDepth += common + 1
                    parentParentPtr = parentPtr
                    parentPtr = currentPtr
                    currentPtr = ptrA
                    data2 = data1
                    data1 = data
                    data = data2
                } else {
                    val key = readKey(currentPage, currentPageOffset)
                    val ptrA = readPtrA(currentPage, currentPageOffset)
                    val ptrB = readPtrB(currentPage, currentPageOffset)
                    if (ptrB == null_ptr) {
                        var kk = next_key++
                        val pagePtr = createChild(data1, 0, inLen, kk, currentDepth)
                        val newPtr = createChild(currentPage, dataOff, len, key, currentDepth, ptrA, pagePtr)
                        freeBytes(currentPtr)
                        updatePointer(parentPtr, currentPtr, newPtr)
                        println("return E")
                        return kk
                    }
                    currentPage = pages[ptrB shr 9]
                    currentPageOffset = (ptrB and 0x1ff) shl 4
                    currentDepth += common + 1
                    parentParentPtr = parentPtr
                    parentPtr = currentPtr
                    currentPtr = ptrB
                    data2 = data1
                    data1 = data
                    data = data2
                }
            } else {
                val significantByte = common shr 3
                val significantBitNumber = ((15 - (common and 0x7)) and 0x7)
                val significantBit = (data[significantByte].toInt() shr significantBitNumber) and 0x1
                shiftLeft(data, data1, common + 1, inLen)
                inLen -= common

                var currentKey = readKey(currentPage, currentPageOffset)
                val ptrA = readPtrA(currentPage, currentPageOffset)
                val ptrB = readPtrB(currentPage, currentPageOffset)

                var newKey = next_key++
                val newPage = createChild(data1, 0, inLen - 1, newKey, currentDepth)
                shiftLeft(currentPage, pageBuffer, ((dataOff) shl 3) + common + 1, len)
                val splitChildLen = len - common - 1
                val splitChildPage = createChild(pageBuffer, 0, splitChildLen, currentKey, currentDepth, ptrA, ptrB)
                val off = dataOff + (common shr 3)
                when (common % 8) {
                    1 -> currentPage[off] = (currentPage[off].toInt() and 0x80).toByte()
                    2 -> currentPage[off] = (currentPage[off].toInt() and 0xc0).toByte()
                    3 -> currentPage[off] = (currentPage[off].toInt() and 0xe0).toByte()
                    4 -> currentPage[off] = (currentPage[off].toInt() and 0xf0).toByte()
                    5 -> currentPage[off] = (currentPage[off].toInt() and 0xf8).toByte()
                    6 -> currentPage[off] = (currentPage[off].toInt() and 0xfc).toByte()
                    7 -> currentPage[off] = (currentPage[off].toInt() and 0xfe).toByte()
                }
                if (significantBit == 0) {
                    val newPtr = createChild(currentPage, dataOff, common, null_key, currentDepth, newPage, splitChildPage)
                    freeBytes(currentPtr)
                    updatePointer(parentPtr, currentPtr, newPtr)
                } else {
                    val newPtr = createChild(currentPage, dataOff, common, null_key, currentDepth, splitChildPage, newPage)
                    freeBytes(currentPtr)
                    updatePointer(parentPtr, currentPtr, newPtr)
                }
                println("return F")
                return newKey
            }
        }
    }
}


inline fun ByteArray.writeInt1(offset: Int, value: Int) {
    this[offset] = (value and 0xFF).toByte()
}

inline fun ByteArray.writeInt2(offset: Int, value: Int) {
    this[offset] = ((value shr 8) and 0xFF).toByte()
    this[offset + 1] = (value and 0xFF).toByte()
}

inline fun ByteArray.writeInt3(offset: Int, value: Int) {
    this[offset] = ((value shr 16) and 0xFF).toByte()
    this[offset + 1] = ((value shr 8) and 0xFF).toByte()
    this[offset + 2] = (value and 0xFF).toByte()
}

inline fun ByteArray.writeInt4(offset: Int, value: Int) {
    this[offset] = ((value shr 24) and 0xFF).toByte()
    this[offset + 1] = ((value shr 16) and 0xFF).toByte()
    this[offset + 2] = ((value shr 8) and 0xFF).toByte()
    this[offset + 3] = (value and 0xFF).toByte()
}

inline fun ByteArray.readInt4(offset: Int): Int {
    return (((this[offset].toInt() and 0xFF) shl 24) or ((this[offset + 1].toInt() and 0xFF) shl 16) or ((this[offset + 2].toInt() and 0xFF) shl 8) or ((this[offset + 3].toInt() and 0xFF)))
}

inline fun ByteArray.readInt3(offset: Int): Int {
    return (((this[offset].toInt() and 0xFF) shl 16) or ((this[offset + 1].toInt() and 0xFF) shl 8) or ((this[offset + 2].toInt() and 0xFF)))
}

inline fun ByteArray.readInt2(offset: Int): Int {
    return (((this[offset].toInt() and 0xFF) shl 8) or ((this[offset + 1].toInt() and 0xFF)))
}

inline fun ByteArray.readInt1(offset: Int): Int {
    return (this[offset].toInt() and 0xFF)
}

val tree = RadixTree()

fun stringToIntArray(str: String): IntArray {
    val s = str.replace(Regex("[^a-zA-Z]"), "")
    val res = IntArray(s.length)
    for (c in 0 until s.length) {
        res[c] = s[c].toInt()
    }
    return res
}

fun convertToUTF8BitStream(arr: IntArray): String {
    var s = ""
    for (i in 0 until arr.size) {
        s += arr[i].toString(2).padStart(8, '0')
    }
    return s
}


tree.print()
var i = 0
val insertMap = mutableMapOf<String, Int>()
java.io.File("/mnt/luposdate-testdata/sp2b/16384/intermediate.dictionary").forEachLine { it ->
    var s = it
    if (s.length > 20) {
        s = s.substring(0, 20)
    }
    println("-----------")
    val arr = stringToIntArray(s)
    val key = tree.insertUTF32(arr, arr.size)
    val stream = convertToUTF8BitStream(arr)
    println("inserting " + stream + " -> " + key)
    insertMap[stream] = key
    println("printing")
    tree.print()

    var insertedBytes = 0
    for ((k, v) in insertMap) {
        insertedBytes += (k.length + 0x7) shr 3
        val v2 = tree.debugMap[k]
        if (v != v2) {
            throw Exception("value $k $v $v2")
        }
    }
    println("bytes inserted $insertedBytes")
    for ((k, v) in tree.debugMap) {
        val v2 = insertMap[k]
        if (v != v2) {
            throw Exception("value $k $v2 $v")
        }
    }
    i++
}
