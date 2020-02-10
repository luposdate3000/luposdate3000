package lupos.s00misc



fun Throwable.kotlinStacktrace() {
    println(this.printStackTrace())
}
