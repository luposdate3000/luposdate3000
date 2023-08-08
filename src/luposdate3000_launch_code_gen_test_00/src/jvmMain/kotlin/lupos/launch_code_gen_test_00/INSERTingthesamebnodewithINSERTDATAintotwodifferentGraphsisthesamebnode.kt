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

    public fun `INSERTing the same bnode with INSERT DATA into two different Graphs is the same bnode - in simulator - PartitionByID_O_AllCollations - Centralized - true - Process - RPL_Fast`() {
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
            "RPL_Fast",
        )
    }
    public fun `INSERTing the same bnode with INSERT DATA into two different Graphs is the same bnode - in simulator - PartitionByKeyAllCollations - Centralized - true - Process - RPL`() {
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
    public fun getTests():Set<Pair<String,()->Unit>> {
        return setOf(
            "INSERTing the same bnode with INSERT DATA into two different Graphs is the same bnode - in simulator - PartitionByID_O_AllCollations - Centralized - true - Process - RPL_Fast" to ::`INSERTing the same bnode with INSERT DATA into two different Graphs is the same bnode - in simulator - PartitionByID_O_AllCollations - Centralized - true - Process - RPL_Fast`,
            "INSERTing the same bnode with INSERT DATA into two different Graphs is the same bnode - in simulator - PartitionByKeyAllCollations - Centralized - true - Process - RPL" to ::`INSERTing the same bnode with INSERT DATA into two different Graphs is the same bnode - in simulator - PartitionByKeyAllCollations - Centralized - true - Process - RPL`,
        )
    }
}
public fun main(){
    for((name,func) in INSERTingthesamebnodewithINSERTDATAintotwodifferentGraphsisthesamebnode().getTests()){
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
