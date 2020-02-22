package lupos.s04arithmetikOperators.multiinput

import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.*
import lupos.s04arithmetikOperators.*
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.OPBase


class AOPDivision(childA: AOPBase, childB: AOPBase) : AOPBinaryOperationFixedName() {
    override val children: Array<OPBase> = arrayOf(childA, childB)

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("AOPDivision")
        res.addContent(childrenToXML())
        return res
    }

    override fun equals(other: Any?): Boolean {
        if (other !is AOPDivision)
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
        if (a is AOPDouble || b is AOPDouble) {
            if (b.toDouble() == 0.0)
                throw addMicroTest(this, resultRow, resultSet, Exception("AOPDivision by zero"))
            return addMicroTest(this, resultRow, resultSet, AOPDouble(a.toDouble() / b.toDouble()))
        }
        if (a is AOPDecimal || b is AOPDecimal) {
            if (b.toDouble() == 0.0)
                throw addMicroTest(this, resultRow, resultSet, Exception("AOPDivision by zero"))
            return addMicroTest(this, resultRow, resultSet, AOPDecimal(a.toDouble() / b.toDouble()))
        }
        if (a is AOPInteger || b is AOPInteger) {
            if (b.toInt() == 0)
                throw addMicroTest(this, resultRow, resultSet, Exception("AOPDivision by zero"))
            return addMicroTest(this, resultRow, resultSet, AOPInteger(a.toInt() / b.toInt()))
        }
        throw addMicroTest(this, resultRow, resultSet, Exception("AOPDivision only works with numeric input"))
    }
}
