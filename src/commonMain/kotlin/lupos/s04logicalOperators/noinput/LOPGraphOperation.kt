package lupos.s04logicalOperators.noinput

import kotlin.jvm.JvmField
import lupos.s00misc.*
import lupos.s00misc.EGraphOperationType
import lupos.s02buildSyntaxTree.sparql1_1.ASTGraphRef

import lupos.s04arithmetikOperators.ResultVektorRaw
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.ResultIterator

class LOPGraphOperation(query: Query,
                        @JvmField var action: EGraphOperationType = EGraphOperationType.CREATE,
                        var silent: Boolean = false,
                        var graph1type: EGraphRefType = EGraphRefType.DefaultGraphRef,
                        var graph1iri: String? = null,
                        var graph2type: EGraphRefType = EGraphRefType.DefaultGraphRef,
                        var graph2iri: String? = null
) : LOPBase(query, EOperatorID.LOPGraphOperationID, "LOPGraphOperation", arrayOf()) {
    override fun equals(other: Any?): Boolean {
        if (other !is LOPGraphOperation)
            return false
        if (silent != other.silent)
            return false
        if (graph1iri != other.graph1iri)
            return false
        if (graph1type != other.graph1type)
            return false
        if (graph2iri != other.graph2iri)
            return false
        if (graph2type != other.graph2type)
            return false
        if (action != other.action)
            return false
        return true
    }

    override fun cloneOP() = LOPGraphOperation(query, action, silent, graph1type, graph1iri, graph2type, graph2iri)
}
