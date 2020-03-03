package lupos.s04arithmetikOperators.singleinput

import lupos.s00misc.*
import lupos.s00misc.resultFlow
import lupos.s02buildSyntaxTree.sparql1_1.Aggregation
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s04arithmetikOperators.*
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04arithmetikOperators.noinput.AOPDecimal
import lupos.s04arithmetikOperators.noinput.AOPDouble
import lupos.s04arithmetikOperators.noinput.AOPInteger
import lupos.s04arithmetikOperators.noinput.AOPUndef
import lupos.s04logicalOperators.OPBase


class AOPAggregationMIN(val distinct: Boolean, childs: Array<AOPBase>) : AOPAggregationBase() {
    override val operatorID = EOperatorID.AOPAggregationMINID
    override val classname = "AOPAggregationMIN"
    override val children: Array<OPBase> = Array(childs.size) { childs[it] }

    override fun toXMLElement() = super.toXMLElement().addAttribute("distinct", "" + distinct)

    override fun equals(other: Any?): Boolean {
        if (other !is AOPAggregationMIN)
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
        if (!collectMode.get()) {
            if (a.get() == null)
                return resultFlow({ this }, { resultRow }, { resultSet }, {
                    AOPUndef()
                })
            else
                return resultFlow({ this }, { resultRow }, { resultSet }, {
                    a.get()!!
                })
        }
        if (distinct)
            throw resultFlow({ this }, { resultRow }, { resultSet }, {
                Exception("AOPAggregationMIN does not support distinct")
            })
        val b = (children[0] as AOPBase).calculate(resultSet, resultRow)
        var flag = false
        if (a.get() == null)
            flag = true
        else if (a.get() is AOPDouble || b is AOPDouble)
            flag = a.get()!!.toDouble() > b.toDouble()
        else if (a.get() is AOPDecimal || b is AOPDecimal)
            flag = a.get()!!.toDouble() > b.toDouble()
        else if (a.get() is AOPInteger || b is AOPInteger)
            flag = a.get()!!.toInt() > b.toInt()
        else
            throw resultFlow({ this }, { resultRow }, { resultSet }, {
                Exception("AOPAggregationMIN avg only defined on numeric input")
            })
        if (flag)
            a.set(b)
        return resultFlow({ this }, { resultRow }, { resultSet }, {
            a.get()!!
        })
    }

    override fun cloneOP() = AOPAggregationMIN(distinct, Array(children.size) { (children[it].cloneOP()) as AOPBase })
}
