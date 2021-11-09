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
import lupos.operator.factory.BinaryMetadataHandler
import lupos.operator.factory.BinaryToOPBase
import lupos.operator.factory.BinaryToOPBaseMap
import lupos.operator.factory.ConverterBinaryToBinary
import lupos.operator.factory.ConverterBinaryToIteratorBundle
import lupos.operator.factory.ConverterBinaryToPOPJson
import lupos.operator.factory.ConverterString
import lupos.operator.physical.partition.EvalDistributedReceiveMulti
import lupos.operator.physical.partition.EvalDistributedReceiveMultiCount
import lupos.operator.physical.partition.EvalDistributedReceiveMultiOrdered
import lupos.operator.physical.partition.EvalDistributedReceiveSingle
import lupos.operator.physical.partition.EvalDistributedReceiveSingleCount
import lupos.operator.physical.partition.EvalDistributedSendMulti
import lupos.operator.physical.partition.EvalDistributedSendSingle
import lupos.operator.physical.partition.EvalDistributedSendWrapper
import lupos.optimizer.physical.PhysicalOptimizer
import lupos.parser.JsonParserObject
import lupos.result_format.EQueryResultToStreamExt
import lupos.result_format.ResultFormatManager
import lupos.shared.EOperatorIDExt
import lupos.shared.EPartitionModeExt
import lupos.shared.EPredefinedPartitionSchemesExt
import lupos.shared.EQueryDistributionModeExt
import lupos.shared.IMyInputStream
import lupos.shared.IMyOutputStream
import lupos.shared.ITripleStoreIndexDescription
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
import lupos.shared.inline.File
import lupos.shared.inline.MyPrintWriter
import lupos.shared.inline.dynamicArray.ByteArrayWrapperExt
import lupos.shared.operator.iterator.IteratorBundleRoot
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

        val binaryPair = BinaryToOPBase.convertToByteArrayAndMeta(op, instance.LUPOS_PARTITION_MODE == EPartitionModeExt.Process, true)
        val data = binaryPair.first
        val handler = binaryPair.second
        val destinations = mutableMapOf<Int, Int>(-1 to ownAdress)
        receive(Package_Luposdate3000_Operatorgraph(pck.queryID, data, handler, destinations, onFinish, expectedResult, verifyAction, q))
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
            println("Application_Luposdate3000.receive simulator-intermediate-result $ownAdress ${pck.params["key"]}")
            SanityCheck.check(
                { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_simulator_db/src/commonMain/kotlin/lupos/simulator_db/luposdate3000/Application_Luposdate3000.kt:252"/*SOURCE_FILE_END*/ },
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
        // println("$ownAdress Application_Luposdate3000.receivePackage_Luposdate3000_Operatorgraph")
        val mapTopDown = mutableMapOf<Int, MutableSet<Int>>()
        val mapBottomUpThis = mutableMapOf<Int, MutableSet<Int>>()
        val operatorGraphPartsToHostMapTmp = mutableSetOf<Int>(rootAddressInt, ownAdress)
        operatorGraphPartsToHostMapTmp.addAll(pck.handler.idToHost.values.map { it.map { it.toInt() } }.flatten())
        val allHostAdresses = operatorGraphPartsToHostMapTmp.map { it.toInt() }.toSet().toIntArray()
        val nextHops = myGetNextHop(allHostAdresses, featureID_any)
        for (i in 0 until nextHops.size) {
            if (nextHops[i] == -1) { // if the package-router does not know it - use the first database instance instead.
                nextHops[i] = rootAddressInt
            }
        }
        var myIdsOnTargetMap = mutableMapOf<Int, MutableSet<Int>>()
        for ((k, v) in pck.handler.idToHost) {
            val targets = v.map { nextHops[allHostAdresses.indexOf(it.toInt())] }.toSet()
            val target = if (targets.size == 1) {
                targets.first()
            } else {
                ownAdress
            }
            if (target == ownAdress) {
                val dep = pck.handler.dependenciesForID[k]
                if (dep != null) {
                    for (d in dep.values) {
                        pck.destinations[d] = ownAdress
                    }
                }
            }
            val mm = myIdsOnTargetMap[target]
            if (mm != null) {
                mm.add(k)
            } else {
                myIdsOnTargetMap[target] = mutableSetOf(k)
            }
        }
        for ((targetHost, filter) in myIdsOnTargetMap) {
            if (targetHost == ownAdress) {
                for (id in filter) {
                    var dependencies2 = pck.handler.dependenciesForID[id]
                    val dependencies = if (dependencies2 == null) {
                        setOf<Int>()
                    } else {
                        dependencies2.values.toSet()
                    }
                    val w = PendingWork(
                        pck.queryID,
                        pck.data,
                        id,
                        pck.destinations,
                        dependencies,
                        pck.onFinish,
                        pck.expectedResult,
                        pck.verifyAction,
                        pck.query,
                    )
                    myPendingWork.add(w)
                }
            } else {
                val data = ConverterBinaryToBinary.decode(pck.query as Query, pck.data, filter.toIntArray())
                val idToHost = mutableMapOf<Int, MutableSet<String>>()
                for ((k, v) in pck.handler.idToHost) {
                    if (filter.contains(k)) {
                        idToHost[k] = v
                    }
                }
                val pck2 = Package_Luposdate3000_Operatorgraph(
                    pck.queryID,
                    data,
                    BinaryMetadataHandler(mutableMapOf(), idToHost, pck.handler.dependenciesForID, mutableMapOf(), mutableMapOf()),
                    pck.destinations,
                    pck.onFinish,
                    pck.expectedResult,
                    pck.verifyAction,
                    pck.query,
                )
                router!!.send(targetHost, pck2)
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

    private fun localConvertToIteratorBundle(query: Query, data: ByteArrayWrapper, dataID: Int, queryID: Int, destinations: Map<Int, Int>): IteratorBundleRoot {
        val operatorMap = ConverterBinaryToIteratorBundle.defaultOperatorMap
        fun assignOP(idx: Int, action: BinaryToOPBaseMap) {
            operatorMap[idx] = action
        }
        assignOP(EOperatorIDExt.POPDistributedSendSingleID) { query, data, off, operatorMap ->
            val key = ByteArrayWrapperExt.readInt4(data, off + 4, { "POPDistributedSendSingle.key" })
            val child = ConverterBinaryToIteratorBundle.decodeHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 8, { "POPDistributedSendSingle.child" }), operatorMap)
            val out = OutputStreamToPackage(queryID, destinations[key]!!, "simulator-intermediate-result", mapOf("key" to "$key"), router!!)
            EvalDistributedSendWrapper(child, { EvalDistributedSendSingle(out, child) })
        }
        assignOP(EOperatorIDExt.POPDistributedSendMultiID) { query, data, off, operatorMap ->
            val child = ConverterBinaryToIteratorBundle.decodeHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "POPDistributedSendMulti.child" }), operatorMap)
            val count = ByteArrayWrapperExt.readInt4(data, off + 8, { "POPDistributedSendMulti.count" })
            val name = ConverterString.decodeString(data, ByteArrayWrapperExt.readInt4(data, off + 12, { "POPDistributedSendMulti.name" }))
            val keys = IntArray(count) { ByteArrayWrapperExt.readInt4(data, off + 16 + 4 * it, { "POPDistributedSendMulti.key[$it]" }) }
            val out = Array<IMyOutputStream?>(keys.size) { OutputStreamToPackage(queryID, destinations[keys[it]]!!, "simulator-intermediate-result", mapOf("key" to "${keys[it]}"), router!!) }
            EvalDistributedSendWrapper(child, { EvalDistributedSendMulti(out, child, name) })
        }
        assignOP(EOperatorIDExt.POPDistributedReceiveSingleID) { query, data, off, operatorMap ->
            val key = ByteArrayWrapperExt.readInt4(data, off + 4, { "POPDistributedReceiveSingle.key" })
            val input = MyInputStreamFromByteArray(myPendingWorkData[key]!!)
            myPendingWorkData.remove(key)
            EvalDistributedReceiveSingle(input, null)
        }
        assignOP(EOperatorIDExt.POPDistributedReceiveSingleCountID) { query, data, off, operatorMap ->
            val key = ByteArrayWrapperExt.readInt4(data, off + 4, { "POPDistributedReceiveSingleCount.key" })
            val input = MyInputStreamFromByteArray(myPendingWorkData[key]!!)
            myPendingWorkData.remove(key)
            EvalDistributedReceiveSingleCount(input, null)
        }
        assignOP(EOperatorIDExt.POPDistributedReceiveMultiID) { query, data, off, operatorMap ->
            var keys = mutableListOf<Int>()
            val len = ByteArrayWrapperExt.readInt4(data, off + 4, { "POPDistributedReceiveMulti.size" })
            for (i in 0 until len) {
                keys.add(ByteArrayWrapperExt.readInt4(data, off + 8 + 4 * i, { "POPDistributedReceiveMulti.key[$i]" }))
            }
            val inputs = keys.map { key ->
                val input: IMyInputStream = MyInputStreamFromByteArray(myPendingWorkData[key]!!)
                myPendingWorkData.remove(key)
                input
            }.toTypedArray()
            val outputs = Array<IMyOutputStream?>(inputs.size) { null }
            EvalDistributedReceiveMulti(inputs, outputs)
        }
        assignOP(EOperatorIDExt.POPDistributedReceiveMultiCountID) { query, data, off, operatorMap ->
            var keys = mutableListOf<Int>()
            val len = ByteArrayWrapperExt.readInt4(data, off + 4, { "POPDistributedReceiveMultiCount.size" })
            for (i in 0 until len) {
                keys.add(ByteArrayWrapperExt.readInt4(data, off + 8 + 4 * i, { "POPDistributedReceiveMultiCount.key[$i]" }))
            }
            val inputs = keys.map { key ->
                val input: IMyInputStream = MyInputStreamFromByteArray(myPendingWorkData[key]!!)
                myPendingWorkData.remove(key)
                input
            }.toTypedArray()
            val outputs = Array<IMyOutputStream?>(inputs.size) { null }
            EvalDistributedReceiveMultiCount(inputs, outputs)
        }
        assignOP(EOperatorIDExt.POPDistributedReceiveMultiOrderedID) { query, data, off, operatorMap ->
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
                val input: IMyInputStream = MyInputStreamFromByteArray(myPendingWorkData[key]!!)
                myPendingWorkData.remove(key)
                input
            }.toTypedArray()
            val outputs = Array<IMyOutputStream?>(inputs.size) { null }
            EvalDistributedReceiveMultiOrdered(inputs, outputs, orderedBy, variablesOut)
        }
        return ConverterBinaryToIteratorBundle.decode(query, data, dataID, operatorMap)
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
                            val query: Query
                            if (ownAdress != rootAddressInt || w.dataID != -1) {
                                query = Query(instance)
                                query.setDictionary(DictionaryCacheLayer(instance, DictionaryNotImplemented(instance), true))
                            } else {
                                query = w.query as Query
                            }
                            println("JSON_OUT_EVAL ${w.dataID} ${ConverterBinaryToPOPJson.decode(query,w.data)}")
                            val iteratorBundle = localConvertToIteratorBundle(query, w.data, w.dataID, w.queryID, w.destinations)
                            // println(iteratorBundle)
                            if (w.dataID == -1) {
                                // println("$ownAdress root executing .. $iteratorBundle")
                                if (w.expectedResult != null) {
                                    val buf = MyPrintWriter(false)
                                    val result = (LuposdateEndpoint.evaluateIteratorBundleToResultE(instance, iteratorBundle, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
                                    val buf_err = MyPrintWriter()
                                    if (!result.equalsVerbose(w.expectedResult, true, true, buf_err)) {
                                        throw Exception(buf_err.toString())
                                    }
                                    w.verifyAction()
                                    if (w.onFinish != null) {
                                        receive(w.onFinish)
                                    } else {
                                        router!!.send(w.destinations[-1]!!, Package_QueryResponse("success".encodeToByteArray(), w.queryID))
                                    }
                                } else {
                                    val buf = MyPrintWriter(true)
                                    val evaluatorInstance = ResultFormatManager[EQueryResultToStreamExt.names[EQueryResultToStreamExt.DEFAULT_STREAM]]!!
                                    evaluatorInstance(iteratorBundle, buf)
                                    if (w.onFinish != null) {
                                        receive(w.onFinish)
                                    } else {
                                        router!!.send(w.destinations[-1]!!, Package_QueryResponse(buf.toString().encodeToByteArray(), w.queryID))
                                    }
                                }
                                // println("done executing")
                            } else {
                                for ((columnNames, nodes) in iteratorBundle.nodes) {
                                    val iter = nodes.rows
                                    do {
                                        val res = iter.next()
                                    } while (res != -1)
                                }
                            }
                            break
                        } else {
                            println("$ownAdress cannot work at ${w.dataID}, because ${w.dependencies.toSet() - myPendingWorkData.keys} is missing")
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
