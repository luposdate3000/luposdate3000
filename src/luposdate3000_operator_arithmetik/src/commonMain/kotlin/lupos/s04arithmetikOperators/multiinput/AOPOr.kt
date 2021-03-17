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
import lupos.s03resultRepresentation.DictionaryExt
import lupos.s03resultRepresentation.ValueDefinition
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.IQuery
import lupos.s04logicalOperators.iterator.IteratorBundle

public class AOPOr public constructor(query: IQuery, childA: AOPBase, childB: AOPBase) : AOPBase(query, EOperatorIDExt.AOPOrID, "AOPOr", arrayOf(childA, childB)) {
    override fun toSparql(): String = "(" + children[0].toSparql() + " || " + children[1].toSparql() + ")"
    override fun equals(other: Any?): Boolean = other is AOPOr && children[0] == other.children[0] && children[1] == other.children[1]

    internal companion object {
        val truthTable = arrayOf(
            DictionaryExt.booleanTrueValue, // T,T
            DictionaryExt.booleanTrueValue, // T,F
            DictionaryExt.errorValue, // T,E
            DictionaryExt.booleanTrueValue, // F,T
            DictionaryExt.booleanFalseValue, // F,F
            DictionaryExt.errorValue, // F,E
            DictionaryExt.errorValue, // E,T
            DictionaryExt.errorValue, // E,F
            DictionaryExt.errorValue // E,E
        )
        val truthTable2 = arrayOf(
            DictionaryExt.booleanTrueValue2, // T,T
            DictionaryExt.booleanTrueValue2, // T,F
            DictionaryExt.errorValue2, // T,E
            DictionaryExt.booleanTrueValue2, // F,T
            DictionaryExt.booleanFalseValue2, // F,F
            DictionaryExt.errorValue2, // F,E
            DictionaryExt.errorValue2, // E,T
            DictionaryExt.errorValue2, // E,F
            DictionaryExt.errorValue2 // E,E
        )
    }

    override fun evaluate(row: IteratorBundle): () -> ValueDefinition {
        val childA = (children[0] as AOPBase).evaluateID(row)
        val childB = (children[1] as AOPBase).evaluateID(row)
        if ((children[0] as AOPBase).enforcesBooleanOrError()) {
            if ((children[1] as AOPBase).enforcesBooleanOrError()) {
                return {
                    val a = childA()
                    val b = childB()
                    truthTable2[a * 3 + b]
                }
            } else {
                return {
                    val a = childA()
                    val b = query.getDictionary().toBooleanOrError(childB())
                    truthTable2[a * 3 + b]
                }
            }
        } else {
            if ((children[1] as AOPBase).enforcesBooleanOrError()) {
                return {
                    val a = query.getDictionary().toBooleanOrError(childA())
                    val b = childB()
                    truthTable2[a * 3 + b]
                }
            } else {
                return {
                    val a = query.getDictionary().toBooleanOrError(childA())
                    val b = query.getDictionary().toBooleanOrError(childB())
                    truthTable2[a * 3 + b]
                }
            }
        }
    }

    override fun evaluateID(row: IteratorBundle): () -> Int {
        val childA = (children[0] as AOPBase).evaluateID(row)
        val childB = (children[1] as AOPBase).evaluateID(row)
        if ((children[0] as AOPBase).enforcesBooleanOrError()) {
            if ((children[1] as AOPBase).enforcesBooleanOrError()) {
                return {
                    val a = childA()
                    val b = childB()
                    truthTable[a * 3 + b]
                }
            } else {
                return {
                    val a = childA()
                    val b = query.getDictionary().toBooleanOrError(childB())
                    truthTable[a * 3 + b]
                }
            }
        } else {
            if ((children[1] as AOPBase).enforcesBooleanOrError()) {
                return {
                    val a = query.getDictionary().toBooleanOrError(childA())
                    val b = childB()
                    truthTable[a * 3 + b]
                }
            } else {
                return {
                    val a = query.getDictionary().toBooleanOrError(childA())
                    val b = query.getDictionary().toBooleanOrError(childB())
                    truthTable[a * 3 + b]
                }
            }
        }
    }

    override fun enforcesBooleanOrError(): Boolean = true
    override fun cloneOP(): IOPBase = AOPOr(query, children[0].cloneOP() as AOPBase, children[1].cloneOP() as AOPBase)
}
