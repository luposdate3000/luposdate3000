package lupos.simulator_iot.measure

import lupos.shared.inline.File
import lupos.simulator_iot.utils.FilePaths

internal class LoggerCollection {

    private val perfCSVFile = "${FilePaths.logDir}/perf.csv"

    private val delimiter = ";"

    init {
        refreshLogFiles()
    }

    private fun refreshLogFiles() {
        File(FilePaths.logDir).deleteRecursively()
        File(FilePaths.logDir).mkdirs()
        File(perfCSVFile).withOutputStream { }
        printHeaderLine()
    }

    private fun printHeaderLine() {
        printLine("Nodes${delimiter}InitDuration${delimiter}SimDuration${delimiter}Packages${delimiter}Load")
    }

    private fun printLine(str: String) {
        val stream = File(perfCSVFile).openOutputStream(true)
        stream.println(str)
        stream.close()
    }

    internal fun add(logger: Logger, nodeCount: Int) {
        // TODO
//        val line = "$nodeCount$delimiter${logger.getInitDuration()}$delimiter" +
//            "${logger.getRealSimulationDuration()}$delimiter${Device.packageCounter}$delimiter${Device.getNetworkLoadKBytes()}"
//        printLine(line)
    }
}
