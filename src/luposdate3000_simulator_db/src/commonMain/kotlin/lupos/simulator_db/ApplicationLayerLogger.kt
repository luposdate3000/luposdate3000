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

public class ApplicationLayerLogger(
    private val ownAddress: Int,
    private val logger: ILogger,
    private val child: IUserApplication,
) : IUserApplicationBoth {
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
        val res = child.receive(pck)
        if (res == null) {
// only if child consumed the message
            logger.onReceivePackage(ownAddress, pck)
        }
        return res
    }
    override fun send(destinationAddress: Int, pck: IPayload) {
        logger.onSendPackage(ownAddress, destinationAddress, pck)
        parent.send(destinationAddress, pck)
    }
    override fun getNextDatabaseHops(destinationAddresses: IntArray): IntArray {
        return parent.getNextDatabaseHops(destinationAddresses)
    }
    override fun registerTimer(durationInNanoSeconds: Long, entity: IUserApplication) {
        parent.registerTimer(durationInNanoSeconds, entity)
    }
    public override fun timerEvent() {}
    override fun flush() {
        parent.flush()
    }
}
