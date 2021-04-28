class Logger: Simulation.Callback {

    override fun onStartUp() {
        log("")
        log("")
        log("================================================")
        log("Simulation has started")
        log("Number of devices: ${Configuration.devices.size}")
        log("Number of links: ${LinkManager.linkCounter}")
        //log("Number of DODAG links: ${LinkManager.linkCounter}")
        log("")
    }

    override fun onShutDown() {
        log("")
        log("Number of processed events: ${Simulation.eventCounter}")
        log("Simulation clock: ${Simulation.clock}")
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
    }
}