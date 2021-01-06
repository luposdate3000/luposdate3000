package lupos.s00misc
public actual object Crypto {
    actual public fun md5(value: String): String = throw NotImplementedException("Crypto", "md5 not implemented")
    actual public fun sha256(value: String): String = throw NotImplementedException("Crypto", "sha256 not implemented")
    actual public fun sha1(value: String): String = throw NotImplementedException("Crypto", "sha1 not implemented")
    actual public fun uuid(): String = throw NotImplementedException("Crypto", "uuid not implemented")
}
