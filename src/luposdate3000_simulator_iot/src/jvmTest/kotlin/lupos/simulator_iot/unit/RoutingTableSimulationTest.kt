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

package lupos.simulator_iot.unit

import lupos.simulator_iot.SimulationRun
import lupos.simulator_iot.models.routing.RPL
import lupos.simulator_iot.utils.FilePaths
import kotlin.test.Test
import kotlin.test.assertEquals

class RoutingTableSimulationTest {

    companion object {
        private const val prefix = "${FilePaths.testResource}/routingTableSimulationTest"
    }

    @Test
    fun multiHopDODAGRoutingTableTest() {
        val simRun = SimulationRun()
        val config = simRun.parseConfig("$prefix/multiHopDODAGRoutingTableTest.json")

        val a = config.getDeviceByName("A")
        val aRouter = a.router as RPL
        val b = config.getDeviceByName("B")
        val bRouter = b.router as RPL
        val c = config.getDeviceByName("C")
        val cRouter = c.router as RPL
        val d = config.getDeviceByName("D")
        val dRouter = d.router as RPL
        val e = config.getDeviceByName("E")
        val eRouter = e.router as RPL
        val f = config.getDeviceByName("F")
        val fRouter = f.router as RPL

        simRun.startSimulation(config)

        // routing table from A
        assertEquals(5, aRouter.routingTable.getDestinations().size)
        assertEquals(b.address, aRouter.routingTable.getNextHop(b.address))
        assertEquals(c.address, aRouter.routingTable.getNextHop(c.address))
        assertEquals(c.address, aRouter.routingTable.getNextHop(d.address))
        assertEquals(c.address, aRouter.routingTable.getNextHop(e.address))
        assertEquals(c.address, aRouter.routingTable.getNextHop(f.address))
        // routing table from B
        assertEquals(0, bRouter.routingTable.getDestinations().size)
        assertEquals(a.address, bRouter.routingTable.fallbackHop)
        // routing table from C
        assertEquals(3, cRouter.routingTable.getDestinations().size)
        assertEquals(a.address, cRouter.routingTable.fallbackHop)
        assertEquals(d.address, cRouter.routingTable.getNextHop(d.address))
        assertEquals(e.address, cRouter.routingTable.getNextHop(e.address))
        assertEquals(e.address, cRouter.routingTable.getNextHop(f.address))
        // routing table from D
        assertEquals(0, bRouter.routingTable.getDestinations().size)
        assertEquals(c.address, dRouter.routingTable.fallbackHop)
        // routing table from E
        assertEquals(1, eRouter.routingTable.getDestinations().size)
        assertEquals(c.address, eRouter.routingTable.fallbackHop)
        assertEquals(f.address, eRouter.routingTable.getNextHop(f.address))
        // routing table from F
        assertEquals(0, fRouter.routingTable.getDestinations().size)
        assertEquals(e.address, fRouter.routingTable.fallbackHop)
    }

    @Test
    fun starNetworkRoutingTables() {
        val simRun = SimulationRun()
        val config = simRun.parseConfig("$prefix/starNetworkRoutingTables.json")

        val starNet = config.randStarNetworks["garageA"]!!
        val root = starNet.root
        val rootRouter = root.router as RPL
        val child1 = starNet.children[0]
        val child1Router = child1.router as RPL

        simRun.startSimulation(config)

        assertEquals(20, rootRouter.routingTable.getDestinations().size)
        assertEquals(0, child1Router.routingTable.getDestinations().size)
        assertEquals(root.address, rootRouter.routingTable.fallbackHop)
        assertEquals(root.address, child1Router.routingTable.fallbackHop)
        for (child in starNet.children)
            assertEquals(child.address, rootRouter.routingTable.getNextHop(child.address))
    }

