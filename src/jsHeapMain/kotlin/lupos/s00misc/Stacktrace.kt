package lupos.s00misc

fun Throwable.kotlinStacktrace() {
    printStackTrace()
    SanityCheck.println { this }
}
