package iot

import simulation.Entity
import simulation.Event

class ParkingSensorEntity(
    name: String,
    dataRateInSeconds: Int) : Sensor(name, dataRateInSeconds) {

    override fun startUpEntity() {
        observeEnvironment()
    }

    override fun processEvent(event: Event) {
        if(isBusyEndEvent(event)) {
            sendObservationToSink()
            if(device!!.powerSupply.hasPowerLeft())
                observeEnvironment()
            else
                terminate()
        }
    }

    private fun sendObservationToSink() {
        val data = getObservation()
        val delay = device!!.getNetworkDelay(dataSink!!)
        sendEvent(dataSink!!.getMessageReceiver(), delay, 0, data)
        device!!.powerSupply.decrease()
    }

    private fun observeEnvironment() {
        beBusy(dataRateInSeconds.toDouble())
        device!!.powerSupply.decrease()
    }

    private fun getObservation()
        = ParkingObservation(RandomGenerator.random.nextBoolean())



    override fun shutDownEntity() {
        terminate()
    }
}