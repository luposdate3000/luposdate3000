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

import lupos.shared.ICommunicationHandler
import lupos.shared.IMyInputStream
import lupos.shared.IMyOutputStream
import lupos.shared.Luposdate3000Instance
import lupos.simulator_db.IRouter

internal class MySimulatorCommunicationHandler(val instance: Luposdate3000Instance, val router: IRouter) : ICommunicationHandler {
    override fun sendData(targetHost: String, path: String, params: Map<String, String>, queryID: Int) {
        router.send(targetHost.toInt(), MySimulatorAbstractPackage(queryID, path, params))
    }

    override fun openConnection(targetHost: String, path: String, params: Map<String, String>, queryID: Int): Pair<IMyInputStream, IMyOutputStream> {
        return Pair(MySimulatorInputStreamCrashAll(targetHost.toInt(), path, params), MySimulatorOutputStreamToPackage(queryID, targetHost.toInt(), path, params, router))
    }

    override fun openConnection(targetHost: String, header: String, queryID: Int): Pair<IMyInputStream, IMyOutputStream> {
        TODO()
    }
}
