package lupos.simulator_iot.measure

import lupos.shared.inline.File
import lupos.simulator_iot.utils.FilePaths
import lupos.simulator_iot.config.Configuration

internal class Logger(
    private val config: Configuration,
    private val measurement: Measurement
) {

    private val directoryPath = FilePaths.logDir
    private val filePath = "${directoryPath}/log.txt"
    private val file = File(filePath)


    private val logFile = "${FilePaths.logDir}/log.txt"

    init {
        refreshFile()
    }

    internal fun refreshFile() {
        File(logFile).deleteRecursively()
        File(FilePaths.logDir).mkdirs()
        File(logFile).withOutputStream { }
        File(FilePaths.queryResult).deleteRecursively()
        File(FilePaths.queryResult).mkdirs()
        File(FilePaths.dbStates).deleteRecursively()
        File(FilePaths.dbStates).mkdirs()
    }

    internal fun logStartUp() {
        log("")
        log("")
        log("================================================")
        log("Simulation has started at ${measurement.realStartUpTimeStampInISO}")
        log("Initialize..")
        log("Number of devices: ${measurement.numberOfDevices}")
        log("Number of sensor devices: ${measurement.numberOfSensorsDevices}")
        log("Number of database devices: ${measurement.numberOfDatabasesDevices}")
        log("Number of query senders: ${measurement.numberOfQuerySenders}")
        log("Number of available links: ${measurement.numberOfLinks}")
        log("Initialization is finished after ${measurement.initializationDurationInSec}s")
        log("")
        log("")
    }

    internal fun logShutDown() {
        log("")
        log("")
        log("Number of parking samples made: ${measurement.numberOfParkingSamplesMade}")
        log("Number of queries made: ${measurement.numberOfQueriesRequested}")
        log("")
        log("Total number of packages sent: ${measurement.numberOfSentPackages}")
        log("DIO packages sent: ${measurement.numberOfSentDIOPackages}")
        log("DAO packages sent: ${measurement.numberOfSentDAOPackages}")
        log("Sensor packages sent: ${measurement.numberOfSentSamplePackages}")
        log("Database packages sent: ${measurement.numberOfSentDatabasePackages}")
        log("")
        log("Total number of packet forwards: ${measurement.numberOfForwardedPackages}")
        log("")
        log("Total network traffic in whole kilobytes: ${measurement.networkTrafficInBytes / 1000}")
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
        val stream = File(logFile).openOutputStream(true)
        stream.println(content)
        stream.close()
//        println(content)
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
