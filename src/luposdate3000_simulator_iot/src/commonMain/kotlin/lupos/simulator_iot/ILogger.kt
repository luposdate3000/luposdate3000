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

package lupos.simulator_iot

import lupos.shared.XMLElement
import lupos.simulator_core.ILoggerCore

public interface ILogger : ILoggerCore {
    public fun initialize(simRun: SimulationRun)
    public fun onSendNetworkPackage(src: Int, dest: Int, hop: Int, pck: IPayload, delay: Long)
    public fun onReceiveNetworkPackage(address: Int, pck: IPayload)
    public fun onSendPackage(src: Int, dest: Int, pck: IPayload)
    public fun onReceivePackage(address: Int, pck: IPayload)
    public fun onStartSimulation()
    public fun onStopSimulation()
    public fun addWork(queryID: Int, address: Int, operatorGraph: XMLElement, keysIn: Set<Int>, keysOut: Set<Int>)
    public fun addOperatorGraph(queryId: Int, operatorGraph: MutableMap<Int, XMLElement>)
    public fun addConnectionTable(src: Int, dest: Int, hop: Int)
    public fun addDevice(address: Int, x: Double, y: Double)
}
