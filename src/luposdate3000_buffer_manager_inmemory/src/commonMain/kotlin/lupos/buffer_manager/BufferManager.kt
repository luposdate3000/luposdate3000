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
import lupos.shared_inline.BufferManagerPage
import kotlin.jvm.JvmField

public class BufferManager public constructor(instance: Luposdate3000Instance) : IBufferManager {

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
    internal var allPages = Array<ByteArray>(128) { BufferManagerPage.create() }

    @JvmField
    internal var allPagesRefcounters = IntArray(128)

    @JvmField
    internal var counter = 0

    @JvmField
    internal val lock = MyReadWriteLock()

    @JvmField
    internal var freeList = IntArray(128)

    @JvmField
    internal var freeListSize = 0

    @ProguardTestAnnotation
    public fun getNumberOfAllocatedPages(): Int = counter - freeListSize

    @ProguardTestAnnotation
    public fun getNumberOfReferencedPages(): Int {
        val res = allPagesRefcounters.sum()
        SanityCheck {
            val tmp = mutableMapOf<Int, Int>()
            for (i in 0 until counter) {
                if (allPagesRefcounters[i] != 0) {
                    tmp[i] = allPagesRefcounters[i]
                }
            }
            SanityCheck.println_buffermanager { "getNumberOfReferencedPages = $tmp" }
        }
        return res
    }

    public override fun flushPage(call_location: String, pageid: Int) {
        SanityCheck.println_buffermanager { "BufferManager.flushPage($pageid) : $call_location" }
    }

    public override fun releasePage(call_location: String, pageid: Int) {
        SanityCheck.println_buffermanager { "BufferManager.releasePage($pageid) : $call_location" }
        SanityCheck.check({ allPagesRefcounters[pageid] > 0 }, { "Failed requirement allPagesRefcounters[$pageid] = ${allPagesRefcounters[pageid]} > 0" })
        allPagesRefcounters[pageid]--
    }

    public override fun getPage(call_location: String, pageid: Int): ByteArray {
        SanityCheck.println_buffermanager { "BufferManager.getPage($pageid) : $call_location" }
        // no locking required, assuming an assignment to 'allPages' is atomic
        SanityCheck {
            SanityCheck.check({ pageid < counter }, { "$pageid < $counter" })
            SanityCheck.check({ pageid >= 0 }, { "$pageid >= 0" })
            for (i in 0 until freeListSize) {
                SanityCheck.check { freeList[i] != pageid }
            }
        }
        SanityCheck.check { BufferManagerPage.getPageID(allPages[pageid]) == pageid }
        allPagesRefcounters[pageid]++
        return allPages[pageid]
    }

    public /*suspend*/ override fun allocPage(call_location: String): Int {
        var pageid: Int = 0
        lock.withWriteLock {
            if (freeListSize > 0) {
                pageid = freeList[--freeListSize]
            } else {
                if (counter == allPages.size) {
                    var size = counter * 2
                    if (size < 128) {
                        size = 128
                    }
                    val tmp = Array<ByteArray>(size) {
                        val res: ByteArray = if (it < counter) {
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
            SanityCheck.check({ BufferManagerPage.getPageID(allPages[pageid]) == -1 }, { "${BufferManagerPage.getPageID(allPages[pageid])} $pageid" })
            BufferManagerPage.setPageID(allPages[pageid], pageid)
        }
        SanityCheck.println_buffermanager { "BufferManager.allocPage($pageid) : $call_location" }
        return pageid
    }

    public /*suspend*/ override fun deletePage(call_location: String, pageid: Int): Unit = lock.withWriteLock {
        SanityCheck.println_buffermanager { "BufferManager.deletePage($pageid) : $call_location" }
        SanityCheck {
            for (i in 0 until freeListSize) {
                SanityCheck.check { freeList[i] != pageid }
            }
        }
        SanityCheck.check({ allPagesRefcounters[pageid] == 1 }, { "Failed requirement allPagesRefcounters[$pageid] = ${allPagesRefcounters[pageid]} == 1" })
        allPagesRefcounters[pageid] = 0
        SanityCheck.check { BufferManagerPage.getPageID(allPages[pageid]) == pageid }
        BufferManagerPage.setPageID(allPages[pageid], -1)
        SanityCheck {
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
    public override fun close() {
        SanityCheck {
            val allErrors = mutableMapOf<Int, Int>()
            for (i in 0 until counter) {
                if (allPagesRefcounters[i] != 0) {
                    allErrors[i] = allPagesRefcounters[i]
                }
            }
            SanityCheck.check({ allErrors.size == 0 }, { "$allErrors" })
        }
    }
}
