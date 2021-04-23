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
import lupos.operator.iterator.IteratorBundle
import lupos.operator.logical.IQuery
import lupos.shared.EOperatorIDExt
import lupos.shared.EvaluationException
import lupos.shared.ValueDefinition
import lupos.shared.ValueError
import lupos.shared.operator.IOPBase

public class AOPBuildInCallIF public constructor(query: IQuery, child: AOPBase, childA: AOPBase, childB: AOPBase) : AOPBase(query, EOperatorIDExt.AOPBuildInCallIFID, "AOPBuildInCallIF", arrayOf(child, childA, childB)) {
    override fun toSparql(): String = "IF(" + children[0].toSparql() + ", " + children[1].toSparql() + ", " + children[1].toSparql() + ")"
    override fun equals(other: Any?): Boolean = other is AOPBuildInCallIF && children[0] == other.children[0] && children[1] == other.children[1] && children[2] == other.children[2]
    override fun evaluate(row: IteratorBundle): () -> ValueDefinition {
        val childA = (children[0] as AOPBase).evaluate(row)
        val childB = (children[1] as AOPBase).evaluate(row)
        val childC = (children[2] as AOPBase).evaluate(row)
        return {
            var res: ValueDefinition = ValueError()
            try {
                res = if (childA().toBoolean()) {
                    childB()
                } else {
                    childC()
                }
            } catch (e: EvaluationException) {
            } catch (e: Throwable) {
                e.printStackTrace()
            }
            res
        }
    }

    override fun cloneOP(): IOPBase = AOPBuildInCallIF(query, children[0].cloneOP() as AOPBase, children[1].cloneOP() as AOPBase, children[2].cloneOP() as AOPBase)
}
