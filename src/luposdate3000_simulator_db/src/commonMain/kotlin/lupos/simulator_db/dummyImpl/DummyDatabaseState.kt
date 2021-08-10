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


package lupos.simulator_db.dummyImpl

import lupos.simulator_db.DatabaseState
import lupos.simulator_db.IRouter
import lupos.visualize.distributed.database.VisualisationNetwork
public class DummyDatabaseState(
    visualisationNetwork: VisualisationNetwork,
    ownAddress: Int,
    allAddresses: IntArray,
    sender: IRouter,
    absolutePathToDataDirectory: String
) :
    DatabaseState(visualisationNetwork, ownAddress, allAddresses, sender, absolutePathToDataDirectory, true, null) {

    public val queriesInProgress: MutableMap<Int, Query> = mutableMapOf()
    public var addressForQueryEndResult: Int = -1
    public lateinit var dataFile: String
}
