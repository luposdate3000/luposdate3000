package lupos.misc

actual fun Throwable.kotlinStacktrace() {
    println(this.toString())
}
