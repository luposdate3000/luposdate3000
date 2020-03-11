package lupos.s09physicalOperators.noinput

import kotlin.jvm.JvmField
import kotlinx.coroutines.channels.Channel
import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.EOperatorID
import lupos.s00misc.resultFlowProduce
import lupos.s00misc.Trace
import lupos.s03resultRepresentation.*
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s09physicalOperators.POPBase


class POPEmptyRow(query: Query) : POPBase(query, EOperatorID.POPEmptyRowID, "POPEmptyRow", ResultSet(query.dictionary), arrayOf()) {
    @JvmField
    var first = true

    override fun cloneOP() = POPEmptyRow(query)

    override fun toSparql() = "{}"

    override fun equals(other: Any?): Boolean {
        if (other !is POPEmptyRow)
            return false
        for (i in children.indices) {
            if (!children[i].equals(other.children[i]))
                return false
        }
        return true
    }

    override fun evaluate() = Trace.trace<ResultIterator>({ "POPEmptyRow.evaluate" }, {
        val channel = Channel<ResultRow>(CoroutinesHelper.channelType)
        CoroutinesHelper.run {
            try {
                channel.send(resultFlowProduce({ this@POPEmptyRow }, { resultSet.createResultRow() }))
                channel.close()
            } catch (e: Throwable) {
                channel.close(e)
            }
        }
return ResultIterator(next={
try{
channel.next()
}catch(e:Throwable){
null
}
},close={
channel.close()
})
    })

}
