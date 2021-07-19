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
package lupos.parser.turtle

import kotlin.jvm.JvmField

internal object Turtle2ParserStateExt {
    internal const val EOF: Turtle2ParserState = 0 // 0x00000000
    internal const val OBJECT: Turtle2ParserState = 1 // 0x00000001
    internal const val PREDICATE: Turtle2ParserState = 2 // 0x00000002
    internal const val STATEMENT: Turtle2ParserState = 3 // 0x00000003
    internal const val TRIPLE_END: Turtle2ParserState = 4 // 0x00000004
    internal const val TRIPLE_END_OR_OBJECT_IRI: Turtle2ParserState = 5 // 0x00000005
    internal const val TRIPLE_END_OR_OBJECT_STRING: Turtle2ParserState = 6 // 0x00000006
    internal const val values_size: Int = 7
    internal const val values_mask: Int = 7 // 0x00000007
    internal const val values_mask_inversed: Int = 2147483640 // 0x7ffffff8

    @JvmField
    internal val names: Array<String> = arrayOf(
        "EOF",
        "OBJECT",
        "PREDICATE",
        "STATEMENT",
        "TRIPLE_END",
        "TRIPLE_END_OR_OBJECT_IRI",
        "TRIPLE_END_OR_OBJECT_STRING",
    )
}
