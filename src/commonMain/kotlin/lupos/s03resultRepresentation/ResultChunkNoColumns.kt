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
        posField++
        return resultSet.createResultRow()
    }
    override fun availableWrite() = ResultVektor.capacity - sizeField
    override fun availableRead() = sizeField - posField
    override fun copy(chunkFrom: ResultChunkBase, count: Int) {
        posField += count
    }
    override fun copy(columnsTo: Array<Variable>, chunkFrom: ResultChunkBase, columnsFrom: Array<Variable>, count: Int) {
        posField += count
    }
    override fun copy(columnsTo: Array<Variable>, arrFrom: Array<Value>, columnsFrom: Array<Variable>, count: Int) {
        posField += count
    }
    override fun copyNonNull(columnsTo: Array<Variable>, arrFrom: Array<Value>, columnsFrom: Array<Variable>, arrFromAlternative: Array<Value>, count: Int) {
        posField += count
    }
    override fun skipPos(columns: Array<Variable>, count: Int) {
        posField += count
    }
    override fun skipSize(columns: Array<Variable>, count: Int) {
        sizeField += count
    }
    override fun skipPos(count: Int) {
        posField += count
    }
    override fun skipSize(count: Int) {
        sizeField += count
    }
    override fun nextArr(): Array<Value> {
        posField++
        return emptyArray
    }
}
