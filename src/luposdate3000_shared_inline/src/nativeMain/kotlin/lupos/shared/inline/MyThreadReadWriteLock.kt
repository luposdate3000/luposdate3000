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

internal actual class MyThreadReadWriteLock {

    @JvmField
    internal val uuid = UUID_Counter.getNextUUID()

    internal actual inline fun getUUID() = uuid
    internal actual inline fun downgradeToReadLock() = TODO("MyThreadReadWriteLock")
    internal actual inline fun readLock() = TODO("MyThreadReadWriteLock")
    internal actual inline fun readUnlock() = TODO("MyThreadReadWriteLock")
    internal actual inline fun writeLock() = TODO("MyThreadReadWriteLock")
    internal actual inline fun tryWriteLock(): Boolean = TODO("MyThreadReadWriteLock")
    internal actual inline fun writeUnlock() = TODO("MyThreadReadWriteLock")
    internal actual inline fun <T> withReadLock(crossinline action: () -> T): T {
        readLock()
        try {
            return action()
        } finally {
            readUnlock()
        }
    }

    internal actual inline fun <T> withWriteLock(crossinline action: () -> T): T {
        writeLock()
        try {
            return action()
        } finally {
            writeUnlock()
        }
    }
}
