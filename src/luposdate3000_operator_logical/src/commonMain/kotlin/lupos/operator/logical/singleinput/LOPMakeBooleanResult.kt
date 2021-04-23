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

import lupos.operator.logical.LOPBase
import lupos.shared.EOperatorIDExt
import lupos.shared.ESortPriorityExt
import lupos.shared.IQuery
import lupos.shared.operator.HistogramResult
import lupos.shared.operator.IOPBase

public class LOPMakeBooleanResult public constructor(query: IQuery, child: IOPBase) : LOPBase(query, EOperatorIDExt.LOPMakeBooleanResultID, "LOPMakeBooleanResult", arrayOf(child), ESortPriorityExt.PREVENT_ANY) {
    override fun getProvidedVariableNames(): List<String> {
        return mutableListOf("?boolean")
    }

    override fun equals(other: Any?): Boolean = other is LOPMakeBooleanResult && children[0] == other.children[0]
    override fun cloneOP(): IOPBase = LOPMakeBooleanResult(query, children[0].cloneOP())
    override /*suspend*/ fun calculateHistogram(): HistogramResult {
        val res = HistogramResult()
        res.values["?boolean"] = 1
        res.count = 1
        return res
    }
}
