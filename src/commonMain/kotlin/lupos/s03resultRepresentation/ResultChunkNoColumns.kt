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
Coverage.funStart(529)
        posField++
Coverage.statementStart(530)
        return resultSet.createResultRow()
    }
    override fun availableWrite() = ResultVektor.capacity - sizeField
    override fun availableRead() = sizeField - posField
    override fun copy(chunkFrom: ResultChunkBase, count: Int) {
Coverage.funStart(531)
        posField += count
Coverage.statementStart(532)
    }
    override fun copy(columnsTo: Array<Variable>, chunkFrom: ResultChunkBase, columnsFrom: Array<Variable>, count: Int) {
Coverage.funStart(533)
        posField += count
Coverage.statementStart(534)
    }
    override fun copy(columnsTo: Array<Variable>, arrFrom: Array<Value>, columnsFrom: Array<Variable>, count: Int) {
Coverage.funStart(535)
        posField += count
Coverage.statementStart(536)
    }
    override fun copyNonNull(columnsTo: Array<Variable>, arrFrom: Array<Value>, columnsFrom: Array<Variable>, arrFromAlternative: Array<Value>, count: Int) {
Coverage.funStart(537)
        posField += count
Coverage.statementStart(538)
    }
    override fun skipPos(columns: Array<Variable>, count: Int) {
Coverage.funStart(539)
        posField += count
Coverage.statementStart(540)
    }
    override fun skipSize(columns: Array<Variable>, count: Int) {
Coverage.funStart(541)
        sizeField += count
Coverage.statementStart(542)
    }
    override fun skipPos(count: Int) {
Coverage.funStart(543)
        posField += count
Coverage.statementStart(544)
    }
    override fun skipSize(count: Int) {
Coverage.funStart(545)
        sizeField += count
Coverage.statementStart(546)
    }
    override fun nextArr(): Array<Value> {
Coverage.funStart(547)
        posField++
Coverage.statementStart(548)
        return emptyArray
    }
}
