package lupos.s03resultRepresentation

import lupos.s00misc.Coverage
import lupos.s03resultRepresentation.ResultChunk
import lupos.s04arithmetikOperators.ResultVektorRaw

open class ResultChunk(resultSet: ResultSet, columns: Int) : ResultChunkBase(resultSet, columns) {
    companion object {
        operator fun invoke(resultSet: ResultSet): ResultChunk {
            Coverage.funStart(351)
            val columns = resultSet.getVariableNames().size
            Coverage.statementStart(352)
            if (columns == 0) {
                Coverage.ifStart(353)
                return ResultChunkNoColumns(resultSet, columns)
            }
            Coverage.statementStart(354)
            return ResultChunk(resultSet, columns)
        }

        fun removeFirst(root: ResultChunk): ResultChunk? {
            Coverage.funStart(355)
            if (root.next == root) {
                Coverage.ifStart(356)
                return null
            }
            Coverage.statementStart(357)
            val res = root.next
            Coverage.statementStart(358)
            res.prev = root.prev
            Coverage.statementStart(359)
            root.prev.next = res
            Coverage.statementStart(360)
            return res
        }

        fun split(root: ResultChunk, count: Int): ResultChunk {
            Coverage.funStart(361)
            require(count > 0)
            Coverage.statementStart(362)
            var other = root
            Coverage.statementStart(363)
            for (i in 0 until count) {
                Coverage.forLoopStart(364)
                other = other.next
                Coverage.statementStart(365)
            }
            Coverage.statementStart(366)
            val rootLast = other.prev
            Coverage.statementStart(367)
            val otherLast = root.prev
            Coverage.statementStart(368)
            root.prev = rootLast
            Coverage.statementStart(369)
            rootLast.next = root
            Coverage.statementStart(370)
            otherLast.next = other
            Coverage.statementStart(371)
            other.prev = otherLast
            Coverage.statementStart(372)
            return other
        }

        fun append(rootLast: ResultChunk, other: ResultChunk): ResultChunk {
            Coverage.funStart(373)
            val root = rootLast.next
            Coverage.statementStart(374)
            val otherLast = other.prev
            Coverage.statementStart(375)
            rootLast.next = other
            Coverage.statementStart(376)
            root.prev = otherLast
            Coverage.statementStart(377)
            otherLast.next = root
            Coverage.statementStart(378)
            other.prev = rootLast
            Coverage.statementStart(379)
            return otherLast
        }

        fun copy(from: ResultChunk, target: ResultChunk, count: Int): ResultChunk {
            Coverage.funStart(380)
            require(count > 0)
            Coverage.statementStart(381)
            val resultSet = from.resultSet
            Coverage.statementStart(382)
            val columns = from.columns
            Coverage.statementStart(383)
            var targetLast = target
            Coverage.statementStart(384)
            var available = targetLast.availableWrite()
            Coverage.statementStart(385)
            if (available == 0) {
                Coverage.ifStart(386)
                targetLast = append(targetLast, ResultChunk(resultSet, columns))
                Coverage.statementStart(387)
                available = ResultVektor.capacity
                Coverage.statementStart(388)
            }
            Coverage.statementStart(389)
            var cnt = count
            Coverage.statementStart(390)
            while (available < cnt) {
                Coverage.whileLoopStart(391)
                targetLast.copy(from, available)
                Coverage.statementStart(392)
                cnt -= available
                Coverage.statementStart(393)
                targetLast = append(targetLast, ResultChunk(resultSet, columns))
                Coverage.statementStart(394)
                available = ResultVektor.capacity
                Coverage.statementStart(395)
            }
            Coverage.statementStart(396)
            targetLast.copy(from, cnt)
            Coverage.statementStart(397)
            return targetLast
        }

        fun sortHelper(comparator: Array<Comparator<Value>>, columnOrder: Array<Variable>, a: ResultChunk, b: ResultChunk, target: ResultChunk): ResultChunk {
            Coverage.funStart(398)
            var targetLast = target
            Coverage.statementStart(399)
            loop@ while (a.availableRead() > 0 && b.availableRead() > 0) {
                Coverage.whileLoopStart(400)
                var cmp = 0
                Coverage.statementStart(401)
                for (i in 0 until columnOrder.size) {
                    Coverage.forLoopStart(402)
                    val c = columnOrder[i].toInt()
                    Coverage.statementStart(403)
                    val vala = a.data[c].current()
                    Coverage.statementStart(404)
                    val valb = b.data[c].current()
                    Coverage.statementStart(405)
                    if (vala != valb) {
                        Coverage.ifStart(406)
                        cmp = comparator[c].compare(vala, valb)
                        Coverage.statementStart(407)
                        require(cmp != 0)
                        Coverage.statementStart(408)
                        if (cmp < 0) {
                            Coverage.ifStart(409)
                            var count = a.sameElements(Array(i + 1) { columnOrder[it] })
                            Coverage.statementStart(410)
                            targetLast = copy(a, targetLast, count)
                            Coverage.statementStart(411)
                        } else {
                            Coverage.ifStart(412)
                            var count = b.sameElements(Array(i + 1) { columnOrder[it] })
                            Coverage.statementStart(413)
                            targetLast = copy(b, targetLast, count)
                            Coverage.statementStart(414)
                        }
                        Coverage.statementStart(415)
                        continue@loop
                    }
                    Coverage.statementStart(416)
                }
                Coverage.statementStart(417)
                if (cmp == 0) {
                    Coverage.ifStart(418)
                    val i = columnOrder[columnOrder.size - 1]
                    Coverage.statementStart(419)
                    var countA = a.sameElements()
                    Coverage.statementStart(420)
                    targetLast = copy(a, targetLast, countA)
                    Coverage.statementStart(421)
                    var countB = b.sameElements()
                    Coverage.statementStart(422)
                    targetLast = copy(b, targetLast, countB)
                    Coverage.statementStart(423)
                }
                Coverage.statementStart(424)
            }
            Coverage.statementStart(425)
            return targetLast
        }

        fun sort(comparator: Array<Comparator<Value>>, columnOrder: Array<Variable>, chunks: ResultChunk): ResultChunk {
            Coverage.funStart(426)
            var chunkCount = 1
            Coverage.statementStart(427)
            var tmp = chunks.next
            Coverage.statementStart(428)
            while (tmp != chunks) {
                Coverage.whileLoopStart(429)
                chunkCount++
                Coverage.statementStart(430)
                tmp = tmp.next
                Coverage.statementStart(431)
            }
            Coverage.statementStart(432)
            if (chunkCount == 1) {
                Coverage.ifStart(433)
                val resultSet = chunks.resultSet
                Coverage.statementStart(434)
                val columns = chunks.columns
                Coverage.statementStart(435)
                val res = ResultChunk(resultSet, columns)
                Coverage.statementStart(436)
                var resLast = res
                Coverage.statementStart(437)
                while (chunks.hasNext()) {
                    Coverage.whileLoopStart(438)
                    val same = chunks.sameElements()
                    Coverage.statementStart(439)
                    if (resLast.availableWrite() < 2) {
                        Coverage.ifStart(440)
                        resLast = append(resLast, ResultChunk(resultSet, columns))
                        Coverage.statementStart(441)
                    }
                    Coverage.statementStart(442)
                    resLast.internalInsertSorted(comparator, columnOrder, chunks.current(), same)
                    Coverage.statementStart(443)
                    chunks.skipPos(same)
                    Coverage.statementStart(444)
                }
                Coverage.statementStart(445)
                if (res != res.next) {
                    Coverage.ifStart(446)
                    return sort(comparator, columnOrder, res)
                }
                Coverage.statementStart(447)
                return res
            } else {
                Coverage.ifStart(448)
                val half = chunkCount / 2
                Coverage.statementStart(449)
                var a: ResultChunk? = sort(comparator, columnOrder, split(chunks, half))
                Coverage.statementStart(450)
                var b: ResultChunk? = sort(comparator, columnOrder, chunks)
                Coverage.statementStart(451)
                val resultSet = a!!.resultSet
                Coverage.statementStart(452)
                val columns = a!!.columns
                Coverage.statementStart(453)
                val res = ResultChunk(resultSet, columns)
                Coverage.statementStart(454)
                var resLast = res
                Coverage.statementStart(455)
                while (true) {
                    Coverage.whileLoopStart(456)
                    resLast = sortHelper(comparator, columnOrder, a!!, b!!, resLast)
                    Coverage.statementStart(457)
                    if (a.hasNext()) {
                        Coverage.ifStart(458)
                        b = removeFirst(b)
                        Coverage.statementStart(459)
                        if (b == null) {
                            Coverage.ifStart(460)
                            break
                        }
                        Coverage.statementStart(461)
                    } else {
                        Coverage.ifStart(462)
                        a = removeFirst(a)
                        Coverage.statementStart(463)
                        if (a == null) {
                            Coverage.ifStart(464)
                            break
                        }
                        Coverage.statementStart(465)
                    }
                    Coverage.statementStart(466)
                }
                Coverage.statementStart(467)
                if (a != null) {
                    Coverage.ifStart(468)
                    var count = a!!.availableRead()
                    Coverage.statementStart(469)
                    if (count > 0) {
                        Coverage.ifStart(470)
                        resLast = copy(a, resLast, count)
                        Coverage.statementStart(471)
                    }
                    Coverage.statementStart(472)
                    a = removeFirst(a)
                    Coverage.statementStart(473)
                    if (a != null) {
                        Coverage.ifStart(474)
                        append(resLast, a)
                        Coverage.statementStart(475)
                    }
                    Coverage.statementStart(476)
                } else {
                    Coverage.ifStart(477)
                    var count = b!!.availableRead()
                    Coverage.statementStart(478)
                    if (count > 0) {
                        Coverage.ifStart(479)
                        resLast = copy(b!!, resLast, count)
                        Coverage.statementStart(480)
                    }
                    Coverage.statementStart(481)
                    b = removeFirst(b)
                    Coverage.statementStart(482)
                    if (b != null) {
                        Coverage.ifStart(483)
                        append(resLast, b!!)
                        Coverage.statementStart(484)
                    }
                    Coverage.statementStart(485)
                }
                Coverage.statementStart(486)
                return res
            }
/*Coverage Unreachable*/
            Coverage.statementStart(488)
        }
    }

