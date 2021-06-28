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
        val testCaseName2 = testName.filter { it.isLetterOrDigit() || it == ' ' }
        val testCaseName = testCaseName2.filter { it.isLetterOrDigit() }
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
        val inputType = outputFile!!.substring(inputFile.lastIndexOf("."))

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
        val ignoreListDueToTooSlow = setOf(
            "resourcesbsbmbiquery11853sparql1853",
            "resourcesbsbmbiquery12210sparql2210",
            "resourcesbsbmbiquery12553sparql2553",
            "resourcesbsbmbiquery21853sparql1853",
            "resourcesbsbmbiquery22210sparql2210",
            "resourcesbsbmbiquery22553sparql2553",
            "resourcesbsbmbiquery31853sparql1853",
            "resourcesbsbmbiquery32210sparql2210",
            "resourcesbsbmbiquery32553sparql2553",
            "resourcesbsbmbiquery41853sparql1853",
            "resourcesbsbmbiquery42210sparql2210",
            "resourcesbsbmbiquery42553sparql2553",
            "resourcesbsbmbiquery51853sparql1853",
            "resourcesbsbmbiquery52210sparql2210",
            "resourcesbsbmbiquery52553sparql2553",
            "resourcesbsbmbiquery61853sparql1853",
            "resourcesbsbmbiquery62210sparql2210",
            "resourcesbsbmbiquery62553sparql2553",
            "resourcesbsbmbiquery71853sparql1853",
            "resourcesbsbmbiquery72210sparql2210",
            "resourcesbsbmbiquery72553sparql2553",
            "resourcesbsbmbiquery81853sparql1853",
            "resourcesbsbmbiquery82210sparql2210",
            "resourcesbsbmbiquery82553sparql2553",
            "resourcesbsbmexplorequery101853sparql1853",
            "resourcesbsbmexplorequery102210sparql2210",
            "resourcesbsbmexplorequery102553sparql2553",
            "resourcesbsbmexplorequery111853sparql1853",
            "resourcesbsbmexplorequery112210sparql2210",
            "resourcesbsbmexplorequery112553sparql2553",
            "resourcesbsbmexplorequery11853sparql1853",
            "resourcesbsbmexplorequery121853sparql1853",
            "resourcesbsbmexplorequery12210sparql2210",
            "resourcesbsbmexplorequery122210sparql2210",
            "resourcesbsbmexplorequery122553sparql2553",
            "resourcesbsbmexplorequery12553sparql2553",
            "resourcesbsbmexplorequery21853sparql1853",
            "resourcesbsbmexplorequery22210sparql2210",
            "resourcesbsbmexplorequery22553sparql2553",
            "resourcesbsbmexplorequery31853sparql1853",
            "resourcesbsbmexplorequery32210sparql2210",
            "resourcesbsbmexplorequery32553sparql2553",
            "resourcesbsbmexplorequery41853sparql1853",
            "resourcesbsbmexplorequery42210sparql2210",
            "resourcesbsbmexplorequery42553sparql2553",
            "resourcesbsbmexplorequery51853sparql1853",
            "resourcesbsbmexplorequery52210sparql2210",
            "resourcesbsbmexplorequery52553sparql2553",
            "resourcesbsbmexplorequery61853sparql1853",
            "resourcesbsbmexplorequery62210sparql2210",
            "resourcesbsbmexplorequery62553sparql2553",
            "resourcesbsbmexplorequery71853sparql1853",
            "resourcesbsbmexplorequery72210sparql2210",
            "resourcesbsbmexplorequery72553sparql2553",
            "resourcesbsbmexplorequery81853sparql1853",
            "resourcesbsbmexplorequery82210sparql2210",
            "resourcesbsbmexplorequery82553sparql2553",
            "resourcesbsbmexplorequery91853sparql1853",
            "resourcesbsbmexplorequery92210sparql2210",
            "resourcesbsbmexplorequery92553sparql2553",
            "resourcesbtc002sparql46",
            "resourcesbtc005sparql578",
            "resourcesbtc020sparql80",
            "resourcesbtc028sparql3778",
            "resourcesbtc029sparql1198",
            "resourcesbtc030sparql867",
            "resourcesbtc035sparql61664",
            "resourcesmyqueriesoptional10sparql4",
            "resourcesmyqueriesoptional10sparql5",
            "resourcesmyqueriesoptional11sparql4",
            "resourcesmyqueriesoptional11sparql5",
            "resourcesmyqueriesoptional12sparql4",
            "resourcesmyqueriesoptional12sparql5",
            "resourcesmyqueriesoptional13sparql4",
            "resourcesmyqueriesoptional13sparql5",
            "resourcesmyqueriesoptional14sparql4",
            "resourcesmyqueriesoptional14sparql5",
            "resourcesmyqueriesoptional15sparql4",
            "resourcesmyqueriesoptional15sparql5",
            "resourcesmyqueriesoptional16sparql4",
            "resourcesmyqueriesoptional16sparql5",
            "resourcesmyqueriesoptional17sparql4",
            "resourcesmyqueriesoptional17sparql5",
            "resourcesmyqueriesoptional18sparql4",
            "resourcesmyqueriesoptional18sparql5",
            "resourcesmyqueriesoptional19sparql4",
            "resourcesmyqueriesoptional19sparql5",
            "resourcesmyqueriesoptional1sparql4",
            "resourcesmyqueriesoptional1sparql5",
            "resourcesmyqueriesoptional20sparql4",
            "resourcesmyqueriesoptional20sparql5",
            "resourcesmyqueriesoptional21sparql4",
            "resourcesmyqueriesoptional21sparql5",
            "resourcesmyqueriesoptional22sparql4",
            "resourcesmyqueriesoptional22sparql5",
            "resourcesmyqueriesoptional23sparql4",
            "resourcesmyqueriesoptional23sparql5",
            "resourcesmyqueriesoptional24sparql4",
            "resourcesmyqueriesoptional24sparql5",
            "resourcesmyqueriesoptional25sparql4",
            "resourcesmyqueriesoptional25sparql5",
            "resourcesmyqueriesoptional26sparql4",
            "resourcesmyqueriesoptional26sparql5",
            "resourcesmyqueriesoptional27sparql4",
            "resourcesmyqueriesoptional27sparql5",
            "resourcesmyqueriesoptional28sparql4",
            "resourcesmyqueriesoptional28sparql5",
            "resourcesmyqueriesoptional29sparql4",
            "resourcesmyqueriesoptional29sparql5",
            "resourcesmyqueriesoptional2sparql4",
            "resourcesmyqueriesoptional2sparql5",
            "resourcesmyqueriesoptional30sparql4",
            "resourcesmyqueriesoptional30sparql5",
            "resourcesmyqueriesoptional31sparql4",
            "resourcesmyqueriesoptional31sparql5",
            "resourcesmyqueriesoptional32sparql4",
            "resourcesmyqueriesoptional32sparql5",
            "resourcesmyqueriesoptional33sparql4",
            "resourcesmyqueriesoptional33sparql5",
            "resourcesmyqueriesoptional34sparql4",
            "resourcesmyqueriesoptional34sparql5",
            "resourcesmyqueriesoptional35sparql4",
            "resourcesmyqueriesoptional35sparql5",
            "resourcesmyqueriesoptional36sparql4",
            "resourcesmyqueriesoptional36sparql5",
            "resourcesmyqueriesoptional37sparql4",
            "resourcesmyqueriesoptional37sparql5",
            "resourcesmyqueriesoptional38sparql4",
            "resourcesmyqueriesoptional38sparql5",
            "resourcesmyqueriesoptional39sparql4",
            "resourcesmyqueriesoptional39sparql5",
            "resourcesmyqueriesoptional3sparql4",
            "resourcesmyqueriesoptional3sparql5",
            "resourcesmyqueriesoptional40sparql4",
            "resourcesmyqueriesoptional40sparql5",
            "resourcesmyqueriesoptional41sparql4",
            "resourcesmyqueriesoptional41sparql5",
            "resourcesmyqueriesoptional42sparql4",
            "resourcesmyqueriesoptional42sparql5",
            "resourcesmyqueriesoptional43sparql4",
            "resourcesmyqueriesoptional43sparql5",
            "resourcesmyqueriesoptional44sparql4",
            "resourcesmyqueriesoptional44sparql5",
            "resourcesmyqueriesoptional45sparql4",
            "resourcesmyqueriesoptional45sparql5",
            "resourcesmyqueriesoptional46sparql4",
            "resourcesmyqueriesoptional46sparql5",
            "resourcesmyqueriesoptional47sparql4",
            "resourcesmyqueriesoptional47sparql5",
            "resourcesmyqueriesoptional48sparql4",
            "resourcesmyqueriesoptional48sparql5",
            "resourcesmyqueriesoptional49sparql4",
            "resourcesmyqueriesoptional49sparql5",
            "resourcesmyqueriesoptional4sparql4",
            "resourcesmyqueriesoptional4sparql5",
            "resourcesmyqueriesoptional50sparql4",
            "resourcesmyqueriesoptional50sparql5",
            "resourcesmyqueriesoptional51sparql4",
            "resourcesmyqueriesoptional51sparql5",
            "resourcesmyqueriesoptional52sparql4",
            "resourcesmyqueriesoptional52sparql5",
            "resourcesmyqueriesoptional53sparql4",
            "resourcesmyqueriesoptional53sparql5",
            "resourcesmyqueriesoptional54sparql4",
            "resourcesmyqueriesoptional54sparql5",
            "resourcesmyqueriesoptional55sparql4",
            "resourcesmyqueriesoptional55sparql5",
            "resourcesmyqueriesoptional56sparql4",
            "resourcesmyqueriesoptional56sparql5",
            "resourcesmyqueriesoptional57sparql4",
            "resourcesmyqueriesoptional57sparql5",
            "resourcesmyqueriesoptional58sparql4",
            "resourcesmyqueriesoptional58sparql5",
            "resourcesmyqueriesoptional59sparql4",
            "resourcesmyqueriesoptional59sparql5",
            "resourcesmyqueriesoptional5sparql4",
            "resourcesmyqueriesoptional5sparql5",
            "resourcesmyqueriesoptional60sparql4",
            "resourcesmyqueriesoptional60sparql5",
            "resourcesmyqueriesoptional61sparql4",
            "resourcesmyqueriesoptional61sparql5",
            "resourcesmyqueriesoptional62sparql4",
            "resourcesmyqueriesoptional62sparql5",
            "resourcesmyqueriesoptional63sparql4",
            "resourcesmyqueriesoptional63sparql5",
            "resourcesmyqueriesoptional64sparql4",
            "resourcesmyqueriesoptional64sparql5",
            "resourcesmyqueriesoptional6sparql4",
            "resourcesmyqueriesoptional6sparql5",
            "resourcesmyqueriesoptional7sparql4",
            "resourcesmyqueriesoptional7sparql5",
            "resourcesmyqueriesoptional8sparql4",
            "resourcesmyqueriesoptional8sparql5",
            "resourcesmyqueriesoptional9sparql4",
            "resourcesmyqueriesoptional9sparql5",
            "resourcesmyqueriesx1sparql5",
            "resourcesmyqueriesx2sparql10",
            "resourcesmyqueriesx3sparql4",
            "resourcesmyqueriesx4sparql4",
            "resourcesmyqueriesx5sparql4",
            "resourcesmyqueriesx6sparql4",
            "resourcesmyqueriesx7sparql4",
            "resourcessp2bq10sparql1294",
            "resourcessp2bq10sparql1640",
            "resourcessp2bq10sparql21",
            "resourcessp2bq10sparql247",
            "resourcessp2bq10sparql32978",
            "resourcessp2bq10sparql700",
            "resourcessp2bq10sparql973",
            "resourcessp2bq111sparql1294",
            "resourcessp2bq111sparql1640",
            "resourcessp2bq111sparql21",
            "resourcessp2bq111sparql247",
            "resourcessp2bq111sparql32978",
            "resourcessp2bq111sparql700",
            "resourcessp2bq111sparql973",
            "resourcessp2bq11sparql1294",
            "resourcessp2bq11sparql1640",
            "resourcessp2bq11sparql21",
            "resourcessp2bq11sparql247",
            "resourcessp2bq11sparql32978",
            "resourcessp2bq11sparql700",
            "resourcessp2bq11sparql973",
            "resourcessp2bq12a1sparql1294",
            "resourcessp2bq12a1sparql1640",
            "resourcessp2bq12a1sparql21",
            "resourcessp2bq12a1sparql247",
            "resourcessp2bq12a1sparql32978",
            "resourcessp2bq12a1sparql700",
            "resourcessp2bq12a1sparql973",
            "resourcessp2bq12asparql1294",
            "resourcessp2bq12asparql1640",
            "resourcessp2bq12asparql21",
            "resourcessp2bq12asparql247",
            "resourcessp2bq12asparql32978",
            "resourcessp2bq12asparql700",
            "resourcessp2bq12asparql973",
            "resourcessp2bq12b1sparql1294",
            "resourcessp2bq12b1sparql1640",
            "resourcessp2bq12b1sparql21",
            "resourcessp2bq12b1sparql247",
            "resourcessp2bq12b1sparql32978",
            "resourcessp2bq12b1sparql700",
            "resourcessp2bq12b1sparql973",
            "resourcessp2bq12b2sparql1294",
            "resourcessp2bq12b2sparql1640",
            "resourcessp2bq12b2sparql21",
            "resourcessp2bq12b2sparql247",
            "resourcessp2bq12b2sparql32978",
            "resourcessp2bq12b2sparql700",
            "resourcessp2bq12b2sparql973",
            "resourcessp2bq12b3sparql1294",
            "resourcessp2bq12b3sparql1640",
            "resourcessp2bq12b3sparql21",
            "resourcessp2bq12b3sparql247",
            "resourcessp2bq12b3sparql32978",
            "resourcessp2bq12b3sparql700",
            "resourcessp2bq12b3sparql973",
            "resourcessp2bq12b4sparql1294",
            "resourcessp2bq12b4sparql1640",
            "resourcessp2bq12b4sparql21",
            "resourcessp2bq12b4sparql247",
            "resourcessp2bq12b4sparql32978",
            "resourcessp2bq12b4sparql700",
            "resourcessp2bq12b4sparql973",
            "resourcessp2bq12bsparql1294",
            "resourcessp2bq12bsparql1640",
            "resourcessp2bq12bsparql21",
            "resourcessp2bq12bsparql247",
            "resourcessp2bq12bsparql32978",
            "resourcessp2bq12bsparql700",
            "resourcessp2bq12bsparql973",
            "resourcessp2bq12csparql1294",
            "resourcessp2bq12csparql1640",
            "resourcessp2bq12csparql21",
            "resourcessp2bq12csparql247",
            "resourcessp2bq12csparql32978",
            "resourcessp2bq12csparql700",
            "resourcessp2bq12csparql973",
            "resourcessp2bq1sparql1294",
            "resourcessp2bq1sparql1640",
            "resourcessp2bq1sparql21",
            "resourcessp2bq1sparql247",
            "resourcessp2bq1sparql32978",
            "resourcessp2bq1sparql700",
            "resourcessp2bq1sparql973",
            "resourcessp2bq2sparql1294",
            "resourcessp2bq2sparql1640",
            "resourcessp2bq2sparql21",
            "resourcessp2bq2sparql247",
            "resourcessp2bq2sparql32978",
            "resourcessp2bq2sparql700",
            "resourcessp2bq2sparql973",
            "resourcessp2bq3asparql1294",
            "resourcessp2bq3asparql1640",
            "resourcessp2bq3asparql21",
            "resourcessp2bq3asparql247",
            "resourcessp2bq3asparql32978",
            "resourcessp2bq3asparql700",
            "resourcessp2bq3asparql973",
            "resourcessp2bq3bsparql1294",
            "resourcessp2bq3bsparql1640",
            "resourcessp2bq3bsparql21",
            "resourcessp2bq3bsparql247",
            "resourcessp2bq3bsparql32978",
            "resourcessp2bq3bsparql700",
            "resourcessp2bq3bsparql973",
            "resourcessp2bq3csparql1294",
            "resourcessp2bq3csparql1640",
            "resourcessp2bq3csparql21",
            "resourcessp2bq3csparql247",
            "resourcessp2bq3csparql32978",
            "resourcessp2bq3csparql700",
            "resourcessp2bq3csparql973",
            "resourcessp2bq41sparql1294",
            "resourcessp2bq41sparql1640",
            "resourcessp2bq41sparql21",
            "resourcessp2bq41sparql247",
            "resourcessp2bq41sparql32978",
            "resourcessp2bq41sparql700",
            "resourcessp2bq41sparql973",
            "resourcessp2bq42sparql1294",
            "resourcessp2bq42sparql1640",
            "resourcessp2bq42sparql21",
            "resourcessp2bq42sparql247",
            "resourcessp2bq42sparql32978",
            "resourcessp2bq42sparql700",
            "resourcessp2bq42sparql973",
            "resourcessp2bq4sparql1294",
            "resourcessp2bq4sparql1640",
            "resourcessp2bq4sparql21",
            "resourcessp2bq4sparql247",
            "resourcessp2bq4sparql32978",
            "resourcessp2bq4sparql700",
            "resourcessp2bq4sparql973",
            "resourcessp2bq5asparql1294",
            "resourcessp2bq5asparql1640",
            "resourcessp2bq5asparql21",
            "resourcessp2bq5asparql247",
            "resourcessp2bq5asparql32978",
            "resourcessp2bq5asparql700",
            "resourcessp2bq5asparql973",
            "resourcessp2bq5bsparql1294",
            "resourcessp2bq5bsparql1640",
            "resourcessp2bq5bsparql21",
            "resourcessp2bq5bsparql247",
            "resourcessp2bq5bsparql32978",
            "resourcessp2bq5bsparql700",
            "resourcessp2bq5bsparql973",
            "resourcessp2bq61sparql1294",
            "resourcessp2bq61sparql1640",
            "resourcessp2bq61sparql21",
            "resourcessp2bq61sparql247",
            "resourcessp2bq61sparql32978",
            "resourcessp2bq61sparql700",
            "resourcessp2bq61sparql973",
            "resourcessp2bq62sparql1294",
            "resourcessp2bq62sparql1640",
            "resourcessp2bq62sparql21",
            "resourcessp2bq62sparql247",
            "resourcessp2bq62sparql32978",
            "resourcessp2bq62sparql700",
            "resourcessp2bq62sparql973",
            "resourcessp2bq63sparql1294",
            "resourcessp2bq63sparql1640",
            "resourcessp2bq63sparql21",
            "resourcessp2bq63sparql247",
            "resourcessp2bq63sparql32978",
            "resourcessp2bq63sparql700",
            "resourcessp2bq63sparql973",
            "resourcessp2bq65sparql1294",
            "resourcessp2bq65sparql1640",
            "resourcessp2bq65sparql21",
            "resourcessp2bq65sparql247",
            "resourcessp2bq65sparql32978",
            "resourcessp2bq65sparql700",
            "resourcessp2bq65sparql973",
            "resourcessp2bq66sparql1294",
            "resourcessp2bq66sparql1640",
            "resourcessp2bq66sparql21",
            "resourcessp2bq66sparql247",
            "resourcessp2bq66sparql32978",
            "resourcessp2bq66sparql700",
            "resourcessp2bq66sparql973",
            "resourcessp2bq67sparql1294",
            "resourcessp2bq67sparql1640",
            "resourcessp2bq67sparql21",
            "resourcessp2bq67sparql247",
            "resourcessp2bq67sparql32978",
            "resourcessp2bq67sparql700",
            "resourcessp2bq67sparql973",
            "resourcessp2bq68sparql1294",
            "resourcessp2bq68sparql1640",
            "resourcessp2bq68sparql21",
            "resourcessp2bq68sparql247",
            "resourcessp2bq68sparql32978",
            "resourcessp2bq68sparql700",
            "resourcessp2bq68sparql973",
            "resourcessp2bq6sparql1294",
            "resourcessp2bq6sparql1640",
            "resourcessp2bq6sparql21",
            "resourcessp2bq6sparql247",
            "resourcessp2bq6sparql32978",
            "resourcessp2bq6sparql700",
            "resourcessp2bq6sparql973",
            "resourcessp2bq71sparql1294",
            "resourcessp2bq71sparql1640",
            "resourcessp2bq71sparql21",
            "resourcessp2bq71sparql247",
            "resourcessp2bq71sparql32978",
            "resourcessp2bq71sparql700",
            "resourcessp2bq71sparql973",
            "resourcessp2bq72sparql1294",
            "resourcessp2bq72sparql1640",
            "resourcessp2bq72sparql21",
            "resourcessp2bq72sparql247",
            "resourcessp2bq72sparql32978",
            "resourcessp2bq72sparql700",
            "resourcessp2bq72sparql973",
            "resourcessp2bq7sparql1294",
            "resourcessp2bq7sparql1640",
            "resourcessp2bq7sparql21",
            "resourcessp2bq7sparql247",
            "resourcessp2bq7sparql32978",
            "resourcessp2bq7sparql700",
            "resourcessp2bq7sparql973",
            "resourcessp2bq8sparql1294",
            "resourcessp2bq8sparql1640",
            "resourcessp2bq8sparql21",
            "resourcessp2bq8sparql247",
            "resourcessp2bq8sparql32978",
            "resourcessp2bq8sparql700",
            "resourcessp2bq8sparql973",
            "resourcessp2bq91sparql1294",
            "resourcessp2bq91sparql1640",
            "resourcessp2bq91sparql21",
            "resourcessp2bq91sparql247",
            "resourcessp2bq91sparql32978",
            "resourcessp2bq91sparql700",
            "resourcessp2bq91sparql973",
            "resourcessp2bq92sparql1294",
            "resourcessp2bq92sparql1640",
            "resourcessp2bq92sparql21",
            "resourcessp2bq92sparql247",
            "resourcessp2bq92sparql32978",
            "resourcessp2bq92sparql700",
            "resourcessp2bq92sparql973",
            "resourcessp2bq9sparql1294",
            "resourcessp2bq9sparql1640",
            "resourcessp2bq9sparql21",
            "resourcessp2bq9sparql247",
            "resourcessp2bq9sparql32978",
            "resourcessp2bq9sparql700",
            "resourcessp2bq9sparql973",
        )
        val ignoreListDueToNotImplemented = setOf(
            "Nestedpositiveexists",
            "GROUPCONCAT2",
            "pp03Simplepathwithloop",
            "pp23Diamondwithtailp",
            "SHA512onUnicodedata",
            "IN1",
            "SUBSTR3argument",
            "ENCODEFORURI",
            "pp30Operatorprecedence1",
            "PositiveEXISTS1",
            "pp12Variablelengthpathandtwopathstosametargetnode",
            "Existswithoneconstant",
            "REPLACEwithcapturedsubstring",
            "STRUUIDpatternmatch",
            "NOTIN2",
            "pp31Operatorprecedence2",
            "GROUPCONCAT1",
            "pp08Reversepath",
            "SHA512",
            "Existswithgroundtriple",
            "pp01Simplepath",
            "Nestednegativeexistsinpositiveexists",
            "REPLACE",
            "pp11Simplepathandtwopathstosametargetnode",
            "IN2",
            "SUBSTR2argument",
            "pp02Starpath",
            "pp36Arbitrarypathwithboundendpoints",
            "pp09Reversesequencepath",
            "pp14Starpathoverfoafknows",
            "pp33Operatorprecedence4",
            "pp16Duplicatepathsandcyclesthroughfoafknows",
            "GROUPCONCATwithSEPARATOR",
            "RAND",
            "pp32Operatorprecedence3",
            "pp25Diamondwithloopp",
            "pp21Diamondp",
            "pp10Pathwithnegation",
            "UUIDpatternmatch",
            "pp37Nested",
            "REPLACEwithoverlappingpattern",
            "PositiveEXISTS2",
            "pp28aDiamondwithlooppp",
            "jsonres01JSONResultFormat",
            "jsonres02JSONResultFormat",
            "jsonres03JSONResultFormat",
            "jsonres04JSONResultFormat",
        )
        val ignoreListDueToBugs = setOf(
            "csv03CSVResultFormat", // csv-parser
        )
        val ignoreList = ignoreListDueToTooSlow + ignoreListDueToNotImplemented
        val acceptedInputTypes = listOf(
            "n3",
        )
        var ignored = ignoreList.contains(testCaseName)
        ignored = ignored || !acceptedInputTypes.contains(inputType)
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
                out.println("import lupos." + "shared.inline.MyPrintWriter")
                out.println("import lupos." + "shared.inline.File")
                out.println("import lupos.shared.MemoryTable")
                out.println("import lupos.result_format.EQueryResultToStreamExt")
                out.println("import lupos.operator.arithmetik.noinput.AOPVariable")
                out.println("import lupos.shared.EIndexPatternExt")
                out.println("import lupos.operator.base.Query")
                if (configIsBuildTime(configuration.second)) {
                    out.println("import kotlin.test.Test")
                    out.println("import kotlin.test.fail")
                    if (ignored) {
                        out.println("import kotlin.test.Ignore")
                    }
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
                    out.println("   val inputType=\"$inputType\"")
                } else {
                    out.println("public object $testCaseName {")
                    out.println("   internal val inputData=File(\"src/luposdate3000_launch_code_gen_test/src/jvmTest/resources/$testCaseName.input\").readAsString()")
                    out.println("   internal val targetData=File(\"src/luposdate3000_launch_code_gen_test/src/jvmTest/resources/$testCaseName.output\").readAsString()")
                    out.println("   internal val targetType=\"$targetType\"")
                    out.println("   internal val inputType=\"$inputType\"")
                }
                if (configIsCodeGen(configuration.second)) {
                    out.println("    @CodeGenerationAnnotation")
                }
                if (configIsBuildTime(configuration.second)) {
                    out.println("    val query = File(\"src/jvmTest/resources/$testCaseName.query\").readAsString()")
                    if (ignored) {
                        out.println("    @Ignore")
                    }
                    out.println("    @Test fun `$testCaseName2}`(){")
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
                out.println("            if(inputType==\"n3\") {")
                out.println("                LuposdateEndpoint.importTurtleString(instance,inputData)")
                out.println("            }else{")
                out.println("                TODO()")
                out.println("            }")
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
