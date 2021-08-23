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

public class RouterCombiningBlocks(private val router: IRouter) : IRouter {
    private val cache = mutableMapOf<Int, MutableList<IPayload>>()
    override fun receive(pck: IPayload) {
        if (pck is QueryPackageBlock) {
            for (p in pck.data) {
                router.receive(p)
            }
        } else {
            router.receive(pck)
        }
        for ((destinationAddress, pckList) in cache) {
            if (pckList.size> 1) {
                router.send(destinationAddress, QueryPackageBlock(pckList))
            } else {
                router.send(destinationAddress, pckList.first())
            }
        }
        cache.clear()
    }
    override fun send(destinationAddress: Int, pck: IPayload) {
        var c = cache[destinationAddress]
        if (c == null) {
            c = mutableListOf()
            cache[destinationAddress] = c
        }
        c.add(pck)
    }
    override fun getNextDatabaseHops(destinationAddresses: IntArray): IntArray {
        return router.getNextDatabaseHops(destinationAddresses)
    }
}
