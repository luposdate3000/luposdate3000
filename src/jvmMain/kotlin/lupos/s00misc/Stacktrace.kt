package lupos.s00misc
import lupos.s00misc.classNameToString

fun Throwable.kotlinStacktrace() {
    println(this.printStackTrace())
}
