package lupos.s04arithmetikOperators.noinput

import com.benasher44.uuid.uuid4
import kotlin.jvm.JvmField
import lupos.s00misc.EOperatorID
import lupos.s03resultRepresentation.*
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.ResultVektorRaw
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.ResultIterator

class AOPBuildInCallSTRUUID(query: Query) : AOPBase(query, EOperatorID.AOPBuildInCallSTRUUIDID, "AOPBuildInCallSTRUUID", arrayOf()) {
    override fun toSparql() = "STRUUID()"
    override fun equals(other: Any?): Boolean {
        if (other !is AOPBuildInCallSTRUUID)
            return false
        return true
    }

    override fun calculate(resultChunk: ResultVektorRaw): ResultVektorRaw {
        val rVektor = ResultVektorRaw(resultChunk.availableRead())
        for (i in 0 until resultChunk.availableRead())
            rVektor.data[i] = ValueSimpleLiteral("\"", "" + uuid4())
        return rVektor
    }

    override fun cloneOP() = this
}
