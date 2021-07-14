package lupos.simulator_iot

import lupos.simulator_iot.measure.Measurement
import lupos.simulator_iot.measure.MeasurementPrinter
import kotlin.math.pow
import kotlin.math.sqrt

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
        printer.printAvgMeasurement(avg)
        val dev = deviation(avg, measurements)
        printer.printDeviationMeasurement(dev)
    }

    private fun average(list: MutableList<Measurement>): Measurement {
        val size = list.size
        val sum = sum(list)
        val avg = Measurement()

        // topology
        avg.numberOfDevices = sum.numberOfDevices / size
        avg.numberOfSensorDevices = sum.numberOfSensorDevices / size
        avg.numberOfDatabaseDevices = sum.numberOfDatabaseDevices / size
        avg.numberOfQuerySenders = sum.numberOfQuerySenders / size
        avg.numberOfLinks = sum.numberOfLinks / size

        //times
        avg.initializationDurationInSec = sum.initializationDurationInSec / size
        avg.realSimulationDurationInSec = sum.realSimulationDurationInSec / size
        avg.simulationDurationInSec = sum.simulationDurationInSec / size

        //traffic
        avg.numberOfSentPackages = sum.numberOfSentPackages / size
        avg.networkTrafficInKiloBytes = sum.networkTrafficInKiloBytes / size
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
            sum.numberOfSensorDevices += m.numberOfSensorDevices
            sum.numberOfDatabaseDevices += m.numberOfDatabaseDevices
            sum.numberOfQuerySenders += m.numberOfQuerySenders
            sum.numberOfLinks += m.numberOfLinks

            //times
            sum.initializationDurationInSec += m.initializationDurationInSec
            sum.realSimulationDurationInSec += m.realSimulationDurationInSec
            sum.simulationDurationInSec += m.simulationDurationInSec

            //traffic
            sum.numberOfSentPackages += m.numberOfSentPackages
            sum.networkTrafficInKiloBytes += m.networkTrafficInKiloBytes
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

    private fun step(observedValue: Double, avgValue: Double): Double {
        if(observedValue == 0.0) {
            return 0.0
        }
        return (observedValue - avgValue).pow(2)
    }

    private fun deviation(avg: Measurement, list: MutableList<Measurement>): Measurement {
        val tmp = Measurement()
        for (m in list) {
            // topology
            tmp.numberOfDevices += step(m.numberOfDevices, avg.numberOfDevices)
            tmp.numberOfSensorDevices += step(m.numberOfSensorDevices, avg.numberOfSensorDevices)
            tmp.numberOfDatabaseDevices += step(m.numberOfDatabaseDevices, avg.numberOfDatabaseDevices)
            tmp.numberOfQuerySenders += step(m.numberOfQuerySenders, avg.numberOfQuerySenders)
            tmp.numberOfLinks += step(m.numberOfLinks, avg.numberOfLinks)

            // times
            tmp.initializationDurationInSec += step(m.initializationDurationInSec, avg.initializationDurationInSec)
            tmp.realSimulationDurationInSec += step(m.realSimulationDurationInSec, avg.realSimulationDurationInSec)
            tmp.simulationDurationInSec += step(m.simulationDurationInSec, avg.simulationDurationInSec)

            // traffic
            tmp.numberOfSentPackages += step(m.numberOfSentPackages, avg.numberOfSentPackages)
            tmp.networkTrafficInKiloBytes += step(m.networkTrafficInKiloBytes, avg.networkTrafficInKiloBytes)
            tmp.numberOfSentDAOPackages += step(m.numberOfSentDAOPackages, avg.numberOfSentDAOPackages)
            tmp.numberOfSentDIOPackages += step(m.numberOfSentDIOPackages, avg.numberOfSentDIOPackages)
            tmp.numberOfSentDatabasePackages += step(m.numberOfSentDatabasePackages, avg.numberOfSentDatabasePackages)
            tmp.numberOfSentSamplePackages += step(m.numberOfSentSamplePackages, avg.numberOfSentSamplePackages)
            tmp.numberOfForwardedPackages += step(m.numberOfForwardedPackages, avg.numberOfForwardedPackages)
            tmp.numberOfParkingSamplesMade += step(m.numberOfParkingSamplesMade, avg.numberOfParkingSamplesMade)
            tmp.numberOfQueriesRequested += step(m.numberOfQueriesRequested, avg.numberOfQueriesRequested)
        }
        val size = list.size - 1

        // topology
        tmp.numberOfDevices = sqrt(tmp.numberOfDevices / size)
        tmp.numberOfSensorDevices = sqrt(tmp.numberOfSensorDevices / size)
        tmp.numberOfDatabaseDevices = sqrt(tmp.numberOfDatabaseDevices / size)
        tmp.numberOfQuerySenders = sqrt(tmp.numberOfQuerySenders / size)
        tmp.numberOfLinks = sqrt(tmp.numberOfLinks / size)

        // times
        tmp.initializationDurationInSec = sqrt(tmp.initializationDurationInSec / size)
        tmp.realSimulationDurationInSec = sqrt(tmp.realSimulationDurationInSec / size)
        tmp.simulationDurationInSec = sqrt(tmp.simulationDurationInSec / size)

        // traffic
        tmp.numberOfSentPackages = sqrt(tmp.numberOfSentPackages / size)
        tmp.networkTrafficInKiloBytes = sqrt(tmp.networkTrafficInKiloBytes / size)
        tmp.numberOfSentDAOPackages = sqrt(tmp.numberOfSentDAOPackages / size)
        tmp.numberOfSentDIOPackages = sqrt(tmp.numberOfSentDIOPackages / size)
        tmp.numberOfSentDatabasePackages = sqrt(tmp.numberOfSentDatabasePackages / size)
        tmp.numberOfSentSamplePackages = sqrt(tmp.numberOfSentSamplePackages / size)
        tmp.numberOfForwardedPackages = sqrt(tmp.numberOfForwardedPackages / size)
        tmp.numberOfParkingSamplesMade = sqrt(tmp.numberOfParkingSamplesMade / size)
        tmp.numberOfQueriesRequested = sqrt(tmp.numberOfQueriesRequested / size)

        return tmp
    }

}
