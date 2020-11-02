package lupos.s00misc

actual object DateHelperRelative {
    actual fun markNow() = System.nanoTime()
    actual fun elapsedSeconds(marker: Long) = (System.nanoTime() - marker).toDouble() / 1_000_000_000.0
}
