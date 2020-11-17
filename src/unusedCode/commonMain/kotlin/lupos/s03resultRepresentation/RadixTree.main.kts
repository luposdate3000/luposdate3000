#!/usr/bin/env kotlin
import kotlin.random.Random

class RadixTree {

    var debugMap = mutableMapOf<String, Int>()
    var next_key = null_key + 1
    var pagesCounter = 1
    var pagesCapacity = 1024
    var pages = Array<ByteArray>(1024) { ByteArray(8192) } // pages[0] is used as temporary buffer
    val rootNode: ByteArray
    val rootNodeOffset: Int
    val rootNodePtr: Int
    var rootKey = null_key

    var allocatedBytes = 0
    var allocatedNodes = 0
    val listSliceSizes = intArrayOf(16, 32, 64, 128, 256, 512, 1024, 2048, 4096, 8196)
    val freeLists = Array(listSliceSizes.size) { mutableListOf<Int>() }
    val slotsAllocedBySize = IntArray(listSliceSizes.size)
    val pagesRequiredSpare = listSliceSizes.size

    init {
        rootNodePtr = allocBytes(off_21_data)
        rootNode = pagePtrToPage(rootNodePtr)
        rootNodeOffset = pagePtrToOffset(rootNodePtr)
        rootNode.writeInt1(rootNodeOffset, header_21)
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

    inline fun pagePtrToPage(ptr: Int): ByteArray {
        return pages[ptr shr 9]
    }

    inline fun pagePtrToOffset(ptr: Int): Int {
        return (ptr and 0x1ff) shl 4
    }

    inline fun mapLenToList(len: Int): Int {
        for (i in 0 until listSliceSizes.size - 1) {
            if (len <= listSliceSizes[i]) {
                return i
            }
        }
        return listSliceSizes.size - 1
    }

    inline fun allocBytes(len: Int): Int {
        allocatedNodes++
        val listToUse = mapLenToList(len)
        slotsAllocedBySize[listToUse]++
        val list = freeLists[listToUse]
        allocatedBytes += len
        if (list.size > 0) {
            val ptr = list.removeAt(0)
            //println("alloc $len at ${ptr shr 9}:${(ptr and 0x1ff) shl 4} = $ptr")
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
        //println("alloc $len at ${newPage shr 9}:${(newPage and 0x1ff) shl 4} = $newPage")
        return newPage
    }

    inline fun freeBytes(ptr: Int) {
        //println("freeing $ptr")
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

        const val off_ptrA = 1 // if ptr exist always the same offset
        const val off_ptrB = off_ptrA + 4 // if ptr exist always the same offset

        /*
         * these constants follow a strict alignment such that calculations are possible
         * - differences between the header-values are used to count bits
         * - relational-operators '<' and '>' are used to compare bit counts
         */

        const val header_00 = 0x00 // 0bit stores len, key, data
        const val header_01 = 0x01 // 1bit stores PtrA, PtrB, len, data
        const val header_02 = 0x02 // 1bit stores PtrA, PtrB, len, key, data

        const val header_10 = 0x10 // 1bit stores PtrA, PtrB
        const val header_11 = 0x11 // 2bit stores PtrA, PtrB, PtrC, PtrD
        const val header_13 = 0x13 // 4bit stores Ptr(16)
        const val header_17 = 0x17 // 8bit stores Ptr(256)

        const val header_20 = 0x20 // 1bit stores PtrA, PtrB, key
        const val header_21 = 0x21 // 2bit stores PtrA, PtrB, PtrC, PtrD, key
        const val header_23 = 0x23 // 4bit stores Ptr(16), key
        const val header_27 = 0x27 // 8bit stores Ptr(256), key

        const val off_00_len = 1
        const val off_00_key = 3
        const val off_00_data = 7
        const val off_01_len = 9
        const val off_01_data = 11
        const val off_02_len = 9
        const val off_02_key = 11
        const val off_02_data = 15

        const val off_10_data = 1 + (1 shl 3)
        const val off_11_data = 1 + (1 shl 4)
        const val off_13_data = 1 + (1 shl 6)
        const val off_17_data = 1 + (1 shl 10)

        const val off_20_key = 1 + (1 shl 3)
        const val off_21_key = 1 + (1 shl 4)
        const val off_23_key = 1 + (1 shl 6)
        const val off_27_key = 1 + (1 shl 10)
        const val off_20_data = off_20_key + 4
        const val off_21_data = off_21_key + 4
        const val off_23_data = off_23_key + 4
        const val off_27_data = off_27_key + 4
    }

    inline fun readHeader(node: ByteArray, offset: Int): Int {
        return node.readInt1(offset)
    }

    inline fun readDataOffset(node: ByteArray, offset: Int): Int {
        val header = readHeader(node, offset)
        when (header) {
            header_00 -> return offset + off_00_data
            header_01 -> return offset + off_01_data
            header_02 -> return offset + off_02_data
            header_10, header_11, header_13, header_17 -> return offset + 1 + (1 shl (3 + header - header_10))
            header_20, header_21, header_23, header_27 -> return offset + 1 + (1 shl (3 + header - header_20)) + 4
            else -> throw Exception("unknown header 0x${header.toString(16)}")
        }
    }

    inline fun readLen(node: ByteArray, offset: Int): Int {
        val header = readHeader(node, offset)
        when (header) {
            header_00 -> return node.readInt2(offset + off_00_len)
            header_01 -> return node.readInt2(offset + off_01_len)
            header_02 -> return node.readInt2(offset + off_02_len)
            header_10, header_11, header_13, header_17 -> return 0
            header_20, header_21, header_23, header_27 -> return 0
            else -> throw Exception("unknown header 0x${header.toString(16)}")
        }
    }

    inline fun readConsumedBytes(node: ByteArray, offset: Int): Int {
        val header = readHeader(node, offset)
        when (header) {
            header_00 -> return off_00_data + ((node.readInt2(offset + off_00_len) + 0x7) shr 3)
            header_01 -> return off_01_data + ((node.readInt2(offset + off_01_len) + 0x7) shr 3)
            header_02 -> return off_02_data + ((node.readInt2(offset + off_02_len) + 0x7) shr 3)
            header_10, header_11, header_13, header_17 -> return 1 + (1 shl (3 + header - header_10))
            header_20, header_21, header_23, header_27 -> return 1 + (1 shl (3 + header - header_20)) + 4
            else -> throw Exception("unknown header 0x${header.toString(16)}")
        }
    }

    inline fun readPtrSpecific(node: ByteArray, offset: Int, id: Int): Int {
        val header = readHeader(node, offset)
        when (header) {
            header_00 -> return null_ptr
            header_10, header_11, header_13, header_17 -> return node.readInt4(offset + off_ptrA + (id shl 2))
            header_20, header_21, header_23, header_27 -> return node.readInt4(offset + off_ptrA + (id shl 2))
            header_01, header_02, header_02 -> return node.readInt4(offset + off_ptrA + (id shl 2))
            else -> throw Exception("unknown header 0x${header.toString(16)}")
        }
    }

    inline fun readPtrA(node: ByteArray, offset: Int): Int {
        val header = readHeader(node, offset)
        when (header) {
            header_00 -> return null_ptr
            header_01, header_02 -> return node.readInt4(offset + off_ptrA)
            header_10, header_11, header_13, header_17 -> return node.readInt4(offset + off_ptrA)
            header_20, header_21, header_23, header_27 -> return node.readInt4(offset + off_ptrA)
            else -> throw Exception("unknown header 0x${header.toString(16)}")
        }
    }

    inline fun readPtrB(node: ByteArray, offset: Int): Int {
        val header = readHeader(node, offset)
        when (header) {
            header_00 -> return null_ptr
            header_01, header_02 -> return node.readInt4(offset + off_ptrB)
            header_10, header_11, header_13, header_17 -> return node.readInt4(offset + off_ptrB)
            header_20, header_21, header_23, header_27 -> return node.readInt4(offset + off_ptrB)
            else -> throw Exception("unknown header 0x${header.toString(16)}")
        }
    }

    inline fun readKey(node: ByteArray, offset: Int): Int {
        val header = readHeader(node, offset)
        when (header) {
            header_00 -> return node.readInt4(offset + off_00_key)
            header_01 -> return null_key
            header_02 -> return node.readInt4(offset + off_02_key)
            header_10, header_11, header_13, header_17 -> return null_key
            header_20, header_21, header_23, header_27 -> return node.readInt4(offset + 1 + (1 shl (3 + header - header_20)))
            else -> throw Exception("unknown header 0x${header.toString(16)}")
        }
    }

    inline fun readHeaderByPtr(ptr: Int): Int {
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
        //println("counting from 0x${header.toString(16)}, remaining $len")
        when (header) {
            header_00 -> {
                return 1
            }
            header_01, header_02 -> {
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
            header_10, header_11, header_13, header_17 -> {
                var bitCount = 1 + header - header_10
                if (len < bitCount) {
                    throw Exception("wrong depth to start counting $len :: 0x${header.toString(16)} $ bitCount ${1 shl (bitCount - 1)}")
                }
                var res = 0
                for (id2 in 0 until (1 shl bitCount)) {
                    val p = readPtrSpecific(current, currentOff, id2)
                    if (p != null_ptr) {
                        if (len == bitCount) {
                            res++
                        } else {
                            res += countChilds(p, len - bitCount)
                        }
                    }
                }
                return res
            }
            header_20, header_21, header_23, header_27 -> {
                var bitCount = 1 + header - header_20
                if (len < bitCount) {
                    throw Exception("wrong depth to start counting $len :: 0x${header.toString(16)}")
                }
                var res = 0
                for (id2 in 0 until (1 shl bitCount)) {
                    val p = readPtrSpecific(current, currentOff, id2)
                    if (p != null_ptr) {
                        if (len == bitCount) {
                            res++
                        } else {
                            res += countChilds(p, len - bitCount)
                        }
                    }
                }
                return res
            }
            else -> {
                throw Exception("unknown header 0x${header.toString(16)}")
            }
        }
    }

    fun writeChilds(target: ByteArray, targetOff: Int, idPrefix: Int, len: Int, currentPtr: Int, currentDepth: Int) {
        if (len == 0) {
            //println("previousid k :: ${idPrefix.toString(2)} depth :: $currentDepth $len add :: ${0.toString(2)} ${idPrefix.toString(2).padStart(8, '0')}")
            target.writeInt4(targetOff + off_ptrA + (idPrefix shl 2), currentPtr)
            return
        }
        if (currentPtr == null_ptr) {
            for (id in 0 until (1 shl len)) {
                //println("previousid j :: ${idPrefix.toString(2)} depth :: $currentDepth $len add :: ${id.toString(2)} ${(idPrefix + id).toString(2).padStart(8, '0')}")
                target.writeInt4(targetOff + off_ptrA + ((idPrefix + id) shl 2), currentPtr)
            }
            return
        }
        var pageBuffer = pages[0]
        val current = pagePtrToPage(currentPtr)
        val currentOff = pagePtrToOffset(currentPtr)
        val currentHeader = readHeader(current, currentOff)
        //println("writing from 0x${currentHeader.toString(16)}, remaining $len")
        when (currentHeader) {
            header_20, header_21, header_23, header_27 -> {
                var bitCount = 1 + currentHeader - header_20
                for (id2 in 0 until (1 shl bitCount)) {
                    val p = readPtrSpecific(current, currentOff, id2)
                    //println("previousid a :: ${idPrefix.toString(2)} depth :: $currentDepth $len add :: ${(id2 shl (len - bitCount)).toString(2)}")
                    writeChilds(target, targetOff, idPrefix + (id2 shl (len - bitCount)), len - bitCount, p, currentDepth + bitCount)
                }
            }
            header_10, header_11, header_13, header_17 -> {
                var bitCount = 1 + currentHeader - header_10
                for (id2 in 0 until (1 shl bitCount)) {
                    val p = readPtrSpecific(current, currentOff, id2)
                    //println("previousid b :: ${idPrefix.toString(2)} depth :: $currentDepth $len add :: ${(id2 shl (len - bitCount)).toString(2)}")
                    writeChilds(target, targetOff, idPrefix + (id2 shl (len - bitCount)), len - bitCount, p, currentDepth + bitCount)
                }
            }
            header_01 -> {
                val currentDataOff = readDataOffset(current, currentOff)
                val l = readLen(current, currentOff)
                if (len > l) {
                    val significantBit = (current[currentDataOff].toInt() shr (8 - l)) and ((1 shl l) - 1)
                    val ll = len - l
                    val myPrefix = idPrefix + (significantBit shl ll)
                    for (id2 in 0 until 2) {
                        val p = readPtrSpecific(current, currentOff, id2)
                        //println("previousid c :: ${idPrefix.toString(2)} depth :: $currentDepth $len add :: ${(id2 shl (ll - 1)).toString(2)}")
                        writeChilds(target, targetOff, myPrefix + (id2 shl (ll - 1)), ll - 1, p, currentDepth + l + 1)
                    }
                } else {
                    val significantBit = (current[currentDataOff].toInt() shr (8 - len)) and ((1 shl len) - 1)
                    shiftLeft(current, pageBuffer, (currentDataOff shl 3) + len, l - len)
                    val key = readKey(current, currentOff)
                    val ptrA = readPtrA(current, currentOff)
                    val ptrB = readPtrB(current, currentOff)
                    //println("createChild 15")
                    val newChild = createChild(pageBuffer, 0, l - len, key, currentDepth + len, ptrA, ptrB)
                    //println("previousid e :: ${idPrefix.toString(2)} depth :: $currentDepth $len add :: ${(idPrefix + significantBit).toString(2)}")
                    writeChilds(target, targetOff, idPrefix + significantBit, 0, newChild, currentDepth + len)
                }
            }
            header_02, header_00 -> {
                val currentDataOff = readDataOffset(current, currentOff)
                val l = readLen(current, currentOff)
                if (len > l) {
                    val significantBit = (current[currentDataOff].toInt() shr (8 - l)) and ((1 shl l) - 1)
                    val key = readKey(current, currentOff)
                    val ptrA = readPtrA(current, currentOff)
                    val ptrB = readPtrB(current, currentOff)
                    //println("previousid g :: ${idPrefix.toString(2)} depth :: $currentDepth $len add :: ${(significantBit shl (len - l)).toString(2)}")
                    //println("previousid h :: ${idPrefix.toString(2)} depth :: $currentDepth $len add :: ${((significantBit shl (len - l)) + (1 shl (len - l - 1))).toString(2)}")
                    writeChilds(target, targetOff, idPrefix + (significantBit shl (len - l)), len - l - 1, ptrA, currentDepth + l + 1)
                    writeChilds(target, targetOff, idPrefix + (significantBit shl (len - l)) + (1 shl (len - l - 1)), len - l - 1, ptrB, currentDepth + l + 1)
                } else {
                    val significantBit = (current[currentDataOff].toInt() shr (8 - len)) and ((1 shl len) - 1)
                    shiftLeft(current, pageBuffer, (currentDataOff shl 3) + len, l - len)
                    val key = readKey(current, currentOff)
                    val ptrA = readPtrA(current, currentOff)
                    val ptrB = readPtrB(current, currentOff)
                    //println("createChild 16")
                    //println("??? $len $l $currentDepth")
                    val newChild = createChild(pageBuffer, 0, l - len, key, currentDepth + len, ptrA, ptrB)
                    //println("previousid i :: ${idPrefix.toString(2)} depth :: $currentDepth $len add :: ${significantBit.toString(2)}")
                    writeChilds(target, targetOff, idPrefix + significantBit, 0, newChild, currentDepth + len)
                }
            }
            else -> {
                throw Exception("unkwnown header $currentHeader")
            }
        }
        freeBytes(currentPtr)
    }

    inline fun verifyCurrentDepth(stack: IntArray, stackPtr: Int, currentDepth: Int) {
        if (debugmode) {
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
    }

    inline fun calculateDepth(currentPtr: Int): Int {
        val current = pagePtrToPage(currentPtr)
        val currentOff = pagePtrToOffset(currentPtr)
        val currentHeader = readHeader(current, currentOff)
        when (currentHeader) {
            header_10, header_11, header_13, header_17 -> return 1 + currentHeader - header_10
            header_20, header_21, header_23, header_27 -> return 1 + currentHeader - header_20
            header_01, header_02 -> return 1 + readLen(current, currentOff)
            header_00 -> return readLen(current, currentOff)
            else -> throw Exception("unkwnown header 0x${currentHeader.toString(16)}")
        }
    }

    fun checkStack(stack: IntArray, stackPtr: Int, currentDepth: Int) {
        var depth = currentDepth
        var sPtr = stackPtr
        var maxStepsDeeper = 2
        // println("checkStack initial $currentDepth")
        while (sPtr > 0) {
            verifyCurrentDepth(stack, sPtr, depth)
            var currentPtr = stack[sPtr - 1]
            if (currentPtr != null_ptr) {
                val parentDepth = depth - calculateDepth(currentPtr)
                //println("checkStack ${stack[sPtr - 1]} $depth")
                checkStackHelper(stack, sPtr, depth, maxStepsDeeper)
                depth = parentDepth
                maxStepsDeeper--
            }
            sPtr--
        }
    }

    fun checkStackHelper(stack: IntArray, stackPtr: Int, currentDepth: Int, maxStepsDeeper: Int) {
        //println("checkToOptimize $stackPtr")
        verifyCurrentDepth(stack, stackPtr, currentDepth)
        var currentPtr = stack[stackPtr - 1]
        val current = pagePtrToPage(currentPtr)
        val currentOff = pagePtrToOffset(currentPtr)
        val currentHeader = readHeader(current, currentOff)
        if (maxStepsDeeper > 0) {
            when (currentHeader) {
                header_01, header_02 -> {
                    for (id2 in 0 until 2) {
                        val p = readPtrSpecific(current, currentOff, id2)
                        if (p != null_ptr) {
                            stack[stackPtr] = p
                            checkStackHelper(stack, stackPtr + 1, currentDepth + calculateDepth(p), maxStepsDeeper - 1)
                        }
                    }
                }
                header_20, header_21, header_23, header_27 -> {
                    var bitCount = 1 + currentHeader - header_20
                    for (id2 in 0 until (1 shl bitCount)) {
                        val p = readPtrSpecific(current, currentOff, id2)
                        if (p != null_ptr) {
                            stack[stackPtr] = p
                            checkStackHelper(stack, stackPtr + 1, currentDepth + calculateDepth(p), maxStepsDeeper - 1)
                        }
                    }
                }
                header_10, header_11, header_13, header_17 -> {
                    var bitCount = 1 + currentHeader - header_10
                    for (id2 in 0 until (1 shl bitCount)) {
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
//TODO                var bits = 3
            val parentDepth = currentDepth - calculateDepth(currentPtr)
            var bitsC = 3
            while (bitsC > 0) {
                val bits = 1 shl bitsC
                if ((parentDepth) and (bits - 1) == 0) {
                    if ((parentDepth) and (0x7) == 0) {
                        //header_2x
                        var newHeader = header_20 + bits - 1
                        if (currentHeader < newHeader) {
                            //println("currentHeader 0x${currentHeader.toString(16)} 0x${newHeader.toString(16)} ${bits} ${(1 shl (bits - 1))} $currentDepth $parentDepth")
                            var count = countChilds(currentPtr, bits)
                            if (count >= (1 shl (bits - 1)) && count < (1 shl bits)) {
                                //println("bits $bits 0x${newHeader.toString(16)} alloc(${1 + (1 shl (bits + 2)) + 4}) key@${(1 + (1 shl (bits + 2)))}")
                                var key = readKey(current, currentOff)
                                //println("createChild 18 :: $bits")
                                val nodePtr = allocBytes(1 + (1 shl (bits + 2)) + 4)
                                //println("child18 at $nodePtr ?!?")
                                val nodeOff = pagePtrToOffset(nodePtr)
                                var node = pagePtrToPage(nodePtr)
                                node.writeInt1(nodeOff, newHeader)
                                node.writeInt4(nodeOff + 1 + (1 shl (bits + 2)), key)
                                for (id in 0 until (1 shl bits)) {
                                    node.writeInt4(nodeOff + 1 + (id shl 2), null_ptr)
                                }
                                writeChilds(node, nodeOff, 0, bits, currentPtr, currentDepth - calculateDepth(currentPtr))
                                //println("writing to parent ${stackPtr} ${stack[stackPtr - 2]} $currentPtr $nodePtr")
                                updatePointer(stack[stackPtr - 2], currentPtr, nodePtr)
                                stack[stackPtr - 1] == nodePtr
                                //this.print()
                                return
                            }
                        }
                    } else {
//header_1x
                        var newHeader = header_10 + bits - 1
                        if (currentHeader < newHeader) {
                            //println("currentHeader 0x${currentHeader.toString(16)} 0x${newHeader.toString(16)} ${bits} ${(1 shl (bits - 1))} $currentDepth $parentDepth")
                            var count = countChilds(currentPtr, bits)
                            if (count >= (1 shl (bits - 1)) && count < (1 shl bits)) {
                                //println("bits $bits 0x${newHeader.toString(16)} alloc(${1 + (1 shl (bits + 2))})")
                                //println("createChild 18 :: $bits")
                                val nodePtr = allocBytes(1 + (1 shl (bits + 2)))
                                //println("child18 at $nodePtr ?!?")
                                val nodeOff = pagePtrToOffset(nodePtr)
                                var node = pagePtrToPage(nodePtr)
                                node.writeInt1(nodeOff, newHeader)
                                for (id in 0 until (1 shl bits)) {
                                    node.writeInt4(nodeOff + 1 + (id shl 2), null_ptr)
                                }
                                writeChilds(node, nodeOff, 0, bits, currentPtr, currentDepth - calculateDepth(currentPtr))
                                //println("writing to parent ${stackPtr} ${stack[stackPtr - 2]} $currentPtr $nodePtr")
                                updatePointer(stack[stackPtr - 2], currentPtr, nodePtr)
                                stack[stackPtr - 1] == nodePtr
                                //this.print()
                                return
                            }
                        }
                    }
                }
                bitsC--
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
        var header = header_01
        if (((currentDepth + len) and 0x7) == 0) {
            if (ptrA == null_ptr && ptrB == null_ptr) {
                header = header_00
            } else {
                if (len == 0) {
                    header = header_20
                } else {
                    header = header_02
                }
            }
        } else {
            if (ptrA != null_ptr && ptrB != null_ptr) {
                if (len == 0) {
                    header = header_10
                }
            }
        }
        when (header) {
            header_01 -> {
                val nodePtr = allocBytes(off_01_data + ((len + 0x7) shr 3))
                val nodeOff = pagePtrToOffset(nodePtr)
                var node = pagePtrToPage(nodePtr)
                node.writeInt1(nodeOff, header)
                node.writeInt4(nodeOff + off_ptrA, ptrA)
                node.writeInt4(nodeOff + off_ptrB, ptrB)
                node.writeInt2(nodeOff + off_01_len, len)
                val b = (len + 0x7) shr 3
                val dataOffOut = nodeOff + off_01_data
                for (i in 0 until b) {
                    node[dataOffOut + i] = data[dataOff + i]
                }
                //println("alloc as 0x${header.toString(16)} $nodePtr")
                return nodePtr
            }
            header_02 -> {
                val nodePtr = allocBytes(off_02_data + ((len + 0x7) shr 3))
                val nodeOff = pagePtrToOffset(nodePtr)
                var node = pagePtrToPage(nodePtr)
                node.writeInt1(nodeOff, header)
                node.writeInt4(nodeOff + off_ptrA, ptrA)
                node.writeInt4(nodeOff + off_ptrB, ptrB)
                node.writeInt2(nodeOff + off_02_len, len)
                node.writeInt4(nodeOff + off_02_key, key)
                val b = (len + 0x7) shr 3
                val dataOffOut = nodeOff + off_02_data
                for (i in 0 until b) {
                    node[dataOffOut + i] = data[dataOff + i]
                }
                //println("alloc as 0x${header.toString(16)} $nodePtr")
                return nodePtr
            }
            header_00 -> {
                val nodePtr = allocBytes(off_00_data + ((len + 0x7) shr 3))
                val nodeOff = pagePtrToOffset(nodePtr)
                var node = pagePtrToPage(nodePtr)
                node.writeInt1(nodeOff, header)
                node.writeInt2(nodeOff + off_00_len, len)
                node.writeInt4(nodeOff + off_00_key, key)
                val b = (len + 0x7) shr 3
                val dataOffOut = nodeOff + off_00_data
                for (i in 0 until b) {
                    node[dataOffOut + i] = data[dataOff + i]
                }
                //println("alloc as 0x${header.toString(16)} $nodePtr")
                return nodePtr
            }
            header_10 -> {
                val nodePtr = allocBytes(off_10_data)
                val nodeOff = pagePtrToOffset(nodePtr)
                var node = pagePtrToPage(nodePtr)
                node.writeInt1(nodeOff, header)
                node.writeInt4(nodeOff + off_ptrA, ptrA)
                node.writeInt4(nodeOff + off_ptrB, ptrB)
                //println("alloc as 0x${header.toString(16)} $nodePtr")
                return nodePtr
            }
            header_20 -> {
                val nodePtr = allocBytes(off_10_data)
                val nodeOff = pagePtrToOffset(nodePtr)
                var node = pagePtrToPage(nodePtr)
                node.writeInt1(nodeOff, header)
                node.writeInt4(nodeOff + off_ptrA, ptrA)
                node.writeInt4(nodeOff + off_ptrB, ptrB)
                node.writeInt4(nodeOff + off_20_key, key)
                //println("alloc as 0x${header.toString(16)} $nodePtr")
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

    inline fun equalBits(dataA: ByteArray, dataB: ByteArray, offsetA: Int, offsetB: Int, len: Int): Int {
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
            }
        } catch (e: Throwable) {
            var header = readHeader(currentPage, currentPageOffset)
            println("$header $dataOff $currentPageOffset $off_00_data $len $pagePtr")
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
        println(value + " :: " + key + " = " + pagePtr + " ? 0x" + header.toString(16))
        when (header) {
            header_11, header_13, header_17 -> {
                var bitCount = 1 + header - header_10
                for (id in 0 until (1 shl (bitCount))) {
                    var ptr = readPtrSpecific(currentPage, currentPageOffset, id)
                    if (ptr != null_ptr && maxdepth > 0) {
                        usedBytes += print(ptr, value + id.toString(2).padStart(bitCount, '0'), maxdepth - 1)
                    }
                }
            }
            header_21, header_23, header_27 -> {
                var bitCount = 1 + header - header_20
                for (id in 0 until (1 shl (bitCount))) {
                    var ptr = readPtrSpecific(currentPage, currentPageOffset, id)
                    if (ptr != null_ptr && maxdepth > 0) {
                        usedBytes += print(ptr, value + id.toString(2).padStart(bitCount, '0'), maxdepth - 1)
                    }
                }
            }
            header_01, header_02, header_00, header_10, header_20 -> {
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
                throw Exception("unknown header 0x${header.toString(16)}")
            }
        }
        return usedBytes
    }

    inline fun updatePointer(parentPtr: Int, currentPtr: Int, newPtr: Int) {
        if (parentPtr == null_ptr) {
            throw Exception("")
        }
        if (currentPtr == null_ptr) {
            throw Exception("")
        }
        val parent = pagePtrToPage(parentPtr)
        val parentOff = pagePtrToOffset(parentPtr)
        val header = readHeader(parent, parentOff)
        when (header) {
            header_00 -> {
                throw Exception("invalid header")
            }
            header_01, header_02 -> {
                if (parent.readInt4(parentOff + off_ptrA) == currentPtr) {
                    parent.writeInt4(parentOff + off_ptrA, newPtr)
                } else {
                    if (parent.readInt4(parentOff + off_ptrB) != currentPtr) {
                        throw Exception("child not found")
                    }
                    parent.writeInt4(parentOff + off_ptrB, newPtr)
                }
            }
            header_10, header_11, header_13, header_17 -> {
                var bitCount = 1 + header - header_10
                for (id in 0 until (1 shl bitCount)) {
                    if (parent.readInt4(parentOff + off_ptrA + (id shl 2)) == currentPtr) {
                        parent.writeInt4(parentOff + off_ptrA + (id shl 2), newPtr)
                        return
                    }
                }
                throw Exception("child not found")
            }
            header_20, header_21, header_23, header_27 -> {
                var bitCount = 1 + header - header_20
                for (id in 0 until (1 shl bitCount)) {
                    if (parent.readInt4(parentOff + off_ptrA + (id shl 2)) == currentPtr) {
                        parent.writeInt4(parentOff + off_ptrA + (id shl 2), newPtr)
                        return
                    }
                }
                throw Exception("child not found")
            }
            else -> {
                throw Exception("unknown header 0x${header.toString(16)}")
            }
        }
    }

    inline fun updatePointerSpecific(parentPtr: Int, id: Int, newPtr: Int) {
        if (parentPtr == null_ptr) {
            throw Exception("")
        }
        val parent = pagePtrToPage(parentPtr)
        val parentOff = pagePtrToOffset(parentPtr)
        if (parent.readInt1(parentOff) == header_00) {
            throw Exception("invalid header")
        }
        parent.writeInt4(parentOff + off_ptrA + (id shl 2), newPtr)
    }


    inline fun insertUTF32(inData: IntArray, inDataLength: Int): Int {
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
        if (pagesRequiredSpare + pagesCounter > pagesCapacity) {
            val newCapacity = pagesCapacity shl 1
            val newPages = Array<ByteArray>(newCapacity) {
                if (it < pagesCapacity) {
                    pages[it]
                } else {
                    ByteArray(8192)
                }
            }
            pagesCapacity = newCapacity
            pages = newPages
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
            //println("path $currentPtr 0x${header.toString(16)} $inLen $currentDepth")
            when (header) {
                header_20, header_21, header_23, header_27 -> {
                    var bitCount = 1 + header - header_20
                    if (inLen == 0) {
                        var key = readKey(currentPage, currentPageOffset)
                        if (key == null_key) {
                            key = next_key++
                            val off_key = 1 + (1 shl (2 + bitCount))
                            currentPage.writeInt4(currentPageOffset + off_key, key)
                        }
                        return key
                    }
                    val significantBit = (data[0].toInt() shr (8 - bitCount)) and ((1 shl bitCount) - 1)
                    shiftLeft(data, data1, bitCount, inLen - bitCount)
                    inLen -= bitCount
                    val ptr = readPtrSpecific(currentPage, currentPageOffset, significantBit)
                    if (ptr == null_ptr) {
                        var kk = next_key++
                        //println("createChild 19 :: $bitCount")
                        val pagePtr = createChild(data1, 0, inLen, kk, currentDepth + bitCount)
                        updatePointerSpecific(currentPtr, significantBit, pagePtr)
                        checkStack(stack, stackPtr - 1, currentDepth - stackLen[stackPtr - 1])
                        return kk
                    }
                    currentPage = pagePtrToPage(ptr)
                    currentPageOffset = pagePtrToOffset(ptr)
                    currentDepth += bitCount
                    stackLen[stackPtr] = bitCount
                    stack[stackPtr] = currentPtr
                    stackPtr++
                    currentPtr = ptr
                    data2 = data1
                    data1 = data
                    data = data2
                }
                header_10, header_11, header_13, header_17 -> {
                    var bitCount = 1 + header - header_10
                    val significantBit = (data[0].toInt() shr (8 - bitCount)) and ((1 shl bitCount) - 1)
                    shiftLeft(data, data1, bitCount, inLen - bitCount)
                    inLen -= bitCount
                    val ptr = readPtrSpecific(currentPage, currentPageOffset, significantBit)
                    if (ptr == null_ptr) {
                        var kk = next_key++
                        //println("createChild 1 :: $bitCount")
                        val pagePtr = createChild(data1, 0, inLen, kk, currentDepth + bitCount)
                        updatePointerSpecific(currentPtr, significantBit, pagePtr)
                        checkStack(stack, stackPtr - 1, currentDepth - stackLen[stackPtr - 1])
                        return kk
                    }
                    currentPage = pagePtrToPage(ptr)
                    currentPageOffset = pagePtrToOffset(ptr)
                    currentDepth += bitCount
                    stackLen[stackPtr] = bitCount
                    stack[stackPtr] = currentPtr
                    stackPtr++
                    currentPtr = ptr
                    data2 = data1
                    data1 = data
                    data = data2
                }
                header_00, header_01, header_02 -> {
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
                                //println("createChild 3")
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
                            //println("createChild 4")
                            val splitChildPage = createChild(pageBuffer, 0, len - common, currentKey, currentDepth + common, ptrA, ptrB)
                            val off = dataOff + (common shr 3)
                            var newPtr = null_ptr
                            if (significantBit == 0) {
                                //println("createChild 5")
                                newPtr = createChild(currentPage, dataOff, common - 1, newKey, currentDepth, splitChildPage, null_ptr)
                                freeBytes(currentPtr)
                                updatePointer(stack[stackPtr - 1], currentPtr, newPtr)
                            } else {
                                //println("createChild 6")
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
                        //println("$common ${inLen - 1}")
                        inLen -= common
                        shiftLeft(data, data1, common, inLen)
                        if (significantBit == 0) {
                            val key = readKey(currentPage, currentPageOffset)
                            val ptrA = readPtrA(currentPage, currentPageOffset)
                            val ptrB = readPtrB(currentPage, currentPageOffset)
                            if (ptrA == null_ptr) {
                                var kk = next_key++
                                //println("createChild 7")
                                val pagePtr = createChild(data1, 0, inLen, kk, currentDepth + len + 1)
                                //println("createChild 8")
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
                                //println("createChild 9")
                                val pagePtr = createChild(data1, 0, inLen, kk, currentDepth + 1)
                                //println("createChild 10")
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
                        if (inLen - common > 0) {
                            shiftLeft(data, data1, common, inLen - common)
                        }
                        var newKey = next_key++
                        //println("createChild 11")
                        val newPage = createChild(data1, 0, inLen - common, newKey, currentDepth + common)
                        var currentKey = readKey(currentPage, currentPageOffset)
                        val ptrA = readPtrA(currentPage, currentPageOffset)
                        val ptrB = readPtrB(currentPage, currentPageOffset)
                        shiftLeft(currentPage, pageBuffer, (dataOff shl 3) + common, len - common)
                        //println("createChild 12")
                        val splitChildPage = createChild(pageBuffer, 0, len - common, currentKey, currentDepth + common, ptrA, ptrB)
                        val off = dataOff + (common shr 3)
                        var newPtr = null_ptr
                        if (significantBit == 0) {
                            //println("createChild 13")
                            newPtr = createChild(currentPage, dataOff, common - 1, null_key, currentDepth, newPage, splitChildPage)
                            freeBytes(currentPtr)
                            updatePointer(stack[stackPtr - 1], currentPtr, newPtr)
                        } else {
                            //println("createChild 14")
                            newPtr = createChild(currentPage, dataOff, common - 1, null_key, currentDepth, splitChildPage, newPage)
                            freeBytes(currentPtr)
                            updatePointer(stack[stackPtr - 1], currentPtr, newPtr)
                        }
                        checkStack(stack, stackPtr - 1, currentDepth - stackLen[stackPtr - 1])
                        return newKey
                    }
                }
                else -> {
                    throw Exception("unknown header 0x${header.toString(16)}")
                }
            }
        }
    }
}
// TEST - CODE :: -->>

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


inline fun stringToIntArray(str: String): IntArray {
    val s = str.replace(Regex("[^a-zA-Z]"), "")
    val res = IntArray(s.length)
    for (c in 0 until s.length) {
        res[c] = s[c].toInt() and 0x7f
    }
    return res
}

inline fun convertToUTF8BitStream(arr: IntArray): String {
    var s = ""
    for (i in 0 until arr.size) {
        s += arr[i].toString(2).padStart(8, '0')
    }
    return s
}

inline fun convertToUTF8BitStream(arr: ByteArray): String {
    var s = ""
    for (i in 0 until arr.size) {
        s += (arr[i].toInt() and 0xff).toString(2).padStart(8, '0')
    }
    return s
}

inline fun testUsingFile(name: String) {
    try {
        java.io.File(name).forEachLine { it2 ->
            for (it in it2.split(" ")) {
                when (testmode) {
                    0 -> {
                        if (inputNumber % 10000 == 0) {
                            println("$inputNumber :: $insertedSize ${tree.allocatedNodes} ${tree.allocatedBytes} ${tree.slotsAllocedBySize.mapIndexed { idx, it -> it * tree.listSliceSizes[idx] }.sum()} ${tree.slotsAllocedBySize.map { it }} ${tree.freeLists.map { it.size }}")
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
                    }
                    1 -> {
                        if (inputNumber % 10000 == 0) {
                            println(inputNumber)
                        }
                        val k = tree2[it]
                        if (k == null) {
                            tree2[it] = tree2Counter++
                        }
                    }
                    2 -> {
                        if (inputNumber % 10000 == 0) {
                            println(inputNumber)
                        }
                        tree3.getOrCreate(it)
                    }
                }
                inputNumber++
            }
        }
    } catch (e: Throwable) {
        println("$inputNumber :: $insertedSize ${tree.allocatedNodes} ${tree.allocatedBytes} ${tree.slotsAllocedBySize.mapIndexed { idx, it -> it * tree.listSliceSizes[idx] }.sum()} ${tree.slotsAllocedBySize.map { it }} ${tree.freeLists.map { it.size }}")
        throw e
    }
}

inline fun testInsertArray(arr: IntArray) {
    val stream = convertToUTF8BitStream(arr)
    if (debugmode) {
        println("going to insert $stream IntArray")
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

inline fun testInsertArray(arr: ByteArray) {
    val stream = convertToUTF8BitStream(arr)
    if (debugmode) {
        println("going to insert $stream ByteArray")
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

val tree3 = MyMapStringIntPatriciaTrieDouble()
tree3._init()
val tree2 = mutableMapOf<String, Int>()
var tree2Counter = 0
val tree = RadixTree()
val testmode = 0        //0:RadixTree, 1:MutableMap, 3:PatriciaTrie
val debugmode = false
var fastMode = true
val testcaseNumber = 1

if (debugmode) {
    fastMode = false
    tree.print()
}

var inputNumber = 0
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
        val data = Array((1 shl 16) + (1 shl 8)) {
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
    5 -> {
        val data = ByteArray(2)
        data[0] = 1
        data[1] = 0
        testInsertArray(data)
        for (i in 0 until 256) {
            data[0] = 0
            data[1] = i.toByte()
            testInsertArray(data)
            println("xxxxx::: ${data[0]} ${data[1]}")
        }
        println("done 5")
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
