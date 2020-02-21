package lupos.s04arithmetikOperators.multiinput

import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.*
import lupos.s04arithmetikOperators.*
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.OPBase


class AOPAnd(childA: AOPBase, childB: AOPBase) : AOPBase() {
    override val children: Array<OPBase> = arrayOf(childA, childB)

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("AOPAnd")
        res.addContent(childrenToXML())
        return res
    }

    override fun equals(other: Any?): Boolean {
        if (other !is AOPAnd)
            return false
        for (i in children.indices) {
            if (!children[i].equals(other.children[i]))
                return false
        }
        return true
    }

    override fun calculate(resultSet: ResultSet, resultRow: ResultRow): AOPConstant {
        val a = (children[0] as AOPBase).calculate(resultSet, resultRow)
        val b = (children[1] as AOPBase).calculate(resultSet, resultRow)
        if (a is AOPBooleanLiteral && b is AOPBooleanLiteral)
            return AOPBooleanLiteral(a.value && b.value)
        throw Exception("AOPAnd only works with boolean input")
    }
}
