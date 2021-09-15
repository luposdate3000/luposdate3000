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
import lupos.simulator_db.IPackage_Database
public class Package_Luposdate3000_Operatorgraph(
    public val queryID: Int,
    public val operatorGraph: MutableMap<Int, XMLElement>,
    public val destinations: MutableMap<Int, Int>,
    public val operatorGraphPartsToHostMap: MutableMap<Int, Int>,
    public val onFinish: IPackage_Database?,
    public val expectedResult: MemoryTable?,
    public val verifyAction: () -> Unit,
    public val query: IQuery, // this is an required fake, because the intermediate valued definetly need to share a dictionary, which requires streaming, which the simulator does not support
) : IPackage_Database {
    public val pckID: Long = UUID_Counter.getNextUUID()
    override fun getPackageID(): Long = pckID

    override fun getSizeInBytes(): Int {
        var size = 0
        for ((key, value) in operatorGraph)
            size += 4 + value.toString().encodeToByteArray().size
        size += 4 * destinations.size
        size += 8 * operatorGraphPartsToHostMap.size
        return size
    }

    override fun getContentLogString(): String {
        return "OperatorGraphPck(graph $operatorGraph, dests $destinations, parts $operatorGraphPartsToHostMap)"
    }
    override fun toString(): String {
        return "Package_Luposdate3000_Operatorgraph($queryID $operatorGraphPartsToHostMap $operatorGraph)"
    }
}
