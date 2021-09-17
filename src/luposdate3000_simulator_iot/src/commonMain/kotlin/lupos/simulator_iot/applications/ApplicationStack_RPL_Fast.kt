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

package lupos.simulator_iot.applications
import lupos.shared.SanityCheck
import lupos.simulator_core.ITimer
import lupos.simulator_db.IApplicationStack_Actuator
import lupos.simulator_db.IApplicationStack_Middleware
import lupos.simulator_db.ILogger
import lupos.simulator_db.IPayload
import lupos.simulator_iot.config.Configuration
import lupos.simulator_iot.models.Device
import lupos.simulator_iot.models.net.NetworkPackage

internal class ApplicationStack_RPL_Fast(
    private val child: IApplicationStack_Actuator,
    private val logger: ILogger,
    private val config: Configuration,
    private val compatibilityMode: Boolean,
) : IApplicationStack_Rooter {
    init {
        child.setRouter(this)
    }

    private lateinit var parent: Device
    private var isRoot = false
    private var routingTable = intArrayOf()
    private var routingTableDatabaseHops = intArrayOf()
    override fun setDevice(device: Device) {
        parent = device
    }

    override fun setRoot() {
        isRoot = true
    }

    override fun getNextDatabaseHops(destinationAddresses: IntArray): IntArray = IntArray(destinationAddresses.size) { routingTableDatabaseHops[destinationAddresses[it]] }
    override fun send(destinationAddress: Int, pck: IPayload) {
        val pck2 = NetworkPackage(parent.address, destinationAddress, pck)
        val hop = routingTable[destinationAddress]
        val delay = parent.getNetworkDelay(hop, pck2)
        parent.assignToSimulation(destinationAddress, hop, pck2, delay)
    }

    override fun receive(pck: IPayload): IPayload? {
        pck as NetworkPackage
        val payload = pck.payload
        if (pck.destinationAddress == parent.address) {
            child.receive(payload)
        } else {
            val hop = routingTable[pck.destinationAddress]
            val delay = parent.getNetworkDelay(hop, pck)
            parent.assignToSimulation(pck.destinationAddress, hop, pck, delay)
        }
        return null
    }

    override fun getAllChildApplications(): Set<IApplicationStack_Actuator> {
        var res = mutableSetOf<IApplicationStack_Actuator>(child)
        if (child is IApplicationStack_Middleware) {
            res.addAll(child.getAllChildApplications())
        }
        return res
    }

    override fun flush() {}
    override fun registerTimer(durationInNanoSeconds: Long, entity: ITimer): Unit = parent.registerTimer(durationInNanoSeconds, entity)
    override fun resolveHostName(name: String): Int = parent.resolveHostName(name)
    override fun shutDown() = child.shutDown()
    override fun addChildApplication(child: IApplicationStack_Actuator): Unit = (this.child as IApplicationStack_Middleware).addChildApplication(child)
    private fun generateRoutingTableUsingGlobalParentTable(globalParentTable: IntArray) {
        routingTable = IntArray(config.devices.size) { -1 }
        routingTable[parent.address] = parent.address // myself
        for (i in 0 until config.devices.size) {
            if (globalParentTable[i] == parent.address) {
                routingTable[i] = i // the next hops write down their own address
            }
        }
        var changed = true
        while (changed) {
            changed = false
            for (i in 0 until config.devices.size) {
                if (routingTable[i] == -1 && routingTable[globalParentTable[i]] != -1) {
                    routingTable[i] = routingTable[globalParentTable[i]] // their next hop is known, so use it
                    changed = true
                }
            }
        }
        for (i in 0 until config.devices.size) {
            if (routingTable[i] == -1) {
                SanityCheck.check(
                    { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_simulator_iot/src/commonMain/kotlin/lupos/simulator_iot/applications/ApplicationStack_RPL_Fast.kt:105"/*SOURCE_FILE_END*/ },
                    { !isRoot }, // no route possible
                )
                routingTable[i] = globalParentTable[parent.address] // everything else goes to my own parent
            }
        }
        routingTableDatabaseHops = IntArray(config.devices.size) { -1 }
        routingTableDatabaseHops[parent.address] = parent.address // myself
        val devicesWithDatabase = mutableSetOf<Int>()
        devicesWithDatabase.addAll(config.dbDeviceAddressesStore.toList())
        devicesWithDatabase.addAll(config.dbDeviceAddressesQuery.toList())
        if (devicesWithDatabase.contains(parent.address)) {
            val mappingOfNextDatabaseTowardsRoot = IntArray(config.devices.size) { -1 }
            val queue = mutableListOf<Int>()
            var myParent = -1
            for (d in devicesWithDatabase) {
                var hop = globalParentTable[d]
                while (!devicesWithDatabase.contains(hop)) {
                    val nextHop = globalParentTable[hop]
                    if (hop == nextHop) {
                        break
                    }
                    hop = nextHop
                }
                if (hop != d && devicesWithDatabase.contains(hop)) {
                    mappingOfNextDatabaseTowardsRoot[d] = hop
                    if (hop == parent.address) {
                        routingTableDatabaseHops[d] = d // next database hop towards leaves saves its own address
                        queue.add(d)
                    } else if (d == parent.address) {
                        routingTableDatabaseHops[hop] = hop // next database hop towards root
                        myParent = hop
                    }
                }
            }
            while (queue.size > 0) {
                val d = queue.removeAt(0)
                for (i in devicesWithDatabase) {
                    if (mappingOfNextDatabaseTowardsRoot[i] == d) {
                        routingTableDatabaseHops[i] = d // routing towards leaves
                        queue.add(i)
                    }
                }
            }
            if (!compatibilityMode) {
                for (i in devicesWithDatabase) {
                    if (routingTableDatabaseHops[i] == -1) {
                        SanityCheck.check(
                            { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_simulator_iot/src/commonMain/kotlin/lupos/simulator_iot/applications/ApplicationStack_RPL_Fast.kt:153"/*SOURCE_FILE_END*/ },
                            { myParent != -1 },
                        )
                        routingTableDatabaseHops[i] = myParent
                    }
                }
            }
        }
    }

    override fun startUp() {
        val globalParentTable = IntArray(config.devices.size) { -1 }
        val globalParentCosts = LongArray(config.devices.size) { Long.MAX_VALUE }
        if (isRoot) {
            globalParentCosts[parent.address] = 0
            globalParentTable[parent.address] = parent.address
            val queue = mutableListOf<Device>(parent)
            while (queue.size > 0) {
                val a = queue.removeAt(0)
                for (b in a.linkManager.getNeighbours().map { config.devices[it] }) {
                    val delay = a.linkManager.getTransmissionDelay(b.address, 1024) // delay for sending 1kb
                    val p = if (globalParentCosts[a.address] < globalParentCosts[b.address]) {
                        a to b
                    } else {
                        b to a
                    }
                    SanityCheck.check(
                        { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_simulator_iot/src/commonMain/kotlin/lupos/simulator_iot/applications/ApplicationStack_RPL_Fast.kt:180"/*SOURCE_FILE_END*/ },
                        { delay> 0 },
                    )
                    if (globalParentCosts[p.second.address] > globalParentCosts[p.first.address] + delay) {
                        globalParentCosts[p.second.address] = globalParentCosts[p.first.address] + delay
                        globalParentTable[p.second.address] = p.first.address
                        queue.add(p.second)
                    }
                }
            }
            for (d in config.devices) {
                (d.applicationStack as ApplicationStack_RPL_Fast).generateRoutingTableUsingGlobalParentTable(globalParentTable)
            }
        }
        child.startUp()
    }
}