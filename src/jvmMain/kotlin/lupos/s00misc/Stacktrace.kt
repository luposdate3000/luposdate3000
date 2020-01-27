package lupos.misc
import lupos.s00misc.*
import lupos.misc.*

fun Throwable.kotlinStacktrace() {
    println(this.printStackTrace())
}
