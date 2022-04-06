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
import lupos.shared.myPrintStackTrace

import lupos.operator.logical.multiinput.LOPJoin
import lupos.shared.SanityCheck
import lupos.shared.operator.IOPBase

public object LogicalOptimizerBuildCustomJoinOrderML {
    public /*suspend*/ operator fun invoke(allChilds: List<IOPBase>, root: LOPJoin, joinOrder: Int): IOPBase? {
        SanityCheck.check(
            { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_optimizer_logical/src/commonMain/kotlin/lupos/optimizer/logical/LogicalOptimizerBuildCustomJoinOrderML.kt:26"/*SOURCE_FILE_END*/ },
            {
                allChilds.isNotEmpty()
            }
        )
//        try {
        val nodes = mutableListOf<IOPBase>()
        nodes.addAll(allChilds)

        // 18 Join Orders Possible
	/*
	        val b = nodes.removeAt(1)
                val a = nodes.removeAt(0)
                val c = LOPJoin(root.query, a, b, false)
                val d = nodes.removeAt(0)
                val e = LOPJoin(root.query, c, d, false)
                val f = nodes.removeAt(0)
                val g = LOPJoin(root.query, e, f, false)
                nodes.add(g)
                return nodes[0]
	*/
        when (joinOrder) {
            // pattern: (01 2 3)
            0 -> {
                val a = nodes[0]
                val b = nodes[1]
                val c = nodes[2]
                val d = nodes[3]
                val join1 = LOPJoin(root.query, a, b, false)
                val join2 = LOPJoin(root.query, join1, c, false)
                val join3 = LOPJoin(root.query, join2, d, false)
                return join3
            }

            // pattern: (01 3 2)
            1 -> {
                val a = nodes[0]
                val b = nodes[1]
                val c = nodes[2]
                val d = nodes[3]
                val join1 = LOPJoin(root.query, a, b, false)
                val join2 = LOPJoin(root.query, join1, d, false)
                val join3 = LOPJoin(root.query, join2, c, false)
                return join3
            }
            // pattern: (01 23)
            2 -> {
                val a = nodes[0]
                val b = nodes[1]
                val c = nodes[2]
                val d = nodes[3]
                val join1 = LOPJoin(root.query, a, b, false)
                val join2 = LOPJoin(root.query, c, d, false)
                val join3 = LOPJoin(root.query, join1, join2, false)
                return join3
            }
            // pattern: (02 1 3)
            3 -> {
                val a = nodes[0]
                val b = nodes[1]
                val c = nodes[2]
                val d = nodes[3]
                val join1 = LOPJoin(root.query, a, c, false)
                val join2 = LOPJoin(root.query, join1, b, false)
                val join3 = LOPJoin(root.query, join2, d, false)
                return join3
            }
            // pattern: (02 3 1)
            4 -> {
                val a = nodes[0]
                val b = nodes[1]
                val c = nodes[2]
                val d = nodes[3]
                val join1 = LOPJoin(root.query, a, c, false)
                val join2 = LOPJoin(root.query, join1, d, false)
                val join3 = LOPJoin(root.query, join2, b, false)
                return join3
            }
            // pattern: (02 13)
            5 -> {
                val a = nodes[0]
                val b = nodes[1]
                val c = nodes[2]
                val d = nodes[3]
                val join1 = LOPJoin(root.query, a, c, false)
                val join2 = LOPJoin(root.query, b, d, false)
                val join3 = LOPJoin(root.query, join1, join2, false)
                return join3
            }
            // pattern: (03 1 2)
            6 -> {
                val a = nodes[0]
                val b = nodes[1]
                val c = nodes[2]
                val d = nodes[3]
                val join1 = LOPJoin(root.query, a, d, false)
                val join2 = LOPJoin(root.query, join1, b, false)
                val join3 = LOPJoin(root.query, join2, c, false)
                return join3
            }
            // pattern: (03 2 1)
            7 -> {
                val a = nodes[0]
                val b = nodes[1]
                val c = nodes[2]
                val d = nodes[3]
                val join1 = LOPJoin(root.query, a, d, false)
                val join2 = LOPJoin(root.query, join1, c, false)
                val join3 = LOPJoin(root.query, join2, b, false)
                return join3
            }
            // pattern: (03 21)
            8 -> {
                val a = nodes[0]
                val b = nodes[1]
                val c = nodes[2]
                val d = nodes[3]
                val join1 = LOPJoin(root.query, a, d, false)
                val join2 = LOPJoin(root.query, b, c, false)
                val join3 = LOPJoin(root.query, join1, join2, false)
                return join3
            }
            // pattern: (12 3 0)
            9 -> {
                val a = nodes[0]
                val b = nodes[1]
                val c = nodes[2]
                val d = nodes[3]
                val join1 = LOPJoin(root.query, b, c, false)
                val join2 = LOPJoin(root.query, join1, d, false)
                val join3 = LOPJoin(root.query, join2, a, false)
                return join3
            }
            // pattern: (12 0 3)
            10 -> {
                val a = nodes[0]
                val b = nodes[1]
                val c = nodes[2]
                val d = nodes[3]
                val join1 = LOPJoin(root.query, b, c, false)
                val join2 = LOPJoin(root.query, join1, a, false)
                val join3 = LOPJoin(root.query, join2, d, false)
                return join3
            }
            // pattern: (13 2 0)
            11 -> {
                val a = nodes[0]
                val b = nodes[1]
                val c = nodes[2]
                val d = nodes[3]
                val join1 = LOPJoin(root.query, b, d, false)
                val join2 = LOPJoin(root.query, join1, c, false)
                val join3 = LOPJoin(root.query, join2, a, false)
                return join3
            }
            // pattern: (13 0 2)
            12 -> {
                val a = nodes[0]
                val b = nodes[1]
                val c = nodes[2]
                val d = nodes[3]
                val join1 = LOPJoin(root.query, b, d, false)
                val join2 = LOPJoin(root.query, join1, a, false)
                val join3 = LOPJoin(root.query, join2, c, false)
                return join3
            }
            // pattern: (23 0 1)
            13 -> {
                val a = nodes[0]
                val b = nodes[1]
                val c = nodes[2]
                val d = nodes[3]
                val join1 = LOPJoin(root.query, c, d, false)
                val join2 = LOPJoin(root.query, join1, a, false)
                val join3 = LOPJoin(root.query, join2, b, false)
                return join3
            }
            // pattern: (23 1 0)
            14 -> {
                val a = nodes[0]
                val b = nodes[1]
                val c = nodes[2]
                val d = nodes[3]
                val join1 = LOPJoin(root.query, c, d, false)
                val join2 = LOPJoin(root.query, join1, b, false)
                val join3 = LOPJoin(root.query, join2, a, false)
                return join3
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
//            e.myPrintStackTrace(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_optimizer_logical/src/commonMain/kotlin/lupos/optimizer/logical/LogicalOptimizerBuildCustomJoinOrderML.kt:233"/*SOURCE_FILE_END*/ )()
//            return null
//        }
/*Coverage Unreachable*/
    }
}
