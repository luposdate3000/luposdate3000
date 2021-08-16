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
package lupos.code_gen_test_16
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
import lupos.simulator_db.luposdate3000.MySimulatorTestingImportPackage
import lupos.simulator_iot.SimulationRun
import kotlin.test.Ignore
import kotlin.test.Test
import kotlin.test.fail

public class sq04Subquerywithingraphpatterndefaultgraphdoesnotapply {
    internal val inputData = arrayOf(
        File("src/jvmTest/resources/sq04Subquerywithingraphpatterndefaultgraphdoesnotapply.input").readAsString(),
        File("src/jvmTest/resources/sq04Subquerywithingraphpatterndefaultgraphdoesnotapply.input1").readAsString(),
    )
    internal val inputGraph = arrayOf(
        "",
        "sq01.rdf",
    )
    internal val inputType = arrayOf(
        ".rdf",
        ".rdf",
    )
    internal val targetData = File("src/jvmTest/resources/sq04Subquerywithingraphpatterndefaultgraphdoesnotapply.output").readAsString()
    internal val targetType = ".srx"
    internal val query = "select ?x  \n" +
        "where { \n" +
        "graph ?g { \n" +
        "{select * where {?x ?p ?y}} \n" +
        "} \n" +
        "} \n" +
        ""

    @Ignore // Reason: >using not implemented feature<
    @Test
    public fun `sq04  Subquery within graph pattern default graph does not apply`() {
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
        val actual2 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator2, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected2 = MemoryTable.parseFromAny(targetData, targetType, Query(instance))!!
        val buf_err2 = MyPrintWriter()
        if (!expected2.equalsVerbose(actual2, true, true, buf_err2)) {
            fail(expected2.toString() + " .. " + actual2.toString() + " .. " + buf_err2.toString() + " .. " + operator2)
        }
        LuposdateEndpoint.close(instance)
    }

    @Ignore // Reason: >using not implemented feature<
    @Test
    public fun `sq04  Subquery within graph pattern default graph does not apply - in simulator - PartitionByIDTwiceAllCollations - true - Centralized - true`() {
        simulatorHelper(
            mutableMapOf(
                "predefinedPartitionScheme" to "PartitionByIDTwiceAllCollations",
                "mergeLocalOperatorgraphs" to "true",
                "queryDistributionMode" to "Centralized",
                "useDictionaryInlineEncoding" to "true",
                "REPLACE_STORE_WITH_VALUES" to "false",
            )
        )
    }

    @Ignore // Reason: >using not implemented feature<
    @Test
    public fun `sq04  Subquery within graph pattern default graph does not apply - in simulator - PartitionByIDTwiceAllCollations - true - Centralized - false`() {
        simulatorHelper(
            mutableMapOf(
                "predefinedPartitionScheme" to "PartitionByIDTwiceAllCollations",
                "mergeLocalOperatorgraphs" to "true",
                "queryDistributionMode" to "Centralized",
                "useDictionaryInlineEncoding" to "false",
                "REPLACE_STORE_WITH_VALUES" to "false",
            )
        )
    }

    @Ignore // Reason: >using not implemented feature<
    @Test
    public fun `sq04  Subquery within graph pattern default graph does not apply - in simulator - PartitionByIDTwiceAllCollations - true - Routing - true`() {
        simulatorHelper(
            mutableMapOf(
                "predefinedPartitionScheme" to "PartitionByIDTwiceAllCollations",
                "mergeLocalOperatorgraphs" to "true",
                "queryDistributionMode" to "Routing",
                "useDictionaryInlineEncoding" to "true",
                "REPLACE_STORE_WITH_VALUES" to "false",
            )
        )
    }

    @Ignore // Reason: >using not implemented feature<
    @Test
    public fun `sq04  Subquery within graph pattern default graph does not apply - in simulator - PartitionByIDTwiceAllCollations - true - Routing - false`() {
        simulatorHelper(
            mutableMapOf(
                "predefinedPartitionScheme" to "PartitionByIDTwiceAllCollations",
                "mergeLocalOperatorgraphs" to "true",
                "queryDistributionMode" to "Routing",
                "useDictionaryInlineEncoding" to "false",
                "REPLACE_STORE_WITH_VALUES" to "false",
            )
        )
    }

