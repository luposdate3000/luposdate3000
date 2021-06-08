package lupos.iot_sim
import lupos.iot_sim.config.Configuration
import lupos.iot_sim.routing.RPLRouter
import lupos.iot_sim.sensor.ParkingSensor
import lupos.des_core.Simulation
import lupos.des_core.ISimulationLifeCycle

public class Logger(public val sim: Simulation): ISimulationLifeCycle {

    public override fun onStartUp() {
        log("")
        log("")
        log("================================================")
        log("Simulation has started")
        log("")
        log("Number of devices: ${Configuration.devices.size}")
        log("Number of sensors: ${ParkingSensor.sensorCounter}")
        log("Number of links: ${LinkManager.linkCounter}")
    }

    override fun onSteadyState() {
        //TODO
    }

    override fun onShutDown() {
        log(getDODAGString())
        log("Total number of network packages: ${Device.packageCounter}")
        log("Number of received DIOs: ${RPLRouter.dioCounter}, of which further sent: ${RPLRouter.forwardedDioCounter}")
        log("Number of received DAOs: ${RPLRouter.daoCounter}, of which further sent: ${RPLRouter.forwardedDaoCounter}")
        log("Number of data packages: ${Device.observationPackageCounter}")
        log("Number of parking observations: ${ParkingSensor.totalSampleCounter}")
        log("")
        log("Simulation clock: ${sim.currentClock}")
        log("Simulation completed")
        log("================================================")
        log("")
        log("")

        resetCounters()
    }

    private fun log(content: String) {
        println(content)
    }

    private fun resetCounters() {
        LinkManager.resetCounter()
        Device.resetCounter()
        RPLRouter.resetCounter()
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
