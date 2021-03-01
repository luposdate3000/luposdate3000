package iot

import simulation.Entity
import simulation.Event

class LocalizationSensor(
    name: String,
    dataRateInSeconds: Int, dataSink: Device, device: Device
) : Sensor(name, dataRateInSeconds, dataSink, device) {

    override fun startUpEntity() {

    }

    override fun processEvent(event: Event) {

    }

    override fun shutDownEntity() {

    }
}