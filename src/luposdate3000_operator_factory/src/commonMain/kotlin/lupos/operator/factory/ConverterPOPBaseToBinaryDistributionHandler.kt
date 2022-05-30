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
package lupos.operator.factory

public class ConverterPOPBaseToBinaryDistributionHandler {
    // entry points into binary
    internal val idToOffset = mutableMapOf<Int/*ID*/, Int/*the offset of the operator*/>()

    // for writing it down
    internal var currentID = -1 //
    internal val partitionVariables = mutableMapOf<Int, String>()/*partitionID->Variable*/
    internal val partitionCount = mutableMapOf<Int, Int>()/*partitionID->partitionCount*/
    internal val partitionToChildID = mutableListOf<Pair<Pair<MutableMap<Int, Int>, Long>, Int>>()/*(thePartition,operatorID)->childID*/
    internal var currentPartition = mutableMapOf<Int, Int>()/*partitionID->partitionIndex*/
    internal val partitionToKey = mutableMapOf<Pair<Map<Int, Int>, Int>, MutableMap<Long, IntArray>>()/*(currentPartition,ID)->(operatorID->keys)*/

    internal companion object {
        internal var global_keys = 0
    }

    internal fun getNextKey(): Int = global_keys++

    // for optimization
    internal val idToHost = mutableMapOf<Int, MutableSet<String>>()/*ID->(hostname)*/
    internal val dependenciesForID = mutableMapOf<Int, MutableMap<Int, Int>>()/*parentID->childID->key*/
    internal val keyLocationSrc = mutableMapOf<Int, Int>()/*key->off*/
    internal val keyLocationDest = mutableMapOf<Int, Int>()/*key->offset, where the offset points to the operator, to enable efficient replacement*/

    internal fun keyLocationSend(id: Int, off: Int) {
        if (keyLocationSrc[id] != null) {
            TODO("")
        }
        keyLocationSrc[id] = off
    }

    internal fun keyLocationReceive(id: Int, off: Int) {
        if (keyLocationDest[id] != null) {
            TODO("")
        }
        keyLocationDest[id] = off
    }

    internal fun getNextChildID(): Int {
        for (i in 0 until idToOffset.size + 1) {
            if (!idToOffset.contains(i)) {
                return i
            }
        }
        return -1 // unreachable
    }

    internal fun getParentsForID(childID: Int): Set<Int> {
        var res = mutableSetOf<Int>()
        if (childID != -1) {
            loop@ for ((parentID, vv) in dependenciesForID) {
                for (v in vv.keys) {
                    if (v == childID) {
                        res.add(parentID)
                    }
                }
            }
        }
        return res
    }
}
