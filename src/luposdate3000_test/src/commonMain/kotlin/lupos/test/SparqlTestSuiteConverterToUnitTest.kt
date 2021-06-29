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

    @JvmField
    internal var counter = 0

    @JvmField
    internal var lastFile: String = ""

    @JvmField
    internal val folderPathCoponent = "code_gen_test"

    @JvmField
    internal val outputFolderRoot = "src/luposdate3000_launch_$folderPathCoponent/"

    @JvmField
    internal val outputFolderSrcJvm = "${outputFolderRoot}src/jvmMain/kotlin/lupos/launch/$folderPathCoponent/"

    @JvmField
    internal val outputFolderTestJvm = "${outputFolderRoot}src/jvmTest/kotlin/lupos/launch/$folderPathCoponent/jvm/"

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
            out.println("package=Luposdate3000_Main")
            out.println("disableJS=true")
            if (withCodeGen) {
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

    private val ignoreListDueToTooSlow = mapOf(
        "resourcesbsbmbiquery11853sparql1853" to "too slow",
        "resourcesbsbmbiquery12210sparql2210" to "too slow",
        "resourcesbsbmbiquery12553sparql2553" to "too slow",
        "resourcesbsbmbiquery21853sparql1853" to "too slow",
        "resourcesbsbmbiquery22210sparql2210" to "too slow",
        "resourcesbsbmbiquery22553sparql2553" to "too slow",
        "resourcesbsbmbiquery31853sparql1853" to "too slow",
        "resourcesbsbmbiquery32210sparql2210" to "too slow",
        "resourcesbsbmbiquery32553sparql2553" to "too slow",
        "resourcesbsbmbiquery41853sparql1853" to "too slow",
        "resourcesbsbmbiquery42210sparql2210" to "too slow",
        "resourcesbsbmbiquery42553sparql2553" to "too slow",
        "resourcesbsbmbiquery51853sparql1853" to "too slow",
        "resourcesbsbmbiquery52210sparql2210" to "too slow",
        "resourcesbsbmbiquery52553sparql2553" to "too slow",
        "resourcesbsbmbiquery61853sparql1853" to "too slow",
        "resourcesbsbmbiquery62210sparql2210" to "too slow",
        "resourcesbsbmbiquery62553sparql2553" to "too slow",
        "resourcesbsbmbiquery71853sparql1853" to "too slow",
        "resourcesbsbmbiquery72210sparql2210" to "too slow",
        "resourcesbsbmbiquery72553sparql2553" to "too slow",
        "resourcesbsbmbiquery81853sparql1853" to "too slow",
        "resourcesbsbmbiquery82210sparql2210" to "too slow",
        "resourcesbsbmbiquery82553sparql2553" to "too slow",
        "resourcesbsbmexplorequery101853sparql1853" to "too slow",
        "resourcesbsbmexplorequery102210sparql2210" to "too slow",
        "resourcesbsbmexplorequery102553sparql2553" to "too slow",
        "resourcesbsbmexplorequery111853sparql1853" to "too slow",
        "resourcesbsbmexplorequery112210sparql2210" to "too slow",
        "resourcesbsbmexplorequery112553sparql2553" to "too slow",
        "resourcesbsbmexplorequery11853sparql1853" to "too slow",
        "resourcesbsbmexplorequery121853sparql1853" to "too slow",
        "resourcesbsbmexplorequery12210sparql2210" to "too slow",
        "resourcesbsbmexplorequery122210sparql2210" to "too slow",
        "resourcesbsbmexplorequery122553sparql2553" to "too slow",
        "resourcesbsbmexplorequery12553sparql2553" to "too slow",
        "resourcesbsbmexplorequery21853sparql1853" to "too slow",
        "resourcesbsbmexplorequery22210sparql2210" to "too slow",
        "resourcesbsbmexplorequery22553sparql2553" to "too slow",
        "resourcesbsbmexplorequery31853sparql1853" to "too slow",
        "resourcesbsbmexplorequery32210sparql2210" to "too slow",
        "resourcesbsbmexplorequery32553sparql2553" to "too slow",
        "resourcesbsbmexplorequery41853sparql1853" to "too slow",
        "resourcesbsbmexplorequery42210sparql2210" to "too slow",
        "resourcesbsbmexplorequery42553sparql2553" to "too slow",
        "resourcesbsbmexplorequery51853sparql1853" to "too slow",
        "resourcesbsbmexplorequery52210sparql2210" to "too slow",
        "resourcesbsbmexplorequery52553sparql2553" to "too slow",
        "resourcesbsbmexplorequery61853sparql1853" to "too slow",
        "resourcesbsbmexplorequery62210sparql2210" to "too slow",
        "resourcesbsbmexplorequery62553sparql2553" to "too slow",
        "resourcesbsbmexplorequery71853sparql1853" to "too slow",
        "resourcesbsbmexplorequery72210sparql2210" to "too slow",
        "resourcesbsbmexplorequery72553sparql2553" to "too slow",
        "resourcesbsbmexplorequery81853sparql1853" to "too slow",
        "resourcesbsbmexplorequery82210sparql2210" to "too slow",
        "resourcesbsbmexplorequery82553sparql2553" to "too slow",
        "resourcesbsbmexplorequery91853sparql1853" to "too slow",
        "resourcesbsbmexplorequery92210sparql2210" to "too slow",
        "resourcesbsbmexplorequery92553sparql2553" to "too slow",
        "resourcesbtc002sparql46" to "too slow",
        "resourcesbtc005sparql578" to "too slow",
        "resourcesbtc020sparql80" to "too slow",
        "resourcesbtc028sparql3778" to "too slow",
        "resourcesbtc029sparql1198" to "too slow",
        "resourcesbtc030sparql867" to "too slow",
        "resourcesbtc035sparql61664" to "too slow",
        "resourcesmyqueriesoptional10sparql4" to "too slow",
        "resourcesmyqueriesoptional10sparql5" to "too slow",
        "resourcesmyqueriesoptional11sparql4" to "too slow",
        "resourcesmyqueriesoptional11sparql5" to "too slow",
        "resourcesmyqueriesoptional12sparql4" to "too slow",
        "resourcesmyqueriesoptional12sparql5" to "too slow",
        "resourcesmyqueriesoptional13sparql4" to "too slow",
        "resourcesmyqueriesoptional13sparql5" to "too slow",
        "resourcesmyqueriesoptional14sparql4" to "too slow",
        "resourcesmyqueriesoptional14sparql5" to "too slow",
        "resourcesmyqueriesoptional15sparql4" to "too slow",
        "resourcesmyqueriesoptional15sparql5" to "too slow",
        "resourcesmyqueriesoptional16sparql4" to "too slow",
        "resourcesmyqueriesoptional16sparql5" to "too slow",
        "resourcesmyqueriesoptional17sparql4" to "too slow",
        "resourcesmyqueriesoptional17sparql5" to "too slow",
        "resourcesmyqueriesoptional18sparql4" to "too slow",
        "resourcesmyqueriesoptional18sparql5" to "too slow",
        "resourcesmyqueriesoptional19sparql4" to "too slow",
        "resourcesmyqueriesoptional19sparql5" to "too slow",
        "resourcesmyqueriesoptional1sparql4" to "too slow",
        "resourcesmyqueriesoptional1sparql5" to "too slow",
        "resourcesmyqueriesoptional20sparql4" to "too slow",
        "resourcesmyqueriesoptional20sparql5" to "too slow",
        "resourcesmyqueriesoptional21sparql4" to "too slow",
        "resourcesmyqueriesoptional21sparql5" to "too slow",
        "resourcesmyqueriesoptional22sparql4" to "too slow",
        "resourcesmyqueriesoptional22sparql5" to "too slow",
        "resourcesmyqueriesoptional23sparql4" to "too slow",
        "resourcesmyqueriesoptional23sparql5" to "too slow",
        "resourcesmyqueriesoptional24sparql4" to "too slow",
        "resourcesmyqueriesoptional24sparql5" to "too slow",
        "resourcesmyqueriesoptional25sparql4" to "too slow",
        "resourcesmyqueriesoptional25sparql5" to "too slow",
        "resourcesmyqueriesoptional26sparql4" to "too slow",
        "resourcesmyqueriesoptional26sparql5" to "too slow",
        "resourcesmyqueriesoptional27sparql4" to "too slow",
        "resourcesmyqueriesoptional27sparql5" to "too slow",
        "resourcesmyqueriesoptional28sparql4" to "too slow",
        "resourcesmyqueriesoptional28sparql5" to "too slow",
        "resourcesmyqueriesoptional29sparql4" to "too slow",
        "resourcesmyqueriesoptional29sparql5" to "too slow",
        "resourcesmyqueriesoptional2sparql4" to "too slow",
        "resourcesmyqueriesoptional2sparql5" to "too slow",
        "resourcesmyqueriesoptional30sparql4" to "too slow",
        "resourcesmyqueriesoptional30sparql5" to "too slow",
        "resourcesmyqueriesoptional31sparql4" to "too slow",
        "resourcesmyqueriesoptional31sparql5" to "too slow",
        "resourcesmyqueriesoptional32sparql4" to "too slow",
        "resourcesmyqueriesoptional32sparql5" to "too slow",
        "resourcesmyqueriesoptional33sparql4" to "too slow",
        "resourcesmyqueriesoptional33sparql5" to "too slow",
        "resourcesmyqueriesoptional34sparql4" to "too slow",
        "resourcesmyqueriesoptional34sparql5" to "too slow",
        "resourcesmyqueriesoptional35sparql4" to "too slow",
        "resourcesmyqueriesoptional35sparql5" to "too slow",
        "resourcesmyqueriesoptional36sparql4" to "too slow",
        "resourcesmyqueriesoptional36sparql5" to "too slow",
        "resourcesmyqueriesoptional37sparql4" to "too slow",
        "resourcesmyqueriesoptional37sparql5" to "too slow",
        "resourcesmyqueriesoptional38sparql4" to "too slow",
        "resourcesmyqueriesoptional38sparql5" to "too slow",
        "resourcesmyqueriesoptional39sparql4" to "too slow",
        "resourcesmyqueriesoptional39sparql5" to "too slow",
        "resourcesmyqueriesoptional3sparql4" to "too slow",
        "resourcesmyqueriesoptional3sparql5" to "too slow",
        "resourcesmyqueriesoptional40sparql4" to "too slow",
        "resourcesmyqueriesoptional40sparql5" to "too slow",
        "resourcesmyqueriesoptional41sparql4" to "too slow",
        "resourcesmyqueriesoptional41sparql5" to "too slow",
        "resourcesmyqueriesoptional42sparql4" to "too slow",
        "resourcesmyqueriesoptional42sparql5" to "too slow",
        "resourcesmyqueriesoptional43sparql4" to "too slow",
        "resourcesmyqueriesoptional43sparql5" to "too slow",
        "resourcesmyqueriesoptional44sparql4" to "too slow",
        "resourcesmyqueriesoptional44sparql5" to "too slow",
        "resourcesmyqueriesoptional45sparql4" to "too slow",
        "resourcesmyqueriesoptional45sparql5" to "too slow",
        "resourcesmyqueriesoptional46sparql4" to "too slow",
        "resourcesmyqueriesoptional46sparql5" to "too slow",
        "resourcesmyqueriesoptional47sparql4" to "too slow",
        "resourcesmyqueriesoptional47sparql5" to "too slow",
        "resourcesmyqueriesoptional48sparql4" to "too slow",
        "resourcesmyqueriesoptional48sparql5" to "too slow",
        "resourcesmyqueriesoptional49sparql4" to "too slow",
        "resourcesmyqueriesoptional49sparql5" to "too slow",
        "resourcesmyqueriesoptional4sparql4" to "too slow",
        "resourcesmyqueriesoptional4sparql5" to "too slow",
        "resourcesmyqueriesoptional50sparql4" to "too slow",
        "resourcesmyqueriesoptional50sparql5" to "too slow",
        "resourcesmyqueriesoptional51sparql4" to "too slow",
        "resourcesmyqueriesoptional51sparql5" to "too slow",
        "resourcesmyqueriesoptional52sparql4" to "too slow",
        "resourcesmyqueriesoptional52sparql5" to "too slow",
        "resourcesmyqueriesoptional53sparql4" to "too slow",
        "resourcesmyqueriesoptional53sparql5" to "too slow",
        "resourcesmyqueriesoptional54sparql4" to "too slow",
        "resourcesmyqueriesoptional54sparql5" to "too slow",
        "resourcesmyqueriesoptional55sparql4" to "too slow",
        "resourcesmyqueriesoptional55sparql5" to "too slow",
        "resourcesmyqueriesoptional56sparql4" to "too slow",
        "resourcesmyqueriesoptional56sparql5" to "too slow",
        "resourcesmyqueriesoptional57sparql4" to "too slow",
        "resourcesmyqueriesoptional57sparql5" to "too slow",
        "resourcesmyqueriesoptional58sparql4" to "too slow",
        "resourcesmyqueriesoptional58sparql5" to "too slow",
        "resourcesmyqueriesoptional59sparql4" to "too slow",
        "resourcesmyqueriesoptional59sparql5" to "too slow",
        "resourcesmyqueriesoptional5sparql4" to "too slow",
        "resourcesmyqueriesoptional5sparql5" to "too slow",
        "resourcesmyqueriesoptional60sparql4" to "too slow",
        "resourcesmyqueriesoptional60sparql5" to "too slow",
        "resourcesmyqueriesoptional61sparql4" to "too slow",
        "resourcesmyqueriesoptional61sparql5" to "too slow",
        "resourcesmyqueriesoptional62sparql4" to "too slow",
        "resourcesmyqueriesoptional62sparql5" to "too slow",
        "resourcesmyqueriesoptional63sparql4" to "too slow",
        "resourcesmyqueriesoptional63sparql5" to "too slow",
        "resourcesmyqueriesoptional64sparql4" to "too slow",
        "resourcesmyqueriesoptional64sparql5" to "too slow",
        "resourcesmyqueriesoptional6sparql4" to "too slow",
        "resourcesmyqueriesoptional6sparql5" to "too slow",
        "resourcesmyqueriesoptional7sparql4" to "too slow",
        "resourcesmyqueriesoptional7sparql5" to "too slow",
        "resourcesmyqueriesoptional8sparql4" to "too slow",
        "resourcesmyqueriesoptional8sparql5" to "too slow",
        "resourcesmyqueriesoptional9sparql4" to "too slow",
        "resourcesmyqueriesoptional9sparql5" to "too slow",
        "resourcesmyqueriesx1sparql5" to "too slow",
        "resourcesmyqueriesx2sparql10" to "too slow",
        "resourcesmyqueriesx3sparql4" to "too slow",
        "resourcesmyqueriesx4sparql4" to "too slow",
        "resourcesmyqueriesx5sparql4" to "too slow",
        "resourcesmyqueriesx6sparql4" to "too slow",
        "resourcesmyqueriesx7sparql4" to "too slow",
        "resourcessp2bq10sparql1294" to "too slow",
        "resourcessp2bq10sparql1640" to "too slow",
        "resourcessp2bq10sparql21" to "too slow",
        "resourcessp2bq10sparql247" to "too slow",
        "resourcessp2bq10sparql32978" to "too slow",
        "resourcessp2bq10sparql700" to "too slow",
        "resourcessp2bq10sparql973" to "too slow",
        "resourcessp2bq111sparql1294" to "too slow",
        "resourcessp2bq111sparql1640" to "too slow",
        "resourcessp2bq111sparql21" to "too slow",
        "resourcessp2bq111sparql247" to "too slow",
        "resourcessp2bq111sparql32978" to "too slow",
        "resourcessp2bq111sparql700" to "too slow",
        "resourcessp2bq111sparql973" to "too slow",
        "resourcessp2bq11sparql1294" to "too slow",
        "resourcessp2bq11sparql1640" to "too slow",
        "resourcessp2bq11sparql21" to "too slow",
        "resourcessp2bq11sparql247" to "too slow",
        "resourcessp2bq11sparql32978" to "too slow",
        "resourcessp2bq11sparql700" to "too slow",
        "resourcessp2bq11sparql973" to "too slow",
        "resourcessp2bq12a1sparql1294" to "too slow",
        "resourcessp2bq12a1sparql1640" to "too slow",
        "resourcessp2bq12a1sparql21" to "too slow",
        "resourcessp2bq12a1sparql247" to "too slow",
        "resourcessp2bq12a1sparql32978" to "too slow",
        "resourcessp2bq12a1sparql700" to "too slow",
        "resourcessp2bq12a1sparql973" to "too slow",
        "resourcessp2bq12asparql1294" to "too slow",
        "resourcessp2bq12asparql1640" to "too slow",
        "resourcessp2bq12asparql21" to "too slow",
        "resourcessp2bq12asparql247" to "too slow",
        "resourcessp2bq12asparql32978" to "too slow",
        "resourcessp2bq12asparql700" to "too slow",
        "resourcessp2bq12asparql973" to "too slow",
        "resourcessp2bq12b1sparql1294" to "too slow",
        "resourcessp2bq12b1sparql1640" to "too slow",
        "resourcessp2bq12b1sparql21" to "too slow",
        "resourcessp2bq12b1sparql247" to "too slow",
        "resourcessp2bq12b1sparql32978" to "too slow",
        "resourcessp2bq12b1sparql700" to "too slow",
        "resourcessp2bq12b1sparql973" to "too slow",
        "resourcessp2bq12b2sparql1294" to "too slow",
        "resourcessp2bq12b2sparql1640" to "too slow",
        "resourcessp2bq12b2sparql21" to "too slow",
        "resourcessp2bq12b2sparql247" to "too slow",
        "resourcessp2bq12b2sparql32978" to "too slow",
        "resourcessp2bq12b2sparql700" to "too slow",
        "resourcessp2bq12b2sparql973" to "too slow",
        "resourcessp2bq12b3sparql1294" to "too slow",
        "resourcessp2bq12b3sparql1640" to "too slow",
        "resourcessp2bq12b3sparql21" to "too slow",
        "resourcessp2bq12b3sparql247" to "too slow",
        "resourcessp2bq12b3sparql32978" to "too slow",
        "resourcessp2bq12b3sparql700" to "too slow",
        "resourcessp2bq12b3sparql973" to "too slow",
        "resourcessp2bq12b4sparql1294" to "too slow",
        "resourcessp2bq12b4sparql1640" to "too slow",
        "resourcessp2bq12b4sparql21" to "too slow",
        "resourcessp2bq12b4sparql247" to "too slow",
        "resourcessp2bq12b4sparql32978" to "too slow",
        "resourcessp2bq12b4sparql700" to "too slow",
        "resourcessp2bq12b4sparql973" to "too slow",
        "resourcessp2bq12bsparql1294" to "too slow",
        "resourcessp2bq12bsparql1640" to "too slow",
        "resourcessp2bq12bsparql21" to "too slow",
        "resourcessp2bq12bsparql247" to "too slow",
        "resourcessp2bq12bsparql32978" to "too slow",
        "resourcessp2bq12bsparql700" to "too slow",
        "resourcessp2bq12bsparql973" to "too slow",
        "resourcessp2bq12csparql1294" to "too slow",
        "resourcessp2bq12csparql1640" to "too slow",
        "resourcessp2bq12csparql21" to "too slow",
        "resourcessp2bq12csparql247" to "too slow",
        "resourcessp2bq12csparql32978" to "too slow",
        "resourcessp2bq12csparql700" to "too slow",
        "resourcessp2bq12csparql973" to "too slow",
        "resourcessp2bq1sparql1294" to "too slow",
        "resourcessp2bq1sparql1640" to "too slow",
        "resourcessp2bq1sparql21" to "too slow",
        "resourcessp2bq1sparql247" to "too slow",
        "resourcessp2bq1sparql32978" to "too slow",
        "resourcessp2bq1sparql700" to "too slow",
        "resourcessp2bq1sparql973" to "too slow",
        "resourcessp2bq2sparql1294" to "too slow",
        "resourcessp2bq2sparql1640" to "too slow",
        "resourcessp2bq2sparql21" to "too slow",
        "resourcessp2bq2sparql247" to "too slow",
        "resourcessp2bq2sparql32978" to "too slow",
        "resourcessp2bq2sparql700" to "too slow",
        "resourcessp2bq2sparql973" to "too slow",
        "resourcessp2bq3asparql1294" to "too slow",
        "resourcessp2bq3asparql1640" to "too slow",
        "resourcessp2bq3asparql21" to "too slow",
        "resourcessp2bq3asparql247" to "too slow",
        "resourcessp2bq3asparql32978" to "too slow",
        "resourcessp2bq3asparql700" to "too slow",
        "resourcessp2bq3asparql973" to "too slow",
        "resourcessp2bq3bsparql1294" to "too slow",
        "resourcessp2bq3bsparql1640" to "too slow",
        "resourcessp2bq3bsparql21" to "too slow",
        "resourcessp2bq3bsparql247" to "too slow",
        "resourcessp2bq3bsparql32978" to "too slow",
        "resourcessp2bq3bsparql700" to "too slow",
        "resourcessp2bq3bsparql973" to "too slow",
        "resourcessp2bq3csparql1294" to "too slow",
        "resourcessp2bq3csparql1640" to "too slow",
        "resourcessp2bq3csparql21" to "too slow",
        "resourcessp2bq3csparql247" to "too slow",
        "resourcessp2bq3csparql32978" to "too slow",
        "resourcessp2bq3csparql700" to "too slow",
        "resourcessp2bq3csparql973" to "too slow",
        "resourcessp2bq41sparql1294" to "too slow",
        "resourcessp2bq41sparql1640" to "too slow",
        "resourcessp2bq41sparql21" to "too slow",
        "resourcessp2bq41sparql247" to "too slow",
        "resourcessp2bq41sparql32978" to "too slow",
        "resourcessp2bq41sparql700" to "too slow",
        "resourcessp2bq41sparql973" to "too slow",
        "resourcessp2bq42sparql1294" to "too slow",
        "resourcessp2bq42sparql1640" to "too slow",
        "resourcessp2bq42sparql21" to "too slow",
        "resourcessp2bq42sparql247" to "too slow",
        "resourcessp2bq42sparql32978" to "too slow",
        "resourcessp2bq42sparql700" to "too slow",
        "resourcessp2bq42sparql973" to "too slow",
        "resourcessp2bq4sparql1294" to "too slow",
        "resourcessp2bq4sparql1640" to "too slow",
        "resourcessp2bq4sparql21" to "too slow",
        "resourcessp2bq4sparql247" to "too slow",
        "resourcessp2bq4sparql32978" to "too slow",
        "resourcessp2bq4sparql700" to "too slow",
        "resourcessp2bq4sparql973" to "too slow",
        "resourcessp2bq5asparql1294" to "too slow",
        "resourcessp2bq5asparql1640" to "too slow",
        "resourcessp2bq5asparql21" to "too slow",
        "resourcessp2bq5asparql247" to "too slow",
        "resourcessp2bq5asparql32978" to "too slow",
        "resourcessp2bq5asparql700" to "too slow",
        "resourcessp2bq5asparql973" to "too slow",
        "resourcessp2bq5bsparql1294" to "too slow",
        "resourcessp2bq5bsparql1640" to "too slow",
        "resourcessp2bq5bsparql21" to "too slow",
        "resourcessp2bq5bsparql247" to "too slow",
        "resourcessp2bq5bsparql32978" to "too slow",
        "resourcessp2bq5bsparql700" to "too slow",
        "resourcessp2bq5bsparql973" to "too slow",
        "resourcessp2bq61sparql1294" to "too slow",
        "resourcessp2bq61sparql1640" to "too slow",
        "resourcessp2bq61sparql21" to "too slow",
        "resourcessp2bq61sparql247" to "too slow",
        "resourcessp2bq61sparql32978" to "too slow",
        "resourcessp2bq61sparql700" to "too slow",
        "resourcessp2bq61sparql973" to "too slow",
        "resourcessp2bq62sparql1294" to "too slow",
        "resourcessp2bq62sparql1640" to "too slow",
        "resourcessp2bq62sparql21" to "too slow",
        "resourcessp2bq62sparql247" to "too slow",
        "resourcessp2bq62sparql32978" to "too slow",
        "resourcessp2bq62sparql700" to "too slow",
        "resourcessp2bq62sparql973" to "too slow",
        "resourcessp2bq63sparql1294" to "too slow",
        "resourcessp2bq63sparql1640" to "too slow",
        "resourcessp2bq63sparql21" to "too slow",
        "resourcessp2bq63sparql247" to "too slow",
        "resourcessp2bq63sparql32978" to "too slow",
        "resourcessp2bq63sparql700" to "too slow",
        "resourcessp2bq63sparql973" to "too slow",
        "resourcessp2bq65sparql1294" to "too slow",
        "resourcessp2bq65sparql1640" to "too slow",
        "resourcessp2bq65sparql21" to "too slow",
        "resourcessp2bq65sparql247" to "too slow",
        "resourcessp2bq65sparql32978" to "too slow",
        "resourcessp2bq65sparql700" to "too slow",
        "resourcessp2bq65sparql973" to "too slow",
        "resourcessp2bq66sparql1294" to "too slow",
        "resourcessp2bq66sparql1640" to "too slow",
        "resourcessp2bq66sparql21" to "too slow",
        "resourcessp2bq66sparql247" to "too slow",
        "resourcessp2bq66sparql32978" to "too slow",
        "resourcessp2bq66sparql700" to "too slow",
        "resourcessp2bq66sparql973" to "too slow",
        "resourcessp2bq67sparql1294" to "too slow",
        "resourcessp2bq67sparql1640" to "too slow",
        "resourcessp2bq67sparql21" to "too slow",
        "resourcessp2bq67sparql247" to "too slow",
        "resourcessp2bq67sparql32978" to "too slow",
        "resourcessp2bq67sparql700" to "too slow",
        "resourcessp2bq67sparql973" to "too slow",
        "resourcessp2bq68sparql1294" to "too slow",
        "resourcessp2bq68sparql1640" to "too slow",
        "resourcessp2bq68sparql21" to "too slow",
        "resourcessp2bq68sparql247" to "too slow",
        "resourcessp2bq68sparql32978" to "too slow",
        "resourcessp2bq68sparql700" to "too slow",
        "resourcessp2bq68sparql973" to "too slow",
        "resourcessp2bq6sparql1294" to "too slow",
        "resourcessp2bq6sparql1640" to "too slow",
        "resourcessp2bq6sparql21" to "too slow",
        "resourcessp2bq6sparql247" to "too slow",
        "resourcessp2bq6sparql32978" to "too slow",
        "resourcessp2bq6sparql700" to "too slow",
        "resourcessp2bq6sparql973" to "too slow",
        "resourcessp2bq71sparql1294" to "too slow",
        "resourcessp2bq71sparql1640" to "too slow",
        "resourcessp2bq71sparql21" to "too slow",
        "resourcessp2bq71sparql247" to "too slow",
        "resourcessp2bq71sparql32978" to "too slow",
        "resourcessp2bq71sparql700" to "too slow",
        "resourcessp2bq71sparql973" to "too slow",
        "resourcessp2bq72sparql1294" to "too slow",
        "resourcessp2bq72sparql1640" to "too slow",
        "resourcessp2bq72sparql21" to "too slow",
        "resourcessp2bq72sparql247" to "too slow",
        "resourcessp2bq72sparql32978" to "too slow",
        "resourcessp2bq72sparql700" to "too slow",
        "resourcessp2bq72sparql973" to "too slow",
        "resourcessp2bq7sparql1294" to "too slow",
        "resourcessp2bq7sparql1640" to "too slow",
        "resourcessp2bq7sparql21" to "too slow",
        "resourcessp2bq7sparql247" to "too slow",
        "resourcessp2bq7sparql32978" to "too slow",
        "resourcessp2bq7sparql700" to "too slow",
        "resourcessp2bq7sparql973" to "too slow",
        "resourcessp2bq8sparql1294" to "too slow",
        "resourcessp2bq8sparql1640" to "too slow",
        "resourcessp2bq8sparql21" to "too slow",
        "resourcessp2bq8sparql247" to "too slow",
        "resourcessp2bq8sparql32978" to "too slow",
        "resourcessp2bq8sparql700" to "too slow",
        "resourcessp2bq8sparql973" to "too slow",
        "resourcessp2bq91sparql1294" to "too slow",
        "resourcessp2bq91sparql1640" to "too slow",
        "resourcessp2bq91sparql21" to "too slow",
        "resourcessp2bq91sparql247" to "too slow",
        "resourcessp2bq91sparql32978" to "too slow",
        "resourcessp2bq91sparql700" to "too slow",
        "resourcessp2bq91sparql973" to "too slow",
        "resourcessp2bq92sparql1294" to "too slow",
        "resourcessp2bq92sparql1640" to "too slow",
        "resourcessp2bq92sparql21" to "too slow",
        "resourcessp2bq92sparql247" to "too slow",
        "resourcessp2bq92sparql32978" to "too slow",
        "resourcessp2bq92sparql700" to "too slow",
        "resourcessp2bq92sparql973" to "too slow",
        "resourcessp2bq9sparql1294" to "too slow",
        "resourcessp2bq9sparql1640" to "too slow",
        "resourcessp2bq9sparql21" to "too slow",
        "resourcessp2bq9sparql247" to "too slow",
        "resourcessp2bq9sparql32978" to "too slow",
        "resourcessp2bq9sparql700" to "too slow",
        "resourcessp2bq9sparql973" to "too slow",
    )
    private val ignoreListDueToNotImplemented = mapOf(
        "Nestedpositiveexists" to "using not implemented feature",
        "GROUPCONCAT2" to "using not implemented feature",
        "pp03Simplepathwithloop" to "using not implemented feature",
        "pp23Diamondwithtailp" to "using not implemented feature",
        "SHA512onUnicodedata" to "using not implemented feature",
        "IN1" to "using not implemented feature",
        "SUBSTR3argument" to "using not implemented feature",
        "ENCODEFORURI" to "using not implemented feature",
        "pp30Operatorprecedence1" to "using not implemented feature",
        "PositiveEXISTS1" to "using not implemented feature",
        "pp12Variablelengthpathandtwopathstosametargetnode" to "using not implemented feature",
        "Existswithoneconstant" to "using not implemented feature",
        "REPLACEwithcapturedsubstring" to "using not implemented feature",
        "STRUUIDpatternmatch" to "using not implemented feature",
        "NOTIN2" to "using not implemented feature",
        "pp31Operatorprecedence2" to "using not implemented feature",
        "GROUPCONCAT1" to "using not implemented feature",
        "pp08Reversepath" to "using not implemented feature",
        "SHA512" to "using not implemented feature",
        "Existswithgroundtriple" to "using not implemented feature",
        "pp01Simplepath" to "using not implemented feature",
        "Nestednegativeexistsinpositiveexists" to "using not implemented feature",
        "REPLACE" to "using not implemented feature",
        "pp11Simplepathandtwopathstosametargetnode" to "using not implemented feature",
        "IN2" to "using not implemented feature",
        "SUBSTR2argument" to "using not implemented feature",
        "pp02Starpath" to "using not implemented feature",
        "pp36Arbitrarypathwithboundendpoints" to "using not implemented feature",
        "pp09Reversesequencepath" to "using not implemented feature",
        "pp14Starpathoverfoafknows" to "using not implemented feature",
        "pp33Operatorprecedence4" to "using not implemented feature",
        "pp16Duplicatepathsandcyclesthroughfoafknows" to "using not implemented feature",
        "GROUPCONCATwithSEPARATOR" to "using not implemented feature",
        "RAND" to "using not implemented feature",
        "pp32Operatorprecedence3" to "using not implemented feature",
        "pp25Diamondwithloopp" to "using not implemented feature",
        "pp21Diamondp" to "using not implemented feature",
        "pp10Pathwithnegation" to "using not implemented feature",
        "UUIDpatternmatch" to "using not implemented feature",
        "pp37Nested" to "using not implemented feature",
        "REPLACEwithoverlappingpattern" to "using not implemented feature",
        "PositiveEXISTS2" to "using not implemented feature",
        "pp28aDiamondwithlooppp" to "using not implemented feature",
        "jsonres01JSONResultFormat" to "using not implemented feature",
        "jsonres02JSONResultFormat" to "using not implemented feature",
        "jsonres03JSONResultFormat" to "using not implemented feature",
        "jsonres04JSONResultFormat" to "using not implemented feature",
        "sq09NestedSubqueries" to "using not implemented feature",
        "sq06Subquerywithgraphpatternfromnamedapplies" to "using not implemented feature",
        "sq10Subquerywithexists" to "using not implemented feature",
        "sq08Subquerywithaggregate" to "using not implemented feature",
        "sq01Subquerywithingraphpattern" to "using not implemented feature",
        "synppincollection" to "using not implemented feature",
        "sq05Subquerywithingraphpatternfromnamedapplies" to "using not implemented feature",
        "syntaxoneof03rq" to "using not implemented feature",
        "pp07Pathwithonegraph" to "using not implemented feature",
        "sq07Subquerywithfrom" to "using not implemented feature",
        "pp06Pathwithtwographs" to "using not implemented feature",
        "syntaxaggregate14rq" to "using not implemented feature",
        "sq04Subquerywithingraphpatterndefaultgraphdoesnotapply" to "using not implemented feature",
        "sq03Subquerywithingraphpatterngraphvariableisnotbound" to "using not implemented feature",
        "syntaxpropertyPaths01rq" to "using not implemented feature",
        "sq02Subquerywithingraphpatterngraphvariableisbound" to "using not implemented feature",
        "syntaxaggregate13rq" to "using not implemented feature",
        "pp34NamedGraph1" to "using not implemented feature",
        "pp35NamedGraph2" to "using not implemented feature",
        "syntaxselectexpr04rq" to "using not implemented feature",
        "syntaxaggregate15rq" to "using not implemented feature",
    )
    private val ignoreListDueToBugsResolveLater = mapOf(
        "csv03CSVResultFormat" to "Bug in CSV-Parser",
        "SHA256onUnicodedata" to "Bug in SHA256-Function",
        "constructwhere04CONSTRUCTWHERE" to "Bug in SparqlTestSuiteConverterToUnitTest",
        "syntaxconstructwhere02rq" to "Bug in SparqlTestSuiteConverterToUnitTest",
    )

// grep -rl "failure message" ./src/luposdate3000_launch_code_gen_test/build/test-results/jvmTest | sed "s/.xml$/\" to \"Bug\",/g" | sed "s/.*\./\"/g" | sort | uniq
    private val ignoreListDueToBugs = mapOf<String, String>(
        "AVG" to "Bug",
        "AVGwithGROUPBY" to "Bug",
        "bind01BINDfixeddataforOWLDL" to "Bug",
        "bind02BINDfixeddataforOWLDL" to "Bug",
        "bind03BINDfixeddataforOWLDL" to "Bug",
        "bind04BINDfixeddataforOWLDL" to "Bug",
        "bind05BINDfixeddataforOWLDL" to "Bug",
        "bind06BINDfixeddataforOWLDL" to "Bug",
        "bind07BINDfixeddataforOWLDL" to "Bug",
        "bind08BINDfixeddataforOWLDL" to "Bug",
        "bnodesarenotexistentials" to "Bug",
        "bnodesarenotexistentialswithanswer" to "Bug",
        "BNODEstr" to "Bug",
        "BNODE" to "Bug",
        "Calculatepropersubset" to "Bug",
        "CalculatewhichsetsaresubsetsofothersexcludeAsubsetOfA" to "Bug",
        "CalculatewhichsetsaresubsetsofothersincludeAsubsetOfA" to "Bug",
        "Calculatewhichsetshavethesameelements" to "Bug",
        "COALESCE" to "Bug",
        "CONCAT2" to "Bug",
        "COPY2" to "Bug",
        "COPY4" to "Bug",
        "COUNT10" to "Bug",
        "COUNT11" to "Bug",
        "COUNT9" to "Bug",
        "csv01CSVResultFormat" to "Bug",
        "cvs02CSVResultFormat" to "Bug",
        "DELETEINSERT1b" to "Bug",
        "DELETEINSERT1c" to "Bug",
        "DELETEINSERT1" to "Bug",
        "DELETEINSERT2" to "Bug",
        "DELETEINSERT3b" to "Bug",
        "DELETEINSERT3" to "Bug",
        "DELETEINSERT4b" to "Bug",
        "DELETEINSERT4" to "Bug",
        "DELETEINSERT5b" to "Bug",
        "DELETEINSERT5" to "Bug",
        "DELETEINSERT6" to "Bug",
        "DELETEINSERT7b" to "Bug",
        "DELETEINSERT7" to "Bug",
        "DELETEINSERT8" to "Bug",
        "DELETEINSERT9" to "Bug",
        "ErrorinAVG" to "Bug",
        "Existswithingraphpattern" to "Bug",
        "Expressionhasvariablethatmaybeunbound" to "Bug",
        "Expressionmayreturnnovalue" to "Bug",
        "Expressionraiseanerror" to "Bug",
        "filteredsubclassquerywithhasChildsomeThingrestriction" to "Bug",
        "GraphspecificDELETE1" to "Bug",
        "GraphspecificDELETE1USING" to "Bug",
        "GraphspecificDELETE1WITH" to "Bug",
        "GraphspecificDELETE2" to "Bug",
        "GraphspecificDELETE2USING" to "Bug",
        "GraphspecificDELETE2WITH" to "Bug",
        "GraphspecificDELETEDATA1" to "Bug",
        "GraphspecificDELETEDATA2" to "Bug",
        "GraphspecificDELETEWHERE1" to "Bug",
        "GraphspecificDELETEWHERE2" to "Bug",
        "Group6" to "Bug",
        "Group7" to "Bug",
        "IFerrorpropogation" to "Bug",
        "INSERT02" to "Bug",
        "INSERTingthesamebnodewithINSERTDATAintotwodifferentGraphsisthesamebnode" to "Bug",
        "INSERTingthesamebnodewithtwoINSERTWHEREstatementwithinonerequestisNOTthesamebnodeevenifbothWHEREclauseshavetheemptysolutionmappingastheonlysolution" to "Bug",
        "INSERTingthesamebnodewithtwoINSERTWHEREstatementwithinonerequestisNOTthesamebnode" to "Bug",
        "INSERTsamebnodetwice" to "Bug",
        "IRIURI" to "Bug",
        "MAX" to "Bug",
        "MAXwithGROUPBY" to "Bug",
        "MedicaltemporalproximitybyexclusionNOTEXISTS" to "Bug",
        "MINwithGROUPBY" to "Bug",
        "MOVE2" to "Bug",
        "MOVE4" to "Bug",
        "NOW" to "Bug",
        "papersparqldlQ1rdfs" to "Bug",
        "papersparqldlQ1" to "Bug",
        "papersparqldlQ2" to "Bug",
        "papersparqldlQ3" to "Bug",
        "papersparqldlQ4" to "Bug",
        "papersparqldlQ5" to "Bug",
        "parentquerywithdistinguishedvariable" to "Bug",
        "parentquerywithhasChildexactly1Femalerestriction" to "Bug",
        "parentquerywithhasChildmax1Femalerestriction" to "Bug",
        "parentquerywithhasChildmin1Femalerestriction" to "Bug",
        "parentquerywithhasChildmin1restriction" to "Bug",
        "parentquerywithhasChildsomeFemalerestriction" to "Bug",
        "parentquerywithhasChildsomeThingrestriction" to "Bug",
        "plus1" to "Bug",
        "plus2" to "Bug",
        "PostqueryVALUESwith2objvars1row" to "Bug",
        "PostqueryVALUESwith2objvars1rowwithUNDEF" to "Bug",
        "PostqueryVALUESwith2objvars2rowswithUNDEF" to "Bug",
        "PostqueryVALUESwithobjvar1row" to "Bug",
        "PostqueryVALUESwithOPTIONALobjvar1row" to "Bug",
        "PostqueryVALUESwithpredvar1row" to "Bug",
        "ProtectfromerrorinAVG" to "Bug",
        "RDFSinferencetestcombiningsubPropertyOfanddomain" to "Bug",
        "RDFSinferencetestdomain" to "Bug",
        "RDFSinferencetestrange" to "Bug",
        "RDFSinferencetestrdfssubPropertyOf" to "Bug",
        "RDFSinferencetestsubClassOf" to "Bug",
        "RDFSinferencetestsubPropertyandinstances" to "Bug",
        "RDFSinferencetesttransitivityofsubClassOf" to "Bug",
        "RDFSinferencetesttransitivityofsubPropertyOf" to "Bug",
        "Reuseaprojectexpressionvariableinselect" to "Bug",
        "SERVICEtest7" to "Bug",
        "SHA1onUnicodedata" to "Bug",
        "simple1" to "Bug",
        "simple2" to "Bug",
        "simple3" to "Bug",
        "simple4" to "Bug",
        "simple5" to "Bug",
        "simple6" to "Bug",
        "simple7" to "Bug",
        "simple8" to "Bug",
        "SimpleDELETE1" to "Bug",
        "SimpleDELETE1USING" to "Bug",
        "SimpleDELETE1WITH" to "Bug",
        "SimpleDELETE2" to "Bug",
        "SimpleDELETE2USING" to "Bug",
        "SimpleDELETE2WITH" to "Bug",
        "SimpleDELETE3USING" to "Bug",
        "SimpleDELETE3WITH" to "Bug",
        "SimpleDELETE4" to "Bug",
        "SimpleDELETE4USING" to "Bug",
        "SimpleDELETE4WITH" to "Bug",
        "SimpleDELETE7" to "Bug",
        "SimpleDELETEDATA1" to "Bug",
        "SimpleDELETEDATA2" to "Bug",
        "SimpleDELETEDATA3" to "Bug",
        "SimpleDELETEDATA4" to "Bug",
        "SimpleDELETEWHERE1" to "Bug",
        "SimpleDELETEWHERE2" to "Bug",
        "SimpleDELETEWHERE4" to "Bug",
        "Simpleinsertdatanamed1" to "Bug",
        "Simpleinsertdatanamed2" to "Bug",
        "Simpleinsertdatanamed3" to "Bug",
        "sparqldl02rqsimplecombinedquery" to "Bug",
        "sparqldl03rqcombinedquerywithcomplexclassdescription" to "Bug",
        "sparqldl04rqbugfixingtest" to "Bug",
        "sparqldl05rqsimpleundistinguishedvariabletest" to "Bug",
        "sparqldl06rqcycleofundistinguishedvariables" to "Bug",
        "sparqldl07rqtwodistinguishedvariablesundist" to "Bug",
        "sparqldl08rqtwodistinguishedvariablesundist" to "Bug",
        "sparqldl09rqundistvarstest" to "Bug",
        "sparqldl10rqundistvarstest" to "Bug",
        "sparqldl11rqdomaintest" to "Bug",
        "sparqldl12rqrangetest" to "Bug",
        "sparqldl13rqsameAs" to "Bug",
        "sq11Subquerylimitperresource" to "Bug",
        "sq13Subqueriesdontinjectbindings" to "Bug",
        "sq14limitbyresource" to "Bug",
        "STRAFTERdatatyping" to "Bug",
        "STRAFTER" to "Bug",
        "STRBEFOREdatatyping" to "Bug",
        "STRBEFORE" to "Bug",
        "STRDT" to "Bug",
        "STRDTTypeErrors" to "Bug",
        "STRLANG" to "Bug",
        "STRLANGTypeErrors" to "Bug",
        "subclassquerywithhasChildsomeThingrestriction" to "Bug",
        "SubtractionwithMINUSfromafullyboundminuend" to "Bug",
        "SubtractionwithMINUSfromapartiallyboundminuend" to "Bug",
        "SUM" to "Bug",
        "synbad01rq" to "Bug",
        "synbad02rq" to "Bug",
        "synbad03rq" to "Bug",
        "synbadpname06" to "Bug",
        "syntaxBINDscope6rq" to "Bug",
        "syntaxBINDscope7rq" to "Bug",
        "syntaxBINDscope8rq" to "Bug",
        "syntaxSELECTscope2" to "Bug",
        "syntaxupdate32ru" to "Bug",
        "syntaxupdate33ru" to "Bug",
        "syntaxupdate34ru" to "Bug",
        "syntaxupdate36ru" to "Bug",
        "syntaxupdate54ru" to "Bug",
        "syntaxupdatebad03ru" to "Bug",
        "syntaxupdatebad04ru" to "Bug",
        "syntaxupdatebad08ru" to "Bug",
        "syntaxupdatebad09ru" to "Bug",
        "syntaxupdatebad10ru" to "Bug",
        "syntaxupdatebad11ru" to "Bug",
        "syntaxupdatebad12ru" to "Bug",
        "TIMEZONE" to "Bug",
        "TZ" to "Bug",
    )
    private val ignoreList = ignoreListDueToTooSlow + ignoreListDueToNotImplemented + ignoreListDueToBugs + ignoreListDueToBugsResolveLater
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
        File(outputFolderTestResourcesJvm + "/$testCaseName.query").withOutputStream { out ->
            File(queryFile).forEachLine {
                out.println(it)
                if (it.contains("order", true)) {
                    queryResultIsOrdered = true
                }
            }
        }
        var ignored = ignoreList.contains(testCaseName)
        for (useCodeGen in setOf(false, withCodeGen)) {
            var filenamePart = ""
            if (useCodeGen) {
                filenamePart = "_CodeGen"
            }
            File("$outputFolderTestJvm/$testCaseName$filenamePart.kt").withOutputStream { out ->
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
                if (ignored) {
                    out.println("import kotlin.test.Ignore")
                }
                out.println("import kotlin.test.Test")
                out.println("import kotlin.test.fail")
                out.println("")
                out.println("public class $testCaseName {")
                if (inputGraphs.size> 0) {
                    out.println("    internal val inputData = arrayOf(")
                    for ((k, v) in inputGraphs) {
                        out.println("        File(\"src/jvmTest/resources/$k\").readAsString(),")
                    }
                    out.println("    )")
                    out.println("    internal val inputGraph = arrayOf(")
                    for ((k, v) in inputGraphs) {
                        out.println("        \"${v.first}\",")
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
                out.println("    internal val query = File(\"src/jvmTest/resources/$testCaseName.query\").readAsString()")
                if (ignored) {
                    val reason = ignoreList[testCaseName]
                    if (reason != null) {
                        out.println("    @Ignore // Reason: >$reason<")
                    } else {
                        out.println("    @Ignore")
                    }
                }
                out.println("    @Test")
                out.println("    fun `$testCaseName2}`() {")
                out.println("        val instance = LuposdateEndpoint.initialize()")
                out.println("        instance.LUPOS_BUFFER_SIZE = 128")
                out.println("        val buf = MyPrintWriter(false)")
                for (i in 0 until inputGraphs.size) {
                    out.println("        if (listOf(\".n3\", \".ttl\", \".nt\").contains(inputType[$i])) {")
                    out.println("            LuposdateEndpoint.importTurtleString(instance, inputData[$i],inputGraph[$i])")
                    out.println("        } else {")
                    out.println("            TODO()")
                    out.println("        }")
                }
                for (i in 0 until inputGraphs.size) {
                    out.println("")
                    out.println("        val query_a_$i = Query(instance)")
                    out.println("        val input_a_$i = MemoryTable.parseFromAny(inputData[$i], inputType[$i], query_a_$i)!!")
                    out.println("        val graph_a_$i = instance.tripleStoreManager!!.getGraph(inputGraph[$i])")
                    out.println("        val op2_a_$i = graph_a_$i.getIterator(query_a_$i, arrayOf(AOPVariable(query_a_$i, \"s\"), AOPVariable(query_a_$i, \"p\"), AOPVariable(query_a_$i, \"o\")), EIndexPatternExt.SPO)")
                    out.println("        val result_a_$i = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, op2_a_$i, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()")
                    out.println("        val buf_err_a_$i = MyPrintWriter()")
                    out.println("        if (!input_a_$i.equalsVerbose(result_a_$i, ${!queryResultIsOrdered}, true, buf_err_a_$i)) {")
                    out.println("            fail(input_a_$i.toString()+\" .. \"+result_a_$i.toString()+\" .. \"+buf_err_a_$i.toString()+\" .. \"+op2_a_$i)")
                    out.println("        }")
                }
                val evaluateIt = outputGraphs.size> 0 || mode == BinaryTestCaseOutputModeExt.SELECT_QUERY_RESULT
                if (evaluateIt || expectedResult) {
                    if (useCodeGen) {
                        out.println("            val op = query_evaluate()")
                    } else {
                        out.println("        val op = LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, query)")
                    }
                } else {
                    out.println("        var flag=false")
                    out.println("        try{")
                    if (useCodeGen) {
                        out.println("            query_evaluate()")
                    } else {
                        out.println("        LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, query)")
                    }
                    out.println("        }catch(e:Throwable) {")
                    out.println("            flag = true")
                    out.println("        }")
                    out.println("        if(!flag){")
                    out.println("            fail(\"expected failure\")")
                    out.println("        }")
                }
                if (mode == BinaryTestCaseOutputModeExt.SELECT_QUERY_RESULT) {
                    out.println("        val query_target = Query(instance)")
                    out.println("        val target = MemoryTable.parseFromAny(targetData, targetType, query_target)!!")
                    out.println("        val result = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, op, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()")
                    out.println("        val buf_err = MyPrintWriter()")
                    out.println("        if (!target.equalsVerbose(result, ${!queryResultIsOrdered}, true, buf_err)) {")
                    out.println("            fail(buf_err.toString())")
                    out.println("        }")
                } else {
                    if (evaluateIt) {
                        out.println("        LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, op, buf, EQueryResultToStreamExt.EMPTY_STREAM)")
                    }
                }
                for (i in 0 until outputGraphs.size) {
                    out.println("")
                    out.println("        val query_$i = Query(instance)")
                    out.println("        val output$i = MemoryTable.parseFromAny(outputData[$i], outputType[$i], query_$i)!!")
                    out.println("        val graph$i = instance.tripleStoreManager!!.getGraph(outputGraph[$i])")
                    out.println("        val op2$i = graph$i.getIterator(query_$i, arrayOf(AOPVariable(query_$i, \"s\"), AOPVariable(query_$i, \"p\"), AOPVariable(query_$i, \"o\")), EIndexPatternExt.SPO)")
                    out.println("        val result$i = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, op2$i, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()")
                    out.println("        val buf_err$i = MyPrintWriter()")
                    out.println("        if (!output$i.equalsVerbose(result$i, ${!queryResultIsOrdered}, true, buf_err$i)) {")
                    out.println("            fail(output$i.toString()+\" .. \"+result$i.toString()+\" .. \"+buf_err$i.toString()+\" .. \"+op2$i+\" .. \"+op)")
                    out.println("        }")
                }
                out.println("        LuposdateEndpoint.close(instance)") // for inmemory db this results in complete wipe of ALL data
                out.println("    }")
                out.println("}")
            }
        }
        return true
    }
}
