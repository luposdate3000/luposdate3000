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

import lupos.dictionary.DictionaryHelper
import lupos.s00misc.File

public class DictionaryIntermediateWriter : DictionaryIntermediate {
    public constructor(filename: String) : this(filename, false)
    public constructor(filename: String, append: Boolean) : super(filename) {
        streamOut = File("$filename$filenameEnding").openOutputStream(append)
    }

    public inline fun writeAssumeOrdered(row: DictionaryIntermediateRow) {
        writeAssumeOrdered(row.id, row.data)
    }

    public inline fun writeAssumeOrdered(id: Int, data: ByteArray) {
        streamOut!!.writeInt(id)
        streamOut!!.writeInt(data.size)
        streamOut!!.write(data, data.size)
    }

    public inline fun write(dict: MutableMap<String, Int>) {
        for ((k, v) in dict) {
            writeAssumeOrdered(v, DictionaryHelper.valueToByteArray(k))
        }
        dict.clear()
        close()
    }

    public override fun close() {
        streamOut?.writeInt(-1)
        streamOut?.close()
        streamOut = null
    }
}
