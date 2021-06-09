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
package lupos.triple_store_manager

import lupos.shared.EIndexPattern
import lupos.shared.EIndexPatternExt
import lupos.shared.EIndexPatternHelper
import lupos.shared.IQuery
import lupos.shared.LuposHostname
import lupos.shared.LuposStoreKey
import lupos.shared.Luposdate3000Instance
import lupos.shared.Partition
import lupos.shared.SanityCheck
import lupos.shared.XMLElement
import lupos.shared.operator.IOPBase
import lupos.shared_inline.ByteArrayHelper
import kotlin.jvm.JvmField

public class TripleStoreIndexDescriptionPartitionedByID(
    idx: EIndexPattern,
    @JvmField internal val partitionCount: Int,
    @JvmField internal val partitionColumn: Int,
    instance: Luposdate3000Instance,
) : TripleStoreIndexDescription(instance) {
    internal val hostnames = Array<LuposHostname>(partitionCount) { "" }
    internal val keys = Array<LuposStoreKey>(partitionCount) { "" }

    @JvmField
    internal var byteArray: ByteArray? = null
    override fun toByteArray(): ByteArray {
        if (byteArray != null) {
            return byteArray!!
        }
        var size = 16
        for (i in 0 until partitionCount) {
            var buf1 = hostnames[i].encodeToByteArray()
            var buf2 = keys[i].encodeToByteArray()
            size += 8 + buf1.size + buf2.size
        }
        val byteArray2 = ByteArray(size)
        byteArray = byteArray2
        var off = 0
        ByteArrayHelper.writeInt4(byteArray2, off, ETripleStoreIndexDescriptionPartitionedTypeExt.PartitionedByID)
        off += 4
        ByteArrayHelper.writeInt4(byteArray2, off, idx_set.first())
        off += 4
        ByteArrayHelper.writeInt4(byteArray2, off, partitionCount)
        off += 4
        ByteArrayHelper.writeInt4(byteArray2, off, partitionColumn)
        off += 4
        for (i in 0 until partitionCount) {
            var buf1 = hostnames[i].encodeToByteArray()
            ByteArrayHelper.writeInt4(byteArray2, off, buf1.size)
            off += 4
            buf1.copyInto(byteArray2, off)
            off += buf1.size
            var buf2 = keys[i].encodeToByteArray()
            ByteArrayHelper.writeInt4(byteArray2, off, buf2.size)
            off += 4
            buf2.copyInto(byteArray2, off)
            off += buf2.size
        }
        return byteArray2
    }

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
            val tmp = ((instance.tripleStoreManager!!) as TripleStoreManagerImpl).getNextHostAndKey()
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
