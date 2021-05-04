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

import lupos.shared_inline.File
import kotlin.jvm.JvmField

public class SparqlTestSuiteConverterToUnitTest(resource_folder: String) : SparqlTestSuite() {
    @JvmField
    internal var counter = 0

    @JvmField
    internal var lastFile: String = ""

    const val folderPathCoponent = "code_gen_test"

    const val outputFolderRoot = "src/luposdate3000_launch_$folderPathCoponent/"
    const val outputFolderSrc = "src/luposdate3000_launch_$folderPathCoponent/src/jvmMain/kotlin/lupos/launch/$folderPathCoponent/"
    val allTests = mutableListOf<String>()

    init {
        prefixDirectory = "$resource_folder/"
        File(outputFolderRoot).deleteRecursively()
        File(outputFolderRoot).mkdirs()
        File(outputFolderSrc).mkdirs()
        File(outputFolderRoot + "/module_config").printWriter().use { out ->
            out.println("package=Luposdate3000_Main")
            out.println("codegenKAPT=true")
        }
        File(outputFolderRoot + "/runOptions").printWriter().use {}
        File(outputFolderRoot + "/disableTarget").printWriter().use { out ->
            out.println("js")
        }
    }

    public fun finish() {
        File(outputFolderSrc + "/MainFunc.kt").printWriter().use { out ->
            out.println("package lupos.launch.$folderPathCoponent")
            out.println("internal fun mainFunc(): Unit = Parallel.runBlocking {")
            for (test in allTests) {
                out.println("    $test()")
            }
            out.println("}")
        }
    }

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
        if (services != null && services.isNotEmpty()) {
            return false
        }
        if (inputDataGraph.size > 0) {
            return false
        }
        if (inputDataFileName == null) {
            return false
        }
        var inputFile = inputDataFileName
        if (inputFile == "#keep-data#") {
            inputFile = lastFile
        }
        lastFile = inputFile
        var outputFile = resultDataFileName
        var mode = BinaryTestCaseOutputModeExt.SELECT_QUERY_RESULT
        if (outputFile == null) {
            if (outputDataGraph.size == 1 && outputDataGraph[0]["name"] == "") {
                mode = BinaryTestCaseOutputModeExt.MODIFY_RESULT
                outputFile = outputDataGraph[0]["filename"]
            } else {
                return false
            }
        } else {
            if (outputDataGraph.size > 0) {
                return false
            }
        }
        counter++
        val testCaseName = "TestCase$counter"
        val testCaseFileName = "$outputFolderSrc/$testCaseName.kt"
        allTests.add("$testCaseName")
        File(testCaseFileName).printWriter().use { out ->
            out.println("package lupos.launch.$folderPathCoponent")
            out.println("internal object $testCaseName {")
            out.println("internal const val query = \"${File(queryFile).readAsString().replace("\"", "\\\"").replace("\n", "\" +\n        \"")}\".trimMargin()"))
            out.println("    public operator fun invoke(){")
            out.println("        println(\"Test: '$testName'\")")
            out.println("        LuposdateEndpoint.importTurtleFiles(\"$inputFile\",mutableMapOf())")
            out.println("        val op = LuposdateEndpoint.evaluateSparqlToOperatorgraphA(query)")
            out.println("        val buf = MyPrintWriter(true)")
            out.println("        val result = LuposdateEndpoint.evaluateOperatorgraphToResultA(op,buf,EQueryResultToStreamExt.MEMORY_TABLE)")
            out.println("        val target = XMLElement.parseFromAny(File(\"${outputFile!!}\").readAsString(), \"${outputFile!!}\")!!")
            if (mode == BinaryTestCaseOutputModeExt.SELECT_QUERY_RESULT) {
//TODO cmp target and result
            } else {
//TODO cmp target and default graph
            }
            out.println("    }")
            out.println("}")
        }
//        val tmp = BinaryTestCase.generateTestcase(inputFile, queryFile, outputFile!!, output_folder + "/${counter++}/", testName, mode)

        return true
    }
}
