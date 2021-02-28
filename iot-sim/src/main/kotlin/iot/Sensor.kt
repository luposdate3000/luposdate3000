package iot

import simulation.Entity

abstract class Sensor(
    val name: String,
    var dataRateInSeconds: Int) : Entity() {

    var dataSink: Device? = null
    var device: Device? = null
}