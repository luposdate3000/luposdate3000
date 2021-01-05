package lupos.s00misc
public actual object DateHelperRelative {
  public   actual fun markNow(): Long = System.nanoTime()
public     actual fun elapsedSeconds(marker: Long): Double = (System.nanoTime() - marker).toDouble() / 1_000_000_000.0
}
