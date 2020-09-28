#!/bin/kscript

class RadixTree {
    companion object {
        const val null_key = 0
        const val null_ptr = 0
        const val data_off = 16
    }

    var next_key = null_key + 1
    var pages = Array<ByteArray>(1024) { ByteArray(1024) }
    var pagesCounter = 1
    var rootNode = pages[0]
    var rootNodeOffset = 0
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

    fun readHeader(node: ByteArray, offset: Int): Int {
        return node.readInt2(offset)
    }

    fun writeHeader(node: ByteArray, offset: Int, header: Int) {
        return node.writeInt2(offset, header)
    }

    fun readLen(node: ByteArray, offset: Int): Int {
        return node.readInt2(offset + 2)
    }

    fun writeLen(node: ByteArray, offset: Int, len: Int) {
        return node.writeInt2(offset + 2, len)
    }

    fun readPtrA(node: ByteArray, offset: Int): Int {
        return node.readInt4(offset + 4)
    }

    fun writePtrA(node: ByteArray, offset: Int, ptr: Int) {
        return node.writeInt4(offset + 4, ptr)
    }

    fun readPtrB(node: ByteArray, offset: Int): Int {
        return node.readInt4(offset + 8)
    }

    fun writePtrB(node: ByteArray, offset: Int, ptr: Int) {
        return node.writeInt4(offset + 8, ptr)
    }

    fun readKey(node: ByteArray, offset: Int): Int {
        return node.readInt4(offset + 12)
    }

    fun writeKey(node: ByteArray, offset: Int, key: Int) {
        return node.writeInt4(offset + 12, key)
    }

