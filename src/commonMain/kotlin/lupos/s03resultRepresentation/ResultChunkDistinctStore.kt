package lupos.s03resultRepresentation

import lupos.s00misc.Coverage
import lupos.s00misc.SanityCheck
import lupos.s03resultRepresentation.ResultChunk
import lupos.s04arithmetikOperators.ResultVektorRaw

open class ResultChunkDistinctStore(resultSet: ResultSet, columns: Int) : ResultChunk(resultSet, columns) {
    companion object {
        fun insertDistinct(value: Array<Value>, target: ResultChunkDistinctStore?, resultSet: ResultSet): ResultChunkDistinctStore {
            println("insertDistinct")
            return internalInsertDistinct(value, target, resultSet, target)
        }

        fun internalInsertDistinct(value: Array<Value>, target: ResultChunkDistinctStore?, resultSet: ResultSet, root: ResultChunkDistinctStore?): ResultChunkDistinctStore {
            println("internalInsertDistinct 0")
            if (target == null) {
                val res = ResultChunkDistinctStore(resultSet, value.size)
                res.append(value)
                println("internalInsertDistinct a")
                return res
            }
            var insertFront = true
            for (i in 0 until target.columns) {
                if (target.data[i].data[0].value < value[i]) {
                    insertFront = false
                    break
                }
            }
            if (insertFront) {
                if (target.availableWrite() > 0) {
                    for (i in 0 until target.columns) {
                        val col = target.data[i]
                        if (col.data[0].value == value[i])
                            col.data[0].count++
                        else {
                            for (j in col.sizeIndex downTo 0) {
                                col.data[j + 1].value = col.data[j].value
                                col.data[j + 1].count = col.data[j].count
                            }
                            col.data[0].value = value[i]
                            col.data[0].count = 1
                            col.sizeIndex++
                            col.sizeAbsolute++
                        }
                    }
                    println("internalInsertDistinct b")
                    return root!!
                } else {
                    val res = ResultChunkDistinctStore(resultSet, value.size)
                    res.append(value)
                    append(res, target).next
                    if (target == root) {
                        println("internalInsertDistinct c")
                        return res
                    }
                    println("internalInsertDistinct d")
                    return root!!
                }
            }
            var insertLater = true
            for (i in 0 until target.columns) {
                if (target.data[i].data[target.data[i].sizeIndex].value <= value[i]) {
                    insertLater = false
                    break
                }
            }
            if (insertLater) {
                if (target.next == root) {
                    val res = ResultChunkDistinctStore(resultSet, value.size)
                    res.append(value)
                    append(target, res).next
                    println("internalInsertDistinct e")
                    return root!!
                } else {
                    println("internalInsertDistinct f")
                    return internalInsertDistinct(value, target.next as ResultChunkDistinctStore, resultSet, root)
                }
            } else {
                if (target.availableWrite() > 2) {
                    target.internalInsertSorted(Array(value.size) { ValueComparatorFast() }, Array(value.size) { it.toLong() }, value, 1, true)
                    println("internalInsertDistinct g")
                    return root!!
                } else {
                    val res = ResultChunkDistinctStore(resultSet, value.size)
                    for (i in 0 until target.columns) {
                        val colIn = target.data[i]
                        val colOut = res.data[i]
                        require(colIn.data[colIn.sizeIndex].count > 0)
                        if (colIn.data[colIn.sizeIndex].count == 1) {
                            colOut.data[0].value = colIn.data[colIn.sizeIndex - 1].value
                            colOut.data[0].count = 1
                            colOut.data[1].value = colIn.data[colIn.sizeIndex].value
                            colOut.data[1].count = 1
                            colOut.sizeIndex = 1
                            colOut.sizeAbsolute = 2
                            colIn.sizeIndex--
                            colIn.sizeAbsolute -= 2
                            colIn.data[colIn.sizeIndex].count--
                            if (colIn.data[colIn.sizeIndex].count == 0) {
                                require(colIn.sizeIndex > 0)
                                colIn.sizeIndex--
                            }
                        } else {
                            colOut.data[0].value = colIn.data[colIn.sizeIndex].value
                            colOut.data[0].count = 2
                            colOut.sizeIndex = 0
                            colOut.sizeAbsolute = 2
                            colIn.sizeAbsolute -= 2
                            colIn.data[colIn.sizeIndex].count -= 2
                            if (colIn.data[colIn.sizeIndex].count == 0) {
                                require(colIn.sizeIndex > 0)
                                colIn.sizeIndex--
                            }
                        }
                    }
                    res.next = target.next
                    res.prev = target
                    target.next = res
                    res.next.prev = res
                    println("internalInsertDistinct h")
                    return internalInsertDistinct(value, target, resultSet, root)
                }
            }
        }
    }

    fun remove(value: Array<Value>, root: ResultChunkDistinctStore = this) {
        require(columns == value.size)
        println("remove ${value.map { it }} columnfirst: ${data.map { it.toString() }}")
        if (data[0].data[0].value > value[0]) {
            println("not found")
            return
        }
        if (data[0].data[data[0].sizeIndex].value < value[0]) {
            if (next != root)
                (next as ResultChunkDistinctStore).remove(value, root)
            println("not found 3")
            return
        }
        var first = 0
        var last = data[0].sizeAbsolute
        val indices = Array(columns) { 0 }
        for (i in 0 until columns) {
//search for the value to delete
            println("firstlast $first $last")
            val column = data[i]
            var localIdx = 0
            var absIdx = 0
            while (absIdx + column.data[localIdx].count < first) {
                println("skipfirst ${column.data[localIdx].value}")
                absIdx += column.data[localIdx++].count
            }
            while (column.data[localIdx].value != value[i]) {
                println("skipneq ${column.data[localIdx].value} $absIdx $last")
                absIdx += column.data[localIdx++].count
                if (absIdx >= last) {
                    println("not found 2")
                    return
                }
            }
            require(localIdx <= column.sizeIndex)
            indices[i] = localIdx
            if (absIdx > first)
                first = absIdx
            if (absIdx + column.data[localIdx].count < last)
                last = absIdx + column.data[localIdx].count
        }
//delete it
        println("remove it")
        for (i in 0 until columns) {
            val column = data[i]
            column.sizeAbsolute--
            if (column.data[indices[i]].count > 1) {
                column.data[indices[i]].count--
            } else {
                for (j in indices[i] until column.sizeIndex) {
                    column.data[j] = column.data[j + 1]
                }
                if (column.sizeIndex == 0)
                    column.data[0].count = 0
                else
                    column.sizeIndex--
            }
        }
        println("remove final ${value.map { it }} columnfirst: ${data.map { it.toString() }}")
    }
}
