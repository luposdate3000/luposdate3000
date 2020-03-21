package lupos.s01io.buffer

import kotlin.jvm.JvmField
import lupos.s03resultRepresentation.ResultChunk
import lupos.s04arithmetikOperators.ResultVektorRaw
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.ResultIterator

data class PageAddress(@JvmField val fileName: String, @JvmField val pageNumber: Int)

val PAGESIZE: Int = 8 * 1024
val bufferManager: BufferManager = BufferManager()

class BufferManager {
    /**
     * the max. number of opened files
     */
    @JvmField
    val MAXPAGES = 10 // first like this, should be dependent on size of main memory in the used computer
    private val cache = LeastRecentlyUsed<PageAddress, Page?>(PageAddress("", -1), null, MAXPAGES)
    fun getPage(file: String, number: Int): Page = getPage(PageAddress(file, number))
    fun getPage(pageAddress: PageAddress): Page {
        val page = this.cache.getEntry(pageAddress)
        if (page == null) {
            while (this.cache.entries.size >= MAXPAGES) {
                val toBeReplaced = this.cache.replaceLeastRecentlyUsed({ it.value!!.isLocked() })
                val oldPage = toBeReplaced.value!!
                if (oldPage.isModified()) { // write modified pages to disk!
                    val oldPageAddress = toBeReplaced.key
                    CacheOfFiles.getFile(oldPageAddress.fileName).write(oldPageAddress.pageNumber.toLong() * PAGESIZE.toLong(), oldPage)
                }
                oldPage.release()
            }
            val page = CacheOfFiles.get(pageAddress.fileName, pageAddress.pageNumber.toLong() * PAGESIZE.toLong())
            val newPageEntry = CachedEntry<PageAddress, Page?>(pageAddress, page)
            this.cache.addNewEntry(newPageEntry)
            return page
        } else {
            this.cache.accessNow(page)
            return page.value!!
        }
    }

    fun writeAllModifiedPages() {
        for (entry in this.cache.entries) {
            val pageAddress = entry.key
            val page = entry.value.value
            if (page != null) {
                if (page.isModified()) {
                    CacheOfFiles.getFile(pageAddress.fileName).write(pageAddress.pageNumber.toLong() * PAGESIZE.toLong(), page)
                }
            }
        }
    }

    fun release() {
        for (entry in this.cache.entries) {
            val pageAddress = entry.key
            val page = entry.value.value
            if (page != null) {
                if (page.isModified()) {
                    CacheOfFiles.getFile(pageAddress.fileName).write(pageAddress.pageNumber.toLong() * PAGESIZE.toLong(), page)
                }
                page.release()
            }
        }
        this.cache.releaseAll()
    }
}
