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
import lupos.operator.base.Query
import lupos.operator.logical.noinput.LOPTriple
import lupos.operator.physical.multiinput.EvalJoinCartesianProduct
import lupos.operator.physical.multiinput.EvalJoinHashMap
import lupos.operator.physical.multiinput.EvalJoinMerge
import lupos.operator.physical.multiinput.EvalJoinMergeOptional
import lupos.operator.physical.multiinput.EvalJoinMergeSingleColumn
import lupos.operator.physical.multiinput.EvalMinus
import lupos.operator.physical.multiinput.EvalUnion
import lupos.operator.physical.multiinput.POPJoinCartesianProduct
import lupos.operator.physical.multiinput.POPJoinHashMap
import lupos.operator.physical.multiinput.POPJoinMerge
import lupos.operator.physical.multiinput.POPJoinMergeOptional
import lupos.operator.physical.multiinput.POPJoinMergeSingleColumn
import lupos.operator.physical.multiinput.POPMinus
import lupos.operator.physical.multiinput.POPUnion
import lupos.operator.physical.noinput.EvalGraphOperation
import lupos.operator.physical.noinput.EvalModifyData
import lupos.operator.physical.noinput.EvalNothing
import lupos.operator.physical.noinput.EvalValues
import lupos.operator.physical.noinput.POPGraphOperation
import lupos.operator.physical.noinput.POPModifyData
import lupos.operator.physical.noinput.POPNothing
import lupos.operator.physical.noinput.POPValues
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
import lupos.operator.physical.singleinput.POPBind
import lupos.operator.physical.singleinput.POPFilter
import lupos.operator.physical.singleinput.POPGroup
import lupos.operator.physical.singleinput.POPMakeBooleanResult
import lupos.operator.physical.singleinput.POPModify
import lupos.operator.physical.singleinput.POPSort
import lupos.operator.physical.singleinput.modifiers.EvalLimit
import lupos.operator.physical.singleinput.modifiers.EvalOffset
import lupos.operator.physical.singleinput.modifiers.EvalReduced
import lupos.operator.physical.singleinput.modifiers.POPLimit
import lupos.operator.physical.singleinput.modifiers.POPOffset
import lupos.operator.physical.singleinput.modifiers.POPReduced
import lupos.shared.DictionaryValueHelper
import lupos.shared.DictionaryValueType
import lupos.shared.DictionaryValueTypeArray
import lupos.shared.EModifyType
import lupos.shared.EOperatorIDExt
import lupos.shared.Partition
import lupos.shared.SanityCheck
import lupos.shared.dynamicArray.ByteArrayWrapper
import lupos.shared.inline.dynamicArray.ByteArrayWrapperExt
import lupos.shared.operator.IOPBase
import lupos.shared.operator.iterator.IteratorBundle
import lupos.shared.operator.iterator.IteratorBundleRoot
import lupos.shared.operator.noinput.IAOPConstant
import lupos.shared.operator.noinput.IAOPVariable
import lupos.triple_store_manager.EvalTripleStoreIterator
import lupos.triple_store_manager.POPTripleStoreIterator

public typealias BinaryToOPBaseMap = (query: Query, data: ByteArrayWrapper, offset: Int) -> IteratorBundle
public typealias OPBaseToBinaryMap = (op: IOPBase, data: ByteArrayWrapper, parent: Partition, mapping: MutableMap<String, Int>) -> Int/*offset*/

public object BinaryToOPBase {
    public var operatorMapDecode: Array<BinaryToOPBaseMap?> = Array(0) { null }
    public var operatorMapEncode: Array<OPBaseToBinaryMap?> = Array(0) { null }
    public fun assignOperatorDecode(operatorIDs: IntArray, operator: BinaryToOPBaseMap) {
        for (operatorID in operatorIDs) {
            assignOperatorDecode(operatorID, operator)
        }
    }

    public fun assignOperatorDecode(operatorID: Int, operator: BinaryToOPBaseMap) {
        if (operatorMapDecode.size <= operatorID) {
            var s = operatorMapDecode.size
            if (s < 16) {
                s = 16
            }
            while (s < operatorID) {
                s = s * 2
            }
            val tmp = Array<BinaryToOPBaseMap?>(s) { null }
            operatorMapDecode.copyInto(tmp)
            operatorMapDecode = tmp
        }
        operatorMapDecode[operatorID] = operator
    }

    public fun assignOperatorEncode(operatorIDs: IntArray, operator: OPBaseToBinaryMap) {
        for (operatorID in operatorIDs) {
            assignOperatorEncode(operatorID, operator)
        }
    }

    public fun assignOperatorEncode(operatorID: Int, operator: OPBaseToBinaryMap) {
        if (operatorMapEncode.size <= operatorID) {
            var s = operatorMapEncode.size
            if (s < 16) {
                s = 16
            }
            while (s < operatorID) {
                s = s * 2
            }
            val tmp = Array<OPBaseToBinaryMap?>(s) { null }
            operatorMapEncode.copyInto(tmp)
            operatorMapEncode = tmp
        }
        operatorMapEncode[operatorID] = operator
    }

    public fun assignOperator(operatorID: Int, encode: OPBaseToBinaryMap, decode: BinaryToOPBaseMap) {
        assignOperatorEncode(operatorID, encode)
        assignOperatorDecode(operatorID, decode)
    }

    public fun convertToByteArray(op: IOPBase): ByteArrayWrapper {
        val mapping = mutableMapOf<String, Int>()
        val data = ByteArrayWrapper()
        if (op is OPBaseCompound) {
            ByteArrayWrapperExt.setSize(data, 5 + 8 * op.children.size + op.columnProjectionOrder.map { it.size }.sum() * 4, false)
            ByteArrayWrapperExt.writeInt1(data, 0, 0x1)
            ByteArrayWrapperExt.writeInt4(data, 1, op.children.size)
            var o = 5
            for (i in 0 until op.children.size) {
                val k = if (op.columnProjectionOrder.size > i) {
                    op.columnProjectionOrder[i]
                } else {
                    listOf()
                }
                val off = convertToByteArrayHelper(op.children[i], data, Partition(), mapping)
                ByteArrayWrapperExt.writeInt4(data, o, off)
                o += 4
                ByteArrayWrapperExt.writeInt4(data, o, k.size)
                o += 4
                for (j in 0 until k.size) {
                    ByteArrayWrapperExt.writeInt4(data, o, encodeString(k[j], data, mapping))
                    o += 4
                }
            }
        } else {
            ByteArrayWrapperExt.setSize(data, 5, false)
            val off = convertToByteArrayHelper(op, data, Partition(), mapping)
            ByteArrayWrapperExt.writeInt1(data, 0, 0x0)
            ByteArrayWrapperExt.writeInt4(data, 1, off)
        }
        return data
    }

