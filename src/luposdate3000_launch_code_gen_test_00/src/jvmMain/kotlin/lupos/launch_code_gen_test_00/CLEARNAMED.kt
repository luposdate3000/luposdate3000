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
package lupos.launch_code_gen_test_00
import lupos.shared.myPrintStackTraceAndThrowAgain
import lupos.shared.myPrintStackTrace
import lupos.optimizer.physical.PhysicalOptimizer
import lupos.endpoint.LuposdateEndpoint
import lupos.operator.arithmetik.noinput.AOPVariable
import simora.addQuerySender
import lupos.operator.base.Query
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
import simora.SimulationRun
import lupos.simulator_db.luposdate3000.Package_Luposdate3000_TestingCompareGraphPackage
import lupos.simulator_db.luposdate3000.Package_Luposdate3000_TestingImportPackage
import lupos.simulator_db.luposdate3000.Package_Luposdate3000_TestingExecute
import lupos.simulator_db.luposdate3000.Application_Luposdate3000


public class CLEARNAMED {
    internal val inputData = arrayOf(
        File("src/luposdate3000_launch_code_gen_test_00/src/jvmTest/resources/CLEARNAMED.input").readAsString(),
        File("src/luposdate3000_launch_code_gen_test_00/src/jvmTest/resources/CLEARNAMED.input1").readAsString(),
        File("src/luposdate3000_launch_code_gen_test_00/src/jvmTest/resources/CLEARNAMED.input2").readAsString(),
    )
    internal val inputDataFile = arrayOf(
        "src/luposdate3000_launch_code_gen_test_00/src/jvmTest/resources/CLEARNAMED.input",
        "src/luposdate3000_launch_code_gen_test_00/src/jvmTest/resources/CLEARNAMED.input1",
        "src/luposdate3000_launch_code_gen_test_00/src/jvmTest/resources/CLEARNAMED.input2",
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
    internal val outputData = arrayOf(
        File("src/luposdate3000_launch_code_gen_test_00/src/jvmTest/resources/CLEARNAMED.output0").readAsString(),
        File("src/luposdate3000_launch_code_gen_test_00/src/jvmTest/resources/CLEARNAMED.output1").readAsString(),
        File("src/luposdate3000_launch_code_gen_test_00/src/jvmTest/resources/CLEARNAMED.output2").readAsString(),
    )
    internal val outputDataFile = arrayOf(
        "src/luposdate3000_launch_code_gen_test_00/src/jvmTest/resources/CLEARNAMED.output0",
        "src/luposdate3000_launch_code_gen_test_00/src/jvmTest/resources/CLEARNAMED.output1",
        "src/luposdate3000_launch_code_gen_test_00/src/jvmTest/resources/CLEARNAMED.output2",
    )
    internal val outputGraph = arrayOf(
        "",
        "http://example.org/g1",
        "http://example.org/g2",
    )
    internal val outputType = arrayOf(
        ".ttl",
        ".ttl",
        ".ttl",
    )
    internal val query = "PREFIX     : <http://example.org/>  \n" +
        "CLEAR NAMED \n" +
        ""

    public fun `CLEAR NAMED - Thread - Simple - true`() {
      var instance = Luposdate3000Instance()
      try{
        instance.LUPOS_BUFFER_SIZE = 512
        instance.LUPOS_PARTITION_MODE=EPartitionModeExt.Thread
        instance.predefinedPartitionScheme=EPredefinedPartitionSchemesExt.Simple
        instance.useDictionaryInlineEncoding=true
        instance.LUPOS_PROCESS_URLS_STORE=arrayOf("localhost:80")
        instance.LUPOS_PROCESS_URLS_QUERY=arrayOf("localhost:80")
        instance.LUPOS_PROCESS_URLS_ALL=arrayOf("localhost:80")
        instance.LUPOS_PROCESS_ID=0
        instance = LuposdateEndpoint.initializeB(instance)
        normalHelper(instance)
      }catch(e:Throwable){
        e.myPrintStackTraceAndThrowAgain(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_launch_code_gen_test_00/src/jvmMain/kotlin/lupos/launch_code_gen_test_00/CLEARNAMED.kt:100"/*SOURCE_FILE_END*/ ) //otherwise this would be silently ignored
      }finally{
        LuposdateEndpoint.close(instance)
      }
    }
    internal fun normalHelper(instance:Luposdate3000Instance) {
        val buf = MyPrintWriter(false)
        if (listOf(".n3", ".ttl", ".nt").contains(inputType[0])) {
            LuposdateEndpoint.importTripleFileC(instance, inputDataFile[0],inputType[0], inputGraph[0])
        } else {
            TODO("unknown filetype "+inputType[0])
        }
        if (listOf(".n3", ".ttl", ".nt").contains(inputType[1])) {
            LuposdateEndpoint.importTripleFileC(instance, inputDataFile[1],inputType[1], inputGraph[1])
        } else {
            TODO("unknown filetype "+inputType[1])
        }
        if (listOf(".n3", ".ttl", ".nt").contains(inputType[2])) {
            LuposdateEndpoint.importTripleFileC(instance, inputDataFile[2],inputType[2], inputGraph[2])
        } else {
            TODO("unknown filetype "+inputType[2])
        }
        val query0 = Query(instance)
        val graph0 = instance.tripleStoreManager!!.getGraph(inputGraph[0])
        val iterator0 = graph0.getIterator(query0, arrayOf(AOPVariable(query0, "s"), AOPVariable(query0, "p"), AOPVariable(query0, "o")), EIndexPatternExt.SPO)
        val operator0 = PhysicalOptimizer(query0).optimizeCall(iterator0)
        val actual0 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator0, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected0 = MemoryTable.parseFromAny(inputData[0], inputType[0], Query(instance))!!
        val buf_err0 = MyPrintWriter()
        if (!expected0.equalsVerbose(actual0, true, true, false, buf_err0)) {
            TODO(expected0.toString() + " .. " + actual0.toString() + " .. " + buf_err0.toString() + " .. " + operator0)
        }
        val query1 = Query(instance)
        val graph1 = instance.tripleStoreManager!!.getGraph(inputGraph[1])
        val iterator1 = graph1.getIterator(query1, arrayOf(AOPVariable(query1, "s"), AOPVariable(query1, "p"), AOPVariable(query1, "o")), EIndexPatternExt.SPO)
        val operator1 = PhysicalOptimizer(query1).optimizeCall(iterator1)
        val actual1 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator1, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected1 = MemoryTable.parseFromAny(inputData[1], inputType[1], Query(instance))!!
        val buf_err1 = MyPrintWriter()
        if (!expected1.equalsVerbose(actual1, true, true, false, buf_err1)) {
            TODO(expected1.toString() + " .. " + actual1.toString() + " .. " + buf_err1.toString() + " .. " + operator1)
        }
        val query2 = Query(instance)
        val graph2 = instance.tripleStoreManager!!.getGraph(inputGraph[2])
        val iterator2 = graph2.getIterator(query2, arrayOf(AOPVariable(query2, "s"), AOPVariable(query2, "p"), AOPVariable(query2, "o")), EIndexPatternExt.SPO)
        val operator2 = PhysicalOptimizer(query2).optimizeCall(iterator2)
        val actual2 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator2, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected2 = MemoryTable.parseFromAny(inputData[2], inputType[2], Query(instance))!!
        val buf_err2 = MyPrintWriter()
        if (!expected2.equalsVerbose(actual2, true, true, false, buf_err2)) {
            TODO(expected2.toString() + " .. " + actual2.toString() + " .. " + buf_err2.toString() + " .. " + operator2)
        }
        val operator3 = LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, query)
        LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator3, buf, EQueryResultToStreamExt.EMPTY_STREAM)
        val query4 = Query(instance)
        val graph4 = instance.tripleStoreManager!!.getGraph(outputGraph[0])
        val iterator4 = graph4.getIterator(query4, arrayOf(AOPVariable(query4, "s"), AOPVariable(query4, "p"), AOPVariable(query4, "o")), EIndexPatternExt.SPO)
        val operator4 = PhysicalOptimizer(query4).optimizeCall(iterator4)
        val actual4 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator4, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected4 = MemoryTable.parseFromAny(outputData[0], outputType[0], Query(instance))!!
        val buf_err4 = MyPrintWriter()
        if (!expected4.equalsVerbose(actual4, true, true, false, buf_err4)) {
            TODO(expected4.toString() + " .. " + actual4.toString() + " .. " + buf_err4.toString() + " .. " + operator4)
        }
        val query5 = Query(instance)
        val graph5 = instance.tripleStoreManager!!.getGraph(outputGraph[1])
        val iterator5 = graph5.getIterator(query5, arrayOf(AOPVariable(query5, "s"), AOPVariable(query5, "p"), AOPVariable(query5, "o")), EIndexPatternExt.SPO)
        val operator5 = PhysicalOptimizer(query5).optimizeCall(iterator5)
        val actual5 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator5, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected5 = MemoryTable.parseFromAny(outputData[1], outputType[1], Query(instance))!!
        val buf_err5 = MyPrintWriter()
        if (!expected5.equalsVerbose(actual5, true, true, false, buf_err5)) {
            TODO(expected5.toString() + " .. " + actual5.toString() + " .. " + buf_err5.toString() + " .. " + operator5)
        }
        val query6 = Query(instance)
        val graph6 = instance.tripleStoreManager!!.getGraph(outputGraph[2])
        val iterator6 = graph6.getIterator(query6, arrayOf(AOPVariable(query6, "s"), AOPVariable(query6, "p"), AOPVariable(query6, "o")), EIndexPatternExt.SPO)
        val operator6 = PhysicalOptimizer(query6).optimizeCall(iterator6)
        val actual6 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator6, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected6 = MemoryTable.parseFromAny(outputData[2], outputType[2], Query(instance))!!
        val buf_err6 = MyPrintWriter()
        if (!expected6.equalsVerbose(actual6, true, true, false, buf_err6)) {
            TODO(expected6.toString() + " .. " + actual6.toString() + " .. " + buf_err6.toString() + " .. " + operator6)
        }
    }
    public fun getTests():Set<Pair<String,()->Unit>> {
        return setOf(
            "CLEAR NAMED - Thread - Simple - true" to ::`CLEAR NAMED - Thread - Simple - true`,
        )
    }
}
public fun main(){
    var idx=0
    var stop=false
    for((name,func) in CLEARNAMED().getTests()){
        if (stop){
            return
        }
        File("lupos.launch_code_gen_test_00.${name.replaceFirstChar { it.uppercase() }}.stat").withOutputStream{ out->
            out.println("started"+idx)
            try{
                println(name)
                func()
                out.println("passed")
            }catch(e:Error){
                out.println("failed")
                e.printStackTrace()
                stop=true
            }
        }
        idx+=1
    }
}
