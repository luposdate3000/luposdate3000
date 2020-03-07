package lupos.s05tripleStore
import lupos.s04logicalOperators.Query

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
import lupos.s04arithmetikOperators.noinput.AOPVariable


class TripleStoreIteratorLocalFilter(resultSet: ResultSet, store: TripleStoreLocal, index: EIndexPattern) : TripleStoreIteratorLocal(resultSet, store, index) {
    override val operatorID = EOperatorID.TripleStoreIteratorLocalFilterID
    override val classname = "TripleStoreIteratorLocalFilter"

    override fun cloneOP() = TripleStoreIteratorLocalFilter(resultSet, store, index)

    override fun evaluate() = Trace.trace<Channel<ResultRow>>({ "TripleStoreIteratorLocalFilter.evaluate" }, {
        val sNew: Variable?
        val pNew: Variable?
        val oNew: Variable?
        if (sparam is AOPVariable)
            sNew = resultSet.createVariable((sparam as AOPVariable).name)
        else
            sNew = null
        if (pparam is AOPVariable)
            pNew = resultSet.createVariable((pparam as AOPVariable).name)
        else
            pNew = null
        if (oparam is AOPVariable)
            oNew = resultSet.createVariable((oparam as AOPVariable).name)
        else
            oNew = null
        val channel = Channel<ResultRow>(CoroutinesHelper.channelType)
        CoroutinesHelper.run {
            try {
                store.forEach(sparam, pparam, oparam, { sv, pv, ov ->
                    val result = resultSet.createResultRow()
                    if (sNew != null)
                        result[sNew] = resultSet.createValue(store.resultSet.getValue(sv))
                    if (pNew != null)
                        result[pNew] = resultSet.createValue(store.resultSet.getValue(pv))
                    if (oNew != null)
                        result[oNew] = resultSet.createValue(store.resultSet.getValue(ov))
                    channel.send(result)
                }, index)
                channel.close()
            } catch (e: Throwable) {
                channel.close(e)
            }
        }
        return channel
    })
}
