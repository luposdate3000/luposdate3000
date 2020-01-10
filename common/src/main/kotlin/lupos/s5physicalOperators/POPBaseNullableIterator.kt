package lupos.s5physicalOperators

import lupos.s2buildOperatorGraph.OPBase
import lupos.s4resultRepresentation.ResultSetIterator
import lupos.s4resultRepresentation.ResultRow


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
