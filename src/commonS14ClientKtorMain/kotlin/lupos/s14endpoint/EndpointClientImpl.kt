package lupos.s14endpoint

import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.client.features.*
import io.ktor.client.features.logging.*
import io.ktor.client.features.websocket.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.http.*
import io.ktor.http.cio.websocket.*
import io.ktor.http.content.*
import io.ktor.utils.io.*
import io.ktor.utils.io.charsets.*
import io.ktor.utils.io.core.*
import kotlin.jvm.JvmField
import kotlinx.coroutines.delay
import lupos.s00misc.*
import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.EGraphOperationType
import lupos.s00misc.ELoggerType
import lupos.s00misc.GlobalLogger
import lupos.s00misc.parseFromXml
import lupos.s00misc.SanityCheck
import lupos.s00misc.Trace
import lupos.s00misc.XMLElement
import lupos.s02buildSyntaxTree.rdf.Dictionary
import lupos.s03resultRepresentation.ResultRepresenationNetwork
import lupos.s03resultRepresentation.ResultSet
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s09physicalOperators.noinput.POPEmptyRow
import lupos.s09physicalOperators.noinput.POPImportFromXml
import lupos.s09physicalOperators.POPBase
import lupos.s14endpoint.*
import lupos.s14endpoint.Endpoint
import lupos.SparqlTestSuite


object EndpointClientImpl {
    @JvmField
    val client = HttpClient() {
        if (GlobalLogger.enabled.ordinal >= ELoggerType.DEBUG.ordinal)
            install(Logging) {
                logger = Logger.SIMPLE
                level = LogLevel.ALL
            }
    }

    fun encodeParam(name: String, value: Any) = listOf(name to ("" + value)).formUrlEncode()
    suspend fun requestGetBytes(url: String): ByteArray = Trace.trace({ "EndpointClientImpl.requestGetBytes" }, {
        SanityCheck.check({ !url.startsWith("http://${endpointServer!!.fullname}") })
        throw Exception("not implemented")
    })

    suspend fun requestPostBytes(url: String, data: DynamicByteArray): ByteArray = Trace.trace({ "EndpointClientImpl.requestPostBytes" }, {
        SanityCheck.check({ !url.startsWith("http://${endpointServer!!.fullname}") })
        throw Exception("not implemented")
    })

    suspend fun requestGetString(url: String): String = Trace.trace({ "EndpointClientImpl.requestGetString" }, {
        SanityCheck.check({ !url.startsWith("http://${endpointServer!!.fullname}") })
        throw Exception("not implemented")
    })

    suspend fun requestPostString(urlString: String, data: DynamicByteArray): String = Trace.trace({ "EndpointClientImpl.requestPostString" }, {
        SanityCheck.check({ !urlString.startsWith("http://${endpointServer!!.fullname}") })
        throw Exception("not implemented")
    })

    suspend fun requestPostString(urlString: String, data: String): String = Trace.trace({ "EndpointClientImpl.requestPostString2" }, {
        SanityCheck.check({ !urlString.startsWith("http://${endpointServer!!.fullname}") })
        return client.post<String> {
            url(Url(urlString))
            contentType(ContentType.Application.FormUrlEncoded.withCharset(Charsets.UTF_8))
            body = data
        }
    })
}

