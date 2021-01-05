package lupos.s04arithmetikOperators.singleinput
import lupos.s00misc.EOperatorID
import lupos.s00misc.MyBigInteger
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.ValueDefinition
import lupos.s03resultRepresentation.ValueInteger
import lupos.s04arithmetikOperators.AOPAggregationBase
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.IQuery
import lupos.s04logicalOperators.iterator.ColumnIteratorAggregate
import lupos.s04logicalOperators.iterator.IteratorBundle
import kotlin.jvm.JvmField
public class AOPAggregationCOUNT(query: IQuery, @JvmField public val distinct: Boolean, childs: Array<AOPBase>) : AOPAggregationBase(query, EOperatorID.AOPAggregationCOUNTID, "AOPAggregationCOUNT", Array(childs.size) { childs[it] }) {
    override /*suspend*/ fun toXMLElement(): XMLElement = super.toXMLElement().addAttribute("distinct", "" + distinct)
    override fun toSparql(): String {
        var res = "COUNT("
        if (distinct) {
            res += "DISTINCT "
        }
        if (children.isNotEmpty()) {
            res += children[0].toSparql()
        }
        res += ")"
        return res
    }
    override fun equals(other: Any?): Boolean = other is AOPAggregationCOUNT && distinct == other.distinct && children.contentEquals(other.children)
    override fun createIterator(row: IteratorBundle): ColumnIteratorAggregate {
        val res = ColumnIteratorAggregate()
        res.evaluate = {
            res.count++
        }
        return res
    }
    override fun evaluate(row: IteratorBundle): () -> ValueDefinition {
        val tmp = row.columns["#$uuid"]!! as ColumnIteratorAggregate
        return {
            ValueInteger(MyBigInteger(tmp.count))
        }
    }
    override fun cloneOP(): IOPBase = AOPAggregationCOUNT(query, distinct, Array(children.size) { (children[it].cloneOP()) as AOPBase })
}
