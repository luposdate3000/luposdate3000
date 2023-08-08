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

import lupos.shared.UnreachableException
import kotlin.contracts.InvocationKind.AT_MOST_ONCE
import kotlin.contracts.contract

@OptIn(kotlin.contracts.ExperimentalContracts::class)
public object SanityCheckOff {
    public val enabled: Boolean = false
    public inline fun println_buffermanager(@Suppress("UNUSED_PARAMETER") crossinline s: () -> Any?) {
        contract { callsInPlace(s, AT_MOST_ONCE) }
    }

    public inline fun println_nodemanager(@Suppress("UNUSED_PARAMETER") crossinline s: () -> Any?) {
        contract { callsInPlace(s, AT_MOST_ONCE) }
    }

    public inline fun println(@Suppress("UNUSED_PARAMETER") crossinline s: () -> Any?) {
        contract { callsInPlace(s, AT_MOST_ONCE) }
    }

    public inline operator fun invoke(@Suppress("UNUSED_PARAMETER") crossinline filename: () -> String, @Suppress("UNUSED_PARAMETER") crossinline action: () -> Unit) {
    }

    /*suspend*/ public inline fun suspended(@Suppress("UNUSED_PARAMETER") crossinline action: /*suspend*/ () -> Unit) {
        contract { callsInPlace(action, AT_MOST_ONCE) }
    }

    public inline fun <T> helper(@Suppress("UNUSED_PARAMETER") crossinline action: () -> Unit): T? {
        contract { callsInPlace(action, AT_MOST_ONCE) }
        return null
    }

    public inline fun check(@Suppress("UNUSED_PARAMETER") crossinline filename: () -> String, @Suppress("UNUSED_PARAMETER") crossinline value: () -> Boolean, @Suppress("UNUSED_PARAMETER") crossinline msg: () -> String) {
        contract { callsInPlace(value, AT_MOST_ONCE) }
    }

    public inline fun check(@Suppress("UNUSED_PARAMETER") crossinline filename: () -> String, @Suppress("UNUSED_PARAMETER") crossinline value: () -> Boolean) {
        contract { callsInPlace(value, AT_MOST_ONCE) }
    }

    @Suppress("NOTHING_TO_INLINE")
    public inline fun checkUnreachable(): Nothing = throw UnreachableException()
}
