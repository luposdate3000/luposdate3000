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

public interface IJsonParserValue {
    public fun setAccessed()
    public fun isAccessed(): Boolean
    public fun cloneJson(): IJsonParserValue
}

public class JsonParserObject(private val map: MutableMap<String, IJsonParserValue>) : Iterable<Pair<String, IJsonParserValue>>, IJsonParserValue {
    private var accessed0 = false
    override fun setAccessed() {
        accessed0 = true
    }

    override fun isAccessed(): Boolean = accessed0
    override fun cloneJson(): JsonParserObject {
        val res = JsonParserObject(mutableMapOf())
        for ((k, v) in map) {
            res.map[k] = v.cloneJson()
        }
        return res
    }

    public fun mergeWith(other: JsonParserObject) {
        for ((k, other_v) in other.map) {
            val my_v = map[k]
            if (my_v == null) {
                map[k] = other_v
            } else {
                when (my_v) {
                    is JsonParserObject -> my_v.mergeWith(other_v as JsonParserObject)
                    is JsonParserArray -> my_v.mergeWith(other_v as JsonParserArray)
                    is JsonParserInt, is JsonParserLong, is JsonParserBoolean, is JsonParserDouble, is JsonParserString -> map[k] = other_v
                    else -> TODO("$my_v - $other_v")
                }
            }
        }
    }

    override operator fun iterator(): Iterator<Pair<String, IJsonParserValue>> {
        return object : Iterator<Pair<String, IJsonParserValue>> {
            var iter = map.iterator()
            override fun hasNext(): Boolean {
                return iter.hasNext()
            }

            override fun next(): Pair<String, IJsonParserValue> {
                val (k, v) = iter.next()
                v.setAccessed()
                return k to v
            }
        }
    }

    public fun putAll(d: Map<String, Any>) {
        for ((k, v) in d) {
            map[k] = when (v) {
                is Int -> JsonParserInt(v)
                is Long -> JsonParserLong(v)
                is Double -> JsonParserDouble(v)
                is Boolean -> JsonParserBoolean(v)
                is String -> JsonParserString(v)
                else -> TODO("$v")
            }
        }
    }

    public fun isEmpty(): Boolean = map.isEmpty()
    public operator fun set(k: String, v: IJsonParserValue) {
        setAccessed()
        v.setAccessed()
        map[k] = v
    }

    public operator fun set(k: String, v: String) {
        setAccessed()
        val tmp = JsonParserString(v)
        tmp.setAccessed()
        map[k] = tmp
    }

    public operator fun set(k: String, v: Int) {
        setAccessed()
        val tmp = JsonParserInt(v)
        tmp.setAccessed()
        map[k] = tmp
    }

    public operator fun set(k: String, v: Long) {
        setAccessed()
        val tmp = JsonParserLong(v)
        tmp.setAccessed()
        map[k] = tmp
    }

    public operator fun set(k: String, v: Boolean) {
        setAccessed()
        val tmp = JsonParserBoolean(v)
        tmp.setAccessed()
        map[k] = tmp
    }

    public operator fun set(k: String, v: Double) {
        setAccessed()
        val tmp = JsonParserDouble(v)
        tmp.setAccessed()
        map[k] = tmp
    }

    public operator fun get(k: String): IJsonParserValue? {
        setAccessed()
        val tmp = map[k]
        tmp?.setAccessed()
        return tmp
    }

    public fun getOrDefault(k: String, v: IJsonParserValue): IJsonParserValue {
        setAccessed()
        val res = map[k]
        val r = if (res == null) {
            map[k] = v
            v
        } else {
            res
        }
        r.setAccessed()
        try {
            when (r) {
                is JsonParserString -> r.setDefault((v as JsonParserString).value)
                is JsonParserInt -> r.setDefault((v as JsonParserInt).value)
                is JsonParserLong -> r.setDefault((v as JsonParserLong).value)
                is JsonParserBoolean -> r.setDefault((v as JsonParserBoolean).value)
                is JsonParserDouble -> r.setDefault((v as JsonParserDouble).value)
            }
        } catch (e: Throwable) {
            throw e
        }
        return r
    }

    public fun getOrEmptyObject(k: String): JsonParserObject {
        setAccessed()
        val res = getOrDefault(k, JsonParserObject(mutableMapOf())) as JsonParserObject
        res.setAccessed()
        return res
    }

    public fun getOrEmptyArray(k: String): JsonParserArray {
        setAccessed()
        val res = getOrDefault(k, JsonParserArray(mutableListOf())) as JsonParserArray
        res.setAccessed()
        return res
    }

    public fun getOrDefault(k: String, v: String): String {
        setAccessed()
        val tmp = getOrDefault(k, JsonParserString(v))
        val res = when (tmp) {
            is JsonParserString -> tmp.value
            is JsonParserBoolean -> "${tmp.value}"
            is JsonParserInt -> "${tmp.value}"
            is JsonParserLong -> "${tmp.value}"
            is JsonParserDouble -> "${tmp.value}"
            else -> TODO("$tmp")
        }
        val tmp2 = JsonParserString(res)
        tmp2.setAccessed()
        tmp2.setDefault(v)
        map[k] = tmp2
        return res
    }

