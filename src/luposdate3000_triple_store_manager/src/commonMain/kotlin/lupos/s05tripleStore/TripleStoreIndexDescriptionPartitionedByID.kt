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
package lupos.s05tripleStore

import lupos.s00misc.EIndexPattern
import lupos.s00misc.EIndexPatternExt
import lupos.s00misc.EIndexPatternHelper
import lupos.s00misc.Partition
import lupos.s00misc.SanityCheck
import lupos.s00misc.XMLElement
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.IQuery
import kotlin.jvm.JvmField

public class TripleStoreIndexDescriptionPartitionedByID(
    idx: EIndexPattern,
    @JvmField internal val partitionCount: Int,
    @JvmField internal val partitionColumn: Int,
) : TripleStoreIndexDescription() {
    internal val hostnames = Array<LuposHostname>(partitionCount) { "" }
    internal val keys = Array<LuposStoreKey>(partitionCount) { "" }
    internal override fun findPartitionFor(query: IQuery, triple: IntArray): Int {
        return triple[EIndexPatternHelper.tripleIndicees[idx_set[0]][partitionColumn]] % partitionCount
    }

    public override fun getStore(query: IQuery, params: Array<IOPBase>, partition: Partition): Pair<LuposHostname, LuposStoreKey> {
        SanityCheck.check({ partition.limit.size == 1 }, { "${partition.limit} ${partition.data}" })
        SanityCheck.check({ partition.data.size == 1 }, { "${partition.limit} ${partition.data}" })
        for (v in partition.data.values) {
            return Pair(hostnames[v], keys[v])
        }
        throw Exception("unreachable")
    }

    init {
        idx_set = when (idx) {
            EIndexPatternExt.SPO, EIndexPatternExt.S_PO, EIndexPatternExt.SP_O -> intArrayOf(EIndexPatternExt.SPO, EIndexPatternExt.S_PO, EIndexPatternExt.SP_O)
            EIndexPatternExt.SOP, EIndexPatternExt.S_OP, EIndexPatternExt.SO_P -> intArrayOf(EIndexPatternExt.SOP, EIndexPatternExt.S_OP, EIndexPatternExt.SO_P)
            EIndexPatternExt.PSO, EIndexPatternExt.P_SO, EIndexPatternExt.PS_O -> intArrayOf(EIndexPatternExt.PSO, EIndexPatternExt.P_SO, EIndexPatternExt.PS_O)
            EIndexPatternExt.POS, EIndexPatternExt.P_OS, EIndexPatternExt.PO_S -> intArrayOf(EIndexPatternExt.POS, EIndexPatternExt.P_OS, EIndexPatternExt.PO_S)
            EIndexPatternExt.OSP, EIndexPatternExt.O_SP, EIndexPatternExt.OS_P -> intArrayOf(EIndexPatternExt.OSP, EIndexPatternExt.O_SP, EIndexPatternExt.OS_P)
            EIndexPatternExt.OPS, EIndexPatternExt.O_PS, EIndexPatternExt.OP_S -> intArrayOf(EIndexPatternExt.OPS, EIndexPatternExt.O_PS, EIndexPatternExt.OP_S)
            else -> SanityCheck.checkUnreachable()
        }
    }

    internal override fun assignHosts() {
        for (i in 0 until partitionCount) {
            val tmp = (tripleStoreManager as TripleStoreManagerImpl).getNextHostAndKey()
            hostnames[i] = tmp.first
            keys[i] = tmp.second
        }
    }

    public override fun getPartitionCount(): Int {
        return partitionCount
    }

    public override fun getDistributionCount(): Int {
        return partitionCount
    }

    internal override fun getAllLocations(): List<Pair<LuposHostname, LuposStoreKey>> {
        var res = mutableListOf<Pair<LuposHostname, LuposStoreKey>>()
        for (i in 0 until partitionCount) {
            res.add(Pair(hostnames[i], keys[i]))
        }
        return res
    }

    public override fun toXMLElement(): XMLElement {
        val res = super.toXMLElement()
        res.addAttribute("type", "TripleStoreIndexDescriptionPartitionedByID")
        res.addAttribute("partitionCount", "$partitionCount")
        res.addAttribute("partitionColumn", "$partitionColumn")
        return res
    }
}
