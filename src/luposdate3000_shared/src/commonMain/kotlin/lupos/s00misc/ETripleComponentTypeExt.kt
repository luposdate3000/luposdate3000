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

import kotlin.jvm.JvmField

public object ETripleComponentTypeExt {
    public const val BLANK_NODE: ETripleComponentType = 0
    public const val BOOLEAN: ETripleComponentType = 1
    public const val DECIMAL: ETripleComponentType = 2
    public const val DOUBLE: ETripleComponentType = 3
    public const val INTEGER: ETripleComponentType = 4
    public const val IRI: ETripleComponentType = 5
    public const val STRING: ETripleComponentType = 6
    public const val STRING_LANG: ETripleComponentType = 7
    public const val STRING_TYPED: ETripleComponentType = 8
    public const val values_size: Int = 9
    @JvmField
    public val names: Array<String> = arrayOf(
        "BLANK_NODE",
        "BOOLEAN",
        "DECIMAL",
        "DOUBLE",
        "INTEGER",
        "IRI",
        "STRING",
        "STRING_LANG",
        "STRING_TYPED",
    )
}
