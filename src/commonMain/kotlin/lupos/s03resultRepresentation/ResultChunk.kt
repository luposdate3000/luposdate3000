package lupos.s03resultRepresentation
import lupos.s00misc.Coverage
import lupos.s03resultRepresentation.ResultChunk
import lupos.s04arithmetikOperators.ResultVektorRaw
open class ResultChunk(resultSet: ResultSet, columns: Int) : ResultChunkBase(resultSet, columns) {
    companion object {
        operator fun invoke(resultSet: ResultSet): ResultChunk {
Coverage.funStart(340)
            val columns = resultSet.getVariableNames().size
Coverage.statementStart(341)
            if (columns == 0) {
Coverage.ifStart(342)
                return ResultChunkNoColumns(resultSet, columns)
            }
Coverage.statementStart(343)
            return ResultChunk(resultSet, columns)
        }
        fun removeFirst(root: ResultChunk): ResultChunk? {
Coverage.funStart(344)
            if (root.next == root) {
Coverage.ifStart(345)
                return null
            }
Coverage.statementStart(346)
            val res = root.next
Coverage.statementStart(347)
            res.prev = root.prev
Coverage.statementStart(348)
            root.prev.next = res
Coverage.statementStart(349)
            return res
        }
        fun split(root: ResultChunk, count: Int): ResultChunk {
Coverage.funStart(350)
            require(count > 0)
Coverage.statementStart(351)
            var other = root
Coverage.statementStart(352)
            for (i in 0 until count) {
Coverage.forLoopStart(353)
                other = other.next
Coverage.statementStart(354)
            }
Coverage.statementStart(355)
            val rootLast = other.prev
Coverage.statementStart(356)
            val otherLast = root.prev
Coverage.statementStart(357)
            root.prev = rootLast
Coverage.statementStart(358)
            rootLast.next = root
Coverage.statementStart(359)
            otherLast.next = other
Coverage.statementStart(360)
            other.prev = otherLast
Coverage.statementStart(361)
            return other
        }
        fun append(rootLast: ResultChunk, other: ResultChunk): ResultChunk {
Coverage.funStart(362)
            val root = rootLast.next
Coverage.statementStart(363)
            val otherLast = other.prev
Coverage.statementStart(364)
            rootLast.next = other
Coverage.statementStart(365)
            root.prev = otherLast
Coverage.statementStart(366)
            otherLast.next = root
Coverage.statementStart(367)
            other.prev = rootLast
Coverage.statementStart(368)
            return otherLast
        }
        fun copy(from: ResultChunk, target: ResultChunk, count: Int): ResultChunk {
Coverage.funStart(369)
            require(count > 0)
Coverage.statementStart(370)
            val resultSet = from.resultSet
Coverage.statementStart(371)
            val columns = from.columns
Coverage.statementStart(372)
            var targetLast = target
Coverage.statementStart(373)
            var available = targetLast.availableWrite()
Coverage.statementStart(374)
            if (available == 0) {
Coverage.ifStart(375)
                targetLast = append(targetLast, ResultChunk(resultSet, columns))
Coverage.statementStart(376)
                available = ResultVektor.capacity
Coverage.statementStart(377)
            }
Coverage.statementStart(378)
            var cnt = count
Coverage.statementStart(379)
            while (available < cnt) {
Coverage.whileLoopStart(380)
                targetLast.copy(from, available)
Coverage.statementStart(381)
                cnt -= available
Coverage.statementStart(382)
                targetLast = append(targetLast, ResultChunk(resultSet, columns))
Coverage.statementStart(383)
                available = ResultVektor.capacity
Coverage.statementStart(384)
            }
Coverage.statementStart(385)
            targetLast.copy(from, cnt)
Coverage.statementStart(386)
            return targetLast
        }
        fun sortHelper(comparator: Array<Comparator<Value>>, columnOrder: Array<Variable>, a: ResultChunk, b: ResultChunk, target: ResultChunk): ResultChunk {
Coverage.funStart(387)
            var targetLast = target
Coverage.statementStart(388)
            loop@ while (a.availableRead() > 0 && b.availableRead() > 0) {
Coverage.whileLoopStart(389)
                var cmp = 0
Coverage.statementStart(390)
                for (i in 0 until columnOrder.size) {
Coverage.forLoopStart(391)
                    val c = columnOrder[i].toInt()
Coverage.statementStart(392)
                    val vala = a.data[c].current()
Coverage.statementStart(393)
                    val valb = b.data[c].current()
Coverage.statementStart(394)
                    if (vala != valb) {
Coverage.ifStart(395)
                        cmp = comparator[c].compare(vala, valb)
Coverage.statementStart(396)
                        require(cmp != 0)
Coverage.statementStart(397)
                        if (cmp < 0) {
Coverage.ifStart(398)
                            var count = a.sameElements(Array(i + 1) { columnOrder[it] })
Coverage.statementStart(399)
                            targetLast = copy(a, targetLast, count)
Coverage.statementStart(400)
                        } else {
Coverage.ifStart(401)
                            var count = b.sameElements(Array(i + 1) { columnOrder[it] })
Coverage.statementStart(402)
                            targetLast = copy(b, targetLast, count)
Coverage.statementStart(403)
                        }
Coverage.statementStart(404)
                        continue@loop
                    }
Coverage.statementStart(405)
                }
Coverage.statementStart(406)
                if (cmp == 0) {
Coverage.ifStart(407)
                    val i = columnOrder[columnOrder.size - 1]
Coverage.statementStart(408)
                    var countA = a.sameElements()
Coverage.statementStart(409)
                    targetLast = copy(a, targetLast, countA)
Coverage.statementStart(410)
                    var countB = b.sameElements()
Coverage.statementStart(411)
                    targetLast = copy(b, targetLast, countB)
Coverage.statementStart(412)
                }
Coverage.statementStart(413)
            }
Coverage.statementStart(414)
            return targetLast
        }
        fun sort(comparator: Array<Comparator<Value>>, columnOrder: Array<Variable>, chunks: ResultChunk): ResultChunk {
Coverage.funStart(415)
            var chunkCount = 1
Coverage.statementStart(416)
            var tmp = chunks.next
Coverage.statementStart(417)
            while (tmp != chunks) {
Coverage.whileLoopStart(418)
                chunkCount++
Coverage.statementStart(419)
                tmp = tmp.next
Coverage.statementStart(420)
            }
Coverage.statementStart(421)
            if (chunkCount == 1) {
Coverage.ifStart(422)
                val resultSet = chunks.resultSet
Coverage.statementStart(423)
                val columns = chunks.columns
Coverage.statementStart(424)
                val res = ResultChunk(resultSet, columns)
Coverage.statementStart(425)
                var resLast = res
Coverage.statementStart(426)
                while (chunks.hasNext()) {
Coverage.whileLoopStart(427)
                    val same = chunks.sameElements()
Coverage.statementStart(428)
                    if (resLast.availableWrite() < 2) {
Coverage.ifStart(429)
                        resLast = append(resLast, ResultChunk(resultSet, columns))
Coverage.statementStart(430)
                    }
Coverage.statementStart(431)
                    resLast.internalInsertSorted(comparator, columnOrder, chunks.current(), same)
Coverage.statementStart(432)
                    chunks.skipPos(same)
Coverage.statementStart(433)
                }
Coverage.statementStart(434)
                if (res != res.next) {
Coverage.ifStart(435)
                    return sort(comparator, columnOrder, res)
                }
Coverage.statementStart(436)
                return res
            } else {
Coverage.ifStart(437)
                val half = chunkCount / 2
Coverage.statementStart(438)
                var a: ResultChunk? = sort(comparator, columnOrder, split(chunks, half))
Coverage.statementStart(439)
                var b: ResultChunk? = sort(comparator, columnOrder, chunks)
Coverage.statementStart(440)
                val resultSet = a!!.resultSet
Coverage.statementStart(441)
                val columns = a!!.columns
Coverage.statementStart(442)
                val res = ResultChunk(resultSet, columns)
Coverage.statementStart(443)
                var resLast = res
Coverage.statementStart(444)
                while (true) {
Coverage.whileLoopStart(445)
                    resLast = sortHelper(comparator, columnOrder, a!!, b!!, resLast)
Coverage.statementStart(446)
                    if (a.hasNext()) {
Coverage.ifStart(447)
                        b = removeFirst(b)
Coverage.statementStart(448)
                        if (b == null) {
Coverage.ifStart(449)
                            break
                        }
Coverage.statementStart(450)
                    } else {
Coverage.ifStart(451)
                        a = removeFirst(a)
Coverage.statementStart(452)
                        if (a == null) {
Coverage.ifStart(453)
                            break
                        }
Coverage.statementStart(454)
                    }
Coverage.statementStart(455)
                }
Coverage.statementStart(456)
                if (a != null) {
Coverage.ifStart(457)
                    var count = a!!.availableRead()
Coverage.statementStart(458)
                    if (count > 0) {
Coverage.ifStart(459)
                        resLast = copy(a, resLast, count)
Coverage.statementStart(460)
                    }
Coverage.statementStart(461)
                    a = removeFirst(a)
Coverage.statementStart(462)
                    if (a != null) {
Coverage.ifStart(463)
                        append(resLast, a)
Coverage.statementStart(464)
                    }
Coverage.statementStart(465)
                } else {
Coverage.ifStart(466)
                    var count = b!!.availableRead()
Coverage.statementStart(467)
                    if (count > 0) {
Coverage.ifStart(468)
                        resLast = copy(b!!, resLast, count)
Coverage.statementStart(469)
                    }
Coverage.statementStart(470)
                    b = removeFirst(b)
Coverage.statementStart(471)
                    if (b != null) {
Coverage.ifStart(472)
                        append(resLast, b!!)
Coverage.statementStart(473)
                    }
Coverage.statementStart(474)
                }
Coverage.statementStart(475)
                return res
            }
/*Coverage Unreachable*/
        }
    }
    var prev = this
    var next = this
    fun internalInsertSorted(comparator: Array<Comparator<Value>>, columnOrder: Array<Variable>, values: Array<Value>, count: Int = 1) {
Coverage.funStart(476)
        var columnidx = columnOrder[0].toInt()
Coverage.statementStart(477)
        var column = data[columnidx]
Coverage.statementStart(478)
        var idx = column.insertSorted(values[columnidx], comparator = comparator[columnOrder[0].toInt()], count = count)
Coverage.statementStart(479)
        var first = idx.first
Coverage.statementStart(480)
        var last = first + idx.second - count
Coverage.statementStart(481)
        for (i in 1 until columns) {
Coverage.forLoopStart(482)
            columnidx = columnOrder[i].toInt()
Coverage.statementStart(483)
            column = data[columnidx]
Coverage.statementStart(484)
            idx = column.insertSorted(values[columnidx], first, last, comparator[columnOrder[i].toInt()], count)
Coverage.statementStart(485)
            if (idx.first > first) {
Coverage.ifStart(486)
                first = idx.first
Coverage.statementStart(487)
            }
Coverage.statementStart(488)
            if (last > first + idx.second - count) {
Coverage.ifStart(489)
                last = first + idx.second - count
Coverage.statementStart(490)
            }
Coverage.statementStart(491)
        }
Coverage.statementStart(492)
    }
}
