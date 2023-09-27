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
package lupos.launch_code_gen_test_00
import lupos.operator.base.Query
import lupos.shared.MemoryTable
import lupos.shared.inline.File
import lupos.simulator_db.luposdate3000.Application_Luposdate3000
import lupos.simulator_db.luposdate3000.Package_Luposdate3000_TestingCompareGraphPackage
import lupos.simulator_db.luposdate3000.Package_Luposdate3000_TestingImportPackage
import simora.SimulationRun
import simora.addQuerySender

public class resourcesbsbmbiquery412553sparql2553 {
    internal val inputData = arrayOf(
        File("src/luposdate3000_launch_code_gen_test_00/src/jvmTest/resources/resourcesbsbmbiquery412553sparql2553.input").readAsString(),
    )
    internal val inputDataFile = arrayOf(
        "src/luposdate3000_launch_code_gen_test_00/src/jvmTest/resources/resourcesbsbmbiquery412553sparql2553.input",
    )
    internal val inputGraph = arrayOf(
        "",
    )
    internal val inputType = arrayOf(
        ".n3",
    )
    internal val targetData = File("src/luposdate3000_launch_code_gen_test_00/src/jvmTest/resources/resourcesbsbmbiquery412553sparql2553.output").readAsString()
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
        "        ?product a bsbm-inst:ProductType6 ; \n" +
        "                 bsbm:productFeature ?feature . \n" +
        "        ?offer bsbm:product ?product ; \n" +
        "               bsbm:price ?price . \n" +
        "      } \n" +
        "      Group By ?feature \n" +
        "    } \n" +
        "    { Select ?feature (avg(xsd:float(xsd:string(?price))) As ?withoutFeaturePrice) \n" +
        "      { \n" +
        "        { Select distinct ?feature {  \n" +
        "          ?p a bsbm-inst:ProductType6 ; \n" +
        "             bsbm:productFeature ?feature . \n" +
        "        } } \n" +
        "        ?product a bsbm-inst:ProductType6 . \n" +
        "        ?offer bsbm:product ?product ; \n" +
        "               bsbm:price ?price . \n" +
        "        FILTER NOT EXISTS { ?product bsbm:productFeature ?feature } \n" +
        "      } \n" +
        "      Group By ?feature \n" +
        "    } \n" +
        "  } \n" +
        ""

    public fun `resourcesbsbmbiquery412553sparql2553 - in simulator - PartitionByID_S_AllCollations - Routing - true - Process - RPL`() {
        simulatorHelper(
            "src/luposdate3000_simulator_db/src/jvmTest/resources/autoIntegrationTest/test1.json",
            mutableMapOf(
                "predefinedPartitionScheme" to "PartitionByID_S_AllCollations",
                "mergeLocalOperatorgraphs" to true,
                "queryDistributionMode" to "Routing",
                "useDictionaryInlineEncoding" to true,
                "REPLACE_STORE_WITH_VALUES" to false,
                "LUPOS_PARTITION_MODE" to "Process",
            ),
            "RPL",
        )
    }
    public fun `resourcesbsbmbiquery412553sparql2553 - in simulator - PartitionByID_S_AllCollations - Routing - true - Process - RPL_Fast`() {
        simulatorHelper(
            "src/luposdate3000_simulator_db/src/jvmTest/resources/autoIntegrationTest/test1.json",
            mutableMapOf(
                "predefinedPartitionScheme" to "PartitionByID_S_AllCollations",
                "mergeLocalOperatorgraphs" to true,
                "queryDistributionMode" to "Routing",
                "useDictionaryInlineEncoding" to true,
                "REPLACE_STORE_WITH_VALUES" to false,
                "LUPOS_PARTITION_MODE" to "Process",
            ),
            "RPL_Fast",
        )
    }
    public fun simulatorHelper(fileName: String, database_cfg: MutableMap<String, Any>, routingProtocol: String) {
        val simRun = SimulationRun()
        simRun.parseConfig(fileName, false, {
            it.getOrEmptyObject("deviceType").getOrEmptyObject("LUPOSDATE_DEVICE").getOrEmptyObject("applications").getOrEmptyObject("lupos.simulator_db.luposdate3000.ApplicationFactory_Luposdate3000").putAll(database_cfg)
            it.getOrEmptyObject("routing").putAll(mapOf("protocol" to routingProtocol))
        })

        simRun.startUp()
        val instance = (simRun.devices.map { it.getAllChildApplications() }.flatten().filter { it is Application_Luposdate3000 }.first()as Application_Luposdate3000).instance
        val pkg0 = Package_Luposdate3000_TestingImportPackage(inputDataFile[0], inputGraph[0], inputType[0])
        var verifyExecuted1 = 0
        val pkg1 = Package_Luposdate3000_TestingCompareGraphPackage(null, MemoryTable.parseFromAny(inputData[0], inputType[0], Query(instance))!!, { verifyExecuted1++ }, inputGraph[0], instance)
        pkg0.setOnFinish(pkg1)
        var verifyExecuted2 = 0
        val pkg2 = Package_Luposdate3000_TestingCompareGraphPackage(query, MemoryTable.parseFromAny(targetData, targetType, Query(instance))!!, { verifyExecuted2++ }, "", instance)
        pkg1.setOnFinish(pkg2)
        simRun.addQuerySender(10, 1, 1, pkg0)
        simRun.run()
        simRun.shutDown()
        if (verifyExecuted1 == 0) {
            TODO("pck1 not verified")
        }
        if (verifyExecuted2 == 0) {
            TODO("pck2 not verified")
        }
    }
    public fun getTests(): Set<Pair<String, () -> Unit>> {
        return setOf(
            "resourcesbsbmbiquery412553sparql2553 - in simulator - PartitionByID_S_AllCollations - Routing - true - Process - RPL" to ::`resourcesbsbmbiquery412553sparql2553 - in simulator - PartitionByID_S_AllCollations - Routing - true - Process - RPL`,
            "resourcesbsbmbiquery412553sparql2553 - in simulator - PartitionByID_S_AllCollations - Routing - true - Process - RPL_Fast" to ::`resourcesbsbmbiquery412553sparql2553 - in simulator - PartitionByID_S_AllCollations - Routing - true - Process - RPL_Fast`,
        )
    }
}
public fun main() {
    var idx = 0
    var stop = false
    for ((name, func) in resourcesbsbmbiquery412553sparql2553().getTests()) {
        if (stop) {
            return
        }
        File("lupos.launch_code_gen_test_00.${name.replaceFirstChar { it.uppercase() }}.stat").withOutputStream { out ->
            out.println("started" + idx)
            try {
                println(name)
                func()
                out.println("passed")
            } catch (e: Error) {
                out.println("failed")
                e.printStackTrace()
                stop = true
            }
        }
        idx += 1
    }
}