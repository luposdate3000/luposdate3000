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
package lupos.code_gen_test_09
import lupos.shared.inline.File

public class resourcessp2bq12a1sparql21 {
    internal val inputData = arrayOf(
        File("src/jvmTest/resources/resourcessp2bq12a1sparql21.input").readAsString(),
    )
    internal val inputGraph = arrayOf(
        "",
    )
    internal val inputType = arrayOf(
        ".n3",
    )
    internal val targetData = File("src/jvmTest/resources/resourcessp2bq12a1sparql21.output").readAsString()
    internal val targetType = ".srx"
    internal val query = "PREFIX rdf:   <http://www.w3.org/1999/02/22-rdf-syntax-ns#> \n" +
        "PREFIX foaf:  <http://xmlns.com/foaf/0.1/> \n" +
        "PREFIX bench: <http://localhost/vocabulary/bench/> \n" +
        "PREFIX dc:    <http://purl.org/dc/elements/1.1/> \n" +
        "SELECT ?article ?person1 ?person2 ?name1 ?inproc { \n" +
        "  ?article rdf:type bench:Article . \n" +
        "  ?article dc:creator ?person1 . \n" +
        "  ?inproc  rdf:type bench:Inproceedings . \n" +
        "  ?inproc  dc:creator ?person2 . \n" +
        "  ?person1 foaf:name ?name1 . \n" +
        "  ?person2 foaf:name ?name2 . \n" +
        "  FILTER (?name1=?name2) \n" +
        "} \n" +
        ""
}
