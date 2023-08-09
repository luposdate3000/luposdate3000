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


public class pp33Operatorprecedence4 {
    internal val inputData = arrayOf(
        File("src/luposdate3000_launch_code_gen_test_00/src/jvmTest/resources/pp33Operatorprecedence4.input").readAsString(),
    )
    internal val inputDataFile = arrayOf(
        "src/luposdate3000_launch_code_gen_test_00/src/jvmTest/resources/pp33Operatorprecedence4.input",
    )
    internal val inputGraph = arrayOf(
        "",
    )
    internal val inputType = arrayOf(
        ".ttl",
    )
    internal val targetData = File("src/luposdate3000_launch_code_gen_test_00/src/jvmTest/resources/pp33Operatorprecedence4.output").readAsString()
    internal val targetType = ".srx"
    internal val query = "prefix :  <http://www.example.org/> \n" +
        "select ?t \n" +
        "where { \n" +
        "  :a (:p0|^:p1)/:p2|:p3 ?t \n" +
        "}"

    public fun `pp33 Operator precedence 4 - None - Simple - true`() {
      var instance = Luposdate3000Instance()
      try{
        instance.LUPOS_BUFFER_SIZE = 128
        instance.LUPOS_PARTITION_MODE=EPartitionModeExt.None
        instance.predefinedPartitionScheme=EPredefinedPartitionSchemesExt.Simple
        instance.useDictionaryInlineEncoding=true
        instance = LuposdateEndpoint.initializeB(instance)
        normalHelper(instance)
      }catch(e:Throwable){
        e.myPrintStackTraceAndThrowAgain(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_launch_code_gen_test_00/src/jvmMain/kotlin/lupos/launch_code_gen_test_00/pp33Operatorprecedence4.kt:72"/*SOURCE_FILE_END*/ ) //otherwise this would be silently ignored
      }finally{
        LuposdateEndpoint.close(instance)
      }
    }
    public fun `pp33 Operator precedence 4 - Thread - BenchmarkFig5 - true`() {
      var instance = Luposdate3000Instance()
      try{
        instance.LUPOS_BUFFER_SIZE = 128
        instance.LUPOS_PARTITION_MODE=EPartitionModeExt.Thread
        instance.predefinedPartitionScheme=EPredefinedPartitionSchemesExt.BenchmarkFig5
        instance.useDictionaryInlineEncoding=true
        instance = LuposdateEndpoint.initializeB(instance)
        normalHelper(instance)
      }catch(e:Throwable){
        e.myPrintStackTraceAndThrowAgain(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_launch_code_gen_test_00/src/jvmMain/kotlin/lupos/launch_code_gen_test_00/pp33Operatorprecedence4.kt:87"/*SOURCE_FILE_END*/ ) //otherwise this would be silently ignored
      }finally{
        LuposdateEndpoint.close(instance)
      }
    }
    public fun `pp33 Operator precedence 4 - Thread - PartitionByIDTwiceAllCollations - false`() {
      var instance = Luposdate3000Instance()
      try{
        instance.LUPOS_BUFFER_SIZE = 128
        instance.LUPOS_PARTITION_MODE=EPartitionModeExt.Thread
        instance.predefinedPartitionScheme=EPredefinedPartitionSchemesExt.PartitionByIDTwiceAllCollations
        instance.useDictionaryInlineEncoding=false
        instance = LuposdateEndpoint.initializeB(instance)
        normalHelper(instance)
      }catch(e:Throwable){
        e.myPrintStackTraceAndThrowAgain(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_launch_code_gen_test_00/src/jvmMain/kotlin/lupos/launch_code_gen_test_00/pp33Operatorprecedence4.kt:102"/*SOURCE_FILE_END*/ ) //otherwise this would be silently ignored
      }finally{
        LuposdateEndpoint.close(instance)
      }
    }
    public fun `pp33 Operator precedence 4 - Thread - PartitionByID_O_AllCollations - true`() {
      var instance = Luposdate3000Instance()
      try{
        instance.LUPOS_BUFFER_SIZE = 128
        instance.LUPOS_PARTITION_MODE=EPartitionModeExt.Thread
        instance.predefinedPartitionScheme=EPredefinedPartitionSchemesExt.PartitionByID_O_AllCollations
        instance.useDictionaryInlineEncoding=true
        instance = LuposdateEndpoint.initializeB(instance)
        normalHelper(instance)
      }catch(e:Throwable){
        e.myPrintStackTraceAndThrowAgain(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_launch_code_gen_test_00/src/jvmMain/kotlin/lupos/launch_code_gen_test_00/pp33Operatorprecedence4.kt:117"/*SOURCE_FILE_END*/ ) //otherwise this would be silently ignored
      }finally{
        LuposdateEndpoint.close(instance)
      }
    }
    public fun `pp33 Operator precedence 4 - in simulator - BenchmarkFig5 - Centralized - false - Process - RPL`() {
        simulatorHelper(
            "src/luposdate3000_simulator_db/src/jvmTest/resources/autoIntegrationTest/test1.json",
            mutableMapOf(
                "predefinedPartitionScheme" to "BenchmarkFig5",
                "mergeLocalOperatorgraphs" to true,
                "queryDistributionMode" to "Centralized",
                "useDictionaryInlineEncoding" to false,
                "REPLACE_STORE_WITH_VALUES" to false,
                "LUPOS_PARTITION_MODE" to "Process",
            ),
            "RPL",
        )
    }
    public fun `pp33 Operator precedence 4 - in simulator - BenchmarkFig5 - Centralized - false - Process - RPL_Fast`() {
        simulatorHelper(
            "src/luposdate3000_simulator_db/src/jvmTest/resources/autoIntegrationTest/test1.json",
            mutableMapOf(
                "predefinedPartitionScheme" to "BenchmarkFig5",
                "mergeLocalOperatorgraphs" to true,
                "queryDistributionMode" to "Centralized",
                "useDictionaryInlineEncoding" to false,
                "REPLACE_STORE_WITH_VALUES" to false,
                "LUPOS_PARTITION_MODE" to "Process",
            ),
            "RPL_Fast",
        )
    }
    public fun `pp33 Operator precedence 4 - in simulator - BenchmarkFig5 - Routing - false - Process - AllShortestPath`() {
        simulatorHelper(
            "src/luposdate3000_simulator_db/src/jvmTest/resources/autoIntegrationTest/test1.json",
            mutableMapOf(
                "predefinedPartitionScheme" to "BenchmarkFig5",
                "mergeLocalOperatorgraphs" to true,
                "queryDistributionMode" to "Routing",
                "useDictionaryInlineEncoding" to false,
                "REPLACE_STORE_WITH_VALUES" to false,
                "LUPOS_PARTITION_MODE" to "Process",
            ),
            "AllShortestPath",
        )
    }
    public fun `pp33 Operator precedence 4 - in simulator - PartitionByIDTwiceAllCollations - Centralized - false - Process - RPL`() {
        simulatorHelper(
            "src/luposdate3000_simulator_db/src/jvmTest/resources/autoIntegrationTest/test1.json",
            mutableMapOf(
                "predefinedPartitionScheme" to "PartitionByIDTwiceAllCollations",
                "mergeLocalOperatorgraphs" to true,
                "queryDistributionMode" to "Centralized",
                "useDictionaryInlineEncoding" to false,
                "REPLACE_STORE_WITH_VALUES" to false,
                "LUPOS_PARTITION_MODE" to "Process",
            ),
            "RPL",
        )
    }
    public fun `pp33 Operator precedence 4 - in simulator - PartitionByIDTwiceAllCollations - Routing - true - Process - RPL_Fast`() {
        simulatorHelper(
            "src/luposdate3000_simulator_db/src/jvmTest/resources/autoIntegrationTest/test1.json",
            mutableMapOf(
                "predefinedPartitionScheme" to "PartitionByIDTwiceAllCollations",
                "mergeLocalOperatorgraphs" to true,
                "queryDistributionMode" to "Routing",
                "useDictionaryInlineEncoding" to true,
                "REPLACE_STORE_WITH_VALUES" to false,
                "LUPOS_PARTITION_MODE" to "Process",
            ),
            "RPL_Fast",
        )
    }
    public fun `pp33 Operator precedence 4 - in simulator - PartitionByID_2_AllCollations - Centralized - true - Process - RPL`() {
        simulatorHelper(
            "src/luposdate3000_simulator_db/src/jvmTest/resources/autoIntegrationTest/test1.json",
            mutableMapOf(
                "predefinedPartitionScheme" to "PartitionByID_2_AllCollations",
                "mergeLocalOperatorgraphs" to true,
                "queryDistributionMode" to "Centralized",
                "useDictionaryInlineEncoding" to true,
                "REPLACE_STORE_WITH_VALUES" to false,
                "LUPOS_PARTITION_MODE" to "Process",
            ),
            "RPL",
        )
    }
    public fun `pp33 Operator precedence 4 - in simulator - PartitionByID_2_AllCollations - Routing - true - Process - RPL`() {
        simulatorHelper(
            "src/luposdate3000_simulator_db/src/jvmTest/resources/autoIntegrationTest/test1.json",
            mutableMapOf(
                "predefinedPartitionScheme" to "PartitionByID_2_AllCollations",
                "mergeLocalOperatorgraphs" to true,
                "queryDistributionMode" to "Routing",
                "useDictionaryInlineEncoding" to true,
                "REPLACE_STORE_WITH_VALUES" to false,
                "LUPOS_PARTITION_MODE" to "Process",
            ),
            "RPL",
        )
    }
    public fun `pp33 Operator precedence 4 - in simulator - PartitionByID_2_AllCollations - Routing - false - Process - RPL`() {
        simulatorHelper(
            "src/luposdate3000_simulator_db/src/jvmTest/resources/autoIntegrationTest/test1.json",
            mutableMapOf(
                "predefinedPartitionScheme" to "PartitionByID_2_AllCollations",
                "mergeLocalOperatorgraphs" to true,
                "queryDistributionMode" to "Routing",
                "useDictionaryInlineEncoding" to false,
                "REPLACE_STORE_WITH_VALUES" to false,
                "LUPOS_PARTITION_MODE" to "Process",
            ),
            "RPL",
        )
    }
    public fun `pp33 Operator precedence 4 - in simulator - PartitionByID_O_AllCollations - Centralized - true - Process - AllShortestPath`() {
        simulatorHelper(
            "src/luposdate3000_simulator_db/src/jvmTest/resources/autoIntegrationTest/test1.json",
            mutableMapOf(
                "predefinedPartitionScheme" to "PartitionByID_O_AllCollations",
                "mergeLocalOperatorgraphs" to true,
                "queryDistributionMode" to "Centralized",
                "useDictionaryInlineEncoding" to true,
                "REPLACE_STORE_WITH_VALUES" to false,
                "LUPOS_PARTITION_MODE" to "Process",
            ),
            "AllShortestPath",
        )
    }
    public fun `pp33 Operator precedence 4 - in simulator - PartitionByID_S_AllCollations - Centralized - true - Process - AllShortestPath`() {
        simulatorHelper(
            "src/luposdate3000_simulator_db/src/jvmTest/resources/autoIntegrationTest/test1.json",
            mutableMapOf(
                "predefinedPartitionScheme" to "PartitionByID_S_AllCollations",
                "mergeLocalOperatorgraphs" to true,
                "queryDistributionMode" to "Centralized",
                "useDictionaryInlineEncoding" to true,
                "REPLACE_STORE_WITH_VALUES" to false,
                "LUPOS_PARTITION_MODE" to "Process",
            ),
            "AllShortestPath",
        )
    }
    public fun `pp33 Operator precedence 4 - in simulator - PartitionByID_S_AllCollations - Routing - false - Process - RPL`() {
        simulatorHelper(
            "src/luposdate3000_simulator_db/src/jvmTest/resources/autoIntegrationTest/test1.json",
            mutableMapOf(
                "predefinedPartitionScheme" to "PartitionByID_S_AllCollations",
                "mergeLocalOperatorgraphs" to true,
                "queryDistributionMode" to "Routing",
                "useDictionaryInlineEncoding" to false,
                "REPLACE_STORE_WITH_VALUES" to false,
                "LUPOS_PARTITION_MODE" to "Process",
            ),
            "RPL",
        )
    }
    public fun `pp33 Operator precedence 4 - in simulator - PartitionByKeyAllCollations - Centralized - false - Process - RPL_Fast`() {
        simulatorHelper(
            "src/luposdate3000_simulator_db/src/jvmTest/resources/autoIntegrationTest/test1.json",
            mutableMapOf(
                "predefinedPartitionScheme" to "PartitionByKeyAllCollations",
                "mergeLocalOperatorgraphs" to true,
                "queryDistributionMode" to "Centralized",
                "useDictionaryInlineEncoding" to false,
                "REPLACE_STORE_WITH_VALUES" to false,
                "LUPOS_PARTITION_MODE" to "Process",
            ),
            "RPL_Fast",
        )
    }
    public fun `pp33 Operator precedence 4 - in simulator - PartitionByKeyAllCollations - Routing - true - Process - RPL`() {
        simulatorHelper(
            "src/luposdate3000_simulator_db/src/jvmTest/resources/autoIntegrationTest/test1.json",
            mutableMapOf(
                "predefinedPartitionScheme" to "PartitionByKeyAllCollations",
                "mergeLocalOperatorgraphs" to true,
                "queryDistributionMode" to "Routing",
                "useDictionaryInlineEncoding" to true,
                "REPLACE_STORE_WITH_VALUES" to false,
                "LUPOS_PARTITION_MODE" to "Process",
            ),
            "RPL",
        )
    }
    public fun `pp33 Operator precedence 4 - in simulator - PartitionByKeyAllCollations - Routing - false - Process - RPL_Fast`() {
        simulatorHelper(
            "src/luposdate3000_simulator_db/src/jvmTest/resources/autoIntegrationTest/test1.json",
            mutableMapOf(
                "predefinedPartitionScheme" to "PartitionByKeyAllCollations",
                "mergeLocalOperatorgraphs" to true,
                "queryDistributionMode" to "Routing",
                "useDictionaryInlineEncoding" to false,
                "REPLACE_STORE_WITH_VALUES" to false,
                "LUPOS_PARTITION_MODE" to "Process",
            ),
            "RPL_Fast",
        )
    }
    public fun simulatorHelper(fileName:String,database_cfg:MutableMap<String,Any>,routingProtocol:String) {
        val simRun = SimulationRun()
        simRun.parseConfig(fileName,false,{
            it.getOrEmptyObject("deviceType").getOrEmptyObject("LUPOSDATE_DEVICE").getOrEmptyObject("applications").getOrEmptyObject("lupos.simulator_db.luposdate3000.ApplicationFactory_Luposdate3000").putAll(database_cfg)
            it.getOrEmptyObject("routing").putAll(mapOf("protocol" to routingProtocol))
        })
        
        
        
        simRun.startUp()
        val instance = (simRun.devices.map{it.getAllChildApplications()}.flatten().filter{it is Application_Luposdate3000}.first()as Application_Luposdate3000).instance
        val pkg0 = Package_Luposdate3000_TestingImportPackage(inputDataFile[0], inputGraph[0], inputType[0])
        var verifyExecuted1 = 0
        val pkg1 = Package_Luposdate3000_TestingCompareGraphPackage(null,MemoryTable.parseFromAny(inputData[0], inputType[0], Query(instance))!!, {verifyExecuted1++},inputGraph[0],instance)
        pkg0.setOnFinish(pkg1)
        var verifyExecuted2 = 0
        val pkg2 = Package_Luposdate3000_TestingCompareGraphPackage(query,MemoryTable.parseFromAny(targetData, targetType, Query(instance))!!, {verifyExecuted2++},"",instance)
        pkg1.setOnFinish(pkg2)
        simRun.addQuerySender(10,1,1,pkg0)
        simRun.run()
        simRun.shutDown()
        if (verifyExecuted1==0) {
            TODO("pck1 not verified")
        }
        if (verifyExecuted2==0) {
            TODO("pck2 not verified")
        }
    }
    internal fun normalHelper(instance:Luposdate3000Instance) {
        val buf = MyPrintWriter(false)
        if (listOf(".n3", ".ttl", ".nt").contains(inputType[0])) {
            LuposdateEndpoint.importTripleFileC(instance, inputDataFile[0],inputType[0], inputGraph[0])
        } else {
            TODO()
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
        val actual1 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator1, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected1 = MemoryTable.parseFromAny(targetData, targetType, Query(instance))!!
        val buf_err1 = MyPrintWriter()
        if (!expected1.equalsVerbose(actual1, true, true, false, buf_err1)) {
            TODO(expected1.toString() + " .. " + actual1.toString() + " .. " + buf_err1.toString() + " .. " + operator1)
        }
    }
    public fun getTests():Set<Pair<String,()->Unit>> {
        return setOf(
            "pp33 Operator precedence 4 - None - Simple - true" to ::`pp33 Operator precedence 4 - None - Simple - true`,
            "pp33 Operator precedence 4 - Thread - BenchmarkFig5 - true" to ::`pp33 Operator precedence 4 - Thread - BenchmarkFig5 - true`,
            "pp33 Operator precedence 4 - Thread - PartitionByIDTwiceAllCollations - false" to ::`pp33 Operator precedence 4 - Thread - PartitionByIDTwiceAllCollations - false`,
            "pp33 Operator precedence 4 - Thread - PartitionByID_O_AllCollations - true" to ::`pp33 Operator precedence 4 - Thread - PartitionByID_O_AllCollations - true`,
            "pp33 Operator precedence 4 - in simulator - BenchmarkFig5 - Centralized - false - Process - RPL" to ::`pp33 Operator precedence 4 - in simulator - BenchmarkFig5 - Centralized - false - Process - RPL`,
            "pp33 Operator precedence 4 - in simulator - BenchmarkFig5 - Centralized - false - Process - RPL_Fast" to ::`pp33 Operator precedence 4 - in simulator - BenchmarkFig5 - Centralized - false - Process - RPL_Fast`,
            "pp33 Operator precedence 4 - in simulator - BenchmarkFig5 - Routing - false - Process - AllShortestPath" to ::`pp33 Operator precedence 4 - in simulator - BenchmarkFig5 - Routing - false - Process - AllShortestPath`,
            "pp33 Operator precedence 4 - in simulator - PartitionByIDTwiceAllCollations - Centralized - false - Process - RPL" to ::`pp33 Operator precedence 4 - in simulator - PartitionByIDTwiceAllCollations - Centralized - false - Process - RPL`,
            "pp33 Operator precedence 4 - in simulator - PartitionByIDTwiceAllCollations - Routing - true - Process - RPL_Fast" to ::`pp33 Operator precedence 4 - in simulator - PartitionByIDTwiceAllCollations - Routing - true - Process - RPL_Fast`,
            "pp33 Operator precedence 4 - in simulator - PartitionByID_2_AllCollations - Centralized - true - Process - RPL" to ::`pp33 Operator precedence 4 - in simulator - PartitionByID_2_AllCollations - Centralized - true - Process - RPL`,
            "pp33 Operator precedence 4 - in simulator - PartitionByID_2_AllCollations - Routing - true - Process - RPL" to ::`pp33 Operator precedence 4 - in simulator - PartitionByID_2_AllCollations - Routing - true - Process - RPL`,
            "pp33 Operator precedence 4 - in simulator - PartitionByID_2_AllCollations - Routing - false - Process - RPL" to ::`pp33 Operator precedence 4 - in simulator - PartitionByID_2_AllCollations - Routing - false - Process - RPL`,
            "pp33 Operator precedence 4 - in simulator - PartitionByID_O_AllCollations - Centralized - true - Process - AllShortestPath" to ::`pp33 Operator precedence 4 - in simulator - PartitionByID_O_AllCollations - Centralized - true - Process - AllShortestPath`,
            "pp33 Operator precedence 4 - in simulator - PartitionByID_S_AllCollations - Centralized - true - Process - AllShortestPath" to ::`pp33 Operator precedence 4 - in simulator - PartitionByID_S_AllCollations - Centralized - true - Process - AllShortestPath`,
            "pp33 Operator precedence 4 - in simulator - PartitionByID_S_AllCollations - Routing - false - Process - RPL" to ::`pp33 Operator precedence 4 - in simulator - PartitionByID_S_AllCollations - Routing - false - Process - RPL`,
            "pp33 Operator precedence 4 - in simulator - PartitionByKeyAllCollations - Centralized - false - Process - RPL_Fast" to ::`pp33 Operator precedence 4 - in simulator - PartitionByKeyAllCollations - Centralized - false - Process - RPL_Fast`,
            "pp33 Operator precedence 4 - in simulator - PartitionByKeyAllCollations - Routing - true - Process - RPL" to ::`pp33 Operator precedence 4 - in simulator - PartitionByKeyAllCollations - Routing - true - Process - RPL`,
            "pp33 Operator precedence 4 - in simulator - PartitionByKeyAllCollations - Routing - false - Process - RPL_Fast" to ::`pp33 Operator precedence 4 - in simulator - PartitionByKeyAllCollations - Routing - false - Process - RPL_Fast`,
        )
    }
}
public fun main(){
    var idx=0
    var stop=false
    for((name,func) in pp33Operatorprecedence4().getTests()){
        if (stop){
            return
        }
        File("lupos.launch_code_gen_test_00.${name.replaceFirstChar { it.uppercase() }}.stat").withOutputStream{ out->
            out.println("started"+idx)
            try{
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
