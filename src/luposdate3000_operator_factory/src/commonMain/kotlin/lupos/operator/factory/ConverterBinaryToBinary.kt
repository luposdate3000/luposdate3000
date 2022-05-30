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
import lupos.operator.arithmetik.noinput.AOPConstant
import lupos.operator.arithmetik.noinput.AOPVariable
import lupos.operator.base.Query
import lupos.operator.logical.noinput.LOPTriple
import lupos.shared.DictionaryValueHelper
import lupos.shared.DictionaryValueType
import lupos.shared.DictionaryValueTypeArray
import lupos.shared.EModifyType
import lupos.shared.EOperatorIDExt
import lupos.shared.dynamicArray.ByteArrayWrapper
import lupos.shared.inline.dynamicArray.ByteArrayWrapperExt
import lupos.shared.myPrintStackTraceAndThrowAgain
import lupos.triple_store_manager.POPTripleStoreIterator

public typealias BinaryToBinaryMap = (query: Query, offset: Int, data: ByteArrayWrapper, dataOut: ByteArrayWrapper, mapping: MutableMap<String, Int>, offPtr: Int) -> Int/*offset*/

public object ConverterBinaryToBinary {
    public var operatorMap: Array<BinaryToBinaryMap?> = Array(0) { null }
    public fun assignOperatorPhysicalDecode(operatorIDs: IntArray, operator: BinaryToBinaryMap) {
        for (operatorID in operatorIDs) {
            assignOperatorPhysicalDecode(operatorID, operator)
        }
    }

    public fun assignOperatorPhysicalDecode(operatorID: Int, operator: BinaryToBinaryMap) {
        if (operatorMap.size <= operatorID) {
            var s = operatorMap.size
            if (s < 16) {
                s = 16
            }
            while (s <= operatorID) {
                s = s * 2
            }
            val tmp = Array<BinaryToBinaryMap?>(s) { null }
            operatorMap.copyInto(tmp)
            operatorMap = tmp
        }
        operatorMap[operatorID] = operator
    }

