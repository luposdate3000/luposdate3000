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

import lupos.operator.base.iterator.ColumnIteratorMultiIterator
import lupos.operator.physical.POPBase
import lupos.shared.EOperatorIDExt
import lupos.shared.ESortPriorityExt
import lupos.shared.IQuery
import lupos.shared.Partition
import lupos.shared.PartitionHelper
import lupos.shared.SanityCheck
import lupos.shared.operator.IOPBase
import lupos.shared.operator.iterator.ColumnIterator
import lupos.shared.operator.iterator.IteratorBundle
public object EvalUnion{
public operator fun invoke(): IteratorBundle {
        val variables = getProvidedVariableNames()
        SanityCheck(
            { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_physical/src/commonMain/kotlin/lupos/operator/physical/multiinput/EvalUnion.kt:33"/*SOURCE_FILE_END*/ },
            {
                for (v in children[0].getProvidedVariableNames()) {
                    getPartitionCount(v)
                }
                for (v in children[1].getProvidedVariableNames()) {
                    getPartitionCount(v)
                }
            }
        )
        SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_physical/src/commonMain/kotlin/lupos/operator/physical/multiinput/EvalUnion.kt:43"/*SOURCE_FILE_END*/ }, { children[0].getProvidedVariableNames().containsAll(variables) })
        SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_physical/src/commonMain/kotlin/lupos/operator/physical/multiinput/EvalUnion.kt:44"/*SOURCE_FILE_END*/ }, { children[1].getProvidedVariableNames().containsAll(variables) })
        val outMap = mutableMapOf<String, ColumnIterator>()
        val childA = children[0].evaluate(parent)
        val childB = children[1].evaluate(parent)
        if (variables.isNotEmpty()) {
            for (variable in variables) {
                outMap[variable] = ColumnIteratorMultiIterator(listOf(childA.columns[variable]!!, childB.columns[variable]!!))
            }
            return IteratorBundle(outMap)
        } else {
            SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_physical/src/commonMain/kotlin/lupos/operator/physical/multiinput/EvalUnion.kt:54"/*SOURCE_FILE_END*/ }, { childA.hasCountMode() && childB.hasCountMode() })
            return object : IteratorBundle(0) {
                override /*suspend*/ fun hasNext2(): Boolean {
                    return childA.hasNext2() || childB.hasNext2()
                }

                override /*suspend*/ fun hasNext2Close() {
                    childA.hasNext2Close()
                    childB.hasNext2Close()
                }
            }
        }
    }
}
