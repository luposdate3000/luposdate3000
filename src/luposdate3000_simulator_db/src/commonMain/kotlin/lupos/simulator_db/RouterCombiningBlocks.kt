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

public class ApplicationLayerMergeMessages(private val parent: IUserApplicationLayer) : IUserApplicationLayer {
    private val cache = mutableMapOf<Int, MutableList<IPayload>>()
    private lateinit var child: IUserApplication
    init {
        parent.addChildApplication(this)
    }
    override fun startUp() {
        child.startUp()
    }
    override fun shutDown() {
        child.shutDown()
    }
    override fun getAllChildApplications(): Set<IUserApplication> {
        var res = mutableSetOf<IUserApplication>()
        res.add(child)
        val c = child
        if (c is IUserApplicationLayer) {
            res.addAll(c.getAllChildApplications())
        }
        return res
    }
    override fun addChildApplication(child: IUserApplication) {
        this.child = child
    }
    override fun receive(pck: IPayload) {
        if (pck is QueryPackageBlock) {
            for (p in pck.data) {
                child.receive(p)
            }
        } else {
            child.receive(pck)
        }
        for ((destinationAddress, pckList) in cache) {
            if (pckList.size> 1) {
                parent.send(destinationAddress, QueryPackageBlock(pckList))
            } else {
                parent.send(destinationAddress, pckList.first())
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
        return parent.getNextDatabaseHops(destinationAddresses)
    }
}
