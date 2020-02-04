package lupos.s01io.buffer

import java.io.File
import java.io.RandomAccessFile
import java.nio.channels.FileChannel
import java.nio.MappedByteBuffer
import lupos.s01io.buffer.Benchmark
import lupos.s01io.buffer.BufferManager
import lupos.s01io.buffer.ByteArrayPage
import lupos.s01io.buffer.Cache
import lupos.s01io.buffer.CachedFile
import lupos.s01io.buffer.CacheOfFiles
import lupos.s01io.buffer.MemoryAccess
import lupos.s01io.buffer.PageHelper


typealias Page = MappedByteBufferPage

inline fun createString(chars: CharArray): String = String(chars)

class MappedByteBufferPage(val buffer: MappedByteBuffer) {
    @JvmField
    // in JVM-environment: this does not generate any getter avoiding a virtual method call!
    var locked = 0

    constructor() : this(RandomAccessFile(File("tmp"), "rw")
            .getChannel()
            .map(FileChannel.MapMode.READ_ONLY, 0, 1)) {
        /* the initialization above is dummy code just to have a MappedByteBuffer as parameter
	 * for the standard constructor (which is necessary in seldom cases)
	 */
        throw
        Error("MappedByteBufferPage must not be initialized via the standard constructor...")
    }

    inline fun getInt(address: Long): Int = this.buffer.getInt(address.toInt())
    inline fun getByte(address: Long): Byte = this.buffer.get(address.toInt())
    inline fun putInt(address: Long, data: Int) {
        this.buffer.putInt(address.toInt(), data)
    }

    inline fun putByte(address: Long, data: Byte) {
        this.buffer.put(address.toInt(), data)
    }

    inline fun putString(address: Long, data: String): Long {
        val size = data.length
        this.putInt(address, size)
        var pos = address + 4
        for (i in 0 until size) {
            val strChar = data[i]
            this.putByte(pos, (strChar.toInt() and 0xFF00 shr 8).toByte())
            pos++
            this.putByte(pos, (strChar.toInt() and 0x00FF).toByte())
            pos++
        }
        return pos
    }

    inline fun getPageIndex(): Long = 0L
    inline fun lock() {
        this.locked++
    }

    inline fun unlock() {
        this.locked--
    }

    inline fun isLocked(): Boolean = (this.locked > 0)
    inline fun release() {
        // according to the standard documentation:
        // MappedByteBuffer is unmapped if garbage collected
        // according to e.g.: https://stackoverflow.com/questions/2972986
        // val cleaner = (buffer as DirectBuffer).cleaner()
        // cleaner.clean()
    }

    inline fun isModified() = false
    // the modification have already been automatically written back as it is a memory mapped file
}
