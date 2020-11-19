package lupos.s00misc

expect object DateHelperRelative {
    fun markNow(): Long
    fun elapsedSeconds(marker: Long): Double
}
