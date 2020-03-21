package lupos.s03resultRepresentation
import lupos.s00misc.Coverage
import lupos.s03resultRepresentation.ResultChunk
import lupos.s04arithmetikOperators.ResultVektorRaw
open class ResultChunk(resultSet: ResultSet, columns: Int) : ResultChunkBase(resultSet, columns) {
    companion object {
        operator fun invoke(resultSet: ResultSet): ResultChunk {
Coverage.funStart(376)
            val columns = resultSet.getVariableNames().size
Coverage.statementStart(377)
            if (columns == 0) {
Coverage.ifStart(378)
                return ResultChunkNoColumns(resultSet, columns)
            }
Coverage.statementStart(379)
            return ResultChunk(resultSet, columns)
        }
        fun removeFirst(root: ResultChunk): ResultChunk? {
Coverage.funStart(380)
            if (root.next == root) {
Coverage.ifStart(381)
                return null
            }
Coverage.statementStart(382)
            val res = root.next
Coverage.statementStart(383)
            res.prev = root.prev
Coverage.statementStart(384)
            root.prev.next = res
Coverage.statementStart(385)
            return res
        }
        fun split(root: ResultChunk, count: Int): ResultChunk {
Coverage.funStart(386)
            require(count > 0)
Coverage.statementStart(387)
            var other = root
Coverage.statementStart(388)
            for (i in 0 until count) {
Coverage.forLoopStart(389)
                other = other.next
Coverage.statementStart(390)
            }
Coverage.statementStart(391)
            val rootLast = other.prev
Coverage.statementStart(392)
            val otherLast = root.prev
Coverage.statementStart(393)
            root.prev = rootLast
Coverage.statementStart(394)
            rootLast.next = root
Coverage.statementStart(395)
            otherLast.next = other
Coverage.statementStart(396)
            other.prev = otherLast
Coverage.statementStart(397)
            return other
        }
        fun append(rootLast: ResultChunk, other: ResultChunk): ResultChunk {
Coverage.funStart(398)
            val root = rootLast.next
Coverage.statementStart(399)
            val otherLast = other.prev
Coverage.statementStart(400)
            rootLast.next = other
Coverage.statementStart(401)
            root.prev = otherLast
Coverage.statementStart(402)
            otherLast.next = root
Coverage.statementStart(403)
            other.prev = rootLast
Coverage.statementStart(404)
            return otherLast
        }
        fun copy(from: ResultChunk, target: ResultChunk, count: Int): ResultChunk {
Coverage.funStart(405)
            require(count > 0)
Coverage.statementStart(406)
            val resultSet = from.resultSet
Coverage.statementStart(407)
            val columns = from.columns
Coverage.statementStart(408)
            var targetLast = target
Coverage.statementStart(409)
            var available = targetLast.availableWrite()
Coverage.statementStart(410)
            if (available == 0) {
Coverage.ifStart(411)
                targetLast = append(targetLast, ResultChunk(resultSet, columns))
Coverage.statementStart(412)
                available = ResultVektor.capacity
Coverage.statementStart(413)
            }
Coverage.statementStart(414)
            var cnt = count
Coverage.statementStart(415)
            while (available < cnt) {
Coverage.whileLoopStart(416)
                targetLast.copy(from, available)
Coverage.statementStart(417)
                cnt -= available
Coverage.statementStart(418)
                targetLast = append(targetLast, ResultChunk(resultSet, columns))
Coverage.statementStart(419)
                available = ResultVektor.capacity
Coverage.statementStart(420)
            }
Coverage.statementStart(421)
            targetLast.copy(from, cnt)
Coverage.statementStart(422)
            return targetLast
        }
        fun sortHelper(comparator: Array<Comparator<Value>>, columnOrder: Array<Variable>, a: ResultChunk, b: ResultChunk, target: ResultChunk): ResultChunk {
Coverage.funStart(423)
            var targetLast = target
Coverage.statementStart(424)
            loop@ while (a.availableRead() > 0 && b.availableRead() > 0) {
Coverage.whileLoopStart(425)
                var cmp = 0
Coverage.statementStart(426)
                for (i in columnOrder) {
Coverage.forLoopStart(427)
                    val vala = a.data[i.toInt()].current()
Coverage.statementStart(428)
                    val valb = b.data[i.toInt()].current()
Coverage.statementStart(429)
                    if (vala != valb) {
Coverage.ifStart(430)
                        cmp = comparator[i.toInt()].compare(vala, valb)
Coverage.statementStart(431)
                        require(cmp != 0)
Coverage.statementStart(432)
                        if (cmp < 0) {
Coverage.ifStart(433)
                            var count = a.data[i.toInt()].sameElements()
Coverage.statementStart(434)
                            targetLast = copy(a, targetLast, count)
Coverage.statementStart(435)
                        } else {
Coverage.ifStart(436)
                            var count = b.data[i.toInt()].sameElements()
Coverage.statementStart(437)
                            targetLast = copy(b, targetLast, count)
Coverage.statementStart(438)
                        }
Coverage.statementStart(439)
                        continue@loop
                    }
Coverage.statementStart(440)
                }
Coverage.statementStart(441)
                if (cmp == 0) {
Coverage.ifStart(442)
                    val i = columnOrder[columnOrder.size - 1]
Coverage.statementStart(443)
                    var countA = a.data[i.toInt()].sameElements()
Coverage.statementStart(444)
                    targetLast = copy(a, targetLast, countA)
Coverage.statementStart(445)
                    var countB = b.data[i.toInt()].sameElements()
Coverage.statementStart(446)
                    targetLast = copy(b, targetLast, countB)
Coverage.statementStart(447)
                }
Coverage.statementStart(448)
            }
Coverage.statementStart(449)
            return targetLast
        }
        fun sort(comparator: Array<Comparator<Value>>, columnOrder: Array<Variable>, chunks: ResultChunk): ResultChunk {
Coverage.funStart(450)
            var chunkCount = 1
Coverage.statementStart(451)
            var tmp = chunks.next
Coverage.statementStart(452)
            while (tmp != chunks) {
Coverage.whileLoopStart(453)
                chunkCount++
Coverage.statementStart(454)
                tmp = tmp.next
Coverage.statementStart(455)
            }
Coverage.statementStart(456)
            if (chunkCount == 1) {
Coverage.ifStart(457)
                val resultSet = chunks.resultSet
Coverage.statementStart(458)
                val columns = chunks.columns
Coverage.statementStart(459)
                val res = ResultChunk(resultSet, columns)
Coverage.statementStart(460)
                var resLast = res
Coverage.statementStart(461)
                while (chunks.hasNext()) {
Coverage.whileLoopStart(462)
                    val same = chunks.sameElements()
Coverage.statementStart(463)
                    if (resLast.availableWrite() < 2) {
Coverage.ifStart(464)
                        resLast = append(resLast, ResultChunk(resultSet, columns))
Coverage.statementStart(465)
                    }
Coverage.statementStart(466)
                    resLast.internalInsertSorted(comparator, columnOrder, chunks.current(), same)
Coverage.statementStart(467)
                    chunks.skipPos(same)
Coverage.statementStart(468)
                }
Coverage.statementStart(469)
                if (res != res.next) {
Coverage.ifStart(470)
                    return sort(comparator, columnOrder, res)
                }
Coverage.statementStart(471)
                return res
            } else {
Coverage.ifStart(472)
                val half = chunkCount / 2
Coverage.statementStart(473)
                var a: ResultChunk? = sort(comparator, columnOrder, split(chunks, half))
Coverage.statementStart(474)
                var b: ResultChunk? = sort(comparator, columnOrder, chunks)
Coverage.statementStart(475)
                val resultSet = a!!.resultSet
Coverage.statementStart(476)
                val columns = a!!.columns
Coverage.statementStart(477)
                val res = ResultChunk(resultSet, columns)
Coverage.statementStart(478)
                var resLast = res
Coverage.statementStart(479)
                while (true) {
Coverage.whileLoopStart(480)
                    resLast = sortHelper(comparator, columnOrder, a!!, b!!, resLast)
Coverage.statementStart(481)
                    if (a.hasNext()) {
Coverage.ifStart(482)
                        b = removeFirst(b)
Coverage.statementStart(483)
                        if (b == null) {
Coverage.ifStart(484)
                            break
                        }
Coverage.statementStart(485)
                    } else {
Coverage.ifStart(486)
                        a = removeFirst(a)
Coverage.statementStart(487)
                        if (a == null) {
Coverage.ifStart(488)
                            break
                        }
Coverage.statementStart(489)
                    }
Coverage.statementStart(490)
                }
Coverage.statementStart(491)
                if (a != null) {
Coverage.ifStart(492)
                    var count = a!!.availableRead()
Coverage.statementStart(493)
                    if (count > 0) {
Coverage.ifStart(494)
                        resLast = copy(a, resLast, count)
Coverage.statementStart(495)
                    }
Coverage.statementStart(496)
                    a = removeFirst(a)
Coverage.statementStart(497)
                    if (a != null) {
Coverage.ifStart(498)
                        append(resLast, a)
Coverage.statementStart(499)
                    }
Coverage.statementStart(500)
                } else {
Coverage.ifStart(501)
                    var count = b!!.availableRead()
Coverage.statementStart(502)
                    if (count > 0) {
Coverage.ifStart(503)
                        resLast = copy(b!!, resLast, count)
Coverage.statementStart(504)
                    }
Coverage.statementStart(505)
                    b = removeFirst(b)
Coverage.statementStart(506)
                    if (b != null) {
Coverage.ifStart(507)
                        append(resLast, b!!)
Coverage.statementStart(508)
                    }
Coverage.statementStart(509)
                }
Coverage.statementStart(510)
                return res
            }
Coverage.statementStart(511)
        }
    }
    var prev = this
    var next = this
















