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
import lupos.simulator_iot.SimulationRun
import kotlin.test.Ignore
import kotlin.test.Test
import kotlin.test.fail

public class INSERTingthesamebnodewithINSERTDATAintotwodifferentGraphsisthesamebnode {
    internal val outputData = arrayOf(
        File("src/jvmTest/resources/INSERTingthesamebnodewithINSERTDATAintotwodifferentGraphsisthesamebnode.output0").readAsString(),
    )
    internal val outputGraph = arrayOf(
        "http://example.org/g3",
    )
    internal val outputType = arrayOf(
        ".ttl",
    )
    internal val query = "PREFIX : <http://example.org/> \n" +
        "# starting with an empty graph store, \n" +
        "# insert the same bnode in two different graphs... \n" +
        "INSERT DATA { GRAPH :g1  { _:b :p :o }  \n" +
        "              GRAPH :g2  { _:b :p :o } } ; \n" +
        "# ... then copy g1 to g2 ... \n" +
        "INSERT { GRAPH :g2  { ?S ?P ?O } } \n" +
        " WHERE { GRAPH :g1  { ?S ?P ?O } } ; \n" +
        "# ... by which the number of triples in  \n" +
        "# g2 should not increase \n" +
        "INSERT { GRAPH :g3 { :s :p ?count } } \n" +
        "WHERE { \n" +
        " SELECT (COUNT(*) AS ?count) WHERE { \n" +
        "  GRAPH :g2 { ?s ?p ?o } \n" +
        " } \n" +
        "} ; \n" +
        "DROP GRAPH :g1 ; \n" +
        "DROP GRAPH :g2 \n" +
        ""

