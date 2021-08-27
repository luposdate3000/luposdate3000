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

package lupos.simulator_iot.measure
import kotlinx.datetime.Instant
import lupos.simulator_iot.SimulationRun
import lupos.simulator_iot.utils.TimeUtils

public class TimeMeasurer(private val simRun: SimulationRun) {

    private lateinit var startUpTimeStamp: Instant
    private lateinit var shutDownTimeStamp: Instant
    private lateinit var realShutDownTimeStamp: Instant
    private var initStartTimeStamp: Instant = TimeUtils.stamp()

    internal fun onStartUp() {
        startUpTimeStamp = TimeUtils.stamp()
    }

    internal fun onShutDown() {
        shutDownTimeStamp = getSimulationTime()
        realShutDownTimeStamp = TimeUtils.stamp()
    }

    private fun getSimulationTime(): Instant {
        return TimeUtils.addNanoSeconds(startUpTimeStamp, simRun.sim.clock)
    }

    internal fun getTimeString(time: Instant): String {
        return TimeUtils.toISOString(time)
    }

    internal fun getSimulationTimeString(): String {
        return getTimeString(getSimulationTime())
    }

    internal fun getShutDownTimeString(): String {
        return getTimeString(shutDownTimeStamp)
    }

    internal fun getStartUpTimeString(): String {
        return getTimeString(startUpTimeStamp)
    }

    internal fun getInitDurationInSec(): Double =
        TimeUtils.differenceInSeconds(initStartTimeStamp, startUpTimeStamp)

    internal fun getSimulationDurationInSec(): Double =
        TimeUtils.differenceInSeconds(startUpTimeStamp, shutDownTimeStamp)

    internal fun getRealSimulationDurationInSec(): Double =
        TimeUtils.differenceInSeconds(startUpTimeStamp, realShutDownTimeStamp)

    internal fun getRealShutDownTimeString(): String {
        return getTimeString(realShutDownTimeStamp)
    }
}
