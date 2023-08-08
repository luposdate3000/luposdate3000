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
import lupos.shared.BufferManagerPage
import lupos.shared.BufferManagerPageWrapper
import lupos.shared.IBufferManager
import lupos.shared.Luposdate3000Instance
import lupos.shared.SanityCheck
import lupos.shared.inline.MyThreadReadWriteLock
import kotlin.jvm.JvmField

public class BufferManager public constructor(@Suppress("UNUSED_PARAMETER") instance: Luposdate3000Instance) : IBufferManager {

    /*
     * each type safe page-manager safes to its own store
     * using another layer of indirection,
     * to be able to share this code across different type-safe managers as
     * - triple store
     * - dictionary
     * - temporary result rows (currently not implemented)
     * additionally this should make it more easy to exchange this with on disk storage
     */
    @JvmField
    internal var allPages = Array(128) { BufferManagerPage.create() }

    @JvmField
    internal var allPagesRefcounters = IntArray(128)

    @JvmField
    internal var counter = 0

    @JvmField
    internal val lock = MyThreadReadWriteLock()

    @JvmField
    internal var freeList = IntArray(128)

    @JvmField
    internal var freeListSize = 0

    @ProguardTestAnnotation
    override fun getNumberOfAllocatedPages(): Int = counter - freeListSize

    @ProguardTestAnnotation
    override fun getNumberOfReferencedPages(): Int {
        val res = allPagesRefcounters.sum()
        if (SanityCheck.enabled) {
            val tmp = mutableMapOf<Int, Int>()
            for (i in 0 until counter) {
                if (allPagesRefcounters[i] != 0) {
                    tmp[i] = allPagesRefcounters[i]
                }
            }
        }
        return res
    }

    override fun flushPage(call_location: String, pageid: Int) {
    }

    override fun releasePage(call_location: String, pageid: Int) {
        if (SanityCheck.enabled) { if (!(allPagesRefcounters[pageid] > 0)) { throw Exception("SanityCheck failed") } }
        allPagesRefcounters[pageid]--
    }

    override fun getPage(call_location: String, pageid: Int): BufferManagerPageWrapper {
        // no locking required, assuming an assignment to 'allPages' is atomic
        if (SanityCheck.enabled) {
            if (SanityCheck.enabled) { if (!(pageid < counter)) { throw Exception("SanityCheck failed") } }
            if (SanityCheck.enabled) { if (!(pageid >= 0)) { throw Exception("SanityCheck failed") } }
            for (i in 0 until freeListSize) {
                if (SanityCheck.enabled) { if (!(freeList[i] != pageid)) { throw Exception("SanityCheck failed") } }
            }
        }
        if (SanityCheck.enabled) { if (!(BufferManagerPage.getPageID(allPages[pageid]) == pageid)) { throw Exception("SanityCheck failed") } }
        allPagesRefcounters[pageid]++
        return allPages[pageid]
    }

    /*suspend*/ override fun allocPage(call_location: String): Int {
        var pageid = 0
        lock.withWriteLock {
            if (freeListSize > 0) {
                pageid = freeList[--freeListSize]
            } else {
                if (counter == allPages.size) {
                    var size = counter * 2
                    val tmp = Array(size) {
                        val res: BufferManagerPageWrapper = if (it < counter) {
                            allPages[it]
                        } else {
                            BufferManagerPage.create()
                        }
                        res
                    }
                    val tmp2 = IntArray(size)
                    allPagesRefcounters.copyInto(tmp2)
                    allPages = tmp
                    allPagesRefcounters = tmp2
                }
                pageid = counter++
            }
            if (SanityCheck.enabled) { if (!(BufferManagerPage.getPageID(allPages[pageid]) == -1)) { throw Exception("SanityCheck failed") } }
            BufferManagerPage.setPageID(allPages[pageid], pageid)
        }
        return pageid
    }

    /*suspend*/ override fun deletePage(call_location: String, pageid: Int): Unit = lock.withWriteLock {
        if (SanityCheck.enabled) {
            for (i in 0 until freeListSize) {
                if (SanityCheck.enabled) { if (!(freeList[i] != pageid)) { throw Exception("SanityCheck failed") } }
            }
        }
        if (SanityCheck.enabled) { if (!(allPagesRefcounters[pageid] == 1)) { throw Exception("SanityCheck failed") } }
        allPagesRefcounters[pageid] = 0
        if (SanityCheck.enabled) { if (!(BufferManagerPage.getPageID(allPages[pageid]) == pageid)) { throw Exception("SanityCheck failed") } }
        BufferManagerPage.setPageID(allPages[pageid], -1)
        if (SanityCheck.enabled) {
            allPages[pageid] = BufferManagerPage.create()
        }
        if (freeListSize == freeList.size) {
            val tmp = IntArray(freeListSize * 2)
            freeList.copyInto(tmp)
            freeList = tmp
        }
        freeList[freeListSize++] = pageid
    }

    @ProguardTestAnnotation
    override fun close() {
        if (SanityCheck.enabled) {
            val allErrors = mutableMapOf<Int, Int>()
            for (i in 0 until counter) {
                if (allPagesRefcounters[i] != 0) {
                    allErrors[i] = allPagesRefcounters[i]
                }
            }
            if (SanityCheck.enabled) { if (!(allErrors.isEmpty())) { throw Exception("SanityCheck failed") } }
        }
    }
}
