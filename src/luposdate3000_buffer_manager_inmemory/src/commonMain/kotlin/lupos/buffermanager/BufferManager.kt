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
package lupos.buffermanager

import lupos.ProguardTestAnnotation
import lupos.s00misc.BUFFER_MANAGER_USE_FREE_LIST
import lupos.s00misc.MyReadWriteLock
import lupos.s00misc.SanityCheck
import kotlin.jvm.JvmField

public class BufferManager internal constructor(@JvmField public val name: String) {
    @ProguardTestAnnotation
    public constructor() : this("")

    /*
     * each type safe page-manager safes to its own store
     * using another layer of indirection,
     * to be able to share this code across different type-safe managers as
     * - triple store
     * - dictionary (currently not implemented)
     * - temporary result rows (currently not implemented)
     * additionally this should make it more easy to exchange this with on disk storage
     */
    private var allPages = Array<BufferManagerPage>(100) { createBufferManagerPage() }

    private var allPagesRefcounters = IntArray(100)

    private var counter = 0

    private val lock = MyReadWriteLock()

    private val freeList = mutableListOf<Int>()
    /*suspend*/ private fun clearAssumeLocks() {
        counter = 0
        SanityCheck {
            for (i in 0 until counter) {
                SanityCheck.check({ allPagesRefcounters[i] == 0 }, { "Failed requirement allPagesRefcounters[$i] = ${allPagesRefcounters[i]} == 0" })
            }
        }
        allPages = Array<BufferManagerPage>(100) { createBufferManagerPage() }
        allPagesRefcounters = IntArray(100)
        if (BUFFER_MANAGER_USE_FREE_LIST) {
            freeList.clear()
        }
    }

    @ProguardTestAnnotation
    public fun getNumberOfAllocatedPages(): Int = counter - freeList.size

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

    public fun flushPage(call_location: String, pageid: Int) {
        SanityCheck.println_buffermanager { "BufferManager.flushPage($pageid) : $call_location" }
    }

    public fun releasePage(call_location: String, pageid: Int) {
        SanityCheck.println_buffermanager { "BufferManager.releasePage($pageid) : $call_location" }
        SanityCheck.check({ allPagesRefcounters[pageid] > 0 }, { "Failed requirement allPagesRefcounters[$pageid] = ${allPagesRefcounters[pageid]} > 0" })
        allPagesRefcounters[pageid]--
    }

    public fun getPage(call_location: String, pageid: Int): BufferManagerPage {
        SanityCheck.println_buffermanager { "BufferManager.getPage($pageid) : $call_location" }
        // no locking required, assuming an assignment to 'allPages' is atomic
        SanityCheck {
            SanityCheck.check({ pageid < counter }, { "$pageid < $counter" })
            SanityCheck.check({ pageid >= 0 }, { "$pageid >= 0" })
            if (BUFFER_MANAGER_USE_FREE_LIST) {
                SanityCheck.check { !freeList.contains(pageid) }
            }
        }
        SanityCheck.check { allPages[pageid].getPageID() == pageid }
        allPagesRefcounters[pageid]++
        return allPages[pageid]
    }

    public /*suspend*/ fun allocPage(call_location: String): Int {
        var pageid: Int = 0
        lock.withWriteLock {
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
                    val tmp = Array<BufferManagerPage>(size) {
                        val res: BufferManagerPage = if (it < counter) {
                            allPages[it]
                        } else {
                            createBufferManagerPage()
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
            SanityCheck.check({ allPages[pageid].getPageID() == -1 }, { "${allPages[pageid].getPageID()}" })
            allPages[pageid].setPageID(pageid)
        }
        SanityCheck.println_buffermanager { "BufferManager.allocPage($pageid) : $call_location" }
        return pageid
    }

    public /*suspend*/ fun deletePage(call_location: String, pageid: Int): Unit = lock.withWriteLock {
        SanityCheck.println_buffermanager { "BufferManager.deletePage($pageid) : $call_location" }
        SanityCheck {
            if (BUFFER_MANAGER_USE_FREE_LIST) {
                SanityCheck.check { !freeList.contains(pageid) }
            }
        }
        SanityCheck.check({ allPagesRefcounters[pageid] == 1 }, { "Failed requirement allPagesRefcounters[$pageid] = ${allPagesRefcounters[pageid]} == 1" })
        allPagesRefcounters[pageid] = 0
        SanityCheck.check { allPages[pageid].getPageID() == pageid }
        allPages[pageid].setPageID(-1)
        SanityCheck {
            allPages[pageid] = createBufferManagerPage()
        }
        if (BUFFER_MANAGER_USE_FREE_LIST) {
            freeList.add(pageid)
            if (freeList.size == counter) {
                clearAssumeLocks()
            }
        } else {
            if (counter == 0) {
                clearAssumeLocks()
            }
        }
    }

    @ProguardTestAnnotation
    public fun close() {
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
