package lupos.s00misc
import kotlin.jvm.JvmField
public object ETripleIndexTypeExt {
    public const val ID_TRIPLE: ETripleIndexType = 0
    public const val PARTITION: ETripleIndexType = 1
    public const val values_size: Int = 2
    @JvmField public val names: Array<String> = arrayOf(
        "ID_TRIPLE",
        "PARTITION",
    )
}
