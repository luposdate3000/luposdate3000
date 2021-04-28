import java.lang.StringBuilder

class Logger: Simulation.Callback {

    override fun onStartUp() {
        log("")
        log("")
        log("================================================")
        log("Simulation has started")
        log("")
        log("Number of devices: ${Configuration.devices.size}")
        log("Number of links: ${LinkManager.linkCounter}")
    }

    override fun onShutDown() {
        //log("Number of DODAG links: ${LinkManager.linkCounter}")
        log(getDODAGString())
        log("Total number of packages: ${Device.packageCounter}")
        log("")
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
        Device.resetCounter()
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