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

import lupos.parser.LexerCharIterator
import lupos.parser.LookAheadTokenIterator
import lupos.parser.turtle.Turtle2Parser
import lupos.parser.turtle.TurtleParserWithStringTriples
import lupos.parser.turtle.TurtleScanner
import lupos.shared.IQuery
import lupos.shared.MemoryTable
import lupos.shared.MemoryTableParser
import lupos.shared.dynamicArray.ByteArrayWrapper
import lupos.shared.inline.DictionaryHelper
import lupos.shared.inline.MyStringStream

public class MemoryTableFromN3 : MemoryTableParser {
    override operator fun invoke(data: String, query: IQuery): MemoryTable {
        var res = MemoryTable(arrayOf("s", "p", "o"))
        res.query = query
        var dictionary = res.query!!.getDictionary()
        try {
            val inputstream = MyStringStream(data)
            val parser = object : Turtle2Parser(inputstream) {
                override fun onTriple() {
                    val row = IntArray(3)
                    res.data.add(row)
                    for (i in 0 until 3) {
                        row[i] = dictionary.createValue(triple[i])
                    }
                }
            }
            parser.parse()
        } catch (e: Throwable) {
            e.printStackTrace()
            res = MemoryTable(arrayOf("s", "p", "o"))
            res.query = query
            dictionary = res.query!!.getDictionary()
            val lcit = LexerCharIterator(data)
            val tit = TurtleScanner(lcit)
            val ltit = LookAheadTokenIterator(tit, 3)
            val buffer = ByteArrayWrapper()
            val x = object : TurtleParserWithStringTriples() {
                /*suspend*/ override fun consume_triple(s: String, p: String, o: String) {
                    val row = IntArray(3)
                    res.data.add(row)
                    DictionaryHelper.sparqlToByteArray(buffer, s)
                    row[0] = dictionary.createValue(buffer)
                    DictionaryHelper.sparqlToByteArray(buffer, p)
                    row[1] = dictionary.createValue(buffer)
                    DictionaryHelper.sparqlToByteArray(buffer, o)
                    row[2] = dictionary.createValue(buffer)
                }
            }
            x.ltit = ltit
            x.parse()
        }
        return res
    }
}
