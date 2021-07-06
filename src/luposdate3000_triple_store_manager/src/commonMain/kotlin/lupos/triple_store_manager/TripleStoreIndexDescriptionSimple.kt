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
        var buf1 = hostname.encodeToByteArray()
        var buf2 = key.encodeToByteArray()
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

    internal override fun findPartitionFor(query: IQuery, triple: DictionaryValueTypeArray): Int {
        return 0
    }

    public override fun getStore(query: IQuery, params: Array<IOPBase>, partition: Partition): Pair<LuposHostname, LuposStoreKey> {
        SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_manager/src/commonMain/kotlin/lupos/triple_store_manager/TripleStoreIndexDescriptionSimple.kt:74"/*SOURCE_FILE_END*/ }, { partition.limit.size == 0 })
        SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_manager/src/commonMain/kotlin/lupos/triple_store_manager/TripleStoreIndexDescriptionSimple.kt:75"/*SOURCE_FILE_END*/ }, { partition.data.size == 0 })
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

    internal override fun assignHosts() {
        SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_manager/src/commonMain/kotlin/lupos/triple_store_manager/TripleStoreIndexDescriptionSimple.kt:92"/*SOURCE_FILE_END*/ }, { hostname == "" })
        SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_manager/src/commonMain/kotlin/lupos/triple_store_manager/TripleStoreIndexDescriptionSimple.kt:93"/*SOURCE_FILE_END*/ }, { key == "" })
        val tmp = ((instance.tripleStoreManager!!) as TripleStoreManagerImpl).getNextHostAndKey()
        hostname = tmp.first
        key = tmp.second
    }

    public override fun getPartitionCount(): Int {
        return 1
    }

    public override fun getDistributionCount(): Int {
        return 1
    }

    internal override fun getAllLocations(): List<Pair<LuposHostname, LuposStoreKey>> {
        return listOf(Pair(hostname, key))
    }

    public override fun toXMLElement(): XMLElement {
        val res = super.toXMLElement()
        res.addAttribute("type", "TripleStoreIndexDescriptionSimple")
        return res
    }
}