    public fun decode(query: Query, data: ByteArrayWrapper, filter: IntArray?): ByteArrayWrapper {
        val dataOut = ByteArrayWrapper()
        try {
            val mapping = mutableMapOf<String, Int>()
            var result = mutableMapOf<Int, String>()
            if (filter==null||filter.contains(-1)) {
                when (ByteArrayWrapperExt.readInt1(data, 4, { "Root.isOPBaseCompound" })) {
                    0x1 -> {
                        val childCount = ByteArrayWrapperExt.readInt4(data, 5, { "OPBaseCompound.children.size" })
                        var o = 9
                        val columnProjectionOrder = mutableListOf<List<String>>()
                        val childs = mutableListOf<Int>()
                        for (i in 0 until childCount) {
                            val child = ByteArrayWrapperExt.readInt4(data, o, { "OPBaseCompound.children[$i]" })
                            o += 4
                            val size = ByteArrayWrapperExt.readInt4(data, o, { "OPBaseCompound.columnProjectionOrder[$i].size" })
                            o += 4
                            val list = mutableListOf<String>()
                            for (j in 0 until size) {
                                list.add(ConverterString.decodeString(data, ByteArrayWrapperExt.readInt4(data, o, { "OPBaseCompound.columnProjectionOrder[$i][$j]" })))
                                o += 4
                            }
                            childs.add(child)
                            columnProjectionOrder.add(list)
                        }
                        ByteArrayWrapperExt.setSize(dataOut, 9 + 8 * childCount + columnProjectionOrder.map { it.size }.sum() * 4, false)
                        ByteArrayWrapperExt.writeInt1(dataOut, 4, 0x1, { "Root.isOPBaseCompound" })
                        ByteArrayWrapperExt.writeInt4(dataOut, 5, childCount, { "OPBaseCompound.children.size" })
                        o = 9
                        for (i in 0 until childCount) {
                            val k = columnProjectionOrder[i]
                            val child = recodeHelper(query, childs[i], data, dataOut, mapping, o)
                            ByteArrayWrapperExt.writeInt4(dataOut, o, child, { "OPBaseCompound.children[$i]" })
                            o += 4
                            ByteArrayWrapperExt.writeInt4(dataOut, o, k.size, { "OPBaseCompound.columnProjectionOrder[$i].size" })
                            o += 4
                            for (j in 0 until k.size) {
                                ByteArrayWrapperExt.writeInt4(dataOut, o, ConverterString.encodeString(k[j], dataOut, mapping), { "OPBaseCompound.columnProjectionOrder[$i][$j]" })
                                o += 4
                            }
                        }
                    }
                    0x2 -> {
                        /*there is no query root here*/
                    }
                    else -> {
                        val tmp = recodeHelper(query, ByteArrayWrapperExt.readInt4(data, 5, { "OPBase.children[0]" }), data, dataOut, mapping, 5)
                        ByteArrayWrapperExt.setSize(dataOut, 9, false)
                        ByteArrayWrapperExt.writeInt1(dataOut, 4, 0x0, { "OPBase.isOPBaseCompound" })
                        ByteArrayWrapperExt.writeInt4(dataOut, 5, tmp, { "OPBase.children[0]" })
                    }
                }
            } else {
                ByteArrayWrapperExt.setSize(dataOut, 5, false)
                ByteArrayWrapperExt.writeInt1(dataOut, 4, 0x2, { "OPBase.isOPBaseCompound" })
            }
            var off = ByteArrayWrapperExt.readInt4(data, 0, { "OPBase.handler" })
            val len = ByteArrayWrapperExt.readInt4(data, off, { "OPBase.offsetMap.size" })
            var o = off + 4
            val childs = mutableMapOf<Int, Int>()
            for (i in 0 until len) {
                val id = ByteArrayWrapperExt.readInt4(data, o, { "OPBase.offsetMap[$i].id" })
                if (filter==null||(filter.contains(id) && id != -1)) {
                    val offset = ByteArrayWrapperExt.readInt4(data, o + 4, { "OPBase.offsetMap[$i].offset" })
                    childs[id] = offset
                }
                o += 8
            }
            val offOut = ByteArrayWrapperExt.getSize(dataOut)
            ByteArrayWrapperExt.writeInt4(dataOut, 0, offOut, { "OPBase.handler" })
            ByteArrayWrapperExt.setSize(dataOut, offOut + 4 + 8 * childs.size, true)
            ByteArrayWrapperExt.writeInt4(dataOut, offOut, childs.size, { "OPBase.offsetMap.size" })
            var oOut = offOut + 4
            var i = 0
            for ((k, oldOff) in childs) {
                val v = recodeHelper(query, oldOff, data, dataOut, mapping, oOut + 4)
                ByteArrayWrapperExt.writeInt4(dataOut, oOut, k, { "OPBase.offsetMap[$i].id" })
                ByteArrayWrapperExt.writeInt4(dataOut, oOut + 4, v, { "OPBase.offsetMap[$i].offset" })
                oOut += 8
                i++
            }
        } catch (e: Throwable) {
            e.myPrintStackTraceAndThrowAgain(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_factory/src/commonMain/kotlin/lupos/operator/factory/ConverterBinaryToBinary.kt:139"/*SOURCE_FILE_END*/)
        }
        return dataOut
    }

    private fun recodeHelper(query: Query, off: Int, data: ByteArrayWrapper, dataOut: ByteArrayWrapper, mapping: MutableMap<String, Int>, offPtr: Int): Int {
        val type = ByteArrayWrapperExt.readInt4(data, off, { "operatorID" })
        if (type >= operatorMap.size) {
            TODO("recodeHelper $type -> ${EOperatorIDExt.names[type]}")
        }
        val recoder = operatorMap[type]
        if (recoder == null) {
            TODO("recodeHelper $type -> ${EOperatorIDExt.names[type]}")
        }
        return recoder(query, off, data, dataOut, mapping, offPtr)
    }

    init {
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPDistributedSendSingleID,
            { query, off, data, dataOut, mapping, offPtr ->
                val key = ByteArrayWrapperExt.readInt4(data, off + 4, { "POPDistributedSendSingle.key" })
                val child = ByteArrayWrapperExt.readInt4(data, off + 8, { "POPDistributedSendSingle.child" })
                ConverterBinaryEncoder.encodePOPDistributedSendSingle(
                    dataOut,
                    mapping,
                    key,
                    { parentOffOff -> recodeHelper(query, child, data, dataOut, mapping, parentOffOff) },
                )
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPDistributedSendMultiID,
            { query, off, data, dataOut, mapping, offPtr ->
                val child = ByteArrayWrapperExt.readInt4(data, off + 4, { "POPDistributedSendMulti.child" })
                val count = ByteArrayWrapperExt.readInt4(data, off + 8, { "POPDistributedSendMulti.count" })
                val name = ConverterString.decodeString(data, ByteArrayWrapperExt.readInt4(data, off + 12, { "POPDistributedSendMulti.name" }))
                val keys = IntArray(count) { ByteArrayWrapperExt.readInt4(data, off + 16 + 4 * it, { "POPDistributedSendMulti.key[$it]" }) }
                ConverterBinaryEncoder.encodePOPDistributedSendMulti(
                    dataOut,
                    mapping,
                    keys.toList(),
                    { parentOffOff -> recodeHelper(query, child, data, dataOut, mapping, parentOffOff) },
                    name,
                )
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPDistributedSendSingleCountID,
            { query, off, data, dataOut, mapping, offPtr ->
                val key = ByteArrayWrapperExt.readInt4(data, off + 4, { "POPDistributedSendSingleCount.key" })
                val child = ByteArrayWrapperExt.readInt4(data, off + 8, { "POPDistributedSendSingleCount.child" })
                ConverterBinaryEncoder.encodePOPDistributedSendSingleCount(
                    dataOut,
                    mapping,
                    key,
                    { parentOffOff -> recodeHelper(query, child, data, dataOut, mapping, parentOffOff) },
                )
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPDistributedReceiveMultiID,
            { query, off, data, dataOut, mapping, offPtr ->
                var keys = mutableListOf<Int>()
                val len = ByteArrayWrapperExt.readInt4(data, off + 4, { "POPDistributedReceiveMulti.size" })
                for (i in 0 until len) {
                    keys.add(ByteArrayWrapperExt.readInt4(data, off + 8 + 4 * i, { "POPDistributedReceiveMulti.key[$i]" }))
                }
                ConverterBinaryEncoder.encodePOPDistributedReceiveMulti(dataOut, mapping, keys)
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPDistributedReceiveSingleID,
            { query, off, data, dataOut, mapping, offPtr ->
                val key = ByteArrayWrapperExt.readInt4(data, off + 4, { "POPDistributedReceiveSingle.key" })
                ConverterBinaryEncoder.encodePOPDistributedReceiveSingle(dataOut, mapping, key)
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPDistributedReceiveSingleCountID,
            { query, off, data, dataOut, mapping, offPtr ->
                val key = ByteArrayWrapperExt.readInt4(data, off + 4, { "POPDistributedReceiveSingleCount.key" })
                ConverterBinaryEncoder.encodePOPDistributedReceiveSingleCount(dataOut, mapping, key)
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPDistributedReceiveMultiCountID,
            { query, off, data, dataOut, mapping, offPtr ->
                var keys = mutableListOf<Int>()
                val len = ByteArrayWrapperExt.readInt4(data, off + 4, { "POPDistributedReceiveMultiCount.size" })
                for (i in 0 until len) {
                    keys.add(ByteArrayWrapperExt.readInt4(data, off + 8 + 4 * i, { "POPDistributedReceiveMultiCount.key[$i]" }))
                }
                ConverterBinaryEncoder.encodePOPDistributedReceiveMultiCount(dataOut, mapping, keys)
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPDistributedReceiveMultiOrderedID,
            { query, off, data, dataOut, mapping, offPtr ->
                var keys = mutableListOf<Int>()
                var orderedBy = mutableListOf<String>()
                var variablesOut = mutableListOf<String>()
                val keysLen = ByteArrayWrapperExt.readInt4(data, off + 4, { "POPDistributedReceiveMultiOrdered.keys.size" })
                val orderedByLen = ByteArrayWrapperExt.readInt4(data, off + 8, { "POPDistributedReceiveMultiOrdered.orderedBy.size" })
                val variablesOutLen = ByteArrayWrapperExt.readInt4(data, off + 12, { "POPDistributedReceiveMultiOrdered.variablesOut.size" })
                var o = off + 16
                for (i in 0 until keysLen) {
                    keys.add(ByteArrayWrapperExt.readInt4(data, o, { "POPDistributedReceiveMultiOrdered.keys[$i]" }))
                    o += 4
                }
                for (i in 0 until orderedByLen) {
                    orderedBy.add(ConverterString.decodeString(data, ByteArrayWrapperExt.readInt4(data, o, { "POPDistributedReceiveMultiOrdered.orderedBy[$i]" })))
                    o += 4
                }
                for (i in 0 until variablesOutLen) {
                    variablesOut.add(ConverterString.decodeString(data, ByteArrayWrapperExt.readInt4(data, o, { "POPDistributedReceiveMultiOrdered.variablesOut[$i]" })))
                    o += 4
                }
                ConverterBinaryEncoder.encodePOPDistributedReceiveMultiOrdered(dataOut, mapping, keys, orderedBy, variablesOut)
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPGraphOperationID,
            { query, off, data, dataOut, mapping, offPtr ->
                val silent = ByteArrayWrapperExt.readInt1(data, off + 24, { "POPGraphOperation.silent" }) == 1
                val graph1type = ByteArrayWrapperExt.readInt4(data, off + 4, { "POPGraphOperation.graph1type" })
                val graph1iri = ConverterString.decodeStringNull(data, ByteArrayWrapperExt.readInt4(data, off + 16, { "POPGraphOperation.graph1iri" }))
                val graph2type = ByteArrayWrapperExt.readInt4(data, off + 8, { "POPGraphOperation.graph2type" })
                val graph2iri = ConverterString.decodeStringNull(data, ByteArrayWrapperExt.readInt4(data, off + 20, { "POPGraphOperation.graph2iri" }))
                val action = ByteArrayWrapperExt.readInt4(data, off + 12, { "POPGraphOperation.action" })
                ConverterBinaryEncoder.encodePOPGraphOperation(
                    dataOut,
                    mapping,
                    graph1type,
                    graph2type,
                    action,
                    graph1iri,
                    graph2iri,
                    silent,
                )
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPModifyDataID,
            { query, off, data, dataOut, mapping, offPtr ->
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
                ConverterBinaryEncoder.encodePOPModifyData(
                    dataOut,
                    mapping,
                    d
                )
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPNothingID,
            { query, off, data, dataOut, mapping, offPtr ->
                val len = ByteArrayWrapperExt.readInt4(data, off + 4, { "POPNothing.size" })
                val list = mutableListOf<String>()
                for (i in 0 until len) {
                    list.add(ConverterString.decodeString(data, ByteArrayWrapperExt.readInt4(data, off + 8 + 4 * i, { "POPNothing.data[$i]" })))
                }
                ConverterBinaryEncoder.encodePOPNothing(
                    dataOut,
                    mapping,
                    list
                )
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPValuesCountID,
            { query, off, data, dataOut, mapping, offPtr ->
                val size = ByteArrayWrapperExt.readInt4(data, off + 4, { "POPValueCount.rows.size" })
                ConverterBinaryEncoder.encodePOPValuesCount(
                    dataOut,
                    mapping,
                    size,
                )
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPValuesID,
            { query, off, data, dataOut, mapping, offPtr ->
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
                ConverterBinaryEncoder.encodePOPValues(
                    dataOut,
                    mapping,
                    dd,
                )
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPEmptyRowID,
            { query, off, data, dataOut, mapping, offPtr ->
                ConverterBinaryEncoder.encodePOPEmptyRow(
                    dataOut,
                    mapping,
                )
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPUnionID,
            { query, off, data, dataOut, mapping, offPtr ->
                val child0 = ByteArrayWrapperExt.readInt4(data, off + 4, { "POPUnion.child0" })
                val child1 = ByteArrayWrapperExt.readInt4(data, off + 8, { "POPUnion.child1" })
                val l = ByteArrayWrapperExt.readInt4(data, off + 12, { "POPUnion.variables.size" })
                var projectedVariables = mutableListOf<String>()
                for (i in 0 until l) {
                    projectedVariables.add(ConverterString.decodeString(data, ByteArrayWrapperExt.readInt4(data, off + 16 + 4 * i, { "POPUnion.variables[$i]" })))
                }
                ConverterBinaryEncoder.encodePOPUnion(
                    dataOut,
                    mapping,
                    { parentOffOff -> recodeHelper(query, child0, data, dataOut, mapping, parentOffOff) },
                    { parentOffOff -> recodeHelper(query, child1, data, dataOut, mapping, parentOffOff) },
                    projectedVariables,
                )
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPMinusID,
            { query, off, data, dataOut, mapping, offPtr ->
                val child0 = ByteArrayWrapperExt.readInt4(data, off + 4, { "POPMinus.child0" })
                val child1 = ByteArrayWrapperExt.readInt4(data, off + 8, { "POPMinus.child1" })
                val l = ByteArrayWrapperExt.readInt4(data, off + 12, { "POPMinus.variables.size" })
                var projectedVariables = mutableListOf<String>()
                for (i in 0 until l) {
                    projectedVariables.add(ConverterString.decodeString(data, ByteArrayWrapperExt.readInt4(data, off + 16 + 4 * i, { "POPMinus.variables[$i]" })))
                }
                ConverterBinaryEncoder.encodePOPMinus(
                    dataOut,
                    mapping,
                    { parentOffOff -> recodeHelper(query, child0, data, dataOut, mapping, parentOffOff) },
                    { parentOffOff -> recodeHelper(query, child1, data, dataOut, mapping, parentOffOff) },
                    projectedVariables,
                )
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPJoinMergeOptionalID,
            { query, off, data, dataOut, mapping, offPtr ->
                val child0 = ByteArrayWrapperExt.readInt4(data, off + 4, { "POPJoinMergeOptional.child0" })
                val child1 = ByteArrayWrapperExt.readInt4(data, off + 8, { "POPJoinMergeOptional.child1" })
                val l = ByteArrayWrapperExt.readInt4(data, off + 12, { "POPJoinMergeOptional.variables.size" })
                var projectedVariables = mutableListOf<String>()
                for (i in 0 until l) {
                    projectedVariables.add(ConverterString.decodeString(data, ByteArrayWrapperExt.readInt4(data, off + 16 + 4 * i, { "POPJoinMergeOptional.variables[$i]" })))
                }
                ConverterBinaryEncoder.encodePOPJoinMergeOptional(
                    dataOut,
                    mapping,
                    { parentOffOff -> recodeHelper(query, child0, data, dataOut, mapping, parentOffOff) },
                    { parentOffOff -> recodeHelper(query, child1, data, dataOut, mapping, parentOffOff) },
                    projectedVariables,
                )
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPJoinMergeID,
            { query, off, data, dataOut, mapping, offPtr ->
                val child0 = ByteArrayWrapperExt.readInt4(data, off + 4, { "POPJoinMerge.child0" })
                val child1 = ByteArrayWrapperExt.readInt4(data, off + 8, { "POPJoinMerge.child1" })
                val l = ByteArrayWrapperExt.readInt4(data, off + 12, { "POPJoinMerge.variables.size" })
                var projectedVariables = mutableListOf<String>()
                for (i in 0 until l) {
                    projectedVariables.add(ConverterString.decodeString(data, ByteArrayWrapperExt.readInt4(data, off + 16 + 4 * i, { "POPJoinMerge.variables[$i]" })))
                }
                ConverterBinaryEncoder.encodePOPJoinMerge(
                    dataOut,
                    mapping,
                    { parentOffOff -> recodeHelper(query, child0, data, dataOut, mapping, parentOffOff) },
                    { parentOffOff -> recodeHelper(query, child1, data, dataOut, mapping, parentOffOff) },
                    projectedVariables,
                )
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPJoinMergeSingleColumnID,
            { query, off, data, dataOut, mapping, offPtr ->
                val child0 = ByteArrayWrapperExt.readInt4(data, off + 4, { "POPJoinMergeSingleColumn.child0" })
                val child1 = ByteArrayWrapperExt.readInt4(data, off + 8, { "POPJoinMergeSingleColumn.child1" })
                ConverterBinaryEncoder.encodePOPJoinMergeSingleColumn(
                    dataOut,
                    mapping,
                    { parentOffOff -> recodeHelper(query, child0, data, dataOut, mapping, parentOffOff) },
                    { parentOffOff -> recodeHelper(query, child1, data, dataOut, mapping, parentOffOff) },
                )
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPJoinHashMapID,
            { query, off, data, dataOut, mapping, offPtr ->
                val child0 = ByteArrayWrapperExt.readInt4(data, off + 4, { "POPJoinHashMap.child0" })
                val child1 = ByteArrayWrapperExt.readInt4(data, off + 8, { "POPJoinHashMap.child1" })
                val l = ByteArrayWrapperExt.readInt4(data, off + 13, { "POPJoinHashMap.variables.size" })
                var projectedVariables = mutableListOf<String>()
                for (i in 0 until l) {
                    projectedVariables.add(ConverterString.decodeString(data, ByteArrayWrapperExt.readInt4(data, off + 17 + 4 * i, { "POPJoinHashMap.variables[$i]" })))
                }
                val optional = ByteArrayWrapperExt.readInt1(data, off + 12, { "POPJoinHashMap.optional" }) == 1
                ConverterBinaryEncoder.encodePOPJoinHashMap(
                    dataOut,
                    mapping,
                    { parentOffOff -> recodeHelper(query, child0, data, dataOut, mapping, parentOffOff) },
                    { parentOffOff -> recodeHelper(query, child1, data, dataOut, mapping, parentOffOff) },
                    optional,
                    projectedVariables,
                )
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPJoinCartesianProductID,
            { query, off, data, dataOut, mapping, offPtr ->
                val child0 = ByteArrayWrapperExt.readInt4(data, off + 4, { "POPJoinCartesianProduct.child0" })
                val child1 = ByteArrayWrapperExt.readInt4(data, off + 8, { "POPJoinCartesianProduct.child1" })
                val optional = ByteArrayWrapperExt.readInt1(data, off + 12, { "POPJoinCartesianProduct.optional" }) == 1
                ConverterBinaryEncoder.encodePOPJoinCartesianProduct(
                    dataOut,
                    mapping,
                    { parentOffOff -> recodeHelper(query, child0, data, dataOut, mapping, parentOffOff) },
                    { parentOffOff -> recodeHelper(query, child1, data, dataOut, mapping, parentOffOff) },
                    optional,
                )
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPLimitID,
            { query, off, data, dataOut, mapping, offPtr ->
                val child = ByteArrayWrapperExt.readInt4(data, off + 4, { "POPLimit.child" })
                val limit = ByteArrayWrapperExt.readInt4(data, off + 8, { "POPLimit.limit" })
                ConverterBinaryEncoder.encodePOPLimit(
                    dataOut,
                    mapping,
                    { parentOffOff -> recodeHelper(query, child, data, dataOut, mapping, parentOffOff) },
                    limit,
                )
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.LOPNOOPID,
            { query, off, data, dataOut, mapping, offPtr ->
                val child = ByteArrayWrapperExt.readInt4(data, off + 4, { "LOPNOOP.child" })
                    recodeHelper(query, child, data, dataOut, mapping, offPtr)
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPSortID,
            { query, off, data, dataOut, mapping, offPtr ->
                val child = ByteArrayWrapperExt.readInt4(data, off + 4, { "POPSort.child" })
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
                ConverterBinaryEncoder.encodePOPSort(
                    dataOut,
                    mapping,
                    { parentOffOff -> recodeHelper(query, child, data, dataOut, mapping, parentOffOff) },
                    spList,
                    pList,
                    sbList,
                    sortOrder,
                )
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPOffsetID,
            { query, off, data, dataOut, mapping, offPtr ->
                val child = ByteArrayWrapperExt.readInt4(data, off + 4, { "POPOffset.child" })
                val offset = ByteArrayWrapperExt.readInt4(data, off + 8, { "POPOffset.offset" })
                ConverterBinaryEncoder.encodePOPOffset(
                    dataOut,
                    mapping,
                    { parentOffOff -> recodeHelper(query, child, data, dataOut, mapping, parentOffOff) },
                    offset,
                )
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPMakeBooleanResultID,
            { query, off, data, dataOut, mapping, offPtr ->
                val child = ByteArrayWrapperExt.readInt4(data, off + 4, { "POPMakeBooleanResult.child" })
                ConverterBinaryEncoder.encodePOPMakeBooleanResult(
                    dataOut,
                    mapping,
                    { parentOffOff -> recodeHelper(query, child, data, dataOut, mapping, parentOffOff) },
                )
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPReducedID,
            { query, off, data, dataOut, mapping, offPtr ->
                val child = ByteArrayWrapperExt.readInt4(data, off + 4, { "POPReduced.child" })
                val size = ByteArrayWrapperExt.readInt4(data, off + 8, { "POPReduced.variables.size" })
                ConverterBinaryEncoder.encodePOPReduced(
                    dataOut,
                    mapping,
                    { parentOffOff -> recodeHelper(query, child, data, dataOut, mapping, parentOffOff) },
                    size,
                )
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPTripleStoreIterator,
            { query, off, data, dataOut, mapping, offPtr ->
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
                ConverterBinaryEncoder.encodePOPTripleStoreIterator(
                    dataOut,
                    mapping,
                    buf1 to buf2,
                    index,
                    child0f,
                    child0c,
                    child0v,
                    child1f,
                    child1c,
                    child1v,
                    child2f,
                    child2c,
                    child2v,
                )
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPBindID,
            { query, off, data, dataOut, mapping, offPtr ->
                val child = ByteArrayWrapperExt.readInt4(data, off + 4, { "POPBind.child" })
                val variablesOut = mutableListOf<String>()
                val len = ByteArrayWrapperExt.readInt4(data, off + 16, { "POPBind.variables.size" })
                for (i in 0 until len) {
                    variablesOut.add(ConverterString.decodeString(data, ByteArrayWrapperExt.readInt4(data, off + 20 + i * 4, { "POPBind.variables[$i]" })))
                }
                val column = ConverterString.decodeString(data, ByteArrayWrapperExt.readInt4(data, off + 12, { "POPBind.column" }))
                val value = ConverterBinaryToAOPBase.decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 8, { "POPBind.value" }))
                val valueOff = ConverterAOPBaseToBinary.encodeAOP(value, dataOut, mapping)
                ConverterBinaryEncoder.encodePOPBind(
                    dataOut,
                    mapping,
                    { parentOffOff -> recodeHelper(query, child, data, dataOut, mapping, parentOffOff) },
                    variablesOut,
                    valueOff,
                    column,
                )
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPFilterID,
            { query, off, data, dataOut, mapping, offPtr ->
                val child = ByteArrayWrapperExt.readInt4(data, off + 4, { "POPFilter.child" })
                val variablesOut = mutableListOf<String>()
                val len = ByteArrayWrapperExt.readInt4(data, off + 12, { "POPFilter.variables.size" })
                for (i in 0 until len) {
                    variablesOut.add(ConverterString.decodeString(data, ByteArrayWrapperExt.readInt4(data, off + 16 + i * 4, { "POPFilter.variables[$i]" })))
                }
                val filter = ConverterBinaryToAOPBase.decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 8, { "POPFilter.filter" }))
                val filterOff = ConverterAOPBaseToBinary.encodeAOP(filter, dataOut, mapping)
                ConverterBinaryEncoder.encodePOPFilter(
                    dataOut,
                    mapping,
                    { parentOffOff -> recodeHelper(query, child, data, dataOut, mapping, parentOffOff) },
                    variablesOut,
                    filterOff,
                )
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPGroupCount0ID,
            { query, off, data, dataOut, mapping, offPtr ->
                val child = ByteArrayWrapperExt.readInt4(data, off + 4, { "POPGroupCount0.child" })
                val binding = ConverterString.decodeString(data, ByteArrayWrapperExt.readInt4(data, off + 8, { "POPGroupCount0.column" }))
                ConverterBinaryEncoder.encodePOPGroupCount0(
                    dataOut,
                    mapping,
                    { parentOffOff -> recodeHelper(query, child, data, dataOut, mapping, parentOffOff) },
                    binding,
                )
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPGroupCount1ID,
            { query, off, data, dataOut, mapping, offPtr ->
                val child = ByteArrayWrapperExt.readInt4(data, off + 4, { "POPGroupCount1.child" })
                val binding = ConverterString.decodeString(data, ByteArrayWrapperExt.readInt4(data, off + 8, { "POPGroupCount1.column" }))
                val name = ConverterString.decodeString(data, ByteArrayWrapperExt.readInt4(data, off + 12, { "POPGroupCount1.by" }))
                ConverterBinaryEncoder.encodePOPGroupCount1(
                    dataOut,
                    mapping,
                    { parentOffOff -> recodeHelper(query, child, data, dataOut, mapping, parentOffOff) },
                    binding,
                    name,
                )
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPGroupSortedID,
            { query, off, data, dataOut, mapping, offPtr ->
                val child = ByteArrayWrapperExt.readInt4(data, off + 4, { "POPGroupSorted.child" })
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
                val bindings = mutableListOf<Pair<String, Int>>()
                for (i in 0 until blen) {
                    val k = ConverterString.decodeString(data, ByteArrayWrapperExt.readInt4(data, o, { "POPGroupSorted.bindings[$i].name" }))
                    o += 4
                    val v = ConverterBinaryToAOPBase.decode(query, data, ByteArrayWrapperExt.readInt4(data, o, { "POPGroupSorted.bindings[$i].value" }))
                    o += 4
                    val vv = ConverterAOPBaseToBinary.encodeAOP(v, dataOut, mapping)
                    bindings.add(k to vv)
                }
                ConverterBinaryEncoder.encodePOPGroupSorted(
                    dataOut,
                    mapping,
                    { parentOffOff -> recodeHelper(query, child, data, dataOut, mapping, parentOffOff) },
                    keyColumnNames.toList(),
                    projectedVariables,
                    bindings,
                )
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPGroupID,
            { query, off, data, dataOut, mapping, offPtr ->
                val child = ByteArrayWrapperExt.readInt4(data, off + 4, { "POPGroup.child" })
                val blen = ByteArrayWrapperExt.readInt4(data, off + 8, { "POPGroup.bindings.size" })
                val klen = ByteArrayWrapperExt.readInt4(data, off + 12, { "POPGroup.keys.size" })
                var o = off + 16
                val keyColumnNames = Array<String>(klen) { "" }
                for (i in 0 until klen) {
                    keyColumnNames[i] = ConverterString.decodeString(data, ByteArrayWrapperExt.readInt4(data, o, { "POPGroup.keys[$i]" }))
                    o += 4
                }
                val bindings = mutableListOf<Pair<String, Int>>()
                for (i in 0 until blen) {
                    val k = ConverterString.decodeString(data, ByteArrayWrapperExt.readInt4(data, o, { "POPGroup.bindings[$i].name" }))
                    o += 4
                    val v = ConverterBinaryToAOPBase.decode(query, data, ByteArrayWrapperExt.readInt4(data, o, { "POPGroup.bindings[$i].value" }))
                    o += 4
                    val vv = ConverterAOPBaseToBinary.encodeAOP(v, dataOut, mapping)
                    bindings.add(k to vv)
                }
                ConverterBinaryEncoder.encodePOPGroup(
                    dataOut,
                    mapping,
                    { parentOffOff -> recodeHelper(query, child, data, dataOut, mapping, parentOffOff) },
                    keyColumnNames.toList(),
                    bindings,
                )
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPGroupWithoutKeyColumnID,
            { query, off, data, dataOut, mapping, offPtr ->
                val child = ByteArrayWrapperExt.readInt4(data, off + 4, { "POPGroupWithoutKeyColumn.child" })
                val plen = ByteArrayWrapperExt.readInt4(data, off + 8, { "POPGroupWithoutKeyColumn.variables.size" })
                val blen = ByteArrayWrapperExt.readInt4(data, off + 12, { "POPGroupWithoutKeyColumn.bindings.size" })
                var o = off + 16
                val projectedVariables = mutableListOf<String>()
                for (i in 0 until plen) {
                    projectedVariables.add(ConverterString.decodeString(data, ByteArrayWrapperExt.readInt4(data, o, { "POPGroupWithoutKeyColumn.variables[$i]" })))
                    o += 4
                }
                val bindings = mutableListOf<Pair<String, Int>>()
                for (i in 0 until blen) {
                    val k = ConverterString.decodeString(data, ByteArrayWrapperExt.readInt4(data, o, { "POPGroupWithoutKeyColumn.bindings[$i].name" }))
                    o += 4
                    val v = ConverterBinaryToAOPBase.decode(query, data, ByteArrayWrapperExt.readInt4(data, o, { "POPGroupWithoutKeyColumn.bindings[$i].value" }))
                    o += 4
                    val vv = ConverterAOPBaseToBinary.encodeAOP(v, dataOut, mapping)
                    bindings.add(k to vv)
                }
                ConverterBinaryEncoder.encodePOPGroupWithoutKeyColumn(
                    dataOut,
                    mapping,
                    { parentOffOff -> recodeHelper(query, child, data, dataOut, mapping, parentOffOff) },
                    projectedVariables,
                    bindings,
                )
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPModifyID,
            { query, off, data, dataOut, mapping, offPtr ->
                val child = ByteArrayWrapperExt.readInt4(data, off + 4, { "POPModify.child" })
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
                ConverterBinaryEncoder.encodePOPModify(
                    dataOut,
                    mapping,
                    { parentOffOff -> recodeHelper(query, child, data, dataOut, mapping, parentOffOff) },
                    modify
                )
            },
        )
    }
}
