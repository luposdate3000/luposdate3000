package lupos.s00misc
public actual object Crypto {
    public actual fun md5(value: String): String = throw NotImplementedException("Crypto", "md5 not implemented")
    public actual fun sha256(value: String): String = throw NotImplementedException("Crypto", "sha256 not implemented")
    public actual fun sha1(value: String): String = throw NotImplementedException("Crypto", "sha1 not implemented")
    public actual fun uuid(): String = throw NotImplementedException("Crypto", "uuid not implemented")
}
