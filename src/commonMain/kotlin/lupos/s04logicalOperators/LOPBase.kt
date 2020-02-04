package lupos.s04logicalOperators
import lupos.s00misc.classNameToString
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s04logicalOperators.OPBase
import lupos.s06resultRepresentation.ResultRow
import lupos.s06resultRepresentation.ResultSet



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
