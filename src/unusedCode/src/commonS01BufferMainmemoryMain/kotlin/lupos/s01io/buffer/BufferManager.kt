package lupos.s01io.buffer
import kotlin.jvm.JvmField
data class PageAddress(@JvmField public val fileName: String, @JvmField public val pageNumber: Int)
const val PAGESIZE: Int = 8 * 1024
val bufferManager: BufferManager = BufferManager()
public class BufferManager {
    @JvmField
    val memoryOfFiles = hashMapOf<String, Array<Page?>>()
    public fun getPage(pageAddress: PageAddress): Page {
        return this.getPage(pageAddress.fileName, pageAddress.pageNumber)
    }
    public fun getPage(file: String, number: Int): Page {
        val memoryOfFile = this.memoryOfFiles.get(file)
        if (memoryOfFile == null) {
            val array = arrayOfNulls<Page>(number + 1)
            val newPage = Page()
            array[number] = newPage
            this.memoryOfFiles[file] = array
            return newPage
        } else {
            if (memoryOfFile.size <= number) {
                /* double the size for each resizing (may avoid often resizing,
		 * but may reserve additional unneeded space)
		 */
                var newSize = memoryOfFile.size.shl(1)
                while (newSize <= number) {
                    newSize = newSize.shl(1)
                }
                val newArray = memoryOfFile.copyOf(number + 1)
                val newPage = Page()
                newArray[number] = newPage
                this.memoryOfFiles[file] = newArray
                return newPage
            } else {
                val page = memoryOfFile[number]
                if (page == null) {
                    val newPage = Page()
                    memoryOfFile[number] = newPage
                    return newPage
                } else {
                    return page
                }
            }
        }
    }
    public fun writeAllModifiedPages() {} // main memory implementation does not persist its pages!
    public fun release() {
        for (entry in this.memoryOfFiles) {
            for (page in entry.value) {
                if (page != null) {
                    page.release()
                }
            }
        }
        this.memoryOfFiles.clear()
    }
}
