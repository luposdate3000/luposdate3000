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
import lupos.simulator_core.Simulation
import lupos.simulator_db.luposdate3000.DatabaseHandle
import lupos.simulator_db.luposdate3000.MySimulatorTestingCompareGraphPackage
import lupos.simulator_db.luposdate3000.MySimulatorTestingExecute
import lupos.simulator_db.luposdate3000.MySimulatorTestingImportPackage
import lupos.simulator_iot.SimulationRun
import kotlin.test.Ignore
import kotlin.test.Test
import kotlin.test.fail

public class GraphspecificDELETEWHERE1 {
    internal val inputData = arrayOf(
        File("src/jvmTest/resources/GraphspecificDELETEWHERE1.input").readAsString(),
        File("src/jvmTest/resources/GraphspecificDELETEWHERE1.input1").readAsString(),
        File("src/jvmTest/resources/GraphspecificDELETEWHERE1.input2").readAsString(),
    )
    internal val inputGraph = arrayOf(
        "",
        "http://example.org/g2",
        "http://example.org/g3",
    )
    internal val inputType = arrayOf(
        ".ttl",
        ".ttl",
        ".ttl",
    )
    internal val outputData = arrayOf(
        File("src/jvmTest/resources/GraphspecificDELETEWHERE1.output0").readAsString(),
        File("src/jvmTest/resources/GraphspecificDELETEWHERE1.output1").readAsString(),
        File("src/jvmTest/resources/GraphspecificDELETEWHERE1.output2").readAsString(),
    )
    internal val outputGraph = arrayOf(
        "",
        "http://example.org/g2",
        "http://example.org/g3",
    )
    internal val outputType = arrayOf(
        ".ttl",
        ".ttl",
        ".ttl",
    )
    internal val query = "PREFIX     : <http://example.org/>  \n" +
        "PREFIX foaf: <http://xmlns.com/foaf/0.1/>  \n" +
        "DELETE WHERE  \n" +
        "{  \n" +
        "  ?a foaf:knows :b . \n" +
        "} \n" +
        ""

