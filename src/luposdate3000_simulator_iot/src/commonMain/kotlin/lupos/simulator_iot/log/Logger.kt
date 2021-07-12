package lupos.simulator_iot.log

import lupos.shared.inline.File
import lupos.simulator_iot.Device
import lupos.simulator_iot.FilePaths
import lupos.simulator_iot.TimeMeasure
import lupos.simulator_iot.config.Configuration
import lupos.simulator_iot.net.LinkManager
import lupos.simulator_iot.net.routing.RPL
import lupos.simulator_iot.sensor.ParkingSensor

internal class Logger(private val timeMeasure: TimeMeasure, private val config: Configuration) {


    private val logFile = "${FilePaths.logDir}/log.txt"

    init {
        refreshFiles()
    }



    internal fun refreshFiles() {
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
        log("Simulation has started at ${timeMeasure.getStartUpTimeString()}")
        log("Initialize..")
        log("Number of devices: ${config.getNumberOfDevices()}")
        log("Number of sensors: ${ParkingSensor.sensorCounter}")
        log("Number of databases: ${config.dbDeviceAddresses.size}")
        log("Number of links: ${LinkManager.linkCounter}")
        log("Initialization is finished after ${timeMeasure.getInitDuration()}s")
        log("")
        log("")
    }

    internal fun logShutDown() {
        log("")
        log("")
        log(getDODAGString())
        log("Total number of network packages: ${Device.packageCounter}")
        log("Number of received DIOs: ${RPL.dioCounter}, of which further sent: ${RPL.forwardedDioCounter}")
        log("Number of received DAOs: ${RPL.daoCounter}, of which further sent: ${RPL.forwardedDaoCounter}")
        log("Number of data packages: ${Device.observationPackageCounter}")
        log("Number of parking observations: ${ParkingSensor.totalSampleCounter}")
        log("")
        log("Simulation end time: ${timeMeasure.getShutDownTimeString()}")
        log("Difference to start time: ${timeMeasure.getSimulationDuration()}s")
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
