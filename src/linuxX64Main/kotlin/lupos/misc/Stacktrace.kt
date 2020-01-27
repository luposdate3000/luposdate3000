package lupos.misc
import lupos.s00misc.ByteHelper

actual fun Throwable.kotlinStacktrace() {
    this.printStackTrace()
}
