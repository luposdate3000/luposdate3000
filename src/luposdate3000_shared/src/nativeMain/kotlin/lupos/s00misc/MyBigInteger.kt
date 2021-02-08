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

public actual class MyBigInteger {
    internal val s: String

    public actual constructor(s1: String) {
        s = s1
    }

    public actual constructor(s1: Int) {
        s = "" + s1
    }

    public actual operator fun compareTo(other: MyBigInteger): Int {
        throw object : NotImplementedException("MyBigInteger", "compareTo not implemented") {}
    }

    public actual fun toDouble(): Double {
        throw object : NotImplementedException("MyBigDecimal", "toDouble not implemented") {}
    }

    public actual operator fun plus(other: MyBigInteger): MyBigInteger {
        throw object : NotImplementedException("MyBigInteger", "plus not implemented") {}
    }

    public actual operator fun minus(other: MyBigInteger): MyBigInteger {
        throw object : NotImplementedException("MyBigInteger", "minus not implemented") {}
    }

    public actual operator fun times(other: MyBigInteger): MyBigInteger {
        throw object : NotImplementedException("MyBigInteger", "times not implemented") {}
    }

    public actual operator fun div(other: MyBigInteger): MyBigInteger {
        throw object : NotImplementedException("MyBigInteger", "div not implemented") {}
    }

    public actual fun toMyBigDecimal(): MyBigDecimal {
        throw object : NotImplementedException("MyBigInteger", "toMyBigDecimal not implemented") {}
    }

    public actual fun abs(): MyBigInteger {
        throw object : NotImplementedException("MyBigInteger", "abs not implemented") {}
    }

    public actual override fun toString(): String = s
    public actual override fun equals(other: Any?): Boolean = other is MyBigInteger && s == other.s
    public actual override fun hashCode(): Int {
        return s.hashCode()
    }
}
