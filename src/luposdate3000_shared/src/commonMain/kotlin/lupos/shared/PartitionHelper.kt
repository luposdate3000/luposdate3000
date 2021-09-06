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

public class PartitionHelper() {
    public var partition: MutableMap<Long, Int> = mutableMapOf<Long, Int>() // uuid->partition
    internal var keys = mutableListOf<PartitionHelper3>()

    public fun startUp() {
        println("PartitionHelper.startUp")
        keys = mutableListOf()
        partition = mutableMapOf()
    }

    public fun tearDown() {
        println("PartitionHelper.tearDown")
        SanityCheck(
            { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_shared/src/commonMain/kotlin/lupos/shared/PartitionHelper.kt:31"/*SOURCE_FILE_END*/ },
            {
                for (k in keys) {
                    for (i in 0 until k.debugMarker.size) {
                        SanityCheck.check(
                            { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_shared/src/commonMain/kotlin/lupos/shared/PartitionHelper.kt:36"/*SOURCE_FILE_END*/ },
                            { k.debugMarker[i] == 3 },
                            { "${k.uuid}, $i ${ k.debugMarker.toList() }" }
                        )
                    }
                }
            }
        )
    }

    internal fun checkDebugMarker(arr: IntArray, off: Int, isSender: Boolean) {
        when (arr[off]) {
            0 -> { // nothing so far
                if (isSender) {
                    arr[off] = 1
                } else {
                    arr[off] = 2
                }
            }
            1 -> { // sender only
                if (isSender) {
                    TODO()
                } else {
                    arr[off] = 3
                }
            }
            2 -> { // receive only
                if (isSender) {
                    arr[off] = 3
                } else {
                    TODO()
                }
            }
            3 -> { // read twice - send+receive
                TODO()
            }
            else -> { // unreachable
                TODO()
            }
        }
    }

    public fun getKeysFor(uuid: Long, query: IQuery, count: Int, isSender: Boolean): IntArray {
        val res = getKeysForInternal(uuid, query, count, isSender)
        println("PartitionHelper $uuid ${res.keys.toList()} $isSender")
        SanityCheck(
            { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_shared/src/commonMain/kotlin/lupos/shared/PartitionHelper.kt:82"/*SOURCE_FILE_END*/ },
            {
                for (i in 0 until count) {
                    checkDebugMarker(res.debugMarker, i, isSender)
                }
            }
        )
        return res.keys
    }

    private fun getKeysForInternal(uuid: Long, query: IQuery, count: Int, isSender: Boolean): PartitionHelper3 {
        loop@ for (key in keys) {
            if (key.uuid != uuid) {
                continue@loop
            }
            if (key.partition.size != partition.size) {
                println("PartitionHelper $uuid can not use it A ${key.partition} $partition")
                continue@loop
            }
            for ((k, v) in partition) {
                if (key.partition[k] != v || k == uuid) {
                    println("PartitionHelper $uuid can not use it B ${key.partition} $partition")
                    continue@loop
                }
            }
            return key
        }
        val m = mutableMapOf<Long, Int>()
        m.putAll(partition)
        val res = PartitionHelper3(uuid, m, IntArray(count) { query.createPartitionKey() })
        keys.add(res)
        return res
    }

    public fun getKeyFor(uuid: Long, query: IQuery, count: Int, isSender: Boolean): Int {
        val r = getKeysForInternal(uuid, query, count, isSender)
        val res = r.keys
        val p = partition[uuid]
        if (p == null) {
            if (res.size == 1) {
                println("PartitionHelper $uuid ${res[0]} $isSender")
                SanityCheck(
                    { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_shared/src/commonMain/kotlin/lupos/shared/PartitionHelper.kt:124"/*SOURCE_FILE_END*/ },
                    {
                        checkDebugMarker(r.debugMarker, 0, isSender)
                    }
                )
                return res[0]
            } else {
                TODO("error here")
            }
        } else {
            println("PartitionHelper $uuid ${res[p]} $isSender")
            SanityCheck(
                { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_shared/src/commonMain/kotlin/lupos/shared/PartitionHelper.kt:136"/*SOURCE_FILE_END*/ },
                {
                    checkDebugMarker(r.debugMarker, p, isSender)
                }
            )
            return res[p]
        }
    }
}

internal class PartitionHelper3(internal val uuid: Long, internal var partition: Map<Long, Int>, internal val keys: IntArray) {
    internal val debugMarker = IntArray(keys.size)
}
