package lupos.s00misc
import kotlin.jvm.JvmField
import lupos.s04arithmetikOperators.AOPBase

import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query


inline fun resultFlowConsume(consumerv: () -> OPBase, producerv: () -> OPBase, action: () -> ResultChunk): ResultChunk {
    val res = action()
    println("consume :: ${consumerv().classname}@${consumerv().uuid} ${producerv().classname}@${producerv().uuid} ${res.availableWrite()} ${res.availableRead()} ${res}")
    return res
}

inline fun resultFlowProduce(producerv: () -> OPBase, action: () -> ResultChunk): ResultChunk {
    val res = action()
    println("produce ::  ${producerv().classname}@${producerv().uuid} ${res.availableWrite()} ${res.availableRead()} ${res}")
    return res
}

inline fun <T> resultFlow(inputv: () -> AOPBase, resultChunkv: () -> ResultChunk, resultSetv: () -> ResultSet, action: () -> T): T = action()
inline fun printAllMicroTest() {
}

fun updateAllMicroTest(testName: String, queryFile: String, success: Boolean) {
}

fun executeBinaryTests(folder: String) {
}
