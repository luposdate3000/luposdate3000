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
package lupos.code_gen_test_00
import lupos.operator.base.Query
import lupos.shared.MemoryTable
import lupos.shared.inline.File
import lupos.simulator_core.Simulation
import lupos.simulator_db.luposdate3000.DatabaseHandle
import lupos.simulator_db.luposdate3000.MySimulatorTestingCompareGraphPackage
import lupos.simulator_db.luposdate3000.MySimulatorTestingImportPackage
import lupos.simulator_iot.SimulationRun
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.fail

public class resourcessp2bq2sparql700 {
    internal val inputData = arrayOf(
        File("src/luposdate3000_code_gen_test_00/src/jvmMain/resources//resourcessp2bq2sparql700.input").readAsString(),
    )
    internal val inputDataFile = arrayOf(
        "src/luposdate3000_code_gen_test_00/src/jvmMain/resources//resourcessp2bq2sparql700.input",
    )
    internal val inputGraph = arrayOf(
        "",
    )
    internal val inputType = arrayOf(
        ".n3",
    )
    internal val targetData = File("src/luposdate3000_code_gen_test_00/src/jvmMain/resources//resourcessp2bq2sparql700.output").readAsString()
    internal val targetType = ".srx"
    internal val query = "PREFIX rdf:     <http://www.w3.org/1999/02/22-rdf-syntax-ns#> \n" +
        "PREFIX rdfs:    <http://www.w3.org/2000/01/rdf-schema#> \n" +
        "PREFIX swrc:    <http://swrc.ontoware.org/ontology#> \n" +
        "PREFIX foaf:    <http://xmlns.com/foaf/0.1/> \n" +
        "PREFIX bench:   <http://localhost/vocabulary/bench/> \n" +
        "PREFIX dc:      <http://purl.org/dc/elements/1.1/> \n" +
        "PREFIX dcterms: <http://purl.org/dc/terms/> \n" +
        "SELECT ?inproc ?author ?booktitle ?title  \n" +
        "       ?proc ?ee ?page ?url ?yr ?abstract \n" +
        "WHERE { \n" +
        "  ?inproc rdf:type bench:Inproceedings . \n" +
        "  ?inproc dc:creator ?author . \n" +
        "  ?inproc bench:booktitle ?booktitle . \n" +
        "  ?inproc dc:title ?title . \n" +
        "  ?inproc dcterms:partOf ?proc . \n" +
        "  ?inproc rdfs:seeAlso ?ee . \n" +
        "  ?inproc swrc:pages ?page . \n" +
        "  ?inproc foaf:homepage ?url . \n" +
        "  ?inproc dcterms:issued ?yr . \n" +
        "  OPTIONAL { \n" +
        "    ?inproc bench:abstract ?abstract . \n" +
        "  } \n" +
        "} \n" +
        "ORDER BY ?yr \n" +
        ""

    @Test
    public fun `resourcessp2bq2sparql700 - in simulator - Simple - Centralized - true - None`() {
        simulatorHelper(
            "src/luposdate3000_simulator_iot/src/jvmTest/resources/autoIntegrationTest/test2.json",
            mutableMapOf(
                "predefinedPartitionScheme" to "Simple",
                "mergeLocalOperatorgraphs" to true,
                "queryDistributionMode" to "Centralized",
                "useDictionaryInlineEncoding" to true,
                "REPLACE_STORE_WITH_VALUES" to false,
                "LUPOS_PARTITION_MODE" to "None",
            )
        )
    }

    @Test
    public fun `resourcessp2bq2sparql700 - in simulator - PartitionByID_O_AllCollations - Centralized - true - Process`() {
        simulatorHelper(
            "src/luposdate3000_simulator_iot/src/jvmTest/resources/autoIntegrationTest/test1.json",
            mutableMapOf(
                "predefinedPartitionScheme" to "PartitionByID_O_AllCollations",
                "mergeLocalOperatorgraphs" to true,
                "queryDistributionMode" to "Centralized",
                "useDictionaryInlineEncoding" to true,
                "REPLACE_STORE_WITH_VALUES" to false,
                "LUPOS_PARTITION_MODE" to "Process",
            )
        )
    }

    @Test
    public fun `resourcessp2bq2sparql700 - in simulator - PartitionByID_O_AllCollations - Centralized - false - Process`() {
        simulatorHelper(
            "src/luposdate3000_simulator_iot/src/jvmTest/resources/autoIntegrationTest/test1.json",
            mutableMapOf(
                "predefinedPartitionScheme" to "PartitionByID_O_AllCollations",
                "mergeLocalOperatorgraphs" to true,
                "queryDistributionMode" to "Centralized",
                "useDictionaryInlineEncoding" to false,
                "REPLACE_STORE_WITH_VALUES" to false,
                "LUPOS_PARTITION_MODE" to "Process",
            )
        )
    }

