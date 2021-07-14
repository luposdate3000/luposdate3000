package lupos.simulator_iot.measure

import lupos.shared.inline.File
import lupos.simulator_iot.FilePaths

internal class MeasurementPrinter {

    private val directoryPath = FilePaths.logDir
    private val csvFilePath = "${directoryPath}/measurements.csv"
    private val file = File(csvFilePath)
    private val delimiter = ";"

    init {
        refreshFile()
    }

    private fun refreshFile() {
        val directory = File(directoryPath)
        if(!directory.exists()) {
            directory.mkdirs()
        }
        if(file.exists()) {
            file.deleteRecursively()
        }
        file.withOutputStream { }
        printHeaderLine()
    }

    private fun printHeaderLine() {
        printLine("Nodes${delimiter}InitDuration${delimiter}SimDuration${delimiter}Packages${delimiter}Load")
    }

    private fun printLine(line: String) {
        val stream = file.openOutputStream(true)
        stream.println(line)
        stream.close()
    }

    internal fun printMeasurement(m: Measurement) {
        printLine("${m.numberOfDevices}$delimiter${m.initializationDurationInSec}$delimiter${m.realSimulationDurationInSec}$delimiter${m.numberOfSentPackages}")
    }


}
