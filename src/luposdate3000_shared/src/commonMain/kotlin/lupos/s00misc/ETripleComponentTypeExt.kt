package lupos.s00misc
import kotlin.jvm.JvmField
public object ETripleComponentTypeExt {
    public  val BLANK_NODE: ETripleComponentType = ETripleComponentType(0)
    public  val BOOLEAN: ETripleComponentType = ETripleComponentType(1)
    public  val DECIMAL: ETripleComponentType = ETripleComponentType(2)
    public  val DOUBLE: ETripleComponentType = ETripleComponentType(3)
    public  val INTEGER: ETripleComponentType = ETripleComponentType(4)
    public  val IRI: ETripleComponentType = ETripleComponentType(5)
    public  val STRING: ETripleComponentType = ETripleComponentType(6)
    public  val STRING_LANG: ETripleComponentType = ETripleComponentType(7)
    public  val STRING_TYPED: ETripleComponentType = ETripleComponentType(8)
    public const val values_size: Int = 9
    @JvmField public val names: Array<String> = arrayOf(
        "BLANK_NODE",
        "BOOLEAN",
        "DECIMAL",
        "DOUBLE",
        "INTEGER",
        "IRI",
        "STRING",
        "STRING_LANG",
        "STRING_TYPED",
    )
}
