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
package lupos.operator.logical.multiinput
import lupos.operator.arithmetik.noinput.AOPVariable
import lupos.operator.logical.LOPBase
import lupos.operator.logical.singleinput.LOPProjection
import lupos.shared.BugException
import lupos.shared.EOperatorIDExt
import lupos.shared.ESortPriorityExt
import lupos.shared.IQuery
import lupos.shared.SanityCheck
import lupos.shared.myPrintStackTrace
import lupos.shared.operator.HistogramResult
import lupos.shared.operator.IOPBase

public class LOPUnion public constructor(query: IQuery, first: IOPBase, second: IOPBase) : LOPBase(query, EOperatorIDExt.LOPUnionID, "LOPUnion", arrayOf(first, second), ESortPriorityExt.UNION) {
    override fun equals(other: Any?): Boolean = other is LOPUnion && children[0] == other.children[0] && children[1] == other.children[1]
    override fun cloneOP(): IOPBase = LOPUnion(query, children[0].cloneOP(), children[1].cloneOP())
    override /*suspend*/ fun calculateHistogram(): HistogramResult {
        val res = HistogramResult()
        val childHistogram0 = children[0].getHistogram()
        val childHistogram1 = children[1].getHistogram()
        res.count = childHistogram0.count + childHistogram1.count
        val providedA = children[0].getProvidedVariableNames()
        val providedB = children[1].getProvidedVariableNames()
        val provided = providedA.intersect(providedB)
        var p = getProvidedVariableNames()
        if (provided.containsAll(p)) {
            children[0] = LOPProjection(query, provided.map { AOPVariable(query, it) }.toMutableList(), children[0])
            children[1] = LOPProjection(query, provided.map { AOPVariable(query, it) }.toMutableList(), children[1])
            p = getProvidedVariableNames()
            if (SanityCheck.enabled) { if (!(provided.containsAll(p))) { throw Exception("SanityCheck failed") } }
        }
        try {
            for (v in p) {
                val a = childHistogram0.values[v] ?: 1
                val b = childHistogram1.values[v] ?: 1
                res.values[v] = a + b
            }
        } catch (e: Throwable) {
            e.myPrintStackTrace(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_logical/src/commonMain/kotlin/lupos/operator/logical/multiinput/LOPUnion.kt:54"/*SOURCE_FILE_END*/)
            throw BugException(classname, "calculateHistogram column missing")
        }
        return res
    }
}
