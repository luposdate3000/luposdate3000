package lupos.s04logicalOperators

import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s00misc.SanityCheck
import lupos.s04logicalOperators.Query

abstract class LOPBase(query: Query, operatorID: EOperatorID, classname: String, children: Array<OPBase>, sortPriority: ESortPriority) :
        OPBase(query, operatorID, classname, children, sortPriority) {
    override fun getPartitionCount(variable: String): Int = SanityCheck.checkUnreachable()
}
