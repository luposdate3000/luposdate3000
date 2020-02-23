package lupos.s04arithmetikOperators.singleinput
import lupos.s04arithmetikOperators.noinput.AOPBoolean
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04arithmetikOperators.addMicroTest
import lupos.s04arithmetikOperators.AOPBase
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet

import lupos.s00misc.XMLElement
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.OPBase


class AOPBuildInCallIF(child: AOPBase, childA: AOPBase, childB: AOPBase) : AOPBase() {
    override val children: Array<OPBase> = arrayOf(child, childA, childB)

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("AOPBuildInCallIF")
        res.addContent(childrenToXML())
        return res
    }

    override fun equals(other: Any?): Boolean {
        if (other !is AOPBuildInCallIF)
            return false
        return children[0].equals(other.children[0])
    }

    override fun calculate(resultSet: ResultSet, resultRow: ResultRow): AOPConstant {
        val a = (children[0] as AOPBase).calculate(resultSet, resultRow)
        if (a is AOPBoolean) {
            if (a.value)
                return addMicroTest(this, resultRow, resultSet, (children[1] as AOPBase).calculate(resultSet, resultRow))
            else
                return addMicroTest(this, resultRow, resultSet, (children[2] as AOPBase).calculate(resultSet, resultRow))
        }
        throw addMicroTest(this, resultRow, resultSet, Exception("AOPBuiltInCall IF only works with boolean condition"))
    }
}
