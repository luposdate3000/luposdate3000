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
package lupos.operator_logical.singleinput

import lupos.operator_logical.HistogramResult
import lupos.operator_logical.IOPBase
import lupos.operator_logical.IQuery
import lupos.operator_logical.LOPBase
import lupos.s00misc.EOperatorIDExt
import lupos.s00misc.ESortPriorityExt

public class LOPSubGroup public constructor(query: IQuery, child: IOPBase) : LOPBase(query, EOperatorIDExt.LOPSubGroupID, "LOPSubGroup", arrayOf(child), ESortPriorityExt.SAME_AS_CHILD) {
    override fun equals(other: Any?): Boolean = other is LOPSubGroup && children[0] == other.children[0]
    override fun cloneOP(): IOPBase = LOPSubGroup(query, children[0].cloneOP())
    override /*suspend*/ fun calculateHistogram(): HistogramResult {
        return children[0].getHistogram()
    }
}
