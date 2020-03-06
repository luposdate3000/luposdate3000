package lupos.s04arithmetikOperators

import kotlin.jvm.JvmField
import lupos.s00misc.resultFlow
import lupos.s00misc.ThreadSafeMutableAny
import lupos.s02buildSyntaxTree.sparql1_1.Aggregation
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04arithmetikOperators.noinput.AOPDecimal
import lupos.s04arithmetikOperators.noinput.AOPDouble
import lupos.s04arithmetikOperators.noinput.AOPInteger
import lupos.s04arithmetikOperators.noinput.AOPUndef
import lupos.s04logicalOperators.OPBase


abstract class AOPAggregationBase : AOPBase() {
    @JvmField
    var a = ThreadSafeMutableAny<AOPConstant?>(null)
    @JvmField
    var count = ThreadSafeMutableAny(0)
    @JvmField
    var collectMode = ThreadSafeMutableAny(true)
}
