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
package lupos.visualize.distributed.database
import lupos.shared.DictionaryValueHelper
import lupos.shared.SanityCheck
import lupos.shared.XMLElement
import lupos.shared.inline.File
import lupos.shared.inline.dynamicArray.ByteArrayWrapperExt
import lupos.simulator_db.ILogger
import lupos.simulator_db.IPayload
import lupos.simulator_db.Package_Query
import lupos.simulator_db.Package_QueryResponse
import lupos.simulator_db.luposdate3000.MySimulatorAbstractPackage
import lupos.simulator_db.luposdate3000.MySimulatorOperatorGraphPackage
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin
public class VisualisationNetwork(private val outputDirectory: String, private val getClock: () -> Long) : ILogger {
    private val skipQueriesWithLessPartsThan = 1
    private val devices = mutableSetOf<VisualisationDevice>() // alle beteiligten Computer
    private var devicesMaxID = 0
    private val connections = mutableSetOf<VisualisationConnection>() // alle möglichen Verbindungen
    private val connectionsInRouting = mutableSetOf<VisualisationConnection>() // alle genutzten Verbindungen
    private val connectionsInRoutingDB = mutableSetOf<VisualisationConnection>() // alle genutzten Verbindungen
    private var connectionTable = IntArray(0) // src*devicesMaxID+dest -> nexthop
    private var connectionTableDB = IntArray(0) // src*devicesMaxID+dest -> nexthop
    private val messages = mutableListOf<VisualisationMessage>() // alle gesendeten Nachrichten
    private val graph_index_to_key = mutableMapOf<String, MutableSet<String>>() // DB welche keys gehören zusammen zu einem Graphen
    private val device_to_key = mutableMapOf<Int, MutableSet<String>>() // //DB welcher key ist wo gespeichert
    private val allMessageTypes = mutableSetOf<String>()
    private val workForQueryAtNode = mutableMapOf<Int/*query*/, MutableMap<Int/*node*/, MutableSet<Pair<String, XMLElement>>/*work-list*/>>()
    private val fullOperatorGraph = mutableMapOf<Int/*queryID*/, MutableMap<Int, XMLElement>>()
    private companion object {
        const val layerConnection = 0
        const val layerConnectionInRouting = 1
        const val layerDeviceNone = 2
        const val layerDeviceNoneName = 3
        const val layerDeviceSensor = 4
        const val layerDeviceSensorName = 5
        const val layerDeviceDB = 6
        const val layerDeviceDBName = 7
        const val layerMessage = 8
        const val layerStorageKey = 9
        const val layerWork = 11
        var deviceRadius = 20.0
        const val minDistToOtherPath = 4.0
    }
    private fun messageToRoutingPath(src: Int, dest: Int): List<Int> { // TODO get this directly from simulator
        val tmp = mutableListOf<Int>()
        var s = src
        while (s != dest) {
            tmp.add(s)
            val idx = s * devicesMaxID + dest
            if (idx >= connectionTable.size) {
                break
            }
            s = connectionTable[idx]
        }
        tmp.add(dest)
        return tmp
    }
    private fun messageToRoutingPathDB(src: Int, dest: Int): List<Int> { // TODO get this directly from simulator
        val tmp = mutableListOf<Int>()
        var s = src
        while (s != dest) {
            tmp.add(s)
            val idx = s * devicesMaxID + dest
            s = connectionTableDB[idx]
        }
        tmp.add(dest)
        return tmp
    }

