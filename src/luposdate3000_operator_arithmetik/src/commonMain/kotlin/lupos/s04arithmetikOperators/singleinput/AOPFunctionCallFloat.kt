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

import lupos.s00misc.CanNotCastLiteralToDoubleException
import lupos.s00misc.DontCareWhichException
import lupos.s00misc.EOperatorIDExt
import lupos.s03resultRepresentation.ValueBoolean
import lupos.s03resultRepresentation.ValueDecimal
import lupos.s03resultRepresentation.ValueDefinition
import lupos.s03resultRepresentation.ValueDouble
import lupos.s03resultRepresentation.ValueError
import lupos.s03resultRepresentation.ValueFloat
import lupos.s03resultRepresentation.ValueInteger
import lupos.s03resultRepresentation.ValueLanguageTaggedLiteral
import lupos.s03resultRepresentation.ValueSimpleLiteral
import lupos.s03resultRepresentation.ValueTypedLiteral
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.IQuery
import lupos.s04logicalOperators.iterator.IteratorBundle

public class AOPFunctionCallFloat public constructor(query: IQuery, child: AOPBase) : AOPBase(query, EOperatorIDExt.AOPFunctionCallFloatID, "AOPFunctionCallFloat", arrayOf(child)) {
    override fun toSparql(): String = "<http://www.w3.org/2001/XMLSchema#float>(" + children[0].toSparql() + ")"
    override fun equals(other: Any?): Boolean = other is AOPFunctionCallFloat && children[0] == other.children[0]
    override fun evaluate(row: IteratorBundle): () -> ValueDefinition {
        val childA = (children[0] as AOPBase).evaluate(row)
        return {
            var res: ValueDefinition = ValueError()
            when (val a = childA()) {
                is ValueFloat -> {
                    res = a
                }
                is ValueInteger -> {
                    res = ValueFloat(a.toDouble())
                }
                is ValueBoolean -> {
                    res = if (a.value) {
                        ValueFloat(1.0)
                    } else {
                        ValueFloat(0.0)
                    }
                }
                is ValueDecimal -> {
                    res = ValueFloat(a.value.toDouble())
                }
                is ValueDouble -> {
                    res = ValueFloat(a.value)
                }
                is ValueSimpleLiteral -> {
                    try {
                        res = ValueFloat(a.content.toDouble())
                    } catch (e: DontCareWhichException) {
                        throw CanNotCastLiteralToDoubleException()
                    }
                }
                is ValueLanguageTaggedLiteral -> {
                    try {
                        res = ValueFloat(a.content.toDouble())
                    } catch (e: DontCareWhichException) {
                        throw CanNotCastLiteralToDoubleException()
                    }
                }
                is ValueTypedLiteral -> {
                    try {
                        res = ValueFloat(a.content.toDouble())
                    } catch (e: DontCareWhichException) {
                        throw CanNotCastLiteralToDoubleException()
                    }
                }
            }
            res
        }
    }

    override fun cloneOP(): IOPBase = AOPFunctionCallFloat(query, children[0].cloneOP() as AOPBase)
}
