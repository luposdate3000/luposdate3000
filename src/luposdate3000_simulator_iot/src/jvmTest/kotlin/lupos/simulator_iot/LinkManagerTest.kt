package lupos.simulator_iot

import lupos.simulator_iot.config.DeviceLinker
import kotlin.test.Test
import kotlin.test.assertEquals


public class LinkManagerTest {

    @Test
    fun transmissionDelay1() {
        val destAddr = 2
        val srcDevice = Stubs.createEmptyDevice(1)
        val destDevice = Stubs.createEmptyDevice(destAddr)

        val deviceLinker = DeviceLinker()
        deviceLinker.link(srcDevice, destDevice, 250)

        assertEquals(0, srcDevice.linkManager.getTransmissionDelay(destAddr, 0))
        assertEquals(1, srcDevice.linkManager.getTransmissionDelay(destAddr, 21))
        assertEquals(2, srcDevice.linkManager.getTransmissionDelay(destAddr, 50))
        assertEquals(64000, srcDevice.linkManager.getTransmissionDelay(destAddr, 2000000))
    }
}
