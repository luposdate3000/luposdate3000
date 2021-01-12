package lupos.s00misc
import kotlin.jvm.JvmField
public object EOperatingSystemExt {
    public const val JS: EOperatingSystem = 0
    public const val Linux: EOperatingSystem = 1
    public const val UNKNOWN: EOperatingSystem = 2
    public const val Windows: EOperatingSystem = 3
    public const val values_size: Int = 4
    @JvmField public val names: Array<String> = arrayOf(
        "JS",
        "Linux",
        "UNKNOWN",
        "Windows",
    )
}
