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

import lupos.operator.arithmetik.AOPBase
import lupos.operator.arithmetik.noinput.AOPConstant
import lupos.operator.arithmetik.noinput.AOPVariable
import lupos.operator.base.OPBase
import lupos.operator.base.OPBaseCompound
import lupos.operator.physical.multiinput.POPJoinCartesianProduct
import lupos.operator.physical.multiinput.POPJoinHashMap
import lupos.operator.physical.multiinput.POPJoinMerge
import lupos.operator.physical.multiinput.POPJoinMergeOptional
import lupos.operator.physical.multiinput.POPJoinMergeSingleColumn
import lupos.operator.physical.multiinput.POPMinus
import lupos.operator.physical.multiinput.POPUnion
import lupos.operator.physical.noinput.POPGraphOperation
import lupos.operator.physical.noinput.POPModifyData
import lupos.operator.physical.noinput.POPNothing
import lupos.operator.physical.noinput.POPValues
import lupos.operator.physical.partition.POPMergePartition
import lupos.operator.physical.partition.POPMergePartitionCount
import lupos.operator.physical.partition.POPMergePartitionOrderedByIntId
import lupos.operator.physical.partition.POPSplitPartition
import lupos.operator.physical.singleinput.POPBind
import lupos.operator.physical.singleinput.POPFilter
import lupos.operator.physical.singleinput.POPGroup
import lupos.operator.physical.singleinput.POPMakeBooleanResult
import lupos.operator.physical.singleinput.POPModify
import lupos.operator.physical.singleinput.POPSort
import lupos.operator.physical.singleinput.modifiers.POPLimit
import lupos.operator.physical.singleinput.modifiers.POPOffset
import lupos.operator.physical.singleinput.modifiers.POPReduced
import lupos.shared.DictionaryValueHelper
import lupos.shared.EOperatorIDExt
import lupos.shared.Partition
import lupos.shared.SanityCheck
import lupos.shared.dynamicArray.ByteArrayWrapper
import lupos.shared.inline.dynamicArray.ByteArrayWrapperExt
import lupos.shared.operator.IOPBase
import lupos.shared.operator.noinput.IAOPConstant
import lupos.shared.operator.noinput.IAOPVariable
import lupos.triple_store_manager.POPTripleStoreIterator

public class ConverterPOPBaseToBinaryDistributionHandler {
    internal var currentID = -1
    internal val idToOffset = mutableMapOf<Int/*ID*/, Int/*the offset of the operator*/>()
    internal val dependenciesForID = mutableMapOf<Int/*ID*/, MutableSet<Int/*ID*/>>()
    internal val partitionVariables = mutableMapOf<Int, String>()/*partitionID->Variable*/
    internal val partitionCount = mutableMapOf<Int, Int>()/*partitionID->partitionCount*/
    internal val partitionToChildID = mutableListOf<Pair<Pair<MutableMap<Int, Int>, Long>, Int>>()/*(thePartition,operatorID)->childID*/
    internal var currentPartition = mutableMapOf<Int, Int>()/*partitionID->partitionIndex*/
    internal val partitionToKey = mutableMapOf<Int, MutableMap<Long, IntArray>>()/*ID->(operatorID->keys)*/
    internal fun getNextChildID(): Int {
        for (i in 0 until idToOffset.size + 1) {
            if (!idToOffset.contains(i)) {
                return i
            }
        }
        return -1 // unreachable
    }
    internal var keys = 0
}
public typealias OPBaseToBinaryMap = (op: IOPBase, data: ByteArrayWrapper, mapping: MutableMap<String, Int>, distributed: Boolean, handler: ConverterPOPBaseToBinaryDistributionHandler) -> Int/*offset*/

public object ConverterPOPBaseToBinary {
    public var operatorMap: Array<OPBaseToBinaryMap?> = Array(0) { null }

    public fun assignOperatorPhysicalEncode(operatorIDs: IntArray, operator: OPBaseToBinaryMap) {
        for (operatorID in operatorIDs) {
            assignOperatorPhysicalEncode(operatorID, operator)
        }
    }

    public fun assignOperatorPhysicalEncode(operatorID: Int, operator: OPBaseToBinaryMap) {
        if (operatorMap.size <= operatorID) {
            var s = operatorMap.size
            if (s < 16) {
                s = 16
            }
            while (s <= operatorID) {
                s = s * 2
            }
            val tmp = Array<OPBaseToBinaryMap?>(s) { null }
            operatorMap.copyInto(tmp)
            operatorMap = tmp
        }
        operatorMap[operatorID] = operator
    }

    public fun encode(op: IOPBase, distributed: Boolean): ByteArrayWrapper {
        val handler = ConverterPOPBaseToBinaryDistributionHandler()
        println("encode ... start")
        val mapping = mutableMapOf<String, Int>()
        val data = ByteArrayWrapper()
        if (op is OPBaseCompound) {
            ByteArrayWrapperExt.setSize(data, 9 + 8 * op.children.size + op.columnProjectionOrder.map { it.size }.sum() * 4, false)
            ByteArrayWrapperExt.writeInt1(data, 4, 0x1, { "Root.isOPBaseCompound" })
            ByteArrayWrapperExt.writeInt4(data, 5, op.children.size, { "OPBaseCompound.children.size" })
            var o = 9
            for (i in 0 until op.children.size) {
                val k = if (op.columnProjectionOrder.size > i) {
                    op.columnProjectionOrder[i]
                } else {
                    listOf()
                }
                val off = convertToByteArrayHelper(op.children[i], data, mapping, distributed, handler)
                ByteArrayWrapperExt.writeInt4(data, o, off, { "OPBaseCompound.children[$i]" })
                o += 4
                ByteArrayWrapperExt.writeInt4(data, o, k.size, { "OPBaseCompound.columnProjectionOrder[$i].size" })
                o += 4
                for (j in 0 until k.size) {
                    ByteArrayWrapperExt.writeInt4(data, o, ConverterString.encodeString(k[j], data, mapping), { "OPBaseCompound.columnProjectionOrder[$i][$j]" })
                    o += 4
                }
            }
        } else {
            ByteArrayWrapperExt.setSize(data, 9, false)
            val off = convertToByteArrayHelper(op, data, mapping, distributed, handler)
            ByteArrayWrapperExt.writeInt1(data, 4, 0x0, { "OPBase.isOPBaseCompound" })
            ByteArrayWrapperExt.writeInt4(data, 5, off, { "OPBase.children[0]" })
        }
        val off = ByteArrayWrapperExt.getSize(data)
        ByteArrayWrapperExt.writeInt4(data, 0, off, { "OPBase.handler" })
        ByteArrayWrapperExt.setSize(data, off + 4 + 8 * handler.idToOffset.size, true)
        ByteArrayWrapperExt.writeInt4(data, off, handler.idToOffset.size, { "OPBase.offsetMap.size" })
        var o = off + 4
        var i = 0
        for ((k, v) in handler.idToOffset) {
            ByteArrayWrapperExt.writeInt4(data, o, k, { "OPBase.offsetMap[$i].id" })
            ByteArrayWrapperExt.writeInt4(data, o + 4, v, { "OPBase.offsetMap[$i].offset" })
            o += 8
            i++
        }
        println("encode ... finish")
        return data
    }

