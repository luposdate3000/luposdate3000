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
package lupos.s00misc

public actual class MyBigDecimal {
    internal val s: String
    internal val v: Double

    public actual constructor(s1: String) {
        s = s1
        v = s.toDouble()
    }

    public actual constructor(s1: Double) {
        s = "" + s1
        v = s.toDouble()
    }

    public actual constructor(s1: Int) {
        s = "" + s1
        v = s.toDouble()
    }

    public actual fun toPlainString(): String {
        return s
    }

    public actual operator fun compareTo(other: MyBigDecimal): Int {
        return v.compareTo(other.v)
    }

    public actual fun toDouble(): Double {
        return v
    }

    public actual operator fun plus(other: MyBigDecimal): MyBigDecimal {
        return MyBigDecimal(v + other.v)
    }

    public actual operator fun minus(other: MyBigDecimal): MyBigDecimal {
        return MyBigDecimal(v - other.v)
    }

    public actual operator fun times(other: MyBigDecimal): MyBigDecimal {
        return MyBigDecimal(v * other.v)
    }

    public actual operator fun div(other: MyBigDecimal): MyBigDecimal {
        return MyBigDecimal(v / other.v)
    }

    public actual fun ceil(): MyBigDecimal {
        return MyBigDecimal(kotlin.math.ceil(v))
    }

    public actual fun floor(): MyBigDecimal {
        return MyBigDecimal(kotlin.math.floor(v))
    }

    public actual fun round(): MyBigDecimal {
        var s2 = ""
        val s3 = s.toCharArray()
        var negative = false
        var p = 0
        if (s.startsWith("-")) {
            negative = true
            p = 1
            s2 = "-"
        }
        for (i in p until s3.size) {
            var c = s3[i]
            if (c >= '0' && c <= '9') {
                s2 += c
            } else {
                for (j in i + 1 until s3.size) {
                    c = s3[j]
                    if (c >= '0' && c <= '4') {
                        return MyBigDecimal(s2)
                    } else if (c >= '5' && c <= '9') {
                        val res: MyBigDecimal
                        if (negative) {
                            res = MyBigDecimal(s2) - MyBigDecimal(1)
                        } else {
                            res = MyBigDecimal(s2) + MyBigDecimal(1)
                        }
                        return res
                    }
                }
            }
        }
        return this
    }

    public actual fun toMyBigInteger(): MyBigInteger {
        return MyBigInteger(kotlin.math.floor(v).toInt())
    }

    public actual fun abs(): MyBigDecimal {
        return MyBigDecimal(kotlin.math.abs(v))
    }

    public actual override fun toString(): String = s
    public actual override fun equals(other: Any?): Boolean = other is MyBigDecimal && s == other.s
    public actual override fun hashCode(): Int {
        return s.hashCode()
    }
}
