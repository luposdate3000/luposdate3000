package lupos.s00misc
import kotlin.jvm.JvmField
import lupos.s00misc.UnreachableException
public object BinaryTestCaseOutputModeExt {
    public const val ASK_QUERY_RESULT : BinaryTestCaseOutputMode = 0
    public const val MODIFY_RESULT : BinaryTestCaseOutputMode = 1
    public const val SELECT_QUERY_RESULT : BinaryTestCaseOutputMode = 2
    public const val SELECT_QUERY_RESULT_COUNT : BinaryTestCaseOutputMode = 3
    @JvmField public val values : IntArray = IntArray(4){it}
    @JvmField public val names : Array<String> = arrayOf(
        "ASK_QUERY_RESULT",
        "MODIFY_RESULT",
        "SELECT_QUERY_RESULT",
        "SELECT_QUERY_RESULT_COUNT",
    )
}
