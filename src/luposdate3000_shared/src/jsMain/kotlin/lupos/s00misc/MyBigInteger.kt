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
internal external fun BigInt(i:Int):BigIntT
internal external fun BigInt(s:String):BigIntT

internal external class BigIntT{
internal  operator fun plus(other: BigIntT): BigIntT 
internal  operator fun minus(other: BigIntT): BigIntT 
internal  operator fun times(other: BigIntT): BigIntT 
internal  operator fun div(other: BigIntT): BigIntT 
}
internal fun bigIntTCompareSmallerThan(a:BigIntT,b:BigIntT):Boolean{
return js("a<b")
}
public actual class MyBigInteger {
    internal val s: String
internal val v:BigIntT
    public actual constructor(s1: String) {
        s = s1
v=BigInt(s)
    }
    public actual constructor(s1: Int) {
        s = "" + s1
v=BigInt(s)
    }
    internal  constructor(s1: BigIntT) {
v=s1
        s = "" + v
    }
    public actual operator fun compareTo(other: MyBigInteger): Int {
if(v==other.v){
return 0
}else if(bigIntTCompareSmallerThan(v,other.v)){
return -1
}else{
return 1
}
    }
    public actual fun toDouble(): Double {
return s.toDouble()
    }
    public actual operator fun plus(other: MyBigInteger): MyBigInteger {
return MyBigInteger(v+other.v)
    }
    public actual operator fun minus(other: MyBigInteger): MyBigInteger {
return MyBigInteger(v-other.v)
    }
    public actual operator fun times(other: MyBigInteger): MyBigInteger {
return MyBigInteger(v*other.v)
    }
    public actual operator fun div(other: MyBigInteger): MyBigInteger {
return MyBigInteger(v/other.v)
    }
    public actual fun toMyBigDecimal(): MyBigDecimal {
return MyBigDecimal("$s.0")
    }
    public actual fun abs(): MyBigInteger {
if(bigIntTCompareSmallerThan(v,BigInt(0))){
return MyBigInteger(v*BigInt(-1))
}else{
return this
}
    }
    public actual override fun toString(): String = s
    public actual override fun equals(other: Any?): Boolean = other is MyBigInteger && s == other.s
    public actual override fun hashCode(): Int {
        return s.hashCode()
    }
}
