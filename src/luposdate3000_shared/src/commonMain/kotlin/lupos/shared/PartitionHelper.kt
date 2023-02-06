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
    public var partition: MutableMap<Int, Int> = mutableMapOf<Int, Int>() // partitionID->partition
    internal var keys = mutableListOf<PartitionHelper3>()

    public fun startUp() {
        // println("PartitionHelper.startUp")
        keys = mutableListOf()
        partition = mutableMapOf()
    }

    public fun tearDown() {
        // println("PartitionHelper.tearDown")
  if(SanityCheck.enabled)            {
                for (k in keys) {
                    for (i in 0 until k.debugMarker.size) {
                             if(SanityCheck.enabled){if(!(  k.debugMarker[i] == 3 )){throw Exception("SanityCheck failed")}}
                    }
                }
            }
    }

    internal fun checkDebugMarker(uuid: Long, partitionid: Int, arr: IntArray, off: Int, isSender: Boolean) {
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
                    TODO("$uuid $partitionid $off $isSender $partition")
                } else {
                    arr[off] = 3
                }
            }
            2 -> { // receive only
                if (isSender) {
                    arr[off] = 3
                } else {
                    TODO("$uuid $partitionid $off $isSender $partition")
                }
            }
            3 -> { // already send+receive
                TODO("$uuid $partitionid $off $isSender $partition")
            }
            else -> { // unreachable
                TODO("$uuid $partitionid $off $isSender $partition")
            }
        }
    }

    public fun getKeysFor(uuid: Long, partitionid: Int, query: IQuery, count: Int, isSender: Boolean, filterIndiceesFunc: (Int) -> IntArray): IntArray {
        val filterIndicees = filterIndiceesFunc(partition[partitionid]!!)
        val res = getKeysForInternal(uuid, partitionid, query, count)
        val myList = IntArray(filterIndicees.size) { res.keys[filterIndicees[it]] }
        // println("PartitionHelper $partitionid ${filterIndicees.map{it}} -> ${myList.toList()} $isSender $partition")
  if(SanityCheck.enabled)            {
                for (i in filterIndicees) {
                    checkDebugMarker(uuid, partitionid, res.debugMarker, i, isSender)
                }
            }
        
        return myList
    }

    public fun getKeysFor(uuid: Long, partitionid: Int, query: IQuery, count: Int, isSender: Boolean): IntArray {
        val res = getKeysForInternal(uuid, partitionid, query, count)
        // println("PartitionHelper $partitionid ${List(res.keys.size){it}} -> ${res.keys.toList()} $isSender $partition")
  if(SanityCheck.enabled)            {
                for (i in 0 until count) {
                    checkDebugMarker(uuid, partitionid, res.debugMarker, i, isSender)
                }
            }
        
        return res.keys
    }

    private fun getKeysForInternal(uuid: Long, partitionid: Int, query: IQuery, count: Int): PartitionHelper3 {
        loop@ for (key in keys) {
            if (key.uuid != uuid) {
                continue@loop
            }
            var skipA = 0
            var skipB = 0
            loop2@ for ((k, v) in partition) {
                if (k == partitionid) {
                    skipA++
                    continue@loop2
                }
                if (key.partition[k] != v) {
                    // println("PartitionHelper $uuid can not use it B ${key.partition} $partition")
                    continue@loop
                }
            }
            if (key.partition.contains(partitionid)) {
                skipB++
            }
            if (key.partition.size - skipB != partition.size - skipA) {
                // println("PartitionHelper $partitionid can not use it A ${key.partition} $partition")
                continue@loop
            }
            return key
        }
        val m = mutableMapOf<Int, Int>()
        m.putAll(partition)
        val res = PartitionHelper3(uuid, m, IntArray(count) { query.createPartitionKey() })
        keys.add(res)
        return res
    }

    public fun getKeyFor(uuid: Long, partitionid: Int, query: IQuery, count: Int, isSender: Boolean): Int {
        val r = getKeysForInternal(uuid, partitionid, query, count)
        val res = r.keys
        val p = partition[partitionid]
        if (p == null) {
            if (res.size == 1) {
                // println("PartitionHelper $partitionid 0 -> ${res[0]} $isSender $partition")
  if(SanityCheck.enabled)                    {
                        checkDebugMarker(uuid, partitionid, r.debugMarker, 0, isSender)
                    }
                
                return res[0]
            } else {
                TODO("error here")
            }
        } else {
            // println("PartitionHelper $partitionid $p -> ${res[p]} $isSender $partition")
  if(SanityCheck.enabled)                {
                    checkDebugMarker(uuid, partitionid, r.debugMarker, p, isSender)
                }
            
            return res[p]
        }
    }
}

internal class PartitionHelper3(internal val uuid: Long, internal var partition: Map<Int, Int>, internal val keys: IntArray) {
    internal val debugMarker = IntArray(keys.size)
}
