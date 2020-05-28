package lupos.s04logicalOperators.noinput
import kotlin.jvm.JvmField
import lupos.s00misc.Coverage
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.Value
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04arithmetikOperators.noinput.AOPValue
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.HistogramResult
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
class LOPValues(query: Query, @JvmField val variables: List<AOPVariable>, values: List<AOPValue>) : LOPBase(query, EOperatorID.LOPValuesID, "LOPValues", Array(values.size) { values[it] }, ESortPriority.PREVENT_ANY) {
    override fun getProvidedVariableNames(): List<String> {
Coverage.funStart(4599)
        return MutableList(variables.size) { variables[it].name }.distinct()
    }
    override fun toXMLElement(): XMLElement {
Coverage.funStart(4600)
        val res = XMLElement("LOPValues").addAttribute("uuid", "" + uuid)
Coverage.statementStart(4601)
        val xmlvariables = XMLElement("LocalVariables")
Coverage.statementStart(4602)
        res.addContent(xmlvariables)
Coverage.statementStart(4603)
        val bindings = XMLElement("LocalBindings")
Coverage.statementStart(4604)
        res.addContent(bindings)
Coverage.statementStart(4605)
        for (v in variables) {
Coverage.forLoopStart(4606)
            xmlvariables.addContent(XMLElement("LocalVariable").addAttribute("name", v.name))
Coverage.statementStart(4607)
        }
Coverage.statementStart(4608)
        bindings.addContent(childrenToXML())
Coverage.statementStart(4609)
        return res
    }
    override fun equals(other: Any?): Boolean {
Coverage.funStart(4610)
        if (other !is LOPValues) {
Coverage.ifStart(4611)
            return false
        }
Coverage.statementStart(4612)
        if (variables != other.variables) {
Coverage.ifStart(4613)
            return false
        }
Coverage.statementStart(4614)
        for (i in children.indices) {
Coverage.forLoopStart(4615)
            if (children[i] != other.children[i]) {
Coverage.ifStart(4616)
                return false
            }
Coverage.statementStart(4617)
        }
Coverage.statementStart(4618)
        return true
    }
    override fun cloneOP() = LOPValues(query, variables, List(children.size) { children[it].cloneOP() as AOPValue })
    override fun calculateHistogram(): HistogramResult {
Coverage.funStart(4619)
        var res = HistogramResult()
Coverage.statementStart(4620)
        var p = getProvidedVariableNames()
Coverage.statementStart(4621)
        for (i in 0 until p.size) {
Coverage.forLoopStart(4622)
            var localSet = mutableSetOf<Value>()
Coverage.statementStart(4623)
            for (row in children) {
Coverage.forLoopStart(4624)
                localSet.add((row.children[i] as AOPConstant).value)
Coverage.statementStart(4625)
            }
Coverage.statementStart(4626)
            res.values[p[i]] = localSet.size
Coverage.statementStart(4627)
        }
Coverage.statementStart(4628)
        res.count = children.size
Coverage.statementStart(4629)
        return res
    }
}
