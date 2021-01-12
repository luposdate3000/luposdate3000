package lupos.s02buildSyntaxTree.sparql1_1
import kotlin.jvm.JvmField
import lupos.s00misc.UnreachableException
public object AggregationExt {
    public const val AVG : Aggregation = 0
    public const val COUNT : Aggregation = 1
    public const val GROUP_CONCAT : Aggregation = 2
    public const val MAX : Aggregation = 3
    public const val MIN : Aggregation = 4
    public const val SAMPLE : Aggregation = 5
    public const val SUM : Aggregation = 6
    @JvmField public val values = IntArray(7){it}
    @JvmField public val names = arrayOf(
        "AVG",
        "COUNT",
        "GROUP_CONCAT",
        "MAX",
        "MIN",
        "SAMPLE",
        "SUM",
    )
}
