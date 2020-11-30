package lupos.s00misc


internal actual object Platform {

    @JvmField
    val operatingSystem = EOperatingSystem.JS


    actual inline fun getOperatingSystem() = operatingSystem
    actual inline fun getUserHome() = throw Exception("not available on this platform")
    actual inline fun getPathSeparator() = throw Exception("not available on this platform")
    actual inline fun findNamedFileInDirectory(dir: String, name: String): List<String> =throw Exception("not available on this platform")

    actual inline fun getNullFileName(): String = nullFileName
inline     actual fun getEnv(key: String, default: String?): String? {
return default
    }

}
