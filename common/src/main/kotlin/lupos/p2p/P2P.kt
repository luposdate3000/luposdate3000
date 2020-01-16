package lupos.p2p

import com.soywiz.korio.stream.*
import com.soywiz.korio.net.*
import com.soywiz.korio.net.http.*
import com.soywiz.korio.async.*
import com.soywiz.korio.stream.*
import com.soywiz.korio.util.*
import com.soywiz.korio.async.*
import com.soywiz.korio.concurrent.atomic.*
import kotlinx.coroutines.*
import kotlinx.coroutines.sync.*
import kotlin.coroutines.*
import kotlinx.coroutines.channels.*

val MESSAGE_HEADER_RESPONSE = 0/*to indicate that this message is the response to a running request*/
val MESSAGE_HEADER_FINISH = 1/*finish answering the running request*/
val MESSAGE_HEADER_ECHO_STRING = 2

class ClientWrapper(val client: AsyncClient, var used: KorAtomicBoolean = KorAtomicBoolean(false))

abstract class Message() {
    companion object {
        var global_uuid = 0
    }

    var uuid = global_uuid++

    suspend abstract fun write(client: ClientWrapper): Message
    suspend abstract fun read(client: ClientWrapper): Message
    suspend abstract fun readAndProcess(client: ClientWrapper): Message
}

class MessageEchoString() : Message() {
    var data = ""
    suspend override fun read(client: ClientWrapper): Message {
        data = client.client.readStringz()
        return this
    }

    suspend override fun write(client: ClientWrapper): Message {
        client.client.write32LE(MESSAGE_HEADER_ECHO_STRING)
        client.client.write32LE(uuid)
        client.client.writeStringz(data)
        return this
    }

    suspend override fun readAndProcess(client: ClientWrapper): Message {
        read(client)
//do sth here
        write(client)
        return this
    }
}

object P2P {
    var port: Int = 8080
    var bootstrap: String? = null//connect to any known node at startup
    private val clients = mutableMapOf<String, ClientWrapper>()//connections to any other known node
    private val channels = mutableMapOf<Int, Channel<Message>>()//running requests started by this peer
    private var server: Any? = null//this is always initialized

    suspend fun addClient(name: String, client: ClientWrapper) {
        clients[name] = client
        onConnect(name, client)
    }

    suspend fun getClients(): List<String> {
        val res = mutableListOf<String>()
        for ((k, v) in clients)
            res.add(k)
        return res
    }

    fun start() {
        launch(Dispatchers.Default) {
            server = createTcpServer(port)
            (server!! as AsyncServer).listen { client ->
                val name = client.readStringz()
                addClient(name, ClientWrapper(client))
            }
        }
        launch(Dispatchers.Default) {
            if (bootstrap != null) {
                val pos = bootstrap!!.indexOf(':')
                var host = bootstrap!!
                var port = 8080
                if (pos >= 0) {
                    host = bootstrap!!.substring(0, pos)
                    port = bootstrap!!.substring(pos + 1, bootstrap!!.length).toInt()
                }
                val client = createTcpClient(host, port)
                client.writeStringz((server!! as AsyncServer).host + ":" + (server!! as AsyncServer).port)
                addClient(bootstrap!!, ClientWrapper(client))
            }
        }
    }

    suspend fun onConnect(name: String, client: ClientWrapper) {
        println("onConnect $name")
        var buffer = ByteArray(10)
        while (client.client.connected) {
            var header = client.client.readS32LE()
            client.used.compareAndSet(false, true)
            onReceive(name, client, header)
        }
    }

    suspend fun onReceive(name: String, client: ClientWrapper, header: Int) {
        println("onReceive $name $header")
        if (header == MESSAGE_HEADER_RESPONSE) {
            val header2 = client.client.readS32LE()
            val uuid = client.client.readS32LE()
            if (header2 == MESSAGE_HEADER_FINISH) {
                channels.remove(uuid)?.close()
                println("closedChannel $port :: ${uuid}")
                require(client.used.compareAndSet(true, false))
            } else {
                val message: Message = when (header2) {
                    MESSAGE_HEADER_ECHO_STRING -> MessageEchoString().read(client)
                    else -> throw Exception("unknown Message header $header")
                }
                message.uuid = uuid
                println("writingToChannel $port :: ${uuid}")
                channels[uuid]!!.send(message)
            }
        } else {
            client.client.write32LE(MESSAGE_HEADER_RESPONSE)
            val uuid = client.client.readS32LE()
            val message: Message = when (header) {
                MESSAGE_HEADER_ECHO_STRING -> MessageEchoString()
                else -> throw Exception("unknown Message header $header")
            }
            message.uuid = uuid
            message.readAndProcess(client)
            client.client.write32LE(MESSAGE_HEADER_RESPONSE)
            client.client.write32LE(MESSAGE_HEADER_FINISH)
            client.client.write32LE(uuid)
            require(client.used.compareAndSet(true, false))
        }
    }

    suspend fun send_message(target: String, message: Message): Channel<Message> {
        val client = clients[target]!!
        while (!client.used.compareAndSet(false, true)) {
            println("blocked :: $target")
            delay(10)
        }
        println("continue :: $target")
        val channel = Channel<Message>()
        println("openingChannel $port :: ${message.uuid}")
        channels[message.uuid] = channel
        message.write(client)
        return channel
    }

}

fun main(args: Array<String>) {
    var i = 0
    var serverPort = 0
    var bootStrapServer: String? = null
    for (a in args) {
        println("args[$i]=$a")
        when (i) {
            0 -> serverPort = a.toInt()
            1 -> bootStrapServer = a
        }
        i++
    }
    P2P.port = serverPort
    P2P.bootstrap = bootStrapServer
    P2P.start()
//    if (bootStrapServer != null) {
    var result = async(Dispatchers.Default) {
        if (serverPort == 8080)
            delay(500)
        for (i in 0..20) {
            delay(1000)
            for (c in P2P.getClients()) {
                val message = MessageEchoString()
                message.data = "test " + serverPort + " " + i
                val channel = P2P.send_message(c, message)
                for (m in channel) {
                    println("recieved-finally " + serverPort + " -- " + message.data)
                }
            }
        }
        delay(1000)
    }
//    }
    while (true) {
    }
}
