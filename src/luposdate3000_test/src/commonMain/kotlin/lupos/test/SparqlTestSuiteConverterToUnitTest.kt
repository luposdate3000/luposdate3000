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
package lupos.test
import lupos.shared.TripleStoreManager
import lupos.shared.inline.File
import kotlin.jvm.JvmField
public class SparqlTestSuiteConverterToUnitTest(resource_folder: String) : SparqlTestSuite() {
    private val withCodeGen = false
    private val withSimulator = true

    @JvmField
    internal var counter = 0

    @JvmField
    internal var lastFile: String = ""

    @JvmField
    internal val folderPathCoponent = "code_gen_test"

    @JvmField
    internal val outputFolderRoot = "src/luposdate3000_$folderPathCoponent/"

    @JvmField
    internal val outputFolderSrcJvm = "${outputFolderRoot}src/jvmMain/kotlin/lupos/$folderPathCoponent/"

    @JvmField
    internal val outputFolderTestJvm = "${outputFolderRoot}src/jvmTest/kotlin/lupos/$folderPathCoponent/"

    @JvmField
    internal val outputFolderTestResourcesJvm = "${outputFolderRoot}src/jvmTest/resources/"

    @JvmField
    internal val allTests = mutableListOf<String>()

    init {
        prefixDirectory = "$resource_folder/"
        File(outputFolderRoot).deleteRecursively()
        File(outputFolderRoot).mkdirs()
        File(outputFolderSrcJvm).mkdirs()
        File(outputFolderTestJvm).mkdirs()
        File(outputFolderTestResourcesJvm).mkdirs()
        File(outputFolderRoot + "/module_config").withOutputStream { out ->
            out.println("disableJS=true")
            if (withCodeGen) {
                out.println("codegenKAPT=true")
            }
        }
    }

    public fun finish() {
    }

