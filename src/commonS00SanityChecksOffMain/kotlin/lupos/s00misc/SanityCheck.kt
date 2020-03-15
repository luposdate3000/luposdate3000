package lupos.s00misc

import lupos.s03resultRepresentation.ResultChunk
import lupos.s04arithmetikOperators.ResultVektorRaw
import lupos.s04logicalOperators.ResultIterator


object SanityCheck {
    inline fun <T> helper(action: () -> Unit): T? {
        return null
    }

    inline fun check(value: () -> Boolean) {}
    inline fun checkFalse(value: () -> Boolean) {}
    inline fun <T> checkEQ(a: () -> T, b: () -> T) {}
    inline fun <T> checkNEQ(a: () -> T, b: () -> T) {}
    inline fun <T> checkNULL(value: () -> T) {}
    inline fun <T> checkNNULL(value: () -> T) {}
    inline fun checkUnreachable(): Nothing = throw Exception("this should be unreachable")
}
