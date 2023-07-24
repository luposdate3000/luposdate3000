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
import java.util.concurrent.locks.ReentrantReadWriteLock
import kotlin.contracts.InvocationKind.EXACTLY_ONCE
import kotlin.contracts.contract
import kotlin.jvm.JvmField

@OptIn(kotlin.contracts.ExperimentalContracts::class)
public actual class MyThreadReadWriteLock {
    public companion object {
        var isEnabled = true
    }

    @JvmField
    public val uuid = UUID_Counter.getNextUUID()

    @JvmField
    public val lock = ReentrantReadWriteLock()

    @Suppress("NOTHING_TO_INLINE")
    public actual inline fun getUUID() = uuid

    @Suppress("NOTHING_TO_INLINE")
    public actual inline fun downgradeToReadLock() {
        if (isEnabled) {
            try {
                lock.readLock().lock()
                lock.writeLock().unlock()
            } catch (e: Throwable) {
                e.printStackTrace()
                val msg = e.message
                if (msg != null && msg.contains("Maximum lock count exceeded")) {
                    isEnabled = false
                }
            }
        }
    }

    @Suppress("NOTHING_TO_INLINE")
    public actual inline fun readLock() {
        if (isEnabled) {
            try {
                lock.readLock().lock()
            } catch (e: Throwable) {
                e.printStackTrace()
                val msg = e.message
                if (msg != null && msg.contains("Maximum lock count exceeded")) {
                    isEnabled = false
                }
            }
        }
    }

    @Suppress("NOTHING_TO_INLINE")
    public actual inline fun readUnlock() {
        if (isEnabled) {
            try {
                lock.readLock().unlock()
            } catch (e: Throwable) {
                e.printStackTrace()
                val msg = e.message
                if (msg != null && msg.contains("Maximum lock count exceeded")) {
                    isEnabled = false
                }
            }
        }
    }

    @Suppress("NOTHING_TO_INLINE")
    public actual inline fun writeLock() {
        if (isEnabled) {
            try {
                lock.writeLock().lock()
            } catch (e: Throwable) {
                e.printStackTrace()
                val msg = e.message
                if (msg != null && msg.contains("Maximum lock count exceeded")) {
                    isEnabled = false
                }
            }
        }
    }

    @Suppress("NOTHING_TO_INLINE")
    public actual inline fun tryWriteLock(): Boolean {
        if (isEnabled) {
            try {
                return lock.writeLock().tryLock()
            } catch (e: Throwable) {
                e.printStackTrace()
                val msg = e.message
                if (msg != null && msg.contains("Maximum lock count exceeded")) {
                    isEnabled = false
                }
            }
        }
return true
    }

    @Suppress("NOTHING_TO_INLINE")
    public actual inline fun writeUnlock() {
        if (isEnabled) {
            try {
                lock.writeLock().unlock()
            } catch (e: Throwable) {
                e.printStackTrace()
                val msg = e.message
                if (msg != null && msg.contains("Maximum lock count exceeded")) {
                    isEnabled = false
                }
            }
        }
    }

    public actual inline fun <T> withReadLock(crossinline action: () -> T): T {
        contract { callsInPlace(action, EXACTLY_ONCE) }
        readLock()
        try {
            return action()
        } finally {
            readUnlock()
        }
    }

    public actual inline fun <T> withWriteLock(crossinline action: () -> T): T {
        contract { callsInPlace(action, EXACTLY_ONCE) }
        writeLock()
        try {
            return action()
        } finally {
            writeUnlock()
        }
    }
}
