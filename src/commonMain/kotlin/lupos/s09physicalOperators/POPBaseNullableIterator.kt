package lupos.s07physicalOperators
import lupos.s00misc.Trace
import lupos.s03resultRepresentation.ResultSetIterator
import lupos.s04logicalOperators.OPBase
import lupos.s06resultRepresentation.ResultRow
import lupos.s07physicalOperators.POPBase



abstract class POPBaseNullableIterator : POPBase() {
    var tmpResult: ResultRow? = null
    abstract fun nnext(): ResultRow?
    var tryNext = true
    override fun hasNext(): Boolean {
        try {
            Trace.start("POPBaseNullableIterator.hasNext")
            if (!tryNext)
                return false
            if (tmpResult == null)
                tmpResult = nnext()
            tryNext = tmpResult != null
            return tryNext
        } finally {
            Trace.stop("POPBaseNullableIterator.hasNext")
        }
    }

    override fun next(): ResultRow {
        try {
            Trace.start("POPBaseNullableIterator.next")
            require(tmpResult != null)
            val res = tmpResult!!
            tmpResult = null
            return res
        } finally {
            Trace.stop("POPBaseNullableIterator.next")
        }
    }
}
