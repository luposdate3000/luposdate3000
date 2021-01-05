package lupos.s00misc
import java.io.DataOutputStream
import kotlin.jvm.JvmName
internal actual class MyDataOutputStream {
    @JvmField val it: DataOutputStream?
    constructor(it: DataOutputStream) {
        this.it = it
    }
    actual constructor() {
        it = null
    }
    @JvmName("writeInt") internal actual inline fun writeInt(value: Int) = it!!.writeInt(value)
    @JvmName("write") internal actual inline fun write(buf: ByteArray, off: Int, len: Int) = it!!.write(buf, off, len)
    @JvmName("close") internal actual inline fun close() = it!!.close()
    @JvmName("flush") internal actual inline fun flush() = it!!.flush()
}
