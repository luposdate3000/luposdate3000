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
import lupos.shared.EQueryDistributionModeExt
import lupos.shared.Luposdate3000Config
import lupos.shared.MemoryTable
import lupos.shared.EPredefinedPartitionSchemesExt
import lupos.shared.inline.File
import lupos.shared.inline.MyPrintWriter
import lupos.simulator_core.Simulation
import lupos.simulator_db.luposdate3000.MySimulatorTestingCompareGraphPackage
import lupos.simulator_db.luposdate3000.MySimulatorTestingImportPackage
import lupos.simulator_db.luposdate3000.MySimulatorTestingExecute
import lupos.simulator_db.luposdate3000.DatabaseHandle
import lupos.simulator_iot.config.Configuration
import lupos.simulator_iot.log.Logger
import lupos.simulator_db.luposdate3000.MySimulatorConfig
import lupos.simulator_iot.SimulationRun

import kotlin.test.Ignore
import kotlin.test.Test
import kotlin.test.fail

public class constructwhere04CONSTRUCTWHERE {
    internal val targetData = File("src/jvmTest/resources/constructwhere04CONSTRUCTWHERE.output").readAsString()
    internal val targetType = ".ttl"
    internal val query = "PREFIX : <http://example.org/> \n" +
        "CONSTRUCT  \n" +
        "FROM <data.ttl> \n" +
        "WHERE { ?s ?p ?o }"

