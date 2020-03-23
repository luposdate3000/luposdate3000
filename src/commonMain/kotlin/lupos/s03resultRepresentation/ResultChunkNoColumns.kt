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
Coverage.funStart(493)
        posField++
Coverage.statementStart(494)
        return resultSet.createResultRow()
    }
    override fun availableWrite() = ResultVektor.capacity - sizeField
    override fun availableRead() = sizeField - posField
    override fun copy(chunkFrom: ResultChunkBase, count: Int) {
Coverage.funStart(495)
        posField += count
Coverage.statementStart(496)
    }
    override fun copy(columnsTo: Array<Variable>, chunkFrom: ResultChunkBase, columnsFrom: Array<Variable>, count: Int) {
Coverage.funStart(497)
        posField += count
Coverage.statementStart(498)
    }
    override fun copy(columnsTo: Array<Variable>, arrFrom: Array<Value>, columnsFrom: Array<Variable>, count: Int) {
Coverage.funStart(499)
        posField += count
Coverage.statementStart(500)
    }
    override fun copyNonNull(columnsTo: Array<Variable>, arrFrom: Array<Value>, columnsFrom: Array<Variable>, arrFromAlternative: Array<Value>, count: Int) {
Coverage.funStart(501)
        posField += count
Coverage.statementStart(502)
    }
    override fun skipPos(columns: Array<Variable>, count: Int) {
Coverage.funStart(503)
        posField += count
Coverage.statementStart(504)
    }
    override fun skipSize(columns: Array<Variable>, count: Int) {
Coverage.funStart(505)
        sizeField += count
Coverage.statementStart(506)
    }
    override fun skipPos(count: Int) {
Coverage.funStart(507)
        posField += count
Coverage.statementStart(508)
    }
    override fun skipSize(count: Int) {
Coverage.funStart(509)
        sizeField += count
Coverage.statementStart(510)
    }
    override fun nextArr(): Array<Value> {
Coverage.funStart(511)
        posField++
Coverage.statementStart(512)
        return emptyArray
    }
}
