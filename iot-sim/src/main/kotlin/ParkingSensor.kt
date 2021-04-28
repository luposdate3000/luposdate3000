class ParkingSensor(
    var device: Device,
    override var dataSinkAddress: Int
): Device.Sensor {

    class ParkingObservation(val isOccupied: Boolean)

    companion object {
        var dataRateInSeconds: Int = 30
        var observationCounter = 0
            private set

        fun resetCounter() {
            observationCounter = 0
        }
    }

    override fun observe() {
        device.waitForObservationEnd(dataRateInSeconds.toLong())
    }

    override fun onObservationEnd() {
        val data = getParkingObservation()
        device.sendRoutedPackage(device.address, dataSinkAddress, data)
        observationCounter++
        observe()
    }

    private fun getParkingObservation()
        = ParkingObservation(RandomGenerator.random.nextBoolean())


}