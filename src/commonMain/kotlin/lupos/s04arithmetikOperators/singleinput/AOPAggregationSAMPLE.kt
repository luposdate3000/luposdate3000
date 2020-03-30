package lupos.s04arithmetikOperators.singleinput

import kotlin.jvm.JvmField
import lupos.s00misc.*
import lupos.s02buildSyntaxTree.sparql1_1.Aggregation
import lupos.s03resultRepresentation.*
import lupos.s04arithmetikOperators.*
import lupos.s04arithmetikOperators.AOPAggregationBase
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04logicalOperators.iterator.*
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query

class AOPAggregationSAMPLE(query: Query, @JvmField val distinct: Boolean, childs: Array<AOPBase>) : AOPAggregationBase(query, EOperatorID.AOPAggregationSAMPLEID, "AOPAggregationSAMPLE", Array(childs.size) { childs[it] }) {
    override fun toXMLElement() = super.toXMLElement().addAttribute("distinct", "" + distinct)
    override fun toSparql(): String {
        if (distinct)
            return "SAMPLE(DISTINCT " + children[0].toSparql() + ")"
        return "SAMPLE(" + children[0].toSparql() + ")"
    }

    override fun equals(other: Any?): Boolean {
        if (other !is AOPAggregationSAMPLE)
            return false
        for (i in children.indices) {
            if (children[i] != other.children[i])
                return false
        }
        if (distinct != other.distinct)
            return false
        return true
    }

    override fun evaluate(row: ColumnIteratorRow): () -> ValueDefinition {
        val value = a.get()!!
        for (i in 0 until resultChunk.availableRead())
            res = value
        res
    }

    override fun calculate(resultSet: ResultSet, resultRow: ResultRow) {
        val child = children[0] as AOPVariable
        val variable = resultSet.createVariable(child.name)
        val b = resultSet.getValueObject(resultRow, variable)
        a.set(b)
    }

    override fun cloneOP() = AOPAggregationSAMPLE(query, distinct, Array(children.size) { (children[it].cloneOP()) as AOPBase })
}
