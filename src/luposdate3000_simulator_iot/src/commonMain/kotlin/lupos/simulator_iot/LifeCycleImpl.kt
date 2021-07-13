package lupos.simulator_iot

import lupos.simulator_core.ISimulationLifeCycle
import lupos.simulator_core.Simulation

public class LifeCycleImpl(private val simRun: SimulationRun) : ISimulationLifeCycle {

    override lateinit var simulation: Simulation // TODO wird nicht gebraucht!

    override fun onStartUp() {
        simRun.timeMeasure.onStartUp()
        simRun.logger.logStartUp()
    }

    override fun onSteadyState() {
    }

    override fun onShutDown() {
        simRun.timeMeasure.onShutDown()
        simRun.logger.logShutDown()
    }
}
