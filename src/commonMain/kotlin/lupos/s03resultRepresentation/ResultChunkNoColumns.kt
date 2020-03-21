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
        Coverage.funStart(145)
        posField++
        return resultSet.createResultRow()
    }

    override fun availableWrite() = ResultVektor.capacity - sizeField
    override fun availableRead() = sizeField - posField
    override fun copy(chunkFrom: ResultChunkBase, count: Int) {
        Coverage.funStart(146)
        posField += count
    }

    override fun copy(columnsTo: Array<Variable>, chunkFrom: ResultChunkBase, columnsFrom: Array<Variable>, count: Int) {
        Coverage.funStart(147)
        posField += count
    }

    override fun copy(columnsTo: Array<Variable>, arrFrom: Array<Value>, columnsFrom: Array<Variable>, count: Int) {
        Coverage.funStart(148)
        posField += count
    }

    override fun copyNonNull(columnsTo: Array<Variable>, arrFrom: Array<Value>, columnsFrom: Array<Variable>, arrFromAlternative: Array<Value>, count: Int) {
        Coverage.funStart(149)
        posField += count
    }

    override fun skipPos(columns: Array<Variable>, count: Int) {
        Coverage.funStart(150)
        posField += count
    }

    override fun skipSize(columns: Array<Variable>, count: Int) {
        Coverage.funStart(151)
        sizeField += count
    }

    override fun skipPos(count: Int) {
        Coverage.funStart(152)
        posField += count
    }

    override fun skipSize(count: Int) {
        Coverage.funStart(153)
        sizeField += count
    }

    override fun nextArr(): Array<Value> {
        Coverage.funStart(154)
        posField++
        return emptyArray
    }
}
