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

package lupos.shared.inline.fileformat

import lupos.shared.IMyInputStream
import lupos.shared.IMyOutputStream
import lupos.shared.inline.File
import kotlin.jvm.JvmField

internal abstract class TriplesIntermediate(@JvmField internal val filename: String) {
    @JvmField
    internal var streamOut: IMyOutputStream? = null

    @JvmField
    internal var streamIn: IMyInputStream? = null

    internal companion object {
        internal const val version: Int = 2
        internal const val filenameEnding = ".triples"

        @Suppress("NOTHING_TO_INLINE")
        internal inline fun delete(filename: String) {
            File("$filename$filenameEnding").deleteRecursively()
        }
    }
}