    public fun getOrDefault(k: String, v: Boolean): Boolean {
        setAccessed()
        val tmp = getOrDefault(k, JsonParserBoolean(v))
        val res = when (tmp) {
            is JsonParserString -> tmp.value.toLowerCase() == "true"
            is JsonParserBoolean -> tmp.value
            is JsonParserInt -> tmp.value != 0
            is JsonParserLong -> tmp.value != 0L
            is JsonParserDouble -> tmp.value != 0.0
            else -> TODO("$tmp")
        }
        val tmp2 = JsonParserBoolean(res)
        tmp2.setAccessed()
        tmp2.setDefault(v)
        map[k] = tmp2
        return res
    }

    public fun getOrDefault(k: String, v: Int): Int {
        setAccessed()
        val tmp = getOrDefault(k, JsonParserInt(v))
        val res = when (tmp) {
            is JsonParserString -> tmp.value.toInt()
            is JsonParserBoolean -> if (tmp.value) 1 else 0
            is JsonParserInt -> tmp.value
            is JsonParserLong -> tmp.value.toInt()
            is JsonParserInt -> tmp.value.toInt()
            else -> TODO("$tmp")
        }
        val tmp2 = JsonParserInt(res)
        tmp2.setAccessed()
        tmp2.setDefault(v)
        map[k] = tmp2
        return res
    }

    public fun getOrDefault(k: String, v: Long): Long {
        setAccessed()
        val tmp = getOrDefault(k, JsonParserLong(v))
        val res = when (tmp) {
            is JsonParserString -> tmp.value.toLong()
            is JsonParserBoolean -> if (tmp.value) 1 else 0
            is JsonParserInt -> tmp.value.toLong()
            is JsonParserLong -> tmp.value
            is JsonParserDouble -> tmp.value.toLong()
            else -> TODO("$tmp")
        }
        val tmp2 = JsonParserLong(res)
        tmp2.setAccessed()
        tmp2.setDefault(v)
        map[k] = tmp2
        return res
    }

    public fun getOrDefault(k: String, v: Double): Double {
        setAccessed()
        val tmp = getOrDefault(k, JsonParserDouble(v))
        val res = when (tmp) {
            is JsonParserString -> tmp.value.toDouble()
            is JsonParserBoolean -> if (tmp.value) 1.0 else 0.0
            is JsonParserInt -> tmp.value.toDouble()
            is JsonParserLong -> tmp.value.toDouble()
            is JsonParserDouble -> tmp.value
            else -> TODO("$tmp")
        }
        val tmp2 = JsonParserDouble(res)
        tmp2.setAccessed()
        tmp2.setDefault(v)
        map[k] = tmp2
        return res
    }
}

public class JsonParserArray(private val array: MutableList<IJsonParserValue>) : Iterable<IJsonParserValue>, IJsonParserValue {
    private var accessed0 = false
    override fun setAccessed() {
        accessed0 = true
    }

    override fun cloneJson(): JsonParserArray {
        val res = JsonParserArray(mutableListOf())
        for (a in array) {
            res.array.add(a.cloneJson())
        }
        return res
    }

    override fun isAccessed(): Boolean = accessed0
    public fun mergeWith(other: JsonParserArray) {
        var k = 0
        while (k < array.size && k < other.array.size) {
            val other_v = other.array[k]
            val my_v = array[k]
            when (my_v) {
                is JsonParserObject -> my_v.mergeWith(other_v as JsonParserObject)
                is JsonParserArray -> my_v.mergeWith(other_v as JsonParserArray)
                is JsonParserInt, is JsonParserLong, is JsonParserBoolean, is JsonParserDouble, is JsonParserString -> array[k] = other_v
                else -> TODO("$my_v - $other_v")
            }
            k++
        }
        while (k < other.array.size) {
            array.add(other.array[k])
            k++
        }
    }

    public fun isEmpty(): Boolean = array.isEmpty()
    public fun <T> map(action: (IJsonParserValue) -> T): MutableList<T> {
        val res: List<T> = array.map { action(it) }
        return res.toMutableList()
    }

    public fun firstOrEmptyObject(): JsonParserObject {
        setAccessed()
        val res =
            if (array.size > 0) {
                array[0] as JsonParserObject
            } else {
                val tmp = JsonParserObject(mutableMapOf())
                array.add(tmp)
                tmp
            }
        res.setAccessed()
        return res
    }

    public operator fun get(i: Int): IJsonParserValue {
        setAccessed()
        val res = array[i]
        res.setAccessed()
        return res
    }

    override operator fun iterator(): Iterator<IJsonParserValue> {
        setAccessed()
        for (a in array) {
            a.setAccessed()
        }
        return array.iterator()
    }

    public fun add(v: IJsonParserValue) {
        setAccessed()
        v.setAccessed()
        array.add(v)
    }
}

