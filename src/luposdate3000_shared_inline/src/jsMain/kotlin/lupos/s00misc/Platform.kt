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
actual inline fun getBenchmarkHome():String{
return Platform.getEnv("LUPOS_BENCHMARK_HOME", "${Platform.getPathSeparator()}mnt")!!
}
actual inline fun getGradleCache():String{
return Platform.getEnv("LUPOS_GRADLE_CACHE","${Platform.getUserHome()}${Platform.getPathSeparator()}.gradle${Platform.getPathSeparator()}caches${Platform.getPathSeparator()}")!!
}
actual inline fun getMavenCache():String{
return Platform.getEnv("LUPOS_MAVEN_CACHE","${Platform.getUserHome()}${Platform.getPathSeparator()}.m2${Platform.getPathSeparator()}repository${Platform.getPathSeparator()}")!!
}
}
