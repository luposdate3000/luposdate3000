package lupos.s04arithmetikOperators.singleinput

import lupos.s00misc.*
import lupos.s00misc.resultFlow
import lupos.s02buildSyntaxTree.sparql1_1.Aggregation
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s04arithmetikOperators.*
import lupos.s04arithmetikOperators.AOPAggregationBase
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04arithmetikOperators.noinput.AOPDecimal
import lupos.s04arithmetikOperators.noinput.AOPDouble
import lupos.s04arithmetikOperators.noinput.AOPInteger
import lupos.s04arithmetikOperators.noinput.AOPUndef
import lupos.s04logicalOperators.OPBase


class AOPAggregationCOUNT(val distinct: Boolean, childs: Array<AOPBase>) : AOPAggregationBase() {
    override val operatorID = EOperatorID.AOPAggregationCOUNTID
    override val classname = "AOPAggregationCOUNT"
    override val children: Array<OPBase> = Array(childs.size) { childs[it] }

    override fun toXMLElement() = super.toXMLElement().addAttribute("distinct", "" + distinct)

    override fun equals(other: Any?): Boolean {
        if (other !is AOPAggregationCOUNT)
            return false
        for (i in children.indices) {
            if (children[i] != other.children[i])
                return false
        }
        if (distinct != other.distinct)
            return false
        return true
    }


    override fun calculate(resultSet: ResultSet, resultRow: ResultRow): AOPConstant {
        return resultFlow({ this }, { resultRow }, { resultSet }, {
            AOPInteger(count.get())
        })
    }

    override fun cloneOP() = AOPAggregationCOUNT(distinct, Array(children.size) { (children[it].cloneOP()) as AOPBase })
}
