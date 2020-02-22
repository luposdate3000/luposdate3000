package lupos.s04arithmetikOperators.singleinput

import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.*
import lupos.s04arithmetikOperators.*
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.OPBase


class AOPBuildInCallURI(child: AOPBase, var prefix: String = "") : AOPBase() {
    override val children: Array<OPBase> = arrayOf(child)

    override fun applyPrefix(prefix: String, iri: String) {
        if (prefix == "")
            this.prefix = iri
    }

    override fun toTestCaseInput() = "AOPBuildInCallURI(${(children[0] as AOPBase).toTestCaseInput()},\"$prefix\")"

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("AOPBuildInCallURI")
        res.addAttribute("prefix", prefix)
        res.addContent(childrenToXML())
        return res
    }

    override fun equals(other: Any?): Boolean {
        if (other !is AOPBuildInCallURI)
            return false
        return children[0].equals(other.children[0])
    }

    override fun calculate(resultSet: ResultSet, resultRow: ResultRow): AOPConstant {
        val a = (children[0] as AOPBase).calculate(resultSet, resultRow)
        if (a is AOPSimpleLiteral)
            return addMicroTest(this, resultRow, resultSet, AOPIri(prefix + a.content))
        throw addMicroTest(this, resultRow, resultSet, Exception("AOPBuiltInCall URI only works with simple string input"))
    }
}
