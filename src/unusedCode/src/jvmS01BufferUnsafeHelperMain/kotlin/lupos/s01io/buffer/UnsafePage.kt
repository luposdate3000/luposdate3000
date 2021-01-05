package lupos.s01io.buffer
import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
class UnsafePage {
    companion object {
        @JvmField
                /* in this way no getter method is used for access to UNSAFE
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
    }
    // this does not generate any setters/getters avoiding a virtual method call!
    @JvmField
    val basepointer: Long
    // this does not generate any getter avoiding a virtual method call!
    const
    val PAGESIZE = 8 * 1024L
    @JvmField
    var locked = 0
    @JvmField
    val cleaner: () -> Unit
    constructor() {
        this.basepointer = allocateMemory(this.PAGESIZE)
        cleaner = { this.freeMemory() }
    }
    /**
     * if the memory has already been allocated (e.g. by FileChannel)
     */
    constructor(allocatedMemoryPointer: Long, cleaner: () -> Unit) {
        this.basepointer = allocatedMemoryPointer
        this.cleaner = cleaner
    }
    @JvmName("getInt") internal inline fun getInt(address: Long): Int = UNSAFE.getInt(address)
    @JvmName("getByte") internal inline fun getByte(address: Long): Byte = UNSAFE.getByte(address)
    @JvmName("allocateMemory") internal inline fun allocateMemory(size: Long): Long {
        return UNSAFE.allocateMemory(size)
    }
    @JvmName("freeMemory") internal inline fun freeMemory() {
        UNSAFE.freeMemory(this.basepointer)
    }
    @JvmName("putInt") internal inline fun putInt(address: Long, data: Int) {
        UNSAFE.putInt(address, data)
    }
    @JvmName("putByte") internal inline fun putByte(address: Long, data: Byte) {
        UNSAFE.putByte(address, data)
    }
    @JvmName("putString") internal inline fun putString(address: Long, data: String): Long {
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
    @JvmName("getPageIndex") public internal inline fun getPageIndex(): Long {
        return this.basepointer
    }
    @JvmName("lock") internal inline fun lock() {
        this.locked++
    }
    @JvmName("unlock") internal inline fun unlock() {
        this.locked--
    }
    @JvmName("isLocked") internal inline fun isLocked(): Boolean {
        return this.locked > 0
    }
    @JvmName("release") internal inline fun release() {
        this.cleaner()
    }
    @JvmName("isModified") internal inline fun isModified() = false
    /*
	 * only used in main memory (-> no need for making it persistence)
	 * and for memory mapped files
	 * (-> modifications are automatically written back to file)
	 */
}