    private fun convertToByteArrayHelper(op: IOPBase, data: ByteArrayWrapper, mapping: MutableMap<String, Int>, distributed: Boolean, handler: ConverterPOPBaseToBinaryDistributionHandler): Int {
        if ((op as OPBase).operatorID >= operatorMap.size) {
            TODO("convertToByteArrayHelper ${(op as OPBase).operatorID} -> ${EOperatorIDExt.names[(op as OPBase).operatorID]}")
        }
        val encoder = operatorMap[(op as OPBase).operatorID]
        if (encoder == null) {
            TODO("convertToByteArrayHelper ${(op as OPBase).operatorID} -> ${EOperatorIDExt.names[(op as OPBase).operatorID]}")
        }
        return encoder(op, data, mapping, distributed, handler)
    }

    private fun mergePartitionEncodeHelperSplit(partitionCount: Int, handler: ConverterPOPBaseToBinaryDistributionHandler, currentID: Int, data: ByteArrayWrapper, partitionVariable: String?, mapping: MutableMap<String, Int>, distributed: Boolean, child: OPBase, labelAppendixReceive: String, labelAppendixSend: String, sendID: Int, receiveID: Int, partitionID: Int, operatorID: Long): Int {
        val currentPartitionCopy = mutableMapOf<Int, Int>()
        for ((k, v) in handler.currentPartition) {
            currentPartitionCopy[k] = v
        }
        val partition = handler.currentPartition[partitionID]!!
        handler.partitionCount[partitionID] = partitionCount
        handler.currentPartition.remove(partitionID)
        if (partitionVariable != null) {
            handler.partitionVariables[partitionID] = partitionVariable
        }
        var childID = -1
        loop@for (kk in handler.partitionToChildID) {
            val thePartition = kk.first.first
            val theOperatorID = kk.first.second
            val theChildID = kk.second
            if (theOperatorID != operatorID) {
                continue@loop
            }
            for ((k, v) in handler.currentPartition) {
                if (thePartition[k] != v) {
                    continue@loop
                }
            }
            for ((k, v) in thePartition) {
                if (handler.currentPartition[k] != v) {
                    continue@loop
                }
            }
            childID = theChildID
            break@loop
        }
        val off: Int
        var keys0 = handler.partitionToKey[childID]
        if (keys0 == null) {
            keys0 = mutableMapOf<Long, IntArray>()
            handler.partitionToKey[childID] = keys0
        }
        var keys = keys0[operatorID]
        if (keys == null) {
            val kk = handler.keys
            handler.keys += partitionCount
            keys = IntArray(partitionCount) { kk + it }
            keys0[operatorID] = keys
        }
        println("mergePartitionEncodeHelperSplit $currentID $childID ... ${keys[partition]}")
        if (childID == -1) {
            childID = handler.getNextChildID()
            handler.idToOffset[childID] = -1
            handler.currentID = childID
            var deps = handler.dependenciesForID[currentID]
            if (deps == null) {
                handler.dependenciesForID[currentID] = mutableSetOf(childID)
            } else {
                deps.add(childID)
            }
            val child = convertToByteArrayHelper(child, data, mapping, distributed, handler)
            off = ByteArrayWrapperExt.getSize(data)
            handler.idToOffset[childID] = off + 8
            ByteArrayWrapperExt.setSize(data, off + 20 + 4 * partitionCount, true)
            ByteArrayWrapperExt.writeInt4(data, off + 8, sendID, { "operatorID" })
            ByteArrayWrapperExt.writeInt4(data, off + 12, child, { "POPDistributedSendMulti$labelAppendixSend.child" })
            ByteArrayWrapperExt.writeInt4(data, off + 16, partitionCount, { "POPDistributedSendMulti$labelAppendixSend.count" })
            ByteArrayWrapperExt.writeInt4(data, off + 20 + 4 * partition, keys[partition], { "POPDistributedSendMulti$labelAppendixSend.key[$partition]" })
            handler.currentID = currentID
        } else {
            off = ByteArrayWrapperExt.getSize(data)
            ByteArrayWrapperExt.setSize(data, off + 8, true)
            var deps = handler.dependenciesForID[currentID]!!
            deps.add(childID)
            val childOff = handler.idToOffset[childID]!!
            ByteArrayWrapperExt.writeInt4(data, childOff + 12 + 4 * partition, keys[partition], { "POPDistributedSendMulti$labelAppendixSend.key[$partition]" })
        }
        ByteArrayWrapperExt.writeInt4(data, off + 0, receiveID, { "operatorID" })
        ByteArrayWrapperExt.writeInt4(data, off + 4, keys[partition], { "POPDistributedReceiveSingle$labelAppendixReceive.key" })
        handler.currentPartition = currentPartitionCopy
        return off
    }

    private fun mergePartitionEncodeHelperMerge(partitionCount: Int, handler: ConverterPOPBaseToBinaryDistributionHandler, currentID: Int, data: ByteArrayWrapper, partitionVariable: String?, mapping: MutableMap<String, Int>, distributed: Boolean, child: OPBase, labelAppendixReceive: String, labelAppendixSend: String, sendID: Int, receiveID: Int, partitionID: Int, operatorID: Long): Int {
        val currentPartitionCopy = mutableMapOf<Int, Int>()
        for ((k, v) in handler.currentPartition) {
            currentPartitionCopy[k] = v
        }
        val childsOff = mutableListOf<Int>()
        val childIDs = mutableListOf<Int>()
        if (partitionVariable != null) {
            handler.partitionVariables[partitionID] = partitionVariable
        }
        handler.partitionCount[partitionID] = partitionCount
        var keys0 = handler.partitionToKey[currentID]
        if (keys0 == null) {
            keys0 = mutableMapOf<Long, IntArray>()
            handler.partitionToKey[currentID] = keys0
        }
        var keys = keys0[operatorID]
        if (keys == null) {
            keys = IntArray(partitionCount) { handler.keys + it }
            handler.keys += partitionCount
            keys0[operatorID] = keys
        }
        for (partition in 0 until partitionCount) {
            handler.currentPartition[partitionID] = partition
            var childID = handler.getNextChildID()
            handler.idToOffset[childID] = -1
            handler.currentID = childID
            var deps = handler.dependenciesForID[currentID]
            if (deps == null) {
                handler.dependenciesForID[currentID] = mutableSetOf(childID)
            } else {
                deps.add(childID)
            }
            val child = convertToByteArrayHelper(child, data, mapping, distributed, handler)
            childsOff.add(child)
            childIDs.add(childID)
        }
        val off = ByteArrayWrapperExt.getSize(data)
        ByteArrayWrapperExt.setSize(data, off + 8 + 16 * partitionCount, true)
        ByteArrayWrapperExt.writeInt4(data, off + 0, receiveID, { "operatorID" })
        ByteArrayWrapperExt.writeInt4(data, off + 4, partitionCount, { "POPDistributedReceiveMulti$labelAppendixReceive.size" })
        var o = off + 8
        for (i in 0 until partitionCount) {
            println("mergePartitionEncodeHelperMerge $currentID ${childIDs[i]} ... ${keys[i]}")
            ByteArrayWrapperExt.writeInt4(data, o, keys[i], { "POPDistributedReceiveMulti$labelAppendixReceive.key[$i]" })
            o += 4
        }
        for (i in 0 until partitionCount) {
            handler.idToOffset[childIDs[i]] = o
            ByteArrayWrapperExt.writeInt4(data, o + 0, sendID, { "operatorID" })
            ByteArrayWrapperExt.writeInt4(data, o + 4, keys[i], { "POPDistributedSendSingle$labelAppendixSend.key" })
            ByteArrayWrapperExt.writeInt4(data, o + 8, childsOff[i], { "POPDistributedSendSingle$labelAppendixSend.child" })
            o += 12
        }
        handler.currentID = currentID
        handler.currentPartition = currentPartitionCopy
        return off
    }

