package lupos.s14endpoint
import com.soywiz.korio.net.http.createHttpServer
import com.soywiz.korio.net.http.Http
import com.soywiz.korio.net.http.HttpServer
import kotlin.concurrent.thread
import kotlin.jvm.JvmField
import kotlinx.coroutines.delay
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import lupos.s00misc.BenchmarkUtils
import lupos.s00misc.classNameToString
import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.Coverage
import lupos.s00misc.DynamicByteArray
import lupos.s00misc.EBenchmark
import lupos.s00misc.EGraphOperationType
import lupos.s00misc.EGraphRefType
import lupos.s00misc.EIndexPattern
import lupos.s00misc.ELoggerType
import lupos.s00misc.EModifyType
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s00misc.File
import lupos.s00misc.GlobalLogger
import lupos.s00misc.JenaWrapper
import lupos.s00misc.MemoryStatistics
import lupos.s00misc.MyListDouble
import lupos.s00misc.MyListGeneric
import lupos.s00misc.MyListInt
import lupos.s00misc.MyMapDoubleInt
import lupos.s00misc.MyMapIntGeneric
import lupos.s00misc.MyMapIntInt
import lupos.s00misc.MyMapLongGeneric
import lupos.s00misc.MyMapLongInt
import lupos.s00misc.MyMapStringIntPatriciaTrie
import lupos.s00misc.MyMapStringIntPatriciaTrieDouble
import lupos.s00misc.MySetInt
import lupos.s00misc.OperatorGraphToLatex
import lupos.s00misc.parseFromXml
import lupos.s00misc.SanityCheck
import lupos.s00misc.ThreadSafeUuid
import lupos.s00misc.XMLElement
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
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.AOPBuildInCallBNODE0
import lupos.s04arithmetikOperators.noinput.AOPBuildInCallSTRUUID
import lupos.s04arithmetikOperators.noinput.AOPBuildInCallUUID
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04arithmetikOperators.noinput.AOPValue
import lupos.s04arithmetikOperators.noinput.AOPVariable
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
