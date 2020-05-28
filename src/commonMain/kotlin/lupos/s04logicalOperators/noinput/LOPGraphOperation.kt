package lupos.s04logicalOperators.noinput
import kotlin.jvm.JvmField
import lupos.s00misc.Coverage
import lupos.s00misc.EGraphOperationType
import lupos.s00misc.EGraphRefType
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s04logicalOperators.HistogramResult
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
class LOPGraphOperation(query: Query,
                        @JvmField var action: EGraphOperationType = EGraphOperationType.CREATE,
                        var silent: Boolean = false,
                        var graph1type: EGraphRefType = EGraphRefType.DefaultGraphRef,
                        var graph1iri: String? = null,
                        var graph2type: EGraphRefType = EGraphRefType.DefaultGraphRef,
                        var graph2iri: String? = null
) : LOPBase(query, EOperatorID.LOPGraphOperationID, "LOPGraphOperation", arrayOf(), ESortPriority.PREVENT_ANY) {
    override fun equals(other: Any?): Boolean {
Coverage.funStart(4472)
        if (other !is LOPGraphOperation) {
Coverage.ifStart(4473)
            return false
        }
Coverage.statementStart(4474)
        if (silent != other.silent) {
Coverage.ifStart(4475)
            return false
        }
Coverage.statementStart(4476)
        if (graph1iri != other.graph1iri) {
Coverage.ifStart(4477)
            return false
        }
Coverage.statementStart(4478)
        if (graph1type != other.graph1type) {
Coverage.ifStart(4479)
            return false
        }
Coverage.statementStart(4480)
        if (graph2iri != other.graph2iri) {
Coverage.ifStart(4481)
            return false
        }
Coverage.statementStart(4482)
        if (graph2type != other.graph2type) {
Coverage.ifStart(4483)
            return false
        }
Coverage.statementStart(4484)
        if (action != other.action) {
Coverage.ifStart(4485)
            return false
        }
Coverage.statementStart(4486)
        return true
    }
    override fun cloneOP() = LOPGraphOperation(query, action, silent, graph1type, graph1iri, graph2type, graph2iri)
    override fun calculateHistogram(): HistogramResult {
Coverage.funStart(4487)
        var res = HistogramResult()
Coverage.statementStart(4488)
        res.count = 1
Coverage.statementStart(4489)
        return res
    }
}
