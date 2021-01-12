package lupos.s00misc
import kotlin.jvm.JvmField
public object ETripleIndexTypeExt {
    public  val ID_TRIPLE: ETripleIndexType = ETripleIndexType(0)
    public  val PARTITION: ETripleIndexType = ETripleIndexType(1)
    public const val values_size: Int = 2
    @JvmField public val names: Array<String> = arrayOf(
        "ID_TRIPLE",
        "PARTITION",
    )
}
