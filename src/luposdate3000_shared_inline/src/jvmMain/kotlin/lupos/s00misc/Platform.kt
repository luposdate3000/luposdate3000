package lupos.s00misc
import java.io.File
import kotlin.jvm.JvmName
internal actual object Platform {
    @JvmField
    val userHome: String = System.getProperty("user.home")
    @JvmField
    val operatingSystem = if (System.getProperty("os.name").contains("Windows")) EOperatingSystem.Windows else EOperatingSystem.Linux
    @JvmField
    val pathSepatator = if (operatingSystem == EOperatingSystem.Windows) "\\\\" else "/"
    @JvmField
    val nullFileName = if (operatingSystem == EOperatingSystem.Windows) "NUL" else "/dev/null"
    @JvmName("getOperatingSystem") internal actual inline fun getOperatingSystem() = operatingSystem
    @JvmName("getUserHome") internal actual inline fun getUserHome() = userHome
    @JvmName("getPathSeparator") internal actual inline fun getPathSeparator() = pathSepatator
    @JvmName("findNamedFileInDirectory") internal actual inline fun findNamedFileInDirectory(dir: String, name: String): List<String> {
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
    @JvmName("getNullFileName") internal actual inline fun getNullFileName(): String = nullFileName
    @JvmName("getEnv") internal actual inline fun getEnv(key: String, default: String?): String? {
        return System.getenv(key) ?: default
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
