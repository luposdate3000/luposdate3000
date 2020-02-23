package lupos.s04arithmetikOperators.singleinput

import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.*
import lupos.s04arithmetikOperators.*
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.OPBase


class AOPBuildInCallLCASE(child: AOPBase) : AOPBase() {
    override val children: Array<OPBase> = arrayOf(child)

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("AOPBuildInCallLCASE")
        res.addContent(childrenToXML())
        return res
    }

    override fun equals(other: Any?): Boolean {
        if (other !is AOPBuildInCallLCASE)
            return false
        return children[0].equals(other.children[0])
    }

    override fun calculate(resultSet: ResultSet, resultRow: ResultRow): AOPConstant {
        val a = (children[0] as AOPBase).calculate(resultSet, resultRow)
        if (a is AOPLanguageTaggedLiteral)
            return resultFlow(this, resultRow, resultSet) {
                AOPLanguageTaggedLiteral(a.delimiter, a.content.toLowerCase(), a.language)
            }
        if (a is AOPTypedLiteral)
            return resultFlow(this, resultRow, resultSet) {
                AOPTypedLiteral(a.delimiter, a.content.toLowerCase(), a.type_iri)
            }
        if (a is AOPSimpleLiteral)
            return resultFlow(this, resultRow, resultSet) {
                AOPSimpleLiteral(a.delimiter, a.content.toLowerCase())
            }
        throw resultFlow(this, resultRow, resultSet) {
            Exception("AOPBuiltInCall LCASE only works with string input")
        }
    }
}
