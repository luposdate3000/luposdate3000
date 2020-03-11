package lupos.s05tripleStore

import kotlin.jvm.JvmField
import kotlinx.coroutines.channels.Channel
import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.EIndexPattern
import lupos.s00misc.EOperatorID
import lupos.s00misc.Trace
import lupos.s03resultRepresentation.*
import lupos.s03resultRepresentation.ResultSet
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.Variable
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.ResultIterator


class TripleStoreIteratorLocalFilter(query: Query, resultSet: ResultSet, store: TripleStoreLocal, index: EIndexPattern) : TripleStoreIteratorLocal(query, resultSet, store, index, EOperatorID.TripleStoreIteratorLocalFilterID, "TripleStoreIteratorLocalFilter") {

    override fun cloneOP() = TripleStoreIteratorLocalFilter(query, resultSet, store, index)

    override fun evaluate() = Trace.trace<ResultIterator>({ "TripleStoreIteratorLocalFilter.evaluate" }, {
        val newVariables = arrayOfNulls<Variable?>(3)
        for (i in 0 until 3)
            if (params[i] is AOPVariable)
                newVariables[i] = resultSet.createVariable((params[i] as AOPVariable).name)
        val channel = Channel<ResultRow>(CoroutinesHelper.channelType)
        CoroutinesHelper.run {
            try {
                store.forEach(params, { it ->
                    val result = resultSet.createResultRow()
                    for (i in 0 until 3)
                        if (newVariables[i] != null)
                            resultSet.setValue(result, newVariables[i]!!, store.resultSet.getValueObject(it[i]))
                    channel.send(result)
                }, index)
                channel.close()
            } catch (e: Throwable) {
                channel.close(e)
            }
        }
        return ResultIterator(next = {
            channel.receive()
        }, close = {
            channel.close()
        })
    })
}
