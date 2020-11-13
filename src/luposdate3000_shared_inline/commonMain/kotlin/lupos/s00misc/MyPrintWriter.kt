package lupos.s00misc

internal expect class MyPrintWriter(hasBuffer: Boolean = true) : IMyPrintWriter {

    override fun clearBuffer()
    override fun toString(): String
    override fun println(x: String)
    override fun print(x: String)
    override fun println(x: Boolean)
    override fun print(x: Boolean)
    override fun println(x: Int)
    override fun print(x: Int)
    override fun println(x: Double)
    override fun print(x: Double)
    override fun println()
    override fun close()
    override fun flush()
}
