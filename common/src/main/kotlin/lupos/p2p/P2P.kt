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

val MESSAGE_HEADER_RESPONSE = 0
val MESSAGE_HEADER_ECHO_STRING = 1

abstract class Message() {
    companion object {
        var global_uuid = 0
    }

    var uuid = global_uuid++

    suspend abstract fun write(client: AsyncClient): Message
    suspend abstract fun read(client: AsyncClient): Message
    suspend abstract fun readAndProcess(client: AsyncClient): Message
}

class MessageEchoString() : Message() {
    var data = ""
    suspend override fun read(client: AsyncClient): Message {
        data = client.readStringz()
        println("data = client.readStringz()")
        return this
    }

    suspend override fun write(client: AsyncClient): Message {
        client.write32LE(MESSAGE_HEADER_ECHO_STRING)
        println("client.write32LE(MESSAGE_HEADER_ECHO_STRING)")
        client.write32LE(uuid)
        println("client.write32LE(uuid)")
        client.writeStringz(data)
        println("client.writeStringz(data)")
        return this
    }

    suspend override fun readAndProcess(client: AsyncClient): Message {
        read(client)
        write(client)
        return this
    }
}

object P2P {

    var port: Int = 8080
    var bootstrap: String? = null
    private val clients = mutableMapOf<String, AsyncClient>()
    private val channels = mutableMapOf<Int, Channel<Message>>()
    private var server: Any? = null

    suspend fun addClient(name: String, client: AsyncClient) {
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
                println(" val name = client.readStringz()")
                addClient(name, client)
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
                println("client.writeStringz(servername)")
                addClient(bootstrap!!, client)
            }
        }
    }

    suspend fun onConnect(name: String, client: AsyncClient) {
//        println("onConnect $name")
        var buffer = ByteArray(10)
        while (client.connected) {
            var header = client.readS32LE()
            println("var header = client.readS32LE()")
            onReceive(name, client, header)
        }
    }

    suspend fun onReceive(name: String, client: AsyncClient, header: Int) {
        // println("onReceive $name $header")
        if (header == MESSAGE_HEADER_RESPONSE) {
            val header2 = client.readS32LE()
            println("val header2 = client.readS32LE()")
            val uuid = client.readS32LE()
            println("val uuid = client.readS32LE()")
            val message: Message = when (header2) {
                MESSAGE_HEADER_ECHO_STRING -> MessageEchoString().read(client)
                else -> throw Exception("unknown Message header $header")
            }
            channels[uuid]!!.send(message)
        } else {
            client.write32LE(MESSAGE_HEADER_RESPONSE)
            println("client.write32LE(MESSAGE_HEADER_RESPONSE)")
            val uuid = client.readS32LE()
            println("val uuid = client.readS32LE()")
            when (header) {
                MESSAGE_HEADER_ECHO_STRING -> MessageEchoString().readAndProcess(client)
            }
        }
    }

    suspend fun send_message(target: String, message: Message): Channel<Message> {
        val client = clients[target]!!
        val channel = Channel<Message>()
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
    if (bootStrapServer != null) {
        var result = async(Dispatchers.Default) {
            delay(1000)
            for (c in P2P.getClients()) {
                val message = MessageEchoString()
                message.data = "test"
                val channel = P2P.send_message(c, message)
                for (m in channel) {
                    println("recieved-finally" + message.data)
                }
            }
            delay(1000)
        }
    }
    while (true) {
    }
}
