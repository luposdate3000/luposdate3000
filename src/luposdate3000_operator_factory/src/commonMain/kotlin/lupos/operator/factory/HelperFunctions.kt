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

import lupos.shared.EOperatorIDExt
import lupos.shared.dynamicArray.ByteArrayWrapper
import lupos.shared.inline.dynamicArray.ByteArrayWrapperExt
import lupos.triple_store_manager.POPTripleStoreIterator

private typealias BinaryToHelperMap = (data: ByteArrayWrapper, offset: Int) -> Unit

public class HelperMetadata(internal val data: ByteArrayWrapper, internal val queryID: Int) {
    public val id2off: MutableMap<Int, Int> = mutableMapOf<Int, Int>()
    public val id2host: MutableMap<Int, MutableSet<String>> = mutableMapOf<Int, MutableSet<String>>()
    public val key_send2id: MutableMap<Int, Int> = mutableMapOf<Int, Int>()
    public val key_rec2id: MutableMap<Int, Int> = mutableMapOf<Int, Int>()
    public val key_send2off: MutableMap<Int, Int> = mutableMapOf<Int, Int>()
    public val key_rec2off: MutableMap<Int, Int> = mutableMapOf<Int, Int>()
    public val id2parent: MutableMap<Int, MutableSet<Int>> = mutableMapOf<Int, MutableSet<Int>>()
    private var operatorMap: Array<BinaryToHelperMap?> = Array(0) { null }

    public fun addChildToBinary(newOff: Int, newID: Int) {
        var off = ByteArrayWrapperExt.readInt4(data, 0, { "OPBase.handler" })
        val len = ByteArrayWrapperExt.readInt4(data, off, { "OPBase.offsetMap.size" })
        var o = off + 4
        val childs = mutableMapOf<Int, Int>(newID to newOff)
        for (i in 0 until len) {
            val id = ByteArrayWrapperExt.readInt4(data, o, { "OPBase.offsetMap[$i].id" })
            val offset = ByteArrayWrapperExt.readInt4(data, o + 4, { "OPBase.offsetMap[$i].offset" })
            childs[id] = offset
            o += 8
        }
        val offOut = ByteArrayWrapperExt.getSize(data)
        ByteArrayWrapperExt.writeInt4(data, 0, offOut, { "OPBase.handler" })
        ByteArrayWrapperExt.setSize(data, offOut + 4 + 8 * childs.size, true)
        ByteArrayWrapperExt.writeInt4(data, offOut, childs.size, { "OPBase.offsetMap.size" })
        var oOut = offOut + 4
        var i = 0
        for ((k, v) in childs) {
            ByteArrayWrapperExt.writeInt4(data, oOut, k, { "OPBase.offsetMap[$i].id" })
            ByteArrayWrapperExt.writeInt4(data, oOut + 4, v, { "OPBase.offsetMap[$i].offset" })
            oOut += 8
            i++
        }
    }

    public fun getNextChildID(): Int {
        for (i in 0 until id2off.size + 1) {
            if (!id2off.contains(i+1000)) {
                return i + 1000
            }
        }
        TODO()
    }
    public fun getNextKey(): Int {
        val keys = (key_send2id.keys + key_rec2id.keys).toSet()
        for (i in 0 until keys.size + 1) {
            if (!keys.contains(i+1000)) {
                return i + 1000
            }
        }
        TODO()
    }

    public fun getDependenciesForID(id: Int): Map<Int, Int> {
        val keys = mutableSetOf<Int>()
        val res = mutableMapOf<Int, Int>()
        for ((key, i) in key_rec2id) {
            if (i == id) {
                keys.add(key)
            }
        }
        for ((key, i) in key_send2id) {
            if (keys.contains(key)) {
                res[i] = key
            }
        }
        return res
    }

    public fun getParentsForID(id: Int): Set<Int> {
        return id2parent.getOrPut(id, { mutableSetOf() })
    }

    private fun assignOperatorPhysicalDecode(operatorIDs: IntArray, operator: BinaryToHelperMap) {
        for (operatorID in operatorIDs) {
            assignOperatorPhysicalDecode(operatorID, operator)
        }
    }

    private fun assignOperatorPhysicalDecode(operatorID: Int, operator: BinaryToHelperMap) {
        if (operatorMap.size <= operatorID) {
            var s = operatorMap.size
            if (s < 16) {
                s = 16
            }
            while (s <= operatorID) {
                s = s * 2
            }
            val tmp = Array<BinaryToHelperMap?>(s) { null }
            operatorMap.copyInto(tmp)
            operatorMap = tmp
        }
        operatorMap[operatorID] = operator
    }

