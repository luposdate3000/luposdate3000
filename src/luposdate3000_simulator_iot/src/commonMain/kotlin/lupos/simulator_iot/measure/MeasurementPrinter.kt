package lupos.simulator_iot.measure

import lupos.shared.inline.File
import lupos.simulator_iot.utils.FilePaths

internal class MeasurementPrinter {

    private val avgFile = File("${FilePaths.outputDir}/average.csv")
    private val deviationFile = File("${FilePaths.outputDir}/deviation.csv")
    private val delimiter = ";"

    init {
        refreshFile()
    }

    private fun refreshFile() {
        val directory = File(FilePaths.outputDir)
        if (!directory.exists()) {
            directory.mkdirs()
        }
        avgFile.deleteRecursively()
        avgFile.withOutputStream { }
        printAvgHeaderLine()

        deviationFile.deleteRecursively()
        deviationFile.withOutputStream { }
        printDeviationHeaderLine()
    }

    private fun printAvgHeaderLine() {
        printLine(getHeaderLine(), avgFile)
    }

    private fun printDeviationHeaderLine() {
        printLine(getHeaderLine(), deviationFile)
    }

    internal fun printAvgMeasurement(m: Measurement) {
        printLine(getPrintLine(m), avgFile)
    }

    internal fun printDeviationMeasurement(m: Measurement) {
        printLine(getPrintLine(m), deviationFile)
    }

    private fun getPrintLine(m: Measurement): String {
        return "${m.numberOfDevices}$delimiter" +
            "${m.numberOfSensorDevices}$delimiter" +
            "${m.numberOfDatabaseDevices}$delimiter" +
            "${m.numberOfQuerySenders}$delimiter" +
            "${m.numberOfLinks}$delimiter" +
            "${"todo"}$delimiter" +
            "${m.initializationDurationInSec}$delimiter" +
            "${m.realSimulationDurationInSec}$delimiter" +
            "${m.simulationDurationInSec}$delimiter" +
            "${m.numberOfSentPackages}$delimiter" +
            "${m.networkTrafficInKiloBytes}$delimiter" +
            "${m.numberOfSentDatabasePackages}$delimiter" +
            "${m.numberOfSentSamplePackages}$delimiter" +
            "${m.numberOfSentDIOPackages}$delimiter" +
            "${m.numberOfSentDAOPackages}$delimiter" +
            "${m.numberOfForwardedPackages}$delimiter" +
            "${m.numberOfParkingSamplesMade}$delimiter" +
            "${m.numberOfQueriesRequested}"
    }

    private fun getHeaderLine(): String {
        return "Devices$delimiter" +
            "Sensors$delimiter" +
            "Databases$delimiter" +
            "QuerySenders$delimiter" +
            "Links$delimiter" +
            "DODAG Links$delimiter" +
            "RealInitDuration$delimiter" +
            "RealSimDuration$delimiter" +
            "SimDuration$delimiter" +
            "SentPackages$delimiter" +
            "TrafficInKBytes$delimiter" +
            "SentDatabasePackages$delimiter" +
            "SentSamplePackages$delimiter" +
            "SentDIOPackages$delimiter" +
            "SentDAOPackages$delimiter" +
            "ForwardedPackages$delimiter" +
            "SamplesMade$delimiter" +
            "QueriesMade$delimiter"
    }

    private fun printLine(line: String, file: File) {
        val stream = file.openOutputStream(true)
        stream.println(line)
        stream.close()
    }
}
