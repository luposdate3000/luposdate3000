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
package lupos.code_gen_test
import lupos.endpoint.LuposdateEndpoint
import lupos.operator.arithmetik.noinput.AOPVariable
import lupos.operator.base.Query
import lupos.result_format.EQueryResultToStreamExt
import lupos.shared.EIndexPatternExt
import lupos.shared.MemoryTable
import lupos.shared.inline.File
import lupos.shared.inline.MyPrintWriter
import lupos.simulator_core.Simulation
import lupos.simulator_db.luposdate3000.DatabaseHandle
import lupos.simulator_db.luposdate3000.MySimulatorTestingCompareGraphPackage
import lupos.simulator_db.luposdate3000.MySimulatorTestingExecute
import lupos.simulator_db.luposdate3000.MySimulatorTestingImportPackage
import lupos.simulator_iot.SimulationRun
import kotlin.test.Test
import kotlin.test.fail

public class INSERT04 {
    internal val inputData = arrayOf(
        File("src/jvmTest/resources/INSERT04.input").readAsString(),
        File("src/jvmTest/resources/INSERT04.input1").readAsString(),
    )
    internal val inputGraph = arrayOf(
        "",
        "http://example.org/g1",
    )
    internal val inputType = arrayOf(
        ".ttl",
        ".ttl",
    )
    internal val outputData = arrayOf(
        File("src/jvmTest/resources/INSERT04.output0").readAsString(),
        File("src/jvmTest/resources/INSERT04.output1").readAsString(),
    )
    internal val outputGraph = arrayOf(
        "",
        "http://example.org/g1",
    )
    internal val outputType = arrayOf(
        ".ttl",
        ".ttl",
    )
    internal val query = "PREFIX     : <http://example.org/>  \n" +
        "INSERT { \n" +
        " ?s ?p \"q\" \n" +
        "} \n" +
        "USING :g1 \n" +
        "WHERE { \n" +
        " ?s ?p ?o \n" +
        "} \n" +
        ""

