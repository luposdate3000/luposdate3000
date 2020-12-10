package lupos.s04logicalOperators
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s00misc.SanityCheck
abstract class LOPBase(query: IQuery, operatorID: EOperatorID, classname: String, children: Array<IOPBase>, sortPriority: ESortPriority) :
    OPBase(query, operatorID, classname, children, sortPriority) {
    override fun getPartitionCount(variable: String): Int = SanityCheck.checkUnreachable()
}
