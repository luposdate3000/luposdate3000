package lupos.simulator_iot.queryproc

import lupos.simulator_iot.models.sensor.ParkingSample

internal object SemanticData {

    internal fun getInsertQueryString(s: ParkingSample): String {
        return "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
            "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n" +
            "PREFIX sosa: <http://www.w3.org/ns/sosa/>\n" +
            "\n" +
            "INSERT DATA {\n" +
            "  <http://observation/${s.sampleID}/sensor/${s.area}/${s.sensorID}> rdf:type sosa:Observation;\n" +
            "  sosa:hasFeatureOfInterest <http://parkingArea/${s.area}>;\n" +
            "  sosa:observedProperty <http://parkingSpace/${s.parkingSpotID}>;\n" +
            "  sosa:madeBySensor <http://sensor/${s.area}/${s.sensorID}>;\n" +
            "  sosa:hasSimpleResult \"${s.isOccupied}\"^^xsd:boolean;\n" +
            "  sosa:resultTime \"${s.sampleTime}\"^^xsd:dateTime.\n" +
            "}\n"
    }

    internal fun getAllTriples(): String {
        return "SELECT ?s ?p ?o WHERE { ?s ?p ?o. }"
    }

    internal fun getNumberOfParkingAreas(): String {
        return "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
            "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n" +
            "PREFIX sosa: <http://www.w3.org/ns/sosa/>\n" +
            "\n" +
            "select (count(distinct ?x) as ?count) \n" +
            "where {\n" +
            " ?s sosa:hasFeatureOfInterest ?x\n" +
            "}"
    }

    internal fun getAllParkingAreas(): String {
        return "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
            "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n" +
            "PREFIX sosa: <http://www.w3.org/ns/sosa/>\n" +
            "\n" +
            "select distinct ?x \n" +
            "where {\n" +
            " ?s sosa:hasFeatureOfInterest ?x\n" +
            "}"
    }

    internal fun getAllSpacesOfParkingArea(areaRDF: String): String {
        return "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
            "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n" +
            "PREFIX sosa: <http://www.w3.org/ns/sosa/>\n" +
            "\n" +
            "select  (count(distinct ?x) as ?count) \n" +
            "where {\n" +
            " ?b rdf:type sosa:Observation.\n" +
            " ?b sosa:hasFeatureOfInterest  $areaRDF.\n" +
            " ?b sosa:madeBySensor ?x.   \n" +
            "}"
    }

    internal fun getSampleNumberOfSensor(sensorRDF: String): String {
        return "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
            "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n" +
            "PREFIX sosa: <http://www.w3.org/ns/sosa/>\n" +
            "\n" +
            "select  (count(?b) as ?count) \n" +
            "where {\n" +
            " ?b rdf:type sosa:Observation.\n" +
            " ?b sosa:madeBySensor $sensorRDF .   \n" +
            "}"
    }

    internal fun getLastSampleOfSensor(sensorRDF: String): String {
        return "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
            "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n" +
            "PREFIX sosa: <http://www.w3.org/ns/sosa/>\n" +
            "\n" +
            "select   (max(?d) AS ?latestDate)\n" +
            "where {\n" +
            " ?b sosa:madeBySensor $sensorRDF .\n" +
            " ?b sosa:resultTime ?d .\n" +
            "}"
    }

    internal fun getLastResultsOfEachSensorInArea(areaRDF: String): String {
        return "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
            "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n" +
            "PREFIX sosa: <http://www.w3.org/ns/sosa/>\n" +
            "\n" +
            "SELECT ?sensor ?isOccupied ?lastObservedAt\n" +
            "WHERE {\n" +
            " ?o a sosa:Observation ;\n" +
            " sosa:madeBySensor ?sensor;\n" +
            " sosa:hasFeatureOfInterest $areaRDF;\n" +
            " sosa:hasSimpleResult ?isOccupied;\n" +
            " sosa:resultTime ?lastObservedAt .\n" +
            " {\n" +
            "   SELECT(MAX(?d) AS ?lastObservedAt) ?sensor WHERE{\n" +
            "     ?o2 a sosa:Observation ;\n" +
            "     sosa:madeBySensor ?sensor;\n" +
            "     sosa:hasFeatureOfInterest $areaRDF;\n" +
            "     sosa:resultTime ?d .\n" +
            "   }\n" +
            "   GROUP BY ?sensor\n" +
            " }\n" +
            "}"
    }

    internal fun getLastResultsOfEachSensorInManyAreas(commaSeparatedAreasRDF: String): String {
        return "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
            "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n" +
            "PREFIX sosa: <http://www.w3.org/ns/sosa/>\n" +
            "\n" +
            "SELECT ?sensor ?isOccupied ?lastObservedAt\n" +
            "WHERE {\n" +
            " ?o a sosa:Observation ;\n" +
            " sosa:madeBySensor ?sensor;\n" +
            " sosa:hasFeatureOfInterest ?f;\n" +
            " sosa:hasSimpleResult ?isOccupied;\n" +
            " sosa:resultTime ?lastObservedAt .\n" +
            " {\n" +
            "   SELECT(MAX(?d) AS ?lastObservedAt) ?sensor ?f WHERE{\n" +
            "     ?o2 a sosa:Observation ;\n" +
            "     sosa:madeBySensor ?sensor;\n" +
            "     sosa:hasFeatureOfInterest ?f;\n" +
            "     sosa:resultTime ?d .\n" +
            "     FILTER (?f IN ($commaSeparatedAreasRDF))\n" +
            "   }\n" +
            "   GROUP BY ?sensor\n" +
            " }\n" +
            "}"
    }

    internal fun getNumberOfCurrentlyFreeSpacesInArea(areaRDF: String): String {
        return "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
            "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n" +
            "PREFIX sosa: <http://www.w3.org/ns/sosa/>\n" +
            "\n" +
            "SELECT (count(?sensor) as ?numberOfFreeSpaces)\n" +
            "WHERE {\n" +
            " ?o a sosa:Observation ;\n" +
            " sosa:madeBySensor ?sensor;\n" +
            " sosa:hasFeatureOfInterest $areaRDF;\n" +
            " sosa:hasSimpleResult \"false\"^^xsd:boolean;\n" +
            " sosa:resultTime ?lastObservedAt .\n" +
            " {\n" +
            "   SELECT(MAX(?d) AS ?lastObservedAt) ?sensor WHERE{\n" +
            "     ?o2 a sosa:Observation ;\n" +
            "     sosa:madeBySensor ?sensor;\n" +
            "     sosa:hasFeatureOfInterest $areaRDF;\n" +
            "     sosa:resultTime ?d .\n" +
            "   }\n" +
            "   GROUP BY ?sensor\n" +
            " }\n" +
            "}"
    }
}
