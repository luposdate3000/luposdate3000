package lupos.s03resultRepresentation
import lupos.s00misc.Coverage
import lupos.s03resultRepresentation.ResultChunk
import lupos.s04arithmetikOperators.ResultVektorRaw
open class ResultChunk(resultSet: ResultSet, columns: Int) : ResultChunkBase(resultSet, columns) {
    companion object {
        operator fun invoke(resultSet: ResultSet): ResultChunk {
Coverage.funStart(339)
            val columns = resultSet.getVariableNames().size
Coverage.statementStart(340)
            if (columns == 0) {
Coverage.ifStart(341)
                return ResultChunkNoColumns(resultSet, columns)
            }
Coverage.statementStart(342)
            return ResultChunk(resultSet, columns)
        }
        fun removeFirst(root: ResultChunk): ResultChunk? {
Coverage.funStart(343)
            if (root.next == root) {
Coverage.ifStart(344)
                return null
            }
Coverage.statementStart(345)
            val res = root.next
Coverage.statementStart(346)
            res.prev = root.prev
Coverage.statementStart(347)
            root.prev.next = res
Coverage.statementStart(348)
            return res
        }
        fun split(root: ResultChunk, count: Int): ResultChunk {
Coverage.funStart(349)
            require(count > 0)
Coverage.statementStart(350)
            var other = root
Coverage.statementStart(351)
            for (i in 0 until count) {
Coverage.forLoopStart(352)
                other = other.next
Coverage.statementStart(353)
            }
Coverage.statementStart(354)
            val rootLast = other.prev
Coverage.statementStart(355)
            val otherLast = root.prev
Coverage.statementStart(356)
            root.prev = rootLast
Coverage.statementStart(357)
            rootLast.next = root
Coverage.statementStart(358)
            otherLast.next = other
Coverage.statementStart(359)
            other.prev = otherLast
Coverage.statementStart(360)
            return other
        }
        fun append(rootLast: ResultChunk, other: ResultChunk): ResultChunk {
Coverage.funStart(361)
            val root = rootLast.next
Coverage.statementStart(362)
            val otherLast = other.prev
Coverage.statementStart(363)
            rootLast.next = other
Coverage.statementStart(364)
            root.prev = otherLast
Coverage.statementStart(365)
            otherLast.next = root
Coverage.statementStart(366)
            other.prev = rootLast
Coverage.statementStart(367)
            return otherLast
        }
        fun copy(from: ResultChunk, target: ResultChunk, count: Int): ResultChunk {
Coverage.funStart(368)
            require(count > 0)
Coverage.statementStart(369)
            val resultSet = from.resultSet
Coverage.statementStart(370)
            val columns = from.columns
Coverage.statementStart(371)
            var targetLast = target
Coverage.statementStart(372)
            var available = targetLast.availableWrite()
Coverage.statementStart(373)
            if (available == 0) {
Coverage.ifStart(374)
                targetLast = append(targetLast, ResultChunk(resultSet, columns))
Coverage.statementStart(375)
                available = ResultVektor.capacity
Coverage.statementStart(376)
            }
Coverage.statementStart(377)
            var cnt = count
Coverage.statementStart(378)
            while (available < cnt) {
Coverage.whileLoopStart(379)
                targetLast.copy(from, available)
Coverage.statementStart(380)
                cnt -= available
Coverage.statementStart(381)
                targetLast = append(targetLast, ResultChunk(resultSet, columns))
Coverage.statementStart(382)
                available = ResultVektor.capacity
Coverage.statementStart(383)
            }
Coverage.statementStart(384)
            targetLast.copy(from, cnt)
Coverage.statementStart(385)
            return targetLast
        }
        fun sortHelper(comparator: Array<Comparator<Value>>, columnOrder: Array<Variable>, a: ResultChunk, b: ResultChunk, target: ResultChunk): ResultChunk {
Coverage.funStart(386)
            var targetLast = target
Coverage.statementStart(387)
            loop@ while (a.availableRead() > 0 && b.availableRead() > 0) {
Coverage.whileLoopStart(388)
                var cmp = 0
Coverage.statementStart(389)
                for (i in 0 until columnOrder.size) {
Coverage.forLoopStart(390)
                    val c = columnOrder[i].toInt()
Coverage.statementStart(391)
                    val vala = a.data[c].current()
Coverage.statementStart(392)
                    val valb = b.data[c].current()
Coverage.statementStart(393)
                    if (vala != valb) {
Coverage.ifStart(394)
                        cmp = comparator[c].compare(vala, valb)
Coverage.statementStart(395)
                        require(cmp != 0)
Coverage.statementStart(396)
                        if (cmp < 0) {
Coverage.ifStart(397)
                            var count = a.sameElements(Array(i + 1) { columnOrder[it] })
Coverage.statementStart(398)
                            targetLast = copy(a, targetLast, count)
Coverage.statementStart(399)
                        } else {
Coverage.ifStart(400)
                            var count = b.sameElements(Array(i + 1) { columnOrder[it] })
Coverage.statementStart(401)
                            targetLast = copy(b, targetLast, count)
Coverage.statementStart(402)
                        }
Coverage.statementStart(403)
                        continue@loop
                    }
Coverage.statementStart(404)
                }
Coverage.statementStart(405)
                if (cmp == 0) {
Coverage.ifStart(406)
                    val i = columnOrder[columnOrder.size - 1]
Coverage.statementStart(407)
                    var countA = a.sameElements()
Coverage.statementStart(408)
                    targetLast = copy(a, targetLast, countA)
Coverage.statementStart(409)
                    var countB = b.sameElements()
Coverage.statementStart(410)
                    targetLast = copy(b, targetLast, countB)
Coverage.statementStart(411)
                }
Coverage.statementStart(412)
            }
Coverage.statementStart(413)
            return targetLast
        }
        fun sort(comparator: Array<Comparator<Value>>, columnOrder: Array<Variable>, chunks: ResultChunk): ResultChunk {
Coverage.funStart(414)
            var chunkCount = 1
Coverage.statementStart(415)
            var tmp = chunks.next
Coverage.statementStart(416)
            while (tmp != chunks) {
Coverage.whileLoopStart(417)
                chunkCount++
Coverage.statementStart(418)
                tmp = tmp.next
Coverage.statementStart(419)
            }
Coverage.statementStart(420)
            if (chunkCount == 1) {
Coverage.ifStart(421)
                val resultSet = chunks.resultSet
Coverage.statementStart(422)
                val columns = chunks.columns
Coverage.statementStart(423)
                val res = ResultChunk(resultSet, columns)
Coverage.statementStart(424)
                var resLast = res
Coverage.statementStart(425)
                while (chunks.hasNext()) {
Coverage.whileLoopStart(426)
                    val same = chunks.sameElements()
Coverage.statementStart(427)
                    if (resLast.availableWrite() < 2) {
Coverage.ifStart(428)
                        resLast = append(resLast, ResultChunk(resultSet, columns))
Coverage.statementStart(429)
                    }
Coverage.statementStart(430)
                    resLast.internalInsertSorted(comparator, columnOrder, chunks.current(), same)
Coverage.statementStart(431)
                    chunks.skipPos(same)
Coverage.statementStart(432)
                }
Coverage.statementStart(433)
                if (res != res.next) {
Coverage.ifStart(434)
                    return sort(comparator, columnOrder, res)
                }
Coverage.statementStart(435)
                return res
            } else {
Coverage.ifStart(436)
                val half = chunkCount / 2
Coverage.statementStart(437)
                var a: ResultChunk? = sort(comparator, columnOrder, split(chunks, half))
Coverage.statementStart(438)
                var b: ResultChunk? = sort(comparator, columnOrder, chunks)
Coverage.statementStart(439)
                val resultSet = a!!.resultSet
Coverage.statementStart(440)
                val columns = a!!.columns
Coverage.statementStart(441)
                val res = ResultChunk(resultSet, columns)
Coverage.statementStart(442)
                var resLast = res
Coverage.statementStart(443)
                while (true) {
Coverage.whileLoopStart(444)
                    resLast = sortHelper(comparator, columnOrder, a!!, b!!, resLast)
Coverage.statementStart(445)
                    if (a.hasNext()) {
Coverage.ifStart(446)
                        b = removeFirst(b)
Coverage.statementStart(447)
                        if (b == null) {
Coverage.ifStart(448)
                            break
                        }
Coverage.statementStart(449)
                    } else {
Coverage.ifStart(450)
                        a = removeFirst(a)
Coverage.statementStart(451)
                        if (a == null) {
Coverage.ifStart(452)
                            break
                        }
Coverage.statementStart(453)
                    }
Coverage.statementStart(454)
                }
Coverage.statementStart(455)
                if (a != null) {
Coverage.ifStart(456)
                    var count = a!!.availableRead()
Coverage.statementStart(457)
                    if (count > 0) {
Coverage.ifStart(458)
                        resLast = copy(a, resLast, count)
Coverage.statementStart(459)
                    }
Coverage.statementStart(460)
                    a = removeFirst(a)
Coverage.statementStart(461)
                    if (a != null) {
Coverage.ifStart(462)
                        append(resLast, a)
Coverage.statementStart(463)
                    }
Coverage.statementStart(464)
                } else {
Coverage.ifStart(465)
                    var count = b!!.availableRead()
Coverage.statementStart(466)
                    if (count > 0) {
Coverage.ifStart(467)
                        resLast = copy(b!!, resLast, count)
Coverage.statementStart(468)
                    }
Coverage.statementStart(469)
                    b = removeFirst(b)
Coverage.statementStart(470)
                    if (b != null) {
Coverage.ifStart(471)
                        append(resLast, b!!)
Coverage.statementStart(472)
                    }
Coverage.statementStart(473)
                }
Coverage.statementStart(474)
                return res
            }
/*Coverage Unreachable*/
        }
    }
    var prev = this
    var next = this
    fun internalInsertSorted(comparator: Array<Comparator<Value>>, columnOrder: Array<Variable>, values: Array<Value>, count: Int = 1) {
Coverage.funStart(475)
        var columnidx = columnOrder[0].toInt()
Coverage.statementStart(476)
        var column = data[columnidx]
Coverage.statementStart(477)
        var idx = column.insertSorted(values[columnidx], comparator = comparator[columnOrder[0].toInt()], count = count)
Coverage.statementStart(478)
        var first = idx.first
Coverage.statementStart(479)
        var last = first + idx.second - count
Coverage.statementStart(480)
        for (i in 1 until columns) {
Coverage.forLoopStart(481)
            columnidx = columnOrder[i].toInt()
Coverage.statementStart(482)
            column = data[columnidx]
Coverage.statementStart(483)
            idx = column.insertSorted(values[columnidx], first, last, comparator[columnOrder[i].toInt()], count)
Coverage.statementStart(484)
            if (idx.first > first) {
Coverage.ifStart(485)
                first = idx.first
Coverage.statementStart(486)
            }
Coverage.statementStart(487)
            if (last > first + idx.second - count) {
Coverage.ifStart(488)
                last = first + idx.second - count
Coverage.statementStart(489)
            }
Coverage.statementStart(490)
        }
Coverage.statementStart(491)
    }
}
class ValueComparatorFast : Comparator<Value> {
    override fun compare(a: Value, b: Value) = a.compareTo(b)
}
