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
package lupos.code_gen_test_02
import lupos.shared.inline.File

public class resourcesbsbmbiquery42210sparql2210 {
    internal val inputData = arrayOf(
        File("src/jvmTest/resources/resourcesbsbmbiquery42210sparql2210.input").readAsString(),
    )
    internal val inputGraph = arrayOf(
        "",
    )
    internal val inputType = arrayOf(
        ".n3",
    )
    internal val targetData = File("src/jvmTest/resources/resourcesbsbmbiquery42210sparql2210.output").readAsString()
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
        "  Select ?feature (?withFeaturePrice/?withoutFeaturePrice As ?priceRatio) \n" +
        "  { \n" +
        "    { Select ?feature (avg(xsd:float(xsd:string(?price))) As ?withFeaturePrice) \n" +
        "      { \n" +
        "        ?product a bsbm-inst:ProductType5 ; \n" +
        "                 bsbm:productFeature ?feature . \n" +
        "        ?offer bsbm:product ?product ; \n" +
        "               bsbm:price ?price . \n" +
        "      } \n" +
        "      Group By ?feature \n" +
        "    } \n" +
        "    { Select ?feature (avg(xsd:float(xsd:string(?price))) As ?withoutFeaturePrice) \n" +
        "      { \n" +
        "        { Select distinct ?feature {  \n" +
        "          ?p a bsbm-inst:ProductType5 ; \n" +
        "             bsbm:productFeature ?feature . \n" +
        "        } } \n" +
        "        ?product a bsbm-inst:ProductType5 . \n" +
        "        ?offer bsbm:product ?product ; \n" +
        "               bsbm:price ?price . \n" +
        "        FILTER NOT EXISTS { ?product bsbm:productFeature ?feature } \n" +
        "      } \n" +
        "      Group By ?feature \n" +
        "    } \n" +
        "  } \n" +
        "  Order By desc(?withFeaturePrice/?withoutFeaturePrice) ?feature \n" +
        "  Limit 10 \n" +
        ""
}
