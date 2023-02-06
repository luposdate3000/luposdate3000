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
import lupos.operator.base.IPOPLimit
import lupos.operator.physical.POPBase
import lupos.shared.DictionaryValueHelper
import lupos.shared.EIndexPattern
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
    private var enforcePartition: Partition? = null
    private fun getEnforcedPartition(fallback: Partition): Partition {
        if (enforcePartition != null) {
            return enforcePartition!!
        } else {
            return fallback
        }
    }

    @JvmField
    public var hasSplitFromStore: Boolean = false
    public fun requireSplitFromStore(): Boolean = tripleStoreIndexDescription.requireSplitFromStore()
    public fun requiresPartitioning(): Map<String, Int> = tripleStoreIndexDescription.requiresPartitioning(children)
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

    override fun toLocalOperatorGraph(parent: Partition, onFoundLimit: (IPOPLimit) -> Unit, onFoundSort: () -> Unit): POPBase? {
        if (getDesiredHostnameFor(parent) == query.getInstance().LUPOS_PROCESS_URLS_ALL[query.getInstance().LUPOS_PROCESS_ID]) {
            val tmp = POPTripleStoreIterator(query, projectedVariables, tripleStoreIndexDescription, children)
            tmp.enforcePartition = parent
            return tmp
        } else {
            return null
        }
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
            if (tmp.size != 0) {
                for ((key, count) in tmp) {
                    partitionColumn = key
                    return count
                }
                return -1 // unreachable
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

    public fun getDesiredHostnameFor(parent: Partition): LuposHostname = getTarget(getEnforcedPartition(parent)).first
    public fun getTarget(parent: Partition): Pair<LuposHostname, LuposStoreKey> = tripleStoreIndexDescription.getStore(query, children, getEnforcedPartition(parent))
    override fun cloneOP(): IOPBase = POPTripleStoreIterator(query, projectedVariables, tripleStoreIndexDescription, children)
    override /*suspend*/ fun evaluate(parent: Partition): IteratorBundle = EvalTripleStoreIterator(
        getTarget(getEnforcedPartition(parent)),
        query,
        tripleStoreIndexDescription.idx_set[0],
        children.map { child ->
            if (child is IAOPConstant) {
                true to (child.getValue() to "")
            } else {
                child as IAOPVariable
                false to (DictionaryValueHelper.nullValue to child.getName())
            }
        }.toTypedArray()
    )

    override fun usesDictionary(): Boolean {
        return false
    }
}
