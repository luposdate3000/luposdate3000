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
package lupos.dictionary

public object DictionaryShared {
    /*to most significant bit leads to signed errors because toInt sadly performs a whole reencoding of the int and stores it completely different*/
    internal const val mask1 = 0x40000000/*first 2 bit*/
    internal const val mask2 = 0x20000000/*first 3 bit*/
    internal const val mask3 = 0x30000000/*first 4 bit*/
    internal const val mask6 = 0x3E000000/*first 7 bit*/
    internal const val filter2 = 0x1FFFFFFF
    internal const val filter3 = 0x0FFFFFFF
    internal const val filter6 = 0x01FFFFFF
    internal const val flaggedValueLocalBnode = 0x00000000/*first 4 bit*/ /*required to be 0 byDictionaryExt.booleanTrueValue*/
    internal const val flaggedValueLocalIri = 0x10000000/*first 4 bit*/
    internal const val flaggedValueLocalTyped = 0x20000000/*first 4 bit*/
    internal const val flaggedValueLocalInt = 0x30000000/*first 7 bit*/
    internal const val flaggedValueLocalDecimal = 0x34000000/*first 7 bit*/
    internal const val flaggedValueLocalDouble = 0x38000000/*first 7 bit*/
    internal const val flaggedValueLocalFloat = 0x3C000000/*first 7 bit*/
    internal const val flaggedValueLocalLangTagged = 0x3E000000/*first 7 bit*/
    internal const val flaggedValueGlobalBnode = 0x40000000/*first 3 bit*/
    internal const val flaggedValueGlobal = 0x60000000/*first 3 bit*/
    internal const val emptyString = ""
    public fun isGlobalBNode(value: Int): Boolean = (value and mask3) == flaggedValueGlobalBnode
}
