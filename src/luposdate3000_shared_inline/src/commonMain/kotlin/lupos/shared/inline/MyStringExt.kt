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
package lupos.shared.inline
import lupos.shared.InvalidInputException

internal object MyStringExt {
    @Suppress("NOTHING_TO_INLINE")
    internal inline fun replaceEscapes(s: String, strictMode: Boolean): String {
        val res = StringBuilder()
        var i = 0
        while (i < s.length) {
            val c = s[i]
            if (c == '\\') {
                val c2 = s[i + 1]
                when (c2) {
                    'u' -> {
                        res.append("${s[i + 2]}${s[i + 3]}${s[i + 4]}${s[i + 5]}".toInt(radix = 16).toChar())
                        i += 6
                    }
                    'U' -> {
                        res.append("${s[i + 2]}${s[i + 3]}${s[i + 4]}${s[i + 5]}${s[i + 6]}${s[i + 7]}${s[i + 8]}${s[i + 9]}".toInt(radix = 16).toChar())
                        i += 10
                    }
                    't' -> {
                        res.append(0x0009.toChar())
                        i += 2
                    }
                    'b' -> {
                        res.append(0x0008.toChar())
                        i += 2
                    }
                    'n' -> {
                        res.append(0x000A.toChar())
                        i += 2
                    }
                    'r' -> {
                        res.append(0x000D.toChar())
                        i += 2
                    }
                    'f' -> {
                        res.append(0x000C.toChar())
                        i += 2
                    }
                    '~', '.', '-', '!', '$', '&', '\'', '(', ')', '*', '+', ',', ';', '=', '/', '?', '#', '@', '%', '_', '\\', '"' -> {
                        res.append(c2)
                        i += 2
                    }
                    else -> {
                        if (strictMode) {
                            throw InvalidInputException("invalid escape sequence '\\$c2' at offset $i in String '$s'")
                        } else {
                            res.append(c)
                            i++
                        }
                    }
                }
            } else {
                res.append(c)
                i++
            }
        }
        return res.toString()
    }
}