    private fun mergePartitionEncodeHelper1x1(handler: ConverterPOPBaseToBinaryDistributionHandler, currentID: Int, data: ByteArrayWrapper, mapping: MutableMap<String, Int>, distributed: Boolean, child: OPBase, labelAppendix: String, sendID: Int, receiveID: Int): Int {
        var childID = handler.getNextChildID()
        handler.idToOffset[childID] = -1
        handler.currentID = childID
        var deps = handler.dependenciesForID[currentID]
        if (deps == null) {
            handler.dependenciesForID[currentID] = mutableSetOf(childID)
        } else {
            deps.add(childID)
        }
        val child = convertToByteArrayHelper(child, data, mapping, distributed, handler)
        val off = ByteArrayWrapperExt.getSize(data)
        val key = handler.keys++
        println("mergePartitionEncodeHelper1x1 $currentID $childID ... $key")
        ByteArrayWrapperExt.setSize(data, off + 20, true)
        ByteArrayWrapperExt.writeInt4(data, off + 0, receiveID, { "operatorID" })
        ByteArrayWrapperExt.writeInt4(data, off + 4, key, { "POPDistributedReceiveSingle$labelAppendix.key" })
        ByteArrayWrapperExt.writeInt4(data, off + 8, sendID, { "operatorID" })
        ByteArrayWrapperExt.writeInt4(data, off + 12, key, { "POPDistributedSendSingle$labelAppendix.key" })
        ByteArrayWrapperExt.writeInt4(data, off + 16, child, { "POPDistributedSendSingle$labelAppendix.child" })
        handler.idToOffset[childID] = off + 8
        handler.currentID = currentID
        return off
    }

