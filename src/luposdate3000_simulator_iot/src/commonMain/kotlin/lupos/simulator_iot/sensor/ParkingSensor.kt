package lupos.simulator_iot.sensor

import lupos.simulator_core.Entity
import lupos.simulator_iot.Device
import lupos.simulator_iot.RandomGenerator
import lupos.simulator_iot.config.Configuration
import lupos.simulator_iot.log.Logger

internal class ParkingSensor(
    internal var device: Device,
    internal val rateInSec: Int,
    internal val maxSamples: Int,
    private val dataSinkDeviceName: String,
) : ISensor {

    private val infinitySamples: Int = -1

    private var dataSinkAddress: Int = -1

    private var isStopped: Boolean = false

    internal var sampleCounter: Int = 0
        private set

    init {
        sensorCounter++
    }

    internal companion object {

        internal var sensorCounter: Int = 0
            private set

        internal var totalSampleCounter: Int = 0
            private set

        internal fun resetCounter() {
            totalSampleCounter = 0
            sensorCounter = 0
        }
    }

    private fun hasMaxSamplesReached() =
        maxSamples != infinitySamples && sampleCounter >= maxSamples

    internal inner class SamplingProcessFinished : Entity.ITimer {
        override fun onExpire() {
            onSampleTaken()
        }
    }

    override fun setDataSink(sinkAddress: Int) {
        dataSinkAddress = sinkAddress
    }

    internal fun getSinkAddress(): Int {
        return if (dataSinkAddress != -1) {
            dataSinkAddress
        } else {
            dataSinkAddress = Configuration.getNamedDevice(dataSinkDeviceName).address
            dataSinkAddress
        }
    }

    override fun startSampling() {
        if (hasMaxSamplesReached()) {
            return
        }

        isStopped = false
        val rateInMillis: Long = rateInSec.toLong() * 1000
        device.setTimer(rateInMillis, SamplingProcessFinished())
    }

    private fun onSampleTaken() {
        if (isStopped) {
            return
        }

        val data = getSample()
        device.sendSensorSample(getSinkAddress(), data)
        sampleCounter++
        totalSampleCounter++
        startSampling()
    }

    private fun getSample(): ParkingSample {
        return ParkingSample(
            sampleID = sampleCounter,
            sensorID = device.address,
            sampleTime = Logger.getSimulationTimeString(),
            isOccupied = Configuration.randomGenerator.getBoolean(0.5f),
            parkingSpotID = device.address,
            area = device.address.toString()
        )
    }

    override fun stopSampling() {
        isStopped = true
    }
}
