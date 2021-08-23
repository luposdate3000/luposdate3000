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
import lupos.shared.IQuery
import lupos.shared.MemoryTable
import lupos.shared.UUID_Counter
import lupos.shared.XMLElement
import lupos.simulator_db.IDatabasePackage
internal class MySimulatorOperatorGraphPackage(
    val queryID: Int,
    val operatorGraph: MutableMap<String, XMLElement>,
    val destinations: MutableMap<String, Int>,
    val operatorGraphPartsToHostMap: MutableMap<String, String>,
    val onFinish: IDatabasePackage?,
    val expectedResult: MemoryTable?,
    val verifyAction: () -> Unit,
    val query: IQuery, // this is an required fake, because the intermediate valued definetly need to share a dictionary, which requires streaming, which the simulator does not support
) : IDatabasePackage {
    public val pckID: Long = UUID_Counter.getNextUUID()
    override fun getPackageID(): Long = pckID

    override fun getPackageSizeInBytes(): Int {
        return getOperatorGraphSizeInBytes() +
            getDestinationsSizeInBytes() +
            getPartsToHostMapSizeInBytes()
    }

    override fun getContentLogString(): String {
        return "OperatorGraphPck(graph $operatorGraph, dests $destinations, parts $operatorGraphPartsToHostMap)"
    }

    private fun getOperatorGraphSizeInBytes(): Int {
        var size = 0
        for ((key, value) in operatorGraph)
            size += key.encodeToByteArray().size + value.toString().encodeToByteArray().size
        return size
    }

    private fun getDestinationsSizeInBytes(): Int {
        val addressSizeIPv6 = 16
        var size = 0
        for ((key) in destinations)
            size += key.encodeToByteArray().size + addressSizeIPv6
        return size
    }

    private fun getPartsToHostMapSizeInBytes(): Int {
        var size = 0
        for ((key, value) in operatorGraphPartsToHostMap)
            size += key.encodeToByteArray().size + value.encodeToByteArray().size
        return size
    }

    private fun getStringSetSizeInBytes(set: Set<String>): Int {
        var size = 0
        for (str in set)
            size += str.encodeToByteArray().size
        return size
    }
}
