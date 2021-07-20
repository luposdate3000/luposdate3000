package lupos.simulator_iot.unit

import lupos.simulator_iot.models.net.DeviceLinker
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
        assertEquals(672000, srcDevice.linkManager.getTransmissionDelay(destAddr, 21))
        assertEquals(1600000, srcDevice.linkManager.getTransmissionDelay(destAddr, 50))
        assertEquals(64000000000, srcDevice.linkManager.getTransmissionDelay(destAddr, 2000000))
    }
}
