package lupos.simulator_iot
import kotlin.test.Ignore
import kotlin.test.Test

class IntegrationTest {

    companion object {
        private const val prefix = "${FilePaths.testResource}/integrationTest"
    }

    @Test
    fun twoSensorOneDB() {
        IoTSimulation().simulate("$prefix/twoSensorOneDB.json")
    }

    @Test
    fun twoDBWithOneSensor() {
        IoTSimulation().simulate("$prefix/twoDBWithOneSensor.json")
    }

    @Test
    fun sensorFromStarSendOverMeshWithDB() {
        IoTSimulation().simulate("$prefix/sensorFromStarSendOverMeshWithDB.json")
    }

    @Test
    fun databasesAsStarRoots() {
        IoTSimulation().simulate("$prefix/databasesAsStarRoots.json")
    }

    @Ignore // dieser Test is so nie für die Ausführung gedacht gewesen
    @Test
    fun test6() {
        IoTSimulation().simulate("${FilePaths.testResource}/autoIntegrationTest/test1.json")
    }
}
