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
import lupos.operator.base.OPBase
import lupos.operator.base.Query
import lupos.shared.DictionaryValueHelper
import lupos.shared.EOperatorIDExt
import lupos.shared.Partition
import lupos.shared.dynamicArray.ByteArrayWrapper
import lupos.shared.inline.dynamicArray.ByteArrayWrapperExt
import lupos.shared.operator.IOPBase
import lupos.shared.operator.iterator.IteratorBundle
import lupos.shared.operator.noinput.IAOPConstant
import lupos.shared.operator.noinput.IAOPVariable
import lupos.triple_store_manager.EvalTripleStoreIterator
import lupos.triple_store_manager.POPTripleStoreIterator

public typealias BinaryToOPBaseMap = (query: Query, data: ByteArrayWrapper, offset: Int) -> IteratorBundle
public typealias OPBaseToBinaryMap = (op: IOPBase, data: ByteArrayWrapper, parent: Partition) -> Int/*offset*/

public object BinaryToOPBase {
    public var operatorMapDecode: Array<BinaryToOPBaseMap?> = Array(0) { null }
    public var operatorMapEncode: Array<OPBaseToBinaryMap?> = Array(0) { null }
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
        val res = ByteArrayWrapper()
        ByteArrayWrapperExt.setSize(res, 4, false)
        val off = convertToByteArrayHelper(op, res, Partition())
        ByteArrayWrapperExt.writeInt4(res, 0, off)
        return res
    }

    private fun convertToByteArrayHelper(op: IOPBase, data: ByteArrayWrapper, parent: Partition): Int {
        return operatorMapEncode[(op as OPBase).operatorID]!!(op, data, parent)
    }

    init {
        assignOperator(
            EOperatorIDExt.POPTripleStoreIterator,
            { op, data, parent ->
                op as POPTripleStoreIterator
                val off = ByteArrayWrapperExt.getSize(data)
                val target = op.getTarget(parent)
                val buf1 = target.first.encodeToByteArray()
                val buf2 = target.second.encodeToByteArray()
                var size = off + 17 + buf1.size + buf2.size
                for (i in 0 until 3) {
                    val child = op.children[i]
                    when (child) {
                        is IAOPConstant -> {
                            size += DictionaryValueHelper.getSize()
                        }
                        is IAOPVariable -> {
                            size += child.getName().encodeToByteArray().size + 4
                        }
                    }
                }
                var childFlag = 0
                ByteArrayWrapperExt.setSize(data, size, true)
                ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.POPTripleStoreIterator)
                ByteArrayWrapperExt.writeInt4(data, off + 4, buf1.size)
                ByteArrayWrapperExt.writeInt4(data, off + 8, buf2.size)
                ByteArrayWrapperExt.writeInt4(data, off + 12, op.getIndexPattern())
                ByteArrayWrapperExt.writeBuf(data, off + 17, buf1)
                ByteArrayWrapperExt.writeBuf(data, off + 17 + buf1.size, buf2)
                var o = off + 17 + buf1.size + buf2.size
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
                            val b = child.getName().encodeToByteArray()
                            val len = ByteArrayWrapperExt.writeInt4(data, o, b.size)
                            o += 4
                            ByteArrayWrapperExt.writeBuf(data, o, b)
                            o += b.size
                        }
                    }
                }
                ByteArrayWrapperExt.writeInt1(data, off + 16, childFlag)
                off
            },
            { query, data, off ->
                val len1 = ByteArrayWrapperExt.readInt4(data, off + 4)
                val len2 = ByteArrayWrapperExt.readInt4(data, off + 8)
                val index = ByteArrayWrapperExt.readInt4(data, off + 12)
                val childFlag = ByteArrayWrapperExt.readInt1(data, off + 16)
                val buf1 = ByteArrayWrapperExt.getBuf(data).decodeToString(off + 17, off + 28 + len1)
                val buf2 = ByteArrayWrapperExt.getBuf(data).decodeToString(off + 17 + len1, off + 28 + len1 + len2)
                var o = off + 17 + len1 + len2
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
                    val len = ByteArrayWrapperExt.readInt4(data, o)
                    ByteArrayWrapperExt.getBuf(data).decodeToString(o + 4, o + 4 + len)
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
                    val len = ByteArrayWrapperExt.readInt4(data, o)
                    ByteArrayWrapperExt.getBuf(data).decodeToString(o + 4, o + 4 + len)
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
                    val len = ByteArrayWrapperExt.readInt4(data, o)
                    ByteArrayWrapperExt.getBuf(data).decodeToString(o + 4, o + 4 + len)
                } else {
                    ""
                }
                EvalTripleStoreIterator(
                    buf1 to buf2,
                    query,
                    index,
                    arrayOf(child0f to (child0c to child0v), child1f to (child1c to child1v), child2f to (child2c to child2v))
                )
            }
        )
    }
}
