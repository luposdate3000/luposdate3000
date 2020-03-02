package lupos.s04logicalOperators

import kotlinx.coroutines.channels.Channel
import lupos.s03resultRepresentation.*
import lupos.s03resultRepresentation.ResultSetDictionary


abstract class LOPBase : OPBase() {
    override val resultSet = ResultSet(ResultSetDictionary())
    override fun evaluate(): Channel<ResultRow> {
        throw Exception("not implemented $classname.evaluate")
    }
}
