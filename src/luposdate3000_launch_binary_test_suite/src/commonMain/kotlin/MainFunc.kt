import lupos.s00misc.BinaryTestCase
import lupos.s00misc.BinaryTestCaseOutputMode
import lupos.s00misc.Parallel
import lupos.s16network.LuposdateEndpoint
@Suppress("NOTHING_TO_INLINE") internal inline fun mainFunc(args: Array<String>) {
    LuposdateEndpoint.initialize()
    Parallel.runBlocking {
        if (args.isNotEmpty() && args[0] == "--generate") {
            if (args.size < 7) {
                println("usage xyz.jar --generate 'query_input_file' 'query_file' 'query_output_file' 'output_folder' 'query_name' [SELECT_QUERY_RESULT|MODIFY_RESULT]")
            } else {
                BinaryTestCase.generateTestcase(args[1], args[2], args[3], args[4], args[5], BinaryTestCaseOutputMode.valueOf(args[6]))
            }
        } else if (args.size == 1) {
            BinaryTestCase.executeAllTestCase(args[0])
        } else if (args.isNotEmpty()) {
            BinaryTestCase.executeTestCase(args[0] + "/" + args[1])
        } else {
            BinaryTestCase.executeAllTestCase()
        }
    }
}
