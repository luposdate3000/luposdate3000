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
public class ApplicationLayerMultipleChilds(
    private var childs: Array<IUserApplication>,
) : IUserApplicationBoth {
    private var hadStartUp = false
    private lateinit var parent: IUserApplicationLayer
    init {
        for (child in childs) {
            child.setRouter(this)
        }
    }
    public fun addChild(child: IUserApplication) {
        val res = Array<IUserApplication>(childs.size + 1) {
            if (it <childs.size) {
                childs[it]
            } else {
                child
            }
        }
        childs = res
        child.setRouter(this)
        if (hadStartUp) {
            child.startUp()
        }
    }
    override fun startUp() {
        for (child in childs) {
            child.startUp()
        }
        hadStartUp = true
    }
    override fun shutDown() {
        for (child in childs) {
            child.shutDown()
        }
    }
    override fun getAllChildApplications(): Set<IUserApplication> {
        var res = mutableSetOf<IUserApplication>()
        for (child in childs) {
            res.add(child)
            val c = child
            if (c is IUserApplicationLayer) {
                res.addAll(c.getAllChildApplications())
            }
        }
        return res
    }
    override fun setRouter(router: IUserApplicationLayer) {
        parent = router
    }
    override fun receive(pck: IPayload): IPayload? {
        for (child in childs) {
            val pp = child.receive(pck)
            if (pp == null) {
                return null
            }
        }
        return pck
    }
    override fun send(destinationAddress: Int, pck: IPayload) {
        parent.send(destinationAddress, pck)
    }
    override fun getNextDatabaseHops(destinationAddresses: IntArray): IntArray {
        return parent.getNextDatabaseHops(destinationAddresses)
    }
    override fun registerTimer(durationInNanoSeconds: Long, entity: IUserApplication) {
        parent.registerTimer(durationInNanoSeconds, entity)
    }
    public override fun timerEvent() {}
}
