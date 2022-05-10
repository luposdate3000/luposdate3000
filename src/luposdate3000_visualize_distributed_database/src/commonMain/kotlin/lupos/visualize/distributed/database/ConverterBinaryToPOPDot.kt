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
import lupos.operator.physical.multiinput.EvalJoinCartesianProduct
import lupos.operator.physical.multiinput.EvalJoinHashMap
import lupos.operator.physical.multiinput.EvalJoinMerge
import lupos.operator.physical.multiinput.EvalJoinMergeOptional
import lupos.operator.physical.multiinput.EvalJoinMergeSingleColumn
import lupos.operator.physical.multiinput.EvalMinus
import lupos.operator.physical.multiinput.EvalUnion
import lupos.operator.physical.noinput.EvalGraphOperation
import lupos.operator.physical.noinput.EvalModifyData
import lupos.operator.physical.noinput.EvalNothing
import lupos.operator.physical.noinput.EvalValues
import lupos.operator.physical.singleinput.EvalBind
import lupos.operator.physical.singleinput.EvalFilter
import lupos.operator.physical.singleinput.EvalGroup
import lupos.operator.physical.singleinput.EvalGroupCount0
import lupos.operator.physical.singleinput.EvalGroupCount1
import lupos.operator.physical.singleinput.EvalGroupSorted
import lupos.operator.physical.singleinput.EvalGroupWithoutKeyColumn
import lupos.operator.physical.singleinput.EvalMakeBooleanResult
import lupos.operator.physical.singleinput.EvalModify
import lupos.operator.physical.singleinput.EvalSort
import lupos.operator.physical.singleinput.modifiers.EvalLimit
import lupos.operator.physical.singleinput.modifiers.EvalOffset
import lupos.operator.physical.singleinput.modifiers.EvalReduced
import lupos.shared.DictionaryValueHelper
import lupos.shared.DictionaryValueType
import lupos.shared.DictionaryValueTypeArray
import lupos.shared.EModifyType
import lupos.shared.EOperatorIDExt
import lupos.shared.dynamicArray.ByteArrayWrapper
import lupos.shared.inline.dynamicArray.ByteArrayWrapperExt
import lupos.shared.myPrintStackTraceAndThrowAgain
import lupos.shared.operator.iterator.IteratorBundle
import lupos.shared.operator.iterator.IteratorBundleRoot
import lupos.triple_store_manager.EvalTripleStoreIterator
import lupos.triple_store_manager.POPTripleStoreIterator

public typealias BinaryToPOPDotMap = (query: Query, data: ByteArrayWrapper, offset: Int, Array<Any?>, graph: DotGraph, nextID: () -> Int) -> String

public object ConverterBinaryToPOPDot {
    public var defaultOperatorMap: Array<Any?> = Array(0) { null }
    public fun assignOperatorPhysicalDecode(operatorIDs: IntArray, operator: BinaryToPOPDotMap) {
        for (operatorID in operatorIDs) {
            assignOperatorPhysicalDecode(operatorID, operator)
        }
    }

    public fun assignOperatorPhysicalDecode(operatorID: Int, operator: BinaryToPOPDotMap) {
        if (defaultOperatorMap.size <= operatorID) {
            var s = defaultOperatorMap.size
            if (s < 16) {
                s = 16
            }
            while (s <= operatorID) {
                s = s * 2
            }
            val tmp = Array<Any?>(s) { null }
            defaultOperatorMap.copyInto(tmp)
            defaultOperatorMap = tmp
        }
        defaultOperatorMap[operatorID] = operator
    }

