package lupos.io.buffer

data class PageAddress(val fileName: String, val pageNumber: Int)

val PAGESIZE: Int = 8 * 1024

val bufferManager: BufferManager = BufferManager()
