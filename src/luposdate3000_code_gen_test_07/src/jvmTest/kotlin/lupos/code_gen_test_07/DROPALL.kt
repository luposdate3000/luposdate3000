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
import lupos.endpoint.LuposdateEndpoint
import lupos.operator.arithmetik.noinput.AOPVariable
import lupos.operator.base.Query
import lupos.result_format.EQueryResultToStreamExt
import lupos.shared.EIndexPatternExt
import lupos.shared.EPartitionModeExt
import lupos.shared.EPredefinedPartitionSchemesExt
import lupos.shared.Luposdate3000Instance
import lupos.shared.MemoryTable
import lupos.shared.inline.File
import lupos.shared.inline.MyPrintWriter
import lupos.simulator_core.Simulation
import lupos.simulator_db.luposdate3000.DatabaseHandle
import lupos.simulator_db.luposdate3000.MySimulatorTestingCompareGraphPackage
import lupos.simulator_db.luposdate3000.MySimulatorTestingImportPackage
import lupos.simulator_iot.SimulationRun
import kotlin.test.Test
import kotlin.test.fail

public class DROPALL {
    internal val inputData = arrayOf(
        File("src/jvmTest/resources/DROPALL.input").readAsString(),
        File("src/jvmTest/resources/DROPALL.input1").readAsString(),
        File("src/jvmTest/resources/DROPALL.input2").readAsString(),
    )
    internal val inputGraph = arrayOf(
        "",
        "http://example.org/g1",
        "http://example.org/g2",
    )
    internal val inputType = arrayOf(
        ".ttl",
        ".ttl",
        ".ttl",
    )
    internal val query = "PREFIX     : <http://example.org/>  \n" +
        "DROP ALL \n" +
        ""

    @Test
    public fun `DROP ALL - None - Simple - true`() {
        var instance = Luposdate3000Instance()
        try {
            instance.LUPOS_BUFFER_SIZE = 128
            instance.LUPOS_PARTITION_MODE = EPartitionModeExt.None
            instance.predefinedPartitionScheme = EPredefinedPartitionSchemesExt.Simple
            instance.useDictionaryInlineEncoding = true
            instance = LuposdateEndpoint.initializeB(instance)
            normalHelper(instance)
        } finally {
            LuposdateEndpoint.close(instance)
        }
    }

    @Test
    public fun `DROP ALL - None - Simple - false`() {
        var instance = Luposdate3000Instance()
        try {
            instance.LUPOS_BUFFER_SIZE = 128
            instance.LUPOS_PARTITION_MODE = EPartitionModeExt.None
            instance.predefinedPartitionScheme = EPredefinedPartitionSchemesExt.Simple
            instance.useDictionaryInlineEncoding = false
            instance = LuposdateEndpoint.initializeB(instance)
            normalHelper(instance)
        } finally {
            LuposdateEndpoint.close(instance)
        }
    }

    @Test
    public fun `DROP ALL - Thread - Simple - true`() {
        var instance = Luposdate3000Instance()
        try {
            instance.LUPOS_BUFFER_SIZE = 128
            instance.LUPOS_PARTITION_MODE = EPartitionModeExt.Thread
            instance.predefinedPartitionScheme = EPredefinedPartitionSchemesExt.Simple
            instance.useDictionaryInlineEncoding = true
            instance = LuposdateEndpoint.initializeB(instance)
            normalHelper(instance)
        } finally {
            LuposdateEndpoint.close(instance)
        }
    }

    @Test
    public fun `DROP ALL - Thread - Simple - false`() {
        var instance = Luposdate3000Instance()
        try {
            instance.LUPOS_BUFFER_SIZE = 128
            instance.LUPOS_PARTITION_MODE = EPartitionModeExt.Thread
            instance.predefinedPartitionScheme = EPredefinedPartitionSchemesExt.Simple
            instance.useDictionaryInlineEncoding = false
            instance = LuposdateEndpoint.initializeB(instance)
            normalHelper(instance)
        } finally {
            LuposdateEndpoint.close(instance)
        }
    }

