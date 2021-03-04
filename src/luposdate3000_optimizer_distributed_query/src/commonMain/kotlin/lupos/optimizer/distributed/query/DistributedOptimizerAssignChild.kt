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
package lupos.optimizer.distributed.query

import lupos.s00misc.XMLElement

public class DistributedOptimizerAssignChild() : DistributedOptimizerBase {
    override fun optimize(key: String, node: XMLElement, dependenciesTopDown: Set<String>, dependenciesBottomUp: Set<String>, keytoHostMapGet: (String) -> String?, keytoHostMapSet: (String, String) -> Unit, onChange: () -> Unit) {
        if (dependenciesTopDown.size > 0) {
            var possibleHost = keytoHostMapGet(dependenciesTopDown.first())
            if (possibleHost != null) {
                for (s in dependenciesTopDown) {
                    if (possibleHost != keytoHostMapGet(s)) {
                        return
                    }
                }
                keytoHostMapSet(key, possibleHost)
                onChange()
            }
        }
    }
}
