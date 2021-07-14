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

    public fun addDevice(device: VisualisationDevice) {
        devices.add(device)
    }

    public fun addConnection(connection: VisualisationConnection) {
        connections.add(connection)
    }

    public fun addMessage(message: VisualisationMessage) {
        messages.add(message)
    }
    override fun toString(): String = "${devices.map{it.toString() + "\n"}}\n${connections.map{it.toString() + "\n"}}\n${messages.map{it.toString() + "\n"}}"
}

public class VisualisationDevice(public val id: Int, public val hasDatabase: Boolean, public val hasSensor: Boolean) {
    override fun equals(other: Any?): Boolean = other is VisualisationDevice && id == other.id
    override fun hashCode(): Int = id
    override fun toString(): String = "VisualisationDevice(id=$id, hasDatabase=$hasDatabase, hasSensor=$hasSensor)"
}

public class VisualisationConnection(public val source: Int, public val destination: Int) {
    override fun equals(other: Any?): Boolean = other is VisualisationConnection && source == other.source && destination == other.destination
    override fun hashCode(): Int = source - destination
    override fun toString(): String = "VisualisationConnection($source -> $destination)"
}

public class VisualisationMessage(public val source: Int, public val destination: Int, public val time: Long, public val shortText: String) {
    override fun toString(): String = "VisualisationMessage($source -> $destination at $time : '$shortText')"
}
