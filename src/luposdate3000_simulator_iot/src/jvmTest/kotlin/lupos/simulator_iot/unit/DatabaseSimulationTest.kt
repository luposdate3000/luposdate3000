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
import lupos.simulator_iot.utils.FilePaths
import kotlin.test.Test
import kotlin.test.assertEquals

class DatabaseSimulationTest {

    companion object {
        private const val prefix = "${FilePaths.testResource}/databaseSimulationTest"
    }

    /**
     * DB(A) -> B -> DB(C) -> DB(D) -> E -> DB(F)
     *                              -> DB(G)
     *
     * Send Data from root A to the leaf G and save it there.
     */
    @Test
    fun saveParkingSamplesInDummyTripleStore() {
        val simRun = SimulationRun()
        val json = simRun.parseConfigFile("$prefix/saveParkingSamplesInDummyTripleStore.json")
        val config = simRun.parseJsonObjects(json)
        val g = config.getDeviceByName("G")

        simRun.simMaxClock = 100000000
        simRun.startSimulation(config)

        assertEquals(0, g.processedSensorDataPackages)
    }
}
