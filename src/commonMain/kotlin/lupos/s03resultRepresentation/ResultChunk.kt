package lupos.s03resultRepresentation

import lupos.s00misc.Coverage
import lupos.s00misc.Coverage
import lupos.s03resultRepresentation.ResultChunk
import lupos.s04arithmetikOperators.ResultVektorRaw

open class ResultChunk(resultSet: ResultSet, columns: Int) : ResultChunkBase(resultSet, columns) {
    companion object {
        operator fun invoke(resultSet: ResultSet): ResultChunk {
            Coverage.funStart(405)
            val columns = resultSet.getVariableNames().size
            Coverage.statementStart(406)
            if (columns == 0) {
                Coverage.ifStart(407)
                return ResultChunkNoColumns(resultSet, columns)
            }
            Coverage.statementStart(408)
            return ResultChunk(resultSet, columns)
        }

        fun removeFirst(root: ResultChunk): ResultChunk? {
            Coverage.funStart(409)
            if (root.next == root) {
                Coverage.ifStart(410)
                return null
            }
            Coverage.statementStart(411)
            val res = root.next
            Coverage.statementStart(412)
            res.prev = root.prev
            Coverage.statementStart(413)
            root.prev.next = res
            Coverage.statementStart(414)
            return res
        }

        fun split(root: ResultChunk, count: Int): ResultChunk {
            Coverage.funStart(415)
            require(count > 0)
            Coverage.statementStart(416)
            var other = root
            Coverage.statementStart(417)
            for (i in 0 until count) {
                Coverage.forLoopStart(418)
                other = other.next
                Coverage.statementStart(419)
            }
            Coverage.statementStart(420)
            val rootLast = other.prev
            Coverage.statementStart(421)
            val otherLast = root.prev
            Coverage.statementStart(422)
            root.prev = rootLast
            Coverage.statementStart(423)
            rootLast.next = root
            Coverage.statementStart(424)
            otherLast.next = other
            Coverage.statementStart(425)
            other.prev = otherLast
            Coverage.statementStart(426)
            return other
        }

        fun append(rootLast: ResultChunk, other: ResultChunk): ResultChunk {
            Coverage.funStart(427)
            val root = rootLast.next
            Coverage.statementStart(428)
            val otherLast = other.prev
            Coverage.statementStart(429)
            rootLast.next = other
            Coverage.statementStart(430)
            root.prev = otherLast
            Coverage.statementStart(431)
            otherLast.next = root
            Coverage.statementStart(432)
            other.prev = rootLast
            Coverage.statementStart(433)
            return otherLast
        }

        fun copy(from: ResultChunk, target: ResultChunk, count: Int): ResultChunk {
            Coverage.funStart(434)
            require(count > 0)
            Coverage.statementStart(435)
            val resultSet = from.resultSet
            Coverage.statementStart(436)
            val columns = from.columns
            Coverage.statementStart(437)
            var targetLast = target
            Coverage.statementStart(438)
            var available = targetLast.availableWrite()
            Coverage.statementStart(439)
            if (available == 0) {
                Coverage.ifStart(440)
                targetLast = append(targetLast, ResultChunk(resultSet, columns))
                Coverage.statementStart(441)
                available = ResultVektor.capacity
                Coverage.statementStart(442)
            }
            Coverage.statementStart(443)
            var cnt = count
            Coverage.statementStart(444)
            while (available < cnt) {
                Coverage.whileLoopStart(445)
                targetLast.copy(from, available)
                Coverage.statementStart(446)
                cnt -= available
                Coverage.statementStart(447)
                targetLast = append(targetLast, ResultChunk(resultSet, columns))
                Coverage.statementStart(448)
                available = ResultVektor.capacity
                Coverage.statementStart(449)
            }
            Coverage.statementStart(450)
            targetLast.copy(from, cnt)
            Coverage.statementStart(451)
            return targetLast
        }

        fun sortHelper(comparator: Array<Comparator<Value>>, columnOrder: Array<Variable>, a: ResultChunk, b: ResultChunk, target: ResultChunk): ResultChunk {
            Coverage.funStart(452)
//                                                                                                                                                                
            Coverage.statementStart(453)
            println("sortHelper1 $a $b ${columnOrder.map { it }}")
            Coverage.statementStart(454)
            var targetLast = target
            Coverage.statementStart(455)
//                                                                                                                                                                
            Coverage.statementStart(456)
            loop@ while (a.availableRead() > 0 && b.availableRead() > 0) {
                Coverage.whileLoopStart(457)
//                                                                                                                                                                
                Coverage.statementStart(458)
                println("sortHelper4 $target")
                Coverage.statementStart(459)
                var cmp = 0
                Coverage.statementStart(460)
//                                                                                                                                                                
                Coverage.statementStart(461)
                for (i in columnOrder) {
                    Coverage.forLoopStart(462)
//                                                                                                                                                                
                    Coverage.statementStart(463)
                    val vala = a.data[i.toInt()].current()
                    Coverage.statementStart(464)
//                                                                                                                                                                
                    Coverage.statementStart(465)
                    val valb = b.data[i.toInt()].current()
                    Coverage.statementStart(466)
//                                                                                                                                                                
                    Coverage.statementStart(467)
                    println("sortHelper2 $vala $valb")
                    Coverage.statementStart(468)
                    if (vala != valb) {
                        Coverage.ifStart(469)
//                                                                                                                                                                
                        Coverage.statementStart(470)
                        cmp = comparator[i.toInt()].compare(vala, valb)
                        Coverage.statementStart(471)
//                                                                                                                                                                
                        Coverage.statementStart(472)
                        require(cmp != 0)
                        Coverage.statementStart(473)
//                                                                                                                                                                
                        Coverage.statementStart(474)
                        if (cmp < 0) {
                            Coverage.ifStart(475)
//                                                                                                                                                                
                            Coverage.statementStart(476)
                            var count = a.data[i.toInt()].sameElements()
                            Coverage.statementStart(477)
                            println("neq a $count")
                            Coverage.statementStart(478)
//                                                                                                                                                                
                            Coverage.statementStart(479)
                            targetLast = copy(a, targetLast, count)
                            Coverage.statementStart(480)
                            println(a)
                            Coverage.statementStart(481)
//                                                                                                                                                                
                            Coverage.statementStart(482)
                        } else {
                            Coverage.ifStart(483)
//                                                                                                                                                                
                            Coverage.statementStart(484)
                            var count = b.data[i.toInt()].sameElements()
                            Coverage.statementStart(485)
                            println("neq b $count")
                            Coverage.statementStart(486)
//                                                                                                                                                                
                            Coverage.statementStart(487)
                            targetLast = copy(b, targetLast, count)
                            Coverage.statementStart(488)
                            println(b)
                            Coverage.statementStart(489)
//                                                                                                                                                                
                            Coverage.statementStart(490)
                        }
                        Coverage.statementStart(491)
//                                                                                                                                                                
                        Coverage.statementStart(492)
                        continue@loop
                    }
                    Coverage.statementStart(493)
//                                                                                                                                                                
                    Coverage.statementStart(494)
                }
                Coverage.statementStart(495)
//                                                                                                                                                                
                Coverage.statementStart(496)
                if (cmp == 0) {
                    Coverage.ifStart(497)
                    println("eq")
                    Coverage.statementStart(498)
//                                                                                                                                                                
                    Coverage.statementStart(499)
                    val i = columnOrder[columnOrder.size - 1]
                    Coverage.statementStart(500)
//                                                                                                                                                                
                    Coverage.statementStart(501)
                    var countA = a.sameElements()
                    Coverage.statementStart(502)
//                                                                                                                                                                
                    Coverage.statementStart(503)
                    targetLast = copy(a, targetLast, countA)
                    Coverage.statementStart(504)
//                                                                                                                                                                
                    Coverage.statementStart(505)
                    var countB = b.sameElements()
                    Coverage.statementStart(506)
//                                                                                                                                                                
                    Coverage.statementStart(507)
                    targetLast = copy(b, targetLast, countB)
                    Coverage.statementStart(508)
                    println("counteq $countA $countB")
                    Coverage.statementStart(509)
                    println(a)
                    Coverage.statementStart(510)
                    println(b)
                    Coverage.statementStart(511)
//                                                                                                                                                                
                    Coverage.statementStart(512)
                }
                Coverage.statementStart(513)
//                                                                                                                                                                
                Coverage.statementStart(514)
            }
            Coverage.statementStart(515)
//                                                                                                                                                                
            Coverage.statementStart(516)
            println("sortHelper3 $target")
            Coverage.statementStart(517)
            return targetLast
        }

        fun sort(comparator: Array<Comparator<Value>>, columnOrder: Array<Variable>, chunks: ResultChunk): ResultChunk {
            Coverage.funStart(518)
            var chunkCount = 1
            Coverage.statementStart(519)
            var tmp = chunks.next
            Coverage.statementStart(520)
            while (tmp != chunks) {
                Coverage.whileLoopStart(521)
                chunkCount++
                Coverage.statementStart(522)
                tmp = tmp.next
                Coverage.statementStart(523)
            }
            Coverage.statementStart(524)
            if (chunkCount == 1) {
                Coverage.ifStart(525)
                val resultSet = chunks.resultSet
                Coverage.statementStart(526)
                val columns = chunks.columns
                Coverage.statementStart(527)
                val res = ResultChunk(resultSet, columns)
                Coverage.statementStart(528)
                var resLast = res
                Coverage.statementStart(529)
                while (chunks.hasNext()) {
                    Coverage.whileLoopStart(530)
                    val same = chunks.sameElements()
                    Coverage.statementStart(531)
                    if (resLast.availableWrite() < 2) {
                        Coverage.ifStart(532)
                        resLast = append(resLast, ResultChunk(resultSet, columns))
                        Coverage.statementStart(533)
                    }
                    Coverage.statementStart(534)
                    resLast.internalInsertSorted(comparator, columnOrder, chunks.current(), same)
                    Coverage.statementStart(535)
                    chunks.skipPos(same)
                    Coverage.statementStart(536)
                }
                Coverage.statementStart(537)
                if (res != res.next) {
                    Coverage.ifStart(538)
                    return sort(comparator, columnOrder, res)
                }
                Coverage.statementStart(539)
                return res
            } else {
                Coverage.ifStart(540)
                val half = chunkCount / 2
                Coverage.statementStart(541)
                var a: ResultChunk? = sort(comparator, columnOrder, split(chunks, half))
                Coverage.statementStart(542)
                var b: ResultChunk? = sort(comparator, columnOrder, chunks)
                Coverage.statementStart(543)
                val resultSet = a!!.resultSet
                Coverage.statementStart(544)
                val columns = a!!.columns
                Coverage.statementStart(545)
                val res = ResultChunk(resultSet, columns)
                Coverage.statementStart(546)
                var resLast = res
                Coverage.statementStart(547)
                while (true) {
                    Coverage.whileLoopStart(548)
                    resLast = sortHelper(comparator, columnOrder, a!!, b!!, resLast)
                    Coverage.statementStart(549)
                    if (a.hasNext()) {
                        Coverage.ifStart(550)
                        b = removeFirst(b)
                        Coverage.statementStart(551)
                        if (b == null) {
                            Coverage.ifStart(552)
                            break
                        }
                        Coverage.statementStart(553)
                    } else {
                        Coverage.ifStart(554)
                        a = removeFirst(a)
                        Coverage.statementStart(555)
                        if (a == null) {
                            Coverage.ifStart(556)
                            break
                        }
                        Coverage.statementStart(557)
                    }
                    Coverage.statementStart(558)
                }
                Coverage.statementStart(559)
                if (a != null) {
                    Coverage.ifStart(560)
                    var count = a!!.availableRead()
                    Coverage.statementStart(561)
                    if (count > 0) {
                        Coverage.ifStart(562)
                        resLast = copy(a, resLast, count)
                        Coverage.statementStart(563)
                    }
                    Coverage.statementStart(564)
                    a = removeFirst(a)
                    Coverage.statementStart(565)
                    if (a != null) {
                        Coverage.ifStart(566)
                        append(resLast, a)
                        Coverage.statementStart(567)
                    }
                    Coverage.statementStart(568)
                } else {
                    Coverage.ifStart(569)
                    var count = b!!.availableRead()
                    Coverage.statementStart(570)
                    if (count > 0) {
                        Coverage.ifStart(571)
                        resLast = copy(b!!, resLast, count)
                        Coverage.statementStart(572)
                    }
                    Coverage.statementStart(573)
                    b = removeFirst(b)
                    Coverage.statementStart(574)
                    if (b != null) {
                        Coverage.ifStart(575)
                        append(resLast, b!!)
                        Coverage.statementStart(576)
                    }
                    Coverage.statementStart(577)
                }
                Coverage.statementStart(578)
                return res
            }
            Coverage.statementStart(579)
        }
    }

