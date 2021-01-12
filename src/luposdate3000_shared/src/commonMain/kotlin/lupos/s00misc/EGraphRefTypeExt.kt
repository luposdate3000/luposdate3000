package lupos.s00misc
import kotlin.jvm.JvmField
import lupos.s00misc.UnreachableException
public object EGraphRefTypeExt {
    public const val AllGraphRef : EGraphRefType = 0
    public const val DefaultGraphRef : EGraphRefType = 1
    public const val IriGraphRef : EGraphRefType = 2
    public const val NamedGraphRef : EGraphRefType = 3
    @JvmField public val values : IntArray = IntArray(4){it}
    @JvmField public val names : Array<String> = arrayOf(
        "AllGraphRef",
        "DefaultGraphRef",
        "IriGraphRef",
        "NamedGraphRef",
    )
}
