import lupos.SparqlTestSuite
import lupos.s00misc.BinaryTestCase
import lupos.s00misc.BinaryTestCaseOutputModeExt
import lupos.s00misc.BinaryTestCaseOutputMode
import lupos.s00misc.SparqlTestSuiteConverter
import lupos.s16network.LuposdateEndpoint
@Suppress("NOTHING_TO_INLINE") internal inline fun mainFunc(args: Array<String>) {
    LuposdateEndpoint.initialize()
    if (args.size < 1) {
        printUsage()
    } else {
        when (args[0]) {
            "Single" -> {
                if (args.size < 7) {
                    printUsage()
                } else {
                    BinaryTestCase.generateTestcase(args[1], args[2], args[3], args[4], args[5], BinaryTestCaseOutputModeExt.names.indexOf(args[6]))
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
@Suppress("NOTHING_TO_INLINE") internal inline fun printUsage() {
    println("usage ./tool-generateTestcase.main.kts Single query_input_file.n3 query_file.sparql query_output_file.srx output_folder query_name ${BinaryTestCaseOutputModeExt.names.map{it}}")
    println("usage ./tool-generateTestcase.main.kts TestSuite resource_folder output_folder")
}
