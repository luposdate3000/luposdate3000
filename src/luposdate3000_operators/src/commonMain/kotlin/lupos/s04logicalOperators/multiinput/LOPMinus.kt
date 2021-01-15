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

package lupos.s04logicalOperators.multiinput
import lupos.s00misc.EOperatorIDExt
import lupos.s00misc.ESortPriorityExt
import lupos.s04logicalOperators.HistogramResult
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.IQuery
import lupos.s04logicalOperators.LOPBase
import kotlin.jvm.JvmField
public class LOPMinus public constructor(query: IQuery, first: IOPBase, second: IOPBase, @JvmField public var tmpFakeVariables: List<String>) : LOPBase(query, EOperatorIDExt.LOPMinusID, "LOPMinus", arrayOf(first, second), ESortPriorityExt.MINUS) {
    @JvmField public var hadSortPushDown: Boolean = false
    override fun getProvidedVariableNames(): List<String> = (children[0].getProvidedVariableNames() + tmpFakeVariables).distinct()
    override fun getRequiredVariableNames(): List<String> = listOf()
    override fun equals(other: Any?): Boolean = other is LOPMinus && children[0] == other.children[0] && children[1] == other.children[1]
    override fun cloneOP(): IOPBase = LOPMinus(query, children[0].cloneOP(), children[1].cloneOP(), tmpFakeVariables.toMutableList())
    override /*suspend*/ fun calculateHistogram(): HistogramResult {
        return children[0].getHistogram()
    }
}
