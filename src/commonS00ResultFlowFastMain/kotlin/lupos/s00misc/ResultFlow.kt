package lupos.s00misc

import kotlin.jvm.JvmField
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.ResultIterator


inline fun resultFlowConsume(consumerv: () -> OPBase, producerv: () -> OPBase, action: () -> ResultRow): ResultRow = action()

inline fun resultFlowProduce(producerv: () -> OPBase, action: () -> ResultRow): ResultRow = action()

inline fun <T> resultFlow(inputv: () -> AOPBase, resultRowv: () -> ResultRow, resultSetv: () -> ResultSet, action: () -> T): T = action()

inline fun printAllMicroTest() {
}

fun updateAllMicroTest(testName: String, queryFile: String, success: Boolean) {
}

fun executeBinaryTests(folder: String) {
}
