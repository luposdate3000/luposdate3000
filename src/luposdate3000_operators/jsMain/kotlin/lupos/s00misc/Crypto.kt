package lupos.s00misc

import com.soywiz.krypto.md5 as _md5
import com.soywiz.krypto.sha1 as _sha1
import com.soywiz.krypto.sha256 as _sha256

@UseExperimental(ExperimentalStdlibApi::class)
actual object Crypto {
    actual fun md5(value: String): String {
        return toHexString(value.encodeToByteArray()._md5())
    }

    actual fun sha256(value: String): String {
        return toHexString(value.encodeToByteArray()._sha256())
    }

    actual fun sha1(value: String): String {
        return toHexString(value.encodeToByteArray()._sha1())
    }

actual fun uuid(): String = throw  NotImplementedException("Crypto", "uuid not implemented")

    internal fun toHexString(data: ByteArray): String {
        val sb = StringBuilder()
        for (b in data) {
            val tmp = (b + 256) % 256
            if (tmp == 0) {
                sb.append("00")
            } else {
                sb.append(tmp.toString(16).padStart(2, '0'))
            }
        }
        return sb.toString()
    }
}
