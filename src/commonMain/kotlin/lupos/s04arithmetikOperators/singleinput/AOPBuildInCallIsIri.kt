package lupos.s04arithmetikOperators.singleinput

import kotlin.jvm.JvmField
import lupos.s00misc.EOperatorID
import lupos.s00misc.resultFlow
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.AOPBoolean
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04arithmetikOperators.noinput.AOPIri
import lupos.s04logicalOperators.OPBase


class AOPBuildInCallIsIri(child: AOPBase) : AOPBase() {
    override val operatorID = EOperatorID.AOPBuildInCallIsIriID
    override val classname = "AOPBuildInCallIsIri"
    override val children: Array<OPBase> = arrayOf(child)

    override fun toSparql() = "isIRI(" + children[0].toSparql() + ")"
    override fun equals(other: Any?): Boolean {
        if (other !is AOPBuildInCallIsIri)
            return false
        return children[0] == other.children[0]
    }

    override fun calculate(resultSet: ResultSet, resultRow: ResultRow): AOPConstant {
        val a = (children[0] as AOPBase).calculate(resultSet, resultRow)
        return resultFlow({ this }, { resultRow }, { resultSet }, {
            AOPBoolean(a is AOPIri)
        })
    }

    override fun cloneOP() = AOPBuildInCallIsIri(children[0].cloneOP() as AOPBase)
}
