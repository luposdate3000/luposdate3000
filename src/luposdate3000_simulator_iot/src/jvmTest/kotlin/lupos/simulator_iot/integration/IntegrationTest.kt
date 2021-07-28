package lupos.simulator_iot.integration

import lupos.simulator_db.luposdate3000.MySimulatorAbstractPackage
import lupos.simulator_iot.Evaluation
import lupos.simulator_iot.SimulationRun
import lupos.simulator_iot.config.QuerySender
import lupos.simulator_iot.queryproc.SemanticData
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
        val query = SemanticData.getAllSpacesOfParkingArea(6)
        centralCaseWithQuery(query)
    }

    @Test
    fun campusCentralCase_getSampleNumberOfSensor() {
        val query = SemanticData.getSampleNumberOfSensor(2, 162)
        centralCaseWithQuery(query)
    }

    @Test
    fun campusCentralCase_getLastSampleOfSensor() {
        val query = SemanticData.getLastSampleOfSensor(2, 162)
        centralCaseWithQuery(query)
    }

    @Test
    fun campusCentralCase_getLastResultsOfEachSensorInArea() {
        val query = SemanticData.getLastResultsOfEachSensorInArea(9)
        centralCaseWithQuery(query)
    }

    @Test
    fun campusCentralCase_getLastResultsOfEachSensorInManyAreas() {
        val query = SemanticData.getLastResultsOfEachSensorInManyAreas(setOf(9, 4))
        centralCaseWithQuery(query)
    }

    @Test
    fun campusCentralCase_getNumberOfCurrentlyFreeSpacesInArea() {
        val query = SemanticData.getNumberOfCurrentlyFreeSpacesInArea(4)
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
        val query = SemanticData.getAllSpacesOfParkingArea(6)
        distributedCaseWithQuery(query)
    }

    @Test
    fun campusDistributedCase_getSampleNumberOfSensor() {
        val query = SemanticData.getSampleNumberOfSensor(2, 162)
        distributedCaseWithQuery(query)
    }

    @Test
    fun campusDistributedCase_getLastSampleOfSensor() {
        val query = SemanticData.getLastSampleOfSensor(2, 162)
        distributedCaseWithQuery(query)
    }

    @Test
    fun campusDistributedCase_getLastResultsOfEachSensorInArea() {
        val query = SemanticData.getLastResultsOfEachSensorInArea(9)
        distributedCaseWithQuery(query)
    }

    @Test
    fun campusDistributedCase_getLastResultsOfEachSensorInManyAreas() {
        val query = SemanticData.getLastResultsOfEachSensorInManyAreas(setOf(9, 4))
        distributedCaseWithQuery(query)
    }

    @Test
    fun campusDistributedCase_getNumberOfCurrentlyFreeSpacesInArea() {
        val query = SemanticData.getNumberOfCurrentlyFreeSpacesInArea(4)
        distributedCaseWithQuery(query)
    }

    private fun centralCaseWithQuery(queryString: String) {
        campusWithQuery("$prefix/campusCentralCaseWithoutQuery.json", queryString)
    }

    private fun distributedCaseWithQuery(queryString: String) {
        campusWithQuery("$prefix/campusDistributedCaseWithoutQuery.json", queryString)
    }

    private fun campusWithQuery(configFile: String, queryString: String) {
        val simRun = SimulationRun()
        val json = simRun.parseConfigFile(configFile)
        json.querySender.add(
            QuerySender(
                name = "Q1",
                sendRateInSeconds = 1,
                maxNumberOfQueries = 1,
                sendStartClockInSec = 10 * 60,
                query = queryString
            )
        )
        val config = simRun.parseJsonObjects(json)
        val ontologySender = lupos.simulator_iot.queryproc.QuerySender(
            simRun,
            "Ontology",
            1,
            1,
            60,
            config.devices.filter { it.hasDatabase() }.first(),
            "ONTOLOGY-QUERY-OVERRIDES-THE-PACKAGE"
        )
        ontologySender.queryPck = MySimulatorAbstractPackage(-1, "/shacl/ontology/import", mapOf("data" to SemanticData.get_SHACL_OntolotgyString()))
        config.querySenders.add(ontologySender)
        simRun.startSimulation(config)
    }

    @Test
    fun campusDistributedCaseWithoutQuery() {
        Evaluation().simulate("$prefix/campusDistributedCaseWithoutQuery.json")
    }

    @Test
    fun campusDistributedCase_getAllSpacesOfParkingArea_modified_for_db() {
        val query = SemanticData.getAllSpacesOfParkingArea(1)
        campusWithQuery("$prefix/campusDistributedCaseWithoutQueryModifiedForDB.json", query)
    }
}
