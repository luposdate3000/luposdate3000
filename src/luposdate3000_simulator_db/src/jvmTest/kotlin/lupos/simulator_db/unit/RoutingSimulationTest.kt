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

package lupos.simulator_db.unit

import lupos.simulator_iot.SimulationRun
import lupos.simulator_iot.applications.ApplicationStack_RPL
import kotlin.test.Test
import kotlin.test.assertEquals

class RoutingSimulationTest {

    companion object {
        private const val prefix = "src/jvmTest/resources/routingSimulationTest"
    }

    @Test
    fun runSimulationWithoutEntities() {
        val simRun = SimulationRun()
        val config = simRun.parseConfig("$prefix/runSimulationWithoutEntities.json")
        simRun.startSimulation(config)

        assertEquals(0, simRun.sim.clock)
    }

    @Test
    fun meshToDODAG() {
        val simRun = SimulationRun()
        val config = simRun.parseConfig("$prefix/meshToDODAG.json")
        val root = config.getRootDevice()
        val rootRouter = root.applicationStack as ApplicationStack_RPL
        simRun.startSimulation(config)

        assertEquals(config.getNumberOfDevices() - 1, rootRouter.routingTable.getDestinations().size)
    }

    @Test
    fun upwardRouteForwarding() {
        // Send data from the leaf F to the root A
        val simRun = SimulationRun()
        val config = simRun.parseConfig("$prefix/upwardRouteForwarding.json")
        simRun.simMaxClock = 300 * 1000 * 1000 * 1000
        simRun.startSimulation(config)

        config.getDeviceByName("A")
    }

    @Test
    fun downwardRouteForwarding() {
        // Send data from the root A to the leaf F
        val simRun = SimulationRun()
        val config = simRun.parseConfig("$prefix/downwardRouteForwarding.json")
        simRun.simMaxClock = 200 * 1000 * 1000 * 1000
        simRun.startSimulation(config)

        config.getDeviceByName("F")
    }

    @Test
    fun upAndDownwardRouteForwarding() {
        // Send data from the leaf F to the leaf D
        val simRun = SimulationRun()
        val config = simRun.parseConfig("$prefix/upAndDownwardRouteForwarding.json")
        simRun.simMaxClock = 800 * 1000 * 1000 * 1000
        simRun.startSimulation(config)

        config.getDeviceByName("D")
    }

    @Test
    fun sensorsFromStarSendOverFixedLinks() {
        val simRun = SimulationRun()
        val config = simRun.parseConfig("$prefix/sensorsFromStarSendOverFixedLinks.json")

        config.getDeviceByName("DODAG ROOT")

        simRun.startSimulation(config)
    }
}
