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

    public fun getKeysFor(uuid: Long, query: IQuery, count: Int, isSender: Boolean): IntArray {
        loop@for (key in keys) {
            if (key.uuid != uuid) {
                continue@loop
            }
            if (isSender) { // partition-list does DOES contain its own
                if (key.partition.size != partition.size) {
                    continue
                }
                for ((k, v) in partition) {
                    if (key.partition[k] != v) {
                        continue@loop
                    }
                }
            } else { // partition-list DOES NOT contain its own uuid
                if (key.partition.size != partition.size + 1) {
                    continue
                }
                for ((k, v) in partition) {
                    if (key.partition[k] != v) {
                        continue@loop
                    }
                }
            }
            return key.keys
        }
        val m = mutableMapOf<Long, Int>()
        m.putAll(partition)
        val res = PartitionHelper3(uuid, m, IntArray(count) { query.createPartitionKey() })
        keys.add(res)
        return res.keys
    }
    public fun getKeyFor(uuid: Long, query: IQuery, count: Int, isSender: Boolean): Int {
        val res = getKeysFor(uuid, query, count, isSender)
        val p = partition[uuid]
        if (p == null) {
            if (res.size == 1) {
                return res[0]
            } else {
                TODO("error here")
            }
        } else {
            return res[p]
        }
    }
}
internal class PartitionHelper3(internal val uuid: Long, internal var partition: Map<Long, Int>, internal val keys: IntArray)
