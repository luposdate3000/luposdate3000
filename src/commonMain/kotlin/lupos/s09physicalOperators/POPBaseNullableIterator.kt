package lupos.s09physicalOperators

import lupos.s00misc.Trace
import lupos.s03resultRepresentation.ResultRow
import lupos.s04logicalOperators.OPBase
import lupos.s09physicalOperators.POPBase


abstract class POPBaseNullableIterator : POPBase() {
    var tmpResult: ResultRow? = null
    abstract fun nnext(): ResultRow?
    var tryNext = true
    override fun hasNext(): Boolean = Trace.trace({ "POPBaseNullableIterator.hasNext" }, {
        if (!tryNext)
            return false
        if (tmpResult == null)
            tmpResult = nnext()
        tryNext = tmpResult != null
        return tryNext
    }) as Boolean

    override fun next(): ResultRow = Trace.trace({ "POPBaseNullableIterator.next" }, {
        require(tmpResult != null)
        val res = tmpResult!!
        tmpResult = null
        return res
    }) as ResultRow
}
