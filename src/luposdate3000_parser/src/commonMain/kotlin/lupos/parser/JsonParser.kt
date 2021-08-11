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
import lupos.shared.inline.File

public interface IJsonParserValue
public class JsonParserObject(private val map: MutableMap<String, IJsonParserValue>) : Iterable<Pair<String, IJsonParserValue>>, IJsonParserValue {
    override operator fun iterator(): Iterator<Pair<String, IJsonParserValue>> {
        return object : Iterator<Pair<String, IJsonParserValue>> {
            var iter = map.iterator()
            override fun hasNext(): Boolean {
                return iter.hasNext()
            }

            override fun next(): Pair<String, IJsonParserValue> {
                val (k, v) = iter.next()
                return k to v
            }
        }
    }

    public fun putAll(d: Map<String, String>) {
        for ((k, v) in d) {
            map[k] = JsonParserString(v)
        }
    }
    public fun isEmpty(): Boolean = map.isEmpty()
    public operator fun set(k: String, v: IJsonParserValue) {
        map[k] = v
    }
    public operator fun set(k: String, v: String) {
        map[k] = JsonParserString(v)
    }
    public operator fun set(k: String, v: Int) {
        map[k] = JsonParserInt(v)
    }
    public operator fun set(k: String, v: Long) {
        map[k] = JsonParserLong(v)
    }
    public operator fun set(k: String, v: Boolean) {
        map[k] = JsonParserBoolean(v)
    }
    public operator fun set(k: String, v: Double) {
        map[k] = JsonParserDouble(v)
    }

    public operator fun get(k: String): IJsonParserValue? {
        return map[k]
    }

    public fun getOrDefault(k: String, v: IJsonParserValue): IJsonParserValue {
        val res = map[k]
        if (res == null) {
            map[k] = v
            return v
        } else {
            return res
        }
    }
    public fun getOrEmptyObject(k: String): JsonParserObject {
        return getOrDefault(k, JsonParserObject(mutableMapOf())) as JsonParserObject
    }
    public fun getOrEmptyArray(k: String): JsonParserArray {
        return getOrDefault(k, JsonParserArray(mutableListOf())) as JsonParserArray
    }
    public fun getOrDefault(k: String, v: String): String {
        val tmp = getOrDefault(k, JsonParserString(v))
        val res = when (tmp) {
            is JsonParserString -> tmp.value
            is JsonParserBoolean -> "${tmp.value}"
            is JsonParserInt -> "${tmp.value}"
            is JsonParserLong -> "${tmp.value}"
            is JsonParserDouble -> "${tmp.value}"
            else -> TODO("$tmp")
        }
        map[k] = JsonParserString(res)
        return res
    }

    public fun getOrDefault(k: String, v: Boolean): Boolean {
        val tmp = getOrDefault(k, JsonParserBoolean(v))
        val res = when (tmp) {
            is JsonParserString -> tmp.value.toLowerCase() == "true"
            is JsonParserBoolean -> tmp.value
            is JsonParserInt -> tmp.value != 0
            is JsonParserLong -> tmp.value != 0L
            is JsonParserDouble -> tmp.value != 0.0
            else -> TODO("$tmp")
        }
        map[k] = JsonParserBoolean(res)
        return res
    }

    public fun getOrDefault(k: String, v: Int): Int {
        val tmp = getOrDefault(k, JsonParserInt(v))
        val res = when (tmp) {
            is JsonParserString -> tmp.value.toInt()
            is JsonParserBoolean -> if (tmp.value) 1 else 0
            is JsonParserInt -> tmp.value
            is JsonParserLong -> tmp.value.toInt()
            is JsonParserInt -> tmp.value.toInt()
            else -> TODO("$tmp")
        }
        map[k] = JsonParserInt(res)
        return res
    }

    public fun getOrDefault(k: String, v: Long): Long {
        val tmp = getOrDefault(k, JsonParserLong(v))
        val res = when (tmp) {
            is JsonParserString -> tmp.value.toLong()
            is JsonParserBoolean -> if (tmp.value) 1 else 0
            is JsonParserInt -> tmp.value.toLong()
            is JsonParserLong -> tmp.value
            is JsonParserDouble -> tmp.value.toLong()
            else -> TODO("$tmp")
        }
        map[k] = JsonParserLong(res)
        return res
    }

