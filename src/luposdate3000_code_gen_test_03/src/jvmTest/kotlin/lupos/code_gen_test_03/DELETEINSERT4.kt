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
package lupos.code_gen_test_03
import lupos.shared.inline.File

public class DELETEINSERT4 {
    internal val inputData = arrayOf(
        File("src/jvmTest/resources/DELETEINSERT4.input").readAsString(),
    )
    internal val inputGraph = arrayOf(
        "",
    )
    internal val inputType = arrayOf(
        ".ttl",
    )
    internal val outputData = arrayOf(
        File("src/jvmTest/resources/DELETEINSERT4.output0").readAsString(),
    )
    internal val outputGraph = arrayOf(
        "",
    )
    internal val outputType = arrayOf(
        ".ttl",
    )
    internal val query = "PREFIX foaf: <http://xmlns.com/foaf/0.1/>  \n" +
        "DELETE  \n" +
        "{ \n" +
        "  ?a foaf:knows ?Var_B . \n" +
        "} \n" +
        "WHERE \n" +
        "{ \n" +
        "  { ?a foaf:name \"Alan\" } \n" +
        "  { SELECT DISTINCT ?Var_B  \n" +
        "            {  { ?Var_B ?Var_B1 ?Var_B2 } UNION  \n" +
        "               { ?Var_B1 ?Var_B ?Var_B2 } UNION  \n" +
        "               { ?Var_B1 ?Var_B2 ?Var_B } UNION  \n" +
        "               { GRAPH ?Var_Bg {?Var_B ?Var_B1 ?Var_B2 } } UNION \n" +
        "               { GRAPH ?Var_Bg {?Var_B1 ?Var_B ?Var_B2 } } UNION \n" +
        "               { GRAPH ?Var_Bg {?Var_B1 ?Var_B2 ?Var_B } } } } \n" +
        "}"
}
