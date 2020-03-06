import lupos.*
import lupos.s00misc.*
import lupos.s00misc.executeBinaryTest
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s12p2p.P2P
import lupos.s14endpoint.*


fun main(args: Array<String>) = CoroutinesHelper.runBlock {
    endpointServer = EndpointServerImpl("localhost")
    P2P.start(null)
    mapOf(
            testDictionaryVarName to "DictionaryVarName",
            testDictionaryValue to "DictionaryValue"
    ).forEach { (k, v) ->
        val buffer = File("resources/$v").readAsDynamicByteArray()
        val len = buffer.getNextInt()
        for (i in 0 until len) {
            val tmp = buffer.getNextString()
            val w = k.createValue(tmp)
            require(w == i)
        }
    }
    testDictionaryValue.mapLTS.forEach {
        try {
            val tmp = AOPVariable.calculate(it)
            if (testDictionaryValueTyped[tmp.operatorID] == null)
                testDictionaryValueTyped[tmp.operatorID] = ResultSetDictionary()
            testDictionaryValueTyped[tmp.operatorID]!!.createValue(it!!)
        } catch (e: Throwable) {
            testDictionaryValueTyped[EOperatorID.AOPSimpleLiteralID]!!.createValue("\"" + it!! + "\"")
        }
    }
    val input = File.readStdInAsDynamicByteArray()
    if (input != null) {
        executeBinaryTest(input!!)
    } else if (args.isEmpty()) {
        executeBinaryTests("/opt/tmpfs")
    } else {
        executeBinaryTest(args[0], true)
    }
}
