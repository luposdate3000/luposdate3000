package lupos.s00misc

import kotlin.jvm.JvmField
import lupos.s03resultRepresentation.ResultChunk
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.ResultVektorRaw
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.ResultIterator


inline fun resultFlowConsume(consumerv: () -> OPBase, producerv: () -> OPBase, action: () -> ResultChunk): ResultChunk = action()

inline fun resultFlowProduce(producerv: () -> OPBase, action: () -> ResultChunk): ResultChunk = action()

inline fun <T> resultFlow(inputv: () -> AOPBase, resultChunkv: () -> ResultChunk, resultSetv: () -> ResultSet, action: () -> T): T = action()

inline fun printAllMicroTest() {
}

fun updateAllMicroTest(testName: String, queryFile: String, success: Boolean) {
}

