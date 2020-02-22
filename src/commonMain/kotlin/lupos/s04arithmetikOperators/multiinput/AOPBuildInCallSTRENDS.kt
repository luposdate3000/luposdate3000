package lupos.s04arithmetikOperators.singleinput

import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.*
import lupos.s04arithmetikOperators.*
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.OPBase


class AOPBuildInCallSTRENDS(child: AOPBase, childB: AOPBase) : AOPBase() {
    override val children: Array<OPBase> = arrayOf(child, childB)

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("AOPBuildInCallSTRENDS")
        res.addContent(childrenToXML())
        return res
    }

    override fun equals(other: Any?): Boolean {
        if (other !is AOPBuildInCallSTRENDS)
            return false
        return children[0].equals(other.children[0])
    }

    override fun calculate(resultSet: ResultSet, resultRow: ResultRow): AOPConstant {
        val a = (children[0] as AOPBase).calculate(resultSet, resultRow)
        if (a is AOPConstantString) {
            val b = (children[1] as AOPBase).calculate(resultSet, resultRow)
            if (b is AOPSimpleLiteral)
                return addMicroTest(this, resultRow, resultSet, AOPBoolean(a.content.endsWith(b.content)))
            else
                throw addMicroTest(this, resultRow, resultSet, Exception("AOPBuiltInCall STRENDS only works with simple compare string input"))
        }
        throw addMicroTest(this, resultRow, resultSet, Exception("AOPBuiltInCall STRENDS only works with string input"))
    }
}
