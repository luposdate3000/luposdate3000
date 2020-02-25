package lupos.s09physicalOperators

import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s04logicalOperators.OPBase


abstract class POPBase : OPBase() {
    override val classname="POPBase"
    abstract val dictionary: ResultSetDictionary
}