    public fun getOrDefault(k: String, v: Double): Double {
        val tmp = getOrDefault(k, JsonParserDouble(v))
        val res = when (tmp) {
            is JsonParserString -> tmp.value.toDouble()
            is JsonParserBoolean -> if (tmp.value) 1.0 else 0.0
            is JsonParserInt -> tmp.value.toDouble()
            is JsonParserLong -> tmp.value.toDouble()
            is JsonParserDouble -> tmp.value
            else -> TODO("$tmp")
        }
        map[k] = JsonParserDouble(res)
        return res
    }
}

public class JsonParserArray(private val array: MutableList<IJsonParserValue>) : Iterable<IJsonParserValue>, IJsonParserValue {
    public fun isEmpty(): Boolean = array.isEmpty()
    public fun <T> map(action: (IJsonParserValue) -> T): MutableList<T> {
        val res: List<T> = array.map { action(it) }
        return res.toMutableList()
    }

    override operator fun iterator(): Iterator<IJsonParserValue> {
        return array.iterator()
    }
    public fun add(v: IJsonParserValue) {
        array.add(v)
    }
}

public class JsonParserInt(public var value: Int) : IJsonParserValue
public class JsonParserLong(public var value: Long) : IJsonParserValue
public class JsonParserDouble(public var value: Double) : IJsonParserValue
public class JsonParserString(public var value: String) : IJsonParserValue
public class JsonParserBoolean(public var value: Boolean) : IJsonParserValue

public class JsonParser {
    private fun readValueAt(data: String, off: Int): Pair<Int, IJsonParserValue> {
        var i = readSpacesAt(data, off)
        while (i < data.length) {
            when (data[i]) {
                '{' -> return readMapAt(data, i)
                '[' -> return readArrayAt(data, i)
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '-', '+', '.' -> return readNumberAt(data, i)
                '"' -> return readStringAt(data, i)
                't', 'f', 'T', 'F' -> return readBooleanAt(data, i)
                else -> throw Exception("unknown char at A $i '${data[i]}' $data")
            }
        }
        return data.length to JsonParserObject(mutableMapOf())
    }

    private fun readMapAt(data: String, off: Int): Pair<Int, JsonParserObject> {
        var i = off + 1
        val res = mutableMapOf<String, IJsonParserValue>()
        loop@ while (i < data.length) {
            i = readSpacesAt(data, i)
            when (data[i]) {
                '}' -> return (i + 1) to JsonParserObject(res)
                '"' -> {
                    val (j, key) = readStringAt(data, i)
                    i = readSpacesAt(data, j)
                    if (data[i] != ':') {
                        throw Exception("unknown char at B $i '${data[i]}' $data")
                    }
                    i = readSpacesAt(data, i + 1)
                    val (k, value) = readValueAt(data, i)
                    res[key.value] = value
                    i = readSpacesAt(data, k)
                    when (data[i]) {
                        ',' -> i++
                        '}' -> return (i + 1) to JsonParserObject(res)
                        else -> throw Exception("unknown char at C $i '${data[i]}' $data")
                    }
                }
            }
        }
        throw Exception("object not closed I $off '${data[off]}' $data")
    }

    private fun readSpacesAt(data: String, off: Int): Int {
        var i = off
        while (i < data.length) {
            when (data[i]) {
                ' ', '\t', '\n', '\r' -> {
                    i++
                }
                else -> return i
            }
        }
        return i
    }

    private fun readArrayAt(data: String, off: Int): Pair<Int, JsonParserArray> {
        var i = off + 1
        val res = mutableListOf<IJsonParserValue>()
        loop@ while (i < data.length) {
            i = readSpacesAt(data, i)
            when (data[i]) {
                ']' -> return (i + 1) to JsonParserArray(res)
                else -> {
                    val (k, value) = readValueAt(data, i)
                    res.add(value)
                    i = readSpacesAt(data, k)
                    when (data[i]) {
                        ',' -> i++
                        ']' -> return (i + 1) to JsonParserArray(res)
                        else -> throw Exception("unknown char at D $i '${data[i]}' $data")
                    }
                }
            }
        }
        throw Exception("array not closed H $off '${data[off]}' $data")
    }

