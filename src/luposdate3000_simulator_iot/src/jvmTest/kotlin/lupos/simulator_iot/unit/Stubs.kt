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
import lupos.simulator_iot.applications.ApplicationStack_MultipleChilds
import lupos.simulator_iot.applications.ApplicationStack_RPL
import lupos.simulator_iot.models.Device
import lupos.simulator_iot.models.geo.GeoLocation
import lupos.simulator_iot.models.net.LinkManager

internal object Stubs {

    internal fun createEmptyDevice(address: Int, simRun: SimulationRun = SimulationRun()): Device {
        val loc = GeoLocation.getRandom(simRun.randGenerator.random)
        return Device(simRun, loc, address, 100.0, LinkManager(intArrayOf()), true, createRouter(simRun), mutableMapOf())
    }

    internal fun createEmptyDevice(address: Int, linkTypes: IntArray, simRun: SimulationRun = SimulationRun()): Device {
        val loc = GeoLocation.getRandom(simRun.randGenerator.random)
        return Device(simRun, loc, address, 100.0, LinkManager(linkTypes), true, createRouter(simRun), mutableMapOf())
    }

    private fun createRouter(simRun: SimulationRun): ApplicationStack_RPL {
        return ApplicationStack_RPL(
            ApplicationStack_MultipleChilds(arrayOf()),
            simRun.logger,
            simRun.config,
        )
    }
}
