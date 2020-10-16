package lupos.s00misc
object DateHelperRelative{
        inline fun markNow() = System.nanoTime()
        inline fun elapsedSeconds(marker: Long) = (System.nanoTime()-marker).toDouble() / 1_000_000_000.0
}
