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

import lupos.s00misc.ETripleComponentTypeExt
import lupos.s00misc.File

class DictionaryIntermediateWriter(filename: String) : DictionaryIntermediate(filename) {
    init {
        streamIn = null
        streamOut = File("$filename$filenameEnding").openOutputStream(false)
    }

    fun writeAssumeOrdered(row: DictionaryIntermediateRow) {
        writeAssumeOrdered(row.type, row.id, row.value)
    }

    fun writeAssumeOrdered(type: Int, id: Int, value: String) {
        val tmp = value.encodeToByteArray()
        streamOut!!.writeInt(type)
        streamOut!!.writeInt(id)
        streamOut!!.writeInt(tmp.size)
        streamOut!!.write(tmp, tmp.size)
    }

    fun write(dict: Array<Map<String, Long>>) {
        for (componentType in 0 until ETripleComponentTypeExt.values_size) {
            var localdict = dict[componentType]
            val size = localdict.size
            var rows = localdict.keys.flatMap { DictionaryIntermediateRow(componentType, v, k) }.sorted()
            for (row in rows) {
                writeAssumeOrdered(row)
            }
            localdict.clear()
        }
        close()
    }

    override fun close() {
        streamOut?.writeInt(ETripleComponentTypeExt.values_size)
        streamOut?.close()
        streamOut = null
    }
}
