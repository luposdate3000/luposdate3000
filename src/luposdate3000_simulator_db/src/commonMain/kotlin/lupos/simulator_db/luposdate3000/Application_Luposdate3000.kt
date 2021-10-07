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
import lupos.operator.arithmetik.noinput.AOPVariable
import lupos.operator.base.OPBaseCompound
import lupos.operator.base.Query
import lupos.operator.factory.XMLElementToOPBase
import lupos.operator.factory.XMLElementToOPBaseMap
import lupos.operator.physical.noinput.POPNothing
import lupos.operator.physical.partition.POPDistributedReceiveMulti
import lupos.operator.physical.partition.POPDistributedReceiveMultiCount
import lupos.operator.physical.partition.POPDistributedReceiveMultiOrdered
import lupos.operator.physical.partition.POPDistributedReceiveSingle
import lupos.operator.physical.partition.POPDistributedSendMulti
import lupos.operator.physical.partition.POPDistributedSendSingle
import lupos.operator.physical.partition.POPDistributedSendSingleCount
import lupos.optimizer.distributed.query.DistributedOptimizerQuery
import lupos.optimizer.physical.PhysicalOptimizer
import lupos.parser.JsonParserObject
import lupos.result_format.EQueryResultToStreamExt
import lupos.result_format.ResultFormatManager
import lupos.shared.EPartitionModeExt
import lupos.shared.EPredefinedPartitionSchemesExt
import lupos.shared.EQueryDistributionModeExt
import lupos.shared.IMyInputStream
import lupos.shared.IMyOutputStream
import lupos.shared.IQuery
import lupos.shared.ITripleStoreIndexDescription
import lupos.shared.Luposdate3000Config
import lupos.shared.Luposdate3000Instance
import lupos.shared.MemoryTable
import lupos.shared.MyInputStreamFromByteArray
import lupos.shared.PartitionHelper
import lupos.shared.SanityCheck
import lupos.shared.XMLElement
import lupos.shared.dictionary.DictionaryNotImplemented
import lupos.shared.dictionary.EDictionaryTypeExt
import lupos.shared.dictionary.IDictionary
import lupos.shared.dynamicArray.ByteArrayWrapper
import lupos.shared.inline.File
import lupos.shared.inline.MyPrintWriter
import lupos.shared.operator.IOPBase
import lupos.simulator_iot.ILogger
import lupos.simulator_iot.IPackage_DatabaseTesting
import lupos.simulator_iot.IPayload
import lupos.simulator_iot.Package_Query
import lupos.simulator_iot.Package_QueryResponse
import lupos.simulator_iot.applications.IApplicationStack_Actuator
import lupos.simulator_iot.applications.IApplicationStack_Middleware
import lupos.triple_store_manager.POPTripleStoreIterator
import lupos.triple_store_manager.TripleStoreIndexDescription
public class Application_Luposdate3000 public constructor(
    internal val config: JsonParserObject,
    private val logger: ILogger,
    private val ownAdress: Int,
    private val absolutePathToDataDirectory: String,
    private val dbDeviceAddressesStoreList: MutableList<Int>,
    private val dbDeviceAddressesQueryList: MutableList<Int>,
    private val featureID_store: Int,
    private val featureID_query: Int,
    private val featureID_any: Int,
) : IApplicationStack_Actuator {
    public fun hasStoreCapability(): Boolean = dbDeviceAddressesStoreList.contains(ownAdress)
    public fun hasQueryCapability(): Boolean = dbDeviceAddressesQueryList.contains(ownAdress)
    private lateinit var parent: IApplicationStack_Middleware
    private var enableSharedMemoryDictionaryCheat = config.getOrDefault("SharedMemoryDictionaryCheat", true)
    public var instance: Luposdate3000Instance = Luposdate3000Instance()
    private val myPendingWork = mutableListOf<PendingWork>()
    private val myPendingWorkData = mutableMapOf<Int, ByteArrayWrapper>()
    private var router: IApplicationStack_Middleware? = null
    private var nodeGlobalDictionaryBackup: IDictionary? = null
    private var rootAddress = ""
    private var rootAddressInt = -1
    private var hadInitDatabaseHopsWithinLuposdate3000 = false
    private var doWorkFlag = false
    private var hasOntology = false
    override fun startUp() {
        File(absolutePathToDataDirectory).mkdirs()
        if (dbDeviceAddressesStoreList.isEmpty()) {
            throw Exception("invalid input")
        }
        router = parent
        instance.enableJoinOrderOnHistogram = false
        instance.LUPOS_PROCESS_URLS_STORE = dbDeviceAddressesStoreList.map { it.toString() }.toTypedArray()
        instance.LUPOS_PROCESS_URLS_QUERY = dbDeviceAddressesQueryList.map { it.toString() }.toTypedArray()
        instance.LUPOS_PROCESS_URLS_ALL = Luposdate3000Config.mergeProcessurls(instance.LUPOS_PROCESS_URLS_STORE, instance.LUPOS_PROCESS_URLS_QUERY)
        instance.LUPOS_PROCESS_ID = instance.LUPOS_PROCESS_URLS_ALL.indexOf(ownAdress.toString())
        instance.LUPOS_HOME = absolutePathToDataDirectory
        instance.LUPOS_PARTITION_MODE = EPartitionModeExt.names.indexOf(config.getOrDefault("LUPOS_PARTITION_MODE", EPartitionModeExt.names[EPartitionModeExt.Process]))
        instance.LUPOS_DICTIONARY_MODE = EDictionaryTypeExt.KV
        instance.LUPOS_BUFFER_SIZE = 8192
        instance.predefinedPartitionScheme = EPredefinedPartitionSchemesExt.names.indexOf(config.getOrDefault("predefinedPartitionScheme", EPredefinedPartitionSchemesExt.names[Luposdate3000Config.predefinedPartitionScheme]))
        instance.mergeLocalOperatorgraphs = config.getOrDefault("mergeLocalOperatorgraphs", Luposdate3000Config.mergeLocalOperatorgraphs)
        instance.queryDistributionMode = EQueryDistributionModeExt.names.indexOf(config.getOrDefault("queryDistributionMode", EQueryDistributionModeExt.names[Luposdate3000Config.queryDistributionMode]))
        instance.useDictionaryInlineEncoding = config.getOrDefault("useDictionaryInlineEncoding", Luposdate3000Config.useDictionaryInlineEncoding)
        instance.REPLACE_STORE_WITH_VALUES = config.getOrDefault("REPLACE_STORE_WITH_VALUES", Luposdate3000Config.REPLACE_STORE_WITH_VALUES)
        instance.queue_size = 2048
        instance.communicationHandler = CommunicationHandler_Luposdate3000(instance, parent)
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

    override fun setRouter(router: IApplicationStack_Middleware) {
        parent = router
    }

    override fun shutDown() {
        if (enableSharedMemoryDictionaryCheat) {
            instance.nodeGlobalDictionary = nodeGlobalDictionaryBackup
            globalCheatEnd()
        }
        LuposdateEndpoint.close(instance)
    }

    private fun receive(pck: Package_Luposdate3000_TestingImportPackage) {
        LuposdateEndpoint.importTripleFileC(instance, pck.filename, pck.type, pck.graph)
        val onFinish = pck.getOnFinish()
        if (onFinish != null) {
            receive(onFinish)
        }
    }

    private fun receive(pck: Package_Luposdate3000_TestingCompareGraphPackage) {
        val p = pck.prepare()
        receive(
            Package_Query(ownAdress, p.query.encodeToByteArray()),
            p.getOnFinish(),
            p.expectedResult,
            p.verifyAction,
            p.idx,
        )
    }

    private fun receive(pck: Package_Luposdate3000_TestingExecute) {
        receive(
            Package_Query(ownAdress, pck.query.encodeToByteArray()),
            pck.getOnFinish(),
            null,
            {},
            null,
        )
    }

    private fun receive(pck: Package_Query, onFinish: IPackage_DatabaseTesting?, expectedResult: MemoryTable?, verifyAction: () -> Unit, enforcedIndex: ITripleStoreIndexDescription?) {
        val queryString = pck.query.decodeToString()
        // println("$ownAdress Application_Luposdate3000.receivePackage_Query $queryString")
        val op = if (enforcedIndex != null) {
            val q = Query(instance)
            val o = OPBaseCompound(q, arrayOf(POPTripleStoreIterator(q, listOf("s", "p", "o"), enforcedIndex as TripleStoreIndexDescription, arrayOf(AOPVariable(q, "s"), AOPVariable(q, "p"), AOPVariable(q, "o")))), listOf(listOf("s", "p", "o")))
            PhysicalOptimizer(q).optimizeCall(o)
        } else {
            LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, queryString)
        }
        val q = op.getQuery()
        // println("$ownAdress Application_Luposdate3000.receivePackage_Query ${q.getRoot()}")
        q.setTransactionID(pck.queryID.toLong())
        q.initialize(op, false, true)
        logger.addOperatorGraph(pck.queryID, q.getOperatorgraphParts())
        val parts = q.getOperatorgraphParts()
        var hostMap = mutableMapOf<Int, Int>()
        if (parts.size <= 1) {
            parts[-1] = q.getRoot().toXMLElement(false, PartitionHelper())
            if (!hostMap.contains(-1)) {
                hostMap[-1] = ownAdress
            }
        }
// calculating all dependencies --->>>
        val mapTopDown = mutableMapOf<Int, MutableSet<Int>>()
        val mapBottomUpThis = mutableMapOf<Int, MutableSet<Int>>()
        val mapBottomUpAbove = mutableMapOf<Int, MutableSet<Int>>()
        for ((k, v) in parts) {
            mapTopDown[k] = extractKey(v, "POPDistributedReceive", "").toMutableSet()
            mapBottomUpThis[k] = (extractKey(v, "POPDistributedSend", "") + setOf(k)).toMutableSet()
            SanityCheck.check(
                { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_simulator_db/src/commonMain/kotlin/lupos/simulator_db/luposdate3000/Application_Luposdate3000.kt:227"/*SOURCE_FILE_END*/ },
                { mapBottomUpThis[k]!!.contains(k) },
                { "loop-dependency bottomUp $k ${mapBottomUpThis[k]} ${mapTopDown[k]} $v" }
            )
            SanityCheck.check(
                { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_simulator_db/src/commonMain/kotlin/lupos/simulator_db/luposdate3000/Application_Luposdate3000.kt:232"/*SOURCE_FILE_END*/ },
                { !mapTopDown[k]!!.contains(k) },
                { "loop-dependency topDown $k ${mapBottomUpThis[k]} ${mapTopDown[k]} $v" }
            )
            SanityCheck.check(
                { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_simulator_db/src/commonMain/kotlin/lupos/simulator_db/luposdate3000/Application_Luposdate3000.kt:237"/*SOURCE_FILE_END*/ },
                { !(!extractKey(v, "POPDistributedSend", "").contains(k) && k != -1) },
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
        val destinations = mutableMapOf(-1 to pck.sourceAddress) // not the operatorgraph key, but the connection key
        when (instance.queryDistributionMode) {
            EQueryDistributionModeExt.Routing -> {
                // only define the explicit triple-store access
                for ((key, value) in q.getOperatorgraphPartsToHostMap()) {
                    val part = parts[key]!!
                    if (containsTripleStoreAccess(part)) {
                        hostMap[key] = value.toInt()
                    }
                }
            }
            EQueryDistributionModeExt.Centralized -> {
                // define everything as in the DB outside of the simulator - later in the execution-pass there are some overrides due to the not implemented distributed dictionary in the simulator for example
                for ((k, v) in q.getOperatorgraphPartsToHostMap()) {
                    hostMap[k] = v.toInt()
                }
                SanityCheck.check(
                    { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_simulator_db/src/commonMain/kotlin/lupos/simulator_db/luposdate3000/Application_Luposdate3000.kt:275"/*SOURCE_FILE_END*/ },
                    { hostMap.size == parts.size },
                    { "${hostMap.size} ${parts.size} ... $hostMap $parts" }
                )
                for ((k, v) in hostMap) {
                    for (d in mapTopDown[k]!!) {
                        destinations[d] = v
                    }
                }
            }
        }
// assign hostnames where necessary <<<---
// this fixes the inability of the simulator for an distributed dictionary --->>>
        val assignToRootDueToDictionary = mutableSetOf<Int>()
        for ((k, v) in parts) {
            if (containsRemoteDictAccess(v)) {
                assignToRootDueToDictionary.add(k)
            }
        }
        loop2@ while (assignToRootDueToDictionary.size > 0) {
            val k = assignToRootDueToDictionary.first()
            hostMap[k] = rootAddressInt
            for (v in mapTopDown[k]!!) {
                destinations[v] = rootAddressInt
            }
            assignToRootDueToDictionary.remove(k)
            val bUp = mapBottomUpAbove[k]
            if (bUp != null) {
                for (v in bUp) {
                    if (hostMap[v] != rootAddressInt) {
                        assignToRootDueToDictionary.add(v)
                    }
                }
            }
        }
// this fixes the inability of the simulator for an distributed dictionary <<<---
// remove unnecessary query parts, which just receive and send from and to single locations --->>>
        var changed = true
        loop3@ while (changed) {
            changed = false
            for ((k, p) in parts) {
                if (p.tag == "POPDistributedSendSingle") {
                    var c = p["children"]!!.childs[0]
                    while (c.tag == "POPDebug") {
                        c = c["children"]!!.childs[0]
                    }
                    if (c.tag == "POPDistributedReceiveSingle") {
                        val receiverKey = c["partitionDistributionKey"]!!.attributes["key"]!!.toInt()
                        val senderKey = k
// replace senderkey with receiverKey
// remove the senderkey entirely
                        // println("initial merge $senderKey -> $receiverKey")
                        val p = parts[mapBottomUpAbove[k]!!.first()]!!
                        replacereceiverKey(p!!, receiverKey, senderKey)
                        val d = destinations[senderKey]
                        if (d != null) {
                            destinations[receiverKey] = d
                        } else {
                            destinations.remove(receiverKey) // is this required?
                        }
                        parts.remove(senderKey)
                        hostMap.remove(senderKey)
                        destinations.remove(senderKey)
                        changed = true
                        continue@loop3
                    }
                }
            }
        }
// remove unnecessary query parts, which just receive and send from and to single locations <<<---
        // println("$ownAdress Application_Luposdate3000.receivePackage_Query $queryString $op $parts $hostMap $mapBottomUpAbove $mapTopDown")
        for (k in parts.keys) {
            if (!hostMap.keys.contains(k)) {
                // println("not assigned $k $v")
            }
        }
        receive(Package_Luposdate3000_Operatorgraph(pck.queryID, parts, destinations, hostMap, onFinish, expectedResult, verifyAction, q))
    }

    private fun replacereceiverKey(node: XMLElement, receiverKey: Int, senderKey: Int): Boolean {
        if (node.tag == "partitionDistributionKey" && node.attributes["key"]!!.toInt() == senderKey) {
            node.attributes["key"] = "$receiverKey"
            return true
        }
        for (c in node.childs) {
            if (replacereceiverKey(c, receiverKey, senderKey)) {
                return true
            }
        }
        return false
    }

    private fun receive(pck: Package_Luposdate3000_Abstract) {
        if (pck.path == "/shacl/ontology/import" || pck.path == "/shacl/ontology/load") {
            hasOntology = true
        }

        // println("$ownAdress Application_Luposdate3000.receivePackage_Luposdate3000_Abstract ${pck.path}")
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
            // println("Application_Luposdate3000.receive simulator-intermediate-result $ownAdress ${pck.params["key"]}")
            SanityCheck.check(
                { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_simulator_db/src/commonMain/kotlin/lupos/simulator_db/luposdate3000/Application_Luposdate3000.kt:385"/*SOURCE_FILE_END*/ },
                { myPendingWorkData[pck.params["key"]!!.toInt()] == null }
            )
            myPendingWorkData[pck.params["key"]!!.toInt()] = pck.data
            true
        }
        val target = paths[pck.path]
        if (target == null) {
            TODO(pck.path)
        } else {
            val input = MyInputStreamFromByteArray(pck.data)
            val output = OutputStreamToVoid()
            target.action(pck.params, input, output)
            input.close()
            output.close()
        }
    }

    private fun myGetNextHop(a: IntArray, flag: Int): IntArray {
        var res = router!!.getNextFeatureHops(a, flag)
        for (i in 0 until res.size) {
            if (res[i] == -1) { // if the package-router does not know it - use the first database instance instead.
                res[i] = rootAddressInt
            }
        }
        for (i in 0 until res.size) {
            if (a[i] == ownAdress) {
                res[i] = ownAdress // just to make sure that self messages are NEVER routed somewhere -> this is a bug in the routing-protocol?!?
            }
        }
        return res
    }

    private fun receive(pck: Package_Luposdate3000_Operatorgraph) {
        // println("$ownAdress Application_Luposdate3000.receivePackage_Luposdate3000_Operatorgraph")
        val mapTopDown = mutableMapOf<Int, MutableSet<Int>>()
        val mapBottomUpThis = mutableMapOf<Int, MutableSet<Int>>()
        val operatorGraphPartsToHostMapTmp = mutableSetOf<Int>(rootAddressInt, ownAdress)
        operatorGraphPartsToHostMapTmp.addAll(pck.operatorGraphPartsToHostMap.values)
        val allHostAdresses = operatorGraphPartsToHostMapTmp.map { it.toInt() }.toSet().toIntArray()
        val nextHops = myGetNextHop(allHostAdresses, featureID_any)
        for (i in 0 until nextHops.size) {
            if (nextHops[i] == -1) { // if the package-router does not know it - use the first database instance instead.
                nextHops[i] = rootAddressInt
            }
        }
        val packages = mutableMapOf<Int, Package_Luposdate3000_Operatorgraph>()
        for (i in nextHops.toSet()) {
            packages[i] = Package_Luposdate3000_Operatorgraph(
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
        for ((k, v) in pck.operatorGraph) {
            mapTopDown[k] = extractKey(v, "POPDistributedReceive", "").toMutableSet()
            mapBottomUpThis[k] = (extractKey(v, "POPDistributedSend", "") + setOf(k)).toMutableSet()
            SanityCheck.check(
                { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_simulator_db/src/commonMain/kotlin/lupos/simulator_db/luposdate3000/Application_Luposdate3000.kt:448"/*SOURCE_FILE_END*/ },
                { mapBottomUpThis[k]!!.contains(k) },
                { "loop-dependency bottomUp $k ${mapBottomUpThis[k]} ${mapTopDown[k]} $v" }
            )
            SanityCheck.check(
                { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_simulator_db/src/commonMain/kotlin/lupos/simulator_db/luposdate3000/Application_Luposdate3000.kt:453"/*SOURCE_FILE_END*/ },
                { !mapTopDown[k]!!.contains(k) },
                { "loop-dependency topDown $k ${mapBottomUpThis[k]} ${mapTopDown[k]} $v" }
            )
            SanityCheck.check(
                { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_simulator_db/src/commonMain/kotlin/lupos/simulator_db/luposdate3000/Application_Luposdate3000.kt:458"/*SOURCE_FILE_END*/ },
                { !(!extractKey(v, "POPDistributedSend", "").contains(k) && k != -1) },
                { "something suspicious ... $k ${extractKey(v, "POPDistributedSend", "")} $v" }
            )
        }
        val packageMap = mutableMapOf<Int, Int>()
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
                        { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_simulator_db/src/commonMain/kotlin/lupos/simulator_db/luposdate3000/Application_Luposdate3000.kt:477"/*SOURCE_FILE_END*/ },
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
            { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_simulator_db/src/commonMain/kotlin/lupos/simulator_db/luposdate3000/Application_Luposdate3000.kt:512"/*SOURCE_FILE_END*/ },
            { packageMap.keys.containsAll(pck.operatorGraph.keys) },
            { "${(pck.operatorGraph.keys - packageMap.keys).map { "$it\n" }} ${packageMap.map { "$it\n" }} ${pck.operatorGraph.map { "$it\n" }}" }
        )
        for ((k, v) in packageMap) {
            val p = packages[v]
            val g = pck.operatorGraph[k]
            if (p != null && g != null) {
// p must not be null - otherwise packages are not sent and therefore lost
// g may be null for sendmulti???
                p.operatorGraph[k] = g
                val h = pck.operatorGraphPartsToHostMap[k]
                if (h != null) {
                    p.operatorGraphPartsToHostMap[k] = h // this allows to keep the final destination, while sending the package only to the next hop
                }
                for (i in mapBottomUpThis[k]!!) {
                    val d = pck.destinations[i]
                    if (d != null) {
                        p.destinations[i] = d
                    } else {
                        p.destinations[i] = ownAdress
                    }
                }
            } else if (p == null) {
                TODO()
            }
        }
        // println("$ownAdress Application_Luposdate3000.receivePackage_Luposdate3000_Operatorgraph ${allHostAdresses.map{it}}:${nextHops.map{it}} ${pck.operatorGraphPartsToHostMap}->$packageMap")
        for ((k, p) in packages) {
            if (k != ownAdress) {
                if (p.operatorGraph.size > 0) {
                    router!!.send(k, p)
                }
            } else {
                if (instance.mergeLocalOperatorgraphs) {
// try to merge operatorgraphs for local queries
                    loop@ for (k5 in p.operatorGraph.keys.toSet()) {
                        if (mapBottomUpThis[k5]!!.size > 1) {
                            continue@loop
                        }
                        for (v in mapBottomUpThis[k5]!!) { // what is provided ... often k5==v
                            for ((k6, k7) in mapTopDown) { // what is required
                                if (k7.contains(v)) {
// merge now !!
                                    val kk6 = p.operatorGraph[k6]
                                    val kk5 = p.operatorGraph[k5]
                                    if (kk5 != null && kk6 != null) {
                                        var res = mergeOperatorGraphLocally(kk6, null, 0, kk6, kk5, k5)
                                        if (res) {
                                            // println("node local merge $k5 into $k6")
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
                for (k2 in p.operatorGraph.keys) {
                    val graph = p.operatorGraph[k2]!!
                    logger.addWork(p.queryID, ownAdress, graph, mapTopDown[k2]!!, mapBottomUpThis[k2]!!)
                    val dep = mapTopDown[k2]!!.toIntArray()
                    val keys = mapBottomUpThis[k2]!!.toIntArray()
                    val dest = IntArray(keys.size) { p.destinations[keys[it]]!! }
                    val w = PendingWork(
                        p.queryID,
                        p.operatorGraph[k2]!!,
                        dest,
                        dep,
                        keys,
                        pck.onFinish,
                        pck.expectedResult,
                        pck.verifyAction,
                        pck.query,
                    )
                    myPendingWork.add(w)
                }
            }
        }
    }

    private fun mergeOperatorGraphLocally(root: XMLElement, parent2: XMLElement?, parentChildIndex: Int, parent: XMLElement, child: XMLElement, key: Int): Boolean {
        if (parent.tag == "POPDistributedReceiveSingle") {
            val tmp = parent.childs.filter { it.tag == "partitionDistributionKey" }
            val tmp2 = child.childs.filter { it.tag == "partitionDistributionKey" }
            if (tmp.size == 1 &&
                tmp.first().attributes["key"]!!.toInt() == key &&
                tmp2.size == 1 &&
                tmp2.first().attributes["key"]!!.toInt() == key &&
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

    private fun extractKey(node: XMLElement, targetTag: String, parentTag: String): Set<Int> {
        val res = mutableSetOf<Int>()
        if (parentTag.startsWith(targetTag)) {
            if (node.tag == "partitionDistributionKey") {
                res.add(node.attributes["key"]!!.toInt())
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
            var key: Int = -1
            for (c in node.childs) {
                if (c.tag == "partitionDistributionKey") {
                    key = c.attributes["key"]!!.toInt()
                    break
                }
            }
            val keyData = myPendingWorkData[key]!!
            val input = MyInputStreamFromByteArray(keyData)
            myPendingWorkData.remove(key)
            val res = POPDistributedReceiveSingle(
                query,
                XMLElementToOPBase.createProjectedVariables(node),
                id,
                POPNothing(query, XMLElementToOPBase.createProjectedVariables(node)),
                input,
                null,
                key to "",
            )
            query.addPartitionOperator(res.uuid, id)
            res
        }
        operatorMap["POPDistributedReceiveMulti"] = { query, node, mapping, recursionFunc ->
            val id = node.attributes["partitionID"]!!.toInt()
            val hosts = mutableMapOf<Int, String>()
            for (c in node.childs) {
                if (c.tag == "partitionDistributionKey") {
                    hosts[c.attributes["key"]!!.toInt()] = c.attributes["host"]!!
                }
            }
            val inputs = hosts.keys.map { key ->
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
                hosts,
            )
            query.addPartitionOperator(res.uuid, id)
            res
        }
        operatorMap["POPDistributedReceiveMultiCount"] = { query, node, mapping, recursionFunc ->
            val id = node.attributes["partitionID"]!!.toInt()
            val hosts = mutableMapOf<Int, String>()
            for (c in node.childs) {
                if (c.tag == "partitionDistributionKey") {
                    hosts[c.attributes["key"]!!.toInt()] = c.attributes["host"]!!
                }
            }
            val inputs = hosts.keys.map { key ->
                val input: IMyInputStream = MyInputStreamFromByteArray(myPendingWorkData[key]!!)
                myPendingWorkData.remove(key)
                input
            }.toTypedArray()
            val res = POPDistributedReceiveMultiCount(
                query,
                XMLElementToOPBase.createProjectedVariables(node),
                id,
                POPNothing(query, XMLElementToOPBase.createProjectedVariables(node)),
                inputs,
                Array<IMyOutputStream?>(inputs.size) { null },
                hosts,
            )
            query.addPartitionOperator(res.uuid, id)
            res
        }
        operatorMap["POPDistributedReceiveMultiOrdered"] = { query, node, mapping, recursionFunc ->
            val id = node.attributes["partitionID"]!!.toInt()
            val hosts = mutableMapOf<Int, String>()
            val orderedBy = mutableListOf<String>()
            for (c in node.childs) {
                when (c.tag) {
                    "partitionDistributionKey" -> {
                        hosts[c.attributes["key"]!!.toInt()] = c.attributes["host"]!!
                    }
                    "orderedBy" -> {
                        orderedBy.add(c.attributes["name"]!!)
                    }
                }
            }
            val inputs = hosts.keys.map { key ->
                val input: IMyInputStream = MyInputStreamFromByteArray(myPendingWorkData[key]!!)
                myPendingWorkData.remove(key)
                input
            }.toTypedArray()
            val res = POPDistributedReceiveMultiOrdered(
                query,
                XMLElementToOPBase.createProjectedVariables(node),
                id,
                POPNothing(query, XMLElementToOPBase.createProjectedVariables(node)),
                inputs,
                Array<IMyOutputStream?>(inputs.size) { null },
                hosts,
                orderedBy,
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
        if (hasOntology) {
            return false
        }
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
        if (!doWorkFlag) {
            doWorkFlag = true // only one exeution at a time
            try {
                var changed = true
                while (changed) {
                    changed = false
                    for (w in myPendingWork) {
                        var flag = true
                        for (k in w.dependencies) {
                            flag = flag && myPendingWorkData.keys.contains(k)
                        }
                        if (flag) {
                            myPendingWork.remove(w)
                            changed = true
                            val query: IQuery
                            if (ownAdress != rootAddressInt || w.operatorGraph.tag != "OPBaseCompound") {
                                query = Query(instance)
                                query.setDictionary(DictionaryCacheLayer(instance, DictionaryNotImplemented(instance), true))
                            } else {
                                query = w.query
                            }
                            val node = localXMLElementToOPBase(query, w.operatorGraph)
                            // println(node)
                            when (node) {
                                is POPDistributedSendSingle -> {
                                    // println("$ownAdress ${w.keys.map{it}} executing .. $node")
                                    val out = OutputStreamToPackage(w.queryID, w.destinations[0], "simulator-intermediate-result", mapOf("key" to "${w.keys[0]}"), router!!)
                                    node.evaluate(out)
                                    out.close()
                                }
                                is POPDistributedSendSingleCount -> {
                                    // println("$ownAdress ${w.keys.map{it}} executing .. $node")
                                    val out = OutputStreamToPackage(w.queryID, w.destinations[0], "simulator-intermediate-result", mapOf("key" to "${w.keys[0]}"), router!!)
                                    node.evaluate(out)
                                    out.close()
                                }
                                is POPDistributedSendMulti -> {
                                    SanityCheck.check(
                                        { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_simulator_db/src/commonMain/kotlin/lupos/simulator_db/luposdate3000/Application_Luposdate3000.kt:819"/*SOURCE_FILE_END*/ },
                                        { w.keys.size == node.keys.size && w.keys.toSet().containsAll(node.keys.toSet()) }
                                    )
                                    // println("$ownAdress ${w.keys.map{it}}->${w.destinations.map{it}} executing .. $node")
                                    val out = Array<IMyOutputStream?>(w.keys.size) {
                                        OutputStreamToPackage(w.queryID, w.destinations[it], "simulator-intermediate-result", mapOf("key" to w.keys[it].toString()), router!!)
                                    }
                                    node.evaluate(out)
                                    for (o in out) {
                                        o?.close()
                                    }
                                }
                                is OPBaseCompound -> {
                                    // println("$ownAdress root executing .. $node")
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
                                            router!!.send(w.destinations[0], Package_QueryResponse("success".encodeToByteArray(), w.queryID))
                                        }
                                    } else {
                                        val buf = MyPrintWriter(true)
                                        val evaluatorInstance = ResultFormatManager[EQueryResultToStreamExt.names[EQueryResultToStreamExt.DEFAULT_STREAM]]!!
                                        evaluatorInstance(node, buf, false)
                                        if (w.onFinish != null) {
                                            receive(w.onFinish)
                                        } else {
                                            router!!.send(w.destinations[0], Package_QueryResponse(buf.toString().encodeToByteArray(), w.queryID))
                                        }
                                    }
                                    // println("done executing")
                                }
                                else -> TODO(node.toString())
                            }
                            break
                        } else {
                            // println("$ownAdress cannot work at ${w.keys.map{it}}, because ${w.dependencies.toSet() - myPendingWorkData.keys} is missing")
                        }
                    }
                }
            } catch (e: Throwable) {
                doWorkFlag = false
                e.printStackTrace()
                throw e
            }
            doWorkFlag = false
        }
    }

    override fun receive(pck: IPayload): IPayload? {
        try {
            if (!hadInitDatabaseHopsWithinLuposdate3000) {
                val names = instance.LUPOS_PROCESS_URLS_ALL.map { it.toInt() }.toIntArray()
                instance.LUPOS_PROCESS_URLS_ALL_NEXT_HOP = { arr -> // contains indices of luposdate3000
                    val arr2 = arr.map { instance.LUPOS_PROCESS_URLS_ALL[it].toInt() }.toIntArray() // contains device ids
                    val res = myGetNextHop(arr2, featureID_any) // res contains device ids
                    res.map { instance.LUPOS_PROCESS_URLS_ALL.indexOf(it.toString()) }.toIntArray() // the final result is again indices of luposdate3000
                }
                hadInitDatabaseHopsWithinLuposdate3000 = true
            }
            when (pck) {
                is Package_Luposdate3000_TestingImportPackage -> receive(pck)
                is Package_Luposdate3000_TestingCompareGraphPackage -> receive(pck)
                is Package_Luposdate3000_TestingExecute -> receive(pck)
                is Package_Query -> receive(pck, null, null, {}, null)
                is Package_Luposdate3000_Abstract -> receive(pck)
                is Package_Luposdate3000_Operatorgraph -> receive(pck)
                else -> return pck
            }
        } catch (e: Throwable) {
            throw e
        }
        doWork()
        return null
    }
}