    @Ignore // Reason: >Bug in SparqlTestSuiteConverterToUnitTest<
    @Test
    public fun `constructwhere04  CONSTRUCT WHERE`() {
        val instance = LuposdateEndpoint.initialize()
        instance.LUPOS_BUFFER_SIZE = 128
        val buf = MyPrintWriter(false)
        val operator0 = LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, query)
        val actual0 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator0, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected0 = MemoryTable.parseFromAny(targetData, targetType, Query(instance))!!
        val buf_err0 = MyPrintWriter()
        if (!expected0.equalsVerbose(actual0, true, true, buf_err0)) {
            fail(expected0.toString() + " .. " + actual0.toString() + " .. " + buf_err0.toString() + " .. " + operator0)
        }
        LuposdateEndpoint.close(instance)
    }
    @Ignore // Reason: >Bug in SparqlTestSuiteConverterToUnitTest<
    @Test
    public fun `constructwhere04  CONSTRUCT WHERE - in simulator - PartitionByIDTwiceAllCollations - true - Centralized - true - true`() {
        simulatorHelper(MySimulatorConfig(predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByIDTwiceAllCollations,mergeLocalOperatorgraphs = true,queryDistributionMode = EQueryDistributionModeExt.Centralized,useDictionaryInlineEncoding = true,REPLACE_STORE_WITH_VALUES = true))
    }
    @Ignore // Reason: >Bug in SparqlTestSuiteConverterToUnitTest<
    @Test
    public fun `constructwhere04  CONSTRUCT WHERE - in simulator - PartitionByIDTwiceAllCollations - true - Centralized - true - false`() {
        simulatorHelper(MySimulatorConfig(predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByIDTwiceAllCollations,mergeLocalOperatorgraphs = true,queryDistributionMode = EQueryDistributionModeExt.Centralized,useDictionaryInlineEncoding = true,REPLACE_STORE_WITH_VALUES = false))
    }
    @Ignore // Reason: >Bug in SparqlTestSuiteConverterToUnitTest<
    @Test
    public fun `constructwhere04  CONSTRUCT WHERE - in simulator - PartitionByIDTwiceAllCollations - true - Centralized - false - true`() {
        simulatorHelper(MySimulatorConfig(predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByIDTwiceAllCollations,mergeLocalOperatorgraphs = true,queryDistributionMode = EQueryDistributionModeExt.Centralized,useDictionaryInlineEncoding = false,REPLACE_STORE_WITH_VALUES = true))
    }
    @Ignore // Reason: >Bug in SparqlTestSuiteConverterToUnitTest<
    @Test
    public fun `constructwhere04  CONSTRUCT WHERE - in simulator - PartitionByIDTwiceAllCollations - true - Centralized - false - false`() {
        simulatorHelper(MySimulatorConfig(predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByIDTwiceAllCollations,mergeLocalOperatorgraphs = true,queryDistributionMode = EQueryDistributionModeExt.Centralized,useDictionaryInlineEncoding = false,REPLACE_STORE_WITH_VALUES = false))
    }
    @Ignore // Reason: >Bug in SparqlTestSuiteConverterToUnitTest<
    @Test
    public fun `constructwhere04  CONSTRUCT WHERE - in simulator - PartitionByIDTwiceAllCollations - true - Routing - true - true`() {
        simulatorHelper(MySimulatorConfig(predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByIDTwiceAllCollations,mergeLocalOperatorgraphs = true,queryDistributionMode = EQueryDistributionModeExt.Routing,useDictionaryInlineEncoding = true,REPLACE_STORE_WITH_VALUES = true))
    }
    @Ignore // Reason: >Bug in SparqlTestSuiteConverterToUnitTest<
    @Test
    public fun `constructwhere04  CONSTRUCT WHERE - in simulator - PartitionByIDTwiceAllCollations - true - Routing - true - false`() {
        simulatorHelper(MySimulatorConfig(predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByIDTwiceAllCollations,mergeLocalOperatorgraphs = true,queryDistributionMode = EQueryDistributionModeExt.Routing,useDictionaryInlineEncoding = true,REPLACE_STORE_WITH_VALUES = false))
    }
    @Ignore // Reason: >Bug in SparqlTestSuiteConverterToUnitTest<
    @Test
    public fun `constructwhere04  CONSTRUCT WHERE - in simulator - PartitionByIDTwiceAllCollations - true - Routing - false - true`() {
        simulatorHelper(MySimulatorConfig(predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByIDTwiceAllCollations,mergeLocalOperatorgraphs = true,queryDistributionMode = EQueryDistributionModeExt.Routing,useDictionaryInlineEncoding = false,REPLACE_STORE_WITH_VALUES = true))
    }
    @Ignore // Reason: >Bug in SparqlTestSuiteConverterToUnitTest<
    @Test
    public fun `constructwhere04  CONSTRUCT WHERE - in simulator - PartitionByIDTwiceAllCollations - true - Routing - false - false`() {
        simulatorHelper(MySimulatorConfig(predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByIDTwiceAllCollations,mergeLocalOperatorgraphs = true,queryDistributionMode = EQueryDistributionModeExt.Routing,useDictionaryInlineEncoding = false,REPLACE_STORE_WITH_VALUES = false))
    }
    @Ignore // Reason: >Bug in SparqlTestSuiteConverterToUnitTest<
    @Test
    public fun `constructwhere04  CONSTRUCT WHERE - in simulator - PartitionByIDTwiceAllCollations - false - Centralized - true - true`() {
        simulatorHelper(MySimulatorConfig(predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByIDTwiceAllCollations,mergeLocalOperatorgraphs = false,queryDistributionMode = EQueryDistributionModeExt.Centralized,useDictionaryInlineEncoding = true,REPLACE_STORE_WITH_VALUES = true))
    }
    @Ignore // Reason: >Bug in SparqlTestSuiteConverterToUnitTest<
    @Test
    public fun `constructwhere04  CONSTRUCT WHERE - in simulator - PartitionByIDTwiceAllCollations - false - Centralized - true - false`() {
        simulatorHelper(MySimulatorConfig(predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByIDTwiceAllCollations,mergeLocalOperatorgraphs = false,queryDistributionMode = EQueryDistributionModeExt.Centralized,useDictionaryInlineEncoding = true,REPLACE_STORE_WITH_VALUES = false))
    }
    @Ignore // Reason: >Bug in SparqlTestSuiteConverterToUnitTest<
    @Test
    public fun `constructwhere04  CONSTRUCT WHERE - in simulator - PartitionByIDTwiceAllCollations - false - Centralized - false - true`() {
        simulatorHelper(MySimulatorConfig(predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByIDTwiceAllCollations,mergeLocalOperatorgraphs = false,queryDistributionMode = EQueryDistributionModeExt.Centralized,useDictionaryInlineEncoding = false,REPLACE_STORE_WITH_VALUES = true))
    }
    @Ignore // Reason: >Bug in SparqlTestSuiteConverterToUnitTest<
    @Test
    public fun `constructwhere04  CONSTRUCT WHERE - in simulator - PartitionByIDTwiceAllCollations - false - Centralized - false - false`() {
        simulatorHelper(MySimulatorConfig(predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByIDTwiceAllCollations,mergeLocalOperatorgraphs = false,queryDistributionMode = EQueryDistributionModeExt.Centralized,useDictionaryInlineEncoding = false,REPLACE_STORE_WITH_VALUES = false))
    }
    @Ignore // Reason: >Bug in SparqlTestSuiteConverterToUnitTest<
    @Test
    public fun `constructwhere04  CONSTRUCT WHERE - in simulator - PartitionByIDTwiceAllCollations - false - Routing - true - true`() {
        simulatorHelper(MySimulatorConfig(predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByIDTwiceAllCollations,mergeLocalOperatorgraphs = false,queryDistributionMode = EQueryDistributionModeExt.Routing,useDictionaryInlineEncoding = true,REPLACE_STORE_WITH_VALUES = true))
    }
    @Ignore // Reason: >Bug in SparqlTestSuiteConverterToUnitTest<
    @Test
    public fun `constructwhere04  CONSTRUCT WHERE - in simulator - PartitionByIDTwiceAllCollations - false - Routing - true - false`() {
        simulatorHelper(MySimulatorConfig(predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByIDTwiceAllCollations,mergeLocalOperatorgraphs = false,queryDistributionMode = EQueryDistributionModeExt.Routing,useDictionaryInlineEncoding = true,REPLACE_STORE_WITH_VALUES = false))
    }
    @Ignore // Reason: >Bug in SparqlTestSuiteConverterToUnitTest<
    @Test
    public fun `constructwhere04  CONSTRUCT WHERE - in simulator - PartitionByIDTwiceAllCollations - false - Routing - false - true`() {
        simulatorHelper(MySimulatorConfig(predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByIDTwiceAllCollations,mergeLocalOperatorgraphs = false,queryDistributionMode = EQueryDistributionModeExt.Routing,useDictionaryInlineEncoding = false,REPLACE_STORE_WITH_VALUES = true))
    }
    @Ignore // Reason: >Bug in SparqlTestSuiteConverterToUnitTest<
    @Test
    public fun `constructwhere04  CONSTRUCT WHERE - in simulator - PartitionByIDTwiceAllCollations - false - Routing - false - false`() {
        simulatorHelper(MySimulatorConfig(predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByIDTwiceAllCollations,mergeLocalOperatorgraphs = false,queryDistributionMode = EQueryDistributionModeExt.Routing,useDictionaryInlineEncoding = false,REPLACE_STORE_WITH_VALUES = false))
    }
    @Ignore // Reason: >Bug in SparqlTestSuiteConverterToUnitTest<
    @Test
    public fun `constructwhere04  CONSTRUCT WHERE - in simulator - PartitionByKeyAllCollations - true - Centralized - true - true`() {
        simulatorHelper(MySimulatorConfig(predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByKeyAllCollations,mergeLocalOperatorgraphs = true,queryDistributionMode = EQueryDistributionModeExt.Centralized,useDictionaryInlineEncoding = true,REPLACE_STORE_WITH_VALUES = true))
    }
    @Ignore // Reason: >Bug in SparqlTestSuiteConverterToUnitTest<
    @Test
    public fun `constructwhere04  CONSTRUCT WHERE - in simulator - PartitionByKeyAllCollations - true - Centralized - true - false`() {
        simulatorHelper(MySimulatorConfig(predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByKeyAllCollations,mergeLocalOperatorgraphs = true,queryDistributionMode = EQueryDistributionModeExt.Centralized,useDictionaryInlineEncoding = true,REPLACE_STORE_WITH_VALUES = false))
    }
    @Ignore // Reason: >Bug in SparqlTestSuiteConverterToUnitTest<
    @Test
    public fun `constructwhere04  CONSTRUCT WHERE - in simulator - PartitionByKeyAllCollations - true - Centralized - false - true`() {
        simulatorHelper(MySimulatorConfig(predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByKeyAllCollations,mergeLocalOperatorgraphs = true,queryDistributionMode = EQueryDistributionModeExt.Centralized,useDictionaryInlineEncoding = false,REPLACE_STORE_WITH_VALUES = true))
    }
    @Ignore // Reason: >Bug in SparqlTestSuiteConverterToUnitTest<
    @Test
    public fun `constructwhere04  CONSTRUCT WHERE - in simulator - PartitionByKeyAllCollations - true - Centralized - false - false`() {
        simulatorHelper(MySimulatorConfig(predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByKeyAllCollations,mergeLocalOperatorgraphs = true,queryDistributionMode = EQueryDistributionModeExt.Centralized,useDictionaryInlineEncoding = false,REPLACE_STORE_WITH_VALUES = false))
    }
    @Ignore // Reason: >Bug in SparqlTestSuiteConverterToUnitTest<
    @Test
    public fun `constructwhere04  CONSTRUCT WHERE - in simulator - PartitionByKeyAllCollations - true - Routing - true - true`() {
        simulatorHelper(MySimulatorConfig(predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByKeyAllCollations,mergeLocalOperatorgraphs = true,queryDistributionMode = EQueryDistributionModeExt.Routing,useDictionaryInlineEncoding = true,REPLACE_STORE_WITH_VALUES = true))
    }
    @Ignore // Reason: >Bug in SparqlTestSuiteConverterToUnitTest<
    @Test
    public fun `constructwhere04  CONSTRUCT WHERE - in simulator - PartitionByKeyAllCollations - true - Routing - true - false`() {
        simulatorHelper(MySimulatorConfig(predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByKeyAllCollations,mergeLocalOperatorgraphs = true,queryDistributionMode = EQueryDistributionModeExt.Routing,useDictionaryInlineEncoding = true,REPLACE_STORE_WITH_VALUES = false))
    }
    @Ignore // Reason: >Bug in SparqlTestSuiteConverterToUnitTest<
    @Test
    public fun `constructwhere04  CONSTRUCT WHERE - in simulator - PartitionByKeyAllCollations - true - Routing - false - true`() {
        simulatorHelper(MySimulatorConfig(predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByKeyAllCollations,mergeLocalOperatorgraphs = true,queryDistributionMode = EQueryDistributionModeExt.Routing,useDictionaryInlineEncoding = false,REPLACE_STORE_WITH_VALUES = true))
    }
    @Ignore // Reason: >Bug in SparqlTestSuiteConverterToUnitTest<
    @Test
    public fun `constructwhere04  CONSTRUCT WHERE - in simulator - PartitionByKeyAllCollations - true - Routing - false - false`() {
        simulatorHelper(MySimulatorConfig(predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByKeyAllCollations,mergeLocalOperatorgraphs = true,queryDistributionMode = EQueryDistributionModeExt.Routing,useDictionaryInlineEncoding = false,REPLACE_STORE_WITH_VALUES = false))
    }
    @Ignore // Reason: >Bug in SparqlTestSuiteConverterToUnitTest<
    @Test
    public fun `constructwhere04  CONSTRUCT WHERE - in simulator - PartitionByKeyAllCollations - false - Centralized - true - true`() {
        simulatorHelper(MySimulatorConfig(predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByKeyAllCollations,mergeLocalOperatorgraphs = false,queryDistributionMode = EQueryDistributionModeExt.Centralized,useDictionaryInlineEncoding = true,REPLACE_STORE_WITH_VALUES = true))
    }
    @Ignore // Reason: >Bug in SparqlTestSuiteConverterToUnitTest<
    @Test
    public fun `constructwhere04  CONSTRUCT WHERE - in simulator - PartitionByKeyAllCollations - false - Centralized - true - false`() {
        simulatorHelper(MySimulatorConfig(predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByKeyAllCollations,mergeLocalOperatorgraphs = false,queryDistributionMode = EQueryDistributionModeExt.Centralized,useDictionaryInlineEncoding = true,REPLACE_STORE_WITH_VALUES = false))
    }
    @Ignore // Reason: >Bug in SparqlTestSuiteConverterToUnitTest<
    @Test
    public fun `constructwhere04  CONSTRUCT WHERE - in simulator - PartitionByKeyAllCollations - false - Centralized - false - true`() {
        simulatorHelper(MySimulatorConfig(predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByKeyAllCollations,mergeLocalOperatorgraphs = false,queryDistributionMode = EQueryDistributionModeExt.Centralized,useDictionaryInlineEncoding = false,REPLACE_STORE_WITH_VALUES = true))
    }
    @Ignore // Reason: >Bug in SparqlTestSuiteConverterToUnitTest<
    @Test
    public fun `constructwhere04  CONSTRUCT WHERE - in simulator - PartitionByKeyAllCollations - false - Centralized - false - false`() {
        simulatorHelper(MySimulatorConfig(predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByKeyAllCollations,mergeLocalOperatorgraphs = false,queryDistributionMode = EQueryDistributionModeExt.Centralized,useDictionaryInlineEncoding = false,REPLACE_STORE_WITH_VALUES = false))
    }
    @Ignore // Reason: >Bug in SparqlTestSuiteConverterToUnitTest<
    @Test
    public fun `constructwhere04  CONSTRUCT WHERE - in simulator - PartitionByKeyAllCollations - false - Routing - true - true`() {
        simulatorHelper(MySimulatorConfig(predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByKeyAllCollations,mergeLocalOperatorgraphs = false,queryDistributionMode = EQueryDistributionModeExt.Routing,useDictionaryInlineEncoding = true,REPLACE_STORE_WITH_VALUES = true))
    }
    @Ignore // Reason: >Bug in SparqlTestSuiteConverterToUnitTest<
    @Test
    public fun `constructwhere04  CONSTRUCT WHERE - in simulator - PartitionByKeyAllCollations - false - Routing - true - false`() {
        simulatorHelper(MySimulatorConfig(predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByKeyAllCollations,mergeLocalOperatorgraphs = false,queryDistributionMode = EQueryDistributionModeExt.Routing,useDictionaryInlineEncoding = true,REPLACE_STORE_WITH_VALUES = false))
    }
    @Ignore // Reason: >Bug in SparqlTestSuiteConverterToUnitTest<
    @Test
    public fun `constructwhere04  CONSTRUCT WHERE - in simulator - PartitionByKeyAllCollations - false - Routing - false - true`() {
        simulatorHelper(MySimulatorConfig(predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByKeyAllCollations,mergeLocalOperatorgraphs = false,queryDistributionMode = EQueryDistributionModeExt.Routing,useDictionaryInlineEncoding = false,REPLACE_STORE_WITH_VALUES = true))
    }
    @Ignore // Reason: >Bug in SparqlTestSuiteConverterToUnitTest<
    @Test
    public fun `constructwhere04  CONSTRUCT WHERE - in simulator - PartitionByKeyAllCollations - false - Routing - false - false`() {
        simulatorHelper(MySimulatorConfig(predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByKeyAllCollations,mergeLocalOperatorgraphs = false,queryDistributionMode = EQueryDistributionModeExt.Routing,useDictionaryInlineEncoding = false,REPLACE_STORE_WITH_VALUES = false))
    }
    public fun simulatorHelper(cfg:MySimulatorConfig) {
        val simRun = SimulationRun()
        val json=simRun.parseConfigFile("../luposdate3000_simulator_iot/src/jvmTest/resources/autoIntegrationTest/test1.json")
        val config = simRun.parseJsonObjects(json)
        config.dbConfig=cfg
        simRun.sim = Simulation(config.getEntities())
        simRun.sim.maxClock = if (simRun.simMaxClock == simRun.notInitializedClock) simRun.sim.maxClock else simRun.simMaxClock
        simRun.sim.steadyClock = if (simRun.simSteadyClock == simRun.notInitializedClock) simRun.sim.steadyClock else simRun.simSteadyClock
        simRun.sim.startUp()
        val instance=(config.devices.filter { it.hasDatabase() }.map{it.database}.filter{it!=null}.map{it!!.db}.first() as DatabaseHandle).instance
        val pkg0 = MySimulatorTestingCompareGraphPackage(query,MemoryTable.parseFromAny(targetData, targetType, Query(instance))!!)
        config.querySenders[0].queryPck = pkg0
        simRun.sim.run()
        simRun.sim.shutDown()
    }
}
