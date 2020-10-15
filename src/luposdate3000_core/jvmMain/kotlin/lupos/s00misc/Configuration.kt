package lupos.s00misc

object Configuration {
    fun getEnv(key: String, default: String? = null): String? {
        return System.getenv(key) ?: default
    }
}
