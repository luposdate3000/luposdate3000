package lupos.s04arithmetikOperators.singleinput
import lupos.s03resultRepresentation.*
import lupos.s04arithmetikOperators.noinput.*

import lupos.s00misc.XMLElement
import lupos.s02buildSyntaxTree.sparql1_1.*
import lupos.s04arithmetikOperators.*
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.OPBase


class AOPBuiltInCall(var function: BuiltInFunctions, childs: List<AOPBase>) : AOPBase() {
    override val children: Array<OPBase> = Array(childs.size) { childs[it] }
    override fun getProvidedVariableNames(): List<String> {
        return mutableListOf<String>()
    }

    override fun getRequiredVariableNames(): List<String> {
        return mutableListOf<String>()
    }

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("AOPBuiltInCall")
        res.addAttribute("function", "" + function)
        res.addContent(childrenToXML())
        return res
    }

    override fun equals(other: Any?): Boolean {
        if (other !is AOPBuiltInCall)
            return false
        for (i in children.indices) {
            if (!children[i].equals(other.children[i]))
                return false
        }
        if (function != other.function)
            return false
        return true
    }
override fun calculate(resultSet: ResultSet, resultRow: ResultRow): AOPConstant{
    TODO("not implemented")
}
}
