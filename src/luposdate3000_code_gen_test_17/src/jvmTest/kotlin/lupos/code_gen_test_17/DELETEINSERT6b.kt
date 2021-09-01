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
import lupos.shared.EPartitionModeExt
import lupos.shared.EPredefinedPartitionSchemesExt
import lupos.shared.Luposdate3000Instance
import lupos.shared.MemoryTable
import lupos.shared.inline.File
import lupos.shared.inline.MyPrintWriter
import kotlin.test.Test
import kotlin.test.fail

public class DELETEINSERT6b {
    internal val inputData = arrayOf(
        File("src/jvmTest/resources/DELETEINSERT6b.input").readAsString(),
    )
    internal val inputGraph = arrayOf(
        "",
    )
    internal val inputType = arrayOf(
        ".ttl",
    )
    internal val outputData = arrayOf(
        File("src/jvmTest/resources/DELETEINSERT6b.output0").readAsString(),
    )
    internal val outputGraph = arrayOf(
        "",
    )
    internal val outputType = arrayOf(
        ".ttl",
    )
    internal val query = "PREFIX     : <http://example.org/>  \n" +
        "PREFIX foaf: <http://xmlns.com/foaf/0.1/>  \n" +
        "DELETE  \n" +
        "{ \n" +
        "  ?a foaf:knows ?Var_B . \n" +
        "} \n" +
        "INSERT \n" +
        "{ \n" +
        "  ?a foaf:knows ?a . \n" +
        "} \n" +
        "WHERE \n" +
        "{ \n" +
        "  { ?a foaf:name \"Alan\" . } \n" +
        "  { ?a foaf:knows ?Var_B . } \n" +
        "} \n" +
        ""

