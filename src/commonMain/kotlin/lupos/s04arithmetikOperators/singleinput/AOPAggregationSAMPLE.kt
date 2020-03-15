package lupos.s04arithmetikOperators.singleinput
import lupos.s04arithmetikOperators.ResultVektorRaw
import kotlin.jvm.JvmField
import lupos.s00misc.*
import lupos.s00misc.resultFlow
import lupos.s02buildSyntaxTree.sparql1_1.Aggregation
import lupos.s03resultRepresentation.*
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s04arithmetikOperators.*
import lupos.s04arithmetikOperators.AOPAggregationBase
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.ResultIterator


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
 override fun calculate(resultSet: ResultSet, resultChunk: ResultChunk): ResultVektorRaw {
val value=a.get()!!
val rVektor = ResultVektorRaw()
for (i in resultChunk.pos until resultChunk.size)  
rVektor.data[i] =value    
 return resultFlow({ this }, { resultChunk }, { resultSet }, { rVektor })
    }

    override fun calculate(resultSet: ResultSet, resultRow: ResultRow) {
val child=children[0]as AOPVariable
val variable=resultSet.createVariable(child.name)
val b=resultSet.getValueObject(resultRow,variable)
        a.set(b)
    }

    override fun cloneOP() = AOPAggregationSAMPLE(query, distinct, Array(children.size) { (children[it].cloneOP()) as AOPBase })
}
