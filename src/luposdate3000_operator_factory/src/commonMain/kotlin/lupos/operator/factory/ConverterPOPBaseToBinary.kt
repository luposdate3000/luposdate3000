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
import lupos.operator.base.OPBase
import lupos.operator.base.OPBaseCompound
import lupos.operator.base.Query
import lupos.operator.logical.multiinput.LOPJoinTopology
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
import lupos.shared.DictionaryValueTypeArray
import lupos.shared.EOperatorIDExt
import lupos.shared.Partition
import lupos.shared.SanityCheck
import lupos.shared.dynamicArray.ByteArrayWrapper
import lupos.shared.inline.dynamicArray.ByteArrayWrapperExt
import lupos.shared.operator.IOPBase
import lupos.shared.operator.noinput.IAOPConstant
import lupos.shared.operator.noinput.IAOPVariable
import lupos.triple_store_manager.POPTripleStoreIterator

public typealias OPBaseToBinaryMap = (op: IOPBase, data: ByteArrayWrapper, mapping: MutableMap<String, Int>, distributed: Boolean, handler: ConverterPOPBaseToBinaryDistributionHandler, offPtr: Int) -> Int/*offset*/

public object ConverterPOPBaseToBinary {
    internal var enableOptimiationMergeIfSharedHost = false
    internal var enableOptimiationMergeConsecutiveSendSingleReceiveSingle = true
    internal var enableOptimiationMergeConsecutiveSendSingleReceiveMulti = true
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

    public fun optimize(data: ByteArrayWrapper, query: Query): ByteArrayWrapper {
        // println("src/luposdate3000_operator_factory/src/commonMain/kotlin/lupos/operator/factory/ConverterPOPBaseToBinary.kt optimize called ${query.transactionID.toInt()}")
        var changed = true
        loop@ while (changed) {
            val handler = HelperMetadata(data, query.transactionID.toInt())
            changed = false
            if (enableOptimiationMergeConsecutiveSendSingleReceiveSingle) {
                for ((id, off0) in handler.id2off) {
                    val type0 = ByteArrayWrapperExt.readInt4(data, off0, { "operatorID" })
                    when (type0) {
                        EOperatorIDExt.POPDistributedSendSingleID -> {
                            val key0 = ByteArrayWrapperExt.readInt4(data, off0 + 4, { "POPDistributedSendSingle.key" })
                            val off1 = ByteArrayWrapperExt.readInt4(data, off0 + 8, { "POPDistributedSendSingle.child" })
                            val type1 = ByteArrayWrapperExt.readInt4(data, off1, { "operatorID" })
                            when (type1) {
                                EOperatorIDExt.POPDistributedReceiveSingleID -> {
                                    val key1 = ByteArrayWrapperExt.readInt4(data, off1 + 4, { "POPDistributedReceiveSingle.key" })
// going to replace 'key1' with 'key0', and remove 'id'
                                    val off2 = handler.key_send2off[key1]!!
                                    val type2 = ByteArrayWrapperExt.readInt4(data, off2, { "operatorID" })
                                    when (type2) {
                                        EOperatorIDExt.POPDistributedSendSingleID -> {
                                            SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_factory/src/commonMain/kotlin/lupos/operator/factory/ConverterPOPBaseToBinary.kt:111"/*SOURCE_FILE_END*/ }, { ByteArrayWrapperExt.readInt4(data, off2 + 4, { "POPDistributedSendSingle.key" }) == key1 })
                                            ByteArrayWrapperExt.writeInt4(data, off2 + 4, key0, { "POPDistributedSendSingle.key" })
                                        }
                                        EOperatorIDExt.POPDistributedSendMultiID -> {
                                            val count = ByteArrayWrapperExt.readInt4(data, off2 + 8, { "POPDistributedSendMulti.count" })
                                            var flag = 0
                                            for (it in 0 until count) {
                                                val key = ByteArrayWrapperExt.readInt4(data, off2 + 16 + 4 * it, { "POPDistributedSendMulti.key[$it]" })
                                                if (key == key1) {
                                                    ByteArrayWrapperExt.writeInt4(data, off2 + 16 + 4 * it, key0, { "POPDistributedSendMulti.key[$it]" })
                                                    flag++
                                                }
                                            }
                                            SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_factory/src/commonMain/kotlin/lupos/operator/factory/ConverterPOPBaseToBinary.kt:124"/*SOURCE_FILE_END*/ }, { flag == 1 })
                                        }
                                        else -> {
// crash, because it is already decided, that this must be replaced
                                            TODO("unknown type $type2")
                                        }
                                    }
                                    ByteArrayWrapperExt.writeInt4(data, off0, -1, { "invalid opertor type" })
                                    changed = true
                                    continue@loop
                                }
                                else -> {
// ok, no optimization right now
                                }
                            }
                        }
                        else -> {
// ok, no optimization right now
                        }
                    }
                }
            }
            if (enableOptimiationMergeConsecutiveSendSingleReceiveMulti) {
                for ((id, off0) in handler.id2off) {
                    val type0 = ByteArrayWrapperExt.readInt4(data, off0, { "operatorID" })
                    when (type0) {
                        EOperatorIDExt.POPDistributedSendSingleID -> {
                            val key0 = ByteArrayWrapperExt.readInt4(data, off0 + 4, { "POPDistributedSendSingle.key" })
                            val off1 = ByteArrayWrapperExt.readInt4(data, off0 + 8, { "POPDistributedSendSingle.child" })
                            val type1 = ByteArrayWrapperExt.readInt4(data, off1, { "operatorID" })
                            when (type1) {
                                EOperatorIDExt.POPDistributedReceiveMultiID -> {
                                    var keys1 = mutableListOf<Int>()
                                    val len = ByteArrayWrapperExt.readInt4(data, off1 + 4, { "POPDistributedReceiveMulti.size" })
                                    for (i in 0 until len) {
                                        keys1.add(ByteArrayWrapperExt.readInt4(data, off1 + 8 + 4 * i, { "POPDistributedReceiveMulti.key[$i]" }))
                                    }
// going to insert 'keys1' into parent reveivemulti
                                    val off2 = ByteArrayWrapperExt.readInt4(data, handler.key_rec2off[key0]!!, { "" })
                                    val type2 = ByteArrayWrapperExt.readInt4(data, off2, { "operatorID" })
                                    when (type2) {
                                        EOperatorIDExt.POPDistributedReceiveMultiID -> {
                                            var keys2 = mutableListOf<Int>()
                                            val lenOut = ByteArrayWrapperExt.readInt4(data, off2 + 4, { "POPDistributedReceiveMulti.size" })
                                            for (i in 0 until lenOut) {
                                                keys2.add(ByteArrayWrapperExt.readInt4(data, off2 + 8 + 4 * i, { "POPDistributedReceiveMulti.key[$i]" }))
                                            }
                                            keys2.remove(key0)
                                            keys2.addAll(keys1)
                                            val newReveice = ConverterBinaryEncoder.encodePOPDistributedReceiveMulti(data, mutableMapOf(), keys2)
                                            ByteArrayWrapperExt.writeInt4(data, handler.key_rec2off[key0]!!, newReveice, { "operatorID" })
                                        }
                                        else -> TODO("unknown type $type2")
                                    }
                                    ByteArrayWrapperExt.writeInt4(data, off0, -1, { "invalid opertor type" })
                                    changed = true
                                    continue@loop
                                }
                                else -> {
// ok, no optimization right now
                                }
                            }
                        }
                        else -> {
// ok, no optimization right now
                        }
                    }
                }
            }
        }
        return data
    }

