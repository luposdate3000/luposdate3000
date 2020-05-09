package lupos.s04logicalOperators.singleinput
import kotlin.jvm.JvmField
import lupos.s00misc.Coverage
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.MyListValue
import lupos.s03resultRepresentation.nodeGlobalDictionary
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.ValueBnode
import lupos.s03resultRepresentation.ValueBoolean
import lupos.s03resultRepresentation.ValueComparatorASC
import lupos.s03resultRepresentation.ValueComparatorDESC
import lupos.s03resultRepresentation.ValueComparatorFast
import lupos.s03resultRepresentation.ValueDateTime
import lupos.s03resultRepresentation.ValueDecimal
import lupos.s03resultRepresentation.ValueDefinition
import lupos.s03resultRepresentation.ValueDouble
import lupos.s03resultRepresentation.ValueError
import lupos.s03resultRepresentation.ValueInteger
import lupos.s03resultRepresentation.ValueIri
import lupos.s03resultRepresentation.ValueLanguageTaggedLiteral
import lupos.s03resultRepresentation.ValueNumeric
import lupos.s03resultRepresentation.ValueSimpleLiteral
import lupos.s03resultRepresentation.ValueStringBase
import lupos.s03resultRepresentation.ValueTypedLiteral
import lupos.s03resultRepresentation.ValueUndef
import lupos.s04arithmetikOperators.noinput.AOPBuildInCallBNODE0
import lupos.s04arithmetikOperators.noinput.AOPBuildInCallSTRUUID
import lupos.s04arithmetikOperators.noinput.AOPBuildInCallUUID
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04arithmetikOperators.noinput.AOPValue
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query


class LOPProjection(query: Query, @JvmField val variables: MutableList<AOPVariable> = mutableListOf(), child: OPBase = OPNothing(query)) : LOPBase(query, EOperatorID.LOPProjectionID, "LOPProjection", arrayOf(child), ESortPriority.SAME_AS_CHILD) {
    override fun getProvidedVariableNames(): List<String> {
        return MutableList(variables.size) { variables[it].name }.distinct()
    }

    override fun getRequiredVariableNames(): List<String> {
        return MutableList(variables.size) { variables[it].name }.distinct()
    }

    override fun toXMLElement(): XMLElement {
        val res = super.toXMLElement()
        val vars = XMLElement("LocalVariables")
        res.addContent(vars)
        for (v in variables) {
            vars.addContent(XMLElement("LocalVariable").addAttribute("name", v.name))
        }
        return res
    }

    override fun equals(other: Any?): Boolean {
        if (other !is LOPProjection) {
            return false
        }
        if (variables != other.variables) {
            return false
        }
        for (i in children.indices) {
            if (children[i] != other.children[i]) {
                return false
            }
        }
        return true
    }

    override fun cloneOP() = LOPProjection(query, variables, children[0].cloneOP())
}