    var prev = this
    var next = this
    fun internalInsertSorted(comparator: Array<Comparator<Value>>, columnOrder: Array<Variable>, values: Array<Value>, count: Int = 1) {
        Coverage.funStart(489)
        var columnidx = columnOrder[0].toInt()
        Coverage.statementStart(490)
        var column = data[columnidx]
        Coverage.statementStart(491)
        var idx = column.insertSorted(values[columnidx], comparator = comparator[columnOrder[0].toInt()], count = count)
        Coverage.statementStart(492)
        var first = idx.first
        Coverage.statementStart(493)
        var last = first + idx.second - count
        Coverage.statementStart(494)
        for (i in 1 until columns) {
            Coverage.forLoopStart(495)
            columnidx = columnOrder[i].toInt()
            Coverage.statementStart(496)
            column = data[columnidx]
            Coverage.statementStart(497)
            idx = column.insertSorted(values[columnidx], first, last, comparator[columnOrder[i].toInt()], count)
            Coverage.statementStart(498)
            if (idx.first > first) {
                Coverage.ifStart(499)
                first = idx.first
                Coverage.statementStart(500)
            }
            Coverage.statementStart(501)
            if (last > first + idx.second - count) {
                Coverage.ifStart(502)
                last = first + idx.second - count
                Coverage.statementStart(503)
            }
            Coverage.statementStart(504)
        }
        Coverage.statementStart(505)
    }
}

class ValueComparatorFast : Comparator<Value> {
    override fun compare(a: Value, b: Value) = a.compareTo(b)
}
