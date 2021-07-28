package lupos.simulator_iot.queryproc

import lupos.simulator_iot.models.sensor.ParkingSample

internal object SemanticData {

    internal fun get_SHACL_OntolotgyString(): String {
        return "" +
            "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n" +
            "PREFIX sh: <http://www.w3.org/ns/shacl#>\n" +
            "PREFIX parking: <https://github.com/luposdate3000/parking#>\n" +
            "_:b0 a sh:NodeShape.\n" +
            "_:b0 sh:targetClass parking:Observation.\n" +
            "_:b0 sh:property _:b1.\n" +
            "_:b0 sh:property _:b2.\n" +
            "_:b0 sh:property _:b3.\n" +
            "_:b0 sh:property _:b4.\n" +

            "_:b1 sh:path parking:area.\n" +
            "_:b1 sh:datatype xsd:integer.\n" +

            "_:b2 sh:path parking:spotInArea.\n" +
            "_:b2 sh:datatype xsd:integer.\n" +

            "_:b3 sh:path parking:isOccupied.\n" +
            "_:b3 sh:datatype xsd:boolean.\n" +

            "_:b4 sh:path parking:resultTime.\n" +
            "_:b4 sh:datatype xsd:dateTime.\n" +

            "\n"
    }

    internal fun getInsertQueryString(s: ParkingSample): String {
        return "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n" +
            "PREFIX parking: <https://github.com/luposdate3000/parking#>\n" +
            "\n" +
            "INSERT DATA {\n" +
            "  _:b0 a parking:Observation;\n" +
            "  parking:area \"${s.area}\"^^xsd:integer ;" +
            "  parking:spotInArea \"${s.sensorID}\"^^xsd:integer ;" +
            "  parking:isOccupied \"${s.isOccupied}\"^^xsd:boolean ;" +
            "  parking:resultTime \"${s.sampleTime}\"^^xsd:dateTime ." +
            "}\n"
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
            "  ?s parking:area ?x.\n" +
            "}"
    }

    internal fun getAllParkingAreas(): String {
        return "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
            "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n" +
            "PREFIX parking: <https://github.com/luposdate3000/parking#>\n" +
            "\n" +
            "select distinct ?x\n" +
            "where {\n" +
            "  ?b a parking:Observation;\n" +
            "  parking:area ?x.\n" +
            "}"
    }

    internal fun getAllSpacesOfParkingArea(area: Int): String {
        return "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
            "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n" +
            "PREFIX parking: <https://github.com/luposdate3000/parking#>\n" +
            "\n" +
            "select  (count(distinct ?x) as ?count)\n" +
            "where {\n" +
            "  ?b a parking:Observation;\n" +
            "  parking:area $area;\n" +
            "  parking:spotInArea ?x.\n" +
            "}"
    }

    internal fun getSampleNumberOfSensor(area: Int, spot: Int): String {
        return "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
            "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n" +
            "PREFIX parking: <https://github.com/luposdate3000/parking#>\n" +
            "\n" +
            "select  (count(?b) as ?count)\n" +
            "where {\n" +
            "  ?b a parking:Observation;\n" +
            "  parking:area $area;\n" +
            "  parking:spotInArea $spot.\n" +
            "}"
    }

    internal fun getLastSampleOfSensor(area: Int, spot: Int): String {
        return "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
            "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n" +
            "PREFIX parking: <https://github.com/luposdate3000/parking#>\n" +
            "\n" +
            "select   (max(?d) AS ?latestDate)\n" +
            "where {\n" +
            "  ?b a parking:Observation;\n" +
            "  parking:area $area;\n" +
            "  parking:spotInArea $spot;\n" +
            "  parking:resultTime ?d.\n" +
            "}"
    }

    internal fun getLastResultsOfEachSensorInArea(area: Int): String {
        return "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
            "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n" +
            "PREFIX parking: <https://github.com/luposdate3000/parking#>\n" +
            "\n" +
            "SELECT ?spot ?isOccupied ?lastObservedAt\n" +
            "WHERE {\n" +
            "  ?o a parking:Observation;\n" +
            "  parking:spotInArea ?spot;\n" +
            "  parking:area $area;\n" +
            "  parking:isOccupied ?isOccupied;\n" +
            "  parking:resultTime ?lastObservedAt.\n" +
            "  {\n" +
            "    SELECT(MAX(?d) AS ?lastObservedAt) ?spot WHERE{\n" +
            "      ?o2 a parking:Observation;\n" +
            "      parking:spotInArea ?spot;\n" +
            "      parking:area $area;\n" +
            "      parking:resultTime ?d.\n" +
            "    }\n" +
            "    GROUP BY ?spot\n" +
            "  }\n" +
            "}"
    }

    internal fun getLastResultsOfEachSensorInManyAreas(areas: Set<Int>): String {
        return "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
            "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n" +
            "PREFIX parking: <https://github.com/luposdate3000/parking#>\n" +
            "\n" +
            "SELECT ?area ?spot ?isOccupied ?lastObservedAt\n" +
            "WHERE {\n" +
            "  ?o a parking:Observation;\n" +
            "  parking:area ?area;\n" +
            "  parking:spotInArea ?spot;\n" +
            "  parking:isOccupied ?isOccupied;\n" +
            "  parking:resultTime ?lastObservedAt.\n" +
            "  {\n" +
            "    SELECT(MAX(?d) AS ?lastObservedAt) ?area ?spot ?f WHERE{\n" +
            "      ?o2 a parking:Observation;\n" +
            "      parking:area ?area;\n" +
            "      parking:spotInArea ?spot;\n" +
            "      parking:resultTime ?d.\n" +
            "      FILTER (?area IN (${areas.joinToString()}))\n" +
            "    }\n" +
            "    GROUP BY ?area ?spot\n" +
            "  }\n" +
            "}"
    }

    internal fun getNumberOfCurrentlyFreeSpacesInArea(area: Int): String {
        return "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
            "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n" +
            "PREFIX parking: <https://github.com/luposdate3000/parking#>\n" +
            "\n" +
            "SELECT (count(?spot) as ?numberOfFreeSpaces)\n" +
            "WHERE {\n" +
            "  ?o a parking:Observation;\n" +
            "  parking:spotInArea ?spot;\n" +
            "  parking:area $area;\n" +
            "  parking:isOccupied \"false\"^^xsd:boolean;\n" +
            "  parking:resultTime ?lastObservedAt.\n" +
            "  {\n" +
            "    SELECT(MAX(?d) AS ?lastObservedAt) ?spot WHERE{\n" +
            "      ?o2 a parking:Observation;\n" +
            "      parking:spotInArea ?spot;\n" +
            "      parking:area $area;\n" +
            "      parking:resultTime ?d.\n" +
            "    }\n" +
            "    GROUP BY ?spot\n" +
            "  }\n" +
            "}"
    }
}
