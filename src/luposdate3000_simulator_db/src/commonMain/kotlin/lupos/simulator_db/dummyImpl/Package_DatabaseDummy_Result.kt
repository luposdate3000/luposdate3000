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

package lupos.simulator_db.dummyImpl

import lupos.shared.UUID_Counter
import simora.applications.scenario.parking.IPackage_Database

public class Package_DatabaseDummy_Result(
    public val result: ByteArray, // die Nutzdaten ... zurzeit alles als ein Block, später besser bidirektionales streaming, wobei primär Richtung root-node gesendet wird.
    public val destinationAddress: Int, // Richtung root-node
    public val senderAddress: Int,
    public val queryID: Int,
    public val operatorID: Int, // damit der empfänger weiß, was für ein ergebnis dies ist ... kann ggf in "result" integriert werden
) : IPackage_Database {
    private val hops = mutableListOf<Int>()
    override fun addHop(address: Int) { hops.add(address) }
    override fun getAllHops(): List<Int> = hops
    public val pckID: Long = UUID_Counter.getNextUUID()
    override fun getPackageID(): Long = pckID
    override fun getSizeInBytes(): Int {
        @Suppress("UnnecessaryVariable")
        val dummySize = 20
        return dummySize
    }

    override fun getTopic(): String = "Database-Intermediate-Result"
}
