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
import lupos.s00misc.EvaluationException
import lupos.s00misc.MyBigDecimal
import lupos.s00misc.MyBigInteger
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

public class AOPDivision public constructor(query: IQuery, childA: AOPBase, childB: AOPBase) : AOPBinaryOperationFixedName(query, EOperatorIDExt.AOPDivisionID, "AOPDivision", arrayOf(childA, childB)) {
    override fun toSparql(): String = "(" + children[0].toSparql() + " / " + children[1].toSparql() + ")"
    override fun equals(other: Any?): Boolean = other is AOPDivision && children[0] == other.children[0] && children[1] == other.children[1]
    override fun evaluate(row: IteratorBundle): () -> ValueDefinition {
        val childA = (children[0] as AOPBase).evaluate(row)
        val childB = (children[1] as AOPBase).evaluate(row)
        return {
            var res: ValueDefinition = ValueError()
            val a = childA()
            val b = childB()
            try {
                if (a is ValueDouble || b is ValueDouble) {
                    if (b.toDouble() != 0.0) {
                        res = ValueDouble(a.toDouble() / b.toDouble())
                    }
                }
                if (a is ValueFloat || b is ValueFloat) {
                    if (b.toDouble() != 0.0) {
                        res = ValueFloat(a.toDouble() / b.toDouble())
                    }
                }
                if (a is ValueDecimal || b is ValueDecimal) {
                    if (b.toDecimal() != MyBigDecimal("0.0")) {
                        res = ValueDecimal(a.toDecimal() / b.toDecimal())
                    }
                }
                if (a is ValueInteger || b is ValueInteger) {
                    if (b.toInt() != MyBigInteger("0")) {
                        res = ValueDecimal(a.toDecimal() / b.toDecimal())
                    }
                }
            } catch (e: EvaluationException) {
            } catch (e: Throwable) {
                SanityCheck.println { "TODO exception 23" }
                e.printStackTrace()
            }
            res
        }
    }

    override fun cloneOP(): IOPBase = AOPDivision(query, children[0].cloneOP() as AOPBase, children[1].cloneOP() as AOPBase)
}
