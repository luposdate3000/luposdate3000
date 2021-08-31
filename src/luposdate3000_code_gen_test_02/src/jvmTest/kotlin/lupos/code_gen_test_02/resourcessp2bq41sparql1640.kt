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

public class resourcessp2bq41sparql1640 {
    internal val inputData = arrayOf(
        File("src/jvmTest/resources/resourcessp2bq41sparql1640.input").readAsString(),
    )
    internal val inputGraph = arrayOf(
        "",
    )
    internal val inputType = arrayOf(
        ".n3",
    )
    internal val targetData = File("src/jvmTest/resources/resourcessp2bq41sparql1640.output").readAsString()
    internal val targetType = ".srx"
    internal val query = "PREFIX rdf:     <http://www.w3.org/1999/02/22-rdf-syntax-ns#> \n" +
        "PREFIX bench:   <http://localhost/vocabulary/bench/> \n" +
        "PREFIX dc:      <http://purl.org/dc/elements/1.1/> \n" +
        "PREFIX dcterms: <http://purl.org/dc/terms/> \n" +
        "PREFIX foaf:    <http://xmlns.com/foaf/0.1/> \n" +
        "PREFIX swrc:    <http://swrc.ontoware.org/ontology#> \n" +
        "SELECT ?name1 ?name2  \n" +
        "WHERE { \n" +
        "  ?article1 rdf:type bench:Article . \n" +
        "  ?article2 rdf:type bench:Article . \n" +
        "  ?article1 dc:creator ?author1 . \n" +
        "  ?author1 foaf:name ?name1 . \n" +
        "  ?article2 dc:creator ?author2 . \n" +
        "  ?author2 foaf:name ?name2 . \n" +
        "  ?article1 swrc:journal ?journal . \n" +
        "  ?article2 swrc:journal ?journal . \n" +
        "  FILTER ( ?name1 < ?name2 ) \n" +
        "} \n" +
        ""
}
