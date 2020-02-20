package lupos.s04arithmetikOperators.noinput
import lupos.s03resultRepresentation.*

import lupos.s04arithmetikOperators.noinput.*
import lupos.s00misc.XMLElement
import lupos.s02buildSyntaxTree.sparql1_1.*
import lupos.s04arithmetikOperators.*
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.OPBase


class AOPAggregation(val type: Aggregation, val distinct: Boolean, childs: Array<AOPBase>) : AOPBase() {
    override val children: Array<OPBase> = Array<OPBase>(childs.size) { childs[it] }
    override fun getProvidedVariableNames(): List<String> {
        return mutableListOf<String>()
    }

    override fun getRequiredVariableNames(): List<String> {
        return mutableListOf<String>()
    }

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("AOPAggregation")
        res.addAttribute("type", "" + type)
        res.addAttribute("distinct", "" + distinct)
        res.addContent(childrenToXML())
        return res
    }

    override fun equals(other: Any?): Boolean {
        if (other !is AOPAggregation)
            return false
        for (i in children.indices) {
            if (!children[i].equals(other.children[i]))
                return false
        }
        if (type != other.type)
            return false
        if (distinct != other.distinct)
            return false
        return true
    }
override fun calculate(resultSet: ResultSet, resultRow: ResultRow): AOPConstant{
    TODO("not implemented")
}
}
