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

package lupos.fileformat

import lupos.s00misc.File

public class DictionaryIntermediateReader(filename: String) : DictionaryIntermediate(filename) {
    init {
        streamIn = File("$filename$filenameEnding").openInputStream()
    }

    public inline fun readAll(crossinline action: (id: Int, data: ByteArray) -> Unit) {
        while (streamIn != null) {
            next(action)
        }
    }

    public inline fun next(crossinline action: (id: Int, data: ByteArray) -> Unit) {
        val id = streamIn!!.readInt()
        if (id < 0) {
            close()
        } else {
            val len = streamIn!!.readInt()
            val buf = ByteArray(len)
            streamIn!!.read(buf, len)
            action(id, buf)
        }
    }

    public inline fun next(): DictionaryIntermediateRow? {
        var res: DictionaryIntermediateRow? = null
        next { id, data ->
            res = DictionaryIntermediateRow(id, data)
        }
        return res
    }

    public override fun close() {
        streamIn?.close()
        streamIn = null
    }
}

public fun ArrayAllocatorDictionaryIntermediateReader(size: Int, initializer: (Int) -> DictionaryIntermediateReader): Array<DictionaryIntermediateReader> {
    val res = Array(size, initializer)
    return res
}
