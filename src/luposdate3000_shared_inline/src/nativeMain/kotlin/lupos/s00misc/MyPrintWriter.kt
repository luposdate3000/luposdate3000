package lupos.modulename
import lupos.s00misc.NotImplementedException
import lupos.s00misc.IMyPrintWriter
internal actual class _MyPrintWriter : IMyPrintWriter {
    actual constructor(hasBuffer: Boolean)
    actual override fun clearBuffer(): Unit = throw NotImplementedException("MyPrintWriter", "xyz not implemented")
    actual override fun toString(): String = throw NotImplementedException("MyPrintWriter", "xyz not implemented")
    actual override fun println(x: String): Unit = throw NotImplementedException("MyPrintWriter", "xyz not implemented")
    actual override fun print(x: String): Unit = throw NotImplementedException("MyPrintWriter", "xyz not implemented")
    actual override fun println(x: Boolean): Unit = throw NotImplementedException("MyPrintWriter", "xyz not implemented")
    actual override fun print(x: Boolean): Unit = throw NotImplementedException("MyPrintWriter", "xyz not implemented")
    actual override fun println(x: Int): Unit = throw NotImplementedException("MyPrintWriter", "xyz not implemented")
    actual override fun print(x: Int): Unit = throw NotImplementedException("MyPrintWriter", "xyz not implemented")
    actual override fun println(x: Double): Unit = throw NotImplementedException("MyPrintWriter", "xyz not implemented")
    actual override fun print(x: Double): Unit = throw NotImplementedException("MyPrintWriter", "xyz not implemented")
    actual override fun println(): Unit = throw NotImplementedException("MyPrintWriter", "xyz not implemented")
    actual override fun close(): Unit = throw NotImplementedException("MyPrintWriter", "xyz not implemented")
    actual override fun flush(): Unit = throw NotImplementedException("MyPrintWriter", "xyz not implemented")
}
