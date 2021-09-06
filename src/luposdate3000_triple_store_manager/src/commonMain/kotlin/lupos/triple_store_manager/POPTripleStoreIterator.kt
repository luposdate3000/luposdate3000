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

import lupos.operator.arithmetik.noinput.AOPVariable
import lupos.operator.physical.POPBase
import lupos.shared.DictionaryValueHelper
import lupos.shared.DictionaryValueType
import lupos.shared.DictionaryValueTypeArray
import lupos.shared.EIndexPattern
import lupos.shared.EIndexPatternExt
import lupos.shared.EIndexPatternHelper
import lupos.shared.EOperatorIDExt
import lupos.shared.ESortPriorityExt
import lupos.shared.IQuery
import lupos.shared.LuposHostname
import lupos.shared.LuposStoreKey
import lupos.shared.Partition
import lupos.shared.PartitionHelper
import lupos.shared.SanityCheck
import lupos.shared.XMLElement
import lupos.shared.operator.IOPBase
import lupos.shared.operator.iterator.IteratorBundle
import lupos.shared.operator.noinput.IAOPConstant
import lupos.shared.operator.noinput.IAOPVariable
import kotlin.jvm.JvmField

public class POPTripleStoreIterator(
    query: IQuery,
    projectedVariables: List<String>,
    @JvmField internal var tripleStoreIndexDescription: TripleStoreIndexDescription,
    children: Array<IOPBase>,
) : POPBase(
    query,
    projectedVariables,
    EOperatorIDExt.POPTripleStoreIterator,
    "POPTripleStoreIterator",
    children,
    ESortPriorityExt.ANY_PROVIDED_VARIABLE
) {
    @JvmField
    public var partitionColumn: String? = null

    @JvmField
    public var hasSplitFromStore: Boolean = false
    public fun requireSplitFromStore(): Boolean = tripleStoreIndexDescription.requireSplitFromStore()
    public fun requiresPartitioning(): Pair<String, Int>? = tripleStoreIndexDescription.requiresPartitioning(children)
    override fun getRequiredVariableNames(): List<String> = listOf()
    override fun getProvidedVariableNames(): List<String> {
        val res = mutableListOf<String>()
        for (c in children) {
            if (c is AOPVariable && c.name != "_") {
                res.add(c.name)
            }
        }
        return res
    }

    override /*suspend*/ fun toXMLElement(partial: Boolean, partition: PartitionHelper): XMLElement {
        val res = super.toXMLElement(partial, partition)
        res.addAttribute("hasSplitFromStore", "$hasSplitFromStore")
        res.addContent(XMLElement("sparam").addContent(children[0].toXMLElement(partial, partition)))
        res.addContent(XMLElement("pparam").addContent(children[1].toXMLElement(partial, partition)))
        res.addContent(XMLElement("oparam").addContent(children[2].toXMLElement(partial, partition)))
        res.addContent(XMLElement("idx").addContent(tripleStoreIndexDescription.toXMLElement()))
        try {
            val target = getTarget(Partition())
            res.addAttribute("targetHost", target.first)
            res.addAttribute("targetKey", target.second)
        } catch (e: Throwable) {
// ignore it
        }
        return res
    }

    public fun getIndexPattern(): EIndexPattern {
        return tripleStoreIndexDescription.idx_set[0]
    }

    override fun childrenToVerifyCount(): Int = 0
    public fun changeToIndexWithMaximumPartitions(max_partitions: Int?, column: String): Int {
        if (column.startsWith("?")) {
            val tmp = requiresPartitioning()
            if (tmp != null) {
                partitionColumn = tmp.first
                return tmp.second
            } else {
                return 1
            }
        } else {
            var partition_column = -1
            for (i in 0 until 3) {
                val c = children[i]
                if (c is AOPVariable && c.name == column) {
                    partition_column = EIndexPatternHelper.tripleIndiceesInverse[tripleStoreIndexDescription.idx_set[0]][i]
                    break
                }
            }
            if (partition_column > -1) {
                val tmp2 = tripleStoreIndexDescription.getIndexWithMaximumPartitions(max_partitions, partition_column, children)
                if (tmp2 == null) {
                    return -1
                }
                tripleStoreIndexDescription = tmp2
                val count = tripleStoreIndexDescription.getPartitionCount(children)
                partitionColumn = column
                return count
            } else {
                return -2
            }
        }
    }

    override fun getPartitionCount(variable: String): Int {
        if (variable.startsWith("?")) {
            return tripleStoreIndexDescription.getPartitionCount(children)
        } else {
            val count = tripleStoreIndexDescription.getPartitionCount(children)
            if (count > 1) {
                SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_manager/src/commonMain/kotlin/lupos/triple_store_manager/POPTripleStoreIterator.kt:138"/*SOURCE_FILE_END*/ }, { (tripleStoreIndexDescription as TripleStoreIndexDescriptionPartitionedByID).partitionCount == count })
                for (i in 0 until 3) {
                    val c = children[i]
                    if (c is AOPVariable && c.name == variable) {
                        val currentindex = tripleStoreIndexDescription
                        if (currentindex is TripleStoreIndexDescriptionPartitionedByID &&
                            currentindex.partitionColumn == EIndexPatternHelper.tripleIndiceesInverse[currentindex.idx_set[0]][i]
                        ) {
                            return count
                        }
                        break
                    }
                }
            }
            return 1
        }
    }

    public fun getDesiredHostnameFor(parent: Partition): LuposHostname = getTarget(parent).first
    public fun getTarget(parent: Partition): Pair<LuposHostname, LuposStoreKey> = tripleStoreIndexDescription.getStore(query, children, parent)
    public override fun cloneOP(): IOPBase = POPTripleStoreIterator(query, projectedVariables, tripleStoreIndexDescription, children)
    override /*suspend*/ fun evaluate(parent: Partition): IteratorBundle {
        val index = tripleStoreIndexDescription
        val target = getTarget(parent)
        val manager = (query.getInstance().tripleStoreManager) as TripleStoreManagerImpl
        SanityCheck.check(
            { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_manager/src/commonMain/kotlin/lupos/triple_store_manager/POPTripleStoreIterator.kt:164"/*SOURCE_FILE_END*/ },
            { target.first == manager.localhost },
            { "${target.first} ${manager.localhost } ${parent.data} ${parent.limit}" }
        )
        val store = manager.localStoresGet()[target.second]!!
        val filter2 = mutableListOf<DictionaryValueType>()
        val projection = mutableListOf<String>()
        for (ii in 0 until 3) {
            val i = EIndexPatternHelper.tripleIndicees[index.idx_set[0]][ii]
            when (val param = children[i]) {
                is IAOPConstant -> {
                    SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_manager/src/commonMain/kotlin/lupos/triple_store_manager/POPTripleStoreIterator.kt:175"/*SOURCE_FILE_END*/ }, { filter2.size == ii })
                    val v = param.getValue()
                    if (query.getDictionary().isLocalValue(v)) {
                        filter2.add(DictionaryValueHelper.nullValue)
                    } else {
                        filter2.add(v)
                    }
                }
                is IAOPVariable -> {
                    projection.add(param.getName())
                }
                else -> {
                    SanityCheck.checkUnreachable()
                }
            }
        }
        val filter = DictionaryValueTypeArray(filter2.size) { filter2[it] }
        return store.getIterator(query, filter, projection)
    }
    public override fun usesDictionary(): Boolean {
        return false
    }
}
