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
import lupos.shared.DictionaryValueHelper
import lupos.shared.DictionaryValueType
import lupos.shared.EOperatorIDExt
import lupos.shared.EvaluationException
import lupos.shared.IQuery
import lupos.shared.operator.IOPBase
import lupos.shared.operator.iterator.IteratorBundle

public class AOPLT public constructor(query: IQuery, childA: AOPBase, childB: AOPBase) : AOPBinaryOperationFixedName(query, EOperatorIDExt.AOPLTID, "AOPLT", arrayOf(childA, childB)) {
    override fun toSparql(): String = "(" + children[0].toSparql() + " < " + children[1].toSparql() + ")"
    override fun equals(other: Any?): Boolean = other is AOPLT && children[0] == other.children[0] && children[1] == other.children[1]

    override fun evaluateID(row: IteratorBundle): () -> DictionaryValueType {
        val childA = (children[0] as AOPBase).evaluate(row)
        val childB = (children[1] as AOPBase).evaluate(row)
        return {
            var res: DictionaryValueType = DictionaryValueHelper.errorValue
            val a = childA()
            val b = childB()
            try {
                res = if (a < b) {
                    DictionaryValueHelper.booleanTrueValue
                } else {
                    DictionaryValueHelper.booleanFalseValue
                }
            } catch (e: EvaluationException) {
            } catch (e: Throwable) {
                e.printStackTrace()
            }
            res
        }
    }

    override fun enforcesBooleanOrError(): Boolean = true
    override fun cloneOP(): IOPBase = AOPLT(query, children[0].cloneOP() as AOPBase, children[1].cloneOP() as AOPBase)
}
