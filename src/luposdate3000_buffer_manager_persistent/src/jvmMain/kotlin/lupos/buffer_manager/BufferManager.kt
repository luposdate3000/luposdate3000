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
package lupos.buffer_manager

import lupos.ProguardTestAnnotation
import lupos.shared.IBufferManager
import lupos.shared.Luposdate3000Instance
import lupos.shared.MyReadWriteLock
import lupos.shared.SanityCheck
import lupos.shared.inline.BufferManagerPage
import lupos.shared.inline.File
import java.io.RandomAccessFile
import kotlin.jvm.JvmField

@OptIn(kotlin.contracts.ExperimentalContracts::class)
public actual class BufferManager public actual constructor(instance: Luposdate3000Instance) : IBufferManager {

    private companion object {
        private const val freelistfileOffsetCounter = 0L
        private const val freelistfileOffsetFreeLen = 4L
        private const val freelistfileOffsetData = 8L
    }

    @ProguardTestAnnotation
    @JvmField
    internal var closed = false

    @JvmField
    internal val cacheSize: Int = 100

    @JvmField
    internal val lock = MyReadWriteLock()

    @JvmField
    internal var openPages = Array(cacheSize) { BufferManagerPage.create() }

    @JvmField
    internal var openPagesRefcounters = IntArray(cacheSize)

    @JvmField
    internal var openPagesMapping = IntArray(cacheSize) { -1 }

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
    private inline fun findOpenID(pageid: Int, crossinline onFound: (Int) -> Unit, crossinline onNotFound: () -> Unit) {
        for (i in 0 until cacheSize) {
            if (openPagesMapping[i] == pageid) {
                onFound(i)
                return
            }
        }
        onNotFound()
    }

    public actual override fun flushPage(call_location: String, pageid: Int) {
        SanityCheck.println_buffermanager { "BufferManager.flushPage($pageid) : $call_location" }
        SanityCheck.check({ /*SOURCE_FILE_START*/"D:/ideaprojects/luposdate3000/src/luposdate3000_buffer_manager_persistent/src/jvmMain/kotlin/lupos/buffer_manager/BufferManager.kt:87"/*SOURCE_FILE_END*/ }, { !closed })
        lock.withWriteLock {
            SanityCheck(
                { /*SOURCE_FILE_START*/"D:/ideaprojects/luposdate3000/src/luposdate3000_buffer_manager_persistent/src/jvmMain/kotlin/lupos/buffer_manager/BufferManager.kt:90"/*SOURCE_FILE_END*/ },
                {
                    SanityCheck.check({ /*SOURCE_FILE_START*/"D:/ideaprojects/luposdate3000/src/luposdate3000_buffer_manager_persistent/src/jvmMain/kotlin/lupos/buffer_manager/BufferManager.kt:92"/*SOURCE_FILE_END*/ }, { pageid < counter })
                    for (i in 0 until freeArrayLength) {
                        SanityCheck.check({ /*SOURCE_FILE_START*/"D:/ideaprojects/luposdate3000/src/luposdate3000_buffer_manager_persistent/src/jvmMain/kotlin/lupos/buffer_manager/BufferManager.kt:94"/*SOURCE_FILE_END*/ }, { freeArray[i] != pageid })
                    }
                }
            )
            findOpenID(
                pageid = pageid,
                onFound = { openId ->
                    SanityCheck.check({ /*SOURCE_FILE_START*/"D:/ideaprojects/luposdate3000/src/luposdate3000_buffer_manager_persistent/src/jvmMain/kotlin/lupos/buffer_manager/BufferManager.kt:101"/*SOURCE_FILE_END*/ }, { openPagesRefcounters[openId] >= 1 })
                    datafile.seek(BufferManagerPage.BUFFER_MANAGER_PAGE_SIZE_IN_BYTES.toLong() * pageid)
                    datafile.write(openPages[openId], 0, BufferManagerPage.BUFFER_MANAGER_PAGE_SIZE_IN_BYTES)
                    SanityCheck(
                        { /*SOURCE_FILE_START*/"D:/ideaprojects/luposdate3000/src/luposdate3000_buffer_manager_persistent/src/jvmMain/kotlin/lupos/buffer_manager/BufferManager.kt:105"/*SOURCE_FILE_END*/ },
                        {
                            val cmp = ByteArray(BufferManagerPage.BUFFER_MANAGER_PAGE_SIZE_IN_BYTES)
                            datafile.seek(BufferManagerPage.BUFFER_MANAGER_PAGE_SIZE_IN_BYTES.toLong() * pageid)
                            datafile.readFully(cmp, 0, BufferManagerPage.BUFFER_MANAGER_PAGE_SIZE_IN_BYTES)
                            for (i in 0 until BufferManagerPage.BUFFER_MANAGER_PAGE_SIZE_IN_BYTES) {
                                SanityCheck.check({ /*SOURCE_FILE_START*/"D:/ideaprojects/luposdate3000/src/luposdate3000_buffer_manager_persistent/src/jvmMain/kotlin/lupos/buffer_manager/BufferManager.kt:111"/*SOURCE_FILE_END*/ }, { cmp[i] == openPages[openId][i] })
                            }
                        }
                    )
                },
                onNotFound = {
                    SanityCheck.checkUnreachable()
                }
            )
        }
    }

    public actual override fun releasePage(call_location: String, pageid: Int) {
        SanityCheck.println_buffermanager { "BufferManager.releasePage($pageid) : $call_location" }
        SanityCheck.check({ /*SOURCE_FILE_START*/"D:/ideaprojects/luposdate3000/src/luposdate3000_buffer_manager_persistent/src/jvmMain/kotlin/lupos/buffer_manager/BufferManager.kt:125"/*SOURCE_FILE_END*/ }, { !closed })
        lock.withWriteLock {
            SanityCheck(
                { /*SOURCE_FILE_START*/"D:/ideaprojects/luposdate3000/src/luposdate3000_buffer_manager_persistent/src/jvmMain/kotlin/lupos/buffer_manager/BufferManager.kt:128"/*SOURCE_FILE_END*/ },
                {
                    SanityCheck.check({ /*SOURCE_FILE_START*/"D:/ideaprojects/luposdate3000/src/luposdate3000_buffer_manager_persistent/src/jvmMain/kotlin/lupos/buffer_manager/BufferManager.kt:130"/*SOURCE_FILE_END*/ }, { pageid < counter })
                    for (i in 0 until freeArrayLength) {
                        SanityCheck.check({ /*SOURCE_FILE_START*/"D:/ideaprojects/luposdate3000/src/luposdate3000_buffer_manager_persistent/src/jvmMain/kotlin/lupos/buffer_manager/BufferManager.kt:132"/*SOURCE_FILE_END*/ }, { freeArray[i] != pageid })
                    }
                }
            )
            findOpenID(
                pageid = pageid,
                onFound = { openId ->
                    SanityCheck.check({ /*SOURCE_FILE_START*/"D:/ideaprojects/luposdate3000/src/luposdate3000_buffer_manager_persistent/src/jvmMain/kotlin/lupos/buffer_manager/BufferManager.kt:139"/*SOURCE_FILE_END*/ }, { openPagesRefcounters[openId] >= 1 })
                    if (openPagesRefcounters[openId] == 1) {
                        datafile.seek(BufferManagerPage.BUFFER_MANAGER_PAGE_SIZE_IN_BYTES.toLong() * pageid)
                        datafile.write(openPages[openId], 0, BufferManagerPage.BUFFER_MANAGER_PAGE_SIZE_IN_BYTES)
                        SanityCheck(
                            { /*SOURCE_FILE_START*/"D:/ideaprojects/luposdate3000/src/luposdate3000_buffer_manager_persistent/src/jvmMain/kotlin/lupos/buffer_manager/BufferManager.kt:144"/*SOURCE_FILE_END*/ },
                            {
                                val cmp = ByteArray(BufferManagerPage.BUFFER_MANAGER_PAGE_SIZE_IN_BYTES)
                                datafile.seek(BufferManagerPage.BUFFER_MANAGER_PAGE_SIZE_IN_BYTES.toLong() * pageid)
                                datafile.readFully(cmp, 0, BufferManagerPage.BUFFER_MANAGER_PAGE_SIZE_IN_BYTES)
                                for (i in 0 until BufferManagerPage.BUFFER_MANAGER_PAGE_SIZE_IN_BYTES) {
                                    SanityCheck.check({ /*SOURCE_FILE_START*/"D:/ideaprojects/luposdate3000/src/luposdate3000_buffer_manager_persistent/src/jvmMain/kotlin/lupos/buffer_manager/BufferManager.kt:150"/*SOURCE_FILE_END*/ }, { cmp[i] == openPages[openId][i] })
                                }
                            }
                        )
                        openPagesMapping[openId] = -1
                        SanityCheck.check({ /*SOURCE_FILE_START*/"D:/ideaprojects/luposdate3000/src/luposdate3000_buffer_manager_persistent/src/jvmMain/kotlin/lupos/buffer_manager/BufferManager.kt:155"/*SOURCE_FILE_END*/ }, { BufferManagerPage.getPageID(openPages[openId]) == pageid }, { "${BufferManagerPage.getPageID(openPages[openId])} $pageid" })
                        BufferManagerPage.setPageID(openPages[openId], -1)
                        SanityCheck(
                            { /*SOURCE_FILE_START*/"D:/ideaprojects/luposdate3000/src/luposdate3000_buffer_manager_persistent/src/jvmMain/kotlin/lupos/buffer_manager/BufferManager.kt:158"/*SOURCE_FILE_END*/ },
                            {
                                openPages[openId] = BufferManagerPage.create()
                            }
                        )
                    }
                    openPagesRefcounters[openId]--
                },
                onNotFound = {
                    SanityCheck.checkUnreachable()
                }
            )
        }
    }

    public actual override fun getPage(call_location: String, pageid: Int): ByteArray {
        SanityCheck.println_buffermanager { "BufferManager.getPage($pageid) : $call_location" }
        SanityCheck.check({ /*SOURCE_FILE_START*/"D:/ideaprojects/luposdate3000/src/luposdate3000_buffer_manager_persistent/src/jvmMain/kotlin/lupos/buffer_manager/BufferManager.kt:175"/*SOURCE_FILE_END*/ }, { !closed })
        var openId2 = -1
        lock.withWriteLock {
            SanityCheck(
                { /*SOURCE_FILE_START*/"D:/ideaprojects/luposdate3000/src/luposdate3000_buffer_manager_persistent/src/jvmMain/kotlin/lupos/buffer_manager/BufferManager.kt:179"/*SOURCE_FILE_END*/ },
                {
                    SanityCheck.check({ /*SOURCE_FILE_START*/"D:/ideaprojects/luposdate3000/src/luposdate3000_buffer_manager_persistent/src/jvmMain/kotlin/lupos/buffer_manager/BufferManager.kt:181"/*SOURCE_FILE_END*/ }, { pageid < counter }, { "pageid < counter :: $pageid < $counter" })
                    SanityCheck.check({ /*SOURCE_FILE_START*/"D:/ideaprojects/luposdate3000/src/luposdate3000_buffer_manager_persistent/src/jvmMain/kotlin/lupos/buffer_manager/BufferManager.kt:182"/*SOURCE_FILE_END*/ }, { pageid >= 0 }, { "pageid >= 0 :: $pageid >= 0" })
                    for (i in 0 until freeArrayLength) {
                        SanityCheck.check({ /*SOURCE_FILE_START*/"D:/ideaprojects/luposdate3000/src/luposdate3000_buffer_manager_persistent/src/jvmMain/kotlin/lupos/buffer_manager/BufferManager.kt:184"/*SOURCE_FILE_END*/ }, { freeArray[i] != pageid }, { "freeArray[$i] != pageid :: ${freeArray[i]} != $pageid" })
                    }
                }
            )
            findOpenID(
                pageid = pageid,
                onFound = { openId ->
                    openId2 = openId
                },
                onNotFound = {
                    openId2 = 0
                    while (openId2 < cacheSize) {
                        if (openPagesRefcounters[openId2] == 0) {
                            break
                        }
                        openId2++
                    }
                    if (openId2 == cacheSize) {
                        throw Exception("no more pages available")
                    }
                    datafile.seek(BufferManagerPage.BUFFER_MANAGER_PAGE_SIZE_IN_BYTES.toLong() * pageid)
                    datafile.readFully(openPages[openId2], 0, BufferManagerPage.BUFFER_MANAGER_PAGE_SIZE_IN_BYTES)
                    openPagesMapping[openId2] = pageid
                    SanityCheck.check({ /*SOURCE_FILE_START*/"D:/ideaprojects/luposdate3000/src/luposdate3000_buffer_manager_persistent/src/jvmMain/kotlin/lupos/buffer_manager/BufferManager.kt:207"/*SOURCE_FILE_END*/ }, { BufferManagerPage.getPageID(openPages[openId2]) == -1 }, { "BufferManagerPage.getPageID(openPages[openId2]) :: ${BufferManagerPage.getPageID(openPages[openId2])}" })
                    BufferManagerPage.setPageID(openPages[openId2], pageid)
                }
            )
            openPagesRefcounters[openId2]++
            SanityCheck.check({ /*SOURCE_FILE_START*/"D:/ideaprojects/luposdate3000/src/luposdate3000_buffer_manager_persistent/src/jvmMain/kotlin/lupos/buffer_manager/BufferManager.kt:212"/*SOURCE_FILE_END*/ }, { BufferManagerPage.getPageID(openPages[openId2]) == pageid })
        }
        SanityCheck.check({ /*SOURCE_FILE_START*/"D:/ideaprojects/luposdate3000/src/luposdate3000_buffer_manager_persistent/src/jvmMain/kotlin/lupos/buffer_manager/BufferManager.kt:214"/*SOURCE_FILE_END*/ }, { openId2 != -1 })
        return openPages[openId2]
    }

    public actual /*suspend*/ override fun allocPage(call_location: String): Int {
        SanityCheck.check({ /*SOURCE_FILE_START*/"D:/ideaprojects/luposdate3000/src/luposdate3000_buffer_manager_persistent/src/jvmMain/kotlin/lupos/buffer_manager/BufferManager.kt:219"/*SOURCE_FILE_END*/ }, { !closed })
        var pageid: Int = -1
        lock.withWriteLock {
            if (freeArrayLength > 0) {
                freeArrayLength--
                freelistfile.seek(freelistfileOffsetFreeLen)
                freelistfile.writeInt(freeArrayLength)
                pageid = freeArray[freeArrayLength]
            } else {
                pageid = counter++
                freelistfile.seek(freelistfileOffsetCounter)
                freelistfile.writeInt(counter)
                val minlen = BufferManagerPage.BUFFER_MANAGER_PAGE_SIZE_IN_BYTES.toLong() * (pageid + 1)
                if (datafilelength < minlen) {
                    datafile.seek(BufferManagerPage.BUFFER_MANAGER_PAGE_SIZE_IN_BYTES.toLong() * pageid)
                    datafile.write(openPages[0], 0, BufferManagerPage.BUFFER_MANAGER_PAGE_SIZE_IN_BYTES)
                    datafilelength = minlen
                }
            }
            SanityCheck(
                { /*SOURCE_FILE_START*/"D:/ideaprojects/luposdate3000/src/luposdate3000_buffer_manager_persistent/src/jvmMain/kotlin/lupos/buffer_manager/BufferManager.kt:239"/*SOURCE_FILE_END*/ },
                {
                    SanityCheck.check({ /*SOURCE_FILE_START*/"D:/ideaprojects/luposdate3000/src/luposdate3000_buffer_manager_persistent/src/jvmMain/kotlin/lupos/buffer_manager/BufferManager.kt:241"/*SOURCE_FILE_END*/ }, { pageid < counter })
                    for (i in 0 until freeArrayLength) {
                        SanityCheck.check({ /*SOURCE_FILE_START*/"D:/ideaprojects/luposdate3000/src/luposdate3000_buffer_manager_persistent/src/jvmMain/kotlin/lupos/buffer_manager/BufferManager.kt:243"/*SOURCE_FILE_END*/ }, { freeArray[i] != pageid })
                    }
                }
            )
        }
        SanityCheck.println_buffermanager { "BufferManager.allocPage($pageid) : $call_location" }
        return pageid
    }

    public actual /*suspend*/ override fun deletePage(call_location: String, pageid: Int): Unit = lock.withWriteLock {
        SanityCheck.println_buffermanager { "BufferManager.deletePage($pageid) : $call_location" }
        SanityCheck(
            { /*SOURCE_FILE_START*/"D:/ideaprojects/luposdate3000/src/luposdate3000_buffer_manager_persistent/src/jvmMain/kotlin/lupos/buffer_manager/BufferManager.kt:255"/*SOURCE_FILE_END*/ },
            {
                SanityCheck.check({ /*SOURCE_FILE_START*/"D:/ideaprojects/luposdate3000/src/luposdate3000_buffer_manager_persistent/src/jvmMain/kotlin/lupos/buffer_manager/BufferManager.kt:257"/*SOURCE_FILE_END*/ }, { !closed })
                SanityCheck.check({ /*SOURCE_FILE_START*/"D:/ideaprojects/luposdate3000/src/luposdate3000_buffer_manager_persistent/src/jvmMain/kotlin/lupos/buffer_manager/BufferManager.kt:258"/*SOURCE_FILE_END*/ }, { pageid < counter })
                for (i in 0 until freeArrayLength) {
                    SanityCheck.check({ /*SOURCE_FILE_START*/"D:/ideaprojects/luposdate3000/src/luposdate3000_buffer_manager_persistent/src/jvmMain/kotlin/lupos/buffer_manager/BufferManager.kt:260"/*SOURCE_FILE_END*/ }, { freeArray[i] != pageid })
                }
            }
        )
        findOpenID(
            pageid = pageid,
            onFound = { openId ->
                SanityCheck.check({ /*SOURCE_FILE_START*/"D:/ideaprojects/luposdate3000/src/luposdate3000_buffer_manager_persistent/src/jvmMain/kotlin/lupos/buffer_manager/BufferManager.kt:267"/*SOURCE_FILE_END*/ }, { openPagesRefcounters[openId] == 1 })
                SanityCheck.check({ /*SOURCE_FILE_START*/"D:/ideaprojects/luposdate3000/src/luposdate3000_buffer_manager_persistent/src/jvmMain/kotlin/lupos/buffer_manager/BufferManager.kt:268"/*SOURCE_FILE_END*/ }, { BufferManagerPage.getPageID(openPages[openId]) == pageid })
                BufferManagerPage.setPageID(openPages[openId], -1)
                SanityCheck(
                    { /*SOURCE_FILE_START*/"D:/ideaprojects/luposdate3000/src/luposdate3000_buffer_manager_persistent/src/jvmMain/kotlin/lupos/buffer_manager/BufferManager.kt:271"/*SOURCE_FILE_END*/ },
                    {
                        openPages[openId] = BufferManagerPage.create()
                    }
                )
                openPagesRefcounters[openId]--
                openPagesMapping[openId] = -1
                if (freeArrayLength >= freeArray.size) {
                    val arr = IntArray(freeArrayLength * 2)
                    freeArray.copyInto(arr)
                    freeArray = arr
                }
                freeArray[freeArrayLength] = pageid
                freelistfile.seek(freelistfileOffsetData + 4 * freeArrayLength)
                freelistfile.writeInt(pageid)
                freeArrayLength++
                freelistfile.seek(freelistfileOffsetFreeLen)
                freelistfile.writeInt(freeArrayLength)
            },
            onNotFound = {
                SanityCheck.checkUnreachable()
            }
        )
    }

    @ProguardTestAnnotation
    public actual override fun close() {
        SanityCheck.check({ /*SOURCE_FILE_START*/"D:/ideaprojects/luposdate3000/src/luposdate3000_buffer_manager_persistent/src/jvmMain/kotlin/lupos/buffer_manager/BufferManager.kt:298"/*SOURCE_FILE_END*/ }, { !closed })
        closed = true
        SanityCheck(
            { /*SOURCE_FILE_START*/"D:/ideaprojects/luposdate3000/src/luposdate3000_buffer_manager_persistent/src/jvmMain/kotlin/lupos/buffer_manager/BufferManager.kt:301"/*SOURCE_FILE_END*/ },
            {
                for (i in 0 until cacheSize) {
                    SanityCheck.check({ /*SOURCE_FILE_START*/"D:/ideaprojects/luposdate3000/src/luposdate3000_buffer_manager_persistent/src/jvmMain/kotlin/lupos/buffer_manager/BufferManager.kt:304"/*SOURCE_FILE_END*/ }, { openPagesRefcounters[i] == 0 })
                }
            }
        )
        datafile.close()
        freelistfile.close()
    }

    @ProguardTestAnnotation
    public actual override fun getNumberOfAllocatedPages(): Int = counter - freeArrayLength

    @ProguardTestAnnotation
    public actual override fun getNumberOfReferencedPages(): Int = openPagesRefcounters.sum()

    init {
        File(instance.BUFFER_HOME).mkdirs()
        val flag = BufferManagerExt.allowInitFromDisk && File(instance.BUFFER_HOME + BufferManagerExt.fileEnding).exists()
        datafile = RandomAccessFile(instance.BUFFER_HOME + BufferManagerExt.fileEnding, "rw")
        freelistfile = RandomAccessFile(instance.BUFFER_HOME + BufferManagerExt.fileEndingFree, "rw")
        if (flag) {
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
