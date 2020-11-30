package lupos.s00misc

internal expect object Platform {
inline fun getEnv(key: String, default: String? = null): String?
    inline fun getUserHome(): String
    inline fun getPathSeparator(): String
    inline fun findNamedFileInDirectory(dir: String, name: String): List<String>
    inline fun getOperatingSystem(): EOperatingSystem
    inline fun getNullFileName(): String
inline fun getBenchmarkHome():String
inline fun getGradleCache():String
inline fun getMavenCache():String
inline fun getAvailableRam():Int
}
