/*
 * This file is part of the Luposdate3000 distribution (https://github.com/luposdate3000/luposdate3000).
 * Copyright (c) 2020-2021, Institute of Information Systems (Benjamin Warnke and contributors of LUPOSDATE3000), University of Luebeck
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, version 3.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package lupos.simulator_iot.models.sensor

import lupos.simulator_core.ITimer
import lupos.simulator_iot.models.Device
import lupos.simulator_iot.utils.TimeUtils

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

    private var sendingVarianceInSec = 10

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
        device.setTimer(getObservationDuration(), SamplingProcessFinished())
    }

    private fun getObservationDuration(): Long {
        val random = device.simRun.randGenerator.getInt(0, sendingVarianceInSec)
        val durationInSec = rateInSec + random
        return TimeUtils.toNanoSec(durationInSec)
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
