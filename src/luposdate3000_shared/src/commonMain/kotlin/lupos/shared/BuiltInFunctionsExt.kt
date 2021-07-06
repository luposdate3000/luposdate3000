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

public object BuiltInFunctionsExt {
    public const val ABS: BuiltInFunctions = 0 // 0x00000000
    public const val BNODE: BuiltInFunctions = 1 // 0x00000001
    public const val BOUND: BuiltInFunctions = 2 // 0x00000002
    public const val CEIL: BuiltInFunctions = 3 // 0x00000003
    public const val COALESCE: BuiltInFunctions = 4 // 0x00000004
    public const val CONCAT: BuiltInFunctions = 5 // 0x00000005
    public const val CONTAINS: BuiltInFunctions = 6 // 0x00000006
    public const val DATATYPE: BuiltInFunctions = 7 // 0x00000007
    public const val DAY: BuiltInFunctions = 8 // 0x00000008
    public const val ENCODE_FOR_URI: BuiltInFunctions = 9 // 0x00000009
    public const val Exists: BuiltInFunctions = 10 // 0x0000000a
    public const val FLOOR: BuiltInFunctions = 11 // 0x0000000b
    public const val HOURS: BuiltInFunctions = 12 // 0x0000000c
    public const val IF: BuiltInFunctions = 13 // 0x0000000d
    public const val IRI: BuiltInFunctions = 14 // 0x0000000e
    public const val LANG: BuiltInFunctions = 15 // 0x0000000f
    public const val LANGMATCHES: BuiltInFunctions = 16 // 0x00000010
    public const val LCASE: BuiltInFunctions = 17 // 0x00000011
    public const val MD5: BuiltInFunctions = 18 // 0x00000012
    public const val MINUTES: BuiltInFunctions = 19 // 0x00000013
    public const val MONTH: BuiltInFunctions = 20 // 0x00000014
    public const val NOW: BuiltInFunctions = 21 // 0x00000015
    public const val NotExists: BuiltInFunctions = 22 // 0x00000016
    public const val RAND: BuiltInFunctions = 23 // 0x00000017
    public const val ROUND: BuiltInFunctions = 24 // 0x00000018
    public const val RegexExpression: BuiltInFunctions = 25 // 0x00000019
    public const val SECONDS: BuiltInFunctions = 26 // 0x0000001a
    public const val SHA1: BuiltInFunctions = 27 // 0x0000001b
    public const val SHA256: BuiltInFunctions = 28 // 0x0000001c
    public const val SHA384: BuiltInFunctions = 29 // 0x0000001d
    public const val SHA512: BuiltInFunctions = 30 // 0x0000001e
    public const val STR: BuiltInFunctions = 31 // 0x0000001f
    public const val STRAFTER: BuiltInFunctions = 32 // 0x00000020
    public const val STRBEFORE: BuiltInFunctions = 33 // 0x00000021
    public const val STRDT: BuiltInFunctions = 34 // 0x00000022
    public const val STRENDS: BuiltInFunctions = 35 // 0x00000023
    public const val STRLANG: BuiltInFunctions = 36 // 0x00000024
    public const val STRLEN: BuiltInFunctions = 37 // 0x00000025
    public const val STRSTARTS: BuiltInFunctions = 38 // 0x00000026
    public const val STRUUID: BuiltInFunctions = 39 // 0x00000027
    public const val StrReplaceExpression: BuiltInFunctions = 40 // 0x00000028
    public const val SubstringExpression: BuiltInFunctions = 41 // 0x00000029
    public const val TIMEZONE: BuiltInFunctions = 42 // 0x0000002a
    public const val TZ: BuiltInFunctions = 43 // 0x0000002b
    public const val UCASE: BuiltInFunctions = 44 // 0x0000002c
    public const val URI: BuiltInFunctions = 45 // 0x0000002d
    public const val UUID: BuiltInFunctions = 46 // 0x0000002e
    public const val YEAR: BuiltInFunctions = 47 // 0x0000002f
    public const val isBLANK: BuiltInFunctions = 48 // 0x00000030
    public const val isIRI: BuiltInFunctions = 49 // 0x00000031
    public const val isLITERAL: BuiltInFunctions = 50 // 0x00000032
    public const val isNUMERIC: BuiltInFunctions = 51 // 0x00000033
    public const val isURI: BuiltInFunctions = 52 // 0x00000034
    public const val sameTerm: BuiltInFunctions = 53 // 0x00000035
    public const val values_size: Int = 54
    public const val values_mask: Int = 63 // 0x0000003f
    public const val values_mask_inversed: Int = 2147483584 // 0x7fffffc0

    @JvmField
    public val names: Array<String> = arrayOf(
        "ABS",
        "BNODE",
        "BOUND",
        "CEIL",
        "COALESCE",
        "CONCAT",
        "CONTAINS",
        "DATATYPE",
        "DAY",
        "ENCODE_FOR_URI",
        "Exists",
        "FLOOR",
        "HOURS",
        "IF",
        "IRI",
        "LANG",
        "LANGMATCHES",
        "LCASE",
        "MD5",
        "MINUTES",
        "MONTH",
        "NOW",
        "NotExists",
        "RAND",
        "ROUND",
        "RegexExpression",
        "SECONDS",
        "SHA1",
        "SHA256",
        "SHA384",
        "SHA512",
        "STR",
        "STRAFTER",
        "STRBEFORE",
        "STRDT",
        "STRENDS",
        "STRLANG",
        "STRLEN",
        "STRSTARTS",
        "STRUUID",
        "StrReplaceExpression",
        "SubstringExpression",
        "TIMEZONE",
        "TZ",
        "UCASE",
        "URI",
        "UUID",
        "YEAR",
        "isBLANK",
        "isIRI",
        "isLITERAL",
        "isNUMERIC",
        "isURI",
        "sameTerm",
    )
}