    public fun encode(op: IOPBase, distributed: Boolean): ByteArrayWrapper {
        val handler = ConverterPOPBaseToBinaryDistributionHandler()
        val mapping = mutableMapOf<String, Int>()
        var data = ByteArrayWrapper()
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
                val child = convertToByteArrayHelper(op.children[i], data, mapping, distributed, handler, o)
                ByteArrayWrapperExt.writeInt4(data, o, child, { "OPBaseCompound.children[$i]" })
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
            val off = convertToByteArrayHelper(op, data, mapping, distributed, handler, 5)
            ByteArrayWrapperExt.writeInt1(data, 4, 0x0, { "OPBase.isOPBaseCompound" })
            ByteArrayWrapperExt.writeInt4(data, 5, off, { "OPBase.children[0]" })
        }
// TODO("validate metadata here")
//        val handler2 = BinaryMetadataHandler(handler.idToOffset, handler.idToHost, handler.dependenciesForID, handler.keyLocationSrc, handler.keyLocationDest)
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
        data = optimize(data, op.getQuery() as Query)
        return data
    }

    private fun convertToByteArrayHelper(op: IOPBase, data: ByteArrayWrapper, mapping: MutableMap<String, Int>, distributed: Boolean, handler: ConverterPOPBaseToBinaryDistributionHandler, offPtr: Int): Int {
        if ((op as OPBase).operatorID >= operatorMap.size) {
            TODO("convertToByteArrayHelper ${op.operatorID} -> ${EOperatorIDExt.names[op.operatorID]}")
        }
        val encoder = operatorMap[op.operatorID]
        if (encoder == null) {
            TODO("convertToByteArrayHelper ${op.operatorID} -> ${EOperatorIDExt.names[op.operatorID]}")
        }
        return encoder(op, data, mapping, distributed, handler, offPtr)
    }

    private fun mergePartitionEncodeHelperSplit(partitionCount: Int, handler: ConverterPOPBaseToBinaryDistributionHandler, currentID: Int, data: ByteArrayWrapper, partitionVariable: String?, mapping: MutableMap<String, Int>, distributed: Boolean, child: OPBase, labelAppendixReceive: String, labelAppendixSend: String, sendID: Int, receiveID: Int, partitionID: Int, operatorID: Long, offPtr: Int, partitionColumn: String): Int {
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
        loop@ for (kk in handler.partitionToChildID) {
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
        var keys0 = handler.partitionToKey[handler.currentPartition to childID]
        if (keys0 == null) {
            keys0 = mutableMapOf<Long, IntArray>()
            handler.partitionToKey[handler.currentPartition to childID] = keys0
        }
        var keys = keys0[operatorID]
        if (keys == null) {
            keys = IntArray(partitionCount) { handler.getNextKey() }
            keys0[operatorID] = keys
        }
        if (childID == -1) {
            childID = handler.getNextChildID()
            handler.idToOffset[childID] = -1
            handler.currentID = childID
            var deps = handler.dependenciesForID[currentID]
            if (deps == null) {
                handler.dependenciesForID[currentID] = mutableMapOf(childID to keys[partition])
            } else {
                deps[childID] = keys[partition]
            }
            off = ByteArrayWrapperExt.getSize(data)
            handler.idToOffset[childID] = off + 8
            handler.keyLocationSend(keys[partition], off + 8)
            ByteArrayWrapperExt.setSize(data, off + 24 + 4 * partitionCount, true)
            val child2 = convertToByteArrayHelper(child, data, mapping, distributed, handler, off + 12)
            ByteArrayWrapperExt.writeInt4(data, off + 8, sendID, { "operatorID" })
            ByteArrayWrapperExt.writeInt4(data, off + 12, child2, { "POPDistributedSendMulti$labelAppendixSend.child" })
            ByteArrayWrapperExt.writeInt4(data, off + 16, partitionCount, { "POPDistributedSendMulti$labelAppendixSend.count" })
            ByteArrayWrapperExt.writeInt4(data, off + 20, ConverterString.encodeString(partitionColumn, data, mapping), { "POPDistributedSendMulti$labelAppendixSend.name" })
            ByteArrayWrapperExt.writeInt4(data, off + 24 + 4 * partition, keys[partition], { "POPDistributedSendMulti$labelAppendixSend.key[$partition]" })
            handler.currentID = currentID
            val cpy = mutableMapOf<Int, Int>()
            for ((k, v) in handler.currentPartition) {
                cpy[k] = v
            }
            handler.partitionToChildID.add((cpy to operatorID) to childID)
        } else {
            off = ByteArrayWrapperExt.getSize(data)
            ByteArrayWrapperExt.setSize(data, off + 8, true)
            var deps = handler.dependenciesForID[currentID]
            if (deps == null) {
                handler.dependenciesForID[currentID] = mutableMapOf(childID to keys[partition])
            } else {
                deps[childID] = keys[partition]
            }
            val childOff = handler.idToOffset[childID]!!
            handler.keyLocationSend(keys[partition], childOff)
            ByteArrayWrapperExt.writeInt4(data, childOff + 16 + 4 * partition, keys[partition], { "POPDistributedSendMulti$labelAppendixSend.key[$partition]" })
        }
        handler.keyLocationReceive(keys[partition], offPtr)
        ByteArrayWrapperExt.writeInt4(data, off + 0, receiveID, { "operatorID" })
        ByteArrayWrapperExt.writeInt4(data, off + 4, keys[partition], { "POPDistributedReceiveSingle$labelAppendixReceive.key" })

        handler.currentPartition = currentPartitionCopy
        return off
    }

    private fun mergePartitionEncodeHelperMerge(partitionCount: Int, handler: ConverterPOPBaseToBinaryDistributionHandler, currentID: Int, data: ByteArrayWrapper, partitionVariable: String?, mapping: MutableMap<String, Int>, distributed: Boolean, child: OPBase, labelAppendixReceive: String, labelAppendixSend: String, sendID: Int, receiveID: Int, partitionID: Int, operatorID: Long, offPtr: Int): Int {
        val currentPartitionCopy = mutableMapOf<Int, Int>()
        for ((k, v) in handler.currentPartition) {
            currentPartitionCopy[k] = v
        }
        if (partitionVariable != null) {
            handler.partitionVariables[partitionID] = partitionVariable
        }
        handler.partitionCount[partitionID] = partitionCount
        var keys0 = handler.partitionToKey[handler.currentPartition to currentID]
        if (keys0 == null) {
            keys0 = mutableMapOf<Long, IntArray>()
            handler.partitionToKey[handler.currentPartition to currentID] = keys0
        }
        var keys = keys0[operatorID]
        if (keys == null) {
            keys = IntArray(partitionCount) { handler.getNextKey() }
            keys0[operatorID] = keys
        }
        val off = ByteArrayWrapperExt.getSize(data)
        ByteArrayWrapperExt.setSize(data, off + 8 + 16 * partitionCount, true)
        ByteArrayWrapperExt.writeInt4(data, off + 0, receiveID, { "operatorID" })
        ByteArrayWrapperExt.writeInt4(data, off + 4, partitionCount, { "POPDistributedReceiveMulti$labelAppendixReceive.size" })
        for (partition in 0 until partitionCount) {
            handler.currentPartition[partitionID] = partition
            var childID = handler.getNextChildID()
            handler.idToOffset[childID] = -1
            handler.currentID = childID
            var deps = handler.dependenciesForID[currentID]
            if (deps == null) {
                handler.dependenciesForID[currentID] = mutableMapOf(childID to keys[partition])
            } else {
                deps[childID] = keys[partition]
            }
            var o = off + 8 + 4 * partitionCount + 12 * partition
            val child = convertToByteArrayHelper(child, data, mapping, distributed, handler, o + 8)
            handler.keyLocationReceive(keys[partition], offPtr)
            ByteArrayWrapperExt.writeInt4(data, off + 8 + 4 * partition, keys[partition], { "POPDistributedReceiveMulti$labelAppendixReceive.key[$partition]" })
            handler.keyLocationSend(keys[partition], o)
            handler.idToOffset[childID] = o
            ByteArrayWrapperExt.writeInt4(data, o + 0, sendID, { "operatorID" })
            ByteArrayWrapperExt.writeInt4(data, o + 4, keys[partition], { "POPDistributedSendSingle$labelAppendixSend.key" })
            ByteArrayWrapperExt.writeInt4(data, o + 8, child, { "POPDistributedSendSingle$labelAppendixSend.child" })
        }
        handler.currentID = currentID
        handler.currentPartition = currentPartitionCopy
        return off
    }

    private fun mergePartitionEncodeHelperMergeOrdered(
        partitionCount: Int,
        handler: ConverterPOPBaseToBinaryDistributionHandler,
        currentID: Int,
        data: ByteArrayWrapper,
        partitionVariable: String?,
        mapping: MutableMap<String, Int>,
        distributed: Boolean,
        child: OPBase,
        partitionID: Int,
        operatorID: Long,
        offPtr: Int,
        orderedBy: List<String>,
        variablesOut: List<String>,
    ): Int {
        val currentPartitionCopy = mutableMapOf<Int, Int>()
        for ((k, v) in handler.currentPartition) {
            currentPartitionCopy[k] = v
        }
        if (partitionVariable != null) {
            handler.partitionVariables[partitionID] = partitionVariable
        }
        handler.partitionCount[partitionID] = partitionCount
        var keys0 = handler.partitionToKey[handler.currentPartition to currentID]
        if (keys0 == null) {
            keys0 = mutableMapOf<Long, IntArray>()
            handler.partitionToKey[handler.currentPartition to currentID] = keys0
        }
        var keys = keys0[operatorID]
        if (keys == null) {
            keys = IntArray(partitionCount) { handler.getNextKey() }
            keys0[operatorID] = keys
        }
        val off = ByteArrayWrapperExt.getSize(data)
        ByteArrayWrapperExt.setSize(data, off + 16 + 16 * partitionCount + 4 * (orderedBy.size + variablesOut.size), true)
        ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.POPDistributedReceiveMultiOrderedID, { "operatorID" })
        ByteArrayWrapperExt.writeInt4(data, off + 4, partitionCount, { "POPDistributedReceiveMultiOrdered.size" })
        ByteArrayWrapperExt.writeInt4(data, off + 8, orderedBy.size, { "POPDistributedReceiveMultiOrdered.orderedBy.size" })
        ByteArrayWrapperExt.writeInt4(data, off + 12, variablesOut.size, { "POPDistributedReceiveMultiOrdered.variablesOut.size" })
        var o = off + 16 + 4 * partitionCount
        for (i in 0 until orderedBy.size) {
            ByteArrayWrapperExt.writeInt4(data, o, ConverterString.encodeString(orderedBy[i], data, mapping), { "POPDistributedReceiveMultiOrdered.orderedBy[$i]" })
            o += 4
        }
        for (i in 0 until variablesOut.size) {
            ByteArrayWrapperExt.writeInt4(data, o, ConverterString.encodeString(variablesOut[i], data, mapping), { "POPDistributedReceiveMultiOrdered.variablesOut[$i]" })
            o += 4
        }
        val baseOff = off + 16 + 4 * (orderedBy.size + variablesOut.size + partitionCount)
        for (partition in 0 until partitionCount) {
            handler.currentPartition[partitionID] = partition
            var childID = handler.getNextChildID()
            handler.idToOffset[childID] = -1
            handler.currentID = childID
            var deps = handler.dependenciesForID[currentID]
            if (deps == null) {
                handler.dependenciesForID[currentID] = mutableMapOf(childID to keys[partition])
            } else {
                deps[childID] = keys[partition]
            }
            var o = baseOff + 12 * partition
            val child = convertToByteArrayHelper(child, data, mapping, distributed, handler, o + 8)
            handler.keyLocationReceive(keys[partition], offPtr)
            ByteArrayWrapperExt.writeInt4(data, off + 16 + 4 * partition, keys[partition], { "POPDistributedReceiveMultiOrdered.key[$partition]" })
            handler.keyLocationSend(keys[partition], o)
            handler.idToOffset[childID] = o
            ByteArrayWrapperExt.writeInt4(data, o + 0, EOperatorIDExt.POPDistributedSendSingleID, { "operatorID" })
            ByteArrayWrapperExt.writeInt4(data, o + 4, keys[partition], { "POPDistributedSendSingle.key" })
            ByteArrayWrapperExt.writeInt4(data, o + 8, child, { "POPDistributedSendSingle.child" })
        }
        handler.currentID = currentID
        handler.currentPartition = currentPartitionCopy
        return off
    }

    private fun mergePartitionEncodeHelper1x1(handler: ConverterPOPBaseToBinaryDistributionHandler, currentID: Int, data: ByteArrayWrapper, mapping: MutableMap<String, Int>, distributed: Boolean, child: OPBase, labelAppendix: String, sendID: Int, receiveID: Int, offPtr: Int): Int {
        var childID = handler.getNextChildID()
        handler.idToOffset[childID] = -1
        handler.currentID = childID
        val key = handler.getNextKey()
        var deps = handler.dependenciesForID[currentID]
        if (deps == null) {
            handler.dependenciesForID[currentID] = mutableMapOf(childID to key)
        } else {
            deps[childID] = key
        }
        val off = ByteArrayWrapperExt.getSize(data)
        handler.keyLocationReceive(key, offPtr)
        handler.keyLocationSend(key, off + 8)
        ByteArrayWrapperExt.setSize(data, off + 20, true)
        val child = convertToByteArrayHelper(child, data, mapping, distributed, handler, off + 16)
        ByteArrayWrapperExt.writeInt4(data, off + 0, receiveID, { "operatorID" })
        ByteArrayWrapperExt.writeInt4(data, off + 4, key, { "POPDistributedReceiveSingle$labelAppendix.key" })
        ByteArrayWrapperExt.writeInt4(data, off + 8, sendID, { "operatorID" })
        ByteArrayWrapperExt.writeInt4(data, off + 12, key, { "POPDistributedSendSingle$labelAppendix.key" })
        ByteArrayWrapperExt.writeInt4(data, off + 16, child, { "POPDistributedSendSingle$labelAppendix.child" })
        handler.idToOffset[childID] = off + 8
        handler.currentID = currentID
        return off
    }

    init {
        assignOperatorPhysicalEncode(
            EOperatorIDExt.POPMergePartitionCountID,
            { op, data, mapping, distributed, handler, offPtr ->
                op as POPMergePartitionCount
                val currentID = handler.currentID
                if (op.partitionCount > 1) {
                    mergePartitionEncodeHelperMerge(
                        op.partitionCount,
                        handler,
                        currentID, data, op.partitionVariable,
                        mapping, distributed, op.children[0] as OPBase,
                        "Count", "Count", EOperatorIDExt.POPDistributedSendSingleCountID, EOperatorIDExt.POPDistributedReceiveMultiCountID, op.partitionID, op.uuid, offPtr
                    )
                } else {
                    mergePartitionEncodeHelper1x1(
                        handler,
                        currentID, data,
                        mapping, distributed, op.children[0] as OPBase,
                        "Count", EOperatorIDExt.POPDistributedSendSingleCountID, EOperatorIDExt.POPDistributedReceiveSingleCountID, offPtr
                    )
                }
            },
        )
        assignOperatorPhysicalEncode(
            EOperatorIDExt.POPMergePartitionOrderedByIntIdID,
            { op, data, mapping, distributed, handler, offPtr ->
                op as POPMergePartitionOrderedByIntId
                val currentID = handler.currentID
                if (op.partitionCount2 > 1) {
                    mergePartitionEncodeHelperMergeOrdered(
                        op.partitionCount2,
                        handler, currentID,
                        data, op.partitionVariable, mapping, distributed,
                        op.children[0] as OPBase, op.partitionID, op.uuid, offPtr, op.mySortPriority.map { it.variableName }, op.projectedVariables
                    )
                } else {
                    mergePartitionEncodeHelper1x1(
                        handler, currentID, data,
                        mapping, distributed,
                        op.children[0] as OPBase, "", EOperatorIDExt.POPDistributedSendSingleID, EOperatorIDExt.POPDistributedReceiveSingleID, offPtr
                    )
                }
            },
        )
        assignOperatorPhysicalEncode(
            EOperatorIDExt.POPMergePartitionID,
            { op, data, mapping, distributed, handler, offPtr ->
                op as POPMergePartition
                val currentID = handler.currentID
                if (op.partitionCount > 1) {
                    mergePartitionEncodeHelperMerge(
                        op.partitionCount,
                        handler, currentID,
                        data, op.partitionVariable, mapping, distributed,
                        op.children[0] as OPBase, "", "", EOperatorIDExt.POPDistributedSendSingleID, EOperatorIDExt.POPDistributedReceiveMultiID, op.partitionID, op.uuid, offPtr
                    )
                } else {
                    mergePartitionEncodeHelper1x1(
                        handler, currentID,
                        data, mapping, distributed,
                        op.children[0] as OPBase, "", EOperatorIDExt.POPDistributedSendSingleID, EOperatorIDExt.POPDistributedReceiveSingleID, offPtr
                    )
                }
            },
        )
        assignOperatorPhysicalEncode(
            EOperatorIDExt.POPSplitPartitionID,
            { op, data, mapping, distributed, handler, offPtr ->
                op as POPSplitPartition
                val currentID = handler.currentID
                if (op.partitionCount > 1) {
                    mergePartitionEncodeHelperSplit(
                        op.partitionCount,
                        handler, currentID,
                        data, op.partitionVariable, mapping, distributed, op.children[0] as OPBase, "", "", EOperatorIDExt.POPDistributedSendMultiID, EOperatorIDExt.POPDistributedReceiveSingleID, op.partitionID, op.uuid, offPtr, op.partitionVariable!!
                    )
                } else {
                    mergePartitionEncodeHelper1x1(
                        handler, currentID,
                        data, mapping,
                        distributed, op.children[0] as OPBase, "", EOperatorIDExt.POPDistributedSendSingleID, EOperatorIDExt.POPDistributedReceiveSingleID, offPtr
                    )
                }
            },
        )
        assignOperatorPhysicalEncode(
            EOperatorIDExt.POPSplitPartitionFromStoreCountID,
            { op, data, mapping, distributed, handler, offPtr ->
                op as OPBase
                val currentID = handler.currentID
                if (distributed) {
                    mergePartitionEncodeHelper1x1(
                        handler,
                        currentID, data, mapping, distributed, op.children[0] as OPBase, "", EOperatorIDExt.POPDistributedSendSingleCountID, EOperatorIDExt.POPDistributedReceiveSingleCountID, offPtr
                    )
                } else {
                    convertToByteArrayHelper(
                        op.children[0],
                        data,
                        mapping,
                        distributed,
                        handler,
                        offPtr
                    )
                }
            },
        )
        assignOperatorPhysicalEncode(
            EOperatorIDExt.POPSplitPartitionFromStoreID,
            { op, data, mapping, distributed, handler, offPtr ->
                op as OPBase
                val currentID = handler.currentID
                if (distributed) {
                    mergePartitionEncodeHelper1x1(
                        handler,
                        currentID, data, mapping, distributed, op.children[0] as OPBase, "", EOperatorIDExt.POPDistributedSendSingleID, EOperatorIDExt.POPDistributedReceiveSingleID, offPtr
                    )
                } else {
                    convertToByteArrayHelper(
                        op.children[0],
                        data,
                        mapping,
                        distributed,
                        handler,
                        offPtr
                    )
                }
            },
        )
        assignOperatorPhysicalEncode(
            EOperatorIDExt.POPSplitMergePartitionFromStoreID,
            { op, data, mapping, distributed, handler, offPtr ->
                op as OPBase
                val currentID = handler.currentID
                if (distributed) {
                    mergePartitionEncodeHelper1x1(
                        handler,
                        currentID, data, mapping, distributed, op.children[0] as OPBase, "", EOperatorIDExt.POPDistributedSendSingleID, EOperatorIDExt.POPDistributedReceiveSingleID, offPtr
                    )
                } else {
                    convertToByteArrayHelper(
                        op.children[0],
                        data,
                        mapping,
                        distributed,
                        handler,
                        offPtr
                    )
                }
            },
        )
        assignOperatorPhysicalEncode(
            EOperatorIDExt.POPGraphOperationID,
            { op, data, mapping, _, _, _ ->
                op as POPGraphOperation
                ConverterBinaryEncoder.encodePOPGraphOperation(data, mapping, op.graph1type, op.graph2type, op.action, op.graph1iri, op.graph2iri, op.silent)
            },
        )
        assignOperatorPhysicalEncode(
            EOperatorIDExt.POPProjectionID,
            { op, data, mapping, distributed, handler, offPtr ->
                op as OPBase
                convertToByteArrayHelper(op.children[0], data, mapping, distributed, handler, offPtr)
            },
        )
        assignOperatorPhysicalEncode(
            EOperatorIDExt.POPDebugID,
            { op, data, mapping, distributed, handler, offPtr ->
                op as OPBase
                convertToByteArrayHelper(op.children[0], data, mapping, distributed, handler, offPtr)
            },
        )
        assignOperatorPhysicalEncode(
            EOperatorIDExt.POPModifyDataID,
            { op, data, mapping, _, _, _ ->
                op as POPModifyData
                ConverterBinaryEncoder.encodePOPModifyData(data, mapping, op.data.map { it.graph to DictionaryValueTypeArray(3) { i -> (it.children[i] as AOPConstant).value } })
            },
        )
        assignOperatorPhysicalEncode(
            EOperatorIDExt.POPNothingID,
            { op, data, mapping, _, _, _ ->
                op as POPNothing
                ConverterBinaryEncoder.encodePOPNothing(data, mapping, op.getProvidedVariableNames())
            },
        )
        assignOperatorPhysicalEncode(
            intArrayOf(
                EOperatorIDExt.POPValuesID,
                EOperatorIDExt.POPValuesCountID,
            ),
            { op, data, mapping, _, _, _ ->
                op as POPValues
                if (op.rows == -1) {
                    ConverterBinaryEncoder.encodePOPValues(data, mapping, op.data)
                } else {
                    ConverterBinaryEncoder.encodePOPValuesCount(data, mapping, op.rows)
                }
            },
        )
        assignOperatorPhysicalEncode(
            EOperatorIDExt.POPEmptyRowID,
            { op, data, mapping, _, _, _ ->
                ConverterBinaryEncoder.encodePOPEmptyRow(data, mapping)
            },
        )
        assignOperatorPhysicalEncode(
            EOperatorIDExt.POPUnionID,
            { op, data, mapping, distributed, handler, _ ->
                op as POPUnion
                ConverterBinaryEncoder.encodePOPUnion(
                    data,
                    mapping,
                    { parentOffOff -> convertToByteArrayHelper(op.children[0], data, mapping, distributed, handler, parentOffOff) },
                    { parentOffOff -> convertToByteArrayHelper(op.children[1], data, mapping, distributed, handler, parentOffOff) },
                    op.projectedVariables,
                )
            },
        )
        assignOperatorPhysicalEncode(
            EOperatorIDExt.POPMinusID,
            { op, data, mapping, distributed, handler, _ ->
                op as POPMinus
                ConverterBinaryEncoder.encodePOPMinus(
                    data,
                    mapping,
                    { parentOffOff -> convertToByteArrayHelper(op.children[0], data, mapping, distributed, handler, parentOffOff) },
                    { parentOffOff -> convertToByteArrayHelper(op.children[1], data, mapping, distributed, handler, parentOffOff) },
                    op.projectedVariables,
                )
            },
        )
        assignOperatorPhysicalEncode(
            EOperatorIDExt.LOPJoinTopologyID,
            { op, data, mapping, distributed, handler, offPtr ->
                op as LOPJoinTopology
val currentID=handler.currentID
        var keys = IntArray(op.children.size){handler.getNextKey() }
val off = ByteArrayWrapperExt.getSize(data)
        ByteArrayWrapperExt.setSize(data, off + 8 + 16 * op.children.size, true)
        ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.LOPJoinTopologyID, { "operatorID" })
        ByteArrayWrapperExt.writeInt4(data, off + 4, op.children.size, { "POPJoinTolology.child.size" })

        for (i in 0 until op.children.size) {
            var childID = handler.getNextChildID()
            handler.idToOffset[childID] = -1
            handler.currentID = childID
            var deps = handler.dependenciesForID[currentID]
            if (deps == null) {
                handler.dependenciesForID[currentID] = mutableMapOf(childID to keys[i])
            } else {
                deps[childID] = keys[i]
            }
            var o = off + 8 + 4 * op.children.size+12*i
            val child = convertToByteArrayHelper(op.children[i], data, mapping, distributed, handler, o + 8)
            handler.keyLocationReceive(keys[i], offPtr)
            ByteArrayWrapperExt.writeInt4(data, off + 8 + 4 * i, keys[i], { "POPJoinTolology.key[$i]" })
            handler.keyLocationSend(keys[i], o)
            handler.idToOffset[childID] = o
            ByteArrayWrapperExt.writeInt4(data, o + 0, EOperatorIDExt.POPDistributedSendSingleID, { "operatorID" })
            ByteArrayWrapperExt.writeInt4(data, o + 4, keys[i], { "POPDistributedSendSingle.key" })
            ByteArrayWrapperExt.writeInt4(data, o + 8, child, { "POPDistributedSendSingle.child" })
        }
        handler.currentID = currentID
off
},
        )
        assignOperatorPhysicalEncode(
            EOperatorIDExt.POPJoinMergeSingleColumnID,
            { op, data, mapping, distributed, handler, _ ->
                op as POPJoinMergeSingleColumn
                ConverterBinaryEncoder.encodePOPJoinMergeSingleColumn(
                    data,
                    mapping,
                    { parentOffOff -> convertToByteArrayHelper(op.children[0], data, mapping, distributed, handler, parentOffOff) },
                    { parentOffOff -> convertToByteArrayHelper(op.children[1], data, mapping, distributed, handler, parentOffOff) },
                )
            },
        )
        assignOperatorPhysicalEncode(
            EOperatorIDExt.POPJoinMergeOptionalID,
            { op, data, mapping, distributed, handler, _ ->
                op as POPJoinMergeOptional
                ConverterBinaryEncoder.encodePOPJoinMergeOptional(
                    data,
                    mapping,
                    { parentOffOff -> convertToByteArrayHelper(op.children[0], data, mapping, distributed, handler, parentOffOff) },
                    { parentOffOff -> convertToByteArrayHelper(op.children[1], data, mapping, distributed, handler, parentOffOff) },
                    op.projectedVariables,
                )
            },
        )
        assignOperatorPhysicalEncode(
            EOperatorIDExt.POPJoinMergeID,
            { op, data, mapping, distributed, handler, _ ->
                op as POPJoinMerge
                ConverterBinaryEncoder.encodePOPJoinMerge(
                    data,
                    mapping,
                    { parentOffOff -> convertToByteArrayHelper(op.children[0], data, mapping, distributed, handler, parentOffOff) },
                    { parentOffOff -> convertToByteArrayHelper(op.children[1], data, mapping, distributed, handler, parentOffOff) },
                    op.projectedVariables,
                )
            },
        )
        assignOperatorPhysicalEncode(
            EOperatorIDExt.POPJoinHashMapID,
            { op, data, mapping, distributed, handler, _ ->
                op as POPJoinHashMap
                ConverterBinaryEncoder.encodePOPJoinHashMap(
                    data,
                    mapping,
                    { parentOffOff -> convertToByteArrayHelper(op.children[0], data, mapping, distributed, handler, parentOffOff) },
                    { parentOffOff -> convertToByteArrayHelper(op.children[1], data, mapping, distributed, handler, parentOffOff) },
                    op.optional,
                    op.projectedVariables,
                )
            },
        )
        assignOperatorPhysicalEncode(
            EOperatorIDExt.POPJoinCartesianProductID,
            { op, data, mapping, distributed, handler, _ ->
                op as POPJoinCartesianProduct
                ConverterBinaryEncoder.encodePOPJoinCartesianProduct(
                    data,
                    mapping,
                    { parentOffOff -> convertToByteArrayHelper(op.children[0], data, mapping, distributed, handler, parentOffOff) },
                    { parentOffOff -> convertToByteArrayHelper(op.children[1], data, mapping, distributed, handler, parentOffOff) },
                    op.optional,
                )
            },
        )
        assignOperatorPhysicalEncode(
            EOperatorIDExt.POPLimitID,
            { op, data, mapping, distributed, handler, _ ->
                op as POPLimit
                ConverterBinaryEncoder.encodePOPLimit(
                    data,
                    mapping,
                    { parentOffOff -> convertToByteArrayHelper(op.children[0], data, mapping, distributed, handler, parentOffOff) },
                    op.limit,
                )
            },
        )
        assignOperatorPhysicalEncode(
            EOperatorIDExt.POPSortID,
            { op, data, mapping, distributed, handler, offPtr ->
                op as POPSort
                if (op.getProvidedVariableNames().size == 0) {
                    convertToByteArrayHelper(op.children[0], data, mapping, distributed, handler, offPtr)
                } else {
                    ConverterBinaryEncoder.encodePOPSort(
                        data,
                        mapping,
                        { parentOffOff -> convertToByteArrayHelper(op.children[0], data, mapping, distributed, handler, parentOffOff) },
                        op.mySortPriority.map { it.variableName },
                        op.getProvidedVariableNames(),
                        op.sortBy.map { it.name },
                        op.sortOrder,
                    )
                }
            },
        )
        assignOperatorPhysicalEncode(
            EOperatorIDExt.POPOffsetID,
            { op, data, mapping, distributed, handler, _ ->
                op as POPOffset
                ConverterBinaryEncoder.encodePOPOffset(
                    data,
                    mapping,
                    { parentOffOff -> convertToByteArrayHelper(op.children[0], data, mapping, distributed, handler, parentOffOff) },
                    op.offset,
                )
            },
        )
        assignOperatorPhysicalEncode(
            EOperatorIDExt.POPMakeBooleanResultID,
            { op, data, mapping, distributed, handler, _ ->
                op as POPMakeBooleanResult
                ConverterBinaryEncoder.encodePOPMakeBooleanResult(
                    data,
                    mapping,
                    { parentOffOff -> convertToByteArrayHelper(op.children[0], data, mapping, distributed, handler, parentOffOff) },
                )
            },
        )
        assignOperatorPhysicalEncode(
            EOperatorIDExt.POPReducedID,
            { op, data, mapping, distributed, handler, _ ->
                op as POPReduced
                ConverterBinaryEncoder.encodePOPReduced(
                    data,
                    mapping,
                    { parentOffOff -> convertToByteArrayHelper(op.children[0], data, mapping, distributed, handler, parentOffOff) },
                    op.projectedVariables.size,
                )
            },
        )
        assignOperatorPhysicalEncode(
            EOperatorIDExt.POPTripleStoreIterator,
            { op, data, mapping, _, handler, _ ->
                op as POPTripleStoreIterator
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
                var hostsTmp = handler.idToHost[handler.currentID]
                if (hostsTmp != null) {
                    hostsTmp.add(target.first)
                } else {
                    hostsTmp = mutableSetOf(target.first)
                    handler.idToHost[handler.currentID] = hostsTmp
                }
                ConverterBinaryEncoder.encodePOPTripleStoreIterator(
                    data,
                    mapping,
                    target,
                    op.getIndexPattern(),
                    op.children[0] is IAOPConstant,
                    (op.children[0] as? IAOPConstant)?.getValue(),
                    (op.children[0] as? IAOPVariable)?.getName(),
                    op.children[1] is IAOPConstant,
                    (op.children[1] as? IAOPConstant)?.getValue(),
                    (op.children[1] as? IAOPVariable)?.getName(),
                    op.children[2] is IAOPConstant,
                    (op.children[2] as? IAOPConstant)?.getValue(),
                    (op.children[2] as? IAOPVariable)?.getName(),
                )
            },
        )
        assignOperatorPhysicalEncode(
            EOperatorIDExt.POPBindID,
            { op, data, mapping, distributed, handler, _ ->
                op as POPBind
                var hostsTmp = handler.idToHost[handler.currentID]
                val rootAddress = op.getQuery().getInstance().LUPOS_PROCESS_URLS_STORE[0]
                if (hostsTmp != null) {
                    hostsTmp!!.add(rootAddress)
                } else {
                    hostsTmp = mutableSetOf(rootAddress)
                    handler.idToHost[handler.currentID] = hostsTmp!!
                }
                ConverterBinaryEncoder.encodePOPBind(
                    data,
                    mapping,
                    { parentOffOff -> convertToByteArrayHelper(op.children[0], data, mapping, distributed, handler, parentOffOff) },
                    op.getProvidedVariableNames(),
                    ConverterAOPBaseToBinary.encodeAOP(op.children[1] as AOPBase, data, mapping),
                    op.name.name,
                )
            },
        )
        assignOperatorPhysicalEncode(
            EOperatorIDExt.POPFilterID,
            { op, data, mapping, distributed, handler, _ ->
                op as POPFilter
                var hostsTmp = handler.idToHost[handler.currentID]
                val rootAddress = op.getQuery().getInstance().LUPOS_PROCESS_URLS_STORE[0]
                if (hostsTmp != null) {
                    hostsTmp!!.add(rootAddress)
                } else {
                    hostsTmp = mutableSetOf(rootAddress)
                    handler.idToHost[handler.currentID] = hostsTmp!!
                }
                ConverterBinaryEncoder.encodePOPFilter(
                    data,
                    mapping,
                    { parentOffOff -> convertToByteArrayHelper(op.children[0], data, mapping, distributed, handler, parentOffOff) },
                    op.getProvidedVariableNames(),
                    ConverterAOPBaseToBinary.encodeAOP(op.children[1] as AOPBase, data, mapping),
                )
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
            { op, data, mapping, distributed, handler, _ ->
                op as POPGroup
                var hostsTmp = handler.idToHost[handler.currentID]
                val rootAddress = op.getQuery().getInstance().LUPOS_PROCESS_URLS_STORE[0]
                if (hostsTmp != null) {
                    hostsTmp!!.add(rootAddress)
                } else {
                    hostsTmp = mutableSetOf(rootAddress)
                    handler.idToHost[handler.currentID] = hostsTmp!!
                }
                val keyColumnNames = op.by.map { it.name }
                var off = -1
                var done = false
                if (op.by.isEmpty()) {
                    off = ConverterBinaryEncoder.encodePOPGroupWithoutKeyColumn(
                        data,
                        mapping,
                        { parentOffOff -> convertToByteArrayHelper(op.children[0], data, mapping, distributed, handler, parentOffOff) },
                        op.projectedVariables,
                        op.bindings.map { it -> it.first to ConverterAOPBaseToBinary.encodeAOP(it.second, data, mapping) },
                    )
                    done = true
                } else if (op.canUseSortedInput()) {
                    off = ConverterBinaryEncoder.encodePOPGroupSorted(
                        data,
                        mapping,
                        { parentOffOff -> convertToByteArrayHelper(op.children[0], data, mapping, distributed, handler, parentOffOff) },
                        keyColumnNames,
                        op.projectedVariables,
                        op.bindings.map { it -> it.first to ConverterAOPBaseToBinary.encodeAOP(it.second, data, mapping) },
                    )
                    done = true
                } else if (op.isCountOnly()) {
                    if (op.by.size == 0) {
                        off = ConverterBinaryEncoder.encodePOPGroupCount0(
                            data,
                            mapping,
                            { parentOffOff -> convertToByteArrayHelper(op.children[0], data, mapping, distributed, handler, parentOffOff) },
                            op.bindings.toList().first().first,
                        )
                        done = true
                    } else if (op.by.size == 1) {
                        off = ConverterBinaryEncoder.encodePOPGroupCount1(
                            data,
                            mapping,
                            { parentOffOff -> convertToByteArrayHelper(op.children[0], data, mapping, distributed, handler, parentOffOff) },
                            op.bindings.toList().first().first,
                            op.by[0].name,
                        )
                        done = true
                    }
                }
                if (!done) {
                    off = ConverterBinaryEncoder.encodePOPGroup(
                        data,
                        mapping,
                        { parentOffOff -> convertToByteArrayHelper(op.children[0], data, mapping, distributed, handler, parentOffOff) },
                        keyColumnNames,
                        op.bindings.map { it -> it.first to ConverterAOPBaseToBinary.encodeAOP(it.second, data, mapping) },
                    )
                }
                off
            },
        )
        assignOperatorPhysicalEncode(
            EOperatorIDExt.POPModifyID,
            { op, data, mapping, distributed, handler, _ ->
                op as POPModify
                ConverterBinaryEncoder.encodePOPModify(
                    data,
                    mapping,
                    { parentOffOff -> convertToByteArrayHelper(op.children[0], data, mapping, distributed, handler, parentOffOff) },
                    op.modify,
                )
            },
        )
    }
}
