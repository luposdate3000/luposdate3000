package lupos.s11outputResult
import kotlin.jvm.JvmField
public object EQueryResultToStreamExt {
    public val DEFAULT_STREAM: EQueryResultToStream = EQueryResultToStream(0)
    public val EMPTYDICTIONARY_STREAM: EQueryResultToStream = EQueryResultToStream(1)
    public val EMPTY_STREAM: EQueryResultToStream = EQueryResultToStream(2)
    public val MEMORY_TABLE: EQueryResultToStream = EQueryResultToStream(3)
    public val XML_ELEMENT: EQueryResultToStream = EQueryResultToStream(4)
    public val XML_STREAM: EQueryResultToStream = EQueryResultToStream(5)
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
