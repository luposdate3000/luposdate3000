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

import lupos.shared.DictionaryValueTypeArray
import lupos.shared.EIndexPattern
import lupos.shared.EIndexPatternExt
import lupos.shared.IQuery
import lupos.shared.LuposHostname
import lupos.shared.LuposStoreKey
import lupos.shared.Luposdate3000Instance
import lupos.shared.Partition
import lupos.shared.SanityCheck
import lupos.shared.XMLElement
import lupos.shared.inline.ByteArrayHelper
import lupos.shared.operator.IOPBase
import kotlin.jvm.JvmField

public class TripleStoreIndexDescriptionSimple(
    idx: EIndexPattern,
    instance: Luposdate3000Instance,
) : TripleStoreIndexDescription(instance) {
    @JvmField
    internal var hostname: LuposHostname = ""

    @JvmField
    internal var key: LuposStoreKey = ""

    @JvmField
    internal var byteArray: ByteArray? = null

    override fun toByteArray(): ByteArray {
        if (byteArray != null) {
            return byteArray!!
        }
        val buf1 = hostname.encodeToByteArray()
        val buf2 = key.encodeToByteArray()
        val size = 16 + buf1.size + buf2.size
        val byteArray2 = ByteArray(size)
        byteArray = byteArray2
        var off = 0
        ByteArrayHelper.writeInt4(byteArray2, off, ETripleStoreIndexDescriptionPartitionedTypeExt.Simple)
        off += 4
        ByteArrayHelper.writeInt4(byteArray2, off, idx_set.first())
        off += 4
        ByteArrayHelper.writeInt4(byteArray2, off, buf1.size)
        off += 4
        buf1.copyInto(byteArray2, off)
        off += buf1.size
        ByteArrayHelper.writeInt4(byteArray2, off, buf2.size)
        off += 4
        buf2.copyInto(byteArray2, off)
        off += buf2.size
        return byteArray2
    }

    override fun findPartitionFor(query: IQuery, triple: DictionaryValueTypeArray): Int {
        return 0
    }

    override fun getStore(query: IQuery, params: Array<IOPBase>, partition: Partition): Pair<LuposHostname, LuposStoreKey> {
if(SanityCheck.enabled){if(!( partition.limit.isEmpty() )){throw Exception("SanityCheck failed")}}
if(SanityCheck.enabled){if(!( partition.data.isEmpty() )){throw Exception("SanityCheck failed")}}
        return Pair(hostname, key)
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

    override fun assignHosts() {
if(SanityCheck.enabled){if(!( hostname == "" )){throw Exception("SanityCheck failed")}}
if(SanityCheck.enabled){if(!( key == "" )){throw Exception("SanityCheck failed")}}
        val tmp = ((instance.tripleStoreManager!!) as TripleStoreManagerImpl).getNextHostAndKey(0)
        hostname = tmp.first
        key = tmp.second
    }

    override fun getPartitionCount(params: Array<IOPBase>): Int {
        return 1
    }

    override fun getDistributionCount(): Int {
        return 1
    }

    override fun getAllLocations(): List<Pair<LuposHostname, LuposStoreKey>> {
        return listOf(Pair(hostname, key))
    }

    override fun toXMLElement(): XMLElement {
        val res = super.toXMLElement()
        res.addAttribute("type", "TripleStoreIndexDescriptionSimple")
        res.addAttribute("hostname", "$hostname")
        res.addAttribute("key", "$key")
        return res
    }

    override fun requireSplitFromStore(): Boolean = true
    override fun requiresPartitioning(params: Array<IOPBase>): Map<String, Int> = mapOf()
}
