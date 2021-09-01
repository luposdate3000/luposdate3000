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

public class resourcesbtc035sparql61664 {
    internal val inputData = arrayOf(
        File("src/jvmTest/resources/resourcesbtc035sparql61664.input").readAsString(),
    )
    internal val inputGraph = arrayOf(
        "",
    )
    internal val inputType = arrayOf(
        ".n3",
    )
    internal val targetData = File("src/jvmTest/resources/resourcesbtc035sparql61664.output").readAsString()
    internal val targetType = ".srx"
    internal val query = "#TripleBit: a Fast and Compact System for Large Scale RDF Data --- BTC 2012 \n" +
        "SELECT DISTINCT ?a ?y WHERE { \n" +
        " ?a <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://dbpedia.org/class/yago/Politician110451263> . \n" +
        " ?a <http://dbpedia.org/property/years> ?y . \n" +
        " ?a <http://xmlns.com/foaf/0.1/name> ?n . \n" +
        " ?b ?c ?n . \n" +
        " ?b <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://dbpedia.org/ontology/OfficeHolder> . \n" +
        "} \n" +
        ""
}
