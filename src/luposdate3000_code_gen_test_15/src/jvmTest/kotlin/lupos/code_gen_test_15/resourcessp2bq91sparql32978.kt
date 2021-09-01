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
package lupos.code_gen_test_15
import lupos.shared.inline.File

public class resourcessp2bq91sparql32978 {
    internal val inputData = arrayOf(
        File("src/jvmTest/resources/resourcessp2bq91sparql32978.input").readAsString(),
    )
    internal val inputGraph = arrayOf(
        "",
    )
    internal val inputType = arrayOf(
        ".n3",
    )
    internal val targetData = File("src/jvmTest/resources/resourcessp2bq91sparql32978.output").readAsString()
    internal val targetType = ".srx"
    internal val query = "PREFIX rdf:  <http://www.w3.org/1999/02/22-rdf-syntax-ns#> \n" +
        "PREFIX foaf: <http://xmlns.com/foaf/0.1/> \n" +
        "SELECT ?predicate \n" +
        "WHERE { \n" +
        "  { \n" +
        "    ?person rdf:type foaf:Person . \n" +
        "    ?subject ?predicate ?person \n" +
        "  } UNION { \n" +
        "    ?person rdf:type foaf:Person . \n" +
        "    ?person ?predicate ?object \n" +
        "  } \n" +
        "} \n" +
        ""
}