    private fun toBaseImageStyle(): ImageHelper {
        val imageHelperBase = ImageHelper()
        imageHelperBase.createClass(
            "device",
            mapOf(
                "fill" to "#FFFFFF",
                "stroke" to "#000000",
                "stroke-width" to "2",
            )
        )
        imageHelperBase.createClass(
            "device-database-store",
            mapOf(
                "stroke" to "#550000",
                "stroke-width" to "4",
            )
        )
        imageHelperBase.createClass(
            "device-database-query",
            mapOf(
                "stroke" to "#990000",
                "stroke-width" to "4",
            )
        )
        imageHelperBase.createClass(
            "device-database-store-query",
            mapOf(
                "stroke" to "#FF0000",
                "stroke-width" to "4",
            )
        )
        imageHelperBase.createClass(
            "device-sensor",
            mapOf(
                "stroke" to "#00FF00",
                "stroke-dasharray" to "3",
            )
        )
        imageHelperBase.createClass(
            "connection",
            mapOf(
                "stroke-width" to "1",
                "stroke" to "#000000",
            )
        )
        imageHelperBase.createClass(
            "connectionInRouting",
            mapOf(
                "stroke-width" to "2",
                "stroke" to "#000099",
            )
        )
        imageHelperBase.createClass(
            "message",
            mapOf(
                "stroke-width" to "4",
                "marker-end" to "url(#arrowhead)",
                "fill-opacity" to "0",
                "stroke-dasharray" to "3",
            )
        )
        imageHelperBase.createClass(
            "message-query",
            mapOf(
                "stroke" to "#FF0000",
            )
        )
        imageHelperBase.createClass(
            "message-create",
            mapOf(
                "stroke" to "#00FF00",
            )
        )
        imageHelperBase.createClass(
            "message-modify",
            mapOf(
                "stroke" to "#FFFF00",
            )
        )
        imageHelperBase.createClass(
            "message-response",
            mapOf(
                "stroke" to "#FF00FF",
            )
        )
        imageHelperBase.createClass(
            "message-operatorgraph",
            mapOf(
                "stroke" to "#00FFFF",
            )
        )
        imageHelperBase.createClass(
            "message-intermediate",
            mapOf(
                "stroke" to "#0000FF",
            )
        )
        if (devices.size> 0) {
            var minX = devices.first().x
            var minY = devices.first().y
            var maxX = devices.first().x
            var maxY = devices.first().y
            for (device in devices) {
                if (minX> device.x) {
                    minX = device.x
                }
                if (maxX <device.x) {
                    maxX = device.x
                }
                if (minY> device.y) {
                    minY = device.y
                }
                if (maxY <device.y) {
                    maxY = device.y
                }
            }
            for (device in devices) {
                val newX = imageHelperBase.minX + ((device.x - minX) / (maxX - minX)) * (imageHelperBase.maxX - imageHelperBase.minX)
                val newY = imageHelperBase.minY + ((device.y - minY) / (maxY - minY)) * (imageHelperBase.maxY - imageHelperBase.minY)
                device.xnew = newX
                device.ynew = newY
            }
        }
        return imageHelperBase
    }
    private fun toBaseImage(): ImageHelper {
        val imageHelperBase = toBaseImageStyle()
        for (device in devices) {
            val classes = mutableListOf("device")
            var layer = layerDeviceNone
            var layerName = layerDeviceNoneName
            if (device.hasSensor) {
                classes.add("device-sensor")
                layer = layerDeviceSensor
                layerName = layerDeviceSensorName
            }
            if (device.hasDatabaseStore && device.hasDatabaseQuery) {
                classes.add("device-database-store-query")
                layer = layerDeviceDB
                layerName = layerDeviceDBName
            } else if (device.hasDatabaseStore) {
                classes.add("device-database-store")
                layer = layerDeviceDB
                layerName = layerDeviceDBName
            } else if (device.hasDatabaseQuery) {
                classes.add("device-database-query")
                layer = layerDeviceDB
                layerName = layerDeviceDBName
            }
            imageHelperBase.addCircle(layer, device.xnew, device.ynew, deviceRadius, classes)
            imageHelperBase.addText(layerName, device.xnew, device.ynew, device.id.toString(), mutableListOf())
        }
        for (connection in connections) {
            val a = getDeviceById(connection.source)
            val b = getDeviceById(connection.destination)
            imageHelperBase.addLine(layerConnection, a.xnew, a.ynew, b.xnew, b.ynew, listOf("connection"))
        }
        for (connection in connectionsInRouting) {
            val a = getDeviceById(connection.source)
            val b = getDeviceById(connection.destination)
            imageHelperBase.addLine(layerConnectionInRouting, a.xnew, a.ynew, b.xnew, b.ynew, listOf("connectionInRouting"))
        }
        return imageHelperBase
    }