    var prev = this
    var next = this
    fun internalInsertSorted(comparator: Array<Comparator<Value>>, columnOrder: Array<Variable>, values: Array<Value>, count: Int = 1) {
        Coverage.funStart(580)
        println("internalInsertSorted $this ${values.map { it }} $count ${columnOrder.map { it }}")
        Coverage.statementStart(581)
        var columnidx = columnOrder[0].toInt()
        Coverage.statementStart(582)
        var column = data[columnidx]
        Coverage.statementStart(583)
        var idx = column.insertSorted(values[columnidx], comparator = comparator[columnOrder[0].toInt()], count = count)
        Coverage.statementStart(584)
        var first = idx.first
        Coverage.statementStart(585)
        var last = first + idx.second - count
        Coverage.statementStart(586)
        for (i in 1 until columns) {
            Coverage.forLoopStart(587)
            columnidx = columnOrder[i].toInt()
            Coverage.statementStart(588)
            column = data[columnidx]
            Coverage.statementStart(589)
            idx = column.insertSorted(values[columnidx], first, last, comparator[columnOrder[i].toInt()], count)
            Coverage.statementStart(590)
            if (idx.first > first) {
                Coverage.ifStart(591)
                first = idx.first
                Coverage.statementStart(592)
            }
            Coverage.statementStart(593)
            if (last > first + idx.second - count) {
                Coverage.ifStart(594)
                last = first + idx.second - count
                Coverage.statementStart(595)
            }
            Coverage.statementStart(596)
        }
        Coverage.statementStart(597)
        println("internalInsertSorted $this")
        Coverage.statementStart(598)
    }
}

class ValueComparatorFast : Comparator<Value> {
    override fun compare(a: Value, b: Value) = a.compareTo(b)
}
