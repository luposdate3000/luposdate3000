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
inline class NodeInner(val data: ByteArray) { //ByteBuffer??
    /*
     * Bitlayout 7..0
     * Bytes 0..3  : Number of stored Triples
     * Bytes 4..7  : next-page-pointer, 0x8FFFFFFF is the "null"-pointer avoiding the highest bit because of the signedness behaviour of java/kotlin
     * Bytes 8..11 : first child-page-pointer
     *
     * triple-group:
     * bits 0..1: # Bytes _for S (00->1,01->2,10->3,11->4)
     * bits 2..3: # Bytes _for P (00->1,01->2,10->3,11->4)
     * bits 4..5: # Bytes _for O (00->1,01->2,10->3,11->4)
     * bits 6..7: (00->SPO,01->PO,10->O,11->undefined)
     * triple-group-data: 1-12 Bytes 
     *
     * child-pointer-group: (may be only partially used if_ Number of stored tiples is reached)
     * assuming consecutive child-pointers are never the same
     * bits 0..1 # Bytes _for 1st child-pointer (00->1,01->2,10->3,11->4)
     * bits 2..3 # Bytes _for 2st child-pointer (00->1,01->2,10->3,11->4)
     * bits 4..5 # Bytes _for 3st child-pointer (00->1,01->2,10->3,11->4)
     * bits 6..7 # Bytes _for 4st child-pointer (00->1,01->2,10->3,11->4)
     * child-pointer-group-data: 4-16 Bytes
     *
     * afterwards alternating 4x triple-group followed by 1x child-pointer-group
     * at the end there might be less than 4 triple-groups in front of the last child-pointer-group
     *
     * absolute minimum is 81 used bytes _for exactly 4 Triple/Node
     */
    fun toByteArray() = data
    fun getFirstTriple(b: IntArray) {
Coverage.funStart(5540)
        var node = this
Coverage.statementStart(5541)
        var done = false
Coverage.statementStart(5542)
        while (!done) {
Coverage.whileLoopStart(5543)
            NodeManager.getNode(node.getFirstChild(), {
Coverage.statementStart(5544)
                it.getFirstTriple(b)
Coverage.statementStart(5545)
                done = true
Coverage.statementStart(5546)
            }, {
Coverage.statementStart(5547)
                node = it
Coverage.statementStart(5548)
            })
Coverage.statementStart(5549)
        }
Coverage.statementStart(5550)
    }
    fun setFirstChild(node: Int) {
Coverage.funStart(5551)
        data.writeInt4(8, node)
Coverage.statementStart(5552)
    }
    fun getFirstChild(): Int {
Coverage.funStart(5553)
        return data.readInt4(8)
    }
    fun setNextNode(node: Int) {
Coverage.funStart(5554)
        data.writeInt4(4, node)
Coverage.statementStart(5555)
    }
    fun getNextNode(): Int {
Coverage.funStart(5556)
        return data.readInt4(4)
    }
    fun setTripleCount(count: Int) {
Coverage.funStart(5557)
        data.writeInt4(0, count)
Coverage.statementStart(5558)
    }
    fun getTripleCount(): Int {
Coverage.funStart(5559)
        return data.readInt4(0)
    }
    inline fun writeFullTriple(offset: Int, d: IntArray): Int {
Coverage.funStart(5560)
        /*
Coverage.statementStart(5561)
         * assuming enough space
Coverage.statementStart(5562)
         * return bytes written
Coverage.statementStart(5563)
         */
Coverage.statementStart(5564)
        data.writeInt1(offset, 0b00111111)
Coverage.statementStart(5565)
        data.writeInt4(offset + 1, d[0])
Coverage.statementStart(5566)
        data.writeInt4(offset + 5, d[1])
Coverage.statementStart(5567)
        data.writeInt4(offset + 9, d[2])
Coverage.statementStart(5568)
        return 13
    }
    inline fun writeChildPointers(offset: Int, count: Int, d: IntArray): Int {
Coverage.funStart(5569)
        SanityCheck.check { count > 0 }
Coverage.statementStart(5570)
        SanityCheck.check { count <= 4 }
Coverage.statementStart(5571)
        SanityCheck.check { d[0] > 0 }
Coverage.statementStart(5572)
        SanityCheck.check { d[1] > 0 || count < 2 }
Coverage.statementStart(5573)
        SanityCheck.check { d[2] > 0 || count < 3 }
Coverage.statementStart(5574)
        SanityCheck.check { d[3] > 0 || count < 4 }
Coverage.statementStart(5575)
        var header = 0b00000000
Coverage.statementStart(5576)
        var localOff = offset + 1
Coverage.statementStart(5577)
        for (i in 0 until count) {
Coverage.forLoopStart(5578)
            if (d[i] >= (1 shl 24)) {
Coverage.ifStart(5579)
                header = header or (0b00000011 shl (6 - i - i))
Coverage.statementStart(5580)
                data.writeInt4(localOff, d[i])
Coverage.statementStart(5581)
                localOff += 4
Coverage.statementStart(5582)
            } else if (d[i] >= (1 shl 16)) {
Coverage.ifStart(5583)
                header = header or (0b00000010 shl (6 - i - i))
Coverage.statementStart(5584)
                data.writeInt3(localOff, d[i])
Coverage.statementStart(5585)
                localOff += 3
Coverage.statementStart(5586)
            } else if (d[i] >= (1 shl 8)) {
Coverage.ifStart(5587)
                header = header or (0b00000001 shl (6 - i - i))
Coverage.statementStart(5588)
                data.writeInt2(localOff, d[i])
Coverage.statementStart(5589)
                localOff += 2
Coverage.statementStart(5590)
            } else {
Coverage.ifStart(5591)
                SanityCheck.check { d[i] >= 0 }
Coverage.statementStart(5592)
                data.writeInt1(localOff, d[i])
Coverage.statementStart(5593)
                localOff += 1
Coverage.statementStart(5594)
            }
Coverage.statementStart(5595)
        }
Coverage.statementStart(5596)
        data.writeInt1(offset, header)
Coverage.statementStart(5597)
        return localOff - offset
    }
    inline fun writeDiffTriple(offset: Int, l: IntArray, d: IntArray, b: IntArray): Int {
Coverage.funStart(5598)
        /*
Coverage.statementStart(5599)
         * assuming enough space
Coverage.statementStart(5600)
         * returns bytes written
Coverage.statementStart(5601)
         */
Coverage.statementStart(5602)
        b[0] = l[0] xor d[0]
Coverage.statementStart(5603)
        b[1] = l[1] xor d[1]
Coverage.statementStart(5604)
        b[2] = l[2] xor d[2]
Coverage.statementStart(5605)
        l[0] = d[0]
Coverage.statementStart(5606)
        l[1] = d[1]
Coverage.statementStart(5607)
        l[2] = d[2]
Coverage.statementStart(5608)
        SanityCheck {
Coverage.statementStart(5609)
            SanityCheck.check { d[0] >= 0 }
Coverage.statementStart(5610)
            SanityCheck.check { d[1] >= 0 }
Coverage.statementStart(5611)
            SanityCheck.check { d[2] >= 0 }
Coverage.statementStart(5612)
            SanityCheck.check { l[0] >= 0 }
Coverage.statementStart(5613)
            SanityCheck.check { l[1] >= 0 }
Coverage.statementStart(5614)
            SanityCheck.check { l[2] >= 0 }
Coverage.statementStart(5615)
            SanityCheck.check { b[0] >= 0 }
Coverage.statementStart(5616)
            SanityCheck.check { b[1] >= 0 }
Coverage.statementStart(5617)
            SanityCheck.check { b[2] >= 0 }
Coverage.statementStart(5618)
        }
Coverage.statementStart(5619)
        var header = 0b00000000
Coverage.statementStart(5620)
        var localOff = offset + 1
Coverage.statementStart(5621)
        var flag = false
Coverage.statementStart(5622)
        if (b[0] >= (1 shl 24)) {
Coverage.ifStart(5623)
            header = 0b00110000
Coverage.statementStart(5624)
            data.writeInt4(localOff, b[0])
Coverage.statementStart(5625)
            localOff += 4
Coverage.statementStart(5626)
            flag = true
Coverage.statementStart(5627)
        } else if (b[0] >= (1 shl 16)) {
Coverage.ifStart(5628)
            header = 0b00100000
Coverage.statementStart(5629)
            data.writeInt3(localOff, b[0])
Coverage.statementStart(5630)
            localOff += 3
Coverage.statementStart(5631)
            flag = true
Coverage.statementStart(5632)
        } else if (b[0] >= (1 shl 8)) {
Coverage.ifStart(5633)
            header = 0b00010000
Coverage.statementStart(5634)
            data.writeInt2(localOff, b[0])
Coverage.statementStart(5635)
            localOff += 2
Coverage.statementStart(5636)
            flag = true
Coverage.statementStart(5637)
        } else if (b[0] >= 0) {
Coverage.ifStart(5638)
            data.writeInt1(localOff, b[0])
Coverage.statementStart(5639)
            localOff += 1
Coverage.statementStart(5640)
            flag = true
Coverage.statementStart(5641)
        }
Coverage.statementStart(5642)
        if (b[1] >= (1 shl 24)) {
Coverage.ifStart(5643)
            if (flag) {
Coverage.ifStart(5644)
                header = header or 0b00001100
Coverage.statementStart(5645)
            } else {
Coverage.ifStart(5646)
                header = 0b01001100
Coverage.statementStart(5647)
            }
Coverage.statementStart(5648)
            data.writeInt4(localOff, b[1])
Coverage.statementStart(5649)
            localOff += 4
Coverage.statementStart(5650)
            flag = true
Coverage.statementStart(5651)
        } else if (b[1] >= (1 shl 16)) {
Coverage.ifStart(5652)
            if (flag) {
Coverage.ifStart(5653)
                header = header or 0b00001000
Coverage.statementStart(5654)
            } else {
Coverage.ifStart(5655)
                header = 0b01001000
Coverage.statementStart(5656)
            }
Coverage.statementStart(5657)
            data.writeInt3(localOff, b[1])
Coverage.statementStart(5658)
            localOff += 3
Coverage.statementStart(5659)
            flag = true
Coverage.statementStart(5660)
        } else if (b[1] >= (1 shl 8)) {
Coverage.ifStart(5661)
            if (flag) {
Coverage.ifStart(5662)
                header = header or 0b00000100
Coverage.statementStart(5663)
            } else {
Coverage.ifStart(5664)
                header = 0b01000100
Coverage.statementStart(5665)
            }
Coverage.statementStart(5666)
            data.writeInt2(localOff, b[1])
Coverage.statementStart(5667)
            localOff += 2
Coverage.statementStart(5668)
            flag = true
Coverage.statementStart(5669)
        } else {
Coverage.ifStart(5670)
            SanityCheck.check { b[1] >= 0 || flag }
Coverage.statementStart(5671)
            if (!flag) {
Coverage.ifStart(5672)
                header = 0b01000000
Coverage.statementStart(5673)
            }
Coverage.statementStart(5674)
            data.writeInt1(localOff, b[1])
Coverage.statementStart(5675)
            localOff += 1
Coverage.statementStart(5676)
            flag = true
Coverage.statementStart(5677)
        }
Coverage.statementStart(5678)
        if (b[2] >= (1 shl 24)) {
Coverage.ifStart(5679)
            if (flag) {
Coverage.ifStart(5680)
                header = header or 0b00000011
Coverage.statementStart(5681)
            } else {
Coverage.ifStart(5682)
                header = 0b10000011
Coverage.statementStart(5683)
            }
Coverage.statementStart(5684)
            data.writeInt4(localOff, b[2])
Coverage.statementStart(5685)
            localOff += 4
Coverage.statementStart(5686)
            flag = true
Coverage.statementStart(5687)
        } else if (b[2] >= (1 shl 16)) {
Coverage.ifStart(5688)
            if (flag) {
Coverage.ifStart(5689)
                header = header or 0b00000010
Coverage.statementStart(5690)
            } else {
Coverage.ifStart(5691)
                header = 0b10000010
Coverage.statementStart(5692)
            }
Coverage.statementStart(5693)
            data.writeInt3(localOff, b[2])
Coverage.statementStart(5694)
            localOff += 3
Coverage.statementStart(5695)
            flag = true
Coverage.statementStart(5696)
        } else if (b[2] >= (1 shl 8)) {
Coverage.ifStart(5697)
            if (flag) {
Coverage.ifStart(5698)
                header = header or 0b00000001
Coverage.statementStart(5699)
            } else {
Coverage.ifStart(5700)
                header = 0b10000001
Coverage.statementStart(5701)
            }
Coverage.statementStart(5702)
            data.writeInt2(localOff, b[2])
Coverage.statementStart(5703)
            localOff += 2
Coverage.statementStart(5704)
            flag = true
Coverage.statementStart(5705)
        } else {
Coverage.ifStart(5706)
            SanityCheck.check { b[2] >= 0 || flag }
Coverage.statementStart(5707)
            if (!flag) {
Coverage.ifStart(5708)
                header = 0b10000000
Coverage.statementStart(5709)
            }
Coverage.statementStart(5710)
            data.writeInt1(localOff, b[2])
Coverage.statementStart(5711)
            localOff += 1
Coverage.statementStart(5712)
            flag = true
Coverage.statementStart(5713)
        }
Coverage.statementStart(5714)
        data.writeInt1(offset, header)
Coverage.statementStart(5715)
        SanityCheck.check { flag }//otherwise this triple would equal the last one
Coverage.statementStart(5716)
        SanityCheck.check { localOff > offset + 1 }//at least ony byte must have been written additionally to the header
Coverage.statementStart(5717)
        return localOff - offset
    }
    fun iterator(): TripleIterator {
Coverage.funStart(5718)
        var iterator: TripleIterator? = null
Coverage.statementStart(5719)
        var node = this
Coverage.statementStart(5720)
        while (iterator == null) {
Coverage.whileLoopStart(5721)
            NodeManager.getNode(node.getFirstChild(), {
Coverage.statementStart(5722)
                iterator = it.iterator()
Coverage.statementStart(5723)
            }, {
Coverage.statementStart(5724)
                node = it
Coverage.statementStart(5725)
            })
Coverage.statementStart(5726)
        }
Coverage.statementStart(5727)
        return iterator!!
    }
    inline fun forEachChild(crossinline action: (Int) -> Unit) {
Coverage.funStart(5728)
        var remaining = getTripleCount()
Coverage.statementStart(5729)
        var offset = 12
Coverage.statementStart(5730)
        var lastChildPointer = getFirstChild()
Coverage.statementStart(5731)
        action(lastChildPointer)
Coverage.statementStart(5732)
        while (remaining > 0) {
Coverage.whileLoopStart(5733)
            var i = 0
Coverage.statementStart(5734)
            while (i < 4 && remaining > 0) {
Coverage.whileLoopStart(5735)
                var header = data.readInt1(offset)
Coverage.statementStart(5736)
                offset++
Coverage.statementStart(5737)
                var headerA = header and 0b11000000
Coverage.statementStart(5738)
                if (headerA == 0b0000000) {
Coverage.ifStart(5739)
                    offset += ((header and 0b00110000) shr 4) + ((header and 0b00001100) shr 2) + ((header and 0b00000011)) + 3
Coverage.statementStart(5740)
                } else if (headerA == 0b01000000) {
Coverage.ifStart(5741)
                    offset += ((header and 0b00001100) shr 2) + ((header and 0b00000011)) + 2
Coverage.statementStart(5742)
                } else {
Coverage.ifStart(5743)
                    offset += ((header and 0b00000011)) + 1
Coverage.statementStart(5744)
                }
Coverage.statementStart(5745)
                remaining--
Coverage.statementStart(5746)
                i++
Coverage.statementStart(5747)
            }
Coverage.statementStart(5748)
            var headerB = data.readInt1(offset)
Coverage.statementStart(5749)
            offset++
Coverage.statementStart(5750)
            for (j in 0 until i) {
Coverage.forLoopStart(5751)
                var h = ((headerB shr (6 - j - j)) and 0x03) + 1
Coverage.statementStart(5752)
                when (h) {
                    1 -> {
Coverage.whenCaseStart(5754)
                        lastChildPointer = lastChildPointer xor data.readInt1(offset)
Coverage.statementStart(5755)
                    }
                    2 -> {
Coverage.whenCaseStart(5756)
                        lastChildPointer = lastChildPointer xor data.readInt2(offset)
Coverage.statementStart(5757)
                    }
                    3 -> {
Coverage.whenCaseStart(5758)
                        lastChildPointer = lastChildPointer xor data.readInt3(offset)
Coverage.statementStart(5759)
                    }
                    4 -> {
Coverage.whenCaseStart(5760)
                        lastChildPointer = lastChildPointer xor data.readInt4(offset)
Coverage.statementStart(5761)
                    }
                }
Coverage.statementStart(5762)
                action(lastChildPointer)
Coverage.statementStart(5763)
                offset += h
Coverage.statementStart(5764)
            }
Coverage.statementStart(5765)
        }
Coverage.statementStart(5766)
    }
    inline fun findIteratorN(crossinline checkTooSmall: (c: IntArray) -> Boolean, crossinline action: (Int) -> Unit): Unit {
Coverage.funStart(5767)
        var lastHeaderOffset = -1 //invalid offset to start with
Coverage.statementStart(5768)
        var lastChildPointer = getFirstChild()
Coverage.statementStart(5769)
        var childLastPointerHeaderOffset = -1
Coverage.statementStart(5770)
        var remaining = getTripleCount()
Coverage.statementStart(5771)
        var offset = 12
Coverage.statementStart(5772)
        var childPointers = IntArray(4)
Coverage.statementStart(5773)
        var counter = IntArray(3)
Coverage.statementStart(5774)
        var value = IntArray(3)
Coverage.statementStart(5775)
        var childToUse = -1
Coverage.statementStart(5776)
        while (remaining > 0) {
Coverage.whileLoopStart(5777)
            var i = 0
Coverage.statementStart(5778)
            while (i < 4 && remaining > 0) {
Coverage.whileLoopStart(5779)
                childLastPointerHeaderOffset = offset
Coverage.statementStart(5780)
                var header = data.readInt1(offset)
Coverage.statementStart(5781)
                offset++
Coverage.statementStart(5782)
                var headerA = header and 0b11000000
Coverage.statementStart(5783)
                if (headerA == 0b0000000) {
Coverage.ifStart(5784)
                    counter[0] = ((header and 0b00110000) shr 4) + 1
Coverage.statementStart(5785)
                    counter[1] = ((header and 0b00001100) shr 2) + 1
Coverage.statementStart(5786)
                    counter[2] = ((header and 0b00000011)) + 1
Coverage.statementStart(5787)
                } else if (headerA == 0b01000000) {
Coverage.ifStart(5788)
                    counter[0] = 0
Coverage.statementStart(5789)
                    counter[1] = ((header and 0b00001100) shr 2) + 1
Coverage.statementStart(5790)
                    counter[2] = ((header and 0b00000011)) + 1
Coverage.statementStart(5791)
                } else {
Coverage.ifStart(5792)
                    SanityCheck.check { headerA == 0b10000000 }
Coverage.statementStart(5793)
                    counter[0] = 0
Coverage.statementStart(5794)
                    counter[1] = 0
Coverage.statementStart(5795)
                    counter[2] = ((header and 0b00000011)) + 1
Coverage.statementStart(5796)
                }
Coverage.statementStart(5797)
                for (j in 0 until 3) {
Coverage.forLoopStart(5798)
                    when (counter[j]) {
                        1 -> {
Coverage.whenCaseStart(5800)
                            value[j] = value[j] xor data.readInt1(offset)
Coverage.statementStart(5801)
                        }
                        2 -> {
Coverage.whenCaseStart(5802)
                            value[j] = value[j] xor data.readInt2(offset)
Coverage.statementStart(5803)
                        }
                        3 -> {
Coverage.whenCaseStart(5804)
                            value[j] = value[j] xor data.readInt3(offset)
Coverage.statementStart(5805)
                        }
                        4 -> {
Coverage.whenCaseStart(5806)
                            value[j] = value[j] xor data.readInt4(offset)
Coverage.statementStart(5807)
                        }
                    }
Coverage.statementStart(5808)
                    offset += counter[j]
Coverage.statementStart(5809)
                }
Coverage.statementStart(5810)
                if (!checkTooSmall(value)) {
Coverage.ifStart(5811)
                    if (i == 0) {
Coverage.ifStart(5812)
                        if (lastHeaderOffset < 0) {
Coverage.ifStart(5813)
                            lastChildPointer = getFirstChild()
Coverage.statementStart(5814)
                        }
Coverage.statementStart(5815)
                        action(lastChildPointer)
Coverage.statementStart(5816)
                        return
                    }
Coverage.statementStart(5817)
                    if (childToUse < 0) {
Coverage.ifStart(5818)
                        childToUse = i - 1
Coverage.statementStart(5819)
                    }
Coverage.statementStart(5820)
                }
Coverage.statementStart(5821)
                remaining--
Coverage.statementStart(5822)
                i++
Coverage.statementStart(5823)
            }
Coverage.statementStart(5824)
            var headerB = data.readInt1(offset)
Coverage.statementStart(5825)
            offset++
Coverage.statementStart(5826)
            for (j in 0 until i) {
Coverage.forLoopStart(5827)
                var h = ((headerB shr (6 - j - j)) and 0x03) + 1
Coverage.statementStart(5828)
                when (h) {
                    1 -> {
Coverage.whenCaseStart(5830)
                        childPointers[j] = lastChildPointer xor data.readInt1(offset)
Coverage.statementStart(5831)
                    }
                    2 -> {
Coverage.whenCaseStart(5832)
                        childPointers[j] = lastChildPointer xor data.readInt2(offset)
Coverage.statementStart(5833)
                    }
                    3 -> {
Coverage.whenCaseStart(5834)
                        childPointers[j] = lastChildPointer xor data.readInt3(offset)
Coverage.statementStart(5835)
                    }
                    4 -> {
Coverage.whenCaseStart(5836)
                        childPointers[j] = lastChildPointer xor data.readInt4(offset)
Coverage.statementStart(5837)
                    }
                }
Coverage.statementStart(5838)
                lastChildPointer = childPointers[j]
Coverage.statementStart(5839)
                offset += h
Coverage.statementStart(5840)
            }
Coverage.statementStart(5841)
            if (childToUse >= 0) {
Coverage.ifStart(5842)
                action(childPointers[childToUse])
Coverage.statementStart(5843)
                return
            }
Coverage.statementStart(5844)
            lastHeaderOffset = childLastPointerHeaderOffset
Coverage.statementStart(5845)
        }
Coverage.statementStart(5846)
        action(lastChildPointer)
Coverage.statementStart(5847)
    }
    fun iterator3(prefix: IntArray): TripleIterator {
Coverage.funStart(5848)
        var node = this
Coverage.statementStart(5849)
        var iterator: TripleIterator? = null
Coverage.statementStart(5850)
        while (iterator == null) {
Coverage.whileLoopStart(5851)
            node.findIteratorN({
Coverage.statementStart(5852)
                /*return*/                (it[0] < prefix[0]) || (it[0] == prefix[0] && it[1] < prefix[1]) || (it[0] == prefix[0] && it[1] == prefix[1] && it[2] < prefix[2])
            }, {
Coverage.statementStart(5853)
                NodeManager.getNode(it, {
Coverage.statementStart(5854)
                    iterator = it.iterator3(prefix)
Coverage.statementStart(5855)
                }, {
Coverage.statementStart(5856)
                    node = it
Coverage.statementStart(5857)
                })
Coverage.statementStart(5858)
            })
Coverage.statementStart(5859)
        }
Coverage.statementStart(5860)
        return iterator!!
    }
    fun iterator2(prefix: IntArray): TripleIterator {
Coverage.funStart(5861)
        var node = this
Coverage.statementStart(5862)
        var iterator: TripleIterator? = null
Coverage.statementStart(5863)
        while (iterator == null) {
Coverage.whileLoopStart(5864)
            node.findIteratorN({
Coverage.statementStart(5865)
                /*return*/        (it[0] < prefix[0]) || (it[0] == prefix[0] && it[1] < prefix[1])
            }, {
Coverage.statementStart(5866)
                NodeManager.getNode(it, {
Coverage.statementStart(5867)
                    iterator = it.iterator2(prefix)
Coverage.statementStart(5868)
                }, {
Coverage.statementStart(5869)
                    node = it
Coverage.statementStart(5870)
                })
Coverage.statementStart(5871)
            })
Coverage.statementStart(5872)
        }
Coverage.statementStart(5873)
        return iterator!!
    }
    fun iterator1(prefix: IntArray): TripleIterator {
Coverage.funStart(5874)
        var node = this
Coverage.statementStart(5875)
        var iterator: TripleIterator? = null
Coverage.statementStart(5876)
        while (iterator == null) {
Coverage.whileLoopStart(5877)
            node.findIteratorN({
Coverage.statementStart(5878)
                /*return*/ (it[0] < prefix[0])
            }, {
Coverage.statementStart(5879)
                NodeManager.getNode(it, {
Coverage.statementStart(5880)
                    iterator = it.iterator1(prefix)
Coverage.statementStart(5881)
                }, {
Coverage.statementStart(5882)
                    node = it
Coverage.statementStart(5883)
                })
Coverage.statementStart(5884)
            })
Coverage.statementStart(5885)
        }
Coverage.statementStart(5886)
        return iterator!!
    }
    fun initializeWith(childs: MutableList<Int>) {
Coverage.funStart(5887)
        var debugListChilds = mutableListOf<Int>()
Coverage.statementStart(5888)
        var debugListTriples = mutableListOf<IntArray>()
Coverage.statementStart(5889)
        SanityCheck.check { childs.size > 0 }
Coverage.statementStart(5890)
        var current = childs.removeAt(0)
Coverage.statementStart(5891)
        var childLastPointer = current
Coverage.statementStart(5892)
        SanityCheck {
Coverage.statementStart(5893)
            debugListChilds.add(childLastPointer)
Coverage.statementStart(5894)
        }
Coverage.statementStart(5895)
        setFirstChild(childLastPointer)
Coverage.statementStart(5896)
        var offset = 12
Coverage.statementStart(5897)
        val offsetEnd = data.size - (13 * 4 + 17) // reserve at least enough space to write !! 4 !! full triple-group AND !! 1 !! full child-pointer-group at the end, to prevent failing-writes
Coverage.statementStart(5898)
        var triples = 0
Coverage.statementStart(5899)
        var i: Int
Coverage.statementStart(5900)
        var bytesWritten: Int
Coverage.statementStart(5901)
        var childPointers = IntArray(4)
Coverage.statementStart(5902)
        val tripleCurrent = IntArray(3)
Coverage.statementStart(5903)
        val tripleLast = IntArray(3)
Coverage.statementStart(5904)
        val tripleBuf = IntArray(3)
Coverage.statementStart(5905)
        while (childs.size > 0 && offset < offsetEnd) {
Coverage.whileLoopStart(5906)
            i = 0
Coverage.statementStart(5907)
            while (i < 4 && childs.size > 0 && offset < offsetEnd) {
Coverage.whileLoopStart(5908)
                current = childs.removeAt(0)
Coverage.statementStart(5909)
                SanityCheck {
Coverage.statementStart(5910)
                    debugListChilds.add(current)
Coverage.statementStart(5911)
                }
Coverage.statementStart(5912)
                childPointers[i] = childLastPointer xor current
Coverage.statementStart(5913)
                childLastPointer = current
Coverage.statementStart(5914)
                NodeManager.getNode(childLastPointer, {
Coverage.statementStart(5915)
                    it.getFirstTriple(tripleCurrent)
Coverage.statementStart(5916)
                }, {
Coverage.statementStart(5917)
                    it.getFirstTriple(tripleCurrent)
Coverage.statementStart(5918)
                })
Coverage.statementStart(5919)
                debugListTriples.add(intArrayOf(tripleCurrent[0], tripleCurrent[1], tripleCurrent[2]))
Coverage.statementStart(5920)
                bytesWritten = writeDiffTriple(offset, tripleLast, tripleCurrent, tripleBuf)
Coverage.statementStart(5921)
                offset += bytesWritten
Coverage.statementStart(5922)
                i++
Coverage.statementStart(5923)
                triples++
Coverage.statementStart(5924)
            }
Coverage.statementStart(5925)
            bytesWritten = writeChildPointers(offset, i, childPointers)
Coverage.statementStart(5926)
            offset += bytesWritten
Coverage.statementStart(5927)
        }
Coverage.statementStart(5928)
        setTripleCount(triples)
Coverage.statementStart(5929)
        setNextNode(NodeManager.nodeNullPointer)
Coverage.statementStart(5930)
        SanityCheck {
Coverage.statementStart(5931)
            println(debugListChilds)
Coverage.statementStart(5932)
            println(debugListTriples.map { it.map { it } })
Coverage.statementStart(5933)
            SanityCheck.check { debugListTriples.size == debugListChilds.size - 1 }
Coverage.statementStart(5934)
            var k = 0
Coverage.statementStart(5935)
            this.forEachChild {
Coverage.statementStart(5936)
                println("debug it $it")
Coverage.statementStart(5937)
                SanityCheck.check { debugListChilds.size >= k }
Coverage.statementStart(5938)
                SanityCheck.check { it == debugListChilds[k] }
Coverage.statementStart(5939)
                k++
Coverage.statementStart(5940)
            }
Coverage.statementStart(5941)
            SanityCheck.check { k == debugListChilds.size }
Coverage.statementStart(5942)
            var j = -1
Coverage.statementStart(5943)
            findIteratorN({
Coverage.statementStart(5944)
                println("debug xx ${it.map { it }}")
Coverage.statementStart(5945)
                j++
Coverage.statementStart(5946)
                SanityCheck.check { j < debugListTriples.size }
Coverage.statementStart(5947)
                SanityCheck.check { it[0] == debugListTriples[j][0] }
Coverage.statementStart(5948)
                SanityCheck.check { it[1] == debugListTriples[j][1] }
Coverage.statementStart(5949)
                SanityCheck.check { it[2] == debugListTriples[j][2] }
Coverage.statementStart(5950)
                /*return*/true
            }, {
Coverage.statementStart(5951)
                println("debug $it")
Coverage.statementStart(5952)
                SanityCheck.check { it == debugListChilds[debugListChilds.size - 1] }
Coverage.statementStart(5953)
            })
Coverage.statementStart(5954)
            SanityCheck.check { j == debugListTriples.size - 1 }
Coverage.statementStart(5955)
            for (l in 0 until debugListTriples.size) {
Coverage.forLoopStart(5956)
                println("debug l $l")
Coverage.statementStart(5957)
                j = -1
Coverage.statementStart(5958)
                findIteratorN({
Coverage.statementStart(5959)
                    println("debug xx ${it.map { it }}")
Coverage.statementStart(5960)
                    j++
Coverage.statementStart(5961)
                    SanityCheck.check { j < debugListTriples.size }
Coverage.statementStart(5962)
                    SanityCheck.check { it[0] == debugListTriples[j][0] }
Coverage.statementStart(5963)
                    SanityCheck.check { it[1] == debugListTriples[j][1] }
Coverage.statementStart(5964)
                    SanityCheck.check { it[2] == debugListTriples[j][2] }
Coverage.statementStart(5965)
                    SanityCheck.check { j < l + 4 }/*read at most one block too much*/
Coverage.statementStart(5966)
                    /*return*/ j < l
                }, {
Coverage.statementStart(5967)
                    println("debug $it")
Coverage.statementStart(5968)
                    SanityCheck.check { it == debugListChilds[l] }
Coverage.statementStart(5969)
                })
Coverage.statementStart(5970)
            }
Coverage.statementStart(5971)
        }
Coverage.statementStart(5972)
    }
}
