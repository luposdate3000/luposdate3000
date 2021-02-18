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
package lupos.s02buildSyntaxTree.sparql1_1

import kotlin.jvm.JvmField

public object BuiltInFunctionsExt {
    public const val ABS: BuiltInFunctions = 0
    public const val BNODE: BuiltInFunctions = 1
    public const val BOUND: BuiltInFunctions = 2
    public const val CEIL: BuiltInFunctions = 3
    public const val COALESCE: BuiltInFunctions = 4
    public const val CONCAT: BuiltInFunctions = 5
    public const val CONTAINS: BuiltInFunctions = 6
    public const val DATATYPE: BuiltInFunctions = 7
    public const val DAY: BuiltInFunctions = 8
    public const val ENCODE_FOR_URI: BuiltInFunctions = 9
    public const val Exists: BuiltInFunctions = 10
    public const val FLOOR: BuiltInFunctions = 11
    public const val HOURS: BuiltInFunctions = 12
    public const val IF: BuiltInFunctions = 13
    public const val IRI: BuiltInFunctions = 14
    public const val LANG: BuiltInFunctions = 15
    public const val LANGMATCHES: BuiltInFunctions = 16
    public const val LCASE: BuiltInFunctions = 17
    public const val MD5: BuiltInFunctions = 18
    public const val MINUTES: BuiltInFunctions = 19
    public const val MONTH: BuiltInFunctions = 20
    public const val NOW: BuiltInFunctions = 21
    public const val NotExists: BuiltInFunctions = 22
    public const val RAND: BuiltInFunctions = 23
    public const val ROUND: BuiltInFunctions = 24
    public const val RegexExpression: BuiltInFunctions = 25
    public const val SECONDS: BuiltInFunctions = 26
    public const val SHA1: BuiltInFunctions = 27
    public const val SHA256: BuiltInFunctions = 28
    public const val SHA384: BuiltInFunctions = 29
    public const val SHA512: BuiltInFunctions = 30
    public const val STR: BuiltInFunctions = 31
    public const val STRAFTER: BuiltInFunctions = 32
    public const val STRBEFORE: BuiltInFunctions = 33
    public const val STRDT: BuiltInFunctions = 34
    public const val STRENDS: BuiltInFunctions = 35
    public const val STRLANG: BuiltInFunctions = 36
    public const val STRLEN: BuiltInFunctions = 37
    public const val STRSTARTS: BuiltInFunctions = 38
    public const val STRUUID: BuiltInFunctions = 39
    public const val StrReplaceExpression: BuiltInFunctions = 40
    public const val SubstringExpression: BuiltInFunctions = 41
    public const val TIMEZONE: BuiltInFunctions = 42
    public const val TZ: BuiltInFunctions = 43
    public const val UCASE: BuiltInFunctions = 44
    public const val URI: BuiltInFunctions = 45
    public const val UUID: BuiltInFunctions = 46
    public const val YEAR: BuiltInFunctions = 47
    public const val isBLANK: BuiltInFunctions = 48
    public const val isIRI: BuiltInFunctions = 49
    public const val isLITERAL: BuiltInFunctions = 50
    public const val isNUMERIC: BuiltInFunctions = 51
    public const val isURI: BuiltInFunctions = 52
    public const val sameTerm: BuiltInFunctions = 53
    public const val values_size: Int = 54
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
