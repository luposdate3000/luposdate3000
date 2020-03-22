package lupos.s03resultRepresentation
import lupos.s00misc.Coverage
import lupos.s03resultRepresentation.ResultChunk
import lupos.s04arithmetikOperators.ResultVektorRaw
open class ResultChunkBase(val resultSet: ResultSet, val columns: Int) : Iterator<ResultRow> {
    val data = Array(columns) { ResultVektor(resultSet.dictionary.undefValue) }
    open fun availableWrite(): Int {
Coverage.funStart(0)
        var res = data[0].availableWrite()
Coverage.statementStart(1)
        for (i in 1 until columns) {
Coverage.forLoopStart(2)
            val tmp = data[i].availableWrite()
Coverage.statementStart(3)
            if (res > tmp) {
Coverage.ifStart(4)
                res = tmp
Coverage.statementStart(5)
            }
Coverage.statementStart(6)
        }
Coverage.statementStart(7)
        return res
    }
    open fun availableRead(): Int = data[0].availableRead()
    fun append(row: ResultRow, count: Int = 1) {
Coverage.funStart(8)
        require(count > 0)
Coverage.statementStart(9)
        for (i in 0 until columns) {
Coverage.forLoopStart(10)
            data[i].append(row.values[i], count)
Coverage.statementStart(11)
        }
Coverage.statementStart(12)
    }
    fun append(values: Array<Value>, count: Int = 1) {
Coverage.funStart(13)
        require(count > 0)
Coverage.statementStart(14)
        for (i in 0 until columns) {
Coverage.forLoopStart(15)
            data[i].append(values[i], count)
Coverage.statementStart(16)
        }
Coverage.statementStart(17)
    }
    fun backupPosition() {
Coverage.funStart(18)
        for (i in 0 until columns) {
Coverage.forLoopStart(19)
            data[i].backupPosition()
Coverage.statementStart(20)
        }
Coverage.statementStart(21)
    }
    fun canAppend() = availableWrite() > 0
    fun current(columns: Array<Variable>) = Array(columns.size) { data[columns[it].toInt()].current() }
    fun current() = Array(columns) { data[it].current() }
    open fun copy(columnsTo: Array<Variable>, chunkFrom: ResultChunkBase, columnsFrom: Array<Variable>, count: Int) {
Coverage.funStart(22)
        for (c in 0 until columnsTo.size) {
Coverage.forLoopStart(23)
            val colTo = data[columnsTo[c].toInt()]
Coverage.statementStart(24)
            val colFrom = chunkFrom.data[columnsFrom[c].toInt()]
Coverage.statementStart(25)
            colTo.copy(colFrom, count)
Coverage.statementStart(26)
        }
Coverage.statementStart(27)
    }
    open fun copy(chunkFrom: ResultChunkBase, count: Int) {
Coverage.funStart(28)
        require(count > 0)
Coverage.statementStart(29)
        for (c in 0 until columns) {
Coverage.forLoopStart(30)
            val colTo = data[c]
Coverage.statementStart(31)
            val colFrom = chunkFrom.data[c]
Coverage.statementStart(32)
            colTo.copy(colFrom, count)
Coverage.statementStart(33)
        }
Coverage.statementStart(34)
    }
    open fun copy(columnsTo: Array<Variable>, arrFrom: Array<Value>, columnsFrom: Array<Variable>, count: Int) {
Coverage.funStart(35)
        require(count > 0)
Coverage.statementStart(36)
        for (c in 0 until columnsTo.size) {
Coverage.forLoopStart(37)
            val colTo = data[columnsTo[c].toInt()]
Coverage.statementStart(38)
            val valFrom = arrFrom[columnsFrom[c].toInt()]
Coverage.statementStart(39)
            colTo.append(valFrom, count)
Coverage.statementStart(40)
        }
Coverage.statementStart(41)
    }
    open fun copyNonNull(columnsTo: Array<Variable>, arrFrom: Array<Value>, columnsFrom: Array<Variable>, arrFromAlternative: Array<Value>, count: Int) {
Coverage.funStart(42)
        require(count > 0)
Coverage.statementStart(43)
        for (c in 0 until columnsTo.size) {
Coverage.forLoopStart(44)
            val colTo = data[columnsTo[c].toInt()]
Coverage.statementStart(45)
            val valFrom = arrFrom[columnsFrom[c].toInt()]
Coverage.statementStart(46)
            if (valFrom != resultSet.dictionary.undefValue) {
Coverage.ifStart(47)
                colTo.append(valFrom, count)
Coverage.statementStart(48)
            } else {
Coverage.ifStart(49)
                colTo.append(arrFromAlternative[c], count)
Coverage.statementStart(50)
            }
Coverage.statementStart(51)
        }
Coverage.statementStart(52)
    }
    fun getColumn(variable: Variable) = data[variable.toInt()]
    override fun hasNext() = data[0].hasNext()
    override fun next(): ResultRow {
Coverage.funStart(53)
        val row = resultSet.createResultRow()
Coverage.statementStart(54)
        for (i in 0 until columns) {
Coverage.forLoopStart(55)
            row.values[i] = data[i].next()
Coverage.statementStart(56)
        }
Coverage.statementStart(57)
        return row
    }
    open fun nextArr() = Array(columns) { data[it].next() }
    fun setColumn(variable: Variable, col: ResultVektor) {
Coverage.funStart(58)
        data[variable.toInt()] = col
Coverage.statementStart(59)
    }
    fun restorePosition() {
Coverage.funStart(60)
        for (i in 0 until columns) {
Coverage.forLoopStart(61)
            data[i].restorePosition()
Coverage.statementStart(62)
        }
Coverage.statementStart(63)
    }
    fun sameElements(columns: Array<Variable>): Int {
Coverage.funStart(64)
        var res = availableRead()
Coverage.statementStart(65)
        for (i in columns) {
Coverage.forLoopStart(66)
            val t = data[i.toInt()].sameElements()
Coverage.statementStart(67)
            if (t < res) {
Coverage.ifStart(68)
                res = t
Coverage.statementStart(69)
            }
Coverage.statementStart(70)
        }
Coverage.statementStart(71)
        return res
    }
    fun sameElements(): Int {
Coverage.funStart(72)
        var res = availableRead()
Coverage.statementStart(73)
        for (i in 0 until columns) {
Coverage.forLoopStart(74)
            val t = data[i].sameElements()
Coverage.statementStart(75)
            if (t < res) {
Coverage.ifStart(76)
                res = t
Coverage.statementStart(77)
            }
Coverage.statementStart(78)
        }
Coverage.statementStart(79)
        return res
    }
    open fun skipPos(columns: Array<Variable>, count: Int) {
Coverage.funStart(80)
        require(count != 0)
Coverage.statementStart(81)
        for (c in 0 until columns.size) {
Coverage.forLoopStart(82)
            data[columns[c].toInt()].skipPos(count)
Coverage.statementStart(83)
        }
Coverage.statementStart(84)
    }
    open fun skipSize(columns: Array<Variable>, count: Int) {
Coverage.funStart(85)
        require(count != 0)
Coverage.statementStart(86)
        for (c in 0 until columns.size) {
Coverage.forLoopStart(87)
            data[columns[c].toInt()].skipSize(count)
Coverage.statementStart(88)
        }
Coverage.statementStart(89)
    }
    open fun skipPos(count: Int) {
Coverage.funStart(90)
        require(count != 0)
Coverage.statementStart(91)
        for (c in 0 until columns) {
Coverage.forLoopStart(92)
            data[c].skipPos(count)
Coverage.statementStart(93)
        }
Coverage.statementStart(94)
    }
    open fun skipSize(count: Int) {
Coverage.funStart(95)
        require(count != 0)
Coverage.statementStart(96)
        for (c in 0 until columns) {
Coverage.forLoopStart(97)
            data[c].skipSize(count)
Coverage.statementStart(98)
        }
Coverage.statementStart(99)
    }
    override fun toString(): String {
Coverage.funStart(100)
//
Coverage.statementStart(101)
        val res = StringBuilder()
Coverage.statementStart(102)
//
Coverage.statementStart(103)
        res.append("" + availableRead() + "r" + availableWrite() + "w")
Coverage.statementStart(104)
//
Coverage.statementStart(105)
        for (c in 0 until columns) {
Coverage.forLoopStart(106)
//
Coverage.statementStart(107)
            res.append("(${data[c].uuid} ${resultSet.getVariableNames()[c]},${data[c].posIndex},${data[c].sizeIndex},${data[c].posAbsolute},${data[c].sizeAbsolute},${data[c].posIndexLocal} ${data[c].availableRead()} ${data[c].availableWrite()}), ")
Coverage.statementStart(108)
//
Coverage.statementStart(109)
        }
Coverage.statementStart(110)
//
Coverage.statementStart(111)
        res.append("\n")
Coverage.statementStart(112)
//
Coverage.statementStart(113)
        if (columns > 0) {
Coverage.ifStart(114)
//
Coverage.statementStart(115)
            for (r in 0 until ResultVektor.capacity) {
Coverage.forLoopStart(116)
//
Coverage.statementStart(117)
                var flag = 0
Coverage.statementStart(118)
//
Coverage.statementStart(119)
                for (c in 0 until columns) {
Coverage.forLoopStart(120)
//
Coverage.statementStart(121)
                    res.append(data[c].data[r].value)
Coverage.statementStart(122)
//
Coverage.statementStart(123)
                    if (r >= data[c].posIndex && r <= data[c].sizeIndex && data[c].data[r].count > 0) {
Coverage.ifStart(124)
//
Coverage.statementStart(125)
                        if (r == data[c].posIndex && data[c].posIndexLocal != 0) {
Coverage.ifStart(126)
//
Coverage.statementStart(127)
                            res.append("(${data[c].data[r].count - data[c].posIndexLocal})<${data[c].data[r].count}>")
Coverage.statementStart(128)
//
Coverage.statementStart(129)
                        } else {
Coverage.ifStart(130)
//
Coverage.statementStart(131)
                            res.append("(${data[c].data[r].count})")
Coverage.statementStart(132)
//
Coverage.statementStart(133)
                        }
Coverage.statementStart(134)
//
Coverage.statementStart(135)
                        res.append(", ")
Coverage.statementStart(136)
//
Coverage.statementStart(137)
                    } else {
Coverage.ifStart(138)
//
Coverage.statementStart(139)
                        res.append("<${data[c].data[r].count}>, ")
Coverage.statementStart(140)
//
Coverage.statementStart(141)
                        flag++
Coverage.statementStart(142)
//
Coverage.statementStart(143)
                    }
Coverage.statementStart(144)
//
Coverage.statementStart(145)
                }
Coverage.statementStart(146)
//
Coverage.statementStart(147)
                res.append("\n")
Coverage.statementStart(148)
//
Coverage.statementStart(149)
                if (flag == columns && r > 4) {
Coverage.ifStart(150)
//
Coverage.statementStart(151)
                    break
                }
Coverage.statementStart(152)
//
Coverage.statementStart(153)
            }
Coverage.statementStart(154)
//
Coverage.statementStart(155)
        }
Coverage.statementStart(156)
//
Coverage.statementStart(157)
        return res.toString()
    }
}
