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
import lupos.s00misc.SanityCheck
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
import kotlin.math.ceil

public class AOPBuildInCallCEIL public constructor(query: IQuery, child: AOPBase) : AOPBase(query, EOperatorIDExt.AOPBuildInCallCEILID, "AOPBuildInCallCEIL", arrayOf(child)) {
    // return integer which is equal or larger than input
    override fun toSparql(): String = "CEIL(" + children[0].toSparql() + ")"
    override fun equals(other: Any?): Boolean = other is AOPBuildInCallCEIL && children[0] == other.children[0]
    override fun evaluate(row: IteratorBundle): () -> ValueDefinition {
        val childA = (children[0] as AOPBase).evaluate(row)
        return {
            var res: ValueDefinition = ValueError()
            val a = childA()
            try {
                when (a) {
                    is ValueDouble -> {
                        res = ValueDouble(ceil(a.toDouble()))
                    }
                    is ValueFloat -> {
                        res = ValueFloat(ceil(a.toDouble()))
                    }
                    is ValueDecimal -> {
                        res = ValueDecimal(a.value.ceil())
                    }
                    is ValueInteger -> {
                        res = a
                    }
                    else -> {
                        res = ValueError()
                    }
                }
            } catch (e: Throwable) {
                SanityCheck.println { "TODO exception 36" }
                e.printStackTrace()
                res = ValueError()
            }
            res
        }
    }

    override fun cloneOP(): IOPBase = AOPBuildInCallCEIL(query, children[0].cloneOP() as AOPBase)
}
