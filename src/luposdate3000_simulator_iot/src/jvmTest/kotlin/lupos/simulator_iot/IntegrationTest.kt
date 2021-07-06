package lupos.simulator_iot

import kotlin.test.Ignore
import kotlin.test.Test

class IntegrationTest {
    @Test
    fun test1() {
        IoTSimulation().simulate("${FilePaths.testResource}/Exception_2Sensors1Database.json")
    }

    @Test
    fun test2() {
        IoTSimulation().simulate("${FilePaths.testResource}/anotherException_2Sensor1Database.json")
    }

    @Ignore
    @Test
    fun test3() {
        IoTSimulation().simulate("${FilePaths.testResource}/Exception_2DBwith1Sensor.json")
    }

    @Test
    fun test4() {
        IoTSimulation().simulate("${FilePaths.testResource}/star.json")
    }
}
