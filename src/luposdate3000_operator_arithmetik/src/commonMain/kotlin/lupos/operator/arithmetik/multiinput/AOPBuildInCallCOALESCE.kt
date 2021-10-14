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
package lupos.operator.arithmetik.multiinput

import lupos.operator.arithmetik.AOPBase
import lupos.shared.EOperatorIDExt
import lupos.shared.EvaluationException
import lupos.shared.IQuery
import lupos.shared.ValueDefinition
import lupos.shared.ValueError
import lupos.shared.ValueUndef
import lupos.shared.operator.IOPBase
import lupos.shared.operator.iterator.IteratorBundle

public class AOPBuildInCallCOALESCE public constructor(query: IQuery, childs: List<AOPBase>) : AOPBase(query, EOperatorIDExt.AOPBuildInCallCOALESCEID, "AOPBuildInCallCOALESCE", Array(childs.size) { childs[it] }) {
    override fun toSparql(): String {
        val res = StringBuilder()
        res.append("COALESCE(")
        var first = true
        for (c in children) {
            if (first) {
                first = false
            } else {
                res.append(", ")
            }
            res.append(c.toSparql())
        }
        res.append(")")
        return res.toString()
    }

    override fun equals(other: Any?): Boolean = other is AOPBuildInCallCOALESCE && children.contentEquals(other.children)
    override fun evaluate(row: IteratorBundle): () -> ValueDefinition {
        val tmpChilds = children.map { (it as AOPBase).evaluate(row) }
        return {
            var res: ValueDefinition = ValueError()
            for (c in tmpChilds) {
                try {
                    val value = c()
                    if (value !is ValueError && value !is ValueUndef) {
                        res = value
                        break
                    }
                } catch (e: EvaluationException) {
                } catch (e: Throwable) {
                    e.printStackTrace()
                }
            }
            res
        }
    }

    override fun cloneOP(): IOPBase = AOPBuildInCallCOALESCE(query, children.map { it.cloneOP() as AOPBase })
}
