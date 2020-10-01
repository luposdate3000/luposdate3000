#!/bin/kscript
import  kotlin.random.Random

class RadixTree {

    var debugMap = mutableMapOf<String, Int>()
    var next_key = null_key + 1
    var pages = Array<ByteArray>(16000) { ByteArray(8192) } //pages[0] is used as temporary buffer
    var pagesCounter = 1
    val rootNode: ByteArray
    val rootNodeOffset: Int
    val rootNodePtr: Int
    var rootKey = null_key

    var allocatedBytes = 0
    var allocatedNodes = 0
    val listSliceSizes = intArrayOf(16, 32, 64, 128, 256, 512, 1024, 2048, 4096, 8196)
    val freeLists = Array(listSliceSizes.size) { mutableListOf<Int>() }
    val slotsAllocedBySize = IntArray(listSliceSizes.size)

    init {
        rootNodePtr = allocBytes(off_6_data)
        rootNode = pagePtrToPage(rootNodePtr)
        rootNodeOffset = pagePtrToOffset(rootNodePtr)
        rootNode.writeInt1(rootNodeOffset, header_6)
        for (id in 0 until 4) {
            rootNode.writeInt4(rootNodeOffset + off_ptrA + (id shl 2), null_ptr)
        }
    }

    fun convertUTF32ToUTF8(data: IntArray, dataLength: Int, outBuffer: ByteArray): Int {
        var len = 0
        for (i in 0 until dataLength) {
            val v = data[i]
            if (v <= 0x7f) {
                outBuffer[len++] = v.toByte()
            } else if (v <= 0x07ff) {
                outBuffer[len++] = (0xc0 or ((v shr 6) and 0x1f)).toByte()
                outBuffer[len++] = (0x80 or (v and 0x3f)).toByte()
            } else if (v <= 0x0ffff) {
                outBuffer[len++] = (0xe0 or ((v shr 12) and 0xf)).toByte()
                outBuffer[len++] = (0x80 or ((v shr 6) and 0x3f)).toByte()
                outBuffer[len++] = (0x80 or (v and 0x3f)).toByte()
            } else {
                outBuffer[len++] = (0xf0 or ((v shr 18) and 0x7)).toByte()
                outBuffer[len++] = (0x80 or ((v shr 12) and 0x3f)).toByte()
                outBuffer[len++] = (0x80 or ((v shr 6) and 0x3f)).toByte()
                outBuffer[len++] = (0x80 or (v and 0x3f)).toByte()
            }
        }
        return len
    }

    fun pagePtrToPage(ptr: Int): ByteArray {
        return pages[ptr shr 9]
    }

    fun pagePtrToOffset(ptr: Int): Int {
        return (ptr and 0x1ff) shl 4
    }

    fun mapLenToList(len: Int): Int {
        for (i in 0 until listSliceSizes.size - 1) {
            if (len <= listSliceSizes[i]) {
                return i
            }
        }
        return listSliceSizes.size - 1
    }

    fun allocBytes(len: Int): Int {
        allocatedNodes++
        val listToUse = mapLenToList(len)
        slotsAllocedBySize[listToUse]++
        val list = freeLists[listToUse]
        allocatedBytes += len
        if (list.size > 0) {
            val ptr = list.removeAt(0)
//println("alloc $len at ${ptr shr 9}:${(ptr and 0x1ff) shl 4}")
            return ptr
        }
        val newPage = (pagesCounter++) shl 9
        var ptr = newPage
        val ptrEnd = ptr + (1 shl 9)
        val slice = listSliceSizes[listToUse] shr 4
        ptr += slice
        while (ptr < ptrEnd) {
            list.add(ptr)
            ptr += slice
        }
//println("alloc $len at ${newPage shr 9}:${(newPage and 0x1ff) shl 4}")
        return newPage
    }

    fun freeBytes(ptr: Int) {
        allocatedNodes--
        val nodeOff = pagePtrToOffset(ptr)
        var node = pagePtrToPage(ptr)
        val len = readConsumedBytes(node, nodeOff)
        allocatedBytes -= len
        val listToUse = mapLenToList(len)
        slotsAllocedBySize[listToUse]--
        freeLists[listToUse].add(ptr)
    }

    companion object {
        const val null_key = 0
        const val null_ptr = 0

        const val off_ptrA = 1 //if ptr exist always the same offset
        const val off_ptrB = 5 //if ptr exist always the same offset

        //header 0x0 stores PtrA, PtrB, len, data
//header 0x1 stores PtrA, PtrB, len, key, data
//header 0x2 stores len, key, data
//header 0x3 stores PtrA, PtrB
//header 0x4 stores PtrA, PtrB, PtrC, PtrD
//header 0x5 stores PtrA, PtrB, key
//header 0x6 stores PtrA, PtrB, PtrC, PtrD, key
        const val header_0 = 0x0
        const val header_1 = 0x1
        const val header_2 = 0x2
        const val header_3 = 0x3
        const val header_4 = 0x4
        const val header_5 = 0x5
        const val header_6 = 0x6

        const val off_0_len = 9
        const val off_0_data = 11

        const val off_1_len = 9
        const val off_1_key = 11
        const val off_1_data = 15

        const val off_2_len = 1
        const val off_2_key = 3
        const val off_2_data = 7

        const val off_3_data = 9

        const val off_4_data = 17

        const val off_5_key = 9
        const val off_5_data = 11

        const val off_6_key = 17
        const val off_6_data = 19
    }

    fun readHeader(node: ByteArray, offset: Int): Int {
        return node.readInt1(offset)
    }

