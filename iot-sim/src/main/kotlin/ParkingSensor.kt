class ParkingSensor(
    var device: Device,
    override var dataSinkAddress: Int
): Device.Sensor {

    companion object {
        var dataRateInSeconds: Int = 30
    }

    override fun observe() {
        val obEnd = NetworkPackage.ObservationEnd()
        device.sendSelfPackage(dataRateInSeconds.toLong(), obEnd)
    }

    override fun onObservationEnd() {
        val data = getParkingObservation()
        device.sendRoutedPackage(device.address, dataSinkAddress, data)
        observe()
    }

    private fun getParkingObservation()
        = NetworkPackage.ParkingObservation(RandomGenerator.random.nextBoolean())


}