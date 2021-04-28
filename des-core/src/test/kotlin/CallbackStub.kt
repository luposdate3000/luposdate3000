class CallbackStub: Simulation.Callback {
    override fun onSimulationStart() {
        logSimulationStart()
    }

    override fun onSimulationEnd() {
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