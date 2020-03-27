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

class AOPBuildInCallUUID(query: Query) : AOPBase(query, EOperatorID.AOPBuildInCallUUIDID, "AOPBuildInCallUUID", arrayOf()) {
    override fun toSparql() = "UUID()"
    override fun equals(other: Any?): Boolean {
        if (other !is AOPBuildInCallUUID)
            return false
        return children[0] == other.children[0]
    }

    override fun calculate(resultChunk: ResultVektorRaw): ResultVektorRaw {
        val rVektor = ResultVektorRaw(resultChunk.availableRead())
        for (i in 0 until resultChunk.availableRead())
            rVektor.data[i] = ValueIri("urn:uuid:" + uuid4())
        return rVektor
    }

    override fun cloneOP() = this
}
