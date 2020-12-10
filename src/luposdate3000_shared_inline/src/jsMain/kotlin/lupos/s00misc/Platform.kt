package lupos.s00misc

internal actual object Platform {
    @JvmField
    val operatingSystem = EOperatingSystem.JS
    actual inline fun getOperatingSystem() = operatingSystem
    actual inline fun getUserHome() = throw Exception("not available on this platform")
    actual inline fun getPathSeparator() = throw Exception("not available on this platform")
    actual inline fun findNamedFileInDirectory(dir: String, name: String): List<String> = throw Exception("not available on this platform")
    actual inline fun getNullFileName(): String = nullFileName
    actual inline fun getEnv(key: String, default: String?): String? {
        return default
    }

    actual inline fun getBenchmarkHome(): String {
        return getEnv("LUPOS_BENCHMARK_HOME", "${getPathSeparator()}mnt")!!
    }

    actual inline fun getGradleCache(): String {
        return getEnv("LUPOS_GRADLE_CACHE", "${getUserHome()}${getPathSeparator()}.gradle${getPathSeparator()}caches${getPathSeparator()}")!!
    }

    actual inline fun getMavenCache(): String {
        return getEnv("LUPOS_MAVEN_CACHE", "${getUserHome()}${getPathSeparator()}.m2${getPathSeparator()}repository${getPathSeparator()}")!!
    }

    actual inline fun getAvailableRam(): Int {
        return getEnv("LUPOS_RAM", "60")!!.toInt()
    }
}
