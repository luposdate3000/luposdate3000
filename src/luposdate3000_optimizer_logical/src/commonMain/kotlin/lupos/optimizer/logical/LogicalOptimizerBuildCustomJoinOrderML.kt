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
package lupos.optimizer.logical

import lupos.operator.logical.multiinput.LOPJoin
import lupos.shared.SanityCheck
import lupos.shared.operator.IOPBase

public object LogicalOptimizerBuildCustomJoinOrderML {
    public /*suspend*/ operator fun invoke(allChilds: List<IOPBase>, root: LOPJoin, joinOrder: Int): IOPBase? {
        SanityCheck.check { allChilds.isNotEmpty() }
//        try {
        val nodes = mutableListOf<IOPBase>()
        nodes.addAll(allChilds)

        when (joinOrder) {
            // Join Pattern: 01 - 2
            0 -> {
                val b = nodes.removeAt(1)
                val a = nodes.removeAt(0)
                val c = LOPJoin(root.query, a, b, false)
                val d = nodes.removeAt(0)
                val e = LOPJoin(root.query, c, d, false)
                nodes.add(e)
                return nodes[0]
            }
            // Join Pattern: 02 - 1
            1 -> {
                val b = nodes.removeAt(2)
                val a = nodes.removeAt(0)
                val c = LOPJoin(root.query, a, b, false)
                val d = nodes.removeAt(0)
                val e = LOPJoin(root.query, c, d, false)
                nodes.add(e)
                return nodes[0]
            }
            // Join Pattern: 12 - 0
            2 -> {
                val b = nodes.removeAt(2)
                val a = nodes.removeAt(1)
                val c = LOPJoin(root.query, a, b, false)
                val d = nodes.removeAt(0)
                val e = LOPJoin(root.query, c, d, false)
                nodes.add(e)
                return nodes[0]
            }

            else -> return null
        }

//            loop2@ while (nodes.size > 1) {
//                for (i in 0 until nodes.size) {
//                    for (j in i + 1 until nodes.size) {
//
//
//                    }
//                }
//                var bestA: Int = 0
//                var bestB: Int = 1
//                val b = nodes.removeAt(bestB) // first remove at the end of list
//                val a = nodes.removeAt(bestA) // afterwards in front of b otherwise, the index would be wrong
//                val c = LOPJoin(root.query, a, b, false)
//                nodes.add(c)
//            }
//            return nodes[0]
//        } catch (e: HistogramNotImplementedException) {
//            e.printStackTrace()
//            return null
//        }
/*Coverage Unreachable*/
    }
}
