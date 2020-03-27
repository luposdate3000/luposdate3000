package lupos.s04arithmetikOperators.noinput

import kotlin.jvm.JvmField
import lupos.s00misc.EOperatorID
import lupos.s00misc.ThreadSafeUuid
import lupos.s03resultRepresentation.*
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.ResultVektorRaw
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query


class AOPBuildInCallBNODE0(query: Query) : AOPBase(query, EOperatorID.AOPBuildInCallBNODE0ID, "AOPBuildInCallBNODE0", arrayOf()) {
    override fun toSparql() = "BNODE()"

    companion object {
        val localbnode = ThreadSafeUuid()
    }

    override fun equals(other: Any?): Boolean {
        return other is AOPBuildInCallBNODE0
    }

    override fun calculate(resultChunk: ResultVektorRaw): ResultVektorRaw {
        val rVektor = ResultVektorRaw(resultChunk.availableRead())
        for (i in 0 until resultChunk.availableRead())
            rVektor.data[i] = ValueBnode("" + uuid + localbnode.next())
        return rVektor
    }

    override fun cloneOP() = this
}
