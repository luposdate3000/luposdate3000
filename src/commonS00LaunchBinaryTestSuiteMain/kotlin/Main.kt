import lupos.s00misc.BinaryTestCase
import lupos.s00misc.Parallel
import lupos.s16network.ServerCommunicationSend
import lupos.SparqlTestSuite

fun main(args: Array<String>): Unit = Parallel.runBlocking {
    ServerCommunicationSend.start()
    if (args.size == 1) {
        BinaryTestCase.executeAllTestCase(args[0])
    } else if (args.size > 0) {
        BinaryTestCase.executeTestCase(args[0] + "/" + args[1])
    } else {
        BinaryTestCase.executeAllTestCase()
    }
}
