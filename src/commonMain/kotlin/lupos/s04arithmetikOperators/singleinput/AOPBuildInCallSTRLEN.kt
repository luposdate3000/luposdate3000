package lupos.s04arithmetikOperators.singleinput

import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.*
import lupos.s04arithmetikOperators.*
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.OPBase


class AOPBuildInCallSTRLEN(child: AOPBase) : AOPBase() {
    override val children: Array<OPBase> = arrayOf(child)

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("AOPBuildInCallSTRLEN")
        res.addAttribute("uuid", "" + uuid)
        res.addContent(childrenToXML())
        return res
    }

    override fun equals(other: Any?): Boolean {
        if (other !is AOPBuildInCallSTRLEN)
            return false
        return children[0].equals(other.children[0])
    }

    override fun calculate(resultSet: ResultSet, resultRow: ResultRow): AOPConstant {
        val a = (children[0] as AOPBase).calculate(resultSet, resultRow)
        if (a is AOPSimpleLiteral)
            return resultFlow({ this }, { resultRow }, { resultSet }, {
                AOPInteger(a.content.length)
            })
        if (a is AOPTypedLiteral)
            return resultFlow({ this }, { resultRow }, { resultSet }, {
                AOPInteger(a.content.length)
            })
        if (a is AOPLanguageTaggedLiteral)
            return resultFlow({ this }, { resultRow }, { resultSet }, {
                AOPInteger(a.content.length)
            })
        throw resultFlow({ this }, { resultRow }, { resultSet }, {
            Exception("AOPBuiltInCall STRLEN only works with string input")
        })
    }
}