    fun readDataOffset(node: ByteArray, offset: Int): Int {
        val header = readHeader(node, offset)
        when (header) {
            header_0 -> return offset + off_0_data
            header_1 -> return offset + off_1_data
            header_2 -> return offset + off_2_data
            header_3 -> return offset + off_3_data
            header_4 -> return offset + off_4_data
            header_5 -> return offset + off_5_data
            header_6 -> return offset + off_6_data
            else -> throw Exception("unknown header $header")
        }
    }

    fun readLen(node: ByteArray, offset: Int): Int {
        val header = readHeader(node, offset)
        when (header) {
            header_0 -> return node.readInt2(offset + off_0_len)
            header_1 -> return node.readInt2(offset + off_1_len)
            header_2 -> return node.readInt2(offset + off_2_len)
            header_3 -> return 0
            header_4 -> return 0
            header_5 -> return 0
            header_6 -> return 0
            else -> throw Exception("unknown header $header")
        }
    }

    fun readConsumedBytes(node: ByteArray, offset: Int): Int {
        val header = readHeader(node, offset)
        when (header) {
            header_0 -> return off_0_data + ((node.readInt2(offset + off_0_len) + 0x7) shr 3)
            header_1 -> return off_1_data + ((node.readInt2(offset + off_1_len) + 0x7) shr 3)
            header_2 -> return off_2_data + ((node.readInt2(offset + off_2_len) + 0x7) shr 3)
            header_3 -> return off_3_data
            header_4 -> return off_4_data
            header_5 -> return off_5_data
            header_6 -> return off_6_data
            else -> throw Exception("unknown header $header")
        }
    }

    fun readPtrSpecific(node: ByteArray, offset: Int, id: Int): Int {
        val header = readHeader(node, offset)
        when (header) {
            header_0, header_1, header_3, header_4, header_5, header_6 -> return node.readInt4(offset + off_ptrA + (id shl 2))
            header_1 -> return node.readInt4(offset + off_ptrA + (id shl 2))
            header_2 -> return null_ptr
            else -> throw Exception("unknown header $header")
        }
    }

    fun readPtrA(node: ByteArray, offset: Int): Int {
        val header = readHeader(node, offset)
        when (header) {
            header_0, header_1, header_3, header_4, header_5, header_6 -> return node.readInt4(offset + off_ptrA)
            header_1 -> return node.readInt4(offset + off_ptrA)
            header_2 -> return null_ptr
            else -> throw Exception("unknown header $header")
        }
    }

    fun readPtrB(node: ByteArray, offset: Int): Int {
        val header = readHeader(node, offset)
        when (header) {
            header_0, header_1, header_3, header_4, header_5, header_6 -> return node.readInt4(offset + off_ptrB)
            header_2 -> return null_ptr
            else -> throw Exception("unknown header $header")
        }
    }

    fun readKey(node: ByteArray, offset: Int): Int {
        val header = readHeader(node, offset)
        when (header) {
            header_0, header_3, header_4 -> return null_key
            header_1 -> return node.readInt4(offset + off_1_key)
            header_2 -> return node.readInt4(offset + off_2_key)
            header_5 -> return node.readInt4(offset + off_5_key)
            header_6 -> return node.readInt4(offset + off_6_key)
            else -> throw Exception("unknown header $header")
        }
    }

    fun readHeaderByPtr(ptr: Int): Int {
        val node = pagePtrToPage(ptr)
        val offset = pagePtrToOffset(ptr)
        return readHeader(node, offset)
    }

    fun countChilds(currentPtr: Int, len: Int): Int {
        if (currentPtr == null_ptr) {
            return 0
        } else if (len == 0) {
            return 1
        }
        val current = pagePtrToPage(currentPtr)
        val currentOff = pagePtrToOffset(currentPtr)
        val header = readHeader(current, currentOff)
        when (header) {
            header_2 -> {
                return 1
            }
            header_0, header_1 -> {
                val l = readLen(current, currentOff)
                var res = 0
                if (l < len) {
                    for (id2 in 0 until 2) {
                        val p = readPtrSpecific(current, currentOff, id2)
                        if (p != null_ptr) {
                            if (len == 1) {
                                res++
                            } else {
                                res += countChilds(p, len - l - 1)
                            }
                        }
                    }
                } else {
                    res++
                }
                return res
            }
            header_3, header_5 -> {
                var res = 0
                for (id2 in 0 until 2) {
                    val p = readPtrSpecific(current, currentOff, id2)
                    if (p != null_ptr) {
                        if (len == 1) {
                            res++
                        } else {
                            res += countChilds(p, len - 1)
                        }
                    }
                }
                return res
            }
            header_4, header_6 -> {
                if (len < 2) {
                    throw Exception("wrong depth to start counting")
                }
                var res = 0
                for (id2 in 0 until 4) {
                    val p = readPtrSpecific(current, currentOff, id2)
                    if (p != null_ptr) {
                        if (len == 1) {
                            res++
                        } else {
                            res += countChilds(p, len - 2)
                        }
                    }
                }
                return res
            }
            else -> {
                throw Exception("unknown header $header")
            }
        }
    }

