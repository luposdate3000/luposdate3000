package lupos.s04arithmetikOperators.singleinput
import lupos.s00misc.EOperatorID
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.ValueDefinition
import lupos.s04arithmetikOperators.AOPAggregationBase
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.IQuery
import lupos.s04logicalOperators.iterator.ColumnIteratorAggregate
import lupos.s04logicalOperators.iterator.IteratorBundle
import kotlin.jvm.JvmField
public class AOPAggregationSAMPLE(query: IQuery, @JvmField public val distinct: Boolean, childs: Array<AOPBase>) : AOPAggregationBase(query, EOperatorID.AOPAggregationSAMPLEID, "AOPAggregationSAMPLE", Array(childs.size) { childs[it] }) {
    override /*suspend*/ fun toXMLElement(): XMLElement = super.toXMLElement().addAttribute("distinct", "" + distinct)
    override fun toSparql(): String {
        if (distinct) {
            return "SAMPLE(DISTINCT " + children[0].toSparql() + ")"
        }
        return "SAMPLE(" + children[0].toSparql() + ")"
    }
    override fun equals(other: Any?): Boolean = other is AOPAggregationSAMPLE && distinct == other.distinct && children.contentEquals(other.children)
    override fun createIterator(row: IteratorBundle): ColumnIteratorAggregate {
        val res = ColumnIteratorAggregate()
        val child = (children[0] as AOPBase).evaluate(row)
        res.evaluate = {
            val value = child()
            res.value = value
            res.evaluate = {
            }
        }
        return res
    }
    override fun evaluate(row: IteratorBundle): () -> ValueDefinition {
        val tmp = row.columns["#$uuid"]!! as ColumnIteratorAggregate
        return {
            tmp.value
        }
    }
    override fun cloneOP(): IOPBase = AOPAggregationSAMPLE(query, distinct, Array(children.size) { (children[it].cloneOP()) as AOPBase })
}
