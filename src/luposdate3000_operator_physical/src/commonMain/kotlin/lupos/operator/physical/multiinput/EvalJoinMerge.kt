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

import lupos.shared.ColumnIteratorChildIterator
import lupos.shared.DictionaryValueTypeArray
import lupos.shared.IQuery
import lupos.shared.operator.iterator.ColumnIterator
import lupos.shared.operator.iterator.IteratorBundle
public object EvalJoinMerge {
    public operator fun invoke(
        query: IQuery,
        child0: IteratorBundle,
        child1: IteratorBundle,
        projectedVariables: List<String>,
        joinVariableOrder: List<String>,
    ): IteratorBundle {
        // setup columns

        for (v in child0.columns.keys) {
            if (v in child1.columns.keys) {
                if (v !in joinVariableOrder) {
                    TODO("missing join sort column order")
                }
            }
        }

val columnsJSize=joinVariableOrder.size
val columnsO0Size=child0.columns.keys.size-columnsJSize
val columnsO1Size=child1.columns.keys.size-columnsJSize

        val columnsINO0 = mutableListOf<ColumnIterator>()
        val columnsINO1 = mutableListOf<ColumnIterator>()
        val columnsINJ0 = mutableListOf<ColumnIterator>()
        val columnsINJ1 = mutableListOf<ColumnIterator>()
        val columnsOUT0 = mutableListOf<ColumnIteratorChildIterator?>()
        val columnsOUT1 = mutableListOf<ColumnIteratorChildIterator?>()
        val columnsOUTJ = mutableListOf<ColumnIteratorChildIterator?>()

        val outMap = mutableMapOf<String, ColumnIterator>()

        val key0 = DictionaryValueTypeArray(columnsJSize)
        val key1 = DictionaryValueTypeArray(columnsJSize)


        for (name in joinVariableOrder) {
            var iterator: POPJoinMerge_Iterator? = null
            if (projectedVariables.contains(name)) {
                iterator = POPJoinMerge_Iterator(query, columnsINJ0, columnsINJ1, columnsINO0, columnsINO1, columnsOUT0, columnsOUT1, columnsOUTJ, key0, key1,columnsJSize,columnsO0Size,columnsO1Size)
                outMap[name] = iterator
            }
            columnsINJ0.add(child0.columns[name]!!)
            columnsINJ1.add(child1.columns[name]!!)
            columnsOUTJ.add(iterator)
        }
        for (name in child0.columns.keys) {
            if (!joinVariableOrder.contains(name)) {
                var iterator: POPJoinMerge_Iterator? = null
                if (projectedVariables.contains(name)) {
                    iterator = POPJoinMerge_Iterator(query, columnsINJ0, columnsINJ1, columnsINO0, columnsINO1, columnsOUT0, columnsOUT1, columnsOUTJ, key0, key1,columnsJSize,columnsO0Size,columnsO1Size)
                    outMap[name] = iterator
                }
                columnsINO0.add(child0.columns[name]!!)
                columnsOUT0.add(iterator)
            }
        }
        for (name in child1.columns.keys) {
            if (!joinVariableOrder.contains(name)) {
                var iterator: POPJoinMerge_Iterator? = null
                if (projectedVariables.contains(name)) {
                    iterator = POPJoinMerge_Iterator(query, columnsINJ0, columnsINJ1, columnsINO0, columnsINO1, columnsOUT0, columnsOUT1, columnsOUTJ, key0, key1,columnsJSize,columnsO0Size,columnsO1Size)
                    outMap[name] = iterator
                }
                columnsINO1.add(child1.columns[name]!!)
                columnsOUT1.add(iterator)
            }
        }

        val res: IteratorBundle
        if (projectedVariables.size == 0) {
            val x = POPJoinMerge_Iterator(query, columnsINJ0, columnsINJ1, columnsINO0, columnsINO1, columnsOUT0, columnsOUT1, columnsOUTJ, key0, key1,columnsJSize,columnsO0Size,columnsO1Size)
            columnsOUTJ.add(0,x)
            res = POPJoinMerge_Bundle(columnsINJ0, columnsINJ1, x)
            for (it in columnsINO0) {
                it.close()
            }
            for (it in columnsINO1) {
                it.close()
            }
        } else {
            res = IteratorBundle(outMap)
        }
        for (i in 0 until columnsJSize) {
            key0[i] = columnsINJ0[i].next()
        }
        for (i in 0 until columnsJSize) {
            key1[i] = columnsINJ1[i].next()
        }
        return res
    }
}
