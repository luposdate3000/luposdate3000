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
package lupos.s00misc

import java.util.concurrent.Semaphore
import kotlin.contracts.InvocationKind.EXACTLY_ONCE
import kotlin.contracts.contract

@OptIn(kotlin.contracts.ExperimentalContracts::class)
public actual class MyThreadLock {
    public companion object {
        private var uuidCounter = 0L
    }

    public val uuid: Long = uuidCounter++
    public val semaphore: Semaphore = Semaphore(1)

    @Suppress("NOTHING_TO_INLINE")
    public actual inline fun getUUID(): Long = uuid

    @Suppress("NOTHING_TO_INLINE")
    public actual inline fun lock() {
        semaphore.acquire()
    }

    @Suppress("NOTHING_TO_INLINE")
    public actual inline fun unlock() {
        semaphore.release()
    }

    @Suppress("NOTHING_TO_INLINE")
    public actual inline fun tryLock(): Boolean {
        return semaphore.tryAcquire()
    }

    public actual inline fun <T> withLock(crossinline action: () -> T): T {
        contract { callsInPlace(action, EXACTLY_ONCE) }
        lock()
        try {
            return action()
        } finally {
            unlock()
        }
    }
}
