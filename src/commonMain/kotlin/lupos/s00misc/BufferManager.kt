package lupos.s00misc

import lupos.s00misc.Coverage
import lupos.s00misc.File
import lupos.s00misc.MyListGeneric
import lupos.s00misc.ReadWriteLock
import lupos.s00misc.SanityCheck

class BufferManager(val bufferName: String) {
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
        var bufferPrefix: String

        init {
            bufferPrefix = Configuration.getEnv("LUPOS_HOME", "/tmp/luposdate3000/")!!
            println("bufferPrefix = $bufferPrefix")
        }

        val managerList = mutableListOf<BufferManager>()
        val managerListLock = ReadWriteLock()
        fun safeToFolder() = managerListLock.withReadLock {
            managerList.forEach {
                it.safeToFolder()
            }
        }

        fun loadFromFolder() = managerListLock.withReadLock {
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

    val allPages = MyListGeneric<ByteArray>()
    var counter = 0
    val lock = ReadWriteLock()
    val pageMappingsOutIn = mutableMapOf<Int, Int>()
    val pageMappingsInOut = mutableMapOf<Int, Int>() // keys are guaranteed to be possible to store as array
    fun clear() = lock.withWriteLock {
        counter = 0
        allPages.clear()
        pageMappingsOutIn.clear()
        pageMappingsInOut.clear()
    }

    fun getPage(pageid: Int): ByteArray = lock.withReadLock {
        val target = pageMappingsOutIn[pageid]!!
        /*return*/ allPages[target]
    }

    fun createPage(pageid: Int): ByteArray = lock.withWriteLock {
        val target = counter++
        SanityCheck.check { pageMappingsOutIn[pageid] == null }
        SanityCheck.check { pageMappingsInOut[target] == null }
        allPages[target] = ByteArray(PAGE_SIZE_IN_BYTES)
        pageMappingsOutIn[pageid] = target
        pageMappingsInOut[target] = pageid
        debug()
        /*return*/ allPages[target]
    }

    fun deletePage(pageid: Int) = lock.withWriteLock {
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
            allPages[target] = allPages[otherTarget]
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

    fun safeToFolder() = lock.withWriteLock {
        File(bufferPrefix + "buffermanager").mkdirs()
        File(bufferPrefix + "buffermanager/" + bufferName + ".data").dataOutputStream { fos ->
            var i = 0
            allPages.forEach {
                if (i < counter) {
                    fos.write(it)
                }
                i++
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

    fun loadFromFolder() = lock.withWriteLock {
        allPages.clear()
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
                val data = ByteArray(PAGE_SIZE_IN_BYTES)
                allPages[i] = data
                fis.read(data)
            }
        }
    }
}
