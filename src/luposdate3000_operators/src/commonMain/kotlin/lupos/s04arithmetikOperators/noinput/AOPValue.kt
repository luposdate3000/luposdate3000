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

package lupos.s04arithmetikOperators.noinput
import lupos.s00misc.EOperatorIDExt
import lupos.s00misc.SanityCheck
import lupos.s03resultRepresentation.ValueDefinition
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.IQuery
import lupos.s04logicalOperators.iterator.IteratorBundle
public class AOPValue public constructor(query: IQuery, childs: List<AOPConstant>) : AOPBase(query, EOperatorIDExt.AOPValueID, "AOPValue", Array(childs.size) { childs[it] }) {
    override fun toSparql(): String {
        var res = ""
        res += "("
        if (children.isNotEmpty()) {
            res += children[0].toSparql()
        }
        for (i in 1 until children.size) {
            res += "," + children[i].toSparql()
        }
        res += ")"
        return res
    }
    override fun equals(other: Any?): Boolean = other is AOPValue && children.contentEquals(other.children)
    override fun evaluate(row: IteratorBundle): () -> ValueDefinition {
        SanityCheck.checkUnreachable()
    }
    override fun cloneOP(): IOPBase = this
}
