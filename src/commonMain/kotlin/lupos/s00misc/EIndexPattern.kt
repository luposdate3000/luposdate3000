package lupos.s00misc

import kotlin.jvm.JvmField
import lupos.s00misc.Coverage
import lupos.s04logicalOperators.Query

enum class EIndexPattern(val keyIndices: Array<Int>, val valueIndices: Array<Int>) {
    S_0(arrayOf(0), arrayOf(1, 2)),
    P_0(arrayOf(1), arrayOf(0, 2)),
    O_0(arrayOf(2), arrayOf(0, 1)),
    S_1(arrayOf(0), arrayOf(2, 1)),
    P_1(arrayOf(1), arrayOf(2, 0)),
    O_1(arrayOf(2), arrayOf(1, 0)),
    SP(arrayOf(0, 1), arrayOf(2)),
    SO(arrayOf(0, 2), arrayOf(1)),
    PO(arrayOf(1, 2), arrayOf(0)),
    SPO(arrayOf(0, 1, 2), arrayOf<Int>())
}
