/*
 * This file is part of the Luposdate3000 distribution (https://github.com/luposdate3000/luposdate3000).
 * Copyright (c) 2020-2021, Institute of Information Systems (Benjamin Warnke and contributors of LUPOSDATE3000), University of Luebeck
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, version 3.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
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
public actual class BufferManager internal actual constructor(
    @JvmField
    public var name: String
) {
    @JvmField
    public val cacheSize: Int = 100

    internal companion object {
        internal const val freelistfileOffsetCounter = 0L
        internal const val freelistfileOffsetFreeLen = 4L
        internal const val freelistfileOffsetData = 8L
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
    internal val freelistfile: RandomAccessFile

    @JvmField
    internal val datafile: RandomAccessFile

    @JvmField
    internal var datafilelength: Long

    @Suppress("NOTHING_TO_INLINE")
    private inline fun localSanityCheck() {
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
                SanityCheck.check { openPagesRefcounters[v] > 0 }
                SanityCheck.check { k < counter }
                for (i in 0 until freeArrayLength) {
                    SanityCheck { freeArray[i] != k }
                }
            }
        }
    }

    @Suppress("NOTHING_TO_INLINE")
    private inline fun findNextOpenID(): Int {
        // this assumes write lock
        var openId = 0
        while (openId < cacheSize) {
            if (openPagesRefcounters[openId] == 0) {
                openPagesRefcounters[openId]++
                break
            }
            openId++
        }
        if (openId == cacheSize) {
            throw Exception("no more pages available")
        }
        return openId
    }

    public actual /*suspend*/ fun clear(): Unit = lock.withWriteLock {
        counter = 0
        freelistfile.seek(freelistfileOffsetCounter)
        freelistfile.writeInt(counter)
        freeArrayLength = 0
        freelistfile.seek(freelistfileOffsetFreeLen)
        freelistfile.writeInt(freeArrayLength)
        openPagesMapping.clear()
        for (i in openPagesRefcounters.indices) {
            openPagesRefcounters[i] = 0
        }
    }

    public actual fun flushPage(pageid: Int) {
        lock.withWriteLock {
            localSanityCheck()
            SanityCheck.check { pageid < counter }
            for (i in 0 until freeArrayLength) {
                SanityCheck { freeArray[i] != pageid }
            }
            SanityCheck.check { openPagesMapping[pageid] != null }
            val openId = openPagesMapping[pageid]!!
            SanityCheck.check { openPagesRefcounters[openId] >= 1 }
            if (datafilelength < BUFFER_MANAGER_PAGE_SIZE_IN_BYTES * pageid) {
                datafilelength = BUFFER_MANAGER_PAGE_SIZE_IN_BYTES * pageid
                datafile.setLength(datafilelength)
            }
            datafile.seek(BUFFER_MANAGER_PAGE_SIZE_IN_BYTES * pageid)
            datafile.write(openPages[openId])
            SanityCheck {
                val cmp = ByteArray(BUFFER_MANAGER_PAGE_SIZE_IN_BYTES.toInt())
                datafile.seek(BUFFER_MANAGER_PAGE_SIZE_IN_BYTES * pageid)
                datafile.readFully(cmp)
                for (i in 0 until BUFFER_MANAGER_PAGE_SIZE_IN_BYTES.toInt()) {
                    SanityCheck.check { cmp[i] == openPages[openId][i] }
                }
            }
            localSanityCheck()
        }
    }

    public actual fun releasePage(pageid: Int) {
        lock.withWriteLock {
            localSanityCheck()
            SanityCheck.check { pageid < counter }
            for (i in 0 until freeArrayLength) {
                SanityCheck { freeArray[i] != pageid }
            }
            SanityCheck.check { openPagesMapping[pageid] != null }
            val openId = openPagesMapping[pageid]!!
            SanityCheck.check { openPagesRefcounters[openId] >= 1 }
            if (openPagesRefcounters[openId] == 1) {
                if (datafilelength < BUFFER_MANAGER_PAGE_SIZE_IN_BYTES * pageid) {
                    datafilelength = BUFFER_MANAGER_PAGE_SIZE_IN_BYTES * pageid
                    datafile.setLength(datafilelength)
                }
                datafile.seek(BUFFER_MANAGER_PAGE_SIZE_IN_BYTES * pageid)
                datafile.write(openPages[openId])
                SanityCheck {
                    val cmp = ByteArray(BUFFER_MANAGER_PAGE_SIZE_IN_BYTES.toInt())
                    datafile.seek(BUFFER_MANAGER_PAGE_SIZE_IN_BYTES * pageid)
                    datafile.readFully(cmp)
                    for (i in 0 until BUFFER_MANAGER_PAGE_SIZE_IN_BYTES.toInt()) {
                        SanityCheck.check { cmp[i] == openPages[openId][i] }
                    }
                }
                openPagesMapping.remove(pageid)
                SanityCheck.check { !openPagesMapping.values.contains(openId) }
            }
            openPagesRefcounters[openId]--
            localSanityCheck()
        }
    }

    public actual fun getPage(pageid: Int): ByteArray {
        var openId: Int?
        lock.withWriteLock {
            localSanityCheck()
            SanityCheck.check { pageid < counter }
            for (i in 0 until freeArrayLength) {
                SanityCheck { freeArray[i] != pageid }
            }
            openId = openPagesMapping[pageid]
            if (openId != null) {
                openPagesRefcounters[openId!!]++
            } else {
                openId = findNextOpenID()
                datafile.seek(BUFFER_MANAGER_PAGE_SIZE_IN_BYTES * pageid)
                datafile.readFully(openPages[openId!!])
                SanityCheck.check { !openPagesMapping.values.contains(openId) }
                openPagesMapping[pageid] = openId!!
            }
            localSanityCheck()
        }
        return openPages[openId!!]
    }

    public actual /*suspend*/ fun createPage(action: (ByteArray, Int) -> Unit) {
        contract { callsInPlace(action, EXACTLY_ONCE) }
        lock.withWriteLock {
            localSanityCheck()
            val pageid: Int
            if (freeArrayLength > 0) {
                freeArrayLength--
                freelistfile.seek(freelistfileOffsetFreeLen)
                freelistfile.writeInt(freeArrayLength)
                pageid = freeArray[freeArrayLength]
            } else {
                pageid = counter++
                freelistfile.seek(freelistfileOffsetCounter)
                freelistfile.writeInt(counter)
            }
            SanityCheck.check { pageid < counter }
            for (i in 0 until freeArrayLength) {
                SanityCheck { freeArray[i] != pageid }
            }
            val openId = findNextOpenID()
            SanityCheck.check { !openPagesMapping.values.contains(openId) }
            openPagesMapping[pageid] = openId
            Arrays.fill(openPages[openId], 0)
            action(openPages[openId], pageid)
            localSanityCheck()
        }
    }

    public actual /*suspend*/ fun deletePage(pageid: Int): Unit = lock.withWriteLock {
        localSanityCheck()
        SanityCheck.check { pageid < counter }
        for (i in 0 until freeArrayLength) {
            SanityCheck { freeArray[i] != pageid }
        }
        SanityCheck.check { openPagesMapping[pageid] != null }
        val openId = openPagesMapping[pageid]!!
        SanityCheck.check { openPagesRefcounters[openId] == 1 }
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
        SanityCheck.check { !openPagesMapping.values.contains(openId) }
        localSanityCheck()
    }

    init {
        datafile = RandomAccessFile(BufferManagerExt.bufferPrefix + name + BufferManagerExt.fileEnding, "rw")
        freelistfile = RandomAccessFile(BufferManagerExt.bufferPrefix + name + BufferManagerExt.fileEndingFree, "rw")
        if (BufferManagerExt.initializedFromDisk) {
            datafilelength = datafile.length()
            freelistfile.seek(freelistfileOffsetFreeLen)
            freeArrayLength = freelistfile.readInt()
            freelistfile.seek(freelistfileOffsetCounter)
            counter = freelistfile.readInt()
            var s = cacheSize
            while (s < freeArrayLength) {
                s *= 2
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
    }
}
