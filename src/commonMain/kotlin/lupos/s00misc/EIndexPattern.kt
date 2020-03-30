package lupos.s00misc
import kotlin.jvm.JvmField

import lupos.s04logicalOperators.Query


enum class EIndexPattern(val keyIndices: Array<Int>, val valueIndices: Array<Int>) {
    S(arrayOf(0), arrayOf(1, 2)),
    P(arrayOf(1), arrayOf(0, 2)),
    O(arrayOf(2), arrayOf(0, 1)),
    SP(arrayOf(0, 1), arrayOf(2)),
    SO(arrayOf(0, 2), arrayOf(1)),
    PO(arrayOf(1, 2), arrayOf(0)),
    SPO(arrayOf(0, 1, 2), arrayOf<Int>())
}
