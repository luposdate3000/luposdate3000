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
import lupos.simulator_db.luposdate3000.MySimulatorTestingImportPackage
import lupos.simulator_iot.SimulationRun
import kotlin.test.Ignore
import kotlin.test.Test
import kotlin.test.fail

public class sq08Subquerywithaggregate {
    internal val inputData = arrayOf(
        File("src/jvmTest/resources/sq08Subquerywithaggregate.input").readAsString(),
    )
    internal val inputGraph = arrayOf(
        "",
    )
    internal val inputType = arrayOf(
        ".rdf",
    )
    internal val targetData = File("src/jvmTest/resources/sq08Subquerywithaggregate.output").readAsString()
    internal val targetType = ".srx"
    internal val query = "prefix ex: <http://www.example.org/schema#> \n" +
        "prefix in: <http://www.example.org/instance#> \n" +
        "select ?x ?max where { \n" +
        "{select (max(?y) as ?max) where {?x ex:p ?y} }  \n" +
        "?x ex:p ?max \n" +
        "} \n" +
        ""

    @Ignore // Reason: >using not implemented feature<
    @Test
    public fun `sq08  Subquery with aggregate`() {
        val instance = LuposdateEndpoint.initialize()
        instance.LUPOS_BUFFER_SIZE = 128
        val buf = MyPrintWriter(false)
        if (listOf(".n3", ".ttl", ".nt").contains(inputType[0])) {
            LuposdateEndpoint.importTurtleString(instance, inputData[0], inputGraph[0])
        } else {
            TODO()
        }
        val query0 = Query(instance)
        val graph0 = instance.tripleStoreManager!!.getGraph(inputGraph[0])
        val operator0 = graph0.getIterator(query0, arrayOf(AOPVariable(query0, "s"), AOPVariable(query0, "p"), AOPVariable(query0, "o")), EIndexPatternExt.SPO)
        val actual0 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator0, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected0 = MemoryTable.parseFromAny(inputData[0], inputType[0], Query(instance))!!
        val buf_err0 = MyPrintWriter()
        if (!expected0.equalsVerbose(actual0, true, true, buf_err0)) {
            fail(expected0.toString() + " .. " + actual0.toString() + " .. " + buf_err0.toString() + " .. " + operator0)
        }
        val operator1 = LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, query)
        val actual1 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator1, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected1 = MemoryTable.parseFromAny(targetData, targetType, Query(instance))!!
        val buf_err1 = MyPrintWriter()
        if (!expected1.equalsVerbose(actual1, true, true, buf_err1)) {
            fail(expected1.toString() + " .. " + actual1.toString() + " .. " + buf_err1.toString() + " .. " + operator1)
        }
        LuposdateEndpoint.close(instance)
    }

    @Ignore // Reason: >using not implemented feature<
    @Test
    public fun `sq08  Subquery with aggregate - in simulator - PartitionByIDTwiceAllCollations - true - Centralized - true - true`() {
        simulatorHelper(MySimulatorConfig(predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByIDTwiceAllCollations, mergeLocalOperatorgraphs = true, queryDistributionMode = EQueryDistributionModeExt.Centralized, useDictionaryInlineEncoding = true, REPLACE_STORE_WITH_VALUES = true))
    }

    @Ignore // Reason: >using not implemented feature<
    @Test
    public fun `sq08  Subquery with aggregate - in simulator - PartitionByIDTwiceAllCollations - true - Centralized - true - false`() {
        simulatorHelper(MySimulatorConfig(predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByIDTwiceAllCollations, mergeLocalOperatorgraphs = true, queryDistributionMode = EQueryDistributionModeExt.Centralized, useDictionaryInlineEncoding = true, REPLACE_STORE_WITH_VALUES = false))
    }

    @Ignore // Reason: >using not implemented feature<
    @Test
    public fun `sq08  Subquery with aggregate - in simulator - PartitionByIDTwiceAllCollations - true - Centralized - false - true`() {
        simulatorHelper(MySimulatorConfig(predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByIDTwiceAllCollations, mergeLocalOperatorgraphs = true, queryDistributionMode = EQueryDistributionModeExt.Centralized, useDictionaryInlineEncoding = false, REPLACE_STORE_WITH_VALUES = true))
    }

    @Ignore // Reason: >using not implemented feature<
    @Test
    public fun `sq08  Subquery with aggregate - in simulator - PartitionByIDTwiceAllCollations - true - Centralized - false - false`() {
        simulatorHelper(MySimulatorConfig(predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByIDTwiceAllCollations, mergeLocalOperatorgraphs = true, queryDistributionMode = EQueryDistributionModeExt.Centralized, useDictionaryInlineEncoding = false, REPLACE_STORE_WITH_VALUES = false))
    }

    @Ignore // Reason: >using not implemented feature<
    @Test
    public fun `sq08  Subquery with aggregate - in simulator - PartitionByIDTwiceAllCollations - true - Routing - true - true`() {
        simulatorHelper(MySimulatorConfig(predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByIDTwiceAllCollations, mergeLocalOperatorgraphs = true, queryDistributionMode = EQueryDistributionModeExt.Routing, useDictionaryInlineEncoding = true, REPLACE_STORE_WITH_VALUES = true))
    }

    @Ignore // Reason: >using not implemented feature<
    @Test
    public fun `sq08  Subquery with aggregate - in simulator - PartitionByIDTwiceAllCollations - true - Routing - true - false`() {
        simulatorHelper(MySimulatorConfig(predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByIDTwiceAllCollations, mergeLocalOperatorgraphs = true, queryDistributionMode = EQueryDistributionModeExt.Routing, useDictionaryInlineEncoding = true, REPLACE_STORE_WITH_VALUES = false))
    }

    @Ignore // Reason: >using not implemented feature<
    @Test
    public fun `sq08  Subquery with aggregate - in simulator - PartitionByIDTwiceAllCollations - true - Routing - false - true`() {
        simulatorHelper(MySimulatorConfig(predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByIDTwiceAllCollations, mergeLocalOperatorgraphs = true, queryDistributionMode = EQueryDistributionModeExt.Routing, useDictionaryInlineEncoding = false, REPLACE_STORE_WITH_VALUES = true))
    }

    @Ignore // Reason: >using not implemented feature<
    @Test
    public fun `sq08  Subquery with aggregate - in simulator - PartitionByIDTwiceAllCollations - true - Routing - false - false`() {
        simulatorHelper(MySimulatorConfig(predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByIDTwiceAllCollations, mergeLocalOperatorgraphs = true, queryDistributionMode = EQueryDistributionModeExt.Routing, useDictionaryInlineEncoding = false, REPLACE_STORE_WITH_VALUES = false))
    }

    @Ignore // Reason: >using not implemented feature<
    @Test
    public fun `sq08  Subquery with aggregate - in simulator - PartitionByIDTwiceAllCollations - false - Centralized - true - true`() {
        simulatorHelper(MySimulatorConfig(predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByIDTwiceAllCollations, mergeLocalOperatorgraphs = false, queryDistributionMode = EQueryDistributionModeExt.Centralized, useDictionaryInlineEncoding = true, REPLACE_STORE_WITH_VALUES = true))
    }

    @Ignore // Reason: >using not implemented feature<
    @Test
    public fun `sq08  Subquery with aggregate - in simulator - PartitionByIDTwiceAllCollations - false - Centralized - true - false`() {
        simulatorHelper(MySimulatorConfig(predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByIDTwiceAllCollations, mergeLocalOperatorgraphs = false, queryDistributionMode = EQueryDistributionModeExt.Centralized, useDictionaryInlineEncoding = true, REPLACE_STORE_WITH_VALUES = false))
    }

    @Ignore // Reason: >using not implemented feature<
    @Test
    public fun `sq08  Subquery with aggregate - in simulator - PartitionByIDTwiceAllCollations - false - Centralized - false - true`() {
        simulatorHelper(MySimulatorConfig(predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByIDTwiceAllCollations, mergeLocalOperatorgraphs = false, queryDistributionMode = EQueryDistributionModeExt.Centralized, useDictionaryInlineEncoding = false, REPLACE_STORE_WITH_VALUES = true))
    }

    @Ignore // Reason: >using not implemented feature<
    @Test
    public fun `sq08  Subquery with aggregate - in simulator - PartitionByIDTwiceAllCollations - false - Centralized - false - false`() {
        simulatorHelper(MySimulatorConfig(predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByIDTwiceAllCollations, mergeLocalOperatorgraphs = false, queryDistributionMode = EQueryDistributionModeExt.Centralized, useDictionaryInlineEncoding = false, REPLACE_STORE_WITH_VALUES = false))
    }

    @Ignore // Reason: >using not implemented feature<
    @Test
    public fun `sq08  Subquery with aggregate - in simulator - PartitionByIDTwiceAllCollations - false - Routing - true - true`() {
        simulatorHelper(MySimulatorConfig(predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByIDTwiceAllCollations, mergeLocalOperatorgraphs = false, queryDistributionMode = EQueryDistributionModeExt.Routing, useDictionaryInlineEncoding = true, REPLACE_STORE_WITH_VALUES = true))
    }

    @Ignore // Reason: >using not implemented feature<
    @Test
    public fun `sq08  Subquery with aggregate - in simulator - PartitionByIDTwiceAllCollations - false - Routing - true - false`() {
        simulatorHelper(MySimulatorConfig(predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByIDTwiceAllCollations, mergeLocalOperatorgraphs = false, queryDistributionMode = EQueryDistributionModeExt.Routing, useDictionaryInlineEncoding = true, REPLACE_STORE_WITH_VALUES = false))
    }

    @Ignore // Reason: >using not implemented feature<
    @Test
    public fun `sq08  Subquery with aggregate - in simulator - PartitionByIDTwiceAllCollations - false - Routing - false - true`() {
        simulatorHelper(MySimulatorConfig(predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByIDTwiceAllCollations, mergeLocalOperatorgraphs = false, queryDistributionMode = EQueryDistributionModeExt.Routing, useDictionaryInlineEncoding = false, REPLACE_STORE_WITH_VALUES = true))
    }

    @Ignore // Reason: >using not implemented feature<
    @Test
    public fun `sq08  Subquery with aggregate - in simulator - PartitionByIDTwiceAllCollations - false - Routing - false - false`() {
        simulatorHelper(MySimulatorConfig(predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByIDTwiceAllCollations, mergeLocalOperatorgraphs = false, queryDistributionMode = EQueryDistributionModeExt.Routing, useDictionaryInlineEncoding = false, REPLACE_STORE_WITH_VALUES = false))
    }

    @Ignore // Reason: >using not implemented feature<
    @Test
    public fun `sq08  Subquery with aggregate - in simulator - PartitionByKeyAllCollations - true - Centralized - true - true`() {
        simulatorHelper(MySimulatorConfig(predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByKeyAllCollations, mergeLocalOperatorgraphs = true, queryDistributionMode = EQueryDistributionModeExt.Centralized, useDictionaryInlineEncoding = true, REPLACE_STORE_WITH_VALUES = true))
    }

    @Ignore // Reason: >using not implemented feature<
    @Test
    public fun `sq08  Subquery with aggregate - in simulator - PartitionByKeyAllCollations - true - Centralized - true - false`() {
        simulatorHelper(MySimulatorConfig(predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByKeyAllCollations, mergeLocalOperatorgraphs = true, queryDistributionMode = EQueryDistributionModeExt.Centralized, useDictionaryInlineEncoding = true, REPLACE_STORE_WITH_VALUES = false))
    }

    @Ignore // Reason: >using not implemented feature<
    @Test
    public fun `sq08  Subquery with aggregate - in simulator - PartitionByKeyAllCollations - true - Centralized - false - true`() {
        simulatorHelper(MySimulatorConfig(predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByKeyAllCollations, mergeLocalOperatorgraphs = true, queryDistributionMode = EQueryDistributionModeExt.Centralized, useDictionaryInlineEncoding = false, REPLACE_STORE_WITH_VALUES = true))
    }

    @Ignore // Reason: >using not implemented feature<
    @Test
    public fun `sq08  Subquery with aggregate - in simulator - PartitionByKeyAllCollations - true - Centralized - false - false`() {
        simulatorHelper(MySimulatorConfig(predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByKeyAllCollations, mergeLocalOperatorgraphs = true, queryDistributionMode = EQueryDistributionModeExt.Centralized, useDictionaryInlineEncoding = false, REPLACE_STORE_WITH_VALUES = false))
    }

    @Ignore // Reason: >using not implemented feature<
    @Test
    public fun `sq08  Subquery with aggregate - in simulator - PartitionByKeyAllCollations - true - Routing - true - true`() {
        simulatorHelper(MySimulatorConfig(predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByKeyAllCollations, mergeLocalOperatorgraphs = true, queryDistributionMode = EQueryDistributionModeExt.Routing, useDictionaryInlineEncoding = true, REPLACE_STORE_WITH_VALUES = true))
    }

    @Ignore // Reason: >using not implemented feature<
    @Test
    public fun `sq08  Subquery with aggregate - in simulator - PartitionByKeyAllCollations - true - Routing - true - false`() {
        simulatorHelper(MySimulatorConfig(predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByKeyAllCollations, mergeLocalOperatorgraphs = true, queryDistributionMode = EQueryDistributionModeExt.Routing, useDictionaryInlineEncoding = true, REPLACE_STORE_WITH_VALUES = false))
    }

    @Ignore // Reason: >using not implemented feature<
    @Test
    public fun `sq08  Subquery with aggregate - in simulator - PartitionByKeyAllCollations - true - Routing - false - true`() {
        simulatorHelper(MySimulatorConfig(predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByKeyAllCollations, mergeLocalOperatorgraphs = true, queryDistributionMode = EQueryDistributionModeExt.Routing, useDictionaryInlineEncoding = false, REPLACE_STORE_WITH_VALUES = true))
    }

    @Ignore // Reason: >using not implemented feature<
    @Test
    public fun `sq08  Subquery with aggregate - in simulator - PartitionByKeyAllCollations - true - Routing - false - false`() {
        simulatorHelper(MySimulatorConfig(predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByKeyAllCollations, mergeLocalOperatorgraphs = true, queryDistributionMode = EQueryDistributionModeExt.Routing, useDictionaryInlineEncoding = false, REPLACE_STORE_WITH_VALUES = false))
    }

    @Ignore // Reason: >using not implemented feature<
    @Test
    public fun `sq08  Subquery with aggregate - in simulator - PartitionByKeyAllCollations - false - Centralized - true - true`() {
        simulatorHelper(MySimulatorConfig(predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByKeyAllCollations, mergeLocalOperatorgraphs = false, queryDistributionMode = EQueryDistributionModeExt.Centralized, useDictionaryInlineEncoding = true, REPLACE_STORE_WITH_VALUES = true))
    }

    @Ignore // Reason: >using not implemented feature<
    @Test
    public fun `sq08  Subquery with aggregate - in simulator - PartitionByKeyAllCollations - false - Centralized - true - false`() {
        simulatorHelper(MySimulatorConfig(predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByKeyAllCollations, mergeLocalOperatorgraphs = false, queryDistributionMode = EQueryDistributionModeExt.Centralized, useDictionaryInlineEncoding = true, REPLACE_STORE_WITH_VALUES = false))
    }

    @Ignore // Reason: >using not implemented feature<
    @Test
    public fun `sq08  Subquery with aggregate - in simulator - PartitionByKeyAllCollations - false - Centralized - false - true`() {
        simulatorHelper(MySimulatorConfig(predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByKeyAllCollations, mergeLocalOperatorgraphs = false, queryDistributionMode = EQueryDistributionModeExt.Centralized, useDictionaryInlineEncoding = false, REPLACE_STORE_WITH_VALUES = true))
    }

    @Ignore // Reason: >using not implemented feature<
    @Test
    public fun `sq08  Subquery with aggregate - in simulator - PartitionByKeyAllCollations - false - Centralized - false - false`() {
        simulatorHelper(MySimulatorConfig(predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByKeyAllCollations, mergeLocalOperatorgraphs = false, queryDistributionMode = EQueryDistributionModeExt.Centralized, useDictionaryInlineEncoding = false, REPLACE_STORE_WITH_VALUES = false))
    }

    @Ignore // Reason: >using not implemented feature<
    @Test
    public fun `sq08  Subquery with aggregate - in simulator - PartitionByKeyAllCollations - false - Routing - true - true`() {
        simulatorHelper(MySimulatorConfig(predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByKeyAllCollations, mergeLocalOperatorgraphs = false, queryDistributionMode = EQueryDistributionModeExt.Routing, useDictionaryInlineEncoding = true, REPLACE_STORE_WITH_VALUES = true))
    }

    @Ignore // Reason: >using not implemented feature<
    @Test
    public fun `sq08  Subquery with aggregate - in simulator - PartitionByKeyAllCollations - false - Routing - true - false`() {
        simulatorHelper(MySimulatorConfig(predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByKeyAllCollations, mergeLocalOperatorgraphs = false, queryDistributionMode = EQueryDistributionModeExt.Routing, useDictionaryInlineEncoding = true, REPLACE_STORE_WITH_VALUES = false))
    }

    @Ignore // Reason: >using not implemented feature<
    @Test
    public fun `sq08  Subquery with aggregate - in simulator - PartitionByKeyAllCollations - false - Routing - false - true`() {
        simulatorHelper(MySimulatorConfig(predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByKeyAllCollations, mergeLocalOperatorgraphs = false, queryDistributionMode = EQueryDistributionModeExt.Routing, useDictionaryInlineEncoding = false, REPLACE_STORE_WITH_VALUES = true))
    }

    @Ignore // Reason: >using not implemented feature<
    @Test
    public fun `sq08  Subquery with aggregate - in simulator - PartitionByKeyAllCollations - false - Routing - false - false`() {
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
        val pkg0 = MySimulatorTestingImportPackage(inputData[0], inputGraph[0], inputType[0])
        val pkg1 = MySimulatorTestingCompareGraphPackage("SELECT ?s ?p ?o WHERE { ?s ?p ?o . }", MemoryTable.parseFromAny(inputData[0], inputType[0], Query(instance))!!)
        pkg0.onFinish = pkg1
        val pkg2 = MySimulatorTestingCompareGraphPackage(query, MemoryTable.parseFromAny(targetData, targetType, Query(instance))!!)
        pkg1.onFinish = pkg2
        config.querySenders[0].queryPck = pkg0
        simRun.sim.run()
        simRun.sim.shutDown()
    }
}