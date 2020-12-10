import lupos.*
import lupos.s00misc.*
fun main(args: Array<String>) {
    if (args.size < 1) {
        printUsage()
    } else {
        var mode = BinaryTestCaseGenerateTestCaseMode.Single
        try {
            mode = BinaryTestCaseGenerateTestCaseMode.valueOf(args[0])
        } catch (e: Throwable) {
            printUsage()
            return
        }
        when (mode) {
            BinaryTestCaseGenerateTestCaseMode.Single -> {
                if (args.size < 7) {
                    printUsage()
                } else {
                    BinaryTestCase.generateTestcase(args[1], args[2], args[3], args[4], args[5], BinaryTestCaseOutputMode.valueOf(args[6]))
                }
            }
            BinaryTestCaseGenerateTestCaseMode.TestSuite -> {
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
fun printUsage() {
    println("usage ./generateTestcase.main.kts Single query_input_file.n3 query_file.sparql query_output_file.srx output_folder query_name ${BinaryTestCaseOutputMode.values()}")
    println("usage ./generateTestcase.main.kts TestSuite resource_folder output_folder")
}
