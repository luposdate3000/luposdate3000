package lupos.s04arithmetikOperators

import kotlin.jvm.JvmField
import lupos.s00misc.*
import lupos.s00misc.ThreadSafeMutableAny
import lupos.s02buildSyntaxTree.sparql1_1.Aggregation
import lupos.s03resultRepresentation.*
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04logicalOperators.iterator.*
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query

abstract class AOPAggregationBase(query: Query,
                                  operatorID: EOperatorID,
                                  classname: String,
                                  children: Array<OPBase>) : AOPBase(query, operatorID, classname, children) {
    abstract fun createIterator(row: ColumnIteratorRow): ColumnIteratorAggregate
}
