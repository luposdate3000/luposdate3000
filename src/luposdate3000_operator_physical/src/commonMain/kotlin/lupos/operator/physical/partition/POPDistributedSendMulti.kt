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

import lupos.operator.base.IPOPLimit
import lupos.operator.physical.POPBase
import lupos.shared.EOperatorIDExt
import lupos.shared.ESortPriorityExt
import lupos.shared.IMyOutputStream
import lupos.shared.IQuery
import lupos.shared.Partition
import lupos.shared.PartitionHelper
import lupos.shared.SanityCheck
import lupos.shared.UnreachableException
import lupos.shared.XMLElement
import lupos.shared.operator.IOPBase
import lupos.shared.operator.iterator.IteratorBundle
import kotlin.jvm.JvmField

// http://blog.pronghorn.tech/optimizing-suspending-functions-in-kotlin/
public class POPDistributedSendMulti public constructor(
    query: IQuery,
    projectedVariables: List<String>,
    @JvmField public var partitionID: Int,
    child: IOPBase,
    @JvmField public val keys: List<Int>, // key
    @JvmField public val partitionedBy: MutableMap<String, Int>, // variable -> partition
    @JvmField public val partitionVariable: String,
    @JvmField public val partitionCount: Int,
) : APOPDistributed(
    query,
    projectedVariables,
    EOperatorIDExt.POPDistributedSendMultiID,
    "POPDistributedSendMulti",
    arrayOf(child),
    ESortPriorityExt.PREVENT_ANY,
) {
    init {
        SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_physical/src/commonMain/kotlin/lupos/operator/physical/partition/POPDistributedSendMulti.kt:52"/*SOURCE_FILE_END*/ }, { projectedVariables.isNotEmpty() })
    }

    public companion object {
        internal fun toXMLElementInternal(partitionID: Int, partial: Boolean, isRoot: Boolean, keys: List<Int>, partitionedBy: MutableMap<String, Int>, partitionVariable: String, partitionCount: Int) = toXMLElementHelper8("POPDistributedSendMulti", partitionID, partial, true, keys, partitionedBy, partitionVariable, partitionCount)
    }

    override fun getPartitionCount(variable: String): Int = TODO()
    override /*suspend*/ fun toXMLElementRoot(partial: Boolean, partition: PartitionHelper): XMLElement = toXMLElementHelperAddBase(partition, partial, true, toXMLElementInternal(partitionID, partial, true, keys, partitionedBy, partitionVariable, partitionCount))
    override /*suspend*/ fun toXMLElement(partial: Boolean, partition: PartitionHelper): XMLElement = toXMLElementHelperAddBase(partition, partial, false, toXMLElementInternal(partitionID, partial, false, keys, partitionedBy, partitionVariable, partitionCount))
    override fun cloneOP(): IOPBase = POPDistributedSendMulti(query, projectedVariables, partitionID, children[0].cloneOP(), keys, partitionedBy, partitionVariable, partitionCount)
    override fun equals(other: Any?): Boolean = other is POPDistributedSendMulti && children[0] == other.children[0]
    override /*suspend*/ fun evaluate(parent: Partition): IteratorBundle = throw UnreachableException()
    public fun evaluate(data: Array<IMyOutputStream?>) {
        var partition = Partition()
        for ((k, v) in partitionedBy) {
            partition = Partition(partition, k, v, -1)
        }
        EvalDistributedSendMulti(data, children[0].evaluate(partition), partitionVariable)
    }

    override fun toLocalOperatorGraph(parent: Partition, onFoundLimit: (IPOPLimit) -> Unit, onFoundSort: () -> Unit): POPBase? = TODO()
}
