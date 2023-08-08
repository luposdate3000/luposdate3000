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


public class SimpleDELETE4USING {
    internal val inputData = arrayOf(
        File("src/luposdate3000_launch_code_gen_test_00/src/jvmTest/resources/SimpleDELETE4USING.input").readAsString(),
        File("src/luposdate3000_launch_code_gen_test_00/src/jvmTest/resources/SimpleDELETE4USING.input1").readAsString(),
        File("src/luposdate3000_launch_code_gen_test_00/src/jvmTest/resources/SimpleDELETE4USING.input2").readAsString(),
    )
    internal val inputDataFile = arrayOf(
        "src/luposdate3000_launch_code_gen_test_00/src/jvmTest/resources/SimpleDELETE4USING.input",
        "src/luposdate3000_launch_code_gen_test_00/src/jvmTest/resources/SimpleDELETE4USING.input1",
        "src/luposdate3000_launch_code_gen_test_00/src/jvmTest/resources/SimpleDELETE4USING.input2",
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
        File("src/luposdate3000_launch_code_gen_test_00/src/jvmTest/resources/SimpleDELETE4USING.output0").readAsString(),
        File("src/luposdate3000_launch_code_gen_test_00/src/jvmTest/resources/SimpleDELETE4USING.output1").readAsString(),
        File("src/luposdate3000_launch_code_gen_test_00/src/jvmTest/resources/SimpleDELETE4USING.output2").readAsString(),
    )
    internal val outputDataFile = arrayOf(
        "src/luposdate3000_launch_code_gen_test_00/src/jvmTest/resources/SimpleDELETE4USING.output0",
        "src/luposdate3000_launch_code_gen_test_00/src/jvmTest/resources/SimpleDELETE4USING.output1",
        "src/luposdate3000_launch_code_gen_test_00/src/jvmTest/resources/SimpleDELETE4USING.output2",
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

    public fun `Simple DELETE 4 USING - in simulator - PartitionByID_1_AllCollations - Routing - true - Process - AllShortestPath`() {
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
        val pkg0 = Package_Luposdate3000_TestingImportPackage(inputDataFile[0], inputGraph[0], inputType[0])
        val pkg1 = Package_Luposdate3000_TestingImportPackage(inputDataFile[1], inputGraph[1], inputType[1])
        pkg0.setOnFinish(pkg1)
        val pkg2 = Package_Luposdate3000_TestingImportPackage(inputDataFile[2], inputGraph[2], inputType[2])
        pkg1.setOnFinish(pkg2)
        var verifyExecuted3 = 0
        val pkg3 = Package_Luposdate3000_TestingCompareGraphPackage(null,MemoryTable.parseFromAny(inputData[0], inputType[0], Query(instance))!!, {verifyExecuted3++},inputGraph[0],instance)
        pkg2.setOnFinish(pkg3)
        var verifyExecuted4 = 0
        val pkg4 = Package_Luposdate3000_TestingCompareGraphPackage(null,MemoryTable.parseFromAny(inputData[1], inputType[1], Query(instance))!!, {verifyExecuted4++},inputGraph[1],instance)
        pkg3.setOnFinish(pkg4)
        var verifyExecuted5 = 0
        val pkg5 = Package_Luposdate3000_TestingCompareGraphPackage(null,MemoryTable.parseFromAny(inputData[2], inputType[2], Query(instance))!!, {verifyExecuted5++},inputGraph[2],instance)
        pkg4.setOnFinish(pkg5)
        val pkg6 = Package_Luposdate3000_TestingExecute(query)
        pkg5.setOnFinish(pkg6)
        var verifyExecuted7 = 0
        val pkg7 = Package_Luposdate3000_TestingCompareGraphPackage(null,MemoryTable.parseFromAny(outputData[0], outputType[0], Query(instance))!!, {verifyExecuted7++},outputGraph[0],instance)
        pkg6.setOnFinish(pkg7)
        var verifyExecuted8 = 0
        val pkg8 = Package_Luposdate3000_TestingCompareGraphPackage(null,MemoryTable.parseFromAny(outputData[1], outputType[1], Query(instance))!!, {verifyExecuted8++},outputGraph[1],instance)
        pkg7.setOnFinish(pkg8)
        var verifyExecuted9 = 0
        val pkg9 = Package_Luposdate3000_TestingCompareGraphPackage(null,MemoryTable.parseFromAny(outputData[2], outputType[2], Query(instance))!!, {verifyExecuted9++},outputGraph[2],instance)
        pkg8.setOnFinish(pkg9)
        simRun.addQuerySender(10,1,1,pkg0)
        simRun.run()
        simRun.shutDown()
        if (verifyExecuted3==0) {
            TODO("pck3 not verified")
        }
        if (verifyExecuted4==0) {
            TODO("pck4 not verified")
        }
        if (verifyExecuted5==0) {
            TODO("pck5 not verified")
        }
        if (verifyExecuted7==0) {
            TODO("pck7 not verified")
        }
        if (verifyExecuted8==0) {
            TODO("pck8 not verified")
        }
        if (verifyExecuted9==0) {
            TODO("pck9 not verified")
        }
    }
    public fun getTests():Set<Pair<String,()->Unit>> {
        return setOf(
            "Simple DELETE 4 USING - in simulator - PartitionByID_1_AllCollations - Routing - true - Process - AllShortestPath" to ::`Simple DELETE 4 USING - in simulator - PartitionByID_1_AllCollations - Routing - true - Process - AllShortestPath`,
        )
    }
}
public fun main(){
    for((name,func) in SimpleDELETE4USING().getTests()){
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
