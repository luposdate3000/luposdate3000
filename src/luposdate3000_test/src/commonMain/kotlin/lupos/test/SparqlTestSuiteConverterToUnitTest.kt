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
import lupos.shared.myPrintStackTrace

import lupos.shared.EPartitionModeExt
import lupos.shared.EPredefinedPartitionSchemesExt
import lupos.shared.EQueryDistributionModeExt
import lupos.shared.IMyOutputStream
import lupos.shared.TripleStoreManager
import lupos.shared.inline.File
import lupos.shared.inline.MyPrintWriter
import kotlin.jvm.JvmField

public class SparqlTestSuiteConverterToUnitTest(resource_folder: String) : SparqlTestSuite() {
    public companion object {
        private val fastMode = false
        private val withSimulator = true
        private val onlyFirstTest = false // to reduce the number of tests, which are failing and can not be abortet by timeout
        public val minifyMode: Boolean = false
        private val fileModeMany = false // very bad for the compiler if there are many test cases because it definetly spams source-code files
        internal val verifyOrderOfColumns = false // show be dependent on the query, if it has a fixed select order or not
    }
/*
in minify mode all passing tests will be removed, such that the next execution will skip them.
without minify mode only the passing tests will be added
*/

    @JvmField
    internal var counter = 0

    @JvmField
    internal var funcCounter = 0

    @JvmField
    internal var lastFile: String = ""

    internal val folderCount = 1
    internal var folderCurrent = 0

    internal fun folderPathCoponent(idx: Int) = "code_gen_test_${idx.toString().padStart(2, '0')}"
    internal fun outputFolderRoot(idx: Int) = "src/luposdate3000_${folderPathCoponent(idx)}/"
    internal fun outputFolderSrcJvm(idx: Int) = "${outputFolderRoot(idx)}src/jvmMain/kotlin/lupos/${folderPathCoponent(idx)}/"
    internal fun outputFolderTestJvm(idx: Int) = "${outputFolderRoot(idx)}src/jvmTest/kotlin/lupos/${folderPathCoponent(idx)}/"
    internal fun outputFolderTestResourcesJvm(idx: Int) = "${outputFolderRoot(idx)}src/jvmTest/resources/"

    internal val duplicationDetector = mutableMapOf<String, Int>()

    @JvmField
    internal val allTests = mutableListOf<String>()

    internal val listOfFailed = mutableSetOf<String>()
    internal val listOfTimeout = mutableSetOf<String>()
    internal val listOfPassed = mutableSetOf<String>()
    internal val listOfRemoved = mutableSetOf<String>()
    internal val listOfAllTests = mutableSetOf<String>()
    internal val listOfBlacklist = mutableSetOf<String>()

    internal fun isIgnored(testName: String): Boolean {
        if (minifyMode) {
            return listOfRemoved.contains(testName)
        } else {
            return !listOfPassed.contains(testName)
        }
    }

    internal fun shouldAddFunction(testName: String): Boolean {
//        if (minifyMode) {
        return !isIgnored(testName)
//        } else {
//            return true
//        }
    }

    init {
        prefixDirectory = "$resource_folder/"
        if (File("resources/tests/failed").exists()) {
            File("resources/tests/failed").forEachLine {
                listOfFailed.add(it)
            }
        }
        if (File("resources/tests/blacklist").exists()) {
            File("resources/tests/blacklist").forEachLine {
                listOfBlacklist.add(it)
            }
        }
        if (File("resources/tests/passed").exists()) {
            File("resources/tests/passed").forEachLine {
                listOfPassed.add(it)
            }
        }
        if (File("resources/tests/timeout").exists()) {
            File("resources/tests/timeout").forEachLine {
                listOfTimeout.add(it)
            }
        }
        if (minifyMode) {
            listOfRemoved.addAll(listOfFailed)
            listOfRemoved.addAll(listOfTimeout)
            listOfRemoved.addAll(listOfPassed)
        }
        listOfRemoved.addAll(listOfBlacklist)
        for (idx in 0 until folderCount) {
            File(outputFolderRoot(idx)).deleteRecursively()
            File(outputFolderRoot(idx)).mkdirs()
            File(outputFolderSrcJvm(idx)).mkdirs()
            File(outputFolderTestJvm(idx)).mkdirs()
            File(outputFolderTestResourcesJvm(idx)).mkdirs()
            File("${outputFolderRoot(idx)}/module_config").withOutputStream { out ->
                out.println("disableJS=true")
                if (fileModeMany) { // because this yields out of memory during compilation
                    out.println("useKTLint=false")
                }
            }
        }
    }

