package lupos.misc

actual fun Throwable.kotlinStacktrace() {
	this.printStackTrace()
}
