package lupos.s04arithmetikOperators.noinput

import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.*
import lupos.s04arithmetikOperators.*
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.OPBase


class AOPVariable(var name: String) : AOPBase() {
    override val children: Array<OPBase> = arrayOf()
    override fun getProvidedVariableNames(): List<String> {
        return mutableListOf<String>(name)
    }

    override fun getRequiredVariableNames(): List<String> {
        return mutableListOf<String>(name)
    }

    override fun toXMLElement(): XMLElement {
        return XMLElement("AOPVariable")
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
            return AOPUndef()
        val variable = resultSet.createVariable(name)
        if (resultSet.isUndefValue(resultRow, variable))
            return AOPUndef()
        val tmp = resultSet.getValue(resultRow[variable])!!
        if (tmp.length == 0)
            return AOPUndef()
        when {
            tmp.endsWith("^^<http://www.w3.org/2001/XMLSchema#integer>") -> return AOPInteger(tmp.substring(1, tmp.length - 1 - "^^<http://www.w3.org/2001/XMLSchema#integer>".length).toInt())
            tmp.endsWith("^^<http://www.w3.org/2001/XMLSchema#decimal>") -> return AOPDecimal(tmp.substring(1, tmp.length - 1 - "^^<http://www.w3.org/2001/XMLSchema#decimal>".length).toDouble())
            tmp.endsWith("^^<http://www.w3.org/2001/XMLSchema#double>") -> return AOPDouble(tmp.substring(1, tmp.length - 1 - "^^<http://www.w3.org/2001/XMLSchema#double>".length).toDouble())
            tmp.endsWith("^^<http://www.w3.org/2001/XMLSchema#boolean>") -> return AOPBooleanLiteral(tmp.substring(1, tmp.length - 1 - "^^<http://www.w3.org/2001/XMLSchema#double>".length).toBoolean())
            tmp.endsWith("^^<http://www.w3.org/2001/XMLSchema#dataTime>") -> return AOPDateTime(tmp.substring(1, tmp.length - 1 - "^^<http://www.w3.org/2001/XMLSchema#double>".length))
            !tmp.endsWith("" + tmp.get(0)) -> {
                val typeIdx = tmp.lastIndexOf("" + tmp.get(0) + "^^<")
                val langIdx = tmp.lastIndexOf("" + tmp.get(0) + "@")
                if (tmp.endsWith(">") && typeIdx > 0)
                    return AOPTypedLiteral("" + tmp.get(0), tmp.substring(1, typeIdx), tmp.substring(typeIdx + 4, tmp.length))
                else if (langIdx > 0)
                    return AOPLanguageTaggedLiteral("" + tmp.get(0), tmp.substring(1, langIdx), tmp.substring(langIdx + 2, tmp.length))
                else
                    throw Exception("AOPVariable cannot identify type")
            }
            else -> return AOPSimpleLiteral("" + tmp.get(0), tmp.substring(1, tmp.length - 1))
        }
    }
}
