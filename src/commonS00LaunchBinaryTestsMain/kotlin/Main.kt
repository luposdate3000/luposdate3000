import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.Coverage
import lupos.s00misc.DynamicByteArray
import lupos.s00misc.executeBinaryTest
import lupos.s00misc.File
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.ValueDefinition
import lupos.s03resultRepresentation.ValueSimpleLiteral
import lupos.s14endpoint.Endpoint
import lupos.s14endpoint.endpointServer
import lupos.s14endpoint.EndpointServerImpl
import lupos.s16network.ServerCommunicationSend
fun main(args: Array<String>) = CoroutinesHelper.runBlock {
Coverage.funStart(13871)
    endpointServer = EndpointServerImpl("localhost")
Coverage.statementStart(13872)
    ServerCommunicationSend.start()
Coverage.statementStart(13873)
    mapOf(/*return*/
            /*return*/testDictionaryVarName to "DictionaryVarName.txt",
/*return*/testDictionaryValue to "DictionaryValue.txt"
    ).forEach { (k, v) ->
Coverage.statementStart(13874)
        File("resources/$v").forEachLine {
Coverage.statementStart(13875)
            k.add(it)
Coverage.statementStart(13876)
        }
Coverage.statementStart(13877)
    }
    testDictionaryValue.forEach {
Coverage.statementStart(13878)
        try {
Coverage.statementStart(13879)
            val tmp = ValueDefinition(it)
Coverage.statementStart(13880)
            if (testDictionaryValueTyped[ValueToID(tmp)] == null) {
Coverage.ifStart(13881)
                testDictionaryValueTyped[ValueToID(tmp)] = ThreadSafeMutableList<String?>()
Coverage.statementStart(13882)
            }
Coverage.statementStart(13883)
            testDictionaryValueTyped[ValueToID(tmp)]!!.add(it!!)
Coverage.statementStart(13884)
        } catch (e: Throwable) {
Coverage.statementStart(13885)
            testDictionaryValueTyped[ValueEnum.ValueSimpleLiteral]!!.add("\"" + it!! + "\"")
Coverage.statementStart(13886)
        }
Coverage.statementStart(13887)
    }
    Coverage.verbose = true
    val input = lupos.s00misc.File.readStdInAsDynamicByteArray()
    if (input != null) {
Coverage.statementStart(13888)
        val pos = input.pos
Coverage.statementStart(13889)
        for (testcase in TestCase.values()) {
Coverage.forLoopStart(13890)
            input.pos = pos
Coverage.statementStart(13891)
            testcase.action(TestRandom(input))
Coverage.statementStart(13892)
        }
Coverage.statementStart(13893)
    } else if (args.isEmpty()) {
Coverage.ifStart(13894)
        executeBinaryTests("/opt/tmpfs")
Coverage.statementStart(13895)
    } else {
Coverage.ifStart(13896)
        executeBinaryTest(args[0], true)
Coverage.statementStart(13897)
    }
}
