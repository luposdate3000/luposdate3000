package lupos.s04arithmetikOperators.singleinput
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04arithmetikOperators.noinput.AOPIri
import lupos.s04arithmetikOperators.noinput.AOPSimpleLiteral
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.resultFlow
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet

import lupos.s00misc.EOperatorID
import lupos.s00misc.XMLElement
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.OPBase


class AOPBuildInCallIRI(child: AOPBase, var prefix: String = "") : AOPBase() {
    override val operatorID = EOperatorID.AOPBuildInCallIRIID
    override val classname = "AOPBuildInCallIRI"
    override val children: Array<OPBase> = arrayOf(child)

    override fun applyPrefix(prefix: String, iri: String) {
        if (prefix == "")
            this.prefix = iri
    }

    override fun toXMLElement() = super.toXMLElement().addAttribute("prefix", prefix)

    override fun equals(other: Any?): Boolean {
        if (other !is AOPBuildInCallIRI)
            return false
        return children[0] == other.children[0]
    }

    override fun calculate(resultSet: ResultSet, resultRow: ResultRow): AOPConstant {
        val a = (children[0] as AOPBase).calculate(resultSet, resultRow)
        if (a is AOPSimpleLiteral)
            return resultFlow({ this }, { resultRow }, { resultSet }, {
                if (prefix != "" && !prefix.endsWith("/"))
                    AOPIri(prefix + "/" + a.content)
                else
                    AOPIri(prefix + a.content)
            })
        throw resultFlow({ this }, { resultRow }, { resultSet }, {
            Exception("AOPBuiltInCall IRI only works with simple string input")
        })
    }
}
