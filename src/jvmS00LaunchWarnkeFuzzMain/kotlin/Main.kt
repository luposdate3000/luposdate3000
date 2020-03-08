import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ConnectException
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.time.Duration
import java.time.Instant
import lupos.*
import lupos.s00misc.*
import lupos.s00misc.executeBinaryTest
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s04arithmetikOperators.noinput.AOPVariable
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
        File("resources/$v").forEachLine {
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
            testDictionaryValueTyped[EOperatorID.AOPSimpleLiteralID]!!.createValue("\"" + it!! + "\"")
        }
    }
    var datasize = 16
    if (args.size > 0) {
        JenaRequest.db = args[0]
        JenaRequest.dbwascreated = true
    }
    if (args.size > 1)
        datasize = args[1].toInt()
    val workdir = "javafuzz/${JenaRequest.db}"
    var timepoint = Instant.now()
    val randomFile = File("/dev/urandom")
    val fis = FileInputStream(randomFile)
    val fileChannel = fis.getChannel()
    var currentSize = 0
    var testnumber = 0
    var counter = datasize
    while (true) {
        testnumber++
        counter--
        if (counter == 0) {
            datasize = (datasize * 1.2).toInt() + 1
            counter = datasize
            println("changed datasize to $datasize for $counter tests")
        }
        val bytebuffer = ByteBuffer.allocate(datasize)
        currentSize = fileChannel.read(bytebuffer)
        val data = bytebuffer.array()
        val input = DynamicByteArray(data, currentSize)
        try {
            while (true)
                try {
                    executeBinaryTest(input!!)
                    val timepointNext2 = Instant.now()
                    val elapsed2 = Duration.between(timepoint, timepointNext2)
                    timepoint = timepointNext2
                    println("test ${JenaRequest.db} ${currentSize} $testnumber ${elapsed2.toMillis()} milliseconds")
                    break
                } catch (e: ConnectException) {
                    e.printStackTrace()
                }
        } catch (e: Throwable) {
            e.printStackTrace()
            input.pos = currentSize
            lupos.s00misc.File("crash-${data.hashCode()}").write(input)
        }
    }
}
