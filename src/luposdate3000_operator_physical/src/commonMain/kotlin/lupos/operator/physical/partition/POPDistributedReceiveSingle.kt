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
package lupos.operator.physical.partition

import lupos.shared.DictionaryValueHelper
import lupos.shared.DictionaryValueTypeArray
import lupos.shared.EOperatorIDExt
import lupos.shared.ESortPriorityExt
import lupos.shared.IMyInputStream
import lupos.shared.IMyOutputStream
import lupos.shared.IQuery
import lupos.shared.Partition
import lupos.shared.SanityCheck
import lupos.shared.XMLElement
import lupos.shared.operator.IOPBase
import lupos.shared.operator.iterator.IteratorBundle
import lupos.shared.operator.iterator.RowIterator
import kotlin.jvm.JvmField

// http://blog.pronghorn.tech/optimizing-suspending-functions-in-kotlin/
public class POPDistributedReceiveSingle public constructor(
    query: IQuery,
    projectedVariables: List<String>,
    @JvmField public var partitionID: Int,
    child: IOPBase,
    private val input: IMyInputStream,
    private val output: IMyOutputStream? = null,
    private val hosts: Pair<Int, String>,
) : APOPDistributed(
    query,
    projectedVariables,
    EOperatorIDExt.POPDistributedReceiveSingleID,
    "POPDistributedReceiveSingle",
    arrayOf(child),
    ESortPriorityExt.PREVENT_ANY,
) {
    public companion object {
        internal fun toXMLElementInternal(partitionID: Int, partial: Boolean, isRoot: Boolean, hosts: Pair<Int, String>,) = toXMLElementHelper5("POPDistributedReceiveSingle", partitionID, partial, true, hosts)
        public operator fun invoke(
            query: IQuery,
            projectedVariables: List<String>,
            partitionID: Int,
            child: IOPBase,
            hosts: Pair<Int, String>,
        ): POPDistributedReceiveSingle {
            val handler = query.getInstance().communicationHandler!!
            val conn = handler.openConnection(hosts.second, "/distributed/query/execute", mapOf("key" to "${hosts.first}", "dictionaryURL" to query.getDictionaryUrl()!!), query.getTransactionID().toInt())
            return POPDistributedReceiveSingle(query, projectedVariables, partitionID, child, conn.first, conn.second, hosts)
        }
    }
    init {
        SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_physical/src/commonMain/kotlin/lupos/operator/physical/partition/POPDistributedReceiveSingle.kt:65"/*SOURCE_FILE_END*/ }, { projectedVariables.isNotEmpty() })
    }

    override fun getPartitionCount(variable: String): Int = 1
    override /*suspend*/ fun toXMLElementRoot(partial: Boolean, partition: Map<String, Int>): XMLElement = toXMLElementHelperAddBase(partition, partial, true, toXMLElementInternal(partitionID, partial, true, hosts))
    override /*suspend*/ fun toXMLElement(partial: Boolean, partition: Map<String, Int>): XMLElement = toXMLElementHelperAddBase(partition, partial, false, toXMLElementInternal(partitionID, partial, false, hosts))
    override fun cloneOP(): IOPBase = POPDistributedReceiveSingle(query, projectedVariables, partitionID, children[0].cloneOP(), input, output, hosts)
    override fun equals(other: Any?): Boolean = other is POPDistributedReceiveSingle && children[0] == other.children[0]

    override /*suspend*/ fun evaluate(parent: Partition): IteratorBundle {
        val variables = mutableListOf<String>()
        variables.addAll(projectedVariables)
        val mapping = IntArray(variables.size)
        val iterator = RowIterator()
        iterator.columns = variables.toTypedArray()
        iterator.buf = DictionaryValueTypeArray(variables.size)
        val cnt = input.readInt()
        SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_physical/src/commonMain/kotlin/lupos/operator/physical/partition/POPDistributedReceiveSingle.kt:82"/*SOURCE_FILE_END*/ }, { cnt == variables.size }, { "$cnt vs ${variables.size}" })
        for (i in 0 until variables.size) {
            val len = input.readInt()
            val buf = ByteArray(len)
            input.read(buf, len)
            val name = buf.decodeToString()
            val j = variables.indexOf(name)
            SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_physical/src/commonMain/kotlin/lupos/operator/physical/partition/POPDistributedReceiveSingle.kt:89"/*SOURCE_FILE_END*/ }, { j >= 0 && j < variables.size }, { "$j ${variables.size} $variables $name" })
            mapping[i] = j
        }
        var closed = false
        iterator.next = {
            var res = -1
            if (!closed) {
                for (i in 0 until variables.size) {
                    iterator.buf[mapping[i]] = input.readDictionaryValueType()
                }
                if (iterator.buf[0] == DictionaryValueHelper.nullValue) {
                    input.close()
                    output?.close()
                    closed = true
                } else {
                    res = 0
                }
            }
            res
        }
        iterator.close = {
            input.close()
            output?.close()
        }
        return IteratorBundle(iterator)
    }
}
