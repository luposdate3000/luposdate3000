package lupos.modulename
import lupos.s00misc.EOperatingSystem
import java.io.File
import kotlin.jvm.JvmField
internal actual object _Platform {
    @JvmField
    val userHome: String = System.getProperty("user.home")
    @JvmField
    val operatingSystem = if (System.getProperty("os.name").contains("Windows")) EOperatingSystem.Windows else EOperatingSystem.Linux
    @JvmField
    val pathSepatator = if (operatingSystem == EOperatingSystem.Windows) "\\\\" else "/"
    @JvmField
    val nullFileName = if (operatingSystem == EOperatingSystem.Windows) "NUL" else "/dev/null"
    @Suppress("NOTHING_TO_INLINE") internal actual inline fun getOperatingSystem() = operatingSystem
    @Suppress("NOTHING_TO_INLINE") internal actual inline fun getUserHome() = userHome
    @Suppress("NOTHING_TO_INLINE") internal actual inline fun getPathSeparator() = pathSepatator
    @Suppress("NOTHING_TO_INLINE") internal actual inline fun findNamedFileInDirectory(dir: String, name: String): List<String> {
        val res = mutableListOf<String>()
        for (f in File(dir).walk()) {
            if (f.isFile()) {
                if (f.getName() == name) {
                    res.add(f.toString())
                }
            }
        }
        return res
    }
    @Suppress("NOTHING_TO_INLINE") internal actual inline fun getNullFileName(): String = nullFileName
    @Suppress("NOTHING_TO_INLINE") internal actual inline fun getEnv(key: String, default: String?): String? {
        return System.getenv(key) ?: default
    }
    @Suppress("NOTHING_TO_INLINE") internal actual inline fun getBenchmarkHome(): String {
        return getEnv("LUPOS_BENCHMARK_HOME", "${getPathSeparator()}mnt")!!
    }
    @Suppress("NOTHING_TO_INLINE") internal actual inline fun getGradleCache(): String {
        return getEnv("LUPOS_GRADLE_CACHE", "${getUserHome()}${getPathSeparator()}.gradle${getPathSeparator()}caches${getPathSeparator()}")!!
    }
    @Suppress("NOTHING_TO_INLINE") internal actual inline fun getMavenCache(): String {
        return getEnv("LUPOS_MAVEN_CACHE", "${getUserHome()}${getPathSeparator()}.m2${getPathSeparator()}repository${getPathSeparator()}")!!
    }
    @Suppress("NOTHING_TO_INLINE") internal actual inline fun getAvailableRam(): Int {
        return getEnv("LUPOS_RAM", "60")!!.toInt()
    }
}
