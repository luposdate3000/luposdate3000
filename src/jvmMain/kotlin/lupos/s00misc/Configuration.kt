package lupos.s00misc

import lupos.s00misc.Coverage

object Configuration {
    fun getEnv(key: String, default: String? = null): String? {
        return System.getenv(key) ?: null
    }
}
