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

package lupos.simulator_core

public abstract class Entity : ISimulationLifeCycle {

    internal lateinit var simulation: Simulation

    private var isTerminated = false

    public abstract fun onEvent(source: Entity, data: Any)

    internal fun processIncomingEvent(event: Event) {
        if (isTerminated) {
            return
        }

        if (event.data is ITimer) {
            event.data.onExpire()
        } else {
            onEvent(event.source, event.data)
        }
    }

    protected fun scheduleEvent(destination: Entity, data: Any, delay: Long) {
        require(!isTerminated)
        val sim = simulation
        sim.addEvent(delay, this, destination, data)
    }

    public fun setTimer(time: Long, callback: ITimer) {
        scheduleEvent(this, callback, time)
    }

    protected fun terminate() {
        onShutDown()
        isTerminated = true
    }
}