public class JsonParserInt(public var value: Int) : IJsonParserValue {
    private var accessed0 = false
    override fun setAccessed() {
        accessed0 = true
    }

    override fun isAccessed(): Boolean = accessed0
    private var default0: Int? = null
    internal fun setDefault(v: Int) {
        default0 = v
    }

    internal fun getDefault() = default0
    override fun cloneJson(): JsonParserInt = this
}

public class JsonParserLong(public var value: Long) : IJsonParserValue {
    private var accessed0 = false
    override fun setAccessed() {
        accessed0 = true
    }

    override fun isAccessed(): Boolean = accessed0
    private var default0: Long? = null
    internal fun setDefault(v: Long) {
        default0 = v
    }

    internal fun getDefault() = default0
    override fun cloneJson(): JsonParserLong = this
}

public class JsonParserDouble(public var value: Double) : IJsonParserValue {
    private var accessed0 = false
    override fun setAccessed() {
        accessed0 = true
    }

    override fun isAccessed(): Boolean = accessed0
    private var default0: Double? = null
    internal fun setDefault(v: Double) {
        default0 = v
    }

    internal fun getDefault() = default0
    override fun cloneJson(): JsonParserDouble = this
}

public class JsonParserString(public var value: String) : IJsonParserValue {
    private var accessed0 = false
    override fun setAccessed() {
        accessed0 = true
    }

    override fun isAccessed(): Boolean = accessed0
    private var default0: String? = null
    internal fun setDefault(v: String) {
        default0 = v
    }

    internal fun getDefault() = default0
    override fun cloneJson(): JsonParserString = this
}

public class JsonParserBoolean(public var value: Boolean) : IJsonParserValue {
    private var accessed0 = false
    override fun setAccessed() {
        accessed0 = true
    }

    override fun isAccessed(): Boolean = accessed0
    private var default0: Boolean? = null
    internal fun setDefault(v: Boolean) {
        default0 = v
    }

    internal fun getDefault() = default0
    override fun cloneJson(): JsonParserBoolean = this
}

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
                    i++
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

    public fun jsonToString(data: IJsonParserValue, printDefaults: Boolean): String {
        return jsonToString(data, "", printDefaults)
    }

    private fun jsonToString(data: IJsonParserValue, indention: String, printDefaults: Boolean): String {
        val r = when (data) {
            is JsonParserObject -> {
                if (data.isEmpty()) {
                    "{}"
                } else {
                    var res = "{\n"
                    for ((k, v) in data.toList().sortedBy { it.first }) {
                        res += "$indention    \"$k\": ${jsonToString(v, indention + "    ", printDefaults)},\n"
                    }
                    "$res$indention}"
                }
            }
            is JsonParserArray -> {
                if (data.isEmpty()) {
                    "[]"
                } else {
                    var res = "[\n"
                    for (e in data) {
                        res += "$indention    ${jsonToString(e, indention + "    ", printDefaults)},\n"
                    }
                    "$res$indention]"
                }
            }
            is JsonParserBoolean -> {
                if (data.getDefault() == null || !printDefaults) {
                    "${data.value}"
                } else {
                    "${data.value} /* '${data.getDefault()}' */"
                }
            }
            is JsonParserInt -> {
                if (data.getDefault() == null || !printDefaults) {
                    "${data.value}"
                } else {
                    "${data.value} /* '${data.getDefault()}' */"
                }
            }
            is JsonParserLong -> {
                if (data.getDefault() == null || !printDefaults) {
                    "${data.value}"
                } else {
                    "${data.value} /* '${data.getDefault()}' */"
                }
            }
            is JsonParserDouble -> {
                if (data.getDefault() == null || !printDefaults) {
                    "${data.value}"
                } else {
                    "${data.value} /* '${data.getDefault()}' */"
                }
            }
            is JsonParserString -> {
                if (data.getDefault() == null || !printDefaults) {
                    "\"${encodeString(data.value)}\""
                } else {
                    "\"${encodeString(data.value)}\" /* '${data.getDefault()}' */"
                }
            }
            else -> throw Exception("unknown JSON element G $data")
        }
        if (data.isAccessed() || !printDefaults) {
            return r
        } else {
            return "$r /* unused */"
        }
    }

    public fun stringToJson(data: String): IJsonParserValue {
        val res = readValueAt(data, 0).second
        return res
    }

    public fun fileToJson(fileName: String, autoformat: Boolean = true): IJsonParserValue {
        try {
            val fileStr = File(fileName).readAsString()
            val json = JsonParser().stringToJson(fileStr) as IJsonParserValue
            if (autoformat) {
                File(fileName).withOutputStream { out ->
                    out.println(JsonParser().jsonToString(json, false))
                }
            }
            return json
        } catch (e: Throwable) {
            println(fileName)
            throw e
        }
    }

    public fun fileMergeToJson(fileNames: List<String>, autoformat: Boolean = true): JsonParserObject {
        var res = JsonParserObject(mutableMapOf())
        for (fileName in fileNames) {
            val json = fileToJson(fileName, autoformat) as JsonParserObject
            res.mergeWith(json)
        }
        return res
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
