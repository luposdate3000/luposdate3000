package lupos.s04arithmetikOperators.multiinput

import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.*
import lupos.s04arithmetikOperators.*
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.OPBase


class AOPLEQ(childA: AOPBase, childB: AOPBase) : AOPBinaryOperationFixedName() {
    override val children: Array<OPBase> = arrayOf(childA, childB)

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("AOPLEQ")
        res.addAttribute("uuid", "" + uuid)
        res.addContent(childrenToXML())
        return res
    }

    override fun equals(other: Any?): Boolean {
        if (other !is AOPLEQ)
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
            return resultFlow({ this }, { resultRow }, { resultSet }, {
                AOPBoolean(a.toDouble() <= b.toDouble())
            })
        if (a is AOPDecimal || b is AOPDecimal)
            return resultFlow({ this }, { resultRow }, { resultSet }, {
                AOPBoolean(a.toDouble() <= b.toDouble())
            })
        if (a is AOPInteger || b is AOPInteger)
            return resultFlow({ this }, { resultRow }, { resultSet }, {
                AOPBoolean(a.toInt() <= b.toInt())
            })
        throw resultFlow({ this }, { resultRow }, { resultSet }, {
            Exception("AOPLEQ only works with numeric input")
        })
    }
}