    private fun saveMessagesForQuery(imageHelperBase: ImageHelper, queryNumber: Int): Boolean {
        var first = 0
        var last = messages.size
        if (queryNumber != -1) {
            var currQuery = 0
            while (first <messages.size && currQuery <queryNumber) {
                val message = messages[first]
                if (message.shortText.startsWith("query")) {
                    currQuery++
                }
                first++
            }
            if (currQuery != queryNumber) {
                return false
            }
            last = first
            while (last <messages.size) {
                val message = messages[last]
                if (message.shortText.startsWith("response")) {
                    break
                }
                last++
            }
        }
        val w = workForQueryAtNode[queryNumber]
        if (w != null && skipQueriesWithLessPartsThan <w.values.map { it.size }.sum()) {
            val imgOverview = imageHelperBase.deepCopy()
            for (i in first until last) {
                val cc = messageToRoutingPath(messages[i].source, messages[i].destination)
                val points = mutableListOf<Pair<Double, Double>>()
                val classes = mutableListOf<String>()
                classes.add("message")
                for (c in 0 until cc.size) {
                    val a = getDeviceById(cc[c])
                    points.add(a.xnew to a.ynew)
                }
                classes.add("message-${messages[i].type}")
                imgOverview.addPath(layerMessage, points, classes, deviceRadius * 1.5, minDistToOtherPath)
            }
            val name = if (queryNumber == -1) {
                "visual-overview.svg"
            } else {
                "visual-overview-${queryNumber.toString().padStart(4,'0')}.svg"
            }
            File(outputDirectory + name).withOutputStream { out ->
                out.println(imgOverview.toString())
            }
        }
        return true
    }
    private fun toBaseImageDB(): ImageHelper {
        val imageHelperBase = toBaseImageStyle()
        for (device in devices) {
            val classes = mutableListOf("device")
            var layer = layerDeviceNone
            var layerName = layerDeviceNoneName

            if (device.hasDatabaseStore && device.hasDatabaseQuery) {
                classes.add("device-database-store-query")
                layer = layerDeviceDB
                layerName = layerDeviceDBName
            } else if (device.hasDatabaseStore) {
                classes.add("device-database-store")
                layer = layerDeviceDB
                layerName = layerDeviceDBName
            } else if (device.hasDatabaseQuery) {
                classes.add("device-database-query")
                layer = layerDeviceDB
                layerName = layerDeviceDBName
            }
            imageHelperBase.addCircle(layer, device.xnew, device.ynew, deviceRadius, classes)
            imageHelperBase.addText(layerName, device.xnew, device.ynew - deviceRadius * 1.5, device.id.toString(), mutableListOf())
        }
        for (connection in connectionsInRoutingDB) {
            val a = getDeviceById(connection.source)
            val b = getDeviceById(connection.destination)
            imageHelperBase.addLine(layerConnectionInRouting, a.xnew, a.ynew, b.xnew, b.ynew, listOf("connectionInRouting"))
        }
        return imageHelperBase
    }
    private fun saveDBStorageLocations(imageHelperBase: ImageHelper): ImageHelper {
        val image = imageHelperBase.deepCopy()
        for ((k, vs) in device_to_key) {
            val device = getDeviceById(k)
            var i = 1
            for (v in vs) {
                image.addText(layerStorageKey, device.xnew, device.ynew - deviceRadius * 1.5 - i * 13, v, mutableListOf())
                i++
            }
        }
        return image
    }
    private fun saveWorkForQuery(imageHelperBase: ImageHelper) {
        for ((queryID, listA) in workForQueryAtNode) {
            if (skipQueriesWithLessPartsThan <listA.values.map { it.size }.sum()) {
                var helperImageCounter = 0
                var allWork = mutableListOf<VisualisationOperatorGraph>()
                val mapOfReceivers: MutableMap<String, Pair<Double, Double>> = mutableMapOf<String, Pair<Double, Double>>()
                val mapOfSenders: MutableMap<String, Pair<Double, Double>> = mutableMapOf<String, Pair<Double, Double>>()
                val image = imageHelperBase.deepCopy()
                for ((deviceID, workList) in listA) {
                    val device = getDeviceById(deviceID)
                    var i = 1
                    var umfang = 0.0
                    var minR = 0.0
                    var list = mutableListOf<VisualisationOperatorGraph>()
                    for (work in workList) {
                        val second = work.second
                        val opGraph = VisualisationOperatorGraph()
                        val opImage = opGraph.operatorGraphToImage(second)
                        opGraph.anchorX = device.xnew
                        opGraph.anchorY = device.ynew
                        list.add(opGraph)
                        if (minR <opGraph.getRadius() + deviceRadius * 1.5) {
                            minR = opGraph.getRadius() + deviceRadius * 1.5
                        }
                        umfang += opGraph.getRadius()
                        allWork.add(opGraph)
                        File(outputDirectory + "visual-db-work-$queryID-$helperImageCounter.svg").withOutputStream { out ->
                            out.println(opImage.toString())
                        }
                        helperImageCounter++
                        i++
                    }
                    if (list.size> 1) {
                        val dist1 = umfang / (2.0 * PI)
                        val dist = if (dist1> minR) {
                            dist1
                        } else {
                            minR
                        }
                        i = 0
                        for (opGraph in list) {
                            opGraph.offsetX = opGraph.anchorX + sin(i.toDouble() / workList.size.toDouble() * 2.0 * PI) * dist
                            opGraph.offsetY = opGraph.anchorY + cos(i.toDouble() / workList.size.toDouble() * 2.0 * PI) * dist
                            i++
                        }
                    } else {
                        for (opGraph in list) {
                            opGraph.offsetX = opGraph.anchorX
                            opGraph.offsetY = opGraph.anchorY
                        }
                    }
                }
                for (w in allWork) {
                    val ox = w.myOffsetX()
                    val oy = w.myOffsetY()
                    for ((k, v) in w.mapOfSenders) {
                        mapOfSenders[k] = (v.first + ox) to(v.second + oy)
                    }
                    for ((k, v) in w.mapOfReceivers) {
                        mapOfReceivers[k] = (v.first + ox) to(v.second + oy)
                    }
                }
                for (w in allWork) {
                    w.toImage(image, layerWork, mapOfSenders, mapOfReceivers)
                }
                File(outputDirectory + "visual-db-work-$queryID.svg").withOutputStream { out ->
                    out.println(image.toString())
                }
            }
        }
    }
    private fun toImage(): String {
        File(outputDirectory).mkdirs()
        val imageHelperBase = toBaseImage()
        val imageHelperBaseDB = toBaseImageDB()
// ---->>>> save it as file
        File(outputDirectory + "visual.svg").withOutputStream { out ->
            out.println(imageHelperBase.toString())
        }
        File(outputDirectory + "visual-db.svg").withOutputStream { out ->
            out.println(imageHelperBaseDB.toString())
        }
        File(outputDirectory + "visual-db-storage.svg").withOutputStream { out ->
            out.println(saveDBStorageLocations(imageHelperBaseDB).toString())
        }
        var flag = true
        var counter = 0
        saveWorkForQuery(imageHelperBaseDB)
        while (flag) {
            flag = saveMessagesForQuery(imageHelperBase, counter)
            counter++
        }
// <<<<---- save it as file
        return imageHelperBase.toString()
    }

