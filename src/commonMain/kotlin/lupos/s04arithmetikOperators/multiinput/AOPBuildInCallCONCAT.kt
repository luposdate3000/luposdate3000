package lupos.s04arithmetikOperators.singleinput
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04arithmetikOperators.noinput.AOPLanguageTaggedLiteral
import lupos.s04arithmetikOperators.noinput.AOPSimpleLiteral
import lupos.s04arithmetikOperators.addMicroTest
import lupos.s04arithmetikOperators.AOPBase
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet

import lupos.s00misc.XMLElement
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
            if (a.language == b.language) 
                return addMicroTest(this, resultRow, resultSet, AOPLanguageTaggedLiteral(a.delimiter,a.content+b.content,a.language))
             else
                throw addMicroTest(this, resultRow, resultSet, Exception("AOPBuiltInCall CONCAT only works with compatible languages input"))
        }
 if (a is AOPSimpleLiteral && b is AOPSimpleLiteral) 
            return addMicroTest(this, resultRow, resultSet, AOPSimpleLiteral(a.delimiter,a.content+b.content))
          if (a is AOPSimpleLiteral && b is AOPLanguageTaggedLiteral) 
            return addMicroTest(this, resultRow, resultSet, AOPLanguageTaggedLiteral(a.delimiter,a.content+b.content,b.language))
          if (a is AOPLanguageTaggedLiteral && b is AOPSimpleLiteral) 
            return addMicroTest(this, resultRow, resultSet, AOPLanguageTaggedLiteral(a.delimiter,a.content+b.content,a.language))
        throw addMicroTest(this, resultRow, resultSet, Exception("AOPBuiltInCall CONCAT only works with compatible string input"))
    }
}
