package iot

import simulation.Entity
import simulation.Event

class LocalizationSensorEntity(
    name: String,
    dataRateInSeconds: Int) : Sensor(name, dataRateInSeconds) {

    override fun startUpEntity() {

    }

    override fun processEvent(event: Event) {

    }

    override fun shutDownEntity() {

    }
}