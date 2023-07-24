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

import lupos.shared.UUID_Counter
import kotlin.jvm.JvmField

public actual class MyThreadReadWriteLock {

    @JvmField
    public val uuid = UUID_Counter.getNextUUID()

    public actual inline fun getUUID() = uuid
    public actual inline fun downgradeToReadLock() = TODO("MyThreadReadWriteLock")
    public actual inline fun readLock() = TODO("MyThreadReadWriteLock")
    public actual inline fun readUnlock() = TODO("MyThreadReadWriteLock")
    public actual inline fun writeLock() = TODO("MyThreadReadWriteLock")
    public actual inline fun tryWriteLock(): Boolean = TODO("MyThreadReadWriteLock")
    public actual inline fun writeUnlock() = TODO("MyThreadReadWriteLock")
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
