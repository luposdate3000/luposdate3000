package lupos.s01io.buffer

import kotlin.jvm.JvmField
import lupos.s01io.buffer.*
import lupos.s03resultRepresentation.ResultChunk
import lupos.s04arithmetikOperators.ResultVektorRaw
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.ResultIterator


/**
 * This class is used for caching opened files (such that we do not need to close and open the files for each single access)
override val classname="is"
 */
object CacheOfFiles {

    /**
     * the max. number of opened files
     */
    const val MAXOPENEDFILES = 10

    @JvmField
    val cache = LeastRecentlyUsed<String, CachedFile?>("", null, MAXOPENEDFILES)

    /**
     * Returns the requested file from cache, or opens the file if it is not in the cache of open files
     */
    inline fun getFile(filename: String): CachedFile {
        val fileEntry = this.cache.getEntry(filename)
        if (fileEntry == null) {
            while (this.cache.entries.size >= MAXOPENEDFILES) {
                val toBeClosed = this.cache.replaceLeastRecentlyUsed()
                toBeClosed!!.close()
            }
            val file = CachedFile(filename)
            val newFileEntry = CachedEntry<String, CachedFile?>(filename, file)
            this.cache.addNewEntry(newFileEntry)
            return file
        } else {
            this.cache.accessNow(fileEntry)
            return fileEntry.value!!
        }
    }

    // problems unmap:
    // see e.g.: https://stackoverflow.com/questions/2972986/how-to-unmap-a-file-from-memory-mapped-using-filechannel-in-java

    // furthermore, memory mapped file and unsafe api:
    // http://nyeggen.com/post/2014-05-18-memory-mapping-%3E2gb-of-data-in-java/
    // and slides comparing different ways:
    // https://www.slideshare.net/AndreiPangin/do-we-need-unsafe-in-java
    inline fun get(filename: String, address: Long): Page {
        return getFile(filename).get(address)
    }

    /**
     * closes all open files
     */
    fun close() {
        for (entry in this.cache.entries) {
            entry.value.value?.close()
        }
        this.cache.releaseAll()
    }

    /**
     * closes all files starting with the given filename
     */
    fun close(filename: String) {
        val entry = this.cache.getEntry(filename)
        if (entry != null) {
            entry.value?.close()
            this.cache.release(entry.key)
        }
    }
}
