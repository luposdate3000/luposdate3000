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

import lupos.shared.EOperatorIDExt
import lupos.shared.ESortPriorityExt
import lupos.shared.IMyInputStream
import lupos.shared.IMyOutputStream
import lupos.shared.IQuery
import lupos.shared.Partition
import lupos.shared.XMLElement
import lupos.shared.operator.IOPBase
import lupos.shared.operator.iterator.IteratorBundle
import kotlin.jvm.JvmField

// http://blog.pronghorn.tech/optimizing-suspending-functions-in-kotlin/
public class POPDistributedReceiveSingleCount public constructor(
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
    EOperatorIDExt.POPDistributedReceiveSingleCountID,
    "POPDistributedReceiveSingleCount",
    arrayOf(child),
    ESortPriorityExt.PREVENT_ANY,
) {
    public companion object {
internal fun toXMLElementInternal(partitionID: Int,partial: Boolean, isRoot:Boolean, hosts: Pair<Int, String>,)=toXMLElementHelper5( "POPDistributedReceiveSingleCount",partitionID, partial, isRoot, hosts)
        public operator fun invoke(
            query: IQuery,
            projectedVariables: List<String>,
            partitionID: Int,
            child: IOPBase,
            hosts: Pair<Int, String>,
        ): POPDistributedReceiveSingleCount {
            val handler = query.getInstance().communicationHandler!!
            val conn = handler.openConnection(hosts.second, "/distributed/query/execute", mapOf("key" to "${hosts.first}", "dictionaryURL" to query.getDictionaryUrl()!!), query.getTransactionID().toInt())
            return POPDistributedReceiveSingleCount(query, projectedVariables, partitionID, child, conn.first, conn.second, hosts)
        }
    }
    override fun getPartitionCount(variable: String): Int = 1
    override /*suspend*/ fun toXMLElementRoot(partial: Boolean, partition: Int): XMLElement = toXMLElementHelperAddBase(partial,true,toXMLElementInternal(partitionID, partial, true, hosts))
    override /*suspend*/ fun toXMLElement(partial: Boolean): XMLElement = toXMLElementHelperAddBase(partial,false,toXMLElementInternal(partitionID, partial, false, hosts))
    override fun cloneOP(): IOPBase = POPDistributedReceiveSingleCount(query, projectedVariables, partitionID, children[0].cloneOP(), input, output, hosts)
    override fun equals(other: Any?): Boolean = other is POPDistributedReceiveSingleCount && children[0] == other.children[0]

    override /*suspend*/ fun evaluate(parent: Partition): IteratorBundle {
        var count = input.readInt()
        input.close()
        output?.close()
        return IteratorBundle(count)
    }
}
