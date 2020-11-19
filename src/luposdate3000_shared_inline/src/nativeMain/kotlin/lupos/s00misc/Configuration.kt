package lupos.s00misc

import kotlinx.cinterop.toKString
import platform.posix.getenv

internal actual object Configuration {
    actual fun getEnv(key: String, default: String?): String? {
        val tmp = getenv(key)?.toKString()
        if (tmp != null) {
            return tmp
        }
        return default
    }
}
