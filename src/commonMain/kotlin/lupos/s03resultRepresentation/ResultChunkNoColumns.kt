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
Coverage.funStart(559)
        posField++
Coverage.statementStart(560)
        return resultSet.createResultRow()
    }
    override fun availableWrite() = ResultVektor.capacity - sizeField
    override fun availableRead() = sizeField - posField
    override fun copy(chunkFrom: ResultChunkBase, count: Int) {
Coverage.funStart(561)
        posField += count
Coverage.statementStart(562)
    }
    override fun copy(columnsTo: Array<Variable>, chunkFrom: ResultChunkBase, columnsFrom: Array<Variable>, count: Int) {
Coverage.funStart(563)
        posField += count
Coverage.statementStart(564)
    }
    override fun copy(columnsTo: Array<Variable>, arrFrom: Array<Value>, columnsFrom: Array<Variable>, count: Int) {
Coverage.funStart(565)
        posField += count
Coverage.statementStart(566)
    }
    override fun copyNonNull(columnsTo: Array<Variable>, arrFrom: Array<Value>, columnsFrom: Array<Variable>, arrFromAlternative: Array<Value>, count: Int) {
Coverage.funStart(567)
        posField += count
Coverage.statementStart(568)
    }
    override fun skipPos(columns: Array<Variable>, count: Int) {
Coverage.funStart(569)
        posField += count
Coverage.statementStart(570)
    }
    override fun skipSize(columns: Array<Variable>, count: Int) {
Coverage.funStart(571)
        sizeField += count
Coverage.statementStart(572)
    }
    override fun skipPos(count: Int) {
Coverage.funStart(573)
        posField += count
Coverage.statementStart(574)
    }
    override fun skipSize(count: Int) {
Coverage.funStart(575)
        sizeField += count
Coverage.statementStart(576)
    }
    override fun nextArr(): Array<Value> {
Coverage.funStart(577)
        posField++
Coverage.statementStart(578)
        return emptyArray
    }
}
