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

import lupos.shared.inline.File
import kotlin.jvm.JvmField

public class SparqlTestSuiteConverterToUnitTest(resource_folder: String) : SparqlTestSuite() {
    private enum class Config {
        RUNTIME, // RUNTIME_CODE_GEN && RUNTIME_NO_CODE_GEN
        RUNTIME_CODE_GEN,
        RUNTIME_NO_CODE_GEN,
        BUILDTIME_CODE_GEN,
        BUILDTIME_NO_CODE_GEN,
    }

    private val enabledConfigurations = setOf(
        Config.BUILDTIME_NO_CODE_GEN,
    )

    private fun configIsBuildTime(conf: Config): Boolean {
        return conf == Config.BUILDTIME_NO_CODE_GEN || conf == Config.BUILDTIME_CODE_GEN
    }

    private fun configIsRunTime(conf: Config): Boolean {
        return !configIsBuildTime(conf)
    }

    private fun configIsCodeGen(conf: Config): Boolean {
        return conf == Config.RUNTIME_CODE_GEN || conf == Config.RUNTIME || conf == Config.BUILDTIME_CODE_GEN
    }

    private fun configIsBuildTimeCodeGen(conf: Config): Boolean {
        return conf == Config.BUILDTIME_CODE_GEN
    }

    private fun configIsRunTimeCodeGen(conf: Config): Boolean {
        return conf == Config.RUNTIME || conf == Config.RUNTIME_CODE_GEN
    }

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
    internal val outputFolderTestCommon = "src/luposdate3000_launch_$folderPathCoponent/src/jvmTest/kotlin/lupos/launch/$folderPathCoponent/common/"

    @JvmField
    internal val outputFolderTestJvm = "src/luposdate3000_launch_$folderPathCoponent/src/jvmTest/kotlin/lupos/launch/$folderPathCoponent/jvm/"

    @JvmField
    internal val outputFolderTestResourcesCommon = "src/luposdate3000_launch_$folderPathCoponent/src/jvmTest/resources/"

    @JvmField
    internal val allTests = mutableListOf<String>()

    init {
        prefixDirectory = "$resource_folder/"
        File(outputFolderRoot).deleteRecursively()
        File(outputFolderRoot).mkdirs()
        File(outputFolderSrcJvm).mkdirs()
        File(outputFolderTestJvm).mkdirs()
        File(outputFolderTestCommon).mkdirs()
        File(outputFolderTestResourcesCommon).mkdirs()
        File(outputFolderRoot + "/module_config").withOutputStream { out ->
            out.println("package=Luposdate3000_Main")
            out.println("disableJS=true")
            if (enabledConfigurations.contains(Config.RUNTIME_CODE_GEN) || enabledConfigurations.contains(Config.BUILDTIME_CODE_GEN) || enabledConfigurations.contains(Config.RUNTIME)) {
                out.println("codegenKAPT=true")
            }
        }
        File(outputFolderRoot + "/runOptions").withOutputStream {}
    }

