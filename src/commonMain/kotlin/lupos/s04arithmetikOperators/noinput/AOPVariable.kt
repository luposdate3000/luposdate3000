package lupos.s04arithmetikOperators.noinput

import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.*
import lupos.s04arithmetikOperators.*
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.OPBase


class AOPVariable(var name: String) : AOPBase() {
    override val children: Array<OPBase> = arrayOf()

    override fun toTestCaseInput() = "AOPVariable(\"$name\")"

    companion object {
        fun calculate(tmp: String?): AOPConstant {
            if (tmp == null || tmp.length == 0)
                return AOPUndef()
            when {
                tmp.startsWith("_:") -> return AOPBnode(tmp.substring(2, tmp.length))
                tmp.endsWith("^^<http://www.w3.org/2001/XMLSchema#integer>") -> return AOPInteger(tmp.substring(1, tmp.length - 1 - "^^<http://www.w3.org/2001/XMLSchema#integer>".length).toInt())
                tmp.endsWith("^^<http://www.w3.org/2001/XMLSchema#decimal>") -> return AOPDecimal(tmp.substring(1, tmp.length - 1 - "^^<http://www.w3.org/2001/XMLSchema#decimal>".length).toDouble())
                tmp.endsWith("^^<http://www.w3.org/2001/XMLSchema#double>") -> return AOPDouble(tmp.substring(1, tmp.length - 1 - "^^<http://www.w3.org/2001/XMLSchema#double>".length).toDouble())
                tmp.endsWith("^^<http://www.w3.org/2001/XMLSchema#boolean>") -> return AOPBoolean(tmp.substring(1, tmp.length - 1 - "^^<http://www.w3.org/2001/XMLSchema#boolean>".length).toBoolean())
                tmp.endsWith("^^<http://www.w3.org/2001/XMLSchema#dateTime>") -> return AOPDateTime(tmp)
                tmp.startsWith("<") && tmp.endsWith(">") -> return AOPIri(tmp.substring(1, tmp.length - 1))
                !tmp.endsWith("" + tmp.get(0)) -> {
                    val typeIdx = tmp.lastIndexOf("" + tmp.get(0) + "^^<")
                    val langIdx = tmp.lastIndexOf("" + tmp.get(0) + "@")
                    if (tmp.endsWith(">") && typeIdx > 0)
                        return AOPTypedLiteral("" + tmp.get(0), tmp.substring(1, typeIdx), tmp.substring(typeIdx + 4, tmp.length - 1))
                    else if (langIdx > 0)
                        return AOPLanguageTaggedLiteral("" + tmp.get(0), tmp.substring(1, langIdx), tmp.substring(langIdx + 2, tmp.length))
                    else
                        throw Exception("AOPVariable cannot identify type #${tmp}#")
                }
                else -> return AOPSimpleLiteral("" + tmp.get(0), tmp.substring(1, tmp.length - 1))
            }
        }
    }

    override fun getRequiredVariableNames() = listOf(name)

    override fun toXMLElement(): XMLElement {
        return XMLElement("AOPVariable").addAttribute("name", name)
    }

    override fun equals(other: Any?): Boolean {
        if (other !is AOPVariable)
            return false
        if (name != other.name)
            return false
        return true
    }

    override fun calculate(resultSet: ResultSet, resultRow: ResultRow): AOPConstant {
        if (!resultSet.getVariableNames().contains(name))
            return resultFlow(this, resultRow, resultSet) {
                AOPUndef()
            }
        val variable = resultSet.createVariable(name)
        if (resultSet.isUndefValue(resultRow, variable))
            return resultFlow(this, resultRow, resultSet) {
                AOPUndef()
            }
        val tmp = resultSet.getValue(resultRow[variable])!!
        try {
            return resultFlow(this, resultRow, resultSet) {
                calculate(tmp)
            }
        } catch (e: Throwable) {
            throw resultFlow(this, resultRow, resultSet) {
                e
            }
        }
    }
}
