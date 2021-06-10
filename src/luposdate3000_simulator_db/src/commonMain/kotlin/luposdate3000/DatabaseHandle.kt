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

import lupos.endpoint.LuposdateEndpoint
import lupos.shared.Luposdate3000Instance

public class DatabaseHandle : IDatabase {
    var instance = Luposdate3000Instance()
    override fun start(initialState: IDatabaseState) {
        instance.LUPOS_PROCESS_URLS = initialState.allAddresses.map { it.toString() }.toTypedArray()
        instance.LUPOS_PROCESS_ID = initialState.allAddresses.indexOf(initialState.ownAddress)
        instance = LuposdateEndpoint.initialize(instance)
    }

    override fun activate(state: IDatabaseState) {
        if (!instance.initialized) {
            instance = LuposdateEndpoint.initialize(instance)
        }
    }

    override fun deactivate(state: IDatabaseState): IDatabaseState {
        if ((!BufferManagerExt.isInMemoryOnly) && (instance.LUPOS_DICTIONARY_MODE != EDictionaryTypeExt.InMemory)) {
            // do not disable inmemory databases, because they would loose all data
            LuposdateEndpoint.close(instance)
        }
    }

    override fun end(state: IDatabaseState) {
        LuposdateEndpoint.close(instance)
    }

    override fun receiveQuery(sourceAddress: Int, query: ByteArray) {
    }

    override fun receive(pck: IDatabasePackage) {
    }
}

/*
public interface IDatabase {
    public fun start(initialState: IDatabaseState)
    public fun activate(state: IDatabaseState)
    public fun deactivate(): IDatabaseState
    public fun end()
    public fun receiveQuery(sourceAddress: Int, query: ByteArray)
    public fun receive(pck: IDatabasePackage)
}

public interface IRouter {
    public fun send(destinationAddress: Int, pck: IDatabasePackage)
    public fun sendQueryResult(destinationAddress: Int, result: ByteArray)
    public fun getNextDatabaseHops(destinationAddresses: IntArray): IntArray
}

public interface IDatabasePackage

public interface IDatabaseState {
    public val ownAddress: Int
    public var allAddresses: IntArray
    public val sender: IRouter
    public val absolutePathToDataDirectory: String
}
*/