    public fun convertToIteratorBundle(query: Query, data: ByteArrayWrapper, off: Int = 0): IteratorBundleRoot {
        if (ByteArrayWrapperExt.readInt1(data, off) == 0x1) {
            val childCount = ByteArrayWrapperExt.readInt4(data, off + 1)
            var o = 5
            val res = mutableListOf<Pair<List<String>, IteratorBundle>>()
            for (i in 0 until childCount) {
                val child = convertToIteratorBundleHelper(query, data, ByteArrayWrapperExt.readInt4(data, o))
                o += 4
                val size = ByteArrayWrapperExt.readInt4(data, o)
                o += 4
                val list = mutableListOf<String>()
                for (j in 0 until size) {
                    list.add(decodeString(data, ByteArrayWrapperExt.readInt4(data, o)))
                    o += 4
                }
                res.add(list to child)
            }
            return IteratorBundleRoot(query, res.toTypedArray())
        } else {
            val tmp = convertToIteratorBundleHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 1))
            return IteratorBundleRoot(query, arrayOf(listOf<String>() to tmp))
        }
    }

    private inline fun convertToByteArrayHelper(op: IOPBase, data: ByteArrayWrapper, parent: Partition, mapping: MutableMap<String, Int>): Int {
        val encoder = operatorMapEncode[(op as OPBase).operatorID]
        if (encoder == null) {
            TODO("convertToByteArrayHelper ${(op as OPBase).operatorID} -> ${EOperatorIDExt.names[(op as OPBase).operatorID]}")
        }
        return encoder(op, data, parent, mapping)
    }

    private inline fun convertToIteratorBundleHelper(query: Query, data: ByteArrayWrapper, off: Int): IteratorBundle {
        val decoder = operatorMapDecode[ByteArrayWrapperExt.readInt4(data, off)]
        if (decoder == null) {
            TODO("convertToIteratorBundleHelper ${ByteArrayWrapperExt.readInt4(data, off)} -> ${EOperatorIDExt.names[ByteArrayWrapperExt.readInt4(data, off)]}")
        }
        return decoder(query, data, off)
    }

    private inline fun encodeString(s: String?, data: ByteArrayWrapper, mapping: MutableMap<String, Int>): Int {
        if (s == null) {
            return -1
        } else {
            val r = mapping[s]
            if (r != null) {
                return r
            } else {
                val off = ByteArrayWrapperExt.getSize(data)
                mapping[s] = off
                val b = s.encodeToByteArray()
                ByteArrayWrapperExt.setSize(data, off + 4 + b.size, false)
                ByteArrayWrapperExt.writeInt4(data, off, b.size)
                ByteArrayWrapperExt.writeBuf(data, off + 4, b)
                return off
            }
        }
    }

    private inline fun decodeString(data: ByteArrayWrapper, off: Int): String {
        return ByteArrayWrapperExt.getBuf(data).decodeToString(off + 4, off + 4 + ByteArrayWrapperExt.readInt4(data, off))
    }

    private inline fun decodeStringNull(data: ByteArrayWrapper, off: Int): String? {
        if (off < 0) {
            return null
        } else {
            return decodeString(data, off)
        }
    }

    private inline fun encodeAOP(op: AOPBase, data: ByteArrayWrapper, mapping: MutableMap<String, Int>): Int {
        TODO()
    }

    private inline fun decodeAOP(query: Query, data: ByteArrayWrapper, off: Int): AOPBase {
        TODO()
    }

    init {
/*
 EOperatorIDExt.POPDistributedReceiveMultiCountID,
 EOperatorIDExt.POPDistributedReceiveMultiID,
 EOperatorIDExt.POPDistributedReceiveMultiOrderedID,
 EOperatorIDExt.POPDistributedReceiveSingleCountID,
 EOperatorIDExt.POPDistributedReceiveSingleID,
*/
        assignOperator(
            EOperatorIDExt.POPProjectionID,
            { op, data, parent, mapping ->
                op as OPBase
                convertToByteArrayHelper(op.children[0], data, parent, mapping)
            },
            { query, data, off ->
                TODO("unreachable")
            },
        )
        assignOperator(
            EOperatorIDExt.POPDebugID,
            { op, data, parent, mapping ->
                op as OPBase
                convertToByteArrayHelper(op.children[0], data, parent, mapping)
            },
            { query, data, off ->
                TODO("unreachable")
            },
        )
        assignOperator(
            EOperatorIDExt.POPSplitPartitionFromStoreCountID,
            { op, data, parent, mapping ->
                op as OPBase
                convertToByteArrayHelper(op.children[0], data, parent, mapping)
            },
            { query, data, off ->
                TODO("unreachable")
            },
        )
        assignOperator(
            EOperatorIDExt.POPSplitPartitionFromStoreID,
            { op, data, parent, mapping ->
                op as OPBase
                convertToByteArrayHelper(op.children[0], data, parent, mapping)
            },
            { query, data, off ->
                TODO("unreachable")
            },
        )
        assignOperator(
            EOperatorIDExt.POPSplitMergePartitionFromStoreID,
            { op, data, parent, mapping ->
                op as OPBase
                convertToByteArrayHelper(op.children[0], data, parent, mapping)
            },
            { query, data, off ->
                TODO("unreachable")
            },
        )
        assignOperator(
            EOperatorIDExt.POPModifyDataID,
            { op, data, parent, mapping ->
                op as POPModifyData
                val off = ByteArrayWrapperExt.getSize(data)
                ByteArrayWrapperExt.setSize(data, off + 4 + op.data.size * (4 + 3 * DictionaryValueHelper.getSize()), true)
                ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.POPModifyDataID)
                ByteArrayWrapperExt.writeInt4(data, off + 4, op.data.size)
                var o = off + 8
                for (t in op.data) {
                    ByteArrayWrapperExt.writeInt4(data, o, encodeString(t.graph, data, mapping))
                    o += 4
                    for (i in 0 until 3) {
                        DictionaryValueHelper.toByteArray(data, o, (t.children[i] as AOPConstant).value)
                        o += DictionaryValueHelper.getSize()
                    }
                }
                off
            },
            { query, data, off ->
                val d = mutableListOf<Pair<String, DictionaryValueTypeArray>>()
                val l = ByteArrayWrapperExt.readInt4(data, off + 4)
                var o = off + 8
                for (i in 0 until l) {
                    val arr = DictionaryValueTypeArray(3)
                    for (i in 0 until 3) {
                        arr[i] = DictionaryValueHelper.fromByteArray(data, o + 4 + i * DictionaryValueHelper.getSize())
                    }
                    d.add(decodeString(data, o) to arr)
                    o += DictionaryValueHelper.getSize() * 3 + 4
                }
                EvalModifyData(d, query)
            },
        )
        assignOperator(
            EOperatorIDExt.POPGraphOperationID,
            { op, data, parent, mapping ->
                op as POPGraphOperation
                val off = ByteArrayWrapperExt.getSize(data)
                ByteArrayWrapperExt.setSize(data, off + 25, true)
                ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.POPGraphOperationID)
                ByteArrayWrapperExt.writeInt4(data, off + 4, op.graph1type)
                ByteArrayWrapperExt.writeInt4(data, off + 8, op.graph2type)
                ByteArrayWrapperExt.writeInt4(data, off + 12, op.action)
                ByteArrayWrapperExt.writeInt4(data, off + 16, encodeString(op.graph1iri, data, mapping))
                ByteArrayWrapperExt.writeInt4(data, off + 20, encodeString(op.graph2iri, data, mapping))
                ByteArrayWrapperExt.writeInt1(data, off + 24, if (op.silent) 1 else 0)
                off
            },
            { query, data, off ->
                EvalGraphOperation(
                    ByteArrayWrapperExt.readInt1(data, off + 24) == 1,
                    ByteArrayWrapperExt.readInt4(data, off + 4),
                    decodeStringNull(data, off + 16),
                    ByteArrayWrapperExt.readInt4(data, off + 8),
                    decodeStringNull(data, off + 20),
                    ByteArrayWrapperExt.readInt4(data, off + 12),
                    query,
                )
            },
        )
        assignOperator(
            EOperatorIDExt.POPNothingID,
            { op, data, parent, mapping ->
                op as POPNothing
                val n = op.getProvidedVariableNames()
                val off = ByteArrayWrapperExt.getSize(data)
                ByteArrayWrapperExt.setSize(data, off + 8 + 4 * n.size, true)
                ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.POPNothingID)
                ByteArrayWrapperExt.writeInt4(data, off + 4, n.size)
                var i = 0
                for (s in n) {
                    ByteArrayWrapperExt.writeInt4(data, off + 8 + i * 4, encodeString(s, data, mapping))
                    i++
                }
                off
            },
            { query, data, off ->
                val len = ByteArrayWrapperExt.readInt4(data, off + 4)
                val list = mutableListOf<String>()
                for (i in 0 until len) {
                    list.add(decodeString(data, ByteArrayWrapperExt.readInt4(data, off + 8 + 4 * i)))
                }
                EvalNothing(list)
            },
        )
        assignOperatorEncode(
            intArrayOf(
                EOperatorIDExt.POPValuesID,
                EOperatorIDExt.POPValuesCountID,
            ),
            { op, data, parent, mapping ->
                op as POPValues
                val off = ByteArrayWrapperExt.getSize(data)
                if (op.rows == -1) {
                    val size = op.data[op.variables.first()]!!.size
                    ByteArrayWrapperExt.setSize(data, off + 12 + op.data.size * (4 + size * DictionaryValueHelper.getSize()), true)
                    ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.POPValuesID)
                    ByteArrayWrapperExt.writeInt4(data, off + 4, op.data.size)
                    ByteArrayWrapperExt.writeInt4(data, off + 8, size)
                    var o = off + 12
                    for ((col, rows) in op.data) {
                        ByteArrayWrapperExt.writeInt4(data, o, encodeString(col, data, mapping))
                        o += 4
                        var i = 0
                        for (row in rows) {
                            i++
                            DictionaryValueHelper.toByteArray(data, o, row)
                            o += DictionaryValueHelper.getSize()
                        }
                        SanityCheck.check(
                            { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_factory/src/commonMain/kotlin/lupos/operator/factory/BinaryToOPBase.kt:422"/*SOURCE_FILE_END*/ },
                            { i == size }
                        )
                    }
                } else {
                    ByteArrayWrapperExt.setSize(data, off + 8, true)
                    ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.POPValuesCountID)
                    ByteArrayWrapperExt.writeInt4(data, off + 4, op.rows)
                }
                off
            },
        )
        assignOperatorDecode(
            EOperatorIDExt.POPValuesCountID,
            { query, data, off ->
                IteratorBundle(ByteArrayWrapperExt.readInt4(data, off + 4))
            },
        )
        assignOperatorDecode(
            EOperatorIDExt.POPValuesID,
            { query, data, off ->
                val columns = ByteArrayWrapperExt.readInt4(data, off + 4)
                val row_count = ByteArrayWrapperExt.readInt4(data, off + 4)
                val dd = mutableMapOf<String, MutableList<DictionaryValueType>>()
                var o = off + 12
                for (c in 0 until columns) {
                    val rows = mutableListOf<DictionaryValueType>()
                    dd[decodeString(data, o)] = rows
                    o += 4
                    for (i in 0 until row_count) {
                        rows.add(DictionaryValueHelper.fromByteArray(data, o))
                        o += DictionaryValueHelper.getSize()
                    }
                }
                EvalValues(dd)
            },
        )

        assignOperator(
            EOperatorIDExt.POPEmptyRowID,
            { op, data, parent, mapping ->
                val off = ByteArrayWrapperExt.getSize(data)
                ByteArrayWrapperExt.setSize(data, off + 4, true)
                ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.POPEmptyRowID)
                off
            },
            { query, data, off ->
                IteratorBundle(1)
            },
        )
        assignOperator(
            EOperatorIDExt.POPUnionID,
            { op, data, parent, mapping ->
                op as POPUnion
                val child0 = convertToByteArrayHelper(op.children[0], data, parent, mapping)
                val child1 = convertToByteArrayHelper(op.children[1], data, parent, mapping)
                val off = ByteArrayWrapperExt.getSize(data)
                ByteArrayWrapperExt.setSize(data, off + 16 + 4 * op.projectedVariables.size, true)
                ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.POPUnionID)
                ByteArrayWrapperExt.writeInt4(data, off + 4, child0)
                ByteArrayWrapperExt.writeInt4(data, off + 8, child1)
                ByteArrayWrapperExt.writeInt4(data, off + 12, op.projectedVariables.size)
                var o = off + 16
                for (s in op.projectedVariables) {
                    ByteArrayWrapperExt.writeInt4(data, o, encodeString(s, data, mapping))
                    o += 4
                }
                off
            },
            { query, data, off ->
                val child0 = convertToIteratorBundleHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 4))
                val child1 = convertToIteratorBundleHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 8))
                val l = ByteArrayWrapperExt.readInt4(data, off + 12)
                var projectedVariables = mutableListOf<String>()
                for (i in 0 until l) {
                    projectedVariables.add(decodeString(data, ByteArrayWrapperExt.readInt4(data, off + 16 + 4 * i)))
                }
                EvalUnion(child0, child1, projectedVariables)
            },
        )
        assignOperator(
            EOperatorIDExt.POPMinusID,
            { op, data, parent, mapping ->
                op as POPMinus
                val child0 = convertToByteArrayHelper(op.children[0], data, parent, mapping)
                val child1 = convertToByteArrayHelper(op.children[1], data, parent, mapping)
                val off = ByteArrayWrapperExt.getSize(data)
                ByteArrayWrapperExt.setSize(data, off + 16 + 4 * op.projectedVariables.size, true)
                ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.POPMinusID)
                ByteArrayWrapperExt.writeInt4(data, off + 4, child0)
                ByteArrayWrapperExt.writeInt4(data, off + 8, child1)
                ByteArrayWrapperExt.writeInt4(data, off + 12, op.projectedVariables.size)
                var o = off + 16
                for (s in op.projectedVariables) {
                    ByteArrayWrapperExt.writeInt4(data, o, encodeString(s, data, mapping))
                    o += 4
                }
                off
            },
            { query, data, off ->
                val child0 = convertToIteratorBundleHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 4))
                val child1 = convertToIteratorBundleHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 8))
                val l = ByteArrayWrapperExt.readInt4(data, off + 12)
                var projectedVariables = mutableListOf<String>()
                for (i in 0 until l) {
                    projectedVariables.add(decodeString(data, ByteArrayWrapperExt.readInt4(data, off + 16 + 4 * i)))
                }
                EvalMinus(child0, child1, projectedVariables)
            },
        )
        assignOperator(
            EOperatorIDExt.POPJoinMergeSingleColumnID,
            { op, data, parent, mapping ->
                op as POPJoinMergeSingleColumn
                val child0 = convertToByteArrayHelper(op.children[0], data, parent, mapping)
                val child1 = convertToByteArrayHelper(op.children[1], data, parent, mapping)
                val off = ByteArrayWrapperExt.getSize(data)
                ByteArrayWrapperExt.setSize(data, off + 12, true)
                ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.POPJoinMergeSingleColumnID)
                ByteArrayWrapperExt.writeInt4(data, off + 4, child0)
                ByteArrayWrapperExt.writeInt4(data, off + 8, child1)
                off
            },
            { query, data, off ->
                val child0 = convertToIteratorBundleHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 4))
                val child1 = convertToIteratorBundleHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 8))
                EvalJoinMergeSingleColumn(child0, child1)
            },
        )
        assignOperator(
            EOperatorIDExt.POPJoinMergeOptionalID,
            { op, data, parent, mapping ->
                op as POPJoinMergeOptional
                val child0 = convertToByteArrayHelper(op.children[0], data, parent, mapping)
                val child1 = convertToByteArrayHelper(op.children[1], data, parent, mapping)
                val off = ByteArrayWrapperExt.getSize(data)
                ByteArrayWrapperExt.setSize(data, off + 16 + 4 * op.projectedVariables.size, true)
                ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.POPJoinMergeOptionalID)
                ByteArrayWrapperExt.writeInt4(data, off + 4, child0)
                ByteArrayWrapperExt.writeInt4(data, off + 8, child1)
                ByteArrayWrapperExt.writeInt4(data, off + 12, op.projectedVariables.size)
                var o = off + 16
                for (s in op.projectedVariables) {
                    ByteArrayWrapperExt.writeInt4(data, o, encodeString(s, data, mapping))
                    o += 4
                }
                off
            },
            { query, data, off ->
                val child0 = convertToIteratorBundleHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 4))
                val child1 = convertToIteratorBundleHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 8))
                val l = ByteArrayWrapperExt.readInt4(data, off + 12)
                var projectedVariables = mutableListOf<String>()
                for (i in 0 until l) {
                    projectedVariables.add(decodeString(data, ByteArrayWrapperExt.readInt4(data, off + 16 + 4 * i)))
                }
                EvalJoinMergeOptional(arrayOf(child0, child1), projectedVariables)
            },
        )
        assignOperator(
            EOperatorIDExt.POPJoinMergeID,
            { op, data, parent, mapping ->
                op as POPJoinMerge
                val child0 = convertToByteArrayHelper(op.children[0], data, parent, mapping)
                val child1 = convertToByteArrayHelper(op.children[1], data, parent, mapping)
                val off = ByteArrayWrapperExt.getSize(data)
                ByteArrayWrapperExt.setSize(data, off + 16 + 4 * op.projectedVariables.size, true)
                ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.POPJoinMergeID)
                ByteArrayWrapperExt.writeInt4(data, off + 4, child0)
                ByteArrayWrapperExt.writeInt4(data, off + 8, child1)
                ByteArrayWrapperExt.writeInt4(data, off + 12, op.projectedVariables.size)
                var o = off + 16
                for (s in op.projectedVariables) {
                    ByteArrayWrapperExt.writeInt4(data, o, encodeString(s, data, mapping))
                    o += 4
                }
                off
            },
            { query, data, off ->
                val child0 = convertToIteratorBundleHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 4))
                val child1 = convertToIteratorBundleHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 8))
                val l = ByteArrayWrapperExt.readInt4(data, off + 12)
                var projectedVariables = mutableListOf<String>()
                for (i in 0 until l) {
                    projectedVariables.add(decodeString(data, ByteArrayWrapperExt.readInt4(data, off + 16 + 4 * i)))
                }
                EvalJoinMerge(child0, child1, projectedVariables)
            },
        )
        assignOperator(
            EOperatorIDExt.POPJoinHashMapID,
            { op, data, parent, mapping ->
                op as POPJoinHashMap
                val child0 = convertToByteArrayHelper(op.children[0], data, parent, mapping)
                val child1 = convertToByteArrayHelper(op.children[1], data, parent, mapping)
                val off = ByteArrayWrapperExt.getSize(data)
                ByteArrayWrapperExt.setSize(data, off + 17 + 4 * op.projectedVariables.size, true)
                ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.POPJoinHashMapID)
                ByteArrayWrapperExt.writeInt4(data, off + 4, child0)
                ByteArrayWrapperExt.writeInt4(data, off + 8, child1)
                ByteArrayWrapperExt.writeInt1(data, off + 12, if (op.optional) 1 else 0)
                ByteArrayWrapperExt.writeInt4(data, off + 13, op.projectedVariables.size)
                var o = off + 17
                for (s in op.projectedVariables) {
                    ByteArrayWrapperExt.writeInt4(data, o, encodeString(s, data, mapping))
                    o += 4
                }
                off
            },
            { query, data, off ->
                val child0 = convertToIteratorBundleHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 4))
                val child1 = convertToIteratorBundleHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 8))
                val l = ByteArrayWrapperExt.readInt4(data, off + 13)
                var projectedVariables = mutableListOf<String>()
                for (i in 0 until l) {
                    projectedVariables.add(decodeString(data, ByteArrayWrapperExt.readInt4(data, off + 17 + 4 * i)))
                }
                EvalJoinHashMap(child0, child1, ByteArrayWrapperExt.readInt1(data, off + 12) == 1, projectedVariables)
            },
        )
        assignOperator(
            EOperatorIDExt.POPJoinCartesianProductID,
            { op, data, parent, mapping ->
                op as POPJoinCartesianProduct
                val child0 = convertToByteArrayHelper(op.children[0], data, parent, mapping)
                val child1 = convertToByteArrayHelper(op.children[1], data, parent, mapping)
                val off = ByteArrayWrapperExt.getSize(data)
                ByteArrayWrapperExt.setSize(data, off + 13, true)
                ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.POPJoinCartesianProductID)
                ByteArrayWrapperExt.writeInt4(data, off + 4, child0)
                ByteArrayWrapperExt.writeInt4(data, off + 8, child1)
                ByteArrayWrapperExt.writeInt1(data, off + 12, if (op.optional) 1 else 0)
                off
            },
            { query, data, off ->
                val child0 = convertToIteratorBundleHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 4))
                val child1 = convertToIteratorBundleHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 8))
                EvalJoinCartesianProduct(child0, child1, ByteArrayWrapperExt.readInt1(data, off + 12) == 1)
            },
        )
        assignOperator(
            EOperatorIDExt.POPLimitID,
            { op, data, parent, mapping ->
                op as POPLimit
                val child = convertToByteArrayHelper(op.children[0], data, parent, mapping)
                val off = ByteArrayWrapperExt.getSize(data)
                ByteArrayWrapperExt.setSize(data, off + 12, true)
                ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.POPLimitID)
                ByteArrayWrapperExt.writeInt4(data, off + 4, child)
                ByteArrayWrapperExt.writeInt4(data, off + 8, op.limit)
                off
            },
            { query, data, off ->
                val child = convertToIteratorBundleHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 4))
                EvalLimit(child, ByteArrayWrapperExt.readInt4(data, off + 8))
            },
        )
        assignOperator(
            EOperatorIDExt.POPSortID,
            { op, data, parent, mapping ->
                op as POPSort
                if (op.getProvidedVariableNames().size == 0) {
                    convertToByteArrayHelper(op.children[0], data, parent, mapping)
                } else {
                    val child = convertToByteArrayHelper(op.children[0], data, parent, mapping)
                    val off = ByteArrayWrapperExt.getSize(data)
                    ByteArrayWrapperExt.setSize(data, off + 21 + 4 * (op.mySortPriority.size + op.getProvidedVariableNames().size + op.sortBy.size), true)
                    ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.POPSortID)
                    ByteArrayWrapperExt.writeInt4(data, off + 4, child)
                    ByteArrayWrapperExt.writeInt4(data, off + 8, op.mySortPriority.size)
                    ByteArrayWrapperExt.writeInt4(data, off + 12, op.getProvidedVariableNames().size)
                    ByteArrayWrapperExt.writeInt4(data, off + 16, op.sortBy.size)
                    ByteArrayWrapperExt.writeInt1(data, off + 20, if (op.sortOrder) 1 else 0)
                    var o = 21
                    for (s in op.mySortPriority.map { it.variableName }) {
                        ByteArrayWrapperExt.writeInt4(data, o, encodeString(s, data, mapping))
                        o += 4
                    }
                    for (s in op.sortBy.map { it.name }) {
                        ByteArrayWrapperExt.writeInt4(data, o, encodeString(s, data, mapping))
                        o += 4
                    }
                    for (s in op.getProvidedVariableNames()) {
                        ByteArrayWrapperExt.writeInt4(data, o, encodeString(s, data, mapping))
                        o += 4
                    }
                    off
                }
            },
            { query, data, off ->
                val child = convertToIteratorBundleHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 4))
                val splen = ByteArrayWrapperExt.readInt4(data, off + 8)
                val plen = ByteArrayWrapperExt.readInt4(data, off + 12)
                val sblen = ByteArrayWrapperExt.readInt4(data, off + 16)
                val sortOrder = ByteArrayWrapperExt.readInt1(data, off + 20) != 0
                var o = 21
                val spList = mutableListOf<String>()
                val pList = mutableListOf<String>()
                val sbList = mutableListOf<String>()
                for (i in 0 until splen) {
                    spList.add(decodeString(data, o))
                    o += 4
                }
                for (i in 0 until sblen) {
                    sbList.add(decodeString(data, o))
                    o += 4
                }
                for (i in 0 until plen) {
                    pList.add(decodeString(data, o))
                    o += 4
                }
                EvalSort(child, spList, query, sbList.toTypedArray(), pList, sortOrder)
            },
        )
        assignOperator(
            EOperatorIDExt.POPOffsetID,
            { op, data, parent, mapping ->
                op as POPOffset
                val child = convertToByteArrayHelper(op.children[0], data, parent, mapping)
                val off = ByteArrayWrapperExt.getSize(data)
                ByteArrayWrapperExt.setSize(data, off + 12, true)
                ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.POPOffsetID)
                ByteArrayWrapperExt.writeInt4(data, off + 4, child)
                ByteArrayWrapperExt.writeInt4(data, off + 8, op.offset)
                off
            },
            { query, data, off ->
                val child = convertToIteratorBundleHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 4))
                EvalOffset(child, ByteArrayWrapperExt.readInt4(data, off + 8))
            },
        )
        assignOperator(
            EOperatorIDExt.POPMakeBooleanResultID,
            { op, data, parent, mapping ->
                op as POPMakeBooleanResult
                val child = convertToByteArrayHelper(op.children[0], data, parent, mapping)
                val off = ByteArrayWrapperExt.getSize(data)
                ByteArrayWrapperExt.setSize(data, off + 8, true)
                ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.POPMakeBooleanResultID)
                ByteArrayWrapperExt.writeInt4(data, off + 4, child)
                off
            },
            { query, data, off ->
                val child = convertToIteratorBundleHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 4))
                EvalMakeBooleanResult(child)
            },
        )
        assignOperator(
            EOperatorIDExt.POPReducedID,
            { op, data, parent, mapping ->
                op as POPReduced
                val child = convertToByteArrayHelper(op.children[0], data, parent, mapping)
                val off = ByteArrayWrapperExt.getSize(data)
                ByteArrayWrapperExt.setSize(data, off + 12, true)
                ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.POPReducedID)
                ByteArrayWrapperExt.writeInt4(data, off + 4, child)
                ByteArrayWrapperExt.writeInt4(data, off + 8, op.projectedVariables.size)
                off
            },
            { query, data, off ->
                val child = convertToIteratorBundleHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 4))
                EvalReduced(child, ByteArrayWrapperExt.readInt4(data, off + 8))
            },
        )
        assignOperator(
            EOperatorIDExt.POPTripleStoreIterator,
            { op, data, parent, mapping ->
                op as POPTripleStoreIterator
                val off = ByteArrayWrapperExt.getSize(data)
                ByteArrayWrapperExt.setSize(data, off + 17 + 3 * if (DictionaryValueHelper.getSize() > 4) DictionaryValueHelper.getSize() else 4, true)
                val target = op.getTarget(parent)
                ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.POPTripleStoreIterator)
                ByteArrayWrapperExt.writeInt4(data, off + 4, encodeString(target.first, data, mapping))
                ByteArrayWrapperExt.writeInt4(data, off + 8, encodeString(target.second, data, mapping))
                ByteArrayWrapperExt.writeInt4(data, off + 12, op.getIndexPattern())
                var childFlag = 0
                var o = off + 17
                for (i in 0 until 3) {
                    val child = op.children[i]
                    when (child) {
                        is IAOPConstant -> {
                            childFlag = childFlag + (1 shl i)
                            DictionaryValueHelper.toByteArray(data, o, child.getValue())
                            o += DictionaryValueHelper.getSize()
                        }
                        else -> {
                            child as IAOPVariable
                            ByteArrayWrapperExt.writeInt4(data, o, encodeString(child.getName(), data, mapping))
                            o += 4
                        }
                    }
                }
                ByteArrayWrapperExt.writeInt1(data, off + 16, childFlag)
                off
            },
            { query, data, off ->
                val buf1 = decodeString(data, ByteArrayWrapperExt.readInt4(data, off + 4))
                val buf2 = decodeString(data, ByteArrayWrapperExt.readInt4(data, off + 8))
                val index = ByteArrayWrapperExt.readInt4(data, off + 12)
                val childFlag = ByteArrayWrapperExt.readInt1(data, off + 16)
                var o = off + 17
                val child0f = (childFlag and 0x1) > 0
                val child1f = (childFlag and 0x2) > 0
                val child2f = (childFlag and 0x4) > 0
                val child0c = if (child0f) {
                    val res = DictionaryValueHelper.fromByteArray(data, o)
                    o += DictionaryValueHelper.getSize()
                    res
                } else {
                    DictionaryValueHelper.nullValue
                }
                val child0v = if (child0f) {
                    decodeString(data, ByteArrayWrapperExt.readInt4(data, o))
                } else {
                    ""
                }
                val child1c = if (child1f) {
                    val res = DictionaryValueHelper.fromByteArray(data, o)
                    o += DictionaryValueHelper.getSize()
                    res
                } else {
                    DictionaryValueHelper.nullValue
                }
                val child1v = if (child1f) {
                    decodeString(data, ByteArrayWrapperExt.readInt4(data, o))
                } else {
                    ""
                }
                val child2c = if (child2f) {
                    val res =
                        DictionaryValueHelper.fromByteArray(data, o)
                    o += DictionaryValueHelper.getSize()
                    res
                } else {
                    DictionaryValueHelper.nullValue
                }
                val child2v = if (child2f) {
                    decodeString(data, ByteArrayWrapperExt.readInt4(data, o))
                } else {
                    ""
                }
                EvalTripleStoreIterator(
                    buf1 to buf2,
                    query,
                    index,
                    arrayOf(child0f to (child0c to child0v), child1f to (child1c to child1v), child2f to (child2c to child2v))
                )
            },
        )
        assignOperator(
            EOperatorIDExt.POPBindID,
            { op, data, parent, mapping ->
                op as POPBind
                val variablesOut = op.getProvidedVariableNames()
                val child = convertToByteArrayHelper(op.children[0], data, parent, mapping)
                val off = ByteArrayWrapperExt.getSize(data)
                ByteArrayWrapperExt.setSize(data, off + 20 + variablesOut.size * 4, true)
                ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.POPBindID)
                ByteArrayWrapperExt.writeInt4(data, off + 4, child)
                ByteArrayWrapperExt.writeInt4(data, off + 8, encodeAOP(op.children[1] as AOPBase, data, mapping))
                ByteArrayWrapperExt.writeInt4(data, off + 12, encodeString(op.name.name, data, mapping))
                ByteArrayWrapperExt.writeInt4(data, off + 16, variablesOut.size)
                for (i in 0 until variablesOut.size) {
                    ByteArrayWrapperExt.writeInt4(data, off + 20 + 4 * i, encodeString(variablesOut[i], data, mapping))
                }
                off
            },
            { query, data, off ->
                val child = convertToIteratorBundleHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 4))
                val variablesOut = mutableListOf<String>()
                val len = ByteArrayWrapperExt.readInt4(data, off + 16)
                for (i in 0 until len) {
                    variablesOut.add(decodeString(data, off + 20 + i * 4))
                }
                EvalBind(child, variablesOut, decodeString(data, off + 12), decodeAOP(query, data, off + 8))
            },
        )
        assignOperator(
            EOperatorIDExt.POPFilterID,
            { op, data, parent, mapping ->
                op as POPFilter
                val variablesOut = op.getProvidedVariableNames()
                val child = convertToByteArrayHelper(op.children[0], data, parent, mapping)
                val off = ByteArrayWrapperExt.getSize(data)
                ByteArrayWrapperExt.setSize(data, off + 16 + 4 * variablesOut.size, true)
                ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.POPFilterID)
                ByteArrayWrapperExt.writeInt4(data, off + 4, child)
                ByteArrayWrapperExt.writeInt4(data, off + 8, encodeAOP(op.children[1] as AOPBase, data, mapping))
                ByteArrayWrapperExt.writeInt4(data, off + 12, variablesOut.size)
                for (i in 0 until variablesOut.size) {
                    ByteArrayWrapperExt.writeInt4(data, off + 16 + 4 * i, encodeString(variablesOut[i], data, mapping))
                }
                off
            },
            { query, data, off ->
                val child = convertToIteratorBundleHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 4))
                val variablesOut = mutableListOf<String>()
                val len = ByteArrayWrapperExt.readInt4(data, off + 12)
                for (i in 0 until len) {
                    variablesOut.add(decodeString(data, off + 16 + i * 4))
                }
                EvalFilter(child, variablesOut, decodeAOP(query, data, off + 8))
            },
        )
        assignOperatorEncode(
            intArrayOf(
                EOperatorIDExt.POPGroupID,
                EOperatorIDExt.POPGroupCount0ID,
                EOperatorIDExt.POPGroupCount1ID,
                EOperatorIDExt.POPGroupSortedID,
                EOperatorIDExt.POPGroupWithoutKeyColumnID,
            ),
            { op, data, parent, mapping ->
                op as POPGroup
                val keyColumnNames = op.by.map { it.name }
                val child = convertToByteArrayHelper(op.children[0], data, parent, mapping)
                val off = ByteArrayWrapperExt.getSize(data)
                var done = false
                if (op.by.isEmpty()) {
                    ByteArrayWrapperExt.setSize(data, off + 20 + 4 * (keyColumnNames.size + op.projectedVariables.size + op.bindings.size * 2), true)
                    ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.POPGroupWithoutKeyColumnID)
                    ByteArrayWrapperExt.writeInt4(data, off + 4, child)
                    ByteArrayWrapperExt.writeInt4(data, off + 8, op.projectedVariables.size)
                    ByteArrayWrapperExt.writeInt4(data, off + 12, op.bindings.size)
                    var o = 16
                    for (s in op.projectedVariables) {
                        ByteArrayWrapperExt.writeInt4(data, o, encodeString(s, data, mapping))
                        o += 4
                    }
                    for ((k, v) in op.bindings) {
                        ByteArrayWrapperExt.writeInt4(data, o, encodeString(k, data, mapping))
                        o += 4
                        ByteArrayWrapperExt.writeInt4(data, o, encodeAOP(v, data, mapping))
                        o += 4
                    }
                    done = true
                } else if (op.canUseSortedInput()) {
                    ByteArrayWrapperExt.setSize(data, off + 20 + 4 * (keyColumnNames.size + op.projectedVariables.size + op.bindings.size * 2), true)
                    ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.POPGroupSortedID)
                    ByteArrayWrapperExt.writeInt4(data, off + 4, child)
                    ByteArrayWrapperExt.writeInt4(data, off + 8, op.projectedVariables.size)
                    ByteArrayWrapperExt.writeInt4(data, off + 12, op.bindings.size)
                    ByteArrayWrapperExt.writeInt4(data, off + 16, keyColumnNames.size)
                    var o = 20
                    for (s in op.projectedVariables) {
                        ByteArrayWrapperExt.writeInt4(data, o, encodeString(s, data, mapping))
                        o += 4
                    }
                    for (s in keyColumnNames) {
                        ByteArrayWrapperExt.writeInt4(data, o, encodeString(s, data, mapping))
                        o += 4
                    }
                    for ((k, v) in op.bindings) {
                        ByteArrayWrapperExt.writeInt4(data, o, encodeString(k, data, mapping))
                        o += 4
                        ByteArrayWrapperExt.writeInt4(data, o, encodeAOP(v, data, mapping))
                        o += 4
                    }
                    done = true
                } else if (op.isCountOnly()) {
                    if (op.by.size == 0) {
                        ByteArrayWrapperExt.setSize(data, off + 12, true)
                        ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.POPGroupCount0ID)
                        ByteArrayWrapperExt.writeInt4(data, off + 4, child)
                        ByteArrayWrapperExt.writeInt4(data, off + 8, encodeString(op.bindings.toList().first().first, data, mapping))
                        done = true
                    } else if (op.by.size == 1) {
                        ByteArrayWrapperExt.setSize(data, off + 16, true)
                        ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.POPGroupCount1ID)
                        ByteArrayWrapperExt.writeInt4(data, off + 4, child)
                        ByteArrayWrapperExt.writeInt4(data, off + 8, encodeString(op.bindings.toList().first().first, data, mapping))
                        ByteArrayWrapperExt.writeInt4(data, off + 12, encodeString(op.by[0].name, data, mapping))
                        done = true
                    }
                }
                if (!done) {
                    ByteArrayWrapperExt.setSize(data, off + 16 + 4 * (keyColumnNames.size + op.projectedVariables.size + op.bindings.size * 2), true)
                    ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.POPGroupID)
                    ByteArrayWrapperExt.writeInt4(data, off + 4, child)
                    ByteArrayWrapperExt.writeInt4(data, off + 8, op.bindings.size)
                    ByteArrayWrapperExt.writeInt4(data, off + 12, keyColumnNames.size)
                    var o = 16
                    for (s in keyColumnNames) {
                        ByteArrayWrapperExt.writeInt4(data, o, encodeString(s, data, mapping))
                        o += 4
                    }
                    for ((k, v) in op.bindings) {
                        ByteArrayWrapperExt.writeInt4(data, o, encodeString(k, data, mapping))
                        o += 4
                        ByteArrayWrapperExt.writeInt4(data, o, encodeAOP(v, data, mapping))
                        o += 4
                    }
                }
                off
            },
        )
        assignOperatorDecode(
            EOperatorIDExt.POPGroupCount0ID,
            { query, data, off ->
                val child = convertToIteratorBundleHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 4))
                val binding = decodeString(data, off + 8)
                EvalGroupCount0(child, binding, query.getDictionary())
            },
        )
        assignOperatorDecode(
            EOperatorIDExt.POPGroupCount1ID,
            { query, data, off ->
                val child = convertToIteratorBundleHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 4))
                val binding = decodeString(data, off + 8)
                val name = decodeString(data, off + 12)
                EvalGroupCount1(child, binding, name, query.getDictionary())
            },
        )
        assignOperatorDecode(
            EOperatorIDExt.POPGroupSortedID,
            { query, data, off ->
                val child = convertToIteratorBundleHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 4))
                val plen = ByteArrayWrapperExt.readInt4(data, off + 8)
                val blen = ByteArrayWrapperExt.readInt4(data, off + 12)
                val klen = ByteArrayWrapperExt.readInt4(data, off + 16)
                var o = 20
                val projectedVariables = mutableListOf<String>()
                for (i in 0 until plen) {
                    projectedVariables.add(decodeString(data, ByteArrayWrapperExt.readInt4(data, o)))
                    o += 4
                }
                val keyColumnNames = Array<String>(klen) { "" }
                for (i in 0 until klen) {
                    keyColumnNames[i] = decodeString(data, ByteArrayWrapperExt.readInt4(data, o))
                    o += 4
                }
                val bindings = mutableListOf<Pair<String, AOPBase>>()
                for (i in 0 until blen) {
                    val k = decodeString(data, ByteArrayWrapperExt.readInt4(data, o))
                    o += 4
                    val v = decodeAOP(query, data, o)
                    o += 4
                    bindings.add(k to v)
                }
                EvalGroupSorted(child, bindings, projectedVariables, keyColumnNames)
            },
        )
        assignOperatorDecode(
            EOperatorIDExt.POPGroupID,
            { query, data, off ->
                val child = convertToIteratorBundleHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 4))
                val blen = ByteArrayWrapperExt.readInt4(data, off + 8)
                val klen = ByteArrayWrapperExt.readInt4(data, off + 12)
                var o = 16
                val keyColumnNames = Array<String>(klen) { "" }
                for (i in 0 until klen) {
                    keyColumnNames[i] = decodeString(data, ByteArrayWrapperExt.readInt4(data, o))
                    o += 4
                }
                val bindings = mutableListOf<Pair<String, AOPBase>>()
                for (i in 0 until blen) {
                    val k = decodeString(data, ByteArrayWrapperExt.readInt4(data, o))
                    o += 4
                    val v = decodeAOP(query, data, o)
                    o += 4
                    bindings.add(k to v)
                }
                EvalGroup(child, bindings, keyColumnNames)
            },
        )
        assignOperatorDecode(
            EOperatorIDExt.POPGroupWithoutKeyColumnID,
            { query, data, off ->
                val child = convertToIteratorBundleHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 4))
                val plen = ByteArrayWrapperExt.readInt4(data, off + 8)
                val blen = ByteArrayWrapperExt.readInt4(data, off + 12)
                var o = 16
                val projectedVariables = mutableListOf<String>()
                for (i in 0 until plen) {
                    projectedVariables.add(decodeString(data, ByteArrayWrapperExt.readInt4(data, o)))
                    o += 4
                }
                val bindings = mutableListOf<Pair<String, AOPBase>>()
                for (i in 0 until blen) {
                    val k = decodeString(data, ByteArrayWrapperExt.readInt4(data, o))
                    o += 4
                    val v = decodeAOP(query, data, o)
                    o += 4
                    bindings.add(k to v)
                }
                EvalGroupWithoutKeyColumn(child, bindings, projectedVariables)
            },
        )
        assignOperator(
            EOperatorIDExt.POPModifyID,
            { op, data, parent, mapping ->
                op as POPModify
                val child = convertToByteArrayHelper(op.children[0], data, parent, mapping)
                val off = ByteArrayWrapperExt.getSize(data)
                ByteArrayWrapperExt.setSize(data, off + 12 + op.modify.size * (9 + 3 * if (DictionaryValueHelper.getSize() > 4) DictionaryValueHelper.getSize() else 4), true)
                ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.POPModifyID)
                ByteArrayWrapperExt.writeInt4(data, off + 4, child)
                ByteArrayWrapperExt.writeInt4(data, off + 8, op.modify.size)
                val steph = if (DictionaryValueHelper.getSize() > 4) DictionaryValueHelper.getSize() else 4
                val step = 9 + 3 * steph
                var i = 0
                for ((k, v) in op.modify) {
                    var o = off + 12 + i * step
                    ByteArrayWrapperExt.writeInt4(data, o, v)
                    ByteArrayWrapperExt.writeInt4(data, o + 4, encodeString(k.graph, data, mapping))
                    var flag = 0
                    if (k.graphVar) {
                        flag += 0x1
                    }
                    val s = k.children[0]
                    val p = k.children[1]
                    val oo = k.children[2]
                    if (s is AOPConstant) {
                        flag += 0x2
                        DictionaryValueHelper.toByteArray(data, o + 9, s.value)
                    } else {
                        s as AOPVariable
                        ByteArrayWrapperExt.writeInt4(data, o + 9, encodeString(s.name, data, mapping))
                    }
                    if (p is AOPConstant) {
                        flag += 0x4
                        DictionaryValueHelper.toByteArray(data, o + 9 + steph, p.value)
                    } else {
                        p as AOPVariable
                        ByteArrayWrapperExt.writeInt4(data, o + 9 + steph, encodeString(p.name, data, mapping))
                    }
                    if (oo is AOPConstant) {
                        flag += 0x8
                        DictionaryValueHelper.toByteArray(data, o + 9 + steph + steph, oo.value)
                    } else {
                        oo as AOPVariable
                        ByteArrayWrapperExt.writeInt4(data, o + 9 + steph + steph, encodeString(oo.name, data, mapping))
                    }
                    ByteArrayWrapperExt.writeInt1(data, o + 8, v)
                    i++
                }
                off
            },
            { query, data, off ->
                val child = convertToIteratorBundleHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 4))
                val steph = if (DictionaryValueHelper.getSize() > 4) DictionaryValueHelper.getSize() else 4
                val step = 9 + 3 * steph
                val modify = Array<Pair<LOPTriple, EModifyType>>(ByteArrayWrapperExt.readInt4(data, off + 8)) { it ->
                    val o = off + 12 + it * step
                    val v = ByteArrayWrapperExt.readInt4(data, o)
                    val flag = ByteArrayWrapperExt.readInt1(data, o + 8)
                    val graph = decodeString(data, o + 4)
                    val s = if ((flag and 0x2) != 0) {
                        AOPConstant(query, DictionaryValueHelper.fromByteArray(data, o + 9))
                    } else {
                        AOPVariable(query, decodeString(data, ByteArrayWrapperExt.readInt4(data, o + 9)))
                    }
                    val p = if ((flag and 0x4) != 0) {
                        AOPConstant(query, DictionaryValueHelper.fromByteArray(data, o + 9 + steph))
                    } else {
                        AOPVariable(query, decodeString(data, ByteArrayWrapperExt.readInt4(data, o + 9 + steph)))
                    }
                    val oo = if ((flag and 0x8) != 0) {
                        AOPConstant(query, DictionaryValueHelper.fromByteArray(data, o + 9 + steph + steph))
                    } else {
                        AOPVariable(query, decodeString(data, ByteArrayWrapperExt.readInt4(data, o + 9 + steph + steph)))
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
