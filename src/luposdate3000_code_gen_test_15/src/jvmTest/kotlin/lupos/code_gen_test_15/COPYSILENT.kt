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

public class COPYSILENT {
    internal val inputData = arrayOf(
        File("src/jvmTest/resources/COPYSILENT.input0").readAsString(),
    )
    internal val inputGraph = arrayOf(
        "http://example.org/g2",
    )
    internal val inputType = arrayOf(
        ".ttl",
    )
    internal val outputData = arrayOf(
        File("src/jvmTest/resources/COPYSILENT.output0").readAsString(),
    )
    internal val outputGraph = arrayOf(
        "http://example.org/g2",
    )
    internal val outputType = arrayOf(
        ".ttl",
    )
    internal val query = "COPY SILENT GRAPH <http://www.example.com/g1> TO GRAPH <http://www.example.com/g2> \n" +
        ""
}
