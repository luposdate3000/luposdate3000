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
import lupos.operator.base.Query
import lupos.shared.MemoryTable
import lupos.shared.inline.File
import lupos.simulator_db.luposdate3000.Application_Luposdate3000
import lupos.simulator_db.luposdate3000.Package_Luposdate3000_TestingCompareGraphPackage
import lupos.simulator_db.luposdate3000.Package_Luposdate3000_TestingImportPackage
import simora.SimulationRun
import simora.addQuerySender

public class PostqueryVALUESwithOPTIONALobjvar1row {
    internal val inputData = arrayOf(
        File("src/luposdate3000_launch_code_gen_test_00/src/jvmTest/resources/PostqueryVALUESwithOPTIONALobjvar1row.input").readAsString(),
    )
    internal val inputDataFile = arrayOf(
        "src/luposdate3000_launch_code_gen_test_00/src/jvmTest/resources/PostqueryVALUESwithOPTIONALobjvar1row.input",
    )
    internal val inputGraph = arrayOf(
        "",
    )
    internal val inputType = arrayOf(
        ".ttl",
    )
    internal val targetData = File("src/luposdate3000_launch_code_gen_test_00/src/jvmTest/resources/PostqueryVALUESwithOPTIONALobjvar1row.output").readAsString()
    internal val targetType = ".srx"
    internal val query = "# bindings with two variables and two sets of values \n" +
        "PREFIX : <http://example.org/>  \n" +
        "PREFIX foaf: <http://xmlns.com/foaf/0.1/>  \n" +
        "SELECT ?s ?o1 ?o2 \n" +
        "{ \n" +
        "  ?s ?p1 ?o1  \n" +
        "  OPTIONAL { ?s foaf:knows ?o2 } \n" +
        "} VALUES (?o2) { \n" +
        " (:b) \n" +
        "} \n" +
        ""

    public fun `Postquery VALUES with OPTIONAL objvar 1 row - in simulator - PartitionByID_2_AllCollations - Centralized - false - Process - RPL_Fast`() {
        simulatorHelper(
            "src/luposdate3000_simulator_db/src/jvmTest/resources/autoIntegrationTest/test1.json",
            mutableMapOf(
                "predefinedPartitionScheme" to "PartitionByID_2_AllCollations",
                "mergeLocalOperatorgraphs" to true,
                "queryDistributionMode" to "Centralized",
                "useDictionaryInlineEncoding" to false,
                "REPLACE_STORE_WITH_VALUES" to false,
                "LUPOS_PARTITION_MODE" to "Process",
            ),
            "RPL_Fast",
        )
    }
    public fun `Postquery VALUES with OPTIONAL objvar 1 row - in simulator - PartitionByID_O_AllCollations - Routing - true - Process - AllShortestPath`() {
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
    public fun simulatorHelper(fileName: String, database_cfg: MutableMap<String, Any>, routingProtocol: String) {
        val simRun = SimulationRun()
        simRun.parseConfig(
            fileName, false,
            {
                it.getOrEmptyObject("deviceType").getOrEmptyObject("LUPOSDATE_DEVICE").getOrEmptyObject("applications").getOrEmptyObject("lupos.simulator_db.luposdate3000.ApplicationFactory_Luposdate3000").putAll(database_cfg)
                it.getOrEmptyObject("routing").putAll(mapOf("protocol" to routingProtocol))
            }
        )

        simRun.startUp()
        val instance = (simRun.devices.map { it.getAllChildApplications() }.flatten().filter { it is Application_Luposdate3000 }.first()as Application_Luposdate3000).instance
        val pkg0 = Package_Luposdate3000_TestingImportPackage(inputDataFile[0], inputGraph[0], inputType[0])
        var verifyExecuted1 = 0
        val pkg1 = Package_Luposdate3000_TestingCompareGraphPackage(null, MemoryTable.parseFromAny(inputData[0], inputType[0], Query(instance))!!, { verifyExecuted1++ }, inputGraph[0], instance)
        pkg0.setOnFinish(pkg1)
        var verifyExecuted2 = 0
        val pkg2 = Package_Luposdate3000_TestingCompareGraphPackage(query, MemoryTable.parseFromAny(targetData, targetType, Query(instance))!!, { verifyExecuted2++ }, "", instance)
        pkg1.setOnFinish(pkg2)
        simRun.addQuerySender(10, 1, 1, pkg0)
        simRun.run()
        simRun.shutDown()
        if (verifyExecuted1 == 0) {
            TODO("pck1 not verified")
        }
        if (verifyExecuted2 == 0) {
            TODO("pck2 not verified")
        }
    }
    public fun getTests(): Set<Pair<String, ()->Unit>> {
        return setOf(
            "Postquery VALUES with OPTIONAL objvar 1 row - in simulator - PartitionByID_2_AllCollations - Centralized - false - Process - RPL_Fast" to ::`Postquery VALUES with OPTIONAL objvar 1 row - in simulator - PartitionByID_2_AllCollations - Centralized - false - Process - RPL_Fast`,
            "Postquery VALUES with OPTIONAL objvar 1 row - in simulator - PartitionByID_O_AllCollations - Routing - true - Process - AllShortestPath" to ::`Postquery VALUES with OPTIONAL objvar 1 row - in simulator - PartitionByID_O_AllCollations - Routing - true - Process - AllShortestPath`,
        )
    }
}
public fun main() {
    for ((name, func) in PostqueryVALUESwithOPTIONALobjvar1row().getTests()) {
        File("lupos.launch_code_gen_test_00.${name.replaceFirstChar { it.uppercase() }}.stat").withOutputStream { out ->
            out.println("started")
            try {
                func()
                out.println("passed")
            } catch (e: Error) {
                out.println("failed")
                e.printStackTrace()
            }
        }
    }
}
