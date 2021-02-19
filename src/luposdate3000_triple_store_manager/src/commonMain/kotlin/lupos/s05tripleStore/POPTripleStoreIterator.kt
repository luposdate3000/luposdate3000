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
import lupos.s00misc.EOperatorIDExt
import lupos.s00misc.ESortPriorityExt
import lupos.s00misc.SanityCheck
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.IQuery
import lupos.s09physicalOperators.POPBase
import kotlin.jvm.JvmField

public class POPTripleStoreIterator(
    query: IQuery,
    projectedVariables: List<String>,
    @JvmField internal var tripleStoreIndexDescription: ITripleStoreIndexDescription,
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
    public fun changeToIndexWithMaximumPartitions(max_partitions: Int?, column: String): Int {
        var partition_column = -1
        for (i in 0 until 3) {
            val c = children[i]
            if (c is AOPVariable && c.name == column) {
                partition_column = EIndexPatternHelper.tripleIndicees[(tripleStoreIndexDescription as TripleStoreIndexDescription).idx][i]
                break
            }
        }
        if (partition_column > -1) {
            tripleStoreIndexDescription = (tripleStoreIndexDescription as TripleStoreIndexDescription).getIndexWithMaximumPartitions(max_partitions, partition_column)
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
                        currentindex.partitionColumn == EIndexPatternHelper.tripleIndicees[currentindex.idx][i]
                    ) {
                        return count
                    }
                    break
                }
            }
        }
        return 1
    }

    public override fun cloneOP(): IOPBase = POPTripleStoreIterator(query, projectedVariables, tripleStoreIndexDescription, children)
}
