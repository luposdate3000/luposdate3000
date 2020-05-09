package lupos.s14endpoint
import com.soywiz.korio.net.http.*
import com.soywiz.korio.net.http.createHttpClient
import com.soywiz.korio.net.http.Http
import com.soywiz.korio.net.URL
import com.soywiz.korio.stream.*
import kotlin.concurrent.thread
import kotlin.jvm.JvmField
import kotlinx.coroutines.delay
import lupos.s00misc.*
import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.Coverage
import lupos.s00misc.EGraphOperationType
import lupos.s00misc.ELoggerType
import lupos.s00misc.GlobalLogger
import lupos.s00misc.parseFromXml
import lupos.s00misc.XMLElement
import lupos.s02buildSyntaxTree.rdf.Dictionary
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
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s09physicalOperators.noinput.*
import lupos.s09physicalOperators.noinput.POPEmptyRow
import lupos.s09physicalOperators.POPBase
import lupos.s14endpoint.*
import lupos.s14endpoint.Endpoint
import lupos.SparqlTestSuite


@UseExperimental(ExperimentalStdlibApi::class)
object EndpointClientImpl {
    @JvmField
    val client = createHttpClient()

    fun encodeParam(key: String, value: Any) = URL.encodeComponent(key) + "=" + URL.encodeComponent("" + value)
    suspend fun requestGetBytes(url: String): ByteArray {
        SanityCheck.check({ !url.startsWith("http://${endpointServer!!.fullname}") })
        var i = 0
        var res: HttpClient.Response
        while (true) {
            i++
            try {
                res = client.request(Http.Method.GET, url)
                break
            } catch (e: Throwable) {
                if (i > 100) {
                    throw e
                }
                delay(10)
            }
        }
        return res.readAllBytes()
    }

    suspend fun requestPostBytes(url: String, data: DynamicByteArray): ByteArray {
        SanityCheck.check({ !url.startsWith("http://${endpointServer!!.fullname}") })
        var i = 0
        var res: HttpClient.Response
        while (true) {
            i++
            try {
                res = client.request(Http.Method.POST, url, Http.Headers(), AsyncStream(MyDynamicByteArray(data)))
                break
            } catch (e: Throwable) {
                if (i > 100) {
                    throw e
                }
                delay(10)
            }
        }
        return res.readAllBytes()
    }

    suspend fun requestGetString(url: String): String {
        SanityCheck.check({ !url.startsWith("http://${endpointServer!!.fullname}") })
        var i = 0
        var res: HttpClient.Response
        while (true) {
            i++
            try {
                res = client.request(Http.Method.GET, url)
                break
            } catch (e: Throwable) {
                if (i > 100) {
                    throw e
                }
                delay(10)
            }
        }
        return res.readAllString()
    }

    suspend fun requestPostString(url: String, data: DynamicByteArray): String {
        SanityCheck.check({ !url.startsWith("http://${endpointServer!!.fullname}") })
        var i = 0
        var res: HttpClient.Response
        while (true) {
            i++
            try {
                res = client.request(Http.Method.POST, url, Http.Headers(), AsyncStream(MyDynamicByteArray(data)))
                break
            } catch (e: Throwable) {
                if (i > 100) {
                    throw e
                }
                delay(10)
            }
        }
        return res.readAllString()
    }

    suspend fun requestPostString(url: String, data: String): String {
        SanityCheck.check({ !url.startsWith("http://${endpointServer!!.fullname}") })
        var res: HttpClient.Response
        var i = 0
        while (true) {
            i++
            try {
                val a = AsyncStream(MyDynamicByteArray(data.encodeToByteArray()))
                res = client.request(Http.Method.POST, url, Http.Headers(), a)
                break
            } catch (e: Throwable) {
                if (i > 100) {
                    throw e
                }
                delay(10)
            }
        }
        return res.readAllString()
    }
}

class MyDynamicByteArray : AsyncStreamBase {
    @JvmField
    val data: DynamicByteArray

    constructor(data: DynamicByteArray) {
        this.data = data
        data.finish()
    }

    constructor(param: ByteArray) {
        data = DynamicByteArray(param)
        data.pos = param.size
    }

    override suspend fun read(position: Long, buffer: ByteArray, offset: Int, len: Int): Int {
        if (position > data.pos) {
            return 0
        }
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
