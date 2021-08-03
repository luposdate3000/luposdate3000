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
import lupos.shared.EIndexPatternHelper
import lupos.shared.EOperatorIDExt
import lupos.shared.ESortPriorityExt
import lupos.shared.IQuery
import lupos.shared.LuposHostname
import lupos.shared.Partition
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

    override /*suspend*/ fun toXMLElement(partial: Boolean): XMLElement {
        val res = super.toXMLElement(partial)
        res.addContent(XMLElement("sparam").addContent(children[0].toXMLElement(partial)))
        res.addContent(XMLElement("pparam").addContent(children[1].toXMLElement(partial)))
        res.addContent(XMLElement("oparam").addContent(children[2].toXMLElement(partial)))
        res.addContent(XMLElement("idx").addContent(tripleStoreIndexDescription.toXMLElement()))
        return res
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
        val count = tripleStoreIndexDescription.getPartitionCount()
        if (count > 1) {
            SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_manager/src/commonMain/kotlin/lupos/triple_store_manager/POPTripleStoreIterator.kt:103"/*SOURCE_FILE_END*/ }, { (tripleStoreIndexDescription as TripleStoreIndexDescriptionPartitionedByID).partitionCount == count })
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
        val index = tripleStoreIndexDescription
        val target = index.getStore(query, children, partition)
        return target.first
    }

    public override fun cloneOP(): IOPBase = POPTripleStoreIterator(query, projectedVariables, tripleStoreIndexDescription, children)
    override /*suspend*/ fun evaluate(parent: Partition): IteratorBundle {
        val index = tripleStoreIndexDescription
        val target = index.getStore(query, children, parent)
        val manager = (query.getInstance().tripleStoreManager) as TripleStoreManagerImpl
        SanityCheck.check(
            { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_manager/src/commonMain/kotlin/lupos/triple_store_manager/POPTripleStoreIterator.kt:132"/*SOURCE_FILE_END*/ },
            { target.first == manager.localhost },
        )
        val store = manager.localStoresGet()[target.second]!!
        val filter2 = mutableListOf<DictionaryValueType>()
        val projection = mutableListOf<String>()
        for (ii in 0 until 3) {
            val i = EIndexPatternHelper.tripleIndicees[index.idx_set[0]][ii]
            when (val param = children[i]) {
                is IAOPConstant -> {
                    SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_manager/src/commonMain/kotlin/lupos/triple_store_manager/POPTripleStoreIterator.kt:142"/*SOURCE_FILE_END*/ }, { filter2.size == ii })
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
    public open override fun usesDictionary(): Boolean {
        return false
    }
}
