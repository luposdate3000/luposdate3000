#!/usr/bin/env kotlin
@file:Import("src/luposdate3000_shared/src/commonMain/kotlin/lupos/s00misc/EOperatingSystem.kt")
@file:Import("src/luposdate3000_shared/src/commonMain/kotlin/lupos/s00misc/EOperatingSystemExt.kt")
@file:Import("src/luposdate3000_shared_inline/src/commonMain/kotlin/lupos/s00misc/PlatformAlias.kt")
@file:Import("src/luposdate3000_shared_inline/src/commonMain/kotlin/lupos/s00misc/Platform.kt")
@file:Import("src/luposdate3000_shared_inline/src/jvmMain/kotlin/lupos/s00misc/Platform.kt")
@file:Import("src/luposdate3000_test/src/commonMain/kotlin/lupos/s00misc/BinaryTestCase.kt")
@file:Import("src/luposdate3000_test/src/commonMain/kotlin/lupos/s00misc/BinaryTestCaseOutputMode.kt")
@file:Import("src/luposdate3000_test/src/commonMain/kotlin/lupos/s00misc/BinaryTestCaseOutputModeExt.kt")
@file:Import("src/luposdate3000_test/src/commonMain/kotlin/lupos/s00misc/SparqlTestSuiteConverter.kt")
@file:Import("src/luposdate3000_shared_inline/src/jvmMain/kotlin/lupos/s00misc/File.kt")
@file:Import("src/luposdate3000_shared_inline/src/commonMain/kotlin/lupos/s00misc/File.kt")
@file:Import("src/luposdate3000_shared_inline/src/commonMain/kotlin/lupos/s00misc/FileAlias.kt")
@file:CompilerOptions("-Xmulti-platform")
import lupos.*
import lupos.s00misc.*
public fun main(args: Array<String>) {
    if (args.size < 1) {
        printUsage()
    } else {
        when (args[0]) {
            "Single" -> {
                if (args.size < 7) {
                    printUsage()
                } else {
                    BinaryTestCase.generateTestcase(args[1], args[2], args[3], args[4], args[5], BinaryTestCaseOutputMode.valueOf(args[6]))
                }
            }
            "TestSuite" -> {
                SparqlTestSuite.prefixDirectory = args[1]
                val converter = SparqlTestSuiteConverter(args[1], args[2])
                converter.testMain()
            }
            else -> {
                printUsage()
            }
        }
    }
}
public fun printUsage() {
    println("usage ./tool-generateTestcase.main.kts Single query_input_file.n3 query_file.sparql query_output_file.srx output_folder query_name ${BinaryTestCaseOutputMode.values()}")
    println("usage ./tool-generateTestcase.main.kts TestSuite resource_folder output_folder")
}
