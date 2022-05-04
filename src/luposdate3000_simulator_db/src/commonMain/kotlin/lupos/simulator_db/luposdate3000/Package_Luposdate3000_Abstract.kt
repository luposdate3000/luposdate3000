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
import lupos.shared.dynamicArray.ByteArrayWrapper
import lupos.shared.inline.dynamicArray.ByteArrayWrapperExt
import simora.applications.scenario.parking.IPackage_Database

public class Package_Luposdate3000_Abstract(
    public val queryID: Int,
    public val path: String,
    public val params: Map<String, String>,
    public val data: ByteArrayWrapper = ByteArrayWrapper()
) : IPackage_Database {
    public val pckID: Long = UUID_Counter.getNextUUID()
    override fun getPackageID(): Long = pckID

    override fun getSizeInBytes(): Int {
        return path.encodeToByteArray().size + getParamsSizeInBytes() + ByteArrayWrapperExt.getSize(data)
    }

    override fun toString(): String = "Package_Luposdate3000_Abstract $path"
    private fun getParamsSizeInBytes(): Int {
        var size = 0
        for ((key, value) in params)
            size += key.encodeToByteArray().size + value.encodeToByteArray().size
        return size
    }

    override fun getTopic(): String {
        return when (path) {
            "/distributed/query/dictionary/register", "/distributed/query/dictionary/remove" -> "Database-Dictionary"
            "/distributed/graph/create" -> "Database-Graph-Create"
            "/distributed/graph/modify" -> "Database-Graph-Modify"
            "simulator-intermediate-result" -> "Database-Intermadiate-Result"
            "/shacl/ontology/load" -> "Database-Ontology-Load"
            "/shacl/ontology/import" -> "Database-Ontology-Import"
            "/import/turtle" -> "Database-Turtle-Import"
            "/distributed/graph/commit" -> "Database-Graph-Commit"
            else -> TODO(path)
        }
    }
}