    @Test
    public fun `DROP ALL - in simulator - Simple - Centralized - true - None`() {
        simulatorHelper(
            "../luposdate3000_simulator_iot/src/jvmTest/resources/autoIntegrationTest/test2.json",
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
    public fun `DROP ALL - in simulator - Simple - Centralized - false - None`() {
        simulatorHelper(
            "../luposdate3000_simulator_iot/src/jvmTest/resources/autoIntegrationTest/test2.json",
            mutableMapOf(
                "predefinedPartitionScheme" to "Simple",
                "mergeLocalOperatorgraphs" to true,
                "queryDistributionMode" to "Centralized",
                "useDictionaryInlineEncoding" to false,
                "REPLACE_STORE_WITH_VALUES" to false,
                "LUPOS_PARTITION_MODE" to "None",
            )
        )
    }

    @Test
    public fun `DROP ALL - in simulator - PartitionByIDTwiceAllCollations - Centralized - true - Process`() {
        simulatorHelper(
            "../luposdate3000_simulator_iot/src/jvmTest/resources/autoIntegrationTest/test1.json",
            mutableMapOf(
                "predefinedPartitionScheme" to "PartitionByIDTwiceAllCollations",
                "mergeLocalOperatorgraphs" to true,
                "queryDistributionMode" to "Centralized",
                "useDictionaryInlineEncoding" to true,
                "REPLACE_STORE_WITH_VALUES" to false,
                "LUPOS_PARTITION_MODE" to "Process",
            )
        )
    }

    @Test
    public fun `DROP ALL - in simulator - PartitionByIDTwiceAllCollations - Centralized - false - Process`() {
        simulatorHelper(
            "../luposdate3000_simulator_iot/src/jvmTest/resources/autoIntegrationTest/test1.json",
            mutableMapOf(
                "predefinedPartitionScheme" to "PartitionByIDTwiceAllCollations",
                "mergeLocalOperatorgraphs" to true,
                "queryDistributionMode" to "Centralized",
                "useDictionaryInlineEncoding" to false,
                "REPLACE_STORE_WITH_VALUES" to false,
                "LUPOS_PARTITION_MODE" to "Process",
            )
        )
    }

    @Test
    public fun `DROP ALL - in simulator - PartitionByIDTwiceAllCollations - Routing - false - Process`() {
        simulatorHelper(
            "../luposdate3000_simulator_iot/src/jvmTest/resources/autoIntegrationTest/test1.json",
            mutableMapOf(
                "predefinedPartitionScheme" to "PartitionByIDTwiceAllCollations",
                "mergeLocalOperatorgraphs" to true,
                "queryDistributionMode" to "Routing",
                "useDictionaryInlineEncoding" to false,
                "REPLACE_STORE_WITH_VALUES" to false,
                "LUPOS_PARTITION_MODE" to "Process",
            )
        )
    }

    @Test
    public fun `DROP ALL - in simulator - PartitionByID_1_AllCollations - Centralized - true - Process`() {
        simulatorHelper(
            "../luposdate3000_simulator_iot/src/jvmTest/resources/autoIntegrationTest/test1.json",
            mutableMapOf(
                "predefinedPartitionScheme" to "PartitionByID_1_AllCollations",
                "mergeLocalOperatorgraphs" to true,
                "queryDistributionMode" to "Centralized",
                "useDictionaryInlineEncoding" to true,
                "REPLACE_STORE_WITH_VALUES" to false,
                "LUPOS_PARTITION_MODE" to "Process",
            )
        )
    }

    @Test
    public fun `DROP ALL - in simulator - PartitionByID_1_AllCollations - Centralized - false - Process`() {
        simulatorHelper(
            "../luposdate3000_simulator_iot/src/jvmTest/resources/autoIntegrationTest/test1.json",
            mutableMapOf(
                "predefinedPartitionScheme" to "PartitionByID_1_AllCollations",
                "mergeLocalOperatorgraphs" to true,
                "queryDistributionMode" to "Centralized",
                "useDictionaryInlineEncoding" to false,
                "REPLACE_STORE_WITH_VALUES" to false,
                "LUPOS_PARTITION_MODE" to "Process",
            )
        )
    }

    @Test
    public fun `DROP ALL - in simulator - PartitionByID_1_AllCollations - Routing - true - Process`() {
        simulatorHelper(
            "../luposdate3000_simulator_iot/src/jvmTest/resources/autoIntegrationTest/test1.json",
            mutableMapOf(
                "predefinedPartitionScheme" to "PartitionByID_1_AllCollations",
                "mergeLocalOperatorgraphs" to true,
                "queryDistributionMode" to "Routing",
                "useDictionaryInlineEncoding" to true,
                "REPLACE_STORE_WITH_VALUES" to false,
                "LUPOS_PARTITION_MODE" to "Process",
            )
        )
    }

    @Test
    public fun `DROP ALL - in simulator - PartitionByID_1_AllCollations - Routing - false - Process`() {
        simulatorHelper(
            "../luposdate3000_simulator_iot/src/jvmTest/resources/autoIntegrationTest/test1.json",
            mutableMapOf(
                "predefinedPartitionScheme" to "PartitionByID_1_AllCollations",
                "mergeLocalOperatorgraphs" to true,
                "queryDistributionMode" to "Routing",
                "useDictionaryInlineEncoding" to false,
                "REPLACE_STORE_WITH_VALUES" to false,
                "LUPOS_PARTITION_MODE" to "Process",
            )
        )
    }

    @Test
    public fun `DROP ALL - in simulator - PartitionByID_2_AllCollations - Centralized - true - Process`() {
        simulatorHelper(
            "../luposdate3000_simulator_iot/src/jvmTest/resources/autoIntegrationTest/test1.json",
            mutableMapOf(
                "predefinedPartitionScheme" to "PartitionByID_2_AllCollations",
                "mergeLocalOperatorgraphs" to true,
                "queryDistributionMode" to "Centralized",
                "useDictionaryInlineEncoding" to true,
                "REPLACE_STORE_WITH_VALUES" to false,
                "LUPOS_PARTITION_MODE" to "Process",
            )
        )
    }

    @Test
    public fun `DROP ALL - in simulator - PartitionByID_2_AllCollations - Centralized - false - Process`() {
        simulatorHelper(
            "../luposdate3000_simulator_iot/src/jvmTest/resources/autoIntegrationTest/test1.json",
            mutableMapOf(
                "predefinedPartitionScheme" to "PartitionByID_2_AllCollations",
                "mergeLocalOperatorgraphs" to true,
                "queryDistributionMode" to "Centralized",
                "useDictionaryInlineEncoding" to false,
                "REPLACE_STORE_WITH_VALUES" to false,
                "LUPOS_PARTITION_MODE" to "Process",
            )
        )
    }

    @Test
    public fun `DROP ALL - in simulator - PartitionByID_2_AllCollations - Routing - true - Process`() {
        simulatorHelper(
            "../luposdate3000_simulator_iot/src/jvmTest/resources/autoIntegrationTest/test1.json",
            mutableMapOf(
                "predefinedPartitionScheme" to "PartitionByID_2_AllCollations",
                "mergeLocalOperatorgraphs" to true,
                "queryDistributionMode" to "Routing",
                "useDictionaryInlineEncoding" to true,
                "REPLACE_STORE_WITH_VALUES" to false,
                "LUPOS_PARTITION_MODE" to "Process",
            )
        )
    }

    @Test
    public fun `DROP ALL - in simulator - PartitionByID_2_AllCollations - Routing - false - Process`() {
        simulatorHelper(
            "../luposdate3000_simulator_iot/src/jvmTest/resources/autoIntegrationTest/test1.json",
            mutableMapOf(
                "predefinedPartitionScheme" to "PartitionByID_2_AllCollations",
                "mergeLocalOperatorgraphs" to true,
                "queryDistributionMode" to "Routing",
                "useDictionaryInlineEncoding" to false,
                "REPLACE_STORE_WITH_VALUES" to false,
                "LUPOS_PARTITION_MODE" to "Process",
            )
        )
    }

    @Test
    public fun `DROP ALL - in simulator - PartitionByID_O_AllCollations - Centralized - true - Process`() {
        simulatorHelper(
            "../luposdate3000_simulator_iot/src/jvmTest/resources/autoIntegrationTest/test1.json",
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
    public fun `DROP ALL - in simulator - PartitionByID_O_AllCollations - Centralized - false - Process`() {
        simulatorHelper(
            "../luposdate3000_simulator_iot/src/jvmTest/resources/autoIntegrationTest/test1.json",
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
    public fun `DROP ALL - in simulator - PartitionByID_O_AllCollations - Routing - true - Process`() {
        simulatorHelper(
            "../luposdate3000_simulator_iot/src/jvmTest/resources/autoIntegrationTest/test1.json",
            mutableMapOf(
                "predefinedPartitionScheme" to "PartitionByID_O_AllCollations",
                "mergeLocalOperatorgraphs" to true,
                "queryDistributionMode" to "Routing",
                "useDictionaryInlineEncoding" to true,
                "REPLACE_STORE_WITH_VALUES" to false,
                "LUPOS_PARTITION_MODE" to "Process",
            )
        )
    }

    @Test
    public fun `DROP ALL - in simulator - PartitionByID_O_AllCollations - Routing - false - Process`() {
        simulatorHelper(
            "../luposdate3000_simulator_iot/src/jvmTest/resources/autoIntegrationTest/test1.json",
            mutableMapOf(
                "predefinedPartitionScheme" to "PartitionByID_O_AllCollations",
                "mergeLocalOperatorgraphs" to true,
                "queryDistributionMode" to "Routing",
                "useDictionaryInlineEncoding" to false,
                "REPLACE_STORE_WITH_VALUES" to false,
                "LUPOS_PARTITION_MODE" to "Process",
            )
        )
    }

    @Test
    public fun `DROP ALL - in simulator - PartitionByID_S_AllCollations - Centralized - false - Process`() {
        simulatorHelper(
            "../luposdate3000_simulator_iot/src/jvmTest/resources/autoIntegrationTest/test1.json",
            mutableMapOf(
                "predefinedPartitionScheme" to "PartitionByID_S_AllCollations",
                "mergeLocalOperatorgraphs" to true,
                "queryDistributionMode" to "Centralized",
                "useDictionaryInlineEncoding" to false,
                "REPLACE_STORE_WITH_VALUES" to false,
                "LUPOS_PARTITION_MODE" to "Process",
            )
        )
    }

    @Test
    public fun `DROP ALL - in simulator - PartitionByID_S_AllCollations - Routing - false - Process`() {
        simulatorHelper(
            "../luposdate3000_simulator_iot/src/jvmTest/resources/autoIntegrationTest/test1.json",
            mutableMapOf(
                "predefinedPartitionScheme" to "PartitionByID_S_AllCollations",
                "mergeLocalOperatorgraphs" to true,
                "queryDistributionMode" to "Routing",
                "useDictionaryInlineEncoding" to false,
                "REPLACE_STORE_WITH_VALUES" to false,
                "LUPOS_PARTITION_MODE" to "Process",
            )
        )
    }

    @Test
    public fun `DROP ALL - in simulator - PartitionByKeyAllCollations - Centralized - true - Process`() {
        simulatorHelper(
            "../luposdate3000_simulator_iot/src/jvmTest/resources/autoIntegrationTest/test1.json",
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
    public fun `DROP ALL - in simulator - PartitionByKeyAllCollations - Centralized - false - Process`() {
        simulatorHelper(
            "../luposdate3000_simulator_iot/src/jvmTest/resources/autoIntegrationTest/test1.json",
            mutableMapOf(
                "predefinedPartitionScheme" to "PartitionByKeyAllCollations",
                "mergeLocalOperatorgraphs" to true,
                "queryDistributionMode" to "Centralized",
                "useDictionaryInlineEncoding" to false,
                "REPLACE_STORE_WITH_VALUES" to false,
                "LUPOS_PARTITION_MODE" to "Process",
            )
        )
    }

    @Test
    public fun `DROP ALL - in simulator - PartitionByKeyAllCollations - Routing - true - Process`() {
        simulatorHelper(
            "../luposdate3000_simulator_iot/src/jvmTest/resources/autoIntegrationTest/test1.json",
            mutableMapOf(
                "predefinedPartitionScheme" to "PartitionByKeyAllCollations",
                "mergeLocalOperatorgraphs" to true,
                "queryDistributionMode" to "Routing",
                "useDictionaryInlineEncoding" to true,
                "REPLACE_STORE_WITH_VALUES" to false,
                "LUPOS_PARTITION_MODE" to "Process",
            )
        )
    }

    @Test
    public fun `DROP ALL - in simulator - PartitionByKeyAllCollations - Routing - false - Process`() {
        simulatorHelper(
            "../luposdate3000_simulator_iot/src/jvmTest/resources/autoIntegrationTest/test1.json",
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
    public fun `DROP ALL - in simulator - PartitionByIDTwiceAllCollations - Centralized - true - Thread`() {
        simulatorHelper(
            "../luposdate3000_simulator_iot/src/jvmTest/resources/autoIntegrationTest/test2.json",
            mutableMapOf(
                "predefinedPartitionScheme" to "PartitionByIDTwiceAllCollations",
                "mergeLocalOperatorgraphs" to true,
                "queryDistributionMode" to "Centralized",
                "useDictionaryInlineEncoding" to true,
                "REPLACE_STORE_WITH_VALUES" to false,
                "LUPOS_PARTITION_MODE" to "Thread",
            )
        )
    }

    @Test
    public fun `DROP ALL - in simulator - PartitionByIDTwiceAllCollations - Centralized - false - Thread`() {
        simulatorHelper(
            "../luposdate3000_simulator_iot/src/jvmTest/resources/autoIntegrationTest/test2.json",
            mutableMapOf(
                "predefinedPartitionScheme" to "PartitionByIDTwiceAllCollations",
                "mergeLocalOperatorgraphs" to true,
                "queryDistributionMode" to "Centralized",
                "useDictionaryInlineEncoding" to false,
                "REPLACE_STORE_WITH_VALUES" to false,
                "LUPOS_PARTITION_MODE" to "Thread",
            )
        )
    }

    @Test
    public fun `DROP ALL - in simulator - PartitionByID_1_AllCollations - Centralized - true - Thread`() {
        simulatorHelper(
            "../luposdate3000_simulator_iot/src/jvmTest/resources/autoIntegrationTest/test2.json",
            mutableMapOf(
                "predefinedPartitionScheme" to "PartitionByID_1_AllCollations",
                "mergeLocalOperatorgraphs" to true,
                "queryDistributionMode" to "Centralized",
                "useDictionaryInlineEncoding" to true,
                "REPLACE_STORE_WITH_VALUES" to false,
                "LUPOS_PARTITION_MODE" to "Thread",
            )
        )
    }

    @Test
    public fun `DROP ALL - in simulator - PartitionByID_1_AllCollations - Centralized - false - Thread`() {
        simulatorHelper(
            "../luposdate3000_simulator_iot/src/jvmTest/resources/autoIntegrationTest/test2.json",
            mutableMapOf(
                "predefinedPartitionScheme" to "PartitionByID_1_AllCollations",
                "mergeLocalOperatorgraphs" to true,
                "queryDistributionMode" to "Centralized",
                "useDictionaryInlineEncoding" to false,
                "REPLACE_STORE_WITH_VALUES" to false,
                "LUPOS_PARTITION_MODE" to "Thread",
            )
        )
    }

    @Test
    public fun `DROP ALL - in simulator - PartitionByID_2_AllCollations - Centralized - true - Thread`() {
        simulatorHelper(
            "../luposdate3000_simulator_iot/src/jvmTest/resources/autoIntegrationTest/test2.json",
            mutableMapOf(
                "predefinedPartitionScheme" to "PartitionByID_2_AllCollations",
                "mergeLocalOperatorgraphs" to true,
                "queryDistributionMode" to "Centralized",
                "useDictionaryInlineEncoding" to true,
                "REPLACE_STORE_WITH_VALUES" to false,
                "LUPOS_PARTITION_MODE" to "Thread",
            )
        )
    }

    @Test
    public fun `DROP ALL - in simulator - PartitionByID_2_AllCollations - Centralized - false - Thread`() {
        simulatorHelper(
            "../luposdate3000_simulator_iot/src/jvmTest/resources/autoIntegrationTest/test2.json",
            mutableMapOf(
                "predefinedPartitionScheme" to "PartitionByID_2_AllCollations",
                "mergeLocalOperatorgraphs" to true,
                "queryDistributionMode" to "Centralized",
                "useDictionaryInlineEncoding" to false,
                "REPLACE_STORE_WITH_VALUES" to false,
                "LUPOS_PARTITION_MODE" to "Thread",
            )
        )
    }

    @Test
    public fun `DROP ALL - in simulator - PartitionByID_O_AllCollations - Centralized - true - Thread`() {
        simulatorHelper(
            "../luposdate3000_simulator_iot/src/jvmTest/resources/autoIntegrationTest/test2.json",
            mutableMapOf(
                "predefinedPartitionScheme" to "PartitionByID_O_AllCollations",
                "mergeLocalOperatorgraphs" to true,
                "queryDistributionMode" to "Centralized",
                "useDictionaryInlineEncoding" to true,
                "REPLACE_STORE_WITH_VALUES" to false,
                "LUPOS_PARTITION_MODE" to "Thread",
            )
        )
    }

    @Test
    public fun `DROP ALL - in simulator - PartitionByID_O_AllCollations - Centralized - false - Thread`() {
        simulatorHelper(
            "../luposdate3000_simulator_iot/src/jvmTest/resources/autoIntegrationTest/test2.json",
            mutableMapOf(
                "predefinedPartitionScheme" to "PartitionByID_O_AllCollations",
                "mergeLocalOperatorgraphs" to true,
                "queryDistributionMode" to "Centralized",
                "useDictionaryInlineEncoding" to false,
                "REPLACE_STORE_WITH_VALUES" to false,
                "LUPOS_PARTITION_MODE" to "Thread",
            )
        )
    }

    @Test
    public fun `DROP ALL - in simulator - PartitionByID_S_AllCollations - Centralized - true - Thread`() {
        simulatorHelper(
            "../luposdate3000_simulator_iot/src/jvmTest/resources/autoIntegrationTest/test2.json",
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
    public fun `DROP ALL - in simulator - PartitionByID_S_AllCollations - Centralized - false - Thread`() {
        simulatorHelper(
            "../luposdate3000_simulator_iot/src/jvmTest/resources/autoIntegrationTest/test2.json",
            mutableMapOf(
                "predefinedPartitionScheme" to "PartitionByID_S_AllCollations",
                "mergeLocalOperatorgraphs" to true,
                "queryDistributionMode" to "Centralized",
                "useDictionaryInlineEncoding" to false,
                "REPLACE_STORE_WITH_VALUES" to false,
                "LUPOS_PARTITION_MODE" to "Thread",
            )
        )
    }

    @Test
    public fun `DROP ALL - in simulator - PartitionByKeyAllCollations - Centralized - true - Thread`() {
        simulatorHelper(
            "../luposdate3000_simulator_iot/src/jvmTest/resources/autoIntegrationTest/test2.json",
            mutableMapOf(
                "predefinedPartitionScheme" to "PartitionByKeyAllCollations",
                "mergeLocalOperatorgraphs" to true,
                "queryDistributionMode" to "Centralized",
                "useDictionaryInlineEncoding" to true,
                "REPLACE_STORE_WITH_VALUES" to false,
                "LUPOS_PARTITION_MODE" to "Thread",
            )
        )
    }

    @Test
    public fun `DROP ALL - in simulator - PartitionByKeyAllCollations - Centralized - false - Thread`() {
        simulatorHelper(
            "../luposdate3000_simulator_iot/src/jvmTest/resources/autoIntegrationTest/test2.json",
            mutableMapOf(
                "predefinedPartitionScheme" to "PartitionByKeyAllCollations",
                "mergeLocalOperatorgraphs" to true,
                "queryDistributionMode" to "Centralized",
                "useDictionaryInlineEncoding" to false,
                "REPLACE_STORE_WITH_VALUES" to false,
                "LUPOS_PARTITION_MODE" to "Thread",
            )
        )
    }

    @Test
    public fun `DROP ALL - in simulator - Simple - Centralized - true - Thread`() {
        simulatorHelper(
            "../luposdate3000_simulator_iot/src/jvmTest/resources/autoIntegrationTest/test2.json",
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

    @Test
    public fun `DROP ALL - in simulator - Simple - Centralized - false - Thread`() {
        simulatorHelper(
            "../luposdate3000_simulator_iot/src/jvmTest/resources/autoIntegrationTest/test2.json",
            mutableMapOf(
                "predefinedPartitionScheme" to "Simple",
                "mergeLocalOperatorgraphs" to true,
                "queryDistributionMode" to "Centralized",
                "useDictionaryInlineEncoding" to false,
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
        val pkg0 = MySimulatorTestingImportPackage(inputData[0], inputGraph[0], inputType[0])
        val pkg1 = MySimulatorTestingImportPackage(inputData[1], inputGraph[1], inputType[1])
        pkg0.setOnFinish(pkg1)
        val pkg2 = MySimulatorTestingImportPackage(inputData[2], inputGraph[2], inputType[2])
        pkg1.setOnFinish(pkg2)
        var verifyExecuted3 = 0
        val pkg3 = MySimulatorTestingCompareGraphPackage(null, MemoryTable.parseFromAny(inputData[0], inputType[0], Query(instance))!!, { verifyExecuted3++ }, inputGraph[0], instance)
        pkg2.setOnFinish(pkg3)
        var verifyExecuted4 = 0
        val pkg4 = MySimulatorTestingCompareGraphPackage(null, MemoryTable.parseFromAny(inputData[1], inputType[1], Query(instance))!!, { verifyExecuted4++ }, inputGraph[1], instance)
        pkg3.setOnFinish(pkg4)
        var verifyExecuted5 = 0
        val pkg5 = MySimulatorTestingCompareGraphPackage(null, MemoryTable.parseFromAny(inputData[2], inputType[2], Query(instance))!!, { verifyExecuted5++ }, inputGraph[2], instance)
        pkg4.setOnFinish(pkg5)
        config.querySenders[0].queryPck = pkg0
        simRun.sim.run()
        simRun.sim.shutDown()
        if (verifyExecuted3 == 0) {
            fail("pck3 not verified")
        }
        if (verifyExecuted4 == 0) {
            fail("pck4 not verified")
        }
        if (verifyExecuted5 == 0) {
            fail("pck5 not verified")
        }
    }
    internal fun normalHelper(instance: Luposdate3000Instance) {
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
        if (listOf(".n3", ".ttl", ".nt").contains(inputType[2])) {
            LuposdateEndpoint.importTurtleString(instance, inputData[2], inputGraph[2])
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
        val query2 = Query(instance)
        val graph2 = instance.tripleStoreManager!!.getGraph(inputGraph[2])
        val operator2 = graph2.getIterator(query2, arrayOf(AOPVariable(query2, "s"), AOPVariable(query2, "p"), AOPVariable(query2, "o")), EIndexPatternExt.SPO)
        val actual2 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator2, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected2 = MemoryTable.parseFromAny(inputData[2], inputType[2], Query(instance))!!
        val buf_err2 = MyPrintWriter()
        if (!expected2.equalsVerbose(actual2, true, true, buf_err2)) {
            fail(expected2.toString() + " .. " + actual2.toString() + " .. " + buf_err2.toString() + " .. " + operator2)
        }
        val operator3 = LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, query)
    }
}