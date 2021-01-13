package lupos.s00misc
import kotlin.jvm.JvmField
public object EGraphRefTypeExt {
    public const val AllGraphRef: EGraphRefType = 0
    public const val DefaultGraphRef: EGraphRefType = 1
    public const val IriGraphRef: EGraphRefType = 2
    public const val NamedGraphRef: EGraphRefType = 3
    public const val values_size: Int = 4
    @JvmField public val names: Array<String> = arrayOf(
        "AllGraphRef",
        "DefaultGraphRef",
        "IriGraphRef",
        "NamedGraphRef",
    )
}
