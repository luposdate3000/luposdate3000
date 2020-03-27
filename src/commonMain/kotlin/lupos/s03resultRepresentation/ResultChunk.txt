package lupos.s03resultRepresentation

import lupos.s00misc.Coverage
import lupos.s00misc.SanityCheck
import lupos.s03resultRepresentation.ResultChunk
import lupos.s04arithmetikOperators.ResultVektorRaw

open class ResultChunk(resultSet: ResultSet, columns: Int) : ResultChunkBase(resultSet, columns) {
    companion object {
        operator fun invoke(resultSet: ResultSet): ResultChunk {
            val columns = resultSet.getVariableNames().size
            if (columns == 0) {
                return ResultChunkNoColumns(resultSet, columns)
            }
            return ResultChunk(resultSet, columns)
        }

        fun removeFirst(root: ResultChunk): ResultChunk? {
            if (root.next == root) {
                return null
            }
            val res = root.next
            res.prev = root.prev
            root.prev.next = res
            root.prev = root
            root.next = root
            return res
        }

        fun split(root: ResultChunk, count: Int): ResultChunk {
            SanityCheck.check { count > 0 }
            var other = root
            for (i in 0 until count) {
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
            val root = rootLast.next
            val otherLast = other.prev
            rootLast.next = other
            root.prev = otherLast
            otherLast.next = root
            other.prev = rootLast
            return otherLast
        }

        fun copy(from: ResultChunk, target: ResultChunk, count: Int): ResultChunk {
            SanityCheck.check { count > 0 }
            val resultSet = from.resultSet
            val columns = from.columns
            var targetLast = target
            var available = targetLast.availableWrite()
            if (available == 0) {
                targetLast = append(targetLast, ResultChunk(resultSet, columns))
                available = ResultVektor.capacity
            }
            var cnt = count
            while (available < cnt) {
                targetLast.copy(from, available)
                cnt -= available
                targetLast = append(targetLast, ResultChunk(resultSet, columns))
                available = ResultVektor.capacity
            }
            targetLast.copy(from, cnt)
            return targetLast
        }

        fun internalSort(comparator: Array<Comparator<Value>>, columnOrder: Array<Variable>, a: ResultChunk, b: ResultChunk, target: ResultChunk): ResultChunk {
            var targetLast = target
            loop@ while (a.availableRead() > 0 && b.availableRead() > 0) {
                var cmp = 0
                for (i in 0 until columnOrder.size) {
                    val c = columnOrder[i].toInt()
                    val vala = a.data[c].current()
                    val valb = b.data[c].current()
                    if (vala != valb) {
                        cmp = comparator[c].compare(vala, valb)
                        if (cmp < 0) {
                            var count = a.sameElements(Array(i + 1) { columnOrder[it] })
                            targetLast = copy(a, targetLast, count)
                        } else {
                            var count = b.sameElements(Array(i + 1) { columnOrder[it] })
                            targetLast = copy(b, targetLast, count)
                        }
                        continue@loop
                    }
                }
                if (cmp == 0) {
                    val i = columnOrder[columnOrder.size - 1]
                    var countA = a.sameElements()
                    var countB = b.sameElements()
                    val row = a.current()
                    a.skipPos(countA)
                    b.skipPos(countB)
                    if (targetLast.availableWrite() == 0)
                        targetLast = append(targetLast, ResultChunk(target.resultSet, target.columns))
                    targetLast.append(row, countA + countB)
                }
            }
            return targetLast
        }

        fun sort(comparator: Array<Comparator<Value>>, columnOrder: Array<Variable>, chunks: ResultChunk, distinct: Boolean = false): ResultChunk {
            var chunkCount = 1
            var tmp = chunks.next
            while (tmp != chunks) {
                chunkCount++
                tmp = tmp.next
            }
            if (chunkCount == 1) {
                val resultSet = chunks.resultSet
                val columns = chunks.columns
                val res = ResultChunk(resultSet, columns)
                var resLast = res
                while (chunks.hasNext()) {
                    val same = chunks.sameElements()
                    SanityCheck.check { same > 0 }
                    if (resLast.availableWrite() <= 2) {
                        resLast = append(resLast, ResultChunk(resultSet, columns))
                    }
                    resLast.internalInsertSorted(comparator, columnOrder, chunks.current(), same)
                    chunks.skipPos(same)
                }
                if (res != res.next) {
                    return sort(comparator, columnOrder, res)
                }
                return res
            } else {
                val half = chunkCount / 2
                var a: ResultChunk? = sort(comparator, columnOrder, split(chunks, half))
                var b: ResultChunk? = sort(comparator, columnOrder, chunks)
                val resultSet = a!!.resultSet
                val columns = a!!.columns
                val res = ResultChunk(resultSet, columns)
                var resLast = res
                while (true) {
                    resLast = internalSort(comparator, columnOrder, a!!, b!!, resLast)
                    if (a.hasNext()) {
                        b = removeFirst(b)
                        if (b == null) {
                            break
                        }
                    } else {
                        a = removeFirst(a)
                        if (a == null) {
                            break
                        }
                    }
                }
                if (a != null) {
                    var count = a!!.availableRead()
                    if (count > 0) {
                        resLast = copy(a, resLast, count)
                    }
                    a = removeFirst(a)
                    if (a != null) {
                        append(resLast, a)
                    }
                } else {
                    var count = b!!.availableRead()
                    if (count > 0) {
                        resLast = copy(b!!, resLast, count)
                    }
                    b = removeFirst(b)
                    if (b != null) {
                        append(resLast, b!!)
                    }
                }
                return res
            }
/*Coverage Unreachable*/
        }
    }

    var prev = this
    var next = this
    fun internalInsertSorted(comparator: Array<Comparator<Value>>, columnOrder: Array<Variable>, values: Array<Value>, count: Int = 1, distinct: Boolean = false) {
        val indices = Array(columnOrder.size) { 0 }
        var columnidx = columnOrder[0].toInt()
        var column = data[columnidx]
        var idx = column.insertSorted(values[columnidx], comparator = comparator[columnOrder[0].toInt()], count = count)
        if (distinct)
            indices[columnidx] = idx.second
        var first = idx.first
        var idxcount = column.data[idx.second].count
        var last = first + idxcount - count
        for (i in 1 until columns) {
            columnidx = columnOrder[i].toInt()
            column = data[columnidx]
            idx = column.insertSorted(values[columnidx], first, last, comparator[columnOrder[i].toInt()], count)
            if (distinct)
                indices[columnidx] = idx.second
            idxcount = column.data[idx.second].count
            if (idx.first > first) {
                first = idx.first
            }
            if (last > first + idxcount - count) {
                last = first + idxcount - count
            }
        }
        if (distinct) {
            require(last - first >= 0)
            require(last - first <= 1)
            if (last - first == 1) {
                for (i in 0 until columns) {
                    data[i].data[indices[i]].count--
                }
            }
        }
    }

    fun makeDistinct(root: ResultChunk = this) {
        backupPosition()
        while (hasNext()) {
            val same = sameElements() - 1
            if (same > 0) {
                for (c in data) {
                    c.data[c.posIndex].count -= same
                    c.sizeAbsolute -= same
                }
            }
            skipPos(1)
        }
        if (next != root) {
            val last = current()
            loop@ while (next != root) {
                val cur = next.current()
                for (i in 0 until columns) {
                    if (last[i] != cur[i]) {
                        break@loop
                    }
                }
                next.makeDistinct(root)
                next.skipPos(1)
                if (next.hasNext()) {
                    break
                }
                val tmp = next
                next = tmp.next
                next.prev = this
                tmp.prev = tmp
                tmp.next = next
//release(tmp)
            }
        }
        restorePosition()
    }
}
