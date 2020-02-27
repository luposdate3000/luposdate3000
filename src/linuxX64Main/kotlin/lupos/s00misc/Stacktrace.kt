package lupos.s00misc



actual fun Throwable.kotlinStacktrace() {
    this.printStackTrace()
}
