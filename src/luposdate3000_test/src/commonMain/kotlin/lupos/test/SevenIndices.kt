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
package lupos.test

public class SevenIndices {

    private val s = mutableMapOf<String, Array<Pair<String, String>>>()
    private val sp = mutableMapOf<Pair<String, String>, Array<String>>()
    private val po = mutableMapOf<Pair<String, String>, Array<String>>()

    public fun s(key: String): Array<Pair<String, String>> = this.s[key] ?: arrayOf()
    public fun sp(key1: String, key2: String): Array<String> = this.sp[Pair(key1, key2)] ?: arrayOf()
    public fun po(key1: String, key2: String): Array<String> = this.po[Pair(key1, key2)] ?: arrayOf()
    public fun distinct() {
        distinctOneKeyMap(this.s)
        distinctTwoKeysMap(this.sp)
        distinctTwoKeysMap(this.po)
    }

    public fun add(triple_s: String, triple_p: String, triple_o: String) {
        addToOneKeyMap(this.s, triple_s, triple_p, triple_o)
        addToTwoKeysMap(this.sp, triple_s, triple_p, triple_o)
        addToTwoKeysMap(this.po, triple_p, triple_o, triple_s)
    }

    private fun addToOneKeyMap(onekeymap: MutableMap<String, Array<Pair<String, String>>>, key: String, value1: String, value2: String) {
        val values = onekeymap[key]
        val value = Pair(value1, value2)
        if (values == null) {
            onekeymap[key] = arrayOf(value)
        } else {
            onekeymap[key] = values + value
        }
    }

    private fun addToTwoKeysMap(twokeysmap: MutableMap<Pair<String, String>, Array<String>>, key1: String, key2: String, value: String) {
        val key = Pair(key1, key2)
        val values = twokeysmap[key]
        if (values == null) {
            twokeysmap[key] = arrayOf(value)
        } else {
            twokeysmap[key] = values + value
        }
    }

    private fun distinctOneKeyMap(onekeymap: MutableMap<String, Array<Pair<String, String>>>) {
        for (entry in onekeymap) {
            entry.setValue(entry.value.toMutableSet().toTypedArray())
        }
    }

    private fun distinctTwoKeysMap(twokeysmap: MutableMap<Pair<String, String>, Array<String>>) {
        for (entry in twokeysmap) {
            entry.setValue(entry.value.toMutableSet().toTypedArray())
        }
    }
}
