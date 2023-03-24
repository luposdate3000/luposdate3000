/*
 * This file is part of the Luposdate3000 distribution (https: // github.com/luposdate3000/luposdate3000).
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
 * along with this program. If not, see <http: // www.gnu.org/licenses/>.
 */

package lupos.simulator_db.luposdate3000
import lupos.dictionary.DictionaryCacheLayer
import lupos.dictionary.DictionaryFactory
import lupos.endpoint.LuposdateEndpoint
import lupos.endpoint.PathMappingHelper
import lupos.endpoint.RestEndpoint
import lupos.endpoint.WebRootEndpoint
import lupos.operator.arithmetik.noinput.AOPVariable
import lupos.operator.base.IPOPLimit
import lupos.operator.base.OPBase
import lupos.operator.base.OPBaseCompound
import lupos.operator.base.Query
import lupos.operator.factory.BinaryToOPBase
import lupos.operator.factory.BinaryToOPBaseMap
import lupos.operator.factory.ConverterBinaryEncoder
import lupos.operator.factory.ConverterBinaryToBinary
import lupos.operator.factory.ConverterBinaryToIteratorBundle
import lupos.operator.factory.ConverterBinaryToPOPJson
import lupos.operator.factory.ConverterString
import lupos.operator.factory.HelperMetadata
import lupos.operator.physical.multiinput.EvalJoinCartesianProduct
import lupos.operator.physical.multiinput.EvalJoinMergeFromUnsortedData
import lupos.operator.physical.partition.EvalDistributedReceiveMulti
import lupos.operator.physical.partition.EvalDistributedReceiveMultiCount
import lupos.operator.physical.partition.EvalDistributedReceiveMultiOrdered
import lupos.operator.physical.partition.EvalDistributedReceiveSingle
import lupos.operator.physical.partition.EvalDistributedReceiveSingleCount
import lupos.operator.physical.partition.EvalDistributedSendMulti
import lupos.operator.physical.partition.EvalDistributedSendSingle
import lupos.operator.physical.partition.EvalDistributedSendSingleCount
import lupos.operator.physical.partition.EvalDistributedSendWrapper
import lupos.optimizer.physical.PhysicalOptimizer
import lupos.result_format.EQueryResultToStreamExt
import lupos.result_format.ResultFormatManager
import lupos.shared.EOperatorIDExt
import lupos.shared.EOptimizerExt
import lupos.shared.EPartitionModeExt
import lupos.shared.EPredefinedPartitionSchemesExt
import lupos.shared.EQueryDistributionModeExt
import lupos.shared.IMyInputStream
import lupos.shared.IMyOutputStream
import lupos.shared.ITripleStoreIndexDescription
import lupos.shared.InvalidInputException
import lupos.shared.Luposdate3000Config
import lupos.shared.Luposdate3000Instance
import lupos.shared.MemoryTable
import lupos.shared.MyInputStreamFromByteArray
import lupos.shared.OperationCanNotBeLocalException
import lupos.shared.Partition
import lupos.shared.SanityCheck
import lupos.shared.dictionary.DictionaryNotImplemented
import lupos.shared.dictionary.EDictionaryTypeExt
import lupos.shared.dictionary.IDictionary
import lupos.shared.dynamicArray.ByteArrayWrapper
import lupos.shared.inline.File
import lupos.shared.inline.MyPrintWriter
import lupos.shared.inline.dynamicArray.ByteArrayWrapperExt
import lupos.shared.myPrintStackTrace
import lupos.shared.myPrintStackTraceAndThrowAgain
import lupos.shared.operator.iterator.IteratorBundleRoot
import lupos.triple_store_manager.POPTripleStoreIterator
import lupos.triple_store_manager.TripleStoreIndexDescription
import simora.ILogger
import simora.IPayload
import simora.applications.IApplicationStack_Actuator
import simora.applications.IApplicationStack_Middleware
import simora.applications.scenario.parking.IPackage_DatabaseTesting
import simora.applications.scenario.parking.Package_Query
import simora.applications.scenario.parking.Package_QueryResponse
import simora.parser.JsonParserObject
import kotlin.math.ceil
import kotlin.math.log2
import kotlin.math.pow

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
    private val tryLocalExecution: Boolean,
) : IApplicationStack_Actuator {
    public fun hasStoreCapability(): Boolean = dbDeviceAddressesStoreList.contains(ownAdress)
    public fun hasQueryCapability(): Boolean = dbDeviceAddressesQueryList.contains(ownAdress)
    private lateinit var parent: IApplicationStack_Middleware
    private var enableSharedMemoryDictionaryCheat = config.getOrDefault("SharedMemoryDictionaryCheat", true)
    public var instance: Luposdate3000Instance = Luposdate3000Instance()
    private val myPendingWork = mutableListOf<PendingWork>()
    private val myPendingWorkData = mutableMapOf<Pair<Int, Int>, Any>()
    private var router: IApplicationStack_Middleware? = null
    private var nodeGlobalDictionaryBackup: IDictionary? = null
    private var rootAddress = ""
    private var rootAddressInt = -1
    private var hadInitDatabaseHopsWithinLuposdate3000 = false
    private var doWorkFlag = false
    private var hasOntology = false
    private var queryCache = mutableMapOf<Int, Query>() // only works on root node ... queryID -> Query
    override fun emptyEventQueue(): String? = null
    override fun startUp() {
        File(absolutePathToDataDirectory).mkdirs()
        if (dbDeviceAddressesStoreList.isEmpty()) {
            throw InvalidInputException()
        }
        router = parent
        instance.optimizer = EOptimizerExt.names.indexOf(config.getOrDefault("optimizer", EOptimizerExt.names[EOptimizerExt.TopologyOnly]))
        instance.inSimulator = true
        instance.enableJoinOrderOnHistogram = false
        instance.tryLocalExecution = tryLocalExecution
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
        instance.relocateOperatorsIfTooMuchDataIsSent = config.getOrDefault("relocateOperatorsIfTooMuchDataIsSent", Luposdate3000Config.relocateOperatorsIfTooMuchDataIsSent)
        instance.queue_size = 2048
        instance.communicationHandler = CommunicationHandler_Luposdate3000(instance, parent)
        instance.maxThreads = ((2.0).pow(ceil(log2(instance.LUPOS_PROCESS_URLS_ALL.size.toDouble())))).toInt()
        instance = LuposdateEndpoint.initializeB(instance)
        instance.timeout = 600000
        rootAddress = instance.LUPOS_PROCESS_URLS_STORE[0]
        rootAddressInt = rootAddress.toInt()
        if (enableSharedMemoryDictionaryCheat) {
            globalCheatStart(instance)
            nodeGlobalDictionaryBackup = instance.nodeGlobalDictionary
            instance.nodeGlobalDictionary = globalCheatDictionary
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
        if (myPendingWork.size > 0) {
            TODO("there is pending work on close")
        }
        if (myPendingWorkData.size > 0) {
            TODO("there is pending data for work on close ${myPendingWorkData.keys}")
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
println("queryString: "+queryString)
//        println(queryString)
        //      println(queryString)
        //    println()
        val q = Query(instance)
if(pck.order!=null){
val order=pck.order!!.decodeToString().split(",").map { it.toInt() }
q.optimizer = EOptimizerExt.MachineLearningLarge
q.machineLearningOptimizerOrder2 = order
            q.machineLearningCounter = 0
}

        val machineLearningOptimizerOrder = pck.attributes["machineLearningOptimizerOrder"]
        if (machineLearningOptimizerOrder != null) {
            q.machineLearningOptimizerOrder = machineLearningOptimizerOrder as Int
        }
        val machineLearningOptimizerTripleCount = pck.attributes["machineLearningOptimizerTripleCount"]
        if (machineLearningOptimizerTripleCount != null) {
            q.machineLearningOptimizerTripleCount = machineLearningOptimizerTripleCount as Int
        }
        val op = if (enforcedIndex != null) {
            val o = OPBaseCompound(q, arrayOf(POPTripleStoreIterator(q, listOf("s", "p", "o"), enforcedIndex as TripleStoreIndexDescription, arrayOf(AOPVariable(q, "s"), AOPVariable(q, "p"), AOPVariable(q, "o")))), listOf(listOf("s", "p", "o")))
            PhysicalOptimizer(q).optimizeCall(o)
        } else {
            LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, q, queryString)
        }
        if (instance.tryLocalExecution) {
            try {
                var hasSort = false
                var limitOperators = mutableListOf<IPOPLimit>()
                val storeoptimizer = instance.optimizer
                instance.optimizer = EOptimizerExt.Default
                val q2 = Query(instance)
                val op2 = LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, q2, queryString)
                instance.optimizer = storeoptimizer
                val localOP = (op2 as OPBase).toLocalOperatorGraph(Partition(), { limitOperators.add(it) }, { hasSort = true })
                if (limitOperators.size > 0 && !hasSort && localOP != null) {
                    val iteratorBundle = localOP.evaluateRootBundle()
                    val buf = MyPrintWriter(true)
                    val evaluatorInstance = ResultFormatManager[EQueryResultToStreamExt.names[EQueryResultToStreamExt.DEFAULT_STREAM]]!!
                    evaluatorInstance(iteratorBundle, buf)
                    if (limitOperators.fold(true) { s, t -> s && t.limitFullfilled() }) {
                        if (expectedResult != null) {
                            val buf2 = MyPrintWriter(false)
                            val result = (LuposdateEndpoint.evaluateIteratorBundleToResultE(instance, iteratorBundle, buf2, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
                            val buf2_err = MyPrintWriter()
                            if (!expectedResult.equalsVerbose(result, true, true, false, buf2_err)) { // TODO check the ordering of columns as well ...
                                throw Exception(buf2_err.toString())
                            }
                            verifyAction()
                            if (onFinish != null) {
                                receive(onFinish)
                            } else {
                                router!!.send(ownAdress, Package_QueryResponse("success".encodeToByteArray(), pck.queryID))
                                println("instance.tryLocalExecution ... ")
                            }
                        } else {
                            if (onFinish != null) {
                                receive(onFinish)
                            } else {
                                router!!.send(ownAdress, Package_QueryResponse(buf.toString().encodeToByteArray(), pck.queryID))
                            }
                        }
                        return
                    }
                } else {
                    // println("can not use local execution due to limitOperators.size > 0 && !hasSort && localOP != null .... ${limitOperators.size} ${!hasSort} ${localOP != null} $op2")
                }
            } catch (e: OperationCanNotBeLocalException) {
            } catch (e: Throwable) {
                e.myPrintStackTrace(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_simulator_db/src/commonMain/kotlin/lupos/simulator_db/luposdate3000/Application_Luposdate3000.kt:299"/*SOURCE_FILE_END*/)
            }
        } else {
            // println("can not use local execution due to instance.tryLocalExecution")
        }
        q.setTransactionID(pck.queryID.toLong())
        q.initialize(op, false, true)
        val binaryPair = BinaryToOPBase.convertToByteArrayAndMeta(op, instance.LUPOS_PARTITION_MODE == EPartitionModeExt.Process, true, false)
        val data = binaryPair
        val handler = HelperMetadata(data, pck.queryID)
        val destinations = mutableMapOf<Int, Int>()
        for (i in handler.lastRootOperator until 0) {
            destinations[i] = ownAdress
        }

// TODO OPBaseCompound müssen anders behandelt werden, damit die operationen nacheinander ausgeführt werden, und nicht alle gleichzeitig

        for (i in handler.id2off.keys) {
            if (handler.id2host[i] == null) {
                handler.id2host[i] = mutableSetOf(ownAdress.toString())
            }
        }
        if (handler.id2host[-1] == null) {
            handler.id2host[-1] = mutableSetOf(ownAdress.toString())
        }
        when (instance.queryDistributionMode) {
            EQueryDistributionModeExt.Routing -> {
            }
            EQueryDistributionModeExt.Centralized -> {
                var keysToAssign = handler.id2off.keys - handler.id2host.keys
                var changed = true
                while (keysToAssign.size > 0 && changed) {
                    changed = false
                    for (maybeAssign in keysToAssign) {
                        val x = handler.getDependenciesForID1(maybeAssign)
                        for (child in x.keys) {
                            val h = handler.id2host[child]
                            if (h != null) {
                                handler.id2host[maybeAssign] = h
                                changed = true
                            }
                        }
                    }
                    keysToAssign = handler.id2host.keys - handler.id2off.keys
                }
            }
        }
        receive(Package_Luposdate3000_Operatorgraph(pck.queryID, data, destinations, onFinish, expectedResult, verifyAction, q, handler.lastRootOperator))
    }

    private fun receive(pck: Package_Luposdate3000_Abstract) {
        if (pck.path == "/shacl/ontology/import" || pck.path == "/shacl/ontology/load") {
            hasOntology = true
        }

        val paths = mutableMapOf<String, PathMappingHelper>()
        RestEndpoint.initialize(instance, paths)
        WebRootEndpoint.initialize(paths)
        paths["/distributed/query/dictionary/register"] = PathMappingHelper(false, mapOf()) { _, _, _ ->
            true
        }
        paths["/distributed/query/dictionary/remove"] = PathMappingHelper(false, mapOf()) { _, _, _ ->
            true
        }
        paths["simulator-intermediate-result"] = PathMappingHelper(false, mapOf()) { _, _, _ ->
            if (SanityCheck.enabled) { if (!(myPendingWorkData[pck.params["query"]!!.toInt() to pck.params["key"]!!.toInt()] == null)) { throw Exception("SanityCheck failed") } }
            myPendingWorkData[pck.params["query"]!!.toInt() to pck.params["key"]!!.toInt()] = pck.data
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
                res[i] = a[i]
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
        val operatorGraphPartsToHostMapTmp = mutableSetOf<Int>(rootAddressInt, ownAdress)
        var handler = HelperMetadata(pck.data, pck.queryID)
        operatorGraphPartsToHostMapTmp.addAll(handler.id2host.values.map { it.map { it.toInt() } }.flatten())
        val allHostAdresses = operatorGraphPartsToHostMapTmp.map { it.toInt() }.toSet().toIntArray()
// 1. calculate next hops for every subquery
        val nextHops = myGetNextHop(allHostAdresses, featureID_any)
        for (i in 0 until nextHops.size) {
            if (nextHops[i] == -1) {
// 1.b. if the destination is unknown, use the root-database instead
                nextHops[i] = rootAddressInt
            }
        }
        var target2id = mutableMapOf<Int, MutableSet<Int>>()

// 2. split receive-multi operators to match the network layout
        val partIds = handler.id2host.keys.toMutableSet()
        var changed = true
        loop@ while (changed) {
            changed = false
            for (id in partIds) {
                val id2host = handler.id2host[id]
                if (id2host != null) {
                    val depsForId = handler.getDependenciesForID1(id)
                    val key2host = depsForId.toList().map { it -> it.second to handler.id2host[it.first]?.map { it2 -> it2.toInt() } }.toMap()
                    val key2hop = key2host.toList().map { it.first to it.second?.map { it2 -> nextHops[allHostAdresses.indexOf(it2)] } }.toMap()
                    val hosts = key2hop.values.toSet()
                    if (hosts.size > 1) {
                        if (true) {
                            val hop2key = key2hop.values.toSet().map { it -> it to key2hop.toList().filter { it2 -> it2.second == it }.map { it2 -> it2.first } }.toMap()
                            for (keys in hop2key.values) {
                                if (keys.size > 1) {
                                    val operatorOffToKeys = mutableMapOf<Int, MutableSet<Int>>()
                                    for (key in keys) {
                                        val off = handler.key_rec2off[key]!!
                                        val ss = operatorOffToKeys.getOrPut(off, { mutableSetOf() })
                                        ss.add(key)
                                    }
                                    for ((operatorOff, keys2) in operatorOffToKeys) {
                                        if (keys2.size > 1) {
                                            val childID = HelperMetadata.getNextChildID()
                                            var childOff = -1
                                            val newKey = HelperMetadata.getNextKey()
                                            val oldOperatorOff = ByteArrayWrapperExt.readInt4(pck.data, operatorOff, { "" })
                                            val oldType = ByteArrayWrapperExt.readInt4(pck.data, oldOperatorOff, { "operatorID" })
                                            when (oldType) {
                                                EOperatorIDExt.POPDistributedReceiveMultiCountID -> {
                                                    childOff = ConverterBinaryEncoder.encodePOPDistributedSendSingleCount(
                                                        pck.data,
                                                        mutableMapOf(),
                                                        newKey,
                                                        { _ ->
                                                            ConverterBinaryEncoder.encodePOPDistributedReceiveMultiCount(pck.data, mutableMapOf(), keys2.toList())
                                                        }
                                                    )
                                                    val len = ByteArrayWrapperExt.readInt4(pck.data, oldOperatorOff + 4, { "POPDistributedReceiveMultiCount.size" })
                                                    val newKeys = mutableSetOf<Int>(newKey)
                                                    for (i in 0 until len) {
                                                        val local_key = ByteArrayWrapperExt.readInt4(pck.data, oldOperatorOff + 8 + 4 * i, { "POPDistributedReceiveMultiCount.key[$i]" })
                                                        if (!keys2.contains(local_key)) {
                                                            newKeys.add(local_key)
                                                        }
                                                    }
                                                    ByteArrayWrapperExt.writeInt4(pck.data, oldOperatorOff + 4, newKeys.size, { "POPDistributedReceiveMultiCount.size" })
                                                    var i = 0
                                                    for (k in newKeys) {
                                                        ByteArrayWrapperExt.writeInt4(pck.data, oldOperatorOff + 8 + 4 * i, k, { "POPDistributedReceiveMultiCount.key[$i]" })
                                                        i++
                                                    }
                                                }
                                                EOperatorIDExt.POPDistributedReceiveMultiID -> {
                                                    childOff = ConverterBinaryEncoder.encodePOPDistributedSendSingle(
                                                        pck.data,
                                                        mutableMapOf(),
                                                        newKey,
                                                        { _ ->
                                                            ConverterBinaryEncoder.encodePOPDistributedReceiveMulti(pck.data, mutableMapOf(), keys2.toList())
                                                        }
                                                    )
                                                    val len = ByteArrayWrapperExt.readInt4(pck.data, oldOperatorOff + 4, { "POPDistributedReceiveMulti.size" })
                                                    val newKeys = mutableSetOf<Int>(newKey)
                                                    for (i in 0 until len) {
                                                        val local_key = ByteArrayWrapperExt.readInt4(pck.data, oldOperatorOff + 8 + 4 * i, { "POPDistributedReceiveMulti.key[$i]" })
                                                        if (!keys2.contains(local_key)) {
                                                            newKeys.add(local_key)
                                                        }
                                                    }
                                                    ByteArrayWrapperExt.writeInt4(pck.data, oldOperatorOff + 4, newKeys.size, { "POPDistributedReceiveMulti.size" })
                                                    var i = 0
                                                    for (k in newKeys) {
                                                        ByteArrayWrapperExt.writeInt4(pck.data, oldOperatorOff + 8 + 4 * i, k, { "POPDistributedReceiveMulti.key[$i]" })
                                                        i++
                                                    }
                                                }
                                                EOperatorIDExt.LOPJoinTopologyID -> {
                                                    var oldParentKeys = mutableListOf<Int>()
                                                    var o = oldOperatorOff + 4
                                                    val childCount = ByteArrayWrapperExt.readInt4(pck.data, o, { "LOPJoinTopology.size" })
                                                    o += 4
                                                    val projectedVariablesCount = ByteArrayWrapperExt.readInt4(pck.data, o, { "LOPJoinTopology.size" })
                                                    o += 4
                                                    val oldParentProjectedVariables = mutableListOf<String>()
                                                    for (i in 0 until projectedVariablesCount) {
                                                        oldParentProjectedVariables.add(ConverterString.decodeString(pck.data, ByteArrayWrapperExt.readInt4(pck.data, o, { "projectedVariable.name" })))
                                                        o += 4
                                                    }
                                                    val oldParentChildProjectedVariables = MutableList(childCount) { mutableListOf<String>() }
                                                    for (i in 0 until childCount) {
                                                        oldParentKeys.add(ByteArrayWrapperExt.readInt4(pck.data, o, { "LOPJoinTopology.key[$i]" }))
                                                        o += 4
                                                        val len = ByteArrayWrapperExt.readInt4(pck.data, o, { "LOPJoinTopology.size" })
                                                        o += 4
                                                        for (j in 0 until len) {
                                                            oldParentChildProjectedVariables[i].add(ConverterString.decodeString(pck.data, ByteArrayWrapperExt.readInt4(pck.data, o, { "projectedVariable.name" })))
                                                            o += 4
                                                        }
                                                    }
                                                    val theNewParentKeys = mutableListOf<Int>()
                                                    val theNewParentProjectedVariables = oldParentProjectedVariables.toMutableList()
                                                    val theNewParentChildProjectedVariables = mutableListOf<MutableList<String>>()
                                                    val theNewChildKeys = mutableListOf<Int>()
                                                    val theNewChildProjectedVariablesMin = mutableListOf<String>()
                                                    val theNewChildProjectedVariablesMax = mutableListOf<String>()
                                                    val theNewChildChildProjectedVariables = mutableListOf<MutableList<String>>()
                                                    theNewChildProjectedVariablesMax.addAll(theNewParentProjectedVariables)

                                                    for (i in 0 until oldParentKeys.size) {
                                                        val k = oldParentKeys[i]
                                                        val p = oldParentChildProjectedVariables[i]
                                                        if (keys2.contains(k)) {
                                                            theNewChildKeys.add(k)
                                                            theNewChildChildProjectedVariables.add(p)
                                                            theNewChildProjectedVariablesMin.addAll(p)
                                                        } else {
                                                            theNewParentKeys.add(k)
                                                            theNewParentChildProjectedVariables.add(p)
                                                            theNewChildProjectedVariablesMax.addAll(p)
                                                        }
                                                    }
                                                    val theNewChildProjectedVariables = theNewChildProjectedVariablesMin.intersect(theNewChildProjectedVariablesMax).toMutableList()
                                                    theNewParentKeys.add(newKey)
                                                    theNewParentChildProjectedVariables.add(theNewChildProjectedVariables)
                                                    val mapping = mutableMapOf<String, Int>()

                                                    childOff = ConverterBinaryEncoder.encodePOPDistributedSendSingle(
                                                        pck.data,
                                                        mapping,
                                                        newKey,
                                                        { _ ->
                                                            ConverterBinaryEncoder.encodeLOPJoinTopology(
                                                                pck.data,
                                                                mapping,
                                                                theNewChildKeys,
                                                                theNewChildProjectedVariables,
                                                                theNewChildChildProjectedVariables
                                                            )
                                                        }
                                                    )
                                                    val finalEndOff = oldOperatorOff + 4 + 4 + 4 + 4 * theNewParentProjectedVariables.size + 4 * theNewParentKeys.size + 4 * theNewParentKeys.size + 4 * theNewParentChildProjectedVariables.map { it.size }.sum()
                                                    o = oldOperatorOff
                                                    o += 4
                                                    ByteArrayWrapperExt.writeInt4(pck.data, o, theNewParentKeys.size, { "LOPJoinTopology.size" })
                                                    o += 4
                                                    ByteArrayWrapperExt.writeInt4(pck.data, o, theNewParentProjectedVariables.size, { "LOPJoinTopology.size" })
                                                    o += 4
                                                    for (x in theNewParentProjectedVariables) {
                                                        ByteArrayWrapperExt.writeInt4(pck.data, o, ConverterString.encodeString(x, pck.data, mapping), { "LOPJoinTolology.projectedVariables[]" })
                                                        o += 4
                                                    }
                                                    for (i in 0 until theNewParentKeys.size) {
                                                        ByteArrayWrapperExt.writeInt4(pck.data, o, theNewParentKeys[i], { "LOPJoinTopology.key[$i]" })
                                                        o += 4
                                                        ByteArrayWrapperExt.writeInt4(pck.data, o, theNewParentChildProjectedVariables[i].size, { "LOPJoinTopology.size" })
                                                        o += 4
                                                        for (x in theNewParentChildProjectedVariables[i]) {
                                                            ByteArrayWrapperExt.writeInt4(pck.data, o, ConverterString.encodeString(x, pck.data, mapping), { "LOPJoinTolology.projectedVariables[$i]" })
                                                            o += 4
                                                        }
                                                    }
                                                    if (o != finalEndOff) {
                                                        TODO("offset error somewhere")
                                                    }
                                                }
                                                EOperatorIDExt.POPDistributedReceiveMultiOrderedID -> {
                                                    var orderedBy = mutableListOf<String>()
                                                    var variablesOut = mutableListOf<String>()
                                                    val keysLen = ByteArrayWrapperExt.readInt4(pck.data, oldOperatorOff + 4, { "POPDistributedReceiveMultiOrdered.keys.size" })
                                                    val orderedByLen = ByteArrayWrapperExt.readInt4(pck.data, oldOperatorOff + 8, { "POPDistributedReceiveMultiOrdered.orderedBy.size" })
                                                    val variablesOutLen = ByteArrayWrapperExt.readInt4(pck.data, oldOperatorOff + 12, { "POPDistributedReceiveMultiOrdered.variablesOut.size" })
                                                    val cacheOrderedBy = IntArray(orderedByLen)
                                                    val cachevariablesOut = IntArray(variablesOutLen)
                                                    var o = oldOperatorOff + 16
                                                    for (i in 0 until keysLen) {
                                                        o += 4
                                                    }
                                                    for (i in 0 until orderedByLen) {
                                                        val a = ByteArrayWrapperExt.readInt4(pck.data, o, { "POPDistributedReceiveMultiOrdered.orderedBy[$i]" })
                                                        cacheOrderedBy[i] = a
                                                        orderedBy.add(ConverterString.decodeString(pck.data, a))
                                                        o += 4
                                                    }
                                                    for (i in 0 until variablesOutLen) {
                                                        val a = ByteArrayWrapperExt.readInt4(pck.data, o, { "POPDistributedReceiveMultiOrdered.variablesOut[$i]" })
                                                        cachevariablesOut[i] = a
                                                        variablesOut.add(ConverterString.decodeString(pck.data, a))
                                                        o += 4
                                                    }
                                                    childOff = ConverterBinaryEncoder.encodePOPDistributedSendSingle(
                                                        pck.data,
                                                        mutableMapOf(),
                                                        newKey,
                                                        { _ ->
                                                            ConverterBinaryEncoder.encodePOPDistributedReceiveMultiOrdered(pck.data, mutableMapOf(), keys2.toList(), orderedBy, variablesOut)
                                                        }
                                                    )
                                                    val len = ByteArrayWrapperExt.readInt4(pck.data, oldOperatorOff + 4, { "POPDistributedReceiveMultiOrdered.size" })
                                                    val newKeys = mutableSetOf<Int>(newKey)
                                                    for (i in 0 until len) {
                                                        val local_key = ByteArrayWrapperExt.readInt4(pck.data, oldOperatorOff + 16 + 4 * i, { "POPDistributedReceiveMultiOrdered.key[$i]" })
                                                        if (!keys2.contains(local_key)) {
                                                            newKeys.add(local_key)
                                                        }
                                                    }
                                                    ByteArrayWrapperExt.writeInt4(pck.data, oldOperatorOff + 4, newKeys.size, { "POPDistributedReceiveMultiOrdered.size" })
                                                    var i = 0
                                                    for (k in newKeys) {
                                                        ByteArrayWrapperExt.writeInt4(pck.data, oldOperatorOff + 16 + 4 * i, k, { "POPDistributedReceiveMultiOrdered.key[$i]" })
                                                        i++
                                                    }
                                                    o = oldOperatorOff + 16 + 4 * newKeys.size
                                                    for (ii in 0 until orderedByLen) {
                                                        ByteArrayWrapperExt.writeInt4(pck.data, o, cacheOrderedBy[ii], { "POPDistributedReceiveMultiOrdered.orderedBy[$ii]" })
                                                        o += 4
                                                    }
                                                    for (ii in 0 until variablesOutLen) {
                                                        ByteArrayWrapperExt.writeInt4(pck.data, o, cachevariablesOut[ii], { "POPDistributedReceiveMultiOrdered.variablesOut[$ii]" })
                                                        o += 4
                                                    }
                                                }
                                                else -> {
                                                    println("{\"id\":$id,\"keys\":$keys,\"json\":" + ConverterBinaryToPOPJson.decode(pck.query as Query, pck.data) + "}")
                                                    TODO("unknown type $oldType -> ${EOperatorIDExt.names[oldType]}")
                                                }
                                            }
                                            handler.addChildToBinary(childOff, childID)
                                            handler = HelperMetadata(pck.data, pck.queryID)
                                            changed = true
                                            continue@loop
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

// 3. recalculate target of execution for each operator
        for ((k, v) in handler.id2host) {
            val targets = v.map { nextHops[allHostAdresses.indexOf(it.toInt())] }.toSet()
            val target = if (targets.size == 1) {
                targets.first()
            } else if (targets.contains(rootAddressInt)) {
                rootAddressInt
            } else {
// 3.a if targets differ, calculate the operator here
                ownAdress
            }
            target2id.getOrPut(target, { mutableSetOf() }).add(k)
        }
// 5. send packets further down the network - or store them locally, if this device is the destination
        changed = true
// println()
// println()
// println()
        // println("beginningjson::::: {\"w.queryID\":${pck.queryID},\"data\":" + ConverterBinaryToPOPJson.decode(pck.query as Query, pck.data) + "}")
//        println()
        //      println(handler)
        //    println(handler._key_rec2id)
        //  println()
//        for ((k, v) in target2id) {
        //          for (x in v) {
        //            println("beginning dependencies:: for $x = ${handler.getDependenciesForID2(x).toSet()}")
        //      }
        //   }
        //   println()
        loop6@while (changed) {
            changed = false
            val filter2 = target2id[ownAdress]
            if (filter2 != null) {
                for (id in filter2) {
                    var dependencies = handler.getDependenciesForID2(id)
                    for (dep in dependencies) {
                        pck.destinations[dep] = ownAdress
                    }
                }
            }
            for ((targetHost, filter) in target2id) {
                for (id in filter) {
                    val destinations = handler.getDestinationsForID2(id).toSet()
                    for (d in destinations) {
                        if (pck.destinations[d] == null) {
// the destination is not set ... search for parent query part to send it there as well
                            for ((targetHost2, filter2) in target2id) {
                                for (id2 in filter2) {
                                    val dependencies = handler.getDependenciesForID2(id2).toSet()
                                    for (d2 in dependencies) {
                                        if (d == d2) {
                                            if (targetHost != targetHost2) {
                                                changed = true
                                                target2id.getOrPut(targetHost2, { mutableSetOf() }).add(id)
                                                filter.remove(id)
                                                continue@loop6
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        val oldhandler = handler
        val filter = target2id[ownAdress]
        if (filter != null) {
            var data = ConverterBinaryToBinary.decode(pck.query as Query, pck.data, filter.toIntArray())
            for (id in filter) {
                var dependencies = oldhandler.getDependenciesForID2(id)
                for (dep in dependencies) {
                    pck.destinations[dep] = ownAdress
                }
            }
            for (id in filter) {
                handler = HelperMetadata(data, pck.queryID)
                var dependencies = handler.getDependenciesForID2(id).toSet()
                val w = PendingWork(
                    ownAdress,
                    pck.queryID,
                    data,
                    id,
                    pck.destinations,
                    dependencies,
                    pck.onFinish,
                    pck.expectedResult,
                    pck.verifyAction,
                    pck.query,
                    pck.lastRootOperator,
                )
                myPendingWork.add(w)
            }
        }
        for ((targetHost, filter2) in target2id) {
            if (targetHost != ownAdress) {
                var data = ConverterBinaryToBinary.decode(pck.query as Query, pck.data, filter2.toIntArray())
                val pck2 = Package_Luposdate3000_Operatorgraph(
                    pck.queryID,
                    data,
                    pck.destinations,
                    pck.onFinish,
                    pck.expectedResult,
                    pck.verifyAction,
                    pck.query,
                    pck.lastRootOperator,
                )
                router!!.send(targetHost, pck2)
            }
        }
    }

    private fun localConvertToIteratorBundle(query2: Query, data2: ByteArrayWrapper, dataID: Int, queryID: Int, destinations: Map<Int, Int>, shouldSendImmediately: Boolean): Triple<IteratorBundleRoot, Map<Pair<Int, Int>, Any>, List<OutputStreamToPackage>> {
        val usedResources = mutableMapOf<Pair<Int, Int>, Any>()
        val packagesToSent = mutableListOf<OutputStreamToPackage>()
        val operatorMap2 = ConverterBinaryToIteratorBundle.defaultOperatorMap
        fun assignOP(idx: Int, action: BinaryToOPBaseMap) {
            operatorMap2[idx] = action
        }

        assignOP(EOperatorIDExt.POPDistributedSendSingleID) { query, data, off, operatorMap ->
            val key = ByteArrayWrapperExt.readInt4(data, off + 4, { "POPDistributedSendSingle.key" })
            val child = ConverterBinaryToIteratorBundle.decodeHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 8, { "POPDistributedSendSingle.child" }), operatorMap)
            val out = OutputStreamToPackage(queryID, destinations[key]!!, "simulator-intermediate-result", mapOf("key" to "$key", "query" to "$queryID"), router!!, shouldSendImmediately)
            packagesToSent.add(out)
            EvalDistributedSendWrapper(child, { EvalDistributedSendSingle(out, child, instance.timeout) })
        }
        assignOP(EOperatorIDExt.POPDistributedSendSingleCountID) { query, data, off, operatorMap ->
            val key = ByteArrayWrapperExt.readInt4(data, off + 4, { "POPDistributedSendSingleCount.key" })
            val child = ConverterBinaryToIteratorBundle.decodeHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 8, { "POPDistributedSendSingleCount.child" }), operatorMap)
            val out = OutputStreamToPackage(queryID, destinations[key]!!, "simulator-intermediate-result", mapOf("key" to "$key", "query" to "$queryID"), router!!, shouldSendImmediately)
            packagesToSent.add(out)
            EvalDistributedSendWrapper(child, { EvalDistributedSendSingleCount(out, child) })
        }
        assignOP(EOperatorIDExt.POPDistributedSendMultiID) { query, data, off, operatorMap ->
            val child = ConverterBinaryToIteratorBundle.decodeHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "POPDistributedSendMulti.child" }), operatorMap)
            val count = ByteArrayWrapperExt.readInt4(data, off + 8, { "POPDistributedSendMulti.count" })
            val name = ConverterString.decodeString(data, ByteArrayWrapperExt.readInt4(data, off + 12, { "POPDistributedSendMulti.name" }))
            val keys = IntArray(count) { ByteArrayWrapperExt.readInt4(data, off + 16 + 4 * it, { "POPDistributedSendMulti.key[$it]" }) }
            for (key in keys) {
            }
            val out = Array<IMyOutputStream?>(keys.size) { OutputStreamToPackage(queryID, destinations[keys[it]]!!, "simulator-intermediate-result", mapOf("key" to "${keys[it]}", "query" to "$queryID"), router!!, shouldSendImmediately) }
            packagesToSent.addAll(out.map { it as OutputStreamToPackage })
            EvalDistributedSendWrapper(child, { EvalDistributedSendMulti(out, child, name, instance.timeout) })
        }
        assignOP(EOperatorIDExt.POPDistributedReceiveSingleID) { _, data, off, _ ->
            val key = ByteArrayWrapperExt.readInt4(data, off + 4, { "POPDistributedReceiveSingle.key" })
            val input = MyInputStreamFromByteArray(myPendingWorkData[queryID to key]as ByteArrayWrapper)
            usedResources[queryID to key] = myPendingWorkData.remove(queryID to key)!!
            EvalDistributedReceiveSingle(input, null, instance.timeout)
        }
        assignOP(EOperatorIDExt.POPDistributedReceiveSingleCountID) { _, data, off, _ ->
            val key = ByteArrayWrapperExt.readInt4(data, off + 4, { "POPDistributedReceiveSingleCount.key" })
            val input = MyInputStreamFromByteArray(myPendingWorkData[queryID to key]as ByteArrayWrapper)
            usedResources[queryID to key] = myPendingWorkData.remove(queryID to key)!!
            EvalDistributedReceiveSingleCount(input, null)
        }
        assignOP(EOperatorIDExt.POPDistributedReceiveMultiID) { _, data, off, _ ->
            var keys = mutableListOf<Int>()
            val len = ByteArrayWrapperExt.readInt4(data, off + 4, { "POPDistributedReceiveMulti.size" })
            for (i in 0 until len) {
                keys.add(ByteArrayWrapperExt.readInt4(data, off + 8 + 4 * i, { "POPDistributedReceiveMulti.key[$i]" }))
            }
            val inputs = keys.map { key ->
                val input: IMyInputStream = MyInputStreamFromByteArray(myPendingWorkData[queryID to key]as ByteArrayWrapper)
                usedResources[queryID to key] = myPendingWorkData.remove(queryID to key)!!
                input
            }.toTypedArray()
            val outputs = Array<IMyOutputStream?>(inputs.size) { null }
            EvalDistributedReceiveMulti(inputs, outputs, instance.timeout)
        }
        assignOP(EOperatorIDExt.LOPJoinTopologyID) { query, data, off, _ ->
            var keys = mutableListOf<Int>()
            var o = off + 4
            val childCount = ByteArrayWrapperExt.readInt4(data, o, { "LOPJoinTopology.size" })
            o += 4
            val projectedVariablesCount = ByteArrayWrapperExt.readInt4(data, o, { "LOPJoinTopology.size" })
            o += 4
            val projectedVariables = mutableListOf<String>()
            for (i in 0 until projectedVariablesCount) {
                projectedVariables.add(ConverterString.decodeString(data, ByteArrayWrapperExt.readInt4(data, o, { "projectedVariable.name" })))
                o += 4
            }
            val childProjectedVariables = MutableList(childCount) { mutableListOf<String>() }
            for (i in 0 until childCount) {
                keys.add(ByteArrayWrapperExt.readInt4(data, o, { "LOPJoinTopology.key[$i]" }))
                o += 4
                val len = ByteArrayWrapperExt.readInt4(data, o, { "LOPJoinTopology.size" })
                o += 4
                for (j in 0 until len) {
                    childProjectedVariables[i].add(ConverterString.decodeString(data, ByteArrayWrapperExt.readInt4(data, o, { "projectedVariable.name" })))
                    o += 4
                }
            }
            val inputs = keys.map { key ->
                val input: IMyInputStream = MyInputStreamFromByteArray(myPendingWorkData[queryID to key]as ByteArrayWrapper)
                usedResources[queryID to key] = myPendingWorkData.remove(queryID to key)!!
                input
            }.toTypedArray()

// println()
// println()
// println()
            val inputIterators = inputs.map { EvalDistributedReceiveSingle(it, null, instance.timeout) }.toMutableList()
            while (inputIterators.size> 1) {
                val child0 = inputIterators.removeFirst()
                val child1 = inputIterators.removeFirst()
                val projected0 = childProjectedVariables.removeFirst()
                val projected1 = childProjectedVariables.removeFirst()
                val maxSet = (projected0 + projected1).toSet()
                val minSet = (projectedVariables).toMutableSet()
                for (x in childProjectedVariables) {
                    minSet.addAll(x)
                }
                val finalSet = mutableSetOf<String>()
                for (x in maxSet) {
                    if (x in minSet) {
                        finalSet.add(x)
                    }
                }
                var isCartesian = projected0.intersect(projected1).size == 0
                val x = if (isCartesian) {
// println("joining cart :: $projected0 + $projected1 -> $finalSet")
                    EvalJoinCartesianProduct(query, child0, child1, false)
                } else {
// println("joining merge :: $projected0 + $projected1 -> $finalSet")
                    EvalJoinMergeFromUnsortedData(query, child0, child1, finalSet.toList())
// EvalJoinHashMap(query, child0, child1, false, finalSet.toList(), query.getInstance().timeout)
                }

                inputIterators.add(x)
                childProjectedVariables.add(finalSet.toMutableList())
            }
// println("Application_Luposdate3000 ... EOperatorIDExt.LOPJoinTopologyID projected at the end ... ${childProjectedVariables} but should have been ${projectedVariables} ... ${inputIterators.size} ${inputIterators[0].columns.keys}")
// println()
// println()
// println()

            inputIterators[0]
        }
        assignOP(EOperatorIDExt.POPDistributedReceiveMultiCountID) { _, data, off, _ ->
            var keys = mutableListOf<Int>()
            val len = ByteArrayWrapperExt.readInt4(data, off + 4, { "POPDistributedReceiveMultiCount.size" })
            for (i in 0 until len) {
                keys.add(ByteArrayWrapperExt.readInt4(data, off + 8 + 4 * i, { "POPDistributedReceiveMultiCount.key[$i]" }))
            }
            val inputs = keys.map { key ->
                val input: IMyInputStream = MyInputStreamFromByteArray(myPendingWorkData[queryID to key]as ByteArrayWrapper)
                usedResources[queryID to key] = myPendingWorkData.remove(queryID to key)!!
                input
            }.toTypedArray()
            val outputs = Array<IMyOutputStream?>(inputs.size) { null }
            EvalDistributedReceiveMultiCount(inputs, outputs)
        }
        assignOP(EOperatorIDExt.POPDistributedReceiveMultiOrderedID) { _, data, off, _ ->
            var keys = mutableListOf<Int>()
            var orderedBy = mutableListOf<String>()
            var variablesOut = mutableListOf<String>()
            val keysLen = ByteArrayWrapperExt.readInt4(data, off + 4, { "POPDistributedReceiveMultiOrdered.keys.size" })
            val orderedByLen = ByteArrayWrapperExt.readInt4(data, off + 8, { "POPDistributedReceiveMultiOrdered.orderedBy.size" })
            val variablesOutLen = ByteArrayWrapperExt.readInt4(data, off + 12, { "POPDistributedReceiveMultiOrdered.variablesOut.size" })
            var o = off + 16
            for (i in 0 until keysLen) {
                keys.add(ByteArrayWrapperExt.readInt4(data, o, { "POPDistributedReceiveMultiOrdered.keys[$i]" }))
                o += 4
            }
            for (i in 0 until orderedByLen) {
                orderedBy.add(ConverterString.decodeString(data, ByteArrayWrapperExt.readInt4(data, o, { "POPDistributedReceiveMultiOrdered.orderedBy[$i]" })))
                o += 4
            }
            for (i in 0 until variablesOutLen) {
                variablesOut.add(ConverterString.decodeString(data, ByteArrayWrapperExt.readInt4(data, o, { "POPDistributedReceiveMultiOrdered.variablesOut[$i]" })))
                o += 4
            }
            val inputs = keys.map { key ->
                //  println("searching for ${queryID to key}, but there is ${myPendingWorkData.keys}")
                val input: IMyInputStream = MyInputStreamFromByteArray(myPendingWorkData[queryID to key]as ByteArrayWrapper)
                usedResources[queryID to key] = myPendingWorkData.remove(queryID to key)!!
                input
            }.toTypedArray()
            val outputs = Array<IMyOutputStream?>(inputs.size) { null }
            EvalDistributedReceiveMultiOrdered(inputs, outputs, orderedBy, variablesOut, instance.timeout)
        }
        return Triple(ConverterBinaryToIteratorBundle.decode(query2, data2, dataID, operatorMap2, true), usedResources, packagesToSent)
    }

    private fun doWork() {
        if (!doWorkFlag) {
            doWorkFlag = true // only one exeution at a time
            try {
                var changed = true
                while (changed) {
                    changed = false
                    for (w in myPendingWork) {
                        var flag = w.dataID >= -1 || myPendingWorkData.keys.contains(w.queryID to (w.dataID))
// println("initial flag : ${flag} ${w.dataID >= -1} ${myPendingWorkData.keys.contains(w.queryID to (w.dataID))} ${w.dataID} ${w.lastRootOperator}")
                        for (k in w.dependencies) {
                            flag = flag && myPendingWorkData.keys.contains(w.queryID to k)
                        }
// println("flag after deps ${flag} ${w.dependencies} ${myPendingWorkData.keys}")
                        if (flag) {
                            // println("checked the dependencies ... ${w.dependencies}")
                            myPendingWork.remove(w)
                            logger.costumData(w)
                            changed = true
                            val query: Query
                            if (ownAdress != rootAddressInt && !enableSharedMemoryDictionaryCheat) {
                                query = Query(instance)
                                query.setDictionary(DictionaryCacheLayer(instance, DictionaryNotImplemented(instance), true))
                            } else {
                                var q = queryCache[w.queryID]
                                if (q == null) {
                                    q = Query(instance)
                                    q.dictionary = (w.query as Query).dictionary
                                    q.transactionID = (w.query as Query).transactionID
                                    queryCache[w.queryID] = q
                                }
                                query = q
                            }
                            if (w.dataID == -1) {
                                queryCache.remove(w.queryID)
                            }
// println()
//                            println("execute json::::: {\"w.queryID\":${w.queryID},\"w.dataID\":${w.dataID},\"data\":" + ConverterBinaryToPOPJson.decode(query as Query, ConverterBinaryToBinary.decode(w.query as Query, w.data, intArrayOf(w.dataID))) + "}")
//                            println("execute json::::: {\"w.queryID\":${w.queryID},\"w.dataID\":${w.dataID},\"data\":" + ConverterBinaryToPOPJson.decode(query as Query, w.data) + "}")
                            if (w.dataID <0) {
                                val iteratorBundle = localConvertToIteratorBundle(query, w.data, w.dataID, w.queryID, w.destinations, true).first
                                if (w.expectedResult != null) {
                                    val buf = MyPrintWriter(false)
                                    var result = (LuposdateEndpoint.evaluateIteratorBundleToResultE(instance, iteratorBundle, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
                                    if (w.dataID != w.lastRootOperator) {
                                        if (w.dataID == -1) {
                                            myPendingWorkData[w.queryID to (w.dataID - 1)] = mutableListOf(result)
                                        } else {
                                            val x = (myPendingWorkData[w.queryID to w.dataID]as MutableList<MemoryTable>)
                                            x.add(result)
                                            myPendingWorkData[w.queryID to (w.dataID - 1)] = x
                                            myPendingWorkData.remove(w.queryID to w.dataID)
                                        }
                                    } else {
                                        if (w.dataID != -1) {
                                            val x = (myPendingWorkData[w.queryID to w.dataID]as MutableList<MemoryTable>)
                                            x.add(result)
                                            result = x.first()
                                            myPendingWorkData.remove(w.queryID to w.dataID)
                                        }
                                    }
                                    if (w.dataID == w.lastRootOperator) {
                                        val buf_err = MyPrintWriter()
                                        if (!w.expectedResult.equalsVerbose(result, true, true, false, buf_err)) { // TODO check the ordering of columns as well ...
                                            throw Exception(buf_err.toString())
                                        }
                                        w.verifyAction()
                                        if (w.onFinish != null) {
                                            receive(w.onFinish)
                                        } else {
                                            router!!.send(w.destinations[-1]!!, Package_QueryResponse("success".encodeToByteArray(), w.queryID))
                                        }
                                    }
                                } else {
                                    var buf = MyPrintWriter(true)
                                    if (w.dataID != w.lastRootOperator) {
                                        if (w.dataID == -1) {
                                            myPendingWorkData[w.queryID to (w.dataID - 1)] = buf
                                        } else {
                                            buf = myPendingWorkData[w.queryID to w.dataID] as MyPrintWriter
                                            myPendingWorkData[w.queryID to (w.dataID - 1)] = buf
                                            myPendingWorkData.remove(w.queryID to w.dataID)
                                        }
                                    } else {
                                        if (w.dataID != -1) {
                                            buf = myPendingWorkData[w.queryID to w.dataID] as MyPrintWriter
                                            myPendingWorkData.remove(w.queryID to w.dataID)
                                        }
                                    }

                                    val evaluatorInstance = ResultFormatManager[EQueryResultToStreamExt.names[EQueryResultToStreamExt.DEFAULT_STREAM]]!!
                                    evaluatorInstance(iteratorBundle, buf)

                                    if (w.dataID == w.lastRootOperator) {
                                        if (w.onFinish != null) {
                                            receive(w.onFinish)
                                        } else {
                                            router!!.send(w.destinations[-1]!!, Package_QueryResponse(buf.toString().encodeToByteArray(), w.queryID))
                                        }
                                    }
                                }
                            } else {
                                val (iteratorBundle, usedInputs, packagesToSent) = localConvertToIteratorBundle(query, w.data, w.dataID, w.queryID, w.destinations, false)
                                for (nodes in iteratorBundle.nodes) {
                                    val iter = nodes.second.rows
                                    do {
                                        val res = iter.next()
                                    } while (res != -1)
                                }
                                val inputSizes = usedInputs.values.map { ByteArrayWrapperExt.getSize((it as ByteArrayWrapper)) }
                                val outputSizes = packagesToSent.map { ByteArrayWrapperExt.getSize(it.buffer) }

                                var inputBytes = inputSizes.sum()
                                var outputBytes = outputSizes.sum()
                                var hadSentOperatorsOrData = false
                                if (instance.relocateOperatorsIfTooMuchDataIsSent) {
                                    if (inputBytes < outputBytes && usedInputs.size > 0) {
                                        var flag = false
                                        var targets = packagesToSent.map { it.target }
                                        var target = targets.first()
                                        for (t in targets) {
                                            flag = flag || t != ownAdress
                                            if (t != target) {
                                                flag = false
                                                break
                                            }
                                        }
                                        if (flag) {
                                            // TODO("this could be redirected_redirected_redirected_redirected")
                                            val compactedOperatorGraph = ConverterBinaryToBinary.decode(w.query as Query, w.data, intArrayOf(w.dataID))
                                            for ((k, v) in usedInputs) {
                                                router!!.send(target, Package_Luposdate3000_Abstract(w.queryID, "simulator-intermediate-result", mapOf("key" to "${k.second}", "query" to "${k.first}"), v as ByteArrayWrapper))
                                            }
                                            router!!.send(target, Package_Luposdate3000_Operatorgraph(w.queryID, compactedOperatorGraph, w.destinations.toMutableMap(), w.onFinish, w.expectedResult, w.verifyAction, w.query, w.lastRootOperator))
                                            hadSentOperatorsOrData = true
                                            println()
                                            println()
                                            println("all input sizes : $inputSizes ($inputBytes) -> output $outputSizes ($outputBytes)")
                                            println("my address: $ownAdress -> targets: ${packagesToSent.map{it.target}}")
                                            println("forwarded everything to another node")
                                            println()
                                            println()
                                        }
                                    }
                                }
                                if (!hadSentOperatorsOrData) {
                                    for (p in packagesToSent) {
                                        p.sendTheData()
                                    }
                                }
                            }
                            break
                        }
                    }
                }
            } catch (e: Throwable) {
                doWorkFlag = false
                e.myPrintStackTraceAndThrowAgain(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_simulator_db/src/commonMain/kotlin/lupos/simulator_db/luposdate3000/Application_Luposdate3000.kt:1097"/*SOURCE_FILE_END*/)
            }
            doWorkFlag = false
        }
    }

    override fun receive(pck: IPayload): IPayload? {
        try {
            if (!hadInitDatabaseHopsWithinLuposdate3000) {
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
            e.myPrintStackTraceAndThrowAgain(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_simulator_db/src/commonMain/kotlin/lupos/simulator_db/luposdate3000/Application_Luposdate3000.kt:1123"/*SOURCE_FILE_END*/)
        }
        doWork()
        return null
    }
}
