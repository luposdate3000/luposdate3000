package lupos.s04arithmetikOperators

import kotlin.jvm.JvmField
import lupos.s00misc.*
import lupos.s00misc.ThreadSafeMutableAny
import lupos.s02buildSyntaxTree.sparql1_1.Aggregation
import lupos.s03resultRepresentation.*
import lupos.s03resultRepresentation.ResultChunk
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04arithmetikOperators.ResultVektorRaw
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.ResultIterator


abstract class AOPAggregationBase(query: Query,
                                  operatorID: EOperatorID,
                                  classname: String,
                                  children: Array<OPBase>) : AOPBase(query, operatorID, classname, children) {
    @JvmField
    var a = ThreadSafeMutableAny<ValueDefinition>(ValueUndef())
    @JvmField
    var count = ThreadSafeMutableAny(0)

    abstract fun calculate(resultSet: ResultSet, resultRow: ResultRow)
}
