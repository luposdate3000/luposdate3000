package lupos.s00misc

internal actual object Configuration {
    fun getEnv(key: String, default: String? = null): String? {
        throw object : NotImplementedException("Configuration", "Configuration not implemented") {}
    }
}
