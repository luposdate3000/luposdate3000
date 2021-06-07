package sensor

import Device
import Entity
import RandomGenerator

class ParkingSensor(var device: Device): ISensor {

    var dataSinkAddress = device.address
        private set

    private var isStopped = false

    var sampleCounter = 0
        private set


    init {
        sensorCounter++
    }

    companion object {
        var dataRateInSeconds: Int = 30

        var sensorCounter = 0
            private set

        var totalSampleCounter = 0
            private set

        fun resetCounter() {
            totalSampleCounter = 0
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
        totalSampleCounter++
        startSampling()
    }


    //TODO setze die richtigen Daten
    private fun getSample(): ParkingSample {
        return ParkingSample(
            sampleID = sampleCounter,
            sensorID = device.address,
            sampleTime = device.getCurrentSimulationTime(),
            isOccupied = RandomGenerator.random.nextBoolean(),
            parkingSpotID = device.address,
            area = device.address.toString())
    }


    override fun stopSampling() {
        isStopped = true
    }
}