    @Ignore // Reason: >using not implemented feature<
    @Test
    public fun `sq04  Subquery within graph pattern default graph does not apply - in simulator - PartitionByIDTwiceAllCollations - false - Centralized - true`() {
        simulatorHelper(
            mutableMapOf(
                "predefinedPartitionScheme" to "PartitionByIDTwiceAllCollations",
                "mergeLocalOperatorgraphs" to "false",
                "queryDistributionMode" to "Centralized",
                "useDictionaryInlineEncoding" to "true",
                "REPLACE_STORE_WITH_VALUES" to "false",
            )
        )
    }

    @Ignore // Reason: >using not implemented feature<
    @Test
    public fun `sq04  Subquery within graph pattern default graph does not apply - in simulator - PartitionByIDTwiceAllCollations - false - Centralized - false`() {
        simulatorHelper(
            mutableMapOf(
                "predefinedPartitionScheme" to "PartitionByIDTwiceAllCollations",
                "mergeLocalOperatorgraphs" to "false",
                "queryDistributionMode" to "Centralized",
                "useDictionaryInlineEncoding" to "false",
                "REPLACE_STORE_WITH_VALUES" to "false",
            )
        )
    }

    @Ignore // Reason: >using not implemented feature<
    @Test
    public fun `sq04  Subquery within graph pattern default graph does not apply - in simulator - PartitionByIDTwiceAllCollations - false - Routing - true`() {
        simulatorHelper(
            mutableMapOf(
                "predefinedPartitionScheme" to "PartitionByIDTwiceAllCollations",
                "mergeLocalOperatorgraphs" to "false",
                "queryDistributionMode" to "Routing",
                "useDictionaryInlineEncoding" to "true",
                "REPLACE_STORE_WITH_VALUES" to "false",
            )
        )
    }

    @Ignore // Reason: >using not implemented feature<
    @Test
    public fun `sq04  Subquery within graph pattern default graph does not apply - in simulator - PartitionByIDTwiceAllCollations - false - Routing - false`() {
        simulatorHelper(
            mutableMapOf(
                "predefinedPartitionScheme" to "PartitionByIDTwiceAllCollations",
                "mergeLocalOperatorgraphs" to "false",
                "queryDistributionMode" to "Routing",
                "useDictionaryInlineEncoding" to "false",
                "REPLACE_STORE_WITH_VALUES" to "false",
            )
        )
    }

    @Ignore // Reason: >using not implemented feature<
    @Test
    public fun `sq04  Subquery within graph pattern default graph does not apply - in simulator - PartitionByKeyAllCollations - true - Centralized - true`() {
        simulatorHelper(
            mutableMapOf(
                "predefinedPartitionScheme" to "PartitionByKeyAllCollations",
                "mergeLocalOperatorgraphs" to "true",
                "queryDistributionMode" to "Centralized",
                "useDictionaryInlineEncoding" to "true",
                "REPLACE_STORE_WITH_VALUES" to "false",
            )
        )
    }

    @Ignore // Reason: >using not implemented feature<
    @Test
    public fun `sq04  Subquery within graph pattern default graph does not apply - in simulator - PartitionByKeyAllCollations - true - Centralized - false`() {
        simulatorHelper(
            mutableMapOf(
                "predefinedPartitionScheme" to "PartitionByKeyAllCollations",
                "mergeLocalOperatorgraphs" to "true",
                "queryDistributionMode" to "Centralized",
                "useDictionaryInlineEncoding" to "false",
                "REPLACE_STORE_WITH_VALUES" to "false",
            )
        )
    }

    @Ignore // Reason: >using not implemented feature<
    @Test
    public fun `sq04  Subquery within graph pattern default graph does not apply - in simulator - PartitionByKeyAllCollations - true - Routing - true`() {
        simulatorHelper(
            mutableMapOf(
                "predefinedPartitionScheme" to "PartitionByKeyAllCollations",
                "mergeLocalOperatorgraphs" to "true",
                "queryDistributionMode" to "Routing",
                "useDictionaryInlineEncoding" to "true",
                "REPLACE_STORE_WITH_VALUES" to "false",
            )
        )
    }

    @Ignore // Reason: >using not implemented feature<
    @Test
    public fun `sq04  Subquery within graph pattern default graph does not apply - in simulator - PartitionByKeyAllCollations - true - Routing - false`() {
        simulatorHelper(
            mutableMapOf(
                "predefinedPartitionScheme" to "PartitionByKeyAllCollations",
                "mergeLocalOperatorgraphs" to "true",
                "queryDistributionMode" to "Routing",
                "useDictionaryInlineEncoding" to "false",
                "REPLACE_STORE_WITH_VALUES" to "false",
            )
        )
    }

