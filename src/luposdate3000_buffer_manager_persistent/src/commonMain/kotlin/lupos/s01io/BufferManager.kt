package lupos.s01io
import lupos.s00misc.File
import lupos.s00misc.BUFFER_MANAGER_PAGE_SIZE_IN_BYTES
import lupos.s00misc.MyReadWriteLock
import lupos.s00misc.Platform
import lupos.s00misc.SanityCheck
import kotlin.jvm.JvmField
object BufferManagerExt {
fun getPageSize():Long{ 
return BUFFER_MANAGER_PAGE_SIZE_IN_BYTES
}
    const val fileEnding = ".data"
    const val fileEndingFree = ".datafree"
    @JvmField // dont put const val here, because it wont work when exchanging the modules
    val isInMemoryOnly = false
    @JvmField
    var bufferPrefix: String = Platform.getEnv("LUPOS_HOME", "/tmp/luposdate3000/")!!
    @JvmField val initializedFromDisk = File(bufferPrefix).exists()
    fun getBuffermanager(name: String): BufferManager {
        var res: BufferManager? = null
        managerListLock.withWriteLock {
            res = managerList[name]
            if (res == null) {
                res = BufferManager(name)
                managerList[name] = res!!
            }
        }
        return res!!
    }
    init {
        SanityCheck.println { "bufferPrefix = $bufferPrefix" }
        File(bufferPrefix).mkdirs()
    }
    @JvmField
    internal val managerList = mutableMapOf<String, BufferManager>()
    @JvmField
    internal val managerListLock = MyReadWriteLock()
}
expect class BufferManager(name: String) {
    /*suspend*/ fun clear()
    fun releasePage(pageid: Int)
    fun getPage(pageid: Int): ByteArray
    /*suspend*/ fun createPage(action: (ByteArray, Int) -> Unit)
    /*suspend*/ fun deletePage(pageid: Int)
    fun flushPage(pageid: Int)
}
