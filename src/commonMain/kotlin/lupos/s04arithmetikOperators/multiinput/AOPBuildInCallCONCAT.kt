package lupos.s04arithmetikOperators.singleinput

import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.*
import lupos.s04arithmetikOperators.*
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.OPBase


class AOPBuildInCallCONCAT(child: AOPBase, childB: AOPBase) : AOPBase() {
    override val children: Array<OPBase> = arrayOf(child, childB)

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("AOPBuildInCallCONCAT")
        res.addContent(childrenToXML())
        return res
    }

    override fun equals(other: Any?): Boolean {
        if (other !is AOPBuildInCallCONCAT)
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
        if (a is AOPLanguageTaggedLiteral && b is AOPLanguageTaggedLiteral) {
            if (a.language == b.language) {
                a.content += b.content
                return addMicroTest(this, resultRow, resultSet, a)
            } else
                throw addMicroTest(this, resultRow, resultSet, Exception("AOPBuiltInCall CONCAT only works with compatible languages input"))
        } else if (a is AOPSimpleLiteral && b is AOPSimpleLiteral) {
            a.content += b.content
            return addMicroTest(this, resultRow, resultSet, a)
        } else if (a is AOPSimpleLiteral && b is AOPLanguageTaggedLiteral) {
            b.content += a.content
            return addMicroTest(this, resultRow, resultSet, b)
        } else if (a is AOPLanguageTaggedLiteral && b is AOPSimpleLiteral) {
            a.content += b.content
            return addMicroTest(this, resultRow, resultSet, a)
        }
        throw addMicroTest(this, resultRow, resultSet, Exception("AOPBuiltInCall CONCAT only works with compatible string input"))
    }
}
