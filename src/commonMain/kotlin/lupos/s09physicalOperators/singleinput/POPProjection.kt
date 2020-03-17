package lupos.s09physicalOperators.singleinput

import kotlin.jvm.JvmField
import kotlinx.coroutines.channels.Channel
import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.EOperatorID
import lupos.s00misc.resultFlowConsume
import lupos.s00misc.resultFlowProduce
import lupos.s00misc.Trace
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.*
import lupos.s03resultRepresentation.ResultChunk
import lupos.s03resultRepresentation.ResultSet
import lupos.s03resultRepresentation.Variable
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04arithmetikOperators.ResultVektorRaw
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.ResultIterator
import lupos.s09physicalOperators.POPBase


class POPProjection(query: Query, @JvmField val variables: MutableList<AOPVariable>, child: OPBase) : POPBase(query, EOperatorID.POPProjectionID, "POPProjection", ResultSet(query.dictionary), arrayOf(child)) {
    override fun toSparql(): String {
        var res = "{SELECT "
        for (c in variables)
            res += c.toSparql() + " "
        res += "{"
        res += children[0].toSparql()
        res += "}}"
        return res
    }

    override fun cloneOP() = POPProjection(query, variables, children[0].cloneOP())
    override fun equals(other: Any?): Boolean = other is POPProjection && variables.equals(other.variables) && children[0] == other.children[0]
    override fun getProvidedVariableNames(): List<String> = MutableList(variables.size) { variables[it].name }.distinct()
    override fun getRequiredVariableNames(): List<String> = MutableList(variables.size) { variables[it].name }.distinct()

    override fun evaluate() = Trace.trace<ResultIterator>({ "POPProjection.evaluate" }, {
        var variablesNew = variables.map { Pair(children[0].resultSet.createVariable(it.name), resultSet.createVariable(it.name)) }
        val child = children[0].evaluate()
        val res = ResultIterator()
        res.close = {
            child.close()
            res._close()
        }
        res.next = {
            Trace.traceSuspend<ResultChunk>({ "POPProjection.next" }, {
                val inbuf = resultFlowConsume({ this@POPProjection }, { children[0] }, { child.next() })
                val outbuf = ResultChunk(resultSet)
                for (v in variablesNew)
                    outbuf.setColumn(v.second, inbuf.getColumn(v.first))
                resultFlowProduce({ this@POPProjection }, { outbuf })
            })
        }
        return res
    })

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("POPProjection")
        res.addAttribute("uuid", "" + uuid)
        val vars = XMLElement("variables")
        res.addContent(vars)
        for (v in variables)
            vars.addContent(XMLElement("variable").addAttribute("name", v.name))
        res.addContent(childrenToXML())
        return res
    }
}
