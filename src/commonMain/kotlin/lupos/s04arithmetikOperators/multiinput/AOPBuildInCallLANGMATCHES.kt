package lupos.s04arithmetikOperators.singleinput

import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.*
import lupos.s04arithmetikOperators.*
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.OPBase


class AOPBuildInCallLANGMATCHES(child: AOPBase, childB: AOPBase) : AOPBase() {
    override val children: Array<OPBase> = arrayOf(child, childB)

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("AOPBuildInCallLANGMATCHES")
        res.addContent(childrenToXML())
        return res
    }

    override fun equals(other: Any?): Boolean {
        if (other !is AOPBuildInCallLANGMATCHES)
            return false
        return children[0].equals(other.children[0])
    }

    override fun calculate(resultSet: ResultSet, resultRow: ResultRow): AOPConstant {
        val a = (children[0] as AOPBase).calculate(resultSet, resultRow)
        val b = (children[1] as AOPBase).calculate(resultSet, resultRow)
        if (a is AOPSimpleLiteral && b is AOPSimpleLiteral)
            return addMicroTest(this, resultRow, resultSet, AOPBoolean(a.content == b.content))
        throw addMicroTest(this, resultRow, resultSet, Exception("AOPBuiltInCall LANGMATCHES only works with simple language string input"))
    }
}
