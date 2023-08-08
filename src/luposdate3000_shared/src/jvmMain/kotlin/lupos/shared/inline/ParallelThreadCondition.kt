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
import lupos.shared.myPrintStackTrace
import kotlin.jvm.JvmField

@Suppress("PLATFORM_CLASS_MAPPED_TO_KOTLIN")
public actual class ParallelThreadCondition {

    @JvmField
    public var wasSignalled: Boolean = false
    public actual inline fun waitCondition(crossinline condition: () -> Boolean) {
        synchronized(this) {
            if (!wasSignalled && condition()) {
                try {
                    (this as Object).wait()
                } catch (e: Exception) {
                    e.myPrintStackTrace(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_shared/src/jvmMain/kotlin/lupos/shared/inline/ParallelThreadCondition.kt:32"/*SOURCE_FILE_END*/)
                }
            }
            wasSignalled = false
        }
    }

    @Suppress("NOTHING_TO_INLINE")
    public actual inline fun signal() {
        synchronized(this) {
            wasSignalled = true
            (this as Object).notify()
        }
    }
}
