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


public class sparqldl07rqtwodistinguishedvariablesundist {
    internal val inputData = arrayOf(
        File("src/luposdate3000_launch_code_gen_test_00/src/jvmTest/resources/sparqldl07rqtwodistinguishedvariablesundist.input").readAsString(),
    )
    internal val inputDataFile = arrayOf(
        "src/luposdate3000_launch_code_gen_test_00/src/jvmTest/resources/sparqldl07rqtwodistinguishedvariablesundist.input",
    )
    internal val inputGraph = arrayOf(
        "",
    )
    internal val inputType = arrayOf(
        ".ttl",
    )
    internal val targetData = File("src/luposdate3000_launch_code_gen_test_00/src/jvmTest/resources/sparqldl07rqtwodistinguishedvariablesundist.output").readAsString()
    internal val targetType = ".srx"
    internal val query = "# Query6 - cycle with undistinguished variables \n" +
        "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> \n" +
        "PREFIX : <http://example.org/test#> \n" +
        "SELECT * \n" +
        "WHERE \n" +
        "{:a :p _:aa. \n" +
        " ?X :t ?Y. \n" +
        " ?Y :s _:aa. \n" +
        " _:aa :r ?Z.}"

    public fun `sparqldl07rq two distinguished variables  undist - in simulator - Simple - Centralized - true - None - RPL_Fast`() {
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
            "RPL_Fast",
        )
    }
    public fun `sparqldl07rq two distinguished variables  undist - in simulator - PartitionByID_O_AllCollations - Routing - true - Process - AllShortestPath`() {
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
            "AllShortestPath",
        )
    }
    public fun `sparqldl07rq two distinguished variables  undist - in simulator - PartitionByID_S_AllCollations - Centralized - true - Process - RPL_Fast`() {
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
    public fun getTests():Set<Pair<String,()->Unit>> {
        return setOf(
            "sparqldl07rq two distinguished variables  undist - in simulator - Simple - Centralized - true - None - RPL_Fast" to ::`sparqldl07rq two distinguished variables  undist - in simulator - Simple - Centralized - true - None - RPL_Fast`,
            "sparqldl07rq two distinguished variables  undist - in simulator - PartitionByID_O_AllCollations - Routing - true - Process - AllShortestPath" to ::`sparqldl07rq two distinguished variables  undist - in simulator - PartitionByID_O_AllCollations - Routing - true - Process - AllShortestPath`,
            "sparqldl07rq two distinguished variables  undist - in simulator - PartitionByID_S_AllCollations - Centralized - true - Process - RPL_Fast" to ::`sparqldl07rq two distinguished variables  undist - in simulator - PartitionByID_S_AllCollations - Centralized - true - Process - RPL_Fast`,
        )
    }
}
public fun main(){
    for((name,func) in sparqldl07rqtwodistinguishedvariablesundist().getTests()){
        File("lupos.launch_code_gen_test_00.${name.replaceFirstChar { it.uppercase() }}.stat").withOutputStream{ out->
            out.println("started")
            try{
                func()
                out.println("passed")
            }catch(e:Error){
                out.println("failed")
                e.printStackTrace()
            }
        }
    }
}
