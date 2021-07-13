package lupos.simulator_iot
import lupos.simulator_iot.config.JsonObjects
import kotlin.test.Test
import kotlin.test.assertEquals

internal class MultipleSimulationRunsTest {

    companion object {
        private const val prefix = "${FilePaths.testResource}/multipleSimulationRunsTest"
    }


    @Test
    fun runMultipleStarNetworkSimulations() {
        val callback = object: IConfigManipulator {
            override fun manipulateJsonObjects(jsonObjects: JsonObjects) {
                jsonObjects.randomStarNetwork[0].number = 4
            }
        }

        val runs = MultipleSimulationRuns(
            configFileName = "$prefix/runMultipleStarNetworkSimulations.json",
            numberOfRepetitions = 5,
            callback = callback,
            printer = MeasurementPrinter())

        runs.startSimulationRuns()

    }
}
