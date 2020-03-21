package lupos.s03resultRepresentation

import lupos.s00misc.Coverage
import lupos.s03resultRepresentation.ResultChunk
import lupos.s04arithmetikOperators.ResultVektorRaw

open class ResultChunkBase(val resultSet: ResultSet, val columns: Int) : Iterator<ResultRow> {
    val data = Array(columns) { ResultVektor(resultSet.dictionary.undefValue) }
    open fun availableWrite(): Int {
Coverage.funStart(0)

        var res = data[0].availableWrite()
        for (i in 1 until columns) {
Coverage.forLoopStart(1)

            val tmp = data[i].availableWrite()
            if (res > tmp) {
Coverage.ifStart(2)

                res = tmp
            }
        }
        return res
    }

    open fun availableRead(): Int = data[0].availableRead()
    fun append(row: ResultRow, count: Int = 1) {
Coverage.funStart(3)

        require(count > 0)
        for (i in 0 until columns) {
Coverage.forLoopStart(4)

            data[i].append(row.values[i], count)
        }
    }

    fun append(values: Array<Value>, count: Int = 1) {
Coverage.funStart(5)

        require(count > 0)
        for (i in 0 until columns) {
Coverage.forLoopStart(6)

            data[i].append(values[i], count)
        }
    }

    fun backupPosition() {
Coverage.funStart(7)

        for (i in 0 until columns) {
Coverage.forLoopStart(8)

            data[i].backupPosition()
        }
    }

    fun canAppend() = availableWrite() > 0
    fun current(columns: Array<Variable>) = Array(columns.size) { data[columns[it].toInt()].current() }
    fun current() = Array(columns) { data[it].current() }
    open fun copy(columnsTo: Array<Variable>, chunkFrom: ResultChunkBase, columnsFrom: Array<Variable>, count: Int) {
Coverage.funStart(9)

        for (c in 0 until columnsTo.size) {
Coverage.forLoopStart(10)

            val colTo = data[columnsTo[c].toInt()]
            val colFrom = chunkFrom.data[columnsFrom[c].toInt()]
            colTo.copy(colFrom, count)
        }
    }

    open fun copy(chunkFrom: ResultChunkBase, count: Int) {
Coverage.funStart(11)

        require(count > 0)
        for (c in 0 until columns) {
Coverage.forLoopStart(12)

            val colTo = data[c]
            val colFrom = chunkFrom.data[c]
            colTo.copy(colFrom, count)
        }
    }

    open fun copy(columnsTo: Array<Variable>, arrFrom: Array<Value>, columnsFrom: Array<Variable>, count: Int) {
Coverage.funStart(13)

        require(count > 0)
        for (c in 0 until columnsTo.size) {
Coverage.forLoopStart(14)

            val colTo = data[columnsTo[c].toInt()]
            val valFrom = arrFrom[columnsFrom[c].toInt()]
            colTo.append(valFrom, count)
        }
    }

    open fun copyNonNull(columnsTo: Array<Variable>, arrFrom: Array<Value>, columnsFrom: Array<Variable>, arrFromAlternative: Array<Value>, count: Int) {
Coverage.funStart(15)

        require(count > 0)
        for (c in 0 until columnsTo.size) {
Coverage.forLoopStart(16)

            val colTo = data[columnsTo[c].toInt()]
            val valFrom = arrFrom[columnsFrom[c].toInt()]
            if (valFrom != resultSet.dictionary.undefValue) {
Coverage.ifStart(17)

                colTo.append(valFrom, count)
            } else {
Coverage.ifStart(18)

                colTo.append(arrFromAlternative[c], count)
            }
        }
    }

    fun getColumn(variable: Variable) = data[variable.toInt()]
    override fun hasNext() = data[0].hasNext()
    override fun next(): ResultRow {
Coverage.funStart(19)

        val row = resultSet.createResultRow()
        for (i in 0 until columns) {
Coverage.forLoopStart(20)

            row.values[i] = data[i].next()
        }
        return row
    }

    open fun nextArr() = Array(columns) { data[it].next() }
    fun setColumn(variable: Variable, col: ResultVektor) {
Coverage.funStart(21)

        data[variable.toInt()] = col
    }

    fun restorePosition() {
Coverage.funStart(22)

        for (i in 0 until columns) {
Coverage.forLoopStart(23)

            data[i].restorePosition()
        }
    }

    fun sameElements(columns: Array<Variable>): Int {
Coverage.funStart(24)

        var res = availableRead()
        for (i in columns) {
Coverage.forLoopStart(25)

            val t = data[i.toInt()].sameElements()
            if (t < res) {
Coverage.ifStart(26)

                res = t
            }
        }
        return res
    }

    fun sameElements(): Int {
Coverage.funStart(27)

        var res = availableRead()
        for (i in 0 until columns) {
Coverage.forLoopStart(28)

            val t = data[i].sameElements()
            if (t < res) {
Coverage.ifStart(29)

                res = t
            }
        }
        return res
    }

    open fun skipPos(columns: Array<Variable>, count: Int) {
Coverage.funStart(30)

        require(count != 0)
        for (c in 0 until columns.size) {
Coverage.forLoopStart(31)

            data[columns[c].toInt()].skipPos(count)
        }
    }

    open fun skipSize(columns: Array<Variable>, count: Int) {
Coverage.funStart(32)

        require(count != 0)
        for (c in 0 until columns.size) {
Coverage.forLoopStart(33)

            data[columns[c].toInt()].skipSize(count)
        }
    }

    open fun skipPos(count: Int) {
Coverage.funStart(34)

        require(count != 0)
        for (c in 0 until columns) {
Coverage.forLoopStart(35)

            data[c].skipPos(count)
        }
    }

    open fun skipSize(count: Int) {
Coverage.funStart(36)

        require(count != 0)
        for (c in 0 until columns) {
Coverage.forLoopStart(37)

            data[c].skipSize(count)
        }
    }

    override fun toString(): String {
Coverage.funStart(38)

        val res = StringBuilder()
        res.append("" + availableRead() + "r" + availableWrite() + "w")
        for (c in 0 until columns) {
Coverage.forLoopStart(39)

            res.append("(${resultSet.getVariableNames()[c]},${data[c].posIndex},${data[c].sizeIndex},${data[c].posAbsolute},${data[c].sizeAbsolute},${data[c].posIndexLocal} ${data[c].availableRead()} ${data[c].availableWrite()}), ")
        }
        res.append("\n")
        if (columns > 0) {
Coverage.ifStart(40)

            for (r in 0 until ResultVektor.capacity) {
Coverage.forLoopStart(41)

                var flag = false
                for (c in 0 until columns) {
Coverage.forLoopStart(42)

                    if (r >= data[c].posIndex && r <= data[c].sizeIndex && data[c].data[r].count > 0) {
Coverage.ifStart(43)

                        res.append(data[c].data[r].value)
                        if (r == data[c].posIndex && data[c].posIndexLocal != 0) {
Coverage.ifStart(44)

                            res.append("(${data[c].data[r].count - data[c].posIndexLocal})<${data[c].data[r].count}>")
                        } else {
Coverage.ifStart(45)

                            res.append("(${data[c].data[r].count})")
                        }
                        res.append(", ")
                    } else {
Coverage.ifStart(46)

                        res.append("<${data[c].data[r].count}>, ")
                        flag = true
                    }
                }
                res.append("\n")
                if (flag && r > 4) {
Coverage.ifStart(47)

                    break
                }
            }
        }
        return res.toString()
    }
}
