package lupos.s00misc

internal actual object Configuration {
    actual fun getEnv(key: String, default: String?): String? {
	return default
    }
}
