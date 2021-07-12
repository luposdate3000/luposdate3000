package lupos.simulator_iot
import lupos.simulator_core.Simulation
import lupos.simulator_db.QueryPackage
import lupos.simulator_iot.config.Configuration
import lupos.simulator_iot.config.QuerySender
import lupos.simulator_iot.db.SemanticData
import lupos.simulator_iot.log.Logger
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

    @Test
    fun campusCentralCaseWithoutQuery() {
        IoTSimulation().simulate("$prefix/campusCentralCaseWithoutQuery.json")
    }

    @Test
    fun campusCentralCase_getAllTriples() {
        val query = SemanticData.getAllTriples()
        centralCaseWithQuery(query)
    }

    @Test
    fun campusCentralCase_getNumberOfParkingAreas() {
        val query = SemanticData.getNumberOfParkingAreas()
        centralCaseWithQuery(query)
    }

    @Test
    fun campusCentralCase_getAllParkingAreas() {
        val query = SemanticData.getAllParkingAreas()
        centralCaseWithQuery(query)
    }

    @Test
    fun campusCentralCase_getAllSpacesOfParkingArea() {
        // TODO Throws Exception
        val areaRDF = "<http://parkingArea/6>"
        val query = SemanticData.getAllSpacesOfParkingArea(areaRDF)
        centralCaseWithQuery(query)
    }

    @Test
    fun campusCentralCase_getSampleNumberOfSensor() {
        // TODO Throws Exception
        val sensorRDF = "<http://sensor/2/162>"
        val query = SemanticData.getSampleNumberOfSensor(sensorRDF)
        centralCaseWithQuery(query)
    }

    @Ignore
    @Test
    fun campusCentralCase_getLastSampleOfSensor() {
        // TODO Throws Exception
        val sensorRDF = "<http://sensor/2/162>"
        val query = SemanticData.getLastSampleOfSensor(sensorRDF)
        centralCaseWithQuery(query)
    }

    @Ignore
    @Test
    fun campusCentralCase_getLastResultsOfEachSensorInArea() {
        // TODO Throws Exception
        val areaRDF = "<http://parkingArea/9>"
        val query = SemanticData.getLastResultsOfEachSensorInArea(areaRDF)
        centralCaseWithQuery(query)
    }

    @Test
    fun campusCentralCase_getLastResultsOfEachSensorInManyAreas() {
        // TODO Throws Exception
        val areasRDF = "<http://parkingArea/9>, <http://parkingArea/4>"
        val query = SemanticData.getLastResultsOfEachSensorInManyAreas(areasRDF)
        centralCaseWithQuery(query)
    }

    @Ignore
    @Test
    fun campusCentralCase_getNumberOfCurrentlyFreeSpacesInArea() {
        // TODO Throws Exception
        val areaRDF = "<http://parkingArea/4>"
        val query = SemanticData.getNumberOfCurrentlyFreeSpacesInArea(areaRDF)
        centralCaseWithQuery(query)
    }

    @Ignore
    @Test
    fun campusDistributedCase_getAllTriples() {
        // TODO Throws Exception
        val query = SemanticData.getAllTriples()
        distributedCaseWithQuery(query)
    }

    @Ignore
    @Test
    fun campusDistributedCase_getNumberOfParkingAreas() {
        // TODO Throws Exception
        val query = SemanticData.getNumberOfParkingAreas()
        distributedCaseWithQuery(query)
    }

    @Ignore
    @Test
    fun campusDistributedCase_getAllParkingAreas() {
        // TODO Throws Exception
        val query = SemanticData.getAllParkingAreas()
        distributedCaseWithQuery(query)
    }

    @Ignore
    @Test
    fun campusDistributedCase_getAllSpacesOfParkingArea() {
        // TODO Throws Exception
        val areaRDF = "<http://parkingArea/6>"
        val query = SemanticData.getAllSpacesOfParkingArea(areaRDF)
        distributedCaseWithQuery(query)
    }

    @Ignore
    @Test
    fun campusDistributedCase_getSampleNumberOfSensor() {
        // TODO Throws Exception
        val sensorRDF = "<http://sensor/2/162>"
        val query = SemanticData.getSampleNumberOfSensor(sensorRDF)
        distributedCaseWithQuery(query)
    }

    @Ignore
    @Test
    fun campusDistributedCase_getLastSampleOfSensor() {
        // TODO Throws Exception
        val sensorRDF = "<http://sensor/2/162>"
        val query = SemanticData.getLastSampleOfSensor(sensorRDF)
        distributedCaseWithQuery(query)
    }

    @Ignore
    @Test
    fun campusDistributedCase_getLastResultsOfEachSensorInArea() {
        // TODO Throws Exception
        val areaRDF = "<http://parkingArea/9>"
        val query = SemanticData.getLastResultsOfEachSensorInArea(areaRDF)
        distributedCaseWithQuery(query)
    }

    @Ignore
    @Test
    fun campusDistributedCase_getLastResultsOfEachSensorInManyAreas() {
        // TODO Throws Exception
        val areasRDF = "<http://parkingArea/9>, <http://parkingArea/4>"
        val query = SemanticData.getLastResultsOfEachSensorInManyAreas(areasRDF)
        distributedCaseWithQuery(query)
    }

    @Ignore
    @Test
    fun campusDistributedCase_getNumberOfCurrentlyFreeSpacesInArea() {
        // TODO Throws Exception
        val areaRDF = "<http://parkingArea/4>"
        val query = SemanticData.getNumberOfCurrentlyFreeSpacesInArea(areaRDF)
        distributedCaseWithQuery(query)
    }

    private fun centralCaseWithQuery(queryString: String) {
        campusWithQuery("$prefix/campusCentralCaseWithoutQuery.json", queryString)
    }

    private fun distributedCaseWithQuery(queryString: String) {
        campusWithQuery("$prefix/campusDistributedCaseWithoutQuery.json", queryString)
    }

    private fun campusWithQuery(configFile: String, queryString: String) {
        val querySender = QuerySender(
            name = "Q1",
            sendRateInSeconds = 1,
            maxNumberOfQueries = 1,
            sendStartClockInSec = 10 * 60,
            query = queryString
        )
        val jsonObjects = Configuration.readJsonFile(configFile)
        jsonObjects.querySender.add(querySender)
        Configuration.parse(jsonObjects)
        val sim = Simulation(entities = Configuration.getEntities(), callback = Logger)
        sim.startSimulation()
    }

    @Test
    fun campusDistributedCaseWithoutQuery() {
        IoTSimulation().simulate("$prefix/campusDistributedCaseWithoutQuery.json")
    }

    @Ignore // TODO dieser Test is so nie für die Ausführung gedacht gewesen
    @Test
    fun test6() {
        Configuration.parse("${FilePaths.testResource}/autoIntegrationTest/test1.json")
        Configuration.querySenders[0].queryPck = QueryPackage(0, byteArrayOf()) // TODO insert your package here
        val sim = Simulation(entities = Configuration.getEntities(), callback = Logger)
        sim.startSimulation()
    }
}
