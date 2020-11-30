package lupos.s00misc

internal expect object Platform {
inline fun getEnv(key: String, default: String? = null): String?
    inline fun getUserHome(): String
    inline fun getPathSeparator(): String
    inline fun findNamedFileInDirectory(dir: String, name: String): List<String>
    inline fun getOperatingSystem(): EOperatingSystem
    inline fun getNullFileName(): String
}
fun Platform.getBenchmarkHome():String=Platform.getEnv("LUPOS_BENCHMARK_HOME", "${Platform.getPathSeparator()}mnt")!!
fun Platform.getGradleCache():String=Platform.getEnv("LUPOS_GRADLE_CACHE","${Platform.getUserHome()}${Platform.getPathSeparator()}.gradle${Platform.getPathSeparator()}caches${Platform.getPathSeparator()}")!!
fun Platform.getMavenCache():String=Platform.getEnv("LUPOS_MAVEN_CACHE","${Platform.getUserHome()}${Platform.getPathSeparator()}.m2${Platform.getPathSeparator()}repository${Platform.getPathSeparator()}")!!
