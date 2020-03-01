package lupos.s04arithmetikOperators.singleinput

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


class AOPAggregation(val type: Aggregation, val distinct: Boolean, childs: Array<AOPBase>) : AOPBase() {
    override val operatorID = EOperatorID.AOPAggregationID
    override val classname = "AOPAggregation"
    override val children: Array<OPBase> = Array(childs.size) { childs[it] }

    override fun toXMLElement() = super.toXMLElement().addAttribute("distinct", "" + distinct).addAttribute("type", "" + type)

    override fun equals(other: Any?): Boolean {
        if (other !is AOPAggregation)
            return false
        for (i in children.indices) {
            if (children[i] != other.children[i])
                return false
        }
        if (type != other.type)
            return false
        if (distinct != other.distinct)
            return false
        return true
    }

    var a = ThreadSafeMutableAny<AOPConstant?>(null)
    var count = ThreadSafeMutableAny(0)
    var collectMode = ThreadSafeMutableAny(true)

    override fun calculate(resultSet: ResultSet, resultRow: ResultRow): AOPConstant {
        if (type == Aggregation.COUNT)
            return resultFlow({ this }, { resultRow }, { resultSet }, {
                AOPInteger(count.get())
            })
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
                Exception("AOPAggregation does not support distinct")
            })
        when (type) {
            Aggregation.SAMPLE -> {
                val b = (children[0] as AOPBase).calculate(resultSet, resultRow)
                a.set(b)
            }
            Aggregation.AVG -> {
                val b = (children[0] as AOPBase).calculate(resultSet, resultRow)
                if (a.get() == null && b is AOPDouble)
                    a.set(AOPDouble(b.toDouble() / (0.0 + count.get())))
                else if (a.get() == null && b is AOPDecimal)
                    a.set(AOPDecimal(b.toDouble() / (0.0 + count.get())))
                else if (a.get() == null && b is AOPInteger)
                    a.set(AOPDecimal(b.toDouble() / (0.0 + count.get())))
                else if (a.get() is AOPDouble || b is AOPDouble)
                    a.set(AOPDouble(a.get()!!.toDouble() + (b.toDouble() / (0.0 + count.get()))))
                else if (a.get() is AOPDecimal || b is AOPDecimal)
                    a.set(AOPDecimal(a.get()!!.toDouble() + (b.toDouble() / (0.0 + count.get()))))
                else if (a.get() is AOPInteger || b is AOPInteger)
                    a.set(AOPDecimal(a.get()!!.toDouble() + (b.toDouble() / (0.0 + count.get()))))
                else
                    throw resultFlow({ this }, { resultRow }, { resultSet }, {
                        Exception("AOPAggregation avg only defined on numberic input")
                    })
            }
            Aggregation.MIN -> {
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
                        Exception("AOPAggregation avg only defined on numeric input")
                    })
                if (flag)
                    a.set(b)
            }
            Aggregation.MAX -> {
                val b = (children[0] as AOPBase).calculate(resultSet, resultRow)
                var flag = false
                if (a.get() == null)
                    flag = true
                else if (a.get() is AOPDouble || b is AOPDouble)
                    flag = a.get()!!.toDouble() < b.toDouble()
                else if (a.get() is AOPDecimal || b is AOPDecimal)
                    flag = a.get()!!.toDouble() < b.toDouble()
                else if (a.get() is AOPInteger || b is AOPInteger)
                    flag = a.get()!!.toInt() < b.toInt()
                else
                    throw resultFlow({ this }, { resultRow }, { resultSet }, {
                        Exception("AOPAggregation avg only defined on numeric input")
                    })
                if (flag)
                    a.set(b)
            }
            Aggregation.SUM -> {
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
                        Exception("AOPAggregation avg only defined on numeric input")
                    })
            }
            else -> throw resultFlow({ this }, { resultRow }, { resultSet }, {
                Exception("AOPAggregation $type not implemented")
            })
        }
        return resultFlow({ this }, { resultRow }, { resultSet }, {
            a.get()!!
        })
    }

    override fun cloneOP() = AOPAggregation(type, distinct, Array(children.size) { (children[it].cloneOP()) as AOPBase })
}
