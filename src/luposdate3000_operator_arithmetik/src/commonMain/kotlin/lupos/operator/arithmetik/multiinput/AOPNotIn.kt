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
import lupos.shared.myPrintStackTrace

import lupos.operator.arithmetik.AOPBase
import lupos.shared.EOperatorIDExt
import lupos.shared.IQuery
import lupos.shared.SanityCheck
import lupos.shared.dynamicArray.ByteArrayWrapper
import lupos.shared.inline.DictionaryHelper
import lupos.shared.operator.IOPBase
import lupos.shared.operator.iterator.IteratorBundle

public class AOPNotIn public constructor(query: IQuery, childA: AOPBase, childB: AOPBase) : AOPBase(query, EOperatorIDExt.AOPNotInID, "AOPNotIn", arrayOf(childA, childB)) {
    override fun toSparql(): String = "( " + children[0].toSparql() + " NOT IN " + children[1].toSparql() + " )"
    override fun equals(other: Any?): Boolean = other is AOPNotIn && children[0] == other.getChildren()[0] && children[1] == other.getChildren()[1]
    override fun evaluate(row: IteratorBundle): () -> ByteArrayWrapper {
        val childA = (children[0] as AOPBase).evaluate(row)
        SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_arithmetik/src/commonMain/kotlin/lupos/operator/arithmetik/multiinput/AOPNotIn.kt:33"/*SOURCE_FILE_END*/ }, { children[1] is AOPSet })
        val childsB = Array(children[1].getChildren().size) { (children[1].getChildren()[it] as AOPBase).evaluate(row) }
        val buffer = ByteArrayWrapper()
        return {
            DictionaryHelper.errorToByteArray(buffer)
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
                    e.myPrintStackTrace(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_arithmetik/src/commonMain/kotlin/lupos/operator/arithmetik/multiinput/AOPNotIn.kt:48"/*SOURCE_FILE_END*/ )()
                    noError = false
                }
            }
            if (!found || noError) {
                DictionaryHelper.booleanToByteArray(buffer, !found)
            }
            buffer
        }
    }

    override fun enforcesBooleanOrError(): Boolean = true
    override fun cloneOP(): IOPBase = AOPNotIn(query, children[0].cloneOP() as AOPBase, children[1].cloneOP() as AOPBase)
}
