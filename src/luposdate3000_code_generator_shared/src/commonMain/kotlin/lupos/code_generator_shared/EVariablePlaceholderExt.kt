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
package lupos.code_generator_shared

import kotlin.jvm.JvmField

internal object EVariablePlaceholderExt {
    internal const val Blank_Node: EVariablePlaceholder = 0 // 0x00000000
    internal const val Boolean: EVariablePlaceholder = 1 // 0x00000001
    internal const val ByteArrayWrapper: EVariablePlaceholder = 2 // 0x00000002
    internal const val DateTime_day: EVariablePlaceholder = 3 // 0x00000003
    internal const val DateTime_hours: EVariablePlaceholder = 4 // 0x00000004
    internal const val DateTime_minutes: EVariablePlaceholder = 5 // 0x00000005
    internal const val DateTime_month: EVariablePlaceholder = 6 // 0x00000006
    internal const val DateTime_seconds: EVariablePlaceholder = 7 // 0x00000007
    internal const val DateTime_timezone: EVariablePlaceholder = 8 // 0x00000008
    internal const val DateTime_typed_content: EVariablePlaceholder = 9 // 0x00000009
    internal const val DateTime_tz: EVariablePlaceholder = 10 // 0x0000000a
    internal const val DateTime_year: EVariablePlaceholder = 11 // 0x0000000b
    internal const val Decimal: EVariablePlaceholder = 12 // 0x0000000c
    internal const val DictionaryID: EVariablePlaceholder = 13 // 0x0000000d
    internal const val Double: EVariablePlaceholder = 14 // 0x0000000e
    internal const val Empty: EVariablePlaceholder = 15 // 0x0000000f
    internal const val Integer: EVariablePlaceholder = 16 // 0x00000010
    internal const val Iri: EVariablePlaceholder = 17 // 0x00000011
    internal const val String_content: EVariablePlaceholder = 18 // 0x00000012
    internal const val String_lang: EVariablePlaceholder = 19 // 0x00000013
    internal const val String_type: EVariablePlaceholder = 20 // 0x00000014
    internal const val values_size: Int = 21
    internal const val values_mask: Int = 31 // 0x0000001f
    internal const val values_mask_inversed: Int = 2147483616 // 0x7fffffe0

    @JvmField
    internal val names: Array<String> = arrayOf(
        "Blank_Node",
        "Boolean",
        "ByteArrayWrapper",
        "DateTime_day",
        "DateTime_hours",
        "DateTime_minutes",
        "DateTime_month",
        "DateTime_seconds",
        "DateTime_timezone",
        "DateTime_typed_content",
        "DateTime_tz",
        "DateTime_year",
        "Decimal",
        "DictionaryID",
        "Double",
        "Empty",
        "Integer",
        "Iri",
        "String_content",
        "String_lang",
        "String_type",
    )
}
