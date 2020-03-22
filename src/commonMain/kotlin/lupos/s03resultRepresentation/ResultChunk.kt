package lupos.s03resultRepresentation
import lupos.s00misc.Coverage
import lupos.s03resultRepresentation.ResultChunk
import lupos.s04arithmetikOperators.ResultVektorRaw
open class ResultChunk(resultSet: ResultSet, columns: Int) : ResultChunkBase(resultSet, columns) {
    companion object {
        operator fun invoke(resultSet: ResultSet): ResultChunk {
Coverage.funStart(346)
            val columns = resultSet.getVariableNames().size
Coverage.statementStart(347)
            if (columns == 0) {
Coverage.ifStart(348)
                return ResultChunkNoColumns(resultSet, columns)
            }
Coverage.statementStart(349)
            return ResultChunk(resultSet, columns)
        }
        fun removeFirst(root: ResultChunk): ResultChunk? {
Coverage.funStart(350)
            if (root.next == root) {
Coverage.ifStart(351)
                return null
            }
Coverage.statementStart(352)
            val res = root.next
Coverage.statementStart(353)
            res.prev = root.prev
Coverage.statementStart(354)
            root.prev.next = res
Coverage.statementStart(355)
            return res
        }
        fun split(root: ResultChunk, count: Int): ResultChunk {
Coverage.funStart(356)
            require(count > 0)
Coverage.statementStart(357)
            var other = root
Coverage.statementStart(358)
            for (i in 0 until count) {
Coverage.forLoopStart(359)
                other = other.next
Coverage.statementStart(360)
            }
Coverage.statementStart(361)
            val rootLast = other.prev
Coverage.statementStart(362)
            val otherLast = root.prev
Coverage.statementStart(363)
            root.prev = rootLast
Coverage.statementStart(364)
            rootLast.next = root
Coverage.statementStart(365)
            otherLast.next = other
Coverage.statementStart(366)
            other.prev = otherLast
Coverage.statementStart(367)
            return other
        }
        fun append(rootLast: ResultChunk, other: ResultChunk): ResultChunk {
Coverage.funStart(368)
            val root = rootLast.next
Coverage.statementStart(369)
            val otherLast = other.prev
Coverage.statementStart(370)
            rootLast.next = other
Coverage.statementStart(371)
            root.prev = otherLast
Coverage.statementStart(372)
            otherLast.next = root
Coverage.statementStart(373)
            other.prev = rootLast
Coverage.statementStart(374)
            return otherLast
        }
        fun copy(from: ResultChunk, target: ResultChunk, count: Int): ResultChunk {
Coverage.funStart(375)
            require(count > 0)
Coverage.statementStart(376)
            val resultSet = from.resultSet
Coverage.statementStart(377)
            val columns = from.columns
Coverage.statementStart(378)
            var targetLast = target
Coverage.statementStart(379)
            var available = targetLast.availableWrite()
Coverage.statementStart(380)
            if (available == 0) {
Coverage.ifStart(381)
                targetLast = append(targetLast, ResultChunk(resultSet, columns))
Coverage.statementStart(382)
                available = ResultVektor.capacity
Coverage.statementStart(383)
            }
Coverage.statementStart(384)
            var cnt = count
Coverage.statementStart(385)
            while (available < cnt) {
Coverage.whileLoopStart(386)
                targetLast.copy(from, available)
Coverage.statementStart(387)
                cnt -= available
Coverage.statementStart(388)
                targetLast = append(targetLast, ResultChunk(resultSet, columns))
Coverage.statementStart(389)
                available = ResultVektor.capacity
Coverage.statementStart(390)
            }
Coverage.statementStart(391)
            targetLast.copy(from, cnt)
Coverage.statementStart(392)
            return targetLast
        }
        fun sortHelper(comparator: Array<Comparator<Value>>, columnOrder: Array<Variable>, a: ResultChunk, b: ResultChunk, target: ResultChunk): ResultChunk {
Coverage.funStart(393)
            var targetLast = target
Coverage.statementStart(394)
            loop@ while (a.availableRead() > 0 && b.availableRead() > 0) {
Coverage.whileLoopStart(395)
                var cmp = 0
Coverage.statementStart(396)
                for (i in 0 until columnOrder.size) {
Coverage.forLoopStart(397)
                    val c = columnOrder[i].toInt()
Coverage.statementStart(398)
                    val vala = a.data[c].current()
Coverage.statementStart(399)
                    val valb = b.data[c].current()
Coverage.statementStart(400)
                    if (vala != valb) {
Coverage.ifStart(401)
                        cmp = comparator[c].compare(vala, valb)
Coverage.statementStart(402)
                        require(cmp != 0)
Coverage.statementStart(403)
                        if (cmp < 0) {
Coverage.ifStart(404)
                            var count = a.sameElements(Array(i + 1) { columnOrder[it] })
Coverage.statementStart(405)
                            targetLast = copy(a, targetLast, count)
Coverage.statementStart(406)
                        } else {
Coverage.ifStart(407)
                            var count = b.sameElements(Array(i + 1) { columnOrder[it] })
Coverage.statementStart(408)
                            targetLast = copy(b, targetLast, count)
Coverage.statementStart(409)
                        }
Coverage.statementStart(410)
                        continue@loop
                    }
Coverage.statementStart(411)
                }
Coverage.statementStart(412)
                if (cmp == 0) {
Coverage.ifStart(413)
                    val i = columnOrder[columnOrder.size - 1]
Coverage.statementStart(414)
                    var countA = a.sameElements()
Coverage.statementStart(415)
                    targetLast = copy(a, targetLast, countA)
Coverage.statementStart(416)
                    var countB = b.sameElements()
Coverage.statementStart(417)
                    targetLast = copy(b, targetLast, countB)
Coverage.statementStart(418)
                }
Coverage.statementStart(419)
            }
Coverage.statementStart(420)
            return targetLast
        }
        fun sort(comparator: Array<Comparator<Value>>, columnOrder: Array<Variable>, chunks: ResultChunk): ResultChunk {
Coverage.funStart(421)
            var chunkCount = 1
Coverage.statementStart(422)
            var tmp = chunks.next
Coverage.statementStart(423)
            while (tmp != chunks) {
Coverage.whileLoopStart(424)
                chunkCount++
Coverage.statementStart(425)
                tmp = tmp.next
Coverage.statementStart(426)
            }
Coverage.statementStart(427)
            if (chunkCount == 1) {
Coverage.ifStart(428)
                val resultSet = chunks.resultSet
Coverage.statementStart(429)
                val columns = chunks.columns
Coverage.statementStart(430)
                val res = ResultChunk(resultSet, columns)
Coverage.statementStart(431)
                var resLast = res
Coverage.statementStart(432)
                while (chunks.hasNext()) {
Coverage.whileLoopStart(433)
                    val same = chunks.sameElements()
Coverage.statementStart(434)
                    if (resLast.availableWrite() < 2) {
Coverage.ifStart(435)
                        resLast = append(resLast, ResultChunk(resultSet, columns))
Coverage.statementStart(436)
                    }
Coverage.statementStart(437)
                    resLast.internalInsertSorted(comparator, columnOrder, chunks.current(), same)
Coverage.statementStart(438)
                    chunks.skipPos(same)
Coverage.statementStart(439)
                }
Coverage.statementStart(440)
                if (res != res.next) {
Coverage.ifStart(441)
                    return sort(comparator, columnOrder, res)
                }
Coverage.statementStart(442)
                return res
            } else {
Coverage.ifStart(443)
                val half = chunkCount / 2
Coverage.statementStart(444)
                var a: ResultChunk? = sort(comparator, columnOrder, split(chunks, half))
Coverage.statementStart(445)
                var b: ResultChunk? = sort(comparator, columnOrder, chunks)
Coverage.statementStart(446)
                val resultSet = a!!.resultSet
Coverage.statementStart(447)
                val columns = a!!.columns
Coverage.statementStart(448)
                val res = ResultChunk(resultSet, columns)
Coverage.statementStart(449)
                var resLast = res
Coverage.statementStart(450)
                while (true) {
Coverage.whileLoopStart(451)
                    resLast = sortHelper(comparator, columnOrder, a!!, b!!, resLast)
Coverage.statementStart(452)
                    if (a.hasNext()) {
Coverage.ifStart(453)
                        b = removeFirst(b)
Coverage.statementStart(454)
                        if (b == null) {
Coverage.ifStart(455)
                            break
                        }
Coverage.statementStart(456)
                    } else {
Coverage.ifStart(457)
                        a = removeFirst(a)
Coverage.statementStart(458)
                        if (a == null) {
Coverage.ifStart(459)
                            break
                        }
Coverage.statementStart(460)
                    }
Coverage.statementStart(461)
                }
Coverage.statementStart(462)
                if (a != null) {
Coverage.ifStart(463)
                    var count = a!!.availableRead()
Coverage.statementStart(464)
                    if (count > 0) {
Coverage.ifStart(465)
                        resLast = copy(a, resLast, count)
Coverage.statementStart(466)
                    }
Coverage.statementStart(467)
                    a = removeFirst(a)
Coverage.statementStart(468)
                    if (a != null) {
Coverage.ifStart(469)
                        append(resLast, a)
Coverage.statementStart(470)
                    }
Coverage.statementStart(471)
                } else {
Coverage.ifStart(472)
                    var count = b!!.availableRead()
Coverage.statementStart(473)
                    if (count > 0) {
Coverage.ifStart(474)
                        resLast = copy(b!!, resLast, count)
Coverage.statementStart(475)
                    }
Coverage.statementStart(476)
                    b = removeFirst(b)
Coverage.statementStart(477)
                    if (b != null) {
Coverage.ifStart(478)
                        append(resLast, b!!)
Coverage.statementStart(479)
                    }
Coverage.statementStart(480)
                }
Coverage.statementStart(481)
                return res
            }
/*Coverage Unreachable*/
        }
    }
    var prev = this
    var next = this
    fun internalInsertSorted(comparator: Array<Comparator<Value>>, columnOrder: Array<Variable>, values: Array<Value>, count: Int = 1) {
Coverage.funStart(483)
        var columnidx = columnOrder[0].toInt()
Coverage.statementStart(484)
        var column = data[columnidx]
Coverage.statementStart(485)
        var idx = column.insertSorted(values[columnidx], comparator = comparator[columnOrder[0].toInt()], count = count)
Coverage.statementStart(486)
        var first = idx.first
Coverage.statementStart(487)
        var last = first + idx.second - count
Coverage.statementStart(488)
        for (i in 1 until columns) {
Coverage.forLoopStart(489)
            columnidx = columnOrder[i].toInt()
Coverage.statementStart(490)
            column = data[columnidx]
Coverage.statementStart(491)
            idx = column.insertSorted(values[columnidx], first, last, comparator[columnOrder[i].toInt()], count)
Coverage.statementStart(492)
            if (idx.first > first) {
Coverage.ifStart(493)
                first = idx.first
Coverage.statementStart(494)
            }
Coverage.statementStart(495)
            if (last > first + idx.second - count) {
Coverage.ifStart(496)
                last = first + idx.second - count
Coverage.statementStart(497)
            }
Coverage.statementStart(498)
        }
Coverage.statementStart(499)
    }
}
class ValueComparatorFast : Comparator<Value> {
    override fun compare(a: Value, b: Value) = a.compareTo(b)
}
