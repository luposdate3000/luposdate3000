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
package lupos.code_gen_test_13
import lupos.shared.inline.File

public class resourcesbsbmexplorequery41853sparql1853 {
    internal val inputData = arrayOf(
        File("src/jvmTest/resources/resourcesbsbmexplorequery41853sparql1853.input").readAsString(),
    )
    internal val inputGraph = arrayOf(
        "",
    )
    internal val inputType = arrayOf(
        ".n3",
    )
    internal val targetData = File("src/jvmTest/resources/resourcesbsbmexplorequery41853sparql1853.output").readAsString()
    internal val targetType = ".srx"
    internal val query = "PREFIX bsbm: <http://www4.wiwiss.fu-berlin.de/bizer/bsbm/v01/vocabulary/> \n" +
        "PREFIX rev: <http://purl.org/stuff/rev#> \n" +
        "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>   \n" +
        "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>   \n" +
        "PREFIX foaf: <http://xmlns.com/foaf/0.1/>   \n" +
        "PREFIX dc: <http://purl.org/dc/elements/1.1/>   \n" +
        "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>   \n" +
        "PREFIX bsbm-inst: <http://www4.wiwiss.fu-berlin.de/bizer/bsbm/v01/instances/>   \n" +
        "PREFIX dataFromProducer1: <http://www4.wiwiss.fu-berlin.de/bizer/bsbm/v01/instances/dataFromProducer1/>   \n" +
        "PREFIX dataFromVendor1: <http://www4.wiwiss.fu-berlin.de/bizer/bsbm/v01/instances/dataFromVendor1/>   \n" +
        "PREFIX dataFromRatingSite1: <http://www4.wiwiss.fu-berlin.de/bizer/bsbm/v01/instances/dataFromRatingSite1/>   \n" +
        "SELECT DISTINCT ?product ?label ?propertyTextual \n" +
        "WHERE { \n" +
        "    {  \n" +
        "       ?product rdfs:label ?label . \n" +
        "       ?product rdf:type bsbm-inst:ProductType5 . \n" +
        "       ?product bsbm:productFeature bsbm-inst:ProductFeature82 . \n" +
        "    ?product bsbm:productFeature bsbm-inst:ProductFeature218 . \n" +
        "       ?product bsbm:productPropertyTextual1 ?propertyTextual . \n" +
        "    ?product bsbm:productPropertyNumeric1 ?p1 . \n" +
        "    FILTER ( ?p1 > \"831\"^^xsd:integer ) \n" +
        "    } UNION { \n" +
        "       ?product rdfs:label ?label . \n" +
        "       ?product rdf:type bsbm-inst:ProductType5 . \n" +
        "       ?product bsbm:productFeature bsbm-inst:ProductFeature82 . \n" +
        "    ?product bsbm:productFeature bsbm-inst:ProductFeature176 . \n" +
        "       ?product bsbm:productPropertyTextual1 ?propertyTextual . \n" +
        "    ?product bsbm:productPropertyNumeric2 ?p2 . \n" +
        "    FILTER ( ?p2> \"312\"^^xsd:integer )  \n" +
        "    }  \n" +
        "} \n" +
        "ORDER BY ?label \n" +
        "OFFSET 5 \n" +
        "LIMIT 10 \n" +
        ""
}
