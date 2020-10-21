package lupos.s04logicalOperators.noinput

import kotlin.jvm.JvmField
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s00misc.XMLElement
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04arithmetikOperators.noinput.AOPValue
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.HistogramResult
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.IQuery
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query

class LOPValues(query: IQuery, @JvmField val variables: List<AOPVariable>, values: List<AOPValue>) : LOPBase(query, EOperatorID.LOPValuesID, "LOPValues", Array(values.size) { values[it] }, ESortPriority.PREVENT_ANY) {
    override fun getProvidedVariableNames(): List<String> {
        return MutableList(variables.size) { variables[it].name }.distinct()
    }

    override suspend fun toXMLElement(): XMLElement {
        val res = XMLElement("LOPValues").addAttribute("uuid", "" + uuid)
        val xmlvariables = XMLElement("LocalVariables")
        res.addContent(xmlvariables)
        val bindings = XMLElement("LocalBindings")
        res.addContent(bindings)
        for (v in variables) {
            xmlvariables.addContent(XMLElement("LocalVariable").addAttribute("name", v.name))
        }
        bindings.addContent(childrenToXML())
        return res
    }

    override fun equals(other: Any?) = other is LOPValues && variables == other.variables && children.contentEquals(other.children)
    override fun cloneOP(): IOPBase = LOPValues(query, variables, List(children.size) { children[it].cloneOP() as AOPValue })
    suspend override fun calculateHistogram(): HistogramResult {
        var res = HistogramResult()
        var p = getProvidedVariableNames()
        for (i in 0 until p.size) {
            var localSet = mutableSetOf<Int>()
            for (row in children) {
                localSet.add((row.getChildren()[i] as AOPConstant).value)
            }
            res.values[p[i]] = localSet.size
        }
        res.count = children.size
        return res
    }
}
