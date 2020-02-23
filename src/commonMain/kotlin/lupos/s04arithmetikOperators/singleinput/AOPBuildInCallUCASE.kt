package lupos.s04arithmetikOperators.singleinput

import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.*
import lupos.s04arithmetikOperators.*
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.OPBase


class AOPBuildInCallUCASE(child: AOPBase) : AOPBase() {
    override val children: Array<OPBase> = arrayOf(child)

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("AOPBuildInCallUCASE")
        res.addContent(childrenToXML())
        return res
    }

    override fun equals(other: Any?): Boolean {
        if (other !is AOPBuildInCallUCASE)
            return false
        return children[0].equals(other.children[0])
    }

    override fun calculate(resultSet: ResultSet, resultRow: ResultRow): AOPConstant {
        val a = (children[0] as AOPBase).calculate(resultSet, resultRow)
        if(a is AOPLanguageTaggedLiteral)
                return addMicroTest(this, resultRow, resultSet, AOPLanguageTaggedLiteral(a.delimiter,a.content.toUpperCase(),a.language))
        if(a is AOPTypedLiteral)
                return addMicroTest(this, resultRow, resultSet, AOPTypedLiteral(a.delimiter,a.content.toUpperCase(),a.type_iri))
        if(a is AOPSimpleLiteral)
                return addMicroTest(this, resultRow, resultSet, AOPSimpleLiteral(a.delimiter,a.content.toUpperCase()))
        throw addMicroTest(this, resultRow, resultSet, Exception("AOPBuiltInCall UCASE only works with string input"))
    }
}
