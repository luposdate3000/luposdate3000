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
import lupos.dictionary.DictionaryCacheLayer
import lupos.dictionary.DictionaryFactory
import lupos.endpoint.LuposdateEndpoint
import lupos.endpoint.PathMappingHelper
import lupos.endpoint.RestEndpoint
import lupos.endpoint.WebRootEndpoint
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
import lupos.shared.IQuery
import lupos.shared.Luposdate3000Config
import lupos.shared.Luposdate3000Instance
import lupos.shared.MemoryTable
import lupos.shared.MyInputStreamFromByteArray
import lupos.shared.SanityCheck
import lupos.shared.XMLElement
import lupos.shared.dictionary.DictionaryNotImplemented
import lupos.shared.dictionary.EDictionaryTypeExt
import lupos.shared.dictionary.IDictionary
import lupos.shared.dynamicArray.ByteArrayWrapper
import lupos.shared.inline.MyPrintWriter
import lupos.shared.operator.IOPBase
import lupos.simulator_db.DatabaseState
import lupos.simulator_db.IDatabasePackage
import lupos.simulator_db.ILogger
import lupos.simulator_db.IPayload
import lupos.simulator_db.IUserApplication
import lupos.simulator_db.IUserApplicationLayer
import lupos.simulator_db.QueryPackage
import lupos.simulator_db.QueryResponsePackage
public class DatabaseHandle public constructor(internal val config: JsonParserObject, internal val initialState: () -> DatabaseState) : IUserApplication {
    private lateinit var parent: IUserApplicationLayer
    private var enableSharedMemoryDictionaryCheat = config.getOrDefault("SharedMemoryDictionaryCheat", true)
    private lateinit var logger: ILogger
    private var ownAdress: Int = 0
    public var instance: Luposdate3000Instance = Luposdate3000Instance()
    private val myPendingWork = mutableListOf<MySimulatorPendingWork>()
    private val myPendingWorkData = mutableMapOf<String, ByteArrayWrapper>()
    private var router: IUserApplicationLayer? = null
    private var nodeGlobalDictionaryBackup: IDictionary? = null
    private var rootAddress = ""
    private var rootAddressInt = -1
    private var hadInitDatabaseHopsWithinLuposdate3000 = false

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
    override fun setRouter(router: IUserApplicationLayer) {
        parent = router
    }
    override fun startUp() {
        val initialStateTmp = initialState()
        logger = initialStateTmp.logger
        if (initialStateTmp.allAddressesStore.isEmpty()) {
            throw Exception("invalid input")
        }
        ownAdress = initialStateTmp.ownAddress
        router = parent
        instance.enableJoinOrderOnHistogram = false
        instance.LUPOS_PROCESS_URLS_STORE = initialStateTmp.allAddressesStore.map { it.toString() }.toTypedArray()
        instance.LUPOS_PROCESS_URLS_QUERY = initialStateTmp.allAddressesQuery.map { it.toString() }.toTypedArray()
        instance.LUPOS_PROCESS_URLS_ALL = Luposdate3000Config.mergeProcessurls(instance.LUPOS_PROCESS_URLS_STORE, instance.LUPOS_PROCESS_URLS_QUERY)
        instance.LUPOS_PROCESS_URLS_ALL_NEXT_HOP = Luposdate3000Config.mergeProcessurls(instance.LUPOS_PROCESS_URLS_STORE, instance.LUPOS_PROCESS_URLS_QUERY)
        instance.LUPOS_PROCESS_ID = instance.LUPOS_PROCESS_URLS_ALL.indexOf(initialStateTmp.ownAddress.toString())
        instance.LUPOS_HOME = initialStateTmp.absolutePathToDataDirectory
        instance.LUPOS_PARTITION_MODE = EPartitionModeExt.names.indexOf(config.getOrDefault("LUPOS_PARTITION_MODE", EPartitionModeExt.names[EPartitionModeExt.Process]))
        instance.LUPOS_DICTIONARY_MODE = EDictionaryTypeExt.KV
        instance.LUPOS_BUFFER_SIZE = 8192
        instance.predefinedPartitionScheme = EPredefinedPartitionSchemesExt.names.indexOf(config.getOrDefault("predefinedPartitionScheme", EPredefinedPartitionSchemesExt.names[Luposdate3000Config.predefinedPartitionScheme]))
        instance.mergeLocalOperatorgraphs = config.getOrDefault("mergeLocalOperatorgraphs", Luposdate3000Config.mergeLocalOperatorgraphs)
        instance.queryDistributionMode = EQueryDistributionModeExt.names.indexOf(config.getOrDefault("queryDistributionMode", EQueryDistributionModeExt.names[Luposdate3000Config.queryDistributionMode]))
        instance.useDictionaryInlineEncoding = config.getOrDefault("useDictionaryInlineEncoding", Luposdate3000Config.useDictionaryInlineEncoding)
        instance.REPLACE_STORE_WITH_VALUES = config.getOrDefault("REPLACE_STORE_WITH_VALUES", Luposdate3000Config.REPLACE_STORE_WITH_VALUES)
        instance.enableMulticastInsertions = config.getOrDefault("multicastInsertionsEnabled", Luposdate3000Config.enableMulticastInsertions)
        instance.queue_size = 2048
        instance.communicationHandler = MySimulatorCommunicationHandler(instance, parent)
        instance = LuposdateEndpoint.initializeB(instance)
        rootAddress = instance.LUPOS_PROCESS_URLS_STORE[0]
        rootAddressInt = rootAddress.toInt()
        if (enableSharedMemoryDictionaryCheat) {
            globalCheatStart(instance)
            nodeGlobalDictionaryBackup = instance.nodeGlobalDictionary
            instance.nodeGlobalDictionary = globalCheatDictionary
        }
        instance.distributedOptimizerQueryFactory = {
            DistributedOptimizerQuery()
        }
    }
    override fun shutDown() {
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
        receive(
            QueryPackage(ownAdress, pck.query.encodeToByteArray()),
            pck.onFinish,
            pck.expectedResult,
            pck.verifyAction,
        )
    }

