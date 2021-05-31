class ParkingSensor(var device: Device): IDeviceSensor {

    var dataSinkAddress = device.address
        private set

    private var isStopped = false

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

    inner class SamplingProcessFinished: Entity.ITimerExpired {
        override fun onExpire() {
            onSampleTaken()
        }
    }

    override fun setDataSink(sinkAddress: Int) {
        dataSinkAddress = sinkAddress
    }

    override fun startSampling() {
        isStopped = false
        device.setTimer(dataRateInSeconds.toLong(), SamplingProcessFinished())
    }

    private fun onSampleTaken() {
        if (isStopped)
            return

        val data = getSample()
        device.sendSensorSample(dataSinkAddress, data)
        sampleCounter++
        startSampling()
    }

    private fun getSample()
        = ParkingSample(RandomGenerator.random.nextBoolean())


    override fun stopSampling() {
        isStopped = true
    }
}