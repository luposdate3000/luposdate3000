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
package lupos.shared

import kotlin.jvm.JvmField

public object ETripleComponentTypeExt {
    public const val BLANK_NODE: ETripleComponentType = 0
    public const val BOOLEAN: ETripleComponentType = 1
    public const val DATE_TIME: ETripleComponentType = 2
    public const val DECIMAL: ETripleComponentType = 3
    public const val DOUBLE: ETripleComponentType = 4
    public const val ERROR: ETripleComponentType = 5
    public const val FLOAT: ETripleComponentType = 6
    public const val INTEGER: ETripleComponentType = 7
    public const val IRI: ETripleComponentType = 8
    public const val STRING: ETripleComponentType = 9
    public const val STRING_LANG: ETripleComponentType = 10
    public const val STRING_TYPED: ETripleComponentType = 11
    public const val UNDEF: ETripleComponentType = 12
    public const val values_size: Int = 13

    @JvmField
    public val names: Array<String> = arrayOf(
        "BLANK_NODE",
        "BOOLEAN",
        "DATE_TIME",
        "DECIMAL",
        "DOUBLE",
        "ERROR",
        "FLOAT",
        "INTEGER",
        "IRI",
        "STRING",
        "STRING_LANG",
        "STRING_TYPED",
        "UNDEF",
    )
}
