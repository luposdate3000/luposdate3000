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
    internal const val Blank_Node: EVariablePlaceholder = 0
    internal const val Boolean: EVariablePlaceholder = 1
    internal const val ByteArrayWrapper: EVariablePlaceholder = 2
    internal const val DateTime_day: EVariablePlaceholder = 3
    internal const val DateTime_hours: EVariablePlaceholder = 4
    internal const val DateTime_minutes: EVariablePlaceholder = 5
    internal const val DateTime_month: EVariablePlaceholder = 6
    internal const val DateTime_seconds: EVariablePlaceholder = 7
    internal const val DateTime_timezone: EVariablePlaceholder = 8
    internal const val DateTime_typed_content: EVariablePlaceholder = 9
    internal const val DateTime_tz: EVariablePlaceholder = 10
    internal const val DateTime_year: EVariablePlaceholder = 11
    internal const val Decimal: EVariablePlaceholder = 12
    internal const val DictionaryID: EVariablePlaceholder = 13
    internal const val Double: EVariablePlaceholder = 14
    internal const val Empty: EVariablePlaceholder = 15
    internal const val Integer: EVariablePlaceholder = 16
    internal const val Iri: EVariablePlaceholder = 17
    internal const val String_content: EVariablePlaceholder = 18
    internal const val String_lang: EVariablePlaceholder = 19
    internal const val String_type: EVariablePlaceholder = 20
    internal const val values_size: Int = 21

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
