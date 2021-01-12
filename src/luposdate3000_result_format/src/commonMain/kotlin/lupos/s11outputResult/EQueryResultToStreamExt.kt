package lupos.s11outputResult
import kotlin.jvm.JvmField
public object EQueryResultToStreamExt {
    public const val DEFAULT_STREAM: EQueryResultToStream = 0
    public const val EMPTYDICTIONARY_STREAM: EQueryResultToStream = 1
    public const val EMPTY_STREAM: EQueryResultToStream = 2
    public const val MEMORY_TABLE: EQueryResultToStream = 3
    public const val XML_ELEMENT: EQueryResultToStream = 4
    public const val XML_STREAM: EQueryResultToStream = 5
    public const val values_size: Int = 6
    @JvmField public val names: Array<String> = arrayOf(
        "DEFAULT_STREAM",
        "EMPTYDICTIONARY_STREAM",
        "EMPTY_STREAM",
        "MEMORY_TABLE",
        "XML_ELEMENT",
        "XML_STREAM",
    )
}
