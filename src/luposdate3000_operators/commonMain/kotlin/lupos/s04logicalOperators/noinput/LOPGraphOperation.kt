package lupos.s04logicalOperators.noinput

import kotlin.jvm.JvmField
import lupos.s00misc.EGraphOperationType
import lupos.s00misc.EGraphRefType
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s04logicalOperators.HistogramResult
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.IQuery
import lupos.s04logicalOperators.LOPBase

class LOPGraphOperation(query: IQuery,
                        @JvmField var action: EGraphOperationType = EGraphOperationType.CREATE,
                        var silent: Boolean = false,
                        var graph1type: EGraphRefType = EGraphRefType.DefaultGraphRef,
                        var graph1iri: String? = null,
                        var graph2type: EGraphRefType = EGraphRefType.DefaultGraphRef,
                        var graph2iri: String? = null
) : LOPBase(query, EOperatorID.LOPGraphOperationID, "LOPGraphOperation", arrayOf(), ESortPriority.PREVENT_ANY) {
    override fun equals(other: Any?) = other is LOPGraphOperation && silent == other.silent && graph1iri == other.graph1iri && graph1type == other.graph1type && graph2iri == other.graph2iri && graph2type == other.graph2type && action == other.action
    override fun cloneOP(): IOPBase = LOPGraphOperation(query, action, silent, graph1type, graph1iri, graph2type, graph2iri)
    override /*suspend*/ fun calculateHistogram(): HistogramResult {
        val res = HistogramResult()
        res.count = 1
        return res
    }
}
