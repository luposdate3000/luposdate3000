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
import lupos.operator.arithmetik.singleinput.AOPAggregationCOUNT
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
public typealias BinaryToAOPBaseMap = (query: Query, data: ByteArrayWrapper, offset: Int) -> AOPBase
public typealias AOPBaseToBinaryMap = (op: AOPBase, data: ByteArrayWrapper, mapping: MutableMap<String, Int>) -> Int/*offset*/

public object BinaryToOPBase {
    public var operatorArithmetikMapDecode: Array<BinaryToAOPBaseMap?> = Array(0) { null }
    public var operatorArithmetikMapEncode: Array<AOPBaseToBinaryMap?> = Array(0) { null }
    public var operatorPhysicalMapDecode: Array<BinaryToOPBaseMap?> = Array(0) { null }
    public var operatorPhysicalMapEncode: Array<OPBaseToBinaryMap?> = Array(0) { null }
    public fun assignOperatorPhysicalDecode(operatorIDs: IntArray, operator: BinaryToOPBaseMap) {
        for (operatorID in operatorIDs) {
            assignOperatorPhysicalDecode(operatorID, operator)
        }
    }

    public fun assignOperatorPhysicalDecode(operatorID: Int, operator: BinaryToOPBaseMap) {
        if (operatorPhysicalMapDecode.size <= operatorID) {
            var s = operatorPhysicalMapDecode.size
            if (s < 16) {
                s = 16
            }
            while (s < operatorID) {
                s = s * 2
            }
            val tmp = Array<BinaryToOPBaseMap?>(s) { null }
            operatorPhysicalMapDecode.copyInto(tmp)
            operatorPhysicalMapDecode = tmp
        }
        operatorPhysicalMapDecode[operatorID] = operator
    }

    public fun assignOperatorPhysicalEncode(operatorIDs: IntArray, operator: OPBaseToBinaryMap) {
        for (operatorID in operatorIDs) {
            assignOperatorPhysicalEncode(operatorID, operator)
        }
    }

    public fun assignOperatorPhysicalEncode(operatorID: Int, operator: OPBaseToBinaryMap) {
        if (operatorPhysicalMapEncode.size <= operatorID) {
            var s = operatorPhysicalMapEncode.size
            if (s < 16) {
                s = 16
            }
            while (s < operatorID) {
                s = s * 2
            }
            val tmp = Array<OPBaseToBinaryMap?>(s) { null }
            operatorPhysicalMapEncode.copyInto(tmp)
            operatorPhysicalMapEncode = tmp
        }
        operatorPhysicalMapEncode[operatorID] = operator
    }

    public fun assignOperatorPhysical(operatorID: Int, encode: OPBaseToBinaryMap, decode: BinaryToOPBaseMap) {
        assignOperatorPhysicalEncode(operatorID, encode)
        assignOperatorPhysicalDecode(operatorID, decode)
    }

    public fun assignOperatorArithmetikDecode(operatorIDs: IntArray, operator: BinaryToAOPBaseMap) {
        for (operatorID in operatorIDs) {
            assignOperatorArithmetikDecode(operatorID, operator)
        }
    }

    public fun assignOperatorArithmetikDecode(operatorID: Int, operator: BinaryToAOPBaseMap) {
        if (operatorArithmetikMapDecode.size <= operatorID) {
            var s = operatorArithmetikMapDecode.size
            if (s < 16) {
                s = 16
            }
            while (s < operatorID) {
                s = s * 2
            }
            val tmp = Array<BinaryToAOPBaseMap?>(s) { null }
            operatorArithmetikMapDecode.copyInto(tmp)
            operatorArithmetikMapDecode = tmp
        }
        operatorArithmetikMapDecode[operatorID] = operator
    }

    public fun assignOperatorArithmetikEncode(operatorIDs: IntArray, operator: AOPBaseToBinaryMap) {
        for (operatorID in operatorIDs) {
            assignOperatorArithmetikEncode(operatorID, operator)
        }
    }

    public fun assignOperatorArithmetikEncode(operatorID: Int, operator: AOPBaseToBinaryMap) {
        if (operatorArithmetikMapEncode.size <= operatorID) {
            var s = operatorArithmetikMapEncode.size
            if (s < 16) {
                s = 16
            }
            while (s < operatorID) {
                s = s * 2
            }
            val tmp = Array<AOPBaseToBinaryMap?>(s) { null }
            operatorArithmetikMapEncode.copyInto(tmp)
            operatorArithmetikMapEncode = tmp
        }
        operatorArithmetikMapEncode[operatorID] = operator
    }

    public fun assignOperatorArithmetik(operatorID: Int, encode: AOPBaseToBinaryMap, decode: BinaryToAOPBaseMap) {
        assignOperatorArithmetikEncode(operatorID, encode)
        assignOperatorArithmetikDecode(operatorID, decode)
    }

    public fun convertToByteArray(op: IOPBase): ByteArrayWrapper {
        println("convertToByteArray ... start")
        val mapping = mutableMapOf<String, Int>()
        val data = ByteArrayWrapper()
        if (op is OPBaseCompound) {
            ByteArrayWrapperExt.setSize(data, 5 + 8 * op.children.size + op.columnProjectionOrder.map { it.size }.sum() * 4, false)
            ByteArrayWrapperExt.writeInt1(data, 0, 0x1, { "convertToByteArray.isOPBaseCompound" })
            ByteArrayWrapperExt.writeInt4(data, 1, op.children.size, { "OPBaseCompound.children.size" })
            var o = 5
            for (i in 0 until op.children.size) {
                val k = if (op.columnProjectionOrder.size > i) {
                    op.columnProjectionOrder[i]
                } else {
                    listOf()
                }
                val off = convertToByteArrayHelper(op.children[i], data, Partition(), mapping)
                ByteArrayWrapperExt.writeInt4(data, o, off, { "OPBaseCompound.children[$i]" })
                o += 4
                ByteArrayWrapperExt.writeInt4(data, o, k.size, { "OPBaseCompound.columnProjectionOrder[$i].size" })
                o += 4
                for (j in 0 until k.size) {
                    ByteArrayWrapperExt.writeInt4(data, o, encodeString(k[j], data, mapping), { "OPBaseCompound.columnProjectionOrder[$i][$j]" })
                    o += 4
                }
            }
        } else {
            ByteArrayWrapperExt.setSize(data, 5, false)
            val off = convertToByteArrayHelper(op, data, Partition(), mapping)
            ByteArrayWrapperExt.writeInt1(data, 0, 0x0, { "convertToByteArray.isOPBaseCompound" })
            ByteArrayWrapperExt.writeInt4(data, 1, off, { "OPBase.children[0]" })
        }
        println("convertToByteArray ... finish")
        return data
    }

