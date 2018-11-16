package lupos.io.buffer

data class PageAddress(val fileName: String, val pageNumber:Int)

val PAGESIZE:Int = 8 * 1024

expect class BufferManager {
    constructor()
    fun getPage(file: String, number:Int):Page
    fun getPage(pageAddress: PageAddress):Page
    fun writeAllModifiedPages()
    fun release()
}

val bufferManager: BufferManager = BufferManager()