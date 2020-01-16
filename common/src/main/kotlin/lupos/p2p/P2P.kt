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

object P2P {
    var port: Int = 8080
    var bootstrap: String? = null
    private val clients = mutableMapOf<String, AsyncClient>()
    private var server: Any? = null

    suspend fun addClient(name: String, client: AsyncClient) {
        clients[name] = client
        onConnect(name, client)
    }

    suspend fun getClients(): List<AsyncClient> {
        val res = mutableListOf<AsyncClient>()
        for ((k, v) in clients)
            res.add(v)
        return res
    }

    fun start() {
        launch(Dispatchers.Default) {
            server = createTcpServer(port)
            (server!! as AsyncServer).listen { client ->
                val name = client.readStringz()
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
                addClient(bootstrap!!, client)
            }
        }
    }

    suspend fun onConnect(name: String, client: AsyncClient) {
        println("onConnect $name")
        var buffer = ByteArray(10)
        while (client.connected) {
            println("server loop")
            var header = client.readS32LE()
            onReceive(name, client, header)
        }
    }

    suspend fun onReceive(name: String, client: AsyncClient, header: Int) {
        println("onReceive $name $header")
//to p2p server calculations here
        client.write32LE(header)
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
    println("a")
    P2P.start()
    println("b")
    var result = async(Dispatchers.Default) {
        println("c")
        delay(1000)
        println("d")
        for (c in P2P.getClients()) {
            println("e1")
            c.write32LE(serverPort)
            println("e4")
            val rec = c.readS32LE()
            println("e5")
            println("recieve-main ${rec}")
            println("f")
        }
        println("g")
        delay(1000)
        println("h")
    }
    println("i")
    while (true) {
    }
    println("j")
    println("stop")
}
