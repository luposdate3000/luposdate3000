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

import lupos.simulator_iot.models.net.DeviceLinker
import kotlin.test.Test
import kotlin.test.assertEquals

class LinkManagerTest {

    @Test
    fun transmissionDelay1() {
        val destAddr = 2
        val srcDevice = Stubs.createEmptyDevice(1)
        val destDevice = Stubs.createEmptyDevice(destAddr)

        val deviceLinker = DeviceLinker()
        deviceLinker.link(srcDevice, destDevice, 250)

        assertEquals(0, srcDevice.linkManager.getTransmissionDelay(destAddr, 0))
        assertEquals(672000, srcDevice.linkManager.getTransmissionDelay(destAddr, 21))
        assertEquals(1600000, srcDevice.linkManager.getTransmissionDelay(destAddr, 50))
        assertEquals(64000000000, srcDevice.linkManager.getTransmissionDelay(destAddr, 2000000))
    }
}
