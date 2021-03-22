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

import java.math.BigInteger
import kotlin.math.abs

public actual class MyBigInteger {
    internal val s: String
    private val v: BigInteger

    public actual constructor(s1: String) {
        v = BigInteger(s1)
        s = s1
    }

    public constructor(v1: BigInteger) {
        v = v1
        s = v1.toString()
    }

    public actual constructor(s1: Int) {
        v = BigInteger("" + s1)
        s = "" + s1
    }

    public actual fun toDouble(): Double {
        return v.toDouble()
    }

    public actual operator fun compareTo(other: MyBigInteger): Int {
        return v.compareTo(other.v)
    }

    public actual operator fun plus(other: MyBigInteger): MyBigInteger {
        return MyBigInteger(v.add(other.v))
    }

    public actual operator fun minus(other: MyBigInteger): MyBigInteger {
        return MyBigInteger(v.subtract(other.v))
    }

    public actual operator fun times(other: MyBigInteger): MyBigInteger {
        return MyBigInteger(v.multiply(other.v))
    }

    public actual fun toMyBigDecimal(): MyBigDecimal {
        return MyBigDecimal(v.toBigDecimal())
    }

    public actual fun abs(): MyBigInteger {
        return MyBigInteger(v.abs())
    }

    public actual override fun toString(): String = s
    public actual override fun equals(other: Any?): Boolean = other is MyBigInteger && s == other.s
    public actual override fun hashCode(): Int {
        return s.hashCode()
    }
}
