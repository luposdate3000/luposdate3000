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


public class Simpleinsertdatanamed2 {
    internal val inputData = arrayOf(
        File("src/luposdate3000_launch_code_gen_test_00/src/jvmTest/resources/Simpleinsertdatanamed2.input0").readAsString(),
    )
    internal val inputDataFile = arrayOf(
        "src/luposdate3000_launch_code_gen_test_00/src/jvmTest/resources/Simpleinsertdatanamed2.input0",
    )
    internal val inputGraph = arrayOf(
        "http://example.org/g1",
    )
    internal val inputType = arrayOf(
        ".ttl",
    )
    internal val outputData = arrayOf(
        File("src/luposdate3000_launch_code_gen_test_00/src/jvmTest/resources/Simpleinsertdatanamed2.output0").readAsString(),
    )
    internal val outputDataFile = arrayOf(
        "src/luposdate3000_launch_code_gen_test_00/src/jvmTest/resources/Simpleinsertdatanamed2.output0",
    )
    internal val outputGraph = arrayOf(
        "http://example.org/g1",
    )
    internal val outputType = arrayOf(
        ".ttl",
    )
    internal val query = "PREFIX : <http://example.org/ns#> \n" +
        "INSERT DATA { GRAPH <http://example.org/g1> { :s :p :o2 } } \n" +
        ""

    public fun `Simple insert data named 2 - None - Simple - true`() {
      var instance = Luposdate3000Instance()
      try{
        instance.LUPOS_BUFFER_SIZE = 512
        instance.LUPOS_PARTITION_MODE=EPartitionModeExt.None
        instance.predefinedPartitionScheme=EPredefinedPartitionSchemesExt.Simple
        instance.useDictionaryInlineEncoding=true
        instance = LuposdateEndpoint.initializeB(instance)
        normalHelper(instance)
      }catch(e:Throwable){
        e.myPrintStackTraceAndThrowAgain(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_launch_code_gen_test_00/src/jvmMain/kotlin/lupos/launch_code_gen_test_00/Simpleinsertdatanamed2.kt:80"/*SOURCE_FILE_END*/ ) //otherwise this would be silently ignored
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
        val operator1 = LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, query)
        LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator1, buf, EQueryResultToStreamExt.EMPTY_STREAM)
        val query2 = Query(instance)
        val graph2 = instance.tripleStoreManager!!.getGraph(outputGraph[0])
        val iterator2 = graph2.getIterator(query2, arrayOf(AOPVariable(query2, "s"), AOPVariable(query2, "p"), AOPVariable(query2, "o")), EIndexPatternExt.SPO)
        val operator2 = PhysicalOptimizer(query2).optimizeCall(iterator2)
        val actual2 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator2, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected2 = MemoryTable.parseFromAny(outputData[0], outputType[0], Query(instance))!!
        val buf_err2 = MyPrintWriter()
        if (!expected2.equalsVerbose(actual2, true, true, false, buf_err2)) {
            TODO(expected2.toString() + " .. " + actual2.toString() + " .. " + buf_err2.toString() + " .. " + operator2)
        }
    }
    public fun getTests():Set<Pair<String,()->Unit>> {
        return setOf(
            "Simple insert data named 2 - None - Simple - true" to ::`Simple insert data named 2 - None - Simple - true`,
        )
    }
}
public fun main(){
    var idx=0
    var stop=false
    for((name,func) in Simpleinsertdatanamed2().getTests()){
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
