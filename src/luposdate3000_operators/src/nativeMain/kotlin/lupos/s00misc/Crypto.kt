package lupos.s00misc
internal actual object Crypto {
    internal actual fun md5(value: String): String = throw NotImplementedException("Crypto", "md5 not implemented")
    internal actual fun sha256(value: String): String = throw NotImplementedException("Crypto", "sha256 not implemented")
    internal actual fun sha1(value: String): String = throw NotImplementedException("Crypto", "sha1 not implemented")
    internal actual fun uuid(): String = throw NotImplementedException("Crypto", "uuid not implemented")
}
