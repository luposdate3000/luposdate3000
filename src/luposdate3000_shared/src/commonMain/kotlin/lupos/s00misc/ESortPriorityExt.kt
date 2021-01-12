package lupos.s00misc
import kotlin.jvm.JvmField
public object ESortPriorityExt {
    public  val ANY_PROVIDED_VARIABLE: ESortPriority = ESortPriority(0)
    public  val BIND: ESortPriority = ESortPriority(1)
    public  val GROUP: ESortPriority = ESortPriority(2)
    public  val JOIN: ESortPriority = ESortPriority(3)
    public  val MINUS: ESortPriority = ESortPriority(4)
    public  val PREVENT_ANY: ESortPriority = ESortPriority(5)
    public  val SAME_AS_CHILD: ESortPriority = ESortPriority(6)
    public  val SORT: ESortPriority = ESortPriority(7)
    public  val UNION: ESortPriority = ESortPriority(8)
    public const val values_size: Int = 9
    @JvmField public val names: Array<String> = arrayOf(
        "ANY_PROVIDED_VARIABLE",
        "BIND",
        "GROUP",
        "JOIN",
        "MINUS",
        "PREVENT_ANY",
        "SAME_AS_CHILD",
        "SORT",
        "UNION",
    )
}
