package lupos.s01io
import lupos.s00misc.BUFFER_MANAGER_PAGE_SIZE_IN_BYTES
import lupos.s00misc.MyReadWriteLock
import lupos.s00misc.SanityCheck
import java.io.RandomAccessFile
import java.util.Arrays
import kotlin.contracts.InvocationKind.EXACTLY_ONCE
import kotlin.contracts.contract
import kotlin.jvm.JvmField
@OptIn(kotlin.contracts.ExperimentalContracts::class)
actual class BufferManager {
    @JvmField val cacheSize = 100
    internal companion object {
        const val freelistfileOffsetCounter = 0L
        const val freelistfileOffsetFreeLen = 4L
        const val freelistfileOffsetData = 8L
    }
    @JvmField
    var name: String = ""
    actual constructor (name: String) {
        this.name = name
        datafile = RandomAccessFile(BufferManagerExt.bufferPrefix + name + BufferManagerExt.fileEnding, "rw")
        freelistfile = RandomAccessFile(BufferManagerExt.bufferPrefix + name + BufferManagerExt.fileEndingFree, "rw")
        if (BufferManagerExt.initializedFromDisk) {
            datafilelength = datafile.length()
            freelistfile.seek(freelistfileOffsetFreeLen)
            freeArrayLength = freelistfile.readInt()
            freelistfile.seek(freelistfileOffsetCounter)
            counter = freelistfile.readInt()
            var s = cacheSize
            while (s <freeArrayLength) {
                s = s * 2
            }
            freeArray = IntArray(s)
            freelistfile.seek(freelistfileOffsetData)
            for (i in 0 until freeArrayLength) {
                freeArray[i] = freelistfile.readInt()
            }
        } else {
            counter = 0
            datafilelength = 0L
            freeArrayLength = 0
            freeArray = IntArray(cacheSize)
            freelistfile.seek(freelistfileOffsetCounter)
            freelistfile.writeInt(counter)
            freelistfile.seek(freelistfileOffsetFreeLen)
            freelistfile.writeInt(freeArrayLength)
        }
        println("creating buffermanager by name :: '$name'")
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
    internal var counter: Int
    @JvmField
    internal var freeArray: IntArray
    @JvmField
    internal var freeArrayLength: Int
    @JvmField
    val freelistfile: RandomAccessFile
    @JvmField
    val datafile: RandomAccessFile
    @JvmField
    var datafilelength: Long
//    @JvmField
//    var debugListOfPages = mutableMapOf<Int, ByteArray>()
    init {
        val manager = this
        BufferManagerExt.managerListLock.withWriteLock {
            BufferManagerExt.managerList[name] = manager
        }
    }
    internal inline fun localSanityCheck() {
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
                for (i in 0 until freeArrayLength) {
                    SanityCheck { freeArray[i] != k }
                }
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
        println("calling clear")
        counter = 0
        freelistfile.seek(freelistfileOffsetCounter)
        freelistfile.writeInt(counter)
        freeArrayLength = 0
        freelistfile.seek(freelistfileOffsetFreeLen)
        freelistfile.writeInt(freeArrayLength)
        openPagesMapping.clear()
        for (i in 0 until openPagesRefcounters.size) {
            openPagesRefcounters[i] = 0
        }
    }
    actual fun flushPage(pageid: Int) {
        lock.withWriteLock {
            localSanityCheck()
            SanityCheck.check { pageid <counter }
            for (i in 0 until freeArrayLength) {
                SanityCheck { freeArray[i] != pageid }
            }
            SanityCheck.check { openPagesMapping[pageid] != null }
            var openId = openPagesMapping[pageid]!!
            SanityCheck.check { openPagesRefcounters[openId] >= 1 }
            println("BufferManager .. $pageid -> $openId release finally")
            if (datafilelength < BUFFER_MANAGER_PAGE_SIZE_IN_BYTES * pageid) {
                datafilelength = BUFFER_MANAGER_PAGE_SIZE_IN_BYTES * pageid
                datafile.setLength(datafilelength)
            }
            datafile.seek(BUFFER_MANAGER_PAGE_SIZE_IN_BYTES * pageid)
            println("RandomAccessFile ${BUFFER_MANAGER_PAGE_SIZE_IN_BYTES * pageid} write")
            datafile.write(openPages[openId])
            SanityCheck {
                val cmp = ByteArray(BUFFER_MANAGER_PAGE_SIZE_IN_BYTES.toInt())
                println("RandomAccessFile ${BUFFER_MANAGER_PAGE_SIZE_IN_BYTES * pageid} cmp")
                datafile.seek(BUFFER_MANAGER_PAGE_SIZE_IN_BYTES * pageid)
                datafile.readFully(cmp)
                for (i in 0 until BUFFER_MANAGER_PAGE_SIZE_IN_BYTES.toInt()) {
                    SanityCheck.check { cmp[i] == openPages[openId][i] }
                }
                // debugListOfPages[pageid] = cmp
            }
            SanityCheck.check { !openPagesMapping.values.contains(openId) }
            localSanityCheck()
        }
    }
    actual fun releasePage(pageid: Int) {
        lock.withWriteLock {
            localSanityCheck()
            SanityCheck.check { pageid <counter }
            for (i in 0 until freeArrayLength) {
                SanityCheck { freeArray[i] != pageid }
            }
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
                println("RandomAccessFile ${BUFFER_MANAGER_PAGE_SIZE_IN_BYTES * pageid} write")
                datafile.write(openPages[openId])
                SanityCheck {
                    val cmp = ByteArray(BUFFER_MANAGER_PAGE_SIZE_IN_BYTES.toInt())
                    println("RandomAccessFile ${BUFFER_MANAGER_PAGE_SIZE_IN_BYTES * pageid} cmp")
                    datafile.seek(BUFFER_MANAGER_PAGE_SIZE_IN_BYTES * pageid)
                    datafile.readFully(cmp)
                    for (i in 0 until BUFFER_MANAGER_PAGE_SIZE_IN_BYTES.toInt()) {
                        SanityCheck.check { cmp[i] == openPages[openId][i] }
                    }
                    // debugListOfPages[pageid] = cmp
                }
// TODO remove unnecessary flush
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
            for (i in 0 until freeArrayLength) {
                SanityCheck { freeArray[i] != pageid }
            }
            openId = openPagesMapping[pageid]
            if (openId != null) {
                println("BufferManager .. $pageid -> $openId opened again")
                openPagesRefcounters[openId!!]++
            } else {
                openId = findNextOpenID()
                datafile.seek(BUFFER_MANAGER_PAGE_SIZE_IN_BYTES * pageid)
                println("RandomAccessFile ${BUFFER_MANAGER_PAGE_SIZE_IN_BYTES * pageid} read")
                datafile.readFully(openPages[openId!!])
//                SanityCheck {
//                    val cmp = debugListOfPages[pageid]!!
//                    var errCnt = 0
//                    for (i in 0 until BUFFER_MANAGER_PAGE_SIZE_IN_BYTES.toInt()) {
//                        if (cmp[i] != openPages[openId!!][i]) {
                //                          errCnt++
                //                    }
                //              }
                //            if (errCnt> 0) {
                //              println("error on read $pageid .. data::")
                //            for (i in 0 until BUFFER_MANAGER_PAGE_SIZE_IN_BYTES.toInt()) {
                //              println("$i :: ${cmp[i]} vs ${openPages[openId!!][i]} .... ${cmp[i] == openPages[openId!!][i]}")
                //        }
                //      throw Exception("")
                //   }
                // }
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
            val pageid: Int
            if (freeArrayLength> 0) {
                freeArrayLength--
                freelistfile.seek(freelistfileOffsetFreeLen)
                freelistfile.writeInt(freeArrayLength)
                pageid = freeArray[freeArrayLength]
                println("RandomAccessFile ${BUFFER_MANAGER_PAGE_SIZE_IN_BYTES * pageid} create freearray")
            } else {
                pageid = counter++
                freelistfile.seek(freelistfileOffsetCounter)
                freelistfile.writeInt(counter)
                println("RandomAccessFile ${BUFFER_MANAGER_PAGE_SIZE_IN_BYTES * pageid} create counter")
            }
            SanityCheck.check { pageid <counter }
            for (i in 0 until freeArrayLength) {
                SanityCheck { freeArray[i] != pageid }
            }
            var openId = findNextOpenID()
            SanityCheck.check { !openPagesMapping.values.contains(openId) }
            println("BufferManager .. $pageid -> $openId created")
            openPagesMapping[pageid] = openId
            Arrays.fill(openPages[openId], 0)
            action(openPages[openId], pageid)
            localSanityCheck()
        }
    }
    actual /*suspend*/ fun deletePage(pageid: Int): Unit = lock.withWriteLock {
        localSanityCheck()
        println("RandomAccessFile ${BUFFER_MANAGER_PAGE_SIZE_IN_BYTES * pageid} delete")
        SanityCheck.check { pageid <counter }
        for (i in 0 until freeArrayLength) {
            SanityCheck { freeArray[i] != pageid }
        }
        SanityCheck.check { openPagesMapping[pageid] != null }
        var openId = openPagesMapping[pageid]!!
        SanityCheck.check { openPagesRefcounters[openId] == 1 }
        println("BufferManager .. $pageid -> $openId deleted")
        openPagesRefcounters[openId]--
        openPagesMapping.remove(pageid)
        if (freeArrayLength >= freeArray.size) {
            val arr = IntArray(freeArrayLength * 2)
            freeArray.copyInto(arr)
            freeArray = arr
        }
        freeArray[freeArrayLength] = pageid
        freelistfile.seek(freelistfileOffsetFreeLen)
        freelistfile.writeInt(freeArrayLength)
        freelistfile.seek(freelistfileOffsetData + 4 * freeArrayLength)
        freelistfile.writeInt(pageid)
        freeArrayLength++
        SanityCheck {
            // debugListOfPages.remove(pageid)
        }
        SanityCheck.check { !openPagesMapping.values.contains(openId) }
        localSanityCheck()
    }
}
