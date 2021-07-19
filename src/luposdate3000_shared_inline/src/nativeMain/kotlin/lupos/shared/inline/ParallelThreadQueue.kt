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

import lupos.shared.NotImplementedException

internal actual class ParallelThreadQueue<T> {
    actual constructor(terminationValue: T) {
    }

    internal actual inline fun send(value: T) {
        throw NotImplementedException("ParallelThreadQueue", "send not implemented")
    }

    internal actual inline fun close() {
        throw NotImplementedException("ParallelThreadQueue", "close not implemented")
    }

    internal actual inline fun receive(): T {
        throw NotImplementedException("ParallelThreadQueue", "receive not implemented")
    }
}
