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

public actual class MyThreadLock {
    public companion object {
        private var uuidCounter = 0L
    }

    private val uuid = uuidCounter++

    @Suppress("NOTHING_TO_INLINE")
    public actual inline fun getUUID(): Long = uuid
    private var locked: Boolean = false

    @Suppress("NOTHING_TO_INLINE")
    public actual inline fun lock() {
        SanityCheck {
            if (locked) {
                throw Exception("deadlock")
            }
            locked = true
        }
    }

    @Suppress("NOTHING_TO_INLINE")
    public actual inline fun unlock() {
        SanityCheck {
            if (!locked) {
                throw Exception("unlock without previous lock")
            }
            locked = false
        }
    }

    @Suppress("NOTHING_TO_INLINE")
    public actual inline fun tryLock(): Boolean {
        SanityCheck {
            if (locked) {
                throw Exception("deadlock")
            }
            locked = true
        }
        return true
    }

    public actual inline fun <T> withLock(crossinline action: () -> T): T {
        lock()
        try {
            return action()
        } finally {
            unlock()
        }
    }
}
