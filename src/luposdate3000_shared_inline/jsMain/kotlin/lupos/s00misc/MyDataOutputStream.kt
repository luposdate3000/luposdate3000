package lupos.s00misc

import lupos.s00misc.IMyInputStream
import lupos.s00misc.Parallel

internal actual class MyDataOutputStream {
    inline actual fun writeInt(value: Int): Unit = throw  NotImplementedException("MyDataInputStream", "xyz not implemented")
    inline actual fun write(buf: ByteArray, off: Int, len: Int): Unit = throw  NotImplementedException("MyDataInputStream", "xyz not implemented")
}