    private fun readNumberAt(data: String, off: Int): Pair<Int, IJsonParserValue> {
        var i = off
        while (i < data.length) {
            when (data[i]) {
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '-', '+', '.', 'e', 'E' -> i++
                else -> break
            }
        }
        if (i != off) {
            var num = data.substring(off, i)
            if (num.startsWith("+")) {
                num = num.substring(1)
            }
            try {
                val res = num.toInt()
                if (num == res.toString()) {
                    return i to JsonParserInt(res)
                }
            } catch (e: Throwable) {
            }
            try {
                val res = num.toLong()
                if (num == res.toString()) {
                    return i to JsonParserLong(res)
                }
            } catch (e: Throwable) {
            }
            try {
                val res = num.toDouble()
                return i to JsonParserDouble(res)
            } catch (e: Throwable) {
            }
        }
        throw Exception("unknown numberformat at E $off '${data.substring(off, i)}' $data")
    }

    private fun readStringAt(data: String, off: Int): Pair<Int, JsonParserString> {
        var i = off + 1
        var backslashOpen = false
        while (i < data.length) {
            when (data[i]) {
                '"' -> {
                    if (backslashOpen) {
                        backslashOpen = false
                        i++
                    } else {
                        return (i + 1) to JsonParserString(decodeString(data.substring(off + 1, i)))
                    }
                }
                '\\' -> {
                    backslashOpen = !backslashOpen
                }
                else -> {
                    backslashOpen = false
                    i++
                }
            }
        }
        throw Exception("string not closed $off '${data[off]}' $data")
    }

    private fun readBooleanAt(data: String, off: Int): Pair<Int, IJsonParserValue> {
        when (data[off]) {
            't', 'T' -> {
                if (data[off + 1] == 'r' || data[off + 1] == 'R') {
                    if (data[off + 2] == 'u' || data[off + 2] == 'U') {
                        if (data[off + 3] == 'e' || data[off + 3] == 'E') {
                            return (off + 4) to JsonParserBoolean(true)
                        }
                    }
                }
            }
            'f', 'F' -> {
                if (data[off + 1] == 'a' || data[off + 1] == 'A') {
                    if (data[off + 2] == 'l' || data[off + 2] == 'L') {
                        if (data[off + 3] == 's' || data[off + 3] == 'S') {
                            if (data[off + 4] == 'e' || data[off + 4] == 'E') {
                                return (off + 5) to JsonParserBoolean(false)
                            }
                        }
                    }
                }
            }
        }
        throw Exception("unknown char at F $off '${data[off]}' $data")
    }

    public fun jsonToString(data: IJsonParserValue): String {
        return jsonToString(data, "")
    }

    private fun jsonToString(data: IJsonParserValue, indention: String): String {
        when (data) {
            is JsonParserObject -> {
                if (data.isEmpty()) {
                    return "{}"
                } else {
                    var res = "{\n"
                    for ((k, v) in data) {
                        res += "$indention    \"$k\": ${jsonToString(v, indention + "    ")},\n"
                    }
                    return "$res$indention}"
                }
            }
            is JsonParserArray -> {
                if (data.isEmpty()) {
                    return "[]"
                } else {
                    var res = "[\n"
                    for (e in data) {
                        res += "$indention    ${jsonToString(e, indention + "    ")},\n"
                    }
                    return "$res$indention]"
                }
            }
            is JsonParserBoolean -> return "${data.value}"
            is JsonParserInt -> return "${data.value}"
            is JsonParserLong -> return "${data.value}"
            is JsonParserDouble -> return "${data.value}"
            is JsonParserString -> return "\"${encodeString(data.value)}\""
            else -> throw Exception("unknown JSON element G $data")
        }
    }

    public fun stringToJson(data: String): IJsonParserValue {
        val res = readValueAt(data, 0).second
        return res
    }
    public fun fileToJson(fileName: String): IJsonParserValue {
        val fileStr = File(fileName).readAsString()
        val json = JsonParser().stringToJson(fileStr) as IJsonParserValue
        return json
    }
    public fun encodeString(s: String): String {
        return s
            .replace("\\", "\\\\")
            .replace("\t", "\\t")
            .replace("\r", "\\r")
            .replace("\n", "\\n")
            .replace("\"", "\\\"")
    }
    public fun decodeString(s: String): String {
        return s
            .replace("\\\"", "\"")
            .replace("\\n", "\n")
            .replace("\\r", "\r")
            .replace("\\t", "\t")
            .replace("\\\\", "\\")
    }
}
