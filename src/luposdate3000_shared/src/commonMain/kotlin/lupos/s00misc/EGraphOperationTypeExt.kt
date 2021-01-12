package lupos.s00misc
import kotlin.jvm.JvmField
public object EGraphOperationTypeExt {
    public  val ADD: EGraphOperationType = EGraphOperationType(0)
    public  val CLEAR: EGraphOperationType = EGraphOperationType(1)
    public  val COPY: EGraphOperationType = EGraphOperationType(2)
    public  val CREATE: EGraphOperationType = EGraphOperationType(3)
    public  val DROP: EGraphOperationType = EGraphOperationType(4)
    public  val LOAD: EGraphOperationType = EGraphOperationType(5)
    public  val MOVE: EGraphOperationType = EGraphOperationType(6)
    public const val values_size: Int = 7
    @JvmField public val names: Array<String> = arrayOf(
        "ADD",
        "CLEAR",
        "COPY",
        "CREATE",
        "DROP",
        "LOAD",
        "MOVE",
    )
}
