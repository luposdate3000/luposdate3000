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

import lupos.s00misc.EIndexPatternHelper
import lupos.s00misc.EIndexPattern
import lupos.s00misc.EOperatorIDExt
import lupos.s00misc.ESortPriorityExt
import lupos.s00misc.Partition
import lupos.s00misc.SanityCheck
import lupos.s00misc.XMLElement
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04arithmetikOperators.noinput.IAOPConstant
import lupos.s04arithmetikOperators.noinput.IAOPVariable
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.IQuery
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s09physicalOperators.POPBase
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
    override fun getRequiredVariableNames(): List<String> = listOf()
    override fun getProvidedVariableNames(): List<String> {
        var res = mutableListOf<String>()
        for (c in children) {
            if (c is AOPVariable && c.name != "_") {
                res.add(c.name)
            }
        }
        return res
    }

    override /*suspend*/ fun toXMLElement(partial: Boolean): XMLElement {
        var res = super.toXMLElement(partial)
        res.addContent(XMLElement("sparam").addContent(children[0].toXMLElement(partial)))
        res.addContent(XMLElement("pparam").addContent(children[1].toXMLElement(partial)))
        res.addContent(XMLElement("oparam").addContent(children[2].toXMLElement(partial)))
        res.addContent(XMLElement("idx").addContent(tripleStoreIndexDescription.toXMLElement()))
        return res
// tripleStoreManager.getIndexFromXML(node["idx"])
    }

    public fun getIndexPattern(): EIndexPattern {
        return tripleStoreIndexDescription.idx_set[0]
    }

    override fun childrenToVerifyCount(): Int = 0
    public fun changeToIndexWithMaximumPartitions(max_partitions: Int?, column: String): Int {
        var partition_column = -1
        for (i in 0 until 3) {
            val c = children[i]
            if (c is AOPVariable && c.name == column) {
                partition_column = EIndexPatternHelper.tripleIndicees[tripleStoreIndexDescription.idx_set[0]][i]
                break
            }
        }
        if (partition_column > -1) {
            tripleStoreIndexDescription = tripleStoreIndexDescription.getIndexWithMaximumPartitions(max_partitions, partition_column)
            val count = tripleStoreIndexDescription.getPartitionCount()
            partitionColumn = column
            return count
        } else {
            throw Exception("no matching index found")
        }
    }

    override fun getPartitionCount(variable: String): Int {
        var count = tripleStoreIndexDescription.getPartitionCount()
        if (count > 1) {
            SanityCheck.check { (tripleStoreIndexDescription as TripleStoreIndexDescriptionPartitionedByID).partitionCount == count }
            for (i in 0 until 3) {
                val c = children[i]
                if (c is AOPVariable && c.name == variable) {
                    val currentindex = tripleStoreIndexDescription
                    if (currentindex is TripleStoreIndexDescriptionPartitionedByID &&
                        currentindex.partitionColumn == EIndexPatternHelper.tripleIndicees[currentindex.idx_set[0]][i]
                    ) {
                        return count
                    }
                    break
                }
            }
        }
        return 1
    }

    public fun getDesiredHostnameFor(partition: Partition): LuposHostname {
        val index = tripleStoreIndexDescription as TripleStoreIndexDescription
        val target = index.getStore(query, children, partition)
        return target.first
    }

    public override fun cloneOP(): IOPBase = POPTripleStoreIterator(query, projectedVariables, tripleStoreIndexDescription, children)
    override /*suspend*/ fun evaluate(parent: Partition): IteratorBundle {
        val index = tripleStoreIndexDescription as TripleStoreIndexDescription
        val target = index.getStore(query, children, parent)
        val manager = tripleStoreManager as TripleStoreManagerImpl
        SanityCheck.check { target.first == manager.localhost }
        val store = manager.localStoresGet()[target.second]!!
        val filter2 = mutableListOf<Int>()
        val projection = mutableListOf<String>()
        for (ii in 0 until 3) {
            val i = EIndexPatternHelper.tripleIndicees[index.idx_set[0]][ii]
            when (val param = children[i]) {
                is IAOPConstant -> {
                    SanityCheck.check { filter2.size == ii }
                    filter2.add(query.getDictionary().valueToGlobal(param.getValue()))
                }
                is IAOPVariable -> {
                    projection.add(param.getName())
                }
                else -> {
                    SanityCheck.checkUnreachable()
                }
            }
        }
        val filter = IntArray(filter2.size) { filter2[it] }
        return store.getIterator(query, filter, projection)
    }
}
