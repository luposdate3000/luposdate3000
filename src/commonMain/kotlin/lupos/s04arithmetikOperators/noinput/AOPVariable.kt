package lupos.s04arithmetikOperators.noinput

import kotlin.jvm.JvmField
import lupos.s00misc.EOperatorID
import lupos.s00misc.resultFlow
import lupos.s03resultRepresentation.*
import lupos.s03resultRepresentation.ResultSet
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query


class AOPVariable(query: Query, @JvmField var name: String) : AOPBase(query, EOperatorID.AOPVariableID, "AOPVariable", arrayOf()) {

    override fun toSparql(): String {
        return "?$name".replace("#", "LuposVariable")
    }

    override fun syntaxVerifyAllVariableExists(additionalProvided: List<String>, autocorrect: Boolean) {}

    companion object {
        fun calculate(tmp: String?): ValueDefinition {
            if (tmp == null || tmp.length == 0)
                return ValueUndef()
            when {
                tmp.startsWith("_:") -> return ValueBnode(tmp.substring(2, tmp.length))
                tmp.startsWith("<") && tmp.endsWith(">") -> return ValueIri(tmp.substring(1, tmp.length - 1))
                !tmp.endsWith("" + tmp.get(0)) -> {
                    val typeIdx = tmp.lastIndexOf("" + tmp.get(0) + "^^<")
                    val langIdx = tmp.lastIndexOf("" + tmp.get(0) + "@")
                    if (tmp.endsWith(">") && typeIdx > 0)
                        return ValueTypedLiteral.create("" + tmp.get(0), tmp.substring(1, typeIdx), tmp.substring(typeIdx + 4, tmp.length - 1))
                    else if (langIdx > 0)
                        return ValueLanguageTaggedLiteral("" + tmp.get(0), tmp.substring(1, langIdx), tmp.substring(langIdx + 2, tmp.length))
                    else
                        throw Exception("AOPVariable cannot identify type #${tmp}#")
                }
                else -> return ValueSimpleLiteral("" + tmp.get(0), tmp.substring(1, tmp.length - 1))
            }
        }
    }

    override fun getRequiredVariableNames(): List<String> {
        return listOf(name)
    }

    override fun toXMLElement() = super.toXMLElement().addAttribute("name", name)

    override fun equals(other: Any?): Boolean {
        if (other !is AOPVariable)
            return false
        if (name != other.name)
            return false
        return true
    }

    override fun calculate(resultSet: ResultSet, resultRow: ResultRow): ValueDefinition {
        if (!resultSet.hasVariable(name))
            return resultFlow({ this }, { resultRow }, { resultSet }, {
                ValueUndef()
            })
        return resultFlow({ this }, { resultRow }, { resultSet }, {
            resultSet.getValueObject(resultRow, name)!!
        })
    }

    override fun cloneOP() = this
}
