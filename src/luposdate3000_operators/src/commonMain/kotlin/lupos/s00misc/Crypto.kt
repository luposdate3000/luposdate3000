package lupos.s00misc
public expect object Crypto {
    public fun md5(value: String): String
    public fun sha256(value: String): String
    public fun sha1(value: String): String
    public fun uuid(): String
}
