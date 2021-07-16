package lupos.simulator_iot.models.sensor

import lupos.simulator_core.ITimer
import lupos.simulator_iot.models.Device

internal class ParkingSensor(
    internal var device: Device,
    internal val rateInSec: Int,
    internal val maxSamples: Int,
    private val dataSinkDeviceName: String,
    private val parkingAreaID: Int,
) : ISensor {

    private val infinitySamples: Int = -1

    private var dataSinkAddress: Int = -1

    private var isStopped: Boolean = false

    private var sampleCounter: Int = 0

    private fun hasMaxSamplesReached() =
        maxSamples != infinitySamples && sampleCounter >= maxSamples

    internal inner class SamplingProcessFinished : ITimer {
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
            dataSinkAddress = device.simRun.config.getDeviceByName(dataSinkDeviceName).address
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
        device.simRun.incNumberOfParkingSamples()
        startSampling()
    }

    private fun getSample(): ParkingSample {
        return ParkingSample(
            sampleID = sampleCounter,
            sensorID = device.address,
            sampleTime = device.simRun.timeMeasurer.getSimulationTimeString(),
            isOccupied = device.simRun.randGenerator.getBoolean(0.5f),
            parkingSpotID = device.address,
            area = parkingAreaID.toString()
        )
    }

    override fun stopSampling() {
        isStopped = true
    }
}
