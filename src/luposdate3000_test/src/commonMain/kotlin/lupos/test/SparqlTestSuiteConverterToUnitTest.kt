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
    private enum class TestCaseVersion {
        RUNTIME, BUILDTIME_NO_CODE_GEN, BUILDTIME_CODE_GEN
    }

    @JvmField
    internal val useCodeGen = false

    @JvmField
    internal var counter = 0

    @JvmField
    internal var lastFile: String = ""

    @JvmField
    internal val folderPathCoponent = "code_gen_test"

    @JvmField
    internal val outputFolderRoot = "src/luposdate3000_launch_$folderPathCoponent/"

    @JvmField
    internal val outputFolderSrcJvm = "src/luposdate3000_launch_$folderPathCoponent/src/jvmMain/kotlin/lupos/launch/$folderPathCoponent/"

    @JvmField
    internal val outputFolderTestCommon = "src/luposdate3000_launch_$folderPathCoponent/src/commonTest/kotlin/lupos/launch/$folderPathCoponent/"

    @JvmField
    internal val outputFolderTestJvm = "src/luposdate3000_launch_$folderPathCoponent/src/jvmTest/kotlin/lupos/launch/$folderPathCoponent/"

    @JvmField
    internal val allTests = mutableListOf<String>()

    init {
        prefixDirectory = "$resource_folder/"
        File(outputFolderRoot).deleteRecursively()
        File(outputFolderRoot).mkdirs()
        File(outputFolderSrcJvm).mkdirs()
        File(outputFolderTestJvm).mkdirs()
        File(outputFolderTestCommon).mkdirs()
        File(outputFolderRoot + "/module_config").withOutputStream { out ->
            out.println("package=Luposdate3000_Main")
            out.println("disableJS=true")
            if (useCodeGen) {
                out.println("codegenKAPT=true")
            }
        }
        File(outputFolderRoot + "/runOptions").withOutputStream {}
    }

    public fun finish() {
        File(outputFolderSrcJvm + "/MainFunc.kt").withOutputStream { out ->
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
            out.println("package lupos.launch.$folderPathCoponent")
            out.println("import lupos.shared.Parallel")
            out.println("")
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
        allTests.add("$testCaseName")

        val queryFileContent = File(queryFile).readAsString()
        val queryFileContentClean = queryFileContent
            .replace("\"", "\\\"")
            .replace("\n", "\" +\n        \"")
            .replace("\r", "\" +\n        \"")
            .replace("\t", " ")
            .replace(" +\n        \"\"", "")
            .replace("\" +\n", " \" +\n")

        val configurations = mutableListOf(
            File("$outputFolderSrcJvm/$testCaseName.kt") to TestCaseVersion.RUNTIME,
            File("$outputFolderTestCommon/$testCaseName.kt") to TestCaseVersion.BUILDTIME_NO_CODE_GEN,
        )
        if (useCodeGen) {
            configurations.add(File("$outputFolderTestJvm/$testCaseName.kt") to TestCaseVersion.BUILDTIME_CODE_GEN)
        }

        for (configuration in configurations) {
            configuration.first.withOutputStream { out ->
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
                out.println("package lupos.launch.$folderPathCoponent")
                out.println("import lupos.endpoint.LuposdateEndpoint")
                out.println("import lupos." + "shared_inline.MyPrintWriter")
                out.println("import lupos." + "shared_inline.File")
                out.println("import lupos.shared.MemoryTable")
                out.println("import lupos.result_format.EQueryResultToStreamExt")
                out.println("import lupos.shared.tripleStoreManager")
                out.println("import lupos.operator.arithmetik.noinput.AOPVariable")
                out.println("import lupos.shared.EIndexPatternExt")
                out.println("import lupos.operator.base.Query")
                if (configuration.second == TestCaseVersion.BUILDTIME_NO_CODE_GEN || configuration.second == TestCaseVersion.BUILDTIME_CODE_GEN) {
                    out.println("import kotlin.test.Test")
                    out.println("import kotlin.test.fail")
                }
                if (useCodeGen || configuration.second == TestCaseVersion.BUILDTIME_CODE_GEN) {
                    out.println("import lupos.shared.CodeGenerationAnnotation")
                }
                out.println("")
                if (configuration.second == TestCaseVersion.BUILDTIME_NO_CODE_GEN || configuration.second == TestCaseVersion.BUILDTIME_CODE_GEN) {
                    out.println("public class $testCaseName {")
                } else {
                    out.println("public object $testCaseName {")
                }
                if (configuration.second == TestCaseVersion.RUNTIME) {
                    out.println("    internal const val inputFileName=\"$inputFile\"")
                    out.println("    internal const val targetFileName=\"${outputFile!!}\"")
                } else {
                    out.println("    val inputFileName=\"$inputFile\"")
                    out.println("    val targetFileName=\"${outputFile!!}\"")
                }
                if (useCodeGen) {
                    out.println("    @CodeGenerationAnnotation")
                }
                if (configuration.second == TestCaseVersion.RUNTIME) {
                    out.println("    internal const val query = \"${queryFileContentClean}\"")
                } else {
                    out.println("    val query = \"${queryFileContentClean}\"")
                }
                if (configuration.second == TestCaseVersion.BUILDTIME_NO_CODE_GEN || configuration.second == TestCaseVersion.BUILDTIME_CODE_GEN) {
                    out.println("    @Test fun `${testName.filter { it.isLetterOrDigit() || it == ' ' }}`(){")
                } else {
                    out.println("    internal operator fun invoke(){")
                    out.println("        println(\"Test #$counter: '$testName'\")")
                    out.println("        var success = true")
                    out.println("        try {")
                }
                out.println("            LuposdateEndpoint.initialize()")
                out.println("            LuposdateEndpoint.importTurtleFile(inputFileName)")
                if (configuration.second == TestCaseVersion.BUILDTIME_CODE_GEN) {
                    out.println("                val op = query_evaluate()")
                } else {
                    out.println("            val op = LuposdateEndpoint.evaluateSparqlToOperatorgraphA(query)")
                }
                out.println("            val buf = MyPrintWriter(true)")
                out.println("            val targetString = File(targetFileName).readAsString()")
                out.println("            val target = MemoryTable.parseFromAny(targetString, targetFileName, op.getQuery())!!")
                if (mode == BinaryTestCaseOutputModeExt.SELECT_QUERY_RESULT) {
                    out.println("            val result = (LuposdateEndpoint.evaluateOperatorgraphToResultA(op, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()")
                } else {
                    out.println("            LuposdateEndpoint.evaluateOperatorgraphToResultA(op, buf, EQueryResultToStreamExt.EMPTY_STREAM)")
                    out.println("            val graph = tripleStoreManager.getGraph(\"\")")
                    out.println("            val op2 = graph.getIterator(op.getQuery(), arrayOf(AOPVariable(op.getQuery(), \"s\"), AOPVariable(op.getQuery(), \"p\"), AOPVariable(op.getQuery(), \"o\")), EIndexPatternExt.SPO)")
                    out.println("            val result = (LuposdateEndpoint.evaluateOperatorgraphToResultA(op2, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()")
                }
                val ordered = queryFileContentClean.toLowerCase().contains("order", true)
                out.println("            if (!target.equalsVerbose(result, ${!ordered}, true)) {")
                if (configuration.second == TestCaseVersion.BUILDTIME_NO_CODE_GEN || configuration.second == TestCaseVersion.BUILDTIME_CODE_GEN) {
                    out.println("                fail()")
                } else {
                    out.println("                success = false")
                }
                out.println("            }")
                if (configuration.second == TestCaseVersion.BUILDTIME_NO_CODE_GEN || configuration.second == TestCaseVersion.BUILDTIME_CODE_GEN) {
                } else {
                    out.println("        } catch (e:Throwable) {")
                    out.println("            e.printStackTrace()")
                    out.println("                success = false")
                    out.println("        }")
                }
                out.println("            LuposdateEndpoint.close()") // for inmemory db this results in complete wipe of ALL data
                if (configuration.second == TestCaseVersion.BUILDTIME_NO_CODE_GEN || configuration.second == TestCaseVersion.BUILDTIME_CODE_GEN) {
                } else {
                    out.println("        if (success) {")
                    out.println("            println(\"Result: '$testName' success\")")
                    out.println("        } else {")
                    out.println("            println(\"Result: '$testName' failed\")")
                    out.println("        }")
                }
                if (useCodeGen && configuration.second == TestCaseVersion.RUNTIME) {
                    out.println("        if(success){")
                    out.println("            var success2 = true")
                    out.println("            try {")
                    out.println("                LuposdateEndpoint.initialize()")
                    out.println("                LuposdateEndpoint.importTurtleFile(inputFileName)")
                    out.println("                val op = query_evaluate()")
                    out.println("                val buf = MyPrintWriter(true)")
                    out.println("                val targetString = File(targetFileName).readAsString()")
                    out.println("                val target = MemoryTable.parseFromAny(targetString, targetFileName, op.getQuery())!!")
                    if (mode == BinaryTestCaseOutputModeExt.SELECT_QUERY_RESULT) {
                        out.println("                val result =( LuposdateEndpoint.evaluateOperatorgraphToResultA(op, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()")
                    } else {
                        out.println("                LuposdateEndpoint.evaluateOperatorgraphToResultA(op, buf, EQueryResultToStreamExt.EMPTY_STREAM)")
                        out.println("                val graph = tripleStoreManager.getGraph(\"\")")
                        out.println("                val op2 = graph.getIterator(op.getQuery(), arrayOf(AOPVariable(op.getQuery(), \"s\"), AOPVariable(op.getQuery(), \"p\"), AOPVariable(op.getQuery(), \"o\")), EIndexPatternExt.SPO)")
                        out.println("                val result = (LuposdateEndpoint.evaluateOperatorgraphToResultA(op2, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()")
                    }
                    val ordered = queryFileContentClean.toLowerCase().contains("order", true)
                    out.println("                if (!target.equalsVerbose(result, ${!ordered}, true)) {")
                    out.println("                success2 = false")
                    out.println("                }")
                    out.println("            } catch (e:Throwable) {")
                    out.println("                e.printStackTrace()")
                    out.println("                success2 = false")
                    out.println("            }")
                    out.println("                LuposdateEndpoint.close()") // for inmemory db this results in complete wipe of ALL data
                    out.println("            if (!success2) {")
                    out.println("                println(\"ResultCodegen: '$testName' failed\")")
                    out.println("            }")
                    out.println("        }")
                }
                out.println("    }")
                out.println("}")
            }
        }
        return true
    }
}
