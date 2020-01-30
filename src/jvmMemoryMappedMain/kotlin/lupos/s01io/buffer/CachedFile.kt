package lupos.s01io.buffer
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
import java.nio.channels.FileChannel

// problems unmap:
// see e.g.: https://stackoverflow.com/questions/2972986

// furthermore, memory mapped file and unsafe api:
// http://nyeggen.com/post/2014-05-18-memory-mapping-%3E2gb-of-data-in-java/
// and slides comparing different ways:
// https://www.slideshare.net/AndreiPangin/do-we-need-unsafe-in-java

class CachedFile {
    val file: RandomAccessFile
    val PAGESIZE = 8 * 1024L

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
        return MappedByteBufferPage(this.file.getChannel()
                .map(FileChannel.MapMode.READ_WRITE, address, PAGESIZE))
    }

    inline fun write(address: Long, page: Page) {
        // it is already written by using put-methods of MappedByteBuffer
    }
}
