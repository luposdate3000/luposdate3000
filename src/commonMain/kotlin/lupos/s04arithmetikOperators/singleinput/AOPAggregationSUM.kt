package lupos.s04arithmetikOperators.singleinput
import lupos.s04arithmetikOperators.*

import lupos.s00misc.*
import lupos.s00misc.resultFlow
import lupos.s02buildSyntaxTree.sparql1_1.Aggregation
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04arithmetikOperators.noinput.AOPDecimal
import lupos.s04arithmetikOperators.noinput.AOPDouble
import lupos.s04arithmetikOperators.noinput.AOPInteger
import lupos.s04arithmetikOperators.noinput.AOPUndef
import lupos.s04logicalOperators.OPBase


class AOPAggregationSUM(val distinct: Boolean, childs: Array<AOPBase>) : AOPAggregationBase() {
    override val operatorID = EOperatorID.AOPAggregationSUMID
    override val classname = "AOPAggregationSUM"
    override val children: Array<OPBase> = Array(childs.size) { childs[it] }

    override fun toXMLElement() = super.toXMLElement().addAttribute("distinct", "" + distinct)

    override fun equals(other: Any?): Boolean {
        if (other !is AOPAggregationSUM)
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
                Exception("AOPAggregationSUM does not support distinct")
            })
                val b = (children[0] as AOPBase).calculate(resultSet, resultRow)
                if (a.get() == null)
                    a.set(b)
                else if (a.get() is AOPDouble || b is AOPDouble)
                    a.set(AOPDouble(a.get()!!.toDouble() + b.toDouble()))
                else if (a.get() is AOPDecimal || b is AOPDecimal)
                    a.set(AOPDecimal(a.get()!!.toDouble() + b.toDouble()))
                else if (a.get() is AOPInteger || b is AOPInteger)
                    a.set(AOPInteger(a.get()!!.toInt() + b.toInt()))
                else
                    throw resultFlow({ this }, { resultRow }, { resultSet }, {
                        Exception("AOPAggregationSUM avg only defined on numeric input")
                    })
        return resultFlow({ this }, { resultRow }, { resultSet }, {
            a.get()!!
        })
    }

    override fun cloneOP() = AOPAggregationSUM( distinct, Array(children.size) { (children[it].cloneOP()) as AOPBase })
}
