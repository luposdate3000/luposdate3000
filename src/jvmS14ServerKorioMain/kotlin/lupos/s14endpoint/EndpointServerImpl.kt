package lupos.s14endpoint

import com.soywiz.korio.net.http.createHttpServer
import com.soywiz.korio.net.http.Http
import com.soywiz.korio.net.http.HttpServer
import kotlin.concurrent.thread
import kotlin.jvm.JvmField
import kotlinx.coroutines.delay
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import lupos.s00misc.*
import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.Coverage
import lupos.s00misc.EGraphOperationType
import lupos.s00misc.EIndexPattern
import lupos.s00misc.ELoggerType
import lupos.s00misc.GlobalLogger
import lupos.s03resultRepresentation.*
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04logicalOperators.Query
import lupos.s12p2p.P2P
import lupos.s14endpoint.Endpoint

@UseExperimental(ExperimentalStdlibApi::class)
class EndpointServerImpl(hostname: String = "localhost", port: Int = 80) : EndpointServer(hostname, port) {
    @JvmField
    var server: HttpServer? = null

    suspend fun responseBinary(request: HttpServer.Request) {
/*        var data: ByteArray? = null
            request.handler { it ->
                if (data == null) {
                    data = it
                } else {
                    data = data!! + it
                }
            }
            request.endHandler {
                CoroutinesHelper.runBlock {
                        try {
                            val res = receive(request.path, data!!)
                            request.replaceHeader("Content-Type", "application/x-binary")
                            request.end(res)
                        } catch (e: Throwable) {
                            request.setStatus(404)
                            request.end()
                        }
                }
            }
*/
    }

    suspend fun myRequestHandler(request: HttpServer.Request) {
        try {
//BenchmarkUtils.start(EBenchmark.HTTP)
            GlobalLogger.log(ELoggerType.DEBUG, { "listen::Request" })
            val params = request.getParams
            GlobalLogger.log(ELoggerType.DEBUG, { params })
            GlobalLogger.log(ELoggerType.DEBUG, { request.path })
            GlobalLogger.log(ELoggerType.DEBUG, { request.method })
            request.replaceHeader("Connection", "close")
            if (request.path == "/binary") {
                responseBinary(request)
                return
            }
            request.replaceHeader("Content-Type", "text/html")
            var responseBytes: ByteArray? = null
            var data = StringBuilder()
            request.handler { it ->
                //BenchmarkUtils.start(EBenchmark.HTTP_HANDLER)
                data.append(it.decodeToString())
//BenchmarkUtils.elapsedSeconds(EBenchmark.HTTP_HANDLER)
            }
            request.endHandler {
                CoroutinesHelper.runBlock {
                    try {
                        val singleParams = mutableMapOf<String, String>()
                        params.forEach { k, v ->
                            singleParams[k] = v?.first()
                        }
                        responseBytes = receive(request.path, request.method == Http.Method.POST, data.toString(), singleParams)
                    } catch (e: Throwable) {
                        responseBytes = e.toString().encodeToByteArray()
                        request.setStatus(404)
                    }
                    request.end(responseBytes!!)
                }
            }
        } finally {
//BenchmarkUtils.elapsedSeconds(EBenchmark.HTTP)
        }
    }

    override suspend fun start() {
        server = createHttpServer().listen(port, hostname, ::myRequestHandler)
    }
}
