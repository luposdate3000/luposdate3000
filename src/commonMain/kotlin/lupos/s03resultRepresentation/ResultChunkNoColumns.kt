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
        Coverage.funStart(599)
        posField++
        Coverage.statementStart(600)
        return resultSet.createResultRow()
    }

    override fun availableWrite() = ResultVektor.capacity - sizeField
    override fun availableRead() = sizeField - posField
    override fun copy(chunkFrom: ResultChunkBase, count: Int) {
        Coverage.funStart(601)
        posField += count
        Coverage.statementStart(602)
    }

    override fun copy(columnsTo: Array<Variable>, chunkFrom: ResultChunkBase, columnsFrom: Array<Variable>, count: Int) {
        Coverage.funStart(603)
        posField += count
        Coverage.statementStart(604)
    }

    override fun copy(columnsTo: Array<Variable>, arrFrom: Array<Value>, columnsFrom: Array<Variable>, count: Int) {
        Coverage.funStart(605)
        posField += count
        Coverage.statementStart(606)
    }

    override fun copyNonNull(columnsTo: Array<Variable>, arrFrom: Array<Value>, columnsFrom: Array<Variable>, arrFromAlternative: Array<Value>, count: Int) {
        Coverage.funStart(607)
        posField += count
        Coverage.statementStart(608)
    }

    override fun skipPos(columns: Array<Variable>, count: Int) {
        Coverage.funStart(609)
        posField += count
        Coverage.statementStart(610)
    }

    override fun skipSize(columns: Array<Variable>, count: Int) {
        Coverage.funStart(611)
        sizeField += count
        Coverage.statementStart(612)
    }

    override fun skipPos(count: Int) {
        Coverage.funStart(613)
        posField += count
        Coverage.statementStart(614)
    }

    override fun skipSize(count: Int) {
        Coverage.funStart(615)
        sizeField += count
        Coverage.statementStart(616)
    }

    override fun nextArr(): Array<Value> {
        Coverage.funStart(617)
        posField++
        Coverage.statementStart(618)
        return emptyArray
    }
}
