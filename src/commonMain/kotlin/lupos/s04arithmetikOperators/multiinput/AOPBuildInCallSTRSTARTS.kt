package lupos.s04arithmetikOperators.singleinput

import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.*
import lupos.s04arithmetikOperators.*
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.OPBase


class AOPBuildInCallSTRSTARTS(child: AOPBase) : AOPBase() {
    override val children: Array<OPBase> = arrayOf(child)

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("AOPBuildInCallSTRSTARTS")
        res.addContent(childrenToXML())
        return res
    }

    override fun equals(other: Any?): Boolean {
        if (other !is AOPBuildInCallSTRSTARTS)
            return false
        return children[0].equals(other.children[0])
    }

    override fun calculate(resultSet: ResultSet, resultRow: ResultRow): AOPConstant {
        val a = (children[0] as AOPBase).calculate(resultSet, resultRow)
        if (a is AOPConstantString) {
            val b = (children[1] as AOPBase).calculate(resultSet, resultRow)
            if (b is AOPSimpleLiteral)
                return AOPBoolean(a.content.startsWith(b.content))
            else
                throw Exception("AOPBuiltInCall STRSTARTS only works with simple compare string input")
        }
        throw Exception("AOPBuiltInCall STRSTARTS only works with string input")
    }
}
