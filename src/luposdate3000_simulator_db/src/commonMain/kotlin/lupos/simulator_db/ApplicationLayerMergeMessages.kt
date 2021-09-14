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

public class ApplicationLayerMergeMessages(private val child: IUserApplication) : IUserApplicationLayer, IUserApplication {
    private var cache = mutableMapOf<Int, MutableList<IPayload>>()
    private lateinit var parent: IUserApplicationLayer
    init {
        child.setRouter(this)
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
    override fun setRouter(router: IUserApplicationLayer) {
        parent = router
    }
    override fun receive(pck: IPayload): IPayload? {
        if (pck is ApplicationLayerMergeMessages_Package) {
            for (p in pck.data) {
                val pp = child.receive(p)
                if (pp != null) {
                    return pp
                }
            }
        } else {
            val pp = child.receive(pck)
            if (pp != null) {
                return pp
            }
        }
        val cacheLocal = cache
        cache = mutableMapOf()
        for ((destinationAddress, pckList) in cacheLocal) {
            if (pckList.size> 1) {
                parent.send(destinationAddress, ApplicationLayerMergeMessages_Package(pckList))
            } else {
                parent.send(destinationAddress, pckList.first())
            }
        }
        return null
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
    override fun registerTimer(durationInNanoSeconds: Long, entity: IUserApplication) {
        parent.registerTimer(durationInNanoSeconds, entity)
    }
    public override fun timerEvent() {}
}
