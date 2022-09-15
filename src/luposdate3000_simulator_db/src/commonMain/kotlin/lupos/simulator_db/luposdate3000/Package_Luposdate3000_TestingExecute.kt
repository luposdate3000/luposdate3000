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

import lupos.shared.UUID_Counter
import simora.applications.scenario.parking.IPackage_DatabaseTesting

public class Package_Luposdate3000_TestingExecute(
    public val query: String,
) : IPackage_DatabaseTesting {
private val hops=mutableListOf<Int>()
    override fun addHop(address:Int){hops.add(address)}
    override fun getAllHops():List<Int> =hops
    public val pckID: Long = UUID_Counter.getNextUUID()
    override fun getPackageID(): Long = pckID
    internal var _onFinish: IPackage_DatabaseTesting? = null
    override fun getOnFinish(): IPackage_DatabaseTesting? = _onFinish
    override fun setOnFinish(pck: IPackage_DatabaseTesting) {
        var b = getOnFinish()
        if (b != null) {
            b.setOnFinish(pck)
        } else {
            _onFinish = pck
        }
    }

    override fun getSizeInBytes(): Int {
        return 0
    }

    override fun toString(): String {
        return ""
    }

    override fun getTopic(): String = "Testing"
}
