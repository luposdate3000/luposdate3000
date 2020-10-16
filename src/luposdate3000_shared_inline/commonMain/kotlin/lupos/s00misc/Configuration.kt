package lupos.s00misc

internal expect object Configuration {
    fun getEnv(key: String, default: String? = null): String?
}
