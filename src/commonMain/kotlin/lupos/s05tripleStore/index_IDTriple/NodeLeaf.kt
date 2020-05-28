package lupos.s05tripleStore.index_IDTriple
import lupos.s00misc.Coverage
import lupos.s00misc.readInt1
import lupos.s00misc.readInt2
import lupos.s00misc.readInt3
import lupos.s00misc.readInt4
import lupos.s00misc.SanityCheck
import lupos.s00misc.writeInt1
import lupos.s00misc.writeInt2
import lupos.s00misc.writeInt3
import lupos.s00misc.writeInt4
var debugListLeaf = mutableListOf<Int>()
inline class NodeLeaf(val data: ByteArray) { //ByteBuffer??
    /*
     * Bytes 0..3 : Number of stored Triples
     * Bytes 4..7 : next-page-pointer, 0x8FFFFFFF is the "null"-pointer avoiding the highest bit because of the signedness behaviour of java/kotlin
     * afterwards :
     *
     * header (Bitlayout 7..0)
     * bits 0..1: # Bytes _for S (00->1,01->2,10->3,11->4)
     * bits 2..3: # Bytes _for P (00->1,01->2,10->3,11->4)
     * bits 4..5: # Bytes _for O (00->1,01->2,10->3,11->4)
     * bits 6..7: (00->SPO,01->PO,10->O,11->undefined)
     *
     * absolute minimum is 21 used bytes for_ exactly 1 Triple/Node
     */
    fun getFirstTriple(b: IntArray) {
Coverage.funStart(6092)
        b[0] = data.readInt4(9)
Coverage.statementStart(6093)
        b[1] = data.readInt4(13)
Coverage.statementStart(6094)
        b[2] = data.readInt4(17)
Coverage.statementStart(6095)
    }
    fun toByteArray() = this.data
    fun setNextNode(node: Int) {
Coverage.funStart(6096)
        data.writeInt4(4, node)
Coverage.statementStart(6097)
    }
    fun getNextNode(): Int {
Coverage.funStart(6098)
        return data.readInt4(4)
    }
    fun setTripleCount(count: Int) {
Coverage.funStart(6099)
        data.writeInt4(0, count)
Coverage.statementStart(6100)
    }
    fun getTripleCount(): Int {
Coverage.funStart(6101)
        return data.readInt4(0)
    }
    fun iterator(): TripleIterator {
Coverage.funStart(6102)
        return NodeLeafIterator(this)
    }
    fun iterator3(prefix: IntArray): TripleIterator {
Coverage.funStart(6103)
        return NodeLeafIteratorPrefix3(this, prefix)
    }
    fun iterator2(prefix: IntArray): TripleIterator {
Coverage.funStart(6104)
        return NodeLeafIteratorPrefix2(this, prefix)
    }
    fun iterator1(prefix: IntArray): TripleIterator {
Coverage.funStart(6105)
        return NodeLeafIteratorPrefix1(this, prefix)
    }
    inline fun writeFullTriple(offset: Int, d: IntArray): Int {
Coverage.funStart(6106)
        /*
Coverage.statementStart(6107)
         * assuming enough space
Coverage.statementStart(6108)
         * return bytes written
Coverage.statementStart(6109)
         */
Coverage.statementStart(6110)
        data.writeInt1(offset, 0b00111111)
Coverage.statementStart(6111)
        data.writeInt4(offset + 1, d[0])
Coverage.statementStart(6112)
        data.writeInt4(offset + 5, d[1])
Coverage.statementStart(6113)
        data.writeInt4(offset + 9, d[2])
Coverage.statementStart(6114)
        SanityCheck {
Coverage.statementStart(6115)
            debugListLeaf.add(d[0])
Coverage.statementStart(6116)
            debugListLeaf.add(d[1])
Coverage.statementStart(6117)
            debugListLeaf.add(d[2])
Coverage.statementStart(6118)
        }
Coverage.statementStart(6119)
        return 13
    }
    inline fun writeDiffTriple(offset: Int, l: IntArray, d: IntArray, b: IntArray): Int {
Coverage.funStart(6120)
        /*
Coverage.statementStart(6121)
         * assuming enough space
Coverage.statementStart(6122)
         * returns bytes written
Coverage.statementStart(6123)
         */
Coverage.statementStart(6124)
        SanityCheck {
Coverage.statementStart(6125)
            debugListLeaf.add(d[0])
Coverage.statementStart(6126)
            debugListLeaf.add(d[1])
Coverage.statementStart(6127)
            debugListLeaf.add(d[2])
Coverage.statementStart(6128)
        }
Coverage.statementStart(6129)
        b[0] = l[0] xor d[0]
Coverage.statementStart(6130)
        b[1] = l[1] xor d[1]
Coverage.statementStart(6131)
        b[2] = l[2] xor d[2]
Coverage.statementStart(6132)
        l[0] = d[0]
Coverage.statementStart(6133)
        l[1] = d[1]
Coverage.statementStart(6134)
        l[2] = d[2]
Coverage.statementStart(6135)
        SanityCheck {
Coverage.statementStart(6136)
            SanityCheck.check { d[0] >= 0 }
Coverage.statementStart(6137)
            SanityCheck.check { d[1] >= 0 }
Coverage.statementStart(6138)
            SanityCheck.check { d[2] >= 0 }
Coverage.statementStart(6139)
            SanityCheck.check { l[0] >= 0 }
Coverage.statementStart(6140)
            SanityCheck.check { l[1] >= 0 }
Coverage.statementStart(6141)
            SanityCheck.check { l[2] >= 0 }
Coverage.statementStart(6142)
            SanityCheck.check { b[0] >= 0 }
Coverage.statementStart(6143)
            SanityCheck.check { b[1] >= 0 }
Coverage.statementStart(6144)
            SanityCheck.check { b[2] >= 0 }
Coverage.statementStart(6145)
        }
Coverage.statementStart(6146)
        var header = 0b00000000
Coverage.statementStart(6147)
        var localOff = offset + 1
Coverage.statementStart(6148)
        var flag = false
Coverage.statementStart(6149)
        if (b[0] >= (1 shl 24)) {
Coverage.ifStart(6150)
            header = 0b00110000
Coverage.statementStart(6151)
            data.writeInt4(localOff, b[0])
Coverage.statementStart(6152)
            localOff += 4
Coverage.statementStart(6153)
            flag = true
Coverage.statementStart(6154)
        } else if (b[0] >= (1 shl 16)) {
Coverage.ifStart(6155)
            header = 0b00100000
Coverage.statementStart(6156)
            data.writeInt3(localOff, b[0])
Coverage.statementStart(6157)
            localOff += 3
Coverage.statementStart(6158)
            flag = true
Coverage.statementStart(6159)
        } else if (b[0] >= (1 shl 8)) {
Coverage.ifStart(6160)
            header = 0b00010000
Coverage.statementStart(6161)
            data.writeInt2(localOff, b[0])
Coverage.statementStart(6162)
            localOff += 2
Coverage.statementStart(6163)
            flag = true
Coverage.statementStart(6164)
        } else if (b[0] >= 0) {
Coverage.ifStart(6165)
            data.writeInt1(localOff, b[0])
Coverage.statementStart(6166)
            localOff += 1
Coverage.statementStart(6167)
            flag = true
Coverage.statementStart(6168)
        }
Coverage.statementStart(6169)
        if (b[1] >= (1 shl 24)) {
Coverage.ifStart(6170)
            if (flag) {
Coverage.ifStart(6171)
                header = header or 0b00001100
Coverage.statementStart(6172)
            } else {
Coverage.ifStart(6173)
                header = 0b01001100
Coverage.statementStart(6174)
            }
Coverage.statementStart(6175)
            data.writeInt4(localOff, b[1])
Coverage.statementStart(6176)
            localOff += 4
Coverage.statementStart(6177)
            flag = true
Coverage.statementStart(6178)
        } else if (b[1] >= (1 shl 16)) {
Coverage.ifStart(6179)
            if (flag) {
Coverage.ifStart(6180)
                header = header or 0b00001000
Coverage.statementStart(6181)
            } else {
Coverage.ifStart(6182)
                header = 0b01001000
Coverage.statementStart(6183)
            }
Coverage.statementStart(6184)
            data.writeInt3(localOff, b[1])
Coverage.statementStart(6185)
            localOff += 3
Coverage.statementStart(6186)
            flag = true
Coverage.statementStart(6187)
        } else if (b[1] >= (1 shl 8)) {
Coverage.ifStart(6188)
            if (flag) {
Coverage.ifStart(6189)
                header = header or 0b00000100
Coverage.statementStart(6190)
            } else {
Coverage.ifStart(6191)
                header = 0b01000100
Coverage.statementStart(6192)
            }
Coverage.statementStart(6193)
            data.writeInt2(localOff, b[1])
Coverage.statementStart(6194)
            localOff += 2
Coverage.statementStart(6195)
            flag = true
Coverage.statementStart(6196)
        } else {
Coverage.ifStart(6197)
            SanityCheck.check { b[1] >= 0 || flag }
Coverage.statementStart(6198)
            if (!flag) {
Coverage.ifStart(6199)
                header = 0b01000000
Coverage.statementStart(6200)
            }
Coverage.statementStart(6201)
            data.writeInt1(localOff, b[1])
Coverage.statementStart(6202)
            localOff += 1
Coverage.statementStart(6203)
            flag = true
Coverage.statementStart(6204)
        }
Coverage.statementStart(6205)
        if (b[2] >= (1 shl 24)) {
Coverage.ifStart(6206)
            if (flag) {
Coverage.ifStart(6207)
                header = header or 0b00000011
Coverage.statementStart(6208)
            } else {
Coverage.ifStart(6209)
                header = 0b10000011
Coverage.statementStart(6210)
            }
Coverage.statementStart(6211)
            data.writeInt4(localOff, b[2])
Coverage.statementStart(6212)
            localOff += 4
Coverage.statementStart(6213)
            flag = true
Coverage.statementStart(6214)
        } else if (b[2] >= (1 shl 16)) {
Coverage.ifStart(6215)
            if (flag) {
Coverage.ifStart(6216)
                header = header or 0b00000010
Coverage.statementStart(6217)
            } else {
Coverage.ifStart(6218)
                header = 0b10000010
Coverage.statementStart(6219)
            }
Coverage.statementStart(6220)
            data.writeInt3(localOff, b[2])
Coverage.statementStart(6221)
            localOff += 3
Coverage.statementStart(6222)
            flag = true
Coverage.statementStart(6223)
        } else if (b[2] >= (1 shl 8)) {
Coverage.ifStart(6224)
            if (flag) {
Coverage.ifStart(6225)
                header = header or 0b00000001
Coverage.statementStart(6226)
            } else {
Coverage.ifStart(6227)
                header = 0b10000001
Coverage.statementStart(6228)
            }
Coverage.statementStart(6229)
            data.writeInt2(localOff, b[2])
Coverage.statementStart(6230)
            localOff += 2
Coverage.statementStart(6231)
            flag = true
Coverage.statementStart(6232)
        } else {
Coverage.ifStart(6233)
            SanityCheck.check { b[2] >= 0 || flag }
Coverage.statementStart(6234)
            if (!flag) {
Coverage.ifStart(6235)
                header = 0b10000000
Coverage.statementStart(6236)
            }
Coverage.statementStart(6237)
            data.writeInt1(localOff, b[2])
Coverage.statementStart(6238)
            localOff += 1
Coverage.statementStart(6239)
            flag = true
Coverage.statementStart(6240)
        }
Coverage.statementStart(6241)
        data.writeInt1(offset, header)
Coverage.statementStart(6242)
        SanityCheck.check { flag }//otherwise this triple would equal the last one
Coverage.statementStart(6243)
        SanityCheck.check { localOff > offset + 1 }//at least ony byte must have been written additionally to the header
Coverage.statementStart(6244)
        return localOff - offset
    }
    fun initializeWith(iterator: TripleIterator) {
Coverage.funStart(6245)
        SanityCheck {
Coverage.statementStart(6246)
            debugListLeaf.clear()
Coverage.statementStart(6247)
        }
Coverage.statementStart(6248)
        SanityCheck.check { iterator.hasNext() }
Coverage.statementStart(6249)
        var tripleCurrent = iterator.next()
Coverage.statementStart(6250)
        val tripleLast = intArrayOf(tripleCurrent[0], tripleCurrent[1], tripleCurrent[2])
Coverage.statementStart(6251)
        val tripleBuf = IntArray(3)
Coverage.statementStart(6252)
        var offset = 8
Coverage.statementStart(6253)
        var bytesWritten = writeFullTriple(offset, tripleLast)
Coverage.statementStart(6254)
        offset += bytesWritten
Coverage.statementStart(6255)
        val offsetEnd = data.size - bytesWritten // reserve at least enough space to write a full triple at the end
Coverage.statementStart(6256)
        var triples = 1
Coverage.statementStart(6257)
        while (iterator.hasNext() && offset <= offsetEnd) {
Coverage.whileLoopStart(6258)
            bytesWritten = writeDiffTriple(offset, tripleLast, iterator.next(), tripleBuf)
Coverage.statementStart(6259)
            offset += bytesWritten
Coverage.statementStart(6260)
            triples++
Coverage.statementStart(6261)
        }
Coverage.statementStart(6262)
        setTripleCount(triples)
Coverage.statementStart(6263)
        setNextNode(NodeManager.nodeNullPointer)
Coverage.statementStart(6264)
        SanityCheck {
Coverage.statementStart(6265)
            var it = iterator()
Coverage.statementStart(6266)
            var offset2 = 0
Coverage.statementStart(6267)
            for (i in debugListLeaf) {
Coverage.forLoopStart(6268)
            }
Coverage.statementStart(6269)
            while (offset2 < debugListLeaf.size) {
Coverage.whileLoopStart(6270)
                SanityCheck.check { it.hasNext() }
Coverage.statementStart(6271)
                var tmp = it.next()
Coverage.statementStart(6272)
                SanityCheck.check { tmp[0] == debugListLeaf[offset2] }
Coverage.statementStart(6273)
                SanityCheck.check { tmp[1] == debugListLeaf[offset2 + 1] }
Coverage.statementStart(6274)
                SanityCheck.check { tmp[2] == debugListLeaf[offset2 + 2] }
Coverage.statementStart(6275)
                offset2 += 3
Coverage.statementStart(6276)
            }
Coverage.statementStart(6277)
            SanityCheck.check { !it.hasNext() }
Coverage.statementStart(6278)
        }
Coverage.statementStart(6279)
    }
}
