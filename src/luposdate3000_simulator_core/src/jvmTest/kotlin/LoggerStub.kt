package lupos.simulator_core

public class LoggerStub() : ISimulationLifeCycle {

    override lateinit var simulation: ISimulation

    override fun onStartUp() {
        logSimulationStart()
    }

    override fun onSteadyState() {
        logSimulationSteadyStateReached()
    }

    override fun onShutDown() {
        logSimulationEnd()
    }

    private fun log(content: String) {
        println(content)
    }

    private fun logSimulationStart() {
        log("")
        log("")
        log("================================================")
        log("Simulation has started")
        log("Number of entities: ${(simulation as Simulation).numberOfEntities()}")
        log("")
    }

    private fun logSimulationSteadyStateReached() {
        log("Steady state is reached. Measurements can start.")
    }

    private fun logSimulationEnd() {
        log("")
        log("Number of processed events: ${(simulation as Simulation).addedEventCounter}")
        // log("Simulation clock: ${simulation.currentClock}")
        log("Simulation completed")
        log("================================================")
        log("")
        log("")
    }
}
