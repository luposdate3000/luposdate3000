import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.time.Duration
import java.time.Instant
import lupos.*
import lupos.s00misc.*
import lupos.s00misc.Coverage
import lupos.s00misc.executeBinaryTest
import lupos.s03resultRepresentation.MyListValue
import lupos.s03resultRepresentation.nodeGlobalDictionary
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.ValueBnode
import lupos.s03resultRepresentation.ValueBoolean
import lupos.s03resultRepresentation.ValueComparatorASC
import lupos.s03resultRepresentation.ValueComparatorDESC
import lupos.s03resultRepresentation.ValueComparatorFast
import lupos.s03resultRepresentation.ValueDateTime
import lupos.s03resultRepresentation.ValueDecimal
import lupos.s03resultRepresentation.ValueDefinition
import lupos.s03resultRepresentation.ValueDouble
import lupos.s03resultRepresentation.ValueError
import lupos.s03resultRepresentation.ValueInteger
import lupos.s03resultRepresentation.ValueIri
import lupos.s03resultRepresentation.ValueLanguageTaggedLiteral
import lupos.s03resultRepresentation.ValueNumeric
import lupos.s03resultRepresentation.ValueSimpleLiteral
import lupos.s03resultRepresentation.ValueStringBase
import lupos.s03resultRepresentation.ValueTypedLiteral
import lupos.s03resultRepresentation.ValueUndef
import lupos.s04arithmetikOperators.noinput.AOPBuildInCallBNODE0
import lupos.s04arithmetikOperators.noinput.AOPBuildInCallSTRUUID
import lupos.s04arithmetikOperators.noinput.AOPBuildInCallUUID
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04arithmetikOperators.noinput.AOPValue
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.*
import lupos.s12p2p.P2P
import lupos.s14endpoint.*

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
