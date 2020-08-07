package lupos.s00misc

import kotlin.jvm.JvmField
import lupos.s00misc.File
import lupos.s00misc.MyListGeneric
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
        fun safeToFolder() = managerListLock.withReadLockSuspend {
            managerList.forEach {
                it.safeToFolder()
            }
        }

        fun loadFromFolder() = managerListLock.withReadLockSuspend {
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
    val pageMappingsOutIn = mutableMapOf<Int, Int>()

    @JvmField
    val pageMappingsInOut = mutableMapOf<Int, Int>() // keys are guaranteed to be possible to store as array

    inline suspend fun clear() = lock.withWriteLock {
        counter = 0
        allPages = Array<ByteArray>(100) { ByteArray(PAGE_SIZE_IN_BYTES) }
        pageMappingsOutIn.clear()
        pageMappingsInOut.clear()
    }

    inline suspend fun getPage(pageid: Int): ByteArray = lock.withReadLock {
        val target = pageMappingsOutIn[pageid]!!
        /*return*/ allPages[target]
    }

    inline suspend fun createPage(pageid: Int): ByteArray = lock.withWriteLock {
        val target = counter++
        SanityCheck.check { pageMappingsOutIn[pageid] == null }
        SanityCheck.check { pageMappingsInOut[target] == null }
        if (target == allPages.size) {
            val tmp = Array<ByteArray>(target * 2) {
                val res: ByteArray
                if (it < target) {
                    res = allPages[it]
                } else {
                    res = ByteArray(PAGE_SIZE_IN_BYTES)
                }
                /*return*/ res
            }
            allPages = tmp
        }
        pageMappingsOutIn[pageid] = target
        pageMappingsInOut[target] = pageid
        debug()
        /*return*/ allPages[target]
    }

    inline suspend fun deletePage(pageid: Int) = lock.withWriteLock {
        val otherTarget = counter - 1
        val target = pageMappingsOutIn[pageid]!!
        pageMappingsOutIn.remove(pageid)
        if (target == otherTarget) {
            pageMappingsInOut.remove(target)
            counter--
            debug()
        } else {
            val other = pageMappingsInOut[otherTarget]!!
            pageMappingsOutIn[other] = target
            pageMappingsInOut[target] = other
            pageMappingsInOut.remove(otherTarget)
            val tmp = allPages[target]
            allPages[target] = allPages[otherTarget]
            allPages[otherTarget] = tmp
            counter--
            debug()
        }
    }

    fun debug() {
        SanityCheck {
            var i = 0
            pageMappingsInOut.forEach { k, v ->
                i++
                SanityCheck.check { k < counter }
                SanityCheck.check { pageMappingsOutIn[v] == k }
            }
            SanityCheck.check { i == counter }
            pageMappingsOutIn.forEach { k, v ->
                SanityCheck.check { pageMappingsInOut[v] == k }
            }
        }
    }

    fun safeToFolder() = lock.withWriteLockSuspend {
        File(bufferPrefix + "buffermanager").mkdirs()
        File(bufferPrefix + "buffermanager/" + bufferName + ".data").dataOutputStream { fos ->
            for (i in 0 until counter) {
                fos.write(it)
            }
        }
        File(bufferPrefix + "buffermanager/" + bufferName + ".header").dataOutputStream { fos ->
            fos.writeInt(counter)
            fos.writeInt(pageMappingsInOut.size)
            pageMappingsInOut.forEach { k, v ->
                fos.writeInt(k)
                fos.writeInt(v)
                SanityCheck { pageMappingsOutIn[v] == k }
            }
        }
    }

    fun loadFromFolder() = lock.withWriteLockSuspend {
        File(bufferPrefix + "buffermanager/" + bufferName + ".header").dataInputStream { fis ->
            counter = fis.readInt()
            val size = fis.readInt()
            for (i in 0 until size) {
                val k = fis.readInt()
                val v = fis.readInt()
                pageMappingsInOut[k] = v
                pageMappingsOutIn[v] = k
            }
        }
        File(bufferPrefix + "buffermanager/" + bufferName + ".data").dataInputStream { fis ->
            for (i in 0 until counter) {
                data = allPages[i]
                fis.read(data)
            }
        }
    }
}
