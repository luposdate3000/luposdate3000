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
    public fun convertToIteratorBundle(query: Query, data: ByteArrayWrapper, dataID: Int, operatorMap: Array<Any?>): IteratorBundleRoot {
        return ConverterBinaryToIteratorBundle.decode(query, data, dataID, operatorMap)
    }

    public fun convertToIteratorBundle(query: Query, data: ByteArrayWrapper, dataID: Int): IteratorBundleRoot {
        return ConverterBinaryToIteratorBundle.decode(query, data, dataID)
    }

    public fun convertToByteArray(op: IOPBase, distributed: Boolean, splitEverything: Boolean): ByteArrayWrapper {
        return convertToByteArrayAndMeta(op, distributed, splitEverything,false).first
    }

    public fun convertToByteArrayAndMeta(op: IOPBase, distributed: Boolean, splitEverything: Boolean,debugPrint:Boolean): Pair<ByteArrayWrapper, BinaryMetadataHandler> {
        val query = op.getQuery() as Query
        val op2 = if (splitEverything) {
            PhysicalOptimizerSplitMergePartition(query).optimizeCall(op)
        } else {
            op
        }
if(debugPrint){
println("debugging $op2")
}
        val res = ConverterPOPBaseToBinary.encode(op2, distributed)
        // println(op)
        // println("JSON_OUT:${ConverterBinaryToPOPJson.decode(query,res.first)}")
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
//        if (t1.size> 0) { println("ConverterAOPBaseToBinary is missing : \n${t1.map{EOperatorIDExt.names[it]}.joinToString("\n")}") }
//        if (t2.size> 0) { println("ConverterBinaryToAOPBase is missing : \n${t2.map{EOperatorIDExt.names[it]}.joinToString("\n")}") }
//        if (t3.size> 0) { println("ConverterBinaryToAOPJson is missing : \n${t3.map{EOperatorIDExt.names[it]}.joinToString("\n")}") }
        val a4 = IntArray(ConverterPOPBaseToBinary.operatorMap.size) { it }.filter { ConverterPOPBaseToBinary.operatorMap[it] != null }.toSet()
        val a5 = IntArray(ConverterBinaryToIteratorBundle.defaultOperatorMap.size) { it }.filter { ConverterBinaryToIteratorBundle.defaultOperatorMap[it] != null }.toSet()
        val a6 = IntArray(ConverterBinaryToPOPJson.operatorMap.size) { it }.filter { ConverterBinaryToPOPJson.operatorMap[it] != null }.toSet()
        val a7 = IntArray(ConverterBinaryToBinary.operatorMap.size) { it }.filter { ConverterBinaryToBinary.operatorMap[it] != null }.toSet()
        val popAll = (a4 + a5 + a6).toSet()
        val t4 = popAll - a4
        val t5 = popAll - a5
        val t6 = popAll - a6
        val t7 = popAll - a7
//        if (t4.size> 0) { println("ConverterPOPBaseToBinary is missing : \n${t4.map{EOperatorIDExt.names[it]}.joinToString("\n")}") }
//        if (t5.size> 0) { println("ConverterBinaryToIteratorBundle is missing : \n${t5.map{EOperatorIDExt.names[it]}.joinToString("\n")}") }
//        if (t6.size> 0) { println("ConverterBinaryToPOPJson is missing : \n${t6.map{EOperatorIDExt.names[it]}.joinToString("\n")}") }
//        if (t7.size> 0) { println("ConverterBinaryToBinary is missing : \n${t7.map{EOperatorIDExt.names[it]}.joinToString("\n")}") }
    }
}
