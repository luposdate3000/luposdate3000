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
package lupos.code_gen_test_04
import lupos.shared.inline.File

public class PostqueryVALUESwithpredvar1row {
    internal val inputData = arrayOf(
        File("src/jvmTest/resources/PostqueryVALUESwithpredvar1row.input").readAsString(),
    )
    internal val inputGraph = arrayOf(
        "",
    )
    internal val inputType = arrayOf(
        ".ttl",
    )
    internal val targetData = File("src/jvmTest/resources/PostqueryVALUESwithpredvar1row.output").readAsString()
    internal val targetType = ".srx"
    internal val query = "# bindings with two variables and two sets of values \n" +
        "PREFIX : <http://example.org/>  \n" +
        "PREFIX foaf: <http://xmlns.com/foaf/0.1/>  \n" +
        "SELECT ?s ?p1 ?o1 \n" +
        "{ \n" +
        "  ?s ?p1 ?o1 . \n" +
        "} VALUES ?p1 { \n" +
        " foaf:knows \n" +
        "} \n" +
        ""
}
