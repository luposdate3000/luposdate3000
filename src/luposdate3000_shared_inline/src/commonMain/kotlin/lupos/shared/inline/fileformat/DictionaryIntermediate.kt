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

internal abstract class DictionaryIntermediate(internal val filename: String) {
    @JvmField
    internal var streamOut: IMyOutputStream? = null

    @JvmField
    internal var streamIn: IMyInputStream? = null
    internal inline fun getFile(): File {
        return getFile(filename)
    }

    internal companion object {
        internal val version: Int = 1
        internal const val filenameEnding = ".dictionary"
        internal inline fun getFile(filename: String): File {
            return File("$filename$filenameEnding")
        }

        internal inline fun fileExists(filename: String): Boolean {
            val f = File("$filename$filenameEnding")
            var res = f.exists()
            if (res) {
                val streamIn = f.openInputStream()
                val version = streamIn.readInt()
                if (version != TriplesIntermediate.version) {
                    res = false
                }
                streamIn.close()
            }
            return res
        }

        internal inline fun delete(filename: String) {
            DictionaryIntermediateReader(filename).getFile().deleteRecursively()
        }
    }
}
