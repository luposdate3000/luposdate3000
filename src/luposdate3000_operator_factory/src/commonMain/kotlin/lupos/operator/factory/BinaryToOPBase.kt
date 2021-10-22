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

import lupos.operator.base.Query
import lupos.shared.dynamicArray.ByteArrayWrapper
import lupos.shared.operator.IOPBase
import lupos.triple_store_manager.POPTripleStoreIterator

public typealias BinaryToOPBaseMap = (query: Query, data: ByteArrayWrapper, offset: Int) -> IteratorBundle
public typealias OPBaseToBinaryMap = (op: IOPBase, data: ByteArrayWrapper, parent: Partition) -> Int/*offset*/

public object BinaryToOPBase {
    public var operatorMapDecode: Array<BinaryToOPBaseMap?> = Array(0) { null }
    public var operatorMapEncode: Array<OPBaseToBinaryMap?> = Array(0) { null }
    public fun assignOperatorDecode(operatorID: Int, operator: BinaryToOPBaseMap) {
        if (operatorMapDecode.size <= operatorID) {
            val s = operatorMapDecode.size
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
            val s = operatorMapEncode.size
            if (s < 16) {
                s = 16
            }
            while (s < operatorID) {
                s = s * 2
            }
            val tmp = Array<BinaryToOPBaseMap?>(s) { null }
            operatorMapEncode.copyInto(tmp)
            operatorMapEncode = tmp
        }
        operatorMapEncode[operatorID] = operator
    }

    public fun assignOperatorEncode(operatorID: Int, encode: OPBaseToBinaryMap, decode: BinaryToOPBaseMap) {
        assignOperatorEncode(operatorID, encode)
        assignOperatorDecode(operatorID, decode)
    }

    public fun convertToByteArray(op: IOPBase): ByteArrayWrapper {
        val res = ByteArrayWrapper()
        ByteArrayWrapperExt.setSize(res, 4)
        val off = convertToByteArrayHelper(op, res, Partition())
        ByteArrayWrapperExt.writeInt4(res, 0, off)
        return res
    }
    private fun convertToByteArrayHelper(op: IOPBase, data: ByteArrayWrapper, parent: Partition): Int {
        operatorMapEncode[op.operatorID](op, data, parent)
    }

    init {
        assignOperator(
            EOperatorIDExt.POPTripleStoreIterator,
            { op, data, parent ->
                op as POPTripleStoreIterator
                val child0 = convertToByteArrayHelper(op.children[0], data, parent)
                val child1 = convertToByteArrayHelper(op.children[1], data, parent)
                val child2 = convertToByteArrayHelper(op.children[2], data, parent)
                val off = ByteArrayWrapperExt.getSize(data)
                val target = op.getTarget(parent)
                val buf1 = target.first.encodeToByteArray()
                val buf2 = target.second.encodeToByteArray()
                ByteArrayWrapperExt.setSize(data, off + 28 + buf1.size + buf2.size, true)
                ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.POPTripleStoreIterator)
                ByteArrayWrapperExt.writeInt4(data, off + 4, buf1.size)
                ByteArrayWrapperExt.writeInt4(data, off + 8, buf2.size)
                ByteArrayWrapperExt.writeInt4(data, off + 12, index)
                ByteArrayWrapperExt.writeInt4(data, off + 16, child0)
                ByteArrayWrapperExt.writeInt4(data, off + 20, child1)
                ByteArrayWrapperExt.writeInt4(data, off + 24, child2)
                ByteArrayWrapperExt.writeBuf(data, off + 28, buf1)
                ByteArrayWrapperExt.writeBuf(data, off + 28 + buf1.size, buf2)
            },
            { query, data, offset ->
            }
        )
    }
}
