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
        callback.perpareJsonObjects(json)
        val config = simRun.parseJsonObjects(json)
        simRun.startSimulation(config)
        measurements.add(simRun.measurement)
    }

    private fun evaluate() {
        val result = Measurement()
        // Berechne Durchschnitt und Abweichung..
        printer.printMeasurement(result)
    }

}
