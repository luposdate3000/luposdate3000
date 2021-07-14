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

public class VisualisationNetwork {

    private val devices = mutableSetOf<VisualisationDevice>()
    private val connections = mutableSetOf<VisualisationConnection>()
    private val messages = mutableListOf<VisualisationMessage>()
    private val graph_index_to_key = mutableMapOf<String, MutableSet<String>>()
    private val device_to_key = mutableMapOf<Int, MutableSet<String>>()

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

    public fun addConnection(connection: VisualisationConnection) {
        connections.add(connection)
    }

    public fun addMessage(message: VisualisationMessage) {
        message.messageCounter = messages.size
        messages.add(message)
    }
    override fun toString(): String = "${devices.map{it.toString() + "\n"}}\n${connections.map{it.toString() + "\n"}}\n${graph_index_to_key}\n${device_to_key}\n${messages.sorted().map{it.toString() + "\n"}}"
}


