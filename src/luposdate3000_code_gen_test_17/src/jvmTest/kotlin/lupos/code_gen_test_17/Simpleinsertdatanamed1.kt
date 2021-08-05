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
package lupos.code_gen_test_17
import lupos.endpoint.LuposdateEndpoint
import lupos.operator.arithmetik.noinput.AOPVariable
import lupos.operator.base.Query
import lupos.result_format.EQueryResultToStreamExt
import lupos.shared.EIndexPatternExt
import lupos.shared.EPredefinedPartitionSchemesExt
import lupos.shared.EQueryDistributionModeExt
import lupos.shared.MemoryTable
import lupos.shared.inline.File
import lupos.shared.inline.MyPrintWriter
import lupos.simulator_core.Simulation
import lupos.simulator_db.luposdate3000.DatabaseHandle
import lupos.simulator_db.luposdate3000.MySimulatorConfig
import lupos.simulator_db.luposdate3000.MySimulatorTestingCompareGraphPackage
import lupos.simulator_db.luposdate3000.MySimulatorTestingExecute
import lupos.simulator_iot.SimulationRun
import kotlin.test.Ignore
import kotlin.test.Test
import kotlin.test.fail

public class Simpleinsertdatanamed1 {
    internal val outputData = arrayOf(
        File("src/jvmTest/resources/Simpleinsertdatanamed1.output0").readAsString(),
    )
    internal val outputGraph = arrayOf(
        "http://example.org/g1",
    )
    internal val outputType = arrayOf(
        ".ttl",
    )
    internal val query = "PREFIX : <http://example.org/ns#> \n" +
        "INSERT DATA { GRAPH <http://example.org/g1> { :s :p :o } } \n" +
        ""