    @Test(timeout = 2000)
    public fun `DELETE INSERT 6b - None - PartitionByIDTwiceAllCollations - false`() {
        var instance = Luposdate3000Instance()
        try {
            instance.LUPOS_BUFFER_SIZE = 128
            instance.LUPOS_PARTITION_MODE = EPartitionModeExt.None
            instance.predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByIDTwiceAllCollations
            instance.useDictionaryInlineEncoding = false
            instance = LuposdateEndpoint.initializeB(instance)
            val buf = MyPrintWriter(false)
            if (listOf(".n3", ".ttl", ".nt").contains(inputType[0])) {
                LuposdateEndpoint.importTurtleString(instance, inputData[0], inputGraph[0])
            } else {
                TODO()
            }
            val query3 = Query(instance)
            val graph3 = instance.tripleStoreManager!!.getGraph(inputGraph[0])
            val operator3 = graph3.getIterator(query3, arrayOf(AOPVariable(query3, "s"), AOPVariable(query3, "p"), AOPVariable(query3, "o")), EIndexPatternExt.SPO)
            val actual3 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator3, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
            val expected3 = MemoryTable.parseFromAny(inputData[0], inputType[0], Query(instance))!!
            val buf_err3 = MyPrintWriter()
            if (!expected3.equalsVerbose(actual3, true, true, buf_err3)) {
                fail(expected3.toString() + " .. " + actual3.toString() + " .. " + buf_err3.toString() + " .. " + operator3)
            }
            val operator4 = LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, query)
            LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator4, buf, EQueryResultToStreamExt.EMPTY_STREAM)
            val query5 = Query(instance)
            val graph5 = instance.tripleStoreManager!!.getGraph(outputGraph[0])
            val operator5 = graph5.getIterator(query5, arrayOf(AOPVariable(query5, "s"), AOPVariable(query5, "p"), AOPVariable(query5, "o")), EIndexPatternExt.SPO)
            val actual5 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator5, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
            val expected5 = MemoryTable.parseFromAny(outputData[0], outputType[0], Query(instance))!!
            val buf_err5 = MyPrintWriter()
            if (!expected5.equalsVerbose(actual5, true, true, buf_err5)) {
                fail(expected5.toString() + " .. " + actual5.toString() + " .. " + buf_err5.toString() + " .. " + operator5)
            }
        } finally {
            LuposdateEndpoint.close(instance)
        }
    }

    @Test(timeout = 2000)
    public fun `DELETE INSERT 6b - None - PartitionByID_1_AllCollations - false`() {
        var instance = Luposdate3000Instance()
        try {
            instance.LUPOS_BUFFER_SIZE = 128
            instance.LUPOS_PARTITION_MODE = EPartitionModeExt.None
            instance.predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByID_1_AllCollations
            instance.useDictionaryInlineEncoding = false
            instance = LuposdateEndpoint.initializeB(instance)
            val buf = MyPrintWriter(false)
            if (listOf(".n3", ".ttl", ".nt").contains(inputType[0])) {
                LuposdateEndpoint.importTurtleString(instance, inputData[0], inputGraph[0])
            } else {
                TODO()
            }
            val query9 = Query(instance)
            val graph9 = instance.tripleStoreManager!!.getGraph(inputGraph[0])
            val operator9 = graph9.getIterator(query9, arrayOf(AOPVariable(query9, "s"), AOPVariable(query9, "p"), AOPVariable(query9, "o")), EIndexPatternExt.SPO)
            val actual9 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator9, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
            val expected9 = MemoryTable.parseFromAny(inputData[0], inputType[0], Query(instance))!!
            val buf_err9 = MyPrintWriter()
            if (!expected9.equalsVerbose(actual9, true, true, buf_err9)) {
                fail(expected9.toString() + " .. " + actual9.toString() + " .. " + buf_err9.toString() + " .. " + operator9)
            }
            val operator10 = LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, query)
            LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator10, buf, EQueryResultToStreamExt.EMPTY_STREAM)
            val query11 = Query(instance)
            val graph11 = instance.tripleStoreManager!!.getGraph(outputGraph[0])
            val operator11 = graph11.getIterator(query11, arrayOf(AOPVariable(query11, "s"), AOPVariable(query11, "p"), AOPVariable(query11, "o")), EIndexPatternExt.SPO)
            val actual11 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator11, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
            val expected11 = MemoryTable.parseFromAny(outputData[0], outputType[0], Query(instance))!!
            val buf_err11 = MyPrintWriter()
            if (!expected11.equalsVerbose(actual11, true, true, buf_err11)) {
                fail(expected11.toString() + " .. " + actual11.toString() + " .. " + buf_err11.toString() + " .. " + operator11)
            }
        } finally {
            LuposdateEndpoint.close(instance)
        }
    }

    @Test(timeout = 2000)
    public fun `DELETE INSERT 6b - None - PartitionByID_2_AllCollations - false`() {
        var instance = Luposdate3000Instance()
        try {
            instance.LUPOS_BUFFER_SIZE = 128
            instance.LUPOS_PARTITION_MODE = EPartitionModeExt.None
            instance.predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByID_2_AllCollations
            instance.useDictionaryInlineEncoding = false
            instance = LuposdateEndpoint.initializeB(instance)
            val buf = MyPrintWriter(false)
            if (listOf(".n3", ".ttl", ".nt").contains(inputType[0])) {
                LuposdateEndpoint.importTurtleString(instance, inputData[0], inputGraph[0])
            } else {
                TODO()
            }
            val query15 = Query(instance)
            val graph15 = instance.tripleStoreManager!!.getGraph(inputGraph[0])
            val operator15 = graph15.getIterator(query15, arrayOf(AOPVariable(query15, "s"), AOPVariable(query15, "p"), AOPVariable(query15, "o")), EIndexPatternExt.SPO)
            val actual15 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator15, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
            val expected15 = MemoryTable.parseFromAny(inputData[0], inputType[0], Query(instance))!!
            val buf_err15 = MyPrintWriter()
            if (!expected15.equalsVerbose(actual15, true, true, buf_err15)) {
                fail(expected15.toString() + " .. " + actual15.toString() + " .. " + buf_err15.toString() + " .. " + operator15)
            }
            val operator16 = LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, query)
            LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator16, buf, EQueryResultToStreamExt.EMPTY_STREAM)
            val query17 = Query(instance)
            val graph17 = instance.tripleStoreManager!!.getGraph(outputGraph[0])
            val operator17 = graph17.getIterator(query17, arrayOf(AOPVariable(query17, "s"), AOPVariable(query17, "p"), AOPVariable(query17, "o")), EIndexPatternExt.SPO)
            val actual17 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator17, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
            val expected17 = MemoryTable.parseFromAny(outputData[0], outputType[0], Query(instance))!!
            val buf_err17 = MyPrintWriter()
            if (!expected17.equalsVerbose(actual17, true, true, buf_err17)) {
                fail(expected17.toString() + " .. " + actual17.toString() + " .. " + buf_err17.toString() + " .. " + operator17)
            }
        } finally {
            LuposdateEndpoint.close(instance)
        }
    }

    @Test(timeout = 2000)
    public fun `DELETE INSERT 6b - None - PartitionByID_O_AllCollations - false`() {
        var instance = Luposdate3000Instance()
        try {
            instance.LUPOS_BUFFER_SIZE = 128
            instance.LUPOS_PARTITION_MODE = EPartitionModeExt.None
            instance.predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByID_O_AllCollations
            instance.useDictionaryInlineEncoding = false
            instance = LuposdateEndpoint.initializeB(instance)
            val buf = MyPrintWriter(false)
            if (listOf(".n3", ".ttl", ".nt").contains(inputType[0])) {
                LuposdateEndpoint.importTurtleString(instance, inputData[0], inputGraph[0])
            } else {
                TODO()
            }
            val query21 = Query(instance)
            val graph21 = instance.tripleStoreManager!!.getGraph(inputGraph[0])
            val operator21 = graph21.getIterator(query21, arrayOf(AOPVariable(query21, "s"), AOPVariable(query21, "p"), AOPVariable(query21, "o")), EIndexPatternExt.SPO)
            val actual21 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator21, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
            val expected21 = MemoryTable.parseFromAny(inputData[0], inputType[0], Query(instance))!!
            val buf_err21 = MyPrintWriter()
            if (!expected21.equalsVerbose(actual21, true, true, buf_err21)) {
                fail(expected21.toString() + " .. " + actual21.toString() + " .. " + buf_err21.toString() + " .. " + operator21)
            }
            val operator22 = LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, query)
            LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator22, buf, EQueryResultToStreamExt.EMPTY_STREAM)
            val query23 = Query(instance)
            val graph23 = instance.tripleStoreManager!!.getGraph(outputGraph[0])
            val operator23 = graph23.getIterator(query23, arrayOf(AOPVariable(query23, "s"), AOPVariable(query23, "p"), AOPVariable(query23, "o")), EIndexPatternExt.SPO)
            val actual23 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator23, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
            val expected23 = MemoryTable.parseFromAny(outputData[0], outputType[0], Query(instance))!!
            val buf_err23 = MyPrintWriter()
            if (!expected23.equalsVerbose(actual23, true, true, buf_err23)) {
                fail(expected23.toString() + " .. " + actual23.toString() + " .. " + buf_err23.toString() + " .. " + operator23)
            }
        } finally {
            LuposdateEndpoint.close(instance)
        }
    }

    @Test(timeout = 2000)
    public fun `DELETE INSERT 6b - None - PartitionByID_S_AllCollations - false`() {
        var instance = Luposdate3000Instance()
        try {
            instance.LUPOS_BUFFER_SIZE = 128
            instance.LUPOS_PARTITION_MODE = EPartitionModeExt.None
            instance.predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByID_S_AllCollations
            instance.useDictionaryInlineEncoding = false
            instance = LuposdateEndpoint.initializeB(instance)
            val buf = MyPrintWriter(false)
            if (listOf(".n3", ".ttl", ".nt").contains(inputType[0])) {
                LuposdateEndpoint.importTurtleString(instance, inputData[0], inputGraph[0])
            } else {
                TODO()
            }
            val query27 = Query(instance)
            val graph27 = instance.tripleStoreManager!!.getGraph(inputGraph[0])
            val operator27 = graph27.getIterator(query27, arrayOf(AOPVariable(query27, "s"), AOPVariable(query27, "p"), AOPVariable(query27, "o")), EIndexPatternExt.SPO)
            val actual27 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator27, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
            val expected27 = MemoryTable.parseFromAny(inputData[0], inputType[0], Query(instance))!!
            val buf_err27 = MyPrintWriter()
            if (!expected27.equalsVerbose(actual27, true, true, buf_err27)) {
                fail(expected27.toString() + " .. " + actual27.toString() + " .. " + buf_err27.toString() + " .. " + operator27)
            }
            val operator28 = LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, query)
            LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator28, buf, EQueryResultToStreamExt.EMPTY_STREAM)
            val query29 = Query(instance)
            val graph29 = instance.tripleStoreManager!!.getGraph(outputGraph[0])
            val operator29 = graph29.getIterator(query29, arrayOf(AOPVariable(query29, "s"), AOPVariable(query29, "p"), AOPVariable(query29, "o")), EIndexPatternExt.SPO)
            val actual29 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator29, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
            val expected29 = MemoryTable.parseFromAny(outputData[0], outputType[0], Query(instance))!!
            val buf_err29 = MyPrintWriter()
            if (!expected29.equalsVerbose(actual29, true, true, buf_err29)) {
                fail(expected29.toString() + " .. " + actual29.toString() + " .. " + buf_err29.toString() + " .. " + operator29)
            }
        } finally {
            LuposdateEndpoint.close(instance)
        }
    }

    @Test(timeout = 2000)
    public fun `DELETE INSERT 6b - None - PartitionByKeyAllCollations - false`() {
        var instance = Luposdate3000Instance()
        try {
            instance.LUPOS_BUFFER_SIZE = 128
            instance.LUPOS_PARTITION_MODE = EPartitionModeExt.None
            instance.predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByKeyAllCollations
            instance.useDictionaryInlineEncoding = false
            instance = LuposdateEndpoint.initializeB(instance)
            val buf = MyPrintWriter(false)
            if (listOf(".n3", ".ttl", ".nt").contains(inputType[0])) {
                LuposdateEndpoint.importTurtleString(instance, inputData[0], inputGraph[0])
            } else {
                TODO()
            }
            val query33 = Query(instance)
            val graph33 = instance.tripleStoreManager!!.getGraph(inputGraph[0])
            val operator33 = graph33.getIterator(query33, arrayOf(AOPVariable(query33, "s"), AOPVariable(query33, "p"), AOPVariable(query33, "o")), EIndexPatternExt.SPO)
            val actual33 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator33, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
            val expected33 = MemoryTable.parseFromAny(inputData[0], inputType[0], Query(instance))!!
            val buf_err33 = MyPrintWriter()
            if (!expected33.equalsVerbose(actual33, true, true, buf_err33)) {
                fail(expected33.toString() + " .. " + actual33.toString() + " .. " + buf_err33.toString() + " .. " + operator33)
            }
            val operator34 = LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, query)
            LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator34, buf, EQueryResultToStreamExt.EMPTY_STREAM)
            val query35 = Query(instance)
            val graph35 = instance.tripleStoreManager!!.getGraph(outputGraph[0])
            val operator35 = graph35.getIterator(query35, arrayOf(AOPVariable(query35, "s"), AOPVariable(query35, "p"), AOPVariable(query35, "o")), EIndexPatternExt.SPO)
            val actual35 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator35, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
            val expected35 = MemoryTable.parseFromAny(outputData[0], outputType[0], Query(instance))!!
            val buf_err35 = MyPrintWriter()
            if (!expected35.equalsVerbose(actual35, true, true, buf_err35)) {
                fail(expected35.toString() + " .. " + actual35.toString() + " .. " + buf_err35.toString() + " .. " + operator35)
            }
        } finally {
            LuposdateEndpoint.close(instance)
        }
    }

    @Test(timeout = 2000)
    public fun `DELETE INSERT 6b - None - Simple - false`() {
        var instance = Luposdate3000Instance()
        try {
            instance.LUPOS_BUFFER_SIZE = 128
            instance.LUPOS_PARTITION_MODE = EPartitionModeExt.None
            instance.predefinedPartitionScheme = EPredefinedPartitionSchemesExt.Simple
            instance.useDictionaryInlineEncoding = false
            instance = LuposdateEndpoint.initializeB(instance)
            val buf = MyPrintWriter(false)
            if (listOf(".n3", ".ttl", ".nt").contains(inputType[0])) {
                LuposdateEndpoint.importTurtleString(instance, inputData[0], inputGraph[0])
            } else {
                TODO()
            }
            val query39 = Query(instance)
            val graph39 = instance.tripleStoreManager!!.getGraph(inputGraph[0])
            val operator39 = graph39.getIterator(query39, arrayOf(AOPVariable(query39, "s"), AOPVariable(query39, "p"), AOPVariable(query39, "o")), EIndexPatternExt.SPO)
            val actual39 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator39, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
            val expected39 = MemoryTable.parseFromAny(inputData[0], inputType[0], Query(instance))!!
            val buf_err39 = MyPrintWriter()
            if (!expected39.equalsVerbose(actual39, true, true, buf_err39)) {
                fail(expected39.toString() + " .. " + actual39.toString() + " .. " + buf_err39.toString() + " .. " + operator39)
            }
            val operator40 = LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, query)
            LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator40, buf, EQueryResultToStreamExt.EMPTY_STREAM)
            val query41 = Query(instance)
            val graph41 = instance.tripleStoreManager!!.getGraph(outputGraph[0])
            val operator41 = graph41.getIterator(query41, arrayOf(AOPVariable(query41, "s"), AOPVariable(query41, "p"), AOPVariable(query41, "o")), EIndexPatternExt.SPO)
            val actual41 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator41, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
            val expected41 = MemoryTable.parseFromAny(outputData[0], outputType[0], Query(instance))!!
            val buf_err41 = MyPrintWriter()
            if (!expected41.equalsVerbose(actual41, true, true, buf_err41)) {
                fail(expected41.toString() + " .. " + actual41.toString() + " .. " + buf_err41.toString() + " .. " + operator41)
            }
        } finally {
            LuposdateEndpoint.close(instance)
        }
    }

    @Test(timeout = 2000)
    public fun `DELETE INSERT 6b - Thread - Simple - false`() {
        var instance = Luposdate3000Instance()
        try {
            instance.LUPOS_BUFFER_SIZE = 128
            instance.LUPOS_PARTITION_MODE = EPartitionModeExt.Thread
            instance.predefinedPartitionScheme = EPredefinedPartitionSchemesExt.Simple
            instance.useDictionaryInlineEncoding = false
            instance = LuposdateEndpoint.initializeB(instance)
            val buf = MyPrintWriter(false)
            if (listOf(".n3", ".ttl", ".nt").contains(inputType[0])) {
                LuposdateEndpoint.importTurtleString(instance, inputData[0], inputGraph[0])
            } else {
                TODO()
            }
            val query81 = Query(instance)
            val graph81 = instance.tripleStoreManager!!.getGraph(inputGraph[0])
            val operator81 = graph81.getIterator(query81, arrayOf(AOPVariable(query81, "s"), AOPVariable(query81, "p"), AOPVariable(query81, "o")), EIndexPatternExt.SPO)
            val actual81 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator81, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
            val expected81 = MemoryTable.parseFromAny(inputData[0], inputType[0], Query(instance))!!
            val buf_err81 = MyPrintWriter()
            if (!expected81.equalsVerbose(actual81, true, true, buf_err81)) {
                fail(expected81.toString() + " .. " + actual81.toString() + " .. " + buf_err81.toString() + " .. " + operator81)
            }
            val operator82 = LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, query)
            LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator82, buf, EQueryResultToStreamExt.EMPTY_STREAM)
            val query83 = Query(instance)
            val graph83 = instance.tripleStoreManager!!.getGraph(outputGraph[0])
            val operator83 = graph83.getIterator(query83, arrayOf(AOPVariable(query83, "s"), AOPVariable(query83, "p"), AOPVariable(query83, "o")), EIndexPatternExt.SPO)
            val actual83 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator83, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
            val expected83 = MemoryTable.parseFromAny(outputData[0], outputType[0], Query(instance))!!
            val buf_err83 = MyPrintWriter()
            if (!expected83.equalsVerbose(actual83, true, true, buf_err83)) {
                fail(expected83.toString() + " .. " + actual83.toString() + " .. " + buf_err83.toString() + " .. " + operator83)
            }
        } finally {
            LuposdateEndpoint.close(instance)
        }
    }

    @Test(timeout = 2000)
    public fun `DELETE INSERT 6b - in simulator - PartitionByID_O_AllCollations - Centralized - false - None`() {
        simulatorHelper(
            "../luposdate3000_simulator_iot/src/jvmTest/resources/autoIntegrationTest/test2.json",
            mutableMapOf(
                "predefinedPartitionScheme" to "PartitionByID_O_AllCollations",
                "mergeLocalOperatorgraphs" to true,
                "queryDistributionMode" to "Centralized",
                "useDictionaryInlineEncoding" to false,
                "REPLACE_STORE_WITH_VALUES" to false,
                "LUPOS_PARTITION_MODE" to "None",
            )
        )
    }

    @Test(timeout = 2000)
    public fun `DELETE INSERT 6b - in simulator - PartitionByID_S_AllCollations - Centralized - false - None`() {
        simulatorHelper(
            "../luposdate3000_simulator_iot/src/jvmTest/resources/autoIntegrationTest/test2.json",
            mutableMapOf(
                "predefinedPartitionScheme" to "PartitionByID_S_AllCollations",
                "mergeLocalOperatorgraphs" to true,
                "queryDistributionMode" to "Centralized",
                "useDictionaryInlineEncoding" to false,
                "REPLACE_STORE_WITH_VALUES" to false,
                "LUPOS_PARTITION_MODE" to "None",
            )
        )
    }
}
