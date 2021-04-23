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
import lupos.shared.ByteArrayWrapper
import lupos.shared.EOperatorIDExt
import lupos.shared.Luposdate3000Exception
import lupos.shared.ValueDefinition
import lupos.shared.dictionary.DictionaryExt
import lupos.shared.operator.IOPBase
import lupos.shared_inline.DictionaryHelper

public class AOPEQ public constructor(query: IQuery, childA: AOPBase, childB: AOPBase) : AOPBinaryOperationFixedName(query, EOperatorIDExt.AOPEQID, "AOPEQ", arrayOf(childA, childB)) {
    override fun toSparql(): String = "(" + children[0].toSparql() + " = " + children[1].toSparql() + ")"
    override fun equals(other: Any?): Boolean = other is AOPEQ && children[0] == other.children[0] && children[1] == other.children[1]
    override fun evaluate(row: IteratorBundle): () -> ValueDefinition {
        val childA = (children[0] as AOPBase).evaluateID(row)
        val childB = (children[1] as AOPBase).evaluateID(row)
        val buffer = ByteArrayWrapper()
        return {
            var res: ValueDefinition = DictionaryExt.booleanTrueValue2
            val a1 = childA()
            val b1 = childB()
            if (a1 != b1) {
                if (query.getDictionary().isBnode(a1) || query.getDictionary().isBnode(b1)) {
                    res = DictionaryExt.booleanFalseValue2
                } else {
                    query.getDictionary().getValue(buffer, a1)
                    val a = DictionaryHelper.byteArrayToValueDefinition(buffer)
                    query.getDictionary().getValue(buffer, b1)
                    val b = DictionaryHelper.byteArrayToValueDefinition(buffer)
                    try {
                        if (a != b) {
                            res = DictionaryExt.booleanFalseValue2
                        }
                    } catch (e: Luposdate3000Exception) {
                        res = DictionaryExt.errorValue2
                    } catch (e: Throwable) {
                        e.printStackTrace()
                        res = DictionaryExt.errorValue2
                    }
                }
            }
            res
        }
    }

    override fun enforcesBooleanOrError(): Boolean = true
    override fun cloneOP(): IOPBase = AOPEQ(query, children[0].cloneOP() as AOPBase, children[1].cloneOP() as AOPBase)
}
