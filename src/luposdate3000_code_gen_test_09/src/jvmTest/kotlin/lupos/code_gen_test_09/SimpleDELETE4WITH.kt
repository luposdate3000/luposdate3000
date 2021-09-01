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

public class SimpleDELETE4WITH {
    internal val inputData = arrayOf(
        File("src/jvmTest/resources/SimpleDELETE4WITH.input0").readAsString(),
        File("src/jvmTest/resources/SimpleDELETE4WITH.input1").readAsString(),
    )
    internal val inputGraph = arrayOf(
        "http://example.org/g1",
        "http://example.org/g2",
    )
    internal val inputType = arrayOf(
        ".ttl",
        ".ttl",
    )
    internal val outputData = arrayOf(
        File("src/jvmTest/resources/SimpleDELETE4WITH.output0").readAsString(),
        File("src/jvmTest/resources/SimpleDELETE4WITH.output1").readAsString(),
    )
    internal val outputGraph = arrayOf(
        "http://example.org/g1",
        "http://example.org/g2",
    )
    internal val outputType = arrayOf(
        ".ttl",
        ".ttl",
    )
    internal val query = "PREFIX     : <http://example.org/>  \n" +
        "PREFIX foaf: <http://xmlns.com/foaf/0.1/>  \n" +
        "WITH <http://example.org/g2> \n" +
        "DELETE  \n" +
        "{ \n" +
        "  GRAPH <http://example.org/g1> { ?s ?p ?o } \n" +
        "} \n" +
        "WHERE \n" +
        "{ \n" +
        "  GRAPH <http://example.org/g1> { ?s foaf:knows :c . \n" +
        "                                  ?s ?p ?o } \n" +
        "} \n" +
        ""
}
