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
package lupos.endpoint
import lupos.parser.turtle.TurtleParserWithDictionaryValueTypeTriples
import lupos.shared.DictionaryValueTypeArray
import lupos.shared.IQuery
import lupos.shared.MemoryTable
import lupos.shared.MemoryTableParser
import lupos.shared.dynamicArray.ByteArrayWrapper
import lupos.shared.inline.DictionaryHelper
import lupos.shared.inline.File
import lupos.shared.inline.FileExt
public class MemoryTableFromN3 : MemoryTableParser {
    override operator fun invoke(data: String, query: IQuery): MemoryTable {
        val buf = ByteArrayWrapper()
        var res = MemoryTable(arrayOf("s", "p", "o"))
        res.query = query
        var dictionary = res.query!!.getDictionary()
        val inputFileName = FileExt.createTempDirectory() + "a.n3"
        val f = File(inputFileName)
        f.withOutputStream {
            it.println(data)
        }
        val parserObject = TurtleParserWithDictionaryValueTypeTriples(
            consume_triple = { s, p, o ->
                val row = DictionaryValueTypeArray(3)
                row[0] = s
                row[1] = p
                row[2] = o
                res.data.add(row)
                dictionary.getValue(buf, s)
                val ss = DictionaryHelper.byteArrayToSparql(buf)
                dictionary.getValue(buf, p)
                val pp = DictionaryHelper.byteArrayToSparql(buf)
                dictionary.getValue(buf, o)
                val oo = DictionaryHelper.byteArrayToSparql(buf)
                println("MemoryTableFromN3 triple $ss $pp $oo")
            },
            kpFileLoc = inputFileName,
        )
        parserObject.convertByteArrayWrapperToID = {
            val res = dictionary.createValue(it)
            val input = DictionaryHelper.byteArrayToSparql(it)
            val tmp = ByteArrayWrapper()
            dictionary.getValue(tmp, res)
            val output = DictionaryHelper.byteArrayToSparql(tmp)
            println("MemoryTableFromN3 dict $input -> $res -> $output")
            res
        }
        try {
            parserObject.initializeCache()
            parserObject.turtleDoc()
        } catch (e: Throwable) {
            throw Exception(inputFileName, e)
        }
        return res
    }
}
