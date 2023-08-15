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
package lupos.operator.logical

import lupos.operator.base.OPBase
import lupos.shared.EOperatorID
import lupos.shared.ESortPriority
import lupos.shared.IQuery
import lupos.shared.SanityCheck
import lupos.shared.operator.ILOPBase
import lupos.shared.operator.IOPBase

public abstract class LOPBase public constructor(
    query: IQuery,
    operatorID: EOperatorID,
    classname: String,
    children: Array<IOPBase>,
    sortPriority: ESortPriority,
) :
    OPBase(query, operatorID, classname, children, sortPriority), ILOPBase {
    override fun getPartitionCount(variable: String): Int = SanityCheck.checkUnreachable()
}
