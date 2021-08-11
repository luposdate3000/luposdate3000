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

package lupos.simulator_iot.config

public class JsonParser {
    private fun readValueAt(data: String, off: Int): Pair<Int, Any> {
        var i = off
        while (i <data.length) {
            val c = data[i]
            when (c) {
                '{' -> return readMapAt(data, i)
                '[' -> return readArrayAt(data, i)
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '-', '+', '.' -> return readNumberAt(data, i)
                '"' -> return readStringAt(data, i)
                't', 'f', 'T', 'F' -> return readBooleanAt(data, i)
                ' ', '\t', '\n', '\r' -> { i++ }
                else -> TODO("unknown char at $off")
            }
        }
        return data.length to mutableMapOf<String, Any>()
    }
    private fun readMapAt(data: String, off: Int): Pair<Int, MutableMap<String, Any>> {
        var i = off + 1
        val res = mutableMapOf<String, Any>()

        var state = 0 // 0-> readKey, 1->readCollon, 2->readValue,3->readCommaOrEnd
        var key = ""

        while (i <data.length) {
            val c = data[i]
            when (c) {
                '}' -> return (i + 1) to res
                ' ', '\t', '\n', '\r' -> { i++ }
                '"' -> {
                    val (j, tmp) = readStringAt(data, i)
                    i = j
                    when (state) {
                        0 -> {
                            key = tmp
                            state = 1
                        }
                        2 -> {
                            res[key] = tmp
                            state = 3
                        }
                        else -> {
                            TODO("unknown char at $off")
                        }
                    }
                }
                else -> TODO("unknown char at $off")
            }
        }
        TODO("object not closed")
    }
    private fun readArrayAt(data: String, off: Int): Pair<Int, MutableList<Any>> {
        TODO()
    }
    private fun readNumberAt(data: String, off: Int): Pair<Int, Number> {
        TODO()
    }
    private fun readStringAt(data: String, off: Int): Pair<Int, String> {
        TODO()
    }
    private fun readBooleanAt(data: String, off: Int): Pair<Int, Boolean> {
        TODO()
    }

    public fun parseJSONString(data: String): Any {
        return readValueAt(data, 0)
    }
}
