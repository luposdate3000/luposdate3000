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

import lupos.modulename.File
import lupos.s00misc.ByteArrayWrapper

public class DictionaryIntermediateWriter : DictionaryIntermediate {
    public constructor(filename: String) : super(filename) {
        streamOut = File("$filename$filenameEnding").openOutputStream(false)
    }

    @Suppress("NOTHING_TO_INLINE")
    public inline fun writeAssumeOrdered(row: DictionaryIntermediateRow) {
        writeAssumeOrdered(row.id, row.data)
    }

    @Suppress("NOTHING_TO_INLINE")
    public inline fun writeAssumeOrdered(id: Int, data: ByteArrayWrapper) {
        streamOut!!.writeInt(id)
        streamOut!!.writeInt(data.getSize())
        streamOut!!.write(data.getBuf(), data.getSize())
    }

    @Suppress("NOTHING_TO_INLINE")
    public inline fun write(dict: MutableMap<ByteArrayWrapper, Int>) {
        val rows = dict.toList().map {
            DictionaryIntermediateRow(it.second, it.first)
        }.sorted()
        for (row in rows) {
            writeAssumeOrdered(row)
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
