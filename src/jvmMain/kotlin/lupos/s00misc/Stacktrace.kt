package lupos.misc

import lupos.s00misc.XMLElement

fun Throwable.kotlinStacktrace() {
    println(this.printStackTrace())
}
