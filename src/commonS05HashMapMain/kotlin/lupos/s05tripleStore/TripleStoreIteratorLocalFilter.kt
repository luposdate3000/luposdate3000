package lupos.s05tripleStore

import kotlin.jvm.JvmField
import kotlinx.coroutines.channels.Channel
import lupos.s00misc.*
import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.EIndexPattern
import lupos.s00misc.EOperatorID
import lupos.s03resultRepresentation.*
import lupos.s03resultRepresentation.ResultChunk
import lupos.s03resultRepresentation.ResultSet
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.Variable
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04arithmetikOperators.ResultVektorRaw
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.ResultIterator

class TripleStoreIteratorLocalFilter(query: Query, resultSet: ResultSet, store: TripleStoreLocal, index: EIndexPattern) : TripleStoreIteratorLocal(query, resultSet, store, index, EOperatorID.TripleStoreIteratorLocalFilterID, "TripleStoreIteratorLocalFilter") {
    override fun cloneOP() = TripleStoreIteratorLocalFilter(query, resultSet, store, index)
    override fun evaluate() = Trace.trace<ResultIterator>({ "TripleStoreIteratorLocal.evaluate" }, {
        //row based
        val newVariables = arrayOfNulls<Variable?>(3)
        var idxHelper = 0
        val key = Array(3) { resultSet.dictionary.undefValue }
        var columns = 0
        var keyidx = 0
        for (i in 0 until 3)
            if (params[i] is AOPVariable)
                newVariables[i] = resultSet.createVariable((params[i] as AOPVariable).name)
            else {
                key[keyidx++] = store.resultSet.dictionary.createValue((params[i] as AOPConstant).value)
                columns++
                if (i == 0) idxHelper += 1
                if (i == 1) idxHelper += 2
                if (i == 2) idxHelper += 4
            }
        require(idxHelper != 0)
        require(idxHelper < 7)//TODO existence test
        val idx = when (idxHelper) {
            1 -> EIndexPattern.S
            2 -> EIndexPattern.P
            3 -> EIndexPattern.SP
            4 -> EIndexPattern.O
            5 -> EIndexPattern.SO
            6 -> EIndexPattern.PO
            else -> EIndexPattern.SPO
        }
        val root = store.getData(key, idx)
        val res = ResultIterator()
        if (root != null) {
            var current = root!!
            res.next = {
                val map = mutableMapOf(query.dictionary.undefValue to query.dictionary.undefValue)
                val outbuf = ResultChunk(resultSet)
                for (i in 0 until current.availableRead()) {
//TODO this can be improved - without the need to iterate over rows
                    val row = current.nextArr()
                    for (i in 0 until columns) {//translate global ids to query local ids
                        val x = map[row[i]]
                        if (x != null) {
                            row[i] = x
                        } else {
                            val y = query.dictionary.createValue(store.resultSet.dictionary.getValue(row[i]))
                            map[row[i]] = y
                            row[i] = y
                        }
                    }
                    outbuf.append(row)
                }
                current = current.next
                if (current == root)
                    res.close()
                resultFlowProduce({ this@TripleStoreIteratorLocalFilter }, { outbuf })
            }
        }
        return res
    })
}