    private fun getDeviceById(id: Int): VisualisationDevice {
        try {
            return devices.filter { it.id == id }.first()
        } catch (e: Throwable) {
            throw Exception("getDeviceById $id not found", e)
        }
    }
    private fun addDistributedStorage(source: Int, destination: Int, time: Long, graphname: String, metaString: String) {
        addMessage(VisualisationMessage(source, destination, time, "create '$graphname'"))
        val metad = metaString.split("|")
        for (meta in metad) {
            val args = meta.split(";")
            if (args.size > 1) {
                when (args[0]) {
                    "PartitionedByID" -> {
                        val keys = mutableSetOf<String>()
                        for (i in 0 until args[2].toInt()) {
                            val hostname = args[4 + i * 2]
                            val key = args[4 + i * 2 + 1]
                            var l = device_to_key[hostname.toInt()]
                            if (l == null) {
                                l = mutableSetOf()
                                device_to_key[hostname.toInt()] = l
                            }
                            keys.add("${args[1]}@$hostname:$key")
                            l.add("${args[1]}@$hostname:$key")
                        }
                        graph_index_to_key["$graphname-${args[0]}(${args[1]},${args[2]},${args[3]})"] = keys
                    }
                    "PartitionedByKey" -> {
                        val keys = mutableSetOf<String>()
                        for (i in 0 until args[2].toInt()) {
                            val hostname = args[3 + i * 2]
                            val key = args[3 + i * 2 + 1]
                            var l = device_to_key[hostname.toInt()]
                            if (l == null) {
                                l = mutableSetOf()
                                device_to_key[hostname.toInt()] = l
                            }
                            keys.add("${args[1]}@$hostname:$key")
                            l.add("${args[1]}@$hostname:$key")
                        }
                        graph_index_to_key["$graphname-${args[0]}(${args[1]},${args[2]})"] = keys
                    }
                    "Simple" -> {
                        val hostname = args[2]
                        val key = args[3]
                        var l = device_to_key[hostname.toInt()]
                        if (l == null) {
                            l = mutableSetOf()
                            device_to_key[hostname.toInt()] = l
                        }
                        val keys = mutableSetOf<String>()
                        keys.add("${args[1]}@$hostname:$key")
                        l.add("${args[1]}@$hostname:$key")
                        graph_index_to_key["$graphname-${args[0]}(${args[1]})"] = keys
                    }
                    else -> throw Exception("unexpected type")
                }
            }
        }
    }
    override fun addDevice(address: Int, x: Double, y: Double, hasDBStore: Boolean, hasDBQuery: Boolean, hasSensor: Boolean) {
        val d = VisualisationDevice(address, hasDBStore, hasDBQuery, hasSensor)
        d.x = x
        d.y = y
        addDevice(d)
    }
    private fun addDevice(device: VisualisationDevice) {
        if (device.id> devicesMaxID) {
            devicesMaxID = device.id
        }
        devices.add(device)
    }

