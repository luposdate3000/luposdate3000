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
import lupos.shared.inline.File
public class VisualisationNetwork {

    private val devices = mutableSetOf<VisualisationDevice>() // alle beteiligten Computer
    private val connections = mutableSetOf<VisualisationConnection>() // alle möglichen Verbindungen
    private val connectionsInRouting = mutableSetOf<VisualisationConnection>() // alle genutzten Verbindungen
    private val messages = mutableListOf<VisualisationMessage>() // alle gesendeten Nachrichten
    private val graph_index_to_key = mutableMapOf<String, MutableSet<String>>() // DB welche keys gehören zusammen zu einem Graphen
    private val device_to_key = mutableMapOf<Int, MutableSet<String>>() // //DB welcher key ist wo gespeichert
    private companion object {
        val layerConnection = 0
        val layerConnectionInRouting = 1
        val layerDevice = 2
        val layerMessage = 3

        val messagesToFade = 10
        var deviceRadius = 20.0
    }
    public fun toBaseImage(): ImageHelper {
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
                "stroke" to "#0000FF",
            )
        )
        for (i in 0 until messagesToFade) {
            val c = (255 * (i + 1) / messagesToFade).toString(16).padStart(2, '0')
            imageHelperBase.createClass(
                "message-fade-$i",
                mapOf(
                    "stroke-width" to "1",
                    "stroke" to "#$c$c$c",
                    "marker-end" to "url(#arrowhead)",
                )
            )
        }
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

            val classes = mutableListOf("device")
            if (device.hasDatabase) {
                classes.add("device-database")
            }
            if (device.hasSensor) {
                classes.add("device-sensor")
            }
            imageHelperBase.addCircle(layerDevice, device.xnew, device.ynew, deviceRadius, classes)
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

    public fun saveMessagesImageVideo(imageHelperBase: ImageHelper, queryNumber: Int): Boolean {
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

        for (i in first until last) {
            val img = imageHelperBase.deepCopy()
            var j = i - messagesToFade
            if (j <0) {
                j = 0
            }
            for (messageIndex in j until i + 1) {
                val messageTime = i - messageIndex // 0 current, $messagesToFade oldest
                val message = messages[messageIndex]
                val a = getDeviceById(message.source)
                val b = getDeviceById(message.destination)
                img.addLine(layerMessage, a.xnew, a.ynew, b.xnew, b.ynew, listOf("message-fade-$messageTime"))
            }
            val name = if (queryNumber == -1) {
                "visual-frame-${i.toString().padStart(4,'0')}.svg"
            } else {
                "visual-frame-${queryNumber.toString().padStart(4,'0')}-${i.toString().padStart(4,'0')}.svg"
            }
            File(name).withOutputStream { out ->
                out.println(img.toString())
            }
        }
        return true
    }

    public fun toImage(): String {
        val imageHelperBase = toBaseImage()
// ---->>>> save it as file
        val res = imageHelperBase.toString()
        File("visual.svg").withOutputStream { out ->
            out.println(res)
        }
        var flag = true
        var counter = 0
        while (flag) {
            flag = saveMessagesImageVideo(imageHelperBase, counter)
            counter++
        }
// <<<<---- save it as file
        return res
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
        devices.add(device)
    }

    public fun addConnectionInRouting(connection: VisualisationConnection) {
        connectionsInRouting.add(connection)
    }
    public fun addConnection(connection: VisualisationConnection) {
        connections.add(connection)
    }

    public fun addMessage(message: VisualisationMessage) {
        message.messageCounter = messages.size
        messages.add(message)
    }
    override fun toString(): String = "${devices.map{it.toString() + "\n"}}\n${connections.map{it.toString() + "\n"}}\n${graph_index_to_key}\n${device_to_key}\n${messages.sorted().map{it.toString() + "\n"}}"
}
