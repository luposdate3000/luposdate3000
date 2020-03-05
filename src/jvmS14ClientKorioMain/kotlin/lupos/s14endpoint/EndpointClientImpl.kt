package lupos.s14endpoint
import com.soywiz.korio.stream.*
import com.soywiz.korio.net.http.createHttpClient
import com.soywiz.korio.net.http.Http
import com.soywiz.korio.net.http.*
import com.soywiz.korio.net.URL
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
    suspend fun requestGetBytes(url: String): ByteArray = Trace.trace({ "EndpointClientImpl.requestGet" }, {
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
        return res.readAllBytes()
    })

    suspend fun requestPostBytes(url: String, data: DynamicByteArray): ByteArray = Trace.trace({ "EndpointClientImpl.requestPost" }, {
        require(!url.startsWith("http://${endpointServer!!.fullname}"))
        var i = 0
        var res: HttpClient.Response
        while (true) {
            i++
            try {
                res = client.request(Http.Method.POST, url, Http.Headers(), AsyncStream(MyDynamicByteArray(data)))
                break
            } catch (e: Throwable) {
                if (i > 100)
                    throw e
                delay(10)
            }
        }
        return res.readAllBytes()
    })
    suspend fun requestGetString(url: String): String = Trace.trace({ "EndpointClientImpl.requestGet" }, {
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
        return res.readAllString()
    })

    suspend fun requestPostString(url: String, data: DynamicByteArray): String = Trace.trace({ "EndpointClientImpl.requestPost" }, {
        require(!url.startsWith("http://${endpointServer!!.fullname}"))
        var i = 0
        var res: HttpClient.Response
        while (true) {
            i++
            try {
                res = client.request(Http.Method.POST, url, Http.Headers(), AsyncStream(MyDynamicByteArray(data)))
                break
            } catch (e: Throwable) {
                if (i > 100)
                    throw e
                delay(10)
            }
        }
        return res.readAllString()
    })
}
class MyDynamicByteArray : AsyncStreamBase{
val data:DynamicByteArray
constructor(data:DynamicByteArray){
this.data=data
data.finish()
}
  override suspend fun read(position: Long, buffer: ByteArray, offset: Int, len: Int): Int {
        if (position > data.pos)
            return 0
        if (position + len > data.pos) {
            data.data.copyInto(buffer, offset, position.toInt(), data.pos)
            return data.pos - position.toInt()
        }
        data.data.copyInto(buffer, offset, position.toInt(), position.toInt() + len)
        return len
    }

    override suspend fun getLength(): Long {
        return data.pos.toLong()
    }
}
