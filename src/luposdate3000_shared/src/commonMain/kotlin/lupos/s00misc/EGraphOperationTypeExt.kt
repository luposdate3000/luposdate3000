package lupos.s00misc
import kotlin.jvm.JvmField
import lupos.s00misc.UnreachableException
public object EGraphOperationTypeExt {
    public const val ADD : EGraphOperationType = 0
    public const val CLEAR : EGraphOperationType = 1
    public const val COPY : EGraphOperationType = 2
    public const val CREATE : EGraphOperationType = 3
    public const val DROP : EGraphOperationType = 4
    public const val LOAD : EGraphOperationType = 5
    public const val MOVE : EGraphOperationType = 6
    @JvmField public val values = IntArray(7){it}
    @JvmField public val names = arrayOf(
        "ADD",
        "CLEAR",
        "COPY",
        "CREATE",
        "DROP",
        "LOAD",
        "MOVE",
    )
}
