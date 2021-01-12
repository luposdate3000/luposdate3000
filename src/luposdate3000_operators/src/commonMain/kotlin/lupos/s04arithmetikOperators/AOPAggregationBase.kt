package lupos.s04arithmetikOperators
import lupos.s00misc.EOperatorID
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.IQuery
import lupos.s04logicalOperators.iterator.ColumnIteratorAggregate
import lupos.s04logicalOperators.iterator.IteratorBundle
public abstract class AOPAggregationBase public constructor(
    query: IQuery,
    operatorID: EOperatorID,
    classname: String,
    children: Array<IOPBase>
) : AOPBase(query, operatorID, classname, children) {
    public abstract fun createIterator(row: IteratorBundle): ColumnIteratorAggregate
}
