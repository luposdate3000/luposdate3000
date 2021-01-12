package lupos.s00misc
import kotlin.jvm.JvmField
public object BinaryTestCaseOutputModeExt {
    public  val ASK_QUERY_RESULT: BinaryTestCaseOutputMode = BinaryTestCaseOutputMode(0)
    public  val MODIFY_RESULT: BinaryTestCaseOutputMode = BinaryTestCaseOutputMode(1)
    public  val SELECT_QUERY_RESULT: BinaryTestCaseOutputMode = BinaryTestCaseOutputMode(2)
    public  val SELECT_QUERY_RESULT_COUNT: BinaryTestCaseOutputMode = BinaryTestCaseOutputMode(3)
    public const val values_size: Int = 4
    @JvmField public val names: Array<String> = arrayOf(
        "ASK_QUERY_RESULT",
        "MODIFY_RESULT",
        "SELECT_QUERY_RESULT",
        "SELECT_QUERY_RESULT_COUNT",
    )
}
