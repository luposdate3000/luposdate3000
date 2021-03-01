package iot

import com.javadocmd.simplelatlng.LatLng
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class NetworkCardTest {

    @Test
    fun `new device with default variables`() {
        val networkCard = Stubs.createEmptyDevice().networkCard
        Assertions.assertTrue(networkCard.connections.isEmpty())
    }

    @Test
    fun `self messages do not delay`() {
        val device = Stubs.createEmptyDevice()
        val networkCard = device.networkCard
        Assertions.assertEquals(0, networkCard.getNetworkDelay(device))
    }

    @Test
    fun `delay to other directly connected device is 1`() {
        val device = Stubs.createEmptyDevice()
        val networkCard = device.networkCard
        val otherDevice = Stubs.createEmptyDevice()
        Assertions.assertEquals(1, networkCard.getNetworkDelay(otherDevice))
    }



}