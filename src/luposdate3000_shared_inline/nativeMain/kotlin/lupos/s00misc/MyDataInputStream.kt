package lupos.s00misc

import lupos.s00misc.IMyInputStream
import lupos.s00misc.Parallel

internal actual class MyDataInputStream {
    inline actual fun readByte(): Byte = throw  NotImplementedException("MyDataOutputStream", "xyz not implemented")
    inline actual fun readInt(): Int = throw  NotImplementedException("MyDataOutputStream", "xyz not implemented")
    inline actual fun read(buf: ByteArray, off: Int, len: Int): Int = throw  NotImplementedException("MyDataOutputStream", "xyz not implemented")
}
