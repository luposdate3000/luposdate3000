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

package lupos.s10physicalOptimisation
import lupos.s00misc.DontCareWhichException
import lupos.s00misc.EOptimizerIDExt
import lupos.s00misc.Partition
import lupos.s00misc.TripleStoreLocal
import lupos.s00misc.USE_PARTITIONS
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.Query
import lupos.s05tripleStore.TripleStoreFeatureExt
import lupos.s05tripleStore.TripleStoreFeatureParamsPartition
import lupos.s08logicalOptimisation.OptimizerBase
import lupos.s09physicalOperators.partition.POPSplitPartition
import lupos.s09physicalOperators.partition.POPSplitPartitionFromStore
import lupos.s09physicalOperators.singleinput.POPFilter
import lupos.s09physicalOperators.singleinput.POPProjection
import lupos.s09physicalOperators.singleinput.modifiers.POPReduced
import lupos.s15tripleStoreDistributed.TripleStoreIteratorGlobal
public class PhysicalOptimizerPartition1(query: Query) : OptimizerBase(query, EOptimizerIDExt.PhysicalOptimizerPartition1ID) {
    override val classname: String = "PhysicalOptimizerPartition1"
    override /*suspend*/ fun optimize(node: IOPBase, parent: IOPBase?, onChange: () -> Unit): IOPBase {
        var res = node
        if (USE_PARTITIONS && Partition.default_k > 1) {
            when (node) {
                is POPSplitPartition -> {
// splitting must always split all variables provided by its direct children - if there is a different children, adapt the variables
                    when (val c = node.children[0]) {
                        is POPReduced -> {
                            res = POPReduced(query, c.projectedVariables, POPSplitPartition(query, c.children[0].getProvidedVariableNames(), node.partitionVariable, node.partitionCount, node.partitionID, c.children[0]))
                            query.removePartitionOperator(node.getUUID(), node.partitionID)
                            query.addPartitionOperator(res.children[0].getUUID(), node.partitionID)
                            onChange()
                        }
                        is POPProjection -> {
                            res = POPProjection(query, c.projectedVariables, POPSplitPartition(query, c.children[0].getProvidedVariableNames(), node.partitionVariable, node.partitionCount, node.partitionID, c.children[0]))
                            query.removePartitionOperator(node.getUUID(), node.partitionID)
                            query.addPartitionOperator(res.children[0].getUUID(), node.partitionID)
                            onChange()
                        }
                        is POPFilter -> {
                            res = POPFilter(query, c.projectedVariables, c.children[1] as AOPBase, POPSplitPartition(query, c.children[0].getProvidedVariableNames(), node.partitionVariable, node.partitionCount, node.partitionID, c.children[0]))
                            query.removePartitionOperator(node.getUUID(), node.partitionID)
                            query.addPartitionOperator(res.children[0].getUUID(), node.partitionID)
                            onChange()
                        }
                        is TripleStoreIteratorGlobal -> {
                            if (TripleStoreLocal.providesFeature(TripleStoreFeatureExt.PARTITION, null)) {
                                try {
                                    val p = Partition(Partition(), node.partitionVariable, 0, node.partitionCount)
                                    val params = TripleStoreFeatureParamsPartition(c.idx, Array(3) { c.children[it] as AOPBase }, p)
                                    if (params.getColumn() > 0 && TripleStoreLocal.providesFeature(TripleStoreFeatureExt.PARTITION, params)) {
                                        res = POPSplitPartitionFromStore(query, node.projectedVariables, node.partitionVariable, node.partitionCount, node.partitionID, c)
                                        c.partition.limit[node.partitionVariable] = node.partitionCount
                                        query.removePartitionOperator(node.getUUID(), node.partitionID)
                                        query.addPartitionOperator(res.getUUID(), node.partitionID)
                                        onChange()
                                    }
                                } catch (e: DontCareWhichException) {
                                    e.printStackTrace()
                                }
                            }
                        }
                    }
                }
            }
        }
        return res
    }

    override fun optimizeCallRico(node: IOPBase, onChange: () -> Unit): MutableList<IOPBase> {
        TODO("Not yet implemented")
    }
}
