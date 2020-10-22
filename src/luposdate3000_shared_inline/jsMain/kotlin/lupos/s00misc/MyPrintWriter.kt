package lupos.s00misc

import lupos.s00misc.IMyInputStream
import lupos.s00misc.Parallelinternal

actual class MyPrintWriter {
    actual fun clearBuffer(): Unit = throw  NotImplementedException("MyPrintWriter", "xyz not implemented")
    override actual fun toString(): String = throw  NotImplementedException("MyPrintWriter", "xyz not implemented")
    actual fun println(x: String): Unit = throw  NotImplementedException("MyPrintWriter", "xyz not implemented")
    actual fun print(x: String): Unit = throw  NotImplementedException("MyPrintWriter", "xyz not implemented")
    actual fun println(x: Boolean): Unit = throw  NotImplementedException("MyPrintWriter", "xyz not implemented")
    actual fun print(x: Boolean): Unit = throw  NotImplementedException("MyPrintWriter", "xyz not implemented")
    actual fun println(x: Int): Unit = throw  NotImplementedException("MyPrintWriter", "xyz not implemented")
    actual fun print(x: Int): Unit = throw  NotImplementedException("MyPrintWriter", "xyz not implemented")
    actual fun println(x: Double): Unit = throw  NotImplementedException("MyPrintWriter", "xyz not implemented")
    actual fun print(x: Double): Unit = throw  NotImplementedException("MyPrintWriter", "xyz not implemented")
    actual fun println(): Unit = throw  NotImplementedException("MyPrintWriter", "xyz not implemented")
    actual fun close(): Unit = throw  NotImplementedException("MyPrintWriter", "xyz not implemented")
    actual fun flush(): Unit = throw  NotImplementedException("MyPrintWriter", "xyz not implemented")
}
