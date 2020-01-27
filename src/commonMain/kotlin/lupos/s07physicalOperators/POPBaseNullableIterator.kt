package lupos.s07physicalOperators

import lupos.s07physicalOperators.POPBase
import lupos.s06resultRepresentation.ResultSetIterator
import lupos.s03buildOperatorGraph.OPBase

import lupos.s06resultRepresentation.ResultRow


abstract class POPBaseNullableIterator : POPBase() {
    var tmpResult: ResultRow? = null
    abstract fun nnext(): ResultRow?
    override fun hasNext(): Boolean {
        if (tmpResult == null)
            tmpResult = nnext()
        return tmpResult != null

    }

    override fun next(): ResultRow {
        require(tmpResult != null)
        val res = tmpResult!!
        tmpResult = null
        return res
    }
}
