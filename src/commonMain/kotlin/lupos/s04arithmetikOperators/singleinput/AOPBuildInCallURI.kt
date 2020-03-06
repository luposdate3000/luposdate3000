package lupos.s04arithmetikOperators.singleinput

import kotlin.jvm.JvmField
import lupos.s00misc.EOperatorID
import lupos.s00misc.resultFlow
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04arithmetikOperators.noinput.AOPIri
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04logicalOperators.OPBase


class AOPBuildInCallURI(child: AOPBase, @JvmField var prefix: String = "") : AOPBase() {
    override val operatorID = EOperatorID.AOPBuildInCallURIID
    override val classname = "AOPBuildInCallURI"
    override val children: Array<OPBase> = arrayOf(child)

    override fun toSparql() = "URI(" + children[0].toSparql() + ")"
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
        if (a is AOPIri)
            return resultFlow({ this }, { resultRow }, { resultSet }, {
                a
            })
      if (a is AOPSimpleLiteral||a is AOPTypedLiteral && a.type_iri=="http://www.w3.org/2001/XMLSchema#string")
            return resultFlow({ this }, { resultRow }, { resultSet }, {
val b=a as AOPConstantString
                if (prefix != "" && !prefix.endsWith("/"))
                    AOPIri(prefix + "/" + b.content)
                else
                    AOPIri(prefix + b.content)
            })
        throw resultFlow({ this }, { resultRow }, { resultSet }, {
            Exception("AOPBuiltInCall URI only works with simple string input")
        })
    }

    override fun cloneOP() = AOPBuildInCallURI(children[0].cloneOP() as AOPBase, prefix)
}
