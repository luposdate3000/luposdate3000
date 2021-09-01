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
package lupos.code_gen_test_07
import lupos.shared.inline.File

public class resourcesbsbmexplorequery102553sparql2553 {
    internal val inputData = arrayOf(
        File("src/jvmTest/resources/resourcesbsbmexplorequery102553sparql2553.input").readAsString(),
    )
    internal val inputGraph = arrayOf(
        "",
    )
    internal val inputType = arrayOf(
        ".n3",
    )
    internal val targetData = File("src/jvmTest/resources/resourcesbsbmexplorequery102553sparql2553.output").readAsString()
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
        "SELECT DISTINCT ?offer ?price \n" +
        "WHERE { \n" +
        " ?offer bsbm:product dataFromProducer1:Product3 . \n" +
        " ?offer bsbm:vendor ?vendor . \n" +
        "    ?offer dc:publisher ?vendor . \n" +
        " ?vendor bsbm:country <http://downlode.org/rdf/iso-3166/countries#US> . \n" +
        " ?offer bsbm:deliveryDays ?deliveryDays . \n" +
        " FILTER (?deliveryDays <= 3) \n" +
        " ?offer bsbm:price ?price . \n" +
        "    ?offer bsbm:validTo ?date . \n" +
        "    FILTER (?date > \"2007-09-04\"^^xsd:date ) \n" +
        "} \n" +
        "ORDER BY xsd:double(str(?price)) \n" +
        "LIMIT 10 \n" +
        ""
}
