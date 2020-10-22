package lupos.s04logicalOperators.iterator

import kotlin.jvm.JvmField
import lupos.s00misc.IteratorBundleColumnModeNotImplementedException
import lupos.s00misc.IteratorBundleRowModeNotImplementedException
import lupos.s00misc.SanityCheck

internal enum class IteratorBundleMode {
    COUNT,
    COLUMN,
    ROW
}
