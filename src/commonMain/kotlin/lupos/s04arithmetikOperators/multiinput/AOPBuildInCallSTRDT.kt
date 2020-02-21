package lupos.s04arithmetikOperators.singleinput

import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.*
import lupos.s04arithmetikOperators.*
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.OPBase


class AOPBuildInCallSTRDT(child: AOPBase,childB:AOPBase) : AOPBase() {
    override val children: Array<OPBase> = arrayOf(child,childB)

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("AOPBuildInCallSTRDT")
        res.addContent(childrenToXML())
        return res
    }

    override fun equals(other: Any?): Boolean {
        if (other !is AOPBuildInCallSTRDT)
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
        if (a is AOPSimpleLiteral && b is AOPSimpleLiteral)
            return AOPTypedLiteral(a.delimiter, a.content, b.content)
        throw Exception("AOPBuiltInCall STRDT only works with simple string input")
    }
}
