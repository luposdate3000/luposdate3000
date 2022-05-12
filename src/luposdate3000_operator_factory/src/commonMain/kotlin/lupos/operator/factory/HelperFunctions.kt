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

import lupos.shared.SanityCheck
import lupos.operator.arithmetik.AOPBase
import lupos.operator.arithmetik.noinput.AOPConstant
import lupos.operator.base.OPBase
import lupos.operator.base.OPBaseCompound
import lupos.operator.base.Query
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
import lupos.shared.dynamicArray.ByteArrayWrapper
import lupos.shared.inline.dynamicArray.ByteArrayWrapperExt
import lupos.shared.operator.IOPBase
import lupos.shared.operator.noinput.IAOPConstant
import lupos.shared.operator.noinput.IAOPVariable
import lupos.triple_store_manager.POPTripleStoreIterator

private typealias BinaryToHelperMap = (data: ByteArrayWrapper, offset: Int) -> String

public class HelperMetadata(internal val data: ByteArrayWrapper) {
    public val id2off = mutableMapOf<Int, Int>()
    public val id2host mutableMapOf<Int, MutableSet<String>>()
    public val key_send2id = mutableMapOf<Int, Int>()
    public val key_rec2id = mutableMapOf<Int, Int>()
    public val key_send2off = mutableMapOf<Int, Int>()
    public val key_rec2off = mutableMapOf<Int, Int>()

    private var operatorMap: Array<BinaryToHelperMap?> = Array(0) { null }
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

