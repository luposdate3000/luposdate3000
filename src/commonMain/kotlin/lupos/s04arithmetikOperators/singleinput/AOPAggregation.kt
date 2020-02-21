package lupos.s04arithmetikOperators.noinput

import lupos.s00misc.XMLElement
import lupos.s02buildSyntaxTree.sparql1_1.*
import lupos.s03resultRepresentation.*
import lupos.s04arithmetikOperators.*
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.OPBase


class AOPAggregation(val type: Aggregation, val distinct: Boolean, childs: Array<AOPBase>) : AOPBase() {
    override val children: Array<OPBase> = Array<OPBase>(childs.size) { childs[it] }

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("AOPAggregation")
        res.addAttribute("type", "" + type)
        res.addAttribute("distinct", "" + distinct)
        res.addContent(childrenToXML())
        return res
    }

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
            return AOPInteger(count)
        if (!collectMode) {
            if (a == null)
                return AOPUndef()
            else
                return a!!
        }
        if (distinct)
            throw Exception("AOPAggregation does not support distinct")
        when (type) {
            Aggregation.SAMPLE -> {
                val b = (children[0] as AOPBase).calculate(resultSet, resultRow)
                a = b
            }
            Aggregation.AVG -> {
                val b = (children[0] as AOPBase).calculate(resultSet, resultRow)
                if (a == null)
                    a = b
                else if (a is AOPDouble || b is AOPDouble)
                    a = AOPDouble(a!!.toDouble() + b.toDouble() / count)
                else if (a is AOPDecimal || b is AOPDecimal)
                    a = AOPDouble(a!!.toDouble() + b.toDouble() / count)
                else if (a is AOPInteger || b is AOPInteger)
                    a = AOPDecimal(a!!.toDouble() + b.toDouble() / count)
                else
                    throw Exception("AOPAggregation avg only defined on numberic input")
            }
            Aggregation.MIN -> {
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
                    throw Exception("AOPAggregation avg only defined on numeric input")
                if (flag)
                    a = b
            }
            Aggregation.MAX -> {
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
                    throw Exception("AOPAggregation avg only defined on numeric input")
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
                    throw Exception("AOPAggregation avg only defined on numeric input")
            }
            else -> throw Exception("AOPAggregation ${type} not implemented")
        }
        return a!!
    }
}
