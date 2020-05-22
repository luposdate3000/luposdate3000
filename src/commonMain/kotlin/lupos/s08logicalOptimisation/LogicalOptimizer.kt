package lupos.s08logicalOptimisation

import lupos.s00misc.Coverage
import lupos.s00misc.EOptimizerID
import lupos.s04logicalOperators.Query

class LogicalOptimizer(query: Query) : OptimizerCompoundBase(query, EOptimizerID.LogicalOptimizerID) {
    override val classname = "LogicalOptimizer"
    override val childrenOptimizers = arrayOf(//
            arrayOf<OptimizerBase>(
                    //assign prefix to all operators which require those
                    LogicalOptimizerRemovePrefix(query)//
            ),
            arrayOf<OptimizerBase>(
                    //split all filters containing AND as main operator
                    LogicalOptimizerFilterSplitAND(query)//
            ),
            arrayOf<OptimizerBase>(
                    //search for_ structures, which form the minus-operator
                    LogicalOptimizerDetectMinus(query)//
            ),
            arrayOf<OptimizerBase>(
                    LogicalOptimizerFilterDown(query)//
            ),
            arrayOf<OptimizerBase>(
                    LogicalOptimizerProjectionDown(query)//
            ),
            arrayOf<OptimizerBase>(
                    //remove all filters testing for_ equality by renaming one of the variables
                    LogicalOptimizerRemoveNOOP(query),// remove noops first, to be able to do a better choice
                    LogicalOptimizerFilterEQ(query)//
            ),
            arrayOf<OptimizerBase>(
                    //solve all arithmetic equations with only constants
                    LogicalOptimizerArithmetic(query)//
            ),
            arrayOf<OptimizerBase>(
                    LogicalOptimizerRemoveProjection(query),//
                    LogicalOptimizerRemoveNOOP(query),//
                    LogicalOptimizerDistinctUp(query),//
                    LogicalOptimizerOptional(query),//
                    LogicalOptimizerRemoveBindVariable(query),//
                    LogicalOptimizerBindToFilter(query)//
            ),
            arrayOf<OptimizerBase>(
                    LogicalOptimizerUnionUp(query),//
                    LogicalOptimizerProjectionDown(query)//
            ),
            arrayOf<OptimizerBase>(
                    //replace variables with constants, _if there are just a few in the store, afterwards eliminate constants
                    LogicalOptimizerStoreToValues(query),//
                    LogicalOptimizerBindUp(query),//
                    LogicalOptimizerArithmetic(query),//
                    LogicalOptimizerRemoveBindVariable(query),//
                    LogicalOptimizerBindToFilter(query),//
                    LogicalOptimizerFilterDown(query),//
                    LogicalOptimizerFilterIntoTriple(query),//
                    LogicalOptimizerRemoveNOOP(query)//
            ),
            arrayOf<OptimizerBase>(
                    LogicalOptimizerProjectionDown(query)//
            ),
            arrayOf<OptimizerBase>(
                    LogicalOptimizerRemoveNOOP(query)//
            ),
            arrayOf<OptimizerBase>(
                    //force as much as possible joins to be next to each other
                    LogicalOptimizerFilterUp(query)//
            ),
            arrayOf<OptimizerBase>(
                    //force as much as possible joins to be next to each other
                    LogicalOptimizerProjectionUp(query),//
            ),
            arrayOf<OptimizerBase>(
                    //calculate if_ only count or real data is required. this must happen directly before join order optimisation{
                    LogicalOptimizerExists(query)//
            ),
            arrayOf<OptimizerBase>(
//join order must stant alone otherwise there are lots of recalulations
                    LogicalOptimizerJoinOrder(query)//
            ),
            arrayOf<OptimizerBase>(
//put the filters between the joins
                    LogicalOptimizerFilterDown(query)//
            ),
            arrayOf<OptimizerBase>(
//merge consecutive filters into a single AND connected one
                    LogicalOptimizerFilterMergeAND(query)//
            ),
            arrayOf<OptimizerBase>(
//try to remove any unnecessary projection operator, never used columns
                    LogicalOptimizerProjectionDown(query),//
                    LogicalOptimizerRemoveProjection(query),//
                    LogicalOptimizerFilterIntoTriple(query),//
                    LogicalOptimizerRemoveBindVariable(query)//
            ),
            arrayOf<OptimizerBase>(
//calculate the natural sort order of the columns, as a prerequisite _for distinct calculation
                    LogicalOptimizerColumnSortOrder(query)//
            ),
            arrayOf<OptimizerBase>(
                    LogicalOptimizerMinusAddSort(query),//
                    LogicalOptimizerDistinctSplit(query),//
                    LogicalOptimizerSortDown(query),//
                    LogicalOptimizerReducedDown(query)//
            ),
            arrayOf<OptimizerBase>(
LogicalOptimizerProjectionDown(query)//
            ),
            arrayOf<OptimizerBase>(
                    LogicalOptimizerRemoveProjection(query)//
            ),
            arrayOf<OptimizerBase>(
//calculate the natural sort order of the columns, as a prerequisite _for physical optimisation, must be the last step here
                    LogicalOptimizerColumnSortOrder(query)//
            )
    )
}
