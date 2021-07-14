package lupos.simulator_iot
import lupos.simulator_iot.config.JsonObjects
import lupos.simulator_iot.measure.MeasurementPrinter
import lupos.simulator_iot.utils.FilePaths
import kotlin.test.Test

internal class MultipleSimulationRunsTest {

    companion object {
        private const val prefix = "${FilePaths.testResource}/multipleSimulationRunsTest"
    }


    @Test
    fun runMultipleStarNetworkSimulations() {
        val callback = object: ISimRunPreparation {
            override fun prepareJsonObjects(jsonObjects: JsonObjects) {
                jsonObjects.randomStarNetwork[0].number = 4
            }
        }
        val runs = MultipleSimulationRuns(
            configFileName = "$prefix/runMultipleStarNetworkSimulations.json",
            numberOfRepetitions = 5,
            callback = callback,
            printer = MeasurementPrinter()
        )
        runs.startSimulationRuns()
    }

    @Test
    fun test_1() {
        val callback = object: ISimRunPreparation {
            override fun prepareJsonObjects(jsonObjects: JsonObjects) {
            }
        }
        val runs = MultipleSimulationRuns(
            configFileName = "$prefix/meshToDODAG.json",
            numberOfRepetitions = 5,
            callback = callback,
            printer = MeasurementPrinter()
        )
        runs.startSimulationRuns()

    }


}
