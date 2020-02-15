package lupos.s12p2p

import com.soywiz.korio.net.http.createHttpClient
import com.soywiz.korio.net.http.Http
import com.soywiz.korio.net.http.HttpClient
import com.soywiz.korio.net.URL
import com.soywiz.korio.stream.*
import kotlin.concurrent.thread
import kotlinx.coroutines.channels.*
import kotlinx.coroutines.delay
import lupos.s00misc.*
import lupos.s00misc.parseFromXml
import lupos.s02buildSyntaxTree.rdf.Dictionary
import lupos.s03resultRepresentation.*
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s04logicalOperators.noinput.*
import lupos.s04logicalOperators.OPBase
import lupos.s09physicalOperators.noinput.POPEmptyRow
import lupos.s09physicalOperators.noinput.POPImportFromXml
import lupos.s09physicalOperators.POPBase
import lupos.s14endpoint.Endpoint
import lupos.s14endpoint.EndpointImpl
import lupos.testMain

class TransferHelperNetwork : AsyncStreamBase {
    companion object {
        suspend fun processBinary(channelIn: DynamicByteArrayAsyncRead) {
            channelIn.fetch()
            val dictionary = ResultSetDictionary()
            val transactionID = channelIn.buffer!!.getNextLong()
            println("read transactionID $transactionID")
                channelIn.fetch()
            var header = ENetworkMessageType.values()[channelIn.buffer!!.getNextInt()]
            println("read header $header")
            while (header != ENetworkMessageType.FINISH) {
                val count = channelIn.buffer!!.getNextInt()
                println("read counter $count")
                when (header) {
                    ENetworkMessageType.NONE -> {
                    }
                    ENetworkMessageType.FINISH -> {
                    }
                    ENetworkMessageType.DICTIONARY_ENTRY -> {
                        for (i in 0 until count) {
                            val name = channelIn.buffer!!.getNextString()
                            dictionary.createValue(name)
                            println("read dict entry $name")
                        }
                    }
                    ENetworkMessageType.TRIPLE_ADD -> {
                        for (i in 0 until count) {
                            val graphName = dictionary.getValue(channelIn.buffer!!.getNextInt())!!
                            println("read triple graph $graphName")
                            val s = dictionary.getValue(channelIn.buffer!!.getNextInt())!!
                            println("read triple s $s")
                            val p = dictionary.getValue(channelIn.buffer!!.getNextInt())!!
                            println("read triple p $p")
                            val o = dictionary.getValue(channelIn.buffer!!.getNextInt())!!
                            println("read triple o $o")
                            val idx = EIndexPattern.values()[channelIn.buffer!!.getNextInt()]
                            println("read triple idx $idx")
                                Endpoint.process_local_triple_add(graphName, transactionID, s, p, o, idx)
                        }
                    }
                }
                channelIn.fetch()
                header = ENetworkMessageType.values()[channelIn.buffer!!.getNextInt()]
                println("read header $header")
            }
            channelIn.finish()
        }
    }

    val dictionary = ResultSetDictionary()
    val channelOut = DynamicByteArrayAsyncWrite()
    var lastHeader = ENetworkMessageType.NONE
    var lastCounterPos = 0
    var lastCounterValue = 0
    var lastDictionaryKey: Value? = null

    constructor(transactionID: Long) {
println("DynamicByteArrayAsyncWrite() b")
        println("write transactionID $transactionID")
        channelOut.buffer!!.appendLong(transactionID)
    }

    suspend fun enforceHeader(h: ENetworkMessageType) {
        if (lastHeader != h) {
            if (lastCounterValue > 0) {
                println("override counter $lastCounterValue")
                channelOut.buffer!!.setInt(lastCounterValue, lastCounterPos)
            }
            lastCounterValue = 0
            lastHeader = h
            channelOut.flush()
            println("write header $h")
            channelOut.buffer!!.appendInt(h.ordinal)
            if (h != ENetworkMessageType.FINISH) {
                println("space counter")
                lastCounterPos = channelOut.buffer!!.appendSpace(4)
            }
        }
    }

