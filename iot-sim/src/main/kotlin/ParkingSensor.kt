class ParkingSensor(
    var device: Device,
    override var dataSinkAddress: Int
): Device.Sensor {

    companion object {
        var dataRateInSeconds: Int = 30
        var observationCounter = 0
            private set
    }

    override fun observe() {
        val obEnd = NetworkPackage.ObservationEnd()
        device.sendSelfPackage(dataRateInSeconds.toLong(), obEnd)
    }

    override fun onObservationEnd() {
        val data = getParkingObservation()
        device.sendRoutedPackage(device.address, dataSinkAddress, data)
        observationCounter++
        observe()
    }

    private fun getParkingObservation()
        = NetworkPackage.ParkingObservation(RandomGenerator.random.nextBoolean())


}