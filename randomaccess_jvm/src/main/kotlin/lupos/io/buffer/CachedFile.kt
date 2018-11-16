package lupos.io.buffer

import java.io.File
import java.io.RandomAccessFile

actual typealias Page = JVMByteArrayPage

actual inline fun createString(chars: CharArray):String = String(chars)

// problems unmap:
// see e.g.: https://stackoverflow.com/questions/2972986/how-to-unmap-a-file-from-memory-mapped-using-filechannel-in-java

// furthermore, memory mapped file and unsafe api:
// http://nyeggen.com/post/2014-05-18-memory-mapping-%3E2gb-of-data-in-java/
// and slides comparing different ways:
// https://www.slideshare.net/AndreiPangin/do-we-need-unsafe-in-java

actual class CachedFile {
    val file: RandomAccessFile

    actual constructor(filename:String){
        val paths = filename.split("/")
        if(paths.size>1) {
            val dirpath = paths.joinToString(separator = "/", limit = paths.size - 1)
            File(dirpath).mkdirs()
        }
        this.file = RandomAccessFile(File(filename), "rw")
    }
    actual inline fun close(){
        this.file.close()
    }
    actual inline fun get(address: Long):Page {
        this.file.seek(address)
        val page = Page()
        this.file.read(page.byteArray)
        return page
    }
    actual inline fun write(address: Long, page: Page){
        this.file.seek(address)
        this.file.write(page.byteArray)
    }
}