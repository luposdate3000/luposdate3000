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
package lupos.code_gen_test_04
import lupos.endpoint.LuposdateEndpoint
import lupos.operator.arithmetik.noinput.AOPVariable
import lupos.operator.base.Query
import lupos.parser.JsonParser
import lupos.parser.JsonParserObject
import lupos.result_format.EQueryResultToStreamExt
import lupos.shared.EIndexPatternExt
import lupos.shared.EQueryDistributionModeExt
import lupos.shared.Luposdate3000Config
import lupos.shared.Luposdate3000Instance
import lupos.shared.EPartitionModeExt
import lupos.shared.MemoryTable
import lupos.shared.EPredefinedPartitionSchemesExt
import lupos.shared.inline.File
import lupos.shared.inline.MyPrintWriter
import lupos.simulator_core.Simulation
import lupos.simulator_db.luposdate3000.MySimulatorTestingCompareGraphPackage
import lupos.simulator_db.luposdate3000.MySimulatorTestingImportPackage
import lupos.simulator_db.luposdate3000.MySimulatorTestingExecute
import lupos.simulator_db.luposdate3000.DatabaseHandle
import lupos.simulator_iot.log.Logger
import lupos.simulator_iot.SimulationRun

import kotlin.test.Ignore
import kotlin.test.Test
import kotlin.test.fail

public class MOVE1 {
    internal val inputData = arrayOf(
        File("src/jvmTest/resources/MOVE1.input").readAsString(),
        File("src/jvmTest/resources/MOVE1.input1").readAsString(),
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
        File("src/jvmTest/resources/MOVE1.output0").readAsString(),
    )
    internal val outputGraph = arrayOf(
        "http://example.org/g1",
    )
    internal val outputType = arrayOf(
        ".ttl",
    )
    internal val query = "PREFIX : <http://example.org/> \n" +
        "MOVE DEFAULT TO :g1"

