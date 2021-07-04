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

import lupos.shared.DictionaryValueType
import lupos.shared.UnreachableException
import kotlin.contracts.InvocationKind.AT_MOST_ONCE
import kotlin.contracts.contract

@OptIn(kotlin.contracts.ExperimentalContracts::class)
internal object SanityCheckOff {
    public val enabled = false
    public val TRIPLE_FLAG_S: DictionaryValueType = 0x00010000
    public val TRIPLE_FLAG_P: DictionaryValueType = 0x00020000
    public val TRIPLE_FLAG_O: DictionaryValueType = 0x00030000
    public val TRIPLE_FLAG_All: DictionaryValueType = TRIPLE_FLAG_S or TRIPLE_FLAG_P or TRIPLE_FLAG_O
    public val TRIPLE_FLAG_NONE: DictionaryValueType = Int.MAX_VALUE - TRIPLE_FLAG_All
    public val ignoreTripleFlag = true
    internal inline fun check_is_S(i: DictionaryValueType) {
    }
    internal inline fun check_is_P(i: DictionaryValueType) {
    }
    internal inline fun check_is_O(i: DictionaryValueType) {
    }
    internal inline fun println_buffermanager(crossinline s: () -> Any?) {
        contract { callsInPlace(s, AT_MOST_ONCE) }
    }

    internal inline fun println_nodemanager(crossinline s: () -> Any?) {
        contract { callsInPlace(s, AT_MOST_ONCE) }
    }

    internal inline fun println(crossinline s: () -> Any?) {
        contract { callsInPlace(s, AT_MOST_ONCE) }
    }

    internal inline operator fun invoke(@Suppress("UNUSED_PARAMETER") crossinline action: () -> Unit) {
    }

    /*suspend*/ internal inline fun suspended(crossinline action: /*suspend*/ () -> Unit) {
        contract { callsInPlace(action, AT_MOST_ONCE) }
    }

    internal inline fun <T> helper(crossinline action: () -> Unit): T? {
        contract { callsInPlace(action, AT_MOST_ONCE) }
        return null
    }

    internal inline fun check(crossinline value: () -> Boolean, @Suppress("UNUSED_PARAMETER") crossinline msg: () -> String) {
        contract { callsInPlace(value, AT_MOST_ONCE) }
    }

    internal inline fun check(crossinline value: () -> Boolean) {
        contract { callsInPlace(value, AT_MOST_ONCE) }
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun checkUnreachable(): Nothing = throw UnreachableException()
}
