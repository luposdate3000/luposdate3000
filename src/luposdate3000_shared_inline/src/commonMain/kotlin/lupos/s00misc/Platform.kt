package lupos.modulename
import lupos.s00misc.EOperatingSystem
import lupos.s00misc.EOperatingSystemExt
internal expect object _Platform {
    internal inline fun getEnv(key: String, default: String? = null): String?
    internal inline fun getUserHome(): String
    internal inline fun getPathSeparator(): String
    internal inline fun findNamedFileInDirectory(dir: String, name: String): List<String>
    internal inline fun getOperatingSystem(): EOperatingSystem
    internal inline fun getNullFileName(): String
    internal inline fun getBenchmarkHome(): String
    internal inline fun getGradleCache(): String
    internal inline fun getMavenCache(): String
    internal inline fun getAvailableRam(): Int
}
