package lupos.s04arithmetikOperators

import kotlin.jvm.JvmField
import lupos.s00misc.EOperatorID
import lupos.s03resultRepresentation.*
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.ResultIterator


abstract class AOPBase(query: Query,
                       operatorID: EOperatorID,
                       classname: String,
                       children: Array<OPBase>) :
        OPBase(query, operatorID, classname, ResultSet(ResultSetDictionary()), children) {
    abstract fun calculate(resultSet: ResultSet, resultRow: ResultRow): ValueDefinition
}
