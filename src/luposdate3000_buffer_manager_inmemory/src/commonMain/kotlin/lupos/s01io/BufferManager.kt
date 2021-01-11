package lupos.s01io
import lupos.s00misc.BUFFER_MANAGER_PAGE_SIZE_IN_BYTES
import lupos.s00misc.BUFFER_MANAGER_USE_FREE_LIST
import lupos.s00misc.MyReadWriteLock
import lupos.s00misc.Platform
import lupos.s00misc.SanityCheck
import kotlin.jvm.JvmField
public object BufferManagerExt {
    public fun getPageSize(): Long {
        return BUFFER_MANAGER_PAGE_SIZE_IN_BYTES
    }
    @JvmField
    public // dont put const val here, because it wont work when exchanging the modules
    val isInMemoryOnly: Boolean = true
    @JvmField
    public val initializedFromDisk: Boolean = false
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
    @JvmField
    public var bufferPrefix: String = Platform.getEnv("LUPOS_HOME", "/tmp/luposdate3000/")!!
    init {
        SanityCheck.println { "bufferPrefix = $bufferPrefix" }
    }
    @JvmField
    internal val managerList = mutableMapOf<String, BufferManager>()
    @JvmField
    internal val managerListLock = MyReadWriteLock()
}
public class BufferManager {
    @JvmField public val name: String
    internal constructor(name: String) {
        this.name = name
    }
    /*
     * each type safe page-manager safes to its own store
     * using another layer of indirection,
     * to be able to share this code across different type-safe managers as
     * - triple store
     * - dictionary (currently not implemented)
     * - temporary result rows (currently not implemented)
     * additionally this should make it more easy to exchange this with on disk storage
     */
    @JvmField
    internal var allPages = Array(100) { ByteArray(BUFFER_MANAGER_PAGE_SIZE_IN_BYTES.toInt()) }
    @JvmField
    internal var allPagesRefcounters = IntArray(100)
    @JvmField
    internal var counter = 0
    @JvmField
    internal val lock = MyReadWriteLock()
    @JvmField
    internal val freeList = mutableListOf<Int>()
    public /*suspend*/ fun clear() {
        lock.withWriteLock {
            clearAssumeLocks()
        }
    }
    /*suspend*/ private fun clearAssumeLocks() {
        counter = 0
        SanityCheck {
            for (i in 0 until counter) {
                SanityCheck.check({ allPagesRefcounters[i] == 0 }, { "Failed requirement pageid = $i" })
            }
        }
        allPages = Array(100) { ByteArray(BUFFER_MANAGER_PAGE_SIZE_IN_BYTES.toInt()) }
        allPagesRefcounters = IntArray(100)
        if (BUFFER_MANAGER_USE_FREE_LIST) {
            freeList.clear()
        }
    }
    public fun flushPage(pageid: Int) {}
    public fun releasePage(pageid: Int) {
        SanityCheck.check({ allPagesRefcounters[pageid] > 0 }, { "Failed requirement pageid = $pageid" })
        allPagesRefcounters[pageid]--
        SanityCheck.println { "BufferManager.refcount($pageid) decreased a ${allPagesRefcounters[pageid]}" }
    }
    public fun getPage(pageid: Int): ByteArray {
        // no locking required, assuming an assignment to 'allPages' is atomic
        SanityCheck {
            if (BUFFER_MANAGER_USE_FREE_LIST) {
                SanityCheck.check { !freeList.contains(pageid) }
            }
        }
        allPagesRefcounters[pageid]++
        SanityCheck.println { "BufferManager.refcount($pageid) increased a ${allPagesRefcounters[pageid]}" }
        return allPages[pageid]
    }
    public /*suspend*/ fun createPage(action: (ByteArray, Int) -> Unit): Unit = lock.withWriteLock {
        val pageid: Int
        if (freeList.size > 0 && BUFFER_MANAGER_USE_FREE_LIST) {
            pageid = freeList.removeAt(0)
        } else {
            if (counter == allPages.size) {
                var size = counter * 2
                if (size < 100) {
                    size = 100
                } else if (counter > 1000) {
                    size = counter + 1000
                }
                val tmp = Array(size) {
                    val res: ByteArray = if (it < counter) {
                        allPages[it]
                    } else {
                        ByteArray(BUFFER_MANAGER_PAGE_SIZE_IN_BYTES.toInt())
                    }
                    res
                }
                val tmp2 = IntArray(size) {
                    if (it < counter) {
                        allPagesRefcounters[it]
                    } else {
                        0
                    }
                }
                allPages = tmp
                allPagesRefcounters = tmp2
            }
            pageid = counter++
        }
        allPagesRefcounters[pageid]++
        SanityCheck.println { "BufferManager.refcount($pageid) increased b ${allPagesRefcounters[pageid]}" }
        action(allPages[pageid], pageid)
    }
    public /*suspend*/ fun deletePage(pageid: Int): Unit = lock.withWriteLock {
        SanityCheck {
            if (BUFFER_MANAGER_USE_FREE_LIST) {
                SanityCheck.check { !freeList.contains(pageid) }
            }
        }
        SanityCheck.check({ allPagesRefcounters[pageid] == 1 }, { "Failed requirement pageid = $pageid" })
        allPagesRefcounters[pageid] = 0
        SanityCheck.println { "BufferManager.refcount($pageid) decreased b ${allPagesRefcounters[pageid]}" }
        if (BUFFER_MANAGER_USE_FREE_LIST) {
            freeList.add(pageid)
            if (freeList.size == counter) {
                clearAssumeLocks()
            }
        } else {
            if (counter == 0) {
                clearAssumeLocks()
            }
        }
    }
}
