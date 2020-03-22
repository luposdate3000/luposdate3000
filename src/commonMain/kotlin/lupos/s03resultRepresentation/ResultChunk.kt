package lupos.s03resultRepresentation
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
            var targetLast = target
Coverage.statementStart(453)
            loop@ while (a.availableRead() > 0 && b.availableRead() > 0) {
Coverage.whileLoopStart(454)
                var cmp = 0
Coverage.statementStart(455)
                for (i in 0 until columnOrder.size) {
Coverage.forLoopStart(456)
val c=columnOrder[i].toInt()
Coverage.statementStart(457)
                    val vala = a.data[c].current()
Coverage.statementStart(458)
                    val valb = b.data[c].current()
Coverage.statementStart(459)
                    if (vala != valb) {
Coverage.ifStart(460)
                        cmp = comparator[c].compare(vala, valb)
Coverage.statementStart(461)
                        require(cmp != 0)
Coverage.statementStart(462)
                        if (cmp < 0) {
Coverage.ifStart(463)
                            var count = a.sameElements(Array(i+1){columnOrder[it]})
Coverage.statementStart(464)
                            targetLast = copy(a, targetLast, count)
Coverage.statementStart(465)
                        } else {
Coverage.ifStart(466)
                            var count = b.sameElements(Array(i+1){columnOrder[it]})
Coverage.statementStart(467)
                            targetLast = copy(b, targetLast, count)
Coverage.statementStart(468)
                        }
Coverage.statementStart(469)
                        continue@loop
                    }
Coverage.statementStart(470)
                }
Coverage.statementStart(471)
                if (cmp == 0) {
Coverage.ifStart(472)
                    val i = columnOrder[columnOrder.size - 1]
Coverage.statementStart(473)
                    var countA = a.sameElements()
Coverage.statementStart(474)
                    targetLast = copy(a, targetLast, countA)
Coverage.statementStart(475)
                    var countB = b.sameElements()
Coverage.statementStart(476)
                    targetLast = copy(b, targetLast, countB)
Coverage.statementStart(477)
                }
Coverage.statementStart(478)
            }