    @Ignore // Reason: >Bug<
    @Test
    public fun `Graphspecific DELETE WHERE 1 - None - PartitionByIDTwiceAllCollations - true`() {
        var instance = Luposdate3000Instance()
        try {
            instance.LUPOS_BUFFER_SIZE = 128
            instance.LUPOS_PARTITION_MODE = EPartitionModeExt.None
            instance.predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByIDTwiceAllCollations
            instance.useDictionaryInlineEncoding = true
            instance = LuposdateEndpoint.initializeB(instance)
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
            LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator3, buf, EQueryResultToStreamExt.EMPTY_STREAM)
            val query4 = Query(instance)
            val graph4 = instance.tripleStoreManager!!.getGraph(outputGraph[0])
            val operator4 = graph4.getIterator(query4, arrayOf(AOPVariable(query4, "s"), AOPVariable(query4, "p"), AOPVariable(query4, "o")), EIndexPatternExt.SPO)
            val actual4 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator4, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
            val expected4 = MemoryTable.parseFromAny(outputData[0], outputType[0], Query(instance))!!
            val buf_err4 = MyPrintWriter()
            if (!expected4.equalsVerbose(actual4, true, true, buf_err4)) {
                fail(expected4.toString() + " .. " + actual4.toString() + " .. " + buf_err4.toString() + " .. " + operator4)
            }
            val query5 = Query(instance)
            val graph5 = instance.tripleStoreManager!!.getGraph(outputGraph[1])
            val operator5 = graph5.getIterator(query5, arrayOf(AOPVariable(query5, "s"), AOPVariable(query5, "p"), AOPVariable(query5, "o")), EIndexPatternExt.SPO)
            val actual5 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator5, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
            val expected5 = MemoryTable.parseFromAny(outputData[1], outputType[1], Query(instance))!!
            val buf_err5 = MyPrintWriter()
            if (!expected5.equalsVerbose(actual5, true, true, buf_err5)) {
                fail(expected5.toString() + " .. " + actual5.toString() + " .. " + buf_err5.toString() + " .. " + operator5)
            }
            val query6 = Query(instance)
            val graph6 = instance.tripleStoreManager!!.getGraph(outputGraph[2])
            val operator6 = graph6.getIterator(query6, arrayOf(AOPVariable(query6, "s"), AOPVariable(query6, "p"), AOPVariable(query6, "o")), EIndexPatternExt.SPO)
            val actual6 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator6, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
            val expected6 = MemoryTable.parseFromAny(outputData[2], outputType[2], Query(instance))!!
            val buf_err6 = MyPrintWriter()
            if (!expected6.equalsVerbose(actual6, true, true, buf_err6)) {
                fail(expected6.toString() + " .. " + actual6.toString() + " .. " + buf_err6.toString() + " .. " + operator6)
            }
        } finally {
            LuposdateEndpoint.close(instance)
        }
    }
    public fun `Graphspecific DELETE WHERE 1 - None - PartitionByIDTwiceAllCollations - false`() {
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
            val query7 = Query(instance)
            val graph7 = instance.tripleStoreManager!!.getGraph(inputGraph[0])
            val operator7 = graph7.getIterator(query7, arrayOf(AOPVariable(query7, "s"), AOPVariable(query7, "p"), AOPVariable(query7, "o")), EIndexPatternExt.SPO)
            val actual7 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator7, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
            val expected7 = MemoryTable.parseFromAny(inputData[0], inputType[0], Query(instance))!!
            val buf_err7 = MyPrintWriter()
            if (!expected7.equalsVerbose(actual7, true, true, buf_err7)) {
                fail(expected7.toString() + " .. " + actual7.toString() + " .. " + buf_err7.toString() + " .. " + operator7)
            }
            val query8 = Query(instance)
            val graph8 = instance.tripleStoreManager!!.getGraph(inputGraph[1])
            val operator8 = graph8.getIterator(query8, arrayOf(AOPVariable(query8, "s"), AOPVariable(query8, "p"), AOPVariable(query8, "o")), EIndexPatternExt.SPO)
            val actual8 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator8, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
            val expected8 = MemoryTable.parseFromAny(inputData[1], inputType[1], Query(instance))!!
            val buf_err8 = MyPrintWriter()
            if (!expected8.equalsVerbose(actual8, true, true, buf_err8)) {
                fail(expected8.toString() + " .. " + actual8.toString() + " .. " + buf_err8.toString() + " .. " + operator8)
            }
            val query9 = Query(instance)
            val graph9 = instance.tripleStoreManager!!.getGraph(inputGraph[2])
            val operator9 = graph9.getIterator(query9, arrayOf(AOPVariable(query9, "s"), AOPVariable(query9, "p"), AOPVariable(query9, "o")), EIndexPatternExt.SPO)
            val actual9 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator9, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
            val expected9 = MemoryTable.parseFromAny(inputData[2], inputType[2], Query(instance))!!
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
            val query12 = Query(instance)
            val graph12 = instance.tripleStoreManager!!.getGraph(outputGraph[1])
            val operator12 = graph12.getIterator(query12, arrayOf(AOPVariable(query12, "s"), AOPVariable(query12, "p"), AOPVariable(query12, "o")), EIndexPatternExt.SPO)
            val actual12 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator12, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
            val expected12 = MemoryTable.parseFromAny(outputData[1], outputType[1], Query(instance))!!
            val buf_err12 = MyPrintWriter()
            if (!expected12.equalsVerbose(actual12, true, true, buf_err12)) {
                fail(expected12.toString() + " .. " + actual12.toString() + " .. " + buf_err12.toString() + " .. " + operator12)
            }
            val query13 = Query(instance)
            val graph13 = instance.tripleStoreManager!!.getGraph(outputGraph[2])
            val operator13 = graph13.getIterator(query13, arrayOf(AOPVariable(query13, "s"), AOPVariable(query13, "p"), AOPVariable(query13, "o")), EIndexPatternExt.SPO)
            val actual13 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator13, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
            val expected13 = MemoryTable.parseFromAny(outputData[2], outputType[2], Query(instance))!!
            val buf_err13 = MyPrintWriter()
            if (!expected13.equalsVerbose(actual13, true, true, buf_err13)) {
                fail(expected13.toString() + " .. " + actual13.toString() + " .. " + buf_err13.toString() + " .. " + operator13)
            }
        } finally {
            LuposdateEndpoint.close(instance)
        }
    }
    public fun `Graphspecific DELETE WHERE 1 - None - PartitionByKeyAllCollations - true`() {
        var instance = Luposdate3000Instance()
        try {
            instance.LUPOS_BUFFER_SIZE = 128
            instance.LUPOS_PARTITION_MODE = EPartitionModeExt.None
            instance.predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByKeyAllCollations
            instance.useDictionaryInlineEncoding = true
            instance = LuposdateEndpoint.initializeB(instance)
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
            val query14 = Query(instance)
            val graph14 = instance.tripleStoreManager!!.getGraph(inputGraph[0])
            val operator14 = graph14.getIterator(query14, arrayOf(AOPVariable(query14, "s"), AOPVariable(query14, "p"), AOPVariable(query14, "o")), EIndexPatternExt.SPO)
            val actual14 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator14, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
            val expected14 = MemoryTable.parseFromAny(inputData[0], inputType[0], Query(instance))!!
            val buf_err14 = MyPrintWriter()
            if (!expected14.equalsVerbose(actual14, true, true, buf_err14)) {
                fail(expected14.toString() + " .. " + actual14.toString() + " .. " + buf_err14.toString() + " .. " + operator14)
            }
            val query15 = Query(instance)
            val graph15 = instance.tripleStoreManager!!.getGraph(inputGraph[1])
            val operator15 = graph15.getIterator(query15, arrayOf(AOPVariable(query15, "s"), AOPVariable(query15, "p"), AOPVariable(query15, "o")), EIndexPatternExt.SPO)
            val actual15 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator15, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
            val expected15 = MemoryTable.parseFromAny(inputData[1], inputType[1], Query(instance))!!
            val buf_err15 = MyPrintWriter()
            if (!expected15.equalsVerbose(actual15, true, true, buf_err15)) {
                fail(expected15.toString() + " .. " + actual15.toString() + " .. " + buf_err15.toString() + " .. " + operator15)
            }
            val query16 = Query(instance)
            val graph16 = instance.tripleStoreManager!!.getGraph(inputGraph[2])
            val operator16 = graph16.getIterator(query16, arrayOf(AOPVariable(query16, "s"), AOPVariable(query16, "p"), AOPVariable(query16, "o")), EIndexPatternExt.SPO)
            val actual16 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator16, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
            val expected16 = MemoryTable.parseFromAny(inputData[2], inputType[2], Query(instance))!!
            val buf_err16 = MyPrintWriter()
            if (!expected16.equalsVerbose(actual16, true, true, buf_err16)) {
                fail(expected16.toString() + " .. " + actual16.toString() + " .. " + buf_err16.toString() + " .. " + operator16)
            }
            val operator17 = LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, query)
            LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator17, buf, EQueryResultToStreamExt.EMPTY_STREAM)
            val query18 = Query(instance)
            val graph18 = instance.tripleStoreManager!!.getGraph(outputGraph[0])
            val operator18 = graph18.getIterator(query18, arrayOf(AOPVariable(query18, "s"), AOPVariable(query18, "p"), AOPVariable(query18, "o")), EIndexPatternExt.SPO)
            val actual18 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator18, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
            val expected18 = MemoryTable.parseFromAny(outputData[0], outputType[0], Query(instance))!!
            val buf_err18 = MyPrintWriter()
            if (!expected18.equalsVerbose(actual18, true, true, buf_err18)) {
                fail(expected18.toString() + " .. " + actual18.toString() + " .. " + buf_err18.toString() + " .. " + operator18)
            }
            val query19 = Query(instance)
            val graph19 = instance.tripleStoreManager!!.getGraph(outputGraph[1])
            val operator19 = graph19.getIterator(query19, arrayOf(AOPVariable(query19, "s"), AOPVariable(query19, "p"), AOPVariable(query19, "o")), EIndexPatternExt.SPO)
            val actual19 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator19, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
            val expected19 = MemoryTable.parseFromAny(outputData[1], outputType[1], Query(instance))!!
            val buf_err19 = MyPrintWriter()
            if (!expected19.equalsVerbose(actual19, true, true, buf_err19)) {
                fail(expected19.toString() + " .. " + actual19.toString() + " .. " + buf_err19.toString() + " .. " + operator19)
            }
            val query20 = Query(instance)
            val graph20 = instance.tripleStoreManager!!.getGraph(outputGraph[2])
            val operator20 = graph20.getIterator(query20, arrayOf(AOPVariable(query20, "s"), AOPVariable(query20, "p"), AOPVariable(query20, "o")), EIndexPatternExt.SPO)
            val actual20 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator20, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
            val expected20 = MemoryTable.parseFromAny(outputData[2], outputType[2], Query(instance))!!
            val buf_err20 = MyPrintWriter()
            if (!expected20.equalsVerbose(actual20, true, true, buf_err20)) {
                fail(expected20.toString() + " .. " + actual20.toString() + " .. " + buf_err20.toString() + " .. " + operator20)
            }
        } finally {
            LuposdateEndpoint.close(instance)
        }
    }
    public fun `Graphspecific DELETE WHERE 1 - None - PartitionByKeyAllCollations - false`() {
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
            val query21 = Query(instance)
            val graph21 = instance.tripleStoreManager!!.getGraph(inputGraph[0])
            val operator21 = graph21.getIterator(query21, arrayOf(AOPVariable(query21, "s"), AOPVariable(query21, "p"), AOPVariable(query21, "o")), EIndexPatternExt.SPO)
            val actual21 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator21, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
            val expected21 = MemoryTable.parseFromAny(inputData[0], inputType[0], Query(instance))!!
            val buf_err21 = MyPrintWriter()
            if (!expected21.equalsVerbose(actual21, true, true, buf_err21)) {
                fail(expected21.toString() + " .. " + actual21.toString() + " .. " + buf_err21.toString() + " .. " + operator21)
            }
            val query22 = Query(instance)
            val graph22 = instance.tripleStoreManager!!.getGraph(inputGraph[1])
            val operator22 = graph22.getIterator(query22, arrayOf(AOPVariable(query22, "s"), AOPVariable(query22, "p"), AOPVariable(query22, "o")), EIndexPatternExt.SPO)
            val actual22 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator22, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
            val expected22 = MemoryTable.parseFromAny(inputData[1], inputType[1], Query(instance))!!
            val buf_err22 = MyPrintWriter()
            if (!expected22.equalsVerbose(actual22, true, true, buf_err22)) {
                fail(expected22.toString() + " .. " + actual22.toString() + " .. " + buf_err22.toString() + " .. " + operator22)
            }
            val query23 = Query(instance)
            val graph23 = instance.tripleStoreManager!!.getGraph(inputGraph[2])
            val operator23 = graph23.getIterator(query23, arrayOf(AOPVariable(query23, "s"), AOPVariable(query23, "p"), AOPVariable(query23, "o")), EIndexPatternExt.SPO)
            val actual23 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator23, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
            val expected23 = MemoryTable.parseFromAny(inputData[2], inputType[2], Query(instance))!!
            val buf_err23 = MyPrintWriter()
            if (!expected23.equalsVerbose(actual23, true, true, buf_err23)) {
                fail(expected23.toString() + " .. " + actual23.toString() + " .. " + buf_err23.toString() + " .. " + operator23)
            }
            val operator24 = LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, query)
            LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator24, buf, EQueryResultToStreamExt.EMPTY_STREAM)
            val query25 = Query(instance)
            val graph25 = instance.tripleStoreManager!!.getGraph(outputGraph[0])
            val operator25 = graph25.getIterator(query25, arrayOf(AOPVariable(query25, "s"), AOPVariable(query25, "p"), AOPVariable(query25, "o")), EIndexPatternExt.SPO)
            val actual25 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator25, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
            val expected25 = MemoryTable.parseFromAny(outputData[0], outputType[0], Query(instance))!!
            val buf_err25 = MyPrintWriter()
            if (!expected25.equalsVerbose(actual25, true, true, buf_err25)) {
                fail(expected25.toString() + " .. " + actual25.toString() + " .. " + buf_err25.toString() + " .. " + operator25)
            }
            val query26 = Query(instance)
            val graph26 = instance.tripleStoreManager!!.getGraph(outputGraph[1])
            val operator26 = graph26.getIterator(query26, arrayOf(AOPVariable(query26, "s"), AOPVariable(query26, "p"), AOPVariable(query26, "o")), EIndexPatternExt.SPO)
            val actual26 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator26, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
            val expected26 = MemoryTable.parseFromAny(outputData[1], outputType[1], Query(instance))!!
            val buf_err26 = MyPrintWriter()
            if (!expected26.equalsVerbose(actual26, true, true, buf_err26)) {
                fail(expected26.toString() + " .. " + actual26.toString() + " .. " + buf_err26.toString() + " .. " + operator26)
            }
            val query27 = Query(instance)
            val graph27 = instance.tripleStoreManager!!.getGraph(outputGraph[2])
            val operator27 = graph27.getIterator(query27, arrayOf(AOPVariable(query27, "s"), AOPVariable(query27, "p"), AOPVariable(query27, "o")), EIndexPatternExt.SPO)
            val actual27 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator27, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
            val expected27 = MemoryTable.parseFromAny(outputData[2], outputType[2], Query(instance))!!
            val buf_err27 = MyPrintWriter()
            if (!expected27.equalsVerbose(actual27, true, true, buf_err27)) {
                fail(expected27.toString() + " .. " + actual27.toString() + " .. " + buf_err27.toString() + " .. " + operator27)
            }
        } finally {
            LuposdateEndpoint.close(instance)
        }
    }
    public fun `Graphspecific DELETE WHERE 1 - None - Simple - true`() {
        var instance = Luposdate3000Instance()
        try {
            instance.LUPOS_BUFFER_SIZE = 128
            instance.LUPOS_PARTITION_MODE = EPartitionModeExt.None
            instance.predefinedPartitionScheme = EPredefinedPartitionSchemesExt.Simple
            instance.useDictionaryInlineEncoding = true
            instance = LuposdateEndpoint.initializeB(instance)
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
            val query28 = Query(instance)
            val graph28 = instance.tripleStoreManager!!.getGraph(inputGraph[0])
            val operator28 = graph28.getIterator(query28, arrayOf(AOPVariable(query28, "s"), AOPVariable(query28, "p"), AOPVariable(query28, "o")), EIndexPatternExt.SPO)
            val actual28 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator28, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
            val expected28 = MemoryTable.parseFromAny(inputData[0], inputType[0], Query(instance))!!
            val buf_err28 = MyPrintWriter()
            if (!expected28.equalsVerbose(actual28, true, true, buf_err28)) {
                fail(expected28.toString() + " .. " + actual28.toString() + " .. " + buf_err28.toString() + " .. " + operator28)
            }
            val query29 = Query(instance)
            val graph29 = instance.tripleStoreManager!!.getGraph(inputGraph[1])
            val operator29 = graph29.getIterator(query29, arrayOf(AOPVariable(query29, "s"), AOPVariable(query29, "p"), AOPVariable(query29, "o")), EIndexPatternExt.SPO)
            val actual29 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator29, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
            val expected29 = MemoryTable.parseFromAny(inputData[1], inputType[1], Query(instance))!!
            val buf_err29 = MyPrintWriter()
            if (!expected29.equalsVerbose(actual29, true, true, buf_err29)) {
                fail(expected29.toString() + " .. " + actual29.toString() + " .. " + buf_err29.toString() + " .. " + operator29)
            }
            val query30 = Query(instance)
            val graph30 = instance.tripleStoreManager!!.getGraph(inputGraph[2])
            val operator30 = graph30.getIterator(query30, arrayOf(AOPVariable(query30, "s"), AOPVariable(query30, "p"), AOPVariable(query30, "o")), EIndexPatternExt.SPO)
            val actual30 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator30, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
            val expected30 = MemoryTable.parseFromAny(inputData[2], inputType[2], Query(instance))!!
            val buf_err30 = MyPrintWriter()
            if (!expected30.equalsVerbose(actual30, true, true, buf_err30)) {
                fail(expected30.toString() + " .. " + actual30.toString() + " .. " + buf_err30.toString() + " .. " + operator30)
            }
            val operator31 = LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, query)
            LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator31, buf, EQueryResultToStreamExt.EMPTY_STREAM)
            val query32 = Query(instance)
            val graph32 = instance.tripleStoreManager!!.getGraph(outputGraph[0])
            val operator32 = graph32.getIterator(query32, arrayOf(AOPVariable(query32, "s"), AOPVariable(query32, "p"), AOPVariable(query32, "o")), EIndexPatternExt.SPO)
            val actual32 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator32, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
            val expected32 = MemoryTable.parseFromAny(outputData[0], outputType[0], Query(instance))!!
            val buf_err32 = MyPrintWriter()
            if (!expected32.equalsVerbose(actual32, true, true, buf_err32)) {
                fail(expected32.toString() + " .. " + actual32.toString() + " .. " + buf_err32.toString() + " .. " + operator32)
            }
            val query33 = Query(instance)
            val graph33 = instance.tripleStoreManager!!.getGraph(outputGraph[1])
            val operator33 = graph33.getIterator(query33, arrayOf(AOPVariable(query33, "s"), AOPVariable(query33, "p"), AOPVariable(query33, "o")), EIndexPatternExt.SPO)
            val actual33 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator33, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
            val expected33 = MemoryTable.parseFromAny(outputData[1], outputType[1], Query(instance))!!
            val buf_err33 = MyPrintWriter()
            if (!expected33.equalsVerbose(actual33, true, true, buf_err33)) {
                fail(expected33.toString() + " .. " + actual33.toString() + " .. " + buf_err33.toString() + " .. " + operator33)
            }
            val query34 = Query(instance)
            val graph34 = instance.tripleStoreManager!!.getGraph(outputGraph[2])
            val operator34 = graph34.getIterator(query34, arrayOf(AOPVariable(query34, "s"), AOPVariable(query34, "p"), AOPVariable(query34, "o")), EIndexPatternExt.SPO)
            val actual34 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator34, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
            val expected34 = MemoryTable.parseFromAny(outputData[2], outputType[2], Query(instance))!!
            val buf_err34 = MyPrintWriter()
            if (!expected34.equalsVerbose(actual34, true, true, buf_err34)) {
                fail(expected34.toString() + " .. " + actual34.toString() + " .. " + buf_err34.toString() + " .. " + operator34)
            }
        } finally {
            LuposdateEndpoint.close(instance)
        }
    }
    public fun `Graphspecific DELETE WHERE 1 - None - Simple - false`() {
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
            val query35 = Query(instance)
            val graph35 = instance.tripleStoreManager!!.getGraph(inputGraph[0])
            val operator35 = graph35.getIterator(query35, arrayOf(AOPVariable(query35, "s"), AOPVariable(query35, "p"), AOPVariable(query35, "o")), EIndexPatternExt.SPO)
            val actual35 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator35, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
            val expected35 = MemoryTable.parseFromAny(inputData[0], inputType[0], Query(instance))!!
            val buf_err35 = MyPrintWriter()
            if (!expected35.equalsVerbose(actual35, true, true, buf_err35)) {
                fail(expected35.toString() + " .. " + actual35.toString() + " .. " + buf_err35.toString() + " .. " + operator35)
            }
            val query36 = Query(instance)
            val graph36 = instance.tripleStoreManager!!.getGraph(inputGraph[1])
            val operator36 = graph36.getIterator(query36, arrayOf(AOPVariable(query36, "s"), AOPVariable(query36, "p"), AOPVariable(query36, "o")), EIndexPatternExt.SPO)
            val actual36 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator36, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
            val expected36 = MemoryTable.parseFromAny(inputData[1], inputType[1], Query(instance))!!
            val buf_err36 = MyPrintWriter()
            if (!expected36.equalsVerbose(actual36, true, true, buf_err36)) {
                fail(expected36.toString() + " .. " + actual36.toString() + " .. " + buf_err36.toString() + " .. " + operator36)
            }
            val query37 = Query(instance)
            val graph37 = instance.tripleStoreManager!!.getGraph(inputGraph[2])
            val operator37 = graph37.getIterator(query37, arrayOf(AOPVariable(query37, "s"), AOPVariable(query37, "p"), AOPVariable(query37, "o")), EIndexPatternExt.SPO)
            val actual37 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator37, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
            val expected37 = MemoryTable.parseFromAny(inputData[2], inputType[2], Query(instance))!!
            val buf_err37 = MyPrintWriter()
            if (!expected37.equalsVerbose(actual37, true, true, buf_err37)) {
                fail(expected37.toString() + " .. " + actual37.toString() + " .. " + buf_err37.toString() + " .. " + operator37)
            }
            val operator38 = LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, query)
            LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator38, buf, EQueryResultToStreamExt.EMPTY_STREAM)
            val query39 = Query(instance)
            val graph39 = instance.tripleStoreManager!!.getGraph(outputGraph[0])
            val operator39 = graph39.getIterator(query39, arrayOf(AOPVariable(query39, "s"), AOPVariable(query39, "p"), AOPVariable(query39, "o")), EIndexPatternExt.SPO)
            val actual39 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator39, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
            val expected39 = MemoryTable.parseFromAny(outputData[0], outputType[0], Query(instance))!!
            val buf_err39 = MyPrintWriter()
            if (!expected39.equalsVerbose(actual39, true, true, buf_err39)) {
                fail(expected39.toString() + " .. " + actual39.toString() + " .. " + buf_err39.toString() + " .. " + operator39)
            }
            val query40 = Query(instance)
            val graph40 = instance.tripleStoreManager!!.getGraph(outputGraph[1])
            val operator40 = graph40.getIterator(query40, arrayOf(AOPVariable(query40, "s"), AOPVariable(query40, "p"), AOPVariable(query40, "o")), EIndexPatternExt.SPO)
            val actual40 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator40, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
            val expected40 = MemoryTable.parseFromAny(outputData[1], outputType[1], Query(instance))!!
            val buf_err40 = MyPrintWriter()
            if (!expected40.equalsVerbose(actual40, true, true, buf_err40)) {
                fail(expected40.toString() + " .. " + actual40.toString() + " .. " + buf_err40.toString() + " .. " + operator40)
            }
            val query41 = Query(instance)
            val graph41 = instance.tripleStoreManager!!.getGraph(outputGraph[2])
            val operator41 = graph41.getIterator(query41, arrayOf(AOPVariable(query41, "s"), AOPVariable(query41, "p"), AOPVariable(query41, "o")), EIndexPatternExt.SPO)
            val actual41 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator41, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
            val expected41 = MemoryTable.parseFromAny(outputData[2], outputType[2], Query(instance))!!
            val buf_err41 = MyPrintWriter()
            if (!expected41.equalsVerbose(actual41, true, true, buf_err41)) {
                fail(expected41.toString() + " .. " + actual41.toString() + " .. " + buf_err41.toString() + " .. " + operator41)
            }
        } finally {
            LuposdateEndpoint.close(instance)
        }
    }
    public fun `Graphspecific DELETE WHERE 1 - Thread - PartitionByIDTwiceAllCollations - true`() {
        var instance = Luposdate3000Instance()
        try {
            instance.LUPOS_BUFFER_SIZE = 128
            instance.LUPOS_PARTITION_MODE = EPartitionModeExt.Thread
            instance.predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByIDTwiceAllCollations
            instance.useDictionaryInlineEncoding = true
            instance = LuposdateEndpoint.initializeB(instance)
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
            val query42 = Query(instance)
            val graph42 = instance.tripleStoreManager!!.getGraph(inputGraph[0])
            val operator42 = graph42.getIterator(query42, arrayOf(AOPVariable(query42, "s"), AOPVariable(query42, "p"), AOPVariable(query42, "o")), EIndexPatternExt.SPO)
            val actual42 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator42, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
            val expected42 = MemoryTable.parseFromAny(inputData[0], inputType[0], Query(instance))!!
            val buf_err42 = MyPrintWriter()
            if (!expected42.equalsVerbose(actual42, true, true, buf_err42)) {
                fail(expected42.toString() + " .. " + actual42.toString() + " .. " + buf_err42.toString() + " .. " + operator42)
            }
            val query43 = Query(instance)
            val graph43 = instance.tripleStoreManager!!.getGraph(inputGraph[1])
            val operator43 = graph43.getIterator(query43, arrayOf(AOPVariable(query43, "s"), AOPVariable(query43, "p"), AOPVariable(query43, "o")), EIndexPatternExt.SPO)
            val actual43 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator43, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
            val expected43 = MemoryTable.parseFromAny(inputData[1], inputType[1], Query(instance))!!
            val buf_err43 = MyPrintWriter()
            if (!expected43.equalsVerbose(actual43, true, true, buf_err43)) {
                fail(expected43.toString() + " .. " + actual43.toString() + " .. " + buf_err43.toString() + " .. " + operator43)
            }
            val query44 = Query(instance)
            val graph44 = instance.tripleStoreManager!!.getGraph(inputGraph[2])
            val operator44 = graph44.getIterator(query44, arrayOf(AOPVariable(query44, "s"), AOPVariable(query44, "p"), AOPVariable(query44, "o")), EIndexPatternExt.SPO)
            val actual44 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator44, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
            val expected44 = MemoryTable.parseFromAny(inputData[2], inputType[2], Query(instance))!!
            val buf_err44 = MyPrintWriter()
            if (!expected44.equalsVerbose(actual44, true, true, buf_err44)) {
                fail(expected44.toString() + " .. " + actual44.toString() + " .. " + buf_err44.toString() + " .. " + operator44)
            }
            val operator45 = LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, query)
            LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator45, buf, EQueryResultToStreamExt.EMPTY_STREAM)
            val query46 = Query(instance)
            val graph46 = instance.tripleStoreManager!!.getGraph(outputGraph[0])
            val operator46 = graph46.getIterator(query46, arrayOf(AOPVariable(query46, "s"), AOPVariable(query46, "p"), AOPVariable(query46, "o")), EIndexPatternExt.SPO)
            val actual46 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator46, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
            val expected46 = MemoryTable.parseFromAny(outputData[0], outputType[0], Query(instance))!!
            val buf_err46 = MyPrintWriter()
            if (!expected46.equalsVerbose(actual46, true, true, buf_err46)) {
                fail(expected46.toString() + " .. " + actual46.toString() + " .. " + buf_err46.toString() + " .. " + operator46)
            }
            val query47 = Query(instance)
            val graph47 = instance.tripleStoreManager!!.getGraph(outputGraph[1])
            val operator47 = graph47.getIterator(query47, arrayOf(AOPVariable(query47, "s"), AOPVariable(query47, "p"), AOPVariable(query47, "o")), EIndexPatternExt.SPO)
            val actual47 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator47, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
            val expected47 = MemoryTable.parseFromAny(outputData[1], outputType[1], Query(instance))!!
            val buf_err47 = MyPrintWriter()
            if (!expected47.equalsVerbose(actual47, true, true, buf_err47)) {
                fail(expected47.toString() + " .. " + actual47.toString() + " .. " + buf_err47.toString() + " .. " + operator47)
            }
            val query48 = Query(instance)
            val graph48 = instance.tripleStoreManager!!.getGraph(outputGraph[2])
            val operator48 = graph48.getIterator(query48, arrayOf(AOPVariable(query48, "s"), AOPVariable(query48, "p"), AOPVariable(query48, "o")), EIndexPatternExt.SPO)
            val actual48 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator48, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
            val expected48 = MemoryTable.parseFromAny(outputData[2], outputType[2], Query(instance))!!
            val buf_err48 = MyPrintWriter()
            if (!expected48.equalsVerbose(actual48, true, true, buf_err48)) {
                fail(expected48.toString() + " .. " + actual48.toString() + " .. " + buf_err48.toString() + " .. " + operator48)
            }
        } finally {
            LuposdateEndpoint.close(instance)
        }
    }
    public fun `Graphspecific DELETE WHERE 1 - Thread - PartitionByIDTwiceAllCollations - false`() {
        var instance = Luposdate3000Instance()
        try {
            instance.LUPOS_BUFFER_SIZE = 128
            instance.LUPOS_PARTITION_MODE = EPartitionModeExt.Thread
            instance.predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByIDTwiceAllCollations
            instance.useDictionaryInlineEncoding = false
            instance = LuposdateEndpoint.initializeB(instance)
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
            val query49 = Query(instance)
            val graph49 = instance.tripleStoreManager!!.getGraph(inputGraph[0])
            val operator49 = graph49.getIterator(query49, arrayOf(AOPVariable(query49, "s"), AOPVariable(query49, "p"), AOPVariable(query49, "o")), EIndexPatternExt.SPO)
            val actual49 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator49, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
            val expected49 = MemoryTable.parseFromAny(inputData[0], inputType[0], Query(instance))!!
            val buf_err49 = MyPrintWriter()
            if (!expected49.equalsVerbose(actual49, true, true, buf_err49)) {
                fail(expected49.toString() + " .. " + actual49.toString() + " .. " + buf_err49.toString() + " .. " + operator49)
            }
            val query50 = Query(instance)
            val graph50 = instance.tripleStoreManager!!.getGraph(inputGraph[1])
            val operator50 = graph50.getIterator(query50, arrayOf(AOPVariable(query50, "s"), AOPVariable(query50, "p"), AOPVariable(query50, "o")), EIndexPatternExt.SPO)
            val actual50 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator50, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
            val expected50 = MemoryTable.parseFromAny(inputData[1], inputType[1], Query(instance))!!
            val buf_err50 = MyPrintWriter()
            if (!expected50.equalsVerbose(actual50, true, true, buf_err50)) {
                fail(expected50.toString() + " .. " + actual50.toString() + " .. " + buf_err50.toString() + " .. " + operator50)
            }
            val query51 = Query(instance)
            val graph51 = instance.tripleStoreManager!!.getGraph(inputGraph[2])
            val operator51 = graph51.getIterator(query51, arrayOf(AOPVariable(query51, "s"), AOPVariable(query51, "p"), AOPVariable(query51, "o")), EIndexPatternExt.SPO)
            val actual51 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator51, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
            val expected51 = MemoryTable.parseFromAny(inputData[2], inputType[2], Query(instance))!!
            val buf_err51 = MyPrintWriter()
            if (!expected51.equalsVerbose(actual51, true, true, buf_err51)) {
                fail(expected51.toString() + " .. " + actual51.toString() + " .. " + buf_err51.toString() + " .. " + operator51)
            }
            val operator52 = LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, query)
            LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator52, buf, EQueryResultToStreamExt.EMPTY_STREAM)
            val query53 = Query(instance)
            val graph53 = instance.tripleStoreManager!!.getGraph(outputGraph[0])
            val operator53 = graph53.getIterator(query53, arrayOf(AOPVariable(query53, "s"), AOPVariable(query53, "p"), AOPVariable(query53, "o")), EIndexPatternExt.SPO)
            val actual53 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator53, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
            val expected53 = MemoryTable.parseFromAny(outputData[0], outputType[0], Query(instance))!!
            val buf_err53 = MyPrintWriter()
            if (!expected53.equalsVerbose(actual53, true, true, buf_err53)) {
                fail(expected53.toString() + " .. " + actual53.toString() + " .. " + buf_err53.toString() + " .. " + operator53)
            }
            val query54 = Query(instance)
            val graph54 = instance.tripleStoreManager!!.getGraph(outputGraph[1])
            val operator54 = graph54.getIterator(query54, arrayOf(AOPVariable(query54, "s"), AOPVariable(query54, "p"), AOPVariable(query54, "o")), EIndexPatternExt.SPO)
            val actual54 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator54, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
            val expected54 = MemoryTable.parseFromAny(outputData[1], outputType[1], Query(instance))!!
            val buf_err54 = MyPrintWriter()
            if (!expected54.equalsVerbose(actual54, true, true, buf_err54)) {
                fail(expected54.toString() + " .. " + actual54.toString() + " .. " + buf_err54.toString() + " .. " + operator54)
            }
            val query55 = Query(instance)
            val graph55 = instance.tripleStoreManager!!.getGraph(outputGraph[2])
            val operator55 = graph55.getIterator(query55, arrayOf(AOPVariable(query55, "s"), AOPVariable(query55, "p"), AOPVariable(query55, "o")), EIndexPatternExt.SPO)
            val actual55 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator55, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
            val expected55 = MemoryTable.parseFromAny(outputData[2], outputType[2], Query(instance))!!
            val buf_err55 = MyPrintWriter()
            if (!expected55.equalsVerbose(actual55, true, true, buf_err55)) {
                fail(expected55.toString() + " .. " + actual55.toString() + " .. " + buf_err55.toString() + " .. " + operator55)
            }
        } finally {
            LuposdateEndpoint.close(instance)
        }
    }
    public fun `Graphspecific DELETE WHERE 1 - Thread - PartitionByKeyAllCollations - true`() {
        var instance = Luposdate3000Instance()
        try {
            instance.LUPOS_BUFFER_SIZE = 128
            instance.LUPOS_PARTITION_MODE = EPartitionModeExt.Thread
            instance.predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByKeyAllCollations
            instance.useDictionaryInlineEncoding = true
            instance = LuposdateEndpoint.initializeB(instance)
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
            val query56 = Query(instance)
            val graph56 = instance.tripleStoreManager!!.getGraph(inputGraph[0])
            val operator56 = graph56.getIterator(query56, arrayOf(AOPVariable(query56, "s"), AOPVariable(query56, "p"), AOPVariable(query56, "o")), EIndexPatternExt.SPO)
            val actual56 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator56, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
            val expected56 = MemoryTable.parseFromAny(inputData[0], inputType[0], Query(instance))!!
            val buf_err56 = MyPrintWriter()
            if (!expected56.equalsVerbose(actual56, true, true, buf_err56)) {
                fail(expected56.toString() + " .. " + actual56.toString() + " .. " + buf_err56.toString() + " .. " + operator56)
            }
            val query57 = Query(instance)
            val graph57 = instance.tripleStoreManager!!.getGraph(inputGraph[1])
            val operator57 = graph57.getIterator(query57, arrayOf(AOPVariable(query57, "s"), AOPVariable(query57, "p"), AOPVariable(query57, "o")), EIndexPatternExt.SPO)
            val actual57 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator57, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
            val expected57 = MemoryTable.parseFromAny(inputData[1], inputType[1], Query(instance))!!
            val buf_err57 = MyPrintWriter()
            if (!expected57.equalsVerbose(actual57, true, true, buf_err57)) {
                fail(expected57.toString() + " .. " + actual57.toString() + " .. " + buf_err57.toString() + " .. " + operator57)
            }
            val query58 = Query(instance)
            val graph58 = instance.tripleStoreManager!!.getGraph(inputGraph[2])
            val operator58 = graph58.getIterator(query58, arrayOf(AOPVariable(query58, "s"), AOPVariable(query58, "p"), AOPVariable(query58, "o")), EIndexPatternExt.SPO)
            val actual58 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator58, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
            val expected58 = MemoryTable.parseFromAny(inputData[2], inputType[2], Query(instance))!!
            val buf_err58 = MyPrintWriter()
            if (!expected58.equalsVerbose(actual58, true, true, buf_err58)) {
                fail(expected58.toString() + " .. " + actual58.toString() + " .. " + buf_err58.toString() + " .. " + operator58)
            }
            val operator59 = LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, query)
            LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator59, buf, EQueryResultToStreamExt.EMPTY_STREAM)
            val query60 = Query(instance)
            val graph60 = instance.tripleStoreManager!!.getGraph(outputGraph[0])
            val operator60 = graph60.getIterator(query60, arrayOf(AOPVariable(query60, "s"), AOPVariable(query60, "p"), AOPVariable(query60, "o")), EIndexPatternExt.SPO)
            val actual60 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator60, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
            val expected60 = MemoryTable.parseFromAny(outputData[0], outputType[0], Query(instance))!!
            val buf_err60 = MyPrintWriter()
            if (!expected60.equalsVerbose(actual60, true, true, buf_err60)) {
                fail(expected60.toString() + " .. " + actual60.toString() + " .. " + buf_err60.toString() + " .. " + operator60)
            }
            val query61 = Query(instance)
            val graph61 = instance.tripleStoreManager!!.getGraph(outputGraph[1])
            val operator61 = graph61.getIterator(query61, arrayOf(AOPVariable(query61, "s"), AOPVariable(query61, "p"), AOPVariable(query61, "o")), EIndexPatternExt.SPO)
            val actual61 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator61, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
            val expected61 = MemoryTable.parseFromAny(outputData[1], outputType[1], Query(instance))!!
            val buf_err61 = MyPrintWriter()
            if (!expected61.equalsVerbose(actual61, true, true, buf_err61)) {
                fail(expected61.toString() + " .. " + actual61.toString() + " .. " + buf_err61.toString() + " .. " + operator61)
            }
            val query62 = Query(instance)
            val graph62 = instance.tripleStoreManager!!.getGraph(outputGraph[2])
            val operator62 = graph62.getIterator(query62, arrayOf(AOPVariable(query62, "s"), AOPVariable(query62, "p"), AOPVariable(query62, "o")), EIndexPatternExt.SPO)
            val actual62 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator62, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
            val expected62 = MemoryTable.parseFromAny(outputData[2], outputType[2], Query(instance))!!
            val buf_err62 = MyPrintWriter()
            if (!expected62.equalsVerbose(actual62, true, true, buf_err62)) {
                fail(expected62.toString() + " .. " + actual62.toString() + " .. " + buf_err62.toString() + " .. " + operator62)
            }
        } finally {
            LuposdateEndpoint.close(instance)
        }
    }
    public fun `Graphspecific DELETE WHERE 1 - Thread - PartitionByKeyAllCollations - false`() {
        var instance = Luposdate3000Instance()
        try {
            instance.LUPOS_BUFFER_SIZE = 128
            instance.LUPOS_PARTITION_MODE = EPartitionModeExt.Thread
            instance.predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByKeyAllCollations
            instance.useDictionaryInlineEncoding = false
            instance = LuposdateEndpoint.initializeB(instance)
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
            val query63 = Query(instance)
            val graph63 = instance.tripleStoreManager!!.getGraph(inputGraph[0])
            val operator63 = graph63.getIterator(query63, arrayOf(AOPVariable(query63, "s"), AOPVariable(query63, "p"), AOPVariable(query63, "o")), EIndexPatternExt.SPO)
            val actual63 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator63, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
            val expected63 = MemoryTable.parseFromAny(inputData[0], inputType[0], Query(instance))!!
            val buf_err63 = MyPrintWriter()
            if (!expected63.equalsVerbose(actual63, true, true, buf_err63)) {
                fail(expected63.toString() + " .. " + actual63.toString() + " .. " + buf_err63.toString() + " .. " + operator63)
            }
            val query64 = Query(instance)
            val graph64 = instance.tripleStoreManager!!.getGraph(inputGraph[1])
            val operator64 = graph64.getIterator(query64, arrayOf(AOPVariable(query64, "s"), AOPVariable(query64, "p"), AOPVariable(query64, "o")), EIndexPatternExt.SPO)
            val actual64 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator64, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
            val expected64 = MemoryTable.parseFromAny(inputData[1], inputType[1], Query(instance))!!
            val buf_err64 = MyPrintWriter()
            if (!expected64.equalsVerbose(actual64, true, true, buf_err64)) {
                fail(expected64.toString() + " .. " + actual64.toString() + " .. " + buf_err64.toString() + " .. " + operator64)
            }
            val query65 = Query(instance)
            val graph65 = instance.tripleStoreManager!!.getGraph(inputGraph[2])
            val operator65 = graph65.getIterator(query65, arrayOf(AOPVariable(query65, "s"), AOPVariable(query65, "p"), AOPVariable(query65, "o")), EIndexPatternExt.SPO)
            val actual65 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator65, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
            val expected65 = MemoryTable.parseFromAny(inputData[2], inputType[2], Query(instance))!!
            val buf_err65 = MyPrintWriter()
            if (!expected65.equalsVerbose(actual65, true, true, buf_err65)) {
                fail(expected65.toString() + " .. " + actual65.toString() + " .. " + buf_err65.toString() + " .. " + operator65)
            }
            val operator66 = LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, query)
            LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator66, buf, EQueryResultToStreamExt.EMPTY_STREAM)
            val query67 = Query(instance)
            val graph67 = instance.tripleStoreManager!!.getGraph(outputGraph[0])
            val operator67 = graph67.getIterator(query67, arrayOf(AOPVariable(query67, "s"), AOPVariable(query67, "p"), AOPVariable(query67, "o")), EIndexPatternExt.SPO)
            val actual67 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator67, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
            val expected67 = MemoryTable.parseFromAny(outputData[0], outputType[0], Query(instance))!!
            val buf_err67 = MyPrintWriter()
            if (!expected67.equalsVerbose(actual67, true, true, buf_err67)) {
                fail(expected67.toString() + " .. " + actual67.toString() + " .. " + buf_err67.toString() + " .. " + operator67)
            }
            val query68 = Query(instance)
            val graph68 = instance.tripleStoreManager!!.getGraph(outputGraph[1])
            val operator68 = graph68.getIterator(query68, arrayOf(AOPVariable(query68, "s"), AOPVariable(query68, "p"), AOPVariable(query68, "o")), EIndexPatternExt.SPO)
            val actual68 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator68, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
            val expected68 = MemoryTable.parseFromAny(outputData[1], outputType[1], Query(instance))!!
            val buf_err68 = MyPrintWriter()
            if (!expected68.equalsVerbose(actual68, true, true, buf_err68)) {
                fail(expected68.toString() + " .. " + actual68.toString() + " .. " + buf_err68.toString() + " .. " + operator68)
            }
            val query69 = Query(instance)
            val graph69 = instance.tripleStoreManager!!.getGraph(outputGraph[2])
            val operator69 = graph69.getIterator(query69, arrayOf(AOPVariable(query69, "s"), AOPVariable(query69, "p"), AOPVariable(query69, "o")), EIndexPatternExt.SPO)
            val actual69 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator69, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
            val expected69 = MemoryTable.parseFromAny(outputData[2], outputType[2], Query(instance))!!
            val buf_err69 = MyPrintWriter()
            if (!expected69.equalsVerbose(actual69, true, true, buf_err69)) {
                fail(expected69.toString() + " .. " + actual69.toString() + " .. " + buf_err69.toString() + " .. " + operator69)
            }
        } finally {
            LuposdateEndpoint.close(instance)
        }
    }
    public fun `Graphspecific DELETE WHERE 1 - Thread - Simple - true`() {
        var instance = Luposdate3000Instance()
        try {
            instance.LUPOS_BUFFER_SIZE = 128
            instance.LUPOS_PARTITION_MODE = EPartitionModeExt.Thread
            instance.predefinedPartitionScheme = EPredefinedPartitionSchemesExt.Simple
            instance.useDictionaryInlineEncoding = true
            instance = LuposdateEndpoint.initializeB(instance)
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
            val query70 = Query(instance)
            val graph70 = instance.tripleStoreManager!!.getGraph(inputGraph[0])
            val operator70 = graph70.getIterator(query70, arrayOf(AOPVariable(query70, "s"), AOPVariable(query70, "p"), AOPVariable(query70, "o")), EIndexPatternExt.SPO)
            val actual70 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator70, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
            val expected70 = MemoryTable.parseFromAny(inputData[0], inputType[0], Query(instance))!!
            val buf_err70 = MyPrintWriter()
            if (!expected70.equalsVerbose(actual70, true, true, buf_err70)) {
                fail(expected70.toString() + " .. " + actual70.toString() + " .. " + buf_err70.toString() + " .. " + operator70)
            }
            val query71 = Query(instance)
            val graph71 = instance.tripleStoreManager!!.getGraph(inputGraph[1])
            val operator71 = graph71.getIterator(query71, arrayOf(AOPVariable(query71, "s"), AOPVariable(query71, "p"), AOPVariable(query71, "o")), EIndexPatternExt.SPO)
            val actual71 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator71, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
            val expected71 = MemoryTable.parseFromAny(inputData[1], inputType[1], Query(instance))!!
            val buf_err71 = MyPrintWriter()
            if (!expected71.equalsVerbose(actual71, true, true, buf_err71)) {
                fail(expected71.toString() + " .. " + actual71.toString() + " .. " + buf_err71.toString() + " .. " + operator71)
            }
            val query72 = Query(instance)
            val graph72 = instance.tripleStoreManager!!.getGraph(inputGraph[2])
            val operator72 = graph72.getIterator(query72, arrayOf(AOPVariable(query72, "s"), AOPVariable(query72, "p"), AOPVariable(query72, "o")), EIndexPatternExt.SPO)
            val actual72 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator72, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
            val expected72 = MemoryTable.parseFromAny(inputData[2], inputType[2], Query(instance))!!
            val buf_err72 = MyPrintWriter()
            if (!expected72.equalsVerbose(actual72, true, true, buf_err72)) {
                fail(expected72.toString() + " .. " + actual72.toString() + " .. " + buf_err72.toString() + " .. " + operator72)
            }
            val operator73 = LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, query)
            LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator73, buf, EQueryResultToStreamExt.EMPTY_STREAM)
            val query74 = Query(instance)
            val graph74 = instance.tripleStoreManager!!.getGraph(outputGraph[0])
            val operator74 = graph74.getIterator(query74, arrayOf(AOPVariable(query74, "s"), AOPVariable(query74, "p"), AOPVariable(query74, "o")), EIndexPatternExt.SPO)
            val actual74 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator74, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
            val expected74 = MemoryTable.parseFromAny(outputData[0], outputType[0], Query(instance))!!
            val buf_err74 = MyPrintWriter()
            if (!expected74.equalsVerbose(actual74, true, true, buf_err74)) {
                fail(expected74.toString() + " .. " + actual74.toString() + " .. " + buf_err74.toString() + " .. " + operator74)
            }
            val query75 = Query(instance)
            val graph75 = instance.tripleStoreManager!!.getGraph(outputGraph[1])
            val operator75 = graph75.getIterator(query75, arrayOf(AOPVariable(query75, "s"), AOPVariable(query75, "p"), AOPVariable(query75, "o")), EIndexPatternExt.SPO)
            val actual75 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator75, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
            val expected75 = MemoryTable.parseFromAny(outputData[1], outputType[1], Query(instance))!!
            val buf_err75 = MyPrintWriter()
            if (!expected75.equalsVerbose(actual75, true, true, buf_err75)) {
                fail(expected75.toString() + " .. " + actual75.toString() + " .. " + buf_err75.toString() + " .. " + operator75)
            }
            val query76 = Query(instance)
            val graph76 = instance.tripleStoreManager!!.getGraph(outputGraph[2])
            val operator76 = graph76.getIterator(query76, arrayOf(AOPVariable(query76, "s"), AOPVariable(query76, "p"), AOPVariable(query76, "o")), EIndexPatternExt.SPO)
            val actual76 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator76, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
            val expected76 = MemoryTable.parseFromAny(outputData[2], outputType[2], Query(instance))!!
            val buf_err76 = MyPrintWriter()
            if (!expected76.equalsVerbose(actual76, true, true, buf_err76)) {
                fail(expected76.toString() + " .. " + actual76.toString() + " .. " + buf_err76.toString() + " .. " + operator76)
            }
        } finally {
            LuposdateEndpoint.close(instance)
        }
    }
    public fun `Graphspecific DELETE WHERE 1 - Thread - Simple - false`() {
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
            val query77 = Query(instance)
            val graph77 = instance.tripleStoreManager!!.getGraph(inputGraph[0])
            val operator77 = graph77.getIterator(query77, arrayOf(AOPVariable(query77, "s"), AOPVariable(query77, "p"), AOPVariable(query77, "o")), EIndexPatternExt.SPO)
            val actual77 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator77, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
            val expected77 = MemoryTable.parseFromAny(inputData[0], inputType[0], Query(instance))!!
            val buf_err77 = MyPrintWriter()
            if (!expected77.equalsVerbose(actual77, true, true, buf_err77)) {
                fail(expected77.toString() + " .. " + actual77.toString() + " .. " + buf_err77.toString() + " .. " + operator77)
            }
            val query78 = Query(instance)
            val graph78 = instance.tripleStoreManager!!.getGraph(inputGraph[1])
            val operator78 = graph78.getIterator(query78, arrayOf(AOPVariable(query78, "s"), AOPVariable(query78, "p"), AOPVariable(query78, "o")), EIndexPatternExt.SPO)
            val actual78 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator78, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
            val expected78 = MemoryTable.parseFromAny(inputData[1], inputType[1], Query(instance))!!
            val buf_err78 = MyPrintWriter()
            if (!expected78.equalsVerbose(actual78, true, true, buf_err78)) {
                fail(expected78.toString() + " .. " + actual78.toString() + " .. " + buf_err78.toString() + " .. " + operator78)
            }
            val query79 = Query(instance)
            val graph79 = instance.tripleStoreManager!!.getGraph(inputGraph[2])
            val operator79 = graph79.getIterator(query79, arrayOf(AOPVariable(query79, "s"), AOPVariable(query79, "p"), AOPVariable(query79, "o")), EIndexPatternExt.SPO)
            val actual79 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator79, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
            val expected79 = MemoryTable.parseFromAny(inputData[2], inputType[2], Query(instance))!!
            val buf_err79 = MyPrintWriter()
            if (!expected79.equalsVerbose(actual79, true, true, buf_err79)) {
                fail(expected79.toString() + " .. " + actual79.toString() + " .. " + buf_err79.toString() + " .. " + operator79)
            }
            val operator80 = LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, query)
            LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator80, buf, EQueryResultToStreamExt.EMPTY_STREAM)
            val query81 = Query(instance)
            val graph81 = instance.tripleStoreManager!!.getGraph(outputGraph[0])
            val operator81 = graph81.getIterator(query81, arrayOf(AOPVariable(query81, "s"), AOPVariable(query81, "p"), AOPVariable(query81, "o")), EIndexPatternExt.SPO)
            val actual81 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator81, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
            val expected81 = MemoryTable.parseFromAny(outputData[0], outputType[0], Query(instance))!!
            val buf_err81 = MyPrintWriter()
            if (!expected81.equalsVerbose(actual81, true, true, buf_err81)) {
                fail(expected81.toString() + " .. " + actual81.toString() + " .. " + buf_err81.toString() + " .. " + operator81)
            }
            val query82 = Query(instance)
            val graph82 = instance.tripleStoreManager!!.getGraph(outputGraph[1])
            val operator82 = graph82.getIterator(query82, arrayOf(AOPVariable(query82, "s"), AOPVariable(query82, "p"), AOPVariable(query82, "o")), EIndexPatternExt.SPO)
            val actual82 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator82, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
            val expected82 = MemoryTable.parseFromAny(outputData[1], outputType[1], Query(instance))!!
            val buf_err82 = MyPrintWriter()
            if (!expected82.equalsVerbose(actual82, true, true, buf_err82)) {
                fail(expected82.toString() + " .. " + actual82.toString() + " .. " + buf_err82.toString() + " .. " + operator82)
            }
            val query83 = Query(instance)
            val graph83 = instance.tripleStoreManager!!.getGraph(outputGraph[2])
            val operator83 = graph83.getIterator(query83, arrayOf(AOPVariable(query83, "s"), AOPVariable(query83, "p"), AOPVariable(query83, "o")), EIndexPatternExt.SPO)
            val actual83 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator83, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
            val expected83 = MemoryTable.parseFromAny(outputData[2], outputType[2], Query(instance))!!
            val buf_err83 = MyPrintWriter()
            if (!expected83.equalsVerbose(actual83, true, true, buf_err83)) {
                fail(expected83.toString() + " .. " + actual83.toString() + " .. " + buf_err83.toString() + " .. " + operator83)
            }
        } finally {
            LuposdateEndpoint.close(instance)
        }
    }

    @Ignore // Reason: >Bug<
    @Test
    public fun `Graphspecific DELETE WHERE 1 - in simulator - PartitionByIDTwiceAllCollations - Centralized - true - None`() {
        simulatorHelper(
            "../luposdate3000_simulator_iot/src/jvmTest/resources/autoIntegrationTest/test2.json",
            mutableMapOf(
                "predefinedPartitionScheme" to "PartitionByIDTwiceAllCollations",
                "mergeLocalOperatorgraphs" to true,
                "queryDistributionMode" to "Centralized",
                "useDictionaryInlineEncoding" to true,
                "REPLACE_STORE_WITH_VALUES" to false,
                "LUPOS_PARTITION_MODE" to "None",
            )
        )
    }

    @Ignore // Reason: >Bug<
    @Test
    public fun `Graphspecific DELETE WHERE 1 - in simulator - PartitionByIDTwiceAllCollations - Centralized - false - None`() {
        simulatorHelper(
            "../luposdate3000_simulator_iot/src/jvmTest/resources/autoIntegrationTest/test2.json",
            mutableMapOf(
                "predefinedPartitionScheme" to "PartitionByIDTwiceAllCollations",
                "mergeLocalOperatorgraphs" to true,
                "queryDistributionMode" to "Centralized",
                "useDictionaryInlineEncoding" to false,
                "REPLACE_STORE_WITH_VALUES" to false,
                "LUPOS_PARTITION_MODE" to "None",
            )
        )
    }

    @Ignore // Reason: >Bug<
    @Test
    public fun `Graphspecific DELETE WHERE 1 - in simulator - PartitionByKeyAllCollations - Centralized - true - None`() {
        simulatorHelper(
            "../luposdate3000_simulator_iot/src/jvmTest/resources/autoIntegrationTest/test2.json",
            mutableMapOf(
                "predefinedPartitionScheme" to "PartitionByKeyAllCollations",
                "mergeLocalOperatorgraphs" to true,
                "queryDistributionMode" to "Centralized",
                "useDictionaryInlineEncoding" to true,
                "REPLACE_STORE_WITH_VALUES" to false,
                "LUPOS_PARTITION_MODE" to "None",
            )
        )
    }

    @Ignore // Reason: >Bug<
    @Test
    public fun `Graphspecific DELETE WHERE 1 - in simulator - PartitionByKeyAllCollations - Centralized - false - None`() {
        simulatorHelper(
            "../luposdate3000_simulator_iot/src/jvmTest/resources/autoIntegrationTest/test2.json",
            mutableMapOf(
                "predefinedPartitionScheme" to "PartitionByKeyAllCollations",
                "mergeLocalOperatorgraphs" to true,
                "queryDistributionMode" to "Centralized",
                "useDictionaryInlineEncoding" to false,
                "REPLACE_STORE_WITH_VALUES" to false,
                "LUPOS_PARTITION_MODE" to "None",
            )
        )
    }

    @Ignore // Reason: >Bug<
    @Test
    public fun `Graphspecific DELETE WHERE 1 - in simulator - Simple - Centralized - true - None`() {
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

    @Ignore // Reason: >Bug<
    @Test
    public fun `Graphspecific DELETE WHERE 1 - in simulator - Simple - Centralized - false - None`() {
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

    @Ignore // Reason: >Bug<
    @Test
    public fun `Graphspecific DELETE WHERE 1 - in simulator - PartitionByIDTwiceAllCollations - Centralized - true - Process`() {
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
    public fun `Graphspecific DELETE WHERE 1 - in simulator - PartitionByIDTwiceAllCollations - Centralized - false - Process`() {
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
    public fun `Graphspecific DELETE WHERE 1 - in simulator - PartitionByIDTwiceAllCollations - Routing - true - Process`() {
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
    public fun `Graphspecific DELETE WHERE 1 - in simulator - PartitionByIDTwiceAllCollations - Routing - false - Process`() {
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
    public fun `Graphspecific DELETE WHERE 1 - in simulator - PartitionByKeyAllCollations - Centralized - true - Process`() {
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
    public fun `Graphspecific DELETE WHERE 1 - in simulator - PartitionByKeyAllCollations - Centralized - false - Process`() {
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
    public fun `Graphspecific DELETE WHERE 1 - in simulator - PartitionByKeyAllCollations - Routing - true - Process`() {
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
    public fun `Graphspecific DELETE WHERE 1 - in simulator - PartitionByKeyAllCollations - Routing - false - Process`() {
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
    public fun `Graphspecific DELETE WHERE 1 - in simulator - PartitionByIDTwiceAllCollations - Centralized - true - Thread`() {
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

    @Ignore // Reason: >Bug<
    @Test
    public fun `Graphspecific DELETE WHERE 1 - in simulator - PartitionByIDTwiceAllCollations - Centralized - false - Thread`() {
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

    @Ignore // Reason: >Bug<
    @Test
    public fun `Graphspecific DELETE WHERE 1 - in simulator - PartitionByKeyAllCollations - Centralized - true - Thread`() {
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

    @Ignore // Reason: >Bug<
    @Test
    public fun `Graphspecific DELETE WHERE 1 - in simulator - PartitionByKeyAllCollations - Centralized - false - Thread`() {
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

    @Ignore // Reason: >Bug<
    @Test
    public fun `Graphspecific DELETE WHERE 1 - in simulator - Simple - Centralized - true - Thread`() {
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

    @Ignore // Reason: >Bug<
    @Test
    public fun `Graphspecific DELETE WHERE 1 - in simulator - Simple - Centralized - false - Thread`() {
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
        val instance = config.devices.filter { it.userApplication != null }.map { it.getAllChildApplications().filter { it is DatabaseHandle } }.flatten().first().instance
        val pkg0 = MySimulatorTestingImportPackage(inputData[0], inputGraph[0], inputType[0])
        val pkg1 = MySimulatorTestingImportPackage(inputData[1], inputGraph[1], inputType[1])
        pkg0.onFinish = pkg1
        val pkg2 = MySimulatorTestingImportPackage(inputData[2], inputGraph[2], inputType[2])
        pkg1.onFinish = pkg2
        var verifyExecuted3 = 0
        val pkg3 = MySimulatorTestingCompareGraphPackage("SELECT ?s ?p ?o WHERE { ?s ?p ?o . }", MemoryTable.parseFromAny(inputData[0], inputType[0], Query(instance))!!, { verifyExecuted3++ })
        pkg2.onFinish = pkg3
        var verifyExecuted4 = 0
        val pkg4 = MySimulatorTestingCompareGraphPackage("SELECT ?s ?p ?o WHERE { GRAPH <${inputGraph[1]}> { ?s ?p ?o . }}", MemoryTable.parseFromAny(inputData[1], inputType[1], Query(instance))!!, { verifyExecuted4++ })
        pkg3.onFinish = pkg4
        var verifyExecuted5 = 0
        val pkg5 = MySimulatorTestingCompareGraphPackage("SELECT ?s ?p ?o WHERE { GRAPH <${inputGraph[2]}> { ?s ?p ?o . }}", MemoryTable.parseFromAny(inputData[2], inputType[2], Query(instance))!!, { verifyExecuted5++ })
        pkg4.onFinish = pkg5
        val pkg6 = MySimulatorTestingExecute(query)
        pkg5.onFinish = pkg6
        var verifyExecuted7 = 0
        val pkg7 = MySimulatorTestingCompareGraphPackage("SELECT ?s ?p ?o WHERE { ?s ?p ?o . }", MemoryTable.parseFromAny(outputData[0], outputType[0], Query(instance))!!, { verifyExecuted7++ })
        pkg6.onFinish = pkg7
        var verifyExecuted8 = 0
        val pkg8 = MySimulatorTestingCompareGraphPackage("SELECT ?s ?p ?o WHERE { GRAPH <${outputGraph[1]}> { ?s ?p ?o . }}", MemoryTable.parseFromAny(outputData[1], outputType[1], Query(instance))!!, { verifyExecuted8++ })
        pkg7.onFinish = pkg8
        var verifyExecuted9 = 0
        val pkg9 = MySimulatorTestingCompareGraphPackage("SELECT ?s ?p ?o WHERE { GRAPH <${outputGraph[2]}> { ?s ?p ?o . }}", MemoryTable.parseFromAny(outputData[2], outputType[2], Query(instance))!!, { verifyExecuted9++ })
        pkg8.onFinish = pkg9
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
        if (verifyExecuted7 == 0) {
            fail("pck7 not verified")
        }
        if (verifyExecuted8 == 0) {
            fail("pck8 not verified")
        }
        if (verifyExecuted9 == 0) {
            fail("pck9 not verified")
        }
    }
}