    fun writeChilds(target: ByteArray, targetOff: Int, idPrefix: Int, len: Int, currentPtr: Int, currentDepth: Int) {
        if (len == 0) {
            target.writeInt4(targetOff + off_ptrA + (idPrefix shl 2), currentPtr)
            return
        }
        if (currentPtr == null_ptr) {
            for (id in 0 until (1 shl len)) {
                target.writeInt4(targetOff + off_ptrA + ((idPrefix + id) shl 2), currentPtr)
            }
            return
        }
        var pageBuffer = pages[0]
        val current = pagePtrToPage(currentPtr)
        val currentOff = pagePtrToOffset(currentPtr)
        val currentHeader = readHeader(current, currentOff)
        when (currentHeader) {
            header_3, header_5 -> {
                for (id2 in 0 until 2) {
                    val p = readPtrSpecific(current, currentOff, id2)
                    writeChilds(target, targetOff, idPrefix + (id2 shl (len - 1)), len - 1, p, currentDepth + 1)
                }
            }
            header_0 -> {
                val currentDataOff = readDataOffset(current, currentOff)
                val significantBit = (current[currentDataOff].toInt() shr (8 - len)) and ((1 shl len) - 1)
                val l = readLen(current, currentOff)
                if (len > l) {
                    val ll = len - l
                    val myPrefix = idPrefix + (significantBit shl ll)
                    for (id2 in 0 until 2) {
                        val p = readPtrSpecific(current, currentOff, id2)
                        writeChilds(target, targetOff, myPrefix + (id2 shl (ll - 1)), ll - 1, p, currentDepth + l + 1)
                    }
                } else {
                    shiftLeft(current, pageBuffer, (currentDataOff shl 3) + len, l - len)
                    val key = readKey(current, currentOff)
                    val ptrA = readPtrA(current, currentOff)
                    val ptrB = readPtrB(current, currentOff)
                    println("createChild 15")
                    val newChild = createChild(pageBuffer, 0, l - len, key, currentDepth + len, ptrA, ptrB)
                    for (id in 0 until (1 shl len)) {
                        target.writeInt4(targetOff + off_ptrA + ((idPrefix + id) shl 2), null_ptr)
                    }
                    target.writeInt4(targetOff + off_ptrA + ((idPrefix + significantBit) shl 2), newChild)
                }
            }
            header_1, header_2 -> {
                val currentDataOff = readDataOffset(current, currentOff)
                val significantBit = (current[currentDataOff].toInt() shr (8 - len)) and ((1 shl len) - 1)
                val l = readLen(current, currentOff)
                if (len > l) {
                    throw Exception("something wrong here")
                }
                shiftLeft(current, pageBuffer, (currentDataOff shl 3) + len, l - len)
                val key = readKey(current, currentOff)
                val ptrA = readPtrA(current, currentOff)
                val ptrB = readPtrB(current, currentOff)
                println("createChild 16")
                val newChild = createChild(pageBuffer, 0, l - len, key, currentDepth + len - 1, ptrA, ptrB)
                for (id in 0 until (1 shl len)) {
                    target.writeInt4(targetOff + off_ptrA + ((idPrefix + id) shl 2), null_ptr)
                }
                target.writeInt4(targetOff + off_ptrA + ((idPrefix + significantBit) shl 2), newChild)
            }
        }
        freeBytes(currentPtr)
    }

    fun verifyCurrentDepth(stack: IntArray, stackPtr: Int, currentDepth: Int) {
        if (stackPtr > 0) {
            var correctDepth = 0
            var used_counter = 0
            var sPtr = 0
            while (sPtr < stackPtr) {
                if (sPtr != null_ptr) {
                    used_counter++
                    var currentPtr = stack[sPtr]
                    correctDepth += calculateDepth(currentPtr)
                }
                sPtr++
            }
            if (correctDepth != currentDepth) {
                tree.print()
                throw Exception("incorrect depth $correctDepth $currentDepth ${stack[stackPtr - 1]}")
            }
        }
    }

    fun calculateDepth(currentPtr: Int): Int {
        val current = pagePtrToPage(currentPtr)
        val currentOff = pagePtrToOffset(currentPtr)
        val currentHeader = readHeader(current, currentOff)
        when (currentHeader) {
            header_4, header_6 -> return 2
            header_3, header_5 -> return 1
            header_0, header_1 -> return 1 + readLen(current, currentOff)
            header_2 -> return readLen(current, currentOff)
            else -> throw Exception("unkwnown header $currentHeader")
        }
    }

    fun checkStack(stack: IntArray, stackPtr: Int, currentDepth: Int) {
        var depth = currentDepth
        var sPtr = stackPtr
        var maxStepsDeeper = 2
        //println("checkStack initial $currentDepth")
        while (sPtr > 2) {
            verifyCurrentDepth(stack, sPtr, depth)
            var currentPtr = stack[sPtr - 1]
            val parentDepth = depth - calculateDepth(currentPtr)
            println("checkStack ${stack[sPtr - 1]} $depth -- ${stack[sPtr - 2]} $parentDepth")
            checkStackHelper(stack, sPtr, depth, maxStepsDeeper)
            depth = parentDepth
            sPtr--
            maxStepsDeeper--
        }
    }

