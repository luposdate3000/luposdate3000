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

import lupos.shared.ITripleStoreIndexDescription
import lupos.shared.Luposdate3000Instance
import lupos.shared.MemoryTable
import lupos.shared.UUID_Counter
import simora.applications.scenario.parking.IPackage_DatabaseTesting

public class Package_Luposdate3000_TestingCompareGraphPackage(
    public val query: String,
    public val expectedResult: MemoryTable,
    public val verifyAction: () -> Unit,
    public val idx: ITripleStoreIndexDescription?,
) : IPackage_DatabaseTesting {

    private val hops = mutableListOf<Int>()
    override fun addHop(address: Int) { hops.add(address) }
    override fun getAllHops(): List<Int> = hops
    private var needsPrepare = false
    private var graph: String? = null
    private var instance: Luposdate3000Instance? = null
    public fun prepare(): Package_Luposdate3000_TestingCompareGraphPackage {
        if (needsPrepare) {
            val q: String = if (graph == "") {
                "SELECT ?s ?p ?o WHERE { ?s ?p ?o . }"
            } else {
                "SELECT ?s ?p ?o WHERE { GRAPH <$graph> { ?s ?p ?o . }}"
            }
            var res: Package_Luposdate3000_TestingCompareGraphPackage? = null
            for (idx in instance!!.tripleStoreManager!!.getGraph(graph!!).getIndices()) {
                if (res == null) {
                    res = Package_Luposdate3000_TestingCompareGraphPackage(q, expectedResult, verifyAction, idx)
                } else {
                    res.setOnFinish(Package_Luposdate3000_TestingCompareGraphPackage(q, expectedResult, verifyAction, idx))
                }
            }
            if (_onFinish != null) {
                res!!.setOnFinish(_onFinish!!)
            }
            return res!!
        } else {
            return this
        }
    }

    public companion object {
        public operator fun invoke(
            query: String?,
            expectedResult: MemoryTable,
            verifyAction: () -> Unit,
            graph: String?,
            instance: Luposdate3000Instance,
        ): Package_Luposdate3000_TestingCompareGraphPackage {
            if (query != null) {
                return Package_Luposdate3000_TestingCompareGraphPackage(query, expectedResult, verifyAction, null)
            } else {
                val res = Package_Luposdate3000_TestingCompareGraphPackage("", expectedResult, verifyAction, null)
                res.needsPrepare = true
                res.graph = graph
                res.instance = instance
                return res
            }
        }
    }

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
