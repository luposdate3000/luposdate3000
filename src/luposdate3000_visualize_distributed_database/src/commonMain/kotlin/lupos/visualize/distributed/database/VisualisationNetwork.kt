/*

src/luposdate3000_visualize_distributed_database/src/commonMain/kotlin/lupos/visualize/distributed/database/VisualisationNetwork.kt
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
import lupos.shared.SanityCheck
import lupos.shared.inline.File
public class VisualisationNetwork {

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
    private companion object {
        val layerConnection = 0
        val layerConnectionInRouting = 1
        val layerDeviceNone = 2
        val layerDeviceNoneName = 3
        val layerDeviceSensor = 4
        val layerDeviceSensorName = 5
        val layerDeviceDB = 6
        val layerDeviceDBName = 7
        val layerMessage = 8
        var deviceRadius = 20.0
        val minDistToOtherPath = 4.0
    }
    private fun messageToRoutingPath(src: Int, dest: Int): List<Int> { // TODO get this directly from simulator
        var tmp = mutableListOf<Int>()
        var s = src
        while (s != dest) {
            tmp.add(s)
            val idx = s * devicesMaxID + dest
            s = connectionTable[idx]
        }
        tmp.add(dest)
        return tmp
    }
    private fun messageToRoutingPathDB(src: Int, dest: Int): List<Int> { // TODO get this directly from simulator
        var tmp = mutableListOf<Int>()
        var s = src
        while (s != dest) {
            tmp.add(s)
            val idx = s * devicesMaxID + dest
            s = connectionTableDB[idx]
        }
        tmp.add(dest)
        return tmp
    }

    public fun toBaseImageStyle(): ImageHelper {
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
            "device-database",
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
            var newX = imageHelperBase.minX + ((device.x - minX) / (maxX - minX)) * (imageHelperBase.maxX - imageHelperBase.minX)
            var newY = imageHelperBase.minY + ((device.y - minY) / (maxY - minY)) * (imageHelperBase.maxY - imageHelperBase.minY)
            device.xnew = newX
            device.ynew = newY
        }
        return imageHelperBase
    }
    public fun toBaseImage(): ImageHelper {
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
            if (device.hasDatabase) {
                classes.add("device-database")
                layer = layerDeviceDB
                layerName = layerDeviceDBName
            }
            imageHelperBase.addCircle(layer, device.xnew, device.ynew, deviceRadius, classes)
            imageHelperBase.addText(layerName, device.xnew, device.ynew - deviceRadius * 1.5, device.id.toString(), mutableListOf())
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
    public fun toBaseImageDB(): ImageHelper {
        val imageHelperBase = toBaseImageStyle()
        for (device in devices) {
            val classes = mutableListOf("device")
            var layer = layerDeviceNone
            var layerName = layerDeviceNoneName
            if (device.hasDatabase) {
                classes.add("device-database")
                layer = layerDeviceDB
                layerName = layerDeviceDBName
                imageHelperBase.addCircle(layer, device.xnew, device.ynew, deviceRadius, classes)
                imageHelperBase.addText(layerName, device.xnew, device.ynew - deviceRadius * 1.5, device.id.toString(), mutableListOf())
            }
        }
        for (connection in connectionsInRoutingDB) {
            val a = getDeviceById(connection.source)
            val b = getDeviceById(connection.destination)
            imageHelperBase.addLine(layerConnectionInRouting, a.xnew, a.ynew, b.xnew, b.ynew, listOf("connectionInRouting"))
        }
        return imageHelperBase
    }

    public fun saveMessagesForQuery(imageHelperBase: ImageHelper, queryNumber: Int): Boolean {
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
        val imgOverview = imageHelperBase.deepCopy()
        for (i in first until last) {
            val cc = messageToRoutingPath(messages[i].source, messages[i].destination)
            var points = mutableListOf<Pair<Double, Double>>()
            var classes = mutableListOf<String>()
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
        File(name).withOutputStream { out ->
            out.println(imgOverview.toString())
        }
        return true
    }

    public fun toImage(): String {
        val imageHelperBase = toBaseImage()
        val imageHelperBaseDB = toBaseImageDB()
// ---->>>> save it as file
        File("visual.svg").withOutputStream { out ->
            out.println(imageHelperBase.toString())
        }
        File("visual-db.svg").withOutputStream { out ->
            out.println(imageHelperBaseDB.toString())
        }
        var flag = true
        var counter = 0
        while (flag) {
            flag = saveMessagesForQuery(imageHelperBase, counter)
            counter++
        }
// <<<<---- save it as file
        return imageHelperBase.toString()
    }

    private fun getDeviceById(id: Int): VisualisationDevice {
        return devices.filter { it.id == id }.first()
    }
    public fun addDistributedStorage(source: Int, destination: Int, time: Long, graphname: String, metaString: String) {
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
                                l = mutableSetOf<String>()
                                device_to_key[hostname.toInt()] = l
                            }
                            keys.add("${args[1]}@$hostname:$key")
                            l!!.add("${args[1]}@$hostname:$key")
                        }
                        graph_index_to_key["$graphname-${args[0]}(${args[1]},${args[2]},${args[3]})"] = keys
                    }
                    "PartitionedByKey" -> {
                        val keys = mutableSetOf<String>()
                        val type = "${args[0]}(${args[1]},${args[2]})"
                        for (i in 0 until args[2].toInt()) {
                            val hostname = args[3 + i * 2]
                            val key = args[3 + i * 2 + 1]
                            var l = device_to_key[hostname.toInt()]
                            if (l == null) {
                                l = mutableSetOf<String>()
                                device_to_key[hostname.toInt()] = l
                            }
                            keys.add("${args[1]}@$hostname:$key")
                            l!!.add("${args[1]}@$hostname:$key")
                        }
                        graph_index_to_key["$graphname-${args[0]}(${args[1]},${args[2]})"] = keys
                    }
                    "Simple" -> {
                        val type = "${args[0]}(${args[1]})"
                        val hostname = args[2]
                        val key = args[3]
                        var l = device_to_key[hostname.toInt()]
                        if (l == null) {
                            l = mutableSetOf<String>()
                            device_to_key[hostname.toInt()] = l
                        }
                        val keys = mutableSetOf<String>()
                        keys.add("${args[1]}@$hostname:$key")
                        l!!.add("${args[1]}@$hostname:$key")
                        graph_index_to_key["$graphname-${args[0]}(${args[1]})"] = keys
                    }
                    else -> throw Exception("unexpected type")
                }
            }
        }
    }
    public fun addDevice(device: VisualisationDevice) {
        if (device.id> devicesMaxID) {
            devicesMaxID = device.id
        }
        devices.add(device)
    }

    public fun addConnectionTable(src: Int, dest: Int, hop: Int) {
        if (src != dest) {
            val idx = src * devicesMaxID + dest
            val size = devicesMaxID * devicesMaxID
            SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_visualize_distributed_database/src/commonMain/kotlin/lupos/visualize/distributed/database/VisualisationNetwork.kt:371"/*SOURCE_FILE_END*/ }, { devicesMaxID> src })
            SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_visualize_distributed_database/src/commonMain/kotlin/lupos/visualize/distributed/database/VisualisationNetwork.kt:372"/*SOURCE_FILE_END*/ }, { devicesMaxID> dest })
            SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_visualize_distributed_database/src/commonMain/kotlin/lupos/visualize/distributed/database/VisualisationNetwork.kt:373"/*SOURCE_FILE_END*/ }, { devicesMaxID> hop })
            SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_visualize_distributed_database/src/commonMain/kotlin/lupos/visualize/distributed/database/VisualisationNetwork.kt:374"/*SOURCE_FILE_END*/ }, { src != hop })
            if (connectionTable.size <size) {
                connectionTable = IntArray(size) { -1 }
            }
            connectionTable[idx] = hop
            connectionsInRouting.add(VisualisationConnection(src, hop))
        }
    }
    public fun addConnectionTableDB(src: Int, dest: Int, hop: Int) {
        if (src != dest && src != hop) {
            val idx = src * devicesMaxID + dest
            val size = devicesMaxID * devicesMaxID
            SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_visualize_distributed_database/src/commonMain/kotlin/lupos/visualize/distributed/database/VisualisationNetwork.kt:386"/*SOURCE_FILE_END*/ }, { devicesMaxID> src })
            SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_visualize_distributed_database/src/commonMain/kotlin/lupos/visualize/distributed/database/VisualisationNetwork.kt:387"/*SOURCE_FILE_END*/ }, { devicesMaxID> dest })
            SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_visualize_distributed_database/src/commonMain/kotlin/lupos/visualize/distributed/database/VisualisationNetwork.kt:388"/*SOURCE_FILE_END*/ }, { devicesMaxID> hop })
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

    public fun addMessage(message: VisualisationMessage) {
        allMessageTypes.add(message.type)
        message.messageCounter = messages.size
        messages.add(message)
    }
    override fun toString(): String = "${devices.map{it.toString() + "\n"}}\n${connections.map{it.toString() + "\n"}}\n${graph_index_to_key}\n${device_to_key}\n${messages.sorted().map{it.toString() + "\n"}}"
}