    private fun cleanFileContent(s: String): String {
        return s
            .replace("\\", "\\\\")
            .replace("\"", "\\\"")
            .replace("\n", "\" +\n        \"")
            .replace("\r", "\" +\n        \"")
            .replace("\t", " ")
            .replace(" +\n        \"\"", "")
            .replace("\" +\n", " \" +\n")
            .replace("\" +\n", "\\n\" +\n")
    }
    private val ignoreList = SparqlTestSuiteConverterToUnitTestIgnoreListDueToTooSlow.ignoreListDueToTooSlow + SparqlTestSuiteConverterToUnitTestIgnoreListDueToNotImplemented.ignoreListDueToNotImplemented + SparqlTestSuiteConverterToUnitTestIgnoreListDueToBugs.ignoreListDueToBugs + SparqlTestSuiteConverterToUnitTestIgnoreListDueToBugsResolveLater.ignoreListDueToBugsResolveLater
    override fun parseSPARQLAndEvaluate( //
        executeJena: Boolean,
        testName: String, //
        expectedResult: Boolean, //
        queryFile: String, //
        inputDataFileName: String?, //
        resultDataFileName: String?, //
        services: List<Map<String, String>>?, //
        inputDataGraph: MutableList<MutableMap<String, String>>, //
        outputDataGraph: MutableList<MutableMap<String, String>> //
    ): Boolean {
        val testCaseName2 = testName.filter { it.isLetterOrDigit() || it == ' ' }
        val testCaseName = testCaseName2.filter { it.isLetterOrDigit() }
        if (services != null && services.isNotEmpty()) {
            return false
        }
        var inputFile = inputDataFileName
        if (inputFile == "#keep-data#") {
            inputFile = lastFile
        }
        if (inputFile != null) {
            lastFile = inputFile
        }
        var outputFile = resultDataFileName
        var mode = BinaryTestCaseOutputModeExt.SELECT_QUERY_RESULT
        if (outputFile == null) {
            mode = BinaryTestCaseOutputModeExt.MODIFY_RESULT
        }

        val inputGraphs = mutableMapOf<String, Pair<String, String>>() // filename -> graphname, filetype
        if (inputFile != null) {
            inputGraphs["$testCaseName.input"] = Pair(TripleStoreManager.DEFAULT_GRAPH_NAME, inputFile!!.substring(inputFile.lastIndexOf(".")))
            File(outputFolderTestResourcesJvm + "/$testCaseName.input").withOutputStream { out ->
                File(inputFile).forEachLine {
                    out.println(it)
                }
            }
        }
        for (g in inputDataGraph) {
            val name = g["name"]!!
            val file = g["filename"]!!
            val outfile = "$testCaseName.input" + inputGraphs.size
            inputGraphs[outfile] = Pair(name, file.substring(file.lastIndexOf(".")))
            File(outputFolderTestResourcesJvm + "/$outfile").withOutputStream { out ->
                File(file).forEachLine {
                    out.println(it)
                }
            }
        }
        val outputGraphs = mutableMapOf<String, Pair<String, String>>() // filename -> graphname, filetype
        for (g in outputDataGraph) {
            val name = g["name"]!!
            val file = g["filename"]!!
            val outfile = "$testCaseName.output" + outputGraphs.size
            outputGraphs[outfile] = Pair(name, file.substring(file.lastIndexOf(".")))
            File(outputFolderTestResourcesJvm + "/$outfile").withOutputStream { out ->
                File(file).forEachLine {
                    out.println(it)
                }
            }
        }
        var targetType = "NONE"
        if (outputFile != null) {
            File(outputFolderTestResourcesJvm + "/$testCaseName.output").withOutputStream { out ->
                File(outputFile).forEachLine {
                    out.println(it)
                }
            }
            targetType = outputFile.substring(outputFile.lastIndexOf("."))
        }
        counter++
        allTests.add("$testCaseName")
        var queryResultIsOrdered = false
        var ignored = ignoreList.contains(testCaseName)
        for (useCodeGen in setOf(false, withCodeGen)) {
            var filenamePart = ""
            if (useCodeGen) {
                filenamePart = "_CodeGen"
            }
            val filenameLocal: String
            if (useCodeGen) {
                filenameLocal = "$outputFolderSrcJvm/$testCaseName$filenamePart.kt"
            } else {
                filenameLocal = "$outputFolderTestJvm/$testCaseName$filenamePart.kt"
            }
            File(filenameLocal).withOutputStream { out ->
                val distributedTest = StringBuilder()
                var distributedTestCtr = 0
                fun appendDistributedTest(s: String) {
                    distributedTest.appendLine("        val pkg$distributedTestCtr = $s")
                    distributedTestCtr++
                    if (distributedTestCtr> 1) {
                        distributedTest.appendLine("        pkg${distributedTestCtr - 2}.onFinish = pkg${distributedTestCtr - 1}")
                    }
                }
                var localCounter = 0
                fun myExpectedData(counter: Int, data: String, type: String) {
                    out.println("        val expected$counter = MemoryTable.parseFromAny($data, $type, Query(instance))!!")
                }
                fun myActualDataOperatorGraph(counter: Int, graph: String) {
                    out.println("        val query$counter = Query(instance)")
                    out.println("        val graph$counter = instance.tripleStoreManager!!.getGraph($graph)")
                    out.println("        val operator$counter = graph$counter.getIterator(query$counter, arrayOf(AOPVariable(query$counter, \"s\"), AOPVariable(query$counter, \"p\"), AOPVariable(query$counter, \"o\")), EIndexPatternExt.SPO)")
                }
                fun myActualDataEvaluate(counter: Int) {
                    out.println("        val actual$counter = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator$counter, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()")
                }
                fun myCompareData(counter: Int) {
                    out.println("        val buf_err$counter = MyPrintWriter()")
                    out.println("        if (!expected$counter.equalsVerbose(actual$counter, ${!queryResultIsOrdered}, true, buf_err$counter)) {")
                    val s = "expected$counter.toString() + \" .. \" + actual$counter.toString() + \" .. \" + buf_err$counter.toString() + \" .. \" + operator$counter"
                    if (useCodeGen) {
                        out.println("            throw Exception($s)")
                    } else {
                        out.println("            fail($s)")
                    }
                    out.println("        }")
                }
                fun myVerifyGraph(counter: Int, data: String, type: String, graph: String, query: String?, isDefaultGraph: Boolean) {
                    if (query == null) {
                        myActualDataOperatorGraph(counter, graph)
                    }
                    myActualDataEvaluate(counter)
                    myExpectedData(counter, data, type)
                    myCompareData(counter)
                    val q: String
                    if (query == null) {
                        if (isDefaultGraph) {
                            q = "\"SELECT ?s ?p ?o WHERE { ?s ?p ?o . }\""
                        } else {
                            q = "\"SELECT ?s ?p ?o WHERE { GRAPH \${$graph} { ?s ?p ?o . }}\""
                        }
                    } else {
                        q = query
                    }
                    appendDistributedTest("MySimulatorTestingCompareGraphPackage($q,MemoryTable.parseFromAny($data, $type, Query(instance))!!)")
                }
                out.println("/*")
                out.println(" * This file is part of the Luposdate3000 distribution (https://github.com/luposdate3000/luposdate3000).")
                out.println(" * Copyright (c) 2020-2021, Institute of Information Systems (Benjamin Warnke and contributors of LUPOSDATE3000), University of Luebeck")
                out.println(" *")
                out.println(" * This program is free software: you can redistribute it and/or modify")
                out.println(" * it under the terms of the GNU General Public License as published by")
                out.println(" * the Free Software Foundation, version 3.")
                out.println(" *")
                out.println(" * This program is distributed in the hope that it will be useful, but")
                out.println(" * WITHOUT ANY WARRANTY; without even the implied warranty of")
                out.println(" * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU")
                out.println(" * General Public License for more details.")
                out.println(" *")
                out.println(" * You should have received a copy of the GNU General Public License")
                out.println(" * along with this program. If not, see <http://www.gnu.org/licenses/>.")
                out.println(" */")
                out.println("package lupos.$folderPathCoponent")
                out.println("import lupos.endpoint.LuposdateEndpoint")
                out.println("import lupos.operator.arithmetik.noinput.AOPVariable")
                out.println("import lupos.operator.base.Query")
                out.println("import lupos.result_format.EQueryResultToStreamExt")
                if (useCodeGen) {
                    out.println("import lupos.shared.CodeGenerationAnnotation")
                }
                out.println("import lupos.shared.EIndexPatternExt")
                out.println("import lupos.shared.MemoryTable")
                out.println("import lupos." + "shared.inline.File")
                out.println("import lupos." + "shared.inline.MyPrintWriter")
                out.println("import lupos.simulator_core.Simulation")
                out.println("import lupos.simulator_db.luposdate3000.MySimulatorTestingCompareGraphPackage")
                out.println("import lupos.simulator_db.luposdate3000.MySimulatorTestingImportPackage")
                out.println("import lupos.simulator_db.luposdate3000.MySimulatorTestingExecute")
                out.println("import lupos.simulator_db.luposdate3000.DatabaseHandle")
                out.println("import lupos.simulator_iot.config.Configuration")
                out.println("import lupos.simulator_iot.log.Logger")
                out.println("import lupos.simulator_iot.SimulationRun")
                out.println("import lupos.simulator_iot.LifeCycleImpl")
                out.println("")
                if (!useCodeGen) {
                    if (ignored) {
                        out.println("import kotlin.test.Ignore")
                    }
                    out.println("import kotlin.test.Test")
                    out.println("import kotlin.test.fail")
                }
                out.println("")
                val inputGraphIsDefaultGraph = mutableListOf<Boolean>()
                val outputGraphIsDefaultGraph = mutableListOf<Boolean>()
                if (useCodeGen) {
                    out.println("public class ${testCaseName}_CodeGen {")
                } else {
                    out.println("public class $testCaseName {")
                }
                if (inputGraphs.size> 0) {
                    out.println("    internal val inputData = arrayOf(")
                    for ((k, v) in inputGraphs) {
                        out.println("        File(\"src/jvmTest/resources/$k\").readAsString(),")
                    }
                    out.println("    )")
                    out.println("    internal val inputGraph = arrayOf(")
                    for ((k, v) in inputGraphs) {
                        out.println("        \"${v.first}\",")
                        inputGraphIsDefaultGraph.add(v.first == "")
                    }
                    out.println("    )")
                    out.println("    internal val inputType = arrayOf(")
                    for ((k, v) in inputGraphs) {
                        out.println("        \"${v.second}\",")
                    }
                    out.println("    )")
                }
                if (outputGraphs.size> 0) {
                    out.println("    internal val outputData = arrayOf(")
                    for ((k, v) in outputGraphs) {
                        out.println("        File(\"src/jvmTest/resources/$k\").readAsString(),")
                    }
                    out.println("    )")
                    out.println("    internal val outputGraph = arrayOf(")
                    for ((k, v) in outputGraphs) {
                        outputGraphIsDefaultGraph.add(v.first == "")
                        out.println("        \"${v.first}\",")
                    }
                    out.println("    )")
                    out.println("    internal val outputType = arrayOf(")
                    for ((k, v) in outputGraphs) {
                        out.println("        \"${v.second}\",")
                    }
                    out.println("    )")
                }
                if (mode == BinaryTestCaseOutputModeExt.SELECT_QUERY_RESULT) {
                    out.println("    internal val targetData = File(\"src/jvmTest/resources/$testCaseName.output\").readAsString()")
                    out.println("    internal val targetType = \"$targetType\"")
                }
                if (useCodeGen) {
                    out.println("    @CodeGenerationAnnotation")
                }
                out.println("    internal val query = \"${cleanFileContent(File(queryFile).readAsString())}\"")
                out.println("")
                if (!useCodeGen) {
                    if (ignored) {
                        val reason = ignoreList[testCaseName]
                        if (reason != null) {
                            out.println("    @Ignore // Reason: >$reason<")
                        } else {
                            out.println("    @Ignore")
                        }
                    }
                    out.println("    @Test")
                }
                out.println("    public fun `$testCaseName2`() {")
                out.println("        val instance = LuposdateEndpoint.initialize()")
                out.println("        instance.LUPOS_BUFFER_SIZE = 128")
                out.println("        val buf = MyPrintWriter(false)")
                for (i in 0 until inputGraphs.size) {
                    appendDistributedTest("MySimulatorTestingImportPackage(inputData[$i], inputGraph[$i], inputType[$i])")
                    out.println("        if (listOf(\".n3\", \".ttl\", \".nt\").contains(inputType[$i])) {")
                    out.println("            LuposdateEndpoint.importTurtleString(instance, inputData[$i], inputGraph[$i])")
                    out.println("        } else {")
                    out.println("            TODO()")
                    out.println("        }")
                }
                for (i in 0 until inputGraphs.size) {
                    val c = localCounter++
                    myVerifyGraph(c, "inputData[$i]", "inputType[$i]", "inputGraph[$i]", null, inputGraphIsDefaultGraph[i])
                }
                val counter = localCounter++
                val evaluateIt = outputGraphs.size> 0 || mode == BinaryTestCaseOutputModeExt.SELECT_QUERY_RESULT
                if (evaluateIt || expectedResult) {
                    if (useCodeGen) {
                        out.println("            val operator$counter = query_evaluate(instance)")
                    } else {
                        out.println("        val operator$counter = LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, query)")
                    }
                    if (mode == BinaryTestCaseOutputModeExt.SELECT_QUERY_RESULT) {
                        myVerifyGraph(counter, "targetData", "targetType", "", "query", false)
                    } else {
                        if (evaluateIt) {
                            appendDistributedTest("MySimulatorTestingExecute(query)")
                            out.println("        LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator$counter, buf, EQueryResultToStreamExt.EMPTY_STREAM)")
                        }
                    }
                } else {
                    out.println("        var flag = false")
                    out.println("        try {")
                    if (useCodeGen) {
                        out.println("            query_evaluate()")
                    } else {
                        out.println("            LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, query)")
                    }
                    out.println("        } catch (e: Throwable) {")
                    out.println("            flag = true")
                    out.println("        }")
                    out.println("        if (!flag) {")
                    if (useCodeGen) {
                        out.println("            throw Exception(\"expected failure\")")
                    } else {
                        out.println("            fail(\"expected failure\")")
                    }
                    out.println("        }")
                }
                for (i in 0 until outputGraphs.size) {
                    val c = localCounter++
                    myVerifyGraph(c, "outputData[$i]", "outputType[$i]", "outputGraph[$i]", null, outputGraphIsDefaultGraph[i])
                }
                out.println("        LuposdateEndpoint.close(instance)") // for inmemory db this results in complete wipe of ALL data
                out.println("    }")
                val str = distributedTest.toString()
                if (!useCodeGen && str.length> 0 ) {
                    if (ignored||!withSimulator) {
                        val reason = ignoreList[testCaseName]
                        if (reason != null) {
                            out.println("    @Ignore // Reason: >$reason<")
                        } else {
                            out.println("    @Ignore")
                        }
                    }
                    out.println("    @Test")
                    out.println("    public fun `$testCaseName2 - in simulator`() {")
                    out.println("        val simRun = SimulationRun()")
                    out.println("        val json=simRun.parseConfigFile(\"../luposdate3000_simulator_iot/src/jvmTest/resources/autoIntegrationTest/test1.json\")")
                    out.println("        val config = simRun.parseJsonObjects(json)")
                    out.println("        simRun.sim = Simulation(config.getEntities(), LifeCycleImpl(simRun))")
                    out.println("        simRun.sim.maxClock = if (simRun.simMaxClock == simRun.notInitializedClock) simRun.sim.maxClock else simRun.simMaxClock")
                    out.println("        simRun.sim.steadyClock = if (simRun.simSteadyClock == simRun.notInitializedClock) simRun.sim.steadyClock else simRun.simSteadyClock")
                    out.println("        simRun.sim.startUp()")
                    out.println("        val instance=(config.devices.filter { it.hasDatabase() }.map{it.database}.filter{it!=null}.map{it!!.db}.first() as DatabaseHandle).instance")
                    out.print(str)
                    out.println("        config.querySenders[0].queryPck = pkg0")
                    out.println("        simRun.sim.run()")
                    out.println("        simRun.sim.shutDown()")

                    out.println("    }")
                }
                if (!useCodeGen && withCodeGen) {
                    if (ignored) {
                        val reason = ignoreList[testCaseName]
                        if (reason != null) {
                            out.println("    @Ignore // Reason: >$reason<")
                        } else {
                            out.println("    @Ignore")
                        }
                    }
                    out.println("    @Test")

                    out.println("    public fun `$testCaseName2 - codegen`() {")
                    out.println("        ${testCaseName}_CodeGen().`$testCaseName2`()")
                    out.println("    }")
                }
                out.println("}")
            }
        }
        return true
    }
}
