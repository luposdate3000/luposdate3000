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
Coverage.funStart(14261)
        DistributedTripleStore.localStore.commit(query)
Coverage.statementStart(14262)
    }
    suspend fun tripleModify(query: Query, graphName: String, data: Array<ColumnIterator>, idx: EIndexPattern, type: EModifyType) {
Coverage.funStart(14263)
        DistributedTripleStore.localStore.getNamedGraph(query, graphName).modify(query, data, idx, type)
Coverage.statementStart(14264)
    }
    fun graphClearAll(query: Query) {
Coverage.funStart(14265)
        DistributedTripleStore.localStore.getDefaultGraph(query).clear()
Coverage.statementStart(14266)
        for (g in DistributedTripleStore.getGraphNames()) {
Coverage.forLoopStart(14267)
            DistributedTripleStore.dropGraph(query, g)
Coverage.statementStart(14268)
        }
Coverage.statementStart(14269)
    }
    fun graphOperation(query: Query, graphName: String, type: EGraphOperationType) {
Coverage.funStart(14270)
        when (type) {
            EGraphOperationType.CLEAR -> {
Coverage.whenCaseStart(14272)
                DistributedTripleStore.localStore.clearGraph(query, graphName)
Coverage.statementStart(14273)
            }
            EGraphOperationType.CREATE -> {
Coverage.whenCaseStart(14274)
                DistributedTripleStore.localStore.createGraph(query, graphName)
Coverage.statementStart(14275)
            }
            EGraphOperationType.DROP -> {
Coverage.whenCaseStart(14276)
                DistributedTripleStore.localStore.dropGraph(query, graphName)
Coverage.statementStart(14277)
            }
            else -> {
Coverage.whenCaseStart(14278)
                throw Exception("unreachable")
            }
        }
Coverage.statementStart(14279)
    }
    fun tripleGet(query: Query, graphName: String, params: Array<AOPBase>, idx: EIndexPattern): IteratorBundle {
Coverage.funStart(14280)
        return DistributedTripleStore.localStore.getNamedGraph(query, graphName).getIterator(query, params, idx)
    }
    fun histogramGet(query: Query, graphName: String, params: Array<AOPBase>, idx: EIndexPattern): Pair<Int, Int> {
Coverage.funStart(14281)
        return DistributedTripleStore.localStore.getNamedGraph(query, graphName).getHistogram(query, params, idx)
    }
    fun start(hostname: String = "localhost", port: Int = NETWORK_DEFAULT_PORT, bootstrap: String? = null) {
Coverage.funStart(14282)
    }
}
