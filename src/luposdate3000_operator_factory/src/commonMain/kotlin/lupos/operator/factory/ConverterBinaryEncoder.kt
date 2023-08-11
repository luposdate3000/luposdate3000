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

import lupos.operator.arithmetik.noinput.AOPConstant
import lupos.operator.arithmetik.noinput.AOPVariable
import lupos.operator.logical.noinput.LOPTriple
import lupos.shared.DictionaryValueHelper
import lupos.shared.DictionaryValueType
import lupos.shared.DictionaryValueTypeArray
import lupos.shared.EGraphOperationType
import lupos.shared.EGraphRefType
import lupos.shared.EModifyType
import lupos.shared.EOperatorIDExt
import lupos.shared.dynamicArray.ByteArrayWrapper
import lupos.shared.inline.dynamicArray.ByteArrayWrapperExt
import lupos.triple_store_manager.POPTripleStoreIterator

public object ConverterBinaryEncoder {
    public fun encodePOPDistributedReceiveMultiOrdered(
        data: ByteArrayWrapper,
        mapping: MutableMap<String, Int>,
        keys: List<Int>,
        orderedBy: List<String>,
        variablesOut: List<String>,
    ): Int {
        val off = ByteArrayWrapperExt.getSize(data)
        ByteArrayWrapperExt.setSize(data, off + 16 + 4 * (keys.size + orderedBy.size + variablesOut.size), true)
        ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.POPDistributedReceiveMultiOrderedID, { "operatorID" })
        ByteArrayWrapperExt.writeInt4(data, off + 4, keys.size, { "POPDistributedReceiveMultiOrdered.keys.size" })
        ByteArrayWrapperExt.writeInt4(data, off + 8, orderedBy.size, { "POPDistributedReceiveMultiOrdered.orderedBy.size" })
        ByteArrayWrapperExt.writeInt4(data, off + 12, variablesOut.size, { "POPDistributedReceiveMultiOrdered.variablesOut.size" })
        var o = off + 16
        for (i in 0 until keys.size) {
            ByteArrayWrapperExt.writeInt4(data, o, keys[i], { "POPDistributedReceiveMultiOrdered.keys[$i]" })
            o += 4
        }
        for (i in 0 until orderedBy.size) {
            ByteArrayWrapperExt.writeInt4(data, o, ConverterString.encodeString(orderedBy[i], data, mapping), { "POPDistributedReceiveMultiOrdered.orderedBy[$i]" })
            o += 4
        }
        for (i in 0 until variablesOut.size) {
            ByteArrayWrapperExt.writeInt4(data, o, ConverterString.encodeString(variablesOut[i], data, mapping), { "POPDistributedReceiveMultiOrdered.variablesOut[$i]" })
            o += 4
        }
        return off
    }

    public fun encodePOPDistributedReceiveMultiCount(data: ByteArrayWrapper, mapping: MutableMap<String, Int>, keys: List<Int>): Int {
        val off = ByteArrayWrapperExt.getSize(data)
        ByteArrayWrapperExt.setSize(data, off + 8 + 4 * keys.size, true)
        ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.POPDistributedReceiveMultiCountID, { "operatorID" })
        ByteArrayWrapperExt.writeInt4(data, off + 4, keys.size, { "POPDistributedReceiveMultiCount.size" })
        for (i in 0 until keys.size) {
            ByteArrayWrapperExt.writeInt4(data, off + 8 + 4 * i, keys[i], { "POPDistributedReceiveMultiCount.key[$i]" })
        }
        return off
    }

    public fun encodePOPDistributedReceiveSingleCount(data: ByteArrayWrapper, mapping: MutableMap<String, Int>, key: Int): Int {
        val off = ByteArrayWrapperExt.getSize(data)
        ByteArrayWrapperExt.setSize(data, off + 8, true)
        ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.POPDistributedReceiveSingleCountID, { "operatorID" })
        ByteArrayWrapperExt.writeInt4(data, off + 4, key, { "POPDistributedReceiveSingleCount.key" })
        return off
    }

    public fun encodePOPDistributedReceiveSingle(data: ByteArrayWrapper, mapping: MutableMap<String, Int>, key: Int): Int {
        val off = ByteArrayWrapperExt.getSize(data)
        ByteArrayWrapperExt.setSize(data, off + 8, true)
        ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.POPDistributedReceiveSingleID, { "operatorID" })
        ByteArrayWrapperExt.writeInt4(data, off + 4, key, { "POPDistributedReceiveSingle.key" })
        return off
    }

    public fun encodePOPDistributedReceiveMulti(data: ByteArrayWrapper, mapping: MutableMap<String, Int>, keys: List<Int>): Int {
        val off = ByteArrayWrapperExt.getSize(data)
        ByteArrayWrapperExt.setSize(data, off + 8 + 4 * keys.size, true)
        ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.POPDistributedReceiveMultiID, { "operatorID" })
        ByteArrayWrapperExt.writeInt4(data, off + 4, keys.size, { "POPDistributedReceiveMulti.size" })
        for (i in 0 until keys.size) {
            ByteArrayWrapperExt.writeInt4(data, off + 8 + 4 * i, keys[i], { "POPDistributedReceiveMulti.key[$i]" })
        }
        return off
    }
    public fun encodeLOPJoinTopology(data: ByteArrayWrapper, mapping: MutableMap<String, Int>, keys: List<Int>, projectedVariables: List<String>, projectedVariablesChilds: List<List<String>>): Int {
        val off = ByteArrayWrapperExt.getSize(data)
        val finalEndOff = off + 4 + 4 + 4 + 4 * projectedVariables.size + 4 * keys.size + 4 * keys.size + 4 * (projectedVariablesChilds.map { it.size }.sum())
        ByteArrayWrapperExt.setSize(data, finalEndOff, true)
        var o = off
        ByteArrayWrapperExt.writeInt4(data, o, EOperatorIDExt.LOPJoinTopologyID, { "operatorID" })
        o += 4
        ByteArrayWrapperExt.writeInt4(data, o, keys.size, { "LOPJoinTopology.size" })
        o += 4
        ByteArrayWrapperExt.writeInt4(data, o, projectedVariables.size, { "LOPJoinTopology.size" })
        o += 4
        for (x in projectedVariables) {
            ByteArrayWrapperExt.writeInt4(data, o, ConverterString.encodeString(x, data, mapping), { "LOPJoinTolology.projectedVariables[]" })
            o += 4
        }
        for (i in 0 until keys.size) {
            ByteArrayWrapperExt.writeInt4(data, o, keys[i], { "LOPJoinTopology.key[$i]" })
            o += 4
            ByteArrayWrapperExt.writeInt4(data, o, projectedVariablesChilds[i].size, { "LOPJoinTopology.size" })
            o += 4
            for (x in projectedVariablesChilds[i]) {
                ByteArrayWrapperExt.writeInt4(data, o, ConverterString.encodeString(x, data, mapping), { "LOPJoinTolology.projectedVariables[$i]" })
                o += 4
            }
        }
        if (o != finalEndOff) {
            TODO("offset error somewhere $o $finalEndOff")
        }
        return off
    }

    public fun encodePOPDistributedSendSingleCount(data: ByteArrayWrapper, mapping: MutableMap<String, Int>, key: Int, childf: (Int) -> Int): Int {
        val off = ByteArrayWrapperExt.getSize(data)
        ByteArrayWrapperExt.setSize(data, off + 12, true)
        val child = childf(off + 8)
        ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.POPDistributedSendSingleCountID, { "operatorID" })
        ByteArrayWrapperExt.writeInt4(data, off + 4, key, { "POPDistributedSendSingleCount.key" })
        ByteArrayWrapperExt.writeInt4(data, off + 8, child, { "POPDistributedSendSingleCount.child" })
        return off
    }

    public fun encodePOPDistributedSendMulti(data: ByteArrayWrapper, mapping: MutableMap<String, Int>, keys: List<Int>, childf: (Int) -> Int, column: String): Int {
        val off = ByteArrayWrapperExt.getSize(data)
        ByteArrayWrapperExt.setSize(data, off + 16 + 4 * keys.size, true)
        val child = childf(off + 4)
        ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.POPDistributedSendMultiID, { "operatorID" })
        ByteArrayWrapperExt.writeInt4(data, off + 4, child, { "POPDistributedSendMulti.child" })
        ByteArrayWrapperExt.writeInt4(data, off + 8, keys.size, { "POPDistributedSendMulti.count" })
        ByteArrayWrapperExt.writeInt4(data, off + 12, ConverterString.encodeString(column, data, mapping), { "POPDistributedSendMulti.name" })
        for (i in 0 until keys.size) {
            ByteArrayWrapperExt.writeInt4(data, off + 16 + 4 * i, keys[i], { "POPDistributedSendMulti.key[$i]" })
        }
        return off
    }

    public fun encodePOPDistributedSendSingle(data: ByteArrayWrapper, mapping: MutableMap<String, Int>, key: Int, childf: (Int) -> Int): Int {
        val off = ByteArrayWrapperExt.getSize(data)
        ByteArrayWrapperExt.setSize(data, off + 12, true)
        val child = childf(off + 8)
        ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.POPDistributedSendSingleID, { "operatorID" })
        ByteArrayWrapperExt.writeInt4(data, off + 4, key, { "POPDistributedSendSingle.key" })
        ByteArrayWrapperExt.writeInt4(data, off + 8, child, { "POPDistributedSendSingle.child" })
        return off
    }

    public fun encodePOPModifyData(data: ByteArrayWrapper,type:EModifyType, mapping: MutableMap<String, Int>, data2: List<Pair<String, DictionaryValueTypeArray>>): Int {
        val off = ByteArrayWrapperExt.getSize(data)
        ByteArrayWrapperExt.setSize(data, off + 12 + data2.size * (4 + 3 * DictionaryValueHelper.getSize()), true)
        ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.POPModifyDataID, { "operatorID" })
        ByteArrayWrapperExt.writeInt4(data, off + 4, type, { "POPModifyData.type" })
        ByteArrayWrapperExt.writeInt4(data, off + 8, data2.size, { "POPModifyData.data.size" })
        var o = off + 12
        var i = 0
        for (t in data2) {
            ByteArrayWrapperExt.writeInt4(data, o, ConverterString.encodeString(t.first, data, mapping), { "POPModifyData.data[$i].graph" })
            o += 4
            for (j in 0 until 3) {
                DictionaryValueHelper.toByteArray(data, o, t.second[j], { "POPModifyData.data[$i][$j]" })
                o += DictionaryValueHelper.getSize()
            }
            i++
        }
        return off
    }

    public fun encodePOPNothing(data: ByteArrayWrapper, mapping: MutableMap<String, Int>, projectedVariables: List<String>): Int {
        val n = projectedVariables
        val off = ByteArrayWrapperExt.getSize(data)
        ByteArrayWrapperExt.setSize(data, off + 8 + 4 * n.size, true)
        ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.POPNothingID, { "operatorID" })
        ByteArrayWrapperExt.writeInt4(data, off + 4, n.size, { "POPNothing.size" })
        var i = 0
        for (s in n) {
            ByteArrayWrapperExt.writeInt4(data, off + 8 + i * 4, ConverterString.encodeString(s, data, mapping), { "POPNothing.data[$i]" })
            i++
        }
        return off
    }

    public fun encodePOPValuesCount(data: ByteArrayWrapper, mapping: MutableMap<String, Int>, rows: Int): Int {
        val off = ByteArrayWrapperExt.getSize(data)
        ByteArrayWrapperExt.setSize(data, off + 8, true)
        ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.POPValuesCountID, { "operatorID" })
        ByteArrayWrapperExt.writeInt4(data, off + 4, rows, { "POPValueCount.rows.size" })
        return off
    }

    public fun encodePOPValues(data: ByteArrayWrapper, mapping: MutableMap<String, Int>, data2: Map<String, MutableList<DictionaryValueType>>): Int {
        val off = ByteArrayWrapperExt.getSize(data)
        val size = data2[data2.keys.first()]!!.size
        ByteArrayWrapperExt.setSize(data, off + 12 + data2.size * (4 + size * DictionaryValueHelper.getSize()), true)
        ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.POPValuesID, { "operatorID" })
        ByteArrayWrapperExt.writeInt4(data, off + 4, data2.size, { "POPValues.columns.size" })
        ByteArrayWrapperExt.writeInt4(data, off + 8, size, { "POPValues.rows.size" })
        var o = off + 12
        var column = 0
        for ((col, rows) in data2) {
            ByteArrayWrapperExt.writeInt4(data, o, ConverterString.encodeString(col, data, mapping), { "POPValues.column[$column]" })
            o += 4
            var i = 0
            for (row in rows) {
                i++
                DictionaryValueHelper.toByteArray(data, o, row, { "POPValues.data[$column][$i]" })
                o += DictionaryValueHelper.getSize()
            }
            column++
        }
        return off
    }

    public fun encodePOPEmptyRow(data: ByteArrayWrapper, mapping: MutableMap<String, Int>): Int {
        val off = ByteArrayWrapperExt.getSize(data)
        ByteArrayWrapperExt.setSize(data, off + 4, true)
        ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.POPEmptyRowID, { "operatorID" })
        return off
    }

    public fun encodePOPUnion(data: ByteArrayWrapper, mapping: MutableMap<String, Int>, child0f: (Int) -> Int, child1f: (Int) -> Int, projectedVariables: List<String>): Int {
        val off = ByteArrayWrapperExt.getSize(data)
        ByteArrayWrapperExt.setSize(data, off + 16 + 4 * projectedVariables.size, true)
        val child0 = child0f(off + 4)
        val child1 = child1f(off + 8)
        ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.POPUnionID, { "operatorID" })
        ByteArrayWrapperExt.writeInt4(data, off + 4, child0, { "POPUnion.child0" })
        ByteArrayWrapperExt.writeInt4(data, off + 8, child1, { "POPUnion.child1" })
        ByteArrayWrapperExt.writeInt4(data, off + 12, projectedVariables.size, { "POPUnion.variables.size" })
        var o = off + 16
        var i = 0
        for (s in projectedVariables) {
            ByteArrayWrapperExt.writeInt4(data, o, ConverterString.encodeString(s, data, mapping), { "POPUnion.variables[$i]" })
            o += 4
            i++
        }
        return off
    }

    public fun encodePOPMinus(data: ByteArrayWrapper, mapping: MutableMap<String, Int>, child0f: (Int) -> Int, child1f: (Int) -> Int, projectedVariables: List<String>): Int {
        val off = ByteArrayWrapperExt.getSize(data)
        ByteArrayWrapperExt.setSize(data, off + 16 + 4 * projectedVariables.size, true)
        val child0 = child0f(off + 4)
        val child1 = child1f(off + 8)
        ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.POPMinusID, { "operatorID" })
        ByteArrayWrapperExt.writeInt4(data, off + 4, child0, { "POPMinus.child0" })
        ByteArrayWrapperExt.writeInt4(data, off + 8, child1, { "POPMinus.child1" })
        ByteArrayWrapperExt.writeInt4(data, off + 12, projectedVariables.size, { "POPMinus.variables.size" })
        var o = off + 16
        var i = 0
        for (s in projectedVariables) {
            ByteArrayWrapperExt.writeInt4(data, o, ConverterString.encodeString(s, data, mapping), { "POPMinus.variables[$i]" })
            o += 4
            i++
        }
        return off
    }

    public fun encodePOPJoinMergeSingleColumn(data: ByteArrayWrapper, mapping: MutableMap<String, Int>, child0f: (Int) -> Int, child1f: (Int) -> Int): Int {
        val off = ByteArrayWrapperExt.getSize(data)
        ByteArrayWrapperExt.setSize(data, off + 12, true)
        val child0 = child0f(off + 4)
        val child1 = child1f(off + 8)
        ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.POPJoinMergeSingleColumnID, { "operatorID" })
        ByteArrayWrapperExt.writeInt4(data, off + 4, child0, { "POPJoinMergeSingleColumn.child0" })
        ByteArrayWrapperExt.writeInt4(data, off + 8, child1, { "POPJoinMergeSingleColumn.child1" })
        return off
    }

    public fun encodePOPJoinMergeOptional(data: ByteArrayWrapper, mapping: MutableMap<String, Int>, child0f: (Int) -> Int, child1f: (Int) -> Int, projectedVariables: List<String>): Int {
        val off = ByteArrayWrapperExt.getSize(data)
        ByteArrayWrapperExt.setSize(data, off + 16 + 4 * projectedVariables.size, true)
        val child0 = child0f(off + 4)
        val child1 = child1f(off + 8)
        ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.POPJoinMergeOptionalID, { "operatorID" })
        ByteArrayWrapperExt.writeInt4(data, off + 4, child0, { "POPJoinMergeOptional.child0" })
        ByteArrayWrapperExt.writeInt4(data, off + 8, child1, { "POPJoinMergeOptional.child1" })
        ByteArrayWrapperExt.writeInt4(data, off + 12, projectedVariables.size, { "POPJoinMergeOptional.variables.size" })
        var o = off + 16
        var i = 0
        for (s in projectedVariables) {
            ByteArrayWrapperExt.writeInt4(data, o, ConverterString.encodeString(s, data, mapping), { "POPJoinMergeOptional.variables[$i]" })
            o += 4
            i++
        }
        return off
    }

    public fun encodePOPJoinMerge(data: ByteArrayWrapper, mapping: MutableMap<String, Int>, child0f: (Int) -> Int, child1f: (Int) -> Int, projectedVariables: List<String>, joinVariableOrder: List<String>): Int {
        val off = ByteArrayWrapperExt.getSize(data)
        ByteArrayWrapperExt.setSize(data, off + 20 + 4 * projectedVariables.size + 4 * joinVariableOrder.size, true)
        val child0 = child0f(off + 4)
        val child1 = child1f(off + 8)
        ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.POPJoinMergeID, { "operatorID" })
        ByteArrayWrapperExt.writeInt4(data, off + 4, child0, { "POPJoinMerge.child0" })
        ByteArrayWrapperExt.writeInt4(data, off + 8, child1, { "POPJoinMerge.child1" })
        ByteArrayWrapperExt.writeInt4(data, off + 12, projectedVariables.size, { "POPJoinMerge.variables.size" })
        var o = off + 16
        var i = 0
        for (s in projectedVariables) {
            ByteArrayWrapperExt.writeInt4(data, o, ConverterString.encodeString(s, data, mapping), { "POPJoinMerge.variables[$i]" })
            o += 4
            i++
        }
        ByteArrayWrapperExt.writeInt4(data, o, joinVariableOrder.size, { "POPJoinMerge.variables.size" })
        o += 4
        i = 0
        for (s in joinVariableOrder) {
            ByteArrayWrapperExt.writeInt4(data, o, ConverterString.encodeString(s, data, mapping), { "POPJoinMerge.variables[$i]" })
            o += 4
            i++
        }
        return off
    }

    public fun encodePOPJoinHashMap(data: ByteArrayWrapper, mapping: MutableMap<String, Int>, child0f: (Int) -> Int, child1f: (Int) -> Int, optional: Boolean, projectedVariables: List<String>): Int {
        val off = ByteArrayWrapperExt.getSize(data)
        ByteArrayWrapperExt.setSize(data, off + 17 + 4 * projectedVariables.size, true)
        val child0 = child0f(off + 4)
        val child1 = child1f(off + 8)
        ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.POPJoinHashMapID, { "operatorID" })
        ByteArrayWrapperExt.writeInt4(data, off + 4, child0, { "POPJoinHashMap.child0" })
        ByteArrayWrapperExt.writeInt4(data, off + 8, child1, { "POPJoinHashMap.child1" })
        ByteArrayWrapperExt.writeInt1(data, off + 12, if (optional) 1 else 0, { "POPJoinHashMap.optional" })
        ByteArrayWrapperExt.writeInt4(data, off + 13, projectedVariables.size, { "POPJoinHashMap.variables.size" })
        var o = off + 17
        var i = 0
        for (s in projectedVariables) {
            ByteArrayWrapperExt.writeInt4(data, o, ConverterString.encodeString(s, data, mapping), { "POPJoinHashMap.variables[$i]" })
            o += 4
            i++
        }
        return off
    }

    public fun encodePOPGraphOperation(data: ByteArrayWrapper, mapping: MutableMap<String, Int>, graph1type: EGraphRefType, graph2type: EGraphRefType, action: EGraphOperationType, graph1iri: String?, graph2iri: String?, silent: Boolean): Int {
        val off = ByteArrayWrapperExt.getSize(data)
        ByteArrayWrapperExt.setSize(data, off + 25, true)
        ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.POPGraphOperationID, { "operatorID" })
        ByteArrayWrapperExt.writeInt4(data, off + 4, graph1type, { "POPGraphOperation.graph1type" })
        ByteArrayWrapperExt.writeInt4(data, off + 8, graph2type, { "POPGraphOperation.graph2type" })
        ByteArrayWrapperExt.writeInt4(data, off + 12, action, { "POPGraphOperation.action" })
        ByteArrayWrapperExt.writeInt4(data, off + 16, ConverterString.encodeString(graph1iri, data, mapping), { "POPGraphOperation.graph1iri" })
        ByteArrayWrapperExt.writeInt4(data, off + 20, ConverterString.encodeString(graph2iri, data, mapping), { "POPGraphOperation.graph2iri" })
        ByteArrayWrapperExt.writeInt1(data, off + 24, if (silent) 1 else 0, { "POPGraphOperation.silent" })
        return off
    }

    public fun encodePOPJoinCartesianProduct(data: ByteArrayWrapper, mapping: MutableMap<String, Int>, child0f: (Int) -> Int, child1f: (Int) -> Int, optional: Boolean): Int {
        val off = ByteArrayWrapperExt.getSize(data)
        ByteArrayWrapperExt.setSize(data, off + 13, true)
        val child0 = child0f(off + 4)
        val child1 = child1f(off + 8)
        ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.POPJoinCartesianProductID, { "operatorID" })
        ByteArrayWrapperExt.writeInt4(data, off + 4, child0, { "POPJoinCartesianProduct.child0" })
        ByteArrayWrapperExt.writeInt4(data, off + 8, child1, { "POPJoinCartesianProduct.child1" })
        ByteArrayWrapperExt.writeInt1(data, off + 12, if (optional) 1 else 0, { "POPJoinCartesianProduct.optional" })
        return off
    }

    public fun encodePOPLimit(data: ByteArrayWrapper, mapping: MutableMap<String, Int>, childf: (Int) -> Int, limit: Int): Int {
        val off = ByteArrayWrapperExt.getSize(data)
        ByteArrayWrapperExt.setSize(data, off + 12, true)
        val child = childf(off + 4)
        ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.POPLimitID, { "operatorID" })
        ByteArrayWrapperExt.writeInt4(data, off + 4, child, { "POPLimit.child" })
        ByteArrayWrapperExt.writeInt4(data, off + 8, limit, { "POPLimit.limit" })
        return off
    }

    public fun encodePOPGroup(data: ByteArrayWrapper, mapping: MutableMap<String, Int>, childf: (Int) -> Int, keyColumnNames: List<String>, bindings: List<Pair<String, Int>>): Int {
        val off = ByteArrayWrapperExt.getSize(data)
        ByteArrayWrapperExt.setSize(data, off + 16 + 4 * (keyColumnNames.size + bindings.size * 2), true)
        val child = childf(off + 4)
        ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.POPGroupID, { "operatorID" })
        ByteArrayWrapperExt.writeInt4(data, off + 4, child, { "POPGroup.child" })
        ByteArrayWrapperExt.writeInt4(data, off + 8, bindings.size, { "POPGroup.bindings.size" })
        ByteArrayWrapperExt.writeInt4(data, off + 12, keyColumnNames.size, { "POPGroup.keys.size" })
        var o = off + 16
        var i = 0
        for (s in keyColumnNames) {
            ByteArrayWrapperExt.writeInt4(data, o, ConverterString.encodeString(s, data, mapping), { "POPGroup.keys[$i]" })
            o += 4
            i++
        }
        i = 0
        for ((k, v) in bindings) {
            ByteArrayWrapperExt.writeInt4(data, o, ConverterString.encodeString(k, data, mapping), { "POPGroup.bindings[$i].name" })
            o += 4
            ByteArrayWrapperExt.writeInt4(data, o, v, { "POPGroup.bindings[$i].value" })
            o += 4
            i++
        }
        return off
    }

    public fun encodePOPGroupCount1(data: ByteArrayWrapper, mapping: MutableMap<String, Int>, childf: (Int) -> Int, column: String, by: String): Int {
        val off = ByteArrayWrapperExt.getSize(data)
        ByteArrayWrapperExt.setSize(data, off + 16, true)
        val child = childf(off + 4)
        ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.POPGroupCount1ID, { "operatorID" })
        ByteArrayWrapperExt.writeInt4(data, off + 4, child, { "POPGroupCount1.child" })
        ByteArrayWrapperExt.writeInt4(data, off + 8, ConverterString.encodeString(column, data, mapping), { "POPGroupCount1.column" })
        ByteArrayWrapperExt.writeInt4(data, off + 12, ConverterString.encodeString(by, data, mapping), { "POPGroupCount1.by" })
        return off
    }

    public fun encodePOPGroupCount0(data: ByteArrayWrapper, mapping: MutableMap<String, Int>, childf: (Int) -> Int, column: String): Int {
        val off = ByteArrayWrapperExt.getSize(data)
        ByteArrayWrapperExt.setSize(data, off + 12, true)
        val child = childf(off + 4)
        ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.POPGroupCount0ID, { "operatorID" })
        ByteArrayWrapperExt.writeInt4(data, off + 4, child, { "POPGroupCount0.child" })
        ByteArrayWrapperExt.writeInt4(data, off + 8, ConverterString.encodeString(column, data, mapping), { "POPGroupCount0.column" })
        return off
    }

    public fun encodePOPGroupSorted(data: ByteArrayWrapper, mapping: MutableMap<String, Int>, childf: (Int) -> Int, keyColumnNames: List<String>, projectedVariables: List<String>, bindings: List<Pair<String, Int>>): Int {
        val off = ByteArrayWrapperExt.getSize(data)
        ByteArrayWrapperExt.setSize(data, off + 20 + 4 * (keyColumnNames.size + projectedVariables.size + bindings.size * 2), true)
        val child = childf(off + 4)
        ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.POPGroupSortedID, { "operatorID" })
        ByteArrayWrapperExt.writeInt4(data, off + 4, child, { "POPGroupSorted.child" })
        ByteArrayWrapperExt.writeInt4(data, off + 8, projectedVariables.size, { "POPGroupSorted.variables.size" })
        ByteArrayWrapperExt.writeInt4(data, off + 12, bindings.size, { "POPGroupSorted.bindings.size" })
        ByteArrayWrapperExt.writeInt4(data, off + 16, keyColumnNames.size, { "POPGroupSorted.keys.size" })
        var o = off + 20
        var i = 0
        for (s in projectedVariables) {
            ByteArrayWrapperExt.writeInt4(data, o, ConverterString.encodeString(s, data, mapping), { "POPGroupSorted.variables[$i]" })
            o += 4
            i++
        }
        i = 0
        for (s in keyColumnNames) {
            ByteArrayWrapperExt.writeInt4(data, o, ConverterString.encodeString(s, data, mapping), { "POPGroupSorted.keys[$i]" })
            o += 4
            i++
        }
        i = 0
        for ((k, v) in bindings) {
            ByteArrayWrapperExt.writeInt4(data, o, ConverterString.encodeString(k, data, mapping), { "POPGroupSorted.bindings[$i].name" })
            o += 4
            ByteArrayWrapperExt.writeInt4(data, o, v, { "POPGroupSorted.bindings[$i].value" })
            o += 4
            i++
        }
        return off
    }

    public fun encodePOPGroupWithoutKeyColumn(data: ByteArrayWrapper, mapping: MutableMap<String, Int>, childf: (Int) -> Int, projectedVariables: List<String>, bindings: List<Pair<String, Int>>): Int {
        val off = ByteArrayWrapperExt.getSize(data)
        ByteArrayWrapperExt.setSize(data, off + 20 + 4 * (projectedVariables.size + bindings.size * 2), true)
        val child = childf(off + 4)
        ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.POPGroupWithoutKeyColumnID, { "operatorID" })
        ByteArrayWrapperExt.writeInt4(data, off + 4, child, { "POPGroupWithoutKeyColumn.child" })
        ByteArrayWrapperExt.writeInt4(data, off + 8, projectedVariables.size, { "POPGroupWithoutKeyColumn.variables.size" })
        ByteArrayWrapperExt.writeInt4(data, off + 12, bindings.size, { "POPGroupWithoutKeyColumn.bindings.size" })
        var o = off + 16
        var i = 0
        for (s in projectedVariables) {
            ByteArrayWrapperExt.writeInt4(data, o, ConverterString.encodeString(s, data, mapping), { "POPGroupWithoutKeyColumn.variables[$i]" })
            o += 4
            i++
        }
        i = 0
        for ((k, v) in bindings) {
            ByteArrayWrapperExt.writeInt4(data, o, ConverterString.encodeString(k, data, mapping), { "POPGroupWithoutKeyColumn.binding[$i].name" })
            o += 4
            ByteArrayWrapperExt.writeInt4(data, o, v, { "POPGroupWithoutKeyColumn.binding[$i].value" })
            o += 4
            i++
        }
        return off
    }

    public fun encodePOPSort(data: ByteArrayWrapper, mapping: MutableMap<String, Int>, childf: (Int) -> Int, mySortPriority: List<String>, projectedVariables: List<String>, sortBy: List<String>, sortOrder: Boolean): Int {
        val off = ByteArrayWrapperExt.getSize(data)
        ByteArrayWrapperExt.setSize(data, off + 21 + 4 * (mySortPriority.size + projectedVariables.size + sortBy.size), true)
        val child = childf(off + 4)
        ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.POPSortID, { "operatorID" })
        ByteArrayWrapperExt.writeInt4(data, off + 4, child, { "POPSort.child" })
        ByteArrayWrapperExt.writeInt4(data, off + 8, mySortPriority.size, { "POPSort.sp.size" })
        ByteArrayWrapperExt.writeInt4(data, off + 12, projectedVariables.size, { "POPSort.p.size" })
        ByteArrayWrapperExt.writeInt4(data, off + 16, sortBy.size, { "POPSort.sb.size" })
        ByteArrayWrapperExt.writeInt1(data, off + 20, if (sortOrder) 1 else 0, { "POPSort.sortOrder" })
        var o = off + 21
        var i = 0
        for (s in mySortPriority) {
            ByteArrayWrapperExt.writeInt4(data, o, ConverterString.encodeString(s, data, mapping), { "POPSort.sp[$i]" })
            o += 4
            i++
        }
        i = 0
        for (s in sortBy) {
            ByteArrayWrapperExt.writeInt4(data, o, ConverterString.encodeString(s, data, mapping), { "POPSort.sb[$i]" })
            o += 4
            i++
        }
        i = 0
        for (s in projectedVariables) {
            ByteArrayWrapperExt.writeInt4(data, o, ConverterString.encodeString(s, data, mapping), { "POPSort.p[$i]" })
            o += 4
            i++
        }
        return off
    }

    public fun encodePOPOffset(data: ByteArrayWrapper, mapping: MutableMap<String, Int>, childf: (Int) -> Int, offset: Int): Int {
        val off = ByteArrayWrapperExt.getSize(data)
        ByteArrayWrapperExt.setSize(data, off + 12, true)
        val child = childf(off + 4)
        ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.POPOffsetID, { "operatorID" })
        ByteArrayWrapperExt.writeInt4(data, off + 4, child, { "POPOffset.child" })
        ByteArrayWrapperExt.writeInt4(data, off + 8, offset, { "POPOffset.offset" })
        return off
    }

    public fun encodePOPMakeBooleanResult(data: ByteArrayWrapper, mapping: MutableMap<String, Int>, childf: (Int) -> Int): Int {
        val off = ByteArrayWrapperExt.getSize(data)
        ByteArrayWrapperExt.setSize(data, off + 8, true)
        val child = childf(off + 4)
        ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.POPMakeBooleanResultID, { "operatorID" })
        ByteArrayWrapperExt.writeInt4(data, off + 4, child, { "POPMakeBooleanResult.child" })
        return off
    }

    public fun encodePOPReduced(data: ByteArrayWrapper, mapping: MutableMap<String, Int>, childf: (Int) -> Int, projectedVariables: List<String>): Int {
        val off = ByteArrayWrapperExt.getSize(data)
        ByteArrayWrapperExt.setSize(data, off + 12 + 4 * projectedVariables.size, true)
        val child = childf(off + 4)
        ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.POPReducedID, { "operatorID" })
        ByteArrayWrapperExt.writeInt4(data, off + 4, child, { "POPReduced.child" })
        ByteArrayWrapperExt.writeInt4(data, off + 8, projectedVariables.size, { "POPReduced.variables.size" })
        for (i in 0 until projectedVariables.size) {
            ByteArrayWrapperExt.writeInt4(data, off + 12 + 4 * i, ConverterString.encodeString(projectedVariables[i], data, mapping), { "POPReduced.variables[$i]" })
        }
        return off
    }

    public fun encodePOPTripleStoreIterator(data: ByteArrayWrapper, mapping: MutableMap<String, Int>, target: Pair<String, String>, index: Int, child0f: Boolean, child0c: DictionaryValueType?, child0v: String?, child1f: Boolean, child1c: DictionaryValueType?, child1v: String?, child2f: Boolean, child2c: DictionaryValueType?, child2v: String?): Int {
        val off = ByteArrayWrapperExt.getSize(data)
        ByteArrayWrapperExt.setSize(data, off + 17 + 3 * if (DictionaryValueHelper.getSize() > 4) DictionaryValueHelper.getSize() else 4, true)
        ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.POPTripleStoreIterator, { "operatorID" })
        ByteArrayWrapperExt.writeInt4(data, off + 4, ConverterString.encodeString(target.first, data, mapping), { "POPTripleStoreIterator.target.first" })
        ByteArrayWrapperExt.writeInt4(data, off + 8, ConverterString.encodeString(target.second, data, mapping), { "POPTripleStoreIterator.target.second" })
        ByteArrayWrapperExt.writeInt4(data, off + 12, index, { "POPTripleStoreIterator.IndexPattern" })
        var childFlag = 0
        var o = off + 17
        if (child0f) {
            childFlag = childFlag + (1 shl 0)
            DictionaryValueHelper.toByteArray(data, o, child0c!!, { "POPTripleStoreIterator.constant[0]" })
            o += DictionaryValueHelper.getSize()
        } else {
            ByteArrayWrapperExt.writeInt4(data, o, ConverterString.encodeString(child0v!!, data, mapping), { "POPTripleStoreIterator.variable[0]" })
            o += 4
        }
        if (child1f) {
            childFlag = childFlag + (1 shl 1)
            DictionaryValueHelper.toByteArray(data, o, child1c!!, { "POPTripleStoreIterator.constant[1]" })
            o += DictionaryValueHelper.getSize()
        } else {
            ByteArrayWrapperExt.writeInt4(data, o, ConverterString.encodeString(child1v!!, data, mapping), { "POPTripleStoreIterator.variable[1]" })
            o += 4
        }
        if (child2f) {
            childFlag = childFlag + (1 shl 2)
            DictionaryValueHelper.toByteArray(data, o, child2c!!, { "POPTripleStoreIterator.constant[2]" })
            o += DictionaryValueHelper.getSize()
        } else {
            ByteArrayWrapperExt.writeInt4(data, o, ConverterString.encodeString(child2v!!, data, mapping), { "POPTripleStoreIterator.variable[2]" })
            o += 4
        }
        ByteArrayWrapperExt.writeInt1(data, off + 16, childFlag, { "POPTripleStoreIterator.childFlag" })
        return off
    }

    public fun encodePOPBind(data: ByteArrayWrapper, mapping: MutableMap<String, Int>, childf: (Int) -> Int, variablesOut: List<String>, valueOff: Int, name: String): Int {
        val off = ByteArrayWrapperExt.getSize(data)
        ByteArrayWrapperExt.setSize(data, off + 20 + variablesOut.size * 4, true)
        val child = childf(off + 4)
        ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.POPBindID, { "operatorID" })
        ByteArrayWrapperExt.writeInt4(data, off + 4, child, { "POPBind.child" })
        ByteArrayWrapperExt.writeInt4(data, off + 8, valueOff)
        ByteArrayWrapperExt.writeInt4(data, off + 12, ConverterString.encodeString(name, data, mapping), { "POPBind.column" })
        ByteArrayWrapperExt.writeInt4(data, off + 16, variablesOut.size, { "POPBind.variables.size" })
        for (i in 0 until variablesOut.size) {
            ByteArrayWrapperExt.writeInt4(data, off + 20 + 4 * i, ConverterString.encodeString(variablesOut[i], data, mapping), { "POPBind.variables[$i]" })
        }
        return off
    }

    public fun encodePOPFilter(data: ByteArrayWrapper, mapping: MutableMap<String, Int>, childf: (Int) -> Int, variablesOut: List<String>, filterOff: Int): Int {
        val off = ByteArrayWrapperExt.getSize(data)
        ByteArrayWrapperExt.setSize(data, off + 16 + 4 * variablesOut.size, true)
        val child = childf(off + 4)
        ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.POPFilterID, { "operatorID" })
        ByteArrayWrapperExt.writeInt4(data, off + 4, child, { "POPFilter.child" })
        ByteArrayWrapperExt.writeInt4(data, off + 8, filterOff)
        ByteArrayWrapperExt.writeInt4(data, off + 12, variablesOut.size, { "POPFilter.variables.size" })
        for (i in 0 until variablesOut.size) {
            ByteArrayWrapperExt.writeInt4(data, off + 16 + 4 * i, ConverterString.encodeString(variablesOut[i], data, mapping), { "POPFilter.variables[$i]" })
        }
        return off
    }

    public fun encodePOPModify(data: ByteArrayWrapper, mapping: MutableMap<String, Int>, childf: (Int) -> Int, modify: Array<Pair<LOPTriple, EModifyType /* = Int */>>,targetName:String): Int {
        val off = ByteArrayWrapperExt.getSize(data)
        ByteArrayWrapperExt.setSize(data, off + 12 + modify.size * (9 + 3 * if (DictionaryValueHelper.getSize() > 4) DictionaryValueHelper.getSize() else 4), true)
        val child = childf(off + 4)
        ByteArrayWrapperExt.writeInt4(data, off + 0, EOperatorIDExt.POPModifyID, { "operatorID" })
        ByteArrayWrapperExt.writeInt4(data, off + 4, ConverterString.encodeString(targetName, data, mapping), { "POPModify.targetName" })
        ByteArrayWrapperExt.writeInt4(data, off + 8, child, { "POPModify.child" })
        ByteArrayWrapperExt.writeInt4(data, off + 12, modify.size, { "POPModify.modify.size" })
        val steph = if (DictionaryValueHelper.getSize() > 4) DictionaryValueHelper.getSize() else 4
        val step = 9 + 3 * steph
        var i = 0
        for ((k, v) in modify) {
            var o = off + 16 + i * step
            ByteArrayWrapperExt.writeInt4(data, o, v, { "POPModify.modify[$i].v" })
            ByteArrayWrapperExt.writeInt4(data, o + 4, ConverterString.encodeString(k.graph, data, mapping), { "POPModify.modify[$i].graph" })
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
                ByteArrayWrapperExt.writeInt4(data, o + 9, ConverterString.encodeString(s.name, data, mapping), { "POPModify.modify[$i].child[0].v" })
            }
            if (p is AOPConstant) {
                flag += 0x4
                DictionaryValueHelper.toByteArray(data, o + 9 + steph, p.value, { "POPModify.modify[$i].child[1].c" })
            } else {
                p as AOPVariable
                ByteArrayWrapperExt.writeInt4(data, o + 9 + steph, ConverterString.encodeString(p.name, data, mapping), { "POPModify.modify[$i].child[1].v" })
            }
            if (oo is AOPConstant) {
                flag += 0x8
                DictionaryValueHelper.toByteArray(data, o + 9 + steph + steph, oo.value, { "POPModify.modify[$i].child[2].c" })
            } else {
                oo as AOPVariable
                ByteArrayWrapperExt.writeInt4(data, o + 9 + steph + steph, ConverterString.encodeString(oo.name, data, mapping), { "POPModify.modify[$i].child[2].v" })
            }
            ByteArrayWrapperExt.writeInt1(data, o + 8, flag, { "POPModify.modify[$i].flag" })
            i++
        }
        return off
    }
}
