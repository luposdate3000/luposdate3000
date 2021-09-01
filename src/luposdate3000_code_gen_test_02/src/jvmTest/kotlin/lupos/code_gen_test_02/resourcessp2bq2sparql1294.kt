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

public class resourcessp2bq2sparql1294 {
    internal val inputData = arrayOf(
        File("src/jvmTest/resources/resourcessp2bq2sparql1294.input").readAsString(),
    )
    internal val inputGraph = arrayOf(
        "",
    )
    internal val inputType = arrayOf(
        ".n3",
    )
    internal val targetData = File("src/jvmTest/resources/resourcessp2bq2sparql1294.output").readAsString()
    internal val targetType = ".srx"
    internal val query = "PREFIX rdf:     <http://www.w3.org/1999/02/22-rdf-syntax-ns#> \n" +
        "PREFIX rdfs:    <http://www.w3.org/2000/01/rdf-schema#> \n" +
        "PREFIX swrc:    <http://swrc.ontoware.org/ontology#> \n" +
        "PREFIX foaf:    <http://xmlns.com/foaf/0.1/> \n" +
        "PREFIX bench:   <http://localhost/vocabulary/bench/> \n" +
        "PREFIX dc:      <http://purl.org/dc/elements/1.1/> \n" +
        "PREFIX dcterms: <http://purl.org/dc/terms/> \n" +
        "SELECT ?inproc ?author ?booktitle ?title  \n" +
        "       ?proc ?ee ?page ?url ?yr ?abstract \n" +
        "WHERE { \n" +
        "  ?inproc rdf:type bench:Inproceedings . \n" +
        "  ?inproc dc:creator ?author . \n" +
        "  ?inproc bench:booktitle ?booktitle . \n" +
        "  ?inproc dc:title ?title . \n" +
        "  ?inproc dcterms:partOf ?proc . \n" +
        "  ?inproc rdfs:seeAlso ?ee . \n" +
        "  ?inproc swrc:pages ?page . \n" +
        "  ?inproc foaf:homepage ?url . \n" +
        "  ?inproc dcterms:issued ?yr . \n" +
        "  OPTIONAL { \n" +
        "    ?inproc bench:abstract ?abstract . \n" +
        "  } \n" +
        "} \n" +
        "ORDER BY ?yr \n" +
        ""
}