    suspend fun createDictionaryValue(s: String): Value {
        val tmp = dictionary.createValue(s)
        if (lastDictionaryKey == null || tmp > lastDictionaryKey!!) {
            enforceHeader(ENetworkMessageType.DICTIONARY_ENTRY)
            println("write dict entry $s")
            channelOut.buffer!!.appendString(s)
            lastCounterValue++
        }
        return tmp
    }

    suspend fun addTriple(graphName: String, s: String, p: String, o: String, idx: EIndexPattern) {
        val gv = createDictionaryValue(graphName)
        val sv = createDictionaryValue(s)
        val pv = createDictionaryValue(p)
        val ov = createDictionaryValue(o)
        enforceHeader(ENetworkMessageType.TRIPLE_ADD)
        println("write triple graph $graphName")
        channelOut.buffer!!.appendInt(gv)
        println("write triple s $s")
        channelOut.buffer!!.appendInt(sv)
        println("write triple p $p")
        channelOut.buffer!!.appendInt(pv)
        println("write triple o $o")
        channelOut.buffer!!.appendInt(ov)
        println("write triple idx $idx")
        channelOut.buffer!!.appendInt(idx.ordinal)
        lastCounterValue++
    }

    var senduntil = 0L
    var sendbuf = ByteArray(0)
    var sendbufsize = 0
    var sendoffset = 0
var combinedChannel=Channel<Pair<ByteArray,Int>>(Channel.UNLIMITED)
var combinedLength=-1
    override suspend fun read(position: Long, buffer: ByteArray, offset: Int, len: Int): Int {
        println("read :: $position $offset $len")
        require(senduntil == position)
        var outoffset = offset
        var outlenremaining = len

        try {
while(true){
println("currentsend ::${len - outlenremaining}")
            while (sendoffset == sendbufsize) {
println("channel 1 receive a")
                val tmp = combinedChannel.receive()
println("channel 1 receive b")
                sendbuf = tmp.first
                sendbufsize = tmp.second
                println("sender.fromqueue :: ${sendbufsize} ${sendbuf.size}")
                sendoffset = 0
            }
            if ((sendbufsize - sendoffset) < outlenremaining) {
                sendbuf.copyInto(buffer, outoffset, sendoffset, sendbufsize)
                sendoffset = sendbufsize
                outoffset += sendbufsize
                outlenremaining -= sendbufsize
                senduntil += sendbufsize
            } else {
                val tmp = sendoffset + outlenremaining
                sendbuf.copyInto(buffer, outoffset, sendoffset, tmp)
                sendoffset = tmp
                outoffset += tmp
                outlenremaining -= tmp
                senduntil += tmp
                println("sender.sending a ${len - outlenremaining}")
                return len - outlenremaining
            }
}
        } catch (e: Throwable) {
println("c 3")
            e.printStackTrace()
println("c 4")
                println("sender.sending b ${len - outlenremaining}")
println("c 5")
                return len - outlenremaining
        }
        println("sender.sending c 0")
        return 0
    }

    override suspend fun getLength(): Long {
        println("getlength start")
if(combinedLength==-1){
var len=0
println("d 1")
try{
println("d 2")
while(true){
println("d 3")
val tmp=channelOut.channel.receive()
println("d 4")
len+=tmp.second
combinedChannel.send(tmp)
println("d 5")
}
println("d 6")
} catch (e: Throwable) {
println("d 7")
combinedChannel.close()
}
println("d 8")
try{
throw Exception("getLength")
}catch (e: Throwable) {
e.printStackTrace()
}
combinedLength=len
}
println("getlength :: ${combinedLength}")
        return 0L+combinedLength
    }

    suspend fun finish(): AsyncStream {
        enforceHeader(ENetworkMessageType.FINISH)
        channelOut.finish()
        return AsyncStream(this)
    }
}

