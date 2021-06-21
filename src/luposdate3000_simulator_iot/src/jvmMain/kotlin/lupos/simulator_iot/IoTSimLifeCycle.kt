package lupos.simulator_iot

import kotlinx.datetime.Instant
import lupos.simulator_core.ISimulationLifeCycle
import lupos.simulator_core.Simulation
import lupos.simulator_iot.config.Configuration
import lupos.simulator_iot.net.LinkManager
import lupos.simulator_iot.net.routing.RPL
import lupos.simulator_iot.sensor.ParkingSensor

internal object IoTSimLifeCycle : ISimulationLifeCycle {

    internal val pathToQueryResult: String = "query_result"
    internal val pathToDatabaseData: String = "db_data"

    override lateinit var simulation: Simulation
    private lateinit var startTimeStamp: Instant

     override fun onStartUp() {
        startTimeStamp = Time.stamp()
        refreshDatabaseDirectories()
        log("")
        log("")
        log("================================================")
        log("Simulation has started at ${Time.toISOString(startTimeStamp)}")
        log("")
        log("Number of devices: ${Configuration.devices.size}")
        log("Number of sensors: ${ParkingSensor.sensorCounter}")
        log("Number of links: ${LinkManager.linkCounter}")
    }

    override fun onSteadyState() {
    }

    override fun onShutDown() {
        val endTime = getSimulationTime()
        log(getDODAGString())
        log("Total number of network packages: ${Device.packageCounter}")
        log("Number of received DIOs: ${RPL.dioCounter}, of which further sent: ${RPL.forwardedDioCounter}")
        log("Number of received DAOs: ${RPL.daoCounter}, of which further sent: ${RPL.forwardedDaoCounter}")
        log("Number of data packages: ${Device.observationPackageCounter}")
        log("Number of parking observations: ${ParkingSensor.totalSampleCounter}")
        log("")
        log("Simulation end time: ${Time.toISOString(endTime)}")
        log("Difference to start time: ${Time.differenceInMillis(startTimeStamp, endTime)} ms")
        log("Simulation completed")
        log("================================================")
        log("")
        log("")

        resetCounters()
    }

    private fun refreshDatabaseDirectories() {
        lupos.shared.inline.File(pathToQueryResult).deleteRecursively()
        lupos.shared.inline.File(pathToQueryResult).mkdirs()

        lupos.shared.inline.File(pathToDatabaseData).deleteRecursively()
        lupos.shared.inline.File(pathToDatabaseData).mkdirs()
    }

    private fun getSimulationTime(): Instant = Time.addMillis(startTimeStamp, simulation.getCurrentClock())

    internal fun getSimulationTimeString(): String = Time.toISOString(getSimulationTime())

    private fun log(content: String) {
        println(content)
    }

    private fun resetCounters() {
        LinkManager.resetCounter()
        Device.resetCounter()
        RPL.resetCounter()
        ParkingSensor.resetCounter()
    }

    private fun getDODAGString(): String {
        val strBuilder = StringBuilder()
        strBuilder.appendLine("Constructed DODAG:")
        for (device in Configuration.devices) {
            strBuilder.appendLine(device.router)
        }
        return strBuilder.toString()
    }
}
