package iot

import com.javadocmd.simplelatlng.LatLng

import simulation.Entity


class Device(
    val powerSupply: PowerSupply,
    val location: LatLng,
    val name: String,
    val application: Entity,
    val sensors: List<Sensor>) {

    var connections: MutableList<Connection> = ArrayList()

    fun getNetworkDelay(destination: Device): Double {
        //delay zur Datensenke TODO
        return 0.0
    }

    fun getDistance(destination: Device): Double {
        return 0.0 //TODO
    }

    fun getMessageReceiver() = application


}