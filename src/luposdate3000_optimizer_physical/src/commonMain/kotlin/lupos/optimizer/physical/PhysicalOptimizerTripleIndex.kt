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
package lupos.optimizer.physical

import lupos.operator.arithmetik.AOPBase
import lupos.operator.arithmetik.noinput.AOPVariable
import lupos.operator.base.Query
import lupos.operator.logical.noinput.LOPTriple
import lupos.operator.logical.singleinput.LOPProjection
import lupos.operator.physical.POPBase
import lupos.operator.physical.singleinput.POPProjection
import lupos.optimizer.logical.EOptimizerIDExt
import lupos.optimizer.logical.OptimizerBase
import lupos.shared.ESortTypeExt
import lupos.shared.SanityCheck
import lupos.shared.operator.IAOPBase
import lupos.shared.operator.IOPBase
import lupos.triple_store_manager.POPTripleStoreIterator

public class PhysicalOptimizerTripleIndex(query: Query) : OptimizerBase(query, EOptimizerIDExt.PhysicalOptimizerTripleIndexID, "PhysicalOptimizerTripleIndex") {
    override /*suspend*/ fun optimize(node: IOPBase, parent: IOPBase?, onChange: () -> Unit): IOPBase {
        var res = node
        if (node is LOPTriple) {
            val projectedVariables: List<String> = when {
                parent is LOPProjection -> {
                    parent.getProvidedVariableNames()
                }
                parent is POPProjection -> {
                    parent.getProvidedVariableNamesInternal()
                }
                node is POPBase -> {
                    node.getProvidedVariableNamesInternal()
                }
                else -> {
                    node.getProvidedVariableNames()
                }
            }
            onChange()
            val store = (query.getInstance().tripleStoreManager!!).getGraph(node.graph)
            val params = Array<IAOPBase>(3) {
                val res2 = node.children[it] as AOPBase
                SanityCheck(
                    { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_optimizer_physical/src/commonMain/kotlin/lupos/optimizer/physical/PhysicalOptimizerTripleIndex.kt:56"/*SOURCE_FILE_END*/ },
                    {
                        if (res2 is AOPVariable) {
if(SanityCheck.enabled){if(!( projectedVariables.contains(res2.name) || res2.name == "_" )){throw Exception("SanityCheck failed")}}
                        }
                    }
                )
                res2
            }
            SanityCheck(
                { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_optimizer_physical/src/commonMain/kotlin/lupos/optimizer/physical/PhysicalOptimizerTripleIndex.kt:66"/*SOURCE_FILE_END*/ },
                {
                    for (i in 0 until node.mySortPriority.size) {
if(SanityCheck.enabled){if(!( node.mySortPriority[i].sortType == ESortTypeExt.FAST )){throw Exception("SanityCheck failed")}}
                    }
                }
            )
            val targetIdx = LOPTriple.getIndex(node.children, node.mySortPriority.map { it.variableName })
            res = store.getIterator(query, params, targetIdx)
            if (res is POPTripleStoreIterator) {
                res.sortPriorities = node.sortPriorities
                res.mySortPriority = node.mySortPriority
                res.sortPrioritiesInitialized = node.sortPrioritiesInitialized
            }
        }
        return res
    }
}
