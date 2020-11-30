package lupos.s00misc

import java.io.File

internal actual object Platform {
    @JvmField
    val userHome: String = System.getProperty("user.home")

    @JvmField
    val operatingSystem = if (System.getProperty("os.name").contains("Windows")) EOperatingSystem.Windows else EOperatingSystem.Linux

    @JvmField
    val pathSepatator = if (operatingSystem == EOperatingSystem.Windows) "\\\\" else "/"

    @JvmField
    val nullFileName = if (operatingSystem == EOperatingSystem.Windows) "NUL" else "/dev/null"
    actual inline fun getOperatingSystem() = operatingSystem
    actual inline fun getUserHome() = userHome
    actual inline fun getPathSeparator() = pathSepatator
    actual inline fun findNamedFileInDirectory(dir: String, name: String): List<String> {
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

    actual inline fun getNullFileName(): String = nullFileName
inline     actual fun getEnv(key: String, default: String?): String? {
        return System.getenv(key) ?: default
    }
actual inline fun getBenchmarkHome():String{
return getEnv("LUPOS_BENCHMARK_HOME", "${getPathSeparator()}mnt")!!
}
actual inline fun getGradleCache():String{
return getEnv("LUPOS_GRADLE_CACHE","${getUserHome()}${getPathSeparator()}.gradle${getPathSeparator()}caches${getPathSeparator()}")!!
}
actual inline fun getMavenCache():String{
return getEnv("LUPOS_MAVEN_CACHE","${getUserHome()}${getPathSeparator()}.m2${getPathSeparator()}repository${getPathSeparator()}")!!
}
actual inline fun getAvailableRam():Int{  
return getEnv("LUPOS_RAM","60").toInt()
}
}
