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
package lupos.code_gen_test_06
import lupos.shared.inline.File

public class resourcesbsbmbiquery81853sparql1853 {
    internal val inputData = arrayOf(
        File("src/jvmTest/resources/resourcesbsbmbiquery81853sparql1853.input").readAsString(),
    )
    internal val inputGraph = arrayOf(
        "",
    )
    internal val inputType = arrayOf(
        ".n3",
    )
    internal val targetData = File("src/jvmTest/resources/resourcesbsbmbiquery81853sparql1853.output").readAsString()
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
        "  Select ?vendor (xsd:float(?belowAvg)/?offerCount As ?cheapExpensiveRatio) \n" +
        "  { \n" +
        "    { Select ?vendor (count(?offer) As ?belowAvg) \n" +
        "      { \n" +
        "        { ?product a bsbm-inst:ProductType5 . \n" +
        "          ?offer bsbm:product ?product . \n" +
        "          ?offer bsbm:vendor ?vendor . \n" +
        "          ?offer bsbm:price ?price . \n" +
        "          { Select ?product (avg(xsd:float(xsd:string(?price))) As ?avgPrice) \n" +
        "            { \n" +
        "              ?product a bsbm-inst:ProductType5 . \n" +
        "              ?offer bsbm:product ?product . \n" +
        "              ?offer bsbm:vendor ?vendor . \n" +
        "              ?offer bsbm:price ?price . \n" +
        "            } \n" +
        "            Group By ?product \n" +
        "          } \n" +
        "        } . \n" +
        "        FILTER (xsd:float(xsd:string(?price)) < ?avgPrice) \n" +
        "      } \n" +
        "      Group By ?vendor \n" +
        "    } \n" +
        "    { Select ?vendor (count(?offer) As ?offerCount) \n" +
        "      { \n" +
        "        ?product a bsbm-inst:ProductType5 . \n" +
        "        ?offer bsbm:product ?product . \n" +
        "        ?offer bsbm:vendor ?vendor . \n" +
        "      } \n" +
        "      Group By ?vendor \n" +
        "    } \n" +
        "  } \n" +
        "  Order by desc(xsd:float(?belowAvg)/?offerCount) ?vendor \n" +
        "  limit 10 \n" +
        ""
}
