/*
 * This file is part of the Luposdate3000 distribution (https://github.com/luposdate3000/luposdate3000).
 * Copyright (c) 2020-2021, Institute of Information Systems (Benjamin Warnke and contributors of LUPOSDATE3000), University of Luebeck
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, version 3.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package lupos.simulator_db.luposdate3000
import lupos.dictionary.DictionaryFactory
import lupos.endpoint.LuposdateEndpoint
import lupos.endpoint_launcher.PathMappingHelper
import lupos.endpoint_launcher.RestEndpoint
import lupos.endpoint_launcher.WebRootEndpoint
import lupos.operator.base.OPBaseCompound
import lupos.operator.base.Query
import lupos.operator.factory.XMLElementToOPBase
import lupos.operator.factory.XMLElementToOPBaseMap
import lupos.operator.physical.noinput.POPNothing
import lupos.operator.physical.partition.POPDistributedReceiveMulti
import lupos.operator.physical.partition.POPDistributedReceiveSingle
import lupos.operator.physical.partition.POPDistributedSendMulti
import lupos.operator.physical.partition.POPDistributedSendSingle
import lupos.optimizer.distributed.query.DistributedOptimizerQuery
import lupos.parser.JsonParserObject
import lupos.result_format.EQueryResultToStreamExt
import lupos.result_format.ResultFormatManager
import lupos.shared.EPartitionModeExt
import lupos.shared.EPredefinedPartitionSchemesExt
import lupos.shared.EQueryDistributionModeExt
import lupos.shared.IMyInputStream
import lupos.shared.IMyOutputStream
import lupos.shared.Luposdate3000Config
import lupos.shared.Luposdate3000Instance
import lupos.shared.MemoryTable
import lupos.shared.MyInputStreamFromByteArray
import lupos.shared.SanityCheck
import lupos.shared.XMLElement
import lupos.shared.dictionary.EDictionaryTypeExt
import lupos.shared.dictionary.IDictionary
import lupos.shared.dynamicArray.ByteArrayWrapper
import lupos.shared.inline.MyPrintWriter
import lupos.shared.operator.IOPBase
import lupos.simulator_db.DatabaseState
import lupos.simulator_db.IDatabase
import lupos.simulator_db.IDatabasePackage
import lupos.simulator_db.IRouter
import lupos.simulator_db.QueryPackage
import lupos.simulator_db.QueryResponsePackage
import lupos.visualize.distributed.database.VisualisationNetwork
public class DatabaseHandle public constructor(config: JsonParserObject) : IDatabase {
    private var enableSharedMemoryDictionaryCheat = true
    private var visualisationNetwork = VisualisationNetwork()
    private var ownAdress: Int = 0
    public var instance: Luposdate3000Instance = Luposdate3000Instance()
    private val myPendingWork = mutableListOf<MySimulatorPendingWork>()
    private val myPendingWorkData = mutableMapOf<String, ByteArrayWrapper>()
    private var router: IRouter? = null
    private var nodeGlobalDictionaryBackup: IDictionary? = null
    private var predefinedPartitionScheme = Luposdate3000Config.predefinedPartitionScheme
    private var mergeLocalOperatorgraphs = Luposdate3000Config.mergeLocalOperatorgraphs
    private var queryDistributionMode = Luposdate3000Config.queryDistributionMode
    private var useDictionaryInlineEncoding = Luposdate3000Config.useDictionaryInlineEncoding
    private var REPLACE_STORE_WITH_VALUES = Luposdate3000Config.REPLACE_STORE_WITH_VALUES
    init {
        val enableSharedMemoryDictionaryCheatTmp = config.getOrDefault("SharedMemoryDictionaryCheat", true)
        when (enableSharedMemoryDictionaryCheatTmp) {
            is String -> enableSharedMemoryDictionaryCheat = enableSharedMemoryDictionaryCheatTmp.toLowerCase() == "true"
            is Boolean -> enableSharedMemoryDictionaryCheat = enableSharedMemoryDictionaryCheatTmp
        }
        val predefinedPartitionSchemeTmp = config.getOrDefault("predefinedPartitionScheme", Luposdate3000Config.predefinedPartitionScheme)
        when (predefinedPartitionSchemeTmp) {
            is String -> predefinedPartitionScheme = EPredefinedPartitionSchemesExt.names.indexOf(predefinedPartitionSchemeTmp)
        }
        val mergeLocalOperatorgraphsTmp = config.getOrDefault("mergeLocalOperatorgraphs", Luposdate3000Config.mergeLocalOperatorgraphs)
        when (mergeLocalOperatorgraphsTmp) {
            is String -> mergeLocalOperatorgraphs = mergeLocalOperatorgraphsTmp.toLowerCase() == "true"
            is Boolean -> mergeLocalOperatorgraphs = mergeLocalOperatorgraphsTmp
        }
        val queryDistributionModeTmp = config.getOrDefault("queryDistributionMode", Luposdate3000Config.queryDistributionMode)
        when (queryDistributionModeTmp) {
            is String -> queryDistributionMode = EQueryDistributionModeExt.names.indexOf(queryDistributionModeTmp)
        }
        val useDictionaryInlineEncodingTmp = config.getOrDefault("useDictionaryInlineEncoding", Luposdate3000Config.useDictionaryInlineEncoding)
        when (useDictionaryInlineEncodingTmp) {
            is String -> useDictionaryInlineEncoding = useDictionaryInlineEncodingTmp.toLowerCase() == "true"
            is Boolean -> useDictionaryInlineEncoding = useDictionaryInlineEncodingTmp
        }
        val REPLACE_STORE_WITH_VALUESTmp = config.getOrDefault("REPLACE_STORE_WITH_VALUES", Luposdate3000Config.REPLACE_STORE_WITH_VALUES)
        when (REPLACE_STORE_WITH_VALUESTmp) {
            is String -> REPLACE_STORE_WITH_VALUES = REPLACE_STORE_WITH_VALUESTmp.toLowerCase() == "true"
            is Boolean -> REPLACE_STORE_WITH_VALUES = REPLACE_STORE_WITH_VALUESTmp
        }
    }
    private companion object {
        // this is used for cheating .... because currently streams of data are not working in simulator
        // streams would additionally depend on the possibility to suspend the database at any point, which is currently not implemented too
        private var globalCheatDictionary: IDictionary? = null
        private var globalCheatInstance: Luposdate3000Instance? = null
        fun globalCheatStart(instance: Luposdate3000Instance) {
            if (globalCheatDictionary == null) {
                globalCheatInstance = instance
                globalCheatDictionary = DictionaryFactory.createGlobalDictionary(instance)
            }
        }
        fun globalCheatEnd() {
            if (globalCheatDictionary != null) {
                val backup = globalCheatInstance!!.nodeGlobalDictionary
                globalCheatInstance!!.nodeGlobalDictionary = globalCheatDictionary!!
                globalCheatDictionary!!.close()
                globalCheatInstance!!.nodeGlobalDictionary = backup
                globalCheatDictionary = null
            }
        }
    }

    override fun start(initialState: DatabaseState) {
        visualisationNetwork = initialState.visualisationNetwork
        println("DatabaseHandle.start ${initialState.allAddresses.map{it}} .. ${initialState.ownAddress}")
        if (initialState.allAddresses.isEmpty()) {
            throw Exception("invalid input")
        }
        ownAdress = initialState.ownAddress
        router = initialState.sender
        instance.enableJoinOrderOnHistogram = false
        instance.LUPOS_PROCESS_URLS_STORE = initialState.allAddresses.map { it.toString() }.toTypedArray()
        instance.LUPOS_PROCESS_URLS_QUERY = initialState.allAddresses.map { it.toString() }.toTypedArray()
        instance.LUPOS_PROCESS_URLS_ALL = Luposdate3000Config.mergeProcessurls(instance.LUPOS_PROCESS_URLS_STORE, instance.LUPOS_PROCESS_URLS_QUERY)
        instance.LUPOS_PROCESS_ID = initialState.allAddresses.indexOf(initialState.ownAddress)
        instance.LUPOS_HOME = initialState.absolutePathToDataDirectory
        instance.LUPOS_PARTITION_MODE = EPartitionModeExt.Process
        instance.LUPOS_DICTIONARY_MODE = EDictionaryTypeExt.KV
        instance.LUPOS_BUFFER_SIZE = 8192
        instance.queue_size = 2048
        instance.communicationHandler = MySimulatorCommunicationHandler(instance, initialState.sender)
        instance = LuposdateEndpoint.initializeB(instance)
        if (enableSharedMemoryDictionaryCheat) {
            globalCheatStart(instance)
            nodeGlobalDictionaryBackup = instance.nodeGlobalDictionary
            instance.nodeGlobalDictionary = globalCheatDictionary
        }
        instance.distributedOptimizerQueryFactory = {
            DistributedOptimizerQuery()
        }
    }

    override fun activate() {
/*
        if (!instance.initialized) {
            instance = LuposdateEndpoint.initializeB(instance)
        }
*/
    }

    override fun deactivate() {
/*
        if ((!BufferManagerExt.isInMemoryOnly) && (instance.LUPOS_DICTIONARY_MODE != EDictionaryTypeExt.InMemory)) {
            // do not disable inmemory databases, because they would loose all data
            LuposdateEndpoint.close(instance)
        }
*/
    }

    override fun end() {
        if (enableSharedMemoryDictionaryCheat) {
            instance.nodeGlobalDictionary = nodeGlobalDictionaryBackup
            globalCheatEnd()
        }
        LuposdateEndpoint.close(instance)
    }

    private fun receive(pck: MySimulatorTestingImportPackage) {
        if (listOf(".n3", ".ttl", ".nt").contains(pck.type)) {
            LuposdateEndpoint.importTurtleString(instance, pck.data, pck.graph)
        } else {
            TODO()
        }
// TODO wait for all ack - or assume ordered messages
        val onFinish = pck.onFinish
        if (onFinish != null) {
            receive(onFinish)
        }
    }
    private fun receive(pck: MySimulatorTestingCompareGraphPackage) {
        receive(QueryPackage(ownAdress, pck.query.encodeToByteArray()), pck.onFinish, pck.expectedResult)
    }
    private fun receive(pck: MySimulatorTestingExecute) {
        receive(QueryPackage(ownAdress, pck.query.encodeToByteArray()), pck.onFinish, null)
    }

    private fun containsTripleStoreAccess(op: XMLElement): Boolean {
        if (op.tag == "POPTripleStoreIterator") {
            return true
        }
        for (c in op.childs) {
            if (containsTripleStoreAccess(c)) {
                return true
            }
        }
        return false
    }

    private fun receive(pck: QueryPackage, onFinish: IDatabasePackage?, expectedResult: MemoryTable?) {
        val queryString = pck.query.decodeToString()
        val op = LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, queryString)
        val q = op.getQuery()
        q.setTransactionID(pck.queryID.toLong())
        q.initialize(op, false)
        visualisationNetwork.addOperatorGraph(pck.queryID, q.getOperatorgraphParts())
        val parts = q.getOperatorgraphParts()
        var hostMap = mutableMapOf<String, String>()
        when (instance.queryDistributionMode) {
            EQueryDistributionModeExt.Routing -> {
                // only define the explicit triple-store access
                for ((key, value) in q.getOperatorgraphPartsToHostMap()) {
                    val part = parts[key]!!
                    if (containsTripleStoreAccess(part)) {
                        hostMap[key] = value
                    }
                }
            }
            EQueryDistributionModeExt.Centralized -> {
                // define everything as in the DB outside of the simulator
                hostMap.putAll(q.getOperatorgraphPartsToHostMap())
                SanityCheck.check(
                    { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_simulator_db/src/commonMain/kotlin/lupos/simulator_db/luposdate3000/DatabaseHandle.kt:234"/*SOURCE_FILE_END*/ },
                    { hostMap.size == parts.size }
                )
            }
        }
        if (parts.size == 1) {
            visualisationNetwork.addWork(pck.queryID, ownAdress, q.getRoot().toXMLElement(false), setOf(), setOf())
// TODO wait for all ack - or assume ordered messages
            if (expectedResult != null) {
                val buf = MyPrintWriter(false)
                val result = (LuposdateEndpoint.evaluateOperatorgraphToResultE(instance, q.getRoot(), buf, EQueryResultToStreamExt.MEMORY_TABLE, false) as List<MemoryTable>).first()
                val buf_err = MyPrintWriter()
                if (!result.equalsVerbose(expectedResult, true, true, buf_err)) {
                    throw Exception(buf_err.toString())
                }
                if (onFinish != null) {
                    receive(onFinish)
                } else {
                    router!!.send(pck.sourceAddress, QueryResponsePackage("success".encodeToByteArray(), pck.queryID))
                }
            } else {
                val out = MyPrintWriter(true)
                val evaluatorInstance = ResultFormatManager[EQueryResultToStreamExt.names[EQueryResultToStreamExt.DEFAULT_STREAM]]!!
                evaluatorInstance(q.getRoot(), out)
                val res = out.toString().encodeToByteArray()
                if (onFinish != null) {
                    receive(onFinish)
                } else {
                    router!!.send(pck.sourceAddress, QueryResponsePackage(res, pck.queryID))
                }
            }
        } else {
            val destinations = mutableMapOf("" to pck.sourceAddress)
            receive(MySimulatorOperatorGraphPackage(pck.queryID, parts, destinations, hostMap, onFinish, expectedResult))
        }
    }
    private fun receive(pck: MySimulatorAbstractPackage) {
        val paths = mutableMapOf<String, PathMappingHelper>()
        RestEndpoint.initialize(instance, paths)
        WebRootEndpoint.initialize(paths)
        paths["/distributed/query/dictionary/register"] = PathMappingHelper(false, mapOf()) { params, connectionInMy, connectionOutMy ->
            true
        }
        paths["/distributed/query/dictionary/remove"] = PathMappingHelper(false, mapOf()) { params, connectionInMy, connectionOutMy ->
            true
        }
        paths["simulator-intermediate-result"] = PathMappingHelper(false, mapOf()) { params, connectionInMy, connectionOutMy ->
            SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_simulator_db/src/commonMain/kotlin/lupos/simulator_db/luposdate3000/DatabaseHandle.kt:281"/*SOURCE_FILE_END*/ }, { myPendingWorkData[pck.params["key"]!!] == null })
            myPendingWorkData[pck.params["key"]!!] = pck.data
            doWork()
            true
        }
        val target = paths[pck.path]
        if (target == null) {
            TODO(pck.path)
        } else {
            val input = MyInputStreamFromByteArray(pck.data)
            val output = MySimulatorOutputStreamToVoid()
            target.action(pck.params, input, output)
            input.close()
            output.close()
        }
    }

    private fun receive(pck: MySimulatorOperatorGraphPackage) {
        val mapTopDown = mutableMapOf<String, MutableSet<String>>()
        val mapBottomUp = mutableMapOf<String, MutableSet<String>>()
        val allHosts = pck.operatorGraphPartsToHostMap.values.toSet().toTypedArray()
        val allHostAdresses = IntArray(allHosts.size) { allHosts[it].toInt() }
//        val nextHops = router!!.getNextDatabaseHops(allHostAdresses)  //TODO
        val packages = mutableMapOf<Int, MySimulatorOperatorGraphPackage>()
        for (i in allHostAdresses.toSet()) {
            packages[i] = MySimulatorOperatorGraphPackage(
                pck.queryID,
                mutableMapOf(),
                mutableMapOf(),
                mutableMapOf(),
                pck.onFinish,
                pck.expectedResult
            )
        }
        packages[ownAdress] = MySimulatorOperatorGraphPackage(pck.queryID, mutableMapOf(), mutableMapOf(), mutableMapOf(), pck.onFinish, pck.expectedResult)
        val packageMap = mutableMapOf<String, Int>()
        for ((k, v) in pck.operatorGraphPartsToHostMap) {
            packageMap[k] = allHostAdresses[allHostAdresses.indexOf(v.toInt())]
        }
        for ((k, v) in pck.operatorGraph) {
            mapTopDown[k] = extractKey(v, "POPDistributedReceive", "").toMutableSet()
            mapBottomUp[k] = (extractKey(v, "POPDistributedSend", "") + setOf(k)).toMutableSet()
            SanityCheck(
                { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_simulator_db/src/commonMain/kotlin/lupos/simulator_db/luposdate3000/DatabaseHandle.kt:324"/*SOURCE_FILE_END*/ },
                {
                    if (!extractKey(v, "POPDistributedSend", "").contains(k) && k != "") {
                        println("something suspicious ... $k ${extractKey(v, "POPDistributedSend", "")} $v")
                    }
                }
            )
        }
        var changed = true
        while (changed) {
            changed = false
            loop@ for ((k, v) in mapTopDown) {
                if (!packageMap.contains(k)) {
                    SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_simulator_db/src/commonMain/kotlin/lupos/simulator_db/luposdate3000/DatabaseHandle.kt:337"/*SOURCE_FILE_END*/ }, { v.isNotEmpty() })
                    var dest = -1
                    for (key in v) {
                        val d = packageMap[key]
                        if (d != null) {
                            if (dest == -1) {
                                dest = d
                            } else {
                                if (dest != d) {
                                    for (assign in mapBottomUp[k]!!) {
                                        packageMap[assign] = ownAdress // alles mit unterschiedlichen next hops selber berechnen
                                    }
                                    for (i in mapTopDown[k]!!) {
                                        pck.destinations[i] = ownAdress
                                    }
                                    changed = true
                                    continue@loop
                                }
                            }
                        } else {
                            continue@loop
                        }
                    }
                    for (i in mapTopDown[k]!!) {
                        pck.destinations[i] = dest
                    }
                    for (assign in mapBottomUp[k]!!) {
                        packageMap[assign] = dest // alles mit gemeinsamen next Hop zusammen weitersenden
                    }
                    changed = true
                }
            }
        }
        for ((k, v) in pck.operatorGraph) {
            if (!packageMap.contains(k)) {
                packageMap[k] = ownAdress
                TODO("this should not happen?? \nYYYY${pck.operatorGraph.keys.map{"$it - ${packageMap.contains(it)} - ${mapTopDown[it]} - ${pck.destinations[it]} - ${pck.operatorGraphPartsToHostMap[it]} - ${ pck.operatorGraph[it]}"}.joinToString("\nYYYY")}")
            }
        }
        for ((k, v) in packageMap) {
            val p = packages[v]
            val g = pck.operatorGraph[k]
            if (p != null && g != null) { // p can be null if there is a send-multi
                p.operatorGraph[k] = g
                val h = pck.operatorGraphPartsToHostMap[k]
                if (h != null) {
                    p.operatorGraphPartsToHostMap[k] = h
                }
                val d = pck.destinations[k]
                if (d != null) {
                    p.destinations[k] = d
                } else {
                    p.destinations[k] = ownAdress
                }
            }
        }
        for ((k, p) in packages) {
            if (k != ownAdress) {
                router!!.send(k, p)
            } else {
                if (instance.mergeLocalOperatorgraphs) {
                    var containsSendMultiFlag = false
                    for ((k, v) in p.operatorGraph) {
                        if (mapBottomUp[k]!!.size > 1) {
                            containsSendMultiFlag = true
                        }
                    }
                    if (!containsSendMultiFlag) {
// try to merge operatorgraphs for local queries
                        loop@for (k5 in p.operatorGraph.keys.toSet()) {
                            for (v in mapBottomUp[k5]!!) { // what is provided .... k5 == v !!! ???
                                for ((k6, k7) in mapTopDown) { // what is required
                                    if (k7.contains(v)) {
// merge now !!
                                        var res = mergeOperatorGraphLocally(null, 0, p.operatorGraph[k6]!!, p.operatorGraph[k5]!!, k5)
                                        if (res) {
                                            p.operatorGraph.remove(k5) // remove the entire child
                                            p.destinations.remove(k5) // remove the entire child
                                            mapTopDown[k6]!!.remove(k5) // remove the child dependency from the parent
                                            mapTopDown[k6]!!.addAll(mapTopDown[k5]!!)
                                            mapTopDown.remove(k5) // remove the entire child
                                        }
                                        continue@loop
                                    }
                                }
                            }
                        }
                    }
                }
                for (k in p.operatorGraph.keys) {
                    val graph = p.operatorGraph[k]!!
                    visualisationNetwork.addWork(p.queryID, ownAdress, graph, mapTopDown[k]!!, mapBottomUp[k]!!)
                    val w = MySimulatorPendingWork(
                        p.queryID,
                        p.operatorGraph[k]!!,
                        p.destinations[k]!!,
                        mapTopDown[k]!!,
                        k,
                        pck.onFinish,
                        pck.expectedResult,
                    )
                    myPendingWork.add(w)
                }
                doWork()
            }
        }
    }

    private fun mergeOperatorGraphLocally(parent2: XMLElement?, parentChildIndex: Int, parent: XMLElement, child: XMLElement, key: String): Boolean {
        if (parent.tag == "POPDistributedReceiveSingle") {
            val tmp = parent.childs.filter { it.tag == "partitionDistributionKey" }
            val tmp2 = child.childs.filter { it.tag == "partitionDistributionKey" }
            if (tmp.size == 1 &&
                tmp.first().attributes["key"] == key &&
                tmp2.size == 1 &&
                tmp2.first().attributes["key"] == key &&
                parent2 != null
            ) {
                parent2.childs.removeAt(parentChildIndex)
                parent2.childs.add(parentChildIndex, child["children"]!!.childs.first())
                return true
            } else {
                return false
            }
        } else {
            var i = 0
            for (c in parent.childs) {
                if (mergeOperatorGraphLocally(parent, i, c, child, key)) {
                    return true
                }
                i++
            }
            return false
        }
    }
    private fun extractKey(node: XMLElement, targetTag: String, parentTag: String): Set<String> {
        val res = mutableSetOf<String>()
        if (parentTag.startsWith(targetTag)) {
            if (node.tag == "partitionDistributionKey") {
                res.add(node.attributes["key"]!!)
            }
        }
        for (c in node.childs) {
            res.addAll(extractKey(c, targetTag, node.tag))
        }
        return res
    }
    private fun localXMLElementToOPBase(query: Query, node: XMLElement): IOPBase {
        val operatorMap = mutableMapOf<String, XMLElementToOPBaseMap>()
        operatorMap.putAll(XMLElementToOPBase.operatorMap)
        operatorMap["POPDistributedReceiveSingle"] = { query, node, mapping, recursionFunc ->
            val id = node.attributes["partitionID"]!!.toInt()
            val keys = mutableSetOf<String>()
            for (c in node.childs) {
                if (c.tag == "partitionDistributionKey") {
                    keys.add(c.attributes["key"]!!)
                }
            }
            SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_simulator_db/src/commonMain/kotlin/lupos/simulator_db/luposdate3000/DatabaseHandle.kt:495"/*SOURCE_FILE_END*/ }, { keys.size == 1 })
            val key = keys.first()
            SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_simulator_db/src/commonMain/kotlin/lupos/simulator_db/luposdate3000/DatabaseHandle.kt:497"/*SOURCE_FILE_END*/ }, { myPendingWorkData.contains(key) })
            val input = MyInputStreamFromByteArray(myPendingWorkData[key]!!)
            myPendingWorkData.remove(key)
            val res = POPDistributedReceiveSingle(
                query,
                XMLElementToOPBase.createProjectedVariables(node),
                node.attributes["partitionVariable"]!!,
                node.attributes["partitionCount"]!!.toInt(),
                id,
                node.attributes["keyPrefix"]!!,
                POPNothing(query, XMLElementToOPBase.createProjectedVariables(node)),
                input
            )
            query.addPartitionOperator(res.uuid, id)
            res
        }
        operatorMap["POPDistributedReceiveMulti"] = { query, node, mapping, recursionFunc ->
            val id = node.attributes["partitionID"]!!.toInt()
            val keys = mutableSetOf<String>()
            for (c in node.childs) {
                if (c.tag == "partitionDistributionKey") {
                    keys.add(c.attributes["key"]!!)
                }
            }
            val inputs = keys.map { key ->
                val input = MyInputStreamFromByteArray(myPendingWorkData[key]!!)
                myPendingWorkData.remove(key)
                input as IMyInputStream
            }.toTypedArray()
            val res = POPDistributedReceiveMulti(
                query,
                XMLElementToOPBase.createProjectedVariables(node),
                node.attributes["partitionVariable"]!!,
                node.attributes["partitionCount"]!!.toInt(),
                id,
                node.attributes["keyPrefix"]!!,
                POPNothing(query, XMLElementToOPBase.createProjectedVariables(node)),
                inputs
            )
            query.addPartitionOperator(res.uuid, id)
            res
        }
        return XMLElementToOPBase(query, node, mutableMapOf(), operatorMap)
    }
    private fun detectBugDueToRemoteDictAccess(node: XMLElement) {
        when (node.tag) {
            "POPBind", "POPGroup" -> {
                TODO()
            }
        }
        for (c in node.childs) {
            detectBugDueToRemoteDictAccess(c)
        }
    }
    private fun doWork() {
        var changed = true
        while (changed) {
            changed = false
            for (w in myPendingWork) {
                if (myPendingWorkData.keys.containsAll(w.dependencies)) {
                    myPendingWork.remove(w)
                    changed = true
                    val query = Query(instance)
                    SanityCheck(
                        { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_simulator_db/src/commonMain/kotlin/lupos/simulator_db/luposdate3000/DatabaseHandle.kt:561"/*SOURCE_FILE_END*/ },
                        {
                            if (ownAdress != 0) {
                                detectBugDueToRemoteDictAccess(w.operatorGraph)
                            }
                        }
                    )
                    when (val node = localXMLElementToOPBase(query, w.operatorGraph)) {
                        is POPDistributedSendSingle -> {
                            val out = MySimulatorOutputStreamToPackage(w.queryID, w.destination, "simulator-intermediate-result", mapOf("key" to w.key), router!!)
                            node.evaluate(out)
                            out.close()
                        }
                        is POPDistributedSendMulti -> {
                            val out = Array<IMyOutputStream?>(node.hosts.size) {
                                MySimulatorOutputStreamToPackage(w.queryID, w.destination, "simulator-intermediate-result", mapOf("key" to node.hosts[it]), router!!)
                            }
                            node.evaluate(out)
                            for (o in out) {
                                o?.close()
                            }
                        }
                        is OPBaseCompound -> {
                            if (w.expectedResult != null) {
                                val buf = MyPrintWriter(false)
                                val result = (LuposdateEndpoint.evaluateOperatorgraphToResultE(instance, node, buf, EQueryResultToStreamExt.MEMORY_TABLE, false) as List<MemoryTable>).first()
                                val buf_err = MyPrintWriter()
                                if (!result.equalsVerbose(w.expectedResult, true, true, buf_err)) {
                                    throw Exception(buf_err.toString())
                                }
                                if (w.onFinish != null) {
                                    receive(w.onFinish)
                                } else {
                                    router!!.send(w.destination, QueryResponsePackage("success".encodeToByteArray(), w.queryID))
                                }
                            } else {
                                val buf = MyPrintWriter(true)
                                val evaluatorInstance = ResultFormatManager[EQueryResultToStreamExt.names[EQueryResultToStreamExt.DEFAULT_STREAM]]!!
                                evaluatorInstance(node, buf, false)
                                if (w.onFinish != null) {
                                    receive(w.onFinish)
                                } else {
                                    router!!.send(w.destination, QueryResponsePackage(buf.toString().encodeToByteArray(), w.queryID))
                                }
                            }
                        }
                        else -> TODO(node.toString())
                    }

                    changed = true
                    break
                }
            }
        }
    }
    override fun receive(pck: IDatabasePackage) {
        try {
            when (pck) {
                is MySimulatorTestingImportPackage -> receive(pck)
                is MySimulatorTestingCompareGraphPackage -> receive(pck)
                is MySimulatorTestingExecute -> receive(pck)
                is QueryPackage -> receive(pck, null, null)
                is MySimulatorAbstractPackage -> receive(pck)
                is MySimulatorOperatorGraphPackage -> receive(pck)
                else -> TODO("$pck")
            }
        } catch (e: Throwable) {
            visualisationNetwork.toImage()
            throw e
        }
    }
}
