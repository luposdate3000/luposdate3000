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
package lupos.shared

import lupos.shared.dictionary.DictionaryExt

public class MemoryTableFromTsv : MemoryTableParser {
    override operator fun invoke(data: String): MemoryTable {
        val lines = data.lines()
        val variables = mutableListOf<String>()
        val columns = lines.first().split("\t")
        for (variableName in columns) {
            variables.add(variableName.substring(1, variableName.length))
        }
        var res = MemoryTable(variables)
        res.query = Query()
        var dictionary = res.query.getDictionary()
        var firstLine = true
        val buffer = ByteArrayWrapper()
        for (line in lines) {
            if (firstLine) {
                firstLine = false
                continue
            }
            if (line.isEmpty()) {
                continue
            }
            val values = line.split("\t")
            var i = 0
            val row = IntArray(variables.size) { DictionaryExt.undefValue }
            res.data.add(row)
            while (i < variables.size && i < values.size) {
                DictionaryHelper.sparqlToByteArray(buffer, values[i])
                row[i] = dictionary.createValue(buffer)
                i++
            }
        }
        return res
    }
}
