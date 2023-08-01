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
import lupos.optimizer.physical.PhysicalOptimizerSplitMergePartition
import lupos.shared.dynamicArray.ByteArrayWrapper
import lupos.shared.operator.IOPBase
import lupos.shared.operator.iterator.IteratorBundleRoot

public object BinaryToOPBase {
    public fun convertToIteratorBundle(query: Query, data: ByteArrayWrapper, dataID: Int, operatorMap: Array<Any?>, compoundPartial: Boolean): IteratorBundleRoot {
        return ConverterBinaryToIteratorBundle.decode(query, data, dataID, operatorMap, compoundPartial)
    }

    public fun convertToIteratorBundle(query: Query, data: ByteArrayWrapper, dataID: Int, compoundPartial: Boolean): IteratorBundleRoot {
        return ConverterBinaryToIteratorBundle.decode(query, data, dataID, compoundPartial = compoundPartial)
    }

    public fun convertToByteArray(op: IOPBase, distributed: Boolean, splitEverything: Boolean): ByteArrayWrapper {
        return convertToByteArrayAndMeta(op, distributed, splitEverything, false)
    }

    public fun convertToByteArrayAndMeta(op: IOPBase, distributed: Boolean, splitEverything: Boolean, debugPrint: Boolean): ByteArrayWrapper {
        val query = op.getQuery() as Query
        val op2 = if (splitEverything) {
            PhysicalOptimizerSplitMergePartition(query).optimizeCall(op)
        } else {
            op
        }
        val res = ConverterPOPBaseToBinary.encode(op2, distributed)
        return res
    }

    public fun copyByteArray(query: Query, data: ByteArrayWrapper, filter: IntArray): ByteArrayWrapper {
        return ConverterBinaryToBinary.decode(query, data, filter)
    }

    init {
        val a1 = IntArray(ConverterAOPBaseToBinary.operatorMap.size) { it }.filter { ConverterAOPBaseToBinary.operatorMap[it] != null }.toSet()
        val a2 = IntArray(ConverterBinaryToAOPBase.operatorMap.size) { it }.filter { ConverterBinaryToAOPBase.operatorMap[it] != null }.toSet()
        val a3 = IntArray(ConverterBinaryToAOPJson.operatorMap.size) { it }.filter { ConverterBinaryToAOPJson.operatorMap[it] != null }.toSet()
        val aopAll = (a1 + a2 + a3).toSet()
        val t1 = aopAll - a1
        val t2 = aopAll - a2
        val t3 = aopAll - a3
        val a4 = IntArray(ConverterPOPBaseToBinary.operatorMap.size) { it }.filter { ConverterPOPBaseToBinary.operatorMap[it] != null }.toSet()
        val a5 = IntArray(ConverterBinaryToIteratorBundle.defaultOperatorMap.size) { it }.filter { ConverterBinaryToIteratorBundle.defaultOperatorMap[it] != null }.toSet()
        val a6 = IntArray(ConverterBinaryToPOPJson.operatorMap.size) { it }.filter { ConverterBinaryToPOPJson.operatorMap[it] != null }.toSet()
        val a7 = IntArray(ConverterBinaryToBinary.operatorMap.size) { it }.filter { ConverterBinaryToBinary.operatorMap[it] != null }.toSet()
        val popAll = (a4 + a5 + a6).toSet()
        val t4 = popAll - a4
        val t5 = popAll - a5
        val t6 = popAll - a6
        val t7 = popAll - a7
    }
}
