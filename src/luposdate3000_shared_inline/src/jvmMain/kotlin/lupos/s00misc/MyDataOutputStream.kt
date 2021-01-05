package lupos.s00misc
import java.io.DataOutputStream
internal actual class MyDataOutputStream {
    @JvmField public val it: DataOutputStream?
    constructor(it: DataOutputStream) {
        this.it = it
    }
    actual constructor() {
        it = null
    }
    internal actual inline fun writeInt(value: Int) = it!!.writeInt(value)
    internal actual inline fun write(buf: ByteArray, off: Int, len: Int) = it!!.write(buf, off, len)
    internal actual inline fun close() = it!!.close()
    internal actual inline fun flush() = it!!.flush()
}
