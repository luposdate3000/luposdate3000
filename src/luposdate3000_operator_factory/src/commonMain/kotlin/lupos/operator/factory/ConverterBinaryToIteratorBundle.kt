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
import lupos.shared.operator.iterator.IteratorBundle
import lupos.shared.operator.iterator.IteratorBundleRoot
import lupos.triple_store_manager.EvalTripleStoreIterator
import lupos.triple_store_manager.POPTripleStoreIterator
public typealias BinaryToOPBaseMap = (query: Query, data: ByteArrayWrapper, offset: Int) -> IteratorBundle
public object ConverterBinaryToIteratorBundle {
    public var operatorMap: Array<BinaryToOPBaseMap?> = Array(0) { null }
    public fun assignOperatorPhysicalDecode(operatorIDs: IntArray, operator: BinaryToOPBaseMap) {
        for (operatorID in operatorIDs) {
            assignOperatorPhysicalDecode(operatorID, operator)
        }
    }
    public fun assignOperatorPhysicalDecode(operatorID: Int, operator: BinaryToOPBaseMap) {
        if (operatorMap.size <= operatorID) {
            var s = operatorMap.size
            if (s < 16) {
                s = 16
            }
            while (s <= operatorID) {
                s = s * 2
            }
            val tmp = Array<BinaryToOPBaseMap?>(s) { null }
            operatorMap.copyInto(tmp)
            operatorMap = tmp
        }
        operatorMap[operatorID] = operator
    }
    public fun decode(query: Query, data: ByteArrayWrapper): Map<Int, IteratorBundleRoot> {
        try {
            var result = mutableMapOf<Int, IteratorBundleRoot>()
            when (ByteArrayWrapperExt.readInt1(data, 4, { "Root.isOPBaseCompound" })) {
                0x1 -> {
                    val childCount = ByteArrayWrapperExt.readInt4(data, 5, { "OPBaseCompound.children.size" })
                    var o = 9
                    val res = mutableListOf<Pair<List<String>, IteratorBundle>>()
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
                        res.add(list to child)
                    }
                    result[-1] = IteratorBundleRoot(query, res.toTypedArray())
                }
                0x2 -> {
/*there is no query root here*/
                }
                else -> {
                    val tmp = decodeHelper(query, data, ByteArrayWrapperExt.readInt4(data, 5, { "OPBase.children[0]" }))
                    result[-1] = IteratorBundleRoot(query, arrayOf(listOf<String>() to tmp))
                }
            }
            var off = ByteArrayWrapperExt.readInt4(data, 0, { "OPBase.handler" })
            val len = ByteArrayWrapperExt.readInt4(data, off, { "OPBase.offsetMap.size" })
            var o = off + 4
            for (i in 0 until len) {
                val id = ByteArrayWrapperExt.readInt4(data, o, { "OPBase.offsetMap[$i].id" })
                val offset = ByteArrayWrapperExt.readInt4(data, o + 4, { "OPBase.offsetMap[$i].offset" })
                result [id] = IteratorBundleRoot(query, arrayOf(listOf<String>() to decodeHelper(query, data, offset)))
                o += 8
            }
            return result
        } catch (e: Throwable) {
            e.printStackTrace()
            throw e
        }
    }
    private fun decodeHelper(query: Query, data: ByteArrayWrapper, off: Int): IteratorBundle {
        val type = ByteArrayWrapperExt.readInt4(data, off, { "operatorID" })
        if (type >= operatorMap.size) {
            TODO("decodeHelper $type -> ${EOperatorIDExt.names[type]}")
        }
        val decoder = operatorMap[type]
        if (decoder == null) {
            TODO("decodeHelper $type -> ${EOperatorIDExt.names[type]}")
        }
        return decoder(query, data, off)
    }
    public fun initDecode() {
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPGraphOperationID,
            { query, data, off ->
                val silent = ByteArrayWrapperExt.readInt1(data, off + 24, { "POPGraphOperation.silent" }) == 1
                val graph1type = ByteArrayWrapperExt.readInt4(data, off + 4, { "POPGraphOperation.graph1type" })
                val graph1iri = ConverterString.decodeStringNull(data, ByteArrayWrapperExt.readInt4(data, off + 16, { "POPGraphOperation.graph1iri" }))
                val graph2type = ByteArrayWrapperExt.readInt4(data, off + 8, { "POPGraphOperation.graph2type" })
                val graph2iri = ConverterString.decodeStringNull(data, ByteArrayWrapperExt.readInt4(data, off + 20, { "POPGraphOperation.graph2iri" }))
                val action = ByteArrayWrapperExt.readInt4(data, off + 12, { "POPGraphOperation.action" })
                EvalGraphOperation(silent, graph1type, graph1iri, graph2type, graph2iri, action, query,)
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPModifyDataID,
            { query, data, off ->
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
                EvalModifyData(d, query)
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPNothingID,
            { query, data, off ->
                val len = ByteArrayWrapperExt.readInt4(data, off + 4, { "POPNothing.size" })
                val list = mutableListOf<String>()
                for (i in 0 until len) {
                    list.add(ConverterString.decodeString(data, ByteArrayWrapperExt.readInt4(data, off + 8 + 4 * i, { "POPNothing.data[$i]" })))
                }
                EvalNothing(list)
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPValuesCountID,
            { query, data, off ->
                IteratorBundle(ByteArrayWrapperExt.readInt4(data, off + 4, { "POPValueCount.rows.size" }))
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPValuesID,
            { query, data, off ->
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
                EvalValues(dd)
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPEmptyRowID,
            { query, data, off ->
                IteratorBundle(1)
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
                EvalUnion(child0, child1, projectedVariables)
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
                EvalMinus(child0, child1, projectedVariables)
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
                EvalJoinMergeOptional(arrayOf(child0, child1), projectedVariables)
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPJoinMergeID,
            { query, data, off ->
                val child0 = decodeHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "POPJoinMerge.child0" }))
                val child1 = decodeHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 8, { "POPJoinMerge.child1" }))
                val l = ByteArrayWrapperExt.readInt4(data, off + 12, { "POPJoinMerge.variables.size" })
                var projectedVariables = mutableListOf<String>()
                for (i in 0 until l) {
                    projectedVariables.add(ConverterString.decodeString(data, ByteArrayWrapperExt.readInt4(data, off + 16 + 4 * i, { "POPJoinMerge.variables[$i]" })))
                }
                EvalJoinMerge(child0, child1, projectedVariables)
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPJoinMergeSingleColumnID,
            { query, data, off ->
                val child0 = decodeHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "POPJoinMergeSingleColumn.child0" }))
                val child1 = decodeHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 8, { "POPJoinMergeSingleColumn.child1" }))
                EvalJoinMergeSingleColumn(child0, child1)
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
                EvalJoinHashMap(child0, child1, optional, projectedVariables)
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPJoinCartesianProductID,
            { query, data, off ->
                val child0 = decodeHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "POPJoinCartesianProduct.child0" }))
                val child1 = decodeHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 8, { "POPJoinCartesianProduct.child1" }))
                val optional = ByteArrayWrapperExt.readInt1(data, off + 12, { "POPJoinCartesianProduct.optional" }) == 1
                EvalJoinCartesianProduct(child0, child1, optional)
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPLimitID,
            { query, data, off ->
                val child = decodeHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "POPLimit.child" }))
                val limit = ByteArrayWrapperExt.readInt4(data, off + 8, { "POPLimit.limit" })
                EvalLimit(child, limit)
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
                EvalSort(child, spList, query, sbList.toTypedArray(), pList, sortOrder)
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPOffsetID,
            { query, data, off ->
                val child = decodeHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "POPOffset.child" }))
                val offset = ByteArrayWrapperExt.readInt4(data, off + 8, { "POPOffset.offset" })
                EvalOffset(child, offset)
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPMakeBooleanResultID,
            { query, data, off ->
                val child = decodeHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "POPMakeBooleanResult.child" }))
                EvalMakeBooleanResult(child)
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPReducedID,
            { query, data, off ->
                val child = decodeHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "POPReduced.child" }))
                val size = ByteArrayWrapperExt.readInt4(data, off + 8, { "POPReduced.variables.size" })
                EvalReduced(child, size)
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
                EvalTripleStoreIterator(buf1 to buf2, query, index, arrayOf(child0f to (child0c to child0v), child1f to (child1c to child1v), child2f to (child2c to child2v)))
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
                val value = ConverterBinaryToAOPBase.decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 8, { "POPBind.value" }))
                EvalBind(child, variablesOut, column, value)
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
                val filter = ConverterBinaryToAOPBase.decode(query, data, ByteArrayWrapperExt.readInt4(data, off + 8, { "POPFilter.filter" }))
                EvalFilter(child, variablesOut, filter)
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPGroupCount0ID,
            { query, data, off ->
                val child = decodeHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "POPGroupCount0.child" }))
                val binding = ConverterString.decodeString(data, ByteArrayWrapperExt.readInt4(data, off + 8, { "POPGroupCount0.column" }))
                EvalGroupCount0(child, binding, query.getDictionary())
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPGroupCount1ID,
            { query, data, off ->
                val child = decodeHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "POPGroupCount1.child" }))
                val binding = ConverterString.decodeString(data, ByteArrayWrapperExt.readInt4(data, off + 8, { "POPGroupCount1.column" }))
                val name = ConverterString.decodeString(data, ByteArrayWrapperExt.readInt4(data, off + 12, { "POPGroupCount1.by" }))
                EvalGroupCount1(child, binding, name, query.getDictionary())
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
                EvalGroupSorted(child, bindings, projectedVariables, keyColumnNames)
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
                EvalGroup(child, bindings, keyColumnNames)
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
                EvalGroupWithoutKeyColumn(child, bindings, projectedVariables)
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPModifyID,
            { query, data, off ->
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
                EvalModify(child, query, modify)
            },
        )
    }
}
