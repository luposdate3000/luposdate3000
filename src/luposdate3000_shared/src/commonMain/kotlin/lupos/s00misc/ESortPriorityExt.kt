package lupos.s00misc
import kotlin.jvm.JvmField
import lupos.s00misc.UnreachableException
public object ESortPriorityExt {
    public const val ANY_PROVIDED_VARIABLE : ESortPriority = 0
    public const val BIND : ESortPriority = 1
    public const val GROUP : ESortPriority = 2
    public const val JOIN : ESortPriority = 3
    public const val MINUS : ESortPriority = 4
    public const val PREVENT_ANY : ESortPriority = 5
    public const val SAME_AS_CHILD : ESortPriority = 6
    public const val SORT : ESortPriority = 7
    public const val UNION : ESortPriority = 8
    @JvmField public val values = IntArray(9){it}
    @JvmField public val names = arrayOf(
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