    init {
        var currentID = -1
        var parentOff = -1
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPDistributedSendSingleID,
            { data, off ->
                parentOff = off
                val key = ByteArrayWrapperExt.readInt4(data, off + 4, { "POPDistributedSendSingle.key" })
                val child = decodeHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 8, { "POPDistributedSendSingle.child" }))
                key_send2id[key] = currentID
                key_send2off[key] = off
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPDistributedSendMultiID,
            { data, off ->
                parentOff = off
                val child = decodeHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "POPDistributedSendMulti.child" }))
                val count = ByteArrayWrapperExt.readInt4(data, off + 8, { "POPDistributedSendMulti.count" })
                val name = ByteArrayWrapperExt.readInt4(data, off + 12, { "POPDistributedSendMulti.name" })
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

                parentOff = off
                val key = ByteArrayWrapperExt.readInt4(data, off + 4, { "POPDistributedSendSingleCount.key" })
                val child = decodeHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 8, { "POPDistributedSendSingleCount.child" }))
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
                    key_rec2id[key] = id
                    key_send2off = parentOff
                }
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPDistributedReceiveSingleID,
            { data, off ->

                val key = ByteArrayWrapperExt.readInt4(data, off + 4, { "POPDistributedReceiveSingle.key" })
                key_rec2id[key] = id
                key_send2off = parentOff

            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPDistributedReceiveSingleCountID,
            { data, off ->

                val key = ByteArrayWrapperExt.readInt4(data, off + 4, { "POPDistributedReceiveSingleCount.key" })
                key_rec2id[key] = id
                key_send2off = parentOff
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
                    key_rec2id[key] = id
                    key_send2off = parentOff
                }
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPDistributedReceiveMultiOrderedID,
            { data, off ->

                parentOff = off
                var keys = mutableListOf<Int>()
                val keysLen = ByteArrayWrapperExt.readInt4(data, off + 4, { "POPDistributedReceiveMultiOrdered.keys.size" })
                var o = off + 16
                for (i in 0 until keysLen) {
                    keys.add(ByteArrayWrapperExt.readInt4(data, o, { "POPDistributedReceiveMultiOrdered.keys[$i]" }))
                    o += 4
                }
                for (key in keys) {
                    key_rec2id[key] = id
                    key_send2off = parentOff
                }
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPGraphOperationID,
            { data, off ->

                parentOff = off
                val silent = ByteArrayWrapperExt.readInt1(data, off + 24, { "POPGraphOperation.silent" }) == 1
                val graph1type = ByteArrayWrapperExt.readInt4(data, off + 4, { "POPGraphOperation.graph1type" })
                val graph1iri = ConverterString.decodeStringNull(data, ByteArrayWrapperExt.readInt4(data, off + 16, { "POPGraphOperation.graph1iri" }))
                val graph2type = ByteArrayWrapperExt.readInt4(data, off + 8, { "POPGraphOperation.graph2type" })
                val graph2iri = ConverterString.decodeStringNull(data, ByteArrayWrapperExt.readInt4(data, off + 20, { "POPGraphOperation.graph2iri" }))
                val action = ByteArrayWrapperExt.readInt4(data, off + 12, { "POPGraphOperation.action" })
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPModifyDataID,
            { data, off ->

                parentOff = off
                val d = mutableListOf<Pair<String, DictionaryValueTypeArray>>()
                val l = ByteArrayWrapperExt.readInt4(data, off + 4, { "POPModifyData.data.size" })
                var o = off + 8
                for (i in 0 until l) {
                    val arr = DictionaryValueTypeArray(3)
                    for (j in 0 until 3) {
                        arr[j] = DictionaryValueHelper.fromByteArray(data, o + 4 + j * DictionaryValueHelper.getSize(), { "POPModifyData.data[$i][$j]" })
                    }
                    d.add(ConverterString.decodeString(data, ByteArrayWrapperExt.readInt4(data, o, { "POPModifyData.data[$i].graph" })) to arr)
                    o += DictionaryValueHelper.getSize() * 3 + 4
                }
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPNothingID,
            { data, off ->

                parentOff = off
                val len = ByteArrayWrapperExt.readInt4(data, off + 4, { "POPNothing.size" })
                val list = mutableListOf<String>()
                for (i in 0 until len) {
                    list.add(ConverterString.decodeString(data, ByteArrayWrapperExt.readInt4(data, off + 8 + 4 * i, { "POPNothing.data[$i]" })))
                }
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPValuesCountID,
            { data, off ->

                parentOff = off
                val size = ByteArrayWrapperExt.readInt4(data, off + 4, { "POPValueCount.rows.size" })
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPValuesID,
            { data, off ->

                parentOff = off
                val columns = ByteArrayWrapperExt.readInt4(data, off + 4, { "POPValues.columns.size" })
                val row_count = ByteArrayWrapperExt.readInt4(data, off + 8, { "POPValues.rows.size" })
                val dd = mutableMapOf<String, MutableList<DictionaryValueType>>()
                var o = off + 12
                for (c in 0 until columns) {
                    val rows = mutableListOf<DictionaryValueType>()
                    dd[ConverterString.decodeString(data, ByteArrayWrapperExt.readInt4(data, o, { "POPValues.column[$c]" }))] = rows
                    o += 4
                    for (i in 0 until row_count) {
                        rows.add(DictionaryValueHelper.fromByteArray(data, o, { "POPValues.data[$c][$i]" }))
                        o += DictionaryValueHelper.getSize()
                    }
                }
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPEmptyRowID,
            { data, off ->

                parentOff = off
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPUnionID,
            { data, off ->

                parentOff = off
                val child0 = decodeHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "POPUnion.child0" }))
                val child1 = decodeHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 8, { "POPUnion.child1" }))
                val l = ByteArrayWrapperExt.readInt4(data, off + 12, { "POPUnion.variables.size" })
                var projectedVariables = mutableListOf<String>()
                for (i in 0 until l) {
                    projectedVariables.add(ConverterString.decodeString(data, ByteArrayWrapperExt.readInt4(data, off + 16 + 4 * i, { "POPUnion.variables[$i]" })))
                }
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPMinusID,
            { data, off ->

                parentOff = off
                val child0 = decodeHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "POPMinus.child0" }))
                val child1 = decodeHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 8, { "POPMinus.child1" }))
                val l = ByteArrayWrapperExt.readInt4(data, off + 12, { "POPMinus.variables.size" })
                var projectedVariables = mutableListOf<String>()
                for (i in 0 until l) {
                    projectedVariables.add(ConverterString.decodeString(data, ByteArrayWrapperExt.readInt4(data, off + 16 + 4 * i, { "POPMinus.variables[$i]" })))
                }
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPJoinMergeOptionalID,
            { data, off ->

                parentOff = off
                val child0 = decodeHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "POPJoinMergeOptional.child0" }))
                val child1 = decodeHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 8, { "POPJoinMergeOptional.child1" }))
                val l = ByteArrayWrapperExt.readInt4(data, off + 12, { "POPJoinMergeOptional.variables.size" })
                var projectedVariables = mutableListOf<String>()
                for (i in 0 until l) {
                    projectedVariables.add(ConverterString.decodeString(data, ByteArrayWrapperExt.readInt4(data, off + 16 + 4 * i, { "POPJoinMergeOptional.variables[$i]" })))
                }
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPJoinMergeID,
            { data, off ->

                parentOff = off
                val child0 = decodeHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "POPJoinMerge.child0" }))
                val child1 = decodeHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 8, { "POPJoinMerge.child1" }))
                val l = ByteArrayWrapperExt.readInt4(data, off + 12, { "POPJoinMerge.variables.size" })
                var projectedVariables = mutableListOf<String>()
                for (i in 0 until l) {
                    projectedVariables.add(ConverterString.decodeString(data, ByteArrayWrapperExt.readInt4(data, off + 16 + 4 * i, { "POPJoinMerge.variables[$i]" })))
                }
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPJoinMergeSingleColumnID,
            { data, off ->

                parentOff = off
                val child0 = decodeHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "POPJoinMergeSingleColumn.child0" }))
                val child1 = decodeHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 8, { "POPJoinMergeSingleColumn.child1" }))
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPJoinHashMapID,
            { data, off ->

                parentOff = off
                val child0 = decodeHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "POPJoinHashMap.child0" }))
                val child1 = decodeHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 8, { "POPJoinHashMap.child1" }))
                val l = ByteArrayWrapperExt.readInt4(data, off + 13, { "POPJoinHashMap.variables.size" })
                var projectedVariables = mutableListOf<String>()
                for (i in 0 until l) {
                    projectedVariables.add(ConverterString.decodeString(data, ByteArrayWrapperExt.readInt4(data, off + 17 + 4 * i, { "POPJoinHashMap.variables[$i]" })))
                }
                val optional = ByteArrayWrapperExt.readInt1(data, off + 12, { "POPJoinHashMap.optional" }) == 1
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPJoinCartesianProductID,
            { data, off ->

                parentOff = off
                val child0 = decodeHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "POPJoinCartesianProduct.child0" }))
                val child1 = decodeHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 8, { "POPJoinCartesianProduct.child1" }))
                val optional = ByteArrayWrapperExt.readInt1(data, off + 12, { "POPJoinCartesianProduct.optional" }) == 1
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPLimitID,
            { data, off ->

                parentOff = off
                val child = decodeHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "POPLimit.child" }))
                val limit = ByteArrayWrapperExt.readInt4(data, off + 8, { "POPLimit.limit" })
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPSortID,
            { data, off ->

                parentOff = off
                val child = decodeHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "POPSort.child" }))
                val splen = ByteArrayWrapperExt.readInt4(data, off + 8, { "POPSort.sp.size" })
                val plen = ByteArrayWrapperExt.readInt4(data, off + 12, { "POPSort.p.size" })
                val sblen = ByteArrayWrapperExt.readInt4(data, off + 16, { "POPSort.sb.size" })
                val sortOrder = ByteArrayWrapperExt.readInt1(data, off + 20, { "POPSort.sortOrder" }) != 0
                var o = off + 21
                val spList = mutableListOf<String>()
                val pList = mutableListOf<String>()
                val sbList = mutableListOf<String>()
                for (i in 0 until splen) {
                    spList.add(ConverterString.decodeString(data, ByteArrayWrapperExt.readInt4(data, o, { "POPSort.sp[$i]" })))
                    o += 4
                }
                for (i in 0 until sblen) {
                    sbList.add(ConverterString.decodeString(data, ByteArrayWrapperExt.readInt4(data, o, { "POPSort.sb[$i]" })))
                    o += 4
                }
                for (i in 0 until plen) {
                    pList.add(ConverterString.decodeString(data, ByteArrayWrapperExt.readInt4(data, o, { "POPSort.p[$i]" })))
                    o += 4
                }
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPOffsetID,
            { data, off ->

                parentOff = off
                val child = decodeHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "POPOffset.child" }))
                val offset = ByteArrayWrapperExt.readInt4(data, off + 8, { "POPOffset.offset" })
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPMakeBooleanResultID,
            { data, off ->

                parentOff = off
                val child = decodeHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "POPMakeBooleanResult.child" }))
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPReducedID,
            { data, off ->

                parentOff = off
                val child = decodeHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "POPReduced.child" }))
                val size = ByteArrayWrapperExt.readInt4(data, off + 8, { "POPReduced.variables.size" })
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPTripleStoreIterator,
            { data, off ->

                parentOff = off
                val buf1 = ConverterString.decodeString(data, ByteArrayWrapperExt.readInt4(data, off + 4, { "POPTripleStoreIterator.target.first" }))
                val buf2 = ConverterString.decodeString(data, ByteArrayWrapperExt.readInt4(data, off + 8, { "POPTripleStoreIterator.target.second" }))
                val index = ByteArrayWrapperExt.readInt4(data, off + 12, { "POPTripleStoreIterator.IndexPattern" })
                val childFlag = ByteArrayWrapperExt.readInt1(data, off + 16, { "POPTripleStoreIterator.childFlag" })
                var o = off + 17
                val child0f = (childFlag and 0x1) > 0
                val child1f = (childFlag and 0x2) > 0
                val child2f = (childFlag and 0x4) > 0
                val child0c = if (child0f) {
                    val res = DictionaryValueHelper.fromByteArray(data, o, { "POPTripleStoreIterator.constant[0]" })
                    o += DictionaryValueHelper.getSize()
                    res
                } else {
                    DictionaryValueHelper.nullValue
                }
                val child0v = if (child0f) {
                    ""
                } else {
                    val res = ConverterString.decodeString(data, ByteArrayWrapperExt.readInt4(data, o, { "POPTripleStoreIterator.variable[0]" }))
                    o += 4
                    res
                }
                val child1c = if (child1f) {
                    val res = DictionaryValueHelper.fromByteArray(data, o, { "POPTripleStoreIterator.constant[1]" })
                    o += DictionaryValueHelper.getSize()
                    res
                } else {
                    DictionaryValueHelper.nullValue
                }
                val child1v = if (child1f) {
                    ""
                } else {
                    val res = ConverterString.decodeString(data, ByteArrayWrapperExt.readInt4(data, o, { "POPTripleStoreIterator.variable[1]" }))
                    o += 4
                    res
                }
                val child2c = if (child2f) {
                    val res =
                        DictionaryValueHelper.fromByteArray(data, o, { "POPTripleStoreIterator.constant[2]" })
                    o += DictionaryValueHelper.getSize()
                    res
                } else {
                    DictionaryValueHelper.nullValue
                }
                val child2v = if (child2f) {
                    ""
                } else {
                    val res = ConverterString.decodeString(data, ByteArrayWrapperExt.readInt4(data, o, { "POPTripleStoreIterator.variable[2]" }))
                    o += 4
                    res
                }
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPBindID,
            { data, off ->

                parentOff = off
                val child = decodeHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "POPBind.child" }))
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPFilterID,
            { data, off ->

                parentOff = off
                val child = decodeHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "POPFilter.child" }))
                val variablesOut = mutableListOf<String>()
                val len = ByteArrayWrapperExt.readInt4(data, off + 12, { "POPFilter.variables.size" })
                for (i in 0 until len) {
                    variablesOut.add(ConverterString.decodeString(data, ByteArrayWrapperExt.readInt4(data, off + 16 + i * 4, { "POPFilter.variables[$i]" })))
                }
                val filter = ConverterBinaryToAOPJson.decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 8, { "POPFilter.filter" }))
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPGroupCount0ID,
            { data, off ->

                parentOff = off
                val child = decodeHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "POPGroupCount0.child" }))
                val binding = ConverterString.decodeString(data, ByteArrayWrapperExt.readInt4(data, off + 8, { "POPGroupCount0.column" }))
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPGroupCount1ID,
            { data, off ->

                parentOff = off
                val child = decodeHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "POPGroupCount1.child" }))
                val binding = ConverterString.decodeString(data, ByteArrayWrapperExt.readInt4(data, off + 8, { "POPGroupCount1.column" }))
                val name = ConverterString.decodeString(data, ByteArrayWrapperExt.readInt4(data, off + 12, { "POPGroupCount1.by" }))
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPGroupSortedID,
            { data, off ->

                parentOff = off
                val child = decodeHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "POPGroupSorted.child" }))
                val plen = ByteArrayWrapperExt.readInt4(data, off + 8, { "POPGroupSorted.variables.size" })
                val blen = ByteArrayWrapperExt.readInt4(data, off + 12, { "POPGroupSorted.bindings.size" })
                val klen = ByteArrayWrapperExt.readInt4(data, off + 16, { "POPGroupSorted.keys.size" })
                var o = off + 20
                val projectedVariables = mutableListOf<String>()
                for (i in 0 until plen) {
                    projectedVariables.add(ConverterString.decodeString(data, ByteArrayWrapperExt.readInt4(data, o, { "POPGroupSorted.variables[$i]" })))
                    o += 4
                }
                val keyColumnNames = Array<String>(klen) { "" }
                for (i in 0 until klen) {
                    keyColumnNames[i] = ConverterString.decodeString(data, ByteArrayWrapperExt.readInt4(data, o, { "POPGroupSorted.keys[$i]" }))
                    o += 4
                }
                val bindings = mutableListOf<Pair<String, AOPBase>>()
                for (i in 0 until blen) {
                    val k = ConverterString.decodeString(data, ByteArrayWrapperExt.readInt4(data, o, { "POPGroupSorted.bindings[$i].name" }))
                    o += 4
                    val v = ConverterBinaryToAOPBase.decode(query, data, ByteArrayWrapperExt.readInt4(data, o, { "POPGroupSorted.bindings[$i].value" }))
                    o += 4
                    bindings.add(k to v)
                }
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPGroupID,
            { data, off ->

                parentOff = off
                val child = decodeHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "POPGroup.child" }))
                val blen = ByteArrayWrapperExt.readInt4(data, off + 8, { "POPGroup.bindings.size" })
                val klen = ByteArrayWrapperExt.readInt4(data, off + 12, { "POPGroup.keys.size" })
                var o = off + 16
                val keyColumnNames = Array<String>(klen) { "" }
                for (i in 0 until klen) {
                    keyColumnNames[i] = ConverterString.decodeString(data, ByteArrayWrapperExt.readInt4(data, o, { "POPGroup.keys[$i]" }))
                    o += 4
                }
                val bindings = mutableListOf<Pair<String, AOPBase>>()
                for (i in 0 until blen) {
                    val k = ConverterString.decodeString(data, ByteArrayWrapperExt.readInt4(data, o, { "POPGroup.bindings[$i].name" }))
                    o += 4
                    val v = ConverterBinaryToAOPBase.decode(query, data, ByteArrayWrapperExt.readInt4(data, o, { "POPGroup.bindings[$i].value" }))
                    o += 4
                    bindings.add(k to v)
                }
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPGroupWithoutKeyColumnID,
            { data, off ->

                parentOff = off
                val child = decodeHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "POPGroupWithoutKeyColumn.child" }))
                val plen = ByteArrayWrapperExt.readInt4(data, off + 8, { "POPGroupWithoutKeyColumn.variables.size" })
                val blen = ByteArrayWrapperExt.readInt4(data, off + 12, { "POPGroupWithoutKeyColumn.bindings.size" })
                var o = off + 16
                val projectedVariables = mutableListOf<String>()
                for (i in 0 until plen) {
                    projectedVariables.add(ConverterString.decodeString(data, ByteArrayWrapperExt.readInt4(data, o, { "POPGroupWithoutKeyColumn.variables[$i]" })))
                    o += 4
                }
                val bindings = mutableListOf<Pair<String, AOPBase>>()
                for (i in 0 until blen) {
                    val k = ConverterString.decodeString(data, ByteArrayWrapperExt.readInt4(data, o, { "POPGroupWithoutKeyColumn.bindings[$i].name" }))
                    o += 4
                    val v = ConverterBinaryToAOPBase.decode(query, data, ByteArrayWrapperExt.readInt4(data, o, { "POPGroupWithoutKeyColumn.bindings[$i].value" }))
                    o += 4
                    bindings.add(k to v)
                }
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPModifyID,
            { data, off ->

                parentOff = off
                val child = decodeHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "POPModify.child" }))
                val steph = if (DictionaryValueHelper.getSize() > 4) DictionaryValueHelper.getSize() else 4
                val step = 9 + 3 * steph
                val modify = Array<Pair<LOPTriple, EModifyType>>(ByteArrayWrapperExt.readInt4(data, off + 8, { "POPModify.modify.size" })) { it ->
                    val o = off + 12 + it * step
                    val v = ByteArrayWrapperExt.readInt4(data, o, { "POPModify.modify[$it].v" })
                    val flag = ByteArrayWrapperExt.readInt1(data, o + 8, { "POPModify.modify[$it].flag" })
                    val graph = ConverterString.decodeString(data, ByteArrayWrapperExt.readInt4(data, o + 4, { "POPModify.modify[$it].graph" }))
                    val s = if ((flag and 0x2) != 0) {
                        AOPConstant(query, DictionaryValueHelper.fromByteArray(data, o + 9, { "POPModify.modify[$it].child[0].c" }))
                    } else {
                        AOPVariable(query, ConverterString.decodeString(data, ByteArrayWrapperExt.readInt4(data, o + 9, { "POPModify.modify[$it].child[0].v" })))
                    }
                    val p = if ((flag and 0x4) != 0) {
                        AOPConstant(query, DictionaryValueHelper.fromByteArray(data, o + 9 + steph, { "POPModify.modify[$it].child[1].c" }))
                    } else {
                        AOPVariable(query, ConverterString.decodeString(data, ByteArrayWrapperExt.readInt4(data, o + 9 + steph, { "POPModify.modify[$it].child[1].v" })))
                    }
                    val oo = if ((flag and 0x8) != 0) {
                        AOPConstant(query, DictionaryValueHelper.fromByteArray(data, o + 9 + steph + steph, { "POPModify.modify[$it].child[2].c" }))
                    } else {
                        AOPVariable(query, ConverterString.decodeString(data, ByteArrayWrapperExt.readInt4(data, o + 9 + steph + steph, { "POPModify.modify[$it].child[2].v" })))
                    }
                    val graphVar = (flag and 0x1) != 0
                    val k = LOPTriple(query, s, p, oo, graph, graphVar)
                    k to v
                }
            },
        )

    }
}
