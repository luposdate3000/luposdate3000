package lupos.s08logicalOptimisation

import lupos.s00misc.EOptimizerID
import lupos.s04logicalOperators.Query

class LogicalOptimizer(query: Query) : OptimizerCompoundBase(query, EOptimizerID.LogicalOptimizerID) {
    override val classname: String = "LogicalOptimizer"
    override val childrenOptimizers: Array<Array<OptimizerBase>> = arrayOf( //
        arrayOf(
            // assign prefix to all operators which require those
            LogicalOptimizerRemovePrefix(query) //
        ),
        arrayOf(
            LogicalOptimizerFilterSplitAND(query), //
            LogicalOptimizerFilterSplitOR(query) //
        ),
        arrayOf(
            // search for_ structures, which form the minus-operator
            LogicalOptimizerDetectMinus(query) //
        ),
        arrayOf(
            LogicalOptimizerFilterOptional(query), // deal with all optionals wich do are not another form of minus-operator
            LogicalOptimizerFilterOptionalStep2(query) // this needs to execute immediately after LogicalOptimizerFilterOptional, and is used to set a single flag that it is finished
        ),
        arrayOf(
            LogicalOptimizerFilterDown(query) //
        ),
        arrayOf(
            LogicalOptimizerProjectionDown(query) //
        ),
        arrayOf(
            LogicalOptimizerDetectMinusStep2(query) //
        ),
        arrayOf(
            // remove all filters testing for_ equality by renaming one of the variables
            LogicalOptimizerRemoveNOOP(query), // remove noops first, to be able to do a better choice
            LogicalOptimizerFilterEQ(query) //
        ),
        arrayOf(
            // solve all arithmetic equations with only constants
            LogicalOptimizerArithmetic(query) //
        ),
        arrayOf(
            LogicalOptimizerRemoveProjection(query), //
            LogicalOptimizerRemoveNOOP(query), //
            LogicalOptimizerDistinctUp(query), //
            LogicalOptimizerOptional(query), //
            LogicalOptimizerRemoveBindVariable(query), //
            LogicalOptimizerBindToFilter(query) //
        ),
        arrayOf(
            LogicalOptimizerUnionUp(query), //
            LogicalOptimizerProjectionDown(query) //
        ),
        arrayOf(
            // replace variables with constants, _if there are just a few in the store, afterwards eliminate constants
            LogicalOptimizerStoreToValues(query), //
            LogicalOptimizerBindUp(query), //
            LogicalOptimizerArithmetic(query), //
            LogicalOptimizerRemoveBindVariable(query), //
            LogicalOptimizerBindToFilter(query), //
            LogicalOptimizerFilterDown(query), //
            LogicalOptimizerFilterIntoTriple(query), //
            LogicalOptimizerRemoveNOOP(query) //
        ),
        arrayOf(
            LogicalOptimizerProjectionDown(query) //
        ),
        arrayOf(
            LogicalOptimizerRemoveNOOP(query) //
        ),
        arrayOf(
            // force as much as possible joins to be next to each other
            LogicalOptimizerFilterUp(query) //
        ),
        arrayOf(
            // force as much as possible joins to be next to each other
            LogicalOptimizerProjectionUp(query), //
        ),
        arrayOf(
            // calculate if_ only count or real data is required. this must happen directly before join order optimisation{
            LogicalOptimizerExists(query) //
        ),
        arrayOf(
// join order must stant alone otherwise there are lots of recalulations
            LogicalOptimizerJoinOrder(query) //
        ),
        arrayOf(
// put the filters between the joins
            LogicalOptimizerFilterDown(query) //
        ),
        arrayOf(
// merge consecutive filters into a single AND connected one
            LogicalOptimizerFilterMergeAND(query) //
        ),
        arrayOf(
// try to remove any unnecessary projection operator, never used columns
            LogicalOptimizerProjectionDown(query), //
            LogicalOptimizerRemoveProjection(query), //
            LogicalOptimizerFilterIntoTriple(query), //
            LogicalOptimizerRemoveBindVariable(query) //
        ),
        arrayOf(
            LogicalOptimizerMinusAddSort(query), //
            LogicalOptimizerDistinctSplit(query) //
        ),
        arrayOf(
            LogicalOptimizerSortDown(query) //
        ),
        arrayOf(
            LogicalOptimizerReducedDown(query) //
        ),
        arrayOf(
            LogicalOptimizerProjectionDown(query) // this may reduce the projected variables inside both minus-operator-childs ... afterwards pull down again
        ),
        arrayOf(
            LogicalOptimizerRemoveProjection(query) //
        ),
        arrayOf(
            LogicalOptimizerReducedDown(query) //
        ),
        arrayOf(
            LogicalOptimizerProjectionDown(query) //
        ),
        arrayOf(
            LogicalOptimizerRemoveProjection(query) //
        ),
        arrayOf(
// calculate the natural sort order of the columns, as a prerequisite _for physical optimisation, must be the last step here
            LogicalOptimizerColumnSortOrder(query) //
        )
    )
}