Coverage.statementStart(479)
            return targetLast
        }
        fun sort(comparator: Array<Comparator<Value>>, columnOrder: Array<Variable>, chunks: ResultChunk): ResultChunk {
Coverage.funStart(480)
            var chunkCount = 1
Coverage.statementStart(481)
            var tmp = chunks.next
Coverage.statementStart(482)
            while (tmp != chunks) {
Coverage.whileLoopStart(483)
                chunkCount++
Coverage.statementStart(484)
                tmp = tmp.next
Coverage.statementStart(485)
            }
Coverage.statementStart(486)
            if (chunkCount == 1) {
Coverage.ifStart(487)
                val resultSet = chunks.resultSet
Coverage.statementStart(488)
                val columns = chunks.columns
Coverage.statementStart(489)
                val res = ResultChunk(resultSet, columns)
Coverage.statementStart(490)
                var resLast = res
Coverage.statementStart(491)
                while (chunks.hasNext()) {
Coverage.whileLoopStart(492)
                    val same = chunks.sameElements()
Coverage.statementStart(493)
                    if (resLast.availableWrite() < 2) {
Coverage.ifStart(494)
                        resLast = append(resLast, ResultChunk(resultSet, columns))
Coverage.statementStart(495)
                    }
Coverage.statementStart(496)
                    resLast.internalInsertSorted(comparator, columnOrder, chunks.current(), same)
Coverage.statementStart(497)
                    chunks.skipPos(same)
Coverage.statementStart(498)
                }
Coverage.statementStart(499)
                if (res != res.next) {
Coverage.ifStart(500)
                    return sort(comparator, columnOrder, res)
                }
Coverage.statementStart(501)
                return res
            } else {
Coverage.ifStart(502)
                val half = chunkCount / 2
Coverage.statementStart(503)
                var a: ResultChunk? = sort(comparator, columnOrder, split(chunks, half))
Coverage.statementStart(504)
                var b: ResultChunk? = sort(comparator, columnOrder, chunks)
Coverage.statementStart(505)
                val resultSet = a!!.resultSet
Coverage.statementStart(506)
                val columns = a!!.columns
Coverage.statementStart(507)
                val res = ResultChunk(resultSet, columns)
Coverage.statementStart(508)
                var resLast = res
Coverage.statementStart(509)
                while (true) {
Coverage.whileLoopStart(510)
                    resLast = sortHelper(comparator, columnOrder, a!!, b!!, resLast)
Coverage.statementStart(511)
                    if (a.hasNext()) {
Coverage.ifStart(512)
                        b = removeFirst(b)
Coverage.statementStart(513)
                        if (b == null) {
Coverage.ifStart(514)
                            break
                        }
Coverage.statementStart(515)
                    } else {
Coverage.ifStart(516)
                        a = removeFirst(a)
Coverage.statementStart(517)
                        if (a == null) {
Coverage.ifStart(518)
                            break
                        }
Coverage.statementStart(519)
                    }
Coverage.statementStart(520)
                }
Coverage.statementStart(521)
                if (a != null) {
Coverage.ifStart(522)
                    var count = a!!.availableRead()
Coverage.statementStart(523)
                    if (count > 0) {
Coverage.ifStart(524)
                        resLast = copy(a, resLast, count)
Coverage.statementStart(525)
                    }
Coverage.statementStart(526)
                    a = removeFirst(a)
Coverage.statementStart(527)
                    if (a != null) {
Coverage.ifStart(528)
                        append(resLast, a)
Coverage.statementStart(529)
                    }
Coverage.statementStart(530)
                } else {
Coverage.ifStart(531)
                    var count = b!!.availableRead()
Coverage.statementStart(532)
                    if (count > 0) {
Coverage.ifStart(533)
                        resLast = copy(b!!, resLast, count)
Coverage.statementStart(534)
                    }
Coverage.statementStart(535)
                    b = removeFirst(b)
Coverage.statementStart(536)
                    if (b != null) {
Coverage.ifStart(537)
                        append(resLast, b!!)
Coverage.statementStart(538)
                    }
Coverage.statementStart(539)
                }
Coverage.statementStart(540)
                return res
            }
Coverage.statementStart(541)
        }
    }
    var prev = this
    var next = this
    fun internalInsertSorted(comparator: Array<Comparator<Value>>, columnOrder: Array<Variable>, values: Array<Value>, count: Int = 1) {
Coverage.funStart(542)
        var columnidx = columnOrder[0].toInt()
Coverage.statementStart(543)
        var column = data[columnidx]
Coverage.statementStart(544)
        var idx = column.insertSorted(values[columnidx], comparator = comparator[columnOrder[0].toInt()], count = count)
Coverage.statementStart(545)
        var first = idx.first
Coverage.statementStart(546)
        var last = first + idx.second - count
Coverage.statementStart(547)
        for (i in 1 until columns) {
Coverage.forLoopStart(548)
            columnidx = columnOrder[i].toInt()
Coverage.statementStart(549)
            column = data[columnidx]
Coverage.statementStart(550)
            idx = column.insertSorted(values[columnidx], first, last, comparator[columnOrder[i].toInt()], count)
Coverage.statementStart(551)
            if (idx.first > first) {
Coverage.ifStart(552)
                first = idx.first
Coverage.statementStart(553)
            }
Coverage.statementStart(554)
            if (last > first + idx.second - count) {
Coverage.ifStart(555)
                last = first + idx.second - count
Coverage.statementStart(556)
            }
Coverage.statementStart(557)
        }
Coverage.statementStart(558)
    }
}
class ValueComparatorFast : Comparator<Value> {
    override fun compare(a: Value, b: Value) = a.compareTo(b)
}
