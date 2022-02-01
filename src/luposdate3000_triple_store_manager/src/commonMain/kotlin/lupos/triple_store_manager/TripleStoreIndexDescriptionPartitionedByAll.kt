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

import lupos.operator.arithmetik.noinput.AOPConstant
import lupos.operator.arithmetik.noinput.AOPVariable
import lupos.shared.DictionaryValueHelper
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

public class TripleStoreIndexDescriptionPartitionedByAll(
    idx: EIndexPattern,
    @JvmField internal val partitionCount: Int, // count for each column
    instance: Luposdate3000Instance,
) : TripleStoreIndexDescription(instance) {
    internal val partitionCount3 = partitionCount * partitionCount * partitionCount
    internal val hostnames = Array(partitionCount3) { "" }
    internal val keys = Array(partitionCount3) { "" }
    internal val fixedPartitionNameS = "?PartitionedByAllS"
    internal val fixedPartitionNameP = "?PartitionedByAllP"
    internal val fixedPartitionNameO = "?PartitionedByAllO"

    @JvmField
    internal var byteArray: ByteArray? = null
    override fun toByteArray(): ByteArray {
        if (byteArray != null) {
            return byteArray!!
        }
        var size = 12
        for (i in 0 until partitionCount3) {
            val buf1 = hostnames[i].encodeToByteArray()
            val buf2 = keys[i].encodeToByteArray()
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
        for (i in 0 until partitionCount3) {
            val buf1 = hostnames[i].encodeToByteArray()
            ByteArrayHelper.writeInt4(byteArray2, off, buf1.size)
            off += 4
            buf1.copyInto(byteArray2, off)
            off += buf1.size
            val buf2 = keys[i].encodeToByteArray()
            ByteArrayHelper.writeInt4(byteArray2, off, buf2.size)
            off += 4
            buf2.copyInto(byteArray2, off)
            off += buf2.size
        }
        return byteArray2
    }

    override fun findPartitionFor(query: IQuery, triple: DictionaryValueTypeArray): Int {
        val s = DictionaryValueHelper.toInt(triple[0]) % partitionCount
        val p = DictionaryValueHelper.toInt(triple[1]) % partitionCount
        val o = DictionaryValueHelper.toInt(triple[2]) % partitionCount
        return myHashG(s, p, o)
    }

    internal inline fun myHashG(s: Int, p: Int, o: Int): Int = s * partitionCount * partitionCount + p * partitionCount + o

    override fun getStore(query: IQuery, params: Array<IOPBase>, partition: Partition): Pair<LuposHostname, LuposStoreKey> {
        var s = (params[0] as? AOPConstant)?.value?.toInt()
        var p = (params[0] as? AOPConstant)?.value?.toInt()
        var o = (params[0] as? AOPConstant)?.value?.toInt()
        if (s == null) {
            val ns = (params[0] as? AOPVariable)?.name
            s = partition.data[ns]
        }
        if (p == null) {
            val np = (params[1] as? AOPVariable)?.name
            p = partition.data[np]
        }
        if (o == null) {
            val no = (params[2] as? AOPVariable)?.name
            o = partition.data[no]
        }
        if (s == null) {
            s = partition.data[fixedPartitionNameS]
        }
        if (p == null) {
            p = partition.data[fixedPartitionNameP]
        }
        if (o == null) {
            o = partition.data[fixedPartitionNameO]
        }
        SanityCheck.check(
            { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_manager/src/commonMain/kotlin/lupos/triple_store_manager/TripleStoreIndexDescriptionPartitionedByAll.kt:118"/*SOURCE_FILE_END*/ },
            { s != null }
        )
        SanityCheck.check(
            { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_manager/src/commonMain/kotlin/lupos/triple_store_manager/TripleStoreIndexDescriptionPartitionedByAll.kt:122"/*SOURCE_FILE_END*/ },
            { p != null }
        )
        SanityCheck.check(
            { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_manager/src/commonMain/kotlin/lupos/triple_store_manager/TripleStoreIndexDescriptionPartitionedByAll.kt:126"/*SOURCE_FILE_END*/ },
            { o != null }
        )
        val h = myHashG(s!! % partitionCount, p!! % partitionCount, o!! % partitionCount)
        return Pair(hostnames[h], keys[h])
    }

    init {
        SanityCheck.check(
            { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_manager/src/commonMain/kotlin/lupos/triple_store_manager/TripleStoreIndexDescriptionPartitionedByAll.kt:135"/*SOURCE_FILE_END*/ },
            { partitionCount > 1 },
        )
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
        for (i in 0 until partitionCount3) {
            val tmp = ((instance.tripleStoreManager!!) as TripleStoreManagerImpl).getNextHostAndKey(i)
            SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_manager/src/commonMain/kotlin/lupos/triple_store_manager/TripleStoreIndexDescriptionPartitionedByAll.kt:152"/*SOURCE_FILE_END*/ }, { hostnames[i] == "" })
            SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_manager/src/commonMain/kotlin/lupos/triple_store_manager/TripleStoreIndexDescriptionPartitionedByAll.kt:153"/*SOURCE_FILE_END*/ }, { keys[i] == "" })
            hostnames[i] = tmp.first
            keys[i] = tmp.second
        }
    }

    override fun getPartitionCount(params: Array<IOPBase>): Int {
        return partitionCount
    }

    override fun getDistributionCount(): Int {
        return partitionCount
    }

    override fun getAllLocations(): List<Pair<LuposHostname, LuposStoreKey>> {
        val res = mutableListOf<Pair<LuposHostname, LuposStoreKey>>()
        for (i in 0 until partitionCount3) {
            res.add(Pair(hostnames[i], keys[i]))
        }
        return res
    }

    override fun toXMLElement(): XMLElement {
        val res = super.toXMLElement()
        res.addAttribute("type", "TripleStoreIndexDescriptionPartitionedByAll")
        res.addAttribute("partitionCount", "$partitionCount")
        res.addAttribute("partitionCount3", "$partitionCount3")
        for (i in 0 until hostnames.size) {
            res.addAttribute("hostname$i", "${hostnames[i]}")
            res.addAttribute("key$i", "${keys[i]}")
        }
        return res
    }

    override fun requireSplitFromStore(): Boolean = false
    override fun requiresPartitioning(params: Array<IOPBase>): Map<String, Int> {
        return mapOf(fixedPartitionNameS to partitionCount, fixedPartitionNameO to partitionCount, fixedPartitionNameO to partitionCount)
    }
}