    fun shiftLeft(inBuffer: ByteArray, outBuffer: ByteArray, inBufferOffset: Int, inBufferLength: Int) {
        var offIn = inBufferOffset shr 3
        val lenIn = (inBufferOffset + inBufferLength) shr 3
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
        println("calculating equalBits $offsetA $offsetB $len")
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
            return (b shl 3) + 8 - d
        }
    }

    fun createChild(data: ByteArray, len: Int, key: Int): Int {
        var nodePtr = pagesCounter++
        var node = pages[nodePtr]
        writeHeader(node, 0, 0x1)
        writeLen(node, 0, len)
        writePtrA(node, 0, null_ptr)
        writePtrB(node, 0, null_ptr)
        writeKey(node, 0, key)
        val b = (len + 0x7) shr 3
        for (i in 0 until b) {
            node[data_off + i] = data[i]
        }
        return nodePtr shl 9
    }

    fun print(pagePtr: Int = 0, prefix: String = "") {
        val currentPage = pages[pagePtr shr 9]
        val currentPageOffset = (pagePtr and 0x1ff) shl 4
        val len = readLen(currentPage, currentPageOffset)
        var s = ""
        val x = (len + 7) shr 3
        for (i in 0 until x) {
            s += (currentPage[currentPageOffset + data_off + i].toInt() and 0xff).toString(2).padStart(8, '0')
        }
	s=s.substring(0,len)
        println(prefix + s + " :: " + readKey(currentPage, currentPageOffset) + " = " + pagePtr)
        var ptr = readPtrA(currentPage, currentPageOffset)
        if (ptr != null_ptr) {
            print(ptr, prefix + s + "0")
        }
        ptr = readPtrB(currentPage, currentPageOffset)
        if (ptr != null_ptr) {
            print(ptr, prefix + s + "1")
        }
    }

    fun insertUTF32(inData: IntArray, inDataLength: Int): Int {
//bytes->
        var data = ByteArray(0)
        var data1 = ByteArray(0)
        var data2 = data1
        if (data.size < inDataLength shl 2) {
            data = ByteArray(inDataLength shl 2)
            data1 = ByteArray(inDataLength shl 2)
        }
        var inLen = convertUTF32ToUTF8(inData, inDataLength, data) shl 3 //bit
//bit->
        var currentPage = rootNode
        var currentPageOffset = rootNodeOffset
        while (true) {
            val len = readLen(currentPage, currentPageOffset)
            var common: Int = 0
            if (len <= inLen) {
                common = equalBits(currentPage, data, currentPageOffset + data_off, 0, len)
            } else {
                common = equalBits(currentPage, data, currentPageOffset + data_off, 0, inLen)
            }
            println("common $common $len $inLen")
            if (common == inLen) {
                val k = readKey(currentPage, currentPageOffset)
                if (k == null_key) {
                    val k2 = next_key++
                    writeKey(currentPage, currentPageOffset, k2)
                    return k2
                } else {
                    return k
                }
            } else if (common == len) {
                val significantByte = common shr 3
                val significantBitNumber = ((15 - (common and 0x7)) and 0x7)
                val significantBit = (data[significantByte].toInt() shr significantBitNumber) and 0x1
                println("significantBit $significantBit $significantByte $significantBitNumber")
                common += 1
                shiftLeft(data, data1, common, inLen)
                inLen -= common
                if (significantBit == 0) {
                    val ptr = readPtrA(currentPage, currentPageOffset)
                    if (ptr == null_ptr) {
                        var kk = next_key++
                        val pagePtr = createChild(data1, inLen, kk)
                        writePtrA(currentPage, currentPageOffset, pagePtr)
                        return kk
                    }
                    currentPage = pages[ptr shr 9]
                    currentPageOffset = (ptr and 0x1ff) shl 4
                } else {
                    val ptr = readPtrB(currentPage, currentPageOffset)
                    if (ptr == null_ptr) {
                        var kk = next_key++
                        val pagePtr = createChild(data1, inLen, kk)
                        writePtrB(currentPage, currentPageOffset, pagePtr)
                        return kk
                    }
                    currentPage = pages[ptr shr 9]
                    currentPageOffset = (ptr and 0x1ff) shl 4
                }
                data2 = data1
                data1 = data
                data = data2
            } else {
                val significantByte = common shr 3
                val significantBitNumber = ((8 - (common and 0x7)) and 0x7)
                val significantBit = (data[significantByte].toInt() shr significantBitNumber) and 0x1
                shiftLeft(data, data1, common + 1, inLen)
                inLen -= common
                var currentKey = readKey(currentPage, currentPageOffset)
                writeKey(currentPage, currentPageOffset, null_key)
                var newKey = next_key++
                val newPage = createChild(data1, inLen, newKey)
                shiftLeft(currentPage, data1, ((currentPageOffset + data_off) shl 3) + len - common, len)
                val splitChildLen = len - common
                println("splitChildLen $splitChildLen")
                val splitChildPage = createChild(data1, splitChildLen, currentKey)
                    writeLen(currentPage, currentPageOffset, common)
val off=currentPageOffset+data_off+(common shr 3)
when(common%8){
1->currentPage[off]=(currentPage[off].toInt() and 0x80).toByte()
2->currentPage[off]=(currentPage[off].toInt() and 0xc0).toByte()
3->currentPage[off]=(currentPage[off].toInt() and 0xe0).toByte()
4->currentPage[off]=(currentPage[off].toInt() and 0xf0).toByte()
5->currentPage[off]=(currentPage[off].toInt() and 0xf8).toByte()
6->currentPage[off]=(currentPage[off].toInt() and 0xfc).toByte()
7->currentPage[off]=(currentPage[off].toInt() and 0xfe).toByte()
}
                if (significantBit == 0) {
                    writePtrA(currentPage, currentPageOffset, newPage)
                    writePtrB(currentPage, currentPageOffset, splitChildPage)
                } else {
                    writePtrB(currentPage, currentPageOffset, newPage)
                    writePtrA(currentPage, currentPageOffset, splitChildPage)
                }
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

val ipsum = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet."


val data = ipsum.split(" ")

tree.print()
var i = 0
for (s in data) {
    println("-----------")
    if (i > 1) {
        break
    }
    val arr = stringToIntArray(s)
    val key = tree.insertUTF32(arr, arr.size)
    val stream = convertToUTF8BitStream(arr)
    println("inserting " + stream + " -> " + key)
    println("printing")
    tree.print()
    i++
}
