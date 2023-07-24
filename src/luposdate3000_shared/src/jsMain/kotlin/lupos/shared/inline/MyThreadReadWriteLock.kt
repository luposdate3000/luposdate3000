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
package lupos.shared.inline

import lupos.shared.SanityCheck
import lupos.shared.UUID_Counter

public actual class MyThreadReadWriteLock {
    public val uuid = UUID_Counter.getNextUUID()

    @Suppress("NOTHING_TO_INLINE")
    public actual inline fun getUUID() = uuid

    public var lockedRead = 0

    public var lockedWrite = false

    @Suppress("NOTHING_TO_INLINE")
    public actual inline fun downgradeToReadLock() {
  if(SanityCheck.enabled)            {
                if (!lockedWrite) {
                    throw Exception("something went wrong 1")
                }
                lockedRead = 1
                lockedWrite = false
            }
        
    }

    @Suppress("NOTHING_TO_INLINE")
    public actual inline fun readLock() {
  if(SanityCheck.enabled)            {
                if (lockedWrite) {
                    throw Exception("something went wrong 2")
                }
                lockedRead++
            }
        
    }

    @Suppress("NOTHING_TO_INLINE")
    public actual inline fun readUnlock() {
  if(SanityCheck.enabled)            {
                if (lockedRead <= 0) {
                    throw Exception("something went wrong 3")
                }
                lockedRead--
            }
        
    }

    @Suppress("NOTHING_TO_INLINE")
    public actual inline fun writeLock() {
  if(SanityCheck.enabled)            {
                if (lockedRead > 0 || lockedWrite) {
                    throw Exception("something went wrong 4 $lockedRead $lockedWrite")
                }
                lockedWrite = true
            }
        
    }

    @Suppress("NOTHING_TO_INLINE")
    public actual inline fun tryWriteLock(): Boolean {
  if(SanityCheck.enabled)            {
                if (lockedRead > 0 || lockedWrite) {
                    throw Exception("something went wrong 5 $lockedRead $lockedWrite")
                }
                lockedWrite = true
            }
        
        return true
    }

    @Suppress("NOTHING_TO_INLINE")
    public actual inline fun writeUnlock() {
  if(SanityCheck.enabled)            {
                if (!lockedWrite) {
                    throw Exception("something went wrong 6")
                }
                lockedWrite = false
            }
        
    }

    public actual inline fun <T> withReadLock(crossinline action: () -> T): T {
        readLock()
        try {
            return action()
        } finally {
            readUnlock()
        }
    }

    public actual inline fun <T> withWriteLock(crossinline action: () -> T): T {
        writeLock()
        try {
            return action()
        } finally {
            writeUnlock()
        }
    }
}
