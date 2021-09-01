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
package lupos.code_gen_test_01
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

public class SimpleDELETE3 {
    internal val inputData = arrayOf(
        File("src/jvmTest/resources/SimpleDELETE3.input").readAsString(),
    )
    internal val inputGraph = arrayOf(
        "",
    )
    internal val inputType = arrayOf(
        ".ttl",
    )
    internal val outputData = arrayOf(
        File("src/jvmTest/resources/SimpleDELETE3.output0").readAsString(),
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
        "  ?s ?p ?o . \n" +
        "} \n" +
        "WHERE  \n" +
        "{ \n" +
        "  ?s foaf:knows :c . \n" +
        "  ?s ?p ?o  \n" +
        "} \n" +
        ""

    @Test(timeout = 2000)
    public fun `Simple DELETE 3 - None - PartitionByIDTwiceAllCollations - false`() {
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
        val query0 = Query(instance)
        val graph0 = instance.tripleStoreManager!!.getGraph(inputGraph[0])
        val operator0 = graph0.getIterator(query0, arrayOf(AOPVariable(query0, "s"), AOPVariable(query0, "p"), AOPVariable(query0, "o")), EIndexPatternExt.SPO)
        val actual0 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator0, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected0 = MemoryTable.parseFromAny(inputData[0], inputType[0], Query(instance))!!
        val buf_err0 = MyPrintWriter()
        if (!expected0.equalsVerbose(actual0, true, true, buf_err0)) {
            fail(expected0.toString() + " .. " + actual0.toString() + " .. " + buf_err0.toString() + " .. " + operator0)
        }
        val operator1 = LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, query)
        LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator1, buf, EQueryResultToStreamExt.EMPTY_STREAM)
        val query2 = Query(instance)
        val graph2 = instance.tripleStoreManager!!.getGraph(outputGraph[0])
        val operator2 = graph2.getIterator(query2, arrayOf(AOPVariable(query2, "s"), AOPVariable(query2, "p"), AOPVariable(query2, "o")), EIndexPatternExt.SPO)
        val actual2 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator2, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected2 = MemoryTable.parseFromAny(outputData[0], outputType[0], Query(instance))!!
        val buf_err2 = MyPrintWriter()
        if (!expected2.equalsVerbose(actual2, true, true, buf_err2)) {
            fail(expected2.toString() + " .. " + actual2.toString() + " .. " + buf_err2.toString() + " .. " + operator2)
        }
      }finally{
        LuposdateEndpoint.close(instance)
      }
    }
    @Test(timeout = 2000)
    public fun `Simple DELETE 3 - None - PartitionByID_1_AllCollations - true`() {
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
      }finally{
        LuposdateEndpoint.close(instance)
      }
    }
    @Test(timeout = 2000)
    public fun `Simple DELETE 3 - None - PartitionByID_1_AllCollations - false`() {
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
        val query6 = Query(instance)
        val graph6 = instance.tripleStoreManager!!.getGraph(inputGraph[0])
        val operator6 = graph6.getIterator(query6, arrayOf(AOPVariable(query6, "s"), AOPVariable(query6, "p"), AOPVariable(query6, "o")), EIndexPatternExt.SPO)
        val actual6 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator6, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected6 = MemoryTable.parseFromAny(inputData[0], inputType[0], Query(instance))!!
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
      }finally{
        LuposdateEndpoint.close(instance)
      }
    }
    @Test(timeout = 2000)
    public fun `Simple DELETE 3 - None - PartitionByID_2_AllCollations - true`() {
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
      }finally{
        LuposdateEndpoint.close(instance)
      }
    }
    @Test(timeout = 2000)
    public fun `Simple DELETE 3 - None - PartitionByID_2_AllCollations - false`() {
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
        val query12 = Query(instance)
        val graph12 = instance.tripleStoreManager!!.getGraph(inputGraph[0])
        val operator12 = graph12.getIterator(query12, arrayOf(AOPVariable(query12, "s"), AOPVariable(query12, "p"), AOPVariable(query12, "o")), EIndexPatternExt.SPO)
        val actual12 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator12, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected12 = MemoryTable.parseFromAny(inputData[0], inputType[0], Query(instance))!!
        val buf_err12 = MyPrintWriter()
        if (!expected12.equalsVerbose(actual12, true, true, buf_err12)) {
            fail(expected12.toString() + " .. " + actual12.toString() + " .. " + buf_err12.toString() + " .. " + operator12)
        }
        val operator13 = LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, query)
        LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator13, buf, EQueryResultToStreamExt.EMPTY_STREAM)
        val query14 = Query(instance)
        val graph14 = instance.tripleStoreManager!!.getGraph(outputGraph[0])
        val operator14 = graph14.getIterator(query14, arrayOf(AOPVariable(query14, "s"), AOPVariable(query14, "p"), AOPVariable(query14, "o")), EIndexPatternExt.SPO)
        val actual14 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator14, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected14 = MemoryTable.parseFromAny(outputData[0], outputType[0], Query(instance))!!
        val buf_err14 = MyPrintWriter()
        if (!expected14.equalsVerbose(actual14, true, true, buf_err14)) {
            fail(expected14.toString() + " .. " + actual14.toString() + " .. " + buf_err14.toString() + " .. " + operator14)
        }
      }finally{
        LuposdateEndpoint.close(instance)
      }
    }
    @Test(timeout = 2000)
    public fun `Simple DELETE 3 - None - PartitionByID_O_AllCollations - true`() {
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
      }finally{
        LuposdateEndpoint.close(instance)
      }
    }
    @Test(timeout = 2000)
    public fun `Simple DELETE 3 - None - PartitionByID_O_AllCollations - false`() {
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
        val query18 = Query(instance)
        val graph18 = instance.tripleStoreManager!!.getGraph(inputGraph[0])
        val operator18 = graph18.getIterator(query18, arrayOf(AOPVariable(query18, "s"), AOPVariable(query18, "p"), AOPVariable(query18, "o")), EIndexPatternExt.SPO)
        val actual18 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator18, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected18 = MemoryTable.parseFromAny(inputData[0], inputType[0], Query(instance))!!
        val buf_err18 = MyPrintWriter()
        if (!expected18.equalsVerbose(actual18, true, true, buf_err18)) {
            fail(expected18.toString() + " .. " + actual18.toString() + " .. " + buf_err18.toString() + " .. " + operator18)
        }
        val operator19 = LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, query)
        LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator19, buf, EQueryResultToStreamExt.EMPTY_STREAM)
        val query20 = Query(instance)
        val graph20 = instance.tripleStoreManager!!.getGraph(outputGraph[0])
        val operator20 = graph20.getIterator(query20, arrayOf(AOPVariable(query20, "s"), AOPVariable(query20, "p"), AOPVariable(query20, "o")), EIndexPatternExt.SPO)
        val actual20 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator20, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected20 = MemoryTable.parseFromAny(outputData[0], outputType[0], Query(instance))!!
        val buf_err20 = MyPrintWriter()
        if (!expected20.equalsVerbose(actual20, true, true, buf_err20)) {
            fail(expected20.toString() + " .. " + actual20.toString() + " .. " + buf_err20.toString() + " .. " + operator20)
        }
      }finally{
        LuposdateEndpoint.close(instance)
      }
    }
    @Test(timeout = 2000)
    public fun `Simple DELETE 3 - None - PartitionByID_S_AllCollations - true`() {
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
      }finally{
        LuposdateEndpoint.close(instance)
      }
    }
    @Test(timeout = 2000)
    public fun `Simple DELETE 3 - None - PartitionByID_S_AllCollations - false`() {
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
        val query24 = Query(instance)
        val graph24 = instance.tripleStoreManager!!.getGraph(inputGraph[0])
        val operator24 = graph24.getIterator(query24, arrayOf(AOPVariable(query24, "s"), AOPVariable(query24, "p"), AOPVariable(query24, "o")), EIndexPatternExt.SPO)
        val actual24 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator24, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected24 = MemoryTable.parseFromAny(inputData[0], inputType[0], Query(instance))!!
        val buf_err24 = MyPrintWriter()
        if (!expected24.equalsVerbose(actual24, true, true, buf_err24)) {
            fail(expected24.toString() + " .. " + actual24.toString() + " .. " + buf_err24.toString() + " .. " + operator24)
        }
        val operator25 = LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, query)
        LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator25, buf, EQueryResultToStreamExt.EMPTY_STREAM)
        val query26 = Query(instance)
        val graph26 = instance.tripleStoreManager!!.getGraph(outputGraph[0])
        val operator26 = graph26.getIterator(query26, arrayOf(AOPVariable(query26, "s"), AOPVariable(query26, "p"), AOPVariable(query26, "o")), EIndexPatternExt.SPO)
        val actual26 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator26, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected26 = MemoryTable.parseFromAny(outputData[0], outputType[0], Query(instance))!!
        val buf_err26 = MyPrintWriter()
        if (!expected26.equalsVerbose(actual26, true, true, buf_err26)) {
            fail(expected26.toString() + " .. " + actual26.toString() + " .. " + buf_err26.toString() + " .. " + operator26)
        }
      }finally{
        LuposdateEndpoint.close(instance)
      }
    }
    @Test(timeout = 2000)
    public fun `Simple DELETE 3 - None - PartitionByKeyAllCollations - true`() {
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
      }finally{
        LuposdateEndpoint.close(instance)
      }
    }
    @Test(timeout = 2000)
    public fun `Simple DELETE 3 - None - PartitionByKeyAllCollations - false`() {
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
        val query30 = Query(instance)
        val graph30 = instance.tripleStoreManager!!.getGraph(inputGraph[0])
        val operator30 = graph30.getIterator(query30, arrayOf(AOPVariable(query30, "s"), AOPVariable(query30, "p"), AOPVariable(query30, "o")), EIndexPatternExt.SPO)
        val actual30 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator30, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected30 = MemoryTable.parseFromAny(inputData[0], inputType[0], Query(instance))!!
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
      }finally{
        LuposdateEndpoint.close(instance)
      }
    }
    @Test(timeout = 2000)
    public fun `Simple DELETE 3 - None - Simple - true`() {
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
      }finally{
        LuposdateEndpoint.close(instance)
      }
    }
    @Test(timeout = 2000)
    public fun `Simple DELETE 3 - None - Simple - false`() {
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
        val query36 = Query(instance)
        val graph36 = instance.tripleStoreManager!!.getGraph(inputGraph[0])
        val operator36 = graph36.getIterator(query36, arrayOf(AOPVariable(query36, "s"), AOPVariable(query36, "p"), AOPVariable(query36, "o")), EIndexPatternExt.SPO)
        val actual36 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator36, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected36 = MemoryTable.parseFromAny(inputData[0], inputType[0], Query(instance))!!
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
      }finally{
        LuposdateEndpoint.close(instance)
      }
    }
    @Test(timeout = 2000)
    public fun `Simple DELETE 3 - Thread - PartitionByIDTwiceAllCollations - true`() {
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
      }finally{
        LuposdateEndpoint.close(instance)
      }
    }
    @Test(timeout = 2000)
    public fun `Simple DELETE 3 - Thread - PartitionByIDTwiceAllCollations - false`() {
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
        val query42 = Query(instance)
        val graph42 = instance.tripleStoreManager!!.getGraph(inputGraph[0])
        val operator42 = graph42.getIterator(query42, arrayOf(AOPVariable(query42, "s"), AOPVariable(query42, "p"), AOPVariable(query42, "o")), EIndexPatternExt.SPO)
        val actual42 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator42, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected42 = MemoryTable.parseFromAny(inputData[0], inputType[0], Query(instance))!!
        val buf_err42 = MyPrintWriter()
        if (!expected42.equalsVerbose(actual42, true, true, buf_err42)) {
            fail(expected42.toString() + " .. " + actual42.toString() + " .. " + buf_err42.toString() + " .. " + operator42)
        }
        val operator43 = LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, query)
        LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator43, buf, EQueryResultToStreamExt.EMPTY_STREAM)
        val query44 = Query(instance)
        val graph44 = instance.tripleStoreManager!!.getGraph(outputGraph[0])
        val operator44 = graph44.getIterator(query44, arrayOf(AOPVariable(query44, "s"), AOPVariable(query44, "p"), AOPVariable(query44, "o")), EIndexPatternExt.SPO)
        val actual44 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator44, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected44 = MemoryTable.parseFromAny(outputData[0], outputType[0], Query(instance))!!
        val buf_err44 = MyPrintWriter()
        if (!expected44.equalsVerbose(actual44, true, true, buf_err44)) {
            fail(expected44.toString() + " .. " + actual44.toString() + " .. " + buf_err44.toString() + " .. " + operator44)
        }
      }finally{
        LuposdateEndpoint.close(instance)
      }
    }
    @Test(timeout = 2000)
    public fun `Simple DELETE 3 - Thread - PartitionByID_1_AllCollations - true`() {
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
        val query45 = Query(instance)
        val graph45 = instance.tripleStoreManager!!.getGraph(inputGraph[0])
        val operator45 = graph45.getIterator(query45, arrayOf(AOPVariable(query45, "s"), AOPVariable(query45, "p"), AOPVariable(query45, "o")), EIndexPatternExt.SPO)
        val actual45 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator45, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected45 = MemoryTable.parseFromAny(inputData[0], inputType[0], Query(instance))!!
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
    public fun `Simple DELETE 3 - Thread - PartitionByID_1_AllCollations - false`() {
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
        val query48 = Query(instance)
        val graph48 = instance.tripleStoreManager!!.getGraph(inputGraph[0])
        val operator48 = graph48.getIterator(query48, arrayOf(AOPVariable(query48, "s"), AOPVariable(query48, "p"), AOPVariable(query48, "o")), EIndexPatternExt.SPO)
        val actual48 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator48, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected48 = MemoryTable.parseFromAny(inputData[0], inputType[0], Query(instance))!!
        val buf_err48 = MyPrintWriter()
        if (!expected48.equalsVerbose(actual48, true, true, buf_err48)) {
            fail(expected48.toString() + " .. " + actual48.toString() + " .. " + buf_err48.toString() + " .. " + operator48)
        }
        val operator49 = LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, query)
        LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator49, buf, EQueryResultToStreamExt.EMPTY_STREAM)
        val query50 = Query(instance)
        val graph50 = instance.tripleStoreManager!!.getGraph(outputGraph[0])
        val operator50 = graph50.getIterator(query50, arrayOf(AOPVariable(query50, "s"), AOPVariable(query50, "p"), AOPVariable(query50, "o")), EIndexPatternExt.SPO)
        val actual50 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator50, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected50 = MemoryTable.parseFromAny(outputData[0], outputType[0], Query(instance))!!
        val buf_err50 = MyPrintWriter()
        if (!expected50.equalsVerbose(actual50, true, true, buf_err50)) {
            fail(expected50.toString() + " .. " + actual50.toString() + " .. " + buf_err50.toString() + " .. " + operator50)
        }
      }finally{
        LuposdateEndpoint.close(instance)
      }
    }
    @Test(timeout = 2000)
    public fun `Simple DELETE 3 - Thread - PartitionByID_2_AllCollations - true`() {
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
        val query51 = Query(instance)
        val graph51 = instance.tripleStoreManager!!.getGraph(inputGraph[0])
        val operator51 = graph51.getIterator(query51, arrayOf(AOPVariable(query51, "s"), AOPVariable(query51, "p"), AOPVariable(query51, "o")), EIndexPatternExt.SPO)
        val actual51 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator51, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected51 = MemoryTable.parseFromAny(inputData[0], inputType[0], Query(instance))!!
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
      }finally{
        LuposdateEndpoint.close(instance)
      }
    }
    @Test(timeout = 2000)
    public fun `Simple DELETE 3 - Thread - PartitionByID_2_AllCollations - false`() {
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
        val query54 = Query(instance)
        val graph54 = instance.tripleStoreManager!!.getGraph(inputGraph[0])
        val operator54 = graph54.getIterator(query54, arrayOf(AOPVariable(query54, "s"), AOPVariable(query54, "p"), AOPVariable(query54, "o")), EIndexPatternExt.SPO)
        val actual54 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator54, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected54 = MemoryTable.parseFromAny(inputData[0], inputType[0], Query(instance))!!
        val buf_err54 = MyPrintWriter()
        if (!expected54.equalsVerbose(actual54, true, true, buf_err54)) {
            fail(expected54.toString() + " .. " + actual54.toString() + " .. " + buf_err54.toString() + " .. " + operator54)
        }
        val operator55 = LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, query)
        LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator55, buf, EQueryResultToStreamExt.EMPTY_STREAM)
        val query56 = Query(instance)
        val graph56 = instance.tripleStoreManager!!.getGraph(outputGraph[0])
        val operator56 = graph56.getIterator(query56, arrayOf(AOPVariable(query56, "s"), AOPVariable(query56, "p"), AOPVariable(query56, "o")), EIndexPatternExt.SPO)
        val actual56 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator56, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected56 = MemoryTable.parseFromAny(outputData[0], outputType[0], Query(instance))!!
        val buf_err56 = MyPrintWriter()
        if (!expected56.equalsVerbose(actual56, true, true, buf_err56)) {
            fail(expected56.toString() + " .. " + actual56.toString() + " .. " + buf_err56.toString() + " .. " + operator56)
        }
      }finally{
        LuposdateEndpoint.close(instance)
      }
    }
    @Test(timeout = 2000)
    public fun `Simple DELETE 3 - Thread - PartitionByID_O_AllCollations - true`() {
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
        val query57 = Query(instance)
        val graph57 = instance.tripleStoreManager!!.getGraph(inputGraph[0])
        val operator57 = graph57.getIterator(query57, arrayOf(AOPVariable(query57, "s"), AOPVariable(query57, "p"), AOPVariable(query57, "o")), EIndexPatternExt.SPO)
        val actual57 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator57, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected57 = MemoryTable.parseFromAny(inputData[0], inputType[0], Query(instance))!!
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
    public fun `Simple DELETE 3 - Thread - PartitionByID_O_AllCollations - false`() {
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
        val query60 = Query(instance)
        val graph60 = instance.tripleStoreManager!!.getGraph(inputGraph[0])
        val operator60 = graph60.getIterator(query60, arrayOf(AOPVariable(query60, "s"), AOPVariable(query60, "p"), AOPVariable(query60, "o")), EIndexPatternExt.SPO)
        val actual60 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator60, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected60 = MemoryTable.parseFromAny(inputData[0], inputType[0], Query(instance))!!
        val buf_err60 = MyPrintWriter()
        if (!expected60.equalsVerbose(actual60, true, true, buf_err60)) {
            fail(expected60.toString() + " .. " + actual60.toString() + " .. " + buf_err60.toString() + " .. " + operator60)
        }
        val operator61 = LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, query)
        LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator61, buf, EQueryResultToStreamExt.EMPTY_STREAM)
        val query62 = Query(instance)
        val graph62 = instance.tripleStoreManager!!.getGraph(outputGraph[0])
        val operator62 = graph62.getIterator(query62, arrayOf(AOPVariable(query62, "s"), AOPVariable(query62, "p"), AOPVariable(query62, "o")), EIndexPatternExt.SPO)
        val actual62 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator62, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected62 = MemoryTable.parseFromAny(outputData[0], outputType[0], Query(instance))!!
        val buf_err62 = MyPrintWriter()
        if (!expected62.equalsVerbose(actual62, true, true, buf_err62)) {
            fail(expected62.toString() + " .. " + actual62.toString() + " .. " + buf_err62.toString() + " .. " + operator62)
        }
      }finally{
        LuposdateEndpoint.close(instance)
      }
    }
    @Test(timeout = 2000)
    public fun `Simple DELETE 3 - Thread - PartitionByID_S_AllCollations - true`() {
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
        val query63 = Query(instance)
        val graph63 = instance.tripleStoreManager!!.getGraph(inputGraph[0])
        val operator63 = graph63.getIterator(query63, arrayOf(AOPVariable(query63, "s"), AOPVariable(query63, "p"), AOPVariable(query63, "o")), EIndexPatternExt.SPO)
        val actual63 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator63, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected63 = MemoryTable.parseFromAny(inputData[0], inputType[0], Query(instance))!!
        val buf_err63 = MyPrintWriter()
        if (!expected63.equalsVerbose(actual63, true, true, buf_err63)) {
            fail(expected63.toString() + " .. " + actual63.toString() + " .. " + buf_err63.toString() + " .. " + operator63)
        }
        val operator64 = LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, query)
        LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator64, buf, EQueryResultToStreamExt.EMPTY_STREAM)
        val query65 = Query(instance)
        val graph65 = instance.tripleStoreManager!!.getGraph(outputGraph[0])
        val operator65 = graph65.getIterator(query65, arrayOf(AOPVariable(query65, "s"), AOPVariable(query65, "p"), AOPVariable(query65, "o")), EIndexPatternExt.SPO)
        val actual65 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator65, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected65 = MemoryTable.parseFromAny(outputData[0], outputType[0], Query(instance))!!
        val buf_err65 = MyPrintWriter()
        if (!expected65.equalsVerbose(actual65, true, true, buf_err65)) {
            fail(expected65.toString() + " .. " + actual65.toString() + " .. " + buf_err65.toString() + " .. " + operator65)
        }
      }finally{
        LuposdateEndpoint.close(instance)
      }
    }
    @Test(timeout = 2000)
    public fun `Simple DELETE 3 - Thread - PartitionByID_S_AllCollations - false`() {
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
        val query66 = Query(instance)
        val graph66 = instance.tripleStoreManager!!.getGraph(inputGraph[0])
        val operator66 = graph66.getIterator(query66, arrayOf(AOPVariable(query66, "s"), AOPVariable(query66, "p"), AOPVariable(query66, "o")), EIndexPatternExt.SPO)
        val actual66 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator66, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected66 = MemoryTable.parseFromAny(inputData[0], inputType[0], Query(instance))!!
        val buf_err66 = MyPrintWriter()
        if (!expected66.equalsVerbose(actual66, true, true, buf_err66)) {
            fail(expected66.toString() + " .. " + actual66.toString() + " .. " + buf_err66.toString() + " .. " + operator66)
        }
        val operator67 = LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, query)
        LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator67, buf, EQueryResultToStreamExt.EMPTY_STREAM)
        val query68 = Query(instance)
        val graph68 = instance.tripleStoreManager!!.getGraph(outputGraph[0])
        val operator68 = graph68.getIterator(query68, arrayOf(AOPVariable(query68, "s"), AOPVariable(query68, "p"), AOPVariable(query68, "o")), EIndexPatternExt.SPO)
        val actual68 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator68, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected68 = MemoryTable.parseFromAny(outputData[0], outputType[0], Query(instance))!!
        val buf_err68 = MyPrintWriter()
        if (!expected68.equalsVerbose(actual68, true, true, buf_err68)) {
            fail(expected68.toString() + " .. " + actual68.toString() + " .. " + buf_err68.toString() + " .. " + operator68)
        }
      }finally{
        LuposdateEndpoint.close(instance)
      }
    }
    @Test(timeout = 2000)
    public fun `Simple DELETE 3 - Thread - PartitionByKeyAllCollations - true`() {
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
        val query69 = Query(instance)
        val graph69 = instance.tripleStoreManager!!.getGraph(inputGraph[0])
        val operator69 = graph69.getIterator(query69, arrayOf(AOPVariable(query69, "s"), AOPVariable(query69, "p"), AOPVariable(query69, "o")), EIndexPatternExt.SPO)
        val actual69 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator69, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected69 = MemoryTable.parseFromAny(inputData[0], inputType[0], Query(instance))!!
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
    public fun `Simple DELETE 3 - Thread - PartitionByKeyAllCollations - false`() {
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
        val query72 = Query(instance)
        val graph72 = instance.tripleStoreManager!!.getGraph(inputGraph[0])
        val operator72 = graph72.getIterator(query72, arrayOf(AOPVariable(query72, "s"), AOPVariable(query72, "p"), AOPVariable(query72, "o")), EIndexPatternExt.SPO)
        val actual72 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator72, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected72 = MemoryTable.parseFromAny(inputData[0], inputType[0], Query(instance))!!
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
      }finally{
        LuposdateEndpoint.close(instance)
      }
    }
    @Test(timeout = 2000)
    public fun `Simple DELETE 3 - Thread - Simple - true`() {
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
        val query75 = Query(instance)
        val graph75 = instance.tripleStoreManager!!.getGraph(inputGraph[0])
        val operator75 = graph75.getIterator(query75, arrayOf(AOPVariable(query75, "s"), AOPVariable(query75, "p"), AOPVariable(query75, "o")), EIndexPatternExt.SPO)
        val actual75 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator75, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected75 = MemoryTable.parseFromAny(inputData[0], inputType[0], Query(instance))!!
        val buf_err75 = MyPrintWriter()
        if (!expected75.equalsVerbose(actual75, true, true, buf_err75)) {
            fail(expected75.toString() + " .. " + actual75.toString() + " .. " + buf_err75.toString() + " .. " + operator75)
        }
        val operator76 = LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, query)
        LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator76, buf, EQueryResultToStreamExt.EMPTY_STREAM)
        val query77 = Query(instance)
        val graph77 = instance.tripleStoreManager!!.getGraph(outputGraph[0])
        val operator77 = graph77.getIterator(query77, arrayOf(AOPVariable(query77, "s"), AOPVariable(query77, "p"), AOPVariable(query77, "o")), EIndexPatternExt.SPO)
        val actual77 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator77, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected77 = MemoryTable.parseFromAny(outputData[0], outputType[0], Query(instance))!!
        val buf_err77 = MyPrintWriter()
        if (!expected77.equalsVerbose(actual77, true, true, buf_err77)) {
            fail(expected77.toString() + " .. " + actual77.toString() + " .. " + buf_err77.toString() + " .. " + operator77)
        }
      }finally{
        LuposdateEndpoint.close(instance)
      }
    }
    @Test(timeout = 2000)
    public fun `Simple DELETE 3 - Thread - Simple - false`() {
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
        val query78 = Query(instance)
        val graph78 = instance.tripleStoreManager!!.getGraph(inputGraph[0])
        val operator78 = graph78.getIterator(query78, arrayOf(AOPVariable(query78, "s"), AOPVariable(query78, "p"), AOPVariable(query78, "o")), EIndexPatternExt.SPO)
        val actual78 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator78, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected78 = MemoryTable.parseFromAny(inputData[0], inputType[0], Query(instance))!!
        val buf_err78 = MyPrintWriter()
        if (!expected78.equalsVerbose(actual78, true, true, buf_err78)) {
            fail(expected78.toString() + " .. " + actual78.toString() + " .. " + buf_err78.toString() + " .. " + operator78)
        }
        val operator79 = LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, query)
        LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator79, buf, EQueryResultToStreamExt.EMPTY_STREAM)
        val query80 = Query(instance)
        val graph80 = instance.tripleStoreManager!!.getGraph(outputGraph[0])
        val operator80 = graph80.getIterator(query80, arrayOf(AOPVariable(query80, "s"), AOPVariable(query80, "p"), AOPVariable(query80, "o")), EIndexPatternExt.SPO)
        val actual80 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator80, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected80 = MemoryTable.parseFromAny(outputData[0], outputType[0], Query(instance))!!
        val buf_err80 = MyPrintWriter()
        if (!expected80.equalsVerbose(actual80, true, true, buf_err80)) {
            fail(expected80.toString() + " .. " + actual80.toString() + " .. " + buf_err80.toString() + " .. " + operator80)
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
        var verifyExecuted1 = 0
        val pkg1 = MySimulatorTestingCompareGraphPackage("SELECT ?s ?p ?o WHERE { ?s ?p ?o . }",MemoryTable.parseFromAny(inputData[0], inputType[0], Query(instance))!!, {verifyExecuted1++})
        pkg0.onFinish = pkg1
        val pkg2 = MySimulatorTestingExecute(query)
        pkg1.onFinish = pkg2
        var verifyExecuted3 = 0
        val pkg3 = MySimulatorTestingCompareGraphPackage("SELECT ?s ?p ?o WHERE { ?s ?p ?o . }",MemoryTable.parseFromAny(outputData[0], outputType[0], Query(instance))!!, {verifyExecuted3++})
        pkg2.onFinish = pkg3
        config.querySenders[0].queryPck = pkg0
        simRun.sim.run()
        simRun.sim.shutDown()
        if (verifyExecuted1==0) {
            fail("pck1 not verified")
        }
        if (verifyExecuted3==0) {
            fail("pck3 not verified")
        }
    }
}
