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

import lupos.shared.ParallelThreadJob

internal actual object ParallelThread {
    internal actual inline fun <T> runBlocking(crossinline action: () -> T): T {
        return action()
    }

    internal actual inline fun launch(crossinline action: () -> Unit): ParallelThreadJob = TODO("ParallelThread")

    @Suppress("NOTHING_TO_INLINE")
    internal actual inline fun delay(milliseconds: Long) {
    }

    @Suppress("NOTHING_TO_INLINE")
    internal actual inline fun createCondition(): ParallelThreadCondition {
        return ParallelThreadCondition()
    }

    @Suppress("NOTHING_TO_INLINE")
    internal actual inline fun <T> createQueue(terminationValue: T): ParallelThreadQueue<T> {
        return ParallelThreadQueue<T>(terminationValue)
    }
}
