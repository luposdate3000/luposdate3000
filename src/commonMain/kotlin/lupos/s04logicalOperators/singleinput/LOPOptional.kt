package lupos.s04logicalOperators.singleinput

import kotlin.jvm.JvmField
import lupos.s00misc.EOperatorID
import lupos.s03resultRepresentation.ResultChunk
import lupos.s04arithmetikOperators.ResultVektorRaw
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.ResultIterator


class LOPOptional(query: Query, child: OPBase = OPNothing(query)) : LOPBase(query, EOperatorID.LOPOptionalID, "LOPOptional", arrayOf(child)) {

    override fun equals(other: Any?): Boolean {
        if (other !is LOPOptional)
            return false
        for (i in children.indices) {
            if (children[i] != other.children[i])
                return false
        }
        return true
    }

    override fun cloneOP() = LOPOptional(query, children[0].cloneOP())
}
