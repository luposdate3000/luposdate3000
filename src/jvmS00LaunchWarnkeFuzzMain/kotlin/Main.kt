import java.net.ConnectException
import java.time.Duration
import java.time.Instant
import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.Coverage
import lupos.s00misc.DynamicByteArray
import lupos.s00misc.File
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.ValueDefinition
import lupos.s03resultRepresentation.ValueSimpleLiteral
import lupos.s04logicalOperators.Query
import lupos.s14endpoint.Endpoint
import lupos.s14endpoint.endpointServer
import lupos.s14endpoint.EndpointServerImpl
import lupos.s16network.ServerCommunicationSend
fun main(args: Array<String>) = CoroutinesHelper.runBlock {
Coverage.funStart(16362)
    endpointServer = EndpointServerImpl("localhost")
Coverage.statementStart(16363)
    ServerCommunicationSend.start(null)
Coverage.statementStart(16364)
    mapOf(/*return*/
/*return*/testDictionaryVarName to "DictionaryVarName.txt",
            /*return*/testDictionaryValue to "DictionaryValue.txt"
    ).forEach { (k, v) ->
Coverage.statementStart(16365)
        File("resources/$v").forEachLine {
Coverage.statementStart(16366)
            k.add(it)
Coverage.statementStart(16367)
        }
Coverage.statementStart(16368)
    }
    val query = Query()
    testDictionaryValue.forEach {
Coverage.statementStart(16369)
        try {
Coverage.statementStart(16370)
            val tmp = ValueDefinition(it)
Coverage.statementStart(16371)
            if (testDictionaryValueTyped[ValueToID(tmp)] == null) {
Coverage.ifStart(16372)
                testDictionaryValueTyped[ValueToID(tmp)] = ThreadSafeMutableList()
Coverage.statementStart(16373)
            }
Coverage.statementStart(16374)
            testDictionaryValueTyped[ValueToID(tmp)]!!.add(it!!)
Coverage.statementStart(16375)
        } catch (e: Throwable) {
Coverage.statementStart(16376)
            testDictionaryValueTyped[ValueEnum.ValueSimpleLiteral]!!.add("\"" + it!! + "\"")
Coverage.statementStart(16377)
        }
Coverage.statementStart(16378)
    }
    var testcase = TestCase.ETripleStore
    var datasize = 16
    if (args.size > 0) {
Coverage.statementStart(16379)
        JenaRequest.db = args[0]
Coverage.statementStart(16380)
        JenaRequest.dbwascreated = true
Coverage.statementStart(16381)
        if (args.size > 1) {
Coverage.ifStart(16382)
            datasize = args[1].toInt()
Coverage.statementStart(16383)
            if (args.size > 2) {
Coverage.ifStart(16384)
                try {
Coverage.statementStart(16385)
                    val x = args[2].toInt()
Coverage.statementStart(16386)
                    testcase = TestCase.values()[x]
Coverage.statementStart(16387)
                } catch (e: Throwable) {
Coverage.statementStart(16388)
                    testcase = TestCase.valueOf(args[2])
Coverage.statementStart(16389)
                }
Coverage.statementStart(16390)
            }
Coverage.statementStart(16391)
        }
Coverage.statementStart(16392)
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
Coverage.statementStart(16393)
        testnumber++
Coverage.statementStart(16394)
        counter--
Coverage.statementStart(16395)
        if (counter == 0) {
Coverage.ifStart(16396)
            if (datasize < 1000) {
Coverage.ifStart(16397)
                datasize += 2
Coverage.statementStart(16398)
            } else {
Coverage.ifStart(16399)
                datasize = (datasize * 1.01).toInt() + 1
Coverage.statementStart(16400)
            }
Coverage.statementStart(16401)
            counter = datasize
Coverage.statementStart(16402)
        }
Coverage.statementStart(16403)
        val bytebuffer = ByteBuffer.allocate(datasize)
Coverage.statementStart(16404)
        currentSize = fileChannel.read(bytebuffer)
Coverage.statementStart(16405)
        val data = bytebuffer.array()
Coverage.statementStart(16406)
        val input = DynamicByteArray(data, currentSize)
Coverage.statementStart(16407)
        try {
Coverage.statementStart(16408)
            for (testcase in TestCase.values()) {
Coverage.forLoopStart(16409)
                runBlocking {
Coverage.statementStart(16410)
                    withTimeout(1000, {
Coverage.statementStart(16411)
                        val position = input.pos
Coverage.statementStart(16412)
                        testcase.action(TestRandom(input!!))
Coverage.statementStart(16413)
                        input.pos = position
Coverage.statementStart(16414)
                    })
Coverage.statementStart(16415)
                }
Coverage.statementStart(16416)
            }
Coverage.statementStart(16417)
            val timepointNext2 = Instant.now()
Coverage.statementStart(16418)
            val elapsed2 = Duration.between(timepoint, timepointNext2)
Coverage.statementStart(16419)
            timepoint = timepointNext2
Coverage.statementStart(16420)
            if (testnumber % 1000 == 0) {
Coverage.ifStart(16421)
                println("test ${JenaRequest.db} ${currentSize} $testnumber ${elapsed2.toMillis()} milliseconds")
Coverage.statementStart(16422)
            }
Coverage.statementStart(16423)
        } catch (e: ConnectException) {
Coverage.statementStart(16424)
            e.printStackTrace()
Coverage.statementStart(16425)
        } catch (e: Throwable) {
Coverage.statementStart(16426)
            e.printStackTrace()
Coverage.statementStart(16427)
            lupos.s00misc.File("mnt/crash-${data.hashCode()}").write(input)
Coverage.statementStart(16428)
        }
Coverage.statementStart(16429)
    }
}
