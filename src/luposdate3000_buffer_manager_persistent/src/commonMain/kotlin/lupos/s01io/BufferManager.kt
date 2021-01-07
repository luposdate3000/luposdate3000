package lupos.s01io
public expect class BufferManager {
    internal constructor(name: String)
    /*suspend*/ public fun clear()
    public fun releasePage(pageid: Int)
    public fun getPage(pageid: Int): ByteArray
    /*suspend*/ public fun createPage(action: (ByteArray, Int) -> Unit)
    /*suspend*/ public fun deletePage(pageid: Int)
    public fun flushPage(pageid: Int)
}
