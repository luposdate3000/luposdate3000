package lupos.s02buildSyntaxTree.sparql1_1
import kotlin.jvm.JvmField
public object AggregationExt {
    public  val AVG: Aggregation = Aggregation(0)
    public  val COUNT: Aggregation = Aggregation(1)
    public  val GROUP_CONCAT: Aggregation = Aggregation(2)
    public  val MAX: Aggregation = Aggregation(3)
    public  val MIN: Aggregation = Aggregation(4)
    public  val SAMPLE: Aggregation = Aggregation(5)
    public  val SUM: Aggregation = Aggregation(6)
    public const val values_size: Int = 7
    @JvmField public val names: Array<String> = arrayOf(
        "AVG",
        "COUNT",
        "GROUP_CONCAT",
        "MAX",
        "MIN",
        "SAMPLE",
        "SUM",
    )
}
