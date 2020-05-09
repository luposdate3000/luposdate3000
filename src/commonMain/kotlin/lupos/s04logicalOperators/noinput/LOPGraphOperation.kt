package lupos.s04logicalOperators.noinput
import kotlin.jvm.JvmField
import lupos.s00misc.BenchmarkUtils
import lupos.s00misc.classNameToString
import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.Coverage
import lupos.s00misc.DynamicByteArray
import lupos.s00misc.EBenchmark
import lupos.s00misc.EGraphOperationType
import lupos.s00misc.EGraphRefType
import lupos.s00misc.EIndexPattern
import lupos.s00misc.EModifyType
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s00misc.File
import lupos.s00misc.JenaWrapper
import lupos.s00misc.MemoryStatistics
import lupos.s00misc.MyListDouble
import lupos.s00misc.MyListGeneric
import lupos.s00misc.MyListInt
import lupos.s00misc.MyMapDoubleInt
import lupos.s00misc.MyMapIntGeneric
import lupos.s00misc.MyMapIntInt
import lupos.s00misc.MyMapLongGeneric
import lupos.s00misc.MyMapLongInt
import lupos.s00misc.MyMapStringIntPatriciaTrie
import lupos.s00misc.MyMapStringIntPatriciaTrieDouble
import lupos.s00misc.MySetInt
import lupos.s00misc.OperatorGraphToLatex
import lupos.s00misc.parseFromXml
import lupos.s00misc.SanityCheck
import lupos.s00misc.ThreadSafeUuid
import lupos.s00misc.XMLElement
import lupos.s02buildSyntaxTree.sparql1_1.ASTGraphRef
import lupos.s03resultRepresentation.MyListValue
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
        if (other !is LOPGraphOperation) {
            return false
        }
        if (silent != other.silent) {
            return false
        }
        if (graph1iri != other.graph1iri) {
            return false
        }
        if (graph1type != other.graph1type) {
            return false
        }
        if (graph2iri != other.graph2iri) {
            return false
        }
        if (graph2type != other.graph2type) {
            return false
        }
        if (action != other.action) {
            return false
        }
        return true
    }

    override fun cloneOP() = LOPGraphOperation(query, action, silent, graph1type, graph1iri, graph2type, graph2iri)
}
