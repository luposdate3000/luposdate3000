package lupos.s09physicalOperators.singleinput

import kotlin.jvm.JvmField
import kotlinx.coroutines.channels.Channel
import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.EOperatorID
import lupos.s00misc.resultFlowConsume
import lupos.s00misc.resultFlowProduce
import lupos.s00misc.Trace
import lupos.s03resultRepresentation.*
import lupos.s03resultRepresentation.ResultChunk
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s03resultRepresentation.Variable
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04arithmetikOperators.ResultVektorRaw
import lupos.s04logicalOperators.noinput.*
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.ResultIterator
import lupos.s09physicalOperators.POPBase


class POPMakeBooleanResult(query: Query, child: OPBase) : POPBase(query, EOperatorID.POPMakeBooleanResultID, "POPMakeBooleanResult", ResultSet(query.dictionary), arrayOf(child)) {
    override fun equals(other: Any?): Boolean = other is POPMakeBooleanResult && children[0] == other.children[0]
    override fun toSparqlQuery() = "ASK{" + children[0].toSparql() + "}"
    override fun cloneOP() = POPMakeBooleanResult(query, children[0].cloneOP())
    override fun getProvidedVariableNames() = mutableListOf("?boolean")
    override fun getRequiredVariableNames() = listOf<String>()

    override fun evaluate() = Trace.trace<ResultIterator>({ "POPMakeBooleanResult.evaluate" }, {
        val variableNew = resultSet.createVariable("?boolean")
        val res = ResultIterator()
        res.next = {
            Trace.traceSuspend<ResultChunk>({ "POPMakeBooleanResult.next" }, {
                val outbuf = ResultChunk(resultSet)
                val child = children[0].evaluate()
                var row = resultSet.createResultRow()
                try {
                    while (resultFlowConsume({ this@POPMakeBooleanResult }, { children[0] }, { child.next() }).availableRead() == 0) {
                    }
                    resultSet.setValue(row, variableNew, ValueBoolean(true).valueToString())
                } catch (e: Throwable) {
                    resultSet.setValue(row, variableNew, ValueBoolean(false).valueToString())
                }
                child.close()
                res.close()
                outbuf.append(row)
                resultFlowProduce({ this@POPMakeBooleanResult }, { outbuf })
            })
        }
        return res
    })

}
