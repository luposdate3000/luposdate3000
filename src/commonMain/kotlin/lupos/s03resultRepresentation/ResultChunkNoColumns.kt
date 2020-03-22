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
Coverage.funStart(492)
        posField++
Coverage.statementStart(493)
        return resultSet.createResultRow()
    }
    override fun availableWrite() = ResultVektor.capacity - sizeField
    override fun availableRead() = sizeField - posField
    override fun copy(chunkFrom: ResultChunkBase, count: Int) {
Coverage.funStart(494)
        posField += count
Coverage.statementStart(495)
    }
    override fun copy(columnsTo: Array<Variable>, chunkFrom: ResultChunkBase, columnsFrom: Array<Variable>, count: Int) {
Coverage.funStart(496)
        posField += count
Coverage.statementStart(497)
    }
    override fun copy(columnsTo: Array<Variable>, arrFrom: Array<Value>, columnsFrom: Array<Variable>, count: Int) {
Coverage.funStart(498)
        posField += count
Coverage.statementStart(499)
    }
    override fun copyNonNull(columnsTo: Array<Variable>, arrFrom: Array<Value>, columnsFrom: Array<Variable>, arrFromAlternative: Array<Value>, count: Int) {
Coverage.funStart(500)
        posField += count
Coverage.statementStart(501)
    }
    override fun skipPos(columns: Array<Variable>, count: Int) {
Coverage.funStart(502)
        posField += count
Coverage.statementStart(503)
    }
    override fun skipSize(columns: Array<Variable>, count: Int) {
Coverage.funStart(504)
        sizeField += count
Coverage.statementStart(505)
    }
    override fun skipPos(count: Int) {
Coverage.funStart(506)
        posField += count
Coverage.statementStart(507)
    }
    override fun skipSize(count: Int) {
Coverage.funStart(508)
        sizeField += count
Coverage.statementStart(509)
    }
    override fun nextArr(): Array<Value> {
Coverage.funStart(510)
        posField++
Coverage.statementStart(511)
        return emptyArray
    }
}
