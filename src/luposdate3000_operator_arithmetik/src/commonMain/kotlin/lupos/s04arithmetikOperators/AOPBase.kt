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
package lupos.s04arithmetikOperators

import lupos.dictionary.DictionaryExt
import lupos.modulename.DictionaryHelper
import lupos.s00misc.ByteArrayWrapper
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriorityExt
import lupos.s00misc.EvaluationException
import lupos.s00misc.SanityCheck
import lupos.s03resultRepresentation.ValueDefinition
import lupos.s04logicalOperators.HistogramResult
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.IQuery
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.iterator.IteratorBundle

public abstract class AOPBase public constructor(
    query: IQuery,
    operatorID: EOperatorID,
    classname: String,
    children: Array<IOPBase>
) :
    OPBase(query, operatorID, classname, children, ESortPriorityExt.PREVENT_ANY), IAOPBase {
    public fun evaluateAsBoolean(row: IteratorBundle): () -> Boolean {
        if (enforcesBooleanOrError()) {
            val tmp = evaluateID(row)
            return {
                tmp() == DictionaryExt.booleanTrueValue
            }
        } else {
            val tmp = evaluate(row)
            return {
                var res: Boolean
                try {
                    val value = tmp()
                    res = value.toBoolean()
                } catch (e: EvaluationException) {
                    e.printStackTrace()
                    res = false
                } catch (e: Throwable) {
                    e.printStackTrace()
                    res = false
                }
                res
            }
        }
    }

    public open fun evaluate(row: IteratorBundle): () -> ValueDefinition {
        val buffer = ByteArrayWrapper()
        return {
            query.getDictionary().getValue(buffer, evaluateID(row)())
            DictionaryHelper.byteArrayToValueDefinition(buffer)
        }
    }

    public open fun evaluateID(row: IteratorBundle): () -> Int {
        val buffer = ByteArrayWrapper()
        return {
            DictionaryHelper.valueDefinitionToByteArray(buffer, evaluate(row)())
            query.getDictionary().createValue(buffer)
        }
    }

    public open fun enforcesBooleanOrError(): Boolean = false
    override fun getPartitionCount(variable: String): Int = SanityCheck.checkUnreachable()
    override /*suspend*/ fun calculateHistogram(): HistogramResult = SanityCheck.checkUnreachable()
}
