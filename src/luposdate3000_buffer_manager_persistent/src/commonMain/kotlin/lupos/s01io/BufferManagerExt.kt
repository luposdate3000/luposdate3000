package lupos.s01io

import lupos.s00misc.BUFFER_MANAGER_PAGE_SIZE_IN_BYTES
import lupos.s00misc.File
import lupos.s00misc.MyReadWriteLock
import lupos.s00misc.Platform
import lupos.s00misc.SanityCheck
import kotlin.jvm.JvmField

public object BufferManagerExt {
    public fun getPageSize(): Long {
        return BUFFER_MANAGER_PAGE_SIZE_IN_BYTES
    }
    public const val fileEnding: String = ".data"
    public const val fileEndingFree: String = ".datafree"
    @JvmField
    public // dont put const val here, because it wont work when exchanging the modules
    val isInMemoryOnly: Boolean = false
    @JvmField
    public var bufferPrefix: String = Platform.getEnv("LUPOS_HOME", "/tmp/luposdate3000/")!!
    @JvmField
    public val initializedFromDisk: Boolean = File(bufferPrefix).exists()
    public fun getBuffermanager(name: String): BufferManager {
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
