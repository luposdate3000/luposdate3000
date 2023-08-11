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

public typealias BinaryToPOPJsonMap = (query: Query, data: ByteArrayWrapper, offset: Int) -> String

public object ConverterBinaryToPOPJson {
    public var operatorMap: Array<BinaryToPOPJsonMap?> = Array(0) { null }
    public fun assignOperatorPhysicalDecode(operatorIDs: IntArray, operator: BinaryToPOPJsonMap) {
        for (operatorID in operatorIDs) {
            assignOperatorPhysicalDecode(operatorID, operator)
        }
    }

    public fun assignOperatorPhysicalDecode(operatorID: Int, operator: BinaryToPOPJsonMap) {
        if (operatorMap.size <= operatorID) {
            var s = operatorMap.size
            if (s < 16) {
                s = 16
            }
            while (s <= operatorID) {
                s = s * 2
            }
            val tmp = Array<BinaryToPOPJsonMap?>(s) { null }
            operatorMap.copyInto(tmp)
            operatorMap = tmp
        }
        operatorMap[operatorID] = operator
    }

    public fun decode(query: Query, data: ByteArrayWrapper): String {
        try {
            var result = mutableMapOf<Int, String>()
            when (ByteArrayWrapperExt.readInt1(data, 4, { "Root.isOPBaseCompound" })) {
                0x1 -> {
                    val childCount = ByteArrayWrapperExt.readInt4(data, 5, { "OPBaseCompound.children.size" })
                    var o = 9
                    for (i in 0 until childCount) {
                        val child = decodeHelper(query, data, ByteArrayWrapperExt.readInt4(data, o, { "OPBaseCompound.children[$i]" }))
                        o += 4
                        val size = ByteArrayWrapperExt.readInt4(data, o, { "OPBaseCompound.columnProjectionOrder[$i].size" })
                        o += 4
                        val list = mutableListOf<String>()
                        for (j in 0 until size) {
                            list.add(ConverterString.decodeString(data, ByteArrayWrapperExt.readInt4(data, o, { "OPBaseCompound.columnProjectionOrder[$i][$j]" })))
                            o += 4
                        }
                        result[-1 - i] = "{\"columns\": $list,\"child\":$child}"
                    }
                }
                0x2 -> {
                    /*there is no query root here*/
                }
                else -> {
                    val tmp = decodeHelper(query, data, ByteArrayWrapperExt.readInt4(data, 5, { "OPBase.children[0]" }))
                    result[-1] = "[$tmp]"
                }
            }
            var off = ByteArrayWrapperExt.readInt4(data, 0, { "OPBase.handler" })
            val len = ByteArrayWrapperExt.readInt4(data, off, { "OPBase.offsetMap.size" })
            var o = off + 4
            for (i in 0 until len) {
                val id = ByteArrayWrapperExt.readInt4(data, o, { "OPBase.offsetMap[$i].id" })
                val offset = ByteArrayWrapperExt.readInt4(data, o + 4, { "OPBase.offsetMap[$i].offset" })
                result[id] = "[${decodeHelper(query, data, offset)}]"
                o += 8
            }
            return "{${result.map { (k, v) -> "\"$k\":$v" }.joinToString()}}"
        } catch (e: Throwable) {
            e.myPrintStackTraceAndThrowAgain(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_factory/src/commonMain/kotlin/lupos/operator/factory/ConverterBinaryToPOPJson.kt:97"/*SOURCE_FILE_END*/)
        }
        TODO("unreachable")
    }

    private fun decodeHelper(query: Query, data: ByteArrayWrapper, off: Int): String {
        val type = ByteArrayWrapperExt.readInt4(data, off, { "operatorID" })
        if (type >= operatorMap.size) {
            TODO("decodeHelper $type -> ${EOperatorIDExt.names[type]}")
        }
        if (type == -1) {
            return "-1"
        } else {
            val decoder = operatorMap[type]
            if (decoder == null) {
                TODO("decodeHelper $type -> ${EOperatorIDExt.names[type]}")
            }
            return decoder(query, data, off)
        }
    }

    init {
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPDistributedSendSingleID,
            { query, data, off ->
                val key = ByteArrayWrapperExt.readInt4(data, off + 4, { "POPDistributedSendSingle.key" })
                val child = decodeHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 8, { "POPDistributedSendSingle.child" }))
                "{\"type\":\"POPDistributedSendSingle\",\"child\":$child,\"key\":$key}"
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPDistributedSendMultiID,
            { query, data, off ->
                val child = decodeHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "POPDistributedSendMulti.child" }))
                val count = ByteArrayWrapperExt.readInt4(data, off + 8, { "POPDistributedSendMulti.count" })
                val name = ByteArrayWrapperExt.readInt4(data, off + 12, { "POPDistributedSendMulti.name" })
                val keys = IntArray(count) { ByteArrayWrapperExt.readInt4(data, off + 16 + 4 * it, { "POPDistributedSendMulti.key[$it]" }) }
                "{\"type\":\"POPDistributedSendMulti\",\"child\":$child,\"keys\":[${keys.map { "{\"key\":$it}" }.joinToString()}]}"
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPDistributedSendSingleCountID,
            { query, data, off ->
                val key = ByteArrayWrapperExt.readInt4(data, off + 4, { "POPDistributedSendSingleCount.key" })
                val child = decodeHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 8, { "POPDistributedSendSingleCount.child" }))
                "{\"type\":\"POPDistributedSendSingleCount\",\"child\":$child,\"key\":$key}"
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPDistributedReceiveMultiID,
            { _, data, off ->
                var keys = mutableListOf<Int>()
                val len = ByteArrayWrapperExt.readInt4(data, off + 4, { "POPDistributedReceiveMulti.size" })
                for (i in 0 until len) {
                    keys.add(ByteArrayWrapperExt.readInt4(data, off + 8 + 4 * i, { "POPDistributedReceiveMulti.key[$i]" }))
                }
                "{\"type\":\"POPDistributedReceiveMulti\",\"keys\":[${keys.map { "{\"key\":$it}" }.joinToString()}]}"
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPDistributedReceiveSingleID,
            { _, data, off ->
                val key = ByteArrayWrapperExt.readInt4(data, off + 4, { "POPDistributedReceiveSingle.key" })
                "{\"type\":\"POPDistributedReceiveSingle\",\"key\":$key}"
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPDistributedReceiveSingleCountID,
            { _, data, off ->
                val key = ByteArrayWrapperExt.readInt4(data, off + 4, { "POPDistributedReceiveSingleCount.key" })
                "{\"type\":\"POPDistributedReceiveSingleCount\",\"key\":$key}"
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPDistributedReceiveMultiCountID,
            { _, data, off ->
                var keys = mutableListOf<Int>()
                val len = ByteArrayWrapperExt.readInt4(data, off + 4, { "POPDistributedReceiveMultiCount.size" })
                for (i in 0 until len) {
                    keys.add(ByteArrayWrapperExt.readInt4(data, off + 8 + 4 * i, { "POPDistributedReceiveMultiCount.key[$i]" }))
                }
                "{\"type\":\"POPDistributedReceiveMultiCount\",\"keys\":[${keys.map { "{\"key\":$it}" }.joinToString()}]}"
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPDistributedReceiveMultiOrderedID,
            { _, data, off ->
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
                "{\"type\":\"POPDistributedReceiveMultiOrdered\",\"keys\":[${keys.map { "{\"key\":$it}" }.joinToString()}]}"
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPGraphOperationID,
            { _, data, off ->
                val silent = ByteArrayWrapperExt.readInt1(data, off + 24, { "POPGraphOperation.silent" }) == 1
                val graph1type = ByteArrayWrapperExt.readInt4(data, off + 4, { "POPGraphOperation.graph1type" })
                val graph1iri = ConverterString.decodeStringNull(data, ByteArrayWrapperExt.readInt4(data, off + 16, { "POPGraphOperation.graph1iri" }))
                val graph2type = ByteArrayWrapperExt.readInt4(data, off + 8, { "POPGraphOperation.graph2type" })
                val graph2iri = ConverterString.decodeStringNull(data, ByteArrayWrapperExt.readInt4(data, off + 20, { "POPGraphOperation.graph2iri" }))
                val action = ByteArrayWrapperExt.readInt4(data, off + 12, { "POPGraphOperation.action" })
                "{\"type\":\"POPGraphOperation\"}"
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPModifyDataID,
            { _, data, off ->
                val d = mutableListOf<Pair<String, DictionaryValueTypeArray>>()
            val type = ByteArrayWrapperExt.readInt4(data, off + 4, { "POPModifyData.type" })
    val l = ByteArrayWrapperExt.readInt4(data, off + 8, { "POPModifyData.data.size" })
                var o = off + 12
                for (i in 0 until l) {
                    val arr = DictionaryValueTypeArray(3)
                    for (j in 0 until 3) {
                        arr[j] = DictionaryValueHelper.fromByteArray(data, o + 4 + j * DictionaryValueHelper.getSize(), { "POPModifyData.data[$i][$j]" })
                    }
                    d.add(ConverterString.decodeString(data, ByteArrayWrapperExt.readInt4(data, o, { "POPModifyData.data[$i].graph" })) to arr)
                    o += DictionaryValueHelper.getSize() * 3 + 4
                }
                "{\"type\":\"POPModifyData\"}"
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPNothingID,
            { _, data, off ->
                val len = ByteArrayWrapperExt.readInt4(data, off + 4, { "POPNothing.size" })
                val list = mutableListOf<String>()
                for (i in 0 until len) {
                    list.add(ConverterString.decodeString(data, ByteArrayWrapperExt.readInt4(data, off + 8 + 4 * i, { "POPNothing.data[$i]" })))
                }
                "{\"type\":\"POPNothing\"}"
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPValuesCountID,
            { _, data, off ->
                val size = ByteArrayWrapperExt.readInt4(data, off + 4, { "POPValueCount.rows.size" })
                "{\"type\":\"POPValuesCount\",\"count\":$size}"
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPValuesID,
            { _, data, off ->
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
                "{\"type\":\"POPValues\"}"
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPEmptyRowID,
            { _, data, off ->
                "{\"type\":\"POPEmptyRow\"}"
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPUnionID,
            { query, data, off ->
                val child0 = decodeHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "POPUnion.child0" }))
                val child1 = decodeHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 8, { "POPUnion.child1" }))
                val l = ByteArrayWrapperExt.readInt4(data, off + 12, { "POPUnion.variables.size" })
                var projectedVariables = mutableListOf<String>()
                for (i in 0 until l) {
                    projectedVariables.add(ConverterString.decodeString(data, ByteArrayWrapperExt.readInt4(data, off + 16 + 4 * i, { "POPUnion.variables[$i]" })))
                }
                "{\"type\":\"POPUnion\",\"childs\":[$child0, $child1]}"
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPMinusID,
            { query, data, off ->
                val child0 = decodeHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "POPMinus.child0" }))
                val child1 = decodeHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 8, { "POPMinus.child1" }))
                val l = ByteArrayWrapperExt.readInt4(data, off + 12, { "POPMinus.variables.size" })
                var projectedVariables = mutableListOf<String>()
                for (i in 0 until l) {
                    projectedVariables.add(ConverterString.decodeString(data, ByteArrayWrapperExt.readInt4(data, off + 16 + 4 * i, { "POPMinus.variables[$i]" })))
                }
                "{\"type\":\"POPMinus\",\"childs\":[$child0, $child1]}"
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.LOPJoinTopologyID,
            { query, data, off ->
                var keys = mutableListOf<Int>()
                var o = off + 4
                val len = ByteArrayWrapperExt.readInt4(data, o, { "LOPJoinTopology.size" })
                o += 4
                val len3 = ByteArrayWrapperExt.readInt4(data, o, { "LOPJoinTopology.size" })
                o += 4
                val projectedVariables = mutableListOf<String>()
                for (i in 0 until len3) {
                    projectedVariables.add(ConverterString.decodeString(data, ByteArrayWrapperExt.readInt4(data, o, { "POPDistributedReceiveMultiOrdered.orderedBy[$i]" })))
                    o += 4
                }
                val projectedVariablesChilds = MutableList(len) { mutableListOf<String>() }
                for (i in 0 until len) {
                    keys.add(ByteArrayWrapperExt.readInt4(data, o, { "LOPJoinTopology.key[$i]" }))
                    o += 4
                    val len4 = ByteArrayWrapperExt.readInt4(data, o, { "LOPJoinTopology.size" })
                    o += 4
                    for (j in 0 until len4) {
                        projectedVariablesChilds[i].add(ConverterString.decodeString(data, ByteArrayWrapperExt.readInt4(data, o, { "POPDistributedReceiveMultiOrdered.orderedBy[$i]" })))
                        o += 4
                    }
                }
                "{\"type\":\"LOPJoinTopology\"," +
                    "\"keys\":[${keys.map { "{\"key\":$it}" }.joinToString()}]," +
                    "\"projectedVariables\":[${projectedVariables.map { "\"$it\"" }.joinToString()}]," +
                    "\"projectedVariablesChilds\":[${projectedVariablesChilds.map {it2 -> it2.map{ "\"$it\"" }.toString()}.joinToString()}]" +
                    "}"
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPJoinMergeOptionalID,
            { query, data, off ->
                val child0 = decodeHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "POPJoinMergeOptional.child0" }))
                val child1 = decodeHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 8, { "POPJoinMergeOptional.child1" }))
                val l = ByteArrayWrapperExt.readInt4(data, off + 12, { "POPJoinMergeOptional.variables.size" })
                var projectedVariables = mutableListOf<String>()
                for (i in 0 until l) {
                    projectedVariables.add(ConverterString.decodeString(data, ByteArrayWrapperExt.readInt4(data, off + 16 + 4 * i, { "POPJoinMergeOptional.variables[$i]" })))
                }
                "{\"type\":\"POPJoinMergeOptional\",\"childs\":[$child0, $child1]}"
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPJoinMergeID,
            { query, data, off ->
                val child0 = decodeHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "POPJoinMerge.child0" }))
                val child1 = decodeHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 8, { "POPJoinMerge.child1" }))
                val l = ByteArrayWrapperExt.readInt4(data, off + 12, { "POPJoinMerge.variables.size" })
                var o = off + 16
                var projectedVariables = mutableListOf<String>()
                for (i in 0 until l) {
                    projectedVariables.add(ConverterString.decodeString(data, ByteArrayWrapperExt.readInt4(data, o, { "POPJoinMerge.variables[$i]" })))
                    o += 4
                }
                val l2 = ByteArrayWrapperExt.readInt4(data, o, { "POPJoinMerge.variables.size" })
                o += 4
                var joinVariableOrder = mutableListOf<String>()
                for (i in 0 until l2) {
                    joinVariableOrder.add(ConverterString.decodeString(data, ByteArrayWrapperExt.readInt4(data, o, { "POPJoinMerge.variables[$i]" })))
                    o += 4
                }
                "{\"type\":\"POPJoinMerge\",\"childs\":[$child0, $child1]}"
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPJoinMergeSingleColumnID,
            { query, data, off ->
                val child0 = decodeHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "POPJoinMergeSingleColumn.child0" }))
                val child1 = decodeHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 8, { "POPJoinMergeSingleColumn.child1" }))
                "{\"type\":\"POPJoinMergeSingleColumn\",\"childs\":[$child0, $child1]}"
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPJoinHashMapID,
            { query, data, off ->
                val child0 = decodeHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "POPJoinHashMap.child0" }))
                val child1 = decodeHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 8, { "POPJoinHashMap.child1" }))
                val l = ByteArrayWrapperExt.readInt4(data, off + 13, { "POPJoinHashMap.variables.size" })
                var projectedVariables = mutableListOf<String>()
                for (i in 0 until l) {
                    projectedVariables.add(ConverterString.decodeString(data, ByteArrayWrapperExt.readInt4(data, off + 17 + 4 * i, { "POPJoinHashMap.variables[$i]" })))
                }
                val optional = ByteArrayWrapperExt.readInt1(data, off + 12, { "POPJoinHashMap.optional" }) == 1
                "{\"type\":\"POPJoinHashMap\",\"childs\":[$child0, $child1],\"optional\":$optional}"
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPJoinCartesianProductID,
            { query, data, off ->
                val child0 = decodeHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "POPJoinCartesianProduct.child0" }))
                val child1 = decodeHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 8, { "POPJoinCartesianProduct.child1" }))
                val optional = ByteArrayWrapperExt.readInt1(data, off + 12, { "POPJoinCartesianProduct.optional" }) == 1
                "{\"type\":\"POPJoinCartesianProduct\",\"childs\":[$child0, $child1],\"optional\":$optional}"
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPLimitID,
            { query, data, off ->
                val child = decodeHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "POPLimit.child" }))
                val limit = ByteArrayWrapperExt.readInt4(data, off + 8, { "POPLimit.limit" })
                "{\"type\":\"POPLimit\",\"child\":$child,\"limit\":$limit}"
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPSortID,
            { query, data, off ->
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
                "{\"type\":\"POPSort\"," +
                    "\"child\":$child," +
                    "\"sortOrder\":$sortOrder," +
                    "\"spList\":[${spList.map { "\"$it\"" }.joinToString()}]," +
                    "\"pList\":[${pList.map { "\"$it\"" }.joinToString()}]," +
                    "\"sbList\":[${sbList.map { "\"$it\"" }.joinToString()}]," +
                    "}"
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPOffsetID,
            { query, data, off ->
                val child = decodeHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "POPOffset.child" }))
                val offset = ByteArrayWrapperExt.readInt4(data, off + 8, { "POPOffset.offset" })
                "{\"type\":\"POPOffset\",\"child\":$child,\"offset\":$offset}"
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPMakeBooleanResultID,
            { query, data, off ->
                val child = decodeHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "POPMakeBooleanResult.child" }))
                "{\"type\":\"POPMakeBooleanResult\",\"child\":$child}"
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPReducedID,
            { query, data, off ->
                val child = decodeHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "POPReduced.child" }))
                val size = ByteArrayWrapperExt.readInt4(data, off + 8, { "POPReduced.variables.size" })
                val variables = mutableListOf<String>()
                for (i in 0 until size) {
                    variables.add(ConverterString.decodeString(data, ByteArrayWrapperExt.readInt4(data, off + 12 + i * 4, { "POPReduced.variables[$i]" })))
                }
                "{\"type\":\"POPReduced\"," +
                    "\"child\":$child," +
                    "\"variables\":[${variables.map { "\"$it\"" }.joinToString()}]," +
                    "}"
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPTripleStoreIterator,
            { query, data, off ->
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
                "{\"type\":\"POPTripleStoreIterator\",\"storehost\":\"$buf1\",\"storekey\":\"$buf2\"}"
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPBindID,
            { query, data, off ->
                val child = decodeHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "POPBind.child" }))
                val variablesOut = mutableListOf<String>()
                val len = ByteArrayWrapperExt.readInt4(data, off + 16, { "POPBind.variables.size" })
                for (i in 0 until len) {
                    variablesOut.add(ConverterString.decodeString(data, ByteArrayWrapperExt.readInt4(data, off + 20 + i * 4, { "POPBind.variables[$i]" })))
                }
                val column = ConverterString.decodeString(data, ByteArrayWrapperExt.readInt4(data, off + 12, { "POPBind.column" }))
                val value = ConverterBinaryToAOPJson.decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 8, { "POPBind.value" }))
                "{\"type\":\"POPBind\",\"child\":$child,\"name\":\"${column}\",\"value\":$value}"
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPFilterID,
            { query, data, off ->
                val child = decodeHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "POPFilter.child" }))
                val variablesOut = mutableListOf<String>()
                val len = ByteArrayWrapperExt.readInt4(data, off + 12, { "POPFilter.variables.size" })
                for (i in 0 until len) {
                    variablesOut.add(ConverterString.decodeString(data, ByteArrayWrapperExt.readInt4(data, off + 16 + i * 4, { "POPFilter.variables[$i]" })))
                }
                val filter = ConverterBinaryToAOPJson.decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 8, { "POPFilter.filter" }))
                "{\"type\":\"POPFilter\",\"child\":$child,\"filter\":$filter}"
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPGroupCount0ID,
            { query, data, off ->
                val child = decodeHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "POPGroupCount0.child" }))
                val binding = ConverterString.decodeString(data, ByteArrayWrapperExt.readInt4(data, off + 8, { "POPGroupCount0.column" }))
                "{\"type\":\"POPGroupCount0\",\"child\":$child}"
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPGroupCount1ID,
            { query, data, off ->
                val child = decodeHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "POPGroupCount1.child" }))
                val binding = ConverterString.decodeString(data, ByteArrayWrapperExt.readInt4(data, off + 8, { "POPGroupCount1.column" }))
                val name = ConverterString.decodeString(data, ByteArrayWrapperExt.readInt4(data, off + 12, { "POPGroupCount1.by" }))
                "{\"type\":\"POPGroupCount1\",\"child\":$child}"
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPGroupSortedID,
            { query, data, off ->
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
                "{\"type\":\"POPGroupSorted\",\"child\":$child}"
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPGroupID,
            { query, data, off ->
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
                "{\"type\":\"POPGroup\",\"child\":$child}"
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPGroupWithoutKeyColumnID,
            { query, data, off ->
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
                "{\"type\":\"POPGroupWithoutKeyColumn\",\"child\":$child}"
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPModifyID,
            { query, data, off ->
                val child = decodeHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "POPModify.child" }))
            val targetName=ConverterString.decodeString(data, ByteArrayWrapperExt.readInt4(data, off + 8, { "POPModify.targetName" }))
    val steph = if (DictionaryValueHelper.getSize() > 4) DictionaryValueHelper.getSize() else 4
                val step = 9 + 3 * steph
                val modify = Array<Pair<LOPTriple, EModifyType>>(ByteArrayWrapperExt.readInt4(data, off + 12, { "POPModify.modify.size" })) { it ->
                    val o = off + 16 + it * step
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
                "{\"type\":\"POPModify\",\"child\":$child}"
            },
        )
    }
}
