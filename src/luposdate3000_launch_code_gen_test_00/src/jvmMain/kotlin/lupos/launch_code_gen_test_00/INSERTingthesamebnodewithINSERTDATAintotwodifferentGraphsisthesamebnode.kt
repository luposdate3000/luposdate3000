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


public class INSERTingthesamebnodewithINSERTDATAintotwodifferentGraphsisthesamebnode {
    internal val outputData = arrayOf(
        File("src/luposdate3000_launch_code_gen_test_00/src/jvmTest/resources/INSERTingthesamebnodewithINSERTDATAintotwodifferentGraphsisthesamebnode.output0").readAsString(),
    )
    internal val outputDataFile = arrayOf(
        "src/luposdate3000_launch_code_gen_test_00/src/jvmTest/resources/INSERTingthesamebnodewithINSERTDATAintotwodifferentGraphsisthesamebnode.output0",
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

    public fun `INSERTing the same bnode with INSERT DATA into two different Graphs is the same bnode - None - Simple - true`() {
      var instance = Luposdate3000Instance()
      try{
        instance.LUPOS_BUFFER_SIZE = 128
        instance.LUPOS_PARTITION_MODE=EPartitionModeExt.None
        instance.predefinedPartitionScheme=EPredefinedPartitionSchemesExt.Simple
        instance.useDictionaryInlineEncoding=true
        instance = LuposdateEndpoint.initializeB(instance)
        normalHelper(instance)
      }catch(e:Throwable){
        e.myPrintStackTraceAndThrowAgain(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_launch_code_gen_test_00/src/jvmMain/kotlin/lupos/launch_code_gen_test_00/INSERTingthesamebnodewithINSERTDATAintotwodifferentGraphsisthesamebnode.kt:84"/*SOURCE_FILE_END*/ ) //otherwise this would be silently ignored
      }finally{
        LuposdateEndpoint.close(instance)
      }
    }
    public fun `INSERTing the same bnode with INSERT DATA into two different Graphs is the same bnode - Thread - BenchmarkFig5 - false`() {
      var instance = Luposdate3000Instance()
      try{
        instance.LUPOS_BUFFER_SIZE = 128
        instance.LUPOS_PARTITION_MODE=EPartitionModeExt.Thread
        instance.predefinedPartitionScheme=EPredefinedPartitionSchemesExt.BenchmarkFig5
        instance.useDictionaryInlineEncoding=false
        instance = LuposdateEndpoint.initializeB(instance)
        normalHelper(instance)
      }catch(e:Throwable){
        e.myPrintStackTraceAndThrowAgain(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_launch_code_gen_test_00/src/jvmMain/kotlin/lupos/launch_code_gen_test_00/INSERTingthesamebnodewithINSERTDATAintotwodifferentGraphsisthesamebnode.kt:99"/*SOURCE_FILE_END*/ ) //otherwise this would be silently ignored
      }finally{
        LuposdateEndpoint.close(instance)
      }
    }
    public fun `INSERTing the same bnode with INSERT DATA into two different Graphs is the same bnode - Thread - PartitionByIDTwiceAllCollations - true`() {
      var instance = Luposdate3000Instance()
      try{
        instance.LUPOS_BUFFER_SIZE = 128
        instance.LUPOS_PARTITION_MODE=EPartitionModeExt.Thread
        instance.predefinedPartitionScheme=EPredefinedPartitionSchemesExt.PartitionByIDTwiceAllCollations
        instance.useDictionaryInlineEncoding=true
        instance = LuposdateEndpoint.initializeB(instance)
        normalHelper(instance)
      }catch(e:Throwable){
        e.myPrintStackTraceAndThrowAgain(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_launch_code_gen_test_00/src/jvmMain/kotlin/lupos/launch_code_gen_test_00/INSERTingthesamebnodewithINSERTDATAintotwodifferentGraphsisthesamebnode.kt:114"/*SOURCE_FILE_END*/ ) //otherwise this would be silently ignored
      }finally{
        LuposdateEndpoint.close(instance)
      }
    }
    public fun `INSERTing the same bnode with INSERT DATA into two different Graphs is the same bnode - in simulator - Simple - Centralized - true - None - RPL`() {
        simulatorHelper(
            "src/luposdate3000_simulator_db/src/jvmTest/resources/autoIntegrationTest/test2.json",
            mutableMapOf(
                "predefinedPartitionScheme" to "Simple",
                "mergeLocalOperatorgraphs" to true,
                "queryDistributionMode" to "Centralized",
                "useDictionaryInlineEncoding" to true,
                "REPLACE_STORE_WITH_VALUES" to false,
                "LUPOS_PARTITION_MODE" to "None",
            ),
            "RPL",
        )
    }
    public fun `INSERTing the same bnode with INSERT DATA into two different Graphs is the same bnode - in simulator - PartitionByIDTwiceAllCollations - Centralized - false - Process - AllShortestPath`() {
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
            "AllShortestPath",
        )
    }
    public fun `INSERTing the same bnode with INSERT DATA into two different Graphs is the same bnode - in simulator - PartitionByID_1_AllCollations - Centralized - false - Process - RPL`() {
        simulatorHelper(
            "src/luposdate3000_simulator_db/src/jvmTest/resources/autoIntegrationTest/test1.json",
            mutableMapOf(
                "predefinedPartitionScheme" to "PartitionByID_1_AllCollations",
                "mergeLocalOperatorgraphs" to true,
                "queryDistributionMode" to "Centralized",
                "useDictionaryInlineEncoding" to false,
                "REPLACE_STORE_WITH_VALUES" to false,
                "LUPOS_PARTITION_MODE" to "Process",
            ),
            "RPL",
        )
    }
    public fun `INSERTing the same bnode with INSERT DATA into two different Graphs is the same bnode - in simulator - PartitionByID_2_AllCollations - Centralized - true - Process - RPL_Fast`() {
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
            "RPL_Fast",
        )
    }
    public fun `INSERTing the same bnode with INSERT DATA into two different Graphs is the same bnode - in simulator - PartitionByID_O_AllCollations - Centralized - true - Process - AllShortestPath`() {
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
    public fun `INSERTing the same bnode with INSERT DATA into two different Graphs is the same bnode - in simulator - PartitionByID_O_AllCollations - Routing - true - Process - RPL_Fast`() {
        simulatorHelper(
            "src/luposdate3000_simulator_db/src/jvmTest/resources/autoIntegrationTest/test1.json",
            mutableMapOf(
                "predefinedPartitionScheme" to "PartitionByID_O_AllCollations",
                "mergeLocalOperatorgraphs" to true,
                "queryDistributionMode" to "Routing",
                "useDictionaryInlineEncoding" to true,
                "REPLACE_STORE_WITH_VALUES" to false,
                "LUPOS_PARTITION_MODE" to "Process",
            ),
            "RPL_Fast",
        )
    }
    public fun `INSERTing the same bnode with INSERT DATA into two different Graphs is the same bnode - in simulator - PartitionByID_S_AllCollations - Centralized - true - Process - RPL_Fast`() {
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
        val pkg0 = Package_Luposdate3000_TestingExecute(query)
        var verifyExecuted1 = 0
        val pkg1 = Package_Luposdate3000_TestingCompareGraphPackage(null,MemoryTable.parseFromAny(outputData[0], outputType[0], Query(instance))!!, {verifyExecuted1++},outputGraph[0],instance)
        pkg0.setOnFinish(pkg1)
        simRun.addQuerySender(10,1,1,pkg0)
        simRun.run()
        simRun.shutDown()
        if (verifyExecuted1==0) {
            TODO("pck1 not verified")
        }
    }
    internal fun normalHelper(instance:Luposdate3000Instance) {
        val buf = MyPrintWriter(false)
        val operator0 = LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, query)
        LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator0, buf, EQueryResultToStreamExt.EMPTY_STREAM)
        val query1 = Query(instance)
        val graph1 = instance.tripleStoreManager!!.getGraph(outputGraph[0])
        val iterator1 = graph1.getIterator(query1, arrayOf(AOPVariable(query1, "s"), AOPVariable(query1, "p"), AOPVariable(query1, "o")), EIndexPatternExt.SPO)
        val operator1 = PhysicalOptimizer(query1).optimizeCall(iterator1)
        val actual1 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator1, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val expected1 = MemoryTable.parseFromAny(outputData[0], outputType[0], Query(instance))!!
        val buf_err1 = MyPrintWriter()
        if (!expected1.equalsVerbose(actual1, true, true, false, buf_err1)) {
            TODO(expected1.toString() + " .. " + actual1.toString() + " .. " + buf_err1.toString() + " .. " + operator1)
        }
    }
    public fun getTests():Set<Pair<String,()->Unit>> {
        return setOf(
            "INSERTing the same bnode with INSERT DATA into two different Graphs is the same bnode - None - Simple - true" to ::`INSERTing the same bnode with INSERT DATA into two different Graphs is the same bnode - None - Simple - true`,
            "INSERTing the same bnode with INSERT DATA into two different Graphs is the same bnode - Thread - BenchmarkFig5 - false" to ::`INSERTing the same bnode with INSERT DATA into two different Graphs is the same bnode - Thread - BenchmarkFig5 - false`,
            "INSERTing the same bnode with INSERT DATA into two different Graphs is the same bnode - Thread - PartitionByIDTwiceAllCollations - true" to ::`INSERTing the same bnode with INSERT DATA into two different Graphs is the same bnode - Thread - PartitionByIDTwiceAllCollations - true`,
            "INSERTing the same bnode with INSERT DATA into two different Graphs is the same bnode - in simulator - Simple - Centralized - true - None - RPL" to ::`INSERTing the same bnode with INSERT DATA into two different Graphs is the same bnode - in simulator - Simple - Centralized - true - None - RPL`,
            "INSERTing the same bnode with INSERT DATA into two different Graphs is the same bnode - in simulator - PartitionByIDTwiceAllCollations - Centralized - false - Process - AllShortestPath" to ::`INSERTing the same bnode with INSERT DATA into two different Graphs is the same bnode - in simulator - PartitionByIDTwiceAllCollations - Centralized - false - Process - AllShortestPath`,
            "INSERTing the same bnode with INSERT DATA into two different Graphs is the same bnode - in simulator - PartitionByID_1_AllCollations - Centralized - false - Process - RPL" to ::`INSERTing the same bnode with INSERT DATA into two different Graphs is the same bnode - in simulator - PartitionByID_1_AllCollations - Centralized - false - Process - RPL`,
            "INSERTing the same bnode with INSERT DATA into two different Graphs is the same bnode - in simulator - PartitionByID_2_AllCollations - Centralized - true - Process - RPL_Fast" to ::`INSERTing the same bnode with INSERT DATA into two different Graphs is the same bnode - in simulator - PartitionByID_2_AllCollations - Centralized - true - Process - RPL_Fast`,
            "INSERTing the same bnode with INSERT DATA into two different Graphs is the same bnode - in simulator - PartitionByID_O_AllCollations - Centralized - true - Process - AllShortestPath" to ::`INSERTing the same bnode with INSERT DATA into two different Graphs is the same bnode - in simulator - PartitionByID_O_AllCollations - Centralized - true - Process - AllShortestPath`,
            "INSERTing the same bnode with INSERT DATA into two different Graphs is the same bnode - in simulator - PartitionByID_O_AllCollations - Routing - true - Process - RPL_Fast" to ::`INSERTing the same bnode with INSERT DATA into two different Graphs is the same bnode - in simulator - PartitionByID_O_AllCollations - Routing - true - Process - RPL_Fast`,
            "INSERTing the same bnode with INSERT DATA into two different Graphs is the same bnode - in simulator - PartitionByID_S_AllCollations - Centralized - true - Process - RPL_Fast" to ::`INSERTing the same bnode with INSERT DATA into two different Graphs is the same bnode - in simulator - PartitionByID_S_AllCollations - Centralized - true - Process - RPL_Fast`,
        )
    }
}
public fun main(){
    var idx=0
    var stop=false
    for((name,func) in INSERTingthesamebnodewithINSERTDATAintotwodifferentGraphsisthesamebnode().getTests()){
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
