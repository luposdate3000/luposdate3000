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
package lupos.simulator_db.luposdate3000

import lupos.shared.IQuery
import lupos.shared.MemoryTable
import lupos.shared.dynamicArray.ByteArrayWrapper
import lupos.result_format.EQueryResultToStream
import simora.applications.scenario.parking.IPackage_Database

public class PendingWork(
    public val deviceAdress: Int,
    public val queryID: Int,
    public val data: ByteArrayWrapper,
    public val dataID: Int,
    public val destinations: Map<Int, Int>,
    public val dependencies: Set<Int>,
    public val onFinish: IPackage_Database?,
    public val expectedResult: MemoryTable?,
    public val verifyAction: () -> Unit,
    public val query: IQuery,
    public val lastRootOperator: Int,
public val evaluatorToUse:EQueryResultToStream,  
) {
    internal companion object {
        public var pendingWorkCounter: Int = 0
    }

    public val pendingWorkID: Int = pendingWorkCounter++
}
