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
package lupos.operator.physical.singleinput

import lupos.shared.DictionaryValueHelper
import lupos.shared.SanityCheck
import lupos.shared.operator.iterator.ColumnIterator
import lupos.shared.operator.iterator.IteratorBundle

public object EvalProjection {
    public operator fun invoke(
        child: IteratorBundle,
        variables: List<String>,
        childVariables: List<String>,
    ): IteratorBundle {
        val outMap = mutableMapOf<String, ColumnIterator>()
        when {
            variables.containsAll(childVariables) -> {
                return child
            }
            variables.isEmpty() -> {
                val variables2 = childVariables
                SanityCheck(
                    { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_physical/src/commonMain/kotlin/lupos/operator/physical/singleinput/EvalProjection.kt:37"/*SOURCE_FILE_END*/ },
                    {
                        SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_physical/src/commonMain/kotlin/lupos/operator/physical/singleinput/EvalProjection.kt:39"/*SOURCE_FILE_END*/ }, { variables2.isNotEmpty() })
                        for (variable in variables2) {
                            SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_physical/src/commonMain/kotlin/lupos/operator/physical/singleinput/EvalProjection.kt:41"/*SOURCE_FILE_END*/ }, { child.columns[variable] != null })
                        }
                    }
                )
                val column = child.columns[variables2[0]]!!
                return object : IteratorBundle(0) {
                    override /*suspend*/ fun hasNext2(): Boolean {
                        return column.next() != DictionaryValueHelper.nullValue
                    }

                    override /*suspend*/ fun hasNext2Close() {
                        column.close()
                    }
                }
            }
            else -> {
                for (variable in variables) {
                    SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_physical/src/commonMain/kotlin/lupos/operator/physical/singleinput/EvalProjection.kt:58"/*SOURCE_FILE_END*/ }, { child.columns[variable] != null })
                    outMap[variable] = child.columns[variable]!!
                }
                return IteratorBundle(outMap)
            }
        }
    }
}
