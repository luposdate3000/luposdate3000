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


public class constructwhere04CONSTRUCTWHERE {
    internal val targetData = File("src/luposdate3000_launch_code_gen_test_00/src/jvmTest/resources/constructwhere04CONSTRUCTWHERE.output").readAsString()
    internal val targetType = ".ttl"
    internal val query = "PREFIX : <http://example.org/> \n" +
        "CONSTRUCT  \n" +
        "FROM <data.ttl> \n" +
        "WHERE { ?s ?p ?o }"

    public fun `constructwhere04  CONSTRUCT WHERE - in simulator - Simple - Centralized - false - None - RPL`() {
        simulatorHelper(
            "src/luposdate3000_simulator_db/src/jvmTest/resources/autoIntegrationTest/test2.json",
            mutableMapOf(
                "predefinedPartitionScheme" to "Simple",
                "mergeLocalOperatorgraphs" to true,
                "queryDistributionMode" to "Centralized",
                "useDictionaryInlineEncoding" to false,
                "REPLACE_STORE_WITH_VALUES" to false,
                "LUPOS_PARTITION_MODE" to "None",
            ),
            "RPL",
        )
    }
    public fun `constructwhere04  CONSTRUCT WHERE - in simulator - BenchmarkFig5 - Centralized - true - Process - RPL_Fast`() {
        simulatorHelper(
            "src/luposdate3000_simulator_db/src/jvmTest/resources/autoIntegrationTest/test1.json",
            mutableMapOf(
                "predefinedPartitionScheme" to "BenchmarkFig5",
                "mergeLocalOperatorgraphs" to true,
                "queryDistributionMode" to "Centralized",
                "useDictionaryInlineEncoding" to true,
                "REPLACE_STORE_WITH_VALUES" to false,
                "LUPOS_PARTITION_MODE" to "Process",
            ),
            "RPL_Fast",
        )
    }
    public fun `constructwhere04  CONSTRUCT WHERE - in simulator - BenchmarkFig5 - Centralized - false - Process - RPL_Fast`() {
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
    public fun `constructwhere04  CONSTRUCT WHERE - in simulator - PartitionByIDTwiceAllCollations - Centralized - true - Process - RPL`() {
        simulatorHelper(
            "src/luposdate3000_simulator_db/src/jvmTest/resources/autoIntegrationTest/test1.json",
            mutableMapOf(
                "predefinedPartitionScheme" to "PartitionByIDTwiceAllCollations",
                "mergeLocalOperatorgraphs" to true,
                "queryDistributionMode" to "Centralized",
                "useDictionaryInlineEncoding" to true,
                "REPLACE_STORE_WITH_VALUES" to false,
                "LUPOS_PARTITION_MODE" to "Process",
            ),
            "RPL",
        )
    }
    public fun `constructwhere04  CONSTRUCT WHERE - in simulator - PartitionByIDTwiceAllCollations - Centralized - false - Process - RPL_Fast`() {
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
            "RPL_Fast",
        )
    }
    public fun `constructwhere04  CONSTRUCT WHERE - in simulator - PartitionByIDTwiceAllCollations - Routing - true - Process - RPL_Fast`() {
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
    public fun `constructwhere04  CONSTRUCT WHERE - in simulator - PartitionByIDTwiceAllCollations - Routing - false - Process - AllShortestPath`() {
        simulatorHelper(
            "src/luposdate3000_simulator_db/src/jvmTest/resources/autoIntegrationTest/test1.json",
            mutableMapOf(
                "predefinedPartitionScheme" to "PartitionByIDTwiceAllCollations",
                "mergeLocalOperatorgraphs" to true,
                "queryDistributionMode" to "Routing",
                "useDictionaryInlineEncoding" to false,
                "REPLACE_STORE_WITH_VALUES" to false,
                "LUPOS_PARTITION_MODE" to "Process",
            ),
            "AllShortestPath",
        )
    }
    public fun `constructwhere04  CONSTRUCT WHERE - in simulator - PartitionByID_1_AllCollations - Routing - true - Process - RPL_Fast`() {
        simulatorHelper(
            "src/luposdate3000_simulator_db/src/jvmTest/resources/autoIntegrationTest/test1.json",
            mutableMapOf(
                "predefinedPartitionScheme" to "PartitionByID_1_AllCollations",
                "mergeLocalOperatorgraphs" to true,
                "queryDistributionMode" to "Routing",
                "useDictionaryInlineEncoding" to true,
                "REPLACE_STORE_WITH_VALUES" to false,
                "LUPOS_PARTITION_MODE" to "Process",
            ),
            "RPL_Fast",
        )
    }
    public fun `constructwhere04  CONSTRUCT WHERE - in simulator - PartitionByID_2_AllCollations - Routing - false - Process - AllShortestPath`() {
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
            "AllShortestPath",
        )
    }
    public fun `constructwhere04  CONSTRUCT WHERE - in simulator - PartitionByID_O_AllCollations - Centralized - false - Process - RPL`() {
        simulatorHelper(
            "src/luposdate3000_simulator_db/src/jvmTest/resources/autoIntegrationTest/test1.json",
            mutableMapOf(
                "predefinedPartitionScheme" to "PartitionByID_O_AllCollations",
                "mergeLocalOperatorgraphs" to true,
                "queryDistributionMode" to "Centralized",
                "useDictionaryInlineEncoding" to false,
                "REPLACE_STORE_WITH_VALUES" to false,
                "LUPOS_PARTITION_MODE" to "Process",
            ),
            "RPL",
        )
    }
    public fun `constructwhere04  CONSTRUCT WHERE - in simulator - PartitionByID_O_AllCollations - Routing - false - Process - RPL_Fast`() {
        simulatorHelper(
            "src/luposdate3000_simulator_db/src/jvmTest/resources/autoIntegrationTest/test1.json",
            mutableMapOf(
                "predefinedPartitionScheme" to "PartitionByID_O_AllCollations",
                "mergeLocalOperatorgraphs" to true,
                "queryDistributionMode" to "Routing",
                "useDictionaryInlineEncoding" to false,
                "REPLACE_STORE_WITH_VALUES" to false,
                "LUPOS_PARTITION_MODE" to "Process",
            ),
            "RPL_Fast",
        )
    }
    public fun `constructwhere04  CONSTRUCT WHERE - in simulator - PartitionByID_S_AllCollations - Centralized - true - Process - RPL_Fast`() {
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
    public fun `constructwhere04  CONSTRUCT WHERE - in simulator - PartitionByID_S_AllCollations - Centralized - true - Process - AllShortestPath`() {
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
    public fun `constructwhere04  CONSTRUCT WHERE - in simulator - PartitionByID_S_AllCollations - Routing - true - Process - RPL`() {
        simulatorHelper(
            "src/luposdate3000_simulator_db/src/jvmTest/resources/autoIntegrationTest/test1.json",
            mutableMapOf(
                "predefinedPartitionScheme" to "PartitionByID_S_AllCollations",
                "mergeLocalOperatorgraphs" to true,
                "queryDistributionMode" to "Routing",
                "useDictionaryInlineEncoding" to true,
                "REPLACE_STORE_WITH_VALUES" to false,
                "LUPOS_PARTITION_MODE" to "Process",
            ),
            "RPL",
        )
    }
    public fun `constructwhere04  CONSTRUCT WHERE - in simulator - PartitionByID_S_AllCollations - Routing - false - Process - AllShortestPath`() {
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
            "AllShortestPath",
        )
    }
    public fun `constructwhere04  CONSTRUCT WHERE - in simulator - PartitionByKeyAllCollations - Centralized - true - Process - RPL_Fast`() {
        simulatorHelper(
            "src/luposdate3000_simulator_db/src/jvmTest/resources/autoIntegrationTest/test1.json",
            mutableMapOf(
                "predefinedPartitionScheme" to "PartitionByKeyAllCollations",
                "mergeLocalOperatorgraphs" to true,
                "queryDistributionMode" to "Centralized",
                "useDictionaryInlineEncoding" to true,
                "REPLACE_STORE_WITH_VALUES" to false,
                "LUPOS_PARTITION_MODE" to "Process",
            ),
            "RPL_Fast",
        )
    }
    public fun `constructwhere04  CONSTRUCT WHERE - in simulator - PartitionByKeyAllCollations - Routing - true - Process - RPL`() {
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
    public fun `constructwhere04  CONSTRUCT WHERE - in simulator - PartitionByKeyAllCollations - Routing - false - Process - RPL_Fast`() {
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
    public fun `constructwhere04  CONSTRUCT WHERE - in simulator - PartitionByKeyAllCollations - Routing - false - Process - AllShortestPath`() {
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
            "AllShortestPath",
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
        var verifyExecuted0 = 0
        val pkg0 = Package_Luposdate3000_TestingCompareGraphPackage(query,MemoryTable.parseFromAny(targetData, targetType, Query(instance))!!, {verifyExecuted0++},"",instance)
        simRun.addQuerySender(10,1,1,pkg0)
        simRun.run()
        simRun.shutDown()
        if (verifyExecuted0==0) {
            TODO("pck0 not verified")
        }
    }
    public fun getTests():Set<Pair<String,()->Unit>> {
        return setOf(
            "constructwhere04  CONSTRUCT WHERE - in simulator - Simple - Centralized - false - None - RPL" to ::`constructwhere04  CONSTRUCT WHERE - in simulator - Simple - Centralized - false - None - RPL`,
            "constructwhere04  CONSTRUCT WHERE - in simulator - BenchmarkFig5 - Centralized - true - Process - RPL_Fast" to ::`constructwhere04  CONSTRUCT WHERE - in simulator - BenchmarkFig5 - Centralized - true - Process - RPL_Fast`,
            "constructwhere04  CONSTRUCT WHERE - in simulator - BenchmarkFig5 - Centralized - false - Process - RPL_Fast" to ::`constructwhere04  CONSTRUCT WHERE - in simulator - BenchmarkFig5 - Centralized - false - Process - RPL_Fast`,
            "constructwhere04  CONSTRUCT WHERE - in simulator - PartitionByIDTwiceAllCollations - Centralized - true - Process - RPL" to ::`constructwhere04  CONSTRUCT WHERE - in simulator - PartitionByIDTwiceAllCollations - Centralized - true - Process - RPL`,
            "constructwhere04  CONSTRUCT WHERE - in simulator - PartitionByIDTwiceAllCollations - Centralized - false - Process - RPL_Fast" to ::`constructwhere04  CONSTRUCT WHERE - in simulator - PartitionByIDTwiceAllCollations - Centralized - false - Process - RPL_Fast`,
            "constructwhere04  CONSTRUCT WHERE - in simulator - PartitionByIDTwiceAllCollations - Routing - true - Process - RPL_Fast" to ::`constructwhere04  CONSTRUCT WHERE - in simulator - PartitionByIDTwiceAllCollations - Routing - true - Process - RPL_Fast`,
            "constructwhere04  CONSTRUCT WHERE - in simulator - PartitionByIDTwiceAllCollations - Routing - false - Process - AllShortestPath" to ::`constructwhere04  CONSTRUCT WHERE - in simulator - PartitionByIDTwiceAllCollations - Routing - false - Process - AllShortestPath`,
            "constructwhere04  CONSTRUCT WHERE - in simulator - PartitionByID_1_AllCollations - Routing - true - Process - RPL_Fast" to ::`constructwhere04  CONSTRUCT WHERE - in simulator - PartitionByID_1_AllCollations - Routing - true - Process - RPL_Fast`,
            "constructwhere04  CONSTRUCT WHERE - in simulator - PartitionByID_2_AllCollations - Routing - false - Process - AllShortestPath" to ::`constructwhere04  CONSTRUCT WHERE - in simulator - PartitionByID_2_AllCollations - Routing - false - Process - AllShortestPath`,
            "constructwhere04  CONSTRUCT WHERE - in simulator - PartitionByID_O_AllCollations - Centralized - false - Process - RPL" to ::`constructwhere04  CONSTRUCT WHERE - in simulator - PartitionByID_O_AllCollations - Centralized - false - Process - RPL`,
            "constructwhere04  CONSTRUCT WHERE - in simulator - PartitionByID_O_AllCollations - Routing - false - Process - RPL_Fast" to ::`constructwhere04  CONSTRUCT WHERE - in simulator - PartitionByID_O_AllCollations - Routing - false - Process - RPL_Fast`,
            "constructwhere04  CONSTRUCT WHERE - in simulator - PartitionByID_S_AllCollations - Centralized - true - Process - RPL_Fast" to ::`constructwhere04  CONSTRUCT WHERE - in simulator - PartitionByID_S_AllCollations - Centralized - true - Process - RPL_Fast`,
            "constructwhere04  CONSTRUCT WHERE - in simulator - PartitionByID_S_AllCollations - Centralized - true - Process - AllShortestPath" to ::`constructwhere04  CONSTRUCT WHERE - in simulator - PartitionByID_S_AllCollations - Centralized - true - Process - AllShortestPath`,
            "constructwhere04  CONSTRUCT WHERE - in simulator - PartitionByID_S_AllCollations - Routing - true - Process - RPL" to ::`constructwhere04  CONSTRUCT WHERE - in simulator - PartitionByID_S_AllCollations - Routing - true - Process - RPL`,
            "constructwhere04  CONSTRUCT WHERE - in simulator - PartitionByID_S_AllCollations - Routing - false - Process - AllShortestPath" to ::`constructwhere04  CONSTRUCT WHERE - in simulator - PartitionByID_S_AllCollations - Routing - false - Process - AllShortestPath`,
            "constructwhere04  CONSTRUCT WHERE - in simulator - PartitionByKeyAllCollations - Centralized - true - Process - RPL_Fast" to ::`constructwhere04  CONSTRUCT WHERE - in simulator - PartitionByKeyAllCollations - Centralized - true - Process - RPL_Fast`,
            "constructwhere04  CONSTRUCT WHERE - in simulator - PartitionByKeyAllCollations - Routing - true - Process - RPL" to ::`constructwhere04  CONSTRUCT WHERE - in simulator - PartitionByKeyAllCollations - Routing - true - Process - RPL`,
            "constructwhere04  CONSTRUCT WHERE - in simulator - PartitionByKeyAllCollations - Routing - false - Process - RPL_Fast" to ::`constructwhere04  CONSTRUCT WHERE - in simulator - PartitionByKeyAllCollations - Routing - false - Process - RPL_Fast`,
            "constructwhere04  CONSTRUCT WHERE - in simulator - PartitionByKeyAllCollations - Routing - false - Process - AllShortestPath" to ::`constructwhere04  CONSTRUCT WHERE - in simulator - PartitionByKeyAllCollations - Routing - false - Process - AllShortestPath`,
        )
    }
}
public fun main(){
    var idx=0
    var stop=false
    for((name,func) in constructwhere04CONSTRUCTWHERE().getTests()){
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
