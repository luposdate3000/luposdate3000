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
public expect class MyBigDecimal {
    public constructor(s1: String)
    public constructor(s1: Double)
    public constructor(s1: Int)
    public operator fun compareTo(other: MyBigDecimal): Int
    public fun toPlainString(): String
    public fun toDouble(): Double
    public fun toMyBigInteger(): MyBigInteger
    public operator fun plus(other: MyBigDecimal): MyBigDecimal
    public operator fun minus(other: MyBigDecimal): MyBigDecimal
    public operator fun times(other: MyBigDecimal): MyBigDecimal
    public operator fun div(other: MyBigDecimal): MyBigDecimal
    public fun ceil(): MyBigDecimal
    public fun floor(): MyBigDecimal
    public fun round(): MyBigDecimal
    public fun abs(): MyBigDecimal
    public override fun toString(): String
    public override fun equals(other: Any?): Boolean
    public override fun hashCode(): Int
}