    fun checkStackHelper(stack: IntArray, stackPtr: Int, currentDepth: Int, maxStepsDeeper: Int) {
        verifyCurrentDepth(stack, stackPtr, currentDepth)
        var currentPtr = stack[stackPtr - 1]
        val current = pagePtrToPage(currentPtr)
        val currentOff = pagePtrToOffset(currentPtr)
        val currentHeader = readHeader(current, currentOff)
        if (maxStepsDeeper > 0) {
            when (currentHeader) {
                header_4, header_6 -> {
                    for (id2 in 0 until 4) {
                        val p = readPtrSpecific(current, currentOff, id2)
                        if (p != null_ptr) {
                            stack[stackPtr] = p
                            checkStackHelper(stack, stackPtr + 1, currentDepth + calculateDepth(p), maxStepsDeeper - 1)
                        }
                    }
                }
                header_3, header_5 -> {
                    for (id2 in 0 until 2) {
                        val p = readPtrSpecific(current, currentOff, id2)
                        if (p != null_ptr) {
                            stack[stackPtr] = p
                            checkStackHelper(stack, stackPtr + 1, currentDepth + calculateDepth(p), maxStepsDeeper - 1)
                        }
                    }
                }
            }
        }
        if (stackPtr > 2) {
            when (currentHeader) {
                header_3 -> {
                    if (currentDepth and 0x1 != 0) {
                        if (countChilds(currentPtr, 1) >= 2) {
                            println("createChild 17")
                            val nodePtr = allocBytes(off_4_data)
                            val nodeOff = pagePtrToOffset(nodePtr)
                            var node = pagePtrToPage(nodePtr)
                            node.writeInt1(nodeOff, header_4)
                            writeChilds(node, nodeOff, 0, 2, currentPtr, currentDepth)
                            updatePointer(stack[stackPtr - 2], currentPtr, nodePtr)
                            stack[stackPtr - 1] == nodePtr
                        }
                    }
                }
                header_5 -> {
                    if (currentDepth and 0x1 != 0) {
                        if (countChilds(currentPtr, 1) >= 2) {
                            println("createChild 18")
                            val nodePtr = allocBytes(off_6_data)
                            val nodeOff = pagePtrToOffset(nodePtr)
                            var node = pagePtrToPage(nodePtr)
                            node.writeInt1(nodeOff, header_6)
                            var key = readKey(current, currentOff)
                            node.writeInt4(nodeOff + off_6_key, key)
                            writeChilds(node, nodeOff, 0, 2, currentPtr, currentDepth)
                            updatePointer(stack[stackPtr - 2], currentPtr, nodePtr)
                            stack[stackPtr - 1] == nodePtr
                        }
                    }
                }
            }
        }
    }

    fun createChild(data: ByteArray, dataOff: Int, len: Int, key: Int, currentDepth: Int, ptrA: Int = null_ptr, ptrB: Int = null_ptr): Int {
        if (len < 0) {
            throw Exception("invalid len $len")
        }
        if (key != null_key) {
            if (((currentDepth + len) and 0x7) != 0) {
                throw Exception("wrong depth ${(currentDepth + len) and 0x7}")
            }
        }
        var header = header_0
        if (((currentDepth + len) and 0x7) == 0) {
            if (ptrA == null_ptr && ptrB == null_ptr) {
                header = header_2
            } else {
                if (len == 0) {
                    header = header_5
                } else {
                    header = header_1
                }
            }
        } else {
            if (ptrA != null_ptr && ptrB != null_ptr) {
                if (len == 0) {
                    header = header_3
                }
            }
        }
        when (header) {
            header_0 -> {
                val nodePtr = allocBytes(off_0_data + ((len + 0x7) shr 3))
                val nodeOff = pagePtrToOffset(nodePtr)
                var node = pagePtrToPage(nodePtr)
                node.writeInt1(nodeOff, header)
                node.writeInt4(nodeOff + off_ptrA, ptrA)
                node.writeInt4(nodeOff + off_ptrB, ptrB)
                node.writeInt2(nodeOff + off_0_len, len)
                val b = (len + 0x7) shr 3
                val dataOffOut = nodeOff + off_0_data
                for (i in 0 until b) {
                    node[dataOffOut + i] = data[dataOff + i]
                }
                println("alloc as $header $nodePtr")
                return nodePtr
            }
            header_1 -> {
                val nodePtr = allocBytes(off_1_data + ((len + 0x7) shr 3))
                val nodeOff = pagePtrToOffset(nodePtr)
                var node = pagePtrToPage(nodePtr)
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
                println("alloc as $header $nodePtr")
                return nodePtr
            }
            header_2 -> {
                val nodePtr = allocBytes(off_2_data + ((len + 0x7) shr 3))
                val nodeOff = pagePtrToOffset(nodePtr)
                var node = pagePtrToPage(nodePtr)
                node.writeInt1(nodeOff, header)
                node.writeInt2(nodeOff + off_2_len, len)
                node.writeInt4(nodeOff + off_2_key, key)
                val b = (len + 0x7) shr 3
                val dataOffOut = nodeOff + off_2_data
                for (i in 0 until b) {
                    node[dataOffOut + i] = data[dataOff + i]
                }
                println("alloc as $header $nodePtr")
                return nodePtr
            }
            header_3 -> {
                val nodePtr = allocBytes(off_3_data)
                val nodeOff = pagePtrToOffset(nodePtr)
                var node = pagePtrToPage(nodePtr)
                node.writeInt1(nodeOff, header)
                node.writeInt4(nodeOff + off_ptrA, ptrA)
                node.writeInt4(nodeOff + off_ptrB, ptrB)
                println("alloc as $header $nodePtr")
                return nodePtr
            }
            header_5 -> {
                val nodePtr = allocBytes(off_3_data)
                val nodeOff = pagePtrToOffset(nodePtr)
                var node = pagePtrToPage(nodePtr)
                node.writeInt1(nodeOff, header)
                node.writeInt4(nodeOff + off_ptrA, ptrA)
                node.writeInt4(nodeOff + off_ptrB, ptrB)
                node.writeInt4(nodeOff + off_5_key, key)
                println("alloc as $header $nodePtr")
                return nodePtr
            }
            else -> {
                throw Exception("unkwnown header $header")
            }
        }
    }

