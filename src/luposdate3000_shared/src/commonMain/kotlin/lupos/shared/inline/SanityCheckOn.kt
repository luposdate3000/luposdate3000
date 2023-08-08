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
import lupos.shared.myPrintStackTraceAndThrowAgain
import kotlin.contracts.InvocationKind.AT_MOST_ONCE
import kotlin.contracts.contract

@OptIn(kotlin.contracts.ExperimentalContracts::class)
public object SanityCheckOn {
    public val enabled: Boolean = true
    public const val SANITYCHECK_PRINTING: Boolean = false
    public const val SANITYCHECK_PRINTING_NODEMANAGER: Boolean = false
    public const val SANITYCHECK_PRINTING_BUFFERMANAGER: Boolean = false
    public inline fun println_buffermanager(crossinline s: () -> Any?) {
        contract { callsInPlace(s, AT_MOST_ONCE) }
        if (SANITYCHECK_PRINTING_BUFFERMANAGER) {
            println(s())
        }
    }

    public inline fun println_nodemanager(crossinline s: () -> Any?) {
        contract { callsInPlace(s, AT_MOST_ONCE) }
        if (SANITYCHECK_PRINTING_NODEMANAGER) {
            println(s())
        }
    }

    public inline fun println(crossinline s: () -> Any?) {
        contract { callsInPlace(s, AT_MOST_ONCE) }
        if (SANITYCHECK_PRINTING) {
            println(s())
        }
    }

    public inline operator fun invoke(crossinline filename: () -> String, crossinline action: () -> Unit) {
        try {
            action()
        } catch (e: Throwable) {
            if (SANITYCHECK_PRINTING) {
                println("Exception during SanityCheck.invoke at ${filename()}")
                e.myPrintStackTraceAndThrowAgain(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_shared/src/commonMain/kotlin/lupos/shared/inline/SanityCheckOn.kt:55"/*SOURCE_FILE_END*/)
            } else {
                throw e
            }
        }
    }

    /*suspend*/ public inline fun suspended(crossinline action: /*suspend*/ () -> Unit) {
        contract { callsInPlace(action, AT_MOST_ONCE) }
        try {
            action()
        } catch (e: Throwable) {
            if (SANITYCHECK_PRINTING) {
                println("Exception during SanityCheck.suspended")
                e.myPrintStackTraceAndThrowAgain(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_shared/src/commonMain/kotlin/lupos/shared/inline/SanityCheckOn.kt:69"/*SOURCE_FILE_END*/)
            } else {
                throw e
            }
        }
    }

    public inline fun <T> helper(crossinline action: () -> T): T? {
        contract { callsInPlace(action, AT_MOST_ONCE) }
        return action()
    }

    public inline fun check(crossinline filename: () -> String, crossinline value: () -> Boolean, crossinline msg: () -> String) {
        contract { callsInPlace(value, AT_MOST_ONCE) }
        try {
            if (!value()) {
                throw Exception("SanityCheck failed at ${filename()} :: " + msg())
            }
        } catch (e: Throwable) {
            if (SANITYCHECK_PRINTING) {
                println("Exception during SanityCheck.check")
                e.myPrintStackTraceAndThrowAgain(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_shared/src/commonMain/kotlin/lupos/shared/inline/SanityCheckOn.kt:90"/*SOURCE_FILE_END*/)
            } else {
                throw e
            }
        }
    }

    public inline fun check(crossinline filename: () -> String, crossinline value: () -> Boolean) {
        contract { callsInPlace(value, AT_MOST_ONCE) }
        try {
            if (!value()) {
                throw Exception("SanityCheck failed at ${filename()}")
            }
        } catch (e: Throwable) {
            if (SANITYCHECK_PRINTING) {
                println("Exception during SanityCheck.check")
                e.myPrintStackTraceAndThrowAgain(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_shared/src/commonMain/kotlin/lupos/shared/inline/SanityCheckOn.kt:106"/*SOURCE_FILE_END*/)
            } else {
                throw e
            }
        }
    }

    @Suppress("NOTHING_TO_INLINE")
    public inline fun checkUnreachable(): Nothing = throw UnreachableException()
}
