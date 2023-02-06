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
public class POPDistributedSendSingle public constructor(
    query: IQuery,
    projectedVariables: List<String>,
    @JvmField public var partitionID: Int,
    child: IOPBase,
    @JvmField public val keys: Int, // key
    @JvmField public val partitionedBy: MutableMap<String, Int>, // variable -> partition
) : APOPDistributed(
    query,
    projectedVariables,
    EOperatorIDExt.POPDistributedSendSingleID,
    "POPDistributedSendSingle",
    arrayOf(child),
    ESortPriorityExt.PREVENT_ANY,
) {
    init {
        if (SanityCheck.enabled) { if (!(projectedVariables.isNotEmpty())) { throw Exception("SanityCheck failed") } }
    }

    public companion object {
        internal fun toXMLElementInternal(partitionID: Int, partial: Boolean, isRoot: Boolean, keys: Int, partitionedBy: MutableMap<String, Int>) = toXMLElementHelper9("POPDistributedSendSingle", partitionID, partial, true, keys, partitionedBy)
    }

    override fun cloneOP(): IOPBase = POPDistributedSendSingle(query, projectedVariables, partitionID, children[0].cloneOP(), keys, partitionedBy)
    override fun equals(other: Any?): Boolean = other is POPDistributedSendSingle && children[0] == other.children[0]
    override /*suspend*/ fun evaluate(parent: Partition): IteratorBundle = throw UnreachableException()
    override fun getPartitionCount(variable: String): Int = TODO()
    override /*suspend*/ fun toXMLElementRoot(partial: Boolean, partition: PartitionHelper): XMLElement = toXMLElementHelperAddBase(partition, partial, true, toXMLElementInternal(partitionID, partial, true, keys, partitionedBy))
    override /*suspend*/ fun toXMLElement(partial: Boolean, partition: PartitionHelper): XMLElement = toXMLElementHelperAddBase(partition, partial, false, toXMLElementInternal(partitionID, partial, false, keys, partitionedBy))
    override fun toLocalOperatorGraph(parent: Partition, onFoundLimit: (IPOPLimit) -> Unit, onFoundSort: () -> Unit): POPBase? = TODO()
}
