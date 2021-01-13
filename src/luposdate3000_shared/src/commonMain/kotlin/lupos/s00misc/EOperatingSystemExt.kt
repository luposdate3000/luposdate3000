package lupos.s00misc
import kotlin.jvm.JvmField
public object EOperatingSystemExt {
    public val JS: EOperatingSystem = EOperatingSystem(0)
    public val Linux: EOperatingSystem = EOperatingSystem(1)
    public val UNKNOWN: EOperatingSystem = EOperatingSystem(2)
    public val Windows: EOperatingSystem = EOperatingSystem(3)
    public const val values_size: Int = 4
    @JvmField public val names: Array<String> = arrayOf(
        "JS",
        "Linux",
        "UNKNOWN",
        "Windows",
    )
}
