package iot

import simulation.Event

class ParkingSensorEntity(
    name: String,
    dataRateInSeconds: Int, dataSink: Device, device: Device
) : Sensor(name, dataRateInSeconds, dataSink, device) {

    override fun startUpEntity() {
        observeEnvironment()
    }

    override fun processEvent(event: Event) {
        if(isBusyEndEvent(event)) {
            sendObservationToSink()
            if(device.powerSupply.hasPowerLeft())
                observeEnvironment()
            else
                terminate()
        }
    }

    private fun sendObservationToSink() {
        val dataPackage = createNetworkPackage(createObservationData())
        sendEvent(device.networkCard, 0, dataPackage)
        device.powerSupply.decrease()
    }

    private fun observeEnvironment() {
        beBusy(dataRateInSeconds.toLong())
        device.powerSupply.decrease()
    }

    private fun createObservationData()
        = ParkingObservation(RandomGenerator.random.nextBoolean())

    private fun createNetworkPackage(data: ParkingObservation)
        = NetworkPackage(device, dataSink, data)

    override fun shutDownEntity() {
        terminate()
    }
}