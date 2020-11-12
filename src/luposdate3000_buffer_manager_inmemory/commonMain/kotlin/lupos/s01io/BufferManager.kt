package lupos.s01io

import kotlin.jvm.JvmField
import lupos.s00misc.BUFFER_MANAGER_PAGE_SIZE_IN_BYTES
import lupos.s00misc.BUFFER_MANAGER_USE_FREE_LIST
import lupos.s00misc.Configuration
import lupos.s00misc.MyReadWriteLock
import lupos.s00misc.SanityCheck

class BufferManager(@JvmField val bufferName: String) {
    /*
     * each type safe page-manager safes to its own store
     * using another layer of indirection,
     * to be able to share this code across different type-safe managers as
     * - triple store
     * - dictionary (currently not implemented)
     * - temporary result rows (currently not implemented)
     * additionally this should make it more easy to exchange this with on disk storage
     */
    companion object {
        @JvmField
        var bufferPrefix: String

        @JvmField
        val page_size_in_bytes = BUFFER_MANAGER_PAGE_SIZE_IN_BYTES

        init {
            bufferPrefix = Configuration.getEnv("LUPOS_HOME", "/tmp/luposdate3000/")!!
            SanityCheck.println({ "bufferPrefix = $bufferPrefix" })
        }

        @JvmField
        internal val managerList = mutableListOf<BufferManager>()

        @JvmField
        internal val managerListLock = MyReadWriteLock()
        /*suspend*/ fun safeToFolder() = managerListLock.withReadLock {
            managerList.forEach {
                it.safeToFolder()
            }
        }

        /*suspend*/ fun loadFromFolder() = managerListLock.withReadLock {
            managerList.forEach {
                it.loadFromFolder()
            }
        }
    }

    init {
        val manager = this
        managerListLock.withWriteLock {
            managerList.add(manager)
        }
    }

    @JvmField
    internal var allPages = Array(100) { ByteArray(BUFFER_MANAGER_PAGE_SIZE_IN_BYTES) }

    @JvmField
    internal var allPagesRefcounters = IntArray(100)

    @JvmField
    internal var counter = 0

    @JvmField
    internal val lock = MyReadWriteLock()

    @JvmField
    internal val freeList = mutableListOf<Int>()
    /*suspend*/ fun clear() = lock.withWriteLock {
        clearAssumeLocks()
    }

    /*suspend*/ fun clearAssumeLocks() {
        counter = 0
        SanityCheck {
            for (i in 0 until counter) {
                SanityCheck.check({ allPagesRefcounters[i] == 0 }, { "Failed requirement pageid = $i" })
            }
        }
        allPages = Array(100) { ByteArray(BUFFER_MANAGER_PAGE_SIZE_IN_BYTES) }
        allPagesRefcounters = IntArray(100)
        freeList.clear()
    }

    fun releasePage(pageid: Int) {
        SanityCheck.check({ allPagesRefcounters[pageid] > 0 }, { "Failed requirement pageid = $pageid" })
        allPagesRefcounters[pageid]--
        SanityCheck.println({ "BufferManager.refcount($pageid) decreased a ${allPagesRefcounters[pageid]}" })
    }

    fun referencePage(pageid: Int) {
        SanityCheck.check({ allPagesRefcounters[pageid] > 0 }, { "Failed requirement pageid = $pageid" })
        allPagesRefcounters[pageid]++
        SanityCheck.println({ "BufferManager.refcount($pageid) increased a ${allPagesRefcounters[pageid]}" })
    }

    fun getPage(pageid: Int): ByteArray {
        //no locking required, assuming an assignment to 'allPages' is atomic
        SanityCheck.check { !freeList.contains(pageid) }
        allPagesRefcounters[pageid]++
        SanityCheck.println({ "BufferManager.refcount($pageid) increased a ${allPagesRefcounters[pageid]}" })
        return allPages[pageid]
    }

    /*suspend*/ fun createPage(action: (ByteArray, Int) -> Unit) = lock.withWriteLock {
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
                    val res: ByteArray
                    if (it < counter) {
                        res = allPages[it]
                    } else {
                        res = ByteArray(BUFFER_MANAGER_PAGE_SIZE_IN_BYTES)
                    }
                    /*return*/ res
                }
                val tmp2 = IntArray(size) {
                    var res: Int
                    if (it < counter) {
                        res = allPagesRefcounters[it]
                    } else {
                        res = 0
                    }
                    /*return*/ res
                }
                allPages = tmp
                allPagesRefcounters = tmp2
            }
            pageid = counter++
        }
        allPagesRefcounters[pageid]++
        SanityCheck.println({ "BufferManager.refcount($pageid) increased b ${allPagesRefcounters[pageid]}" })
        action(allPages[pageid], pageid)
    }

    /*suspend*/ fun deletePage(pageid: Int) = lock.withWriteLock {
        SanityCheck.check { !freeList.contains(pageid) }
        SanityCheck.check({ allPagesRefcounters[pageid] == 1 }, { "Failed requirement pageid = $pageid" })
        allPagesRefcounters[pageid] = 0
        SanityCheck.println({ "BufferManager.refcount($pageid) decreased b ${allPagesRefcounters[pageid]}" })
        freeList.add(pageid)
        if (freeList.size == counter) {
            clearAssumeLocks()
        }
    }

    fun debug() {
    }

    /*suspend*/ fun safeToFolder() {
    }

    /*suspend*/ fun loadFromFolder() {
    }
}