    public fun convertToIteratorBundle(query: Query, data: ByteArrayWrapper, off: Int = 0): IteratorBundleRoot {
        try {
            println("convertToIteratorBundle ... start")
            if (ByteArrayWrapperExt.readInt1(data, off, { "convertToByteArray.isOPBaseCompound" }) == 0x1) {
                val childCount = ByteArrayWrapperExt.readInt4(data, off + 1, { "OPBaseCompound.children.size" })
                var o = 5
                val res = mutableListOf<Pair<List<String>, IteratorBundle>>()
                for (i in 0 until childCount) {
                    val child = convertToIteratorBundleHelper(query, data, ByteArrayWrapperExt.readInt4(data, o, { "OPBaseCompound.children[$i]" }))
                    o += 4
                    val size = ByteArrayWrapperExt.readInt4(data, o, { "OPBaseCompound.columnProjectionOrder[$i].size" })
                    o += 4
                    val list = mutableListOf<String>()
                    for (j in 0 until size) {
                        list.add(decodeString(data, ByteArrayWrapperExt.readInt4(data, o, { "OPBaseCompound.columnProjectionOrder[$i][$j]" })))
                        o += 4
                    }
                    res.add(list to child)
                }
                println("convertToIteratorBundle ... finish")
                return IteratorBundleRoot(query, res.toTypedArray())
            } else {
                val tmp = convertToIteratorBundleHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 1, { "OPBase.children[0]" }))
                println("convertToIteratorBundle ... finish")
                return IteratorBundleRoot(query, arrayOf(listOf<String>() to tmp))
            }
        } catch (e: Throwable) {
            e.printStackTrace()
            throw e
        }
    }

    private inline fun convertToByteArrayHelper(op: IOPBase, data: ByteArrayWrapper, parent: Partition, mapping: MutableMap<String, Int>): Int {
        if ((op as OPBase).operatorID >= operatorPhysicalMapEncode.size) {
            TODO("convertToByteArrayHelper ${(op as OPBase).operatorID} -> ${EOperatorIDExt.names[(op as OPBase).operatorID]}")
        }
        val encoder = operatorPhysicalMapEncode[(op as OPBase).operatorID]
        if (encoder == null) {
            TODO("convertToByteArrayHelper ${(op as OPBase).operatorID} -> ${EOperatorIDExt.names[(op as OPBase).operatorID]}")
        }
        return encoder(op, data, parent, mapping)
    }

    private inline fun convertToIteratorBundleHelper(query: Query, data: ByteArrayWrapper, off: Int): IteratorBundle {
        val type = ByteArrayWrapperExt.readInt4(data, off, { "operatorID" })
        if (type >= operatorPhysicalMapDecode.size) {
            TODO("convertToIteratorBundleHelper $type -> ${EOperatorIDExt.names[type]}")
        }
        val decoder = operatorPhysicalMapDecode[type]
        if (decoder == null) {
            TODO("convertToIteratorBundleHelper $type -> ${EOperatorIDExt.names[type]}")
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
                ByteArrayWrapperExt.setSize(data, off + 4 + b.size, true)
                ByteArrayWrapperExt.writeInt4(data, off, b.size, { "encodeString.len" })
                ByteArrayWrapperExt.writeBuf(data, off + 4, b, { "encodeString.data" })
                return off
            }
        }
    }

    private inline fun decodeString(data: ByteArrayWrapper, off: Int): String {
        return ByteArrayWrapperExt.getBuf(data).decodeToString(off + 4, off + 4 + ByteArrayWrapperExt.readInt4(data, off, { "encodeString.len" }))
    }

    private inline fun decodeStringNull(data: ByteArrayWrapper, off: Int): String? {
        if (off < 0) {
            return null
        } else {
            return decodeString(data, off)
        }
    }

    private fun encodeAOP(op: AOPBase, data: ByteArrayWrapper, mapping: MutableMap<String, Int>): Int {
        if (op.operatorID >= operatorArithmetikMapEncode.size) {
            TODO("encodeAOP ${op.operatorID} -> ${EOperatorIDExt.names[op.operatorID]}")
        }
        val encoder = operatorArithmetikMapEncode[op.operatorID]
        if (encoder == null) {
            TODO("encodeAOP ${op.operatorID} -> ${EOperatorIDExt.names[op.operatorID]}")
        }
        return encoder(op, data, mapping)
    }

    private fun decodeAOP(query: Query, data: ByteArrayWrapper, off: Int): AOPBase {
        val type = ByteArrayWrapperExt.readInt4(data, off, { "operatorID" })
        if (type >= operatorArithmetikMapDecode.size) {
            TODO("decodeAOP $type -> ${EOperatorIDExt.names[type]}")
        }
        val decoder = operatorArithmetikMapDecode[type]
        if (decoder == null) {
            TODO("decodeAOP $type -> ${EOperatorIDExt.names[type]}")
        }
        return decoder(query, data, off)
    }

    init {
/*
 EOperatorIDExt.POPDistributedReceiveMultiCountID,
 EOperatorIDExt.POPDistributedReceiveMultiID,
 EOperatorIDExt.POPDistributedReceiveMultiOrderedID,
 EOperatorIDExt.POPDistributedReceiveSingleCountID,
 EOperatorIDExt.POPDistributedReceiveSingleID,
*/

        assignOperatorPhysical(
            EOperatorIDExt.POPProjectionID,
            { op, data, parent, mapping ->
                op as OPBase
                convertToByteArrayHelper(op.children[0], data, parent, mapping)
            },
            { query, data, off ->
                TODO("unreachable")
            },
        )
        assignOperatorPhysical(
            EOperatorIDExt.POPDebugID,
            { op, data, parent, mapping ->
                op as OPBase
                convertToByteArrayHelper(op.children[0], data, parent, mapping)
            },
            { query, data, off ->
                TODO("unreachable")
            },
        )
        assignOperatorPhysical(
            EOperatorIDExt.POPSplitPartitionFromStoreCountID,
            { op, data, parent, mapping ->
                op as OPBase
                convertToByteArrayHelper(op.children[0], data, parent, mapping)
            },
            { query, data, off ->
                TODO("unreachable")
            },
        )
        assignOperatorPhysical(
            EOperatorIDExt.POPSplitPartitionFromStoreID,
            { op, data, parent, mapping ->
                op as OPBase
                convertToByteArrayHelper(op.children[0], data, parent, mapping)
            },
            { query, data, off ->
                TODO("unreachable")
            },
        )
        assignOperatorPhysical(
            EOperatorIDExt.POPSplitMergePartitionFromStoreID,
            { op, data, parent, mapping ->
                op as OPBase
                convertToByteArrayHelper(op.children[0], data, parent, mapping)
            },
            { query, data, off ->
                TODO("unreachable")
            },
        )
        assignOperatorPhysical(
            EOperatorIDExt.POPModifyDataID,
            { op, data, parent, mapping ->
                op as POPModifyData
                val off = ByteArrayWrapperExt.getSize(data)
                ByteArrayWrapperExt.setSize(data, off + 4 + op.data.size * (4 + 3 * DictionaryValueHelper.getSize()), true)
                ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.POPModifyDataID, { "operatorID" })
                ByteArrayWrapperExt.writeInt4(data, off + 4, op.data.size, { "POPModifyData.data.size" })
                var o = off + 8
                var i = 0
                for (t in op.data) {
                    ByteArrayWrapperExt.writeInt4(data, o, encodeString(t.graph, data, mapping), { "POPModifyData.data[$i].graph" })
                    o += 4
                    for (j in 0 until 3) {
                        DictionaryValueHelper.toByteArray(data, o, (t.children[j] as AOPConstant).value, { "POPModifyData.data[$i][$j]" })
                        o += DictionaryValueHelper.getSize()
                    }
                    i++
                }
                off
            },
            { query, data, off ->
                val d = mutableListOf<Pair<String, DictionaryValueTypeArray>>()
                val l = ByteArrayWrapperExt.readInt4(data, off + 4, { "POPModifyData.data.size" })
                var o = off + 8
                for (i in 0 until l) {
                    val arr = DictionaryValueTypeArray(3)
                    for (j in 0 until 3) {
                        arr[j] = DictionaryValueHelper.fromByteArray(data, o + 4 + j * DictionaryValueHelper.getSize(), { "POPModifyData.data[$i][$j]" })
                    }
                    d.add(decodeString(data, ByteArrayWrapperExt.readInt4(data, o, { "POPModifyData.data[$i].graph" })) to arr)
                    o += DictionaryValueHelper.getSize() * 3 + 4
                }
                EvalModifyData(d, query)
            },
        )
        assignOperatorPhysical(
            EOperatorIDExt.POPGraphOperationID,
            { op, data, parent, mapping ->
                op as POPGraphOperation
                val off = ByteArrayWrapperExt.getSize(data)
                ByteArrayWrapperExt.setSize(data, off + 25, true)
                ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.POPGraphOperationID, { "operatorID" })
                ByteArrayWrapperExt.writeInt4(data, off + 4, op.graph1type, { "POPGraphOperation.graph1type" })
                ByteArrayWrapperExt.writeInt4(data, off + 8, op.graph2type, { "POPGraphOperation.graph2type" })
                ByteArrayWrapperExt.writeInt4(data, off + 12, op.action, { "POPGraphOperation.action" })
                ByteArrayWrapperExt.writeInt4(data, off + 16, encodeString(op.graph1iri, data, mapping), { "POPGraphOperation.graph1iri" })
                ByteArrayWrapperExt.writeInt4(data, off + 20, encodeString(op.graph2iri, data, mapping), { "POPGraphOperation.graph2iri" })
                ByteArrayWrapperExt.writeInt1(data, off + 24, if (op.silent) 1 else 0, { "POPGraphOperation.silent" })
                off
            },
            { query, data, off ->
                EvalGraphOperation(
                    ByteArrayWrapperExt.readInt1(data, off + 24, { "POPGraphOperation.silent" }) == 1,
                    ByteArrayWrapperExt.readInt4(data, off + 4, { "POPGraphOperation.graph1type" }),
                    decodeStringNull(data, ByteArrayWrapperExt.readInt4(data, off + 16, { "POPGraphOperation.graph1iri" })),
                    ByteArrayWrapperExt.readInt4(data, off + 8, { "POPGraphOperation.graph2type" }),
                    decodeStringNull(data, ByteArrayWrapperExt.readInt4(data, off + 20, { "POPGraphOperation.graph2iri" })),
                    ByteArrayWrapperExt.readInt4(data, off + 12, { "POPGraphOperation.action" }),
                    query,
                )
            },
        )
        assignOperatorPhysical(
            EOperatorIDExt.POPNothingID,
            { op, data, parent, mapping ->
                op as POPNothing
                val n = op.getProvidedVariableNames()
                val off = ByteArrayWrapperExt.getSize(data)
                ByteArrayWrapperExt.setSize(data, off + 8 + 4 * n.size, true)
                ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.POPNothingID, { "operatorID" })
                ByteArrayWrapperExt.writeInt4(data, off + 4, n.size, { "POPNothing.size" })
                var i = 0
                for (s in n) {
                    ByteArrayWrapperExt.writeInt4(data, off + 8 + i * 4, encodeString(s, data, mapping), { "POPNothing.data[$i]" })
                    i++
                }
                off
            },
            { query, data, off ->
                val len = ByteArrayWrapperExt.readInt4(data, off + 4, { "POPNothing.size" })
                val list = mutableListOf<String>()
                for (i in 0 until len) {
                    list.add(decodeString(data, ByteArrayWrapperExt.readInt4(data, off + 8 + 4 * i, { "POPNothing.data[$i]" })))
                }
                EvalNothing(list)
            },
        )
        assignOperatorPhysicalEncode(
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
                    ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.POPValuesID, { "operatorID" })
                    ByteArrayWrapperExt.writeInt4(data, off + 4, op.data.size, { "POPValues.columns.size" })
                    ByteArrayWrapperExt.writeInt4(data, off + 8, size, { "POPValues.rows.size" })
                    var o = off + 12
                    var column = 0
                    for ((col, rows) in op.data) {
                        ByteArrayWrapperExt.writeInt4(data, o, encodeString(col, data, mapping), { "POPValues.column[$column]" })
                        o += 4
                        var i = 0
                        for (row in rows) {
                            i++
                            DictionaryValueHelper.toByteArray(data, o, row, { "POPValues.data[$column][$i]" })
                            o += DictionaryValueHelper.getSize()
                        }
                        SanityCheck.check(
                            { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_factory/src/commonMain/kotlin/lupos/operator/factory/BinaryToOPBase.kt:512"/*SOURCE_FILE_END*/ },
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
                    dd[decodeString(data, ByteArrayWrapperExt.readInt4(data, o, { "POPValues.column[$c]" }))] = rows
                    o += 4
                    for (i in 0 until row_count) {
                        rows.add(DictionaryValueHelper.fromByteArray(data, o, { "POPValues.data[$c][$i]" }))
                        o += DictionaryValueHelper.getSize()
                    }
                }
                EvalValues(dd)
            },
        )

        assignOperatorPhysical(
            EOperatorIDExt.POPEmptyRowID,
            { op, data, parent, mapping ->
                val off = ByteArrayWrapperExt.getSize(data)
                ByteArrayWrapperExt.setSize(data, off + 4, true)
                ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.POPEmptyRowID, { "operatorID" })
                off
            },
            { query, data, off ->
                IteratorBundle(1)
            },
        )
        assignOperatorPhysical(
            EOperatorIDExt.POPUnionID,
            { op, data, parent, mapping ->
                op as POPUnion
                val child0 = convertToByteArrayHelper(op.children[0], data, parent, mapping)
                val child1 = convertToByteArrayHelper(op.children[1], data, parent, mapping)
                val off = ByteArrayWrapperExt.getSize(data)
                ByteArrayWrapperExt.setSize(data, off + 16 + 4 * op.projectedVariables.size, true)
                ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.POPUnionID, { "operatorID" })
                ByteArrayWrapperExt.writeInt4(data, off + 4, child0, { "POPUnion.child0" })
                ByteArrayWrapperExt.writeInt4(data, off + 8, child1, { "POPUnion.child1" })
                ByteArrayWrapperExt.writeInt4(data, off + 12, op.projectedVariables.size, { "POPUnion.variables.size" })
                var o = off + 16
                var i = 0
                for (s in op.projectedVariables) {
                    ByteArrayWrapperExt.writeInt4(data, o, encodeString(s, data, mapping), { "POPUnion.variables[$i]" })
                    o += 4
                    i++
                }
                off
            },
            { query, data, off ->
                val child0 = convertToIteratorBundleHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "POPUnion.child0" }))
                val child1 = convertToIteratorBundleHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 8, { "POPUnion.child1" }))
                val l = ByteArrayWrapperExt.readInt4(data, off + 12, { "POPUnion.variables.size" })
                var projectedVariables = mutableListOf<String>()
                for (i in 0 until l) {
                    projectedVariables.add(decodeString(data, ByteArrayWrapperExt.readInt4(data, off + 16 + 4 * i, { "POPUnion.variables[$i]" })))
                }
                EvalUnion(child0, child1, projectedVariables)
            },
        )
        assignOperatorPhysical(
            EOperatorIDExt.POPMinusID,
            { op, data, parent, mapping ->
                op as POPMinus
                val child0 = convertToByteArrayHelper(op.children[0], data, parent, mapping)
                val child1 = convertToByteArrayHelper(op.children[1], data, parent, mapping)
                val off = ByteArrayWrapperExt.getSize(data)
                ByteArrayWrapperExt.setSize(data, off + 16 + 4 * op.projectedVariables.size, true)
                ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.POPMinusID, { "operatorID" })
                ByteArrayWrapperExt.writeInt4(data, off + 4, child0, { "POPMinus.child0" })
                ByteArrayWrapperExt.writeInt4(data, off + 8, child1, { "POPMinus.child1" })
                ByteArrayWrapperExt.writeInt4(data, off + 12, op.projectedVariables.size, { "POPMinus.variables.size" })
                var o = off + 16
                var i = 0
                for (s in op.projectedVariables) {
                    ByteArrayWrapperExt.writeInt4(data, o, encodeString(s, data, mapping), { "POPMinus.variables[$i]" })
                    o += 4
                    i++
                }
                off
            },
            { query, data, off ->
                val child0 = convertToIteratorBundleHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "POPMinus.child0" }))
                val child1 = convertToIteratorBundleHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 8, { "POPMinus.child1" }))
                val l = ByteArrayWrapperExt.readInt4(data, off + 12, { "POPMinus.variables.size" })
                var projectedVariables = mutableListOf<String>()
                for (i in 0 until l) {
                    projectedVariables.add(decodeString(data, ByteArrayWrapperExt.readInt4(data, off + 16 + 4 * i, { "POPMinus.variables[$i]" })))
                }
                EvalMinus(child0, child1, projectedVariables)
            },
        )
        assignOperatorPhysical(
            EOperatorIDExt.POPJoinMergeSingleColumnID,
            { op, data, parent, mapping ->
                op as POPJoinMergeSingleColumn
                val child0 = convertToByteArrayHelper(op.children[0], data, parent, mapping)
                val child1 = convertToByteArrayHelper(op.children[1], data, parent, mapping)
                val off = ByteArrayWrapperExt.getSize(data)
                ByteArrayWrapperExt.setSize(data, off + 12, true)
                ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.POPJoinMergeSingleColumnID, { "operatorID" })
                ByteArrayWrapperExt.writeInt4(data, off + 4, child0, { "POPJoinMergeSingleColumn.child0" })
                ByteArrayWrapperExt.writeInt4(data, off + 8, child1, { "POPJoinMergeSingleColumn.child1" })
                off
            },
            { query, data, off ->
                val child0 = convertToIteratorBundleHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "POPJoinMergeSingleColumn.child0" }))
                val child1 = convertToIteratorBundleHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 8, { "POPJoinMergeSingleColumn.child1" }))
                EvalJoinMergeSingleColumn(child0, child1)
            },
        )
        assignOperatorPhysical(
            EOperatorIDExt.POPJoinMergeOptionalID,
            { op, data, parent, mapping ->
                op as POPJoinMergeOptional
                val child0 = convertToByteArrayHelper(op.children[0], data, parent, mapping)
                val child1 = convertToByteArrayHelper(op.children[1], data, parent, mapping)
                val off = ByteArrayWrapperExt.getSize(data)
                ByteArrayWrapperExt.setSize(data, off + 16 + 4 * op.projectedVariables.size, true)
                ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.POPJoinMergeOptionalID, { "operatorID" })
                ByteArrayWrapperExt.writeInt4(data, off + 4, child0, { "POPJoinMergeOptional.child0" })
                ByteArrayWrapperExt.writeInt4(data, off + 8, child1, { "POPJoinMergeOptional.child1" })
                ByteArrayWrapperExt.writeInt4(data, off + 12, op.projectedVariables.size, { "POPJoinMergeOptional.variables.size" })
                var o = off + 16
                var i = 0
                for (s in op.projectedVariables) {
                    ByteArrayWrapperExt.writeInt4(data, o, encodeString(s, data, mapping), { "POPJoinMergeOptional.variables[$i]" })
                    o += 4
                    i++
                }
                off
            },
            { query, data, off ->
                val child0 = convertToIteratorBundleHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "POPJoinMergeOptional.child0" }))
                val child1 = convertToIteratorBundleHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 8, { "POPJoinMergeOptional.child1" }))
                val l = ByteArrayWrapperExt.readInt4(data, off + 12, { "POPJoinMergeOptional.variables.size" })
                var projectedVariables = mutableListOf<String>()
                for (i in 0 until l) {
                    projectedVariables.add(decodeString(data, ByteArrayWrapperExt.readInt4(data, off + 16 + 4 * i, { "POPJoinMergeOptional.variables[$i]" })))
                }
                EvalJoinMergeOptional(arrayOf(child0, child1), projectedVariables)
            },
        )
        assignOperatorPhysical(
            EOperatorIDExt.POPJoinMergeID,
            { op, data, parent, mapping ->
                op as POPJoinMerge
                val child0 = convertToByteArrayHelper(op.children[0], data, parent, mapping)
                val child1 = convertToByteArrayHelper(op.children[1], data, parent, mapping)
                val off = ByteArrayWrapperExt.getSize(data)
                ByteArrayWrapperExt.setSize(data, off + 16 + 4 * op.projectedVariables.size, true)
                ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.POPJoinMergeID, { "operatorID" })
                ByteArrayWrapperExt.writeInt4(data, off + 4, child0, { "POPJoinMerge.child0" })
                ByteArrayWrapperExt.writeInt4(data, off + 8, child1, { "POPJoinMerge.child1" })
                ByteArrayWrapperExt.writeInt4(data, off + 12, op.projectedVariables.size, { "POPJoinMerge.variables.size" })
                var o = off + 16
                var i = 0
                for (s in op.projectedVariables) {
                    ByteArrayWrapperExt.writeInt4(data, o, encodeString(s, data, mapping), { "POPJoinMerge.variables[$i]" })
                    o += 4
                    i++
                }
                off
            },
            { query, data, off ->
                val child0 = convertToIteratorBundleHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "POPJoinMerge.child0" }))
                val child1 = convertToIteratorBundleHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 8, { "POPJoinMerge.child1" }))
                val l = ByteArrayWrapperExt.readInt4(data, off + 12, { "POPJoinMerge.variables.size" })
                var projectedVariables = mutableListOf<String>()
                for (i in 0 until l) {
                    projectedVariables.add(decodeString(data, ByteArrayWrapperExt.readInt4(data, off + 16 + 4 * i, { "POPJoinMerge.variables[$i]" })))
                }
                EvalJoinMerge(child0, child1, projectedVariables)
            },
        )
        assignOperatorPhysical(
            EOperatorIDExt.POPJoinHashMapID,
            { op, data, parent, mapping ->
                op as POPJoinHashMap
                val child0 = convertToByteArrayHelper(op.children[0], data, parent, mapping)
                val child1 = convertToByteArrayHelper(op.children[1], data, parent, mapping)
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
                    ByteArrayWrapperExt.writeInt4(data, o, encodeString(s, data, mapping), { "POPJoinHashMap.variables[$i]" })
                    o += 4
                    i++
                }
                off
            },
            { query, data, off ->
                val child0 = convertToIteratorBundleHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "POPJoinHashMap.child0" }))
                val child1 = convertToIteratorBundleHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 8, { "POPJoinHashMap.child1" }))
                val l = ByteArrayWrapperExt.readInt4(data, off + 13, { "POPJoinHashMap.variables.size" })
                var projectedVariables = mutableListOf<String>()
                for (i in 0 until l) {
                    projectedVariables.add(decodeString(data, ByteArrayWrapperExt.readInt4(data, off + 17 + 4 * i, { "POPJoinHashMap.variables[$i]" })))
                }
                EvalJoinHashMap(child0, child1, ByteArrayWrapperExt.readInt1(data, off + 12, { "POPJoinHashMap.optional" }) == 1, projectedVariables)
            },
        )
        assignOperatorPhysical(
            EOperatorIDExt.POPJoinCartesianProductID,
            { op, data, parent, mapping ->
                op as POPJoinCartesianProduct
                val child0 = convertToByteArrayHelper(op.children[0], data, parent, mapping)
                val child1 = convertToByteArrayHelper(op.children[1], data, parent, mapping)
                val off = ByteArrayWrapperExt.getSize(data)
                ByteArrayWrapperExt.setSize(data, off + 13, true)
                ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.POPJoinCartesianProductID, { "operatorID" })
                ByteArrayWrapperExt.writeInt4(data, off + 4, child0, { "POPJoinCartesianProduct.child0" })
                ByteArrayWrapperExt.writeInt4(data, off + 8, child1, { "POPJoinCartesianProduct.child1" })
                ByteArrayWrapperExt.writeInt1(data, off + 12, if (op.optional) 1 else 0, { "POPJoinCartesianProduct.optional" })
                off
            },
            { query, data, off ->
                val child0 = convertToIteratorBundleHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "POPJoinCartesianProduct.child0" }))
                val child1 = convertToIteratorBundleHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 8, { "POPJoinCartesianProduct.child1" }))
                EvalJoinCartesianProduct(child0, child1, ByteArrayWrapperExt.readInt1(data, off + 12, { "POPJoinCartesianProduct.optional" }) == 1)
            },
        )
        assignOperatorPhysical(
            EOperatorIDExt.POPLimitID,
            { op, data, parent, mapping ->
                op as POPLimit
                val child = convertToByteArrayHelper(op.children[0], data, parent, mapping)
                val off = ByteArrayWrapperExt.getSize(data)
                ByteArrayWrapperExt.setSize(data, off + 12, true)
                ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.POPLimitID, { "operatorID" })
                ByteArrayWrapperExt.writeInt4(data, off + 4, child, { "POPLimit.child" })
                ByteArrayWrapperExt.writeInt4(data, off + 8, op.limit, { "POPLimit.limit" })
                off
            },
            { query, data, off ->
                val child = convertToIteratorBundleHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "POPLimit.child" }))
                EvalLimit(child, ByteArrayWrapperExt.readInt4(data, off + 8, { "POPLimit.limit" }))
            },
        )
        assignOperatorPhysical(
            EOperatorIDExt.POPSortID,
            { op, data, parent, mapping ->
                op as POPSort
                if (op.getProvidedVariableNames().size == 0) {
                    convertToByteArrayHelper(op.children[0], data, parent, mapping)
                } else {
                    val child = convertToByteArrayHelper(op.children[0], data, parent, mapping)
                    val off = ByteArrayWrapperExt.getSize(data)
                    ByteArrayWrapperExt.setSize(data, off + 21 + 4 * (op.mySortPriority.size + op.getProvidedVariableNames().size + op.sortBy.size), true)
                    ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.POPSortID, { "operatorID" })
                    ByteArrayWrapperExt.writeInt4(data, off + 4, child, { "POPSort.child" })
                    ByteArrayWrapperExt.writeInt4(data, off + 8, op.mySortPriority.size, { "POPSort.sp.size" })
                    ByteArrayWrapperExt.writeInt4(data, off + 12, op.getProvidedVariableNames().size, { "POPSort.p.size" })
                    ByteArrayWrapperExt.writeInt4(data, off + 16, op.sortBy.size, { "POPSort.sb.size" })
                    ByteArrayWrapperExt.writeInt1(data, off + 20, if (op.sortOrder) 1 else 0, { "POPSort.sortOrder" })
                    var o = 21
                    var i = 0
                    for (s in op.mySortPriority.map { it.variableName }) {
                        ByteArrayWrapperExt.writeInt4(data, o, encodeString(s, data, mapping), { "POPSort.sp[$i]" })
                        o += 4
                        i++
                    }
                    i = 0
                    for (s in op.sortBy.map { it.name }) {
                        ByteArrayWrapperExt.writeInt4(data, o, encodeString(s, data, mapping), { "POPSort.sb[$i]" })
                        o += 4
                        i++
                    }
                    i = 0
                    for (s in op.getProvidedVariableNames()) {
                        ByteArrayWrapperExt.writeInt4(data, o, encodeString(s, data, mapping), { "POPSort.p[$i]" })
                        o += 4
                        i++
                    }
                    off
                }
            },
            { query, data, off ->
                val child = convertToIteratorBundleHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "POPSort.child" }))
                val splen = ByteArrayWrapperExt.readInt4(data, off + 8, { "POPSort.sp.size" })
                val plen = ByteArrayWrapperExt.readInt4(data, off + 12, { "POPSort.p.size" })
                val sblen = ByteArrayWrapperExt.readInt4(data, off + 16, { "POPSort.sb.size" })
                val sortOrder = ByteArrayWrapperExt.readInt1(data, off + 20, { "POPSort.sortOrder" }) != 0
                var o = 21
                val spList = mutableListOf<String>()
                val pList = mutableListOf<String>()
                val sbList = mutableListOf<String>()
                for (i in 0 until splen) {
                    spList.add(decodeString(data, ByteArrayWrapperExt.readInt4(data, o, { "POPSort.sp[$i]" })))
                    o += 4
                }
                for (i in 0 until sblen) {
                    sbList.add(decodeString(data, ByteArrayWrapperExt.readInt4(data, o, { "POPSort.sb[$i]" })))
                    o += 4
                }
                for (i in 0 until plen) {
                    pList.add(decodeString(data, ByteArrayWrapperExt.readInt4(data, o, { "POPSort.p[$i]" })))
                    o += 4
                }
                EvalSort(child, spList, query, sbList.toTypedArray(), pList, sortOrder)
            },
        )
        assignOperatorPhysical(
            EOperatorIDExt.POPOffsetID,
            { op, data, parent, mapping ->
                op as POPOffset
                val child = convertToByteArrayHelper(op.children[0], data, parent, mapping)
                val off = ByteArrayWrapperExt.getSize(data)
                ByteArrayWrapperExt.setSize(data, off + 12, true)
                ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.POPOffsetID, { "operatorID" })
                ByteArrayWrapperExt.writeInt4(data, off + 4, child, { "POPOffset.child" })
                ByteArrayWrapperExt.writeInt4(data, off + 8, op.offset, { "POPOffset.offset" })
                off
            },
            { query, data, off ->
                val child = convertToIteratorBundleHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "POPOffset.child" }))
                EvalOffset(child, ByteArrayWrapperExt.readInt4(data, off + 8, { "POPOffset.offset" }))
            },
        )
        assignOperatorPhysical(
            EOperatorIDExt.POPMakeBooleanResultID,
            { op, data, parent, mapping ->
                op as POPMakeBooleanResult
                val child = convertToByteArrayHelper(op.children[0], data, parent, mapping)
                val off = ByteArrayWrapperExt.getSize(data)
                ByteArrayWrapperExt.setSize(data, off + 8, true)
                ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.POPMakeBooleanResultID, { "operatorID" })
                ByteArrayWrapperExt.writeInt4(data, off + 4, child, { "POPMakeBooleanResult.child" })
                off
            },
            { query, data, off ->
                val child = convertToIteratorBundleHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "POPMakeBooleanResult.child" }))
                EvalMakeBooleanResult(child)
            },
        )
        assignOperatorPhysical(
            EOperatorIDExt.POPReducedID,
            { op, data, parent, mapping ->
                op as POPReduced
                val child = convertToByteArrayHelper(op.children[0], data, parent, mapping)
                val off = ByteArrayWrapperExt.getSize(data)
                ByteArrayWrapperExt.setSize(data, off + 12, true)
                ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.POPReducedID, { "operatorID" })
                ByteArrayWrapperExt.writeInt4(data, off + 4, child, { "POPReduced.child" })
                ByteArrayWrapperExt.writeInt4(data, off + 8, op.projectedVariables.size, { "POPReduced.variables.size" })
                off
            },
            { query, data, off ->
                val child = convertToIteratorBundleHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "POPReduced.child" }))
                EvalReduced(child, ByteArrayWrapperExt.readInt4(data, off + 8, { "POPReduced.variables.size" }))
            },
        )
        assignOperatorPhysical(
            EOperatorIDExt.POPTripleStoreIterator,
            { op, data, parent, mapping ->
                op as POPTripleStoreIterator
                val off = ByteArrayWrapperExt.getSize(data)
                ByteArrayWrapperExt.setSize(data, off + 17 + 3 * if (DictionaryValueHelper.getSize() > 4) DictionaryValueHelper.getSize() else 4, true)
                val target = op.getTarget(parent)
                ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.POPTripleStoreIterator, { "operatorID" })
                ByteArrayWrapperExt.writeInt4(data, off + 4, encodeString(target.first, data, mapping), { "POPTripleStoreIterator.target.first" })
                ByteArrayWrapperExt.writeInt4(data, off + 8, encodeString(target.second, data, mapping), { "POPTripleStoreIterator.target.second" })
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
                            ByteArrayWrapperExt.writeInt4(data, o, encodeString(child.getName(), data, mapping), { "POPTripleStoreIterator.variable[$i]" })
                            o += 4
                        }
                    }
                }
                ByteArrayWrapperExt.writeInt1(data, off + 16, childFlag, { "POPTripleStoreIterator.childFlag" })
                off
            },
            { query, data, off ->
                val buf1 = decodeString(data, ByteArrayWrapperExt.readInt4(data, off + 4, { "POPTripleStoreIterator.target.first" }))
                val buf2 = decodeString(data, ByteArrayWrapperExt.readInt4(data, off + 8, { "POPTripleStoreIterator.target.second" }))
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
                    val res = decodeString(data, ByteArrayWrapperExt.readInt4(data, o, { "POPTripleStoreIterator.variable[0]" }))
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
                    val res = decodeString(data, ByteArrayWrapperExt.readInt4(data, o, { "POPTripleStoreIterator.variable[1]" }))
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
                    val res = decodeString(data, ByteArrayWrapperExt.readInt4(data, o, { "POPTripleStoreIterator.variable[2]" }))
                    o += 4
                    res
                }
                EvalTripleStoreIterator(
                    buf1 to buf2,
                    query,
                    index,
                    arrayOf(child0f to (child0c to child0v), child1f to (child1c to child1v), child2f to (child2c to child2v))
                )
            },
        )
        assignOperatorPhysical(
            EOperatorIDExt.POPBindID,
            { op, data, parent, mapping ->
                op as POPBind
                val variablesOut = op.getProvidedVariableNames()
                val child = convertToByteArrayHelper(op.children[0], data, parent, mapping)
                val off = ByteArrayWrapperExt.getSize(data)
                ByteArrayWrapperExt.setSize(data, off + 20 + variablesOut.size * 4, true)
                ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.POPBindID, { "operatorID" })
                ByteArrayWrapperExt.writeInt4(data, off + 4, child, { "POPBind.child" })
                ByteArrayWrapperExt.writeInt4(data, off + 8, encodeAOP(op.children[1] as AOPBase, data, mapping), { "POPBind.value" })
                ByteArrayWrapperExt.writeInt4(data, off + 12, encodeString(op.name.name, data, mapping), { "POPBind.column" })
                ByteArrayWrapperExt.writeInt4(data, off + 16, variablesOut.size, { "POPBind.variables.size" })
                for (i in 0 until variablesOut.size) {
                    ByteArrayWrapperExt.writeInt4(data, off + 20 + 4 * i, encodeString(variablesOut[i], data, mapping), { "POPBind.variables[$i]" })
                }
                off
            },
            { query, data, off ->
                val child = convertToIteratorBundleHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "POPBind.child" }))
                val variablesOut = mutableListOf<String>()
                val len = ByteArrayWrapperExt.readInt4(data, off + 16, { "POPBind.variables.size" })
                for (i in 0 until len) {
                    variablesOut.add(decodeString(data, ByteArrayWrapperExt.readInt4(data, off + 20 + i * 4, { "POPBind.variables[$i]" })))
                }
                EvalBind(child, variablesOut, decodeString(data, ByteArrayWrapperExt.readInt4(data, off + 12, { "POPBind.column" })), decodeAOP(query, data, ByteArrayWrapperExt.readInt4(data, off + 8, { "POPBind.value" })))
            },
        )
        assignOperatorPhysical(
            EOperatorIDExt.POPFilterID,
            { op, data, parent, mapping ->
                op as POPFilter
                val variablesOut = op.getProvidedVariableNames()
                val child = convertToByteArrayHelper(op.children[0], data, parent, mapping)
                val off = ByteArrayWrapperExt.getSize(data)
                ByteArrayWrapperExt.setSize(data, off + 16 + 4 * variablesOut.size, true)
                ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.POPFilterID, { "operatorID" })
                ByteArrayWrapperExt.writeInt4(data, off + 4, child, { "POPFilter.child" })
                ByteArrayWrapperExt.writeInt4(data, off + 8, encodeAOP(op.children[1] as AOPBase, data, mapping), { "POPFilter.filter" })
                ByteArrayWrapperExt.writeInt4(data, off + 12, variablesOut.size, { "POPFilter.variables.size" })
                for (i in 0 until variablesOut.size) {
                    ByteArrayWrapperExt.writeInt4(data, off + 16 + 4 * i, encodeString(variablesOut[i], data, mapping), { "POPFilter.variables[$i]" })
                }
                off
            },
            { query, data, off ->
                val child = convertToIteratorBundleHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "POPFilter.child" }))
                val variablesOut = mutableListOf<String>()
                val len = ByteArrayWrapperExt.readInt4(data, off + 12, { "POPFilter.variables.size" })
                for (i in 0 until len) {
                    variablesOut.add(decodeString(data, ByteArrayWrapperExt.readInt4(data, off + 16 + i * 4, { "POPFilter.variables[$i]" })))
                }
                EvalFilter(child, variablesOut, decodeAOP(query, data, ByteArrayWrapperExt.readInt4(data, off + 8, { "POPFilter.filter" })))
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
            { op, data, parent, mapping ->
                op as POPGroup
                val keyColumnNames = op.by.map { it.name }
                val child = convertToByteArrayHelper(op.children[0], data, parent, mapping)
                val off = ByteArrayWrapperExt.getSize(data)
                var done = false
                if (op.by.isEmpty()) {
                    ByteArrayWrapperExt.setSize(data, off + 20 + 4 * (keyColumnNames.size + op.projectedVariables.size + op.bindings.size * 2), true)
                    ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.POPGroupWithoutKeyColumnID, { "operatorID" })
                    ByteArrayWrapperExt.writeInt4(data, off + 4, child, { "POPGroupWithoutKeyColumn.child" })
                    ByteArrayWrapperExt.writeInt4(data, off + 8, op.projectedVariables.size, { "POPGroupWithoutKeyColumn.variables.size" })
                    ByteArrayWrapperExt.writeInt4(data, off + 12, op.bindings.size, { "POPGroupWithoutKeyColumn.bindings.size" })
                    var o = 16
                    var i = 0
                    for (s in op.projectedVariables) {
                        ByteArrayWrapperExt.writeInt4(data, o, encodeString(s, data, mapping), { "POPGroupWithoutKeyColumn.variables[$i]" })
                        o += 4
                        i++
                    }
                    i = 0
                    for ((k, v) in op.bindings) {
                        ByteArrayWrapperExt.writeInt4(data, o, encodeString(k, data, mapping), { "POPGroupWithoutKeyColumn.binding[$i].name" })
                        o += 4
                        ByteArrayWrapperExt.writeInt4(data, o, encodeAOP(v, data, mapping), { "POPGroupWithoutKeyColumn.binding[$i].value" })
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
                    var o = 20
                    var i = 0
                    for (s in op.projectedVariables) {
                        ByteArrayWrapperExt.writeInt4(data, o, encodeString(s, data, mapping), { "POPGroupSorted.variables[$i]" })
                        o += 4
                        i++
                    }
                    i = 0
                    for (s in keyColumnNames) {
                        ByteArrayWrapperExt.writeInt4(data, o, encodeString(s, data, mapping), { "POPGroupSorted.keys[$i]" })
                        o += 4
                        i++
                    }
                    i = 0
                    for ((k, v) in op.bindings) {
                        ByteArrayWrapperExt.writeInt4(data, o, encodeString(k, data, mapping), { "POPGroupSorted.bindings[$i].name" })
                        o += 4
                        ByteArrayWrapperExt.writeInt4(data, o, encodeAOP(v, data, mapping), { "POPGroupSorted.bindings[$i].value" })
                        o += 4
                        i++
                    }
                    done = true
                } else if (op.isCountOnly()) {
                    if (op.by.size == 0) {
                        ByteArrayWrapperExt.setSize(data, off + 12, true)
                        ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.POPGroupCount0ID, { "operatorID" })
                        ByteArrayWrapperExt.writeInt4(data, off + 4, child, { "POPGroupCount0.child" })
                        ByteArrayWrapperExt.writeInt4(data, off + 8, encodeString(op.bindings.toList().first().first, data, mapping), { "POPGroupCount0.column" })
                        done = true
                    } else if (op.by.size == 1) {
                        ByteArrayWrapperExt.setSize(data, off + 16, true)
                        ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.POPGroupCount1ID, { "operatorID" })
                        ByteArrayWrapperExt.writeInt4(data, off + 4, child, { "POPGroupCount1.child" })
                        ByteArrayWrapperExt.writeInt4(data, off + 8, encodeString(op.bindings.toList().first().first, data, mapping), { "POPGroupCount1.column" })
                        ByteArrayWrapperExt.writeInt4(data, off + 12, encodeString(op.by[0].name, data, mapping), { "POPGroupCount1.by" })
                        done = true
                    }
                }
                if (!done) {
                    ByteArrayWrapperExt.setSize(data, off + 16 + 4 * (keyColumnNames.size + op.projectedVariables.size + op.bindings.size * 2), true)
                    ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.POPGroupID, { "operatorID" })
                    ByteArrayWrapperExt.writeInt4(data, off + 4, child, { "POPGroup.child" })
                    ByteArrayWrapperExt.writeInt4(data, off + 8, op.bindings.size, { "POPGroup.bindings.size" })
                    ByteArrayWrapperExt.writeInt4(data, off + 12, keyColumnNames.size, { "POPGroup.keys.size" })
                    var o = 16
                    var i = 0
                    for (s in keyColumnNames) {
                        ByteArrayWrapperExt.writeInt4(data, o, encodeString(s, data, mapping), { "POPGroup.keys[$i]" })
                        o += 4
                        i++
                    }
                    i = 0
                    for ((k, v) in op.bindings) {
                        ByteArrayWrapperExt.writeInt4(data, o, encodeString(k, data, mapping), { "POPGroup.bindings[$i].name" })
                        o += 4
                        ByteArrayWrapperExt.writeInt4(data, o, encodeAOP(v, data, mapping), { "POPGroup.bindings[$i].value" })
                        o += 4
                        i++
                    }
                }
                off
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPGroupCount0ID,
            { query, data, off ->
                val child = convertToIteratorBundleHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "POPGroupCount0.child" }))
                val binding = decodeString(data, ByteArrayWrapperExt.readInt4(data, off + 8, { "POPGroupCount0.column" }))
                EvalGroupCount0(child, binding, query.getDictionary())
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPGroupCount1ID,
            { query, data, off ->
                val child = convertToIteratorBundleHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "POPGroupCount1.child" }))
                val binding = decodeString(data, ByteArrayWrapperExt.readInt4(data, off + 8, { "POPGroupCount1.column" }))
                val name = decodeString(data, ByteArrayWrapperExt.readInt4(data, off + 12, { "POPGroupCount1.by" }))
                EvalGroupCount1(child, binding, name, query.getDictionary())
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPGroupSortedID,
            { query, data, off ->
                val child = convertToIteratorBundleHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "POPGroupSorted.child" }))
                val plen = ByteArrayWrapperExt.readInt4(data, off + 8, { "POPGroupSorted.variables.size" })
                val blen = ByteArrayWrapperExt.readInt4(data, off + 12, { "POPGroupSorted.bindings.size" })
                val klen = ByteArrayWrapperExt.readInt4(data, off + 16, { "POPGroupSorted.keys.size" })
                var o = 20
                val projectedVariables = mutableListOf<String>()
                for (i in 0 until plen) {
                    projectedVariables.add(decodeString(data, ByteArrayWrapperExt.readInt4(data, o, { "POPGroupSorted.variables[$i]" })))
                    o += 4
                }
                val keyColumnNames = Array<String>(klen) { "" }
                for (i in 0 until klen) {
                    keyColumnNames[i] = decodeString(data, ByteArrayWrapperExt.readInt4(data, o, { "POPGroupSorted.keys[$i]" }))
                    o += 4
                }
                val bindings = mutableListOf<Pair<String, AOPBase>>()
                for (i in 0 until blen) {
                    val k = decodeString(data, ByteArrayWrapperExt.readInt4(data, o, { "POPGroupSorted.bindings[$i].name" }))
                    o += 4
                    val v = decodeAOP(query, data, ByteArrayWrapperExt.readInt4(data, o, { "POPGroupSorted.bindings[$i].value" }))
                    o += 4
                    bindings.add(k to v)
                }
                EvalGroupSorted(child, bindings, projectedVariables, keyColumnNames)
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPGroupID,
            { query, data, off ->
                val child = convertToIteratorBundleHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "POPGroup.child" }))
                val blen = ByteArrayWrapperExt.readInt4(data, off + 8, { "POPGroup.bindings.size" })
                val klen = ByteArrayWrapperExt.readInt4(data, off + 12, { "POPGroup.keys.size" })
                var o = 16
                val keyColumnNames = Array<String>(klen) { "" }
                for (i in 0 until klen) {
                    keyColumnNames[i] = decodeString(data, ByteArrayWrapperExt.readInt4(data, o, { "POPGroup.keys[$i]" }))
                    o += 4
                }
                val bindings = mutableListOf<Pair<String, AOPBase>>()
                for (i in 0 until blen) {
                    val k = decodeString(data, ByteArrayWrapperExt.readInt4(data, o, { "POPGroup.bindings[$i].name" }))
                    o += 4
                    val v = decodeAOP(query, data, ByteArrayWrapperExt.readInt4(data, o, { "POPGroup.bindings[$i].value" }))
                    o += 4
                    bindings.add(k to v)
                }
                EvalGroup(child, bindings, keyColumnNames)
            },
        )
        assignOperatorPhysicalDecode(
            EOperatorIDExt.POPGroupWithoutKeyColumnID,
            { query, data, off ->
                val child = convertToIteratorBundleHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "POPGroupWithoutKeyColumn.child" }))
                val plen = ByteArrayWrapperExt.readInt4(data, off + 8, { "POPGroupWithoutKeyColumn.variables.size" })
                val blen = ByteArrayWrapperExt.readInt4(data, off + 12, { "POPGroupWithoutKeyColumn.bindings.size" })
                var o = 16
                val projectedVariables = mutableListOf<String>()
                for (i in 0 until plen) {
                    projectedVariables.add(decodeString(data, ByteArrayWrapperExt.readInt4(data, o, { "POPGroupWithoutKeyColumn.variables[$i]" })))
                    o += 4
                }
                val bindings = mutableListOf<Pair<String, AOPBase>>()
                for (i in 0 until blen) {
                    val k = decodeString(data, ByteArrayWrapperExt.readInt4(data, o, { "POPGroupWithoutKeyColumn.bindings[$i].name" }))
                    o += 4
                    val v = decodeAOP(query, data, ByteArrayWrapperExt.readInt4(data, o, { "POPGroupWithoutKeyColumn.bindings[$i].value" }))
                    o += 4
                    bindings.add(k to v)
                }
                EvalGroupWithoutKeyColumn(child, bindings, projectedVariables)
            },
        )
        assignOperatorPhysical(
            EOperatorIDExt.POPModifyID,
            { op, data, parent, mapping ->
                op as POPModify
                val child = convertToByteArrayHelper(op.children[0], data, parent, mapping)
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
                    ByteArrayWrapperExt.writeInt4(data, o + 4, encodeString(k.graph, data, mapping), { "POPModify.modify[$i].graph" })
                    var flag = 0
                    if (k.graphVar) {
                        flag += 0x1
                    }
                    val s = k.children[0]
                    val p = k.children[1]
                    val oo = k.children[2]
                    if (s is AOPConstant) {
                        flag += 0x2
                        DictionaryValueHelper.toByteArray(data, o + 9, s.value, { "POPModify.modify[$i].child[0].c" })
                    } else {
                        s as AOPVariable
                        ByteArrayWrapperExt.writeInt4(data, o + 9, encodeString(s.name, data, mapping), { "POPModify.modify[$i].child[0].v" })
                    }
                    if (p is AOPConstant) {
                        flag += 0x4
                        DictionaryValueHelper.toByteArray(data, o + 9 + steph, p.value, { "POPModify.modify[$i].child[1].c" })
                    } else {
                        p as AOPVariable
                        ByteArrayWrapperExt.writeInt4(data, o + 9 + steph, encodeString(p.name, data, mapping), { "POPModify.modify[$i].child[1].v" })
                    }
                    if (oo is AOPConstant) {
                        flag += 0x8
                        DictionaryValueHelper.toByteArray(data, o + 9 + steph + steph, oo.value, { "POPModify.modify[$i].child[2].c" })
                    } else {
                        oo as AOPVariable
                        ByteArrayWrapperExt.writeInt4(data, o + 9 + steph + steph, encodeString(oo.name, data, mapping), { "POPModify.modify[$i].child[2].v" })
                    }
                    ByteArrayWrapperExt.writeInt1(data, o + 8, v, { "POPModify.modify[$i].flag" })
                    i++
                }
                off
            },
            { query, data, off ->
                val child = convertToIteratorBundleHelper(query, data, ByteArrayWrapperExt.readInt4(data, off + 4, { "POPModify.child" }))
                val steph = if (DictionaryValueHelper.getSize() > 4) DictionaryValueHelper.getSize() else 4
                val step = 9 + 3 * steph
                val modify = Array<Pair<LOPTriple, EModifyType>>(ByteArrayWrapperExt.readInt4(data, off + 8, { "POPModify.modify.size" })) { it ->
                    val o = off + 12 + it * step
                    val v = ByteArrayWrapperExt.readInt4(data, o, { "POPModify.modify[$it].v" })
                    val flag = ByteArrayWrapperExt.readInt1(data, o + 8, { "POPModify.modify[$it].flag" })
                    val graph = decodeString(data, o + 4)
                    val s = if ((flag and 0x2) != 0) {
                        AOPConstant(query, DictionaryValueHelper.fromByteArray(data, o + 9, { "POPModify.modify[$it].child[0].c" }))
                    } else {
                        AOPVariable(query, decodeString(data, ByteArrayWrapperExt.readInt4(data, o + 9, { "POPModify.modify[$it].child[0].v" })))
                    }
                    val p = if ((flag and 0x4) != 0) {
                        AOPConstant(query, DictionaryValueHelper.fromByteArray(data, o + 9 + steph, { "POPModify.modify[$it].child[1].c" }))
                    } else {
                        AOPVariable(query, decodeString(data, ByteArrayWrapperExt.readInt4(data, o + 9 + steph, { "POPModify.modify[$it].child[1].v" })))
                    }
                    val oo = if ((flag and 0x8) != 0) {
                        AOPConstant(query, DictionaryValueHelper.fromByteArray(data, o + 9 + steph + steph, { "POPModify.modify[$it].child[2].c" }))
                    } else {
                        AOPVariable(query, decodeString(data, ByteArrayWrapperExt.readInt4(data, o + 9 + steph + steph, { "POPModify.modify[$it].child[2].v" })))
                    }
                    val graphVar = (flag and 0x1) != 0
                    val k = LOPTriple(query, s, p, oo, graph, graphVar)
                    k to v
                }
                EvalModify(child, query, modify)
            },
        )
