package lupos.simulator_iot.sensor

import lupos.simulator_core.Entity
import lupos.simulator_iot.Device
import lupos.simulator_iot.RandomGenerator

public class ParkingSensor(public var device: Device) : ISensor {

    public var dataSinkAddress: Int = device.address
        private set

    private var isStopped: Boolean = false

    public var sampleCounter: Int = 0
        private set

    init {
        sensorCounter++
    }

    public companion object {
        public var dataRateInSeconds: Int = 30

        public var sensorCounter: Int = 0
            private set

        public var totalSampleCounter: Int = 0
            private set

        public fun resetCounter() {
            totalSampleCounter = 0
            sensorCounter = 0
        }
    }

    public inner class SamplingProcessFinished : Entity.ITimer {
        override fun onExpire() {
            onSampleTaken()
        }
    }

    override fun setDataSink(sinkAddress: Int) {
        dataSinkAddress = sinkAddress
    }

    override fun startSampling() {
        isStopped = false
        device.setTimer(dataRateInSeconds, SamplingProcessFinished())
    }

    private fun onSampleTaken() {
        if (isStopped)
            return

        val data = getSample()
        device.sendSensorSample(dataSinkAddress, data)
        totalSampleCounter++
        // startSampling()
    }

    // TODO setze die richtigen Daten
    private fun getSample(): ParkingSample {
        return ParkingSample(
            sampleID = sampleCounter,
            sensorID = device.address,
            sampleTime = device.simulation.getCurrentClock(),
            isOccupied = RandomGenerator.random.nextBoolean(),
            parkingSpotID = device.address,
            area = device.address.toString()
        )
    }

    override fun stopSampling() {
        isStopped = true
    }
}
