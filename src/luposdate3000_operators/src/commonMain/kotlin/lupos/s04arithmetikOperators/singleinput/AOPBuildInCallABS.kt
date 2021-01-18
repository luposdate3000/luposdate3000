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
package lupos.s04arithmetikOperators.singleinput
import lupos.s00misc.EOperatorIDExt
import lupos.s03resultRepresentation.ValueDecimal
import lupos.s03resultRepresentation.ValueDefinition
import lupos.s03resultRepresentation.ValueDouble
import lupos.s03resultRepresentation.ValueError
import lupos.s03resultRepresentation.ValueFloat
import lupos.s03resultRepresentation.ValueInteger
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.IQuery
import lupos.s04logicalOperators.iterator.IteratorBundle
import kotlin.math.abs
public class AOPBuildInCallABS public constructor(query: IQuery, child: AOPBase) : AOPBase(query, EOperatorIDExt.AOPBuildInCallABSID, "AOPBuildInCallABS", arrayOf(child)) {
    override fun toSparql(): String = "ABS(" + children[0].toSparql() + ")"
    override fun equals(other: Any?): Boolean = other is AOPBuildInCallABS && children[0] == other.children[0]
    override fun evaluate(row: IteratorBundle): () -> ValueDefinition {
        val childA = (children[0] as AOPBase).evaluate(row)
        return {
            var res: ValueDefinition = ValueError()
            when (val a = childA()) {
                is ValueDouble -> {
                    res = ValueDouble(abs(a.value))
                }
                is ValueFloat -> {
                    res = ValueFloat(abs(a.value))
                }
                is ValueDecimal -> {
                    res = ValueDecimal(a.value.abs())
                }
                is ValueInteger -> {
                    res = ValueInteger(a.value.abs())
                }
            }
            res
        }
    }
    override fun cloneOP(): IOPBase = AOPBuildInCallABS(query, children[0].cloneOP() as AOPBase)
}
