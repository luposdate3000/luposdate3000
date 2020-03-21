package lupos.s03resultRepresentation

import lupos.s03resultRepresentation.ResultChunk
import lupos.s04arithmetikOperators.ResultVektorRaw


open class ResultChunk(resultSet: ResultSet, columns: Int) : ResultChunkBase(resultSet, columns) {
    companion object {
        operator fun invoke(resultSet: ResultSet): ResultChunk {
            val columns = resultSet.getVariableNames().size
            if (columns == 0)
                return ResultChunkNoColumns(resultSet, columns)
            return ResultChunk(resultSet, columns)
        }

        fun removeFirst(root: ResultChunk): ResultChunk? {
            if (root.next == root)
                return null
            val res = root.next
            res.prev = root.prev
            root.prev.next = res
            return res
        }

        fun split(root: ResultChunk, count: Int): ResultChunk {
            var other = root
            for (i in 0 until count)
                other = other.next
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

        fun sortHelper(comparator: Array<Comparator<Value>>, columnOrder: Array<Variable>, a: ResultChunk, b: ResultChunk, target: ResultChunk): ResultChunk {
            println("a")
            var targetLast = target
            loop@ while (a.hasNext() && b.hasNext()) {
                println("b")
                var cmp = 0
                for (i in columnOrder) {
                    println("c")
                    val vala = a.data[i.toInt()].current()
                    val valb = b.data[i.toInt()].current()
                    if (vala != valb) {
                        println("d")
                        cmp = comparator[i.toInt()].compare(vala, valb)
                        require(cmp != 0)
                        if (cmp < 0) {
                            println("e")
                            var count = a.data[i.toInt()].sameElements()
                            targetLast = copy(a, targetLast, count)
                        } else {
                            println("f")
                            var count = b.data[i.toInt()].sameElements()
                            targetLast = copy(b, targetLast, count)
                        }
                        continue@loop
                    }
                }
                if (cmp == 0) {
                    println("g")
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
            var chunkCount = 1
            var tmp = chunks.next
            while (tmp != chunks) {
                println("h")
                chunkCount++
                tmp = tmp.next
            }
            if (chunkCount == 1) {
                println("i")
                val resultSet = chunks.resultSet
                val columns = chunks.columns
                val res = ResultChunk(resultSet, columns)
println("x1 $res")
                var resLast = res
                while (chunks.hasNext()) {
                    println("j")
                    val same = chunks.sameElements()
                    if (resLast.availableWrite() < 2) {
                        println("k")
                        resLast = append(resLast, ResultChunk(resultSet, columns))
println("x2 $res")
                    }
                    resLast.internalInsertSorted(comparator, columnOrder, chunks.current(), same)
println("x3 $res")
                    chunks.skipPos(same)
                }
println("x4 $res")
                return res
            } else {
                println("l")
                val half = chunkCount / 2
                var a: ResultChunk? = sort(comparator, columnOrder, split(chunks, half))
                var b: ResultChunk? = sort(comparator, columnOrder, chunks)
                val resultSet = a!!.resultSet
                val columns = a!!.columns
                val res = ResultChunk(resultSet, columns)
                var resLast = res
                while (true) {
                    println("m")
                    resLast = sortHelper(comparator, columnOrder, a!!, b!!, resLast)
                    if (a.hasNext()) {
                        println("n")
                        b = removeFirst(b)
                        if (b == null) {
                            println("o")
                            break
                        }
                    } else {
                        println("p")
                        a = removeFirst(a)
                        if (a == null) {
                            println("a")
                            break
                        }
                    }
                }
                if (a != null) {
                    println("q")
                    var count = a!!.availableRead()
                    resLast = copy(a, resLast, count)
                    append(resLast, a)
                } else {
                    println("r")
                    var count = b!!.availableRead()
                    resLast = copy(b!!, resLast, count)
                    append(resLast, b!!)
                }
                return res
            }
        }
    }

    var prev = this
    var next = this

    fun chunkCount(): Int {
        var res = 1
        var tmp = this.next
        while (tmp != this) {
            res++
            tmp = tmp.next
        }
        return res
    }

    fun internalInsertSorted(comparator: Array<Comparator<Value>>, columnOrder: Array<Variable>, values: Array<Value>, count: Int = 1) {
        println("s ${values.map { it }} $count ${columnOrder.map { it }}")
        var columnidx = columnOrder[0].toInt()
        var column = data[columnidx]
        var idx = column.insertSorted(values[columnidx], comparator = comparator[columnOrder[0].toInt()], count = count)
        var first = idx.first
        var last = first + idx.second - count
        for (i in 1 until columns) {
            println("t $first $last $idx $columnidx")
            columnidx = columnOrder[i].toInt()
            column = data[columnidx]
            idx = column.insertSorted(values[columnidx], first, last, comparator[columnOrder[i].toInt()], count)
if(idx.first>first)
first=idx.first
if(last>first + idx.second - count)
last=first + idx.second - count
        }
println("y1 $this")
    }
}

class ValueComparatorFast : Comparator<Value> {
    override fun compare(a: Value, b: Value) = a.compareTo(b)
}

