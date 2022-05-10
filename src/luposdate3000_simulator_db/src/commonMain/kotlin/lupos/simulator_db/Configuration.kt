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
package simora

import simora.applications.scenario.parking.Application_QuerySender
import simora.applications.scenario.parking.IPackage_Database

public fun SimulationRun.addQuerySender(
    startClockInSec: Int,
    sendRateInSec: Int,
    maxNumberOfQueries: Int,
    query: String,
    receiver: Int = rootRouterAddress
) {
    val sender = Application_QuerySender(getLogger0(), query, receiver, outputDirectory, "")
    val device = getDeviceByAddress(receiver)
    device.applicationStack.addChildApplication(sender)
    globalApplications.add(sender)
}

public fun SimulationRun.addQuerySender(
    startClockInSec: Int,
    sendRateInSec: Int,
    maxNumberOfQueries: Int,
    queryPck: IPackage_Database,
    receiver: Int = rootRouterAddress
) {
    val sender = Application_QuerySender(getLogger0(), queryPck, receiver, outputDirectory, "")
    val device = getDeviceByAddress(receiver)
    device.applicationStack.addChildApplication(sender)
    globalApplications.add(sender)
}
