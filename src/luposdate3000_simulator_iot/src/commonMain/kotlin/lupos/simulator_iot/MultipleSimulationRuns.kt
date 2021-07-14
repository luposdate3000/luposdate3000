package lupos.simulator_iot

import lupos.simulator_iot.measure.Measurement
import lupos.simulator_iot.measure.MeasurementPrinter

internal class MultipleSimulationRuns(
    private val configFileName: String,
    private val numberOfRepetitions: Int,
    private val callback: ISimRunPreparation,
    private val printer: MeasurementPrinter
) {

    private val measurements: MutableList<Measurement> = mutableListOf()

    internal fun startSimulationRuns() {
        for(repetition in 1..numberOfRepetitions) {
            startSimulationRun()
        }
        evaluate()
    }

    private fun startSimulationRun() {
        val simRun = SimulationRun()
        val json = simRun.parseConfigFile(configFileName)
        callback.prepareJsonObjects(json)
        val config = simRun.parseJsonObjects(json)
        simRun.startSimulation(config)
        measurements.add(simRun.measurement)
    }

    private fun evaluate() {
        val avg = average(measurements)
        printer.printMeasurement(avg)
    }

    private fun average(list: MutableList<Measurement>): Measurement {
        val size = list.size
        val sum = sum(list)
        val avg = Measurement()

        // topology
        avg.numberOfDevices = sum.numberOfDevices / size
        avg.numberOfSensorsDevices = sum.numberOfSensorsDevices / size
        avg.numberOfDatabasesDevices = sum.numberOfDatabasesDevices / size
        avg.numberOfQuerySenders = sum.numberOfQuerySenders / size
        avg.numberOfLinks = sum.numberOfLinks / size

        //times
        avg.initializationDurationInSec = sum.initializationDurationInSec / size
        avg.realSimulationDurationInSec = sum.realSimulationDurationInSec / size
        avg.simulationDurationInSec = sum.simulationDurationInSec / size

        //traffic
        avg.numberOfSentPackages = sum.numberOfSentPackages / size
        avg.networkTrafficInBytes = sum.networkTrafficInBytes / size
        avg.numberOfSentDAOPackages = sum.numberOfSentDAOPackages / size
        avg.numberOfSentDIOPackages = sum.numberOfSentDIOPackages / size
        avg.numberOfSentDatabasePackages = sum.numberOfSentDatabasePackages / size
        avg.numberOfSentSamplePackages = sum.numberOfSentSamplePackages / size
        avg.numberOfForwardedPackages = sum.numberOfForwardedPackages / size
        avg.numberOfParkingSamplesMade = sum.numberOfParkingSamplesMade / size
        avg.numberOfQueriesRequested = sum.numberOfQueriesRequested / size

        return avg
    }

    private fun sum(list: MutableList<Measurement>): Measurement {
        val sum = Measurement()
        for(m in list) {
            // topology
            sum.numberOfDevices += m.numberOfDevices
            sum.numberOfSensorsDevices += m.numberOfSensorsDevices
            sum.numberOfDatabasesDevices += m.numberOfDatabasesDevices
            sum.numberOfQuerySenders += m.numberOfQuerySenders
            sum.numberOfLinks += m.numberOfLinks

            //times
            sum.initializationDurationInSec += m.initializationDurationInSec
            sum.realSimulationDurationInSec += m.realSimulationDurationInSec
            sum.simulationDurationInSec += m.simulationDurationInSec

            //traffic
            sum.numberOfSentPackages += m.numberOfSentPackages
            sum.networkTrafficInBytes += m.networkTrafficInBytes
            sum.numberOfSentDAOPackages += m.numberOfSentDAOPackages
            sum.numberOfSentDIOPackages += m.numberOfSentDIOPackages
            sum.numberOfSentDatabasePackages += m.numberOfSentDatabasePackages
            sum.numberOfSentSamplePackages += m.numberOfSentSamplePackages
            sum.numberOfForwardedPackages += sum.numberOfForwardedPackages
            sum.numberOfParkingSamplesMade += sum.numberOfParkingSamplesMade
            sum.numberOfQueriesRequested += sum.numberOfQueriesRequested
        }
        return sum
    }

}
