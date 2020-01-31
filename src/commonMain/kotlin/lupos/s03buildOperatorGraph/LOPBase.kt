package lupos.s03buildOperatorGraph

import lupos.s03buildOperatorGraph.OPBase
import lupos.s06resultRepresentation.*


abstract class LOPBase : OPBase() {
    override fun getResultSet(): ResultSet {
        return ResultSet()
    }

    override fun next(): ResultRow {
        throw Exception("LOPBase.next must not be called")
    }

    override fun hasNext(): Boolean {
        throw Exception("LOPBase.hasNext must not be called")
    }
}
