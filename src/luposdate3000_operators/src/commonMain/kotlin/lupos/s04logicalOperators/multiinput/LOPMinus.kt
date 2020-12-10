package lupos.s04logicalOperators.multiinput

import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s04logicalOperators.HistogramResult
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.IQuery
import lupos.s04logicalOperators.LOPBase
import kotlin.jvm.JvmField

class LOPMinus(query: IQuery, first: IOPBase, second: IOPBase, @JvmField var tmpFakeVariables: List<String>) : LOPBase(query, EOperatorID.LOPMinusID, "LOPMinus", arrayOf(first, second), ESortPriority.MINUS) {
    @JvmField
    var hadSortPushDown: Boolean = false
    override fun getProvidedVariableNames(): List<String> = (children[0].getProvidedVariableNames() + tmpFakeVariables).distinct()
    override fun getRequiredVariableNames(): List<String> = listOf()
    override fun equals(other: Any?): Boolean = other is LOPMinus && children[0] == other.children[0] && children[1] == other.children[1]
    override fun cloneOP(): IOPBase = LOPMinus(query, children[0].cloneOP(), children[1].cloneOP(), tmpFakeVariables.toMutableList())
    override /*suspend*/ fun calculateHistogram(): HistogramResult {
        return children[0].getHistogram()
    }
}
