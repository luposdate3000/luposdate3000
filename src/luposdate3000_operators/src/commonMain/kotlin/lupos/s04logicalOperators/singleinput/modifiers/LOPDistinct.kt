package lupos.s04logicalOperators.singleinput.modifiers
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s04logicalOperators.HistogramResult
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.IQuery
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.noinput.OPEmptyRow
public class LOPDistinct public constructor(query: IQuery, child: IOPBase ) : LOPBase(query, EOperatorID.LOPDistinctID, "LOPDistinct", arrayOf(child), ESortPriority.SAME_AS_CHILD) {
public constructor(query: IQuery):this(query,OPEmptyRow(query))
    override fun equals(other: Any?): Boolean = other is LOPDistinct && children[0] == other.children[0]
    override fun cloneOP(): IOPBase = LOPDistinct(query, children[0].cloneOP())
    override /*suspend*/ fun calculateHistogram(): HistogramResult {
        return children[0].getHistogram()
    }
}
