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

suspend fun myRequestHandler(request:HttpServer.Request){
println("listen::Request")
println("${request.uri} ${request.method}")
request.replaceHeader("Connection","close")
request.replaceHeader("Content-Type","text/html")
println("listen::Requesta")
request.end("test")
println("listen::Requestb")
}
suspend fun myWsRequestHandler(request:HttpServer.WsRequest){
println("listen::WsRequest")
}

fun main(args: Array<String>) {
    var i = 0
    var serverPort = 0
    var serverName = ""
    var bootStrapServer: String? = null
    for (a in args) {
        println("args[$i]=$a")
        when (i) {
            0 -> serverPort = a.toInt()
            1 -> serverName = a
            2 -> bootStrapServer = a
        }
        i++
    }	
var result = async(Dispatchers.Default) {
	val server=createHttpServer().listen(serverPort,serverName,::myRequestHandler).websocketHandler(::myWsRequestHandler)
}
if(bootStrapServer!=null){
var result = async(Dispatchers.Default) {
delay(500)
val client=createHttpClient()
println("XXX::"+"http://${bootStrapServer}/")
val response=client.request(Http.Method.GET,"http://${bootStrapServer}/").readAllString()
println(response)
}
}
while(true){
}
}

//maybe better :: ../korio/korio/src/commonMain/kotlin/com/soywiz/korio/net/http/HttpServer.kt