    fun shiftLeft(inBuffer: ByteArray, outBuffer: ByteArray, inBufferOffset: Int, inBufferLength: Int) {
        if (inBufferLength > 0) {
            var offIn = inBufferOffset shr 3
            var lenIn = (inBufferOffset + inBufferLength) shr 3
            var offOut = 0
            var toShift = inBufferOffset and 0x7
            val endoffset = (inBufferOffset + inBufferLength) and 0x7
            if (endoffset == 0) {
                lenIn--
            }
            when (toShift) {
                0 -> {
                    while (offIn < lenIn) {
                        outBuffer[offOut] = inBuffer[offIn]
                        offIn++
                        offOut++
                    }
                    outBuffer[offOut] = inBuffer[offIn]
                }
                1 -> {
                    while (offIn < lenIn) {
                        outBuffer[offOut] = (((inBuffer[offIn].toInt() shl 1) and 0xfe) or ((inBuffer[offIn + 1].toInt() shr 7) and 0x01)).toByte()
                        offIn++
                        offOut++
                    }
                    outBuffer[offOut] = ((inBuffer[offIn].toInt() shl 1) and 0xfe).toByte()
                }
                2 -> {
                    while (offIn < lenIn) {
                        outBuffer[offOut] = (((inBuffer[offIn].toInt() shl 2) and 0xfc) or ((inBuffer[offIn + 1].toInt() shr 6) and 0x03)).toByte()
                        offIn++
                        offOut++
                    }
                    outBuffer[offOut] = ((inBuffer[offIn].toInt() shl 2) and 0xfc).toByte()
                }
                3 -> {
                    while (offIn < lenIn) {
                        outBuffer[offOut] = (((inBuffer[offIn].toInt() shl 3) and 0xf8) or ((inBuffer[offIn + 1].toInt() shr 5) and 0x07)).toByte()
                        offIn++
                        offOut++
                    }
                    outBuffer[offOut] = ((inBuffer[offIn].toInt() shl 3) and 0xf8).toByte()
                }
                4 -> {
                    while (offIn < lenIn) {
                        outBuffer[offOut] = (((inBuffer[offIn].toInt() shl 4) and 0xf0) or ((inBuffer[offIn + 1].toInt() shr 4) and 0x0f)).toByte()
                        offIn++
                        offOut++
                    }
                    outBuffer[offOut] = ((inBuffer[offIn].toInt() shl 4) and 0xf0).toByte()
                }
                5 -> {
                    while (offIn < lenIn) {
                        outBuffer[offOut] = (((inBuffer[offIn].toInt() shl 5) and 0xe0) or ((inBuffer[offIn + 1].toInt() shr 3) and 0x1f)).toByte()
                        offIn++
                        offOut++
                    }
                    outBuffer[offOut] = ((inBuffer[offIn].toInt() shl 5) and 0xe0).toByte()
                }
                6 -> {
                    while (offIn < lenIn) {
                        outBuffer[offOut] = (((inBuffer[offIn].toInt() shl 6) and 0xc0) or ((inBuffer[offIn + 1].toInt() shr 2) and 0x3f)).toByte()
                        offIn++
                        offOut++
                    }
                    outBuffer[offOut] = ((inBuffer[offIn].toInt() shl 6) and 0xc0).toByte()
                }
                7 -> {
                    while (offIn < lenIn) {
                        outBuffer[offOut] = (((inBuffer[offIn].toInt() shl 7) and 0x80) or ((inBuffer[offIn + 1].toInt() shr 1) and 0x7f)).toByte()
                        offIn++
                        offOut++
                    }
                    outBuffer[offOut] = ((inBuffer[offIn].toInt() shl 7) and 0x80).toByte()
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

    fun print(maxdepth: Int = Int.MAX_VALUE) {
        debugMap.clear()
        if (rootKey != null_key) {
            debugMap[""] = rootKey
        }
        var usedBytes = print(rootNodePtr, "", maxdepth)
        println("bytes consumed $usedBytes ($allocatedBytes) .. ${slotsAllocedBySize.mapIndexed { idx, it -> it * listSliceSizes[idx] }.sum()} ${slotsAllocedBySize.map { it }} used nodes :: $allocatedNodes")
    }

    fun print(pagePtr: Int, prefix: String, maxdepth: Int): Int {
        var usedBytes = 0
        var currentPage = pagePtrToPage(pagePtr)
        val currentPageOffset = pagePtrToOffset(pagePtr)
        val len = readLen(currentPage, currentPageOffset)
        var s = ""
        val x = (len + 0x7) shr 3
        usedBytes += readConsumedBytes(currentPage, currentPageOffset)
        val dataOff = readDataOffset(currentPage, currentPageOffset)
        try {
            for (i in 0 until x) {
                s += (currentPage[dataOff + i].toInt() and 0xff).toString(2).padStart(8, '0')

/*val b = (len + 0x7) shr 3
                val dataOffOut = nodeOff + off_2_data
                for (i in 0 until b) {
                    node[dataOffOut + i] = data[dataOff + i]
                }
*/
            }
        } catch (e: Throwable) {
            var header = readHeader(currentPage, currentPageOffset)
//2 503 496 7 65534 1055
            println("$header $dataOff $currentPageOffset $off_2_data $len $pagePtr")
            throw e
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
        when (header) {
            header_4, header_6 -> {
                for (id in 0 until 4) {
                    var ptr = readPtrSpecific(currentPage, currentPageOffset, id)
                    if (ptr != null_ptr && maxdepth > 0) {
                        usedBytes += print(ptr, value + id.toString(2).padStart(2, '0'), maxdepth - 1)
                    }
                }
            }
            header_0, header_1, header_2, header_3, header_5 -> {
                var ptr = readPtrA(currentPage, currentPageOffset)
                if (ptr != null_ptr && maxdepth > 0) {
                    usedBytes += print(ptr, value + "0", maxdepth - 1)
                }
                ptr = readPtrB(currentPage, currentPageOffset)
                if (ptr != null_ptr && maxdepth > 0) {
                    usedBytes += print(ptr, value + "1", maxdepth - 1)
                }
            }
            else -> {
                throw Exception("unknown header $header")
            }
        }
        return usedBytes
    }

    fun updatePointer(parentPtr: Int, currentPtr: Int, newPtr: Int) {
        if (parentPtr == null_ptr) {
            throw Exception("")
        }
        val parent = pagePtrToPage(parentPtr)
        val parentOff = pagePtrToOffset(parentPtr)
        val header = readHeader(parent, parentOff)
        when (header) {
            header_2 -> {
                throw Exception("invalid header")
            }
            header_0, header_1, header_3, header_5 -> {
                if (parent.readInt4(parentOff + off_ptrA) == currentPtr) {
                    parent.writeInt4(parentOff + off_ptrA, newPtr)
                } else {
                    parent.writeInt4(parentOff + off_ptrB, newPtr)
                }
            }
            header_4, header_6 -> {
                for (id in 0 until 4) {
                    if (parent.readInt4(parentOff + off_ptrA + (id shl 2)) == currentPtr) {
                        parent.writeInt4(parentOff + off_ptrA + (id shl 2), newPtr)
                        return
                    }
                }
            }
            else -> {
                throw Exception("unknown header $header")
            }
        }
    }

    fun updatePointerSpecific(parentPtr: Int, id: Int, newPtr: Int) {
        if (parentPtr == null_ptr) {
            throw Exception("")
        }
        val parent = pagePtrToPage(parentPtr)
        val parentOff = pagePtrToOffset(parentPtr)
        if (parent.readInt1(parentOff) == header_2) {
            throw Exception("invalid header")
        }
        parent.writeInt4(parentOff + off_ptrA + (id shl 2), newPtr)
    }


    fun insertUTF32(inData: IntArray, inDataLength: Int): Int {
        var data = ByteArray(inDataLength shl 2)
        var inLen = convertUTF32ToUTF8(inData, inDataLength, data)
        return insertByteArray(data, inLen)
    }

    fun insertByteArray(inData: ByteArray, inDataLength: Int): Int {
        if (inDataLength == 0) {
            if (rootKey == null_key) {
                rootKey = next_key++
            }
            return rootKey
        }
        var pageBuffer = pages[0]
        var data = inData
        var data1 = ByteArray(inDataLength)
        var data2 = data1
        var inLen = inDataLength shl 3
        var stack = IntArray(100) { null_ptr }
        var stackLen = IntArray(100) { 0 }
        var stackPtr = 1
        var currentPage = rootNode
        var currentPageOffset = rootNodeOffset
        var currentPtr = rootNodePtr
        var currentDepth = 0
        while (true) {
            verifyCurrentDepth(stack, stackPtr, currentDepth)
            val header = readHeader(currentPage, currentPageOffset)
            //println("path $currentPtr $header")
            when (header) {
                header_6 -> {
                    if (inLen == 0) {
                        var key = readKey(currentPage, currentPageOffset)
                        if (key == null_key) {
                            key = next_key++
                            currentPage.writeInt4(currentPageOffset + off_6_key, key)
                        }
                        return key
                    }
                    val significantBit = (data[0].toInt() shr 6) and 0x3
                    shiftLeft(data, data1, 2, inLen-2)
                    inLen -= 2
                    val ptr = readPtrSpecific(currentPage, currentPageOffset, significantBit)
                    if (ptr == null_ptr) {
                        var kk = next_key++
                        println("createChild 19")
                        val pagePtr = createChild(data1, 0, inLen, kk, currentDepth + 2)
                        updatePointerSpecific(currentPtr, significantBit, pagePtr)
                        checkStack(stack, stackPtr - 1, currentDepth - stackLen[stackPtr - 1])
                        return kk
                    }
                    currentPage = pagePtrToPage(ptr)
                    currentPageOffset = pagePtrToOffset(ptr)
                    currentDepth += 2
                    stackLen[stackPtr] = 2
                    stack[stackPtr] = currentPtr
                    stackPtr++
                    currentPtr = ptr
                    data2 = data1
                    data1 = data
                    data = data2
                }
                header_5 -> {
                    if (inLen == 0) {
                        var key = readKey(currentPage, currentPageOffset)
                        if (key == null_key) {
                            key = next_key++
                            currentPage.writeInt4(currentPageOffset + off_5_key, key)
                        }
                        return key
                    }
                    val significantBit = (data[0].toInt() shr 7) and 0x1
                    shiftLeft(data, data1, 1, inLen - 1)
                    inLen -= 1
                    val ptr = readPtrSpecific(currentPage, currentPageOffset, significantBit)
                    if (ptr == null_ptr) {
                        var kk = next_key++
                        println("createChild 20")
                        val pagePtr = createChild(data1, 0, inLen, kk, currentDepth + 1)
                        updatePointerSpecific(currentPtr, significantBit, pagePtr)
                        checkStack(stack, stackPtr - 1, currentDepth - stackLen[stackPtr - 1])
                        return kk
                    }
                    currentPage = pagePtrToPage(ptr)
                    currentPageOffset = pagePtrToOffset(ptr)
                    currentDepth += 1
                    stackLen[stackPtr] = 1
                    stack[stackPtr] = currentPtr
                    stackPtr++
                    currentPtr = ptr
                    data2 = data1
                    data1 = data
                    data = data2
                }
                header_4 -> {
                    val significantBit = (data[0].toInt() shr 6) and 0x3
                    shiftLeft(data, data1, 2, inLen)
                    inLen -= 2
                    val ptr = readPtrSpecific(currentPage, currentPageOffset, significantBit)
                    if (ptr == null_ptr) {
                        var kk = next_key++
                        println("createChild 1")
                        val pagePtr = createChild(data1, 0, inLen, kk, currentDepth + 2)
                        updatePointerSpecific(currentPtr, significantBit, pagePtr)
                        checkStack(stack, stackPtr - 1, currentDepth - stackLen[stackPtr - 1])
                        return kk
                    }
                    currentPage = pagePtrToPage(ptr)
                    currentPageOffset = pagePtrToOffset(ptr)
                    currentDepth += 2
                    stackLen[stackPtr] = 2
                    stack[stackPtr] = currentPtr
                    stackPtr++
                    currentPtr = ptr
                    data2 = data1
                    data1 = data
                    data = data2
                }
                header_3 -> {
                    val significantBit = (data[0].toInt() shr 7) and 0x1
                    shiftLeft(data, data1, 1, inLen - 1)
                    inLen -= 1
                    val ptr = readPtrSpecific(currentPage, currentPageOffset, significantBit)
                    if (ptr == null_ptr) {
                        var kk = next_key++
                        println("createChild 2")
                        val pagePtr = createChild(data1, 0, inLen, kk, currentDepth + 1)
                        updatePointerSpecific(currentPtr, significantBit, pagePtr)
                        checkStack(stack, stackPtr - 1, currentDepth - stackLen[stackPtr - 1])
                        return kk
                    }
                    currentPage = pagePtrToPage(ptr)
                    currentPageOffset = pagePtrToOffset(ptr)
                    currentDepth += 1
                    stackLen[stackPtr] = 1
                    stack[stackPtr] = currentPtr
                    stackPtr++
                    currentPtr = ptr
                    data2 = data1
                    data1 = data
                    data = data2
                }
                header_0, header_1, header_2 -> {
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
                                println("createChild 3")
                                val newPtr = createChild(currentPage, dataOff, len, k2, currentDepth, ptrA, ptrB)
                                freeBytes(currentPtr)
                                updatePointer(stack[stackPtr - 1], currentPtr, newPtr)
                                checkStack(stack, stackPtr, currentDepth)
                                return k2
                            } else {
                                return k
                            }
                        } else {
                            val ptrA = readPtrA(currentPage, currentPageOffset)
                            val ptrB = readPtrB(currentPage, currentPageOffset)
                            var currentKey = readKey(currentPage, currentPageOffset)
                            var newKey = next_key++
                            val significantByte = common shr 3
                            val significantBitNumber = ((15 - (common and 0x7)) and 0x7)
                            val significantBit = (currentPage[dataOff + significantByte].toInt() shr significantBitNumber) and 0x1
                            common += 1
                            shiftLeft(currentPage, pageBuffer, (dataOff shl 3) + common, len - common)
                            println("createChild 4")
                            val splitChildPage = createChild(pageBuffer, 0, len - common, currentKey, currentDepth + common, ptrA, ptrB)
                            val off = dataOff + (common shr 3)
                            var newPtr = null_ptr
                            if (significantBit == 0) {
                                println("createChild 5")
                                newPtr = createChild(currentPage, dataOff, common - 1, newKey, currentDepth, splitChildPage, null_ptr)
                                freeBytes(currentPtr)
                                updatePointer(stack[stackPtr - 1], currentPtr, newPtr)
                            } else {
                                println("createChild 6")
                                newPtr = createChild(currentPage, dataOff, common - 1, newKey, currentDepth, null_ptr, splitChildPage)
                                freeBytes(currentPtr)
                                updatePointer(stack[stackPtr - 1], currentPtr, newPtr)
                            }
                            checkStack(stack, stackPtr, currentDepth)
                            return newKey
                        }
                    } else if (common == len) {
                        val significantByte = common shr 3
                        val significantBitNumber = ((15 - (common and 0x7)) and 0x7)
                        val significantBit = (data[significantByte].toInt() shr significantBitNumber) and 0x1
                        common += 1
                        shiftLeft(data, data1, common, inLen - 1)
                        inLen -= common
                        if (significantBit == 0) {
                            val key = readKey(currentPage, currentPageOffset)
                            val ptrA = readPtrA(currentPage, currentPageOffset)
                            val ptrB = readPtrB(currentPage, currentPageOffset)
                            if (ptrA == null_ptr) {
                                var kk = next_key++
                                println("createChild 7")
                                val pagePtr = createChild(data1, 0, inLen, kk, currentDepth + len + 1)
                                println("createChild 8")
                                val newPtr = createChild(currentPage, dataOff, len, key, currentDepth, pagePtr, ptrB)
                                freeBytes(currentPtr)
                                updatePointer(stack[stackPtr - 1], currentPtr, newPtr)
                                checkStack(stack, stackPtr, currentDepth)
                                return kk
                            }
                            currentPage = pagePtrToPage(ptrA)
                            currentPageOffset = pagePtrToOffset(ptrA)
                            currentDepth += common
                            stackLen[stackPtr] = common
                            stack[stackPtr] = currentPtr
                            stackPtr++
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
                                println("createChild 9")
                                val pagePtr = createChild(data1, 0, inLen, kk, currentDepth + 1)
                                println("createChild 10")
                                val newPtr = createChild(currentPage, dataOff, len, key, currentDepth, ptrA, pagePtr)
                                freeBytes(currentPtr)
                                updatePointer(stack[stackPtr - 1], currentPtr, newPtr)
                                checkStack(stack, stackPtr, currentDepth)
                                return kk
                            }
                            currentPage = pagePtrToPage(ptrB)
                            currentPageOffset = pagePtrToOffset(ptrB)
                            currentDepth += common
                            stackLen[stackPtr] = common
                            stack[stackPtr] = currentPtr
                            stackPtr++
                            currentPtr = ptrB
                            data2 = data1
                            data1 = data
                            data = data2
                        }
                    } else {
                        val significantByte = common shr 3
                        val significantBitNumber = ((15 - (common and 0x7)) and 0x7)
                        val significantBit = (data[significantByte].toInt() shr significantBitNumber) and 0x1
                        common++
                        shiftLeft(data, data1, common, inLen - common)
                        var newKey = next_key++
                        println("createChild 11")
                        val newPage = createChild(data1, 0, inLen - common, newKey, currentDepth + common)
                        var currentKey = readKey(currentPage, currentPageOffset)
                        val ptrA = readPtrA(currentPage, currentPageOffset)
                        val ptrB = readPtrB(currentPage, currentPageOffset)
                        shiftLeft(currentPage, pageBuffer, (dataOff shl 3) + common, len - common)
                        println("createChild 12")
                        val splitChildPage = createChild(pageBuffer, 0, len - common, currentKey, currentDepth + common, ptrA, ptrB)
                        val off = dataOff + (common shr 3)
                        var newPtr = null_ptr
                        if (significantBit == 0) {
                            println("createChild 13")
                            newPtr = createChild(currentPage, dataOff, common - 1, null_key, currentDepth, newPage, splitChildPage)
                            freeBytes(currentPtr)
                            updatePointer(stack[stackPtr - 1], currentPtr, newPtr)
                        } else {
                            println("createChild 14")
                            newPtr = createChild(currentPage, dataOff, common - 1, null_key, currentDepth, splitChildPage, newPage)
                            freeBytes(currentPtr)
                            updatePointer(stack[stackPtr - 1], currentPtr, newPtr)
                        }
                        checkStack(stack, stackPtr - 1, currentDepth - stackLen[stackPtr - 1])
                        return newKey
                    }
                }
                else -> {
                    throw Exception("unknown header $header")
                }
            }
        }
    }
}
//TEST - CODE :: -->>

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


fun stringToIntArray(str: String): IntArray {
    val s = str.replace(Regex("[^a-zA-Z]"), "")
    val res = IntArray(s.length)
    for (c in 0 until s.length) {
        res[c] = s[c].toInt() and 0x7f
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

fun convertToUTF8BitStream(arr: ByteArray): String {
    var s = ""
    for (i in 0 until arr.size) {
        s += (arr[i].toInt() and 0xff).toString(2).padStart(8, '0')
    }
    return s
}

fun testUsingFile(name: String) {
    java.io.File(name).forEachLine { it2 ->
        for (it in it2.split(" ")) {
            if (i % 100 == 0) {
                println("$i :: $insertedSize ${tree.allocatedNodes} ${tree.allocatedBytes} ${tree.slotsAllocedBySize.mapIndexed { idx, it -> it * tree.listSliceSizes[idx] }.sum()} ${tree.slotsAllocedBySize.map { it }} ${tree.freeLists.map { it.size }}")
            }
            var s = it
            if (debugmode) {
                if (s.length > 20) {
                    s = s.substring(0, 20)
                }
                println("-----------")
            } else {
                if (s.length > 8000) {
                    s = s.substring(0, 8000)
                }
            }
            val arr = stringToIntArray(s)
            testInsertArray(arr)
            i++
        }
    }
}

fun testInsertArray(arr: IntArray) {
    val stream = convertToUTF8BitStream(arr)
    if (debugmode) {
        println("going to insert $stream")
    }
    val key = tree.insertUTF32(arr, arr.size)
    if (!fastMode) {
        if (insertMap[stream] == null) {
            insertedSize += stream.length shr 3
        }
        insertMap[stream] = key
    }
    if (debugmode) {
        println("inserting " + stream + " -> " + key)
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
    }
}

fun testInsertArray(arr: ByteArray) {
    val stream = convertToUTF8BitStream(arr)
    if (debugmode) {
        println("going to insert $stream")
    }
    val key = tree.insertByteArray(arr, arr.size)
    if (!fastMode) {
        if (insertMap[stream] == null) {
            insertedSize += stream.length shr 3
        }
        insertMap[stream] = key
    }
    if (debugmode) {
        println("inserting " + stream + " -> " + key)
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
    }
}

val tree = RadixTree()
val debugmode = false
var fastMode = false
var testcaseNumber = 4

if (debugmode) {
    fastMode = false
    tree.print()
}
var i = 0
var insertedSize = 0
val insertMap = mutableMapOf<String, Int>()

when (testcaseNumber) {
    0 -> {
        testUsingFile("/mnt/luposdate-testdata/sp2b/16384/intermediate.dictionary")
    }
    1 -> {
        testUsingFile("/mnt/luposdate-testdata/yago2/yago-2.n3")
    }
    2 -> {
        val arr = IntArray(2)
        for (i in 0 until 32) {
            arr[1] = i * 4
            for (j in 0 until 32) {
                if (debugmode) {
                    println("-----------")
                }
                arr[0] = j * 4
                testInsertArray(arr)
            }
        }
    }
    3 -> {
        testUsingFile("/mnt/luposdate-testdata/generate_random_words")
    }
    4 -> {
        val data = Array((2 shl 16) + (2 shl 8)) {
            if (it < 256) {
                byteArrayOf(it.toByte())
            } else {
                var i = it - 256
                byteArrayOf(((i shr 8) and 0xff).toByte(), (i and 0xff).toByte())
            }
        }
        data.shuffle(Random(1))
        for (d in data) {
            testInsertArray(d)
        }
    }
}
if (!fastMode) {
    if (!debugmode) {
        tree.print()
        for ((k, v) in insertMap) {
            val v2 = tree.debugMap[k]
            if (v != v2) {
                throw Exception("value $k $v $v2")
            }
        }
        for ((k, v) in tree.debugMap) {
            val v2 = insertMap[k]
            if (v != v2) {
                throw Exception("value $k $v2 $v")
            }
        }
    }
}
tree.print(4)
