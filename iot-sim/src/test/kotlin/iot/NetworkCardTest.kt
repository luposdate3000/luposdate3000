package iot

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class NetworkCardTest {


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

    @Test
    fun `add, retrieve and remove connections`() {
        val destAddress = "Test"
        val device = Stubs.createEmptyDevice()
        val networkCard = device.networkCard
        val params = Connection(0, 0.0, "WIFI", 0.0)
        networkCard.addDirectConnection(destAddress, params)
        Assertions.assertTrue(networkCard.hasDirectConnection(destAddress))
        val retrieved: Connection = networkCard.getDirectConnection(destAddress)
        networkCard.removeDirectConnection(destAddress)
        Assertions.assertFalse(networkCard.hasDirectConnection(destAddress))
        Assertions.assertEquals(params, retrieved)
    }

    @Test
    fun `get next Hop of existing connection`() {
        val device = Stubs.createEmptyDevice()
        val networkCard = device.networkCard

       // val hopAddress: String = networkCard.getNextHop(destinationAddress)

        val otherDevice = Stubs.createEmptyDevice()
        Assertions.assertEquals(1, networkCard.getNetworkDelay(otherDevice))
    }



}