    /**
     * DB(A) -> B -> DB(C)
     */
    @Test
    fun getNextDBHops1() {
        val simRun = SimulationRun()
        val config = simRun.parseConfig("$prefix/getNextDBHops1.json")

        val a = config.getDeviceByName("A")
        val b = config.getDeviceByName("B")
        val c = config.getDeviceByName("C")

        simRun.startSimulation(config)

        // routing table from A
        var actual = a.router.getNextDatabaseHops(intArrayOf(a.address, b.address, c.address))
        assertEquals(a.address, actual[0])
        assertEquals(-1, actual[1])
        assertEquals(c.address, actual[2])
        // routing table from B
        actual = b.router.getNextDatabaseHops(intArrayOf(c.address, b.address, a.address, 999))
        assertEquals(c.address, actual[0])
        assertEquals(-1, actual[1])
        assertEquals(-1, actual[2])
        assertEquals(-1, actual[3])
        // routing table from C
        actual = c.router.getNextDatabaseHops(intArrayOf(c.address, 999, a.address))
        assertEquals(-1, actual[0])
        assertEquals(-1, actual[1])
        assertEquals(-1, actual[2])
    }

    /**
     * DB(A) -> B -> DB(C)
     *            -> DB(D)
     */
    @Test
    fun getNextDBHops2() {
        val simRun = SimulationRun()
        val config = simRun.parseConfig("$prefix/getNextDBHops2.json")

        val a = config.getDeviceByName("A")
        val b = config.getDeviceByName("B")
        val c = config.getDeviceByName("C")
        val d = config.getDeviceByName("D")

        simRun.startSimulation(config)

        // routing table from A
        var actual = a.router.getNextDatabaseHops(intArrayOf(a.address, b.address, c.address, d.address))
        assertEquals(a.address, actual[0])
        assertEquals(-1, actual[1])
        assertEquals(c.address, actual[2])
        assertEquals(d.address, actual[3])
        // routing table from B
        actual = b.router.getNextDatabaseHops(intArrayOf(c.address, d.address, b.address))
        assertEquals(c.address, actual[0])
        assertEquals(d.address, actual[1])
        assertEquals(-1, actual[2])
    }

    /**
     * DB(A) -> B -> DB(C) -> DB(D) -> E -> DB(F)
     *                              -> DB(G)
     */
    @Test
    fun getNextDBHops3() {
        val simRun = SimulationRun()
        val config = simRun.parseConfig("$prefix/getNextDBHops3.json")

        val a = config.getDeviceByName("A")
        val b = config.getDeviceByName("B")
        val c = config.getDeviceByName("C")
        val d = config.getDeviceByName("D")
        val e = config.getDeviceByName("E")
        val f = config.getDeviceByName("F")
        val g = config.getDeviceByName("G")

        simRun.startSimulation(config)

        // routing table from A
        var actual = a.router.getNextDatabaseHops(intArrayOf(a.address, b.address, c.address, d.address, e.address, f.address, g.address))
        assertEquals(a.address, actual[0])
        assertEquals(-1, actual[1])
        assertEquals(c.address, actual[2])
        assertEquals(c.address, actual[3])
        assertEquals(c.address, actual[4])
        assertEquals(c.address, actual[5])
        assertEquals(c.address, actual[6])
        // routing table from B
        actual = b.router.getNextDatabaseHops(intArrayOf(c.address, d.address, e.address, f.address, g.address))
        assertEquals(c.address, actual[0])
        assertEquals(c.address, actual[1])
        assertEquals(c.address, actual[2])
        assertEquals(c.address, actual[3])
        assertEquals(c.address, actual[4])
        // routing table from D
        actual = d.router.getNextDatabaseHops(intArrayOf(e.address, f.address, g.address))
        assertEquals(-1, actual[0])
        assertEquals(f.address, actual[1])
        assertEquals(g.address, actual[2])
        // routing table from E
        actual = e.router.getNextDatabaseHops(intArrayOf(e.address, f.address, a.address))
        assertEquals(-1, actual[0])
        assertEquals(f.address, actual[1])
        assertEquals(-1, actual[2])
    }
}
