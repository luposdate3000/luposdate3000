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

import lupos.operator.base.Query
import lupos.shared.SanityCheck
import lupos.shared.operator.IOPBase
import kotlin.jvm.JvmField

public abstract class OptimizerBase public constructor(@JvmField public val query: Query, @JvmField public val optimizerID: EOptimizerID, @JvmField public val classname: String) {
    public abstract /*suspend*/ fun optimize(node: IOPBase, parent: IOPBase?, onChange: () -> Unit): IOPBase
    public /*suspend*/ fun optimizeInternal(node: IOPBase, parent: IOPBase?, onChange: () -> Unit): IOPBase {
  if(SanityCheck.enabled)            {
                if (parent != null) {
                    var found = false
                    for (c in parent.getChildren()) {
                        if (c === node) {
                            found = true
                            break
                        }
                    }
if(SanityCheck.enabled){if(!( found )){throw Exception("SanityCheck failed")}}
                }
            }
        
        for (i in node.getChildren().indices) {
            val tmp = optimizeInternal(node.getChildren()[i], node, onChange)
            node.updateChildren(i, tmp)
        }
        return optimize(node, parent, onChange)
    }

    public open /*suspend*/ fun optimizeCall(node: IOPBase): IOPBase {
        return optimizeCall(node, {}, {})
    }

    public open /*suspend*/ fun optimizeCall(node: IOPBase, onChange: () -> Unit, nextStep: (IOPBase) -> Unit): IOPBase {
        if (query.filtersMovedUpFromOptionals) {
            node.syntaxVerifyAllVariableExists(listOf(), true)
        }
        val res = optimizeInternal(node, null, onChange)
        if (query.filtersMovedUpFromOptionals) {
            res.syntaxVerifyAllVariableExists(listOf(), false)
        }
        return res
    }
}