    override fun addConnectionTable(src: Int, dest: Int, hop: Int) {
        if (src != dest) {
            val idx = src * devicesMaxID + dest
            val size = devicesMaxID * devicesMaxID
            SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_visualize_distributed_database/src/commonMain/kotlin/lupos/visualize/distributed/database/VisualisationNetwork.kt:519"/*SOURCE_FILE_END*/ }, { devicesMaxID> src })
            SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_visualize_distributed_database/src/commonMain/kotlin/lupos/visualize/distributed/database/VisualisationNetwork.kt:520"/*SOURCE_FILE_END*/ }, { devicesMaxID> dest })
            SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_visualize_distributed_database/src/commonMain/kotlin/lupos/visualize/distributed/database/VisualisationNetwork.kt:521"/*SOURCE_FILE_END*/ }, { devicesMaxID> hop })
            SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_visualize_distributed_database/src/commonMain/kotlin/lupos/visualize/distributed/database/VisualisationNetwork.kt:522"/*SOURCE_FILE_END*/ }, { src != hop })
            if (connectionTable.size <size) {
                connectionTable = IntArray(size) { -1 }
            }
            connectionTable[idx] = hop
            connectionsInRouting.add(VisualisationConnection(src, hop))
        }
    }
    override fun addConnectionTableDB(src: Int, dest: Int, hop: Int) {
        if (src != dest && src != hop) {
            val idx = src * devicesMaxID + dest
            val size = devicesMaxID * devicesMaxID
            SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_visualize_distributed_database/src/commonMain/kotlin/lupos/visualize/distributed/database/VisualisationNetwork.kt:534"/*SOURCE_FILE_END*/ }, { devicesMaxID> src })
            SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_visualize_distributed_database/src/commonMain/kotlin/lupos/visualize/distributed/database/VisualisationNetwork.kt:535"/*SOURCE_FILE_END*/ }, { devicesMaxID> dest })
            SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_visualize_distributed_database/src/commonMain/kotlin/lupos/visualize/distributed/database/VisualisationNetwork.kt:536"/*SOURCE_FILE_END*/ }, { devicesMaxID> hop })
            if (connectionTableDB.size <size) {
                connectionTableDB = IntArray(size) { -1 }
            }
            connectionTableDB[idx] = hop
            connectionsInRoutingDB.add(VisualisationConnection(src, hop))
        }
    }
    public fun addConnection(connection: VisualisationConnection) {
        connections.add(connection)
    }

