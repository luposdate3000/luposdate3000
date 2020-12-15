package lupos.s01io
import lupos.s00misc.BUFFER_MANAGER_PAGE_SIZE_IN_BYTES
import lupos.s00misc.MyReadWriteLock
import lupos.s00misc.SanityCheck
import java.io.RandomAccessFile
import kotlin.contracts.InvocationKind.EXACTLY_ONCE
import kotlin.contracts.contract
import kotlin.jvm.JvmField
@OptIn(kotlin.contracts.ExperimentalContracts::class)
actual class BufferManager {
    @JvmField val cacheSize = 100
    @JvmField
    var name: String = ""
    actual constructor (name: String) {
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
    internal val lock = MyReadWriteLock()
    @JvmField
    internal var openPages = Array(cacheSize) { ByteArray(BUFFER_MANAGER_PAGE_SIZE_IN_BYTES.toInt()) }
    @JvmField
    internal var openPagesRefcounters = IntArray(cacheSize)
    @JvmField
    internal var openPagesMapping = mutableMapOf<Int, Int>()
    @JvmField
    internal var counter = 0 // TODO store counter in File
    @JvmField
    internal val freeList = mutableListOf<Int>() // TODO store list in File
    @JvmField
    val datafile = RandomAccessFile(BufferManagerExt.bufferPrefix + name + BufferManagerExt.fileEnding, "rw")
    @JvmField
    var datafilelength = datafile.length()
    init {
        val manager = this
        BufferManagerExt.managerListLock.withWriteLock {
            BufferManagerExt.managerList[name] = manager
        }
    }

internal    inline fun localSanityCheck() {
        SanityCheck {
            var cntg = 0
            for (i in 0 until cacheSize) {
                SanityCheck.check { openPagesRefcounters[i] >= 0 }
                if (openPagesRefcounters[i] == 0) {
                    SanityCheck.check { !openPagesMapping.values.contains(i) }
                } else {
                    cntg++
                    var cnt = 0
                    for (f in openPagesMapping.values) {
                        if (f == i) {
                            cnt++
                        }
                    }
                    SanityCheck.check { openPagesMapping.values.contains(i) }
                    SanityCheck.check { cnt == 1 }
                }
            }
            SanityCheck.check { openPagesMapping.size == cntg }
            for ((k, v) in openPagesMapping) {
                SanityCheck.check { openPagesRefcounters[v]> 0 }
                SanityCheck.check { k <counter }
                SanityCheck.check { !freeList.contains(k) }
            }
        }
    }

    internal inline fun findNextOpenID(): Int {
        // this assumes write lock
        var openId = 0
        while (openId <cacheSize) {
            if (openPagesRefcounters[openId] == 0) {
                openPagesRefcounters[openId]++
                break
            }
            openId++
        }
        if (openId == cacheSize) {
            throw Exception("")
        }
        return openId
    }
    actual /*suspend*/ fun clear(): Unit = lock.withWriteLock {
        counter = 0
        freeList.clear()
        openPagesMapping.clear()
        for (i in 0 until openPagesRefcounters.size) {
            openPagesRefcounters[i] = 0
        }
    }
    actual fun releasePage(pageid: Int) {
        lock.withWriteLock {
            localSanityCheck()
            SanityCheck.check { pageid <counter }
            SanityCheck.check { !freeList.contains(pageid) }
            SanityCheck.check { openPagesMapping[pageid] != null }
            var openId = openPagesMapping[pageid]!!
            SanityCheck.check { openPagesRefcounters[openId] >= 1 }
            if (openPagesRefcounters[openId] == 1) {
                println("BufferManager .. $pageid -> $openId release finally")
                if (datafilelength < BUFFER_MANAGER_PAGE_SIZE_IN_BYTES * pageid) {
                    datafilelength = BUFFER_MANAGER_PAGE_SIZE_IN_BYTES * pageid
                    datafile.setLength(datafilelength)
                }
                datafile.seek(BUFFER_MANAGER_PAGE_SIZE_IN_BYTES * pageid)
                datafile.write(openPages[openId])
                openPagesMapping.remove(pageid)
                SanityCheck.check { !openPagesMapping.values.contains(openId) }
            } else {
                println("BufferManager .. $pageid -> $openId release, but keep")
            }
            openPagesRefcounters[openId]--
            localSanityCheck()
        }
    }
    actual fun getPage(pageid: Int): ByteArray {
        var openId: Int?
        lock.withWriteLock {
            localSanityCheck()
            SanityCheck.check { pageid <counter }
            SanityCheck.check { !freeList.contains(pageid) }
            openId = openPagesMapping[pageid]
            if (openId != null) {
                println("BufferManager .. $pageid -> $openId opened again")
                openPagesRefcounters[openId!!]++
            } else {
                openId = findNextOpenID()
datafile.seek(BUFFER_MANAGER_PAGE_SIZE_IN_BYTES * pageid)
datafile.readFully(openPages[openId!!])
                SanityCheck.check { !openPagesMapping.values.contains(openId) }
                println("BufferManager .. $pageid -> $openId opened first")
                openPagesMapping[pageid] = openId!!
            }
            localSanityCheck()
        }
        return openPages[openId!!]
    }
    actual /*suspend*/ fun createPage(action: (ByteArray, Int) -> Unit) {
        contract { callsInPlace(action, EXACTLY_ONCE) }
        lock.withWriteLock {
            localSanityCheck()
            val pageid = if (freeList.size> 0) freeList.removeAt(0) else counter++
            SanityCheck.check { pageid <counter }
            SanityCheck.check { !freeList.contains(pageid) }
            var openId = findNextOpenID()
            SanityCheck.check { !openPagesMapping.values.contains(openId) }
            SanityCheck.check { !openPagesMapping.values.contains(openId) }
            println("BufferManager .. $pageid -> $openId created")
            openPagesMapping[pageid] = openId
            action(openPages[openId], pageid)
            localSanityCheck()
        }
    }
    actual /*suspend*/ fun deletePage(pageid: Int): Unit = lock.withWriteLock {
        localSanityCheck()
        SanityCheck.check { pageid <counter }
        SanityCheck.check { !freeList.contains(pageid) }
        SanityCheck.check { openPagesMapping[pageid] != null }
        var openId = openPagesMapping[pageid]!!
        SanityCheck.check { openPagesRefcounters[openId] == 1 }
        println("BufferManager .. $pageid -> $openId deleted")
        openPagesRefcounters[openId]--
        openPagesMapping.remove(pageid)
        freeList.add(pageid)
        SanityCheck.check { !openPagesMapping.values.contains(openId) }
        if (counter == freeList.size) {
            counter = 0
            freeList.clear()
        }
        localSanityCheck()
    }
    actual /*suspend*/ fun safeToFolder() {
    }
    actual /*suspend*/ fun loadFromFolder() {
    }
}
