package lupos.s00misc
import kotlin.jvm.JvmName
internal expect object Platform {
    @JvmName("getEnv") internal inline fun getEnv(key: String, default: String? = null): String?
    @JvmName("getUserHome") internal inline fun getUserHome(): String
    @JvmName("getPathSeparator") internal inline fun getPathSeparator(): String
    @JvmName("findNamedFileInDirectory") internal inline fun findNamedFileInDirectory(dir: String, name: String): List<String>
    @JvmName("getOperatingSystem") internal inline fun getOperatingSystem(): EOperatingSystem
    @JvmName("getNullFileName") internal inline fun getNullFileName(): String
    @JvmName("getBenchmarkHome") internal inline fun getBenchmarkHome(): String
    @JvmName("getGradleCache") internal inline fun getGradleCache(): String
    @JvmName("getMavenCache") internal inline fun getMavenCache(): String
    @JvmName("getAvailableRam") internal inline fun getAvailableRam(): Int
}
