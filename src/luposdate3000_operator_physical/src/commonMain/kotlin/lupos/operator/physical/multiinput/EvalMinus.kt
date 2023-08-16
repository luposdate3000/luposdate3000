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
package lupos.operator.physical.multiinput

import lupos.operator.base.iterator.RowIteratorBuf
import lupos.operator.base.iterator.RowIteratorMerge
import lupos.operator.base.iterator.RowIteratorMinus
import lupos.shared.DictionaryValueHelper
import lupos.shared.DictionaryValueType
import lupos.shared.DictionaryValueTypeArray
import lupos.shared.operator.iterator.IteratorBundle

public object EvalMinus {
    internal fun idx2columns(idx: Int, columns: List<String>): List<String> {
        val useColumns = mutableListOf<String>()
        for (i in 0 until columns.size) {
            if (idx and (1 shl i) != 0) {
                useColumns.add(columns[i])
            }
        }
        return useColumns
    }
    public operator fun invoke(
        childA: IteratorBundle,
        childB: IteratorBundle,
        projectedVariables: List<String>,
    ): IteratorBundle {
        val rowA = childA.rows
        val rowB = childB.rows

        val minusColumnsA = (rowA.columns.toSet().intersect(rowB.columns.toSet())).toList()
        val minusColumns = mutableListOf<String>()
        for (x in rowB.columns) {
            if (x in minusColumnsA) {
                minusColumns.add(x)
            }
        }
        val len = 1 shl minusColumns.size
        val subtrahends = Array(len) { it -> mutableListOf<DictionaryValueType> () }
        while (true) {
            val idx = rowB.next()
            if (idx < 0) {
                break
            }
            val tmp = mutableListOf<DictionaryValueType> ()
            var target = 0
            for (i in 0 until minusColumns.size) {
                val v = rowB.buf[idx + i]
                if (v != DictionaryValueHelper.undefValue) {
                    target = target or (1 shl i)
                    tmp.add(v)
                }
            }
            subtrahends[target].addAll(tmp)
        }
        val comparator  = ValueComparatorNothing()
        var x = rowA
        for (i in 1 until subtrahends.size) {
            val namesOut2 = projectedVariables.toTypedArray()
            val namesTmp =idx2columns(i,minusColumns).toTypedArray()
            val namesOut=namesTmp.toMutableList()
            for (y in namesOut2){
if(!namesOut.contains(y)){
namesOut.add(y)
}
}
            val a = DictionaryValueTypeArray(subtrahends[i].size) { subtrahends[i][it] }
            val b = RowIteratorBuf(a, namesTmp, subtrahends[i].size)
            val c = RowIteratorMerge( b,  comparator,  namesTmp.size,  namesTmp)
            val d = RowIteratorMerge(x,  comparator,  namesTmp.size,  namesOut.toTypedArray())
            val x2 = RowIteratorMinus(d, c, namesOut.toTypedArray())
            x2._init()
            x = x2
        }
        return IteratorBundle(x)
    }
}

public class ValueComparatorNothing() : Comparator<DictionaryValueType> {
    override fun compare(a: DictionaryValueType, b: DictionaryValueType): Int {
        val x=a-b
if(x<0){
return -1
}else if(x>0){
return 1
}else{
return 0
}
    }
}
