package lupos.s16network

import kotlin.jvm.JvmField
import lupos.s00misc.Coverage
import lupos.s00misc.EGraphOperationType
import lupos.s00misc.EIndexPattern
import lupos.s00misc.ELoggerType
import lupos.s00misc.EModifyType
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s00misc.GlobalLogger
import lupos.s00misc.SanityCheck
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.MyListValue
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Value
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.ColumnIteratorMultiValue
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s05tripleStore.PersistentStoreLocal
import lupos.s05tripleStore.TripleStoreBulkImport
import lupos.s05tripleStore.TripleStoreLocalBase
import lupos.s09physicalOperators.POPBase
import lupos.s15tripleStoreDistributed.*

object ServerCommunicationSend {
    fun commit(query: Query) {
DistributedTripleStore.localStore.commit(query)
    }

    suspend fun tripleModify(query: Query, graphName: String, data: Array<ColumnIterator>, idx: EIndexPattern, type: EModifyType) {
 DistributedTripleStore.localStore.getNamedGraph(query, graphName).modify(query, data, idx, type)
    }

    fun graphClearAll(query: Query) {
 DistributedTripleStore.localStore.getDefaultGraph(query).clear()
        for (g in DistributedTripleStore.getGraphNames()) {
            DistributedTripleStore.dropGraph(query, g)
        }    }

    fun graphOperation(query: Query, graphName: String, type: EGraphOperationType) {
when (type) {
            EGraphOperationType.CLEAR -> {
                DistributedTripleStore.localStore.clearGraph(query, graphName)
            }
            EGraphOperationType.CREATE -> {
                DistributedTripleStore.localStore.createGraph(query, graphName)
            }
            EGraphOperationType.DROP -> {
                DistributedTripleStore.localStore.dropGraph(query, graphName)
            }
        }
    }

    fun tripleGet(query: Query, graphName: String, params: Array<AOPBase>, idx: EIndexPattern): IteratorBundle {
return DistributedTripleStore.localStore.getNamedGraph(query, graphName).getIterator(query, params, idx)
    }

    fun histogramGet(query: Query, graphName: String, params: Array<AOPBase>, idx: EIndexPattern): Pair<Int, Int> {
 return DistributedTripleStore.localStore.getNamedGraph(query, graphName).getHistogram(query, params, idx)
    }

fun start(hostname: String="localhost", port: Int=NETWORK_DEFAULT_PORT, bootstrap: String? = null) {
    }
}
