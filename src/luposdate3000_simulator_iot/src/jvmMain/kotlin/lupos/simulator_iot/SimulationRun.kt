package lupos.simulator_iot

import kotlinx.datetime.Instant
import lupos.simulator_core.ISimulationLifeCycle
import lupos.simulator_core.Simulation
import lupos.simulator_iot.config.Configuration
import lupos.simulator_iot.log.Logger

internal class SimulationRun {

    internal val config = Configuration
    internal val logger = Logger
    private lateinit var startUpTimeStamp: Instant
    private lateinit var shutDownTimeStamp: Instant
    private lateinit var realShutDownTimeStamp: Instant
    private var initStartTimeStamp: Instant = Time.stamp()

    internal lateinit var sim: Simulation

    private inner class LifeCycleImpl() : ISimulationLifeCycle {
        override lateinit var simulation: Simulation //TODO wird nicht gebraucht!

        override fun onStartUp() {
            TODO("Not yet implemented")
        }

        override fun onSteadyState() {
            TODO("Not yet implemented")
        }

        override fun onShutDown() {
            TODO("Not yet implemented")
        }
    }

    internal fun startSimulationRun() {
        sim = Simulation(Configuration.devices, callback = LifeCycleImpl())
        sim.startSimulation()
    }

}
