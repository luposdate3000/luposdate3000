package lupos.s04arithmetikOperators.noinput
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.resultFlow
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s02buildSyntaxTree.sparql1_1.Aggregation

import lupos.s00misc.EOperatorID
import lupos.s00misc.XMLElement
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.OPBase


class AOPAggregation(val type: Aggregation, val distinct: Boolean, childs: Array<AOPBase>) : AOPBase() {
    override val operatorID = EOperatorID.AOPAggregationID
    override val classname = "AOPAggregation"
    override val children: Array<OPBase> = Array<OPBase>(childs.size) { childs[it] }

    override fun toXMLElement() = super.toXMLElement().addAttribute("distinct", "" + distinct).addAttribute("type", "" + type)

    override fun equals(other: Any?): Boolean {
        if (other !is AOPAggregation)
            return false
        for (i in children.indices) {
            if (!children[i].equals(other.children[i]))
                return false
        }
        if (type != other.type)
            return false
        if (distinct != other.distinct)
            return false
        return true
    }

    var a: AOPConstant? = null
    var count = 0
    var collectMode = true

    override fun calculate(resultSet: ResultSet, resultRow: ResultRow): AOPConstant {
        if (type == Aggregation.COUNT)
            return resultFlow({ this }, { resultRow }, { resultSet }, {
                AOPInteger(count)
            })
        if (!collectMode) {
            if (a == null)
                return resultFlow({ this }, { resultRow }, { resultSet }, {
                    AOPUndef()
                })
            else
                return resultFlow({ this }, { resultRow }, { resultSet }, {
                    a!!
                })
        }
        if (distinct)
            throw resultFlow({ this }, { resultRow }, { resultSet }, {
                Exception("AOPAggregation does not support distinct")
            })
        when (type) {
            Aggregation.SAMPLE -> {
                val b = (children[0] as AOPBase).calculate(resultSet, resultRow)
                a = b
            }
            Aggregation.AVG -> {
                val b = (children[0] as AOPBase).calculate(resultSet, resultRow)
                if (a == null && b is AOPDouble)
                    a = AOPDouble(b.toDouble() / (0.0 + count))
                else if (a == null && b is AOPDecimal)
                    a = AOPDecimal(b.toDouble() / (0.0 + count))
                else if (a == null && b is AOPInteger)
                    a = AOPDecimal(b.toDouble() / (0.0 + count))
                else if (a is AOPDouble || b is AOPDouble)
                    a = AOPDouble(a!!.toDouble() + (b.toDouble() / (0.0 + count)))
                else if (a is AOPDecimal || b is AOPDecimal)
                    a = AOPDecimal(a!!.toDouble() + (b.toDouble() / (0.0 + count)))
                else if (a is AOPInteger || b is AOPInteger)
                    a = AOPDecimal(a!!.toDouble() + (b.toDouble() / (0.0 + count)))
                else
                    throw resultFlow({ this }, { resultRow }, { resultSet }, {
                        Exception("AOPAggregation avg only defined on numberic input")
                    })
            }
            Aggregation.MIN -> {
                val b = (children[0] as AOPBase).calculate(resultSet, resultRow)
                var flag = false
                if (a == null)
                    flag = true
                else if (a is AOPDouble || b is AOPDouble)
                    flag = a!!.toDouble() > b.toDouble()
                else if (a is AOPDecimal || b is AOPDecimal)
                    flag = a!!.toDouble() > b.toDouble()
                else if (a is AOPInteger || b is AOPInteger)
                    flag = a!!.toInt() > b.toInt()
                else
                    throw resultFlow({ this }, { resultRow }, { resultSet }, {
                        Exception("AOPAggregation avg only defined on numeric input")
                    })
                if (flag)
                    a = b
            }
            Aggregation.MAX -> {
                val b = (children[0] as AOPBase).calculate(resultSet, resultRow)
                var flag = false
                if (a == null)
                    flag = true
                else if (a is AOPDouble || b is AOPDouble)
                    flag = a!!.toDouble() < b.toDouble()
                else if (a is AOPDecimal || b is AOPDecimal)
                    flag = a!!.toDouble() < b.toDouble()
                else if (a is AOPInteger || b is AOPInteger)
                    flag = a!!.toInt() < b.toInt()
                else
                    throw resultFlow({ this }, { resultRow }, { resultSet }, {
                        Exception("AOPAggregation avg only defined on numeric input")
                    })
                if (flag)
                    a = b
            }
            Aggregation.SUM -> {
                val b = (children[0] as AOPBase).calculate(resultSet, resultRow)
                if (a == null)
                    a = b
                else if (a is AOPDouble || b is AOPDouble)
                    a = AOPDouble(a!!.toDouble() + b.toDouble())
                else if (a is AOPDecimal || b is AOPDecimal)
                    a = AOPDecimal(a!!.toDouble() + b.toDouble())
                else if (a is AOPInteger || b is AOPInteger)
                    a = AOPInteger(a!!.toInt() + b.toInt())
                else
                    throw resultFlow({ this }, { resultRow }, { resultSet }, {
                        Exception("AOPAggregation avg only defined on numeric input")
                    })
            }
            else -> throw resultFlow({ this }, { resultRow }, { resultSet }, {
                Exception("AOPAggregation ${type} not implemented")
            })
        }
        return resultFlow({ this }, { resultRow }, { resultSet }, {
            a!!
        })
    }
}
