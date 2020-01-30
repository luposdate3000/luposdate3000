package lupos.s01io.buffer
import lupos.s01io.buffer.CachedFile
import lupos.s01io.buffer.MappedByteBufferPage
import lupos.s01io.buffer.CachedFile
import lupos.s01io.buffer.MemoryAccess

import lupos.s01io.buffer.MemoryAccess
import lupos.s01io.buffer.BufferManager
import lupos.s01io.buffer.CacheOfFiles
import lupos.s01io.buffer.Cache
import lupos.s01io.buffer.BufferManager

import lupos.s01io.buffer.PageHelper
import lupos.s01io.buffer.ByteArrayPage
import lupos.s01io.buffer.Benchmark

import java.io.File
import java.io.RandomAccessFile

typealias Page = ByteArrayPage

inline fun createString(chars: CharArray): String = String(chars)

// problems unmap:
// see e.g.: https://stackoverflow.com/questions/2972986

// furthermore, memory mapped file and unsafe api:
// http://nyeggen.com/post/2014-05-18-memory-mapping-%3E2gb-of-data-in-java/
// and slides comparing different ways:
// https://www.slideshare.net/AndreiPangin/do-we-need-unsafe-in-java

class CachedFile {
    @JvmField
    // in JVM-environment: this does not generate any getter avoiding a virtual method call!
    val file: RandomAccessFile

    constructor(filename: String) {
        val paths = filename.split("/")
        if (paths.size > 1) {
            val dirpath = paths.joinToString(separator = "/", limit = paths.size - 1)
            File(dirpath).mkdirs()
        }
        this.file = RandomAccessFile(File(filename), "rw")
    }

    inline fun close() {
        this.file.close()
    }

    inline fun get(address: Long): Page {
        this.file.seek(address)
        val page = Page()
        this.file.read(page.byteArray)
        return page
    }

    inline fun write(address: Long, page: Page) {
        this.file.seek(address)
        this.file.write(page.byteArray)
    }
}
