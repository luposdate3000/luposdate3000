class ParkingSensor(
    var device: Device,
    override var dataSinkAddress: Int
): IDeviceSensor {

    class ParkingSample(val isOccupied: Boolean)

    init {
        sensorCounter++
    }

    companion object {
        var dataRateInSeconds: Int = 30

        var sensorCounter = 0
            private set

        var sampleCounter = 0
            private set

        fun resetCounter() {
            sampleCounter = 0
            sensorCounter = 0
        }
    }

    override fun startTakingSample() {
        device.waitForObservationEnd(dataRateInSeconds.toLong())
    }

    override fun onSampleTaken() {
        val data = getSample()
        device.sendSensorSample(dataSinkAddress, data)
        sampleCounter++
        startTakingSample()
    }


    private fun getSample()
        = ParkingSample(RandomGenerator.random.nextBoolean())


}