package lupos.s09physicalOperators.singleinput

import kotlin.jvm.JvmField
import kotlinx.coroutines.channels.Channel
import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.ELoggerType
import lupos.s00misc.EOperatorID
import lupos.s00misc.GlobalLogger
import lupos.s00misc.resultFlowConsume
import lupos.s00misc.resultFlowProduce
import lupos.s00misc.Trace
import lupos.s03resultRepresentation.*
import lupos.s03resultRepresentation.ResultChunk
import lupos.s03resultRepresentation.ResultSet
import lupos.s03resultRepresentation.Variable
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04arithmetikOperators.ResultVektorRaw
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.ResultIterator
import lupos.s09physicalOperators.POPBase


class POPBind(query: Query, @JvmField val name: AOPVariable, value: AOPBase, child: OPBase) : POPBase(query, EOperatorID.POPBindID, "POPBind", ResultSet(query.dictionary), arrayOf(child, value)) {
    override fun toSparql(): String {
        if (children[1] is AOPConstant && (children[1] as AOPConstant).value is ValueUndef)
            return children[0].toSparql()
        var res = "{SELECT "
        for (v in children[0].getProvidedVariableNames())
            res += AOPVariable(query, v).toSparql() + " "
        res += "(" + children[1].toSparql() + " as " + name.toSparql() + "){"
        res += children[0].toSparql()
        res += "}}"
        return res
    }

    override fun cloneOP() = POPBind(query, name, children[1].cloneOP() as AOPBase, children[0].cloneOP())
    override fun equals(other: Any?): Boolean = other is POPBind && name == other.name && children[0] == other.children[0]
    override fun childrenToVerifyCount(): Int = 1
    override fun getProvidedVariableNames(): List<String> = (children[0].getProvidedVariableNames() + name.name).distinct()
    override fun getRequiredVariableNames(): List<String> = children[1].getRequiredVariableNames()
    override fun toXMLElement() = super.toXMLElement().addAttribute("name", name.name)
    override fun evaluate() = Trace.trace<ResultIterator>({ "POPBind.evaluate" }, {
        val child = children[0].evaluate()
        var variables = children[0].getProvidedVariableNames().map { Pair(children[0].resultSet.createVariable(it), resultSet.createVariable(it)) }
        var variableNew = resultSet.createVariable(name.name)
        val res = ResultIterator()
        res.next = {
            Trace.traceSuspend<ResultChunk>({ "POPBind.next" }, {
                val inbuf = resultFlowConsume({ this@POPBind }, { children[0] }, { child.next() })
                val outbuf = ResultChunk(resultSet)
                try {
                    val col = outbuf.getColumn(variableNew)
                    for (v in variables)
                        outbuf.setColumn(v.second, inbuf.getColumn(v.first))
                    val vektor = (children[1] as AOPBase).calculate(children[0].resultSet, inbuf)
                    for (i in inbuf.pos until inbuf.size)
                        col.data[i] = resultSet.createValue(vektor.data[i])
                    outbuf.size = inbuf.size
                    outbuf.pos = inbuf.pos
                } catch (e: Throwable) {
                    e.printStackTrace()
res.close()
res.next.invoke()
                }
                resultFlowProduce({ this@POPBind }, { outbuf })
            })
        }
        res.close = {
            child.close()
            res._close()
        }
        return res
    })
}
