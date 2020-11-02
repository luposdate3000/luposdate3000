package lupos.s00misc
interface IMyPrintWriter{
    fun clearBuffer()
override    fun toString(): String
    fun println(x: String)
    fun print(x: String)
    fun println(x: Boolean)
    fun print(x: Boolean)
    fun println(x: Int)
    fun print(x: Int)
    fun println(x: Double)
    fun print(x: Double)
    fun println()
    fun close()
    fun flush()
}
