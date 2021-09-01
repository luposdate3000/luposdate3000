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
package lupos.code_gen_test_06
import lupos.shared.inline.File

public class sparqldl10rqundistvarstest {
    internal val inputData = arrayOf(
        File("src/jvmTest/resources/sparqldl10rqundistvarstest.input").readAsString(),
    )
    internal val inputGraph = arrayOf(
        "",
    )
    internal val inputType = arrayOf(
        ".ttl",
    )
    internal val targetData = File("src/jvmTest/resources/sparqldl10rqundistvarstest.output").readAsString()
    internal val targetType = ".srx"
    internal val query = "# Query6 - articulation simplification test \n" +
        "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> \n" +
        "PREFIX owl:  <http://www.w3.org/2002/07/owl#>  \n" +
        "PREFIX : <http://example.org/test#> \n" +
        "SELECT ?X ?Y \n" +
        "WHERE \n" +
        "{ \n" +
        "  ?X :p ?a. \n" +
        "  ?a owl:sameAs ?b. \n" +
        "  ?b owl:sameAs ?Y. \n" +
        "  ?Y :q ?c. \n" +
        "} \n" +
        ""
}
