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
import lupos.shared.EvaluationException
import lupos.shared.IQuery
import lupos.shared.dynamicArray.ByteArrayWrapper
import lupos.shared.inline.DictionaryHelper
import lupos.shared.operator.IOPBase
import lupos.shared.operator.iterator.IteratorBundle

public class AOPBuildInCallIF public constructor(query: IQuery, child: AOPBase, childA: AOPBase, childB: AOPBase) : AOPBase(query, EOperatorIDExt.AOPBuildInCallIFID, "AOPBuildInCallIF", arrayOf(child, childA, childB)) {
    override fun toSparql(): String = "IF(" + children[0].toSparql() + ", " + children[1].toSparql() + ", " + children[1].toSparql() + ")"
    override fun equals(other: Any?): Boolean = other is AOPBuildInCallIF && children[0] == other.children[0] && children[1] == other.children[1] && children[2] == other.children[2]
    override fun evaluate(row: IteratorBundle): () -> ByteArrayWrapper {
        val childA = (children[0] as AOPBase).evaluate(row)
        val childB = (children[1] as AOPBase).evaluate(row)
        val childC = (children[2] as AOPBase).evaluate(row)
        val buffer = ByteArrayWrapper()
        DictionaryHelper.errorToByteArray(buffer)
        return {
            var res = buffer
            try {
                res = if (DictionaryHelper.byteArrayAsBoolean(childA())) {
                    childB()
                } else {
                    childC()
                }
            } catch (e: EvaluationException) {
            } catch (e: Throwable) {
                e.myPrintStackTrace(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_arithmetik/src/commonMain/kotlin/lupos/operator/arithmetik/multiinput/AOPBuildInCallIF.kt:47"/*SOURCE_FILE_END*/ )
            }
            res
        }
    }

    override fun cloneOP(): IOPBase = AOPBuildInCallIF(query, children[0].cloneOP() as AOPBase, children[1].cloneOP() as AOPBase, children[2].cloneOP() as AOPBase)
}
