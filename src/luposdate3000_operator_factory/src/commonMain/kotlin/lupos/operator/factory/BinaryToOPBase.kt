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
import lupos.shared.EOperatorIDExt
import lupos.shared.dynamicArray.ByteArrayWrapper
import lupos.shared.operator.IOPBase
import lupos.shared.operator.iterator.IteratorBundleRoot

public object BinaryToOPBase {
    public fun convertToIteratorBundle(query: Query, data: ByteArrayWrapper): IteratorBundleRoot {
        return ConverterBinaryToIteratorBundle.decode(query, data)[-1]!!
    }
    public fun convertToByteArray(op: IOPBase, distributed: Boolean): ByteArrayWrapper {
        val res = ConverterPOPBaseToBinary.encode(op, distributed)
        for (i in 0 until 20) {
            ConverterPOPBaseToBinary.optimizationLimit = i
            val res2 = ConverterPOPBaseToBinary.encode(op, distributed)
            println("JSON_OUT$i:${ConverterBinaryToPOPJson.decode(op.getQuery()as Query,res2)}")
        }
        return res
    }
    init {
        println("BinaryToOPBase.init start")
        ConverterAOPBaseToBinary.initEncode()
        ConverterBinaryToAOPBase.initDecode()
        ConverterBinaryToAOPJson.initDecode()
        val a1 = IntArray(ConverterAOPBaseToBinary.operatorMap.size) { it }.filter { ConverterAOPBaseToBinary.operatorMap[it] != null }.toSet()
        val a2 = IntArray(ConverterBinaryToAOPBase.operatorMap.size) { it }.filter { ConverterBinaryToAOPBase.operatorMap[it] != null }.toSet()
        val a3 = IntArray(ConverterBinaryToAOPJson.operatorMap.size) { it }.filter { ConverterBinaryToAOPJson.operatorMap[it] != null }.toSet()
        val aopAll = (a1 + a2 + a3).toSet()
        val t1 = aopAll - a1
        val t2 = aopAll - a2
        val t3 = aopAll - a3
        if (t1.size> 0) { println("ConverterAOPBaseToBinary is missing : \n${t1.map{EOperatorIDExt.names[it]}.joinToString("\n")}") }
        if (t2.size> 0) { println("ConverterBinaryToAOPBase is missing : \n${t2.map{EOperatorIDExt.names[it]}.joinToString("\n")}") }
        if (t3.size> 0) { println("ConverterBinaryToAOPJson is missing : \n${t3.map{EOperatorIDExt.names[it]}.joinToString("\n")}") }
        ConverterPOPBaseToBinary.initEncode()
        ConverterBinaryToIteratorBundle.initDecode()
        ConverterBinaryToPOPJson.initDecode()
        val a4 = IntArray(ConverterPOPBaseToBinary.operatorMap.size) { it }.filter { ConverterPOPBaseToBinary.operatorMap[it] != null }.toSet()
        val a5 = IntArray(ConverterBinaryToIteratorBundle.operatorMap.size) { it }.filter { ConverterBinaryToIteratorBundle.operatorMap[it] != null }.toSet()
        val a6 = IntArray(ConverterBinaryToPOPJson.operatorMap.size) { it }.filter { ConverterBinaryToPOPJson.operatorMap[it] != null }.toSet()
        val popAll = (a4 + a5 + a6).toSet()
        val t4 = popAll - a4
        val t5 = popAll - a5
        val t6 = popAll - a6
        if (t4.size> 0) { println("ConverterPOPBaseToBinary is missing : \n${t4.map{EOperatorIDExt.names[it]}.joinToString("\n")}") }
        if (t5.size> 0) { println("ConverterBinaryToIteratorBundle is missing : \n${t5.map{EOperatorIDExt.names[it]}.joinToString("\n")}") }
        if (t6.size> 0) { println("ConverterBinaryToPOPJson is missing : \n${t6.map{EOperatorIDExt.names[it]}.joinToString("\n")}") }
        println("BinaryToOPBase.init finish")
    }
}
