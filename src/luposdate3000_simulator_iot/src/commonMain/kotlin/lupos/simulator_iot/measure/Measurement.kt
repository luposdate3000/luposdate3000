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

package lupos.simulator_iot.measure

internal class Measurement {

    // topology
    internal var numberOfDevices: Double = 0.0
    internal var numberOfSensorDevices: Double = 0.0
    internal var numberOfDatabaseDevices: Double = 0.0
    internal var numberOfQuerySenders: Double = 0.0
    internal var numberOfLinks: Double = 0.0

    // times
    internal var initializationDurationInSec: Double = 0.0
    internal var realSimulationDurationInSec: Double = 0.0
    internal var simulationDurationInSec: Double = 0.0
    internal var realStartUpTimeStampInISO: String = ""
    internal var shutDownTimeStampInISO: String = ""
    internal var realShutDownTimeStampInISO: String = ""

    // traffic
    internal var numberOfSentPackages: Double = 0.0
    internal var networkTrafficInKiloBytes: Double = 0.0
    internal var numberOfParkingSamplesMade: Double = 0.0
    internal var numberOfQueriesRequested: Double = 0.0
    internal var numberOfSentDatabasePackages: Double = 0.0
    internal var numberOfSentSamplePackages: Double = 0.0
    internal var numberOfSentDIOPackages: Double = 0.0
    internal var numberOfSentDAOPackages: Double = 0.0
    internal var numberOfForwardedPackages: Double = 0.0
}
