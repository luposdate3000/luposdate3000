package lupos.s00misc

enum class EIndexPattern(val keyIndices: IntArray, val valueIndices: IntArray, val tripleIndicees: IntArray) {
    S_PO(intArrayOf(0), intArrayOf(1, 2), intArrayOf(0, 1, 2)),
    SP_O(intArrayOf(0, 1), intArrayOf(2), intArrayOf(0, 1, 2)),
    SPO(intArrayOf(0, 1, 2), intArrayOf(), intArrayOf(0, 1, 2)),
    S_OP(intArrayOf(0), intArrayOf(2, 1), intArrayOf(0, 2, 1)),
    SO_P(intArrayOf(0, 2), intArrayOf(1), intArrayOf(0, 2, 1)),
    SOP(intArrayOf(0, 2, 1), intArrayOf(), intArrayOf(0, 2, 1)),
    P_OS(intArrayOf(1), intArrayOf(2, 0), intArrayOf(1, 2, 0)),
    PO_S(intArrayOf(1, 2), intArrayOf(0), intArrayOf(1, 2, 0)),
    POS(intArrayOf(1, 2, 0), intArrayOf(), intArrayOf(1, 2, 0)),
    P_SO(intArrayOf(1), intArrayOf(0, 2), intArrayOf(1, 0, 2)),
    PS_O(intArrayOf(1, 0), intArrayOf(2), intArrayOf(1, 0, 2)),
    PSO(intArrayOf(1, 0, 2), intArrayOf(), intArrayOf(1, 0, 2)),
    O_SP(intArrayOf(2), intArrayOf(0, 1), intArrayOf(2, 0, 1)),
    OS_P(intArrayOf(2, 0), intArrayOf(1), intArrayOf(2, 0, 1)),
    OSP(intArrayOf(2, 0, 1), intArrayOf(), intArrayOf(2, 0, 1)),
    O_PS(intArrayOf(2), intArrayOf(1, 0), intArrayOf(2, 1, 0)),
    OP_S(intArrayOf(2, 1), intArrayOf(0), intArrayOf(2, 1, 0)),
    OPS(intArrayOf(2, 1, 0), intArrayOf(), intArrayOf(2, 1, 0))
}
