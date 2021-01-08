package lupos.s00misc
internal expect object Crypto {
    internal fun md5(value: String): String
    internal fun sha256(value: String): String
    internal fun sha1(value: String): String
    internal fun uuid(): String
}
