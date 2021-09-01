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
package lupos.code_gen_test_15
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

public class SimpleDELETE4USING {
    internal val inputData = arrayOf(
        File("src/jvmTest/resources/SimpleDELETE4USING.input").readAsString(),
        File("src/jvmTest/resources/SimpleDELETE4USING.input1").readAsString(),
        File("src/jvmTest/resources/SimpleDELETE4USING.input2").readAsString(),
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
        File("src/jvmTest/resources/SimpleDELETE4USING.output0").readAsString(),
        File("src/jvmTest/resources/SimpleDELETE4USING.output1").readAsString(),
        File("src/jvmTest/resources/SimpleDELETE4USING.output2").readAsString(),
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
        "DELETE  \n" +
        "{ \n" +
        "  ?s ?p ?o . \n" +
        "} \n" +
        "USING <http://example.org/g3> \n" +
        "WHERE \n" +
        "{ \n" +
        "  GRAPH <http://example.org/g2> { ?s foaf:knows :d . \n" +
        "                                  ?s ?p ?o } \n" +
        "} \n" +
        ""

    @Test(timeout = 2000)
    public fun `Simple DELETE 4 USING - None - PartitionByIDTwiceAllCollations - false`() {
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
      }finally{
        LuposdateEndpoint.close(instance)
      }
    }
    @Test(timeout = 2000)
    public fun `Simple DELETE 4 USING - None - PartitionByID_1_AllCollations - true`() {
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
      }finally{
        LuposdateEndpoint.close(instance)
      }
    }
    @Test(timeout = 2000)
    public fun `Simple DELETE 4 USING - None - PartitionByID_1_AllCollations - false`() {
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
      }finally{
        LuposdateEndpoint.close(instance)
      }
    }
    @Test(timeout = 2000)
    public fun `Simple DELETE 4 USING - None - PartitionByID_2_AllCollations - true`() {
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
      }finally{
        LuposdateEndpoint.close(instance)
      }
    }
    @Test(timeout = 2000)
    public fun `Simple DELETE 4 USING - None - PartitionByID_2_AllCollations - false`() {
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
      }finally{
        LuposdateEndpoint.close(instance)
      }
    }
    @Test(timeout = 2000)
    public fun `Simple DELETE 4 USING - None - PartitionByID_O_AllCollations - true`() {
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
      }finally{
        LuposdateEndpoint.close(instance)
      }
    }
    @Test(timeout = 2000)
    public fun `Simple DELETE 4 USING - None - PartitionByID_O_AllCollations - false`() {
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
      }finally{
        LuposdateEndpoint.close(instance)
      }
    }
    @Test(timeout = 2000)
    public fun `Simple DELETE 4 USING - None - PartitionByID_S_AllCollations - true`() {
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
      }finally{
        LuposdateEndpoint.close(instance)
      }
    }
    @Test(timeout = 2000)
    public fun `Simple DELETE 4 USING - None - PartitionByID_S_AllCollations - false`() {
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
      }finally{
        LuposdateEndpoint.close(instance)
      }
    }
    @Test(timeout = 2000)
    public fun `Simple DELETE 4 USING - None - PartitionByKeyAllCollations - true`() {
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
      }finally{
        LuposdateEndpoint.close(instance)
      }
    }
    @Test(timeout = 2000)
    public fun `Simple DELETE 4 USING - None - PartitionByKeyAllCollations - false`() {
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
      }finally{
        LuposdateEndpoint.close(instance)
      }
    }
    @Test(timeout = 2000)
    public fun `Simple DELETE 4 USING - None - Simple - true`() {
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
      }finally{
        LuposdateEndpoint.close(instance)
      }
    }
    @Test(timeout = 2000)
    public fun `Simple DELETE 4 USING - None - Simple - false`() {
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
        if (listOf(".n3", ".ttl", ".nt").contains(inputType[2])) {
            LuposdateEndpoint.importTurtleString(instance, inputData[2], inputGraph[2])
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
        val query86 = Query(instance)
        val graph86 = instance.tripleStoreManager!!.getGraph(inputGraph[2])
        val operator86 = graph86.getIterator(query86, arrayOf(AOPVariable(query86, "s"), AOPVariable(query86, "p"), AOPVariable(query86, "o")), EIndexPatternExt.SPO)
        val actual86 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator86, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected86 = MemoryTable.parseFromAny(inputData[2], inputType[2], Query(instance))!!
        val buf_err86 = MyPrintWriter()
        if (!expected86.equalsVerbose(actual86, true, true, buf_err86)) {
            fail(expected86.toString() + " .. " + actual86.toString() + " .. " + buf_err86.toString() + " .. " + operator86)
        }
        val operator87 = LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, query)
        LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator87, buf, EQueryResultToStreamExt.EMPTY_STREAM)
        val query88 = Query(instance)
        val graph88 = instance.tripleStoreManager!!.getGraph(outputGraph[0])
        val operator88 = graph88.getIterator(query88, arrayOf(AOPVariable(query88, "s"), AOPVariable(query88, "p"), AOPVariable(query88, "o")), EIndexPatternExt.SPO)
        val actual88 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator88, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected88 = MemoryTable.parseFromAny(outputData[0], outputType[0], Query(instance))!!
        val buf_err88 = MyPrintWriter()
        if (!expected88.equalsVerbose(actual88, true, true, buf_err88)) {
            fail(expected88.toString() + " .. " + actual88.toString() + " .. " + buf_err88.toString() + " .. " + operator88)
        }
        val query89 = Query(instance)
        val graph89 = instance.tripleStoreManager!!.getGraph(outputGraph[1])
        val operator89 = graph89.getIterator(query89, arrayOf(AOPVariable(query89, "s"), AOPVariable(query89, "p"), AOPVariable(query89, "o")), EIndexPatternExt.SPO)
        val actual89 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator89, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected89 = MemoryTable.parseFromAny(outputData[1], outputType[1], Query(instance))!!
        val buf_err89 = MyPrintWriter()
        if (!expected89.equalsVerbose(actual89, true, true, buf_err89)) {
            fail(expected89.toString() + " .. " + actual89.toString() + " .. " + buf_err89.toString() + " .. " + operator89)
        }
        val query90 = Query(instance)
        val graph90 = instance.tripleStoreManager!!.getGraph(outputGraph[2])
        val operator90 = graph90.getIterator(query90, arrayOf(AOPVariable(query90, "s"), AOPVariable(query90, "p"), AOPVariable(query90, "o")), EIndexPatternExt.SPO)
        val actual90 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator90, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected90 = MemoryTable.parseFromAny(outputData[2], outputType[2], Query(instance))!!
        val buf_err90 = MyPrintWriter()
        if (!expected90.equalsVerbose(actual90, true, true, buf_err90)) {
            fail(expected90.toString() + " .. " + actual90.toString() + " .. " + buf_err90.toString() + " .. " + operator90)
        }
      }finally{
        LuposdateEndpoint.close(instance)
      }
    }
    @Test(timeout = 2000)
    public fun `Simple DELETE 4 USING - Thread - PartitionByIDTwiceAllCollations - true`() {
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
        if (listOf(".n3", ".ttl", ".nt").contains(inputType[2])) {
            LuposdateEndpoint.importTurtleString(instance, inputData[2], inputGraph[2])
        } else {
            TODO()
        }
        val query91 = Query(instance)
        val graph91 = instance.tripleStoreManager!!.getGraph(inputGraph[0])
        val operator91 = graph91.getIterator(query91, arrayOf(AOPVariable(query91, "s"), AOPVariable(query91, "p"), AOPVariable(query91, "o")), EIndexPatternExt.SPO)
        val actual91 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator91, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected91 = MemoryTable.parseFromAny(inputData[0], inputType[0], Query(instance))!!
        val buf_err91 = MyPrintWriter()
        if (!expected91.equalsVerbose(actual91, true, true, buf_err91)) {
            fail(expected91.toString() + " .. " + actual91.toString() + " .. " + buf_err91.toString() + " .. " + operator91)
        }
        val query92 = Query(instance)
        val graph92 = instance.tripleStoreManager!!.getGraph(inputGraph[1])
        val operator92 = graph92.getIterator(query92, arrayOf(AOPVariable(query92, "s"), AOPVariable(query92, "p"), AOPVariable(query92, "o")), EIndexPatternExt.SPO)
        val actual92 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator92, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected92 = MemoryTable.parseFromAny(inputData[1], inputType[1], Query(instance))!!
        val buf_err92 = MyPrintWriter()
        if (!expected92.equalsVerbose(actual92, true, true, buf_err92)) {
            fail(expected92.toString() + " .. " + actual92.toString() + " .. " + buf_err92.toString() + " .. " + operator92)
        }
        val query93 = Query(instance)
        val graph93 = instance.tripleStoreManager!!.getGraph(inputGraph[2])
        val operator93 = graph93.getIterator(query93, arrayOf(AOPVariable(query93, "s"), AOPVariable(query93, "p"), AOPVariable(query93, "o")), EIndexPatternExt.SPO)
        val actual93 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator93, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected93 = MemoryTable.parseFromAny(inputData[2], inputType[2], Query(instance))!!
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
        val query96 = Query(instance)
        val graph96 = instance.tripleStoreManager!!.getGraph(outputGraph[1])
        val operator96 = graph96.getIterator(query96, arrayOf(AOPVariable(query96, "s"), AOPVariable(query96, "p"), AOPVariable(query96, "o")), EIndexPatternExt.SPO)
        val actual96 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator96, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected96 = MemoryTable.parseFromAny(outputData[1], outputType[1], Query(instance))!!
        val buf_err96 = MyPrintWriter()
        if (!expected96.equalsVerbose(actual96, true, true, buf_err96)) {
            fail(expected96.toString() + " .. " + actual96.toString() + " .. " + buf_err96.toString() + " .. " + operator96)
        }
        val query97 = Query(instance)
        val graph97 = instance.tripleStoreManager!!.getGraph(outputGraph[2])
        val operator97 = graph97.getIterator(query97, arrayOf(AOPVariable(query97, "s"), AOPVariable(query97, "p"), AOPVariable(query97, "o")), EIndexPatternExt.SPO)
        val actual97 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator97, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected97 = MemoryTable.parseFromAny(outputData[2], outputType[2], Query(instance))!!
        val buf_err97 = MyPrintWriter()
        if (!expected97.equalsVerbose(actual97, true, true, buf_err97)) {
            fail(expected97.toString() + " .. " + actual97.toString() + " .. " + buf_err97.toString() + " .. " + operator97)
        }
      }finally{
        LuposdateEndpoint.close(instance)
      }
    }
    @Test(timeout = 2000)
    public fun `Simple DELETE 4 USING - Thread - PartitionByIDTwiceAllCollations - false`() {
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
        if (listOf(".n3", ".ttl", ".nt").contains(inputType[2])) {
            LuposdateEndpoint.importTurtleString(instance, inputData[2], inputGraph[2])
        } else {
            TODO()
        }
        val query98 = Query(instance)
        val graph98 = instance.tripleStoreManager!!.getGraph(inputGraph[0])
        val operator98 = graph98.getIterator(query98, arrayOf(AOPVariable(query98, "s"), AOPVariable(query98, "p"), AOPVariable(query98, "o")), EIndexPatternExt.SPO)
        val actual98 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator98, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected98 = MemoryTable.parseFromAny(inputData[0], inputType[0], Query(instance))!!
        val buf_err98 = MyPrintWriter()
        if (!expected98.equalsVerbose(actual98, true, true, buf_err98)) {
            fail(expected98.toString() + " .. " + actual98.toString() + " .. " + buf_err98.toString() + " .. " + operator98)
        }
        val query99 = Query(instance)
        val graph99 = instance.tripleStoreManager!!.getGraph(inputGraph[1])
        val operator99 = graph99.getIterator(query99, arrayOf(AOPVariable(query99, "s"), AOPVariable(query99, "p"), AOPVariable(query99, "o")), EIndexPatternExt.SPO)
        val actual99 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator99, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected99 = MemoryTable.parseFromAny(inputData[1], inputType[1], Query(instance))!!
        val buf_err99 = MyPrintWriter()
        if (!expected99.equalsVerbose(actual99, true, true, buf_err99)) {
            fail(expected99.toString() + " .. " + actual99.toString() + " .. " + buf_err99.toString() + " .. " + operator99)
        }
        val query100 = Query(instance)
        val graph100 = instance.tripleStoreManager!!.getGraph(inputGraph[2])
        val operator100 = graph100.getIterator(query100, arrayOf(AOPVariable(query100, "s"), AOPVariable(query100, "p"), AOPVariable(query100, "o")), EIndexPatternExt.SPO)
        val actual100 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator100, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected100 = MemoryTable.parseFromAny(inputData[2], inputType[2], Query(instance))!!
        val buf_err100 = MyPrintWriter()
        if (!expected100.equalsVerbose(actual100, true, true, buf_err100)) {
            fail(expected100.toString() + " .. " + actual100.toString() + " .. " + buf_err100.toString() + " .. " + operator100)
        }
        val operator101 = LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, query)
        LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator101, buf, EQueryResultToStreamExt.EMPTY_STREAM)
        val query102 = Query(instance)
        val graph102 = instance.tripleStoreManager!!.getGraph(outputGraph[0])
        val operator102 = graph102.getIterator(query102, arrayOf(AOPVariable(query102, "s"), AOPVariable(query102, "p"), AOPVariable(query102, "o")), EIndexPatternExt.SPO)
        val actual102 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator102, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected102 = MemoryTable.parseFromAny(outputData[0], outputType[0], Query(instance))!!
        val buf_err102 = MyPrintWriter()
        if (!expected102.equalsVerbose(actual102, true, true, buf_err102)) {
            fail(expected102.toString() + " .. " + actual102.toString() + " .. " + buf_err102.toString() + " .. " + operator102)
        }
        val query103 = Query(instance)
        val graph103 = instance.tripleStoreManager!!.getGraph(outputGraph[1])
        val operator103 = graph103.getIterator(query103, arrayOf(AOPVariable(query103, "s"), AOPVariable(query103, "p"), AOPVariable(query103, "o")), EIndexPatternExt.SPO)
        val actual103 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator103, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected103 = MemoryTable.parseFromAny(outputData[1], outputType[1], Query(instance))!!
        val buf_err103 = MyPrintWriter()
        if (!expected103.equalsVerbose(actual103, true, true, buf_err103)) {
            fail(expected103.toString() + " .. " + actual103.toString() + " .. " + buf_err103.toString() + " .. " + operator103)
        }
        val query104 = Query(instance)
        val graph104 = instance.tripleStoreManager!!.getGraph(outputGraph[2])
        val operator104 = graph104.getIterator(query104, arrayOf(AOPVariable(query104, "s"), AOPVariable(query104, "p"), AOPVariable(query104, "o")), EIndexPatternExt.SPO)
        val actual104 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator104, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected104 = MemoryTable.parseFromAny(outputData[2], outputType[2], Query(instance))!!
        val buf_err104 = MyPrintWriter()
        if (!expected104.equalsVerbose(actual104, true, true, buf_err104)) {
            fail(expected104.toString() + " .. " + actual104.toString() + " .. " + buf_err104.toString() + " .. " + operator104)
        }
      }finally{
        LuposdateEndpoint.close(instance)
      }
    }
    @Test(timeout = 2000)
    public fun `Simple DELETE 4 USING - Thread - PartitionByID_1_AllCollations - true`() {
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
        if (listOf(".n3", ".ttl", ".nt").contains(inputType[2])) {
            LuposdateEndpoint.importTurtleString(instance, inputData[2], inputGraph[2])
        } else {
            TODO()
        }
        val query105 = Query(instance)
        val graph105 = instance.tripleStoreManager!!.getGraph(inputGraph[0])
        val operator105 = graph105.getIterator(query105, arrayOf(AOPVariable(query105, "s"), AOPVariable(query105, "p"), AOPVariable(query105, "o")), EIndexPatternExt.SPO)
        val actual105 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator105, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected105 = MemoryTable.parseFromAny(inputData[0], inputType[0], Query(instance))!!
        val buf_err105 = MyPrintWriter()
        if (!expected105.equalsVerbose(actual105, true, true, buf_err105)) {
            fail(expected105.toString() + " .. " + actual105.toString() + " .. " + buf_err105.toString() + " .. " + operator105)
        }
        val query106 = Query(instance)
        val graph106 = instance.tripleStoreManager!!.getGraph(inputGraph[1])
        val operator106 = graph106.getIterator(query106, arrayOf(AOPVariable(query106, "s"), AOPVariable(query106, "p"), AOPVariable(query106, "o")), EIndexPatternExt.SPO)
        val actual106 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator106, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected106 = MemoryTable.parseFromAny(inputData[1], inputType[1], Query(instance))!!
        val buf_err106 = MyPrintWriter()
        if (!expected106.equalsVerbose(actual106, true, true, buf_err106)) {
            fail(expected106.toString() + " .. " + actual106.toString() + " .. " + buf_err106.toString() + " .. " + operator106)
        }
        val query107 = Query(instance)
        val graph107 = instance.tripleStoreManager!!.getGraph(inputGraph[2])
        val operator107 = graph107.getIterator(query107, arrayOf(AOPVariable(query107, "s"), AOPVariable(query107, "p"), AOPVariable(query107, "o")), EIndexPatternExt.SPO)
        val actual107 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator107, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected107 = MemoryTable.parseFromAny(inputData[2], inputType[2], Query(instance))!!
        val buf_err107 = MyPrintWriter()
        if (!expected107.equalsVerbose(actual107, true, true, buf_err107)) {
            fail(expected107.toString() + " .. " + actual107.toString() + " .. " + buf_err107.toString() + " .. " + operator107)
        }
        val operator108 = LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, query)
        LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator108, buf, EQueryResultToStreamExt.EMPTY_STREAM)
        val query109 = Query(instance)
        val graph109 = instance.tripleStoreManager!!.getGraph(outputGraph[0])
        val operator109 = graph109.getIterator(query109, arrayOf(AOPVariable(query109, "s"), AOPVariable(query109, "p"), AOPVariable(query109, "o")), EIndexPatternExt.SPO)
        val actual109 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator109, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected109 = MemoryTable.parseFromAny(outputData[0], outputType[0], Query(instance))!!
        val buf_err109 = MyPrintWriter()
        if (!expected109.equalsVerbose(actual109, true, true, buf_err109)) {
            fail(expected109.toString() + " .. " + actual109.toString() + " .. " + buf_err109.toString() + " .. " + operator109)
        }
        val query110 = Query(instance)
        val graph110 = instance.tripleStoreManager!!.getGraph(outputGraph[1])
        val operator110 = graph110.getIterator(query110, arrayOf(AOPVariable(query110, "s"), AOPVariable(query110, "p"), AOPVariable(query110, "o")), EIndexPatternExt.SPO)
        val actual110 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator110, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected110 = MemoryTable.parseFromAny(outputData[1], outputType[1], Query(instance))!!
        val buf_err110 = MyPrintWriter()
        if (!expected110.equalsVerbose(actual110, true, true, buf_err110)) {
            fail(expected110.toString() + " .. " + actual110.toString() + " .. " + buf_err110.toString() + " .. " + operator110)
        }
        val query111 = Query(instance)
        val graph111 = instance.tripleStoreManager!!.getGraph(outputGraph[2])
        val operator111 = graph111.getIterator(query111, arrayOf(AOPVariable(query111, "s"), AOPVariable(query111, "p"), AOPVariable(query111, "o")), EIndexPatternExt.SPO)
        val actual111 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator111, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected111 = MemoryTable.parseFromAny(outputData[2], outputType[2], Query(instance))!!
        val buf_err111 = MyPrintWriter()
        if (!expected111.equalsVerbose(actual111, true, true, buf_err111)) {
            fail(expected111.toString() + " .. " + actual111.toString() + " .. " + buf_err111.toString() + " .. " + operator111)
        }
      }finally{
        LuposdateEndpoint.close(instance)
      }
    }
    @Test(timeout = 2000)
    public fun `Simple DELETE 4 USING - Thread - PartitionByID_1_AllCollations - false`() {
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
        if (listOf(".n3", ".ttl", ".nt").contains(inputType[2])) {
            LuposdateEndpoint.importTurtleString(instance, inputData[2], inputGraph[2])
        } else {
            TODO()
        }
        val query112 = Query(instance)
        val graph112 = instance.tripleStoreManager!!.getGraph(inputGraph[0])
        val operator112 = graph112.getIterator(query112, arrayOf(AOPVariable(query112, "s"), AOPVariable(query112, "p"), AOPVariable(query112, "o")), EIndexPatternExt.SPO)
        val actual112 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator112, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected112 = MemoryTable.parseFromAny(inputData[0], inputType[0], Query(instance))!!
        val buf_err112 = MyPrintWriter()
        if (!expected112.equalsVerbose(actual112, true, true, buf_err112)) {
            fail(expected112.toString() + " .. " + actual112.toString() + " .. " + buf_err112.toString() + " .. " + operator112)
        }
        val query113 = Query(instance)
        val graph113 = instance.tripleStoreManager!!.getGraph(inputGraph[1])
        val operator113 = graph113.getIterator(query113, arrayOf(AOPVariable(query113, "s"), AOPVariable(query113, "p"), AOPVariable(query113, "o")), EIndexPatternExt.SPO)
        val actual113 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator113, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected113 = MemoryTable.parseFromAny(inputData[1], inputType[1], Query(instance))!!
        val buf_err113 = MyPrintWriter()
        if (!expected113.equalsVerbose(actual113, true, true, buf_err113)) {
            fail(expected113.toString() + " .. " + actual113.toString() + " .. " + buf_err113.toString() + " .. " + operator113)
        }
        val query114 = Query(instance)
        val graph114 = instance.tripleStoreManager!!.getGraph(inputGraph[2])
        val operator114 = graph114.getIterator(query114, arrayOf(AOPVariable(query114, "s"), AOPVariable(query114, "p"), AOPVariable(query114, "o")), EIndexPatternExt.SPO)
        val actual114 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator114, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected114 = MemoryTable.parseFromAny(inputData[2], inputType[2], Query(instance))!!
        val buf_err114 = MyPrintWriter()
        if (!expected114.equalsVerbose(actual114, true, true, buf_err114)) {
            fail(expected114.toString() + " .. " + actual114.toString() + " .. " + buf_err114.toString() + " .. " + operator114)
        }
        val operator115 = LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, query)
        LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator115, buf, EQueryResultToStreamExt.EMPTY_STREAM)
        val query116 = Query(instance)
        val graph116 = instance.tripleStoreManager!!.getGraph(outputGraph[0])
        val operator116 = graph116.getIterator(query116, arrayOf(AOPVariable(query116, "s"), AOPVariable(query116, "p"), AOPVariable(query116, "o")), EIndexPatternExt.SPO)
        val actual116 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator116, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected116 = MemoryTable.parseFromAny(outputData[0], outputType[0], Query(instance))!!
        val buf_err116 = MyPrintWriter()
        if (!expected116.equalsVerbose(actual116, true, true, buf_err116)) {
            fail(expected116.toString() + " .. " + actual116.toString() + " .. " + buf_err116.toString() + " .. " + operator116)
        }
        val query117 = Query(instance)
        val graph117 = instance.tripleStoreManager!!.getGraph(outputGraph[1])
        val operator117 = graph117.getIterator(query117, arrayOf(AOPVariable(query117, "s"), AOPVariable(query117, "p"), AOPVariable(query117, "o")), EIndexPatternExt.SPO)
        val actual117 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator117, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected117 = MemoryTable.parseFromAny(outputData[1], outputType[1], Query(instance))!!
        val buf_err117 = MyPrintWriter()
        if (!expected117.equalsVerbose(actual117, true, true, buf_err117)) {
            fail(expected117.toString() + " .. " + actual117.toString() + " .. " + buf_err117.toString() + " .. " + operator117)
        }
        val query118 = Query(instance)
        val graph118 = instance.tripleStoreManager!!.getGraph(outputGraph[2])
        val operator118 = graph118.getIterator(query118, arrayOf(AOPVariable(query118, "s"), AOPVariable(query118, "p"), AOPVariable(query118, "o")), EIndexPatternExt.SPO)
        val actual118 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator118, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected118 = MemoryTable.parseFromAny(outputData[2], outputType[2], Query(instance))!!
        val buf_err118 = MyPrintWriter()
        if (!expected118.equalsVerbose(actual118, true, true, buf_err118)) {
            fail(expected118.toString() + " .. " + actual118.toString() + " .. " + buf_err118.toString() + " .. " + operator118)
        }
      }finally{
        LuposdateEndpoint.close(instance)
      }
    }
    @Test(timeout = 2000)
    public fun `Simple DELETE 4 USING - Thread - PartitionByID_2_AllCollations - true`() {
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
        if (listOf(".n3", ".ttl", ".nt").contains(inputType[2])) {
            LuposdateEndpoint.importTurtleString(instance, inputData[2], inputGraph[2])
        } else {
            TODO()
        }
        val query119 = Query(instance)
        val graph119 = instance.tripleStoreManager!!.getGraph(inputGraph[0])
        val operator119 = graph119.getIterator(query119, arrayOf(AOPVariable(query119, "s"), AOPVariable(query119, "p"), AOPVariable(query119, "o")), EIndexPatternExt.SPO)
        val actual119 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator119, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected119 = MemoryTable.parseFromAny(inputData[0], inputType[0], Query(instance))!!
        val buf_err119 = MyPrintWriter()
        if (!expected119.equalsVerbose(actual119, true, true, buf_err119)) {
            fail(expected119.toString() + " .. " + actual119.toString() + " .. " + buf_err119.toString() + " .. " + operator119)
        }
        val query120 = Query(instance)
        val graph120 = instance.tripleStoreManager!!.getGraph(inputGraph[1])
        val operator120 = graph120.getIterator(query120, arrayOf(AOPVariable(query120, "s"), AOPVariable(query120, "p"), AOPVariable(query120, "o")), EIndexPatternExt.SPO)
        val actual120 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator120, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected120 = MemoryTable.parseFromAny(inputData[1], inputType[1], Query(instance))!!
        val buf_err120 = MyPrintWriter()
        if (!expected120.equalsVerbose(actual120, true, true, buf_err120)) {
            fail(expected120.toString() + " .. " + actual120.toString() + " .. " + buf_err120.toString() + " .. " + operator120)
        }
        val query121 = Query(instance)
        val graph121 = instance.tripleStoreManager!!.getGraph(inputGraph[2])
        val operator121 = graph121.getIterator(query121, arrayOf(AOPVariable(query121, "s"), AOPVariable(query121, "p"), AOPVariable(query121, "o")), EIndexPatternExt.SPO)
        val actual121 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator121, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected121 = MemoryTable.parseFromAny(inputData[2], inputType[2], Query(instance))!!
        val buf_err121 = MyPrintWriter()
        if (!expected121.equalsVerbose(actual121, true, true, buf_err121)) {
            fail(expected121.toString() + " .. " + actual121.toString() + " .. " + buf_err121.toString() + " .. " + operator121)
        }
        val operator122 = LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, query)
        LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator122, buf, EQueryResultToStreamExt.EMPTY_STREAM)
        val query123 = Query(instance)
        val graph123 = instance.tripleStoreManager!!.getGraph(outputGraph[0])
        val operator123 = graph123.getIterator(query123, arrayOf(AOPVariable(query123, "s"), AOPVariable(query123, "p"), AOPVariable(query123, "o")), EIndexPatternExt.SPO)
        val actual123 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator123, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected123 = MemoryTable.parseFromAny(outputData[0], outputType[0], Query(instance))!!
        val buf_err123 = MyPrintWriter()
        if (!expected123.equalsVerbose(actual123, true, true, buf_err123)) {
            fail(expected123.toString() + " .. " + actual123.toString() + " .. " + buf_err123.toString() + " .. " + operator123)
        }
        val query124 = Query(instance)
        val graph124 = instance.tripleStoreManager!!.getGraph(outputGraph[1])
        val operator124 = graph124.getIterator(query124, arrayOf(AOPVariable(query124, "s"), AOPVariable(query124, "p"), AOPVariable(query124, "o")), EIndexPatternExt.SPO)
        val actual124 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator124, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected124 = MemoryTable.parseFromAny(outputData[1], outputType[1], Query(instance))!!
        val buf_err124 = MyPrintWriter()
        if (!expected124.equalsVerbose(actual124, true, true, buf_err124)) {
            fail(expected124.toString() + " .. " + actual124.toString() + " .. " + buf_err124.toString() + " .. " + operator124)
        }
        val query125 = Query(instance)
        val graph125 = instance.tripleStoreManager!!.getGraph(outputGraph[2])
        val operator125 = graph125.getIterator(query125, arrayOf(AOPVariable(query125, "s"), AOPVariable(query125, "p"), AOPVariable(query125, "o")), EIndexPatternExt.SPO)
        val actual125 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator125, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected125 = MemoryTable.parseFromAny(outputData[2], outputType[2], Query(instance))!!
        val buf_err125 = MyPrintWriter()
        if (!expected125.equalsVerbose(actual125, true, true, buf_err125)) {
            fail(expected125.toString() + " .. " + actual125.toString() + " .. " + buf_err125.toString() + " .. " + operator125)
        }
      }finally{
        LuposdateEndpoint.close(instance)
      }
    }
    @Test(timeout = 2000)
    public fun `Simple DELETE 4 USING - Thread - PartitionByID_2_AllCollations - false`() {
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
        if (listOf(".n3", ".ttl", ".nt").contains(inputType[2])) {
            LuposdateEndpoint.importTurtleString(instance, inputData[2], inputGraph[2])
        } else {
            TODO()
        }
        val query126 = Query(instance)
        val graph126 = instance.tripleStoreManager!!.getGraph(inputGraph[0])
        val operator126 = graph126.getIterator(query126, arrayOf(AOPVariable(query126, "s"), AOPVariable(query126, "p"), AOPVariable(query126, "o")), EIndexPatternExt.SPO)
        val actual126 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator126, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected126 = MemoryTable.parseFromAny(inputData[0], inputType[0], Query(instance))!!
        val buf_err126 = MyPrintWriter()
        if (!expected126.equalsVerbose(actual126, true, true, buf_err126)) {
            fail(expected126.toString() + " .. " + actual126.toString() + " .. " + buf_err126.toString() + " .. " + operator126)
        }
        val query127 = Query(instance)
        val graph127 = instance.tripleStoreManager!!.getGraph(inputGraph[1])
        val operator127 = graph127.getIterator(query127, arrayOf(AOPVariable(query127, "s"), AOPVariable(query127, "p"), AOPVariable(query127, "o")), EIndexPatternExt.SPO)
        val actual127 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator127, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected127 = MemoryTable.parseFromAny(inputData[1], inputType[1], Query(instance))!!
        val buf_err127 = MyPrintWriter()
        if (!expected127.equalsVerbose(actual127, true, true, buf_err127)) {
            fail(expected127.toString() + " .. " + actual127.toString() + " .. " + buf_err127.toString() + " .. " + operator127)
        }
        val query128 = Query(instance)
        val graph128 = instance.tripleStoreManager!!.getGraph(inputGraph[2])
        val operator128 = graph128.getIterator(query128, arrayOf(AOPVariable(query128, "s"), AOPVariable(query128, "p"), AOPVariable(query128, "o")), EIndexPatternExt.SPO)
        val actual128 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator128, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected128 = MemoryTable.parseFromAny(inputData[2], inputType[2], Query(instance))!!
        val buf_err128 = MyPrintWriter()
        if (!expected128.equalsVerbose(actual128, true, true, buf_err128)) {
            fail(expected128.toString() + " .. " + actual128.toString() + " .. " + buf_err128.toString() + " .. " + operator128)
        }
        val operator129 = LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, query)
        LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator129, buf, EQueryResultToStreamExt.EMPTY_STREAM)
        val query130 = Query(instance)
        val graph130 = instance.tripleStoreManager!!.getGraph(outputGraph[0])
        val operator130 = graph130.getIterator(query130, arrayOf(AOPVariable(query130, "s"), AOPVariable(query130, "p"), AOPVariable(query130, "o")), EIndexPatternExt.SPO)
        val actual130 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator130, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected130 = MemoryTable.parseFromAny(outputData[0], outputType[0], Query(instance))!!
        val buf_err130 = MyPrintWriter()
        if (!expected130.equalsVerbose(actual130, true, true, buf_err130)) {
            fail(expected130.toString() + " .. " + actual130.toString() + " .. " + buf_err130.toString() + " .. " + operator130)
        }
        val query131 = Query(instance)
        val graph131 = instance.tripleStoreManager!!.getGraph(outputGraph[1])
        val operator131 = graph131.getIterator(query131, arrayOf(AOPVariable(query131, "s"), AOPVariable(query131, "p"), AOPVariable(query131, "o")), EIndexPatternExt.SPO)
        val actual131 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator131, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected131 = MemoryTable.parseFromAny(outputData[1], outputType[1], Query(instance))!!
        val buf_err131 = MyPrintWriter()
        if (!expected131.equalsVerbose(actual131, true, true, buf_err131)) {
            fail(expected131.toString() + " .. " + actual131.toString() + " .. " + buf_err131.toString() + " .. " + operator131)
        }
        val query132 = Query(instance)
        val graph132 = instance.tripleStoreManager!!.getGraph(outputGraph[2])
        val operator132 = graph132.getIterator(query132, arrayOf(AOPVariable(query132, "s"), AOPVariable(query132, "p"), AOPVariable(query132, "o")), EIndexPatternExt.SPO)
        val actual132 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator132, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected132 = MemoryTable.parseFromAny(outputData[2], outputType[2], Query(instance))!!
        val buf_err132 = MyPrintWriter()
        if (!expected132.equalsVerbose(actual132, true, true, buf_err132)) {
            fail(expected132.toString() + " .. " + actual132.toString() + " .. " + buf_err132.toString() + " .. " + operator132)
        }
      }finally{
        LuposdateEndpoint.close(instance)
      }
    }
    @Test(timeout = 2000)
    public fun `Simple DELETE 4 USING - Thread - PartitionByID_O_AllCollations - true`() {
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
        if (listOf(".n3", ".ttl", ".nt").contains(inputType[2])) {
            LuposdateEndpoint.importTurtleString(instance, inputData[2], inputGraph[2])
        } else {
            TODO()
        }
        val query133 = Query(instance)
        val graph133 = instance.tripleStoreManager!!.getGraph(inputGraph[0])
        val operator133 = graph133.getIterator(query133, arrayOf(AOPVariable(query133, "s"), AOPVariable(query133, "p"), AOPVariable(query133, "o")), EIndexPatternExt.SPO)
        val actual133 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator133, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected133 = MemoryTable.parseFromAny(inputData[0], inputType[0], Query(instance))!!
        val buf_err133 = MyPrintWriter()
        if (!expected133.equalsVerbose(actual133, true, true, buf_err133)) {
            fail(expected133.toString() + " .. " + actual133.toString() + " .. " + buf_err133.toString() + " .. " + operator133)
        }
        val query134 = Query(instance)
        val graph134 = instance.tripleStoreManager!!.getGraph(inputGraph[1])
        val operator134 = graph134.getIterator(query134, arrayOf(AOPVariable(query134, "s"), AOPVariable(query134, "p"), AOPVariable(query134, "o")), EIndexPatternExt.SPO)
        val actual134 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator134, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected134 = MemoryTable.parseFromAny(inputData[1], inputType[1], Query(instance))!!
        val buf_err134 = MyPrintWriter()
        if (!expected134.equalsVerbose(actual134, true, true, buf_err134)) {
            fail(expected134.toString() + " .. " + actual134.toString() + " .. " + buf_err134.toString() + " .. " + operator134)
        }
        val query135 = Query(instance)
        val graph135 = instance.tripleStoreManager!!.getGraph(inputGraph[2])
        val operator135 = graph135.getIterator(query135, arrayOf(AOPVariable(query135, "s"), AOPVariable(query135, "p"), AOPVariable(query135, "o")), EIndexPatternExt.SPO)
        val actual135 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator135, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected135 = MemoryTable.parseFromAny(inputData[2], inputType[2], Query(instance))!!
        val buf_err135 = MyPrintWriter()
        if (!expected135.equalsVerbose(actual135, true, true, buf_err135)) {
            fail(expected135.toString() + " .. " + actual135.toString() + " .. " + buf_err135.toString() + " .. " + operator135)
        }
        val operator136 = LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, query)
        LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator136, buf, EQueryResultToStreamExt.EMPTY_STREAM)
        val query137 = Query(instance)
        val graph137 = instance.tripleStoreManager!!.getGraph(outputGraph[0])
        val operator137 = graph137.getIterator(query137, arrayOf(AOPVariable(query137, "s"), AOPVariable(query137, "p"), AOPVariable(query137, "o")), EIndexPatternExt.SPO)
        val actual137 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator137, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected137 = MemoryTable.parseFromAny(outputData[0], outputType[0], Query(instance))!!
        val buf_err137 = MyPrintWriter()
        if (!expected137.equalsVerbose(actual137, true, true, buf_err137)) {
            fail(expected137.toString() + " .. " + actual137.toString() + " .. " + buf_err137.toString() + " .. " + operator137)
        }
        val query138 = Query(instance)
        val graph138 = instance.tripleStoreManager!!.getGraph(outputGraph[1])
        val operator138 = graph138.getIterator(query138, arrayOf(AOPVariable(query138, "s"), AOPVariable(query138, "p"), AOPVariable(query138, "o")), EIndexPatternExt.SPO)
        val actual138 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator138, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected138 = MemoryTable.parseFromAny(outputData[1], outputType[1], Query(instance))!!
        val buf_err138 = MyPrintWriter()
        if (!expected138.equalsVerbose(actual138, true, true, buf_err138)) {
            fail(expected138.toString() + " .. " + actual138.toString() + " .. " + buf_err138.toString() + " .. " + operator138)
        }
        val query139 = Query(instance)
        val graph139 = instance.tripleStoreManager!!.getGraph(outputGraph[2])
        val operator139 = graph139.getIterator(query139, arrayOf(AOPVariable(query139, "s"), AOPVariable(query139, "p"), AOPVariable(query139, "o")), EIndexPatternExt.SPO)
        val actual139 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator139, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected139 = MemoryTable.parseFromAny(outputData[2], outputType[2], Query(instance))!!
        val buf_err139 = MyPrintWriter()
        if (!expected139.equalsVerbose(actual139, true, true, buf_err139)) {
            fail(expected139.toString() + " .. " + actual139.toString() + " .. " + buf_err139.toString() + " .. " + operator139)
        }
      }finally{
        LuposdateEndpoint.close(instance)
      }
    }
    @Test(timeout = 2000)
    public fun `Simple DELETE 4 USING - Thread - PartitionByID_O_AllCollations - false`() {
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
        if (listOf(".n3", ".ttl", ".nt").contains(inputType[2])) {
            LuposdateEndpoint.importTurtleString(instance, inputData[2], inputGraph[2])
        } else {
            TODO()
        }
        val query140 = Query(instance)
        val graph140 = instance.tripleStoreManager!!.getGraph(inputGraph[0])
        val operator140 = graph140.getIterator(query140, arrayOf(AOPVariable(query140, "s"), AOPVariable(query140, "p"), AOPVariable(query140, "o")), EIndexPatternExt.SPO)
        val actual140 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator140, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected140 = MemoryTable.parseFromAny(inputData[0], inputType[0], Query(instance))!!
        val buf_err140 = MyPrintWriter()
        if (!expected140.equalsVerbose(actual140, true, true, buf_err140)) {
            fail(expected140.toString() + " .. " + actual140.toString() + " .. " + buf_err140.toString() + " .. " + operator140)
        }
        val query141 = Query(instance)
        val graph141 = instance.tripleStoreManager!!.getGraph(inputGraph[1])
        val operator141 = graph141.getIterator(query141, arrayOf(AOPVariable(query141, "s"), AOPVariable(query141, "p"), AOPVariable(query141, "o")), EIndexPatternExt.SPO)
        val actual141 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator141, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected141 = MemoryTable.parseFromAny(inputData[1], inputType[1], Query(instance))!!
        val buf_err141 = MyPrintWriter()
        if (!expected141.equalsVerbose(actual141, true, true, buf_err141)) {
            fail(expected141.toString() + " .. " + actual141.toString() + " .. " + buf_err141.toString() + " .. " + operator141)
        }
        val query142 = Query(instance)
        val graph142 = instance.tripleStoreManager!!.getGraph(inputGraph[2])
        val operator142 = graph142.getIterator(query142, arrayOf(AOPVariable(query142, "s"), AOPVariable(query142, "p"), AOPVariable(query142, "o")), EIndexPatternExt.SPO)
        val actual142 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator142, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected142 = MemoryTable.parseFromAny(inputData[2], inputType[2], Query(instance))!!
        val buf_err142 = MyPrintWriter()
        if (!expected142.equalsVerbose(actual142, true, true, buf_err142)) {
            fail(expected142.toString() + " .. " + actual142.toString() + " .. " + buf_err142.toString() + " .. " + operator142)
        }
        val operator143 = LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, query)
        LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator143, buf, EQueryResultToStreamExt.EMPTY_STREAM)
        val query144 = Query(instance)
        val graph144 = instance.tripleStoreManager!!.getGraph(outputGraph[0])
        val operator144 = graph144.getIterator(query144, arrayOf(AOPVariable(query144, "s"), AOPVariable(query144, "p"), AOPVariable(query144, "o")), EIndexPatternExt.SPO)
        val actual144 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator144, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected144 = MemoryTable.parseFromAny(outputData[0], outputType[0], Query(instance))!!
        val buf_err144 = MyPrintWriter()
        if (!expected144.equalsVerbose(actual144, true, true, buf_err144)) {
            fail(expected144.toString() + " .. " + actual144.toString() + " .. " + buf_err144.toString() + " .. " + operator144)
        }
        val query145 = Query(instance)
        val graph145 = instance.tripleStoreManager!!.getGraph(outputGraph[1])
        val operator145 = graph145.getIterator(query145, arrayOf(AOPVariable(query145, "s"), AOPVariable(query145, "p"), AOPVariable(query145, "o")), EIndexPatternExt.SPO)
        val actual145 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator145, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected145 = MemoryTable.parseFromAny(outputData[1], outputType[1], Query(instance))!!
        val buf_err145 = MyPrintWriter()
        if (!expected145.equalsVerbose(actual145, true, true, buf_err145)) {
            fail(expected145.toString() + " .. " + actual145.toString() + " .. " + buf_err145.toString() + " .. " + operator145)
        }
        val query146 = Query(instance)
        val graph146 = instance.tripleStoreManager!!.getGraph(outputGraph[2])
        val operator146 = graph146.getIterator(query146, arrayOf(AOPVariable(query146, "s"), AOPVariable(query146, "p"), AOPVariable(query146, "o")), EIndexPatternExt.SPO)
        val actual146 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator146, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected146 = MemoryTable.parseFromAny(outputData[2], outputType[2], Query(instance))!!
        val buf_err146 = MyPrintWriter()
        if (!expected146.equalsVerbose(actual146, true, true, buf_err146)) {
            fail(expected146.toString() + " .. " + actual146.toString() + " .. " + buf_err146.toString() + " .. " + operator146)
        }
      }finally{
        LuposdateEndpoint.close(instance)
      }
    }
    @Test(timeout = 2000)
    public fun `Simple DELETE 4 USING - Thread - PartitionByID_S_AllCollations - true`() {
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
        if (listOf(".n3", ".ttl", ".nt").contains(inputType[2])) {
            LuposdateEndpoint.importTurtleString(instance, inputData[2], inputGraph[2])
        } else {
            TODO()
        }
        val query147 = Query(instance)
        val graph147 = instance.tripleStoreManager!!.getGraph(inputGraph[0])
        val operator147 = graph147.getIterator(query147, arrayOf(AOPVariable(query147, "s"), AOPVariable(query147, "p"), AOPVariable(query147, "o")), EIndexPatternExt.SPO)
        val actual147 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator147, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected147 = MemoryTable.parseFromAny(inputData[0], inputType[0], Query(instance))!!
        val buf_err147 = MyPrintWriter()
        if (!expected147.equalsVerbose(actual147, true, true, buf_err147)) {
            fail(expected147.toString() + " .. " + actual147.toString() + " .. " + buf_err147.toString() + " .. " + operator147)
        }
        val query148 = Query(instance)
        val graph148 = instance.tripleStoreManager!!.getGraph(inputGraph[1])
        val operator148 = graph148.getIterator(query148, arrayOf(AOPVariable(query148, "s"), AOPVariable(query148, "p"), AOPVariable(query148, "o")), EIndexPatternExt.SPO)
        val actual148 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator148, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected148 = MemoryTable.parseFromAny(inputData[1], inputType[1], Query(instance))!!
        val buf_err148 = MyPrintWriter()
        if (!expected148.equalsVerbose(actual148, true, true, buf_err148)) {
            fail(expected148.toString() + " .. " + actual148.toString() + " .. " + buf_err148.toString() + " .. " + operator148)
        }
        val query149 = Query(instance)
        val graph149 = instance.tripleStoreManager!!.getGraph(inputGraph[2])
        val operator149 = graph149.getIterator(query149, arrayOf(AOPVariable(query149, "s"), AOPVariable(query149, "p"), AOPVariable(query149, "o")), EIndexPatternExt.SPO)
        val actual149 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator149, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected149 = MemoryTable.parseFromAny(inputData[2], inputType[2], Query(instance))!!
        val buf_err149 = MyPrintWriter()
        if (!expected149.equalsVerbose(actual149, true, true, buf_err149)) {
            fail(expected149.toString() + " .. " + actual149.toString() + " .. " + buf_err149.toString() + " .. " + operator149)
        }
        val operator150 = LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, query)
        LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator150, buf, EQueryResultToStreamExt.EMPTY_STREAM)
        val query151 = Query(instance)
        val graph151 = instance.tripleStoreManager!!.getGraph(outputGraph[0])
        val operator151 = graph151.getIterator(query151, arrayOf(AOPVariable(query151, "s"), AOPVariable(query151, "p"), AOPVariable(query151, "o")), EIndexPatternExt.SPO)
        val actual151 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator151, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected151 = MemoryTable.parseFromAny(outputData[0], outputType[0], Query(instance))!!
        val buf_err151 = MyPrintWriter()
        if (!expected151.equalsVerbose(actual151, true, true, buf_err151)) {
            fail(expected151.toString() + " .. " + actual151.toString() + " .. " + buf_err151.toString() + " .. " + operator151)
        }
        val query152 = Query(instance)
        val graph152 = instance.tripleStoreManager!!.getGraph(outputGraph[1])
        val operator152 = graph152.getIterator(query152, arrayOf(AOPVariable(query152, "s"), AOPVariable(query152, "p"), AOPVariable(query152, "o")), EIndexPatternExt.SPO)
        val actual152 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator152, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected152 = MemoryTable.parseFromAny(outputData[1], outputType[1], Query(instance))!!
        val buf_err152 = MyPrintWriter()
        if (!expected152.equalsVerbose(actual152, true, true, buf_err152)) {
            fail(expected152.toString() + " .. " + actual152.toString() + " .. " + buf_err152.toString() + " .. " + operator152)
        }
        val query153 = Query(instance)
        val graph153 = instance.tripleStoreManager!!.getGraph(outputGraph[2])
        val operator153 = graph153.getIterator(query153, arrayOf(AOPVariable(query153, "s"), AOPVariable(query153, "p"), AOPVariable(query153, "o")), EIndexPatternExt.SPO)
        val actual153 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator153, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected153 = MemoryTable.parseFromAny(outputData[2], outputType[2], Query(instance))!!
        val buf_err153 = MyPrintWriter()
        if (!expected153.equalsVerbose(actual153, true, true, buf_err153)) {
            fail(expected153.toString() + " .. " + actual153.toString() + " .. " + buf_err153.toString() + " .. " + operator153)
        }
      }finally{
        LuposdateEndpoint.close(instance)
      }
    }
    @Test(timeout = 2000)
    public fun `Simple DELETE 4 USING - Thread - PartitionByID_S_AllCollations - false`() {
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
        if (listOf(".n3", ".ttl", ".nt").contains(inputType[2])) {
            LuposdateEndpoint.importTurtleString(instance, inputData[2], inputGraph[2])
        } else {
            TODO()
        }
        val query154 = Query(instance)
        val graph154 = instance.tripleStoreManager!!.getGraph(inputGraph[0])
        val operator154 = graph154.getIterator(query154, arrayOf(AOPVariable(query154, "s"), AOPVariable(query154, "p"), AOPVariable(query154, "o")), EIndexPatternExt.SPO)
        val actual154 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator154, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected154 = MemoryTable.parseFromAny(inputData[0], inputType[0], Query(instance))!!
        val buf_err154 = MyPrintWriter()
        if (!expected154.equalsVerbose(actual154, true, true, buf_err154)) {
            fail(expected154.toString() + " .. " + actual154.toString() + " .. " + buf_err154.toString() + " .. " + operator154)
        }
        val query155 = Query(instance)
        val graph155 = instance.tripleStoreManager!!.getGraph(inputGraph[1])
        val operator155 = graph155.getIterator(query155, arrayOf(AOPVariable(query155, "s"), AOPVariable(query155, "p"), AOPVariable(query155, "o")), EIndexPatternExt.SPO)
        val actual155 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator155, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected155 = MemoryTable.parseFromAny(inputData[1], inputType[1], Query(instance))!!
        val buf_err155 = MyPrintWriter()
        if (!expected155.equalsVerbose(actual155, true, true, buf_err155)) {
            fail(expected155.toString() + " .. " + actual155.toString() + " .. " + buf_err155.toString() + " .. " + operator155)
        }
        val query156 = Query(instance)
        val graph156 = instance.tripleStoreManager!!.getGraph(inputGraph[2])
        val operator156 = graph156.getIterator(query156, arrayOf(AOPVariable(query156, "s"), AOPVariable(query156, "p"), AOPVariable(query156, "o")), EIndexPatternExt.SPO)
        val actual156 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator156, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected156 = MemoryTable.parseFromAny(inputData[2], inputType[2], Query(instance))!!
        val buf_err156 = MyPrintWriter()
        if (!expected156.equalsVerbose(actual156, true, true, buf_err156)) {
            fail(expected156.toString() + " .. " + actual156.toString() + " .. " + buf_err156.toString() + " .. " + operator156)
        }
        val operator157 = LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, query)
        LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator157, buf, EQueryResultToStreamExt.EMPTY_STREAM)
        val query158 = Query(instance)
        val graph158 = instance.tripleStoreManager!!.getGraph(outputGraph[0])
        val operator158 = graph158.getIterator(query158, arrayOf(AOPVariable(query158, "s"), AOPVariable(query158, "p"), AOPVariable(query158, "o")), EIndexPatternExt.SPO)
        val actual158 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator158, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected158 = MemoryTable.parseFromAny(outputData[0], outputType[0], Query(instance))!!
        val buf_err158 = MyPrintWriter()
        if (!expected158.equalsVerbose(actual158, true, true, buf_err158)) {
            fail(expected158.toString() + " .. " + actual158.toString() + " .. " + buf_err158.toString() + " .. " + operator158)
        }
        val query159 = Query(instance)
        val graph159 = instance.tripleStoreManager!!.getGraph(outputGraph[1])
        val operator159 = graph159.getIterator(query159, arrayOf(AOPVariable(query159, "s"), AOPVariable(query159, "p"), AOPVariable(query159, "o")), EIndexPatternExt.SPO)
        val actual159 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator159, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected159 = MemoryTable.parseFromAny(outputData[1], outputType[1], Query(instance))!!
        val buf_err159 = MyPrintWriter()
        if (!expected159.equalsVerbose(actual159, true, true, buf_err159)) {
            fail(expected159.toString() + " .. " + actual159.toString() + " .. " + buf_err159.toString() + " .. " + operator159)
        }
        val query160 = Query(instance)
        val graph160 = instance.tripleStoreManager!!.getGraph(outputGraph[2])
        val operator160 = graph160.getIterator(query160, arrayOf(AOPVariable(query160, "s"), AOPVariable(query160, "p"), AOPVariable(query160, "o")), EIndexPatternExt.SPO)
        val actual160 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator160, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected160 = MemoryTable.parseFromAny(outputData[2], outputType[2], Query(instance))!!
        val buf_err160 = MyPrintWriter()
        if (!expected160.equalsVerbose(actual160, true, true, buf_err160)) {
            fail(expected160.toString() + " .. " + actual160.toString() + " .. " + buf_err160.toString() + " .. " + operator160)
        }
      }finally{
        LuposdateEndpoint.close(instance)
      }
    }
    @Test(timeout = 2000)
    public fun `Simple DELETE 4 USING - Thread - PartitionByKeyAllCollations - true`() {
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
        if (listOf(".n3", ".ttl", ".nt").contains(inputType[2])) {
            LuposdateEndpoint.importTurtleString(instance, inputData[2], inputGraph[2])
        } else {
            TODO()
        }
        val query161 = Query(instance)
        val graph161 = instance.tripleStoreManager!!.getGraph(inputGraph[0])
        val operator161 = graph161.getIterator(query161, arrayOf(AOPVariable(query161, "s"), AOPVariable(query161, "p"), AOPVariable(query161, "o")), EIndexPatternExt.SPO)
        val actual161 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator161, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected161 = MemoryTable.parseFromAny(inputData[0], inputType[0], Query(instance))!!
        val buf_err161 = MyPrintWriter()
        if (!expected161.equalsVerbose(actual161, true, true, buf_err161)) {
            fail(expected161.toString() + " .. " + actual161.toString() + " .. " + buf_err161.toString() + " .. " + operator161)
        }
        val query162 = Query(instance)
        val graph162 = instance.tripleStoreManager!!.getGraph(inputGraph[1])
        val operator162 = graph162.getIterator(query162, arrayOf(AOPVariable(query162, "s"), AOPVariable(query162, "p"), AOPVariable(query162, "o")), EIndexPatternExt.SPO)
        val actual162 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator162, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected162 = MemoryTable.parseFromAny(inputData[1], inputType[1], Query(instance))!!
        val buf_err162 = MyPrintWriter()
        if (!expected162.equalsVerbose(actual162, true, true, buf_err162)) {
            fail(expected162.toString() + " .. " + actual162.toString() + " .. " + buf_err162.toString() + " .. " + operator162)
        }
        val query163 = Query(instance)
        val graph163 = instance.tripleStoreManager!!.getGraph(inputGraph[2])
        val operator163 = graph163.getIterator(query163, arrayOf(AOPVariable(query163, "s"), AOPVariable(query163, "p"), AOPVariable(query163, "o")), EIndexPatternExt.SPO)
        val actual163 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator163, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected163 = MemoryTable.parseFromAny(inputData[2], inputType[2], Query(instance))!!
        val buf_err163 = MyPrintWriter()
        if (!expected163.equalsVerbose(actual163, true, true, buf_err163)) {
            fail(expected163.toString() + " .. " + actual163.toString() + " .. " + buf_err163.toString() + " .. " + operator163)
        }
        val operator164 = LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, query)
        LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator164, buf, EQueryResultToStreamExt.EMPTY_STREAM)
        val query165 = Query(instance)
        val graph165 = instance.tripleStoreManager!!.getGraph(outputGraph[0])
        val operator165 = graph165.getIterator(query165, arrayOf(AOPVariable(query165, "s"), AOPVariable(query165, "p"), AOPVariable(query165, "o")), EIndexPatternExt.SPO)
        val actual165 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator165, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected165 = MemoryTable.parseFromAny(outputData[0], outputType[0], Query(instance))!!
        val buf_err165 = MyPrintWriter()
        if (!expected165.equalsVerbose(actual165, true, true, buf_err165)) {
            fail(expected165.toString() + " .. " + actual165.toString() + " .. " + buf_err165.toString() + " .. " + operator165)
        }
        val query166 = Query(instance)
        val graph166 = instance.tripleStoreManager!!.getGraph(outputGraph[1])
        val operator166 = graph166.getIterator(query166, arrayOf(AOPVariable(query166, "s"), AOPVariable(query166, "p"), AOPVariable(query166, "o")), EIndexPatternExt.SPO)
        val actual166 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator166, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected166 = MemoryTable.parseFromAny(outputData[1], outputType[1], Query(instance))!!
        val buf_err166 = MyPrintWriter()
        if (!expected166.equalsVerbose(actual166, true, true, buf_err166)) {
            fail(expected166.toString() + " .. " + actual166.toString() + " .. " + buf_err166.toString() + " .. " + operator166)
        }
        val query167 = Query(instance)
        val graph167 = instance.tripleStoreManager!!.getGraph(outputGraph[2])
        val operator167 = graph167.getIterator(query167, arrayOf(AOPVariable(query167, "s"), AOPVariable(query167, "p"), AOPVariable(query167, "o")), EIndexPatternExt.SPO)
        val actual167 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator167, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected167 = MemoryTable.parseFromAny(outputData[2], outputType[2], Query(instance))!!
        val buf_err167 = MyPrintWriter()
        if (!expected167.equalsVerbose(actual167, true, true, buf_err167)) {
            fail(expected167.toString() + " .. " + actual167.toString() + " .. " + buf_err167.toString() + " .. " + operator167)
        }
      }finally{
        LuposdateEndpoint.close(instance)
      }
    }
    @Test(timeout = 2000)
    public fun `Simple DELETE 4 USING - Thread - PartitionByKeyAllCollations - false`() {
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
        if (listOf(".n3", ".ttl", ".nt").contains(inputType[2])) {
            LuposdateEndpoint.importTurtleString(instance, inputData[2], inputGraph[2])
        } else {
            TODO()
        }
        val query168 = Query(instance)
        val graph168 = instance.tripleStoreManager!!.getGraph(inputGraph[0])
        val operator168 = graph168.getIterator(query168, arrayOf(AOPVariable(query168, "s"), AOPVariable(query168, "p"), AOPVariable(query168, "o")), EIndexPatternExt.SPO)
        val actual168 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator168, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected168 = MemoryTable.parseFromAny(inputData[0], inputType[0], Query(instance))!!
        val buf_err168 = MyPrintWriter()
        if (!expected168.equalsVerbose(actual168, true, true, buf_err168)) {
            fail(expected168.toString() + " .. " + actual168.toString() + " .. " + buf_err168.toString() + " .. " + operator168)
        }
        val query169 = Query(instance)
        val graph169 = instance.tripleStoreManager!!.getGraph(inputGraph[1])
        val operator169 = graph169.getIterator(query169, arrayOf(AOPVariable(query169, "s"), AOPVariable(query169, "p"), AOPVariable(query169, "o")), EIndexPatternExt.SPO)
        val actual169 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator169, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected169 = MemoryTable.parseFromAny(inputData[1], inputType[1], Query(instance))!!
        val buf_err169 = MyPrintWriter()
        if (!expected169.equalsVerbose(actual169, true, true, buf_err169)) {
            fail(expected169.toString() + " .. " + actual169.toString() + " .. " + buf_err169.toString() + " .. " + operator169)
        }
        val query170 = Query(instance)
        val graph170 = instance.tripleStoreManager!!.getGraph(inputGraph[2])
        val operator170 = graph170.getIterator(query170, arrayOf(AOPVariable(query170, "s"), AOPVariable(query170, "p"), AOPVariable(query170, "o")), EIndexPatternExt.SPO)
        val actual170 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator170, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected170 = MemoryTable.parseFromAny(inputData[2], inputType[2], Query(instance))!!
        val buf_err170 = MyPrintWriter()
        if (!expected170.equalsVerbose(actual170, true, true, buf_err170)) {
            fail(expected170.toString() + " .. " + actual170.toString() + " .. " + buf_err170.toString() + " .. " + operator170)
        }
        val operator171 = LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, query)
        LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator171, buf, EQueryResultToStreamExt.EMPTY_STREAM)
        val query172 = Query(instance)
        val graph172 = instance.tripleStoreManager!!.getGraph(outputGraph[0])
        val operator172 = graph172.getIterator(query172, arrayOf(AOPVariable(query172, "s"), AOPVariable(query172, "p"), AOPVariable(query172, "o")), EIndexPatternExt.SPO)
        val actual172 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator172, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected172 = MemoryTable.parseFromAny(outputData[0], outputType[0], Query(instance))!!
        val buf_err172 = MyPrintWriter()
        if (!expected172.equalsVerbose(actual172, true, true, buf_err172)) {
            fail(expected172.toString() + " .. " + actual172.toString() + " .. " + buf_err172.toString() + " .. " + operator172)
        }
        val query173 = Query(instance)
        val graph173 = instance.tripleStoreManager!!.getGraph(outputGraph[1])
        val operator173 = graph173.getIterator(query173, arrayOf(AOPVariable(query173, "s"), AOPVariable(query173, "p"), AOPVariable(query173, "o")), EIndexPatternExt.SPO)
        val actual173 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator173, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected173 = MemoryTable.parseFromAny(outputData[1], outputType[1], Query(instance))!!
        val buf_err173 = MyPrintWriter()
        if (!expected173.equalsVerbose(actual173, true, true, buf_err173)) {
            fail(expected173.toString() + " .. " + actual173.toString() + " .. " + buf_err173.toString() + " .. " + operator173)
        }
        val query174 = Query(instance)
        val graph174 = instance.tripleStoreManager!!.getGraph(outputGraph[2])
        val operator174 = graph174.getIterator(query174, arrayOf(AOPVariable(query174, "s"), AOPVariable(query174, "p"), AOPVariable(query174, "o")), EIndexPatternExt.SPO)
        val actual174 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator174, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected174 = MemoryTable.parseFromAny(outputData[2], outputType[2], Query(instance))!!
        val buf_err174 = MyPrintWriter()
        if (!expected174.equalsVerbose(actual174, true, true, buf_err174)) {
            fail(expected174.toString() + " .. " + actual174.toString() + " .. " + buf_err174.toString() + " .. " + operator174)
        }
      }finally{
        LuposdateEndpoint.close(instance)
      }
    }
    @Test(timeout = 2000)
    public fun `Simple DELETE 4 USING - Thread - Simple - true`() {
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
        if (listOf(".n3", ".ttl", ".nt").contains(inputType[2])) {
            LuposdateEndpoint.importTurtleString(instance, inputData[2], inputGraph[2])
        } else {
            TODO()
        }
        val query175 = Query(instance)
        val graph175 = instance.tripleStoreManager!!.getGraph(inputGraph[0])
        val operator175 = graph175.getIterator(query175, arrayOf(AOPVariable(query175, "s"), AOPVariable(query175, "p"), AOPVariable(query175, "o")), EIndexPatternExt.SPO)
        val actual175 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator175, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected175 = MemoryTable.parseFromAny(inputData[0], inputType[0], Query(instance))!!
        val buf_err175 = MyPrintWriter()
        if (!expected175.equalsVerbose(actual175, true, true, buf_err175)) {
            fail(expected175.toString() + " .. " + actual175.toString() + " .. " + buf_err175.toString() + " .. " + operator175)
        }
        val query176 = Query(instance)
        val graph176 = instance.tripleStoreManager!!.getGraph(inputGraph[1])
        val operator176 = graph176.getIterator(query176, arrayOf(AOPVariable(query176, "s"), AOPVariable(query176, "p"), AOPVariable(query176, "o")), EIndexPatternExt.SPO)
        val actual176 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator176, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected176 = MemoryTable.parseFromAny(inputData[1], inputType[1], Query(instance))!!
        val buf_err176 = MyPrintWriter()
        if (!expected176.equalsVerbose(actual176, true, true, buf_err176)) {
            fail(expected176.toString() + " .. " + actual176.toString() + " .. " + buf_err176.toString() + " .. " + operator176)
        }
        val query177 = Query(instance)
        val graph177 = instance.tripleStoreManager!!.getGraph(inputGraph[2])
        val operator177 = graph177.getIterator(query177, arrayOf(AOPVariable(query177, "s"), AOPVariable(query177, "p"), AOPVariable(query177, "o")), EIndexPatternExt.SPO)
        val actual177 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator177, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected177 = MemoryTable.parseFromAny(inputData[2], inputType[2], Query(instance))!!
        val buf_err177 = MyPrintWriter()
        if (!expected177.equalsVerbose(actual177, true, true, buf_err177)) {
            fail(expected177.toString() + " .. " + actual177.toString() + " .. " + buf_err177.toString() + " .. " + operator177)
        }
        val operator178 = LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, query)
        LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator178, buf, EQueryResultToStreamExt.EMPTY_STREAM)
        val query179 = Query(instance)
        val graph179 = instance.tripleStoreManager!!.getGraph(outputGraph[0])
        val operator179 = graph179.getIterator(query179, arrayOf(AOPVariable(query179, "s"), AOPVariable(query179, "p"), AOPVariable(query179, "o")), EIndexPatternExt.SPO)
        val actual179 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator179, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected179 = MemoryTable.parseFromAny(outputData[0], outputType[0], Query(instance))!!
        val buf_err179 = MyPrintWriter()
        if (!expected179.equalsVerbose(actual179, true, true, buf_err179)) {
            fail(expected179.toString() + " .. " + actual179.toString() + " .. " + buf_err179.toString() + " .. " + operator179)
        }
        val query180 = Query(instance)
        val graph180 = instance.tripleStoreManager!!.getGraph(outputGraph[1])
        val operator180 = graph180.getIterator(query180, arrayOf(AOPVariable(query180, "s"), AOPVariable(query180, "p"), AOPVariable(query180, "o")), EIndexPatternExt.SPO)
        val actual180 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator180, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected180 = MemoryTable.parseFromAny(outputData[1], outputType[1], Query(instance))!!
        val buf_err180 = MyPrintWriter()
        if (!expected180.equalsVerbose(actual180, true, true, buf_err180)) {
            fail(expected180.toString() + " .. " + actual180.toString() + " .. " + buf_err180.toString() + " .. " + operator180)
        }
        val query181 = Query(instance)
        val graph181 = instance.tripleStoreManager!!.getGraph(outputGraph[2])
        val operator181 = graph181.getIterator(query181, arrayOf(AOPVariable(query181, "s"), AOPVariable(query181, "p"), AOPVariable(query181, "o")), EIndexPatternExt.SPO)
        val actual181 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator181, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected181 = MemoryTable.parseFromAny(outputData[2], outputType[2], Query(instance))!!
        val buf_err181 = MyPrintWriter()
        if (!expected181.equalsVerbose(actual181, true, true, buf_err181)) {
            fail(expected181.toString() + " .. " + actual181.toString() + " .. " + buf_err181.toString() + " .. " + operator181)
        }
      }finally{
        LuposdateEndpoint.close(instance)
      }
    }
    @Test(timeout = 2000)
    public fun `Simple DELETE 4 USING - Thread - Simple - false`() {
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
        if (listOf(".n3", ".ttl", ".nt").contains(inputType[2])) {
            LuposdateEndpoint.importTurtleString(instance, inputData[2], inputGraph[2])
        } else {
            TODO()
        }
        val query182 = Query(instance)
        val graph182 = instance.tripleStoreManager!!.getGraph(inputGraph[0])
        val operator182 = graph182.getIterator(query182, arrayOf(AOPVariable(query182, "s"), AOPVariable(query182, "p"), AOPVariable(query182, "o")), EIndexPatternExt.SPO)
        val actual182 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator182, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected182 = MemoryTable.parseFromAny(inputData[0], inputType[0], Query(instance))!!
        val buf_err182 = MyPrintWriter()
        if (!expected182.equalsVerbose(actual182, true, true, buf_err182)) {
            fail(expected182.toString() + " .. " + actual182.toString() + " .. " + buf_err182.toString() + " .. " + operator182)
        }
        val query183 = Query(instance)
        val graph183 = instance.tripleStoreManager!!.getGraph(inputGraph[1])
        val operator183 = graph183.getIterator(query183, arrayOf(AOPVariable(query183, "s"), AOPVariable(query183, "p"), AOPVariable(query183, "o")), EIndexPatternExt.SPO)
        val actual183 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator183, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected183 = MemoryTable.parseFromAny(inputData[1], inputType[1], Query(instance))!!
        val buf_err183 = MyPrintWriter()
        if (!expected183.equalsVerbose(actual183, true, true, buf_err183)) {
            fail(expected183.toString() + " .. " + actual183.toString() + " .. " + buf_err183.toString() + " .. " + operator183)
        }
        val query184 = Query(instance)
        val graph184 = instance.tripleStoreManager!!.getGraph(inputGraph[2])
        val operator184 = graph184.getIterator(query184, arrayOf(AOPVariable(query184, "s"), AOPVariable(query184, "p"), AOPVariable(query184, "o")), EIndexPatternExt.SPO)
        val actual184 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator184, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected184 = MemoryTable.parseFromAny(inputData[2], inputType[2], Query(instance))!!
        val buf_err184 = MyPrintWriter()
        if (!expected184.equalsVerbose(actual184, true, true, buf_err184)) {
            fail(expected184.toString() + " .. " + actual184.toString() + " .. " + buf_err184.toString() + " .. " + operator184)
        }
        val operator185 = LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, query)
        LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator185, buf, EQueryResultToStreamExt.EMPTY_STREAM)
        val query186 = Query(instance)
        val graph186 = instance.tripleStoreManager!!.getGraph(outputGraph[0])
        val operator186 = graph186.getIterator(query186, arrayOf(AOPVariable(query186, "s"), AOPVariable(query186, "p"), AOPVariable(query186, "o")), EIndexPatternExt.SPO)
        val actual186 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator186, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected186 = MemoryTable.parseFromAny(outputData[0], outputType[0], Query(instance))!!
        val buf_err186 = MyPrintWriter()
        if (!expected186.equalsVerbose(actual186, true, true, buf_err186)) {
            fail(expected186.toString() + " .. " + actual186.toString() + " .. " + buf_err186.toString() + " .. " + operator186)
        }
        val query187 = Query(instance)
        val graph187 = instance.tripleStoreManager!!.getGraph(outputGraph[1])
        val operator187 = graph187.getIterator(query187, arrayOf(AOPVariable(query187, "s"), AOPVariable(query187, "p"), AOPVariable(query187, "o")), EIndexPatternExt.SPO)
        val actual187 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator187, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected187 = MemoryTable.parseFromAny(outputData[1], outputType[1], Query(instance))!!
        val buf_err187 = MyPrintWriter()
        if (!expected187.equalsVerbose(actual187, true, true, buf_err187)) {
            fail(expected187.toString() + " .. " + actual187.toString() + " .. " + buf_err187.toString() + " .. " + operator187)
        }
        val query188 = Query(instance)
        val graph188 = instance.tripleStoreManager!!.getGraph(outputGraph[2])
        val operator188 = graph188.getIterator(query188, arrayOf(AOPVariable(query188, "s"), AOPVariable(query188, "p"), AOPVariable(query188, "o")), EIndexPatternExt.SPO)
        val actual188 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator188, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected188 = MemoryTable.parseFromAny(outputData[2], outputType[2], Query(instance))!!
        val buf_err188 = MyPrintWriter()
        if (!expected188.equalsVerbose(actual188, true, true, buf_err188)) {
            fail(expected188.toString() + " .. " + actual188.toString() + " .. " + buf_err188.toString() + " .. " + operator188)
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
        val pkg2 = MySimulatorTestingImportPackage(inputData[2], inputGraph[2], inputType[2])
        pkg1.onFinish = pkg2
        var verifyExecuted3 = 0
        val pkg3 = MySimulatorTestingCompareGraphPackage("SELECT ?s ?p ?o WHERE { ?s ?p ?o . }",MemoryTable.parseFromAny(inputData[0], inputType[0], Query(instance))!!, {verifyExecuted3++})
        pkg2.onFinish = pkg3
        var verifyExecuted4 = 0
        val pkg4 = MySimulatorTestingCompareGraphPackage("SELECT ?s ?p ?o WHERE { GRAPH <${inputGraph[1]}> { ?s ?p ?o . }}",MemoryTable.parseFromAny(inputData[1], inputType[1], Query(instance))!!, {verifyExecuted4++})
        pkg3.onFinish = pkg4
        var verifyExecuted5 = 0
        val pkg5 = MySimulatorTestingCompareGraphPackage("SELECT ?s ?p ?o WHERE { GRAPH <${inputGraph[2]}> { ?s ?p ?o . }}",MemoryTable.parseFromAny(inputData[2], inputType[2], Query(instance))!!, {verifyExecuted5++})
        pkg4.onFinish = pkg5
        val pkg6 = MySimulatorTestingExecute(query)
        pkg5.onFinish = pkg6
        var verifyExecuted7 = 0
        val pkg7 = MySimulatorTestingCompareGraphPackage("SELECT ?s ?p ?o WHERE { ?s ?p ?o . }",MemoryTable.parseFromAny(outputData[0], outputType[0], Query(instance))!!, {verifyExecuted7++})
        pkg6.onFinish = pkg7
        var verifyExecuted8 = 0
        val pkg8 = MySimulatorTestingCompareGraphPackage("SELECT ?s ?p ?o WHERE { GRAPH <${outputGraph[1]}> { ?s ?p ?o . }}",MemoryTable.parseFromAny(outputData[1], outputType[1], Query(instance))!!, {verifyExecuted8++})
        pkg7.onFinish = pkg8
        var verifyExecuted9 = 0
        val pkg9 = MySimulatorTestingCompareGraphPackage("SELECT ?s ?p ?o WHERE { GRAPH <${outputGraph[2]}> { ?s ?p ?o . }}",MemoryTable.parseFromAny(outputData[2], outputType[2], Query(instance))!!, {verifyExecuted9++})
        pkg8.onFinish = pkg9
        config.querySenders[0].queryPck = pkg0
        simRun.sim.run()
        simRun.sim.shutDown()
        if (verifyExecuted3==0) {
            fail("pck3 not verified")
        }
        if (verifyExecuted4==0) {
            fail("pck4 not verified")
        }
        if (verifyExecuted5==0) {
            fail("pck5 not verified")
        }
        if (verifyExecuted7==0) {
            fail("pck7 not verified")
        }
        if (verifyExecuted8==0) {
            fail("pck8 not verified")
        }
        if (verifyExecuted9==0) {
            fail("pck9 not verified")
        }
    }
}
