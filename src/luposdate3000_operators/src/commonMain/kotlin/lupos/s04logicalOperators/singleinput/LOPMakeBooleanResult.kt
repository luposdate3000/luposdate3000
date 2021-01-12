package lupos.s04logicalOperators.singleinput
import lupos.s00misc.EOperatorIDExt
import lupos.s00misc.ESortPriorityExt
import lupos.s04logicalOperators.HistogramResult
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.IQuery
import lupos.s04logicalOperators.LOPBase
public class LOPMakeBooleanResult public constructor(query: IQuery, child: IOPBase) : LOPBase(query, EOperatorIDExt.LOPMakeBooleanResultID, "LOPMakeBooleanResult", arrayOf(child), ESortPriorityExt.PREVENT_ANY) {
    override fun getProvidedVariableNames(): List<String> {
        return mutableListOf("?boolean")
    }
    override fun equals(other: Any?): Boolean = other is LOPMakeBooleanResult && children[0] == other.children[0]
    override fun cloneOP(): IOPBase = LOPMakeBooleanResult(query, children[0].cloneOP())
    override /*suspend*/ fun calculateHistogram(): HistogramResult {
        val res = HistogramResult()
        res.values["?boolean"] = 1
        res.count = 1
        return res
    }
}
