package lupos.s04arithmetikOperators.multiinput

import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.*
import lupos.s04arithmetikOperators.*
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.OPBase


class AOPGEQ(childA: AOPBase, childB: AOPBase) : AOPBinaryOperationFixedName() {
    override val children: Array<OPBase> = arrayOf(childA, childB)

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("AOPGEQ")
        res.addContent(childrenToXML())
        return res
    }

    override fun equals(other: Any?): Boolean {
        if (other !is AOPGEQ)
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
        if (a is AOPDouble || b is AOPDouble)
            return AOPBooleanLiteral(a.toDouble() >= b.toDouble())
        if (a is AOPDecimal || b is AOPDecimal)
            return AOPBooleanLiteral(a.toDouble() >= b.toDouble())
        if (a is AOPInteger || b is AOPInteger)
            return AOPBooleanLiteral(a.toInt() >= b.toInt())
        throw Exception("AOPGEQ only works with numeric input")
    }
}
