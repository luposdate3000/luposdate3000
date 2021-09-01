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
package lupos.code_gen_test_05
import lupos.shared.inline.File

public class sq13Subqueriesdontinjectbindings {
    internal val inputData = arrayOf(
        File("src/jvmTest/resources/sq13Subqueriesdontinjectbindings.input").readAsString(),
    )
    internal val inputGraph = arrayOf(
        "",
    )
    internal val inputType = arrayOf(
        ".ttl",
    )
    internal val targetData = File("src/jvmTest/resources/sq13Subqueriesdontinjectbindings.output").readAsString()
    internal val targetType = ".srx"
    internal val query = "# return labels of items for the first 2 orders \n" +
        "PREFIX : <http://www.example.org> \n" +
        "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> \n" +
        "SELECT ?L \n" +
        "WHERE { \n" +
        " ?O :hasItem [ rdfs:label ?L ] . \n" +
        " { \n" +
        " SELECT DISTINCT ?O   \n" +
        " WHERE { ?O a :Order } \n" +
        " ORDER BY ?O \n" +
        " LIMIT 2 \n" +
        " } \n" +
        "} ORDER BY ?L \n" +
        ""
}
