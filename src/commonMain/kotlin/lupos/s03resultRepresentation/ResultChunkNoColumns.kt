package lupos.s03resultRepresentation

import lupos.s03resultRepresentation.ResultChunk
import lupos.s04arithmetikOperators.ResultVektorRaw


import lupos.s00misc.Coverage
class ResultChunkNoColumns(resultSet: ResultSet, columns: Int) : ResultChunk(resultSet, columns) {
    val emptyArray = arrayOf<Value>()
    var posField = 0
    var sizeField = 0

    override fun hasNext() = posField < sizeField
    override fun next(): ResultRow {
Coverage.funStart(96)
        posField++
        return resultSet.createResultRow()
    }

    override fun availableWrite() = ResultVektor.capacity - sizeField
    override fun availableRead() = sizeField - posField
    override fun copy(chunkFrom: ResultChunkBase, count: Int) {
Coverage.funStart(97)
        posField += count
    }

    override fun copy(columnsTo: Array<Variable>, chunkFrom: ResultChunkBase, columnsFrom: Array<Variable>, count: Int) {
Coverage.funStart(98)
        posField += count
    }

    override fun copy(columnsTo: Array<Variable>, arrFrom: Array<Value>, columnsFrom: Array<Variable>, count: Int) {
Coverage.funStart(99)
        posField += count
    }

    override fun copyNonNull(columnsTo: Array<Variable>, arrFrom: Array<Value>, columnsFrom: Array<Variable>, arrFromAlternative: Array<Value>, count: Int) {
Coverage.funStart(100)
        posField += count
    }

    override fun skipPos(columns: Array<Variable>, count: Int) {
Coverage.funStart(101)
        posField += count
    }

    override fun skipSize(columns: Array<Variable>, count: Int) {
Coverage.funStart(102)
        sizeField += count
    }

    override fun skipPos(count: Int) {
Coverage.funStart(103)
        posField += count
    }

    override fun skipSize(count: Int) {
Coverage.funStart(104)
        sizeField += count
    }

    override fun nextArr(): Array<Value> {
Coverage.funStart(105)
        posField++
        return emptyArray
    }
}