    @Ignore // Reason: >Bug<
    @Test
    public fun `Simple insert data named 1`() {
        val instance = LuposdateEndpoint.initialize()
        instance.LUPOS_BUFFER_SIZE = 128
        val buf = MyPrintWriter(false)
        val operator0 = LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, query)
        LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator0, buf, EQueryResultToStreamExt.EMPTY_STREAM)
        val query1 = Query(instance)
        val graph1 = instance.tripleStoreManager!!.getGraph(outputGraph[0])
        val operator1 = graph1.getIterator(query1, arrayOf(AOPVariable(query1, "s"), AOPVariable(query1, "p"), AOPVariable(query1, "o")), EIndexPatternExt.SPO)
        val actual1 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator1, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected1 = MemoryTable.parseFromAny(outputData[0], outputType[0], Query(instance))!!
        val buf_err1 = MyPrintWriter()
        if (!expected1.equalsVerbose(actual1, true, true, buf_err1)) {
            fail(expected1.toString() + " .. " + actual1.toString() + " .. " + buf_err1.toString() + " .. " + operator1)
        }
        LuposdateEndpoint.close(instance)
    }

    @Ignore // Reason: >Bug<
    @Test
    public fun `Simple insert data named 1 - in simulator - PartitionByIDTwiceAllCollations - true - Centralized - true - true`() {
        simulatorHelper(MySimulatorConfig(predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByIDTwiceAllCollations, mergeLocalOperatorgraphs = true, queryDistributionMode = EQueryDistributionModeExt.Centralized, useDictionaryInlineEncoding = true, REPLACE_STORE_WITH_VALUES = true))
    }

    @Ignore // Reason: >Bug<
    @Test
    public fun `Simple insert data named 1 - in simulator - PartitionByIDTwiceAllCollations - true - Centralized - true - false`() {
        simulatorHelper(MySimulatorConfig(predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByIDTwiceAllCollations, mergeLocalOperatorgraphs = true, queryDistributionMode = EQueryDistributionModeExt.Centralized, useDictionaryInlineEncoding = true, REPLACE_STORE_WITH_VALUES = false))
    }

    @Ignore // Reason: >Bug<
    @Test
    public fun `Simple insert data named 1 - in simulator - PartitionByIDTwiceAllCollations - true - Centralized - false - true`() {
        simulatorHelper(MySimulatorConfig(predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByIDTwiceAllCollations, mergeLocalOperatorgraphs = true, queryDistributionMode = EQueryDistributionModeExt.Centralized, useDictionaryInlineEncoding = false, REPLACE_STORE_WITH_VALUES = true))
    }

    @Ignore // Reason: >Bug<
    @Test
    public fun `Simple insert data named 1 - in simulator - PartitionByIDTwiceAllCollations - true - Centralized - false - false`() {
        simulatorHelper(MySimulatorConfig(predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByIDTwiceAllCollations, mergeLocalOperatorgraphs = true, queryDistributionMode = EQueryDistributionModeExt.Centralized, useDictionaryInlineEncoding = false, REPLACE_STORE_WITH_VALUES = false))
    }

    @Ignore // Reason: >Bug<
    @Test
    public fun `Simple insert data named 1 - in simulator - PartitionByIDTwiceAllCollations - true - Routing - true - true`() {
        simulatorHelper(MySimulatorConfig(predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByIDTwiceAllCollations, mergeLocalOperatorgraphs = true, queryDistributionMode = EQueryDistributionModeExt.Routing, useDictionaryInlineEncoding = true, REPLACE_STORE_WITH_VALUES = true))
    }

    @Ignore // Reason: >Bug<
    @Test
    public fun `Simple insert data named 1 - in simulator - PartitionByIDTwiceAllCollations - true - Routing - true - false`() {
        simulatorHelper(MySimulatorConfig(predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByIDTwiceAllCollations, mergeLocalOperatorgraphs = true, queryDistributionMode = EQueryDistributionModeExt.Routing, useDictionaryInlineEncoding = true, REPLACE_STORE_WITH_VALUES = false))
    }

    @Ignore // Reason: >Bug<
    @Test
    public fun `Simple insert data named 1 - in simulator - PartitionByIDTwiceAllCollations - true - Routing - false - true`() {
        simulatorHelper(MySimulatorConfig(predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByIDTwiceAllCollations, mergeLocalOperatorgraphs = true, queryDistributionMode = EQueryDistributionModeExt.Routing, useDictionaryInlineEncoding = false, REPLACE_STORE_WITH_VALUES = true))
    }

    @Ignore // Reason: >Bug<
    @Test
    public fun `Simple insert data named 1 - in simulator - PartitionByIDTwiceAllCollations - true - Routing - false - false`() {
        simulatorHelper(MySimulatorConfig(predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByIDTwiceAllCollations, mergeLocalOperatorgraphs = true, queryDistributionMode = EQueryDistributionModeExt.Routing, useDictionaryInlineEncoding = false, REPLACE_STORE_WITH_VALUES = false))
    }

    @Ignore // Reason: >Bug<
    @Test
    public fun `Simple insert data named 1 - in simulator - PartitionByIDTwiceAllCollations - false - Centralized - true - true`() {
        simulatorHelper(MySimulatorConfig(predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByIDTwiceAllCollations, mergeLocalOperatorgraphs = false, queryDistributionMode = EQueryDistributionModeExt.Centralized, useDictionaryInlineEncoding = true, REPLACE_STORE_WITH_VALUES = true))
    }

    @Ignore // Reason: >Bug<
    @Test
    public fun `Simple insert data named 1 - in simulator - PartitionByIDTwiceAllCollations - false - Centralized - true - false`() {
        simulatorHelper(MySimulatorConfig(predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByIDTwiceAllCollations, mergeLocalOperatorgraphs = false, queryDistributionMode = EQueryDistributionModeExt.Centralized, useDictionaryInlineEncoding = true, REPLACE_STORE_WITH_VALUES = false))
    }

    @Ignore // Reason: >Bug<
    @Test
    public fun `Simple insert data named 1 - in simulator - PartitionByIDTwiceAllCollations - false - Centralized - false - true`() {
        simulatorHelper(MySimulatorConfig(predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByIDTwiceAllCollations, mergeLocalOperatorgraphs = false, queryDistributionMode = EQueryDistributionModeExt.Centralized, useDictionaryInlineEncoding = false, REPLACE_STORE_WITH_VALUES = true))
    }

    @Ignore // Reason: >Bug<
    @Test
    public fun `Simple insert data named 1 - in simulator - PartitionByIDTwiceAllCollations - false - Centralized - false - false`() {
        simulatorHelper(MySimulatorConfig(predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByIDTwiceAllCollations, mergeLocalOperatorgraphs = false, queryDistributionMode = EQueryDistributionModeExt.Centralized, useDictionaryInlineEncoding = false, REPLACE_STORE_WITH_VALUES = false))
    }

    @Ignore // Reason: >Bug<
    @Test
    public fun `Simple insert data named 1 - in simulator - PartitionByIDTwiceAllCollations - false - Routing - true - true`() {
        simulatorHelper(MySimulatorConfig(predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByIDTwiceAllCollations, mergeLocalOperatorgraphs = false, queryDistributionMode = EQueryDistributionModeExt.Routing, useDictionaryInlineEncoding = true, REPLACE_STORE_WITH_VALUES = true))
    }

    @Ignore // Reason: >Bug<
    @Test
    public fun `Simple insert data named 1 - in simulator - PartitionByIDTwiceAllCollations - false - Routing - true - false`() {
        simulatorHelper(MySimulatorConfig(predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByIDTwiceAllCollations, mergeLocalOperatorgraphs = false, queryDistributionMode = EQueryDistributionModeExt.Routing, useDictionaryInlineEncoding = true, REPLACE_STORE_WITH_VALUES = false))
    }

    @Ignore // Reason: >Bug<
    @Test
    public fun `Simple insert data named 1 - in simulator - PartitionByIDTwiceAllCollations - false - Routing - false - true`() {
        simulatorHelper(MySimulatorConfig(predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByIDTwiceAllCollations, mergeLocalOperatorgraphs = false, queryDistributionMode = EQueryDistributionModeExt.Routing, useDictionaryInlineEncoding = false, REPLACE_STORE_WITH_VALUES = true))
    }

    @Ignore // Reason: >Bug<
    @Test
    public fun `Simple insert data named 1 - in simulator - PartitionByIDTwiceAllCollations - false - Routing - false - false`() {
        simulatorHelper(MySimulatorConfig(predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByIDTwiceAllCollations, mergeLocalOperatorgraphs = false, queryDistributionMode = EQueryDistributionModeExt.Routing, useDictionaryInlineEncoding = false, REPLACE_STORE_WITH_VALUES = false))
    }

    @Ignore // Reason: >Bug<
    @Test
    public fun `Simple insert data named 1 - in simulator - PartitionByKeyAllCollations - true - Centralized - true - true`() {
        simulatorHelper(MySimulatorConfig(predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByKeyAllCollations, mergeLocalOperatorgraphs = true, queryDistributionMode = EQueryDistributionModeExt.Centralized, useDictionaryInlineEncoding = true, REPLACE_STORE_WITH_VALUES = true))
    }

    @Ignore // Reason: >Bug<
    @Test
    public fun `Simple insert data named 1 - in simulator - PartitionByKeyAllCollations - true - Centralized - true - false`() {
        simulatorHelper(MySimulatorConfig(predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByKeyAllCollations, mergeLocalOperatorgraphs = true, queryDistributionMode = EQueryDistributionModeExt.Centralized, useDictionaryInlineEncoding = true, REPLACE_STORE_WITH_VALUES = false))
    }

    @Ignore // Reason: >Bug<
    @Test
    public fun `Simple insert data named 1 - in simulator - PartitionByKeyAllCollations - true - Centralized - false - true`() {
        simulatorHelper(MySimulatorConfig(predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByKeyAllCollations, mergeLocalOperatorgraphs = true, queryDistributionMode = EQueryDistributionModeExt.Centralized, useDictionaryInlineEncoding = false, REPLACE_STORE_WITH_VALUES = true))
    }

    @Ignore // Reason: >Bug<
    @Test
    public fun `Simple insert data named 1 - in simulator - PartitionByKeyAllCollations - true - Centralized - false - false`() {
        simulatorHelper(MySimulatorConfig(predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByKeyAllCollations, mergeLocalOperatorgraphs = true, queryDistributionMode = EQueryDistributionModeExt.Centralized, useDictionaryInlineEncoding = false, REPLACE_STORE_WITH_VALUES = false))
    }

    @Ignore // Reason: >Bug<
    @Test
    public fun `Simple insert data named 1 - in simulator - PartitionByKeyAllCollations - true - Routing - true - true`() {
        simulatorHelper(MySimulatorConfig(predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByKeyAllCollations, mergeLocalOperatorgraphs = true, queryDistributionMode = EQueryDistributionModeExt.Routing, useDictionaryInlineEncoding = true, REPLACE_STORE_WITH_VALUES = true))
    }

    @Ignore // Reason: >Bug<
    @Test
    public fun `Simple insert data named 1 - in simulator - PartitionByKeyAllCollations - true - Routing - true - false`() {
        simulatorHelper(MySimulatorConfig(predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByKeyAllCollations, mergeLocalOperatorgraphs = true, queryDistributionMode = EQueryDistributionModeExt.Routing, useDictionaryInlineEncoding = true, REPLACE_STORE_WITH_VALUES = false))
    }

    @Ignore // Reason: >Bug<
    @Test
    public fun `Simple insert data named 1 - in simulator - PartitionByKeyAllCollations - true - Routing - false - true`() {
        simulatorHelper(MySimulatorConfig(predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByKeyAllCollations, mergeLocalOperatorgraphs = true, queryDistributionMode = EQueryDistributionModeExt.Routing, useDictionaryInlineEncoding = false, REPLACE_STORE_WITH_VALUES = true))
    }

    @Ignore // Reason: >Bug<
    @Test
    public fun `Simple insert data named 1 - in simulator - PartitionByKeyAllCollations - true - Routing - false - false`() {
        simulatorHelper(MySimulatorConfig(predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByKeyAllCollations, mergeLocalOperatorgraphs = true, queryDistributionMode = EQueryDistributionModeExt.Routing, useDictionaryInlineEncoding = false, REPLACE_STORE_WITH_VALUES = false))
    }

    @Ignore // Reason: >Bug<
    @Test
    public fun `Simple insert data named 1 - in simulator - PartitionByKeyAllCollations - false - Centralized - true - true`() {
        simulatorHelper(MySimulatorConfig(predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByKeyAllCollations, mergeLocalOperatorgraphs = false, queryDistributionMode = EQueryDistributionModeExt.Centralized, useDictionaryInlineEncoding = true, REPLACE_STORE_WITH_VALUES = true))
    }

    @Ignore // Reason: >Bug<
    @Test
    public fun `Simple insert data named 1 - in simulator - PartitionByKeyAllCollations - false - Centralized - true - false`() {
        simulatorHelper(MySimulatorConfig(predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByKeyAllCollations, mergeLocalOperatorgraphs = false, queryDistributionMode = EQueryDistributionModeExt.Centralized, useDictionaryInlineEncoding = true, REPLACE_STORE_WITH_VALUES = false))
    }

    @Ignore // Reason: >Bug<
    @Test
    public fun `Simple insert data named 1 - in simulator - PartitionByKeyAllCollations - false - Centralized - false - true`() {
        simulatorHelper(MySimulatorConfig(predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByKeyAllCollations, mergeLocalOperatorgraphs = false, queryDistributionMode = EQueryDistributionModeExt.Centralized, useDictionaryInlineEncoding = false, REPLACE_STORE_WITH_VALUES = true))
    }

    @Ignore // Reason: >Bug<
    @Test
    public fun `Simple insert data named 1 - in simulator - PartitionByKeyAllCollations - false - Centralized - false - false`() {
        simulatorHelper(MySimulatorConfig(predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByKeyAllCollations, mergeLocalOperatorgraphs = false, queryDistributionMode = EQueryDistributionModeExt.Centralized, useDictionaryInlineEncoding = false, REPLACE_STORE_WITH_VALUES = false))
    }

    @Ignore // Reason: >Bug<
    @Test
    public fun `Simple insert data named 1 - in simulator - PartitionByKeyAllCollations - false - Routing - true - true`() {
        simulatorHelper(MySimulatorConfig(predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByKeyAllCollations, mergeLocalOperatorgraphs = false, queryDistributionMode = EQueryDistributionModeExt.Routing, useDictionaryInlineEncoding = true, REPLACE_STORE_WITH_VALUES = true))
    }

    @Ignore // Reason: >Bug<
    @Test
    public fun `Simple insert data named 1 - in simulator - PartitionByKeyAllCollations - false - Routing - true - false`() {
        simulatorHelper(MySimulatorConfig(predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByKeyAllCollations, mergeLocalOperatorgraphs = false, queryDistributionMode = EQueryDistributionModeExt.Routing, useDictionaryInlineEncoding = true, REPLACE_STORE_WITH_VALUES = false))
    }

    @Ignore // Reason: >Bug<
    @Test
    public fun `Simple insert data named 1 - in simulator - PartitionByKeyAllCollations - false - Routing - false - true`() {
        simulatorHelper(MySimulatorConfig(predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByKeyAllCollations, mergeLocalOperatorgraphs = false, queryDistributionMode = EQueryDistributionModeExt.Routing, useDictionaryInlineEncoding = false, REPLACE_STORE_WITH_VALUES = true))
    }

    @Ignore // Reason: >Bug<
    @Test
    public fun `Simple insert data named 1 - in simulator - PartitionByKeyAllCollations - false - Routing - false - false`() {
        simulatorHelper(MySimulatorConfig(predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByKeyAllCollations, mergeLocalOperatorgraphs = false, queryDistributionMode = EQueryDistributionModeExt.Routing, useDictionaryInlineEncoding = false, REPLACE_STORE_WITH_VALUES = false))
    }
    public fun simulatorHelper(cfg: MySimulatorConfig) {
        val simRun = SimulationRun()
        val json = simRun.parseConfigFile("../luposdate3000_simulator_iot/src/jvmTest/resources/autoIntegrationTest/test1.json")
        val config = simRun.parseJsonObjects(json)
        config.dbConfig = cfg
        simRun.sim = Simulation(config.getEntities())
        simRun.sim.maxClock = if (simRun.simMaxClock == simRun.notInitializedClock) simRun.sim.maxClock else simRun.simMaxClock
        simRun.sim.steadyClock = if (simRun.simSteadyClock == simRun.notInitializedClock) simRun.sim.steadyClock else simRun.simSteadyClock
        simRun.sim.startUp()
        val instance = (config.devices.filter { it.hasDatabase() }.map { it.database }.filter { it != null }.map { it!!.db }.first() as DatabaseHandle).instance
        val pkg0 = MySimulatorTestingExecute(query)
        val pkg1 = MySimulatorTestingCompareGraphPackage("SELECT ?s ?p ?o WHERE { GRAPH <${outputGraph[0]}> { ?s ?p ?o . }}", MemoryTable.parseFromAny(outputData[0], outputType[0], Query(instance))!!)
        pkg0.onFinish = pkg1
        config.querySenders[0].queryPck = pkg0
        simRun.sim.run()
        simRun.sim.shutDown()
    }
}
