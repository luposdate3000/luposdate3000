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
import lupos.shared.operator.IOPBase

public object LogicalOptimizerBuildCustomJoinOrderML2 {
    public /*suspend*/ operator fun invoke(allChilds: List<IOPBase>, root: LOPJoin, joinOrder: List<Int>, tripleCount: Int): IOPBase? {
        val intermediates = mutableListOf<IOPBase>()
        println("joinOrder ${joinOrder.toList()}")
        for (i in 0 until joinOrder.size / 2) {
            val ai = i * 2
            val bi = ai + 1
            val a = joinOrder[ai]
            val b = joinOrder[bi]
            val ao = if (a < 0) {
                intermediates[-a - 1]
            } else {
                allChilds[a]
            }
            val bo = if (b < 0) {
                intermediates[-b - 1]
            } else {
                allChilds[b]
            }
            intermediates.add(LOPJoin(root.query, ao, bo, false))
        }
        return intermediates.last()
    }
}
