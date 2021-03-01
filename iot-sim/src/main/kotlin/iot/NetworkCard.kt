package iot

import simulation.Entity
import simulation.Event

class NetworkCard(val device: Device) : Entity() {

    var connections: MutableList<Connection> = ArrayList()

    fun getNetworkDelay(destination: Device): Long {
        return if (destination == device) {
            0
        } else {
            1
        }
    }

    fun getDistance(destination: Device): Double {
        return 0.0
    }

    override fun startUpEntity() {

    }

    override fun processEvent(event: Event) {
        val pck = event.data as NetworkPackage
        val destination = getReceiverEntity(pck)
        if(this == destination) {
            // do something
            return
        }
        else {
            val delay = getNetworkDelay(pck.receiver)
            sendEvent(destination, delay, pck )
        }

    }

    private fun getReceiverEntity(networkPackage: NetworkPackage)
        = networkPackage.receiver.networkCard

    override fun shutDownEntity() {
    }

}