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

    internal val folderPathCoponent = "code_gen_test"

    internal val outputFolderRoot = "src/luposdate3000_launch_$folderPathCoponent/"
    internal val outputFolderSrc = "src/luposdate3000_launch_$folderPathCoponent/src/jvmMain/kotlin/lupos/launch/$folderPathCoponent/"
    internal val allTests = mutableListOf<String>()

    init {
        prefixDirectory = "$resource_folder/"
        File(outputFolderRoot).deleteRecursively()
        File(outputFolderRoot).mkdirs()
        File(outputFolderSrc).mkdirs()
        File(outputFolderRoot + "/module_config").withOutputStream { out ->
            out.println("package=Luposdate3000_Main")
            out.println("codegenKAPT=true")
        }
        File(outputFolderRoot + "/runOptions").withOutputStream {}
        File(outputFolderRoot + "/disableTarget").withOutputStream { out ->
            out.println("js")
        }
    }

    public fun finish() {
        File(outputFolderSrc + "/MainFunc.kt").withOutputStream { out ->
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
        val testCaseFileName = "$outputFolderSrc/$testCaseName.kt"
        allTests.add("$testCaseName")

        val queryFileContent = File(queryFile).readAsString()
        val queryFileContentClean = queryFileContent.replace("\"", "\\\"").replace("\n", "\" +\n        \"").replace("\r", "\" +\n        \"").replace("\t", " ")

        File(testCaseFileName).withOutputStream { out ->
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
            out.println("")
            out.println("internal object $testCaseName {")
            out.println("    internal const val inputFileName=\"$inputFile\"")
            out.println("    internal const val targetFileName=\"${outputFile!!}\"")
            out.println("    internal const val query = \"${queryFileContentClean}\"")
            out.println("    internal operator fun invoke(){")
            out.println("        println(\"Test: '$testName'\")")
            out.println("        var success = true")
            out.println("        try {")
            out.println("            try {")
            out.println("                tripleStoreManager.dropGraph(Query(),\"\")")
            out.println("            }catch (e:Throwable){")
            out.println("            }")
            out.println("            LuposdateEndpoint.importTurtleFiles(inputFileName,mutableMapOf())")
            out.println("            val op = LuposdateEndpoint.evaluateSparqlToOperatorgraphA(query)")
            out.println("            val buf = MyPrintWriter(true)")
            out.println("            val targetString = File(targetFileName).readAsString()")
            out.println("            val target = MemoryTable.parseFromAny(targetString, targetFileName, op.getQuery())!!")
            if (mode == BinaryTestCaseOutputModeExt.SELECT_QUERY_RESULT) {
                out.println("            val result = LuposdateEndpoint.evaluateOperatorgraphToResultA(op,buf,EQueryResultToStreamExt.MEMORY_TABLE)")
            } else {
                out.println("            LuposdateEndpoint.evaluateOperatorgraphToResultA(op,buf,EQueryResultToStreamExt.EMPTY_STREAM)")
                out.println("            val graph = tripleStoreManager.getGraph(\"\")")
                out.println("            val op2 = graph.getIterator(op.getQuery(), arrayOf(AOPVariable(op.getQuery(), \"s\"),AOPVariable(op.getQuery(), \"p\"),AOPVariable(op.getQuery(), \"o\")),EIndexPatternExt.SPO)")
                out.println("            val result = LuposdateEndpoint.evaluateOperatorgraphToResultA(op2,buf,EQueryResultToStreamExt.MEMORY_TABLE)")
            }
            val ordered = queryFileContentClean.toLowerCase().contains("order", true)
            out.println("            if (target.equalsVerbose(result,${!ordered},true)){")
            out.println("                success=false")
            out.println("            }")
            out.println("        }catch (e:Throwable){")
            out.println("            e.printStackTrace()")
            out.println("            success=false")
            out.println("        }")
            out.println("        if(success){")
            out.println("            println(\"Result: '$testName' success\")")
            out.println("        }else{")
            out.println("            println(\"Result: '$testName' failed\")")
            out.println("        }")
            out.println("    }")
            out.println("}")
        }
        return true
    }
}
