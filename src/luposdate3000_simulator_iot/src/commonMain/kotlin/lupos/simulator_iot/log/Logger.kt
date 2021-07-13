package lupos.simulator_iot.log

import lupos.shared.inline.File
import lupos.simulator_iot.Device
import lupos.simulator_iot.FilePaths
import lupos.simulator_iot.Measurement
import lupos.simulator_iot.config.Configuration
import lupos.simulator_iot.net.routing.RPL

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
        log("Number of queries requested: ${measurement.numberOfQueriesRequested}")
        log("")
        log("Number of packages sent: ${measurement.numberOfSentPackages}")
        log("DIO packages: ${measurement.numberOfSentDIOPackages}")
        log("DAO packages: ${measurement.numberOfSentDAOPackages}")
        log("Sample packages: ${measurement.numberOfSentSamplePackages}")
        log("Database packages: ${measurement.numberOfSentDatabasePackages}")
        log("")
        log(getDODAGString())
        log("")
        log("Simulation end time: ${measurement.shutDownTimeStampInISO}")
        log("Difference to start time: ${measurement.simulationDurationInSec}s")
        log("Real simulation end time: ${measurement.realShutDownTimeStampInISO}")
        log("Real difference to start time: ${measurement.realSimulationDurationInSec}s")
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
