package lupos.s09physicalOperators.singleinput

import kotlin.jvm.JvmField
import kotlinx.coroutines.channels.Channel
import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.Coverage
import lupos.s00misc.EOperatorID
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.*
import lupos.s03resultRepresentation.Variable
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04logicalOperators.iterator.*
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s09physicalOperators.POPBase

class POPProjection(query: Query, projectedVariables: List<String>, @JvmField val variables: MutableList<AOPVariable>, child: OPBase) : POPBase(query, projectedVariables, EOperatorID.POPProjectionID, "POPProjection", arrayOf(child)) {
    override fun toSparql(): String {
        var res = "{SELECT "
        for (c in variables) {
            res += c.toSparql() + " "
        }
        res += "{"
        res += children[0].toSparql()
        res += "}}"
        return res
    }

    override fun cloneOP() = POPProjection(query, projectedVariables, variables, children[0].cloneOP())
    override fun equals(other: Any?): Boolean = other is POPProjection && variables.equals(other.variables) && children[0] == other.children[0]
    override fun getProvidedVariableNamesInternal(): List<String> = MutableList(variables.size) { variables[it].name }.distinct()
    override fun getRequiredVariableNames(): List<String> = MutableList(variables.size) { variables[it].name }.distinct()
    override suspend fun evaluate(): ColumnIteratorRow {
        val variables = getProvidedVariableNames()
        val child = children[0].evaluate()
        val outMap = mutableMapOf<String, ColumnIterator>()
        if (variables.containsAll(children[0].getProvidedVariableNames())) {
            return child
        }
        if (variables.size == 0) {
            val variables2 = children[0].getProvidedVariableNames()
            require(variables2.size > 0)
            for (variable in variables2) {
                require(child.columns[variable] != null, { "$variable $uuid" })
            }
            val column = child.columns[variables2[0]]!!
            val res = ColumnIteratorRow(outMap)
            res.hasNext = {
                column.next() != null
            }
            return res
        } else {
            for (variable in variables) {
                require(child.columns[variable] != null, { "$variable $uuid" })
                outMap[variable] = ColumnIteratorDebug(uuid, variable, child.columns[variable]!!)
            }
            return ColumnIteratorRow(outMap)
        }
    }

    override fun toXMLElement(): XMLElement {
        val res = super.toXMLElement()
        val vars = XMLElement("variables")
        res.addContent(vars)
        for (v in variables) {
            vars.addContent(XMLElement("variable").addAttribute("name", v.name))
        }
        return res
    }
}
