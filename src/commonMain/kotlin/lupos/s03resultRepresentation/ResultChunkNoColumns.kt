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
Coverage.funStart(536)
        posField++
Coverage.statementStart(537)
        return resultSet.createResultRow()
    }
    override fun availableWrite() = ResultVektor.capacity - sizeField
    override fun availableRead() = sizeField - posField
    override fun copy(chunkFrom: ResultChunkBase, count: Int) {
Coverage.funStart(538)
        posField += count
Coverage.statementStart(539)
    }
    override fun copy(columnsTo: Array<Variable>, chunkFrom: ResultChunkBase, columnsFrom: Array<Variable>, count: Int) {
Coverage.funStart(540)
        posField += count
Coverage.statementStart(541)
    }
    override fun copy(columnsTo: Array<Variable>, arrFrom: Array<Value>, columnsFrom: Array<Variable>, count: Int) {
Coverage.funStart(542)
        posField += count
Coverage.statementStart(543)
    }
    override fun copyNonNull(columnsTo: Array<Variable>, arrFrom: Array<Value>, columnsFrom: Array<Variable>, arrFromAlternative: Array<Value>, count: Int) {
Coverage.funStart(544)
        posField += count
Coverage.statementStart(545)
    }
    override fun skipPos(columns: Array<Variable>, count: Int) {
Coverage.funStart(546)
        posField += count
Coverage.statementStart(547)
    }
    override fun skipSize(columns: Array<Variable>, count: Int) {
Coverage.funStart(548)
        sizeField += count
Coverage.statementStart(549)
    }
    override fun skipPos(count: Int) {
Coverage.funStart(550)
        posField += count
Coverage.statementStart(551)
    }
    override fun skipSize(count: Int) {
Coverage.funStart(552)
        sizeField += count
Coverage.statementStart(553)
    }
    override fun nextArr(): Array<Value> {
Coverage.funStart(554)
        posField++
Coverage.statementStart(555)
        return emptyArray
    }
}