    @Ignore // Reason: >using not implemented feature<
    @Test
    public fun `sq04  Subquery within graph pattern default graph does not apply - in simulator - PartitionByKeyAllCollations - false - Centralized - true`() {
        simulatorHelper(
            mutableMapOf(
                "predefinedPartitionScheme" to "PartitionByKeyAllCollations",
                "mergeLocalOperatorgraphs" to "false",
                "queryDistributionMode" to "Centralized",
                "useDictionaryInlineEncoding" to "true",
                "REPLACE_STORE_WITH_VALUES" to "false",
            )
        )
    }

    @Ignore // Reason: >using not implemented feature<
    @Test
    public fun `sq04  Subquery within graph pattern default graph does not apply - in simulator - PartitionByKeyAllCollations - false - Centralized - false`() {
        simulatorHelper(
            mutableMapOf(
                "predefinedPartitionScheme" to "PartitionByKeyAllCollations",
                "mergeLocalOperatorgraphs" to "false",
                "queryDistributionMode" to "Centralized",
                "useDictionaryInlineEncoding" to "false",
                "REPLACE_STORE_WITH_VALUES" to "false",
            )
        )
    }

    @Ignore // Reason: >using not implemented feature<
    @Test
    public fun `sq04  Subquery within graph pattern default graph does not apply - in simulator - PartitionByKeyAllCollations - false - Routing - true`() {
        simulatorHelper(
            mutableMapOf(
                "predefinedPartitionScheme" to "PartitionByKeyAllCollations",
                "mergeLocalOperatorgraphs" to "false",
                "queryDistributionMode" to "Routing",
                "useDictionaryInlineEncoding" to "true",
                "REPLACE_STORE_WITH_VALUES" to "false",
            )
        )
    }

    @Ignore // Reason: >using not implemented feature<
    @Test
    public fun `sq04  Subquery within graph pattern default graph does not apply - in simulator - PartitionByKeyAllCollations - false - Routing - false`() {
        simulatorHelper(
            mutableMapOf(
                "predefinedPartitionScheme" to "PartitionByKeyAllCollations",
                "mergeLocalOperatorgraphs" to "false",
                "queryDistributionMode" to "Routing",
                "useDictionaryInlineEncoding" to "false",
                "REPLACE_STORE_WITH_VALUES" to "false",
            )
        )
    }
    public fun simulatorHelper(cfg: MutableMap<String, String>) {
        val simRun = SimulationRun()
        val config = simRun.parseConfig("../luposdate3000_simulator_iot/src/jvmTest/resources/autoIntegrationTest/test1.json", false)
        config.jsonObjects.database.putAll(cfg)
        simRun.sim = Simulation(config.getEntities())
        simRun.sim.maxClock = if (simRun.simMaxClock == simRun.notInitializedClock) simRun.sim.maxClock else simRun.simMaxClock
        simRun.sim.steadyClock = if (simRun.simSteadyClock == simRun.notInitializedClock) simRun.sim.steadyClock else simRun.simSteadyClock
        simRun.sim.startUp()
        val instance = (config.devices.filter { it.hasDatabaseStore }.map { it.database }.filter { it != null }.map { it!!.db }.first() as DatabaseHandle).instance
        val pkg0 = MySimulatorTestingImportPackage(inputData[0], inputGraph[0], inputType[0])
        val pkg1 = MySimulatorTestingImportPackage(inputData[1], inputGraph[1], inputType[1])
        pkg0.onFinish = pkg1
        val pkg2 = MySimulatorTestingCompareGraphPackage("SELECT ?s ?p ?o WHERE { ?s ?p ?o . }", MemoryTable.parseFromAny(inputData[0], inputType[0], Query(instance))!!)
        pkg1.onFinish = pkg2
        val pkg3 = MySimulatorTestingCompareGraphPackage("SELECT ?s ?p ?o WHERE { GRAPH <${inputGraph[1]}> { ?s ?p ?o . }}", MemoryTable.parseFromAny(inputData[1], inputType[1], Query(instance))!!)
        pkg2.onFinish = pkg3
        val pkg4 = MySimulatorTestingCompareGraphPackage(query, MemoryTable.parseFromAny(targetData, targetType, Query(instance))!!)
        pkg3.onFinish = pkg4
        config.querySenders[0].queryPck = pkg0
        simRun.sim.run()
        simRun.sim.shutDown()
    }
}