    private fun decodeHelper(data: ByteArrayWrapper, off: Int) {
        val type = ByteArrayWrapperExt.readInt4(data, off, { "operatorID" })
        if (type >= operatorMap.size) {
            TODO("decodeHelper $type -> ${EOperatorIDExt.names[type]}")
        }
        val decoder = operatorMap[type]
        if (decoder == null) {
            TODO("decodeHelper $type -> ${EOperatorIDExt.names[type]}")
        }
        decoder(data, off)
    }

    init {
        var currentID = -1
        var parentOff = -1
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPDistributedSendSingleID,
            { data, off ->
                val key = ByteArrayWrapperExt.readInt4(data, off + 4, { "POPDistributedSendSingle.key" })
                parentOff = off + 8
                val child = decodeHelper(data, ByteArrayWrapperExt.readInt4(data, off + 8, { "POPDistributedSendSingle.child" }))
                key_send2id[key] = currentID
                key_send2off[key] = off
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPDistributedSendMultiID,
            { data, off ->
                parentOff = off + 4
                val child = decodeHelper(data, ByteArrayWrapperExt.readInt4(data, off + 4, { "POPDistributedSendMulti.child" }))
                val count = ByteArrayWrapperExt.readInt4(data, off + 8, { "POPDistributedSendMulti.count" })
                val keys = IntArray(count) { ByteArrayWrapperExt.readInt4(data, off + 16 + 4 * it, { "POPDistributedSendMulti.key[$it]" }) }
                for (key in keys) {
                    key_send2id[key] = currentID
                    key_send2off[key] = off
                }
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPDistributedSendSingleCountID,
            { data, off ->
                val key = ByteArrayWrapperExt.readInt4(data, off + 4, { "POPDistributedSendSingleCount.key" })
                parentOff = off + 8
                val child = decodeHelper(data, ByteArrayWrapperExt.readInt4(data, off + 8, { "POPDistributedSendSingleCount.child" }))
                key_send2id[key] = currentID
                key_send2off[key] = off
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPDistributedReceiveMultiID,
            { data, off ->
                var keys = mutableListOf<Int>()
                val len = ByteArrayWrapperExt.readInt4(data, off + 4, { "POPDistributedReceiveMulti.size" })
                for (i in 0 until len) {
                    keys.add(ByteArrayWrapperExt.readInt4(data, off + 8 + 4 * i, { "POPDistributedReceiveMulti.key[$i]" }))
                }
                for (key in keys) {
                    key_rec2id[key] = currentID
                    key_rec2off[key] = parentOff
                }
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPDistributedReceiveSingleID,
            { data, off ->
                val key = ByteArrayWrapperExt.readInt4(data, off + 4, { "POPDistributedReceiveSingle.key" })
                key_rec2id[key] = currentID
                key_rec2off[key] = parentOff
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPDistributedReceiveSingleCountID,
            { data, off ->
                val key = ByteArrayWrapperExt.readInt4(data, off + 4, { "POPDistributedReceiveSingleCount.key" })
                key_rec2id[key] = currentID
                key_rec2off[key] = parentOff
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPDistributedReceiveMultiCountID,
            { data, off ->
                var keys = mutableListOf<Int>()
                val len = ByteArrayWrapperExt.readInt4(data, off + 4, { "POPDistributedReceiveMultiCount.size" })
                for (i in 0 until len) {
                    keys.add(ByteArrayWrapperExt.readInt4(data, off + 8 + 4 * i, { "POPDistributedReceiveMultiCount.key[$i]" }))
                }
                for (key in keys) {
                    key_rec2id[key] = currentID
                    key_rec2off[key] = parentOff
                }
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPDistributedReceiveMultiOrderedID,
            { data, off ->
                var keys = mutableListOf<Int>()
                val keysLen = ByteArrayWrapperExt.readInt4(data, off + 4, { "POPDistributedReceiveMultiOrdered.keys.size" })
                var o = off + 16
                for (i in 0 until keysLen) {
                    keys.add(ByteArrayWrapperExt.readInt4(data, o, { "POPDistributedReceiveMultiOrdered.keys[$i]" }))
                    o += 4
                }
                for (key in keys) {
                    key_rec2id[key] = currentID
                    key_rec2off[key] = parentOff
                }
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPGraphOperationID,
            { data, off ->
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPModifyDataID,
            { data, off ->
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPNothingID,
            { data, off ->
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPValuesCountID,
            { data, off ->
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPValuesID,
            { data, off ->
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPEmptyRowID,
            { data, off ->
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPUnionID,
            { data, off ->
                parentOff = off + 4
                val child0 = decodeHelper(data, ByteArrayWrapperExt.readInt4(data, off + 4, { "POPUnion.child0" }))
                parentOff = off + 8
                val child1 = decodeHelper(data, ByteArrayWrapperExt.readInt4(data, off + 8, { "POPUnion.child1" }))
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPMinusID,
            { data, off ->
                parentOff = off + 4
                val child0 = decodeHelper(data, ByteArrayWrapperExt.readInt4(data, off + 4, { "POPMinus.child0" }))
                parentOff = off + 8
                val child1 = decodeHelper(data, ByteArrayWrapperExt.readInt4(data, off + 8, { "POPMinus.child1" }))
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPJoinMergeOptionalID,
            { data, off ->
                parentOff = off + 4
                val child0 = decodeHelper(data, ByteArrayWrapperExt.readInt4(data, off + 4, { "POPJoinMergeOptional.child0" }))
                parentOff = off + 8
                val child1 = decodeHelper(data, ByteArrayWrapperExt.readInt4(data, off + 8, { "POPJoinMergeOptional.child1" }))
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPJoinMergeID,
            { data, off ->
                parentOff = off + 4
                val child0 = decodeHelper(data, ByteArrayWrapperExt.readInt4(data, off + 4, { "POPJoinMerge.child0" }))
                parentOff = off + 8
                val child1 = decodeHelper(data, ByteArrayWrapperExt.readInt4(data, off + 8, { "POPJoinMerge.child1" }))
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPJoinMergeSingleColumnID,
            { data, off ->
                parentOff = off + 4
                val child0 = decodeHelper(data, ByteArrayWrapperExt.readInt4(data, off + 4, { "POPJoinMergeSingleColumn.child0" }))
                parentOff = off + 8
                val child1 = decodeHelper(data, ByteArrayWrapperExt.readInt4(data, off + 8, { "POPJoinMergeSingleColumn.child1" }))
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPJoinHashMapID,
            { data, off ->
                parentOff = off + 4
                val child0 = decodeHelper(data, ByteArrayWrapperExt.readInt4(data, off + 4, { "POPJoinHashMap.child0" }))
                parentOff = off + 8
                val child1 = decodeHelper(data, ByteArrayWrapperExt.readInt4(data, off + 8, { "POPJoinHashMap.child1" }))
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPJoinCartesianProductID,
            { data, off ->
                parentOff = off + 4
                val child0 = decodeHelper(data, ByteArrayWrapperExt.readInt4(data, off + 4, { "POPJoinCartesianProduct.child0" }))
                parentOff = off + 8
                val child1 = decodeHelper(data, ByteArrayWrapperExt.readInt4(data, off + 8, { "POPJoinCartesianProduct.child1" }))
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPLimitID,
            { data, off ->
                parentOff = off - 4
                val child = decodeHelper(data, ByteArrayWrapperExt.readInt4(data, off + 4, { "POPLimit.child" }))
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPSortID,
            { data, off ->
                parentOff = off + 4
                val child = decodeHelper(data, ByteArrayWrapperExt.readInt4(data, off + 4, { "POPSort.child" }))
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPOffsetID,
            { data, off ->
                parentOff = off + 4
                val child = decodeHelper(data, ByteArrayWrapperExt.readInt4(data, off + 4, { "POPOffset.child" }))
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPMakeBooleanResultID,
            { data, off ->
                parentOff = off + 4
                val child = decodeHelper(data, ByteArrayWrapperExt.readInt4(data, off + 4, { "POPMakeBooleanResult.child" }))
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPReducedID,
            { data, off ->
                parentOff = off + 4
                val child = decodeHelper(data, ByteArrayWrapperExt.readInt4(data, off + 4, { "POPReduced.child" }))
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPTripleStoreIterator,
            { data, off ->
                val buf1 = ConverterString.decodeString(data, ByteArrayWrapperExt.readInt4(data, off + 4, { "POPTripleStoreIterator.target.first" }))
                id2host.getOrPut(currentID, { mutableSetOf() }).add(buf1)
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPBindID,
            { data, off ->
                parentOff = off + 4
                val child = decodeHelper(data, ByteArrayWrapperExt.readInt4(data, off + 4, { "POPBind.child" }))
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPFilterID,
            { data, off ->
                parentOff = off + 4
                val child = decodeHelper(data, ByteArrayWrapperExt.readInt4(data, off + 4, { "POPFilter.child" }))
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPGroupCount0ID,
            { data, off ->
                parentOff = off + 4
                decodeHelper(data, ByteArrayWrapperExt.readInt4(data, off + 4, { "POPGroupCount0.child" }))
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPGroupCount1ID,
            { data, off ->
                parentOff = off + 4
                decodeHelper(data, ByteArrayWrapperExt.readInt4(data, off + 4, { "POPGroupCount1.child" }))
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPGroupSortedID,
            { data, off ->
                parentOff = off + 4
                decodeHelper(data, ByteArrayWrapperExt.readInt4(data, off + 4, { "POPGroupSorted.child" }))
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPGroupID,
            { data, off ->
                parentOff = off + 4
                val child = decodeHelper(data, ByteArrayWrapperExt.readInt4(data, off + 4, { "POPGroup.child" }))
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPGroupWithoutKeyColumnID,
            { data, off ->
                parentOff = off + 4
                val child = decodeHelper(data, ByteArrayWrapperExt.readInt4(data, off + 4, { "POPGroupWithoutKeyColumn.child" }))
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPModifyID,
            { data, off ->
                parentOff = off + 4
                val child = decodeHelper(data, ByteArrayWrapperExt.readInt4(data, off + 4, { "POPModify.child" }))
            },
        )

        when (ByteArrayWrapperExt.readInt1(data, 4, { "Root.isOPBaseCompound" })) {
            0x1 -> {
                val childCount = ByteArrayWrapperExt.readInt4(data, 5, { "OPBaseCompound.children.size" })
                var o = 9
                val res = mutableListOf<Pair<List<String>, String>>()
                for (i in 0 until childCount) {
                    currentID = -1 - i
                    parentOff = o
                    val offset = ByteArrayWrapperExt.readInt4(data, o, { "OPBaseCompound.children[$i]" })
                    val child = decodeHelper(data, offset)
                    id2off[-1 - i] = offset
                    o += 4
                    val size = ByteArrayWrapperExt.readInt4(data, o, { "OPBaseCompound.columnProjectionOrder[$i].size" })
                    o += 4 + size * 4
                }
            }
            0x2 -> {
                /*there is no query root here*/
            }
            else -> {
                val off = ByteArrayWrapperExt.readInt4(data, 5, { "OPBase.children[0]" })
                id2off[-1] = off
                parentOff = 5
                currentID = -1
                val tmp = decodeHelper(data, off)
            }
        }
        var off = ByteArrayWrapperExt.readInt4(data, 0, { "OPBase.handler" })
        val len = ByteArrayWrapperExt.readInt4(data, off, { "OPBase.offsetMap.size" })
        var o = off + 4
        for (i in 0 until len) {
            val id = ByteArrayWrapperExt.readInt4(data, o, { "OPBase.offsetMap[$i].id" })
            val offset = ByteArrayWrapperExt.readInt4(data, o + 4, { "OPBase.offsetMap[$i].offset" })
            val firstType = ByteArrayWrapperExt.readInt4(data, offset, { "" })
            if (firstType >= 0) {
                id2off[id] = offset
                parentOff = o + 4
                currentID = id
                decodeHelper(data, offset)
            }
            o += 8
        }
        for (id in id2off.keys) {
            val keys = mutableSetOf<Int>()
            val res = mutableSetOf<Int>()
            for ((key, i) in key_send2id) {
                if (i == id) {
                    keys.add(key)
                }
            }
            for ((key, i) in key_rec2id) {
                if (keys.contains(key)) {
                    res.add(i)
                }
            }
            id2parent[id] = res
        }
        var queue0 = id2host.keys.toSet()
        var queue1 = mutableSetOf<Int>()
        while (queue0.size > 0) {
            for (child in queue0) {
                for (parent in id2parent.getOrPut(child, { mutableSetOf() })) {
                    id2host.getOrPut(parent, { mutableSetOf() }).addAll(id2host[child]!!)
                    queue1.add(parent)
                }
            }
            queue0 = queue1
            queue1 = mutableSetOf<Int>()
        }
        for (id in id2off.keys) {
            id2host.getOrPut(id, { mutableSetOf() })
        }
//        for ((k, i) in key_send2id) {
//            println("key $k query $queryID : $i -> ${key_rec2id[k]} ... key : send -> rec")
//        }
    }
}
