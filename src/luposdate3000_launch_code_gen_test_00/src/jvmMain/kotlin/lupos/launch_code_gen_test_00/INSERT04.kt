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


public class INSERT04 {
    internal val inputData = arrayOf(
        File("src/luposdate3000_launch_code_gen_test_00/src/jvmTest/resources/INSERT04.input").readAsString(),
        File("src/luposdate3000_launch_code_gen_test_00/src/jvmTest/resources/INSERT04.input1").readAsString(),
    )
    internal val inputDataFile = arrayOf(
        "src/luposdate3000_launch_code_gen_test_00/src/jvmTest/resources/INSERT04.input",
        "src/luposdate3000_launch_code_gen_test_00/src/jvmTest/resources/INSERT04.input1",
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
        File("src/luposdate3000_launch_code_gen_test_00/src/jvmTest/resources/INSERT04.output0").readAsString(),
        File("src/luposdate3000_launch_code_gen_test_00/src/jvmTest/resources/INSERT04.output1").readAsString(),
    )
    internal val outputDataFile = arrayOf(
        "src/luposdate3000_launch_code_gen_test_00/src/jvmTest/resources/INSERT04.output0",
        "src/luposdate3000_launch_code_gen_test_00/src/jvmTest/resources/INSERT04.output1",
    )
    internal val outputGraph = arrayOf(
        "",
        "http://example.org/g1",
    )
    internal val outputType = arrayOf(
        ".ttl",
        ".ttl",
    )
    internal val query = "PREFIX     : <http://example.org/>  \n" +
        "INSERT { \n" +
        " ?s ?p \"q\" \n" +
        "} \n" +
        "USING :g1 \n" +
        "WHERE { \n" +
        " ?s ?p ?o \n" +
        "} \n" +
        ""

    public fun `INSERT 04 - in simulator - BenchmarkFig5 - Routing - true - Process - RPL_Fast`() {
        simulatorHelper(
            "src/luposdate3000_simulator_db/src/jvmTest/resources/autoIntegrationTest/test1.json",
            mutableMapOf(
                "predefinedPartitionScheme" to "BenchmarkFig5",
                "mergeLocalOperatorgraphs" to true,
                "queryDistributionMode" to "Routing",
                "useDictionaryInlineEncoding" to true,
                "REPLACE_STORE_WITH_VALUES" to false,
                "LUPOS_PARTITION_MODE" to "Process",
            ),
            "RPL_Fast",
        )
    }
    public fun `INSERT 04 - in simulator - PartitionByID_1_AllCollations - Centralized - false - Process - RPL`() {
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
    public fun simulatorHelper(fileName:String,database_cfg:MutableMap<String,Any>,routingProtocol:String) {
        val simRun = SimulationRun()
        simRun.parseConfig(fileName,false,{
            it.getOrEmptyObject("deviceType").getOrEmptyObject("LUPOSDATE_DEVICE").getOrEmptyObject("applications").getOrEmptyObject("lupos.simulator_db.luposdate3000.ApplicationFactory_Luposdate3000").putAll(database_cfg)
            it.getOrEmptyObject("routing").putAll(mapOf("protocol" to routingProtocol))
        })
        
        
        
        simRun.startUp()
        val instance = (simRun.devices.map{it.getAllChildApplications()}.flatten().filter{it is Application_Luposdate3000}.first()as Application_Luposdate3000).instance
        val pkg0 = Package_Luposdate3000_TestingImportPackage(inputDataFile[0], inputGraph[0], inputType[0])
        val pkg1 = Package_Luposdate3000_TestingImportPackage(inputDataFile[1], inputGraph[1], inputType[1])
        pkg0.setOnFinish(pkg1)
        var verifyExecuted2 = 0
        val pkg2 = Package_Luposdate3000_TestingCompareGraphPackage(null,MemoryTable.parseFromAny(inputData[0], inputType[0], Query(instance))!!, {verifyExecuted2++},inputGraph[0],instance)
        pkg1.setOnFinish(pkg2)
        var verifyExecuted3 = 0
        val pkg3 = Package_Luposdate3000_TestingCompareGraphPackage(null,MemoryTable.parseFromAny(inputData[1], inputType[1], Query(instance))!!, {verifyExecuted3++},inputGraph[1],instance)
        pkg2.setOnFinish(pkg3)
        val pkg4 = Package_Luposdate3000_TestingExecute(query)
        pkg3.setOnFinish(pkg4)
        var verifyExecuted5 = 0
        val pkg5 = Package_Luposdate3000_TestingCompareGraphPackage(null,MemoryTable.parseFromAny(outputData[0], outputType[0], Query(instance))!!, {verifyExecuted5++},outputGraph[0],instance)
        pkg4.setOnFinish(pkg5)
        var verifyExecuted6 = 0
        val pkg6 = Package_Luposdate3000_TestingCompareGraphPackage(null,MemoryTable.parseFromAny(outputData[1], outputType[1], Query(instance))!!, {verifyExecuted6++},outputGraph[1],instance)
        pkg5.setOnFinish(pkg6)
        simRun.addQuerySender(10,1,1,pkg0)
        simRun.run()
        simRun.shutDown()
        if (verifyExecuted2==0) {
            TODO("pck2 not verified")
        }
        if (verifyExecuted3==0) {
            TODO("pck3 not verified")
        }
        if (verifyExecuted5==0) {
            TODO("pck5 not verified")
        }
        if (verifyExecuted6==0) {
            TODO("pck6 not verified")
        }
    }
    public fun getTests():Set<Pair<String,()->Unit>> {
        return setOf(
            "INSERT 04 - in simulator - BenchmarkFig5 - Routing - true - Process - RPL_Fast" to ::`INSERT 04 - in simulator - BenchmarkFig5 - Routing - true - Process - RPL_Fast`,
            "INSERT 04 - in simulator - PartitionByID_1_AllCollations - Centralized - false - Process - RPL" to ::`INSERT 04 - in simulator - PartitionByID_1_AllCollations - Centralized - false - Process - RPL`,
        )
    }
}
public fun main(){
    for((name,func) in INSERT04().getTests()){
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
