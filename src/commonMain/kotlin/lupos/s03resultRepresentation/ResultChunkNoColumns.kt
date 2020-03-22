package lupos.s03resultRepresentation

import lupos.s00misc.Coverage
import lupos.s03resultRepresentation.ResultChunk
import lupos.s04arithmetikOperators.ResultVektorRaw

class ResultChunkNoColumns(resultSet: ResultSet, columns: Int) : ResultChunk(resultSet, columns) {
    val emptyArray = arrayOf<Value>()
    var posField = 0
    var sizeField = 0
    override fun hasNext() = posField < sizeField
    override fun next(): ResultRow {
        Coverage.funStart(506)
        posField++
        Coverage.statementStart(507)
        return resultSet.createResultRow()
    }

    override fun availableWrite() = ResultVektor.capacity - sizeField
    override fun availableRead() = sizeField - posField
    override fun copy(chunkFrom: ResultChunkBase, count: Int) {
        Coverage.funStart(508)
        posField += count
        Coverage.statementStart(509)
    }

    override fun copy(columnsTo: Array<Variable>, chunkFrom: ResultChunkBase, columnsFrom: Array<Variable>, count: Int) {
        Coverage.funStart(510)
        posField += count
        Coverage.statementStart(511)
    }

    override fun copy(columnsTo: Array<Variable>, arrFrom: Array<Value>, columnsFrom: Array<Variable>, count: Int) {
        Coverage.funStart(512)
        posField += count
        Coverage.statementStart(513)
    }

    override fun copyNonNull(columnsTo: Array<Variable>, arrFrom: Array<Value>, columnsFrom: Array<Variable>, arrFromAlternative: Array<Value>, count: Int) {
        Coverage.funStart(514)
        posField += count
        Coverage.statementStart(515)
    }

    override fun skipPos(columns: Array<Variable>, count: Int) {
        Coverage.funStart(516)
        posField += count
        Coverage.statementStart(517)
    }

    override fun skipSize(columns: Array<Variable>, count: Int) {
        Coverage.funStart(518)
        sizeField += count
        Coverage.statementStart(519)
    }

    override fun skipPos(count: Int) {
        Coverage.funStart(520)
        posField += count
        Coverage.statementStart(521)
    }

    override fun skipSize(count: Int) {
        Coverage.funStart(522)
        sizeField += count
        Coverage.statementStart(523)
    }

    override fun nextArr(): Array<Value> {
        Coverage.funStart(524)
        posField++
        Coverage.statementStart(525)
        return emptyArray
    }
}
