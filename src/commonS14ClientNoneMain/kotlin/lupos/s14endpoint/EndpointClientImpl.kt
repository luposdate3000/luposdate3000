package lupos.s14endpoint
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
import lupos.s09physicalOperators.noinput.POPEmptyRow
import lupos.s09physicalOperators.POPBase
import lupos.s14endpoint.*
import lupos.s14endpoint.Endpoint
import lupos.SparqlTestSuite


object EndpointClientImpl {
    fun encodeParam(key: String, value: Any) = key + "=" + value
    suspend fun requestGetBytes(url: String): ByteArray {
        return ByteArray(0)
    }

    suspend fun requestPostBytes(url: String, data: DynamicByteArray): ByteArray {
        return ByteArray(0)
    }

    suspend fun requestGetString(url: String): String {
        return ""
    }

    suspend fun requestPostString(url: String, data: DynamicByteArray): String {
        return ""
    }

    suspend fun requestPostString(url: String, data: String): String {
        return ""
    }
}
