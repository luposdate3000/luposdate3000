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
package lupos.code_gen_test_19
import lupos.shared.inline.File

public class resourcesbtc002sparql46 {
    internal val inputData = arrayOf(
        File("src/jvmTest/resources/resourcesbtc002sparql46.input").readAsString(),
    )
    internal val inputGraph = arrayOf(
        "",
    )
    internal val inputType = arrayOf(
        ".n3",
    )
    internal val targetData = File("src/jvmTest/resources/resourcesbtc002sparql46.output").readAsString()
    internal val targetType = ".srx"
    internal val query = "#Scalable Join Processing on Very Large RDF Graphs --- BTC 2008 \n" +
        "select ?b ?p ?bn where { \n" +
        " ?a ?c \"Tim Berners-Lee\" . \n" +
        " ?a <http://dbpedia.org/property/dateOfBirth> ?b . \n" +
        " ?a <http://dbpedia.org/property/placeOfBirth> ?p . \n" +
        " ?a <http://dbpedia.org/property/name> ?bn. \n" +
        "} \n" +
        ""
}
