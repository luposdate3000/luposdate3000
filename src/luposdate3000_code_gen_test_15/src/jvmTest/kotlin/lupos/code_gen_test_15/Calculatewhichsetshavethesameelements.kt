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

public class Calculatewhichsetshavethesameelements {
    internal val inputData = arrayOf(
        File("src/jvmTest/resources/Calculatewhichsetshavethesameelements.input").readAsString(),
    )
    internal val inputGraph = arrayOf(
        "",
    )
    internal val inputType = arrayOf(
        ".ttl",
    )
    internal val targetData = File("src/jvmTest/resources/Calculatewhichsetshavethesameelements.output").readAsString()
    internal val targetType = ".srx"
    internal val query = "PREFIX :    <http://example/> \n" +
        "PREFIX  rdf:    <http://www.w3.org/1999/02/22-rdf-syntax-ns#> \n" +
        "# Find sets that have exactly the same members. \n" +
        "# Find all (s1,s2) such that (s1 subset of s) and (s2 subset of s1). \n" +
        "SELECT DISTINCT ?s1 ?s2 \n" +
        "WHERE \n" +
        "{ \n" +
        "    # All pairs of sets (no duplicates, not reveres pairs) \n" +
        "    ?s2 rdf:type :Set . \n" +
        "    ?s1 rdf:type :Set . \n" +
        "    FILTER(str(?s1) < str(?s2)) \n" +
        "    MINUS  \n" +
        "    { \n" +
        "        ?s1 rdf:type :Set . \n" +
        "        ?s2 rdf:type :Set . \n" +
        "        ?s1 :member ?x . \n" +
        "        FILTER NOT EXISTS { ?s2 :member ?x . } \n" +
        "    } \n" +
        "    MINUS  \n" +
        "    { \n" +
        "        ?s1 rdf:type :Set . \n" +
        "        ?s2 rdf:type :Set . \n" +
        "        ?s2 :member ?x . \n" +
        "        FILTER NOT EXISTS { ?s1 :member ?x . } \n" +
        "    } \n" +
        "} \n" +
        ""
}
