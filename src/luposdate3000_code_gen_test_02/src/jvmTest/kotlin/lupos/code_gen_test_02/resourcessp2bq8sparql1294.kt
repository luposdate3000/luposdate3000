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
package lupos.code_gen_test_02
import lupos.shared.inline.File

public class resourcessp2bq8sparql1294 {
    internal val inputData = arrayOf(
        File("src/jvmTest/resources/resourcessp2bq8sparql1294.input").readAsString(),
    )
    internal val inputGraph = arrayOf(
        "",
    )
    internal val inputType = arrayOf(
        ".n3",
    )
    internal val targetData = File("src/jvmTest/resources/resourcessp2bq8sparql1294.output").readAsString()
    internal val targetType = ".srx"
    internal val query = "PREFIX xsd:  <http://www.w3.org/2001/XMLSchema#>  \n" +
        "PREFIX rdf:  <http://www.w3.org/1999/02/22-rdf-syntax-ns#> \n" +
        "PREFIX foaf: <http://xmlns.com/foaf/0.1/> \n" +
        "PREFIX dc:   <http://purl.org/dc/elements/1.1/> \n" +
        "SELECT DISTINCT ?name \n" +
        "WHERE { \n" +
        "  ?erdoes rdf:type foaf:Person . \n" +
        "  ?erdoes foaf:name \"Paul Erdoes\"^^xsd:string . \n" +
        "  { \n" +
        "    ?document dc:creator ?erdoes . \n" +
        "    ?document dc:creator ?author . \n" +
        "    ?document2 dc:creator ?author . \n" +
        "    ?document2 dc:creator ?author2 . \n" +
        "    ?author2 foaf:name ?name . \n" +
        "    FILTER (?author!=?erdoes && \n" +
        "            ?document2!=?document && \n" +
        "            ?author2!=?erdoes && \n" +
        "            ?author2!=?author) \n" +
        "  } UNION { \n" +
        "    ?document dc:creator ?erdoes. \n" +
        "    ?document dc:creator ?author. \n" +
        "    ?author foaf:name ?name . \n" +
        "    FILTER (?author!=?erdoes) \n" +
        "  } \n" +
        "} \n" +
        ""
}
