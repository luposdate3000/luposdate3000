import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ConnectException
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.time.Duration
import java.time.Instant
import kotlinx.coroutines.*
import lupos.*
import lupos.s00misc.*
import lupos.s00misc.Coverage
import lupos.s00misc.executeBinaryTest
import lupos.s01io.*
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
    val query = Query()
    testDictionaryValue.forEach {
        try {
            val tmp = ValueDefinition(it)
            if (testDictionaryValueTyped[ValueToID(tmp)] == null) {
                testDictionaryValueTyped[ValueToID(tmp)] = ThreadSafeMutableList()
            }
            testDictionaryValueTyped[ValueToID(tmp)]!!.add(it!!)
        } catch (e: Throwable) {
            testDictionaryValueTyped[ValueEnum.ValueSimpleLiteral]!!.add("\"" + it!! + "\"")
        }
    }
    var testcase = TestCase.ETripleStore
    var datasize = 16
    if (args.size > 0) {
        JenaRequest.db = args[0]
        JenaRequest.dbwascreated = true
        if (args.size > 1) {
            datasize = args[1].toInt()
            if (args.size > 2) {
                try {
                    val x = args[2].toInt()
                    testcase = TestCase.values()[x]
                } catch (e: Throwable) {
                    testcase = TestCase.valueOf(args[2])
                }
            }
        }
    }
    val workdir = "javafuzz/${JenaRequest.db}"
    var timepoint = Instant.now()
    val randomFile = File("/dev/urandom")
    val fis = FileInputStream(randomFile)
    val fileChannel = fis.getChannel()
    var currentSize = 0
    var testnumber = 0
    var counter = datasize
    var errors = 0
    while (true) {
        testnumber++
        counter--
        if (counter == 0) {
            if (datasize < 1000) {
                datasize += 2
            } else {
                datasize = (datasize * 1.01).toInt() + 1
            }
            counter = datasize
        }
        val bytebuffer = ByteBuffer.allocate(datasize)
        currentSize = fileChannel.read(bytebuffer)
        val data = bytebuffer.array()
        val input = DynamicByteArray(data, currentSize)
        try {
            for (testcase in TestCase.values()) {
                runBlocking {
                    withTimeout(1000, {
                        val position = input.pos
                        testcase.action(TestRandom(input!!))
                        input.pos = position
                    })
                }
            }
            val timepointNext2 = Instant.now()
            val elapsed2 = Duration.between(timepoint, timepointNext2)
            timepoint = timepointNext2
            if (testnumber % 1000 == 0) {
                println("test ${JenaRequest.db} ${currentSize} $testnumber ${elapsed2.toMillis()} milliseconds")
            }
        } catch (e: ConnectException) {
            e.printStackTrace()
        } catch (e: Throwable) {
            e.printStackTrace()
            lupos.s00misc.File("mnt/crash-${data.hashCode()}").write(input)
        }
    }
}
