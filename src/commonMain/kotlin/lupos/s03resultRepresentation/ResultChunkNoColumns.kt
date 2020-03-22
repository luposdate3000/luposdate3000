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
Coverage.funStart(558)
        posField++
Coverage.statementStart(559)
        return resultSet.createResultRow()
    }
    override fun availableWrite() = ResultVektor.capacity - sizeField
    override fun availableRead() = sizeField - posField
    override fun copy(chunkFrom: ResultChunkBase, count: Int) {
Coverage.funStart(560)
        posField += count
Coverage.statementStart(561)
    }
    override fun copy(columnsTo: Array<Variable>, chunkFrom: ResultChunkBase, columnsFrom: Array<Variable>, count: Int) {
Coverage.funStart(562)
        posField += count
Coverage.statementStart(563)
    }
    override fun copy(columnsTo: Array<Variable>, arrFrom: Array<Value>, columnsFrom: Array<Variable>, count: Int) {
Coverage.funStart(564)
        posField += count
Coverage.statementStart(565)
    }
    override fun copyNonNull(columnsTo: Array<Variable>, arrFrom: Array<Value>, columnsFrom: Array<Variable>, arrFromAlternative: Array<Value>, count: Int) {
Coverage.funStart(566)
        posField += count
Coverage.statementStart(567)
    }
    override fun skipPos(columns: Array<Variable>, count: Int) {
Coverage.funStart(568)
        posField += count
Coverage.statementStart(569)
    }
    override fun skipSize(columns: Array<Variable>, count: Int) {
Coverage.funStart(570)
        sizeField += count
Coverage.statementStart(571)
    }
    override fun skipPos(count: Int) {
Coverage.funStart(572)
        posField += count
Coverage.statementStart(573)
    }
    override fun skipSize(count: Int) {
Coverage.funStart(574)
        sizeField += count
Coverage.statementStart(575)
    }
    override fun nextArr(): Array<Value> {
Coverage.funStart(576)
        posField++
Coverage.statementStart(577)
        return emptyArray
    }
}
