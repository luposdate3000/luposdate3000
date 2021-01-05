package lupos.s00misc
import kotlinx.cinterop.toKString
import platform.posix.getenv
import kotlin.jvm.JvmName
internal actual object Platform {
    val operatingSystem = EOperatingSystem.UNKNOWN
    @JvmName("getOperatingSystem") internal actual inline fun getOperatingSystem() = operatingSystem
    @JvmName("getUserHome") internal actual inline fun getUserHome(): String = throw Exception("not available on this platform")
    @JvmName("getPathSeparator") internal actual inline fun getPathSeparator(): String = throw Exception("not available on this platform")
    @JvmName("findNamedFileInDirectory") internal actual inline fun findNamedFileInDirectory(dir: String, name: String): List<String> = throw Exception("not available on this platform")
    @JvmName("getNullFileName") internal actual inline fun getNullFileName(): String = "/dev/null"
    @JvmName("getEnv") internal actual inline fun getEnv(key: String, default: String?): String? {
        val tmp = getenv(key)?.toKString()
        if (tmp != null) {
            return tmp
        }
        return default
    }
    @JvmName("getBenchmarkHome") internal actual inline fun getBenchmarkHome(): String {
        return getEnv("LUPOS_BENCHMARK_HOME", "${getPathSeparator()}mnt")!!
    }
    @JvmName("getGradleCache") internal actual inline fun getGradleCache(): String {
        return getEnv("LUPOS_GRADLE_CACHE", "${getUserHome()}${getPathSeparator()}.gradle${getPathSeparator()}caches${getPathSeparator()}")!!
    }
    @JvmName("getMavenCache") internal actual inline fun getMavenCache(): String {
        return getEnv("LUPOS_MAVEN_CACHE", "${getUserHome()}${getPathSeparator()}.m2${getPathSeparator()}repository${getPathSeparator()}")!!
    }
    @JvmName("getAvailableRam") internal actual inline fun getAvailableRam(): Int {
        return getEnv("LUPOS_RAM", "60")!!.toInt()
    }
}
