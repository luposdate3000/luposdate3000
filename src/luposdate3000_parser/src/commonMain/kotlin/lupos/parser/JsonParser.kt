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

package lupos.parser
import lupos.shared.SanityCheck
public class JsonParserObject(private val map: MutableMap<String, Any>) : Iterable<Pair<String, Any>> {
    override operator fun iterator(): Iterator<Pair<String, Any>> {
        return object : Iterator<Pair<String, Any>> {
            var iter = map.iterator()
            override fun hasNext(): Boolean {
                return iter.hasNext()
            }

            override fun next(): Pair<String, Any> {
                val (k, v) = iter.next()
                return k to v
            }
        }
    }
    public fun putAll(d: Map<String, Any>) {
        map.putAll(d)
    }
    public operator fun set(k: String, v: Any) {
        map[k] = v
    }
    public operator fun get(k: String): Any? {
        return map[k]
    }
    public fun getOrDefault(k: String, v: Any): Any {
        val res = map[k]
        if (res == null) {
            map[k] = v
            return v
        } else {
            return res
        }
    }
}
public class JsonParserArray(private val array: MutableList<Any>) : Iterable<Any> {
    public fun <T> map(action: (Any) -> T): MutableList<T> {
        return array.map { action(it) }.toMutableList()
    }
    override operator fun iterator(): Iterator<Any> {
        return array.iterator()
    }
}
public class JsonParser {
    private fun readValueAt(data: String, off: Int): Pair<Int, Any> {
        var i = readSpacesAt(data, off)
        while (i <data.length) {
            when (data[i]) {
                '{' -> return readMapAt(data, i)
                '[' -> return readArrayAt(data, i)
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '-', '+', '.' -> return readNumberAt(data, i)
                '"' -> return readStringAt(data, i)
                't', 'f', 'T', 'F' -> return readBooleanAt(data, i)
                else -> TODO("unknown char at $i")
            }
        }
        return data.length to mutableMapOf<String, Any>()
    }
    private fun readMapAt(data: String, off: Int): Pair<Int, JsonParserObject> {
        var i = off + 1
        val res = mutableMapOf<String, Any>()
        loop@ while (i <data.length) {
            i = readSpacesAt(data, i)
            when (data[i]) {
                '}' -> return (i + 1) to JsonParserObject(res)
                '"' -> {
                    val (j, key) = readStringAt(data, i)
                    i = readSpacesAt(data, j)
                    if (data[i] != ':') {
                        TODO("unknown char at $i")
                    }
                    i = readSpacesAt(data, i + 1)
                    val (k, value) = readValueAt(data, i)
                    res[key] = value
                    i = readSpacesAt(data, k)
                    when (data[i]) {
                        ',' -> i++
                        '}' -> return (i + 1)to JsonParserObject(res)
                        else -> TODO("unknown char at $i")
                    }
                }
            }
        }
        TODO("object not closed")
    }
    private fun readSpacesAt(data: String, off: Int): Int {
        var i = off
        while (i <data.length) {
            when (data[i]) {
                ' ', '\t', '\n', '\r' -> { i++ }
                else -> return i
            }
        }
        return i
    }
    private fun readArrayAt(data: String, off: Int): Pair<Int, JsonParserArray> {
        var i = off + 1
        val res = mutableListOf<Any>()
        loop@ while (i <data.length) {
            i = readSpacesAt(data, i)
            when (data[i]) {
                ']' -> return (i + 1) to JsonParserArray(res)
                else -> {
                    val (k, value) = readValueAt(data, i)
                    res.add(value)
                    i = readSpacesAt(data, k)
                    when (data[i]) {
                        ',' -> i++
                        ']' -> return (i + 1)to JsonParserArray(res)
                        else -> TODO("unknown char at $i")
                    }
                }
            }
        }
        TODO("object not closed")
    }
    private fun readNumberAt(data: String, off: Int): Pair<Int, Number> {
        var i = off
        while (i <data.length) {
            when (data[i]) {
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '-', '+', '.' -> i++
                else -> break
            }
            var num = data.substring(off, i)
            if (num.startsWith("+")) {
                num = num.substring(1)
            }
            try {
                val res = num.toInt()
                if (num == res.toString()) {
                    return (i + 1) to res
                }
            } catch (e: Throwable) {
            }
            try {
                val res = num.toLong()
                if (num == res.toString()) {
                    return (i + 1) to res
                }
            } catch (e: Throwable) {
            }
            try {
                val res = num.toDouble()
                if (num == res.toString()) {
                    return (i + 1) to res
                }
            } catch (e: Throwable) {
            }
        }
        TODO("unknown numberformat at $off")
    }
    private fun readStringAt(data: String, off: Int): Pair<Int, String> {
        var i = off + 1
        while (i <data.length) {
            when (data[i]) {
                '"' -> return (i + 1) to data.substring(off + 1, i)
                else -> i++
            }
        }
        TODO("string not closed")
    }
    private fun readBooleanAt(data: String, off: Int): Pair<Int, Boolean> {
        when (data[off]) {
            't', 'T' -> {
                if (data[off + 1] == 'r' || data[off + 1] == 'R') {
                    if (data[off + 2] == 'u' || data[off + 2] == 'U') {
                        if (data[off + 3] == 'e' || data[off + 3] == 'E') {
                            return (off + 4) to true
                        }
                    }
                }
            }
            'f', 'F' -> {
                if (data[off + 1] == 'a' || data[off + 1] == 'A') {
                    if (data[off + 2] == 'l' || data[off + 2] == 'L') {
                        if (data[off + 3] == 's' || data[off + 3] == 'S') {
                            if (data[off + 4] == 'e' || data[off + 4] == 'E') {
                                return (off + 5) to false
                            }
                        }
                    }
                }
            }
        }
        TODO("unknown char at $off")
    }

    public fun jsonToString(data: Any): String {
        return jsonToString(data, "")
    }
    private fun jsonToString(data: Any, indention: String): String {
        when (data) {
            is JsonParserObject -> {
                var res = "{\n"
                for ((k, v) in data) {
                    res += "$indention    \"$k\" : ${jsonToString(v,indention + "    ")},\n"
                }
                return "$res}"
            }
            is JsonParserArray -> {
                var res = "[\n"
                for (e in data) {
                    res += "$indention    ${jsonToString(e,indention + "    ")},\n"
                }
                return "$res]"
            }
            is Boolean -> return "$data"
            is Int -> return "$data"
            is Long -> return "$data"
            is Double -> return "$data"
            is String -> return "\"$data\""
            else -> TODO("unknown JSON element $data")
        }
    }

    public fun stringToJson(data: String): Any {
        val res = readValueAt(data, 0)
        SanityCheck.check(
            { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_parser/src/commonMain/kotlin/lupos/parser/JsonParser.kt:235"/*SOURCE_FILE_END*/ },
            { data == jsonToString(res) },
            { "$data\n -> \n${jsonToString(res)}" }
        )
        return res
    }
}
