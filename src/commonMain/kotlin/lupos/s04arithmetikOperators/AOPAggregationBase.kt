package lupos.s04arithmetikOperators
import lupos.s00misc.EOperatorID
import lupos.s02buildSyntaxTree.sparql1_1.Aggregation
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.ColumnIteratorAggregate
import lupos.s04logicalOperators.iterator.ColumnIteratorRow
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query




abstract class AOPAggregationBase(query: Query,
                                  operatorID: EOperatorID,
                                  classname: String,
                                  children: Array<OPBase>) : AOPBase(query, operatorID, classname, children) {
    abstract fun createIterator(row: ColumnIteratorRow): ColumnIteratorAggregate
}
