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
package lupos.operator.logical.singleinput

import lupos.operator.arithmetik.AOPBase
import lupos.operator.base.noinput.OPEmptyRow
import lupos.operator.logical.LOPBase
import lupos.shared.EOperatorIDExt
import lupos.shared.ESortPriorityExt
import lupos.shared.IQuery
import lupos.shared.operator.HistogramResult
import lupos.shared.operator.IOPBase
import kotlin.jvm.JvmField

public class LOPFilter public constructor(query: IQuery, filter: AOPBase, child: IOPBase) : LOPBase(query, EOperatorIDExt.LOPFilterID, "LOPFilter", arrayOf(child, filter), ESortPriorityExt.SAME_AS_CHILD) {
    public constructor(query: IQuery, filter: AOPBase) : this(query, filter, OPEmptyRow(query))

    @JvmField
    public var dontSplitFilter: Int = 0
    override fun childrenToVerifyCount(): Int = 1
    override fun getProvidedVariableNames(): List<String> = children[0].getProvidedVariableNames().distinct()
    override fun getRequiredVariableNames(): List<String> = children[1].getRequiredVariableNamesRecoursive()
    override fun equals(other: Any?): Boolean = other is LOPFilter && children[0] == other.children[0] && children[1] == other.children[1]
    override fun cloneOP(): IOPBase = LOPFilter(query, children[1].cloneOP() as AOPBase, children[0].cloneOP())
    override /*suspend*/ fun calculateHistogram(): HistogramResult {
        return children[0].getHistogram()
    }

    public override fun syntaxVerifyAllVariableExistsAutocorrect() {
        for (name in getRequiredVariableNames()) {
            var found = false
            for (prov in getProvidedVariableNames()) {
                if (prov == name) {
                    found = true
                    break
                }
            }
            if (!found) {
                children[1] = children[1].replaceVariableWithUndef(name, false)
            }
        }
    }
}
