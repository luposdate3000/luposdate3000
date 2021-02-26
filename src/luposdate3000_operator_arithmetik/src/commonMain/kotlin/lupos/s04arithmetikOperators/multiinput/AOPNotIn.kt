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
package lupos.s04arithmetikOperators.multiinput

import lupos.s00misc.EOperatorIDExt
import lupos.s00misc.SanityCheck
import lupos.s03resultRepresentation.ValueBoolean
import lupos.s03resultRepresentation.ValueDefinition
import lupos.s03resultRepresentation.ValueError
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.IQuery
import lupos.s04logicalOperators.iterator.IteratorBundle

public class AOPNotIn public constructor(query: IQuery, childA: AOPBase, childB: AOPBase) : AOPBase(query, EOperatorIDExt.AOPNotInID, "AOPNotIn", arrayOf(childA, childB)) {
    override fun toSparql(): String = "( " + children[0].toSparql() + " NOT IN " + children[1].toSparql() + " )"
    override fun equals(other: Any?): Boolean = other is AOPNotIn && children[0] == other.getChildren()[0] && children[1] == other.getChildren()[1]
    override fun evaluate(row: IteratorBundle): () -> ValueDefinition {
        val childA = (children[0] as AOPBase).evaluate(row)
        SanityCheck.check { children[1] is AOPSet }
        val childsB = Array(children[1].getChildren().size) { (children[1].getChildren()[it] as AOPBase).evaluate(row) }
        return {
            var res: ValueDefinition = ValueError()
            val a = childA()
            var found = false
            var noError = true
            for (childB in childsB) {
                try {
                    if (childB() == a) {
                        found = true
                        break
                    }
                } catch (e: Throwable) {
                    e.printStackTrace()
                    noError = false
                }
            }
            found = !found
            if (found || noError) {
                res = ValueBoolean(found)
            }
            res
        }
    }

    override fun enforcesBooleanOrError(): Boolean = true
    override fun cloneOP(): IOPBase = AOPNotIn(query, children[0].cloneOP() as AOPBase, children[1].cloneOP() as AOPBase)
}
