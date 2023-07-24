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

import lupos.shared.DictionaryValueType
import lupos.shared.dynamicArray.ByteArrayWrapper
import lupos.shared.fileformat.DictionaryIntermediateRow
import lupos.shared.inline.dynamicArray.ByteArrayWrapperExt
import lupos.shared.InvalidInputException
internal class DictionaryIntermediateReader(filename: String) : DictionaryIntermediate(filename) {
    init {
        streamIn = getFile().openInputStream()
        val version = streamIn!!.readInt()
        if (version != DictionaryIntermediate.version) {
            throw InvalidInputException("incompatible file format version in '$filename'. Expected ${DictionaryIntermediate.version}, but found $version")
        }
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun readAll(buffer: ByteArrayWrapper, crossinline action: (id: DictionaryValueType) -> Unit) {
        while (hasNext()) {
            next(buffer, action)
        }
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun hasNext(): Boolean {
        return streamIn != null
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun next(buffer: ByteArrayWrapper, crossinline action: (id: DictionaryValueType) -> Unit) {
        val id = streamIn!!.readDictionaryValueType()
        if (id < 0) {
            close()
        } else {
            val len = streamIn!!.readInt()
            ByteArrayWrapperExt.setSize(buffer, len, false)
            streamIn!!.read(ByteArrayWrapperExt.getBuf(buffer), len)
            action(id)
        }
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun next(buffer: ByteArrayWrapper): DictionaryIntermediateRow? {
        var res: DictionaryIntermediateRow? = null
        next(buffer) { id ->
            res = DictionaryIntermediateRow(id, buffer)
        }
        return res
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun close() {
        streamIn?.close()
        streamIn = null
    }
}
