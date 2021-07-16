package lupos.simulator_iot

import lupos.simulator_iot.config.QuerySender
import lupos.simulator_iot.models.db.SemanticData
import lupos.simulator_iot.utils.FilePaths
import kotlin.test.Test

class IntegrationTest {

    companion object {
        private const val prefix = "${FilePaths.testResource}/integrationTest"
    }

    @Test
    fun twoSensorOneDB() {
        Evaluation().simulate("$prefix/twoSensorOneDB.json")
    }

    @Test
    fun twoDBWithOneSensor() {
        Evaluation().simulate("$prefix/twoDBWithOneSensor.json")
    }

    @Test
    fun sensorFromStarSendOverMeshWithDB() {
        Evaluation().simulate("$prefix/sensorFromStarSendOverMeshWithDB.json")
    }

    @Test
    fun databasesAsStarRoots() {
        Evaluation().simulate("$prefix/databasesAsStarRoots.json")
    }

    @Test
    fun campusCentralCaseWithoutQuery() {
        Evaluation().simulate("$prefix/campusCentralCaseWithoutQuery.json")
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
        val areaRDF = "<http://parkingArea/6>"
        val query = SemanticData.getAllSpacesOfParkingArea(areaRDF)
        centralCaseWithQuery(query)
    }

    @Test
    fun campusCentralCase_getSampleNumberOfSensor() {
        val sensorRDF = "<http://sensor/2/162>"
        val query = SemanticData.getSampleNumberOfSensor(sensorRDF)
        centralCaseWithQuery(query)
    }

    @Test
    fun campusCentralCase_getLastSampleOfSensor() {
        val sensorRDF = "<http://sensor/2/162>"
        val query = SemanticData.getLastSampleOfSensor(sensorRDF)
        centralCaseWithQuery(query)
    }

    @Test
    fun campusCentralCase_getLastResultsOfEachSensorInArea() {
        val areaRDF = "<http://parkingArea/9>"
        val query = SemanticData.getLastResultsOfEachSensorInArea(areaRDF)
        centralCaseWithQuery(query)
    }

    @Test
    fun campusCentralCase_getLastResultsOfEachSensorInManyAreas() {
        val areasRDF = "<http://parkingArea/9>, <http://parkingArea/4>"
        val query = SemanticData.getLastResultsOfEachSensorInManyAreas(areasRDF)
        centralCaseWithQuery(query)
    }

    @Test
    fun campusCentralCase_getNumberOfCurrentlyFreeSpacesInArea() {
        val areaRDF = "<http://parkingArea/4>"
        val query = SemanticData.getNumberOfCurrentlyFreeSpacesInArea(areaRDF)
        centralCaseWithQuery(query)
    }

    @Test
    fun campusDistributedCase_getAllTriples() {
        val query = SemanticData.getAllTriples()
        distributedCaseWithQuery(query)
    }

    @Test
    fun campusDistributedCase_getNumberOfParkingAreas() {
        val query = SemanticData.getNumberOfParkingAreas()
        distributedCaseWithQuery(query)
    }

    @Test
    fun campusDistributedCase_getAllParkingAreas() {
        val query = SemanticData.getAllParkingAreas()
        distributedCaseWithQuery(query)
    }

    @Test
    fun campusDistributedCase_getAllSpacesOfParkingArea() {
        val areaRDF = "<http://parkingArea/6>"
        val query = SemanticData.getAllSpacesOfParkingArea(areaRDF)
        distributedCaseWithQuery(query)
    }

    @Test
    fun campusDistributedCase_getSampleNumberOfSensor() {
        val sensorRDF = "<http://sensor/2/162>"
        val query = SemanticData.getSampleNumberOfSensor(sensorRDF)
        distributedCaseWithQuery(query)
    }

    @Test
    fun campusDistributedCase_getLastSampleOfSensor() {
        val sensorRDF = "<http://sensor/2/162>"
        val query = SemanticData.getLastSampleOfSensor(sensorRDF)
        distributedCaseWithQuery(query)
    }

    @Test
    fun campusDistributedCase_getLastResultsOfEachSensorInArea() {
        val areaRDF = "<http://parkingArea/9>"
        val query = SemanticData.getLastResultsOfEachSensorInArea(areaRDF)
        distributedCaseWithQuery(query)
    }

    @Test
    fun campusDistributedCase_getLastResultsOfEachSensorInManyAreas() {
        val areasRDF = "<http://parkingArea/9>, <http://parkingArea/4>"
        val query = SemanticData.getLastResultsOfEachSensorInManyAreas(areasRDF)
        distributedCaseWithQuery(query)
    }

    @Test
    fun campusDistributedCase_getNumberOfCurrentlyFreeSpacesInArea() {
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

        val simRun = SimulationRun()
        val json = simRun.parseConfigFile(configFile)
        json.querySender.add(querySender)
        val config = simRun.parseJsonObjects(json)
        simRun.startSimulation(config)
    }

    @Test
    fun campusDistributedCaseWithoutQuery() {
        Evaluation().simulate("$prefix/campusDistributedCaseWithoutQuery.json")
    }
}
