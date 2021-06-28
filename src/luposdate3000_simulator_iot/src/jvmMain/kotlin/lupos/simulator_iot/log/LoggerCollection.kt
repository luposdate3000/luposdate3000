package lupos.simulator_iot.log

import lupos.shared.inline.File
import lupos.simulator_iot.FilePaths

internal class LoggerCollection {

    private val perfCSVFile = "${FilePaths.logDir}\\perf.csv"

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
        printLine("Nodes${delimiter}InitDuration${delimiter}SimDuration${delimiter}")
    }

    private fun printLine(str: String) {
        val stream = File(perfCSVFile).openOutputStream(true)
        stream.println(str)
        stream.close()
    }

    internal fun add(logger: Logger, nodeCount: Int) {
        val line = "$nodeCount$delimiter${logger.getInitDuration()}$delimiter${logger.getRealSimulationDuration()}"
        printLine(line)
    }


}
