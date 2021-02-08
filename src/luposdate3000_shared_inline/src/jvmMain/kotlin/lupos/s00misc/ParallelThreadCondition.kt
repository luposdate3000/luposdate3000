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
package lupos.modulename

import kotlin.jvm.JvmField

internal actual class ParallelThreadCondition {
    @JvmField
    val myMonitorObject = this as Object

    @JvmField
    var wasSignalled = false
    internal actual inline fun waitCondition(crossinline condition: () -> Boolean) {
        synchronized(myMonitorObject) {
            if (!wasSignalled && condition()) {
                try {
                    myMonitorObject.wait()
                } catch (e: Exception) {
                }
            }
            wasSignalled = false
        }
    }

    @Suppress("NOTHING_TO_INLINE")
    internal actual inline fun signal() {
        synchronized(myMonitorObject) {
            wasSignalled = true
            myMonitorObject.notify()
        }
    }
}
