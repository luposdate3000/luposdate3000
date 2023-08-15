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
package lupos.operator.arithmetik
import lupos.operator.base.OPBase
import lupos.shared.DictionaryValueHelper
import lupos.shared.DictionaryValueType
import lupos.shared.EOperatorID
import lupos.shared.ESortPriorityExt
import lupos.shared.EvaluationException
import lupos.shared.IQuery
import lupos.shared.SanityCheck
import lupos.shared.dynamicArray.ByteArrayWrapper
import lupos.shared.inline.DictionaryHelper
import lupos.shared.myPrintStackTrace
import lupos.shared.operator.HistogramResult
import lupos.shared.operator.IAOPBase
import lupos.shared.operator.IOPBase
import lupos.shared.operator.iterator.IteratorBundle

public abstract class AOPBase public constructor(
    query: IQuery,
    operatorID: EOperatorID,
    classname: String,
    children: Array<IOPBase>,
) :
    OPBase(query, operatorID, classname, children, ESortPriorityExt.PREVENT_ANY), IAOPBase {
    public fun evaluateAsBoolean(row: IteratorBundle): () -> Boolean {
        if (enforcesBooleanOrError()) {
            val tmp = evaluateID(row)
            return {
                tmp() == DictionaryValueHelper.booleanTrueValue
            }
        } else {
            val tmp = evaluate(row)
            return {
                var res: Boolean = try {
                    DictionaryHelper.byteArrayAsBoolean(tmp())
                } catch (e: EvaluationException) {
                    e.myPrintStackTrace(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_arithmetik/src/commonMain/kotlin/lupos/operator/arithmetik/AOPBase.kt:52"/*SOURCE_FILE_END*/)
                    false
                } catch (e: Throwable) {
                    e.myPrintStackTrace(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_arithmetik/src/commonMain/kotlin/lupos/operator/arithmetik/AOPBase.kt:55"/*SOURCE_FILE_END*/)
                    false
                }
                res
            }
        }
    }

    public open fun evaluate(row: IteratorBundle): () -> ByteArrayWrapper {
        val buffer = ByteArrayWrapper()
        val tmp = evaluateID(row)
        return {
            query.getDictionary().getValue(buffer, tmp())
            buffer
        }
    }

    public open fun evaluateID(row: IteratorBundle): () -> DictionaryValueType {
        val tmp = evaluate(row)
        return {
            query.getDictionary().createValue(tmp())
        }
    }

    public open fun enforcesBooleanOrError(): Boolean = false
    override fun getPartitionCount(variable: String): Int = SanityCheck.checkUnreachable()
    override /*suspend*/ fun calculateHistogram(): HistogramResult = SanityCheck.checkUnreachable()
    override fun usesDictionary(): Boolean = true
    public open fun containsAggregate(): Boolean = children.map { it is AOPBase && it.containsAggregate() }.fold(false) { s, t -> s || t }
}
