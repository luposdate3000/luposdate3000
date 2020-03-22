package lupos.s03resultRepresentation
import lupos.s00misc.Coverage
import lupos.s03resultRepresentation.ResultChunk
import lupos.s04arithmetikOperators.ResultVektorRaw
open class ResultChunk(resultSet: ResultSet, columns: Int) : ResultChunkBase(resultSet, columns) {
    companion object {
        operator fun invoke(resultSet: ResultSet): ResultChunk {
Coverage.funStart(356)
            val columns = resultSet.getVariableNames().size
Coverage.statementStart(357)
            if (columns == 0) {
Coverage.ifStart(358)
                return ResultChunkNoColumns(resultSet, columns)
            }
Coverage.statementStart(359)
            return ResultChunk(resultSet, columns)
        }
        fun removeFirst(root: ResultChunk): ResultChunk? {
Coverage.funStart(360)
            if (root.next == root) {
Coverage.ifStart(361)
                return null
            }
Coverage.statementStart(362)
            val res = root.next
Coverage.statementStart(363)
            res.prev = root.prev
Coverage.statementStart(364)
            root.prev.next = res
Coverage.statementStart(365)
            return res
        }
        fun split(root: ResultChunk, count: Int): ResultChunk {
Coverage.funStart(366)
            require(count > 0)
Coverage.statementStart(367)
            var other = root
Coverage.statementStart(368)
            for (i in 0 until count) {
Coverage.forLoopStart(369)
                other = other.next
Coverage.statementStart(370)
            }
Coverage.statementStart(371)
            val rootLast = other.prev
Coverage.statementStart(372)
            val otherLast = root.prev
Coverage.statementStart(373)
            root.prev = rootLast
Coverage.statementStart(374)
            rootLast.next = root
Coverage.statementStart(375)
            otherLast.next = other
Coverage.statementStart(376)
            other.prev = otherLast
Coverage.statementStart(377)
            return other
        }
        fun append(rootLast: ResultChunk, other: ResultChunk): ResultChunk {
Coverage.funStart(378)
            val root = rootLast.next
Coverage.statementStart(379)
            val otherLast = other.prev
Coverage.statementStart(380)
            rootLast.next = other
Coverage.statementStart(381)
            root.prev = otherLast
Coverage.statementStart(382)
            otherLast.next = root
Coverage.statementStart(383)
            other.prev = rootLast
Coverage.statementStart(384)
            return otherLast
        }
        fun copy(from: ResultChunk, target: ResultChunk, count: Int): ResultChunk {
Coverage.funStart(385)
            require(count > 0)
Coverage.statementStart(386)
            val resultSet = from.resultSet
Coverage.statementStart(387)
            val columns = from.columns
Coverage.statementStart(388)
            var targetLast = target
Coverage.statementStart(389)
            var available = targetLast.availableWrite()
Coverage.statementStart(390)
            if (available == 0) {
Coverage.ifStart(391)
                targetLast = append(targetLast, ResultChunk(resultSet, columns))
Coverage.statementStart(392)
                available = ResultVektor.capacity
Coverage.statementStart(393)
            }
Coverage.statementStart(394)
            var cnt = count
Coverage.statementStart(395)
            while (available < cnt) {
Coverage.whileLoopStart(396)
                targetLast.copy(from, available)
Coverage.statementStart(397)
                cnt -= available
Coverage.statementStart(398)
                targetLast = append(targetLast, ResultChunk(resultSet, columns))
Coverage.statementStart(399)
                available = ResultVektor.capacity
Coverage.statementStart(400)
            }
Coverage.statementStart(401)
            targetLast.copy(from, cnt)
Coverage.statementStart(402)
            return targetLast
        }
        fun sortHelper(comparator: Array<Comparator<Value>>, columnOrder: Array<Variable>, a: ResultChunk, b: ResultChunk, target: ResultChunk): ResultChunk {
Coverage.funStart(403)
            var targetLast = target
Coverage.statementStart(404)
            loop@ while (a.availableRead() > 0 && b.availableRead() > 0) {
Coverage.whileLoopStart(405)
                var cmp = 0
Coverage.statementStart(406)
                for (i in 0 until columnOrder.size) {
Coverage.forLoopStart(407)
                    val c = columnOrder[i].toInt()
Coverage.statementStart(408)
                    val vala = a.data[c].current()
Coverage.statementStart(409)
                    val valb = b.data[c].current()
Coverage.statementStart(410)
                    if (vala != valb) {
Coverage.ifStart(411)
                        cmp = comparator[c].compare(vala, valb)
Coverage.statementStart(412)
                        require(cmp != 0)
Coverage.statementStart(413)
                        if (cmp < 0) {
Coverage.ifStart(414)
                            var count = a.sameElements(Array(i + 1) { columnOrder[it] })
Coverage.statementStart(415)
                            targetLast = copy(a, targetLast, count)
Coverage.statementStart(416)
                        } else {
Coverage.ifStart(417)
                            var count = b.sameElements(Array(i + 1) { columnOrder[it] })
Coverage.statementStart(418)
                            targetLast = copy(b, targetLast, count)
Coverage.statementStart(419)
                        }
Coverage.statementStart(420)
                        continue@loop
                    }
Coverage.statementStart(421)
                }
Coverage.statementStart(422)
                if (cmp == 0) {
Coverage.ifStart(423)
                    val i = columnOrder[columnOrder.size - 1]
Coverage.statementStart(424)
                    var countA = a.sameElements()
Coverage.statementStart(425)
                    targetLast = copy(a, targetLast, countA)
Coverage.statementStart(426)
                    var countB = b.sameElements()
Coverage.statementStart(427)
                    targetLast = copy(b, targetLast, countB)
Coverage.statementStart(428)
                }
Coverage.statementStart(429)
            }
Coverage.statementStart(430)
            return targetLast
        }
        fun sort(comparator: Array<Comparator<Value>>, columnOrder: Array<Variable>, chunks: ResultChunk): ResultChunk {
Coverage.funStart(431)
            var chunkCount = 1
Coverage.statementStart(432)
            var tmp = chunks.next
Coverage.statementStart(433)
            while (tmp != chunks) {
Coverage.whileLoopStart(434)
                chunkCount++
Coverage.statementStart(435)
                tmp = tmp.next
Coverage.statementStart(436)
            }
Coverage.statementStart(437)
            if (chunkCount == 1) {
Coverage.ifStart(438)
                val resultSet = chunks.resultSet
Coverage.statementStart(439)
                val columns = chunks.columns
Coverage.statementStart(440)
                val res = ResultChunk(resultSet, columns)
Coverage.statementStart(441)
                var resLast = res
Coverage.statementStart(442)
                while (chunks.hasNext()) {
Coverage.whileLoopStart(443)
                    val same = chunks.sameElements()
Coverage.statementStart(444)
                    if (resLast.availableWrite() < 2) {
Coverage.ifStart(445)
                        resLast = append(resLast, ResultChunk(resultSet, columns))
Coverage.statementStart(446)
                    }
Coverage.statementStart(447)
                    resLast.internalInsertSorted(comparator, columnOrder, chunks.current(), same)
Coverage.statementStart(448)
                    chunks.skipPos(same)
Coverage.statementStart(449)
                }
Coverage.statementStart(450)
                if (res != res.next) {
Coverage.ifStart(451)
                    return sort(comparator, columnOrder, res)
                }
Coverage.statementStart(452)
                return res
            } else {
Coverage.ifStart(453)
                val half = chunkCount / 2
Coverage.statementStart(454)
                var a: ResultChunk? = sort(comparator, columnOrder, split(chunks, half))
Coverage.statementStart(455)
                var b: ResultChunk? = sort(comparator, columnOrder, chunks)
Coverage.statementStart(456)
                val resultSet = a!!.resultSet
Coverage.statementStart(457)
                val columns = a!!.columns
Coverage.statementStart(458)
                val res = ResultChunk(resultSet, columns)
Coverage.statementStart(459)
                var resLast = res
Coverage.statementStart(460)
                while (true) {
Coverage.whileLoopStart(461)
                    resLast = sortHelper(comparator, columnOrder, a!!, b!!, resLast)
Coverage.statementStart(462)
                    if (a.hasNext()) {
Coverage.ifStart(463)
                        b = removeFirst(b)
Coverage.statementStart(464)
                        if (b == null) {
Coverage.ifStart(465)
                            break
                        }
Coverage.statementStart(466)
                    } else {
Coverage.ifStart(467)
                        a = removeFirst(a)
Coverage.statementStart(468)
                        if (a == null) {
Coverage.ifStart(469)
                            break
                        }
Coverage.statementStart(470)
                    }
Coverage.statementStart(471)
                }
Coverage.statementStart(472)
                if (a != null) {
Coverage.ifStart(473)
                    var count = a!!.availableRead()
Coverage.statementStart(474)
                    if (count > 0) {
Coverage.ifStart(475)
                        resLast = copy(a, resLast, count)
Coverage.statementStart(476)
                    }
Coverage.statementStart(477)
                    a = removeFirst(a)
Coverage.statementStart(478)
                    if (a != null) {
Coverage.ifStart(479)
                        append(resLast, a)
Coverage.statementStart(480)
                    }
Coverage.statementStart(481)
                } else {
Coverage.ifStart(482)
                    var count = b!!.availableRead()
Coverage.statementStart(483)
                    if (count > 0) {
Coverage.ifStart(484)
                        resLast = copy(b!!, resLast, count)
Coverage.statementStart(485)
                    }
Coverage.statementStart(486)
                    b = removeFirst(b)
Coverage.statementStart(487)
                    if (b != null) {
Coverage.ifStart(488)
                        append(resLast, b!!)
Coverage.statementStart(489)
                    }
Coverage.statementStart(490)
                }
Coverage.statementStart(491)
                return res
            }
Coverage.statementStart(492)
        }
    }
    var prev = this
    var next = this
    fun internalInsertSorted(comparator: Array<Comparator<Value>>, columnOrder: Array<Variable>, values: Array<Value>, count: Int = 1) {
Coverage.funStart(493)
        var columnidx = columnOrder[0].toInt()
Coverage.statementStart(494)
        var column = data[columnidx]
Coverage.statementStart(495)
        var idx = column.insertSorted(values[columnidx], comparator = comparator[columnOrder[0].toInt()], count = count)
Coverage.statementStart(496)
        var first = idx.first
Coverage.statementStart(497)
        var last = first + idx.second - count
Coverage.statementStart(498)
        for (i in 1 until columns) {
Coverage.forLoopStart(499)
            columnidx = columnOrder[i].toInt()
Coverage.statementStart(500)
            column = data[columnidx]
Coverage.statementStart(501)
            idx = column.insertSorted(values[columnidx], first, last, comparator[columnOrder[i].toInt()], count)
Coverage.statementStart(502)
            if (idx.first > first) {
Coverage.ifStart(503)
                first = idx.first
Coverage.statementStart(504)
            }
Coverage.statementStart(505)
            if (last > first + idx.second - count) {
Coverage.ifStart(506)
                last = first + idx.second - count
Coverage.statementStart(507)
            }
Coverage.statementStart(508)
        }
Coverage.statementStart(509)
    }
}
class ValueComparatorFast : Comparator<Value> {
    override fun compare(a: Value, b: Value) = a.compareTo(b)
}
