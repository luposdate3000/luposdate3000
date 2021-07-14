package lupos.simulator_iot.measure

import lupos.shared.inline.File
import lupos.simulator_iot.utils.FilePaths

internal class MeasurementPrinter {

    private val file = File("${FilePaths.outputDir}/measurements.csv")
    private val delimiter = ";"

    init {
        refreshFile()
    }

    private fun refreshFile() {
        val directory = File(FilePaths.outputDir)
        if(!directory.exists()) {
            directory.mkdirs()
        }
        file.deleteRecursively()
        file.withOutputStream { }
        printHeaderLine()
    }

    private fun printHeaderLine() {
        printLine(
            "Devices${delimiter}" +
                "Sensors${delimiter}" +
                "Databases${delimiter}" +
                "QuerySenders${delimiter}" +
                "Links${delimiter}" +
                "DODAG Links${delimiter}" +
                "RealInitDuration${delimiter}" +
                "RealSimDuration${delimiter}" +
                "SimDuration${delimiter}" +
                "SentPackages${delimiter}" +
                "TrafficInKBytes${delimiter}" +
                "SentDatabasePackages${delimiter}" +
                "SentSamplePackages${delimiter}" +
                "SentDIOPackages${delimiter}" +
                "SentDAOPackages${delimiter}" +
                "ForwardedPackages${delimiter}" +
                "SamplesMade${delimiter}" +
                "QueriesMade${delimiter}"
        )
    }

    internal fun printMeasurement(m: Measurement) {
        printLine(
            "${m.numberOfDevices}$delimiter" +
            "${m.numberOfSensorsDevices}$delimiter" +
            "${m.numberOfDatabasesDevices}$delimiter" +
            "${m.numberOfQuerySenders}$delimiter" +
            "${m.numberOfLinks}$delimiter" +
            "${"todo"}$delimiter" +
            "${m.initializationDurationInSec}$delimiter" +
            "${m.realSimulationDurationInSec}$delimiter" +
            "${m.simulationDurationInSec}$delimiter" +
            "${m.numberOfSentPackages}$delimiter" +
            "${m.networkTrafficInBytes / 1000}$delimiter" +
            "${m.numberOfSentDatabasePackages}$delimiter" +
            "${m.numberOfSentSamplePackages}$delimiter" +
            "${m.numberOfSentDIOPackages}$delimiter" +
            "${m.numberOfSentDAOPackages}$delimiter" +
            "${m.numberOfForwardedPackages}$delimiter" +
            "${m.numberOfParkingSamplesMade}$delimiter" +
            "${m.numberOfQueriesRequested}"
        )
    }

    private fun printLine(line: String) {
        val stream = file.openOutputStream(true)
        stream.println(line)
        stream.close()
    }


}
