package lupos.s16network

import kotlin.jvm.JvmField
import kotlinx.coroutines.delay
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import lupos.s00misc.ByteArrayBuilder
import lupos.s00misc.ByteArrayRead
import lupos.s00misc.Coverage
import lupos.s00misc.EGraphOperationType
import lupos.s00misc.EIndexPattern
import lupos.s00misc.ELoggerType
import lupos.s00misc.EModifyType
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s00misc.GlobalLogger
import lupos.s00misc.SanityCheck
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.MyListValue
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.ValueDefinition
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.ColumnIteratorMultiValue
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s05tripleStore.PersistentStoreLocal
import lupos.s05tripleStore.TripleStoreBulkImport
import lupos.s05tripleStore.TripleStoreLocalBase
import lupos.s09physicalOperators.POPBase
import lupos.s15tripleStoreDistributed.*

object ServerCommunicationTransferParams {
    fun receiveParams(packet: ByteArrayRead, query: Query): Array<AOPBase> {
/*always assume SPO*/
        var paramsF = Array<Boolean>(3) { true }
        var paramsS = Array<String>(3) { "" }
        for (i in 0 until 3) {
            paramsF[i] = packet.readByte() != 0.toByte()
            paramsS[i] = packet.readString()
        }
        val params = Array<AOPBase>(3) {
            var res: AOPBase
            if (paramsF[it]) {
                res = AOPVariable(query, paramsS[it])
            } else {
                res = AOPConstant(query, ValueDefinition(paramsS[it]))
            }
            /*return*/res
        }
        return params
    }

    fun sendParams(builder: ByteArrayBuilder, params: Array<AOPBase>) {
/*always assume SPO*/
        for (i in 0 until 3) {
            val p = params[i]
            if (p is AOPVariable) {
                builder.writeByte(0x1)
                builder.writeString(p.name)
            } else {
                val q = p as AOPConstant
                builder.writeByte(0x0)
                builder.writeString(q.toSparql())
            }
        }
    }
}
