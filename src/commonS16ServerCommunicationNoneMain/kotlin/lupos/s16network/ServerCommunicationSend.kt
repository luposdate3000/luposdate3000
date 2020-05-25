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

object ServerCommunicationSend {
    fun commit(query: Query) {
        ServerCommunicationReceive.commit(query)
    }

    suspend fun tripleModify(query: Query, graphName: String, data: Array<ColumnIterator>, idx: EIndexPattern, type: EModifyType) {
        ServerCommunicationReceive.tripleModify(query, graphName, data, idx, type)
    }

    fun graphClearAll(query: Query) {
        ServerCommunicationReceive.graphClearAll(query)
    }

    fun graphOperation(query: Query, graphName: String, type: EGraphOperationType) {
        ServerCommunicationReceive.graphOperation(query, graphName, type)
    }

    fun tripleGet(query: Query, graphName: String, params: Array<AOPBase>, idx: EIndexPattern): IteratorBundle {
        return ServerCommunicationReceive.tripleGet(query, graphName, params, idx)
    }

    fun histogramGet(query: Query, graphName: String, params: Array<AOPBase>, idx: EIndexPattern): Pair<Int, Int> {
        return ServerCommunicationReceive.histogramGet(query, graphName, params, idx)
    }

    fun start(bootstrap: String? = null) {
        ServerCommunicationReceive.start(bootstrap)
    }
}
