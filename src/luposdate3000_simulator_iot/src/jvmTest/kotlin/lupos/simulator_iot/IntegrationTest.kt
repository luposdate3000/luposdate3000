package lupos.simulator_iot

import kotlin.test.Ignore
import kotlin.test.Test

class IntegrationTest {

    companion object {
        private const val prefix = "${FilePaths.testResource}/IntegrationTest"
    }

    @Test
    fun test1() {
        IoTSimulation().simulate("$prefix/Exception_2Sensors1Database.json")
    }

    @Test
    fun test2() {
        IoTSimulation().simulate("$prefix/anotherException_2Sensor1Database.json")
    }

    @Test
    fun test3() {
        IoTSimulation().simulate("$prefix/Exception_2DBwith1Sensor.json")
    }

    @Test
    fun test4() {
        IoTSimulation().simulate("$prefix/star.json")
    }

    @Ignore
    @Test
    fun test5() {
        IoTSimulation().simulate("$prefix/databasesAsStarRoots.json")
    }

    @Test
    fun databasesAsStarRoots() {
        IoTSimulation().simulate("$prefix/databasesAsStarRoots.json")
    }

    @Ignore
    @Test
    fun test6() {
        IoTSimulation().simulate("${FilePaths.testResource}/AutoIntegrationTest/test1.json")
    }
}