    public fun finish() {
        val flag = enabledConfigurations.contains(Config.RUNTIME_CODE_GEN) || enabledConfigurations.contains(Config.RUNTIME_NO_CODE_GEN) || enabledConfigurations.contains(Config.RUNTIME)
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
            if (flag) {
                for (test in allTests) {
                    out.println("    $test()")
                }
            }
            out.println("}")
        }
    }

    private fun cleanFileContent(s: String): String {
        return s
            .replace("\"", "\\\"")
            .replace("\n", "\" +\n        \"")
            .replace("\r", "\" +\n        \"")
            .replace("\t", " ")
            .replace(" +\n        \"\"", "")
            .replace("\" +\n", " \" +\n")
            .replace("\" +\n", "\\n\" +\n")
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
        var queryResultIsOrdered = false
        File(outputFolderTestResourcesCommon + "/$testCaseName.query").withOutputStream { out ->
            File(queryFile).forEachLine {
                out.println(it)
                if (it.contains("order", true)) {
                    queryResultIsOrdered = true
                }
            }
        }
        File(outputFolderTestResourcesCommon + "/$testCaseName.input").withOutputStream { out ->
            File(inputFile).forEachLine {
                out.println(it)
            }
        }
        File(outputFolderTestResourcesCommon + "/$testCaseName.output").withOutputStream { out ->
            File(outputFile!!).forEachLine {
                out.println(it)
            }
        }
        val targetType = outputFile!!.substring(outputFile.lastIndexOf("."))

        val configurations = mutableListOf<Pair<File, Config>>()
        if ((enabledConfigurations.contains(Config.RUNTIME_CODE_GEN) && enabledConfigurations.contains(Config.RUNTIME_NO_CODE_GEN)) || enabledConfigurations.contains(Config.RUNTIME)) {
            configurations.add(File("$outputFolderSrcJvm/$testCaseName.kt") to Config.RUNTIME)
        } else if (enabledConfigurations.contains(Config.RUNTIME_CODE_GEN)) {
            configurations.add(File("$outputFolderSrcJvm/$testCaseName.kt") to Config.RUNTIME_CODE_GEN)
        } else if (enabledConfigurations.contains(Config.RUNTIME_NO_CODE_GEN)) {
            configurations.add(File("$outputFolderSrcJvm/$testCaseName.kt") to Config.RUNTIME_NO_CODE_GEN)
        }
        if (enabledConfigurations.contains(Config.BUILDTIME_CODE_GEN)) {
            configurations.add(File("$outputFolderTestJvm/$testCaseName.kt") to Config.BUILDTIME_CODE_GEN)
        }
        if (enabledConfigurations.contains(Config.BUILDTIME_NO_CODE_GEN)) {
            configurations.add(File("$outputFolderTestCommon/$testCaseName.kt") to Config.BUILDTIME_NO_CODE_GEN)
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
                out.println("import lupos.operator.arithmetik.noinput.AOPVariable")
                out.println("import lupos.shared.EIndexPatternExt")
                out.println("import lupos.operator.base.Query")
                if (configIsBuildTime(configuration.second)) {
                    out.println("import kotlin.test.Test")
                    out.println("import kotlin.test.fail")
                }
                if (configIsCodeGen(configuration.second)) {
                    out.println("import lupos.shared.CodeGenerationAnnotation")
                }
                out.println("")
                if (configIsBuildTime(configuration.second)) {
                    out.println("public class $testCaseName {")
                    out.println("   val inputData=File(\"src/jvmTest/resources/$testCaseName.input\").readAsString()")
                    out.println("   val targetData=File(\"src/jvmTest/resources/$testCaseName.output\").readAsString()")
                    out.println("   val targetType=\"$targetType\"")
                } else {
                    out.println("public object $testCaseName {")
                    out.println("   internal val inputData=File(\"src/luposdate3000_launch_code_gen_test/src/jvmTest/resources/$testCaseName.input\").readAsString()")
                    out.println("   internal val targetData=File(\"src/luposdate3000_launch_code_gen_test/src/jvmTest/resources/$testCaseName.output\").readAsString()")
                    out.println("   internal val targetType=\"$targetType\"")
                }
                if (configIsCodeGen(configuration.second)) {
                    out.println("    @CodeGenerationAnnotation")
                }
                if (configIsBuildTime(configuration.second)) {
                    out.println("    val query = File(\"src/jvmTest/resources/$testCaseName.query\").readAsString()")
                    out.println("    @Test fun `${testName.filter { it.isLetterOrDigit() || it == ' ' }}`(){")
                    out.println("            val instance = LuposdateEndpoint.initialize()")
                } else {
                    out.println("    internal val query = File(\"src/luposdate3000_launch_code_gen_test/src/jvmTest/resources/$testCaseName.query\").readAsString()")
                    out.println("    internal operator fun invoke(){")
                    out.println("        println(\"Test #$counter: '$testName'\")")
                    out.println("        var success = true")
                    out.println("            val instance = LuposdateEndpoint.initialize()")
                    out.println("        try {")
                }
                out.println("            instance.LUPOS_BUFFER_SIZE=128")
                out.println("            LuposdateEndpoint.importTurtleString(instance,inputData)")
                if (configIsBuildTimeCodeGen(configuration.second)) {
                    out.println("                val op = query_evaluate()")
                } else {
                    out.println("            val op = LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance,query)")
                }
                out.println("            val buf = MyPrintWriter(true)")
                out.println("            val target = MemoryTable.parseFromAny(targetData, targetType, op.getQuery())!!")
                if (mode == BinaryTestCaseOutputModeExt.SELECT_QUERY_RESULT) {
                    out.println("            val result = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance,op, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()")
                } else {
                    out.println("            LuposdateEndpoint.evaluateOperatorgraphToResultA(instance,op, buf, EQueryResultToStreamExt.EMPTY_STREAM)")
                    out.println("            val graph = instance.tripleStoreManager!!.getGraph(\"\")")
                    out.println("            val op2 = graph.getIterator(op.getQuery(), arrayOf(AOPVariable(op.getQuery(), \"s\"), AOPVariable(op.getQuery(), \"p\"), AOPVariable(op.getQuery(), \"o\")), EIndexPatternExt.SPO)")
                    out.println("            val result = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance,op2, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()")
                }
                out.println("            val buf_err=MyPrintWriter()")
                out.println("            if (!target.equalsVerbose(result, ${!queryResultIsOrdered}, true,buf_err)) {")
                if (configIsBuildTime(configuration.second)) {
                    out.println("                fail(buf_err.toString())")
                } else {
                    out.println("                 println(buf_err.toString())")
                    out.println("                success = false")
                }
                out.println("            }")
                if (configIsRunTime(configuration.second)) {
                    out.println("        } catch (e:Throwable) {")
                    out.println("            e.printStackTrace()")
                    out.println("                success = false")
                    out.println("        }")
                }
                out.println("            LuposdateEndpoint.close(instance)") // for inmemory db this results in complete wipe of ALL data
                if (configIsRunTime(configuration.second)) {
                    out.println("        if (success) {")
                    out.println("            println(\"Result: '$testName' success\")")
                    out.println("        } else {")
                    out.println("            println(\"Result: '$testName' failed\")")
                    out.println("        }")
                }
                if (configIsRunTimeCodeGen(configuration.second)) {
                    out.println("        if(success){")
                    out.println("            var success2 = true")
                    out.println("               val instance2= LuposdateEndpoint.initialize()")
                    out.println("            instance2.LUPOS_BUFFER_SIZE=128")
                    out.println("            try {")
                    out.println("                LuposdateEndpoint.importTurtleString(instance2,inputData)")
                    out.println("                val op = query_evaluate()")
                    out.println("                val buf = MyPrintWriter(true)")
                    out.println("                val target = MemoryTable.parseFromAny(targetData, targetType, op.getQuery())!!")
                    if (mode == BinaryTestCaseOutputModeExt.SELECT_QUERY_RESULT) {
                        out.println("                val result =( LuposdateEndpoint.evaluateOperatorgraphToResultA(instance2,op, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()")
                    } else {
                        out.println("                LuposdateEndpoint.evaluateOperatorgraphToResultA(instance2,op, buf, EQueryResultToStreamExt.EMPTY_STREAM)")
                        out.println("                val graph = instance2.tripleStoreManager!!.getGraph(\"\")")
                        out.println("                val op2 = graph.getIterator(op.getQuery(), arrayOf(AOPVariable(op.getQuery(), \"s\"), AOPVariable(op.getQuery(), \"p\"), AOPVariable(op.getQuery(), \"o\")), EIndexPatternExt.SPO)")
                        out.println("                val result = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance2,op2, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()")
                    }
                    out.println("            val buf_err2=MyPrintWriter()")
                    out.println("                if (!target.equalsVerbose(result, ${!queryResultIsOrdered}, true,buf_err2)) {")
                    out.println("                 println(buf_err2.toString())")
                    out.println("                success2 = false")
                    out.println("                }")
                    out.println("            } catch (e:Throwable) {")
                    out.println("                e.printStackTrace()")
                    out.println("                success2 = false")
                    out.println("            }")
                    out.println("                instance2.close()") // for inmemory db this results in complete wipe of ALL data
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
