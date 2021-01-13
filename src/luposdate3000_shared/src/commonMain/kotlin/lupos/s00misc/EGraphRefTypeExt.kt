package lupos.s00misc
import kotlin.jvm.JvmField
public object EGraphRefTypeExt {
    public val AllGraphRef: EGraphRefType = EGraphRefType(0)
    public val DefaultGraphRef: EGraphRefType = EGraphRefType(1)
    public val IriGraphRef: EGraphRefType = EGraphRefType(2)
    public val NamedGraphRef: EGraphRefType = EGraphRefType(3)
    public const val values_size: Int = 4
    @JvmField public val names: Array<String> = arrayOf(
        "AllGraphRef",
        "DefaultGraphRef",
        "IriGraphRef",
        "NamedGraphRef",
    )
}
