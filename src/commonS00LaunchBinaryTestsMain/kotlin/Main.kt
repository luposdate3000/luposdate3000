import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.Coverage
import lupos.s00misc.DynamicByteArray
import lupos.s00misc.executeBinaryTest
import lupos.s00misc.File
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.ValueDefinition
import lupos.s03resultRepresentation.ValueSimpleLiteral
import lupos.s12p2p.P2P
import lupos.s14endpoint.Endpoint
import lupos.s14endpoint.endpointServer
import lupos.s14endpoint.EndpointServerImpl


fun main(args: Array<String>) = CoroutinesHelper.runBlock {
    endpointServer = EndpointServerImpl("localhost")
    P2P.start(null)
    mapOf(/*return*/
            /*return*/testDictionaryVarName to "DictionaryVarName.txt",
/*return*/testDictionaryValue to "DictionaryValue.txt"
    ).forEach { (k, v) ->
        File("resources/$v").forEachLine {
            k.add(it)
        }
    }
    testDictionaryValue.forEach {
        try {
            val tmp = ValueDefinition(it)
            if (testDictionaryValueTyped[ValueToID(tmp)] == null) {
                testDictionaryValueTyped[ValueToID(tmp)] = ThreadSafeMutableList<String?>()
            }
            testDictionaryValueTyped[ValueToID(tmp)]!!.add(it!!)
        } catch (e: Throwable) {
            testDictionaryValueTyped[ValueEnum.ValueSimpleLiteral]!!.add("\"" + it!! + "\"")
        }
    }
    Coverage.verbose = true
    val input = lupos.s00misc.File.readStdInAsDynamicByteArray()
    if (input != null) {
        val pos = input.pos
        for (testcase in TestCase.values()) {
            input.pos = pos
            testcase.action(TestRandom(input))
        }
    } else if (args.isEmpty()) {
        executeBinaryTests("/opt/tmpfs")
    } else {
        executeBinaryTest(args[0], true)
    }
}
