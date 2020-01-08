package lupos.io.buffer

expect class Page {
    constructor()

    inline fun getInt(address: Long): Int
    inline fun getByte(address: Long): Byte
    inline fun putInt(address: Long, data: Int)
    inline fun putByte(address: Long, data: Byte)
    inline fun putString(address: Long, data: String): Long // avoid using this method and intermediate strings and operate directly in the pages!
    inline fun getPageIndex(): Long
    inline fun lock()
    inline fun unlock()
    inline fun isLocked(): Boolean
    inline fun release()
    inline fun isModified(): Boolean
}
