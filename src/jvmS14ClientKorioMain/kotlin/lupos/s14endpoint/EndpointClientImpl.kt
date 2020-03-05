package lupos.s14endpoint

import com.soywiz.korio.net.http.createHttpClient
import com.soywiz.korio.net.http.Http
import com.soywiz.korio.net.http.HttpClient
import com.soywiz.korio.net.URL
import com.soywiz.korio.stream.AsyncStream
import kotlin.concurrent.thread
import kotlinx.coroutines.delay
import lupos.s00misc.*
import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.EGraphOperationType
import lupos.s00misc.ELoggerType
import lupos.s00misc.GlobalLogger
import lupos.s00misc.parseFromXml
import lupos.s00misc.Trace
import lupos.s00misc.XMLElement
import lupos.s02buildSyntaxTree.rdf.Dictionary
import lupos.s03resultRepresentation.ResultRepresenationNetwork
import lupos.s03resultRepresentation.ResultSet
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.OPBase
import lupos.s09physicalOperators.noinput.POPEmptyRow
import lupos.s09physicalOperators.noinput.POPImportFromXml
import lupos.s09physicalOperators.POPBase
import lupos.s14endpoint.*
import lupos.s14endpoint.Endpoint
import lupos.SparqlTestSuite


object EndpointClientImpl {
    val client = createHttpClient()
    fun encodeString(s: String) = URL.encodeComponent(s)
    suspend fun requestGet(url: String): HttpClient.Response = Trace.trace({ "P2P.retryRequest" }, {
        require(!url.startsWith("http://${endpointServer!!.fullname}"))
        var i = 0
        var res: HttpClient.Response
        while (true) {
            i++
            try {
                res = client.request(Http.Method.GET, url)
                break
            } catch (e: Throwable) {
                if (i > 100)
                    throw e
                delay(10)
            }
        }
        return res
    })

    suspend fun requestPost(url: String, data: AsyncStream): HttpClient.Response = Trace.trace({ "P2P.retryRequest" }, {
        require(!url.startsWith("http://${endpointServer!!.fullname}"))
        var i = 0
        var res: HttpClient.Response
        while (true) {
            i++
            try {
                res = client.request(Http.Method.POST, url, Http.Headers(), data)
                break
            } catch (e: Throwable) {
                if (i > 100)
                    throw e
                delay(10)
            }
        }
        return res
    })
}
