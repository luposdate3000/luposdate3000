package lupos.s03buildOperatorGraph

import lupos.s00misc.classNameToString
import lupos.s03buildOperatorGraph.OPBase
import lupos.s06resultRepresentation.*


abstract class LOPBase : OPBase() {
    override fun getResultSet(): ResultSet {
        return ResultSet()
    }

    override fun next(): ResultRow {
        throw Exception("${classNameToString(this)}.next must not be called")
    }

    override fun hasNext(): Boolean {
        throw Exception("${classNameToString(this)}.hasNext must not be called")
    }
}