    public fun finish() {
        File("resources/tests/all").withOutputStream { out ->
            for (t in listOfAllTests) {
                out.println(t)
            }
        }
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

    internal class GraphHelper(val graph: String, val type: String, val filename: String, val filenameoriginal: String)

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
        var testCaseName2 = testName.filter { it.isLetterOrDigit() || it == ' ' }
        var testCaseName = testCaseName2.filter { it.isLetterOrDigit() }
        val duplicate = duplicationDetector[testCaseName]
        if (duplicate != null) {
            testCaseName = testCaseName + "luposDuplicate$duplicate"
            testCaseName2 = testCaseName2 + " luposDuplicate$duplicate"
            duplicationDetector[testCaseName] = duplicate + 1
        } else {
            duplicationDetector[testCaseName] = 1
        }

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
        var mode = BinaryTestCaseOutputModeExt.SELECT_QUERY_RESULT
        if (resultDataFileName == null) {
            mode = BinaryTestCaseOutputModeExt.MODIFY_RESULT
        }
        val inputGraphs = mutableMapOf<String, GraphHelper>() // filename -> graphname, filetype
        if (inputFile != null) {
            inputGraphs["$testCaseName.input"] = GraphHelper(
                TripleStoreManager.DEFAULT_GRAPH_NAME,
                inputFile.substring(inputFile.lastIndexOf(".")),
                "${outputFolderTestResourcesJvm(folderCurrent)}/$testCaseName.input",
                inputFile,
            )
        }
        for (g in inputDataGraph) {
            val name = g["name"]!!
            val file = g["filename"]!!
            val outfile = "$testCaseName.input" + inputGraphs.size
            inputGraphs[outfile] = GraphHelper(
                name,
                file.substring(file.lastIndexOf(".")),
                "${outputFolderTestResourcesJvm(folderCurrent)}/$outfile",
                file,
            )
        }
        val outputGraphs = mutableMapOf<String, GraphHelper>() // filename -> graphname, filetype
        for (g in outputDataGraph) {
            val name = g["name"]!!
            val file = g["filename"]!!
            val outfile = "$testCaseName.output" + outputGraphs.size
            outputGraphs[outfile] = GraphHelper(
                name,
                file.substring(file.lastIndexOf(".")),
                "${outputFolderTestResourcesJvm(folderCurrent)}/$outfile",
                file,
            )
        }
        var targetType = "NONE"
        if (resultDataFileName != null) {
            targetType = resultDataFileName.substring(resultDataFileName.lastIndexOf("."))
        }
        counter++
        allTests.add(testCaseName)
        val queryResultIsOrdered = false
        val fileBufferPrefix = MyPrintWriter(true)
        val fileBufferTests = mutableMapOf<String/*classname*/, MyPrintWriter>()
        val fileBufferPostfix = MyPrintWriter(true)
        val distributedTest = StringBuilder()
        val distributedTestAtEnd = StringBuilder()
        var distributedTestCtr = 0
        var distributedTestAppendFlag = true
        fun appendDistributedTest(s: String, verify: Boolean) {
            if (distributedTestAppendFlag) {
                if (verify) {
                    distributedTest.appendLine("        var verifyExecuted$distributedTestCtr = 0")
                    distributedTestAtEnd.appendLine("        if (verifyExecuted$distributedTestCtr==0) {")
                    distributedTestAtEnd.appendLine("            fail(\"pck$distributedTestCtr not verified\")")
                    distributedTestAtEnd.appendLine("        }")
                }
                distributedTest.appendLine("        val pkg$distributedTestCtr = $s")
                distributedTestCtr++
                if (distributedTestCtr > 1) {
                    distributedTest.appendLine("        pkg${distributedTestCtr - 2}.setOnFinish(pkg${distributedTestCtr - 1})")
                }
            }
        }

        var localCounter = 0
        fun myExpectedData(counter: Int, data: String, type: String, out: IMyOutputStream) {
            out.println("        val expected$counter = MemoryTable.parseFromAny($data, $type, Query(instance))!!")
        }

        fun myActualDataOperatorGraph(counter: Int, graph: String, out: IMyOutputStream) {
            out.println("        val query$counter = Query(instance)")
            out.println("        val graph$counter = instance.tripleStoreManager!!.getGraph($graph)")
            out.println("        val iterator$counter = graph$counter.getIterator(query$counter, arrayOf(AOPVariable(query$counter, \"s\"), AOPVariable(query$counter, \"p\"), AOPVariable(query$counter, \"o\")), EIndexPatternExt.SPO)")
            out.println("        val operator$counter = PhysicalOptimizer(query$counter).optimizeCall(iterator$counter)")
        }

        fun myActualDataEvaluate(counter: Int, out: IMyOutputStream) {
            out.println("        val actual$counter = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator$counter, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()")
        }

        fun myCompareData(counter: Int, out: IMyOutputStream) {
            out.println("        val buf_err$counter = MyPrintWriter()")
            out.println("        if (!expected$counter.equalsVerbose(actual$counter, ${!queryResultIsOrdered}, true, $verifyOrderOfColumns, buf_err$counter)) {")
            val s = "expected$counter.toString() + \" .. \" + actual$counter.toString() + \" .. \" + buf_err$counter.toString() + \" .. \" + operator$counter"
            out.println("            fail($s)")
            out.println("        }")
        }

        fun myVerifyGraph(counter: Int, data: String, type: String, graph: String, query: String, isDefaultGraph: Boolean, out: IMyOutputStream) {
            if (query == "null") {
                myActualDataOperatorGraph(counter, graph, out)
            }
            myActualDataEvaluate(counter, out)
            myExpectedData(counter, data, type, out)
            myCompareData(counter, out)
            appendDistributedTest("Package_Luposdate3000_TestingCompareGraphPackage($query,MemoryTable.parseFromAny($data, $type, Query(instance))!!, {verifyExecuted$distributedTestCtr++},$graph,instance)", true)
        }
        fileBufferPrefix.println("/*")
        fileBufferPrefix.println(" * This file is part of the Luposdate3000 distribution (https://github.com/luposdate3000/luposdate3000).")
        fileBufferPrefix.println(" * Copyright (c) 2020-2021, Institute of Information Systems (Benjamin Warnke and contributors of LUPOSDATE3000), University of Luebeck")
        fileBufferPrefix.println(" *")
        fileBufferPrefix.println(" * This program is free software: you can redistribute it and/or modify")
        fileBufferPrefix.println(" * it under the terms of the GNU General Public License as published by")
        fileBufferPrefix.println(" * the Free Software Foundation, version 3.")
        fileBufferPrefix.println(" *")
        fileBufferPrefix.println(" * This program is distributed in the hope that it will be useful, but")
        fileBufferPrefix.println(" * WITHOUT ANY WARRANTY; without even the implied warranty of")
        fileBufferPrefix.println(" * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU")
        fileBufferPrefix.println(" * General Public License for more details.")
        fileBufferPrefix.println(" *")
        fileBufferPrefix.println(" * You should have received a copy of the GNU General Public License")
        fileBufferPrefix.println(" * along with this program. If not, see <http://www.gnu.org/licenses/>.")
        fileBufferPrefix.println(" */")
        fileBufferPrefix.println("package lupos.${folderPathCoponent(folderCurrent)}")
        fileBufferPrefix.println("import lupos.optimizer.physical.PhysicalOptimizer")
        fileBufferPrefix.println("import lupos.endpoint.LuposdateEndpoint")
        fileBufferPrefix.println("import lupos.operator.arithmetik.noinput.AOPVariable")
        fileBufferPrefix.println("import simora.addQuerySender")
        fileBufferPrefix.println("import lupos.operator.base.Query")
        fileBufferPrefix.println("import lupos.result_format.EQueryResultToStreamExt")
        fileBufferPrefix.println("import lupos.shared.EIndexPatternExt")
        fileBufferPrefix.println("import lupos.shared.EQueryDistributionModeExt")
        fileBufferPrefix.println("import lupos.shared.Luposdate3000Config")
        fileBufferPrefix.println("import lupos.shared.Luposdate3000Instance")
        fileBufferPrefix.println("import lupos.shared.EPartitionModeExt")
        fileBufferPrefix.println("import lupos.shared.MemoryTable")
        fileBufferPrefix.println("import lupos.shared.EPredefinedPartitionSchemesExt")
        fileBufferPrefix.println("import lupos." + "shared.inline.File")
        fileBufferPrefix.println("import lupos." + "shared.inline.MyPrintWriter")
        fileBufferPrefix.println("import simora.SimulationRun")
        fileBufferPrefix.println("import lupos.simulator_db.luposdate3000.Package_Luposdate3000_TestingCompareGraphPackage")
        fileBufferPrefix.println("import lupos.simulator_db.luposdate3000.Package_Luposdate3000_TestingImportPackage")
        fileBufferPrefix.println("import lupos.simulator_db.luposdate3000.Package_Luposdate3000_TestingExecute")
        fileBufferPrefix.println("import lupos.simulator_db.luposdate3000.Application_Luposdate3000")
        fileBufferPrefix.println("")
        fileBufferPrefix.println("import kotlin.test.Ignore")
        fileBufferPrefix.println("import kotlin.test.Test")
        fileBufferPrefix.println("import kotlin.test.fail")
        fileBufferPrefix.println("")
        val inputGraphIsDefaultGraph = mutableListOf<Boolean>()
        val outputGraphIsDefaultGraph = mutableListOf<Boolean>()
        fileBufferPrefix.println("public class $testCaseName {")
        if (inputGraphs.isNotEmpty()) {
            fileBufferPrefix.println("    internal val inputData = arrayOf(")
            for (k in inputGraphs.keys) {
                fileBufferPrefix.println("        File(\"src/jvmTest/resources/$k\").readAsString(),")
            }
            fileBufferPrefix.println("    )")
            fileBufferPrefix.println("    internal val inputDataFile = arrayOf(")
            for (k in inputGraphs.keys) {
                fileBufferPrefix.println("        \"src/jvmTest/resources/$k\",")
            }
            fileBufferPrefix.println("    )")
            fileBufferPrefix.println("    internal val inputGraph = arrayOf(")
            for (v in inputGraphs.values) {
                fileBufferPrefix.println("        \"${v.graph}\",")
                inputGraphIsDefaultGraph.add(v.graph == "")
            }
            fileBufferPrefix.println("    )")
            fileBufferPrefix.println("    internal val inputType = arrayOf(")
            for (v in inputGraphs.values) {
                fileBufferPrefix.println("        \"${v.type}\",")
            }
            fileBufferPrefix.println("    )")
        }
        if (outputGraphs.isNotEmpty()) {
            fileBufferPrefix.println("    internal val outputData = arrayOf(")
            for (k in outputGraphs.keys) {
                fileBufferPrefix.println("        File(\"src/jvmTest/resources/$k\").readAsString(),")
            }
            fileBufferPrefix.println("    )")
            fileBufferPrefix.println("    internal val outputDataFile = arrayOf(")
            for (k in outputGraphs.keys) {
                fileBufferPrefix.println("        \"src/jvmTest/resources/$k\",")
            }
            fileBufferPrefix.println("    )")
            fileBufferPrefix.println("    internal val outputGraph = arrayOf(")
            for (v in outputGraphs.values) {
                outputGraphIsDefaultGraph.add(v.graph == "")
                fileBufferPrefix.println("        \"${v.graph}\",")
            }
            fileBufferPrefix.println("    )")
            fileBufferPrefix.println("    internal val outputType = arrayOf(")
            for (v in outputGraphs.values) {
                fileBufferPrefix.println("        \"${v.type}\",")
            }
            fileBufferPrefix.println("    )")
        }
        if (mode == BinaryTestCaseOutputModeExt.SELECT_QUERY_RESULT) {
            fileBufferPrefix.println("    internal val targetData = File(\"src/jvmTest/resources/$testCaseName.output\").readAsString()")
            fileBufferPrefix.println("    internal val targetType = \"$targetType\"")
        }
        fileBufferPrefix.println("    internal val query = \"${cleanFileContent(File(queryFile).readAsString())}\"")
        fileBufferPrefix.println("")
        var first = true

        val fileBufferNormalHelper = MyPrintWriter(true)

        fileBufferNormalHelper.println("    internal fun normalHelper(instance:Luposdate3000Instance) {")
        fileBufferNormalHelper.println("        val buf = MyPrintWriter(false)")
        for (i in 0 until inputGraphs.size) {
            appendDistributedTest("Package_Luposdate3000_TestingImportPackage(inputDataFile[$i], inputGraph[$i], inputType[$i])", false)
            fileBufferNormalHelper.println("        if (listOf(\".n3\", \".ttl\", \".nt\").contains(inputType[$i])) {")
            fileBufferNormalHelper.println("            LuposdateEndpoint.importTripleFileC(instance, inputDataFile[$i],inputType[$i], inputGraph[$i])")
            fileBufferNormalHelper.println("        } else {")
            fileBufferNormalHelper.println("            TODO()")
            fileBufferNormalHelper.println("        }")
        }
        for (i in 0 until inputGraphs.size) {
            val c = localCounter++
            myVerifyGraph(c, "inputData[$i]", "inputType[$i]", "inputGraph[$i]", "null", inputGraphIsDefaultGraph[i], fileBufferNormalHelper)
        }
        val counter = localCounter++
        val evaluateIt = outputGraphs.isNotEmpty() || mode == BinaryTestCaseOutputModeExt.SELECT_QUERY_RESULT
        if (evaluateIt || expectedResult) {
            fileBufferNormalHelper.println("        val operator$counter = LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, query)")
            if (mode == BinaryTestCaseOutputModeExt.SELECT_QUERY_RESULT) {
                myVerifyGraph(counter, "targetData", "targetType", "\"\"", "query", false, fileBufferNormalHelper)
            } else {
                if (evaluateIt) {
                    appendDistributedTest("Package_Luposdate3000_TestingExecute(query)", false)
                    fileBufferNormalHelper.println("        LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator$counter, buf, EQueryResultToStreamExt.EMPTY_STREAM)")
                }
            }
        } else {
            fileBufferNormalHelper.println("        var flag = false")
            fileBufferNormalHelper.println("        try {")
            fileBufferNormalHelper.println("            LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, query)")
            fileBufferNormalHelper.println("        } catch (e: Throwable) {")
            fileBufferNormalHelper.println("            flag = true")
            fileBufferNormalHelper.println("        }")
            fileBufferNormalHelper.println("        if (!flag) {")
            fileBufferNormalHelper.println("            fail(\"expected failure\")")
            fileBufferNormalHelper.println("        }")
        }
        for (i in 0 until outputGraphs.size) {
            val c = localCounter++
            myVerifyGraph(c, "outputData[$i]", "outputType[$i]", "outputGraph[$i]", "null", outputGraphIsDefaultGraph[i], fileBufferNormalHelper)
        }
        fileBufferNormalHelper.println("    }")

        outerloop@ for (LUPOS_PARTITION_MODE in EPartitionModeExt.names) {
            for (predefinedPartitionScheme in EPredefinedPartitionSchemesExt.names) {
                for (useDictionaryInlineEncoding in listOf("true", "false")) {
                    if (LUPOS_PARTITION_MODE == EPartitionModeExt.names[EPartitionModeExt.Process]) {
                        continue
                    }
                    if (LUPOS_PARTITION_MODE == EPartitionModeExt.names[EPartitionModeExt.None] && predefinedPartitionScheme != EPredefinedPartitionSchemesExt.names[EPredefinedPartitionSchemesExt.Simple]) {
                        continue
                    }
                    if (onlyFirstTest) {
                        if (first) {
                            first = false
                        } else {
                            break@outerloop
                        }
                    }
                    val finalTestName = "$testCaseName2 - $LUPOS_PARTITION_MODE - $predefinedPartitionScheme - $useDictionaryInlineEncoding"
                    listOfAllTests.add(finalTestName)
                    val fileBufferTest = MyPrintWriter(true)
                    fileBufferTests[finalTestName] = fileBufferTest
                    if (isIgnored(finalTestName)) {
                        fileBufferTest.println("    @Ignore")
                    }
                    if (minifyMode) {
                        fileBufferTest.println("    @Test(timeout = 10000)")
                    } else {
                        fileBufferTest.println("    @Test")
                    }
                    fileBufferTest.println("    public fun `$finalTestName`() {")
                    fileBufferTest.println("      var instance = Luposdate3000Instance()")
                    fileBufferTest.println("      try{")
                    fileBufferTest.println("        instance.LUPOS_BUFFER_SIZE = 128")
                    fileBufferTest.println("        instance.LUPOS_PARTITION_MODE=EPartitionModeExt.$LUPOS_PARTITION_MODE")
                    fileBufferTest.println("        instance.predefinedPartitionScheme=EPredefinedPartitionSchemesExt.$predefinedPartitionScheme")
                    fileBufferTest.println("        instance.useDictionaryInlineEncoding=$useDictionaryInlineEncoding")
                    fileBufferTest.println("        instance = LuposdateEndpoint.initializeB(instance)")
                    fileBufferTest.println("        normalHelper(instance)")
                    fileBufferTest.println("      }catch(e:Throwable){")
                    fileBufferTest.println("        e.myPrintStackTrace(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_test/src/commonMain/kotlin/lupos/test/SparqlTestSuiteConverterToUnitTest.kt:466"/*SOURCE_FILE_END*/ )() //otherwise this would be silently ignored")
                    fileBufferTest.println("        throw e")
                    fileBufferTest.println("      }finally{")
                    fileBufferTest.println("        LuposdateEndpoint.close(instance)") // for inmemory db this results in complete wipe of ALL data
                    fileBufferTest.println("      }")
                    fileBufferTest.println("    }")
                    distributedTestAppendFlag = false
                }
            }
        }
        val str = distributedTest.toString()
        if (str.isNotEmpty()) {
            for (LUPOS_PARTITION_MODE in EPartitionModeExt.names) {
                for (predefinedPartitionScheme in EPredefinedPartitionSchemesExt.names) {
                    for (queryDistributionMode in EQueryDistributionModeExt.names) {
                        for (useDictionaryInlineEncoding in listOf("true", "false")) {
                            for (routingProtocol in listOf("RPL", "RPL_Fast", "AllShortestPath")) {
                                if (LUPOS_PARTITION_MODE == EPartitionModeExt.names[EPartitionModeExt.Thread]) {
                                    continue // this errors ...
                                }

                                if (LUPOS_PARTITION_MODE != EPartitionModeExt.names[EPartitionModeExt.Process] && queryDistributionMode == EQueryDistributionModeExt.names[EQueryDistributionModeExt.Routing]) {
                                    continue
                                }
                                if (LUPOS_PARTITION_MODE == EPartitionModeExt.names[EPartitionModeExt.Process] && predefinedPartitionScheme == EPredefinedPartitionSchemesExt.names[EPredefinedPartitionSchemesExt.Simple]) {
                                    continue
                                }
                                if (LUPOS_PARTITION_MODE == EPartitionModeExt.names[EPartitionModeExt.None] && predefinedPartitionScheme != EPredefinedPartitionSchemesExt.names[EPredefinedPartitionSchemesExt.Simple]) {
                                    continue
                                }
                                val finalTestName = "$testCaseName2 - in simulator - $predefinedPartitionScheme - $queryDistributionMode - $useDictionaryInlineEncoding - $LUPOS_PARTITION_MODE - $routingProtocol"
                                listOfAllTests.add(finalTestName)
                                val fileBufferTest = MyPrintWriter(true)
                                fileBufferTests[finalTestName] = fileBufferTest
                                if (fastMode) {
                                    fileBufferTest.println("    @Ignore")
                                } else {
                                    if (isIgnored(finalTestName) || !withSimulator) {
                                        fileBufferTest.println("    @Ignore")
                                    }
                                }
                                if (minifyMode) {
                                    fileBufferTest.println("    @Test(timeout = 10000)")
                                } else {
                                    fileBufferTest.println("    @Test")
                                }
                                fileBufferTest.println("    public fun `$finalTestName`() {")
                                fileBufferTest.println("        simulatorHelper(")
                                if (LUPOS_PARTITION_MODE == EPartitionModeExt.names[EPartitionModeExt.Process]) {
                                    fileBufferTest.println("            \"../luposdate3000_simulator_db/src/jvmTest/resources/autoIntegrationTest/test1.json\",")
                                } else {
                                    fileBufferTest.println("            \"../luposdate3000_simulator_db/src/jvmTest/resources/autoIntegrationTest/test2.json\",")
                                }
                                fileBufferTest.println("            mutableMapOf(")
                                fileBufferTest.println("                \"predefinedPartitionScheme\" to \"$predefinedPartitionScheme\",")
                                fileBufferTest.println("                \"mergeLocalOperatorgraphs\" to true,")
                                fileBufferTest.println("                \"queryDistributionMode\" to \"$queryDistributionMode\",")
                                fileBufferTest.println("                \"useDictionaryInlineEncoding\" to $useDictionaryInlineEncoding,")
                                fileBufferTest.println("                \"REPLACE_STORE_WITH_VALUES\" to false,") // this does not work in simulator
                                fileBufferTest.println("                \"LUPOS_PARTITION_MODE\" to \"$LUPOS_PARTITION_MODE\",")
                                fileBufferTest.println("            ),")
                                fileBufferTest.println("            \"$routingProtocol\",")
                                fileBufferTest.println("        )")
                                fileBufferTest.println("    }")
                            }
                        }
                    }
                }
            }
        }
        val fileBufferSimulator = MyPrintWriter(true)
        fileBufferSimulator.println("    public fun simulatorHelper(fileName:String,database_cfg:MutableMap<String,Any>,routingProtocol:String) {")
        fileBufferSimulator.println("        val simRun = SimulationRun()")
        fileBufferSimulator.println("        simRun.parseConfig(fileName,false,{")
        fileBufferSimulator.println("            it.getOrEmptyObject(\"deviceType\").getOrEmptyObject(\"LUPOSDATE_DEVICE\").getOrEmptyObject(\"applications\").getOrEmptyObject(\"lupos.simulator_db.luposdate3000.ApplicationFactory_Luposdate3000\").putAll(database_cfg)")
        fileBufferSimulator.println("            it.getOrEmptyObject(\"routing\").putAll(mapOf(\"protocol\" to routingProtocol))")
        fileBufferSimulator.println("        })")
        fileBufferSimulator.println("        ")
        fileBufferSimulator.println("        ")
        fileBufferSimulator.println("        ")
        fileBufferSimulator.println("        simRun.startUp()")
        fileBufferSimulator.println("        val instance = (simRun.devices.map{it.getAllChildApplications()}.flatten().filter{it is Application_Luposdate3000}.first()as Application_Luposdate3000).instance")
        fileBufferSimulator.print(str)
        fileBufferSimulator.println("        simRun.addQuerySender(10,1,1,pkg0)")
        fileBufferSimulator.println("        simRun.run()")
        fileBufferSimulator.println("        simRun.shutDown()")
        fileBufferSimulator.print(distributedTestAtEnd.toString())
        fileBufferSimulator.println("    }")
        val stringBufferSimulator = fileBufferSimulator.toString()
        val stringBufferNormalHelper = fileBufferNormalHelper.toString()
        fileBufferPostfix.println("}")
        if (!minifyMode || funcCounter < 5000) {
            val prefix = fileBufferPrefix.toString()
            val postfix = fileBufferPostfix.toString()
            var ctr = 0
            for (testname in fileBufferTests.keys) {
                if (shouldAddFunction(testname)) {
                    ctr++
                }
            }
            if (ctr > 0) {
                if (fileModeMany) {
                    for ((testname, fileBufferTest) in fileBufferTests) {
                        if (shouldAddFunction(testname)) {
                            funcCounter++
                            val finalClassName = "${testname.takeLast(150)}".filter { it.isLetterOrDigit() }
                            File("${outputFolderTestJvm(folderCurrent)}/$finalClassName.kt").withOutputStream { out ->
                                var stringBufferSimulatorRequired = false
                                var stringBufferNormalHelperRequired = false
                                if (testname.contains(" - in simulator - ")) {
                                    stringBufferSimulatorRequired = true
                                } else {
                                    stringBufferNormalHelperRequired = true
                                }
                                val content = fileBufferTest.toString()
                                out.print(prefix.replace("class $testCaseName", "class $finalClassName"))
                                out.print(content)
                                if (stringBufferSimulatorRequired) {
                                    out.print(stringBufferSimulator)
                                }
                                if (stringBufferNormalHelperRequired) {
                                    out.print(stringBufferNormalHelper)
                                }
                                out.print(postfix)
                            }
                        }
                    }
                } else {
                    File("${outputFolderTestJvm(folderCurrent)}/$testCaseName.kt").withOutputStream { out ->
                        var stringBufferSimulatorRequired = false
                        var stringBufferNormalHelperRequired = false
                        out.print(prefix)
                        for ((testname, fileBufferTest) in fileBufferTests) {
                            if (shouldAddFunction(testname)) {
                                if (testname.contains(" - in simulator - ")) {
                                    stringBufferSimulatorRequired = true
                                } else {
                                    stringBufferNormalHelperRequired = true
                                }
                                funcCounter++
                                val content = fileBufferTest.toString()
                                out.print(content)
                            }
                        }
                        if (stringBufferSimulatorRequired) {
                            out.print(stringBufferSimulator)
                        }
                        if (stringBufferNormalHelperRequired) {
                            out.print(stringBufferNormalHelper)
                        }
                        out.print(postfix)
                    }
                }
                if (fileBufferTests.size > 0) {
                    for (g in inputGraphs.values) {
                        File(g.filename).withOutputStream { out ->
                            File(g.filenameoriginal).forEachLine {
                                out.println(it)
                            }
                        }
                    }
                    for (g in outputGraphs.values) {
                        File(g.filename).withOutputStream { out ->
                            File(g.filenameoriginal).forEachLine {
                                out.println(it)
                            }
                        }
                    }
                    if (resultDataFileName != null) {
                        File("${outputFolderTestResourcesJvm(folderCurrent)}/$testCaseName.output").withOutputStream { out ->
                            File(resultDataFileName).forEachLine {
                                out.println(it)
                            }
                        }
                    }
                    folderCurrent = (folderCurrent + 1) % folderCount
                }
            }
        }
        return true
    }
}
