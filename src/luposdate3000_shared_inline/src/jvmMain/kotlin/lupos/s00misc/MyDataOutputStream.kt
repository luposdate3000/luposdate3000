package lupos.s00misc
import java.io.DataOutputStream
internal actual class MyDataOutputStream {
    @JvmField val it: DataOutputStream?
    constructor(it: DataOutputStream) {
        this.it = it
    }
    actual constructor() {
        it = null
    }
    actual inline fun writeInt(value: Int) = it!!.writeInt(value)
    actual inline fun write(buf: ByteArray, off: Int, len: Int) = it!!.write(buf, off, len)
    actual inline fun close() = it!!.close()
    actual inline fun flush() = it!!.flush()
}
