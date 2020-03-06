package lupos.s04arithmetikOperators.singleinput

import kotlin.jvm.JvmField
import lupos.s00misc.EOperatorID
import lupos.s00misc.resultFlow
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.AOPBoolean
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04logicalOperators.OPBase


class AOPNot(@JvmField var child: AOPBase) : AOPBase() {
    override val operatorID = EOperatorID.AOPNotID
    override val classname = "AOPNot"
    override val children: Array<OPBase> = arrayOf(child)

    override fun toSparql() = "!(" + children[0].toSparql() + ")"

    override fun equals(other: Any?): Boolean {
        if (other !is AOPNot)
            return false
        for (i in children.indices) {
            if (children[i] != other.children[i])
                return false
        }
        return true
    }

    override fun calculate(resultSet: ResultSet, resultRow: ResultRow): AOPConstant {
        val a = (children[0] as AOPBase).calculate(resultSet, resultRow)
            return resultFlow({ this }, { resultRow }, { resultSet }, {
                AOPBoolean(!a.toBoolean())
            })
    }

    override fun cloneOP() = AOPNot(children[0].cloneOP() as AOPBase)
}
