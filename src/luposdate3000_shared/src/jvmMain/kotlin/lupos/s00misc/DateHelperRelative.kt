package lupos.s00misc
actual object DateHelperRelative {
    actual fun markNow(): Long = System.nanoTime()
    actual fun elapsedSeconds(marker: Long): Double = (System.nanoTime() - marker).toDouble() / 1_000_000_000.0
}
