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

import lupos.shared.inline.File
import lupos.simulator_iot.config.Configuration
import lupos.simulator_iot.utils.FilePaths

internal class Logger(
    private val config: Configuration,
    private val measurement: Measurement
) {

    private val logFile = File("${FilePaths.outputDir}/log.txt")

    init {
        refreshLogFile()
    }

    private fun refreshLogFile() {
        logFile.deleteRecursively()
        logFile.withOutputStream { }
    }

    internal fun logStartUp() {
        log("")
        log("")
        log("================================================")
        log("Simulation has started at ${measurement.realStartUpTimeStampInISO}")
        log("Initialize..")
        log("Number of devices: ${measurement.numberOfDevices}")
        log("Number of sensor devices: ${measurement.numberOfSensorDevices}")
        log("Number of database devices: ${measurement.numberOfDatabaseDevices}")
        log("Number of query senders: ${measurement.numberOfQuerySenders}")
        log("Number of available links: ${measurement.numberOfLinks}")
        log("Initialization is finished after ${measurement.initializationDurationInSec}s")
        log("")
        log("")
    }

    internal fun logShutDown() {
        log("")
        log("")
        log("Number of parking samples made: ${measurement.numberOfParkingSamplesMade.toLong()}")
        log("Number of queries made: ${measurement.numberOfQueriesRequested.toLong()}")
        log("")
        log("Total number of packages sent: ${measurement.numberOfSentPackages.toLong()}")
        log("DIO packages sent: ${measurement.numberOfSentDIOPackages.toLong()}")
        log("DAO packages sent: ${measurement.numberOfSentDAOPackages.toLong()}")
        log("Sensor packages sent: ${measurement.numberOfSentSamplePackages.toLong()}")
        log("Database packages sent: ${measurement.numberOfSentDatabasePackages.toLong()}")
        log("")
        log("Total number of packet forwards: ${measurement.numberOfForwardedPackages.toLong()}")
        log("")
        log("Total network traffic in whole kilobytes: ${measurement.networkTrafficInKiloBytes}")
        log("")
        log(getDODAGString())
        log("")
        log("Simulation end time: ${measurement.shutDownTimeStampInISO}")
        log("Difference to start time: ${measurement.simulationDurationInSec}s")
        log("")
        log("Real simulation end time: ${measurement.realShutDownTimeStampInISO}")
        log("Real difference to start time: ${measurement.realSimulationDurationInSec}s")
        log("")
        log("Simulation completed")
        log("================================================")
        log("")
        log("")
    }

    internal fun log(content: String) {
        val stream = logFile.openOutputStream(true)
        stream.println(content)
        stream.close()
    }

    private fun getDODAGString(): String {
        val strBuilder = StringBuilder()
        strBuilder.appendLine("Constructed DODAG:")
        for (i in 0 until config.getNumberOfDevices()) {
            val device = config.getDeviceByAddress(i)
            strBuilder.appendLine(device.router)
        }
        return strBuilder.toString()
    }
}
