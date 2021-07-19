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
package lupos.codegen

val registeredTypes: Map<String, CodeType> = mutableMapOf(
    "Error" to CodeType(null, null),
    "Boolean" to CodeType("Boolean", null),
    "Unit" to CodeType("Unit", null),
    "Int" to CodeType("Int", null),
    "Double" to CodeType("Double", null),
    "BigInteger" to CodeType("BigInteger", "com.ionspin.kotlin.bignum.integer"),
    "BigDecimal" to CodeType("BigDecimal", "com.ionspin.kotlin.bignum.decimal"),
    "ByteArrayWrapper" to CodeType("ByteArrayWrapper", "lupos.shared"),
    "ByteArrayWrapperDouble" to CodeType("ByteArrayWrapper", "lupos.shared"),
    "ByteArrayWrapperFloat" to CodeType("ByteArrayWrapper", "lupos.shared"),
    "ByteArrayWrapperInteger" to CodeType("ByteArrayWrapper", "lupos.shared"),
    "ByteArrayWrapperDecimal" to CodeType("ByteArrayWrapper", "lupos.shared"),
)
