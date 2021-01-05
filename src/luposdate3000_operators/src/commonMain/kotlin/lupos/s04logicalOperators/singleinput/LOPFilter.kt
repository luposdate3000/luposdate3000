package lupos.s04logicalOperators.singleinput
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.HistogramResult
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.IQuery
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.noinput.OPEmptyRow
import kotlin.jvm.JvmField
public class LOPFilter(query: IQuery, filter: AOPBase, child: IOPBase = OPEmptyRow(query)) : LOPBase(query, EOperatorID.LOPFilterID, "LOPFilter", arrayOf(child, filter), ESortPriority.SAME_AS_CHILD) {
    @JvmField public var dontSplitFilter: Int = 0
    override fun childrenToVerifyCount(): Int = 1
    override fun getProvidedVariableNames(): List<String> = children[0].getProvidedVariableNames().distinct()
    override fun getRequiredVariableNames(): List<String> = children[1].getRequiredVariableNamesRecoursive()
    override fun equals(other: Any?): Boolean = other is LOPFilter && children[0] == other.children[0] && children[1] == other.children[1]
    override fun cloneOP(): IOPBase = LOPFilter(query, children[1].cloneOP() as AOPBase, children[0].cloneOP())
    override /*suspend*/ fun calculateHistogram(): HistogramResult {
        return children[0].getHistogram()
    }
}
