package lupos.s00misc
public interface IMyPrintWriter {
    public fun clearBuffer()
    override public fun toString(): String
    public fun println(x: String)
    public fun print(x: String)
    public fun println(x: Boolean)
    public fun print(x: Boolean)
    public fun println(x: Int)
    public fun print(x: Int)
    public fun println(x: Double)
    public fun print(x: Double)
    public fun println()
    public fun close()
    public fun flush()
}