    public fun decode(query: Query, data: ByteArrayWrapper, dataID: Int, graph: DotGraph, nextID: () -> Int, operatorMap: Array<Any?> = defaultOperatorMap) {
        try {
            if (dataID == -1) {
                when (ByteArrayWrapperExt.readInt1(data, 4, { "Root.isOPBaseCompound" })) {
                    0x1 -> {
                        val childCount = ByteArrayWrapperExt.readInt4(data, 5, { "OPBaseCompound.children.size" })
                        var o = 9
                        for (i in 0 until childCount) {
                            decodeHelper(query, data, ByteArrayWrapperExt.readInt4(data, o, { "OPBaseCompound.children[$i]" }), operatorMap, graph, nextID)
                            o += 4
                            val size = ByteArrayWrapperExt.readInt4(data, o, { "OPBaseCompound.columnProjectionOrder[$i].size" })
                            o += 4
                            for (j in 0 until size) {
                                o += 4
                            }
                        }
                        return
                    }
                    0x0 -> {
                        decodeHelper(query, data, ByteArrayWrapperExt.readInt4(data, 5, { "OPBase.children[0]" }), operatorMap, graph, nextID)
                        return
                    }
                }
            } else {
                var off = ByteArrayWrapperExt.readInt4(data, 0, { "OPBase.handler" })
                val len = ByteArrayWrapperExt.readInt4(data, off, { "OPBase.offsetMap.size" })
                var o = off + 4
                for (i in 0 until len) {
                    val id = ByteArrayWrapperExt.readInt4(data, o, { "OPBase.offsetMap[$i].id" })
                    if (id == dataID) {
                        val offset = ByteArrayWrapperExt.readInt4(data, o + 4, { "OPBase.offsetMap[$i].offset" })
                        decodeHelper(query, data, offset, operatorMap, graph, nextID)
                        return
                    }
                    o += 8
                }
            }
            TODO("dataID $dataID not found")
        } catch (e: Throwable) {
            e.myPrintStackTraceAndThrowAgain(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_factory/src/commonMain/kotlin/lupos/operator/factory/ConverterBinaryToIteratorBundle.kt:127"/*SOURCE_FILE_END*/)
        }
        TODO("unreachable")
    }

    public fun decodeHelper(query: Query, data: ByteArrayWrapper, off: Int, operatorMap: Array<Any?>, graph: DotGraph, nextID: () -> Int): String {
        val type = ByteArrayWrapperExt.readInt4(data, off, { "operatorID" })
        if (type >= operatorMap.size) {
            TODO("decodeHelper $type -> ${EOperatorIDExt.names[type]}")
        }
        val decoder = operatorMap[type]
        if (decoder == null) {
            TODO("decodeHelper $type -> ${EOperatorIDExt.names[type]}")
        }
        decoder as BinaryToPOPDotMap
        return decoder(query, data, off, operatorMap)
    }

    init {
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPGraphOperationID,
            { query, data, off, operatorMap, graph, nextID ->
                val silent = ByteArrayWrapperExt.readInt1(data, off + 24, { "POPGraphOperation.silent" }) == 1
                val graph1type = ByteArrayWrapperExt.readInt4(data, off + 4, { "POPGraphOperation.graph1type" })
                val graph1iri = ConverterString.decodeStringNull(data, ByteArrayWrapperExt.readInt4(data, off + 16, { "POPGraphOperation.graph1iri" }))
                val graph2type = ByteArrayWrapperExt.readInt4(data, off + 8, { "POPGraphOperation.graph2type" })
                val graph2iri = ConverterString.decodeStringNull(data, ByteArrayWrapperExt.readInt4(data, off + 20, { "POPGraphOperation.graph2iri" }))
                val action = ByteArrayWrapperExt.readInt4(data, off + 12, { "POPGraphOperation.action" })
                graph.addNode(DotNode("GraphOperation#${
                    nextID()($action)"));
                },
                )
                    assignOperatorPhysicalDecode (
                    EOperatorIDExt.POPModifyDataID,
                    { query, data, off, operatorMap, graph, nextID ->
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
                        graph.addNode(DotNode("ModifyData#${
                            nextID()"));
                        },
                        )
                            assignOperatorPhysicalDecode (
                            EOperatorIDExt.POPNothingID,
                            { query, data, off, operatorMap, graph, nextID ->
                                val len = ByteArrayWrapperExt.readInt4(data, off + 4, { "POPNothing.size" })
                                val list = mutableListOf<String>()
                                for (i in 0 until len) {
                                    list.add(ConverterString.decodeString(data, ByteArrayWrapperExt.readInt4(data, off + 8 + 4 * i, { "POPNothing.data[$i]" })))
                                }
                                graph.addNode(DotNode("Nothing#${
                                    nextID()"));
                                },
                                )
                                    assignOperatorPhysicalDecode (
                                    EOperatorIDExt.POPValuesCountID,
                                    { query, data, off, operatorMap, graph, nextID ->
                                        graph.addNode(
                                            DotNode("ValuesCount#${
                                                nextID()"));
                                            },
                                            )
                                                assignOperatorPhysicalDecode (
                                                EOperatorIDExt.POPValuesID,
                                            { query, data, off, operatorMap, graph, nextID ->
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
                                                graph.addNode(DotNode("Values#${nextID()}"));
                                            },
                                        )
                                        assignOperatorPhysicalDecode(
                                            EOperatorIDExt.POPEmptyRowID,
                                            { query, data, off, operatorMap, graph, nextID ->
                                                graph.addNode(DotNode("EmptyRow#${nextID()}"));
                                            },
                                        )
                                        assignOperatorPhysicalDecode(
                                            EOperatorIDExt.POPUnionID,
                                            { query, data, off, operatorMap, graph, nextID ->
                                                val child0 = decodeHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "POPUnion.child0" }), operatorMap, graph, nextID)
                                                val child1 = decodeHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 8, { "POPUnion.child1" }), operatorMap, graph, nextID)
                                                val l = ByteArrayWrapperExt.readInt4(data, off + 12, { "POPUnion.variables.size" })
                                                var projectedVariables = mutableListOf<String>()
                                                for (i in 0 until l) {
                                                    projectedVariables.add(ConverterString.decodeString(data, ByteArrayWrapperExt.readInt4(data, off + 16 + 4 * i, { "POPUnion.variables[$i]" })))
                                                }
                                                val res = graph.addNode(DotNode("Union#${nextID()}"));
                                                graph.addEdge(child0, res)
                                                graph.addEdge(child1, res)
                                                res
                                            },
                                        )
                                        assignOperatorPhysicalDecode(
                                            EOperatorIDExt.POPMinusID,
                                            { query, data, off, operatorMap, graph, nextID ->
                                                val child0 = decodeHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "POPMinus.child0" }), operatorMap, graph, nextID)
                                                val child1 = decodeHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 8, { "POPMinus.child1" }), operatorMap, graph, nextID)
                                                val l = ByteArrayWrapperExt.readInt4(data, off + 12, { "POPMinus.variables.size" })
                                                var projectedVariables = mutableListOf<String>()
                                                for (i in 0 until l) {
                                                    projectedVariables.add(ConverterString.decodeString(data, ByteArrayWrapperExt.readInt4(data, off + 16 + 4 * i, { "POPMinus.variables[$i]" })))
                                                }
                                                val res = graph.addNode(DotNode("Minus#${nextID()}"));
                                                graph.addEdge(child0, res)
                                                graph.addEdge(child1, res)
                                                res
                                            },
                                        )
                                        assignOperatorPhysicalDecode(
                                            EOperatorIDExt.POPJoinMergeOptionalID,
                                            { query, data, off, operatorMap, graph, nextID ->
                                                val child0 = decodeHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "POPJoinMergeOptional.child0" }), operatorMap, graph, nextID)
                                                val child1 = decodeHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 8, { "POPJoinMergeOptional.child1" }), operatorMap, graph, nextID)
                                                val l = ByteArrayWrapperExt.readInt4(data, off + 12, { "POPJoinMergeOptional.variables.size" })
                                                var projectedVariables = mutableListOf<String>()
                                                for (i in 0 until l) {
                                                    projectedVariables.add(ConverterString.decodeString(data, ByteArrayWrapperExt.readInt4(data, off + 16 + 4 * i, { "POPJoinMergeOptional.variables[$i]" })))
                                                }
                                                val res = graph.addNode(DotNode("JoinMergeOptional#${nextID()}"));
                                                graph.addEdge(child0, res)
                                                graph.addEdge(child1, res)
                                                res
                                            },
                                        )
                                        assignOperatorPhysicalDecode(
                                            EOperatorIDExt.POPJoinMergeID,
                                            { query, data, off, operatorMap, graph, nextID ->
                                                val child0 = decodeHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "POPJoinMerge.child0" }), operatorMap, graph, nextID)
                                                val child1 = decodeHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 8, { "POPJoinMerge.child1" }), operatorMap, graph, nextID)
                                                val l = ByteArrayWrapperExt.readInt4(data, off + 12, { "POPJoinMerge.variables.size" })
                                                var projectedVariables = mutableListOf<String>()
                                                for (i in 0 until l) {
                                                    projectedVariables.add(ConverterString.decodeString(data, ByteArrayWrapperExt.readInt4(data, off + 16 + 4 * i, { "POPJoinMerge.variables[$i]" })))
                                                }
                                                val res = graph.addNode(DotNode("JoinMerge#${nextID()}"));
                                                graph.addEdge(child0, res)
                                                graph.addEdge(child1, res)
                                                res
                                            },
                                        )
                                        assignOperatorPhysicalDecode(
                                            EOperatorIDExt.POPJoinMergeSingleColumnID,
                                            { query, data, off, operatorMap, graph, nextID ->
                                                val child0 = decodeHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "POPJoinMergeSingleColumn.child0" }), operatorMap, graph, nextID)
                                                val child1 = decodeHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 8, { "POPJoinMergeSingleColumn.child1" }), operatorMap, graph, nextID)
                                                val res = graph.addNode(DotNode("JoinMergeSingleColumn#${nextID()}"));
                                                graph.addEdge(child0, res)
                                                graph.addEdge(child1, res)
                                                res
                                            },
                                        )
                                        assignOperatorPhysicalDecode(
                                            EOperatorIDExt.POPJoinHashMapID,
                                            { query, data, off, operatorMap, graph, nextID ->
                                                val child0 = decodeHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "POPJoinHashMap.child0" }), operatorMap, graph, nextID)
                                                val child1 = decodeHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 8, { "POPJoinHashMap.child1" }), operatorMap, graph, nextID)
                                                val l = ByteArrayWrapperExt.readInt4(data, off + 13, { "POPJoinHashMap.variables.size" })
                                                var projectedVariables = mutableListOf<String>()
                                                for (i in 0 until l) {
                                                    projectedVariables.add(ConverterString.decodeString(data, ByteArrayWrapperExt.readInt4(data, off + 17 + 4 * i, { "POPJoinHashMap.variables[$i]" })))
                                                }
                                                val optional = ByteArrayWrapperExt.readInt1(data, off + 12, { "POPJoinHashMap.optional" }) == 1
                                                val res = graph.addNode(DotNode("JoinMergeHashMap#${nextID()}"));
                                                graph.addEdge(child0, res)
                                                graph.addEdge(child1, res)
                                                res
                                            },
                                        )
                                        assignOperatorPhysicalDecode(
                                            EOperatorIDExt.POPJoinCartesianProductID,
                                            { query, data, off, operatorMap, graph, nextID ->
                                                val child0 = decodeHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "POPJoinCartesianProduct.child0" }), operatorMap, graph, nextID)
                                                val child1 = decodeHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 8, { "POPJoinCartesianProduct.child1" }), operatorMap, graph, nextID)
                                                val optional = ByteArrayWrapperExt.readInt1(data, off + 12, { "POPJoinCartesianProduct.optional" }) == 1
                                                val res = graph.addNode(DotNode("JoinMergeCartesianProduct#${nextID()}"));
                                                graph.addEdge(child0, res)
                                                graph.addEdge(child1, res)
                                                res
                                            },
                                        )
                                        assignOperatorPhysicalDecode(
                                            EOperatorIDExt.POPLimitID,
                                            { query, data, off, operatorMap, graph, nextID ->
                                                val child = decodeHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "POPLimit.child" }), operatorMap, graph, nextID)
                                                val limit = ByteArrayWrapperExt.readInt4(data, off + 8, { "POPLimit.limit" })
                                                val res = graph.addNode(DotNode("Limit#${nextID()}($limit)"));
                                                graph.addEdge(child, res)
                                                res
                                            },
                                        )
                                        assignOperatorPhysicalDecode(
                                            EOperatorIDExt.POPSortID,
                                            { query, data, off, operatorMap, graph, nextID ->
                                                val child = decodeHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "POPSort.child" }), operatorMap, graph, nextID)
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
                                                val res = graph.addNode(DotNode("Sort#${nextID()}($sortOrder)"));
                                                graph.addEdge(child, res)
                                                res
                                            },
                                        )
                                        assignOperatorPhysicalDecode(
                                            EOperatorIDExt.POPOffsetID,
                                            { query, data, off, operatorMap, graph, nextID ->
                                                val child = decodeHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "POPOffset.child" }), operatorMap, graph, nextID)
                                                val offset = ByteArrayWrapperExt.readInt4(data, off + 8, { "POPOffset.offset" })
                                                val res = graph.addNode(DotNode("Offset#${nextID()}($offset)"));
                                                graph.addEdge(child, res)
                                                res
                                            },
                                        )
                                        assignOperatorPhysicalDecode(
                                            EOperatorIDExt.POPMakeBooleanResultID,
                                            { query, data, off, operatorMap, graph, nextID ->
                                                val child = decodeHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "POPMakeBooleanResult.child" }), operatorMap, graph, nextID)
                                                val res = graph.addNode(DotNode("MakeBooleanResult#${nextID()}"));
                                                graph.addEdge(child, res)
                                                res
                                            },
                                        )
                                        assignOperatorPhysicalDecode(
                                            EOperatorIDExt.POPReducedID,
                                            { query, data, off, operatorMap, graph, nextID ->
                                                val child = decodeHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "POPReduced.child" }), operatorMap, graph, nextID)
                                                val size = ByteArrayWrapperExt.readInt4(data, off + 8, { "POPReduced.variables.size" })
                                                val res = graph.addNode(DotNode("Reduced#${nextID()}"));
                                                graph.addEdge(child, res)
                                                res
                                            },
                                        )
                                        assignOperatorPhysicalDecode(
                                            EOperatorIDExt.POPTripleStoreIterator,
                                            { query, data, off, operatorMap, graph, nextID ->
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
                                                graph.addNode(DotNode("TripleStore#${nextID()}"));
                                            },
                                        )
                                        assignOperatorPhysicalDecode(
                                            EOperatorIDExt.POPBindID,
                                            { query, data, off, operatorMap, graph, nextID ->
                                                val child = decodeHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "POPBind.child" }), operatorMap, graph, nextID)
                                                val variablesOut = mutableListOf<String>()
                                                val len = ByteArrayWrapperExt.readInt4(data, off + 16, { "POPBind.variables.size" })
                                                for (i in 0 until len) {
                                                    variablesOut.add(ConverterString.decodeString(data, ByteArrayWrapperExt.readInt4(data, off + 20 + i * 4, { "POPBind.variables[$i]" })))
                                                }
                                                val column = ConverterString.decodeString(data, ByteArrayWrapperExt.readInt4(data, off + 12, { "POPBind.column" }))
                                                val value = ConverterBinaryToAOPBase.decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 8, { "POPBind.value" }))
                                                val res = graph.addNode(DotNode("Bind#${nextID()}"));
                                                graph.addEdge(child, res)
                                                res
                                            },
                                        )
                                        assignOperatorPhysicalDecode(
                                            EOperatorIDExt.POPFilterID,
                                            { query, data, off, operatorMap, graph, nextID ->
                                                val child = decodeHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "POPFilter.child" }), operatorMap, graph, nextID)
                                                val variablesOut = mutableListOf<String>()
                                                val len = ByteArrayWrapperExt.readInt4(data, off + 12, { "POPFilter.variables.size" })
                                                for (i in 0 until len) {
                                                    variablesOut.add(ConverterString.decodeString(data, ByteArrayWrapperExt.readInt4(data, off + 16 + i * 4, { "POPFilter.variables[$i]" })))
                                                }
                                                val filter = ConverterBinaryToAOPBase.decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 8, { "POPFilter.filter" }))
                                                val res = graph.addNode(DotNode("Filter#${nextID()}"));
                                                graph.addEdge(child, res)
                                                res
                                            },
                                        )
                                        assignOperatorPhysicalDecode(
                                            EOperatorIDExt.POPGroupCount0ID,
                                            { query, data, off, operatorMap, graph, nextID ->
                                                val child = decodeHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "POPGroupCount0.child" }), operatorMap, graph, nextID)
                                                val binding = ConverterString.decodeString(data, ByteArrayWrapperExt.readInt4(data, off + 8, { "POPGroupCount0.column" }))
                                                val res = graph.addNode(DotNode("GroupCount0#${nextID()}"));
                                                graph.addEdge(child, res)
                                                res
                                            },
                                        )
                                        assignOperatorPhysicalDecode(
                                            EOperatorIDExt.POPGroupCount1ID,
                                            { query, data, off, operatorMap, graph, nextID ->
                                                val child = decodeHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "POPGroupCount1.child" }), operatorMap, graph, nextID)
                                                val binding = ConverterString.decodeString(data, ByteArrayWrapperExt.readInt4(data, off + 8, { "POPGroupCount1.column" }))
                                                val name = ConverterString.decodeString(data, ByteArrayWrapperExt.readInt4(data, off + 12, { "POPGroupCount1.by" }))
                                                val res = graph.addNode(DotNode("GroupCount1#${nextID()}"));
                                                graph.addEdge(child, res)
                                                res
                                            },
                                        )
                                        assignOperatorPhysicalDecode(
                                            EOperatorIDExt.POPGroupSortedID,
                                            { query, data, off, operatorMap, graph, nextID ->
                                                val child = decodeHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "POPGroupSorted.child" }), operatorMap, graph, nextID)
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
                                                val res = graph.addNode(DotNode("GroupSorted#${nextID()}"));
                                                graph.addEdge(child, res)
                                                res
                                            },
                                        )
                                        assignOperatorPhysicalDecode(
                                            EOperatorIDExt.POPGroupID,
                                            { query, data, off, operatorMap, graph, nextID ->
                                                val child = decodeHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "POPGroup.child" }), operatorMap, graph, nextID)
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
                                                val res = graph.addNode(DotNode("Group#${nextID()}"));
                                                graph.addEdge(child, res)
                                                res
                                            },
                                        )
                                        assignOperatorPhysicalDecode(
                                            EOperatorIDExt.POPGroupWithoutKeyColumnID,
                                            { query, data, off, operatorMap, graph, nextID ->
                                                val child = decodeHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "POPGroupWithoutKeyColumn.child" }), operatorMap, graph, nextID)
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
                                                val res = graph.addNode(DotNode("GroupWithoutKeyColumn#${nextID()}"));
                                                graph.addEdge(child, res)
                                                res
                                            },
                                        )
                                        assignOperatorPhysicalDecode(
                                            EOperatorIDExt.POPModifyID,
                                            { query, data, off, operatorMap, graph, nextID ->
                                                val child = decodeHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "POPModify.child" }), operatorMap, graph, nextID)
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
                                                val res = graph.addNode(DotNode("Modify#${nextID()}"));
                                                graph.addEdge(child, res)
                                                res
                                            },
                                        )
                                    }
                            }