    override fun onSendPackage(src: Int, dest: Int, pck: IPayload) {
        val clock = getClock()
        when (pck) {
            is MySimulatorAbstractPackage -> {
                when (pck.path) {
                    "/distributed/query/dictionary/remove",
                    "/distributed/query/dictionary/register" -> {
// ignore dictionary right now
                    }
                    "/distributed/graph/create" -> {
                        addDistributedStorage(src, dest, clock, pck.params["name"]!!, pck.params["metadata"]!!)
                    }
                    "/distributed/graph/modify" -> {
                        val count = ((ByteArrayWrapperExt.getSize(pck.data) / DictionaryValueHelper.getSize()) - 1) / 3
                        addMessage(VisualisationMessage(src, dest, clock, "modify ${pck.params["mode"]} ${pck.params["idx"]}@$dest:${pck.params["key"]} .. triples=$count"))
                    }
                    "simulator-intermediate-result" -> {
                        val bytes = ByteArrayWrapperExt.getSize(pck.data)
                        addMessage(VisualisationMessage(src, dest, clock, "intermediate ${pck.params["key"]} .. count=$bytes"))
                    }
                    else -> addMessage(VisualisationMessage(src, dest, clock, pck.toString()))
                }
            }
            is MySimulatorOperatorGraphPackage -> {
                addMessage(VisualisationMessage(src, dest, clock, "operatorgraph ${pck.queryID} .. ${pck.operatorGraph.keys}"))
            }
            is Package_QueryResponse -> {
                addMessage(VisualisationMessage(src, dest, clock, "response ${pck.queryID} .. ${pck.result.size}"))
            }
            is Package_Query -> {
                addMessage(VisualisationMessage(src, dest, clock, "query ${pck.queryID} .. ${pck.query.decodeToString()}"))
            }
            else -> addMessage(VisualisationMessage(src, dest, clock, pck.toString()))
        }
    }

    private fun addMessage(message: VisualisationMessage) {
        allMessageTypes.add(message.type)
        message.messageCounter = messages.size
        messages.add(message)
    }
    override fun addWork(queryID: Int, address: Int, operatorGraph: XMLElement, keysIn: Set<Int>, keysOut: Set<Int>) {
        var workNode = workForQueryAtNode[queryID]
        if (workNode == null) {
            workNode = mutableMapOf()
            workForQueryAtNode[queryID] = workNode
        }
        var workquery = workNode[address]
        if (workquery == null) {
            workquery = mutableSetOf()
            workNode[address] = workquery
        }
        workquery.add("$keysIn -> $keysOut" to operatorGraph)
    }
    override fun addOperatorGraph(queryId: Int, operatorGraph: MutableMap<Int, XMLElement>) {
        fullOperatorGraph[queryId] = operatorGraph
    }
    override fun toString(): String = "${devices.map{it.toString() + "\n"}}\n${connections.map{it.toString() + "\n"}}\n${graph_index_to_key}\n${device_to_key}\n${messages.sorted().map{it.toString() + "\n"}}"
    override fun onStartSimulation() {
    }
    override fun onStopSimulation() {
        toImage()
    }
    override fun onStartUp() { }
    override fun onShutDown() { }
    override fun onSendNetworkPackage(src: Int, dest: Int, hop: Int, pck: IPayload, delay: Long) {}
    override fun onReceiveNetworkPackage(address: Int, pck: IPayload) {}
    override fun onReceivePackage(address: Int, pck: IPayload) {}
    override fun onSteadyState() {}
}
