package lupos.s09physicalOperators.singleinput

import kotlin.jvm.JvmField
import kotlinx.coroutines.channels.Channel
import lupos.s00misc.*
import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.ELoggerType
import lupos.s00misc.EOperatorID
import lupos.s00misc.GlobalLogger
import lupos.s00misc.resultFlowConsume
import lupos.s00misc.resultFlowProduce
import lupos.s03resultRepresentation.*
import lupos.s03resultRepresentation.ResultChunk
import lupos.s03resultRepresentation.ResultSet
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.multiinput.*
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04arithmetikOperators.ResultVektorRaw
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.ResultIterator
import lupos.s09physicalOperators.POPBase


class POPFilter(query: Query, filter: AOPBase, child: OPBase) : POPBase(query, EOperatorID.POPFilterID, "POPFilter", child.resultSet, arrayOf(child, filter)) {
    override fun toSparql(): String {
        val sparql = children[0].toSparql()
        if (sparql.startsWith("{SELECT "))
            return "{SELECT * {" + sparql + ". FILTER (" + children[1].toSparql() + ")}}"
        return "{SELECT * {" + sparql + " FILTER (" + children[1].toSparql() + ")}}"
    }

    override fun equals(other: Any?): Boolean = other is POPFilter && children[0] == other.children[0] && children[1] == other.children[1]
    override fun childrenToVerifyCount() = 1
    override fun cloneOP() = POPFilter(query, children[1].cloneOP() as AOPBase, children[0].cloneOP())
    override fun getProvidedVariableNames() = children[0].getProvidedVariableNames()
    override fun getRequiredVariableNames() = children[1].getRequiredVariableNamesRecoursive()
    override fun evaluate() = Trace.trace<ResultIterator>({ "POPFilter.evaluate" }, {
        val child = children[0].evaluate()
        val expression = children[1] as AOPBase
        val res = ResultIterator()
        res.close = {
            child.close()
            res._close()
        }
        if (expression is AOPConstant) {
            if (expression.value.toBoolean())
                return child
            child.close()
            return ResultIterator()
        }
        if (expression is AOPNEQ && (expression.children[0] is AOPConstant || expression.children[0] is AOPVariable) && (expression.children[1] is AOPConstant || expression.children[1] is AOPVariable)) {
            SanityCheck.checkFalse({ expression.children[0] is AOPConstant && expression.children[1] is AOPConstant })
            val childA = expression.children[0]
            val childB = expression.children[1]
            if (childA is AOPConstant || childB is AOPConstant) {
                var constID: Value
                var variableID: Variable
                if (childA is AOPConstant) {
                    constID = resultSet.createValue(childA.value)
                    variableID = resultSet.createVariable((childB as AOPVariable).name)
                } else {
                    constID = resultSet.createValue((childB as AOPConstant).value)
                    variableID = resultSet.createVariable((childA as AOPVariable).name)
                }
                res.next = {
                    Trace.trace<ResultChunk>({ "POPFilter.next" }, {
                        val outbuf = ResultChunk(resultSet)
                        val inbuf = resultFlowConsume({ this@POPFilter }, { children[0] }, { child.next() })
                        for (row in inbuf)
                            if (constID != resultSet.getValue(row, variableID))
                                outbuf.append(row)
                        resultFlowProduce({ this@POPFilter }, { outbuf })
                    })
                }
            } else {
                val variableIDA = resultSet.createVariable((childA as AOPVariable).name)
                val variableIDB = resultSet.createVariable((childB as AOPVariable).name)
                res.next = {
                    Trace.trace<ResultChunk>({ "POPFilter.next" }, {
                        val outbuf = ResultChunk(resultSet)
                        var inbuf = resultFlowConsume({ this@POPFilter }, { children[0] }, { child.next() })
                        for (row in inbuf)
                            if (resultSet.getValue(row, variableIDA) != resultSet.getValue(row, variableIDB))
                                outbuf.append(row)
                        resultFlowProduce({ this@POPFilter }, { outbuf })
                    })
                }
            }
            return res
        }
        res.next = {
            Trace.traceSuspend<ResultChunk>({ "POPFilter.next" }, {
                val outbuf = ResultChunk(resultSet)
                var inbuf = resultFlowConsume({ this@POPFilter }, { children[0] }, { child.next() })
                val resultVektor = expression.calculate(resultSet, inbuf)
                var pos = inbuf.pos
                for (row in inbuf) {
                    try {
                        if (resultVektor.data[pos].toBoolean())
                            outbuf.append(row)
                    } catch (e: Throwable) {
                        e.printStackTrace()
                    }
                    pos++
                }
                resultFlowProduce({ this@POPFilter }, { outbuf })
            })
        }
        return res
    })
}
