package lupos.s00misc
import com.soywiz.krypto.md5 as _md5
import com.soywiz.krypto.sha1 as _sha1
import com.soywiz.krypto.sha256 as _sha256
@OptIn(ExperimentalStdlibApi::class)
public actual object Crypto {
    public actual fun md5(value: String): String {
        return toHexString(value.encodeToByteArray()._md5())
    }
    public actual fun sha256(value: String): String {
        return toHexString(value.encodeToByteArray()._sha256())
    }
    public actual fun sha1(value: String): String {
        return toHexString(value.encodeToByteArray()._sha1())
    }
    public actual fun uuid(): String = throw NotImplementedException("Crypto", "uuid not implemented")
    private fun toHexString(data: ByteArray): String {
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
