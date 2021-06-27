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
internal object SanityCheckOn {
    public val TRIPLE_FLAG_S = 0x00010000
    public val TRIPLE_FLAG_P = 0x00020000
    public val TRIPLE_FLAG_O = 0x00030000
    public val TRIPLE_FLAG_All = TRIPLE_FLAG_S or TRIPLE_FLAG_P or TRIPLE_FLAG_O
    public val ignoreTripleFlag = false

    internal inline fun check_is_S(i: Int) {
        this {
            if (!ignoreTripleFlag) {
                val flag = i and TRIPLE_FLAG_All
                when (flag) {
                    TRIPLE_FLAG_S -> {}
                    TRIPLE_FLAG_P -> TODO("expected subject but found predicate $i")
                    TRIPLE_FLAG_O -> TODO("expected subject but found object $i")
                    else -> TODO("expected subject but found undefined $i")
                }
            }
        }
    }
    internal inline fun check_is_P(i: Int) {
        this {
            if (!ignoreTripleFlag) {
                val flag = i and TRIPLE_FLAG_All
                when (flag) {
                    TRIPLE_FLAG_S -> TODO("expected predicate but found subject $i")
                    TRIPLE_FLAG_P -> {}
                    TRIPLE_FLAG_O -> TODO("expected predicate but found object $i")
                    else -> TODO("expected predicate but found undefined $i")
                }
            }
        }
    }
    internal inline fun check_is_O(i: Int) {
        this {
            if (!ignoreTripleFlag) {
                val flag = i and TRIPLE_FLAG_All
                when (flag) {
                    TRIPLE_FLAG_S -> TODO("expected object but found subject $i")
                    TRIPLE_FLAG_P -> TODO("expected object but found predicate $i")
                    TRIPLE_FLAG_O -> {}
                    else -> TODO("expected object but found undefined $i")
                }
            }
        }
    }
    public val enabled = true
    internal const val SANITYCHECK_PRINTING = false
    internal const val SANITYCHECK_PRINTING_NODEMANAGER = false
    internal const val SANITYCHECK_PRINTING_BUFFERMANAGER = false
    internal inline fun println_buffermanager(crossinline s: () -> Any?) {
        contract { callsInPlace(s, AT_MOST_ONCE) }
        if (SANITYCHECK_PRINTING_BUFFERMANAGER) {
            println(s())
        }
    }

    internal inline fun println_nodemanager(crossinline s: () -> Any?) {
        contract { callsInPlace(s, AT_MOST_ONCE) }
        if (SANITYCHECK_PRINTING_NODEMANAGER) {
            println(s())
        }
    }

    internal inline fun println(crossinline s: () -> Any?) {
        contract { callsInPlace(s, AT_MOST_ONCE) }
        if (SANITYCHECK_PRINTING) {
            println(s())
        }
    }

    internal inline operator fun invoke(crossinline action: () -> Unit) {
        try {
            action()
        } catch (e: Throwable) {
            if (SANITYCHECK_PRINTING) {
                println("Exception during SanityCheck.invoke")
                e.printStackTrace()
            }
            throw e
        }
    }

    /*suspend*/ internal inline fun suspended(crossinline action: /*suspend*/ () -> Unit) {
        contract { callsInPlace(action, AT_MOST_ONCE) }
        try {
            action()
        } catch (e: Throwable) {
            if (SANITYCHECK_PRINTING) {
                println("Exception during SanityCheck.suspended")
                e.printStackTrace()
            }
            throw e
        }
    }

    internal inline fun <T> helper(crossinline action: () -> T): T? {
        contract { callsInPlace(action, AT_MOST_ONCE) }
        return action()
    }

    internal inline fun check(crossinline value: () -> Boolean, crossinline msg: () -> String) {
        contract { callsInPlace(value, AT_MOST_ONCE) }
        try {
            if (!value()) {
                throw Exception("SanityCheck failed :: " + msg())
            }
        } catch (e: Throwable) {
            if (SANITYCHECK_PRINTING) {
                println("Exception during SanityCheck.check")
                e.printStackTrace()
            }
            throw e
        }
    }

    internal inline fun check(crossinline value: () -> Boolean) {
        contract { callsInPlace(value, AT_MOST_ONCE) }
        try {
            if (!value()) {
                throw Exception("SanityCheck failed")
            }
        } catch (e: Throwable) {
            if (SANITYCHECK_PRINTING) {
                println("Exception during SanityCheck.check")
                e.printStackTrace()
            }
            throw e
        }
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun checkUnreachable(): Nothing = throw UnreachableException()
}