    @Test
    public fun `resourcessp2bq2sparql700 - in simulator - PartitionByID_S_AllCollations - Centralized - true - Process`() {
        simulatorHelper(
            "src/luposdate3000_simulator_iot/src/jvmTest/resources/autoIntegrationTest/test1.json",
            mutableMapOf(
                "predefinedPartitionScheme" to "PartitionByID_S_AllCollations",
                "mergeLocalOperatorgraphs" to true,
                "queryDistributionMode" to "Centralized",
                "useDictionaryInlineEncoding" to true,
                "REPLACE_STORE_WITH_VALUES" to false,
                "LUPOS_PARTITION_MODE" to "Process",
            )
        )
    }

    @Test
    public fun `resourcessp2bq2sparql700 - in simulator - PartitionByKeyAllCollations - Centralized - true - Process`() {
        simulatorHelper(
            "src/luposdate3000_simulator_iot/src/jvmTest/resources/autoIntegrationTest/test1.json",
            mutableMapOf(
                "predefinedPartitionScheme" to "PartitionByKeyAllCollations",
                "mergeLocalOperatorgraphs" to true,
                "queryDistributionMode" to "Centralized",
                "useDictionaryInlineEncoding" to true,
                "REPLACE_STORE_WITH_VALUES" to false,
                "LUPOS_PARTITION_MODE" to "Process",
            )
        )
    }

    @Test
    public fun `resourcessp2bq2sparql700 - in simulator - PartitionByKeyAllCollations - Routing - false - Process`() {
        simulatorHelper(
            "src/luposdate3000_simulator_iot/src/jvmTest/resources/autoIntegrationTest/test1.json",
            mutableMapOf(
                "predefinedPartitionScheme" to "PartitionByKeyAllCollations",
                "mergeLocalOperatorgraphs" to true,
                "queryDistributionMode" to "Routing",
                "useDictionaryInlineEncoding" to false,
                "REPLACE_STORE_WITH_VALUES" to false,
                "LUPOS_PARTITION_MODE" to "Process",
            )
        )
    }

    @Test
    public fun `resourcessp2bq2sparql700 - in simulator - PartitionByID_S_AllCollations - Centralized - true - Thread`() {
        simulatorHelper(
            "src/luposdate3000_simulator_iot/src/jvmTest/resources/autoIntegrationTest/test2.json",
            mutableMapOf(
                "predefinedPartitionScheme" to "PartitionByID_S_AllCollations",
                "mergeLocalOperatorgraphs" to true,
                "queryDistributionMode" to "Centralized",
                "useDictionaryInlineEncoding" to true,
                "REPLACE_STORE_WITH_VALUES" to false,
                "LUPOS_PARTITION_MODE" to "Thread",
            )
        )
    }

    @Test
    public fun `resourcessp2bq2sparql700 - in simulator - Simple - Centralized - true - Thread`() {
        simulatorHelper(
            "src/luposdate3000_simulator_iot/src/jvmTest/resources/autoIntegrationTest/test2.json",
            mutableMapOf(
                "predefinedPartitionScheme" to "Simple",
                "mergeLocalOperatorgraphs" to true,
                "queryDistributionMode" to "Centralized",
                "useDictionaryInlineEncoding" to true,
                "REPLACE_STORE_WITH_VALUES" to false,
                "LUPOS_PARTITION_MODE" to "Thread",
            )
        )
    }
    public fun simulatorHelper(fileName: String, cfg: MutableMap<String, Any>) {
        val simRun = SimulationRun()
        val config = simRun.parseConfig(fileName, false)
        config.jsonObjects.database.putAll(cfg)
        simRun.sim = Simulation(config.getEntities())
        simRun.sim.maxClock = if (simRun.simMaxClock == simRun.notInitializedClock) simRun.sim.maxClock else simRun.simMaxClock
        simRun.sim.steadyClock = if (simRun.simSteadyClock == simRun.notInitializedClock) simRun.sim.steadyClock else simRun.simSteadyClock
        simRun.sim.startUp()
        val instance = (config.devices.filter { it.userApplication != null }.map { it.userApplication!!.getAllChildApplications() }.flatten().filter { it is DatabaseHandle }.first()as DatabaseHandle).instance
        val pkg0 = MySimulatorTestingImportPackage(inputDataFile[0], inputGraph[0], inputType[0])
        var verifyExecuted1 = 0
        val pkg1 = MySimulatorTestingCompareGraphPackage(null, MemoryTable.parseFromAny(inputData[0], inputType[0], Query(instance))!!, { verifyExecuted1++ }, inputGraph[0], instance)
        pkg0.setOnFinish(pkg1)
        var verifyExecuted2 = 0
        val pkg2 = MySimulatorTestingCompareGraphPackage(query, MemoryTable.parseFromAny(targetData, targetType, Query(instance))!!, { verifyExecuted2++ }, "", instance)
        pkg1.setOnFinish(pkg2)
        config.querySenders[0].queryPck = pkg0
        simRun.sim.run()
        simRun.sim.shutDown()
        if (verifyExecuted1 == 0) {
            fail("pck1 not verified")
        }
        if (verifyExecuted2 == 0) {
            fail("pck2 not verified")
        }
    }
}
