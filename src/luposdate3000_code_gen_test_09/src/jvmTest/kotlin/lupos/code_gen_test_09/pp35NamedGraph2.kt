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

public class pp35NamedGraph2 {
    internal val inputData = arrayOf(
        File("src/jvmTest/resources/pp35NamedGraph2.input0").readAsString(),
        File("src/jvmTest/resources/pp35NamedGraph2.input1").readAsString(),
        File("src/jvmTest/resources/pp35NamedGraph2.input2").readAsString(),
    )
    internal val inputGraph = arrayOf(
        "ng-01.ttl",
        "ng-02.ttl",
        "ng-03.ttl",
    )
    internal val inputType = arrayOf(
        ".ttl",
        ".ttl",
        ".ttl",
    )
    internal val targetData = File("src/jvmTest/resources/pp35NamedGraph2.output").readAsString()
    internal val targetType = ".srx"
    internal val query = "prefix :  <http://www.example.org/> \n" +
        "select ?t \n" +
        "where { \n" +
        "  GRAPH ?g { \n" +
        "    ?s :p1* ?t } \n" +
        "  FILTER (?g = <ng-01.ttl>) \n" +
        "}"
}
