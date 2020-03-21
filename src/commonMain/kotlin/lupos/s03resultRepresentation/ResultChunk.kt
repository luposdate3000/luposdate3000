package lupos.s03resultRepresentation

import lupos.s00misc.Coverage
import lupos.s03resultRepresentation.ResultChunk
import lupos.s04arithmetikOperators.ResultVektorRaw

open class ResultChunk(resultSet: ResultSet, columns: Int) : ResultChunkBase(resultSet, columns) {
    companion object {
        operator fun invoke(resultSet: ResultSet): ResultChunk {
            Coverage.funStart(118)
            val columns = resultSet.getVariableNames().size
            if (columns == 0) {
                Coverage.ifStart(119)
                return ResultChunkNoColumns(resultSet, columns)
            }
            return ResultChunk(resultSet, columns)
        }

        fun removeFirst(root: ResultChunk): ResultChunk? {
            Coverage.funStart(120)
            if (root.next == root) {
                Coverage.ifStart(121)
                return null
            }
            val res = root.next
            res.prev = root.prev
            root.prev.next = res
            return res
        }

        fun split(root: ResultChunk, count: Int): ResultChunk {
            Coverage.funStart(122)
            require(count > 0)
            var other = root
            for (i in 0 until count) {
                Coverage.forLoopStart(123)
                other = other.next
            }
            val rootLast = other.prev
            val otherLast = root.prev
            root.prev = rootLast
            rootLast.next = root
            otherLast.next = other
            other.prev = otherLast
            return other
        }

        fun append(rootLast: ResultChunk, other: ResultChunk): ResultChunk {
            Coverage.funStart(124)
            val root = rootLast.next
            val otherLast = other.prev
            rootLast.next = other
            root.prev = otherLast
            otherLast.next = root
            other.prev = rootLast
            return otherLast
        }

        fun copy(from: ResultChunk, target: ResultChunk, count: Int): ResultChunk {
            Coverage.funStart(125)
            require(count > 0)
            val resultSet = from.resultSet
            val columns = from.columns
            var targetLast = target
            var available = targetLast.availableWrite()
            if (available == 0) {
                Coverage.ifStart(126)
                targetLast = append(targetLast, ResultChunk(resultSet, columns))
                available = ResultVektor.capacity
            }
            var cnt = count
            while (available < cnt) {
                Coverage.whileLoopStart(127)
                targetLast.copy(from, available)
                cnt -= available
                targetLast = append(targetLast, ResultChunk(resultSet, columns))
                available = ResultVektor.capacity
            }
            targetLast.copy(from, cnt)
            return targetLast
        }

        fun sortHelper(comparator: Array<Comparator<Value>>, columnOrder: Array<Variable>, a: ResultChunk, b: ResultChunk, target: ResultChunk): ResultChunk {
            Coverage.funStart(128)
            var targetLast = target
            loop@ while (a.availableRead() > 0 && b.availableRead() > 0) {
                Coverage.whileLoopStart(129)
                var cmp = 0
                for (i in columnOrder) {
                    Coverage.forLoopStart(130)
                    val vala = a.data[i.toInt()].current()
                    val valb = b.data[i.toInt()].current()
                    if (vala != valb) {
                        Coverage.ifStart(131)
                        cmp = comparator[i.toInt()].compare(vala, valb)
                        require(cmp != 0)
                        if (cmp < 0) {
                            Coverage.ifStart(132)
                            var count = a.data[i.toInt()].sameElements()
                            targetLast = copy(a, targetLast, count)
                        } else {
                            Coverage.ifStart(133)
                            var count = b.data[i.toInt()].sameElements()
                            targetLast = copy(b, targetLast, count)
                        }
                        continue@loop
                    }
                }
                if (cmp == 0) {
                    Coverage.ifStart(134)
                    val i = columnOrder[columnOrder.size - 1]
                    var countA = a.data[i.toInt()].sameElements()
                    targetLast = copy(a, targetLast, countA)
                    var countB = b.data[i.toInt()].sameElements()
                    targetLast = copy(b, targetLast, countB)
                }
            }
            return targetLast
        }

        fun sort(comparator: Array<Comparator<Value>>, columnOrder: Array<Variable>, chunks: ResultChunk): ResultChunk {
            Coverage.funStart(135)
            var chunkCount = 1
            var tmp = chunks.next
            while (tmp != chunks) {
                Coverage.whileLoopStart(136)
                chunkCount++
                tmp = tmp.next
            }
            if (chunkCount == 1) {
                Coverage.ifStart(137)
                val resultSet = chunks.resultSet
                val columns = chunks.columns
                val res = ResultChunk(resultSet, columns)
                var resLast = res
                while (chunks.hasNext()) {
                    Coverage.whileLoopStart(138)
                    val same = chunks.sameElements()
                    if (resLast.availableWrite() < 2) {
                        Coverage.ifStart(139)
                        resLast = append(resLast, ResultChunk(resultSet, columns))
                    }
                    resLast.internalInsertSorted(comparator, columnOrder, chunks.current(), same)
                    chunks.skipPos(same)
                }
                if (res != res.next) {
                    Coverage.ifStart(140)
                    return sort(comparator, columnOrder, res)
                }
                return res
            } else {
                Coverage.ifStart(141)
                val half = chunkCount / 2
                var a: ResultChunk? = sort(comparator, columnOrder, split(chunks, half))
                var b: ResultChunk? = sort(comparator, columnOrder, chunks)
                val resultSet = a!!.resultSet
                val columns = a!!.columns
                val res = ResultChunk(resultSet, columns)
                var resLast = res
                while (true) {
                    Coverage.whileLoopStart(142)
                    resLast = sortHelper(comparator, columnOrder, a!!, b!!, resLast)
                    if (a.hasNext()) {
                        Coverage.ifStart(143)
                        b = removeFirst(b)
                        if (b == null) {
                            Coverage.ifStart(144)
                            break
                        }
                    } else {
                        Coverage.ifStart(145)
                        a = removeFirst(a)
                        if (a == null) {
                            Coverage.ifStart(146)
                            break
                        }
                    }
                }
                if (a != null) {
                    Coverage.ifStart(147)
                    var count = a!!.availableRead()
                    if (count > 0) {
                        Coverage.ifStart(148)
                        resLast = copy(a, resLast, count)
                    }
                    a = removeFirst(a)
                    if (a != null) {
                        Coverage.ifStart(149)
                        append(resLast, a)
                    }
                } else {
                    Coverage.ifStart(150)
                    var count = b!!.availableRead()
                    if (count > 0) {
                        Coverage.ifStart(151)
                        resLast = copy(b!!, resLast, count)
                    }
                    b = removeFirst(b)
                    if (b != null) {
                        Coverage.ifStart(152)
                        append(resLast, b!!)
                    }
                }
                return res
            }
        }
    }

