package lupos.s00misc
import kotlin.jvm.JvmField
public object EIndexPatternHelper {
    @JvmField public val keyIndices: Array<IntArray> = Array(EIndexPatternExt.values_size) {
        when (it) {
EIndexPatternExt.S_PO -> intArrayOf(0)
EIndexPatternExt.SP_O -> intArrayOf(0, 1)
EIndexPatternExt.SPO -> intArrayOf(0, 1, 2)
EIndexPatternExt.S_OP -> intArrayOf(0)
EIndexPatternExt.SO_P -> intArrayOf(0, 2)
EIndexPatternExt.SOP -> intArrayOf(0, 2, 1)
EIndexPatternExt.P_OS -> intArrayOf(1)
EIndexPatternExt.PO_S -> intArrayOf(1, 2)
EIndexPatternExt.POS -> intArrayOf(1, 2, 0)
EIndexPatternExt.P_SO -> intArrayOf(1)
EIndexPatternExt.PS_O -> intArrayOf(1, 0)
EIndexPatternExt.PSO -> intArrayOf(1, 0, 2)
EIndexPatternExt.O_SP -> intArrayOf(2)
EIndexPatternExt.OS_P -> intArrayOf(2, 0)
EIndexPatternExt.OSP -> intArrayOf(2, 0, 1)
EIndexPatternExt.O_PS -> intArrayOf(2)
EIndexPatternExt.OP_S -> intArrayOf(2, 1)
EIndexPatternExt.OPS -> intArrayOf(2, 1, 0)
else -> throw UnreachableException()
        }
    }
    @JvmField public val valueIndices: Array<IntArray> = Array(EIndexPatternExt.values_size) {
        when (it) {
EIndexPatternExt.S_PO -> intArrayOf(1, 2)
EIndexPatternExt.SP_O -> intArrayOf(2)
EIndexPatternExt.SPO -> intArrayOf()
EIndexPatternExt.S_OP -> intArrayOf(2, 1)
EIndexPatternExt.SO_P -> intArrayOf(1)
EIndexPatternExt.SOP -> intArrayOf()
EIndexPatternExt.P_OS -> intArrayOf(2, 0)
EIndexPatternExt.PO_S -> intArrayOf(0)
EIndexPatternExt.POS -> intArrayOf()
EIndexPatternExt.P_SO -> intArrayOf(0, 2)
EIndexPatternExt.PS_O -> intArrayOf(2)
EIndexPatternExt.PSO -> intArrayOf()
EIndexPatternExt.O_SP -> intArrayOf(0, 1)
EIndexPatternExt.OS_P -> intArrayOf(1)
EIndexPatternExt.OSP -> intArrayOf()
EIndexPatternExt.O_PS -> intArrayOf(1, 0)
EIndexPatternExt.OP_S -> intArrayOf(0)
EIndexPatternExt.OPS -> intArrayOf()
else -> throw UnreachableException()
        }
    }
    @JvmField public val tripleIndicees: Array<IntArray> = Array(EIndexPatternExt.values_size) {
        when (it) {
EIndexPatternExt.S_PO -> intArrayOf(0, 1, 2)
EIndexPatternExt.SP_O -> intArrayOf(0, 1, 2)
EIndexPatternExt.SPO -> intArrayOf(0, 1, 2)
EIndexPatternExt.S_OP -> intArrayOf(0, 2, 1)
EIndexPatternExt.SO_P -> intArrayOf(0, 2, 1)
EIndexPatternExt.SOP -> intArrayOf(0, 2, 1)
EIndexPatternExt.P_OS -> intArrayOf(1, 2, 0)
EIndexPatternExt.PO_S -> intArrayOf(1, 2, 0)
EIndexPatternExt.POS -> intArrayOf(1, 2, 0)
EIndexPatternExt.P_SO -> intArrayOf(1, 0, 2)
EIndexPatternExt.PS_O -> intArrayOf(1, 0, 2)
EIndexPatternExt.PSO -> intArrayOf(1, 0, 2)
EIndexPatternExt.O_SP -> intArrayOf(2, 0, 1)
EIndexPatternExt.OS_P -> intArrayOf(2, 0, 1)
EIndexPatternExt.OSP -> intArrayOf(2, 0, 1)
EIndexPatternExt.O_PS -> intArrayOf(2, 1, 0)
EIndexPatternExt.OP_S -> intArrayOf(2, 1, 0)
EIndexPatternExt.OPS -> intArrayOf(2, 1, 0)
else -> throw UnreachableException()
        }
    }
}