    @Ignore // Reason: >Bug<
    @Test
    public fun `INSERTing the same bnode with INSERT DATA into two different Graphs is the same bnode`() {
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
    public fun `INSERTing the same bnode with INSERT DATA into two different Graphs is the same bnode - in simulator - PartitionByIDTwiceAllCollations - false - Centralized - true - None`() {
        simulatorHelper(
            "../luposdate3000_simulator_iot/src/jvmTest/resources/autoIntegrationTest/test2.json",
            mutableMapOf(
                "predefinedPartitionScheme" to "PartitionByIDTwiceAllCollations",
                "mergeLocalOperatorgraphs" to false,
                "queryDistributionMode" to "Centralized",
                "useDictionaryInlineEncoding" to true,
                "REPLACE_STORE_WITH_VALUES" to false,
                "LUPOS_PARTITION_MODE" to "None",
            )
        )
    }

    @Ignore // Reason: >Bug<
    @Test
    public fun `INSERTing the same bnode with INSERT DATA into two different Graphs is the same bnode - in simulator - PartitionByIDTwiceAllCollations - false - Centralized - false - None`() {
        simulatorHelper(
            "../luposdate3000_simulator_iot/src/jvmTest/resources/autoIntegrationTest/test2.json",
            mutableMapOf(
                "predefinedPartitionScheme" to "PartitionByIDTwiceAllCollations",
                "mergeLocalOperatorgraphs" to false,
                "queryDistributionMode" to "Centralized",
                "useDictionaryInlineEncoding" to false,
                "REPLACE_STORE_WITH_VALUES" to false,
                "LUPOS_PARTITION_MODE" to "None",
            )
        )
    }

    @Ignore // Reason: >Bug<
    @Test
    public fun `INSERTing the same bnode with INSERT DATA into two different Graphs is the same bnode - in simulator - PartitionByKeyAllCollations - false - Centralized - true - None`() {
        simulatorHelper(
            "../luposdate3000_simulator_iot/src/jvmTest/resources/autoIntegrationTest/test2.json",
            mutableMapOf(
                "predefinedPartitionScheme" to "PartitionByKeyAllCollations",
                "mergeLocalOperatorgraphs" to false,
                "queryDistributionMode" to "Centralized",
                "useDictionaryInlineEncoding" to true,
                "REPLACE_STORE_WITH_VALUES" to false,
                "LUPOS_PARTITION_MODE" to "None",
            )
        )
    }

    @Ignore // Reason: >Bug<
    @Test
    public fun `INSERTing the same bnode with INSERT DATA into two different Graphs is the same bnode - in simulator - PartitionByKeyAllCollations - false - Centralized - false - None`() {
        simulatorHelper(
            "../luposdate3000_simulator_iot/src/jvmTest/resources/autoIntegrationTest/test2.json",
            mutableMapOf(
                "predefinedPartitionScheme" to "PartitionByKeyAllCollations",
                "mergeLocalOperatorgraphs" to false,
                "queryDistributionMode" to "Centralized",
                "useDictionaryInlineEncoding" to false,
                "REPLACE_STORE_WITH_VALUES" to false,
                "LUPOS_PARTITION_MODE" to "None",
            )
        )
    }

    @Ignore // Reason: >Bug<
    @Test
    public fun `INSERTing the same bnode with INSERT DATA into two different Graphs is the same bnode - in simulator - Simple - false - Centralized - true - None`() {
        simulatorHelper(
            "../luposdate3000_simulator_iot/src/jvmTest/resources/autoIntegrationTest/test2.json",
            mutableMapOf(
                "predefinedPartitionScheme" to "Simple",
                "mergeLocalOperatorgraphs" to false,
                "queryDistributionMode" to "Centralized",
                "useDictionaryInlineEncoding" to true,
                "REPLACE_STORE_WITH_VALUES" to false,
                "LUPOS_PARTITION_MODE" to "None",
            )
        )
    }

    @Ignore // Reason: >Bug<
    @Test
    public fun `INSERTing the same bnode with INSERT DATA into two different Graphs is the same bnode - in simulator - Simple - false - Centralized - false - None`() {
        simulatorHelper(
            "../luposdate3000_simulator_iot/src/jvmTest/resources/autoIntegrationTest/test2.json",
            mutableMapOf(
                "predefinedPartitionScheme" to "Simple",
                "mergeLocalOperatorgraphs" to false,
                "queryDistributionMode" to "Centralized",
                "useDictionaryInlineEncoding" to false,
                "REPLACE_STORE_WITH_VALUES" to false,
                "LUPOS_PARTITION_MODE" to "None",
            )
        )
    }

    @Ignore // Reason: >Bug<
    @Test
    public fun `INSERTing the same bnode with INSERT DATA into two different Graphs is the same bnode - in simulator - PartitionByIDTwiceAllCollations - true - Centralized - true - Process`() {
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

    @Ignore // Reason: >Bug<
    @Test
    public fun `INSERTing the same bnode with INSERT DATA into two different Graphs is the same bnode - in simulator - PartitionByIDTwiceAllCollations - true - Centralized - false - Process`() {
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

    @Ignore // Reason: >Bug<
    @Test
    public fun `INSERTing the same bnode with INSERT DATA into two different Graphs is the same bnode - in simulator - PartitionByIDTwiceAllCollations - true - Routing - true - Process`() {
        simulatorHelper(
            "../luposdate3000_simulator_iot/src/jvmTest/resources/autoIntegrationTest/test1.json",
            mutableMapOf(
                "predefinedPartitionScheme" to "PartitionByIDTwiceAllCollations",
                "mergeLocalOperatorgraphs" to true,
                "queryDistributionMode" to "Routing",
                "useDictionaryInlineEncoding" to true,
                "REPLACE_STORE_WITH_VALUES" to false,
                "LUPOS_PARTITION_MODE" to "Process",
            )
        )
    }

    @Ignore // Reason: >Bug<
    @Test
    public fun `INSERTing the same bnode with INSERT DATA into two different Graphs is the same bnode - in simulator - PartitionByIDTwiceAllCollations - true - Routing - false - Process`() {
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

    @Ignore // Reason: >Bug<
    @Test
    public fun `INSERTing the same bnode with INSERT DATA into two different Graphs is the same bnode - in simulator - PartitionByIDTwiceAllCollations - false - Centralized - true - Process`() {
        simulatorHelper(
            "../luposdate3000_simulator_iot/src/jvmTest/resources/autoIntegrationTest/test1.json",
            mutableMapOf(
                "predefinedPartitionScheme" to "PartitionByIDTwiceAllCollations",
                "mergeLocalOperatorgraphs" to false,
                "queryDistributionMode" to "Centralized",
                "useDictionaryInlineEncoding" to true,
                "REPLACE_STORE_WITH_VALUES" to false,
                "LUPOS_PARTITION_MODE" to "Process",
            )
        )
    }

    @Ignore // Reason: >Bug<
    @Test
    public fun `INSERTing the same bnode with INSERT DATA into two different Graphs is the same bnode - in simulator - PartitionByIDTwiceAllCollations - false - Centralized - false - Process`() {
        simulatorHelper(
            "../luposdate3000_simulator_iot/src/jvmTest/resources/autoIntegrationTest/test1.json",
            mutableMapOf(
                "predefinedPartitionScheme" to "PartitionByIDTwiceAllCollations",
                "mergeLocalOperatorgraphs" to false,
                "queryDistributionMode" to "Centralized",
                "useDictionaryInlineEncoding" to false,
                "REPLACE_STORE_WITH_VALUES" to false,
                "LUPOS_PARTITION_MODE" to "Process",
            )
        )
    }

    @Ignore // Reason: >Bug<
    @Test
    public fun `INSERTing the same bnode with INSERT DATA into two different Graphs is the same bnode - in simulator - PartitionByIDTwiceAllCollations - false - Routing - true - Process`() {
        simulatorHelper(
            "../luposdate3000_simulator_iot/src/jvmTest/resources/autoIntegrationTest/test1.json",
            mutableMapOf(
                "predefinedPartitionScheme" to "PartitionByIDTwiceAllCollations",
                "mergeLocalOperatorgraphs" to false,
                "queryDistributionMode" to "Routing",
                "useDictionaryInlineEncoding" to true,
                "REPLACE_STORE_WITH_VALUES" to false,
                "LUPOS_PARTITION_MODE" to "Process",
            )
        )
    }

    @Ignore // Reason: >Bug<
    @Test
    public fun `INSERTing the same bnode with INSERT DATA into two different Graphs is the same bnode - in simulator - PartitionByIDTwiceAllCollations - false - Routing - false - Process`() {
        simulatorHelper(
            "../luposdate3000_simulator_iot/src/jvmTest/resources/autoIntegrationTest/test1.json",
            mutableMapOf(
                "predefinedPartitionScheme" to "PartitionByIDTwiceAllCollations",
                "mergeLocalOperatorgraphs" to false,
                "queryDistributionMode" to "Routing",
                "useDictionaryInlineEncoding" to false,
                "REPLACE_STORE_WITH_VALUES" to false,
                "LUPOS_PARTITION_MODE" to "Process",
            )
        )
    }

    @Ignore // Reason: >Bug<
    @Test
    public fun `INSERTing the same bnode with INSERT DATA into two different Graphs is the same bnode - in simulator - PartitionByKeyAllCollations - true - Centralized - true - Process`() {
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

    @Ignore // Reason: >Bug<
    @Test
    public fun `INSERTing the same bnode with INSERT DATA into two different Graphs is the same bnode - in simulator - PartitionByKeyAllCollations - true - Centralized - false - Process`() {
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

    @Ignore // Reason: >Bug<
    @Test
    public fun `INSERTing the same bnode with INSERT DATA into two different Graphs is the same bnode - in simulator - PartitionByKeyAllCollations - true - Routing - true - Process`() {
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

    @Ignore // Reason: >Bug<
    @Test
    public fun `INSERTing the same bnode with INSERT DATA into two different Graphs is the same bnode - in simulator - PartitionByKeyAllCollations - true - Routing - false - Process`() {
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

    @Ignore // Reason: >Bug<
    @Test
    public fun `INSERTing the same bnode with INSERT DATA into two different Graphs is the same bnode - in simulator - PartitionByKeyAllCollations - false - Centralized - true - Process`() {
        simulatorHelper(
            "../luposdate3000_simulator_iot/src/jvmTest/resources/autoIntegrationTest/test1.json",
            mutableMapOf(
                "predefinedPartitionScheme" to "PartitionByKeyAllCollations",
                "mergeLocalOperatorgraphs" to false,
                "queryDistributionMode" to "Centralized",
                "useDictionaryInlineEncoding" to true,
                "REPLACE_STORE_WITH_VALUES" to false,
                "LUPOS_PARTITION_MODE" to "Process",
            )
        )
    }

    @Ignore // Reason: >Bug<
    @Test
    public fun `INSERTing the same bnode with INSERT DATA into two different Graphs is the same bnode - in simulator - PartitionByKeyAllCollations - false - Centralized - false - Process`() {
        simulatorHelper(
            "../luposdate3000_simulator_iot/src/jvmTest/resources/autoIntegrationTest/test1.json",
            mutableMapOf(
                "predefinedPartitionScheme" to "PartitionByKeyAllCollations",
                "mergeLocalOperatorgraphs" to false,
                "queryDistributionMode" to "Centralized",
                "useDictionaryInlineEncoding" to false,
                "REPLACE_STORE_WITH_VALUES" to false,
                "LUPOS_PARTITION_MODE" to "Process",
            )
        )
    }

    @Ignore // Reason: >Bug<
    @Test
    public fun `INSERTing the same bnode with INSERT DATA into two different Graphs is the same bnode - in simulator - PartitionByKeyAllCollations - false - Routing - true - Process`() {
        simulatorHelper(
            "../luposdate3000_simulator_iot/src/jvmTest/resources/autoIntegrationTest/test1.json",
            mutableMapOf(
                "predefinedPartitionScheme" to "PartitionByKeyAllCollations",
                "mergeLocalOperatorgraphs" to false,
                "queryDistributionMode" to "Routing",
                "useDictionaryInlineEncoding" to true,
                "REPLACE_STORE_WITH_VALUES" to false,
                "LUPOS_PARTITION_MODE" to "Process",
            )
        )
    }

    @Ignore // Reason: >Bug<
    @Test
    public fun `INSERTing the same bnode with INSERT DATA into two different Graphs is the same bnode - in simulator - PartitionByKeyAllCollations - false - Routing - false - Process`() {
        simulatorHelper(
            "../luposdate3000_simulator_iot/src/jvmTest/resources/autoIntegrationTest/test1.json",
            mutableMapOf(
                "predefinedPartitionScheme" to "PartitionByKeyAllCollations",
                "mergeLocalOperatorgraphs" to false,
                "queryDistributionMode" to "Routing",
                "useDictionaryInlineEncoding" to false,
                "REPLACE_STORE_WITH_VALUES" to false,
                "LUPOS_PARTITION_MODE" to "Process",
            )
        )
    }

    @Ignore // Reason: >Bug<
    @Test
    public fun `INSERTing the same bnode with INSERT DATA into two different Graphs is the same bnode - in simulator - Simple - true - Centralized - true - Process`() {
        simulatorHelper(
            "../luposdate3000_simulator_iot/src/jvmTest/resources/autoIntegrationTest/test1.json",
            mutableMapOf(
                "predefinedPartitionScheme" to "Simple",
                "mergeLocalOperatorgraphs" to true,
                "queryDistributionMode" to "Centralized",
                "useDictionaryInlineEncoding" to true,
                "REPLACE_STORE_WITH_VALUES" to false,
                "LUPOS_PARTITION_MODE" to "Process",
            )
        )
    }

    @Ignore // Reason: >Bug<
    @Test
    public fun `INSERTing the same bnode with INSERT DATA into two different Graphs is the same bnode - in simulator - Simple - true - Centralized - false - Process`() {
        simulatorHelper(
            "../luposdate3000_simulator_iot/src/jvmTest/resources/autoIntegrationTest/test1.json",
            mutableMapOf(
                "predefinedPartitionScheme" to "Simple",
                "mergeLocalOperatorgraphs" to true,
                "queryDistributionMode" to "Centralized",
                "useDictionaryInlineEncoding" to false,
                "REPLACE_STORE_WITH_VALUES" to false,
                "LUPOS_PARTITION_MODE" to "Process",
            )
        )
    }

    @Ignore // Reason: >Bug<
    @Test
    public fun `INSERTing the same bnode with INSERT DATA into two different Graphs is the same bnode - in simulator - Simple - true - Routing - true - Process`() {
        simulatorHelper(
            "../luposdate3000_simulator_iot/src/jvmTest/resources/autoIntegrationTest/test1.json",
            mutableMapOf(
                "predefinedPartitionScheme" to "Simple",
                "mergeLocalOperatorgraphs" to true,
                "queryDistributionMode" to "Routing",
                "useDictionaryInlineEncoding" to true,
                "REPLACE_STORE_WITH_VALUES" to false,
                "LUPOS_PARTITION_MODE" to "Process",
            )
        )
    }

    @Ignore // Reason: >Bug<
    @Test
    public fun `INSERTing the same bnode with INSERT DATA into two different Graphs is the same bnode - in simulator - Simple - true - Routing - false - Process`() {
        simulatorHelper(
            "../luposdate3000_simulator_iot/src/jvmTest/resources/autoIntegrationTest/test1.json",
            mutableMapOf(
                "predefinedPartitionScheme" to "Simple",
                "mergeLocalOperatorgraphs" to true,
                "queryDistributionMode" to "Routing",
                "useDictionaryInlineEncoding" to false,
                "REPLACE_STORE_WITH_VALUES" to false,
                "LUPOS_PARTITION_MODE" to "Process",
            )
        )
    }

    @Ignore // Reason: >Bug<
    @Test
    public fun `INSERTing the same bnode with INSERT DATA into two different Graphs is the same bnode - in simulator - Simple - false - Centralized - true - Process`() {
        simulatorHelper(
            "../luposdate3000_simulator_iot/src/jvmTest/resources/autoIntegrationTest/test1.json",
            mutableMapOf(
                "predefinedPartitionScheme" to "Simple",
                "mergeLocalOperatorgraphs" to false,
                "queryDistributionMode" to "Centralized",
                "useDictionaryInlineEncoding" to true,
                "REPLACE_STORE_WITH_VALUES" to false,
                "LUPOS_PARTITION_MODE" to "Process",
            )
        )
    }

    @Ignore // Reason: >Bug<
    @Test
    public fun `INSERTing the same bnode with INSERT DATA into two different Graphs is the same bnode - in simulator - Simple - false - Centralized - false - Process`() {
        simulatorHelper(
            "../luposdate3000_simulator_iot/src/jvmTest/resources/autoIntegrationTest/test1.json",
            mutableMapOf(
                "predefinedPartitionScheme" to "Simple",
                "mergeLocalOperatorgraphs" to false,
                "queryDistributionMode" to "Centralized",
                "useDictionaryInlineEncoding" to false,
                "REPLACE_STORE_WITH_VALUES" to false,
                "LUPOS_PARTITION_MODE" to "Process",
            )
        )
    }

    @Ignore // Reason: >Bug<
    @Test
    public fun `INSERTing the same bnode with INSERT DATA into two different Graphs is the same bnode - in simulator - Simple - false - Routing - true - Process`() {
        simulatorHelper(
            "../luposdate3000_simulator_iot/src/jvmTest/resources/autoIntegrationTest/test1.json",
            mutableMapOf(
                "predefinedPartitionScheme" to "Simple",
                "mergeLocalOperatorgraphs" to false,
                "queryDistributionMode" to "Routing",
                "useDictionaryInlineEncoding" to true,
                "REPLACE_STORE_WITH_VALUES" to false,
                "LUPOS_PARTITION_MODE" to "Process",
            )
        )
    }

    @Ignore // Reason: >Bug<
    @Test
    public fun `INSERTing the same bnode with INSERT DATA into two different Graphs is the same bnode - in simulator - Simple - false - Routing - false - Process`() {
        simulatorHelper(
            "../luposdate3000_simulator_iot/src/jvmTest/resources/autoIntegrationTest/test1.json",
            mutableMapOf(
                "predefinedPartitionScheme" to "Simple",
                "mergeLocalOperatorgraphs" to false,
                "queryDistributionMode" to "Routing",
                "useDictionaryInlineEncoding" to false,
                "REPLACE_STORE_WITH_VALUES" to false,
                "LUPOS_PARTITION_MODE" to "Process",
            )
        )
    }

    @Ignore // Reason: >Bug<
    @Test
    public fun `INSERTing the same bnode with INSERT DATA into two different Graphs is the same bnode - in simulator - PartitionByIDTwiceAllCollations - false - Centralized - true - Thread`() {
        simulatorHelper(
            "../luposdate3000_simulator_iot/src/jvmTest/resources/autoIntegrationTest/test2.json",
            mutableMapOf(
                "predefinedPartitionScheme" to "PartitionByIDTwiceAllCollations",
                "mergeLocalOperatorgraphs" to false,
                "queryDistributionMode" to "Centralized",
                "useDictionaryInlineEncoding" to true,
                "REPLACE_STORE_WITH_VALUES" to false,
                "LUPOS_PARTITION_MODE" to "Thread",
            )
        )
    }

    @Ignore // Reason: >Bug<
    @Test
    public fun `INSERTing the same bnode with INSERT DATA into two different Graphs is the same bnode - in simulator - PartitionByIDTwiceAllCollations - false - Centralized - false - Thread`() {
        simulatorHelper(
            "../luposdate3000_simulator_iot/src/jvmTest/resources/autoIntegrationTest/test2.json",
            mutableMapOf(
                "predefinedPartitionScheme" to "PartitionByIDTwiceAllCollations",
                "mergeLocalOperatorgraphs" to false,
                "queryDistributionMode" to "Centralized",
                "useDictionaryInlineEncoding" to false,
                "REPLACE_STORE_WITH_VALUES" to false,
                "LUPOS_PARTITION_MODE" to "Thread",
            )
        )
    }

    @Ignore // Reason: >Bug<
    @Test
    public fun `INSERTing the same bnode with INSERT DATA into two different Graphs is the same bnode - in simulator - PartitionByKeyAllCollations - false - Centralized - true - Thread`() {
        simulatorHelper(
            "../luposdate3000_simulator_iot/src/jvmTest/resources/autoIntegrationTest/test2.json",
            mutableMapOf(
                "predefinedPartitionScheme" to "PartitionByKeyAllCollations",
                "mergeLocalOperatorgraphs" to false,
                "queryDistributionMode" to "Centralized",
                "useDictionaryInlineEncoding" to true,
                "REPLACE_STORE_WITH_VALUES" to false,
                "LUPOS_PARTITION_MODE" to "Thread",
            )
        )
    }

    @Ignore // Reason: >Bug<
    @Test
    public fun `INSERTing the same bnode with INSERT DATA into two different Graphs is the same bnode - in simulator - PartitionByKeyAllCollations - false - Centralized - false - Thread`() {
        simulatorHelper(
            "../luposdate3000_simulator_iot/src/jvmTest/resources/autoIntegrationTest/test2.json",
            mutableMapOf(
                "predefinedPartitionScheme" to "PartitionByKeyAllCollations",
                "mergeLocalOperatorgraphs" to false,
                "queryDistributionMode" to "Centralized",
                "useDictionaryInlineEncoding" to false,
                "REPLACE_STORE_WITH_VALUES" to false,
                "LUPOS_PARTITION_MODE" to "Thread",
            )
        )
    }

    @Ignore // Reason: >Bug<
    @Test
    public fun `INSERTing the same bnode with INSERT DATA into two different Graphs is the same bnode - in simulator - Simple - false - Centralized - true - Thread`() {
        simulatorHelper(
            "../luposdate3000_simulator_iot/src/jvmTest/resources/autoIntegrationTest/test2.json",
            mutableMapOf(
                "predefinedPartitionScheme" to "Simple",
                "mergeLocalOperatorgraphs" to false,
                "queryDistributionMode" to "Centralized",
                "useDictionaryInlineEncoding" to true,
                "REPLACE_STORE_WITH_VALUES" to false,
                "LUPOS_PARTITION_MODE" to "Thread",
            )
        )
    }

    @Ignore // Reason: >Bug<
    @Test
    public fun `INSERTing the same bnode with INSERT DATA into two different Graphs is the same bnode - in simulator - Simple - false - Centralized - false - Thread`() {
        simulatorHelper(
            "../luposdate3000_simulator_iot/src/jvmTest/resources/autoIntegrationTest/test2.json",
            mutableMapOf(
                "predefinedPartitionScheme" to "Simple",
                "mergeLocalOperatorgraphs" to false,
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
        val instance = (config.devices.filter { it.hasDatabaseStore }.map { it.database }.filter { it != null }.map { it!!.db }.first() as DatabaseHandle).instance
        val pkg0 = MySimulatorTestingExecute(query)
        val pkg1 = MySimulatorTestingCompareGraphPackage("SELECT ?s ?p ?o WHERE { GRAPH <${outputGraph[0]}> { ?s ?p ?o . }}", MemoryTable.parseFromAny(outputData[0], outputType[0], Query(instance))!!)
        pkg0.onFinish = pkg1
        config.querySenders[0].queryPck = pkg0
        simRun.sim.run()
        simRun.sim.shutDown()
    }
}
