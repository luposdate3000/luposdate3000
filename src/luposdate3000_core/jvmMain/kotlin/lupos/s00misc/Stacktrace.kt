package lupos.s00misc

fun Throwable.kotlinStacktrace() {
    SanityCheck.println { this.printStackTrace() }
}
