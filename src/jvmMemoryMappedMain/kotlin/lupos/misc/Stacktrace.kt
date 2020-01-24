package lupos.misc

fun Throwable.kotlinStacktrace() {
    println(this.printStackTrace())
}