    @Test(timeout = 2000)
    public fun `MOVE 1 - None - PartitionByIDTwiceAllCollations - false`() {
      var instance = Luposdate3000Instance()
      try{
        instance.LUPOS_BUFFER_SIZE = 128
        instance.LUPOS_PARTITION_MODE=EPartitionModeExt.None
        instance.predefinedPartitionScheme=EPredefinedPartitionSchemesExt.PartitionByIDTwiceAllCollations
        instance.useDictionaryInlineEncoding=false
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
      }finally{
        LuposdateEndpoint.close(instance)
      }
    }
    @Test(timeout = 2000)
    public fun `MOVE 1 - None - PartitionByID_1_AllCollations - true`() {
      var instance = Luposdate3000Instance()
      try{
        instance.LUPOS_BUFFER_SIZE = 128
        instance.LUPOS_PARTITION_MODE=EPartitionModeExt.None
        instance.predefinedPartitionScheme=EPredefinedPartitionSchemesExt.PartitionByID_1_AllCollations
        instance.useDictionaryInlineEncoding=true
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
        val query4 = Query(instance)
        val graph4 = instance.tripleStoreManager!!.getGraph(inputGraph[0])
        val operator4 = graph4.getIterator(query4, arrayOf(AOPVariable(query4, "s"), AOPVariable(query4, "p"), AOPVariable(query4, "o")), EIndexPatternExt.SPO)
        val actual4 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator4, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected4 = MemoryTable.parseFromAny(inputData[0], inputType[0], Query(instance))!!
        val buf_err4 = MyPrintWriter()
        if (!expected4.equalsVerbose(actual4, true, true, buf_err4)) {
            fail(expected4.toString() + " .. " + actual4.toString() + " .. " + buf_err4.toString() + " .. " + operator4)
        }
        val query5 = Query(instance)
        val graph5 = instance.tripleStoreManager!!.getGraph(inputGraph[1])
        val operator5 = graph5.getIterator(query5, arrayOf(AOPVariable(query5, "s"), AOPVariable(query5, "p"), AOPVariable(query5, "o")), EIndexPatternExt.SPO)
        val actual5 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator5, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected5 = MemoryTable.parseFromAny(inputData[1], inputType[1], Query(instance))!!
        val buf_err5 = MyPrintWriter()
        if (!expected5.equalsVerbose(actual5, true, true, buf_err5)) {
            fail(expected5.toString() + " .. " + actual5.toString() + " .. " + buf_err5.toString() + " .. " + operator5)
        }
        val operator6 = LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, query)
        LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator6, buf, EQueryResultToStreamExt.EMPTY_STREAM)
        val query7 = Query(instance)
        val graph7 = instance.tripleStoreManager!!.getGraph(outputGraph[0])
        val operator7 = graph7.getIterator(query7, arrayOf(AOPVariable(query7, "s"), AOPVariable(query7, "p"), AOPVariable(query7, "o")), EIndexPatternExt.SPO)
        val actual7 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator7, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected7 = MemoryTable.parseFromAny(outputData[0], outputType[0], Query(instance))!!
        val buf_err7 = MyPrintWriter()
        if (!expected7.equalsVerbose(actual7, true, true, buf_err7)) {
            fail(expected7.toString() + " .. " + actual7.toString() + " .. " + buf_err7.toString() + " .. " + operator7)
        }
      }finally{
        LuposdateEndpoint.close(instance)
      }
    }
    @Test(timeout = 2000)
    public fun `MOVE 1 - None - PartitionByID_1_AllCollations - false`() {
      var instance = Luposdate3000Instance()
      try{
        instance.LUPOS_BUFFER_SIZE = 128
        instance.LUPOS_PARTITION_MODE=EPartitionModeExt.None
        instance.predefinedPartitionScheme=EPredefinedPartitionSchemesExt.PartitionByID_1_AllCollations
        instance.useDictionaryInlineEncoding=false
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
        val query8 = Query(instance)
        val graph8 = instance.tripleStoreManager!!.getGraph(inputGraph[0])
        val operator8 = graph8.getIterator(query8, arrayOf(AOPVariable(query8, "s"), AOPVariable(query8, "p"), AOPVariable(query8, "o")), EIndexPatternExt.SPO)
        val actual8 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator8, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected8 = MemoryTable.parseFromAny(inputData[0], inputType[0], Query(instance))!!
        val buf_err8 = MyPrintWriter()
        if (!expected8.equalsVerbose(actual8, true, true, buf_err8)) {
            fail(expected8.toString() + " .. " + actual8.toString() + " .. " + buf_err8.toString() + " .. " + operator8)
        }
        val query9 = Query(instance)
        val graph9 = instance.tripleStoreManager!!.getGraph(inputGraph[1])
        val operator9 = graph9.getIterator(query9, arrayOf(AOPVariable(query9, "s"), AOPVariable(query9, "p"), AOPVariable(query9, "o")), EIndexPatternExt.SPO)
        val actual9 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator9, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected9 = MemoryTable.parseFromAny(inputData[1], inputType[1], Query(instance))!!
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
      }finally{
        LuposdateEndpoint.close(instance)
      }
    }
    @Test(timeout = 2000)
    public fun `MOVE 1 - None - PartitionByID_2_AllCollations - true`() {
      var instance = Luposdate3000Instance()
      try{
        instance.LUPOS_BUFFER_SIZE = 128
        instance.LUPOS_PARTITION_MODE=EPartitionModeExt.None
        instance.predefinedPartitionScheme=EPredefinedPartitionSchemesExt.PartitionByID_2_AllCollations
        instance.useDictionaryInlineEncoding=true
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
        val query12 = Query(instance)
        val graph12 = instance.tripleStoreManager!!.getGraph(inputGraph[0])
        val operator12 = graph12.getIterator(query12, arrayOf(AOPVariable(query12, "s"), AOPVariable(query12, "p"), AOPVariable(query12, "o")), EIndexPatternExt.SPO)
        val actual12 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator12, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected12 = MemoryTable.parseFromAny(inputData[0], inputType[0], Query(instance))!!
        val buf_err12 = MyPrintWriter()
        if (!expected12.equalsVerbose(actual12, true, true, buf_err12)) {
            fail(expected12.toString() + " .. " + actual12.toString() + " .. " + buf_err12.toString() + " .. " + operator12)
        }
        val query13 = Query(instance)
        val graph13 = instance.tripleStoreManager!!.getGraph(inputGraph[1])
        val operator13 = graph13.getIterator(query13, arrayOf(AOPVariable(query13, "s"), AOPVariable(query13, "p"), AOPVariable(query13, "o")), EIndexPatternExt.SPO)
        val actual13 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator13, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected13 = MemoryTable.parseFromAny(inputData[1], inputType[1], Query(instance))!!
        val buf_err13 = MyPrintWriter()
        if (!expected13.equalsVerbose(actual13, true, true, buf_err13)) {
            fail(expected13.toString() + " .. " + actual13.toString() + " .. " + buf_err13.toString() + " .. " + operator13)
        }
        val operator14 = LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, query)
        LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator14, buf, EQueryResultToStreamExt.EMPTY_STREAM)
        val query15 = Query(instance)
        val graph15 = instance.tripleStoreManager!!.getGraph(outputGraph[0])
        val operator15 = graph15.getIterator(query15, arrayOf(AOPVariable(query15, "s"), AOPVariable(query15, "p"), AOPVariable(query15, "o")), EIndexPatternExt.SPO)
        val actual15 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator15, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected15 = MemoryTable.parseFromAny(outputData[0], outputType[0], Query(instance))!!
        val buf_err15 = MyPrintWriter()
        if (!expected15.equalsVerbose(actual15, true, true, buf_err15)) {
            fail(expected15.toString() + " .. " + actual15.toString() + " .. " + buf_err15.toString() + " .. " + operator15)
        }
      }finally{
        LuposdateEndpoint.close(instance)
      }
    }
    @Test(timeout = 2000)
    public fun `MOVE 1 - None - PartitionByID_2_AllCollations - false`() {
      var instance = Luposdate3000Instance()
      try{
        instance.LUPOS_BUFFER_SIZE = 128
        instance.LUPOS_PARTITION_MODE=EPartitionModeExt.None
        instance.predefinedPartitionScheme=EPredefinedPartitionSchemesExt.PartitionByID_2_AllCollations
        instance.useDictionaryInlineEncoding=false
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
        val query16 = Query(instance)
        val graph16 = instance.tripleStoreManager!!.getGraph(inputGraph[0])
        val operator16 = graph16.getIterator(query16, arrayOf(AOPVariable(query16, "s"), AOPVariable(query16, "p"), AOPVariable(query16, "o")), EIndexPatternExt.SPO)
        val actual16 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator16, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected16 = MemoryTable.parseFromAny(inputData[0], inputType[0], Query(instance))!!
        val buf_err16 = MyPrintWriter()
        if (!expected16.equalsVerbose(actual16, true, true, buf_err16)) {
            fail(expected16.toString() + " .. " + actual16.toString() + " .. " + buf_err16.toString() + " .. " + operator16)
        }
        val query17 = Query(instance)
        val graph17 = instance.tripleStoreManager!!.getGraph(inputGraph[1])
        val operator17 = graph17.getIterator(query17, arrayOf(AOPVariable(query17, "s"), AOPVariable(query17, "p"), AOPVariable(query17, "o")), EIndexPatternExt.SPO)
        val actual17 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator17, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected17 = MemoryTable.parseFromAny(inputData[1], inputType[1], Query(instance))!!
        val buf_err17 = MyPrintWriter()
        if (!expected17.equalsVerbose(actual17, true, true, buf_err17)) {
            fail(expected17.toString() + " .. " + actual17.toString() + " .. " + buf_err17.toString() + " .. " + operator17)
        }
        val operator18 = LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, query)
        LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator18, buf, EQueryResultToStreamExt.EMPTY_STREAM)
        val query19 = Query(instance)
        val graph19 = instance.tripleStoreManager!!.getGraph(outputGraph[0])
        val operator19 = graph19.getIterator(query19, arrayOf(AOPVariable(query19, "s"), AOPVariable(query19, "p"), AOPVariable(query19, "o")), EIndexPatternExt.SPO)
        val actual19 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator19, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected19 = MemoryTable.parseFromAny(outputData[0], outputType[0], Query(instance))!!
        val buf_err19 = MyPrintWriter()
        if (!expected19.equalsVerbose(actual19, true, true, buf_err19)) {
            fail(expected19.toString() + " .. " + actual19.toString() + " .. " + buf_err19.toString() + " .. " + operator19)
        }
      }finally{
        LuposdateEndpoint.close(instance)
      }
    }
    @Test(timeout = 2000)
    public fun `MOVE 1 - None - PartitionByID_O_AllCollations - true`() {
      var instance = Luposdate3000Instance()
      try{
        instance.LUPOS_BUFFER_SIZE = 128
        instance.LUPOS_PARTITION_MODE=EPartitionModeExt.None
        instance.predefinedPartitionScheme=EPredefinedPartitionSchemesExt.PartitionByID_O_AllCollations
        instance.useDictionaryInlineEncoding=true
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
      }finally{
        LuposdateEndpoint.close(instance)
      }
    }
    @Test(timeout = 2000)
    public fun `MOVE 1 - None - PartitionByID_O_AllCollations - false`() {
      var instance = Luposdate3000Instance()
      try{
        instance.LUPOS_BUFFER_SIZE = 128
        instance.LUPOS_PARTITION_MODE=EPartitionModeExt.None
        instance.predefinedPartitionScheme=EPredefinedPartitionSchemesExt.PartitionByID_O_AllCollations
        instance.useDictionaryInlineEncoding=false
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
        val query24 = Query(instance)
        val graph24 = instance.tripleStoreManager!!.getGraph(inputGraph[0])
        val operator24 = graph24.getIterator(query24, arrayOf(AOPVariable(query24, "s"), AOPVariable(query24, "p"), AOPVariable(query24, "o")), EIndexPatternExt.SPO)
        val actual24 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator24, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected24 = MemoryTable.parseFromAny(inputData[0], inputType[0], Query(instance))!!
        val buf_err24 = MyPrintWriter()
        if (!expected24.equalsVerbose(actual24, true, true, buf_err24)) {
            fail(expected24.toString() + " .. " + actual24.toString() + " .. " + buf_err24.toString() + " .. " + operator24)
        }
        val query25 = Query(instance)
        val graph25 = instance.tripleStoreManager!!.getGraph(inputGraph[1])
        val operator25 = graph25.getIterator(query25, arrayOf(AOPVariable(query25, "s"), AOPVariable(query25, "p"), AOPVariable(query25, "o")), EIndexPatternExt.SPO)
        val actual25 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator25, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected25 = MemoryTable.parseFromAny(inputData[1], inputType[1], Query(instance))!!
        val buf_err25 = MyPrintWriter()
        if (!expected25.equalsVerbose(actual25, true, true, buf_err25)) {
            fail(expected25.toString() + " .. " + actual25.toString() + " .. " + buf_err25.toString() + " .. " + operator25)
        }
        val operator26 = LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, query)
        LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator26, buf, EQueryResultToStreamExt.EMPTY_STREAM)
        val query27 = Query(instance)
        val graph27 = instance.tripleStoreManager!!.getGraph(outputGraph[0])
        val operator27 = graph27.getIterator(query27, arrayOf(AOPVariable(query27, "s"), AOPVariable(query27, "p"), AOPVariable(query27, "o")), EIndexPatternExt.SPO)
        val actual27 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator27, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected27 = MemoryTable.parseFromAny(outputData[0], outputType[0], Query(instance))!!
        val buf_err27 = MyPrintWriter()
        if (!expected27.equalsVerbose(actual27, true, true, buf_err27)) {
            fail(expected27.toString() + " .. " + actual27.toString() + " .. " + buf_err27.toString() + " .. " + operator27)
        }
      }finally{
        LuposdateEndpoint.close(instance)
      }
    }
    @Test(timeout = 2000)
    public fun `MOVE 1 - None - PartitionByID_S_AllCollations - true`() {
      var instance = Luposdate3000Instance()
      try{
        instance.LUPOS_BUFFER_SIZE = 128
        instance.LUPOS_PARTITION_MODE=EPartitionModeExt.None
        instance.predefinedPartitionScheme=EPredefinedPartitionSchemesExt.PartitionByID_S_AllCollations
        instance.useDictionaryInlineEncoding=true
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
        val operator30 = LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, query)
        LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator30, buf, EQueryResultToStreamExt.EMPTY_STREAM)
        val query31 = Query(instance)
        val graph31 = instance.tripleStoreManager!!.getGraph(outputGraph[0])
        val operator31 = graph31.getIterator(query31, arrayOf(AOPVariable(query31, "s"), AOPVariable(query31, "p"), AOPVariable(query31, "o")), EIndexPatternExt.SPO)
        val actual31 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator31, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected31 = MemoryTable.parseFromAny(outputData[0], outputType[0], Query(instance))!!
        val buf_err31 = MyPrintWriter()
        if (!expected31.equalsVerbose(actual31, true, true, buf_err31)) {
            fail(expected31.toString() + " .. " + actual31.toString() + " .. " + buf_err31.toString() + " .. " + operator31)
        }
      }finally{
        LuposdateEndpoint.close(instance)
      }
    }
    @Test(timeout = 2000)
    public fun `MOVE 1 - None - PartitionByID_S_AllCollations - false`() {
      var instance = Luposdate3000Instance()
      try{
        instance.LUPOS_BUFFER_SIZE = 128
        instance.LUPOS_PARTITION_MODE=EPartitionModeExt.None
        instance.predefinedPartitionScheme=EPredefinedPartitionSchemesExt.PartitionByID_S_AllCollations
        instance.useDictionaryInlineEncoding=false
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
        val query32 = Query(instance)
        val graph32 = instance.tripleStoreManager!!.getGraph(inputGraph[0])
        val operator32 = graph32.getIterator(query32, arrayOf(AOPVariable(query32, "s"), AOPVariable(query32, "p"), AOPVariable(query32, "o")), EIndexPatternExt.SPO)
        val actual32 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator32, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected32 = MemoryTable.parseFromAny(inputData[0], inputType[0], Query(instance))!!
        val buf_err32 = MyPrintWriter()
        if (!expected32.equalsVerbose(actual32, true, true, buf_err32)) {
            fail(expected32.toString() + " .. " + actual32.toString() + " .. " + buf_err32.toString() + " .. " + operator32)
        }
        val query33 = Query(instance)
        val graph33 = instance.tripleStoreManager!!.getGraph(inputGraph[1])
        val operator33 = graph33.getIterator(query33, arrayOf(AOPVariable(query33, "s"), AOPVariable(query33, "p"), AOPVariable(query33, "o")), EIndexPatternExt.SPO)
        val actual33 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator33, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected33 = MemoryTable.parseFromAny(inputData[1], inputType[1], Query(instance))!!
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
      }finally{
        LuposdateEndpoint.close(instance)
      }
    }
    @Test(timeout = 2000)
    public fun `MOVE 1 - None - PartitionByKeyAllCollations - true`() {
      var instance = Luposdate3000Instance()
      try{
        instance.LUPOS_BUFFER_SIZE = 128
        instance.LUPOS_PARTITION_MODE=EPartitionModeExt.None
        instance.predefinedPartitionScheme=EPredefinedPartitionSchemesExt.PartitionByKeyAllCollations
        instance.useDictionaryInlineEncoding=true
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
        val query36 = Query(instance)
        val graph36 = instance.tripleStoreManager!!.getGraph(inputGraph[0])
        val operator36 = graph36.getIterator(query36, arrayOf(AOPVariable(query36, "s"), AOPVariable(query36, "p"), AOPVariable(query36, "o")), EIndexPatternExt.SPO)
        val actual36 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator36, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected36 = MemoryTable.parseFromAny(inputData[0], inputType[0], Query(instance))!!
        val buf_err36 = MyPrintWriter()
        if (!expected36.equalsVerbose(actual36, true, true, buf_err36)) {
            fail(expected36.toString() + " .. " + actual36.toString() + " .. " + buf_err36.toString() + " .. " + operator36)
        }
        val query37 = Query(instance)
        val graph37 = instance.tripleStoreManager!!.getGraph(inputGraph[1])
        val operator37 = graph37.getIterator(query37, arrayOf(AOPVariable(query37, "s"), AOPVariable(query37, "p"), AOPVariable(query37, "o")), EIndexPatternExt.SPO)
        val actual37 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator37, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected37 = MemoryTable.parseFromAny(inputData[1], inputType[1], Query(instance))!!
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
      }finally{
        LuposdateEndpoint.close(instance)
      }
    }
    @Test(timeout = 2000)
    public fun `MOVE 1 - None - PartitionByKeyAllCollations - false`() {
      var instance = Luposdate3000Instance()
      try{
        instance.LUPOS_BUFFER_SIZE = 128
        instance.LUPOS_PARTITION_MODE=EPartitionModeExt.None
        instance.predefinedPartitionScheme=EPredefinedPartitionSchemesExt.PartitionByKeyAllCollations
        instance.useDictionaryInlineEncoding=false
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
      }finally{
        LuposdateEndpoint.close(instance)
      }
    }
    @Test(timeout = 2000)
    public fun `MOVE 1 - None - Simple - true`() {
      var instance = Luposdate3000Instance()
      try{
        instance.LUPOS_BUFFER_SIZE = 128
        instance.LUPOS_PARTITION_MODE=EPartitionModeExt.None
        instance.predefinedPartitionScheme=EPredefinedPartitionSchemesExt.Simple
        instance.useDictionaryInlineEncoding=true
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
        val query44 = Query(instance)
        val graph44 = instance.tripleStoreManager!!.getGraph(inputGraph[0])
        val operator44 = graph44.getIterator(query44, arrayOf(AOPVariable(query44, "s"), AOPVariable(query44, "p"), AOPVariable(query44, "o")), EIndexPatternExt.SPO)
        val actual44 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator44, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected44 = MemoryTable.parseFromAny(inputData[0], inputType[0], Query(instance))!!
        val buf_err44 = MyPrintWriter()
        if (!expected44.equalsVerbose(actual44, true, true, buf_err44)) {
            fail(expected44.toString() + " .. " + actual44.toString() + " .. " + buf_err44.toString() + " .. " + operator44)
        }
        val query45 = Query(instance)
        val graph45 = instance.tripleStoreManager!!.getGraph(inputGraph[1])
        val operator45 = graph45.getIterator(query45, arrayOf(AOPVariable(query45, "s"), AOPVariable(query45, "p"), AOPVariable(query45, "o")), EIndexPatternExt.SPO)
        val actual45 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator45, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected45 = MemoryTable.parseFromAny(inputData[1], inputType[1], Query(instance))!!
        val buf_err45 = MyPrintWriter()
        if (!expected45.equalsVerbose(actual45, true, true, buf_err45)) {
            fail(expected45.toString() + " .. " + actual45.toString() + " .. " + buf_err45.toString() + " .. " + operator45)
        }
        val operator46 = LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, query)
        LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator46, buf, EQueryResultToStreamExt.EMPTY_STREAM)
        val query47 = Query(instance)
        val graph47 = instance.tripleStoreManager!!.getGraph(outputGraph[0])
        val operator47 = graph47.getIterator(query47, arrayOf(AOPVariable(query47, "s"), AOPVariable(query47, "p"), AOPVariable(query47, "o")), EIndexPatternExt.SPO)
        val actual47 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator47, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected47 = MemoryTable.parseFromAny(outputData[0], outputType[0], Query(instance))!!
        val buf_err47 = MyPrintWriter()
        if (!expected47.equalsVerbose(actual47, true, true, buf_err47)) {
            fail(expected47.toString() + " .. " + actual47.toString() + " .. " + buf_err47.toString() + " .. " + operator47)
        }
      }finally{
        LuposdateEndpoint.close(instance)
      }
    }
    @Test(timeout = 2000)
    public fun `MOVE 1 - None - Simple - false`() {
      var instance = Luposdate3000Instance()
      try{
        instance.LUPOS_BUFFER_SIZE = 128
        instance.LUPOS_PARTITION_MODE=EPartitionModeExt.None
        instance.predefinedPartitionScheme=EPredefinedPartitionSchemesExt.Simple
        instance.useDictionaryInlineEncoding=false
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
        val query48 = Query(instance)
        val graph48 = instance.tripleStoreManager!!.getGraph(inputGraph[0])
        val operator48 = graph48.getIterator(query48, arrayOf(AOPVariable(query48, "s"), AOPVariable(query48, "p"), AOPVariable(query48, "o")), EIndexPatternExt.SPO)
        val actual48 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator48, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected48 = MemoryTable.parseFromAny(inputData[0], inputType[0], Query(instance))!!
        val buf_err48 = MyPrintWriter()
        if (!expected48.equalsVerbose(actual48, true, true, buf_err48)) {
            fail(expected48.toString() + " .. " + actual48.toString() + " .. " + buf_err48.toString() + " .. " + operator48)
        }
        val query49 = Query(instance)
        val graph49 = instance.tripleStoreManager!!.getGraph(inputGraph[1])
        val operator49 = graph49.getIterator(query49, arrayOf(AOPVariable(query49, "s"), AOPVariable(query49, "p"), AOPVariable(query49, "o")), EIndexPatternExt.SPO)
        val actual49 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator49, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected49 = MemoryTable.parseFromAny(inputData[1], inputType[1], Query(instance))!!
        val buf_err49 = MyPrintWriter()
        if (!expected49.equalsVerbose(actual49, true, true, buf_err49)) {
            fail(expected49.toString() + " .. " + actual49.toString() + " .. " + buf_err49.toString() + " .. " + operator49)
        }
        val operator50 = LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, query)
        LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator50, buf, EQueryResultToStreamExt.EMPTY_STREAM)
        val query51 = Query(instance)
        val graph51 = instance.tripleStoreManager!!.getGraph(outputGraph[0])
        val operator51 = graph51.getIterator(query51, arrayOf(AOPVariable(query51, "s"), AOPVariable(query51, "p"), AOPVariable(query51, "o")), EIndexPatternExt.SPO)
        val actual51 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator51, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected51 = MemoryTable.parseFromAny(outputData[0], outputType[0], Query(instance))!!
        val buf_err51 = MyPrintWriter()
        if (!expected51.equalsVerbose(actual51, true, true, buf_err51)) {
            fail(expected51.toString() + " .. " + actual51.toString() + " .. " + buf_err51.toString() + " .. " + operator51)
        }
      }finally{
        LuposdateEndpoint.close(instance)
      }
    }
    @Test(timeout = 2000)
    public fun `MOVE 1 - Thread - PartitionByIDTwiceAllCollations - true`() {
      var instance = Luposdate3000Instance()
      try{
        instance.LUPOS_BUFFER_SIZE = 128
        instance.LUPOS_PARTITION_MODE=EPartitionModeExt.Thread
        instance.predefinedPartitionScheme=EPredefinedPartitionSchemesExt.PartitionByIDTwiceAllCollations
        instance.useDictionaryInlineEncoding=true
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
        val query52 = Query(instance)
        val graph52 = instance.tripleStoreManager!!.getGraph(inputGraph[0])
        val operator52 = graph52.getIterator(query52, arrayOf(AOPVariable(query52, "s"), AOPVariable(query52, "p"), AOPVariable(query52, "o")), EIndexPatternExt.SPO)
        val actual52 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator52, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected52 = MemoryTable.parseFromAny(inputData[0], inputType[0], Query(instance))!!
        val buf_err52 = MyPrintWriter()
        if (!expected52.equalsVerbose(actual52, true, true, buf_err52)) {
            fail(expected52.toString() + " .. " + actual52.toString() + " .. " + buf_err52.toString() + " .. " + operator52)
        }
        val query53 = Query(instance)
        val graph53 = instance.tripleStoreManager!!.getGraph(inputGraph[1])
        val operator53 = graph53.getIterator(query53, arrayOf(AOPVariable(query53, "s"), AOPVariable(query53, "p"), AOPVariable(query53, "o")), EIndexPatternExt.SPO)
        val actual53 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator53, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected53 = MemoryTable.parseFromAny(inputData[1], inputType[1], Query(instance))!!
        val buf_err53 = MyPrintWriter()
        if (!expected53.equalsVerbose(actual53, true, true, buf_err53)) {
            fail(expected53.toString() + " .. " + actual53.toString() + " .. " + buf_err53.toString() + " .. " + operator53)
        }
        val operator54 = LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, query)
        LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator54, buf, EQueryResultToStreamExt.EMPTY_STREAM)
        val query55 = Query(instance)
        val graph55 = instance.tripleStoreManager!!.getGraph(outputGraph[0])
        val operator55 = graph55.getIterator(query55, arrayOf(AOPVariable(query55, "s"), AOPVariable(query55, "p"), AOPVariable(query55, "o")), EIndexPatternExt.SPO)
        val actual55 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator55, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected55 = MemoryTable.parseFromAny(outputData[0], outputType[0], Query(instance))!!
        val buf_err55 = MyPrintWriter()
        if (!expected55.equalsVerbose(actual55, true, true, buf_err55)) {
            fail(expected55.toString() + " .. " + actual55.toString() + " .. " + buf_err55.toString() + " .. " + operator55)
        }
      }finally{
        LuposdateEndpoint.close(instance)
      }
    }
    @Test(timeout = 2000)
    public fun `MOVE 1 - Thread - PartitionByIDTwiceAllCollations - false`() {
      var instance = Luposdate3000Instance()
      try{
        instance.LUPOS_BUFFER_SIZE = 128
        instance.LUPOS_PARTITION_MODE=EPartitionModeExt.Thread
        instance.predefinedPartitionScheme=EPredefinedPartitionSchemesExt.PartitionByIDTwiceAllCollations
        instance.useDictionaryInlineEncoding=false
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
        val operator58 = LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, query)
        LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator58, buf, EQueryResultToStreamExt.EMPTY_STREAM)
        val query59 = Query(instance)
        val graph59 = instance.tripleStoreManager!!.getGraph(outputGraph[0])
        val operator59 = graph59.getIterator(query59, arrayOf(AOPVariable(query59, "s"), AOPVariable(query59, "p"), AOPVariable(query59, "o")), EIndexPatternExt.SPO)
        val actual59 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator59, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected59 = MemoryTable.parseFromAny(outputData[0], outputType[0], Query(instance))!!
        val buf_err59 = MyPrintWriter()
        if (!expected59.equalsVerbose(actual59, true, true, buf_err59)) {
            fail(expected59.toString() + " .. " + actual59.toString() + " .. " + buf_err59.toString() + " .. " + operator59)
        }
      }finally{
        LuposdateEndpoint.close(instance)
      }
    }
    @Test(timeout = 2000)
    public fun `MOVE 1 - Thread - PartitionByID_1_AllCollations - true`() {
      var instance = Luposdate3000Instance()
      try{
        instance.LUPOS_BUFFER_SIZE = 128
        instance.LUPOS_PARTITION_MODE=EPartitionModeExt.Thread
        instance.predefinedPartitionScheme=EPredefinedPartitionSchemesExt.PartitionByID_1_AllCollations
        instance.useDictionaryInlineEncoding=true
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
        val query60 = Query(instance)
        val graph60 = instance.tripleStoreManager!!.getGraph(inputGraph[0])
        val operator60 = graph60.getIterator(query60, arrayOf(AOPVariable(query60, "s"), AOPVariable(query60, "p"), AOPVariable(query60, "o")), EIndexPatternExt.SPO)
        val actual60 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator60, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected60 = MemoryTable.parseFromAny(inputData[0], inputType[0], Query(instance))!!
        val buf_err60 = MyPrintWriter()
        if (!expected60.equalsVerbose(actual60, true, true, buf_err60)) {
            fail(expected60.toString() + " .. " + actual60.toString() + " .. " + buf_err60.toString() + " .. " + operator60)
        }
        val query61 = Query(instance)
        val graph61 = instance.tripleStoreManager!!.getGraph(inputGraph[1])
        val operator61 = graph61.getIterator(query61, arrayOf(AOPVariable(query61, "s"), AOPVariable(query61, "p"), AOPVariable(query61, "o")), EIndexPatternExt.SPO)
        val actual61 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator61, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected61 = MemoryTable.parseFromAny(inputData[1], inputType[1], Query(instance))!!
        val buf_err61 = MyPrintWriter()
        if (!expected61.equalsVerbose(actual61, true, true, buf_err61)) {
            fail(expected61.toString() + " .. " + actual61.toString() + " .. " + buf_err61.toString() + " .. " + operator61)
        }
        val operator62 = LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, query)
        LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator62, buf, EQueryResultToStreamExt.EMPTY_STREAM)
        val query63 = Query(instance)
        val graph63 = instance.tripleStoreManager!!.getGraph(outputGraph[0])
        val operator63 = graph63.getIterator(query63, arrayOf(AOPVariable(query63, "s"), AOPVariable(query63, "p"), AOPVariable(query63, "o")), EIndexPatternExt.SPO)
        val actual63 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator63, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected63 = MemoryTable.parseFromAny(outputData[0], outputType[0], Query(instance))!!
        val buf_err63 = MyPrintWriter()
        if (!expected63.equalsVerbose(actual63, true, true, buf_err63)) {
            fail(expected63.toString() + " .. " + actual63.toString() + " .. " + buf_err63.toString() + " .. " + operator63)
        }
      }finally{
        LuposdateEndpoint.close(instance)
      }
    }
    @Test(timeout = 2000)
    public fun `MOVE 1 - Thread - PartitionByID_1_AllCollations - false`() {
      var instance = Luposdate3000Instance()
      try{
        instance.LUPOS_BUFFER_SIZE = 128
        instance.LUPOS_PARTITION_MODE=EPartitionModeExt.Thread
        instance.predefinedPartitionScheme=EPredefinedPartitionSchemesExt.PartitionByID_1_AllCollations
        instance.useDictionaryInlineEncoding=false
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
        val query64 = Query(instance)
        val graph64 = instance.tripleStoreManager!!.getGraph(inputGraph[0])
        val operator64 = graph64.getIterator(query64, arrayOf(AOPVariable(query64, "s"), AOPVariable(query64, "p"), AOPVariable(query64, "o")), EIndexPatternExt.SPO)
        val actual64 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator64, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected64 = MemoryTable.parseFromAny(inputData[0], inputType[0], Query(instance))!!
        val buf_err64 = MyPrintWriter()
        if (!expected64.equalsVerbose(actual64, true, true, buf_err64)) {
            fail(expected64.toString() + " .. " + actual64.toString() + " .. " + buf_err64.toString() + " .. " + operator64)
        }
        val query65 = Query(instance)
        val graph65 = instance.tripleStoreManager!!.getGraph(inputGraph[1])
        val operator65 = graph65.getIterator(query65, arrayOf(AOPVariable(query65, "s"), AOPVariable(query65, "p"), AOPVariable(query65, "o")), EIndexPatternExt.SPO)
        val actual65 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator65, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected65 = MemoryTable.parseFromAny(inputData[1], inputType[1], Query(instance))!!
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
      }finally{
        LuposdateEndpoint.close(instance)
      }
    }
    @Test(timeout = 2000)
    public fun `MOVE 1 - Thread - PartitionByID_2_AllCollations - true`() {
      var instance = Luposdate3000Instance()
      try{
        instance.LUPOS_BUFFER_SIZE = 128
        instance.LUPOS_PARTITION_MODE=EPartitionModeExt.Thread
        instance.predefinedPartitionScheme=EPredefinedPartitionSchemesExt.PartitionByID_2_AllCollations
        instance.useDictionaryInlineEncoding=true
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
        val query68 = Query(instance)
        val graph68 = instance.tripleStoreManager!!.getGraph(inputGraph[0])
        val operator68 = graph68.getIterator(query68, arrayOf(AOPVariable(query68, "s"), AOPVariable(query68, "p"), AOPVariable(query68, "o")), EIndexPatternExt.SPO)
        val actual68 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator68, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected68 = MemoryTable.parseFromAny(inputData[0], inputType[0], Query(instance))!!
        val buf_err68 = MyPrintWriter()
        if (!expected68.equalsVerbose(actual68, true, true, buf_err68)) {
            fail(expected68.toString() + " .. " + actual68.toString() + " .. " + buf_err68.toString() + " .. " + operator68)
        }
        val query69 = Query(instance)
        val graph69 = instance.tripleStoreManager!!.getGraph(inputGraph[1])
        val operator69 = graph69.getIterator(query69, arrayOf(AOPVariable(query69, "s"), AOPVariable(query69, "p"), AOPVariable(query69, "o")), EIndexPatternExt.SPO)
        val actual69 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator69, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected69 = MemoryTable.parseFromAny(inputData[1], inputType[1], Query(instance))!!
        val buf_err69 = MyPrintWriter()
        if (!expected69.equalsVerbose(actual69, true, true, buf_err69)) {
            fail(expected69.toString() + " .. " + actual69.toString() + " .. " + buf_err69.toString() + " .. " + operator69)
        }
        val operator70 = LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, query)
        LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator70, buf, EQueryResultToStreamExt.EMPTY_STREAM)
        val query71 = Query(instance)
        val graph71 = instance.tripleStoreManager!!.getGraph(outputGraph[0])
        val operator71 = graph71.getIterator(query71, arrayOf(AOPVariable(query71, "s"), AOPVariable(query71, "p"), AOPVariable(query71, "o")), EIndexPatternExt.SPO)
        val actual71 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator71, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected71 = MemoryTable.parseFromAny(outputData[0], outputType[0], Query(instance))!!
        val buf_err71 = MyPrintWriter()
        if (!expected71.equalsVerbose(actual71, true, true, buf_err71)) {
            fail(expected71.toString() + " .. " + actual71.toString() + " .. " + buf_err71.toString() + " .. " + operator71)
        }
      }finally{
        LuposdateEndpoint.close(instance)
      }
    }
    @Test(timeout = 2000)
    public fun `MOVE 1 - Thread - PartitionByID_2_AllCollations - false`() {
      var instance = Luposdate3000Instance()
      try{
        instance.LUPOS_BUFFER_SIZE = 128
        instance.LUPOS_PARTITION_MODE=EPartitionModeExt.Thread
        instance.predefinedPartitionScheme=EPredefinedPartitionSchemesExt.PartitionByID_2_AllCollations
        instance.useDictionaryInlineEncoding=false
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
        val query72 = Query(instance)
        val graph72 = instance.tripleStoreManager!!.getGraph(inputGraph[0])
        val operator72 = graph72.getIterator(query72, arrayOf(AOPVariable(query72, "s"), AOPVariable(query72, "p"), AOPVariable(query72, "o")), EIndexPatternExt.SPO)
        val actual72 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator72, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected72 = MemoryTable.parseFromAny(inputData[0], inputType[0], Query(instance))!!
        val buf_err72 = MyPrintWriter()
        if (!expected72.equalsVerbose(actual72, true, true, buf_err72)) {
            fail(expected72.toString() + " .. " + actual72.toString() + " .. " + buf_err72.toString() + " .. " + operator72)
        }
        val query73 = Query(instance)
        val graph73 = instance.tripleStoreManager!!.getGraph(inputGraph[1])
        val operator73 = graph73.getIterator(query73, arrayOf(AOPVariable(query73, "s"), AOPVariable(query73, "p"), AOPVariable(query73, "o")), EIndexPatternExt.SPO)
        val actual73 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator73, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected73 = MemoryTable.parseFromAny(inputData[1], inputType[1], Query(instance))!!
        val buf_err73 = MyPrintWriter()
        if (!expected73.equalsVerbose(actual73, true, true, buf_err73)) {
            fail(expected73.toString() + " .. " + actual73.toString() + " .. " + buf_err73.toString() + " .. " + operator73)
        }
        val operator74 = LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, query)
        LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator74, buf, EQueryResultToStreamExt.EMPTY_STREAM)
        val query75 = Query(instance)
        val graph75 = instance.tripleStoreManager!!.getGraph(outputGraph[0])
        val operator75 = graph75.getIterator(query75, arrayOf(AOPVariable(query75, "s"), AOPVariable(query75, "p"), AOPVariable(query75, "o")), EIndexPatternExt.SPO)
        val actual75 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator75, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected75 = MemoryTable.parseFromAny(outputData[0], outputType[0], Query(instance))!!
        val buf_err75 = MyPrintWriter()
        if (!expected75.equalsVerbose(actual75, true, true, buf_err75)) {
            fail(expected75.toString() + " .. " + actual75.toString() + " .. " + buf_err75.toString() + " .. " + operator75)
        }
      }finally{
        LuposdateEndpoint.close(instance)
      }
    }
    @Test(timeout = 2000)
    public fun `MOVE 1 - Thread - PartitionByID_O_AllCollations - true`() {
      var instance = Luposdate3000Instance()
      try{
        instance.LUPOS_BUFFER_SIZE = 128
        instance.LUPOS_PARTITION_MODE=EPartitionModeExt.Thread
        instance.predefinedPartitionScheme=EPredefinedPartitionSchemesExt.PartitionByID_O_AllCollations
        instance.useDictionaryInlineEncoding=true
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
        val query76 = Query(instance)
        val graph76 = instance.tripleStoreManager!!.getGraph(inputGraph[0])
        val operator76 = graph76.getIterator(query76, arrayOf(AOPVariable(query76, "s"), AOPVariable(query76, "p"), AOPVariable(query76, "o")), EIndexPatternExt.SPO)
        val actual76 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator76, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected76 = MemoryTable.parseFromAny(inputData[0], inputType[0], Query(instance))!!
        val buf_err76 = MyPrintWriter()
        if (!expected76.equalsVerbose(actual76, true, true, buf_err76)) {
            fail(expected76.toString() + " .. " + actual76.toString() + " .. " + buf_err76.toString() + " .. " + operator76)
        }
        val query77 = Query(instance)
        val graph77 = instance.tripleStoreManager!!.getGraph(inputGraph[1])
        val operator77 = graph77.getIterator(query77, arrayOf(AOPVariable(query77, "s"), AOPVariable(query77, "p"), AOPVariable(query77, "o")), EIndexPatternExt.SPO)
        val actual77 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator77, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected77 = MemoryTable.parseFromAny(inputData[1], inputType[1], Query(instance))!!
        val buf_err77 = MyPrintWriter()
        if (!expected77.equalsVerbose(actual77, true, true, buf_err77)) {
            fail(expected77.toString() + " .. " + actual77.toString() + " .. " + buf_err77.toString() + " .. " + operator77)
        }
        val operator78 = LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, query)
        LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator78, buf, EQueryResultToStreamExt.EMPTY_STREAM)
        val query79 = Query(instance)
        val graph79 = instance.tripleStoreManager!!.getGraph(outputGraph[0])
        val operator79 = graph79.getIterator(query79, arrayOf(AOPVariable(query79, "s"), AOPVariable(query79, "p"), AOPVariable(query79, "o")), EIndexPatternExt.SPO)
        val actual79 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator79, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected79 = MemoryTable.parseFromAny(outputData[0], outputType[0], Query(instance))!!
        val buf_err79 = MyPrintWriter()
        if (!expected79.equalsVerbose(actual79, true, true, buf_err79)) {
            fail(expected79.toString() + " .. " + actual79.toString() + " .. " + buf_err79.toString() + " .. " + operator79)
        }
      }finally{
        LuposdateEndpoint.close(instance)
      }
    }
    @Test(timeout = 2000)
    public fun `MOVE 1 - Thread - PartitionByID_O_AllCollations - false`() {
      var instance = Luposdate3000Instance()
      try{
        instance.LUPOS_BUFFER_SIZE = 128
        instance.LUPOS_PARTITION_MODE=EPartitionModeExt.Thread
        instance.predefinedPartitionScheme=EPredefinedPartitionSchemesExt.PartitionByID_O_AllCollations
        instance.useDictionaryInlineEncoding=false
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
        val query80 = Query(instance)
        val graph80 = instance.tripleStoreManager!!.getGraph(inputGraph[0])
        val operator80 = graph80.getIterator(query80, arrayOf(AOPVariable(query80, "s"), AOPVariable(query80, "p"), AOPVariable(query80, "o")), EIndexPatternExt.SPO)
        val actual80 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator80, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected80 = MemoryTable.parseFromAny(inputData[0], inputType[0], Query(instance))!!
        val buf_err80 = MyPrintWriter()
        if (!expected80.equalsVerbose(actual80, true, true, buf_err80)) {
            fail(expected80.toString() + " .. " + actual80.toString() + " .. " + buf_err80.toString() + " .. " + operator80)
        }
        val query81 = Query(instance)
        val graph81 = instance.tripleStoreManager!!.getGraph(inputGraph[1])
        val operator81 = graph81.getIterator(query81, arrayOf(AOPVariable(query81, "s"), AOPVariable(query81, "p"), AOPVariable(query81, "o")), EIndexPatternExt.SPO)
        val actual81 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator81, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected81 = MemoryTable.parseFromAny(inputData[1], inputType[1], Query(instance))!!
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
      }finally{
        LuposdateEndpoint.close(instance)
      }
    }
    @Test(timeout = 2000)
    public fun `MOVE 1 - Thread - PartitionByID_S_AllCollations - true`() {
      var instance = Luposdate3000Instance()
      try{
        instance.LUPOS_BUFFER_SIZE = 128
        instance.LUPOS_PARTITION_MODE=EPartitionModeExt.Thread
        instance.predefinedPartitionScheme=EPredefinedPartitionSchemesExt.PartitionByID_S_AllCollations
        instance.useDictionaryInlineEncoding=true
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
        val query84 = Query(instance)
        val graph84 = instance.tripleStoreManager!!.getGraph(inputGraph[0])
        val operator84 = graph84.getIterator(query84, arrayOf(AOPVariable(query84, "s"), AOPVariable(query84, "p"), AOPVariable(query84, "o")), EIndexPatternExt.SPO)
        val actual84 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator84, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected84 = MemoryTable.parseFromAny(inputData[0], inputType[0], Query(instance))!!
        val buf_err84 = MyPrintWriter()
        if (!expected84.equalsVerbose(actual84, true, true, buf_err84)) {
            fail(expected84.toString() + " .. " + actual84.toString() + " .. " + buf_err84.toString() + " .. " + operator84)
        }
        val query85 = Query(instance)
        val graph85 = instance.tripleStoreManager!!.getGraph(inputGraph[1])
        val operator85 = graph85.getIterator(query85, arrayOf(AOPVariable(query85, "s"), AOPVariable(query85, "p"), AOPVariable(query85, "o")), EIndexPatternExt.SPO)
        val actual85 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator85, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected85 = MemoryTable.parseFromAny(inputData[1], inputType[1], Query(instance))!!
        val buf_err85 = MyPrintWriter()
        if (!expected85.equalsVerbose(actual85, true, true, buf_err85)) {
            fail(expected85.toString() + " .. " + actual85.toString() + " .. " + buf_err85.toString() + " .. " + operator85)
        }
        val operator86 = LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, query)
        LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator86, buf, EQueryResultToStreamExt.EMPTY_STREAM)
        val query87 = Query(instance)
        val graph87 = instance.tripleStoreManager!!.getGraph(outputGraph[0])
        val operator87 = graph87.getIterator(query87, arrayOf(AOPVariable(query87, "s"), AOPVariable(query87, "p"), AOPVariable(query87, "o")), EIndexPatternExt.SPO)
        val actual87 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator87, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected87 = MemoryTable.parseFromAny(outputData[0], outputType[0], Query(instance))!!
        val buf_err87 = MyPrintWriter()
        if (!expected87.equalsVerbose(actual87, true, true, buf_err87)) {
            fail(expected87.toString() + " .. " + actual87.toString() + " .. " + buf_err87.toString() + " .. " + operator87)
        }
      }finally{
        LuposdateEndpoint.close(instance)
      }
    }
    @Test(timeout = 2000)
    public fun `MOVE 1 - Thread - PartitionByID_S_AllCollations - false`() {
      var instance = Luposdate3000Instance()
      try{
        instance.LUPOS_BUFFER_SIZE = 128
        instance.LUPOS_PARTITION_MODE=EPartitionModeExt.Thread
        instance.predefinedPartitionScheme=EPredefinedPartitionSchemesExt.PartitionByID_S_AllCollations
        instance.useDictionaryInlineEncoding=false
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
        val query88 = Query(instance)
        val graph88 = instance.tripleStoreManager!!.getGraph(inputGraph[0])
        val operator88 = graph88.getIterator(query88, arrayOf(AOPVariable(query88, "s"), AOPVariable(query88, "p"), AOPVariable(query88, "o")), EIndexPatternExt.SPO)
        val actual88 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator88, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected88 = MemoryTable.parseFromAny(inputData[0], inputType[0], Query(instance))!!
        val buf_err88 = MyPrintWriter()
        if (!expected88.equalsVerbose(actual88, true, true, buf_err88)) {
            fail(expected88.toString() + " .. " + actual88.toString() + " .. " + buf_err88.toString() + " .. " + operator88)
        }
        val query89 = Query(instance)
        val graph89 = instance.tripleStoreManager!!.getGraph(inputGraph[1])
        val operator89 = graph89.getIterator(query89, arrayOf(AOPVariable(query89, "s"), AOPVariable(query89, "p"), AOPVariable(query89, "o")), EIndexPatternExt.SPO)
        val actual89 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator89, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected89 = MemoryTable.parseFromAny(inputData[1], inputType[1], Query(instance))!!
        val buf_err89 = MyPrintWriter()
        if (!expected89.equalsVerbose(actual89, true, true, buf_err89)) {
            fail(expected89.toString() + " .. " + actual89.toString() + " .. " + buf_err89.toString() + " .. " + operator89)
        }
        val operator90 = LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, query)
        LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator90, buf, EQueryResultToStreamExt.EMPTY_STREAM)
        val query91 = Query(instance)
        val graph91 = instance.tripleStoreManager!!.getGraph(outputGraph[0])
        val operator91 = graph91.getIterator(query91, arrayOf(AOPVariable(query91, "s"), AOPVariable(query91, "p"), AOPVariable(query91, "o")), EIndexPatternExt.SPO)
        val actual91 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator91, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected91 = MemoryTable.parseFromAny(outputData[0], outputType[0], Query(instance))!!
        val buf_err91 = MyPrintWriter()
        if (!expected91.equalsVerbose(actual91, true, true, buf_err91)) {
            fail(expected91.toString() + " .. " + actual91.toString() + " .. " + buf_err91.toString() + " .. " + operator91)
        }
      }finally{
        LuposdateEndpoint.close(instance)
      }
    }
    @Test(timeout = 2000)
    public fun `MOVE 1 - Thread - PartitionByKeyAllCollations - true`() {
      var instance = Luposdate3000Instance()
      try{
        instance.LUPOS_BUFFER_SIZE = 128
        instance.LUPOS_PARTITION_MODE=EPartitionModeExt.Thread
        instance.predefinedPartitionScheme=EPredefinedPartitionSchemesExt.PartitionByKeyAllCollations
        instance.useDictionaryInlineEncoding=true
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
        val query92 = Query(instance)
        val graph92 = instance.tripleStoreManager!!.getGraph(inputGraph[0])
        val operator92 = graph92.getIterator(query92, arrayOf(AOPVariable(query92, "s"), AOPVariable(query92, "p"), AOPVariable(query92, "o")), EIndexPatternExt.SPO)
        val actual92 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator92, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected92 = MemoryTable.parseFromAny(inputData[0], inputType[0], Query(instance))!!
        val buf_err92 = MyPrintWriter()
        if (!expected92.equalsVerbose(actual92, true, true, buf_err92)) {
            fail(expected92.toString() + " .. " + actual92.toString() + " .. " + buf_err92.toString() + " .. " + operator92)
        }
        val query93 = Query(instance)
        val graph93 = instance.tripleStoreManager!!.getGraph(inputGraph[1])
        val operator93 = graph93.getIterator(query93, arrayOf(AOPVariable(query93, "s"), AOPVariable(query93, "p"), AOPVariable(query93, "o")), EIndexPatternExt.SPO)
        val actual93 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator93, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected93 = MemoryTable.parseFromAny(inputData[1], inputType[1], Query(instance))!!
        val buf_err93 = MyPrintWriter()
        if (!expected93.equalsVerbose(actual93, true, true, buf_err93)) {
            fail(expected93.toString() + " .. " + actual93.toString() + " .. " + buf_err93.toString() + " .. " + operator93)
        }
        val operator94 = LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, query)
        LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator94, buf, EQueryResultToStreamExt.EMPTY_STREAM)
        val query95 = Query(instance)
        val graph95 = instance.tripleStoreManager!!.getGraph(outputGraph[0])
        val operator95 = graph95.getIterator(query95, arrayOf(AOPVariable(query95, "s"), AOPVariable(query95, "p"), AOPVariable(query95, "o")), EIndexPatternExt.SPO)
        val actual95 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator95, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected95 = MemoryTable.parseFromAny(outputData[0], outputType[0], Query(instance))!!
        val buf_err95 = MyPrintWriter()
        if (!expected95.equalsVerbose(actual95, true, true, buf_err95)) {
            fail(expected95.toString() + " .. " + actual95.toString() + " .. " + buf_err95.toString() + " .. " + operator95)
        }
      }finally{
        LuposdateEndpoint.close(instance)
      }
    }
    @Test(timeout = 2000)
    public fun `MOVE 1 - Thread - PartitionByKeyAllCollations - false`() {
      var instance = Luposdate3000Instance()
      try{
        instance.LUPOS_BUFFER_SIZE = 128
        instance.LUPOS_PARTITION_MODE=EPartitionModeExt.Thread
        instance.predefinedPartitionScheme=EPredefinedPartitionSchemesExt.PartitionByKeyAllCollations
        instance.useDictionaryInlineEncoding=false
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
        val query96 = Query(instance)
        val graph96 = instance.tripleStoreManager!!.getGraph(inputGraph[0])
        val operator96 = graph96.getIterator(query96, arrayOf(AOPVariable(query96, "s"), AOPVariable(query96, "p"), AOPVariable(query96, "o")), EIndexPatternExt.SPO)
        val actual96 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator96, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected96 = MemoryTable.parseFromAny(inputData[0], inputType[0], Query(instance))!!
        val buf_err96 = MyPrintWriter()
        if (!expected96.equalsVerbose(actual96, true, true, buf_err96)) {
            fail(expected96.toString() + " .. " + actual96.toString() + " .. " + buf_err96.toString() + " .. " + operator96)
        }
        val query97 = Query(instance)
        val graph97 = instance.tripleStoreManager!!.getGraph(inputGraph[1])
        val operator97 = graph97.getIterator(query97, arrayOf(AOPVariable(query97, "s"), AOPVariable(query97, "p"), AOPVariable(query97, "o")), EIndexPatternExt.SPO)
        val actual97 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator97, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected97 = MemoryTable.parseFromAny(inputData[1], inputType[1], Query(instance))!!
        val buf_err97 = MyPrintWriter()
        if (!expected97.equalsVerbose(actual97, true, true, buf_err97)) {
            fail(expected97.toString() + " .. " + actual97.toString() + " .. " + buf_err97.toString() + " .. " + operator97)
        }
        val operator98 = LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, query)
        LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator98, buf, EQueryResultToStreamExt.EMPTY_STREAM)
        val query99 = Query(instance)
        val graph99 = instance.tripleStoreManager!!.getGraph(outputGraph[0])
        val operator99 = graph99.getIterator(query99, arrayOf(AOPVariable(query99, "s"), AOPVariable(query99, "p"), AOPVariable(query99, "o")), EIndexPatternExt.SPO)
        val actual99 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator99, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected99 = MemoryTable.parseFromAny(outputData[0], outputType[0], Query(instance))!!
        val buf_err99 = MyPrintWriter()
        if (!expected99.equalsVerbose(actual99, true, true, buf_err99)) {
            fail(expected99.toString() + " .. " + actual99.toString() + " .. " + buf_err99.toString() + " .. " + operator99)
        }
      }finally{
        LuposdateEndpoint.close(instance)
      }
    }
    @Test(timeout = 2000)
    public fun `MOVE 1 - Thread - Simple - true`() {
      var instance = Luposdate3000Instance()
      try{
        instance.LUPOS_BUFFER_SIZE = 128
        instance.LUPOS_PARTITION_MODE=EPartitionModeExt.Thread
        instance.predefinedPartitionScheme=EPredefinedPartitionSchemesExt.Simple
        instance.useDictionaryInlineEncoding=true
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
        val query100 = Query(instance)
        val graph100 = instance.tripleStoreManager!!.getGraph(inputGraph[0])
        val operator100 = graph100.getIterator(query100, arrayOf(AOPVariable(query100, "s"), AOPVariable(query100, "p"), AOPVariable(query100, "o")), EIndexPatternExt.SPO)
        val actual100 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator100, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected100 = MemoryTable.parseFromAny(inputData[0], inputType[0], Query(instance))!!
        val buf_err100 = MyPrintWriter()
        if (!expected100.equalsVerbose(actual100, true, true, buf_err100)) {
            fail(expected100.toString() + " .. " + actual100.toString() + " .. " + buf_err100.toString() + " .. " + operator100)
        }
        val query101 = Query(instance)
        val graph101 = instance.tripleStoreManager!!.getGraph(inputGraph[1])
        val operator101 = graph101.getIterator(query101, arrayOf(AOPVariable(query101, "s"), AOPVariable(query101, "p"), AOPVariable(query101, "o")), EIndexPatternExt.SPO)
        val actual101 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator101, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected101 = MemoryTable.parseFromAny(inputData[1], inputType[1], Query(instance))!!
        val buf_err101 = MyPrintWriter()
        if (!expected101.equalsVerbose(actual101, true, true, buf_err101)) {
            fail(expected101.toString() + " .. " + actual101.toString() + " .. " + buf_err101.toString() + " .. " + operator101)
        }
        val operator102 = LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, query)
        LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator102, buf, EQueryResultToStreamExt.EMPTY_STREAM)
        val query103 = Query(instance)
        val graph103 = instance.tripleStoreManager!!.getGraph(outputGraph[0])
        val operator103 = graph103.getIterator(query103, arrayOf(AOPVariable(query103, "s"), AOPVariable(query103, "p"), AOPVariable(query103, "o")), EIndexPatternExt.SPO)
        val actual103 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator103, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected103 = MemoryTable.parseFromAny(outputData[0], outputType[0], Query(instance))!!
        val buf_err103 = MyPrintWriter()
        if (!expected103.equalsVerbose(actual103, true, true, buf_err103)) {
            fail(expected103.toString() + " .. " + actual103.toString() + " .. " + buf_err103.toString() + " .. " + operator103)
        }
      }finally{
        LuposdateEndpoint.close(instance)
      }
    }
    @Test(timeout = 2000)
    public fun `MOVE 1 - Thread - Simple - false`() {
      var instance = Luposdate3000Instance()
      try{
        instance.LUPOS_BUFFER_SIZE = 128
        instance.LUPOS_PARTITION_MODE=EPartitionModeExt.Thread
        instance.predefinedPartitionScheme=EPredefinedPartitionSchemesExt.Simple
        instance.useDictionaryInlineEncoding=false
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
        val query104 = Query(instance)
        val graph104 = instance.tripleStoreManager!!.getGraph(inputGraph[0])
        val operator104 = graph104.getIterator(query104, arrayOf(AOPVariable(query104, "s"), AOPVariable(query104, "p"), AOPVariable(query104, "o")), EIndexPatternExt.SPO)
        val actual104 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator104, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected104 = MemoryTable.parseFromAny(inputData[0], inputType[0], Query(instance))!!
        val buf_err104 = MyPrintWriter()
        if (!expected104.equalsVerbose(actual104, true, true, buf_err104)) {
            fail(expected104.toString() + " .. " + actual104.toString() + " .. " + buf_err104.toString() + " .. " + operator104)
        }
        val query105 = Query(instance)
        val graph105 = instance.tripleStoreManager!!.getGraph(inputGraph[1])
        val operator105 = graph105.getIterator(query105, arrayOf(AOPVariable(query105, "s"), AOPVariable(query105, "p"), AOPVariable(query105, "o")), EIndexPatternExt.SPO)
        val actual105 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator105, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected105 = MemoryTable.parseFromAny(inputData[1], inputType[1], Query(instance))!!
        val buf_err105 = MyPrintWriter()
        if (!expected105.equalsVerbose(actual105, true, true, buf_err105)) {
            fail(expected105.toString() + " .. " + actual105.toString() + " .. " + buf_err105.toString() + " .. " + operator105)
        }
        val operator106 = LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, query)
        LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator106, buf, EQueryResultToStreamExt.EMPTY_STREAM)
        val query107 = Query(instance)
        val graph107 = instance.tripleStoreManager!!.getGraph(outputGraph[0])
        val operator107 = graph107.getIterator(query107, arrayOf(AOPVariable(query107, "s"), AOPVariable(query107, "p"), AOPVariable(query107, "o")), EIndexPatternExt.SPO)
        val actual107 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator107, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected107 = MemoryTable.parseFromAny(outputData[0], outputType[0], Query(instance))!!
        val buf_err107 = MyPrintWriter()
        if (!expected107.equalsVerbose(actual107, true, true, buf_err107)) {
            fail(expected107.toString() + " .. " + actual107.toString() + " .. " + buf_err107.toString() + " .. " + operator107)
        }
      }finally{
        LuposdateEndpoint.close(instance)
      }
    }
    public fun simulatorHelper(fileName:String,cfg:MutableMap<String,Any>) {
        val simRun = SimulationRun()
        val config=simRun.parseConfig(fileName,false)
        config.jsonObjects.database.putAll(cfg)
        simRun.sim = Simulation(config.getEntities())
        simRun.sim.maxClock = if (simRun.simMaxClock == simRun.notInitializedClock) simRun.sim.maxClock else simRun.simMaxClock
        simRun.sim.steadyClock = if (simRun.simSteadyClock == simRun.notInitializedClock) simRun.sim.steadyClock else simRun.simSteadyClock
        simRun.sim.startUp()
        val instance = (config.devices.filter {it.userApplication!=null}.map{it.userApplication!!.getAllChildApplications()}.flatten().filter{it is DatabaseHandle}.first()as DatabaseHandle).instance
        val pkg0 = MySimulatorTestingImportPackage(inputData[0], inputGraph[0], inputType[0])
        val pkg1 = MySimulatorTestingImportPackage(inputData[1], inputGraph[1], inputType[1])
        pkg0.onFinish = pkg1
        var verifyExecuted2 = 0
        val pkg2 = MySimulatorTestingCompareGraphPackage("SELECT ?s ?p ?o WHERE { ?s ?p ?o . }",MemoryTable.parseFromAny(inputData[0], inputType[0], Query(instance))!!, {verifyExecuted2++})
        pkg1.onFinish = pkg2
        var verifyExecuted3 = 0
        val pkg3 = MySimulatorTestingCompareGraphPackage("SELECT ?s ?p ?o WHERE { GRAPH <${inputGraph[1]}> { ?s ?p ?o . }}",MemoryTable.parseFromAny(inputData[1], inputType[1], Query(instance))!!, {verifyExecuted3++})
        pkg2.onFinish = pkg3
        val pkg4 = MySimulatorTestingExecute(query)
        pkg3.onFinish = pkg4
        var verifyExecuted5 = 0
        val pkg5 = MySimulatorTestingCompareGraphPackage("SELECT ?s ?p ?o WHERE { GRAPH <${outputGraph[0]}> { ?s ?p ?o . }}",MemoryTable.parseFromAny(outputData[0], outputType[0], Query(instance))!!, {verifyExecuted5++})
        pkg4.onFinish = pkg5
        config.querySenders[0].queryPck = pkg0
        simRun.sim.run()
        simRun.sim.shutDown()
        if (verifyExecuted2==0) {
            fail("pck2 not verified")
        }
        if (verifyExecuted3==0) {
            fail("pck3 not verified")
        }
        if (verifyExecuted5==0) {
            fail("pck5 not verified")
        }
    }
}
