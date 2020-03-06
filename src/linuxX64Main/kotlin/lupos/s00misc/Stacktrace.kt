package lupos.s00misc

import kotlin.jvm.JvmField


fun Throwable.kotlinStacktrace() {
    this.printStackTrace()
}
