package lupos.s04arithmetikOperators.singleinput

import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.*
import lupos.s04arithmetikOperators.*
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.OPBase


class AOPBuildInCallIRI(child: AOPBase, var prefix: String = "") : AOPBase() {
    override val children: Array<OPBase> = arrayOf(child)

    override fun applyPrefix(prefix: String, iri: String) {
        if (prefix == "")
            this.prefix = iri
    }

    override fun toTestCaseInput() = "AOPBuildInCallIRI(${(children[0] as AOPBase).toTestCaseInput()},\"$prefix\")"

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("AOPBuildInCallIRI")
        res.addAttribute("prefix", prefix)
        res.addContent(childrenToXML())
        return res
    }

    override fun equals(other: Any?): Boolean {
        if (other !is AOPBuildInCallIRI)
            return false
        return children[0].equals(other.children[0])
    }

    override fun calculate(resultSet: ResultSet, resultRow: ResultRow): AOPConstant {
        val a = (children[0] as AOPBase).calculate(resultSet, resultRow)
        if (a is AOPSimpleLiteral)
            return addMicroTest(this, resultRow, resultSet, AOPIri(prefix+a.content))
        throw addMicroTest(this, resultRow, resultSet, Exception("AOPBuiltInCall IRI only works with simple string input"))
    }
}
