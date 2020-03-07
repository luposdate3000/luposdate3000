package lupos.s01io.buffer

import java.io.File
import java.io.RandomAccessFile
import java.lang.reflect.Method
import kotlin.jvm.JvmField
import lupos.s01io.buffer.CachedFile
import lupos.s04logicalOperators.Query
import sun.nio.ch.FileChannelImpl


typealias Page = UnsafePage

inline fun createString(chars: CharArray): String = String(chars)

// memory mapped file and unsafe api:
// http://nyeggen.com/post/2014-05-18-memory-mapping-%3E2gb-of-data-in-java/
// and slides comparing different ways:
// https://www.slideshare.net/AndreiPangin/do-we-need-unsafe-in-java

class CachedFile {
    @JvmField
    val file: RandomAccessFile
    @JvmField
    val PAGESIZE = 8 * 1024L

    constructor(filename: String) {
        val paths = filename.split("/")
        if (paths.size > 1) {
            val dirpath = paths.joinToString(separator = "/", limit = paths.size - 1)
            File(dirpath).mkdirs()
        }
        this.file = RandomAccessFile(File(filename), "rw")
    }

    companion object {
        @JvmField
                /*
                 * in this way no getter method is used for access to UNSAFE
                 * (i.e., for avoiding the costly call of a virtual function)
                 */
        val UNSAFE: sun.misc.Unsafe = initUnsafe()

        private fun initUnsafe(): sun.misc.Unsafe {
            var theUnsafe: Any? = null

            try {
                val uc = Class.forName("sun.misc.Unsafe")
                val f = uc.getDeclaredField("theUnsafe")
                f.isAccessible = true
                theUnsafe = f.get(uc)
            } catch (e: Exception) {
                if (theUnsafe == null) throw Error("Could not obtain access to sun.misc.Unsafe", e)
            }
            if (theUnsafe == null) throw Error("Could not obtain access to sun.misc.Unsafe")
            return theUnsafe as sun.misc.Unsafe
        }

        val mmap = getMethod(FileChannelImpl::class.javaObjectType,
                "map0",
                Int::class.javaPrimitiveType,
                Long::class.javaPrimitiveType,
                Long::class.javaPrimitiveType)
        val unmmap = getMethod(FileChannelImpl::class.javaObjectType,
                "unmap0",
                Long::class.javaPrimitiveType,
                Long::class.javaPrimitiveType)
        val BYTE_ARRAY_OFFSET = UNSAFE.arrayBaseOffset(ByteArray::class.javaObjectType)

        // Bundle reflection calls to get access to the given method
        @Throws(Exception::class)
        private fun getMethod(cls: Class<*>, name: String, vararg params: Class<*>?): Method {
            val m = cls.getDeclaredMethod(name, *params)
            m.isAccessible = true
            return m
        }
    }

    @Throws(Exception::class)
    fun mapAndGetOffset(pageOffset: Long): Long {
        // this.file.setLength(this.size)
        val endOffset = pageOffset + PAGESIZE
        if (this.file.length() < endOffset) {
            this.file.setLength(pageOffset)
        }
        val ch = this.file.getChannel()
        val result = mmap.invoke(ch, 1, pageOffset, PAGESIZE) as Long
        // ch.close()
        return result
    }

    inline fun close() {
        this.file.close()
    }

    inline fun get(address: Long): Page {
        val pageOffset = mapAndGetOffset(address)
        return UnsafePage(pageOffset, { unmmap.invoke(null, pageOffset, PAGESIZE) })
    }

    inline fun write(address: Long, page: Page) {
        // it is already written because technically it is a memory mapped file!
    }
}