    @Test
    public fun `INSERT 04`() {
        val instance = LuposdateEndpoint.initialize()
        instance.LUPOS_BUFFER_SIZE = 128
        val buf = MyPrintWriter(false)
        if (listOf(".n3", ".ttl", ".nt").contains(inputType[0])) {
            LuposdateEndpoint.importTurtleString(instance, inputData[0], inputGraph[0])
        } else {
            TODO()
        }
        if (listOf(".n3", ".ttl", ".nt").contains(inputType[1])) {
            LuposdateEndpoint.importTurtleString(instance, inputData[1], inputGraph[1])
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
        val query1 = Query(instance)
        val graph1 = instance.tripleStoreManager!!.getGraph(inputGraph[1])
        val operator1 = graph1.getIterator(query1, arrayOf(AOPVariable(query1, "s"), AOPVariable(query1, "p"), AOPVariable(query1, "o")), EIndexPatternExt.SPO)
        val actual1 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator1, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected1 = MemoryTable.parseFromAny(inputData[1], inputType[1], Query(instance))!!
        val buf_err1 = MyPrintWriter()
        if (!expected1.equalsVerbose(actual1, true, true, buf_err1)) {
            fail(expected1.toString() + " .. " + actual1.toString() + " .. " + buf_err1.toString() + " .. " + operator1)
        }
        val operator2 = LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, query)
        LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator2, buf, EQueryResultToStreamExt.EMPTY_STREAM)
        val query3 = Query(instance)
        val graph3 = instance.tripleStoreManager!!.getGraph(outputGraph[0])
        val operator3 = graph3.getIterator(query3, arrayOf(AOPVariable(query3, "s"), AOPVariable(query3, "p"), AOPVariable(query3, "o")), EIndexPatternExt.SPO)
        val actual3 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator3, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected3 = MemoryTable.parseFromAny(outputData[0], outputType[0], Query(instance))!!
        val buf_err3 = MyPrintWriter()
        if (!expected3.equalsVerbose(actual3, true, true, buf_err3)) {
            fail(expected3.toString() + " .. " + actual3.toString() + " .. " + buf_err3.toString() + " .. " + operator3)
        }
        val query4 = Query(instance)
        val graph4 = instance.tripleStoreManager!!.getGraph(outputGraph[1])
        val operator4 = graph4.getIterator(query4, arrayOf(AOPVariable(query4, "s"), AOPVariable(query4, "p"), AOPVariable(query4, "o")), EIndexPatternExt.SPO)
        val actual4 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator4, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected4 = MemoryTable.parseFromAny(outputData[1], outputType[1], Query(instance))!!
        val buf_err4 = MyPrintWriter()
        if (!expected4.equalsVerbose(actual4, true, true, buf_err4)) {
            fail(expected4.toString() + " .. " + actual4.toString() + " .. " + buf_err4.toString() + " .. " + operator4)
        }
        LuposdateEndpoint.close(instance)
    }

    @Test
    public fun `INSERT 04 - in simulator - PartitionByIDTwiceAllCollations - true - Centralized - true - true`() {
        Luposdate3000Config.predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByIDTwiceAllCollations
        Luposdate3000Config.mergeLocalOperatorgraphs = true
        Luposdate3000Config.queryDistributionMode = EQueryDistributionModeExt.Centralized
        Luposdate3000Config.useDictionaryInlineEncoding = true
        Luposdate3000Config.REPLACE_STORE_WITH_VALUES = true
        simulatorHelper()
    }

    @Test
    public fun `INSERT 04 - in simulator - PartitionByIDTwiceAllCollations - true - Centralized - true - false`() {
        Luposdate3000Config.predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByIDTwiceAllCollations
        Luposdate3000Config.mergeLocalOperatorgraphs = true
        Luposdate3000Config.queryDistributionMode = EQueryDistributionModeExt.Centralized
        Luposdate3000Config.useDictionaryInlineEncoding = true
        Luposdate3000Config.REPLACE_STORE_WITH_VALUES = false
        simulatorHelper()
    }

    @Test
    public fun `INSERT 04 - in simulator - PartitionByIDTwiceAllCollations - true - Centralized - false - true`() {
        Luposdate3000Config.predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByIDTwiceAllCollations
        Luposdate3000Config.mergeLocalOperatorgraphs = true
        Luposdate3000Config.queryDistributionMode = EQueryDistributionModeExt.Centralized
        Luposdate3000Config.useDictionaryInlineEncoding = false
        Luposdate3000Config.REPLACE_STORE_WITH_VALUES = true
        simulatorHelper()
    }

    @Test
    public fun `INSERT 04 - in simulator - PartitionByIDTwiceAllCollations - true - Centralized - false - false`() {
        Luposdate3000Config.predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByIDTwiceAllCollations
        Luposdate3000Config.mergeLocalOperatorgraphs = true
        Luposdate3000Config.queryDistributionMode = EQueryDistributionModeExt.Centralized
        Luposdate3000Config.useDictionaryInlineEncoding = false
        Luposdate3000Config.REPLACE_STORE_WITH_VALUES = false
        simulatorHelper()
    }

    @Test
    public fun `INSERT 04 - in simulator - PartitionByIDTwiceAllCollations - true - Routing - true - true`() {
        Luposdate3000Config.predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByIDTwiceAllCollations
        Luposdate3000Config.mergeLocalOperatorgraphs = true
        Luposdate3000Config.queryDistributionMode = EQueryDistributionModeExt.Routing
        Luposdate3000Config.useDictionaryInlineEncoding = true
        Luposdate3000Config.REPLACE_STORE_WITH_VALUES = true
        simulatorHelper()
    }

    @Test
    public fun `INSERT 04 - in simulator - PartitionByIDTwiceAllCollations - true - Routing - true - false`() {
        Luposdate3000Config.predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByIDTwiceAllCollations
        Luposdate3000Config.mergeLocalOperatorgraphs = true
        Luposdate3000Config.queryDistributionMode = EQueryDistributionModeExt.Routing
        Luposdate3000Config.useDictionaryInlineEncoding = true
        Luposdate3000Config.REPLACE_STORE_WITH_VALUES = false
        simulatorHelper()
    }

    @Test
    public fun `INSERT 04 - in simulator - PartitionByIDTwiceAllCollations - true - Routing - false - true`() {
        Luposdate3000Config.predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByIDTwiceAllCollations
        Luposdate3000Config.mergeLocalOperatorgraphs = true
        Luposdate3000Config.queryDistributionMode = EQueryDistributionModeExt.Routing
        Luposdate3000Config.useDictionaryInlineEncoding = false
        Luposdate3000Config.REPLACE_STORE_WITH_VALUES = true
        simulatorHelper()
    }

    @Test
    public fun `INSERT 04 - in simulator - PartitionByIDTwiceAllCollations - true - Routing - false - false`() {
        Luposdate3000Config.predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByIDTwiceAllCollations
        Luposdate3000Config.mergeLocalOperatorgraphs = true
        Luposdate3000Config.queryDistributionMode = EQueryDistributionModeExt.Routing
        Luposdate3000Config.useDictionaryInlineEncoding = false
        Luposdate3000Config.REPLACE_STORE_WITH_VALUES = false
        simulatorHelper()
    }

    @Test
    public fun `INSERT 04 - in simulator - PartitionByIDTwiceAllCollations - false - Centralized - true - true`() {
        Luposdate3000Config.predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByIDTwiceAllCollations
        Luposdate3000Config.mergeLocalOperatorgraphs = false
        Luposdate3000Config.queryDistributionMode = EQueryDistributionModeExt.Centralized
        Luposdate3000Config.useDictionaryInlineEncoding = true
        Luposdate3000Config.REPLACE_STORE_WITH_VALUES = true
        simulatorHelper()
    }

    @Test
    public fun `INSERT 04 - in simulator - PartitionByIDTwiceAllCollations - false - Centralized - true - false`() {
        Luposdate3000Config.predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByIDTwiceAllCollations
        Luposdate3000Config.mergeLocalOperatorgraphs = false
        Luposdate3000Config.queryDistributionMode = EQueryDistributionModeExt.Centralized
        Luposdate3000Config.useDictionaryInlineEncoding = true
        Luposdate3000Config.REPLACE_STORE_WITH_VALUES = false
        simulatorHelper()
    }

    @Test
    public fun `INSERT 04 - in simulator - PartitionByIDTwiceAllCollations - false - Centralized - false - true`() {
        Luposdate3000Config.predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByIDTwiceAllCollations
        Luposdate3000Config.mergeLocalOperatorgraphs = false
        Luposdate3000Config.queryDistributionMode = EQueryDistributionModeExt.Centralized
        Luposdate3000Config.useDictionaryInlineEncoding = false
        Luposdate3000Config.REPLACE_STORE_WITH_VALUES = true
        simulatorHelper()
    }

    @Test
    public fun `INSERT 04 - in simulator - PartitionByIDTwiceAllCollations - false - Centralized - false - false`() {
        Luposdate3000Config.predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByIDTwiceAllCollations
        Luposdate3000Config.mergeLocalOperatorgraphs = false
        Luposdate3000Config.queryDistributionMode = EQueryDistributionModeExt.Centralized
        Luposdate3000Config.useDictionaryInlineEncoding = false
        Luposdate3000Config.REPLACE_STORE_WITH_VALUES = false
        simulatorHelper()
    }

    @Test
    public fun `INSERT 04 - in simulator - PartitionByIDTwiceAllCollations - false - Routing - true - true`() {
        Luposdate3000Config.predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByIDTwiceAllCollations
        Luposdate3000Config.mergeLocalOperatorgraphs = false
        Luposdate3000Config.queryDistributionMode = EQueryDistributionModeExt.Routing
        Luposdate3000Config.useDictionaryInlineEncoding = true
        Luposdate3000Config.REPLACE_STORE_WITH_VALUES = true
        simulatorHelper()
    }

    @Test
    public fun `INSERT 04 - in simulator - PartitionByIDTwiceAllCollations - false - Routing - true - false`() {
        Luposdate3000Config.predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByIDTwiceAllCollations
        Luposdate3000Config.mergeLocalOperatorgraphs = false
        Luposdate3000Config.queryDistributionMode = EQueryDistributionModeExt.Routing
        Luposdate3000Config.useDictionaryInlineEncoding = true
        Luposdate3000Config.REPLACE_STORE_WITH_VALUES = false
        simulatorHelper()
    }

    @Test
    public fun `INSERT 04 - in simulator - PartitionByIDTwiceAllCollations - false - Routing - false - true`() {
        Luposdate3000Config.predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByIDTwiceAllCollations
        Luposdate3000Config.mergeLocalOperatorgraphs = false
        Luposdate3000Config.queryDistributionMode = EQueryDistributionModeExt.Routing
        Luposdate3000Config.useDictionaryInlineEncoding = false
        Luposdate3000Config.REPLACE_STORE_WITH_VALUES = true
        simulatorHelper()
    }

    @Test
    public fun `INSERT 04 - in simulator - PartitionByIDTwiceAllCollations - false - Routing - false - false`() {
        Luposdate3000Config.predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByIDTwiceAllCollations
        Luposdate3000Config.mergeLocalOperatorgraphs = false
        Luposdate3000Config.queryDistributionMode = EQueryDistributionModeExt.Routing
        Luposdate3000Config.useDictionaryInlineEncoding = false
        Luposdate3000Config.REPLACE_STORE_WITH_VALUES = false
        simulatorHelper()
    }

    @Test
    public fun `INSERT 04 - in simulator - PartitionByKeyAllCollations - true - Centralized - true - true`() {
        Luposdate3000Config.predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByKeyAllCollations
        Luposdate3000Config.mergeLocalOperatorgraphs = true
        Luposdate3000Config.queryDistributionMode = EQueryDistributionModeExt.Centralized
        Luposdate3000Config.useDictionaryInlineEncoding = true
        Luposdate3000Config.REPLACE_STORE_WITH_VALUES = true
        simulatorHelper()
    }

    @Test
    public fun `INSERT 04 - in simulator - PartitionByKeyAllCollations - true - Centralized - true - false`() {
        Luposdate3000Config.predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByKeyAllCollations
        Luposdate3000Config.mergeLocalOperatorgraphs = true
        Luposdate3000Config.queryDistributionMode = EQueryDistributionModeExt.Centralized
        Luposdate3000Config.useDictionaryInlineEncoding = true
        Luposdate3000Config.REPLACE_STORE_WITH_VALUES = false
        simulatorHelper()
    }

    @Test
    public fun `INSERT 04 - in simulator - PartitionByKeyAllCollations - true - Centralized - false - true`() {
        Luposdate3000Config.predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByKeyAllCollations
        Luposdate3000Config.mergeLocalOperatorgraphs = true
        Luposdate3000Config.queryDistributionMode = EQueryDistributionModeExt.Centralized
        Luposdate3000Config.useDictionaryInlineEncoding = false
        Luposdate3000Config.REPLACE_STORE_WITH_VALUES = true
        simulatorHelper()
    }

    @Test
    public fun `INSERT 04 - in simulator - PartitionByKeyAllCollations - true - Centralized - false - false`() {
        Luposdate3000Config.predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByKeyAllCollations
        Luposdate3000Config.mergeLocalOperatorgraphs = true
        Luposdate3000Config.queryDistributionMode = EQueryDistributionModeExt.Centralized
        Luposdate3000Config.useDictionaryInlineEncoding = false
        Luposdate3000Config.REPLACE_STORE_WITH_VALUES = false
        simulatorHelper()
    }

    @Test
    public fun `INSERT 04 - in simulator - PartitionByKeyAllCollations - true - Routing - true - true`() {
        Luposdate3000Config.predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByKeyAllCollations
        Luposdate3000Config.mergeLocalOperatorgraphs = true
        Luposdate3000Config.queryDistributionMode = EQueryDistributionModeExt.Routing
        Luposdate3000Config.useDictionaryInlineEncoding = true
        Luposdate3000Config.REPLACE_STORE_WITH_VALUES = true
        simulatorHelper()
    }

    @Test
    public fun `INSERT 04 - in simulator - PartitionByKeyAllCollations - true - Routing - true - false`() {
        Luposdate3000Config.predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByKeyAllCollations
        Luposdate3000Config.mergeLocalOperatorgraphs = true
        Luposdate3000Config.queryDistributionMode = EQueryDistributionModeExt.Routing
        Luposdate3000Config.useDictionaryInlineEncoding = true
        Luposdate3000Config.REPLACE_STORE_WITH_VALUES = false
        simulatorHelper()
    }

    @Test
    public fun `INSERT 04 - in simulator - PartitionByKeyAllCollations - true - Routing - false - true`() {
        Luposdate3000Config.predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByKeyAllCollations
        Luposdate3000Config.mergeLocalOperatorgraphs = true
        Luposdate3000Config.queryDistributionMode = EQueryDistributionModeExt.Routing
        Luposdate3000Config.useDictionaryInlineEncoding = false
        Luposdate3000Config.REPLACE_STORE_WITH_VALUES = true
        simulatorHelper()
    }

    @Test
    public fun `INSERT 04 - in simulator - PartitionByKeyAllCollations - true - Routing - false - false`() {
        Luposdate3000Config.predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByKeyAllCollations
        Luposdate3000Config.mergeLocalOperatorgraphs = true
        Luposdate3000Config.queryDistributionMode = EQueryDistributionModeExt.Routing
        Luposdate3000Config.useDictionaryInlineEncoding = false
        Luposdate3000Config.REPLACE_STORE_WITH_VALUES = false
        simulatorHelper()
    }

    @Test
    public fun `INSERT 04 - in simulator - PartitionByKeyAllCollations - false - Centralized - true - true`() {
        Luposdate3000Config.predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByKeyAllCollations
        Luposdate3000Config.mergeLocalOperatorgraphs = false
        Luposdate3000Config.queryDistributionMode = EQueryDistributionModeExt.Centralized
        Luposdate3000Config.useDictionaryInlineEncoding = true
        Luposdate3000Config.REPLACE_STORE_WITH_VALUES = true
        simulatorHelper()
    }

    @Test
    public fun `INSERT 04 - in simulator - PartitionByKeyAllCollations - false - Centralized - true - false`() {
        Luposdate3000Config.predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByKeyAllCollations
        Luposdate3000Config.mergeLocalOperatorgraphs = false
        Luposdate3000Config.queryDistributionMode = EQueryDistributionModeExt.Centralized
        Luposdate3000Config.useDictionaryInlineEncoding = true
        Luposdate3000Config.REPLACE_STORE_WITH_VALUES = false
        simulatorHelper()
    }

    @Test
    public fun `INSERT 04 - in simulator - PartitionByKeyAllCollations - false - Centralized - false - true`() {
        Luposdate3000Config.predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByKeyAllCollations
        Luposdate3000Config.mergeLocalOperatorgraphs = false
        Luposdate3000Config.queryDistributionMode = EQueryDistributionModeExt.Centralized
        Luposdate3000Config.useDictionaryInlineEncoding = false
        Luposdate3000Config.REPLACE_STORE_WITH_VALUES = true
        simulatorHelper()
    }

    @Test
    public fun `INSERT 04 - in simulator - PartitionByKeyAllCollations - false - Centralized - false - false`() {
        Luposdate3000Config.predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByKeyAllCollations
        Luposdate3000Config.mergeLocalOperatorgraphs = false
        Luposdate3000Config.queryDistributionMode = EQueryDistributionModeExt.Centralized
        Luposdate3000Config.useDictionaryInlineEncoding = false
        Luposdate3000Config.REPLACE_STORE_WITH_VALUES = false
        simulatorHelper()
    }

    @Test
    public fun `INSERT 04 - in simulator - PartitionByKeyAllCollations - false - Routing - true - true`() {
        Luposdate3000Config.predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByKeyAllCollations
        Luposdate3000Config.mergeLocalOperatorgraphs = false
        Luposdate3000Config.queryDistributionMode = EQueryDistributionModeExt.Routing
        Luposdate3000Config.useDictionaryInlineEncoding = true
        Luposdate3000Config.REPLACE_STORE_WITH_VALUES = true
        simulatorHelper()
    }

    @Test
    public fun `INSERT 04 - in simulator - PartitionByKeyAllCollations - false - Routing - true - false`() {
        Luposdate3000Config.predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByKeyAllCollations
        Luposdate3000Config.mergeLocalOperatorgraphs = false
        Luposdate3000Config.queryDistributionMode = EQueryDistributionModeExt.Routing
        Luposdate3000Config.useDictionaryInlineEncoding = true
        Luposdate3000Config.REPLACE_STORE_WITH_VALUES = false
        simulatorHelper()
    }

    @Test
    public fun `INSERT 04 - in simulator - PartitionByKeyAllCollations - false - Routing - false - true`() {
        Luposdate3000Config.predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByKeyAllCollations
        Luposdate3000Config.mergeLocalOperatorgraphs = false
        Luposdate3000Config.queryDistributionMode = EQueryDistributionModeExt.Routing
        Luposdate3000Config.useDictionaryInlineEncoding = false
        Luposdate3000Config.REPLACE_STORE_WITH_VALUES = true
        simulatorHelper()
    }

    @Test
    public fun `INSERT 04 - in simulator - PartitionByKeyAllCollations - false - Routing - false - false`() {
        Luposdate3000Config.predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByKeyAllCollations
        Luposdate3000Config.mergeLocalOperatorgraphs = false
        Luposdate3000Config.queryDistributionMode = EQueryDistributionModeExt.Routing
        Luposdate3000Config.useDictionaryInlineEncoding = false
        Luposdate3000Config.REPLACE_STORE_WITH_VALUES = false
        simulatorHelper()
    }
    public fun simulatorHelper() {
        val simRun = SimulationRun()
        val json = simRun.parseConfigFile("../luposdate3000_simulator_iot/src/jvmTest/resources/autoIntegrationTest/test1.json")
        val config = simRun.parseJsonObjects(json)
        simRun.sim = Simulation(config.getEntities())
        simRun.sim.maxClock = if (simRun.simMaxClock == simRun.notInitializedClock) simRun.sim.maxClock else simRun.simMaxClock
        simRun.sim.steadyClock = if (simRun.simSteadyClock == simRun.notInitializedClock) simRun.sim.steadyClock else simRun.simSteadyClock
        simRun.sim.startUp()
        val instance = (config.devices.filter { it.hasDatabase() }.map { it.database }.filter { it != null }.map { it!!.db }.first() as DatabaseHandle).instance
        val pkg0 = MySimulatorTestingImportPackage(inputData[0], inputGraph[0], inputType[0])
        val pkg1 = MySimulatorTestingImportPackage(inputData[1], inputGraph[1], inputType[1])
        pkg0.onFinish = pkg1
        val pkg2 = MySimulatorTestingCompareGraphPackage("SELECT ?s ?p ?o WHERE { ?s ?p ?o . }", MemoryTable.parseFromAny(inputData[0], inputType[0], Query(instance))!!)
        pkg1.onFinish = pkg2
        val pkg3 = MySimulatorTestingCompareGraphPackage("SELECT ?s ?p ?o WHERE { GRAPH <${inputGraph[1]}> { ?s ?p ?o . }}", MemoryTable.parseFromAny(inputData[1], inputType[1], Query(instance))!!)
        pkg2.onFinish = pkg3
        val pkg4 = MySimulatorTestingExecute(query)
        pkg3.onFinish = pkg4
        val pkg5 = MySimulatorTestingCompareGraphPackage("SELECT ?s ?p ?o WHERE { ?s ?p ?o . }", MemoryTable.parseFromAny(outputData[0], outputType[0], Query(instance))!!)
        pkg4.onFinish = pkg5
        val pkg6 = MySimulatorTestingCompareGraphPackage("SELECT ?s ?p ?o WHERE { GRAPH <${outputGraph[1]}> { ?s ?p ?o . }}", MemoryTable.parseFromAny(outputData[1], outputType[1], Query(instance))!!)
        pkg5.onFinish = pkg6
        config.querySenders[0].queryPck = pkg0
        simRun.sim.run()
        simRun.sim.shutDown()
    }
}
