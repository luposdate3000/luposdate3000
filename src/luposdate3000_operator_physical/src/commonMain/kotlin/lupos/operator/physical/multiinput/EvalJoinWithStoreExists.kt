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

import lupos.operator.arithmetik.noinput.AOPConstant
import lupos.operator.arithmetik.noinput.AOPVariable
import lupos.operator.logical.noinput.LOPTriple
import lupos.shared.DictionaryValueHelper
import lupos.shared.IQuery
import lupos.shared.Partition
import lupos.shared.SanityCheck
import lupos.shared.operator.IAOPBase
import lupos.shared.operator.iterator.ColumnIterator
import lupos.shared.operator.iterator.IteratorBundle

public object EvalJoinWithStoreExists {
    public operator fun invoke(
        childAv: IteratorBundle,
        childB: LOPTriple,
        query: IQuery,
        parent: Partition,
    ): IteratorBundle {
        val iteratorsHelper = mutableListOf<ColumnIterator>()
        val params = Array(3) { childB.children[it] as IAOPBase }
        var res = IteratorBundle(0)
        val mappingHelper = mutableListOf<Int>()
        for (i in 0 until 3) {
            val p = params[i]
            if (p is AOPVariable && p.name != "_") {
                mappingHelper.add(i)
                iteratorsHelper.add(childAv.columns[p.name]!!)
                params[i] = AOPConstant(query, 0)
            }
        }
        val index = LOPTriple.getIndex(params.map { it }.toTypedArray(), listOf())
        var done = false
        val iterators = iteratorsHelper.toTypedArray()
        val mapping = IntArray(mappingHelper.size) { mappingHelper[it] }
        SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_physical/src/commonMain/kotlin/lupos/operator/physical/multiinput/EvalJoinWithStoreExists.kt:52"/*SOURCE_FILE_END*/ }, { mapping.isNotEmpty() })
        for (i in mapping.indices) {
            val tmp = iterators[i].next()
            if (tmp == DictionaryValueHelper.nullValue) {
                done = true
                for (element in iterators) {
                    element.close()
                }
                SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_physical/src/commonMain/kotlin/lupos/operator/physical/multiinput/EvalJoinWithStoreExists.kt:60"/*SOURCE_FILE_END*/ }, { i == 0 })
                break
            } else {
                params[mapping[i]] = AOPConstant(query, tmp)
            }
        }
        if (!done) {
            val distributedStore = query.getInstance().tripleStoreManager!!.getGraph(childB.graph)
            var iteratorB = distributedStore.getIterator(query, params, index).evaluate(parent)
            res = object : IteratorBundle(0) {
                override /*suspend*/ fun hasNext2(): Boolean {
                    val t = iteratorB.hasNext2()
                    loop@ while (!t && !done) {
                        for (i in mapping.indices) {
                            val tmp = iterators[i].next()
                            if (tmp == DictionaryValueHelper.nullValue) {
                                for (element in iterators) {
                                    element.close()
                                }
                                done = true
                                SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_physical/src/commonMain/kotlin/lupos/operator/physical/multiinput/EvalJoinWithStoreExists.kt:80"/*SOURCE_FILE_END*/ }, { i == 0 })
                                break@loop
                            } else {
                                params[mapping[i]] = AOPConstant(query, tmp)
                            }
                        }
                        if (!done) {
                            iteratorB = distributedStore.getIterator(query, params, index).evaluate(parent)
                        }
                    }
                    return t
                }

                override /*suspend*/ fun hasNext2Close() {
                    for (element in iterators) {
                        element.close()
                    }
                }
            }
        }
        return res
    }
}
