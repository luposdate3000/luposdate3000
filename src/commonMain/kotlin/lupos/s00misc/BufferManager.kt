package lupos.s00misc

import kotlin.jvm.JvmField
import lupos.s00misc.File
import lupos.s00misc.ReadWriteLock
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
        const val useFreeList = true

        @JvmField
        var bufferPrefix: String

        init {
            bufferPrefix = Configuration.getEnv("LUPOS_HOME", "/tmp/luposdate3000/")!!
            SanityCheck.println({ "bufferPrefix = $bufferPrefix" })
        }

        @JvmField
        val managerList = mutableListOf<BufferManager>()

        @JvmField
        val managerListLock = ReadWriteLock()
        suspend fun safeToFolder() = managerListLock.withReadLock {
            managerList.forEach {
                it.safeToFolder()
            }
        }

        suspend fun loadFromFolder() = managerListLock.withReadLock {
            managerList.forEach {
                it.loadFromFolder()
            }
        }
    }

    init {
        val manager = this
        managerListLock.withWriteLockSuspend {
            managerList.add(manager)
        }
    }

    @JvmField
    var allPages = Array<ByteArray>(100) { ByteArray(PAGE_SIZE_IN_BYTES) }

    @JvmField
    var counter = 0

    @JvmField
    val lock = ReadWriteLock()

    @JvmField
    val freeList = mutableListOf<Int>()
    inline suspend fun clear() = lock.withWriteLock {
        clearAssumeLocks()
    }

    inline suspend fun clearAssumeLocks() {
        counter = 0
        allPages = Array<ByteArray>(100) { ByteArray(PAGE_SIZE_IN_BYTES) }
        freeList.clear()
    }

    inline fun getPage(pageid: Int): ByteArray {
//no locking required, assuming an assignment to 'allPages' is atomic
        SanityCheck { !freeList.contains(pageid) }
        return allPages[pageid]
    }

    inline suspend fun createPage(crossinline action: (ByteArray, Int) -> Unit) = lock.withWriteLock {
        val id: Int
        if (freeList.size > 0 && useFreeList) {
            id = freeList.removeAt(0)
        } else {
            if (counter == allPages.size) {
var size=counter*2
if(size<100){
size=100
}else if(counter>1000){
size=counter+1000
}
                val tmp = Array<ByteArray>(size) {
                    val res: ByteArray
                    if (it < counter) {
                        res = allPages[it]
                    } else {
                        res = ByteArray(PAGE_SIZE_IN_BYTES)
                    }
                    /*return*/ res
                }
                allPages = tmp
            }
            id = counter++
        }
        action(allPages[id], id)
    }

    inline suspend fun deletePage(pageid: Int) = lock.withWriteLock {
        SanityCheck.check { !freeList.contains(pageid) }
        freeList.add(pageid)
        if (freeList.size == counter) {
            clearAssumeLocks()
        }
    }

    fun debug() {
    }

    suspend fun safeToFolder() = lock.withWriteLock {
        File(bufferPrefix + "buffermanager").mkdirs()
        File(bufferPrefix + "buffermanager/" + bufferName + ".data").dataOutputStream { fos ->
            for (i in 0 until counter) {
                fos.write(allPages[i])
            }
        }
        File(bufferPrefix + "buffermanager/" + bufferName + ".header").dataOutputStream { fos ->
            fos.writeInt(counter)
            fos.writeInt(freeList.size)
            freeList.forEach { v ->
                fos.writeInt(v)
            }
        }
    }

    suspend fun loadFromFolder() = lock.withWriteLock {
        File(bufferPrefix + "buffermanager/" + bufferName + ".header").dataInputStream { fis ->
            counter = fis.readInt()
            val size = fis.readInt()
            freeList.clear()
            for (i in 0 until size) {
                val v = fis.readInt()
                freeList.add(v)
            }
        }
        allPages = Array<ByteArray>(counter) { ByteArray(PAGE_SIZE_IN_BYTES) }
        File(bufferPrefix + "buffermanager/" + bufferName + ".data").dataInputStream { fis ->
            for (i in 0 until counter) {
                fis.read(allPages[i])
            }
        }
    }
}
