package lupos.s04logicalOperators.singleinput.modifiers
import lupos.s00misc.EOperatorIDExt
import lupos.s00misc.ESortPriorityExt
import lupos.s04logicalOperators.HistogramResult
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.IQuery
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.noinput.OPEmptyRow
import kotlin.jvm.JvmField
public class LOPReduced public constructor(query: IQuery, child: IOPBase) : LOPBase(query, EOperatorIDExt.LOPReducedID, "LOPReduced", arrayOf(child), ESortPriorityExt.SAME_AS_CHILD) {
    public constructor(query: IQuery) : this(query, OPEmptyRow(query))
    @JvmField public var hadPushDown: Boolean = false
    override fun equals(other: Any?): Boolean = other is LOPReduced && children[0] == other.children[0]
    override fun cloneOP(): IOPBase = LOPReduced(query, children[0].cloneOP())
    override /*suspend*/ fun calculateHistogram(): HistogramResult {
        return children[0].getHistogram()
    }
}
