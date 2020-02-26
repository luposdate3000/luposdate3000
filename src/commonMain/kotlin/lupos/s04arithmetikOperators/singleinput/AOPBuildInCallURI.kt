package lupos.s04arithmetikOperators.singleinput

import lupos.s00misc.EOperatorID
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.*
import lupos.s04arithmetikOperators.*
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.OPBase


class AOPBuildInCallURI(child: AOPBase, var prefix: String = "") : AOPBase() {
    override val operatorID = EOperatorID.AOPBuildInCallURIID
    override val classname = "AOPBuildInCallURI"
    override val children: Array<OPBase> = arrayOf(child)

    override fun applyPrefix(prefix: String, iri: String) {
        if (prefix == "")
            this.prefix = iri
    }

    override fun toXMLElement() = super.toXMLElement().addAttribute("prefix", prefix)

    override fun equals(other: Any?): Boolean {
        if (other !is AOPBuildInCallURI)
            return false
        return children[0] == other.children[0]
    }

    override fun calculate(resultSet: ResultSet, resultRow: ResultRow): AOPConstant {
        val a = (children[0] as AOPBase).calculate(resultSet, resultRow)
        if (a is AOPSimpleLiteral)
            return resultFlow({ this }, { resultRow }, { resultSet }, {
                AOPIri(prefix + a.content)
            })
        throw resultFlow({ this }, { resultRow }, { resultSet }, {
            Exception("AOPBuiltInCall URI only works with simple string input")
        })
    }
}
