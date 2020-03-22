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
val c=columnOrder[i].toInt()
Coverage.forLoopStart(456)
                    val vala = a.data[c].current()
Coverage.statementStart(457)
                    val valb = b.data[c].current()
Coverage.statementStart(458)
                    if (vala != valb) {
Coverage.ifStart(459)
                        cmp = comparator[c].compare(vala, valb)
Coverage.statementStart(460)
                        require(cmp != 0)
Coverage.statementStart(461)
                        if (cmp < 0) {
Coverage.ifStart(462)
                            var count = a.sameElements(Array(i+1){columnOrder[it]})
Coverage.statementStart(463)
                            targetLast = copy(a, targetLast, count)
Coverage.statementStart(464)
                        } else {
Coverage.ifStart(465)
                            var count = b.sameElements(Array(i+1){columnOrder[it]})
Coverage.statementStart(466)
                            targetLast = copy(b, targetLast, count)
Coverage.statementStart(467)
                        }
Coverage.statementStart(468)
                        continue@loop
                    }
Coverage.statementStart(469)
                }
Coverage.statementStart(470)
                if (cmp == 0) {
Coverage.ifStart(471)
                    val i = columnOrder[columnOrder.size - 1]
Coverage.statementStart(472)
                    var countA = a.sameElements()
Coverage.statementStart(473)
                    targetLast = copy(a, targetLast, countA)
Coverage.statementStart(474)
                    var countB = b.sameElements()
Coverage.statementStart(475)
                    targetLast = copy(b, targetLast, countB)
Coverage.statementStart(476)
                }
Coverage.statementStart(477)
            }
Coverage.statementStart(478)
            return targetLast
        }
        fun sort(comparator: Array<Comparator<Value>>, columnOrder: Array<Variable>, chunks: ResultChunk): ResultChunk {
Coverage.funStart(479)
            var chunkCount = 1
Coverage.statementStart(480)
            var tmp = chunks.next
Coverage.statementStart(481)
            while (tmp != chunks) {
Coverage.whileLoopStart(482)
                chunkCount++
Coverage.statementStart(483)
                tmp = tmp.next
Coverage.statementStart(484)
            }
Coverage.statementStart(485)
            if (chunkCount == 1) {
Coverage.ifStart(486)
                val resultSet = chunks.resultSet
Coverage.statementStart(487)
                val columns = chunks.columns
Coverage.statementStart(488)
                val res = ResultChunk(resultSet, columns)
Coverage.statementStart(489)
                var resLast = res
Coverage.statementStart(490)
                while (chunks.hasNext()) {
Coverage.whileLoopStart(491)
                    val same = chunks.sameElements()
Coverage.statementStart(492)
                    if (resLast.availableWrite() < 2) {
Coverage.ifStart(493)
                        resLast = append(resLast, ResultChunk(resultSet, columns))
Coverage.statementStart(494)
                    }
Coverage.statementStart(495)
                    resLast.internalInsertSorted(comparator, columnOrder, chunks.current(), same)
Coverage.statementStart(496)
                    chunks.skipPos(same)
Coverage.statementStart(497)
                }
Coverage.statementStart(498)
                if (res != res.next) {
Coverage.ifStart(499)
                    return sort(comparator, columnOrder, res)
                }
Coverage.statementStart(500)
                return res
            } else {
Coverage.ifStart(501)
                val half = chunkCount / 2
Coverage.statementStart(502)
                var a: ResultChunk? = sort(comparator, columnOrder, split(chunks, half))
Coverage.statementStart(503)
                var b: ResultChunk? = sort(comparator, columnOrder, chunks)
Coverage.statementStart(504)
                val resultSet = a!!.resultSet
Coverage.statementStart(505)
                val columns = a!!.columns
Coverage.statementStart(506)
                val res = ResultChunk(resultSet, columns)
Coverage.statementStart(507)
                var resLast = res
Coverage.statementStart(508)
                while (true) {
Coverage.whileLoopStart(509)
                    resLast = sortHelper(comparator, columnOrder, a!!, b!!, resLast)
Coverage.statementStart(510)
                    if (a.hasNext()) {
Coverage.ifStart(511)
                        b = removeFirst(b)
Coverage.statementStart(512)
                        if (b == null) {
Coverage.ifStart(513)
                            break
                        }
Coverage.statementStart(514)
                    } else {
Coverage.ifStart(515)
                        a = removeFirst(a)
Coverage.statementStart(516)
                        if (a == null) {
Coverage.ifStart(517)
                            break
                        }
Coverage.statementStart(518)
                    }
Coverage.statementStart(519)
                }
Coverage.statementStart(520)
                if (a != null) {
Coverage.ifStart(521)
                    var count = a!!.availableRead()
Coverage.statementStart(522)
                    if (count > 0) {
Coverage.ifStart(523)
                        resLast = copy(a, resLast, count)
Coverage.statementStart(524)
                    }
Coverage.statementStart(525)
                    a = removeFirst(a)
Coverage.statementStart(526)
                    if (a != null) {
Coverage.ifStart(527)
                        append(resLast, a)
Coverage.statementStart(528)
                    }
Coverage.statementStart(529)
                } else {
Coverage.ifStart(530)
                    var count = b!!.availableRead()
Coverage.statementStart(531)
                    if (count > 0) {
Coverage.ifStart(532)
                        resLast = copy(b!!, resLast, count)
Coverage.statementStart(533)
                    }
Coverage.statementStart(534)
                    b = removeFirst(b)
Coverage.statementStart(535)
                    if (b != null) {
Coverage.ifStart(536)
                        append(resLast, b!!)
Coverage.statementStart(537)
                    }
Coverage.statementStart(538)
                }
Coverage.statementStart(539)
                return res
            }
Coverage.statementStart(540)
        }
    }
    var prev = this
    var next = this
    fun internalInsertSorted(comparator: Array<Comparator<Value>>, columnOrder: Array<Variable>, values: Array<Value>, count: Int = 1) {
Coverage.funStart(541)
        var columnidx = columnOrder[0].toInt()
Coverage.statementStart(542)
        var column = data[columnidx]
Coverage.statementStart(543)
        var idx = column.insertSorted(values[columnidx], comparator = comparator[columnOrder[0].toInt()], count = count)
Coverage.statementStart(544)
        var first = idx.first
Coverage.statementStart(545)
        var last = first + idx.second - count
Coverage.statementStart(546)
        for (i in 1 until columns) {
Coverage.forLoopStart(547)
            columnidx = columnOrder[i].toInt()
Coverage.statementStart(548)
            column = data[columnidx]
Coverage.statementStart(549)
            idx = column.insertSorted(values[columnidx], first, last, comparator[columnOrder[i].toInt()], count)
Coverage.statementStart(550)
            if (idx.first > first) {
Coverage.ifStart(551)
                first = idx.first
Coverage.statementStart(552)
            }
Coverage.statementStart(553)
            if (last > first + idx.second - count) {
Coverage.ifStart(554)
                last = first + idx.second - count
Coverage.statementStart(555)
            }
Coverage.statementStart(556)
        }
Coverage.statementStart(557)
    }
}
class ValueComparatorFast : Comparator<Value> {
    override fun compare(a: Value, b: Value) = a.compareTo(b)
}
