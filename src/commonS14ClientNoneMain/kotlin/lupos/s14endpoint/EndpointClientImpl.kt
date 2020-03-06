package lupos.s14endpoint

import kotlin.jvm.JvmField
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
    fun encodeParam(key: String, value: Any) = key + "=" + value
    suspend fun requestGetBytes(url: String): ByteArray = Trace.trace({ "P2P.retryRequestBytes" }, {
        return ByteArray(0)
    })

    suspend fun requestPostBytes(url: String, data: DynamicByteArray): ByteArray = Trace.trace({ "P2P.retryRequestBytes" }, {
        return ByteArray(0)
    })

    suspend fun requestGetString(url: String): String = Trace.trace({ "P2P.retryRequestString" }, {
        return ""
    })

    suspend fun requestPostString(url: String, data: DynamicByteArray): String = Trace.trace({ "P2P.retryRequestString" }, {
        return ""
    })

    suspend fun requestPostString(url: String, data: String): String = Trace.trace({ "P2P.retryRequestString" }, {
        return ""
    })
}