assignOperatorArithmetik(
EOperatorIDExt.AOPAggregationCOUNTID,
{ op, data, mapping ->
op as AOPAggregationCOUNT
val off = ByteArrayWrapperExt.getSize(data)
ByteArrayWrapperExt.setSize(data, off+9+4*op.children.size,true)
ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.AOPAggregationCOUNTID, { "operatorID" })
ByteArrayWrapperExt.writeInt4(data, off + 4,op.children.size,{"AOPAggregationCOUNT.children.size"})
ByteArrayWrapperExt.writeInt1(data, off + 8,if(op.distinct) 0x1 else 0x0,{"AOPAggregationCOUNT.distinct"})
for(i in 0 until op.children.size){
ByteArrayWrapperExt.writeInt4(data, off + 9+4*i,encodeAOP(op.children[i]as AOPBase,data,mapping),{"AOPAggregationCOUNT.children[$i]"})
}
off
},
            { query, data, off ->
val childs=Array(ByteArrayWrapperExt.readInt4(data, off + 4, { "AOPAggregationCOUNT.children.size" })){
decodeAOP(query,data,off + 9+4*it)
}
AOPAggregationCOUNT(query,ByteArrayWrapperExt.readInt1(data, off +8,{"AOPAggregationCOUNT.distinct"})!=0x0,childs)
},
)

    }
}

