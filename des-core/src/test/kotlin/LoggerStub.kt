class LoggerStub: ISimulationLifeCycle {
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
        log("Number of entities: ${Simulation.numberOfEntities()}")
        log("")
    }

    private fun logSimulationSteadyStateReached() {
        log("Steady state is reached. Measurements can start.")
    }

    private fun logSimulationEnd() {
        log("")
        log("Number of processed events: ${Simulation.eventCounter}")
        log("Simulation clock: ${Simulation.clock}")
        log("Simulation completed")
        log("================================================")
        log("")
        log("")
    }
}