package lupos.s16network

import lupos.s00misc.ByteArrayBuilder
import lupos.s00misc.ByteArrayRead
import lupos.s03resultRepresentation.ValueDefinition
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query

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
