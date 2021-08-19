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
package lupos.code_gen_test_05
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

public class ADD6 {
    internal val inputData = arrayOf(
        File("src/jvmTest/resources/ADD6.input").readAsString(),
        File("src/jvmTest/resources/ADD6.input1").readAsString(),
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
        File("src/jvmTest/resources/ADD6.output0").readAsString(),
        File("src/jvmTest/resources/ADD6.output1").readAsString(),
    )
    internal val outputGraph = arrayOf(
        "",
        "http://example.org/g1",
    )
    internal val outputType = arrayOf(
        ".ttl",
        ".ttl",
    )
    internal val query = "PREFIX : <http://example.org/> \n" +
        "ADD SILENT :g4 TO :g1"

    @Test
    public fun `ADD 6 - None - PartitionByIDTwiceAllCollations - true`() {
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
        } finally {
            LuposdateEndpoint.close(instance)
        }
    }
    public fun `ADD 6 - None - PartitionByIDTwiceAllCollations - false`() {
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
            val query5 = Query(instance)
            val graph5 = instance.tripleStoreManager!!.getGraph(inputGraph[0])
            val operator5 = graph5.getIterator(query5, arrayOf(AOPVariable(query5, "s"), AOPVariable(query5, "p"), AOPVariable(query5, "o")), EIndexPatternExt.SPO)
            val actual5 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator5, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
            val expected5 = MemoryTable.parseFromAny(inputData[0], inputType[0], Query(instance))!!
            val buf_err5 = MyPrintWriter()
            if (!expected5.equalsVerbose(actual5, true, true, buf_err5)) {
                fail(expected5.toString() + " .. " + actual5.toString() + " .. " + buf_err5.toString() + " .. " + operator5)
            }
            val query6 = Query(instance)
            val graph6 = instance.tripleStoreManager!!.getGraph(inputGraph[1])
            val operator6 = graph6.getIterator(query6, arrayOf(AOPVariable(query6, "s"), AOPVariable(query6, "p"), AOPVariable(query6, "o")), EIndexPatternExt.SPO)
            val actual6 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator6, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
            val expected6 = MemoryTable.parseFromAny(inputData[1], inputType[1], Query(instance))!!
            val buf_err6 = MyPrintWriter()
            if (!expected6.equalsVerbose(actual6, true, true, buf_err6)) {
                fail(expected6.toString() + " .. " + actual6.toString() + " .. " + buf_err6.toString() + " .. " + operator6)
            }
            val operator7 = LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, query)
            LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator7, buf, EQueryResultToStreamExt.EMPTY_STREAM)
            val query8 = Query(instance)
            val graph8 = instance.tripleStoreManager!!.getGraph(outputGraph[0])
            val operator8 = graph8.getIterator(query8, arrayOf(AOPVariable(query8, "s"), AOPVariable(query8, "p"), AOPVariable(query8, "o")), EIndexPatternExt.SPO)
            val actual8 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator8, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
            val expected8 = MemoryTable.parseFromAny(outputData[0], outputType[0], Query(instance))!!
            val buf_err8 = MyPrintWriter()
            if (!expected8.equalsVerbose(actual8, true, true, buf_err8)) {
                fail(expected8.toString() + " .. " + actual8.toString() + " .. " + buf_err8.toString() + " .. " + operator8)
            }
            val query9 = Query(instance)
            val graph9 = instance.tripleStoreManager!!.getGraph(outputGraph[1])
            val operator9 = graph9.getIterator(query9, arrayOf(AOPVariable(query9, "s"), AOPVariable(query9, "p"), AOPVariable(query9, "o")), EIndexPatternExt.SPO)
            val actual9 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator9, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
            val expected9 = MemoryTable.parseFromAny(outputData[1], outputType[1], Query(instance))!!
            val buf_err9 = MyPrintWriter()
            if (!expected9.equalsVerbose(actual9, true, true, buf_err9)) {
                fail(expected9.toString() + " .. " + actual9.toString() + " .. " + buf_err9.toString() + " .. " + operator9)
            }
        } finally {
            LuposdateEndpoint.close(instance)
        }
    }
    public fun `ADD 6 - None - PartitionByKeyAllCollations - true`() {
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
            val query10 = Query(instance)
            val graph10 = instance.tripleStoreManager!!.getGraph(inputGraph[0])
            val operator10 = graph10.getIterator(query10, arrayOf(AOPVariable(query10, "s"), AOPVariable(query10, "p"), AOPVariable(query10, "o")), EIndexPatternExt.SPO)
            val actual10 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator10, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
            val expected10 = MemoryTable.parseFromAny(inputData[0], inputType[0], Query(instance))!!
            val buf_err10 = MyPrintWriter()
            if (!expected10.equalsVerbose(actual10, true, true, buf_err10)) {
                fail(expected10.toString() + " .. " + actual10.toString() + " .. " + buf_err10.toString() + " .. " + operator10)
            }
            val query11 = Query(instance)
            val graph11 = instance.tripleStoreManager!!.getGraph(inputGraph[1])
            val operator11 = graph11.getIterator(query11, arrayOf(AOPVariable(query11, "s"), AOPVariable(query11, "p"), AOPVariable(query11, "o")), EIndexPatternExt.SPO)
            val actual11 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator11, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
            val expected11 = MemoryTable.parseFromAny(inputData[1], inputType[1], Query(instance))!!
            val buf_err11 = MyPrintWriter()
            if (!expected11.equalsVerbose(actual11, true, true, buf_err11)) {
                fail(expected11.toString() + " .. " + actual11.toString() + " .. " + buf_err11.toString() + " .. " + operator11)
            }
            val operator12 = LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, query)
            LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator12, buf, EQueryResultToStreamExt.EMPTY_STREAM)
            val query13 = Query(instance)
            val graph13 = instance.tripleStoreManager!!.getGraph(outputGraph[0])
            val operator13 = graph13.getIterator(query13, arrayOf(AOPVariable(query13, "s"), AOPVariable(query13, "p"), AOPVariable(query13, "o")), EIndexPatternExt.SPO)
            val actual13 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator13, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
            val expected13 = MemoryTable.parseFromAny(outputData[0], outputType[0], Query(instance))!!
            val buf_err13 = MyPrintWriter()
            if (!expected13.equalsVerbose(actual13, true, true, buf_err13)) {
                fail(expected13.toString() + " .. " + actual13.toString() + " .. " + buf_err13.toString() + " .. " + operator13)
            }
            val query14 = Query(instance)
            val graph14 = instance.tripleStoreManager!!.getGraph(outputGraph[1])
            val operator14 = graph14.getIterator(query14, arrayOf(AOPVariable(query14, "s"), AOPVariable(query14, "p"), AOPVariable(query14, "o")), EIndexPatternExt.SPO)
            val actual14 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator14, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
            val expected14 = MemoryTable.parseFromAny(outputData[1], outputType[1], Query(instance))!!
            val buf_err14 = MyPrintWriter()
            if (!expected14.equalsVerbose(actual14, true, true, buf_err14)) {
                fail(expected14.toString() + " .. " + actual14.toString() + " .. " + buf_err14.toString() + " .. " + operator14)
            }
        } finally {
            LuposdateEndpoint.close(instance)
        }
    }
    public fun `ADD 6 - None - PartitionByKeyAllCollations - false`() {
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
            val query15 = Query(instance)
            val graph15 = instance.tripleStoreManager!!.getGraph(inputGraph[0])
            val operator15 = graph15.getIterator(query15, arrayOf(AOPVariable(query15, "s"), AOPVariable(query15, "p"), AOPVariable(query15, "o")), EIndexPatternExt.SPO)
            val actual15 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator15, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
            val expected15 = MemoryTable.parseFromAny(inputData[0], inputType[0], Query(instance))!!
            val buf_err15 = MyPrintWriter()
            if (!expected15.equalsVerbose(actual15, true, true, buf_err15)) {
                fail(expected15.toString() + " .. " + actual15.toString() + " .. " + buf_err15.toString() + " .. " + operator15)
            }
            val query16 = Query(instance)
            val graph16 = instance.tripleStoreManager!!.getGraph(inputGraph[1])
            val operator16 = graph16.getIterator(query16, arrayOf(AOPVariable(query16, "s"), AOPVariable(query16, "p"), AOPVariable(query16, "o")), EIndexPatternExt.SPO)
            val actual16 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator16, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
            val expected16 = MemoryTable.parseFromAny(inputData[1], inputType[1], Query(instance))!!
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
        } finally {
            LuposdateEndpoint.close(instance)
        }
    }
    public fun `ADD 6 - None - Simple - true`() {
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
            val query20 = Query(instance)
            val graph20 = instance.tripleStoreManager!!.getGraph(inputGraph[0])
            val operator20 = graph20.getIterator(query20, arrayOf(AOPVariable(query20, "s"), AOPVariable(query20, "p"), AOPVariable(query20, "o")), EIndexPatternExt.SPO)
            val actual20 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator20, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
            val expected20 = MemoryTable.parseFromAny(inputData[0], inputType[0], Query(instance))!!
            val buf_err20 = MyPrintWriter()
            if (!expected20.equalsVerbose(actual20, true, true, buf_err20)) {
                fail(expected20.toString() + " .. " + actual20.toString() + " .. " + buf_err20.toString() + " .. " + operator20)
            }
            val query21 = Query(instance)
            val graph21 = instance.tripleStoreManager!!.getGraph(inputGraph[1])
            val operator21 = graph21.getIterator(query21, arrayOf(AOPVariable(query21, "s"), AOPVariable(query21, "p"), AOPVariable(query21, "o")), EIndexPatternExt.SPO)
            val actual21 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator21, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
            val expected21 = MemoryTable.parseFromAny(inputData[1], inputType[1], Query(instance))!!
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
            val query24 = Query(instance)
            val graph24 = instance.tripleStoreManager!!.getGraph(outputGraph[1])
            val operator24 = graph24.getIterator(query24, arrayOf(AOPVariable(query24, "s"), AOPVariable(query24, "p"), AOPVariable(query24, "o")), EIndexPatternExt.SPO)
            val actual24 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator24, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
            val expected24 = MemoryTable.parseFromAny(outputData[1], outputType[1], Query(instance))!!
            val buf_err24 = MyPrintWriter()
            if (!expected24.equalsVerbose(actual24, true, true, buf_err24)) {
                fail(expected24.toString() + " .. " + actual24.toString() + " .. " + buf_err24.toString() + " .. " + operator24)
            }
        } finally {
            LuposdateEndpoint.close(instance)
        }
    }
    public fun `ADD 6 - None - Simple - false`() {
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
            val query25 = Query(instance)
            val graph25 = instance.tripleStoreManager!!.getGraph(inputGraph[0])
            val operator25 = graph25.getIterator(query25, arrayOf(AOPVariable(query25, "s"), AOPVariable(query25, "p"), AOPVariable(query25, "o")), EIndexPatternExt.SPO)
            val actual25 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator25, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
            val expected25 = MemoryTable.parseFromAny(inputData[0], inputType[0], Query(instance))!!
            val buf_err25 = MyPrintWriter()
            if (!expected25.equalsVerbose(actual25, true, true, buf_err25)) {
                fail(expected25.toString() + " .. " + actual25.toString() + " .. " + buf_err25.toString() + " .. " + operator25)
            }
            val query26 = Query(instance)
            val graph26 = instance.tripleStoreManager!!.getGraph(inputGraph[1])
            val operator26 = graph26.getIterator(query26, arrayOf(AOPVariable(query26, "s"), AOPVariable(query26, "p"), AOPVariable(query26, "o")), EIndexPatternExt.SPO)
            val actual26 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator26, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
            val expected26 = MemoryTable.parseFromAny(inputData[1], inputType[1], Query(instance))!!
            val buf_err26 = MyPrintWriter()
            if (!expected26.equalsVerbose(actual26, true, true, buf_err26)) {
                fail(expected26.toString() + " .. " + actual26.toString() + " .. " + buf_err26.toString() + " .. " + operator26)
            }
            val operator27 = LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, query)
            LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator27, buf, EQueryResultToStreamExt.EMPTY_STREAM)
            val query28 = Query(instance)
            val graph28 = instance.tripleStoreManager!!.getGraph(outputGraph[0])
            val operator28 = graph28.getIterator(query28, arrayOf(AOPVariable(query28, "s"), AOPVariable(query28, "p"), AOPVariable(query28, "o")), EIndexPatternExt.SPO)
            val actual28 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator28, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
            val expected28 = MemoryTable.parseFromAny(outputData[0], outputType[0], Query(instance))!!
            val buf_err28 = MyPrintWriter()
            if (!expected28.equalsVerbose(actual28, true, true, buf_err28)) {
                fail(expected28.toString() + " .. " + actual28.toString() + " .. " + buf_err28.toString() + " .. " + operator28)
            }
            val query29 = Query(instance)
            val graph29 = instance.tripleStoreManager!!.getGraph(outputGraph[1])
            val operator29 = graph29.getIterator(query29, arrayOf(AOPVariable(query29, "s"), AOPVariable(query29, "p"), AOPVariable(query29, "o")), EIndexPatternExt.SPO)
            val actual29 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator29, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
            val expected29 = MemoryTable.parseFromAny(outputData[1], outputType[1], Query(instance))!!
            val buf_err29 = MyPrintWriter()
            if (!expected29.equalsVerbose(actual29, true, true, buf_err29)) {
                fail(expected29.toString() + " .. " + actual29.toString() + " .. " + buf_err29.toString() + " .. " + operator29)
            }
        } finally {
            LuposdateEndpoint.close(instance)
        }
    }
    public fun `ADD 6 - Thread - PartitionByIDTwiceAllCollations - true`() {
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
            val query30 = Query(instance)
            val graph30 = instance.tripleStoreManager!!.getGraph(inputGraph[0])
            val operator30 = graph30.getIterator(query30, arrayOf(AOPVariable(query30, "s"), AOPVariable(query30, "p"), AOPVariable(query30, "o")), EIndexPatternExt.SPO)
            val actual30 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator30, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
            val expected30 = MemoryTable.parseFromAny(inputData[0], inputType[0], Query(instance))!!
            val buf_err30 = MyPrintWriter()
            if (!expected30.equalsVerbose(actual30, true, true, buf_err30)) {
                fail(expected30.toString() + " .. " + actual30.toString() + " .. " + buf_err30.toString() + " .. " + operator30)
            }
            val query31 = Query(instance)
            val graph31 = instance.tripleStoreManager!!.getGraph(inputGraph[1])
            val operator31 = graph31.getIterator(query31, arrayOf(AOPVariable(query31, "s"), AOPVariable(query31, "p"), AOPVariable(query31, "o")), EIndexPatternExt.SPO)
            val actual31 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator31, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
            val expected31 = MemoryTable.parseFromAny(inputData[1], inputType[1], Query(instance))!!
            val buf_err31 = MyPrintWriter()
            if (!expected31.equalsVerbose(actual31, true, true, buf_err31)) {
                fail(expected31.toString() + " .. " + actual31.toString() + " .. " + buf_err31.toString() + " .. " + operator31)
            }
            val operator32 = LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, query)
            LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator32, buf, EQueryResultToStreamExt.EMPTY_STREAM)
            val query33 = Query(instance)
            val graph33 = instance.tripleStoreManager!!.getGraph(outputGraph[0])
            val operator33 = graph33.getIterator(query33, arrayOf(AOPVariable(query33, "s"), AOPVariable(query33, "p"), AOPVariable(query33, "o")), EIndexPatternExt.SPO)
            val actual33 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator33, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
            val expected33 = MemoryTable.parseFromAny(outputData[0], outputType[0], Query(instance))!!
            val buf_err33 = MyPrintWriter()
            if (!expected33.equalsVerbose(actual33, true, true, buf_err33)) {
                fail(expected33.toString() + " .. " + actual33.toString() + " .. " + buf_err33.toString() + " .. " + operator33)
            }
            val query34 = Query(instance)
            val graph34 = instance.tripleStoreManager!!.getGraph(outputGraph[1])
            val operator34 = graph34.getIterator(query34, arrayOf(AOPVariable(query34, "s"), AOPVariable(query34, "p"), AOPVariable(query34, "o")), EIndexPatternExt.SPO)
            val actual34 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator34, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
            val expected34 = MemoryTable.parseFromAny(outputData[1], outputType[1], Query(instance))!!
            val buf_err34 = MyPrintWriter()
            if (!expected34.equalsVerbose(actual34, true, true, buf_err34)) {
                fail(expected34.toString() + " .. " + actual34.toString() + " .. " + buf_err34.toString() + " .. " + operator34)
            }
        } finally {
            LuposdateEndpoint.close(instance)
        }
    }
    public fun `ADD 6 - Thread - PartitionByIDTwiceAllCollations - false`() {
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
            val operator37 = LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, query)
            LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator37, buf, EQueryResultToStreamExt.EMPTY_STREAM)
            val query38 = Query(instance)
            val graph38 = instance.tripleStoreManager!!.getGraph(outputGraph[0])
            val operator38 = graph38.getIterator(query38, arrayOf(AOPVariable(query38, "s"), AOPVariable(query38, "p"), AOPVariable(query38, "o")), EIndexPatternExt.SPO)
            val actual38 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator38, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
            val expected38 = MemoryTable.parseFromAny(outputData[0], outputType[0], Query(instance))!!
            val buf_err38 = MyPrintWriter()
            if (!expected38.equalsVerbose(actual38, true, true, buf_err38)) {
                fail(expected38.toString() + " .. " + actual38.toString() + " .. " + buf_err38.toString() + " .. " + operator38)
            }
            val query39 = Query(instance)
            val graph39 = instance.tripleStoreManager!!.getGraph(outputGraph[1])
            val operator39 = graph39.getIterator(query39, arrayOf(AOPVariable(query39, "s"), AOPVariable(query39, "p"), AOPVariable(query39, "o")), EIndexPatternExt.SPO)
            val actual39 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator39, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
            val expected39 = MemoryTable.parseFromAny(outputData[1], outputType[1], Query(instance))!!
            val buf_err39 = MyPrintWriter()
            if (!expected39.equalsVerbose(actual39, true, true, buf_err39)) {
                fail(expected39.toString() + " .. " + actual39.toString() + " .. " + buf_err39.toString() + " .. " + operator39)
            }
        } finally {
            LuposdateEndpoint.close(instance)
        }
    }
    public fun `ADD 6 - Thread - PartitionByKeyAllCollations - true`() {
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
            val query40 = Query(instance)
            val graph40 = instance.tripleStoreManager!!.getGraph(inputGraph[0])
            val operator40 = graph40.getIterator(query40, arrayOf(AOPVariable(query40, "s"), AOPVariable(query40, "p"), AOPVariable(query40, "o")), EIndexPatternExt.SPO)
            val actual40 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator40, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
            val expected40 = MemoryTable.parseFromAny(inputData[0], inputType[0], Query(instance))!!
            val buf_err40 = MyPrintWriter()
            if (!expected40.equalsVerbose(actual40, true, true, buf_err40)) {
                fail(expected40.toString() + " .. " + actual40.toString() + " .. " + buf_err40.toString() + " .. " + operator40)
            }
            val query41 = Query(instance)
            val graph41 = instance.tripleStoreManager!!.getGraph(inputGraph[1])
            val operator41 = graph41.getIterator(query41, arrayOf(AOPVariable(query41, "s"), AOPVariable(query41, "p"), AOPVariable(query41, "o")), EIndexPatternExt.SPO)
            val actual41 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator41, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
            val expected41 = MemoryTable.parseFromAny(inputData[1], inputType[1], Query(instance))!!
            val buf_err41 = MyPrintWriter()
            if (!expected41.equalsVerbose(actual41, true, true, buf_err41)) {
                fail(expected41.toString() + " .. " + actual41.toString() + " .. " + buf_err41.toString() + " .. " + operator41)
            }
            val operator42 = LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, query)
            LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator42, buf, EQueryResultToStreamExt.EMPTY_STREAM)
            val query43 = Query(instance)
            val graph43 = instance.tripleStoreManager!!.getGraph(outputGraph[0])
            val operator43 = graph43.getIterator(query43, arrayOf(AOPVariable(query43, "s"), AOPVariable(query43, "p"), AOPVariable(query43, "o")), EIndexPatternExt.SPO)
            val actual43 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator43, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
            val expected43 = MemoryTable.parseFromAny(outputData[0], outputType[0], Query(instance))!!
            val buf_err43 = MyPrintWriter()
            if (!expected43.equalsVerbose(actual43, true, true, buf_err43)) {
                fail(expected43.toString() + " .. " + actual43.toString() + " .. " + buf_err43.toString() + " .. " + operator43)
            }
            val query44 = Query(instance)
            val graph44 = instance.tripleStoreManager!!.getGraph(outputGraph[1])
            val operator44 = graph44.getIterator(query44, arrayOf(AOPVariable(query44, "s"), AOPVariable(query44, "p"), AOPVariable(query44, "o")), EIndexPatternExt.SPO)
            val actual44 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator44, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
            val expected44 = MemoryTable.parseFromAny(outputData[1], outputType[1], Query(instance))!!
            val buf_err44 = MyPrintWriter()
            if (!expected44.equalsVerbose(actual44, true, true, buf_err44)) {
                fail(expected44.toString() + " .. " + actual44.toString() + " .. " + buf_err44.toString() + " .. " + operator44)
            }
        } finally {
            LuposdateEndpoint.close(instance)
        }
    }
    public fun `ADD 6 - Thread - PartitionByKeyAllCollations - false`() {
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
            val query45 = Query(instance)
            val graph45 = instance.tripleStoreManager!!.getGraph(inputGraph[0])
            val operator45 = graph45.getIterator(query45, arrayOf(AOPVariable(query45, "s"), AOPVariable(query45, "p"), AOPVariable(query45, "o")), EIndexPatternExt.SPO)
            val actual45 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator45, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
            val expected45 = MemoryTable.parseFromAny(inputData[0], inputType[0], Query(instance))!!
            val buf_err45 = MyPrintWriter()
            if (!expected45.equalsVerbose(actual45, true, true, buf_err45)) {
                fail(expected45.toString() + " .. " + actual45.toString() + " .. " + buf_err45.toString() + " .. " + operator45)
            }
            val query46 = Query(instance)
            val graph46 = instance.tripleStoreManager!!.getGraph(inputGraph[1])
            val operator46 = graph46.getIterator(query46, arrayOf(AOPVariable(query46, "s"), AOPVariable(query46, "p"), AOPVariable(query46, "o")), EIndexPatternExt.SPO)
            val actual46 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator46, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
            val expected46 = MemoryTable.parseFromAny(inputData[1], inputType[1], Query(instance))!!
            val buf_err46 = MyPrintWriter()
            if (!expected46.equalsVerbose(actual46, true, true, buf_err46)) {
                fail(expected46.toString() + " .. " + actual46.toString() + " .. " + buf_err46.toString() + " .. " + operator46)
            }
            val operator47 = LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, query)
            LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator47, buf, EQueryResultToStreamExt.EMPTY_STREAM)
            val query48 = Query(instance)
            val graph48 = instance.tripleStoreManager!!.getGraph(outputGraph[0])
            val operator48 = graph48.getIterator(query48, arrayOf(AOPVariable(query48, "s"), AOPVariable(query48, "p"), AOPVariable(query48, "o")), EIndexPatternExt.SPO)
            val actual48 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator48, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
            val expected48 = MemoryTable.parseFromAny(outputData[0], outputType[0], Query(instance))!!
            val buf_err48 = MyPrintWriter()
            if (!expected48.equalsVerbose(actual48, true, true, buf_err48)) {
                fail(expected48.toString() + " .. " + actual48.toString() + " .. " + buf_err48.toString() + " .. " + operator48)
            }
            val query49 = Query(instance)
            val graph49 = instance.tripleStoreManager!!.getGraph(outputGraph[1])
            val operator49 = graph49.getIterator(query49, arrayOf(AOPVariable(query49, "s"), AOPVariable(query49, "p"), AOPVariable(query49, "o")), EIndexPatternExt.SPO)
            val actual49 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator49, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
            val expected49 = MemoryTable.parseFromAny(outputData[1], outputType[1], Query(instance))!!
            val buf_err49 = MyPrintWriter()
            if (!expected49.equalsVerbose(actual49, true, true, buf_err49)) {
                fail(expected49.toString() + " .. " + actual49.toString() + " .. " + buf_err49.toString() + " .. " + operator49)
            }
        } finally {
            LuposdateEndpoint.close(instance)
        }
    }
    public fun `ADD 6 - Thread - Simple - true`() {
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
            val query50 = Query(instance)
            val graph50 = instance.tripleStoreManager!!.getGraph(inputGraph[0])
            val operator50 = graph50.getIterator(query50, arrayOf(AOPVariable(query50, "s"), AOPVariable(query50, "p"), AOPVariable(query50, "o")), EIndexPatternExt.SPO)
            val actual50 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator50, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
            val expected50 = MemoryTable.parseFromAny(inputData[0], inputType[0], Query(instance))!!
            val buf_err50 = MyPrintWriter()
            if (!expected50.equalsVerbose(actual50, true, true, buf_err50)) {
                fail(expected50.toString() + " .. " + actual50.toString() + " .. " + buf_err50.toString() + " .. " + operator50)
            }
            val query51 = Query(instance)
            val graph51 = instance.tripleStoreManager!!.getGraph(inputGraph[1])
            val operator51 = graph51.getIterator(query51, arrayOf(AOPVariable(query51, "s"), AOPVariable(query51, "p"), AOPVariable(query51, "o")), EIndexPatternExt.SPO)
            val actual51 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator51, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
            val expected51 = MemoryTable.parseFromAny(inputData[1], inputType[1], Query(instance))!!
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
        } finally {
            LuposdateEndpoint.close(instance)
        }
    }
    public fun `ADD 6 - Thread - Simple - false`() {
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
            val query55 = Query(instance)
            val graph55 = instance.tripleStoreManager!!.getGraph(inputGraph[0])
            val operator55 = graph55.getIterator(query55, arrayOf(AOPVariable(query55, "s"), AOPVariable(query55, "p"), AOPVariable(query55, "o")), EIndexPatternExt.SPO)
            val actual55 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator55, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
            val expected55 = MemoryTable.parseFromAny(inputData[0], inputType[0], Query(instance))!!
            val buf_err55 = MyPrintWriter()
            if (!expected55.equalsVerbose(actual55, true, true, buf_err55)) {
                fail(expected55.toString() + " .. " + actual55.toString() + " .. " + buf_err55.toString() + " .. " + operator55)
            }
            val query56 = Query(instance)
            val graph56 = instance.tripleStoreManager!!.getGraph(inputGraph[1])
            val operator56 = graph56.getIterator(query56, arrayOf(AOPVariable(query56, "s"), AOPVariable(query56, "p"), AOPVariable(query56, "o")), EIndexPatternExt.SPO)
            val actual56 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator56, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
            val expected56 = MemoryTable.parseFromAny(inputData[1], inputType[1], Query(instance))!!
            val buf_err56 = MyPrintWriter()
            if (!expected56.equalsVerbose(actual56, true, true, buf_err56)) {
                fail(expected56.toString() + " .. " + actual56.toString() + " .. " + buf_err56.toString() + " .. " + operator56)
            }
            val operator57 = LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, query)
            LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator57, buf, EQueryResultToStreamExt.EMPTY_STREAM)
            val query58 = Query(instance)
            val graph58 = instance.tripleStoreManager!!.getGraph(outputGraph[0])
            val operator58 = graph58.getIterator(query58, arrayOf(AOPVariable(query58, "s"), AOPVariable(query58, "p"), AOPVariable(query58, "o")), EIndexPatternExt.SPO)
            val actual58 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator58, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
            val expected58 = MemoryTable.parseFromAny(outputData[0], outputType[0], Query(instance))!!
            val buf_err58 = MyPrintWriter()
            if (!expected58.equalsVerbose(actual58, true, true, buf_err58)) {
                fail(expected58.toString() + " .. " + actual58.toString() + " .. " + buf_err58.toString() + " .. " + operator58)
            }
            val query59 = Query(instance)
            val graph59 = instance.tripleStoreManager!!.getGraph(outputGraph[1])
            val operator59 = graph59.getIterator(query59, arrayOf(AOPVariable(query59, "s"), AOPVariable(query59, "p"), AOPVariable(query59, "o")), EIndexPatternExt.SPO)
            val actual59 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator59, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
            val expected59 = MemoryTable.parseFromAny(outputData[1], outputType[1], Query(instance))!!
            val buf_err59 = MyPrintWriter()
            if (!expected59.equalsVerbose(actual59, true, true, buf_err59)) {
                fail(expected59.toString() + " .. " + actual59.toString() + " .. " + buf_err59.toString() + " .. " + operator59)
            }
        } finally {
            LuposdateEndpoint.close(instance)
        }
    }

    @Ignore // Reason: >query not distributed<
    @Test
    public fun `ADD 6 - in simulator - PartitionByIDTwiceAllCollations - false - Centralized - true - None`() {
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

    @Ignore // Reason: >query not distributed<
    @Test
    public fun `ADD 6 - in simulator - PartitionByIDTwiceAllCollations - false - Centralized - false - None`() {
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

    @Ignore // Reason: >query not distributed<
    @Test
    public fun `ADD 6 - in simulator - PartitionByKeyAllCollations - false - Centralized - true - None`() {
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

    @Ignore // Reason: >query not distributed<
    @Test
    public fun `ADD 6 - in simulator - PartitionByKeyAllCollations - false - Centralized - false - None`() {
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

    @Ignore // Reason: >query not distributed<
    @Test
    public fun `ADD 6 - in simulator - Simple - false - Centralized - true - None`() {
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

    @Ignore // Reason: >query not distributed<
    @Test
    public fun `ADD 6 - in simulator - Simple - false - Centralized - false - None`() {
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

    @Ignore // Reason: >query not distributed<
    @Test
    public fun `ADD 6 - in simulator - PartitionByIDTwiceAllCollations - true - Centralized - true - Process`() {
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

    @Ignore // Reason: >query not distributed<
    @Test
    public fun `ADD 6 - in simulator - PartitionByIDTwiceAllCollations - true - Centralized - false - Process`() {
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

    @Ignore // Reason: >query not distributed<
    @Test
    public fun `ADD 6 - in simulator - PartitionByIDTwiceAllCollations - true - Routing - true - Process`() {
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

    @Ignore // Reason: >query not distributed<
    @Test
    public fun `ADD 6 - in simulator - PartitionByIDTwiceAllCollations - true - Routing - false - Process`() {
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

    @Ignore // Reason: >query not distributed<
    @Test
    public fun `ADD 6 - in simulator - PartitionByIDTwiceAllCollations - false - Centralized - true - Process`() {
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

    @Ignore // Reason: >query not distributed<
    @Test
    public fun `ADD 6 - in simulator - PartitionByIDTwiceAllCollations - false - Centralized - false - Process`() {
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

    @Ignore // Reason: >query not distributed<
    @Test
    public fun `ADD 6 - in simulator - PartitionByIDTwiceAllCollations - false - Routing - true - Process`() {
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

    @Ignore // Reason: >query not distributed<
    @Test
    public fun `ADD 6 - in simulator - PartitionByIDTwiceAllCollations - false - Routing - false - Process`() {
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

    @Ignore // Reason: >query not distributed<
    @Test
    public fun `ADD 6 - in simulator - PartitionByKeyAllCollations - true - Centralized - true - Process`() {
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

    @Ignore // Reason: >query not distributed<
    @Test
    public fun `ADD 6 - in simulator - PartitionByKeyAllCollations - true - Centralized - false - Process`() {
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

    @Ignore // Reason: >query not distributed<
    @Test
    public fun `ADD 6 - in simulator - PartitionByKeyAllCollations - true - Routing - true - Process`() {
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

    @Ignore // Reason: >query not distributed<
    @Test
    public fun `ADD 6 - in simulator - PartitionByKeyAllCollations - true - Routing - false - Process`() {
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

    @Ignore // Reason: >query not distributed<
    @Test
    public fun `ADD 6 - in simulator - PartitionByKeyAllCollations - false - Centralized - true - Process`() {
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

    @Ignore // Reason: >query not distributed<
    @Test
    public fun `ADD 6 - in simulator - PartitionByKeyAllCollations - false - Centralized - false - Process`() {
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

    @Ignore // Reason: >query not distributed<
    @Test
    public fun `ADD 6 - in simulator - PartitionByKeyAllCollations - false - Routing - true - Process`() {
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

    @Ignore // Reason: >query not distributed<
    @Test
    public fun `ADD 6 - in simulator - PartitionByKeyAllCollations - false - Routing - false - Process`() {
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

    @Ignore // Reason: >query not distributed<
    @Test
    public fun `ADD 6 - in simulator - PartitionByIDTwiceAllCollations - false - Centralized - true - Thread`() {
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

    @Ignore // Reason: >query not distributed<
    @Test
    public fun `ADD 6 - in simulator - PartitionByIDTwiceAllCollations - false - Centralized - false - Thread`() {
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

    @Ignore // Reason: >query not distributed<
    @Test
    public fun `ADD 6 - in simulator - PartitionByKeyAllCollations - false - Centralized - true - Thread`() {
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

    @Ignore // Reason: >query not distributed<
    @Test
    public fun `ADD 6 - in simulator - PartitionByKeyAllCollations - false - Centralized - false - Thread`() {
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

    @Ignore // Reason: >query not distributed<
    @Test
    public fun `ADD 6 - in simulator - Simple - false - Centralized - true - Thread`() {
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

    @Ignore // Reason: >query not distributed<
    @Test
    public fun `ADD 6 - in simulator - Simple - false - Centralized - false - Thread`() {
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