    public fun initEncode() {
        assignOperatorPhysicalEncode(
            EOperatorIDExt.POPMergePartitionCountID,
            { op, data, mapping, distributed, handler ->
                op as POPMergePartitionCount
                val currentID = handler.currentID
                if (distributed) {
                    if (op.partitionCount > 1) {
                        mergePartitionEncodeHelperMerge(op.partitionCount, handler, currentID, data, op.partitionVariable, mapping, distributed, op.children[0] as OPBase, "Count", "Count", EOperatorIDExt.POPDistributedSendSingleCountID, EOperatorIDExt.POPDistributedReceiveMultiCountID, op.partitionID, op.uuid)
                    } else {
                        mergePartitionEncodeHelper1x1(handler, currentID, data, mapping, distributed, op.children[0] as OPBase, "Count", EOperatorIDExt.POPDistributedSendSingleCountID, EOperatorIDExt.POPDistributedReceiveSingleCountID)
                    }
                } else {
                    TODO("ConverterPOPBaseToBinary.POPMergePartitionCount")
                }
            },
        )
        assignOperatorPhysicalEncode(
            EOperatorIDExt.POPMergePartitionOrderedByIntIdID,
            { op, data, mapping, distributed, handler ->
                op as POPMergePartitionOrderedByIntId
                val currentID = handler.currentID
                if (distributed) {
                    if (op.partitionCount2 > 1) {
                        mergePartitionEncodeHelperMerge(op.partitionCount2, handler, currentID, data, op.partitionVariable, mapping, distributed, op.children[0] as OPBase, "Ordered", "", EOperatorIDExt.POPDistributedSendSingleID, EOperatorIDExt.POPDistributedReceiveMultiOrderedID, op.partitionID, op.uuid)
                    } else {
                        mergePartitionEncodeHelper1x1(handler, currentID, data, mapping, distributed, op.children[0] as OPBase, "", EOperatorIDExt.POPDistributedSendSingleID, EOperatorIDExt.POPDistributedReceiveSingleID,)
                    }
                } else {
                    TODO("ConverterPOPBaseToBinary.POPMergePartitionOrderedByIntId")
                }
            },
        )
        assignOperatorPhysicalEncode(
            EOperatorIDExt.POPMergePartitionID,
            { op, data, mapping, distributed, handler ->
                op as POPMergePartition
                val currentID = handler.currentID
                if (distributed) {
                    if (op.partitionCount > 1) {
                        mergePartitionEncodeHelperMerge(op.partitionCount, handler, currentID, data, op.partitionVariable, mapping, distributed, op.children[0] as OPBase, "", "", EOperatorIDExt.POPDistributedSendSingleID, EOperatorIDExt.POPDistributedReceiveMultiID, op.partitionID, op.uuid)
                    } else {
                        mergePartitionEncodeHelper1x1(handler, currentID, data, mapping, distributed, op.children[0] as OPBase, "", EOperatorIDExt.POPDistributedSendSingleID, EOperatorIDExt.POPDistributedReceiveSingleID,)
                    }
                } else {
                    TODO("ConverterPOPBaseToBinary.POPMergePartition")
                }
            },
        )
        assignOperatorPhysicalEncode(
            EOperatorIDExt.POPSplitPartitionID,
            { op, data, mapping, distributed, handler ->
                op as POPSplitPartition
                val currentID = handler.currentID
                if (distributed) {
                    if (op.partitionCount > 1) {
                        mergePartitionEncodeHelperSplit(op.partitionCount, handler, currentID, data, op.partitionVariable, mapping, distributed, op.children[0] as OPBase, "", "", EOperatorIDExt.POPDistributedSendMultiID, EOperatorIDExt.POPDistributedReceiveSingleID, op.partitionID, op.uuid)
                    } else {
                        mergePartitionEncodeHelper1x1(handler, currentID, data, mapping, distributed, op.children[0] as OPBase, "", EOperatorIDExt.POPDistributedSendSingleID, EOperatorIDExt.POPDistributedReceiveSingleID,)
                    }
                } else {
                    TODO("ConverterPOPBaseToBinary.POPSplitPartition")
                }
            },
        )
        assignOperatorPhysicalEncode(
            EOperatorIDExt.POPGraphOperationID,
            { op, data, mapping, distributed, handler ->
                op as POPGraphOperation
                val off = ByteArrayWrapperExt.getSize(data)
                ByteArrayWrapperExt.setSize(data, off + 25, true)
                ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.POPGraphOperationID, { "operatorID" })
                ByteArrayWrapperExt.writeInt4(data, off + 4, op.graph1type, { "POPGraphOperation.graph1type" })
                ByteArrayWrapperExt.writeInt4(data, off + 8, op.graph2type, { "POPGraphOperation.graph2type" })
                ByteArrayWrapperExt.writeInt4(data, off + 12, op.action, { "POPGraphOperation.action" })
                ByteArrayWrapperExt.writeInt4(data, off + 16, ConverterString.encodeString(op.graph1iri, data, mapping), { "POPGraphOperation.graph1iri" })
                ByteArrayWrapperExt.writeInt4(data, off + 20, ConverterString.encodeString(op.graph2iri, data, mapping), { "POPGraphOperation.graph2iri" })
                ByteArrayWrapperExt.writeInt1(data, off + 24, if (op.silent) 1 else 0, { "POPGraphOperation.silent" })
                off
            },
        )
        assignOperatorPhysicalEncode(
            EOperatorIDExt.POPProjectionID,
            { op, data, mapping, distributed, handler ->
                op as OPBase
                convertToByteArrayHelper(op.children[0], data, mapping, distributed, handler)
            },
        )
        assignOperatorPhysicalEncode(
            EOperatorIDExt.POPDebugID,
            { op, data, mapping, distributed, handler ->
                op as OPBase
                convertToByteArrayHelper(op.children[0], data, mapping, distributed, handler)
            },
        )
        assignOperatorPhysicalEncode(
            EOperatorIDExt.POPSplitPartitionFromStoreCountID,
            { op, data, mapping, distributed, handler ->
                op as OPBase
                convertToByteArrayHelper(op.children[0], data, mapping, distributed, handler)
            },
        )
        assignOperatorPhysicalEncode(
            EOperatorIDExt.POPSplitPartitionFromStoreID,
            { op, data, mapping, distributed, handler ->
                op as OPBase
                convertToByteArrayHelper(op.children[0], data, mapping, distributed, handler)
            },
        )
        assignOperatorPhysicalEncode(
            EOperatorIDExt.POPSplitMergePartitionFromStoreID,
            { op, data, mapping, distributed, handler ->
                op as OPBase
                convertToByteArrayHelper(op.children[0], data, mapping, distributed, handler)
            },
        )
        assignOperatorPhysicalEncode(
            EOperatorIDExt.POPModifyDataID,
            { op, data, mapping, distributed, handler ->
                op as POPModifyData
                val off = ByteArrayWrapperExt.getSize(data)
                ByteArrayWrapperExt.setSize(data, off + 8 + op.data.size * (4 + 3 * DictionaryValueHelper.getSize()), true)
                ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.POPModifyDataID, { "operatorID" })
                ByteArrayWrapperExt.writeInt4(data, off + 4, op.data.size, { "POPModifyData.data.size" })
                var o = off + 8
                var i = 0
                for (t in op.data) {
                    ByteArrayWrapperExt.writeInt4(data, o, ConverterString.encodeString(t.graph, data, mapping), { "POPModifyData.data[$i].graph" })
                    o += 4
                    for (j in 0 until 3) {
                        DictionaryValueHelper.toByteArray(data, o, (t.children[j] as AOPConstant).value, { "POPModifyData.data[$i][$j]" })
                        o += DictionaryValueHelper.getSize()
                    }
                    i++
                }
                off
            },
        )
        assignOperatorPhysicalEncode(
            EOperatorIDExt.POPNothingID,
            { op, data, mapping, distributed, handler ->
                op as POPNothing
                val n = op.getProvidedVariableNames()
                val off = ByteArrayWrapperExt.getSize(data)
                ByteArrayWrapperExt.setSize(data, off + 8 + 4 * n.size, true)
                ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.POPNothingID, { "operatorID" })
                ByteArrayWrapperExt.writeInt4(data, off + 4, n.size, { "POPNothing.size" })
                var i = 0
                for (s in n) {
                    ByteArrayWrapperExt.writeInt4(data, off + 8 + i * 4, ConverterString.encodeString(s, data, mapping), { "POPNothing.data[$i]" })
                    i++
                }
                off
            },
        )
        assignOperatorPhysicalEncode(
            intArrayOf(
                EOperatorIDExt.POPValuesID,
                EOperatorIDExt.POPValuesCountID,
            ),
            { op, data, mapping, distributed, handler ->
                op as POPValues
                val off = ByteArrayWrapperExt.getSize(data)
                if (op.rows == -1) {
                    val size = op.data[op.variables.first()]!!.size
                    ByteArrayWrapperExt.setSize(data, off + 12 + op.data.size * (4 + size * DictionaryValueHelper.getSize()), true)
                    ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.POPValuesID, { "operatorID" })
                    ByteArrayWrapperExt.writeInt4(data, off + 4, op.data.size, { "POPValues.columns.size" })
                    ByteArrayWrapperExt.writeInt4(data, off + 8, size, { "POPValues.rows.size" })
                    var o = off + 12
                    var column = 0
                    for ((col, rows) in op.data) {
                        ByteArrayWrapperExt.writeInt4(data, o, ConverterString.encodeString(col, data, mapping), { "POPValues.column[$column]" })
                        o += 4
                        var i = 0
                        for (row in rows) {
                            i++
                            DictionaryValueHelper.toByteArray(data, o, row, { "POPValues.data[$column][$i]" })
                            o += DictionaryValueHelper.getSize()
                        }
                        SanityCheck.check(
                            { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_factory/src/commonMain/kotlin/lupos/operator/factory/ConverterPOPBaseToBinary.kt:507"/*SOURCE_FILE_END*/ },
                            { i == size }
                        )
                        column++
                    }
                } else {
                    ByteArrayWrapperExt.setSize(data, off + 8, true)
                    ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.POPValuesCountID, { "operatorID" })
                    ByteArrayWrapperExt.writeInt4(data, off + 4, op.rows, { "POPValueCount.rows.size" })
                }
                off
            },
        )
        assignOperatorPhysicalEncode(
            EOperatorIDExt.POPEmptyRowID,
            { op, data, mapping, distributed, handler ->
                val off = ByteArrayWrapperExt.getSize(data)
                ByteArrayWrapperExt.setSize(data, off + 4, true)
                ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.POPEmptyRowID, { "operatorID" })
                off
            },
        )
        assignOperatorPhysicalEncode(
            EOperatorIDExt.POPUnionID,
            { op, data, mapping, distributed, handler ->
                op as POPUnion
                val child0 = convertToByteArrayHelper(op.children[0], data, mapping, distributed, handler)
                val child1 = convertToByteArrayHelper(op.children[1], data, mapping, distributed, handler)
                val off = ByteArrayWrapperExt.getSize(data)
                ByteArrayWrapperExt.setSize(data, off + 16 + 4 * op.projectedVariables.size, true)
                ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.POPUnionID, { "operatorID" })
                ByteArrayWrapperExt.writeInt4(data, off + 4, child0, { "POPUnion.child0" })
                ByteArrayWrapperExt.writeInt4(data, off + 8, child1, { "POPUnion.child1" })
                ByteArrayWrapperExt.writeInt4(data, off + 12, op.projectedVariables.size, { "POPUnion.variables.size" })
                var o = off + 16
                var i = 0
                for (s in op.projectedVariables) {
                    ByteArrayWrapperExt.writeInt4(data, o, ConverterString.encodeString(s, data, mapping), { "POPUnion.variables[$i]" })
                    o += 4
                    i++
                }
                off
            },
        )
        assignOperatorPhysicalEncode(
            EOperatorIDExt.POPMinusID,
            { op, data, mapping, distributed, handler ->
                op as POPMinus
                val child0 = convertToByteArrayHelper(op.children[0], data, mapping, distributed, handler)
                val child1 = convertToByteArrayHelper(op.children[1], data, mapping, distributed, handler)
                val off = ByteArrayWrapperExt.getSize(data)
                ByteArrayWrapperExt.setSize(data, off + 16 + 4 * op.projectedVariables.size, true)
                ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.POPMinusID, { "operatorID" })
                ByteArrayWrapperExt.writeInt4(data, off + 4, child0, { "POPMinus.child0" })
                ByteArrayWrapperExt.writeInt4(data, off + 8, child1, { "POPMinus.child1" })
                ByteArrayWrapperExt.writeInt4(data, off + 12, op.projectedVariables.size, { "POPMinus.variables.size" })
                var o = off + 16
                var i = 0
                for (s in op.projectedVariables) {
                    ByteArrayWrapperExt.writeInt4(data, o, ConverterString.encodeString(s, data, mapping), { "POPMinus.variables[$i]" })
                    o += 4
                    i++
                }
                off
            },
        )
        assignOperatorPhysicalEncode(
            EOperatorIDExt.POPJoinMergeSingleColumnID,
            { op, data, mapping, distributed, handler ->
                op as POPJoinMergeSingleColumn
                val child0 = convertToByteArrayHelper(op.children[0], data, mapping, distributed, handler)
                val child1 = convertToByteArrayHelper(op.children[1], data, mapping, distributed, handler)
                val off = ByteArrayWrapperExt.getSize(data)
                ByteArrayWrapperExt.setSize(data, off + 12, true)
                ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.POPJoinMergeSingleColumnID, { "operatorID" })
                ByteArrayWrapperExt.writeInt4(data, off + 4, child0, { "POPJoinMergeSingleColumn.child0" })
                ByteArrayWrapperExt.writeInt4(data, off + 8, child1, { "POPJoinMergeSingleColumn.child1" })
                off
            },
        )
        assignOperatorPhysicalEncode(
            EOperatorIDExt.POPJoinMergeOptionalID,
            { op, data, mapping, distributed, handler ->
                op as POPJoinMergeOptional
                val child0 = convertToByteArrayHelper(op.children[0], data, mapping, distributed, handler)
                val child1 = convertToByteArrayHelper(op.children[1], data, mapping, distributed, handler)
                val off = ByteArrayWrapperExt.getSize(data)
                ByteArrayWrapperExt.setSize(data, off + 16 + 4 * op.projectedVariables.size, true)
                ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.POPJoinMergeOptionalID, { "operatorID" })
                ByteArrayWrapperExt.writeInt4(data, off + 4, child0, { "POPJoinMergeOptional.child0" })
                ByteArrayWrapperExt.writeInt4(data, off + 8, child1, { "POPJoinMergeOptional.child1" })
                ByteArrayWrapperExt.writeInt4(data, off + 12, op.projectedVariables.size, { "POPJoinMergeOptional.variables.size" })
                var o = off + 16
                var i = 0
                for (s in op.projectedVariables) {
                    ByteArrayWrapperExt.writeInt4(data, o, ConverterString.encodeString(s, data, mapping), { "POPJoinMergeOptional.variables[$i]" })
                    o += 4
                    i++
                }
                off
            },
        )
        assignOperatorPhysicalEncode(
            EOperatorIDExt.POPJoinMergeID,
            { op, data, mapping, distributed, handler ->
                op as POPJoinMerge
                val child0 = convertToByteArrayHelper(op.children[0], data, mapping, distributed, handler)
                val child1 = convertToByteArrayHelper(op.children[1], data, mapping, distributed, handler)
                val off = ByteArrayWrapperExt.getSize(data)
                ByteArrayWrapperExt.setSize(data, off + 16 + 4 * op.projectedVariables.size, true)
                ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.POPJoinMergeID, { "operatorID" })
                ByteArrayWrapperExt.writeInt4(data, off + 4, child0, { "POPJoinMerge.child0" })
                ByteArrayWrapperExt.writeInt4(data, off + 8, child1, { "POPJoinMerge.child1" })
                ByteArrayWrapperExt.writeInt4(data, off + 12, op.projectedVariables.size, { "POPJoinMerge.variables.size" })
                var o = off + 16
                var i = 0
                for (s in op.projectedVariables) {
                    ByteArrayWrapperExt.writeInt4(data, o, ConverterString.encodeString(s, data, mapping), { "POPJoinMerge.variables[$i]" })
                    o += 4
                    i++
                }
                off
            },
        )
        assignOperatorPhysicalEncode(
            EOperatorIDExt.POPJoinHashMapID,
            { op, data, mapping, distributed, handler ->
                op as POPJoinHashMap
                val child0 = convertToByteArrayHelper(op.children[0], data, mapping, distributed, handler)
                val child1 = convertToByteArrayHelper(op.children[1], data, mapping, distributed, handler)
                val off = ByteArrayWrapperExt.getSize(data)
                ByteArrayWrapperExt.setSize(data, off + 17 + 4 * op.projectedVariables.size, true)
                ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.POPJoinHashMapID, { "operatorID" })
                ByteArrayWrapperExt.writeInt4(data, off + 4, child0, { "POPJoinHashMap.child0" })
                ByteArrayWrapperExt.writeInt4(data, off + 8, child1, { "POPJoinHashMap.child1" })
                ByteArrayWrapperExt.writeInt1(data, off + 12, if (op.optional) 1 else 0, { "POPJoinHashMap.optional" })
                ByteArrayWrapperExt.writeInt4(data, off + 13, op.projectedVariables.size, { "POPJoinHashMap.variables.size" })
                var o = off + 17
                var i = 0
                for (s in op.projectedVariables) {
                    ByteArrayWrapperExt.writeInt4(data, o, ConverterString.encodeString(s, data, mapping), { "POPJoinHashMap.variables[$i]" })
                    o += 4
                    i++
                }
                off
            },
        )
        assignOperatorPhysicalEncode(
            EOperatorIDExt.POPJoinCartesianProductID,
            { op, data, mapping, distributed, handler ->
                op as POPJoinCartesianProduct
                val child0 = convertToByteArrayHelper(op.children[0], data, mapping, distributed, handler)
                val child1 = convertToByteArrayHelper(op.children[1], data, mapping, distributed, handler)
                val off = ByteArrayWrapperExt.getSize(data)
                ByteArrayWrapperExt.setSize(data, off + 13, true)
                ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.POPJoinCartesianProductID, { "operatorID" })
                ByteArrayWrapperExt.writeInt4(data, off + 4, child0, { "POPJoinCartesianProduct.child0" })
                ByteArrayWrapperExt.writeInt4(data, off + 8, child1, { "POPJoinCartesianProduct.child1" })
                ByteArrayWrapperExt.writeInt1(data, off + 12, if (op.optional) 1 else 0, { "POPJoinCartesianProduct.optional" })
                off
            },
        )
        assignOperatorPhysicalEncode(
            EOperatorIDExt.POPLimitID,
            { op, data, mapping, distributed, handler ->
                op as POPLimit
                val child = convertToByteArrayHelper(op.children[0], data, mapping, distributed, handler)
                val off = ByteArrayWrapperExt.getSize(data)
                ByteArrayWrapperExt.setSize(data, off + 12, true)
                ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.POPLimitID, { "operatorID" })
                ByteArrayWrapperExt.writeInt4(data, off + 4, child, { "POPLimit.child" })
                ByteArrayWrapperExt.writeInt4(data, off + 8, op.limit, { "POPLimit.limit" })
                off
            },
        )
        assignOperatorPhysicalEncode(
            EOperatorIDExt.POPSortID,
            { op, data, mapping, distributed, handler ->
                op as POPSort
                if (op.getProvidedVariableNames().size == 0) {
                    convertToByteArrayHelper(op.children[0], data, mapping, distributed, handler)
                } else {
                    val child = convertToByteArrayHelper(op.children[0], data, mapping, distributed, handler)
                    val off = ByteArrayWrapperExt.getSize(data)
                    ByteArrayWrapperExt.setSize(data, off + 21 + 4 * (op.mySortPriority.size + op.getProvidedVariableNames().size + op.sortBy.size), true)
                    ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.POPSortID, { "operatorID" })
                    ByteArrayWrapperExt.writeInt4(data, off + 4, child, { "POPSort.child" })
                    ByteArrayWrapperExt.writeInt4(data, off + 8, op.mySortPriority.size, { "POPSort.sp.size" })
                    ByteArrayWrapperExt.writeInt4(data, off + 12, op.getProvidedVariableNames().size, { "POPSort.p.size" })
                    ByteArrayWrapperExt.writeInt4(data, off + 16, op.sortBy.size, { "POPSort.sb.size" })
                    ByteArrayWrapperExt.writeInt1(data, off + 20, if (op.sortOrder) 1 else 0, { "POPSort.sortOrder" })
                    var o = off + 21
                    var i = 0
                    for (s in op.mySortPriority.map { it.variableName }) {
                        ByteArrayWrapperExt.writeInt4(data, o, ConverterString.encodeString(s, data, mapping), { "POPSort.sp[$i]" })
                        o += 4
                        i++
                    }
                    i = 0
                    for (s in op.sortBy.map { it.name }) {
                        ByteArrayWrapperExt.writeInt4(data, o, ConverterString.encodeString(s, data, mapping), { "POPSort.sb[$i]" })
                        o += 4
                        i++
                    }
                    i = 0
                    for (s in op.getProvidedVariableNames()) {
                        ByteArrayWrapperExt.writeInt4(data, o, ConverterString.encodeString(s, data, mapping), { "POPSort.p[$i]" })
                        o += 4
                        i++
                    }
                    off
                }
            },
        )
        assignOperatorPhysicalEncode(
            EOperatorIDExt.POPOffsetID,
            { op, data, mapping, distributed, handler ->
                op as POPOffset
                val child = convertToByteArrayHelper(op.children[0], data, mapping, distributed, handler)
                val off = ByteArrayWrapperExt.getSize(data)
                ByteArrayWrapperExt.setSize(data, off + 12, true)
                ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.POPOffsetID, { "operatorID" })
                ByteArrayWrapperExt.writeInt4(data, off + 4, child, { "POPOffset.child" })
                ByteArrayWrapperExt.writeInt4(data, off + 8, op.offset, { "POPOffset.offset" })
                off
            },
        )
        assignOperatorPhysicalEncode(
            EOperatorIDExt.POPMakeBooleanResultID,
            { op, data, mapping, distributed, handler ->
                op as POPMakeBooleanResult
                val child = convertToByteArrayHelper(op.children[0], data, mapping, distributed, handler)
                val off = ByteArrayWrapperExt.getSize(data)
                ByteArrayWrapperExt.setSize(data, off + 8, true)
                ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.POPMakeBooleanResultID, { "operatorID" })
                ByteArrayWrapperExt.writeInt4(data, off + 4, child, { "POPMakeBooleanResult.child" })
                off
            },
        )
        assignOperatorPhysicalEncode(
            EOperatorIDExt.POPReducedID,
            { op, data, mapping, distributed, handler ->
                op as POPReduced
                val child = convertToByteArrayHelper(op.children[0], data, mapping, distributed, handler)
                val off = ByteArrayWrapperExt.getSize(data)
                ByteArrayWrapperExt.setSize(data, off + 12, true)
                ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.POPReducedID, { "operatorID" })
                ByteArrayWrapperExt.writeInt4(data, off + 4, child, { "POPReduced.child" })
                ByteArrayWrapperExt.writeInt4(data, off + 8, op.projectedVariables.size, { "POPReduced.variables.size" })
                off
            },
        )
        assignOperatorPhysicalEncode(
            EOperatorIDExt.POPTripleStoreIterator,
            { op, data, mapping, distributed, handler ->
                op as POPTripleStoreIterator
                val off = ByteArrayWrapperExt.getSize(data)
                ByteArrayWrapperExt.setSize(data, off + 17 + 3 * if (DictionaryValueHelper.getSize() > 4) DictionaryValueHelper.getSize() else 4, true)
                val parent: Partition = if (handler.currentPartition.size == 0) {
                    Partition()
                } else if (handler.currentPartition.size == 1) {
                    var partitionID = 0
                    var partitionIndex = 0
                    for ((k, v) in handler.currentPartition) {
                        partitionID = k
                        partitionIndex = v
                    }
                    val partitionVariable = handler.partitionVariables[partitionID]!!
                    val partitionCount = handler.partitionCount[partitionID]!!
                    Partition(Partition(), partitionVariable, partitionIndex, partitionCount)
                } else {
                    TODO("??? ${handler.currentPartition}")
                }

                val target = op.getTarget(parent)
                ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.POPTripleStoreIterator, { "operatorID" })
                ByteArrayWrapperExt.writeInt4(data, off + 4, ConverterString.encodeString(target.first, data, mapping), { "POPTripleStoreIterator.target.first" })
                ByteArrayWrapperExt.writeInt4(data, off + 8, ConverterString.encodeString(target.second, data, mapping), { "POPTripleStoreIterator.target.second" })
                ByteArrayWrapperExt.writeInt4(data, off + 12, op.getIndexPattern(), { "POPTripleStoreIterator.IndexPattern" })
                var childFlag = 0
                var o = off + 17
                for (i in 0 until 3) {
                    val child = op.children[i]
                    when (child) {
                        is IAOPConstant -> {
                            childFlag = childFlag + (1 shl i)
                            DictionaryValueHelper.toByteArray(data, o, child.getValue(), { "POPTripleStoreIterator.constant[$i]" })
                            o += DictionaryValueHelper.getSize()
                        }
                        else -> {
                            child as IAOPVariable
                            ByteArrayWrapperExt.writeInt4(data, o, ConverterString.encodeString(child.getName(), data, mapping), { "POPTripleStoreIterator.variable[$i]" })
                            o += 4
                        }
                    }
                }
                ByteArrayWrapperExt.writeInt1(data, off + 16, childFlag, { "POPTripleStoreIterator.childFlag" })
                off
            },
        )
        assignOperatorPhysicalEncode(
            EOperatorIDExt.POPBindID,
            { op, data, mapping, distributed, handler ->
                op as POPBind
                val variablesOut = op.getProvidedVariableNames()
                val child = convertToByteArrayHelper(op.children[0], data, mapping, distributed, handler)
                val off = ByteArrayWrapperExt.getSize(data)
                ByteArrayWrapperExt.setSize(data, off + 20 + variablesOut.size * 4, true)
                ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.POPBindID, { "operatorID" })
                ByteArrayWrapperExt.writeInt4(data, off + 4, child, { "POPBind.child" })
                ByteArrayWrapperExt.writeInt4(data, off + 8, ConverterAOPBaseToBinary.encodeAOP(op.children[1] as AOPBase, data, mapping), { "POPBind.value" })
                ByteArrayWrapperExt.writeInt4(data, off + 12, ConverterString.encodeString(op.name.name, data, mapping), { "POPBind.column" })
                ByteArrayWrapperExt.writeInt4(data, off + 16, variablesOut.size, { "POPBind.variables.size" })
                for (i in 0 until variablesOut.size) {
                    ByteArrayWrapperExt.writeInt4(data, off + 20 + 4 * i, ConverterString.encodeString(variablesOut[i], data, mapping), { "POPBind.variables[$i]" })
                }
                off
            },
        )
        assignOperatorPhysicalEncode(
            EOperatorIDExt.POPFilterID,
            { op, data, mapping, distributed, handler ->
                op as POPFilter
                val variablesOut = op.getProvidedVariableNames()
                val child = convertToByteArrayHelper(op.children[0], data, mapping, distributed, handler)
                val off = ByteArrayWrapperExt.getSize(data)
                ByteArrayWrapperExt.setSize(data, off + 16 + 4 * variablesOut.size, true)
                ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.POPFilterID, { "operatorID" })
                ByteArrayWrapperExt.writeInt4(data, off + 4, child, { "POPFilter.child" })
                ByteArrayWrapperExt.writeInt4(data, off + 8, ConverterAOPBaseToBinary.encodeAOP(op.children[1] as AOPBase, data, mapping), { "POPFilter.filter" })
                ByteArrayWrapperExt.writeInt4(data, off + 12, variablesOut.size, { "POPFilter.variables.size" })
                for (i in 0 until variablesOut.size) {
                    ByteArrayWrapperExt.writeInt4(data, off + 16 + 4 * i, ConverterString.encodeString(variablesOut[i], data, mapping), { "POPFilter.variables[$i]" })
                }
                off
            },
        )
        assignOperatorPhysicalEncode(
            intArrayOf(
                EOperatorIDExt.POPGroupID,
                EOperatorIDExt.POPGroupCount0ID,
                EOperatorIDExt.POPGroupCount1ID,
                EOperatorIDExt.POPGroupSortedID,
                EOperatorIDExt.POPGroupWithoutKeyColumnID,
            ),
            { op, data, mapping, distributed, handler ->
                op as POPGroup
                val keyColumnNames = op.by.map { it.name }
                val child = convertToByteArrayHelper(op.children[0], data, mapping, distributed, handler)
                val off = ByteArrayWrapperExt.getSize(data)
                var done = false
                if (op.by.isEmpty()) {
                    ByteArrayWrapperExt.setSize(data, off + 20 + 4 * (keyColumnNames.size + op.projectedVariables.size + op.bindings.size * 2), true)
                    ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.POPGroupWithoutKeyColumnID, { "operatorID" })
                    ByteArrayWrapperExt.writeInt4(data, off + 4, child, { "POPGroupWithoutKeyColumn.child" })
                    ByteArrayWrapperExt.writeInt4(data, off + 8, op.projectedVariables.size, { "POPGroupWithoutKeyColumn.variables.size" })
                    ByteArrayWrapperExt.writeInt4(data, off + 12, op.bindings.size, { "POPGroupWithoutKeyColumn.bindings.size" })
                    var o = off + 16
                    var i = 0
                    for (s in op.projectedVariables) {
                        ByteArrayWrapperExt.writeInt4(data, o, ConverterString.encodeString(s, data, mapping), { "POPGroupWithoutKeyColumn.variables[$i]" })
                        o += 4
                        i++
                    }
                    i = 0
                    for ((k, v) in op.bindings) {
                        ByteArrayWrapperExt.writeInt4(data, o, ConverterString.encodeString(k, data, mapping), { "POPGroupWithoutKeyColumn.binding[$i].name" })
                        o += 4
                        ByteArrayWrapperExt.writeInt4(data, o, ConverterAOPBaseToBinary.encodeAOP(v, data, mapping), { "POPGroupWithoutKeyColumn.binding[$i].value" })
                        o += 4
                        i++
                    }
                    done = true
                } else if (op.canUseSortedInput()) {
                    ByteArrayWrapperExt.setSize(data, off + 20 + 4 * (keyColumnNames.size + op.projectedVariables.size + op.bindings.size * 2), true)
                    ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.POPGroupSortedID, { "operatorID" })
                    ByteArrayWrapperExt.writeInt4(data, off + 4, child, { "POPGroupSorted.child" })
                    ByteArrayWrapperExt.writeInt4(data, off + 8, op.projectedVariables.size, { "POPGroupSorted.variables.size" })
                    ByteArrayWrapperExt.writeInt4(data, off + 12, op.bindings.size, { "POPGroupSorted.bindings.size" })
                    ByteArrayWrapperExt.writeInt4(data, off + 16, keyColumnNames.size, { "POPGroupSorted.keys.size" })
                    var o = off + 20
                    var i = 0
                    for (s in op.projectedVariables) {
                        ByteArrayWrapperExt.writeInt4(data, o, ConverterString.encodeString(s, data, mapping), { "POPGroupSorted.variables[$i]" })
                        o += 4
                        i++
                    }
                    i = 0
                    for (s in keyColumnNames) {
                        ByteArrayWrapperExt.writeInt4(data, o, ConverterString.encodeString(s, data, mapping), { "POPGroupSorted.keys[$i]" })
                        o += 4
                        i++
                    }
                    i = 0
                    for ((k, v) in op.bindings) {
                        ByteArrayWrapperExt.writeInt4(data, o, ConverterString.encodeString(k, data, mapping), { "POPGroupSorted.bindings[$i].name" })
                        o += 4
                        ByteArrayWrapperExt.writeInt4(data, o, ConverterAOPBaseToBinary.encodeAOP(v, data, mapping), { "POPGroupSorted.bindings[$i].value" })
                        o += 4
                        i++
                    }
                    done = true
                } else if (op.isCountOnly()) {
                    if (op.by.size == 0) {
                        ByteArrayWrapperExt.setSize(data, off + 12, true)
                        ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.POPGroupCount0ID, { "operatorID" })
                        ByteArrayWrapperExt.writeInt4(data, off + 4, child, { "POPGroupCount0.child" })
                        ByteArrayWrapperExt.writeInt4(data, off + 8, ConverterString.encodeString(op.bindings.toList().first().first, data, mapping), { "POPGroupCount0.column" })
                        done = true
                    } else if (op.by.size == 1) {
                        ByteArrayWrapperExt.setSize(data, off + 16, true)
                        ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.POPGroupCount1ID, { "operatorID" })
                        ByteArrayWrapperExt.writeInt4(data, off + 4, child, { "POPGroupCount1.child" })
                        ByteArrayWrapperExt.writeInt4(data, off + 8, ConverterString.encodeString(op.bindings.toList().first().first, data, mapping), { "POPGroupCount1.column" })
                        ByteArrayWrapperExt.writeInt4(data, off + 12, ConverterString.encodeString(op.by[0].name, data, mapping), { "POPGroupCount1.by" })
                        done = true
                    }
                }
                if (!done) {
                    ByteArrayWrapperExt.setSize(data, off + 16 + 4 * (keyColumnNames.size + op.projectedVariables.size + op.bindings.size * 2), true)
                    ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.POPGroupID, { "operatorID" })
                    ByteArrayWrapperExt.writeInt4(data, off + 4, child, { "POPGroup.child" })
                    ByteArrayWrapperExt.writeInt4(data, off + 8, op.bindings.size, { "POPGroup.bindings.size" })
                    ByteArrayWrapperExt.writeInt4(data, off + 12, keyColumnNames.size, { "POPGroup.keys.size" })
                    var o = off + 16
                    var i = 0
                    for (s in keyColumnNames) {
                        ByteArrayWrapperExt.writeInt4(data, o, ConverterString.encodeString(s, data, mapping), { "POPGroup.keys[$i]" })
                        o += 4
                        i++
                    }
                    i = 0
                    for ((k, v) in op.bindings) {
                        ByteArrayWrapperExt.writeInt4(data, o, ConverterString.encodeString(k, data, mapping), { "POPGroup.bindings[$i].name" })
                        o += 4
                        ByteArrayWrapperExt.writeInt4(data, o, ConverterAOPBaseToBinary.encodeAOP(v, data, mapping), { "POPGroup.bindings[$i].value" })
                        o += 4
                        i++
                    }
                }
                off
            },
        )
        assignOperatorPhysicalEncode(
            EOperatorIDExt.POPModifyID,
            { op, data, mapping, distributed, handler ->
                op as POPModify
                val child = convertToByteArrayHelper(op.children[0], data, mapping, distributed, handler)
                val off = ByteArrayWrapperExt.getSize(data)
                ByteArrayWrapperExt.setSize(data, off + 12 + op.modify.size * (9 + 3 * if (DictionaryValueHelper.getSize() > 4) DictionaryValueHelper.getSize() else 4), true)
                ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.POPModifyID, { "operatorID" })
                ByteArrayWrapperExt.writeInt4(data, off + 4, child, { "POPModify.child" })
                ByteArrayWrapperExt.writeInt4(data, off + 8, op.modify.size, { "POPModify.modify.size" })
                val steph = if (DictionaryValueHelper.getSize() > 4) DictionaryValueHelper.getSize() else 4
                val step = 9 + 3 * steph
                var i = 0
                for ((k, v) in op.modify) {
                    var o = off + 12 + i * step
                    ByteArrayWrapperExt.writeInt4(data, o, v, { "POPModify.modify[$i].v" })
                    ByteArrayWrapperExt.writeInt4(data, o + 4, ConverterString.encodeString(k.graph, data, mapping), { "POPModify.modify[$i].graph" })
                    var flag = 0
                    if (k.graphVar) {
                        flag += 0x1
                    }
                    val s = k.children[0]
                    val p = k.children[1]
                    val oo = k.children[2]
                    println("flag $o a ... $flag")
                    if (s is AOPConstant) {
                        flag += 0x2
                        DictionaryValueHelper.toByteArray(data, o + 9, s.value, { "POPModify.modify[$i].child[0].c" })
                    } else {
                        s as AOPVariable
                        ByteArrayWrapperExt.writeInt4(data, o + 9, ConverterString.encodeString(s.name, data, mapping), { "POPModify.modify[$i].child[0].v" })
                    }
                    println("flag $o b ... $flag")
                    if (p is AOPConstant) {
                        flag += 0x4
                        DictionaryValueHelper.toByteArray(data, o + 9 + steph, p.value, { "POPModify.modify[$i].child[1].c" })
                    } else {
                        p as AOPVariable
                        ByteArrayWrapperExt.writeInt4(data, o + 9 + steph, ConverterString.encodeString(p.name, data, mapping), { "POPModify.modify[$i].child[1].v" })
                    }
                    println("flag $o c ... $flag")
                    if (oo is AOPConstant) {
                        flag += 0x8
                        DictionaryValueHelper.toByteArray(data, o + 9 + steph + steph, oo.value, { "POPModify.modify[$i].child[2].c" })
                    } else {
                        oo as AOPVariable
                        ByteArrayWrapperExt.writeInt4(data, o + 9 + steph + steph, ConverterString.encodeString(oo.name, data, mapping), { "POPModify.modify[$i].child[2].v" })
                    }
                    println("flag $o d ... $flag")
                    ByteArrayWrapperExt.writeInt1(data, o + 8, flag, { "POPModify.modify[$i].flag" })
                    i++
                }
                off
            },
        )
    }
}
