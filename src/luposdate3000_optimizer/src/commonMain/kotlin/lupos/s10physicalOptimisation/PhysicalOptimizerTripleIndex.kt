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
package lupos.s10physicalOptimisation

import lupos.s00misc.EIndexPatternExt
import lupos.s00misc.EOptimizerIDExt
import lupos.s00misc.ESortTypeExt
import lupos.s00misc.SanityCheck
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.IAOPBase
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.noinput.LOPTriple
import lupos.s04logicalOperators.singleinput.LOPProjection
import lupos.s05tripleStore.POPTripleStoreIterator
import lupos.s05tripleStore.tripleStoreManager
import lupos.s08logicalOptimisation.OptimizerBase
import lupos.s09physicalOperators.POPBase
import lupos.s09physicalOperators.singleinput.POPProjection

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
            val store = tripleStoreManager.getGraph(node.graph)
            val params = Array<IAOPBase>(3) {
                val res2 = node.children[it] as AOPBase
                SanityCheck {
                    if (res2 is AOPVariable) {
                        SanityCheck.check { projectedVariables.contains(res2.name) || res2.name == "_" }
                    }
                }
                res2
            }
            SanityCheck {
                for (i in 0 until node.mySortPriority.size) {
                    SanityCheck.check { node.mySortPriority[i].sortType == ESortTypeExt.FAST }
                }
            }
            val targetIdx = LOPTriple.getIndex(node.children, node.mySortPriority.map { it.variableName })
            println("targetIdx ${EIndexPatternExt.names[targetIdx]}")
            res = store.getIterator(query, params, targetIdx)
// println("usedIdx ${((res as POPTripleStoreIterator).tripleStoreIndexDescription as TripleStoreIndexDescription).idx_set.map{EIndexPatternExt.names[it]}}")
            println("PhysicalOptimizerTripleIndex : initialize any Iterator ${res.getUUID()}")
            if (res is POPTripleStoreIterator) {
                res.sortPriorities = node.sortPriorities
                res.mySortPriority = node.mySortPriority
                res.sortPrioritiesInitialized = node.sortPrioritiesInitialized
            }
        }
        return res
    }
}
