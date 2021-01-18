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
package lupos.s02buildSyntaxTree.turtle
import kotlin.jvm.JvmField
internal object Turtle2ParserStateExt {
    internal const val EOF: Turtle2ParserState = 0
    internal const val OBJECT: Turtle2ParserState = 1
    internal const val PREDICATE: Turtle2ParserState = 2
    internal const val STATEMENT: Turtle2ParserState = 3
    internal const val TRIPLE_END: Turtle2ParserState = 4
    internal const val TRIPLE_END_OR_OBJECT_IRI: Turtle2ParserState = 5
    internal const val TRIPLE_END_OR_OBJECT_STRING: Turtle2ParserState = 6
    internal const val values_size: Int = 7
    @JvmField internal val names: Array<String> = arrayOf(
        "EOF",
        "OBJECT",
        "PREDICATE",
        "STATEMENT",
        "TRIPLE_END",
        "TRIPLE_END_OR_OBJECT_IRI",
        "TRIPLE_END_OR_OBJECT_STRING",
    )
}
