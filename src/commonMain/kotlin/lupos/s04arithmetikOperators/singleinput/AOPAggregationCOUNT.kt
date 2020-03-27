package lupos.s04arithmetikOperators.singleinput

import kotlin.jvm.JvmField
import lupos.s00misc.*
import lupos.s02buildSyntaxTree.sparql1_1.Aggregation
import lupos.s03resultRepresentation.*
import lupos.s04arithmetikOperators.*
import lupos.s04arithmetikOperators.AOPAggregationBase
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04arithmetikOperators.ResultVektorRaw
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.ResultIterator

class AOPAggregationCOUNT(query: Query, @JvmField val distinct: Boolean, childs: Array<AOPBase>) : AOPAggregationBase(query, EOperatorID.AOPAggregationCOUNTID, "AOPAggregationCOUNT", Array(childs.size) { childs[it] }) {
    override fun toXMLElement() = super.toXMLElement().addAttribute("distinct", "" + distinct)
    override fun toSparql(): String {
        var res = "COUNT("
        if (distinct)
            res += "DISTINCT "
        if (children.size > 0)
            res += children[0].toSparql()
        res += ")"
        return res
    }

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

    override fun calculate(resultChunk: ResultVektorRaw): ResultVektorRaw {
        val value = ValueInteger(count.get())
        val rVektor = ResultVektorRaw(resultChunk.availableRead())
        for (i in 0 until resultChunk.availableRead())
            rVektor.data[i] = value
        return rVektor
    }

    override fun calculate(resultSet: ResultSet, resultRow: ResultRow) {
    }

    override fun cloneOP() = AOPAggregationCOUNT(query, distinct, Array(children.size) { (children[it].cloneOP()) as AOPBase })
}
