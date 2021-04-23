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
package lupos.operator_base.singleinput

import lupos.operator_logical.HistogramResult
import lupos.operator_logical.IOPBase
import lupos.operator_logical.IQuery
import lupos.operator_logical.OPBase
import lupos.operator_logical.noinput.OPEmptyRow
import lupos.s00misc.EOperatorIDExt
import lupos.s00misc.ESortPriorityExt
import lupos.s00misc.SanityCheck

public class LOPNOOP public constructor(query: IQuery, child: IOPBase) : OPBase(query, EOperatorIDExt.LOPNOOPID, "LOPNOOP", arrayOf(child), ESortPriorityExt.SAME_AS_CHILD) {
    public constructor(query: IQuery) : this(query, OPEmptyRow(query))

    override fun getPartitionCount(variable: String): Int = SanityCheck.checkUnreachable()
    override fun equals(other: Any?): Boolean = other is LOPNOOP && children[0] == other.children[0]
    override fun cloneOP(): IOPBase = LOPNOOP(query, children[0].cloneOP())
    override /*suspend*/ fun calculateHistogram(): HistogramResult {
        return children[0].getHistogram()
    }
}
