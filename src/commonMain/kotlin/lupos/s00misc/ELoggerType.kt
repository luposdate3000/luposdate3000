package lupos.s00misc

import kotlin.jvm.JvmField

import lupos.s04arithmetikOperators.ResultVektorRaw
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.ResultIterator

enum class ELoggerType {
    RELEASE,
    BINARY_ENCODING,
    TRACE_RESULT,
    TEST_RESULT,
    TEST_RESULT_DISABLED,
    TEST_DETAIL,
    DEBUG
}