    private fun receive(pck: MySimulatorTestingExecute) {
        receive(
            QueryPackage(ownAdress, pck.query.encodeToByteArray()),
            pck.onFinish,
            null,
            {},
        )
    }

    private fun receive(pck: QueryPackage, onFinish: IDatabasePackage?, expectedResult: MemoryTable?, verifyAction: () -> Unit) {
        val queryString = pck.query.decodeToString()
        // println("$ownAdress DatabaseHandle.receiveQueryPackage $queryString")
        val op = LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, queryString)
        val q = op.getQuery()
        // println("$ownAdress DatabaseHandle.receiveQueryPackage ${q.getRoot()}")
        q.setTransactionID(pck.queryID.toLong())
        q.initialize(op, false, true)
        logger.addOperatorGraph(pck.queryID, q.getOperatorgraphParts())
        val parts = q.getOperatorgraphParts()
        var hostMap = mutableMapOf<String, String>()
        if (parts.size <= 1) {
            parts[""] = q.getRoot().toXMLElement(false)
            if (!hostMap.contains("")) {
                hostMap[""] = "$ownAdress"
            }
        }
// calculating all dependencies --->>>
        val mapTopDown = mutableMapOf<String, MutableSet<String>>()
        val mapBottomUpThis = mutableMapOf<String, MutableSet<String>>()
        val mapBottomUpAbove = mutableMapOf<String, MutableSet<String>>()
        for ((k, v) in parts) {
            mapTopDown[k] = extractKey(v, "POPDistributedReceive", "").toMutableSet()
            mapBottomUpThis[k] = (extractKey(v, "POPDistributedSend", "") + setOf(k)).toMutableSet()
            SanityCheck.check(
                { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_simulator_db/src/commonMain/kotlin/lupos/simulator_db/luposdate3000/DatabaseHandle.kt:204"/*SOURCE_FILE_END*/ },
                { mapBottomUpThis[k]!!.contains(k) },
                { "loop-dependency bottomUp $k ${mapBottomUpThis[k]} ${mapTopDown[k]} $v" }
            )
            SanityCheck.check(
                { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_simulator_db/src/commonMain/kotlin/lupos/simulator_db/luposdate3000/DatabaseHandle.kt:209"/*SOURCE_FILE_END*/ },
                { !mapTopDown[k]!!.contains(k) },
                { "loop-dependency topDown $k ${mapBottomUpThis[k]} ${mapTopDown[k]} $v" }
            )
            SanityCheck.check(
                { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_simulator_db/src/commonMain/kotlin/lupos/simulator_db/luposdate3000/DatabaseHandle.kt:214"/*SOURCE_FILE_END*/ },
                { !(!extractKey(v, "POPDistributedSend", "").contains(k) && k != "") },
                { "something suspicious ... $k ${extractKey(v, "POPDistributedSend", "")} $v" }
            )
        }
        for ((k, v1) in mapBottomUpThis) {
            for (v in v1) { // für jedes ergebnis v von k
                for ((k2, v2) in mapTopDown) {
                    if (v2.contains(v)) { // benötigt k2 ein ergebnis von k?
                        var a = mapBottomUpAbove[k]
                        if (a == null) {
                            a = mutableSetOf()
                            mapBottomUpAbove[k] = a
                        }
                        a.add(k2)
                    }
                }
            }
        }
// calculating all dependencies <<<---
// assign hostnames where necessary --->>>
        val destinations = mutableMapOf("" to pck.sourceAddress)
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
                // define everything as in the DB outside of the simulator - later in the execution-pass there are some overrides due to the not implemented distributed dictionary in the simulator for example
                hostMap.putAll(q.getOperatorgraphPartsToHostMap())
                SanityCheck.check(
                    { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_simulator_db/src/commonMain/kotlin/lupos/simulator_db/luposdate3000/DatabaseHandle.kt:250"/*SOURCE_FILE_END*/ },
                    { hostMap.size == parts.size },
                    { "${hostMap.size} ${parts.size} ... $hostMap $parts" }
                )
                for ((k, v) in hostMap) {
                    for (d in mapTopDown[k]!!) {
                        destinations[d] = v.toInt()
                    }
                }
            }
        }
// assign hostnames where necessary <<<---
// this fixes the inability of the simulator for an distributed dictionary --->>>
        val assignToRootDueToDictionary = mutableSetOf<String>()
        for ((k, v) in parts) {
            if (containsRemoteDictAccess(v)) {
                assignToRootDueToDictionary.add(k)
            }
        }
        loop2@while (assignToRootDueToDictionary.size> 0) {
            val k = assignToRootDueToDictionary.first()
            hostMap[k] = rootAddress
            for (v in mapTopDown[k]!!) {
                destinations[v] = rootAddressInt
            }
            assignToRootDueToDictionary.remove(k)
            val bUp = mapBottomUpAbove[k]
            if (bUp != null) {
                for (v in bUp) {
                    if (hostMap[v] != rootAddress) {
                        assignToRootDueToDictionary.add(v)
                    }
                }
            }
        }
// this fixes the inability of the simulator for an distributed dictionary <<<---
        // println("$ownAdress DatabaseHandle.receiveQueryPackage $parts $hostMap")
        for (k in parts.keys) {
            if (!hostMap.keys.contains(k)) {
                // println("not assigned $k $v")
            }
        }
        receive(MySimulatorOperatorGraphPackage(pck.queryID, parts, destinations, hostMap, onFinish, expectedResult, verifyAction, q))
    }

    private fun receive(pck: MySimulatorAbstractPackage) {
        // println("$ownAdress DatabaseHandle.receiveMySimulatorAbstractPackage ${pck.path}")
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
            SanityCheck.check(
                { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_simulator_db/src/commonMain/kotlin/lupos/simulator_db/luposdate3000/DatabaseHandle.kt:308"/*SOURCE_FILE_END*/ },
                { myPendingWorkData[pck.params["key"]!!] == null }
            )
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
        // println("$ownAdress DatabaseHandle.receiveMySimulatorOperatorGraphPackage")
        val mapTopDown = mutableMapOf<String, MutableSet<String>>()
        val mapBottomUpThis = mutableMapOf<String, MutableSet<String>>()
        val operatorGraphPartsToHostMapTmp = mutableSetOf<String>(rootAddress)
        operatorGraphPartsToHostMapTmp.addAll(pck.operatorGraphPartsToHostMap.values)
        val allHostAdresses = operatorGraphPartsToHostMapTmp.map { it.toInt() }.toSet().toIntArray()
        val nextHops = router!!.getNextDatabaseHops(allHostAdresses)
        for (i in 0 until nextHops.size) {
            if (nextHops[i] == -1) { // if the package-router does not know it - use the first database instance instead.
                nextHops[i] = rootAddressInt
            }
        }
        val packages = mutableMapOf<Int, MySimulatorOperatorGraphPackage>()
        for (i in allHostAdresses.toSet()) {
            packages[i] = MySimulatorOperatorGraphPackage(
                pck.queryID,
                mutableMapOf(),
                mutableMapOf(),
                mutableMapOf(),
                pck.onFinish,
                pck.expectedResult,
                pck.verifyAction,
                pck.query,
            )
        }
        packages[ownAdress] = MySimulatorOperatorGraphPackage(
            pck.queryID,
            mutableMapOf(),
            mutableMapOf(),
            mutableMapOf(),
            pck.onFinish,
            pck.expectedResult,
            pck.verifyAction,
            pck.query,
        )
        for ((k, v) in pck.operatorGraph) {
            mapTopDown[k] = extractKey(v, "POPDistributedReceive", "").toMutableSet()
            mapBottomUpThis[k] = (extractKey(v, "POPDistributedSend", "") + setOf(k)).toMutableSet()
            SanityCheck.check(
                { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_simulator_db/src/commonMain/kotlin/lupos/simulator_db/luposdate3000/DatabaseHandle.kt:367"/*SOURCE_FILE_END*/ },
                { mapBottomUpThis[k]!!.contains(k) },
                { "loop-dependency bottomUp $k ${mapBottomUpThis[k]} ${mapTopDown[k]} $v" }
            )
            SanityCheck.check(
                { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_simulator_db/src/commonMain/kotlin/lupos/simulator_db/luposdate3000/DatabaseHandle.kt:372"/*SOURCE_FILE_END*/ },
                { !mapTopDown[k]!!.contains(k) },
                { "loop-dependency topDown $k ${mapBottomUpThis[k]} ${mapTopDown[k]} $v" }
            )
            SanityCheck.check(
                { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_simulator_db/src/commonMain/kotlin/lupos/simulator_db/luposdate3000/DatabaseHandle.kt:377"/*SOURCE_FILE_END*/ },
                { !(!extractKey(v, "POPDistributedSend", "").contains(k) && k != "") },
                { "something suspicious ... $k ${extractKey(v, "POPDistributedSend", "")} $v" }
            )
        }
        val packageMap = mutableMapOf<String, Int>()
        for ((k, v) in pck.operatorGraphPartsToHostMap) {
            val target = nextHops[allHostAdresses.indexOf(v.toInt())]
            for (i in mapBottomUpThis[k]!!) {
                packageMap[i] = target // required for Send-Multi-operator
            }
        }
        var changed = true
        while (changed) {
            changed = false
            loop@ for ((k, v) in mapTopDown) {
// k benötigt alle Ergebnisse von v
                if (!packageMap.contains(k)) {
                    SanityCheck.check(
                        { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_simulator_db/src/commonMain/kotlin/lupos/simulator_db/luposdate3000/DatabaseHandle.kt:396"/*SOURCE_FILE_END*/ },
                        { v.isNotEmpty() },
                        {
                            "${pck.operatorGraph[k]}"
                        }
                    )
                    var dest = -1
                    innerloop@ for (key in v) {
                        val d = packageMap[key]
// d ist der Ort wo eine dependency von k ausgeführt wird
                        if (d != null) {
                            if (dest == -1) {
                                dest = d
                            } else if (dest != d) {
                                dest = ownAdress
                                break@innerloop
                            }
                        } else {
// die abhängigkeiten stehen noch nicht alle fest, daher jetzt kein assignment
                            continue@loop
                        }
                    }
// alle abhängigkeiten werden in dest berechnet, daher dies jetzt auch
// oder die abhängigkeiten kommen von unterschiedlichen quellen
                    for (i in mapBottomUpThis[k]!!) { // required for send-multi-operator
                        packageMap[i] = dest
                    }
                    for (i in mapTopDown[k]!!) {
                        pck.destinations[i] = dest
                    }
                    changed = true
                }
            }
        }
        SanityCheck.check(
            { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_simulator_db/src/commonMain/kotlin/lupos/simulator_db/luposdate3000/DatabaseHandle.kt:431"/*SOURCE_FILE_END*/ },
            { packageMap.keys.containsAll(pck.operatorGraph.keys) },
            {
                "${(pck.operatorGraph.keys - packageMap.keys).map{"$it\n"}} ${packageMap.map{"$it\n"}} ${pck.operatorGraph.map{"$it\n"}}"
            }
        )
        for ((k, v) in packageMap) {
            val p = packages[v]
            val g = pck.operatorGraph[k]
            if (p != null && g != null) { // p can be null if there is a send-multi
                p.operatorGraph[k] = g
                val h = pck.operatorGraphPartsToHostMap[k]
                if (h != null) {
                    p.operatorGraphPartsToHostMap[k] = h // this allows to keep the final destination, while sending the package only to the next hop
                }
                val d = pck.destinations[k]
                if (d != null) {
                    p.destinations[k] = d
                } else {
                    p.destinations[k] = ownAdress
                }
            }
        }
        // println("$ownAdress DatabaseHandle.receiveMySimulatorOperatorGraphPackage ${allHostAdresses.map{it}}:${nextHops.map{it}} ${pck.operatorGraphPartsToHostMap}->$packageMap")
        for ((k, p) in packages) {
            if (k != ownAdress) {
                if (p.operatorGraph.size> 0) {
                    router!!.send(k, p)
                }
            } else {
                if (instance.mergeLocalOperatorgraphs) {
                    var containsSendMultiFlag = false
                    for (k2 in p.operatorGraph.keys) {
                        if (mapBottomUpThis[k2]!!.size > 1) {
                            containsSendMultiFlag = true
                        }
                    }
                    if (!containsSendMultiFlag) {
// try to merge operatorgraphs for local queries
                        loop@ for (k5 in p.operatorGraph.keys.toSet()) {
                            for (v in mapBottomUpThis[k5]!!) { // what is provided ... often k5==v
                                for ((k6, k7) in mapTopDown) { // what is required
                                    if (k7.contains(v)) {
// merge now !!
                                        val kk6 = p.operatorGraph[k6]
                                        val kk5 = p.operatorGraph[k5]
                                        if (kk5 != null && kk6 != null) {
                                            var res = mergeOperatorGraphLocally(kk6, null, 0, kk6, kk5, k5)
                                            if (res) {
                                                p.operatorGraph.remove(k5) // remove the entire child
                                                p.destinations.remove(k5) // remove the entire child
                                                mapTopDown[k6]!!.remove(k5) // remove the child dependency from the parent
                                                mapTopDown[k6]!!.addAll(mapTopDown[k5]!!)
                                                mapTopDown.remove(k5) // remove the entire child
                                            }
                                        }
                                        continue@loop
                                    }
                                }
                            }
                        }
                    }
                }
                for (k2 in p.operatorGraph.keys) {
                    val graph = p.operatorGraph[k2]!!
                    logger.addWork(p.queryID, ownAdress, graph, mapTopDown[k2]!!, mapBottomUpThis[k2]!!)
                    val w = MySimulatorPendingWork(
                        p.queryID,
                        p.operatorGraph[k2]!!,
                        p.destinations[k2]!!,
                        mapTopDown[k2]!!,
                        k2,
                        pck.onFinish,
                        pck.expectedResult,
                        pck.verifyAction,
                        pck.query,
                    )
                    myPendingWork.add(w)
                }
                doWork()
            }
        }
    }

    private fun mergeOperatorGraphLocally(root: XMLElement, parent2: XMLElement?, parentChildIndex: Int, parent: XMLElement, child: XMLElement, key: String): Boolean {
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
                if (mergeOperatorGraphLocally(root, parent, i, c, child, key)) {
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

    private fun localXMLElementToOPBase(query2: IQuery, node2: XMLElement): IOPBase {
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
            SanityCheck.check(
                { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_simulator_db/src/commonMain/kotlin/lupos/simulator_db/luposdate3000/DatabaseHandle.kt:568"/*SOURCE_FILE_END*/ },
                { keys.size == 1 }
            )
            val key = keys.first()
            SanityCheck.check(
                { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_simulator_db/src/commonMain/kotlin/lupos/simulator_db/luposdate3000/DatabaseHandle.kt:573"/*SOURCE_FILE_END*/ },
                { myPendingWorkData.contains(key) }
            )
            val input = MyInputStreamFromByteArray(myPendingWorkData[key]!!)
            myPendingWorkData.remove(key)
            val res = POPDistributedReceiveSingle(
                query,
                XMLElementToOPBase.createProjectedVariables(node),
                id,
                POPNothing(query, XMLElementToOPBase.createProjectedVariables(node)),
                input,
                null,
                keys.first() to "",
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
                val input: IMyInputStream = MyInputStreamFromByteArray(myPendingWorkData[key]!!)
                myPendingWorkData.remove(key)
                input
            }.toTypedArray()
            val res = POPDistributedReceiveMulti(
                query,
                XMLElementToOPBase.createProjectedVariables(node),
                id,
                POPNothing(query, XMLElementToOPBase.createProjectedVariables(node)),
                inputs,
                Array<IMyOutputStream?>(inputs.size) { null },
                keys.map { it to "" }.toMap()
            )
            query.addPartitionOperator(res.uuid, id)
            res
        }
        return XMLElementToOPBase(query2 as Query, node2, mutableMapOf(), operatorMap)
    }
    private fun containsTripleStoreAccess(node: XMLElement): Boolean {
        var res = false
        when (node.tag) {
            "POPTripleStoreIterator" -> {
                res = true
            }
        }
        for (c in node.childs) {
            res = res || containsTripleStoreAccess(c)
        }
        return res
    }

    private fun containsRemoteDictAccess(node: XMLElement): Boolean {
        var res = false
        when (node.tag) {
            "POPBind", "POPGroup", "POPFilter", "POPModifyData" -> { // POPFilter does not matter, because they do not calculate relevant values, BUT popfilter does create temporary values until the new arithmetic calculation is applied
                res = true
            }
        }
        for (c in node.childs) {
            res = res || containsRemoteDictAccess(c)
        }
        return res
    }

    private fun doWork() {
        var changed = true
        while (changed) {
            changed = false
            for (w in myPendingWork) {
                if (myPendingWorkData.keys.containsAll(w.dependencies)) {
                    myPendingWork.remove(w)
                    // println("$ownAdress ${w.key} executing .. ${w.operatorGraph}")
                    changed = true
                    val query: IQuery
                    if (ownAdress != rootAddressInt || w.operatorGraph.tag != "OPBaseCompound") {
                        query = Query(instance)
                        query.setDictionary(DictionaryCacheLayer(instance, DictionaryNotImplemented(), true))
                    } else {
                        query = w.query
                    }
                    val node = localXMLElementToOPBase(query, w.operatorGraph)
                    when (node) {
                        is POPDistributedSendSingle -> {
                            val out = MySimulatorOutputStreamToPackage(w.queryID, w.destination, "simulator-intermediate-result", mapOf("key" to w.key), router!!)
                            node.evaluate(out)
                            out.close()
                        }
                        is POPDistributedSendMulti -> {
                            val keysList = node.keys.toList()
                            val out = Array<IMyOutputStream?>(keysList.size) {
                                MySimulatorOutputStreamToPackage(w.queryID, w.destination, "simulator-intermediate-result", mapOf("key" to keysList[it]), router!!)
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
                                w.verifyAction()
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
                            // println("done executing")
                        }
                        else -> TODO(node.toString())
                    }
                    break
                }
            }
        }
    }
    override fun receive(pck: IPayload): IPayload? {
        try {
            if (!hadInitDatabaseHopsWithinLuposdate3000) {
                val names = instance.LUPOS_PROCESS_URLS_ALL.map { it.toInt() }.toIntArray()
                val hops = router!!.getNextDatabaseHops(names)
                for (i in 0 until hops.size) {
                    if (hops[i] != -1) {
                        instance.LUPOS_PROCESS_URLS_ALL_NEXT_HOP[i] = hops[i].toString()
                    }
                }
                hadInitDatabaseHopsWithinLuposdate3000 = true
            }
            when (pck) {
                is MySimulatorTestingImportPackage -> receive(pck)
                is MySimulatorTestingCompareGraphPackage -> receive(pck)
                is MySimulatorTestingExecute -> receive(pck)
                is QueryPackage -> receive(pck, null, null, {})
                is MySimulatorAbstractPackage -> receive(pck)
                is MySimulatorOperatorGraphPackage -> receive(pck)
                else -> return pck
            }
        } catch (e: Throwable) {
            throw e
        }
        return null
    }
}
