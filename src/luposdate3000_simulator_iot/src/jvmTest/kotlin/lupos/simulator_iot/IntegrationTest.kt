package lupos.simulator_iot

import lupos.simulator_iot.config.Configuration
import lupos.simulator_iot.config.RandomStarNetwork
import lupos.simulator_iot.geo.GeoLocation
import lupos.simulator_iot.sensor.ParkingSensor
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertSame
import kotlin.test.assertTrue

class IntegrationTest{
@Test
fun test1(){
IoTSimulation().simulate("${FilePaths.jvmResource}/Exception_2Sensors1Database.json")
}
@Test
fun test2(){ 
IoTSimulation().simulate("${FilePaths.jvmResource}/anotherException_2Sensor1Database.json")
}
@Test
fun test3(){ 
 IoTSimulation().simulate("${FilePaths.jvmResource}/Exception_2DBwith1Sensor.json")
}
@Test
fun test4(){ 
IoTSimulation().simulate("${FilePaths.jvmResource}/star.json")
}

}
