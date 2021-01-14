package lupos.modulename
import java.io.DataOutputStream
import kotlin.jvm.JvmField
internal actual class _MyDataOutputStream {
    @JvmField internal val it: DataOutputStream?
    internal constructor(it: DataOutputStream) {
        this.it = it
    }
    internal actual constructor() {
        it = null
    }
    @Suppress("NOTHING_TO_INLINE") internal actual inline fun writeInt(value: Int): Unit = it!!.writeInt(value)
    @Suppress("NOTHING_TO_INLINE") internal actual inline fun write(buf: ByteArray, off: Int, len: Int): Unit = it!!.write(buf, off, len)
    @Suppress("NOTHING_TO_INLINE") internal actual inline fun close(): Unit = it!!.close()
    @Suppress("NOTHING_TO_INLINE") internal actual inline fun flush(): Unit = it!!.flush()
}
