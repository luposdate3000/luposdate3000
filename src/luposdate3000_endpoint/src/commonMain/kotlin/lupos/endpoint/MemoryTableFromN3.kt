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
import lupos.shared.myPrintStackTraceAndThrowAgain
import lupos.shared.myPrintStackTrace

import lupos.parser.turtle.TurtleParser
import lupos.shared.DictionaryValueTypeArray
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
        val dataStream = MyStringStream(data)
        val parserObject = TurtleParser(dataStream)
        parserObject.consumeTriple = { s, p, o ->
            val s1 = ByteArrayWrapper()
            val p1 = ByteArrayWrapper()
            val o1 = ByteArrayWrapper()
            DictionaryHelper.sparqlToByteArray(s1, s)
            DictionaryHelper.sparqlToByteArray(p1, p)
            DictionaryHelper.sparqlToByteArray(o1, o)
            val row = DictionaryValueTypeArray(3)
            row[0] = dictionary.createValue(s1)
            row[1] = dictionary.createValue(p1)
            row[2] = dictionary.createValue(o1)
            res.data.add(row)
        }
        try {
            parserObject.parserDefinedParse()
        } catch (e: Throwable) {
            println(">>>>")
            println(data)
            println("<<<<")
            e.myPrintStackTraceAndThrowAgain(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_endpoint/src/commonMain/kotlin/lupos/endpoint/MemoryTableFromN3.kt:54"/*SOURCE_FILE_END*/ )
        }
        dataStream.close()
        return res
    }
}
