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
        SanityCheck.check(
            { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_optimizer_logical/src/commonMain/kotlin/lupos/optimizer/logical/LogicalOptimizerBuildCustomJoinOrderML.kt:25"/*SOURCE_FILE_END*/ },
            {
                allChilds.isNotEmpty()
            }
        )
//        try {
        val nodes = mutableListOf<IOPBase>()
        nodes.addAll(allChilds)

        when (joinOrder) {
            0 -> {
                val b = nodes.removeAt(1)
                val a = nodes.removeAt(0)
                val c = LOPJoin(root.query, a, b, false)
                val d = nodes.removeAt(0)
                val e = LOPJoin(root.query, c, d, false)
                val f = nodes.removeAt(0)
                val g = LOPJoin(root.query, e, f, false)
                nodes.add(g)
                return nodes[0]
            }

            1 -> {
                val b = nodes.removeAt(2)
                val a = nodes.removeAt(0)
                val c = LOPJoin(root.query, a, b, false)
                val d = nodes.removeAt(0)
                val e = LOPJoin(root.query, c, d, false)
                val f = nodes.removeAt(0)
                val g = LOPJoin(root.query, e, f, false)
                nodes.add(g)
                return nodes[0]
            }

            2 -> {
                val b = nodes.removeAt(2)
                val a = nodes.removeAt(1)
                val c = LOPJoin(root.query, a, b, false)
                val d = nodes.removeAt(0)
                val e = LOPJoin(root.query, c, d, false)
                val f = nodes.removeAt(0)
                val g = LOPJoin(root.query, e, f, false)
                nodes.add(g)
                return nodes[0]
            }

            3 -> {
                val b = nodes.removeAt(3)
                val a = nodes.removeAt(0)
                val c = LOPJoin(root.query, a, b, false)
                val d = nodes.removeAt(0)
                val e = LOPJoin(root.query, c, d, false)
                val f = nodes.removeAt(0)
                val g = LOPJoin(root.query, e, f, false)
                nodes.add(g)
                return nodes[0]
            }

            4 -> {
                val b = nodes.removeAt(3)
                val a = nodes.removeAt(1)
                val c = LOPJoin(root.query, a, b, false)
                val d = nodes.removeAt(0)
                val e = LOPJoin(root.query, c, d, false)
                val f = nodes.removeAt(0)
                val g = LOPJoin(root.query, e, f, false)
                nodes.add(g)
                return nodes[0]
            }

            5 -> {
                val b = nodes.removeAt(3)
                val a = nodes.removeAt(2)
                val c = LOPJoin(root.query, a, b, false)
                val d = nodes.removeAt(0)
                val e = LOPJoin(root.query, c, d, false)
                val f = nodes.removeAt(0)
                val g = LOPJoin(root.query, e, f, false)
                nodes.add(g)
                return nodes[0]
            }

            6 -> {
                val b = nodes.removeAt(4)
                val a = nodes.removeAt(0)
                val c = LOPJoin(root.query, a, b, false)
                val d = nodes.removeAt(0)
                val e = LOPJoin(root.query, c, d, false)
                val f = nodes.removeAt(0)
                val g = LOPJoin(root.query, e, f, false)
                nodes.add(g)
                return nodes[0]
            }

            7 -> {
                val b = nodes.removeAt(4)
                val a = nodes.removeAt(1)
                val c = LOPJoin(root.query, a, b, false)
                val d = nodes.removeAt(0)
                val e = LOPJoin(root.query, c, d, false)
                val f = nodes.removeAt(0)
                val g = LOPJoin(root.query, e, f, false)
                nodes.add(g)
                return nodes[0]
            }

            8 -> {
                val b = nodes.removeAt(4)
                val a = nodes.removeAt(2)
                val c = LOPJoin(root.query, a, b, false)
                val d = nodes.removeAt(0)
                val e = LOPJoin(root.query, c, d, false)
                val f = nodes.removeAt(0)
                val g = LOPJoin(root.query, e, f, false)
                nodes.add(g)
                return nodes[0]
            }

            9 -> {
                val b = nodes.removeAt(4)
                val a = nodes.removeAt(3)
                val c = LOPJoin(root.query, a, b, false)
                val d = nodes.removeAt(0)
                val e = LOPJoin(root.query, c, d, false)
                val f = nodes.removeAt(0)
                val g = LOPJoin(root.query, e, f, false)
                nodes.add(g)
                return nodes[0]
            }

            10 -> {
                val b = nodes.removeAt(5)
                val a = nodes.removeAt(0)
                val c = LOPJoin(root.query, a, b, false)
                val d = nodes.removeAt(0)
                val e = LOPJoin(root.query, c, d, false)
                val f = nodes.removeAt(0)
                val g = LOPJoin(root.query, e, f, false)
                nodes.add(g)
                return nodes[0]
            }

            11 -> {
                val b = nodes.removeAt(5)
                val a = nodes.removeAt(1)
                val c = LOPJoin(root.query, a, b, false)
                val d = nodes.removeAt(0)
                val e = LOPJoin(root.query, c, d, false)
                val f = nodes.removeAt(0)
                val g = LOPJoin(root.query, e, f, false)
                nodes.add(g)
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
