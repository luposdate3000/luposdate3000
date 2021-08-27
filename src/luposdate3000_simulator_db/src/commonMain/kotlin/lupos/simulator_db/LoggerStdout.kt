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

package lupos.simulator_db
import lupos.shared.XMLElement
public interface LoggerStdout (val sim:Simulation): ILogger {
    override fun onSendNetworkPackage(src: Int, dest: Int, hop: Int,  pck: IPayload, delay: Long) {println("${sim.clock} onSendNetworkPackage $src $dest $hop $delay $pck")}
    override fun onReceiveNetworkPackage(address: Int,  pck: IPayload) {println("${sim.clock} onReceiveNetworkPackage $address $pck")}
    override fun onSendPackage(src: Int, dest: Int,  pck: IPayload) {println("${sim.clock} onSendPackage $src $dest $pck")}
    override fun onReceivePackage(address: Int,  pck: IPayload) {println("${sim.clock} onReceivePackage $address $pck")}
    override fun addWork(queryID: Int, address: Int, operatorGraph: XMLElement, keysIn: Set<String>, keysOut: Set<String>) {println("${sim.clock} addWork $queryID $address $operatorGraph $keysIn $keysOut")}
    override fun addOperatorGraph(queryId: Int, operatorGraph: MutableMap<String, XMLElement>) {println("${sim.clock} addOperatorGraph $queryId $operatorGraph")}
    override fun addConnectionTable(src: Int, dest: Int, hop: Int) {println("${sim.clock} addConnectionTable $src $dest $hop")}
    override fun addConnectionTableDB(src: Int, dest: Int, hop: Int) {println("${sim.clock} addConnectionTableDB $src $dest $hop")}
    override fun onStartUp() {println("${sim.clock} onStartUp")}
    override fun onShutDown() {println("${sim.clock} onShutDown")}
    override fun onSteadyState() {println("${sim.clock} onSteadyState")}
}
