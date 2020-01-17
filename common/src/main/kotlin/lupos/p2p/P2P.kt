package lupos.p2p

import lupos.misc.*
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

object P2P {
    val REQUEST_GET_KNOWN_HOSTS = arrayOf("getKnownHosts", "hostname")
    var hostname = "localhost"
    var port = 8080
    val knownClients = mutableListOf<String>()
    var server: HttpServer? = null
    val client = createHttpClient()

    suspend fun process_get_known_hosts(hostname: String): String {
        return knownClients.toString()
    }

    suspend fun myRequestHandler(request: HttpServer.Request) {
        println("listen::Request")
        val params = request.getParams
        println(params)
        println(request.path)
        println(request.method)
        request.replaceHeader("Connection", "close")
        request.replaceHeader("Content-Type", "text/html")
        var response = ""
        try {
            when (request.path) {
                REQUEST_GET_KNOWN_HOSTS[0] -> response = process_get_known_hosts(params[REQUEST_GET_KNOWN_HOSTS[1]]!![0]!!)
                else -> request.setStatus(404)
            }
        } catch (e: Throwable) {
            e.kotlinStacktrace()
            response = e.toString()
            request.setStatus(404)
        }
        request.end(response)
    }

    fun start() {
        var result = async(Dispatchers.Default) {
            server = createHttpServer().listen(port, hostname, ::myRequestHandler)
        }
    }

    fun joinNetwork(bootstrap: String) {
        var result = async(Dispatchers.Default) {
            var response = client.request(Http.Method.GET, "http://${bootstrap}/${REQUEST_GET_KNOWN_HOSTS[0]}?${REQUEST_GET_KNOWN_HOSTS[1]}=${hostname}")
            var responseString = response.readAllString()
            println(responseString)
        }
    }
}

fun main(args: Array<String>) {
    var i = 0
    var serverPort = 0
    var serverName = ""
    var bootStrapServer: String? = null
    for (a in args) {
        println("args[$i]=$a")
        when (i) {
            0 -> P2P.port = a.toInt()
            1 -> P2P.hostname = a
            2 -> bootStrapServer = a
        }
        i++
    }
    P2P.start()
    if (bootStrapServer != null)
        P2P.joinNetwork(bootStrapServer)
    while (true) {
    }
}
