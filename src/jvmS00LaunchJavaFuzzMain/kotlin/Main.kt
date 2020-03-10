import dev.fuzzit.javafuzz.core.AbstractFuzzTarget
import dev.fuzzit.javafuzz.core.Fuzzer
import java.time.Duration
import java.time.Instant
import lupos.*
import lupos.s00misc.*
import lupos.s00misc.executeBinaryTest
import lupos.s03resultRepresentation.*
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04logicalOperators.*
import lupos.s12p2p.P2P
import lupos.s14endpoint.*


fun main(args: Array<String>) = CoroutinesHelper.runBlock {
    endpointServer = EndpointServerImpl("localhost")
    P2P.start(null)
    mapOf(
            testDictionaryVarName to "DictionaryVarName.txt",
            testDictionaryValue to "DictionaryValue.txt"
    ).forEach { (k, v) ->
        java.io.File("resources/$v").forEachLine {
            k.createValue(it)
        }
    }
    val query = Query()
    testDictionaryValue.mapLTS.forEach {
        try {
            val tmp = AOPVariable.calculate(query, it)
            if (testDictionaryValueTyped[tmp.operatorID] == null)
                testDictionaryValueTyped[tmp.operatorID] = ResultSetDictionary()
            testDictionaryValueTyped[tmp.operatorID]!!.createValue(it!!)
        } catch (e: Throwable) {
            testDictionaryValueTyped[EOperatorID.ValueSimpleLiteralID]!!.createValue("\"" + it!! + "\"")
        }
    }
    query.commit()
    if (args.size > 0) {
        JenaRequest.db = args[0]
        JenaRequest.dbwascreated = true
    }
    val fuzzer = Fuzzer(FuzzInstance(), "javafuzz/${JenaRequest.db}")
    fuzzer.start()
}

//https://gitlab.com/akihe/radamsa
//https://github.com/fuzzitdev/javafuzz
class FuzzInstance() : AbstractFuzzTarget() {
    @JvmField
    var timepoint = Instant.now()

    override fun fuzz(data: ByteArray) = CoroutinesHelper.runBlock {
        val timepointNext = Instant.now()
        val elapsed = Duration.between(timepoint, timepointNext)
        timepoint = timepointNext
//        println("time between tests :: ${elapsed.toMillis()} milliseconds")
        if (data.size >= 4) {
            val input = DynamicByteArray(data)
            executeBinaryTest(input!!)
        }
        val timepointNext2 = Instant.now()
        val elapsed2 = Duration.between(timepoint, timepointNext2)
        timepoint = timepointNext2
//        println("time for tests :: ${elapsed2.toMillis()} milliseconds")
    }
}