    fun internalInsertSorted(comparator: Array<Comparator<Value>>, columnOrder: Array<Variable>, values: Array<Value>, count: Int = 1) {
Coverage.funStart(519)
        var columnidx = columnOrder[0].toInt()
Coverage.statementStart(520)
        var column = data[columnidx]
Coverage.statementStart(521)
        var idx = column.insertSorted(values[columnidx], comparator = comparator[columnOrder[0].toInt()], count = count)
Coverage.statementStart(522)
        var first = idx.first
Coverage.statementStart(523)
        var last = first + idx.second - count
Coverage.statementStart(524)
        for (i in 1 until columns) {
Coverage.forLoopStart(525)
            columnidx = columnOrder[i].toInt()
Coverage.statementStart(526)
            column = data[columnidx]
Coverage.statementStart(527)
            idx = column.insertSorted(values[columnidx], first, last, comparator[columnOrder[i].toInt()], count)
Coverage.statementStart(528)
            if (idx.first > first) {
Coverage.ifStart(529)
                first = idx.first
Coverage.statementStart(530)
            }
Coverage.statementStart(531)
            if (last > first + idx.second - count) {
Coverage.ifStart(532)
                last = first + idx.second - count
Coverage.statementStart(533)
            }
Coverage.statementStart(534)
        }
Coverage.statementStart(535)
    }
}
class ValueComparatorFast : Comparator<Value> {
    override fun compare(a: Value, b: Value) = a.compareTo(b)
}
