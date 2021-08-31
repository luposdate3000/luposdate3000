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
package lupos.code_gen_test_08
import lupos.shared.inline.File

public class INSERTingthesamebnodewithtwoINSERTWHEREstatementwithinonerequestisNOTthesamebnodeevenifbothWHEREclauseshavetheemptysolutionmappingastheonlysolution {
    internal val inputData = arrayOf(
        File("src/jvmTest/resources/INSERTingthesamebnodewithtwoINSERTWHEREstatementwithinonerequestisNOTthesamebnodeevenifbothWHEREclauseshavetheemptysolutionmappingastheonlysolution.input").readAsString(),
    )
    internal val inputGraph = arrayOf(
        "",
    )
    internal val inputType = arrayOf(
        ".ttl",
    )
    internal val outputData = arrayOf(
        File("src/jvmTest/resources/INSERTingthesamebnodewithtwoINSERTWHEREstatementwithinonerequestisNOTthesamebnodeevenifbothWHEREclauseshavetheemptysolutionmappingastheonlysolution.output0").readAsString(),
        File("src/jvmTest/resources/INSERTingthesamebnodewithtwoINSERTWHEREstatementwithinonerequestisNOTthesamebnodeevenifbothWHEREclauseshavetheemptysolutionmappingastheonlysolution.output1").readAsString(),
    )
    internal val outputGraph = arrayOf(
        "",
        "http://example.org/g3",
    )
    internal val outputType = arrayOf(
        ".ttl",
        ".ttl",
    )
    internal val query = "PREFIX : <http://example.org/> \n" +
        "# starting with an empty graph store, \n" +
        "# insert the same bnode in two different graphs... \n" +
        "INSERT  { GRAPH :g1  { _:b :p :o } } WHERE {};  \n" +
        "INSERT  { GRAPH :g2  { _:b :p :o } } WHERE {}; \n" +
        "# ... then copy g1 to g2 ... \n" +
        "INSERT { GRAPH :g2  { ?S ?P ?O } } \n" +
        " WHERE { GRAPH :g1  { ?S ?P ?O } } ; \n" +
        "# ... by which the number of triples in  \n" +
        "# g2 should increase \n" +
        "INSERT { GRAPH :g3 { :s :p ?count } } \n" +
        "WHERE { \n" +
        " SELECT (COUNT(*) AS ?count) WHERE { \n" +
        "  GRAPH :g2 { ?s ?p ?o } \n" +
        " } \n" +
        "} ; \n" +
        "DROP GRAPH :g1 ; \n" +
        "DROP GRAPH :g2 \n" +
        ""
}