    var prev = this
    var next = this
    fun chunkCount(): Int {
        Coverage.funStart(153)
        var res = 1
        var tmp = this.next
        while (tmp != this) {
            Coverage.whileLoopStart(154)
            res++
            tmp = tmp.next
        }
        return res
    }

    fun internalInsertSorted(comparator: Array<Comparator<Value>>, columnOrder: Array<Variable>, values: Array<Value>, count: Int = 1) {
        Coverage.funStart(155)
        var columnidx = columnOrder[0].toInt()
        var column = data[columnidx]
        var idx = column.insertSorted(values[columnidx], comparator = comparator[columnOrder[0].toInt()], count = count)
        var first = idx.first
        var last = first + idx.second - count
        for (i in 1 until columns) {
            Coverage.forLoopStart(156)
            columnidx = columnOrder[i].toInt()
            column = data[columnidx]
            idx = column.insertSorted(values[columnidx], first, last, comparator[columnOrder[i].toInt()], count)
            if (idx.first > first) {
                Coverage.ifStart(157)
                first = idx.first
            }
            if (last > first + idx.second - count) {
                Coverage.ifStart(158)
                last = first + idx.second - count
            }
        }
    }
}

class ValueComparatorFast : Comparator<Value> {
    override fun compare(a: Value, b: Value) = a.compareTo(b)
}
