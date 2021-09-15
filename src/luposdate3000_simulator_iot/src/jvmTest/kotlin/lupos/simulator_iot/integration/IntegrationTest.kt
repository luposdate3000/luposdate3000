/*
 * This file is part of the Luposdate3000 distribution (https://github.com/luposdate3000/luposdate3000).
 * Copyright (c) 2020-2021, Institute of Information Systems (Benjamin Warnke and contributors of LUPOSDATE3000), University of Luebeck
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, version 3.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package lupos.simulator_iot.integration
import lupos.simulator_db.luposdate3000.Package_Luposdate3000_Abstract
import lupos.simulator_iot.Evaluation
import lupos.simulator_iot.SimulationRun
import kotlin.test.Test

class IntegrationTest {

    companion object {
        private const val prefix = "src/jvmTest/resources/integrationTest"
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
        val query = getAllTriples()
        centralCaseWithQuery(query)
    }

    @Test
    fun campusCentralCase_getNumberOfParkingAreas() {
        val query = getNumberOfParkingAreas()
        centralCaseWithQuery(query)
    }

    @Test
    fun campusCentralCase_getAllParkingAreas() {
        val query = getAllParkingAreas()
        centralCaseWithQuery(query)
    }

    @Test
    fun campusCentralCase_getAllSpacesOfParkingArea() {
        val query = getAllSpacesOfParkingArea(6)
        centralCaseWithQuery(query)
    }

    @Test
    fun campusCentralCase_getSampleNumberOfSensor() {
        val query = getSampleNumberOfSensor(2, 162)
        centralCaseWithQuery(query)
    }

    @Test
    fun campusCentralCase_getLastSampleOfSensor() {
        val query = getLastSampleOfSensor(2, 162)
        centralCaseWithQuery(query)
    }

    @Test
    fun campusCentralCase_getLastResultsOfEachSensorInArea() {
        val query = getLastResultsOfEachSensorInArea(9)
        centralCaseWithQuery(query)
    }

    @Test
    fun campusCentralCase_getLastResultsOfEachSensorInManyAreas() {
        val query = getLastResultsOfEachSensorInManyAreas(setOf(9, 4))
        centralCaseWithQuery(query)
    }

    @Test
    fun campusCentralCase_getNumberOfCurrentlyFreeSpacesInArea() {
        val query = getNumberOfCurrentlyFreeSpacesInArea(4)
        centralCaseWithQuery(query)
    }

    @Test
    fun campusDistributedCase_getAllTriples() {
        val query = getAllTriples()
        distributedCaseWithQuery(query)
    }

    @Test
    fun campusDistributedCase_getNumberOfParkingAreas() {
        val query = getNumberOfParkingAreas()
        distributedCaseWithQuery(query)
    }

    @Test
    fun campusDistributedCase_getAllParkingAreas() {
        val query = getAllParkingAreas()
        distributedCaseWithQuery(query)
    }

    @Test
    fun campusDistributedCase_getAllSpacesOfParkingArea() {
        val query = getAllSpacesOfParkingArea(6)
        distributedCaseWithQuery(query)
    }

    @Test
    fun campusDistributedCase_getSampleNumberOfSensor() {
        val query = getSampleNumberOfSensor(2, 162)
        distributedCaseWithQuery(query)
    }

    @Test
    fun campusDistributedCase_getLastSampleOfSensor() {
        val query = getLastSampleOfSensor(2, 162)
        distributedCaseWithQuery(query)
    }

    @Test
    fun campusDistributedCase_getLastResultsOfEachSensorInArea() {
        val query = getLastResultsOfEachSensorInArea(9)
        distributedCaseWithQuery(query)
    }

    @Test
    fun campusDistributedCase_getLastResultsOfEachSensorInManyAreas() {
        val query = getLastResultsOfEachSensorInManyAreas(setOf(9, 4))
        distributedCaseWithQuery(query)
    }

    @Test
    fun campusDistributedCase_getNumberOfCurrentlyFreeSpacesInArea() {
        val query = getNumberOfCurrentlyFreeSpacesInArea(4)
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
        val config = simRun.parseConfig(configFile, false)
val ontologyQuery=StringBuilder()
        ontologyQuery.appendLine("PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>" )
        ontologyQuery.appendLine("PREFIX sh: <http://www.w3.org/shacl#>" )
        ontologyQuery.appendLine("PREFIX parking: <https://github.com/luposdate3000/parking#>" )
        ontologyQuery.appendLine("_:b0 a sh:NodeShape ." )
        ontologyQuery.appendLine("_:b0 sh:targetClass parking:Observation ." )
        ontologyQuery.appendLine("_:b0 sh:property _:b1 ." )
        ontologyQuery.appendLine("_:b0 sh:property _:b2 ." )
        ontologyQuery.appendLine("_:b0 sh:property _:b3 ." )
        ontologyQuery.appendLine("_:b0 sh:property _:b4 ." )
        ontologyQuery.appendLine("_:b1 sh:path parking:area ." )
        ontologyQuery.appendLine("_:b1 sh:datatype xsd:integer ." )
        ontologyQuery.appendLine("_:b2 sh:path parking:spotInArea ." )
        ontologyQuery.appendLine("_:b2 sh:datatype xsd:integer ." )
        ontologyQuery.appendLine("_:b3 sh:path parking:isOccupied ." )
        ontologyQuery.appendLine("_:b3 sh:datatype xsd:boolean ." )
        ontologyQuery.appendLine("_:b4 sh:path parking:resultTime ." )
        ontologyQuery.appendLine("_:b4 sh:datatype xsd:dateTime ." )
        config.addQuerySender(
            60,
            1,
            1,
            Package_Luposdate3000_Abstract(-1, "/shacl/ontology/import", mapOf("data" to ontologyQuery.toString())),
        )
        config.addQuerySender(
            600,
            1,
            1,
            queryString
        )
        simRun.startSimulation(config)
    }

    @Test
    fun campusDistributedCaseWithoutQuery() {
        Evaluation().simulate("$prefix/campusDistributedCaseWithoutQuery.json")
    }

    @Test
    fun campusDistributedCase_getAllSpacesOfParkingArea_modified_for_db() {
        val query = getAllSpacesOfParkingArea(1)
        campusWithQuery("$prefix/campusDistributedCaseWithoutQueryModifiedForDB.json", query)
    }


    internal fun getAllTriples(): String {
        return "SELECT ?s ?p ?o WHERE { ?s ?p ?o. }"
    }

    internal fun getNumberOfParkingAreas(): String {
        return "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
            "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n" +
            "PREFIX parking: <https://github.com/luposdate3000/parking#>\n" +
            "\n" +
            "select (count(distinct ?x) as ?count)\n" +
            "where {\n" +
            " ?s parking:area ?x .\n" +
            "}"
    }

    internal fun getAllParkingAreas(): String {
        return "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
            "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n" +
            "PREFIX parking: <https://github.com/luposdate3000/parking#>\n" +
            "\n" +
            "select distinct ?x\n" +
            "where {\n" +
            " ?b a parking:Observation ;\n" +
            " parking:area ?x .\n" +
            "}"
    }

    internal fun getAllSpacesOfParkingArea(area: Int): String {
        return "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
            "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n" +
            "PREFIX parking: <https://github.com/luposdate3000/parking#>\n" +
            "\n" +
            "select (count(distinct ?x) as ?count)\n" +
            "where {\n" +
            " ?b a parking:Observation ;\n" +
            " parking:area $area ;\n" +
            " parking:spotInArea ?x .\n" +
            "}"
    }

    internal fun getSampleNumberOfSensor(area: Int, spot: Int): String {
        return "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
            "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n" +
            "PREFIX parking: <https://github.com/luposdate3000/parking#>\n" +
            "\n" +
            "select (count(?b) as ?count)\n" +
            "where {\n" +
            " ?b a parking:Observation ;\n" +
            " parking:area $area ;\n" +
            " parking:spotInArea $spot .\n" +
            "}"
    }

    internal fun getLastSampleOfSensor(area: Int, spot: Int): String {
        return "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
            "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n" +
            "PREFIX parking: <https://github.com/luposdate3000/parking#>\n" +
            "\n" +
            "select (max(?d) AS ?latestDate)\n" +
            "where {\n" +
            " ?b a parking:Observation ;\n" +
            " parking:area $area ;\n" +
            " parking:spotInArea $spot ;\n" +
            " parking:resultTime ?d .\n" +
            "}"
    }

    internal fun getLastResultsOfEachSensorInArea(area: Int): String {
        return "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
            "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n" +
            "PREFIX parking: <https://github.com/luposdate3000/parking#>\n" +
            "\n" +
            "SELECT ?spot ?isOccupied ?lastObservedAt\n" +
            "WHERE {\n" +
            " ?o a parking:Observation ;\n" +
            " parking:spotInArea ?spot ;\n" +
            " parking:area $area ;\n" +
            " parking:isOccupied ?isOccupied ;\n" +
            " parking:resultTime ?lastObservedAt .\n" +
            " {\n" +
            " SELECT(MAX(?d) AS ?lastObservedAt) ?spot WHERE{\n" +
            "  ?o2 a parking:Observation ;\n" +
            "  parking:spotInArea ?spot ;\n" +
            "  parking:area $area ;\n" +
            "  parking:resultTime ?d .\n" +
            " }\n" +
            " GROUP BY ?spot\n" +
            " }\n" +
            "}"
    }

    internal fun getLastResultsOfEachSensorInManyAreas(areas: Set<Int>): String {
        return "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
            "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n" +
            "PREFIX parking: <https://github.com/luposdate3000/parking#>\n" +
            "\n" +
            "SELECT ?area ?spot ?isOccupied ?lastObservedAt\n" +
            "WHERE {\n" +
            " ?o a parking:Observation ;\n" +
            " parking:area ?area ;\n" +
            " parking:spotInArea ?spot ;\n" +
            " parking:isOccupied ?isOccupied ;\n" +
            " parking:resultTime ?lastObservedAt .\n" +
            " {\n" +
            " SELECT(MAX(?d) AS ?lastObservedAt) ?area ?spot WHERE{\n" +
            "  ?o2 a parking:Observation ;\n" +
            "  parking:area ?area ;\n" +
            "  parking:spotInArea ?spot ;\n" +
            "  parking:resultTime ?d .\n" +
            "  FILTER (?area IN (${areas.joinToString()}))\n" +
            " }\n" +
            " GROUP BY ?area ?spot\n" +
            " }\n" +
            "}"
    }

    internal fun getNumberOfCurrentlyFreeSpacesInArea(area: Int): String {
        return "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
            "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n" +
            "PREFIX parking: <https://github.com/luposdate3000/parking#>\n" +
            "\n" +
            "SELECT (count(?spot) as ?numberOfFreeSpaces)\n" +
            "WHERE {\n" +
            " ?o a parking:Observation ;\n" +
            " parking:spotInArea ?spot ;\n" +
            " parking:area $area ;\n" +
            " parking:isOccupied \"false\"^^xsd:boolean ;\n" +
            " parking:resultTime ?lastObservedAt .\n" +
            " {\n" +
            " SELECT(MAX(?d) AS ?lastObservedAt) ?spot WHERE{\n" +
            "  ?o2 a parking:Observation ;\n" +
            "  parking:spotInArea ?spot ;\n" +
            "  parking:area $area ;\n" +
            "  parking:resultTime ?d .\n" +
            " }\n" +
            " GROUP BY ?spot\n" +
            " }\n" +
            "}"
    }
}
